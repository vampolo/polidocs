/*
 spoletiner - a TRIO to Promela translator
 Copyright (C) 2006 Domenico Bianculli (bianculli@gmail.com) & Paola Spoletini (spoleti@elet.polimi.it)

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

 */
package t2p.translation;

import java.util.Map;

import t2p.translation.TranslationUtilities.TemporalQuality;

/**
 * A (sub)formula containing the Futr operator.
 * 
 * @author bianculli
 * 
 */
public class FutrFormula extends TrioFormula {
	private String pc_ee_format = "\nproctype Futr_%d(chan msg; byte proc){\nbyte i;\nbool val1, val2;\ni=0;\n"
			+ "\t\tdo\n\t\t\t::msg?val1,val2,eval(proc);\n"
			+ "\t\t\tif\n\t\t\t\t::!val1->goto stop;\n\t\t\t\t::val1->\n"
			+ "\t\t\t\t\t\tif\n"
			+ "\t\t\t\t\t\t\t::i==%d->i=0; msg!1,1,proc;\n"
			+ "\t\t\t\t\t\t\t::i<%d->i++;msg!1,0,proc;\n"
			+ "\t\t\t\t\t\tfi;\n\t\t\tfi;\n\t\tod;\n" + "stop: skip;\n}";

	private String pc_format = "\nproctype Futr_%d(chan msg; byte proc){\nbyte i;\nbool val1, val2;\ni=0;\n"
			+ "\t\tdo\n\t\t\t::msg?val1,val2,eval(proc);\n"
			+ "\t\t\tif\n\t\t\t\t::!val1->goto stop;\n\t\t\t\t::val1->\n"
			+ "\t\t\t\t\t\tif\n"
			+ "\t\t\t\t\t\t\t::i==%d&&(%s)->i=0; msg!1,1,proc;\n"
			+ "\t\t\t\t\t\t\t::i==%d&&!(%s)->i=0; msg!0,1,proc;\n"
			+ "\t\t\t\t\t\t\t::i<%d->i++;msg!1,0,proc;\n"
			+ "\t\t\t\t\t\tfi;\n\t\t\tfi;\n\t\tod;\n" + "stop: skip;\n}";

	// the tree parser assigned id of the subformula, when it is a lasts
	private int child_id;

	private String child_logicE;

	private String err_format = "\n\ti=0;\n\tdo\n\t\t::i<%d-> proc=%d+i;\n\t\t\tmsg!0,0,proc;\n\t\t\ti++;\n\t\t::i==%d-> break;\n\tod;\n";

	private String extbc_format = "\n\t\t\t\t\ti=0;\n\t\t\t\t\tdo\n\t\t\t\t\t\t::i<%d && ex_futr_%d[i]==0 -> ex_futr_%d[i]=1;break;\n"
			+ "\t\t\t\t\t\t::i==%d-> s=0; goto gen;\n\t\t\t\t\t\t::i<%d && ex_futr_%d[i]!=0 -> i++;\n\t\t\t\t\tod;\n";

	// ID of futr formula as matched by the tree parser
	private int id;

	private TranslationUtilities.Lasting interval;

	private String lvars_format = "\nbool ex_futr_%d[%d];";

	private String proc_st_format = "\ti=0;\n\tdo\n\t\t::i<%d-> proc=%d+i;\n\t\t\tif\n\t\t\t\t::ex_futr_%d[i]->\tmsg!1,1,proc;\n\t"
			+ "\t\t\t\tmsg?value,status,eval(proc);\n\t\t\t\t\tif\n\t\t\t\t\t\t::!value->flag=1;\n\t\t\t\t\t\t::value->skip;\n\t\t\t\t\tfi;\n\t"
			+ "\t\t\t\tif\n\t\t\t\t\t\t::status->ex_futr_%d[i]=0;\n\t\t\t\t\t\t::!status->skip;\n\t\t\t\t\tfi;\n\t"
			+ "\t\t\t::!ex_futr_%d[i]->skip;\n\t\t\tfi;\n\t\t\ti++;\n\t\t::i==%d->break;\n\tod;\n";

	private String proc_st_lasts_format = "\ti=0;\n\tdo\n\t\t::i<%d-> proc=%d+i;\n\t\t\tif\n\t\t\t\t::ex_futr_%d[i]->\tmsg!1,1,proc;\n\t"
			+ "\t\t\t\tmsg?value,status,eval(proc);\n"
			+ "\t\t\t\tif\n\t\t\t\t\t\t::status->ex_futr_%d[i]=0; ex_Lasts_%d=1; cont_lasts_%d=0;\n\t\t\t\t\t\t::!status->skip;\n\t\t\t\tfi;\n\t"
			+ "\t\t\t::!ex_futr_%d[i]->skip;\n\t\t\tfi;\n\t\t\ti++;\n\t\t::i==%d->break;\n\tod;\n";

