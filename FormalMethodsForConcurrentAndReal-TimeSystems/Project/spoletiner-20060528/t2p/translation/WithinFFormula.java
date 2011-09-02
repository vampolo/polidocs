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
 * A (sub)formula containing the WithinF operator.
 * 
 * @author bianculli
 * 
 */
public class WithinFFormula extends TrioFormula {

	// ID of futr formula as matched by the tree parser
	private int id;

	// var used in the child formula
	private String var;

	private String child_logicE;

	private String pc_format_i = "\nproctype Withinf_%d(chan msg; byte proc){\nbyte i;\nbool val1, val2;\ni=0;\n"
			+ "\t\tdo\n\t\t\t::msg?val1,val2,eval(proc);\n"
			+ "\t\t\tif\n\t\t\t\t::!val1->goto stop;\n\t\t\t\t::val1->\n"
			+ "\t\t\t\t\t\tif\n"
			+ "\t\t\t\t\t\t\t::i<%d->i++;msg!1,0,proc;\n"
			+ "\t\t\t\t\t\t\t::i>=%d && i<%d->\n"
			+ "\t\t\t\t\t\t\t\tif\n"
			+ "\t\t\t\t\t\t\t\t\t::%s->i=0; msg!1,1,proc;\n"
			+ "\t\t\t\t\t\t\t\t\t::!(%s)->i++; msg!1,0,proc;\n"
			+ "\t\t\t\t\t\t\t\tfi\n"
			+ "\t\t\t\t\t\t\t::i==%d && (%s) ->i=0; msg!1,1,proc;\n"
			+ "\t\t\t\t\t\t\t::i==%d && !(%s)->i=0; msg!0,1,proc;\n"
			+ "\t\t\t\t\t\tfi;\n\t\t\tfi;\n\t\tod;\n" + "stop: skip;\n}";

	private String procrun_format = "\ni=0;\ndo\n\t::i<%d-> proc=%d+i;\n\t\trun Withinf_%d(msg,proc);\n\t\ti++;\n\t::i==%d->break;\nod;\n";

	private String lvars_format = "\nbool ex_withinF_%d[%d];";

	private String err_format = "\n\ti=0;\n\tdo\n\t\t::i<%d-> proc=%d+i;\n\t\t\tmsg!0,0,proc;\n\t\t\ti++;\n\t\t::i==%d-> break;\n\tod;\n";

	private String extbc_format = "\n\t\t\t\t\tif"
			+ "\n\t\t\t\t\t\t::!(%s)->"
			+ "\n\t\t\t\t\t\t\ti=0;\n\t\t\t\t\t\t\tdo\n\t\t\t\t\t\t\t\t::i<%d && ex_withinF_%d[i]==0 -> ex_withinF_%d[i]=1;break;\n"
			+ "\t\t\t\t\t\t\t\t::i==%d-> s=0; goto gen;\n\t\t\t\t\t\t\t\t::i<%d && ex_withinF_%d[i]!=0 -> i++;\n\t\t\t\t\t\t\tod;\n"
			+ "\t\t\t\t\t\t::(%s)->skip;\n\t\t\t\t\tfi;";

	private String extbc_format_e = "\n\t\t\t\t\t\t\ti=0;\n\t\t\t\t\t\t\tdo\n\t\t\t\t\t\t\t\t::i<%d && ex_withinF_%d[i]==0 -> ex_withinF_%d[i]=1;break;\n"
			+ "\t\t\t\t\t\t\t\t::i==%d-> s=0; goto gen;\n\t\t\t\t\t\t\t\t::i<%d && ex_withinF_%d[i]!=0 -> i++;\n\t\t\t\t\t\t\tod;\n";

	private String proc_st_format = "\ti=0;\n\tdo\n\t\t::i<%d-> proc=%d+i;\n\t\t\tif\n\t\t\t\t::ex_withinF_%d[i]->\tmsg!1,1,proc;\n\t"
			+ "\t\t\t\tmsg?value,status,eval(proc);\n\t\t\t\t\tif\n\t\t\t\t\t\t::!value->flag=1;\n\t\t\t\t\t\t::value->skip;\n\t\t\t\t\tfi;\n\t"
			+ "\t\t\t\tif\n\t\t\t\t\t\t::status->ex_withinF_%d[i]=0;\n\t\t\t\t\t\t::!status->skip;\n\t\t\t\t\tfi;\n\t"
			+ "\t\t\t::!ex_withinF_%d[i]->skip;\n\t\t\tfi;\n\t\t\ti++;\n\t\t::i==%d->break;\n\tod;\n";