	private String proc_st_untilw_format = "\ti=0;\n\tdo\n\t\t::i<%d-> proc=%d+i;\n\t\t\tif\n\t\t\t\t::ex_futr_%d[i]->\tmsg!1,1,proc;\n\t"
			+ "\t\t\t\tmsg?value,status,eval(proc);\n"
			+ "\t\t\t\tif\n\t\t\t\t\t\t::status->ex_futr_%d[i]=0; ex_UntilW_%d=1;\n\t\t\t\t\t\t::!status->skip;\n\t\t\t\tfi;\n\t"
			+ "\t\t\t::!ex_futr_%d[i]->skip;\n\t\t\tfi;\n\t\t\ti++;\n\t\t::i==%d->break;\n\tod;\n";

	private String procrun_format = "\ni=0;\ndo\n\t::i<%d-> proc=%d+i;\n\t\trun Futr_%d(msg,proc);\n\t\ti++;\n\t::i==%d->break;\nod;\n";

	// var used in the child formula
	private String var;

	/***
	 * Builds a Futr formula. 
	 * @param f the child formula
	 * @param t temporal instant of the formula
	 * @param id id of the formula
	 * @param interval interval of the Last formula
	 * @param child_id the child formula id
	 * @param constTable table for resolving constants name
	 */
	public FutrFormula(TrioFormula f, TermFormula t, int id,
			TranslationUtilities.Lasting interval, int child_id,
			Map<String, Integer> constTable) {

		this.temporalQuality = TemporalQuality.future;
		this.child_id = child_id;
		this.interval = interval;
		this.id = id;
		// retrieve TermFormula value
		String numericValue = t.eval();
		// suppose t represents a number
		try {
			this.temporalConstant = Integer.parseInt(numericValue);
		}
		// here t is not a number; check in constant Table
		catch (NumberFormatException ex) {
			if (constTable.containsKey(numericValue)) {
				temporalConstant = constTable.get(numericValue);
			} else {
				System.out.println("No suitable numeric instantion found for "
						+ numericValue);
				temporalConstant = 0;
			}
		}

		if (f instanceof ExpressionFormula) {
			ExpressionFormula eq = (ExpressionFormula) f;
			this.var = eq.getLeft().eval();

		}

		this.child_logicE = f.getLogicExpression();
		switch (this.interval) {
		case (no):
			this.processCode = String.format(this.pc_format, this.id,
					temporalConstant - 1, child_logicE, temporalConstant - 1,
					child_logicE, temporalConstant - 1);
			break;
		case (ee):
		case (ei):
			this.processCode = String.format(this.pc_ee_format, this.id,
					temporalConstant, temporalConstant);
			break;
		case (ie):
		case (ii):
			this.processCode = String.format(this.pc_ee_format, this.id,
					temporalConstant - 1, temporalConstant - 1);
			break;
		case (untilw):
			this.processCode = String.format(this.pc_ee_format, this.id,
					temporalConstant - 1, temporalConstant - 1);
			break;
		}

		this.localVariables = String.format(lvars_format, this.id,
				this.temporalConstant);
		this.bodyCode=this.externalBodyCode = String.format(extbc_format, temporalConstant,
				this.id, this.id, temporalConstant, temporalConstant, this.id);
	}

	public String getError(int procID) {
		this.error = String.format(err_format, temporalConstant, procID,
				temporalConstant);
		return error;
	}

	public String getProcessRun(int procID) {
		this.processRun = String.format(this.procrun_format, temporalConstant,
				procID, this.id, temporalConstant);
		return processRun;
	}

	public String getProcessStatus(int procID) {
		switch (this.interval) {
		case (no):
			this.processStatus = String.format(proc_st_format,
					temporalConstant, procID, this.id, this.id, this.id,
					temporalConstant);
			break;
		case (ei):
		case (ie):
		case (ii):
		case (ee):
			this.processStatus = String.format(this.proc_st_lasts_format,
					temporalConstant, procID, this.id, this.id, this.child_id,
					this.child_id, this.id, temporalConstant);
			break;
		case (untilw):
			this.processStatus = String.format(this.proc_st_untilw_format,
					temporalConstant, procID, this.id, this.id, this.child_id,
					this.id, temporalConstant);
			break;
		}

		return processStatus;
	}

	public String getVar() {
		return var;
	}

}