	private TranslationUtilities.Lasting interval;

	/**
	 * Builds a WithinF formula
	 * 
	 * @param f
	 *            the child formula
	 * @param t
	 *            temporal expression of the formula
	 * @param id
	 *            id of the formula
	 * @param interval
	 *            interval of the formula
	 * @param constTable
	 *            table for resolving constants name
	 */
	public WithinFFormula(TrioFormula f, TermFormula t, int id,
			TranslationUtilities.Lasting interval,
			Map<String, Integer> constTable) {
		this(f, t, 0, id, interval, constTable);
	}

	/**
	 * Builds a WithinF formula
	 * 
	 * @param f
	 *            the child formula
	 * @param t
	 *            temporal expression of the formula
	 * @param base
	 *            base instant of the formula
	 * @param id
	 *            id of the formula
	 * @param interval
	 *            interval of the formula
	 * @param constTable
	 *            table for resolving constants name
	 */
	public WithinFFormula(TrioFormula f, TermFormula t, int base, int id,
			TranslationUtilities.Lasting interval,
			Map<String, Integer> constTable) {
		this.temporalQuality = TemporalQuality.future;
		this.interval = interval;
		this.id = id;
		// retrieve TermFormula value
		String numericValue = t.eval();
		// suppose t represents a number
		try {
			this.temporalConstant = Integer.parseInt(numericValue) + base;
		}
		// here t is not a number; check in constant Table
		catch (NumberFormatException ex) {
			if (constTable.containsKey(numericValue)) {
				temporalConstant = constTable.get(numericValue) + base;
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

		this.localVariables = String.format(lvars_format, this.id,
				this.temporalConstant);
		this.child_logicE = f.getLogicExpression();
		switch (this.interval) {
		case (ii):
			this.processCode = String.format(this.pc_format_i, this.id, base,
					base, temporalConstant, child_logicE, child_logicE,
					temporalConstant, child_logicE, temporalConstant,
					child_logicE);
			break;
		case (ei):
			this.processCode = String.format(this.pc_format_i, this.id, base + 1,
					base + 1, temporalConstant, child_logicE, child_logicE,
					temporalConstant, child_logicE, temporalConstant,
					child_logicE);
			break;
		case (ee):
			this.processCode = String.format(this.pc_format_i, this.id, base + 1,
					base + 1, temporalConstant - 1, child_logicE, child_logicE,
					temporalConstant - 1, child_logicE, temporalConstant - 1,
					child_logicE);
			break;
		case (ie):
			this.processCode = String.format(this.pc_format_i, this.id, base,
					base, temporalConstant - 1, child_logicE, child_logicE,
					temporalConstant - 1, child_logicE, temporalConstant - 1,
					child_logicE);
			break;
		}

		switch (this.interval) {
		case (ii):
		case (ie):
			this.bodyCode=this.externalBodyCode = String.format(extbc_format, child_logicE,
					temporalConstant - 1, this.id, this.id,
					temporalConstant - 1, temporalConstant - 1, this.id,
					child_logicE);
			break;
		case (ee):
		case (ei):
			this.bodyCode=this.externalBodyCode = String.format(extbc_format_e,
					temporalConstant - 1, this.id, this.id,
					temporalConstant - 1, temporalConstant - 1, this.id);
			break;
		}

	}

	@Override
	public String getProcessRun(int procID) {
		this.processRun = String.format(this.procrun_format,
				temporalConstant - 1, procID, this.id, temporalConstant - 1);
		return processRun;
	}

	@Override
	public String getError(int procID) {

		this.error = String.format(err_format, temporalConstant - 1, procID,
				temporalConstant - 1);
		return error;
	}

	@Override
	public String getProcessStatus(int procID) {
		this.processStatus = String.format(proc_st_format,
				temporalConstant - 1, procID, this.id, this.id, this.id,
				temporalConstant - 1);
		return processStatus;
	}

	@Override
	public int getTemporalConstant() {
		switch (this.interval) {
		case (ei):
		case (ii):
			return super.getTemporalConstant();
		case (ee):
		case (ie):
			return super.getTemporalConstant() - 1;
		}
		return 0;
	}

	public String getVar() {
		return var;
	}

}
