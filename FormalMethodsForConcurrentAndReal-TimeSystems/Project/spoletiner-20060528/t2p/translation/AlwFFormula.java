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

import t2p.translation.TranslationUtilities.TemporalQuality;

/*******************************************************************************
 * A (sub)formula containg the AlwF operator.
 * @author bianculli
 * 
 */
public class AlwFFormula extends TrioFormula {

	private String pc_format = "\tif\n\t\t::ex_AlwF_%d ->\n\t\t\tif\n\t\t\t\t::!(%s) -> s=0; goto gen; \n"
			+ "\t\t\t\t::(%s) -> skip;\n\t\t\tfi;\n\t\t::!ex_AlwF_%d -> skip;\n\tfi;";

	private String child_logicE;

	private String extbc_format_e = "\t\t\t\t\tif\n\t\t\t\t\t\t::ex_AlwF_%d==0 -> \n\t\t\t\t\t\t\tex_AlwF_%d=1;" +
			"\n\t\t\t\t\t\t::ex_AlwF_%d!=0 ->skip;\n\t\t\t\t\tfi;";

	private String extbc_format_i = "\t\t\t\t\tif\n\t\t\t\t\t\t::ex_AlwF_%d==0 ->\n"
			+ "\t\t\t\t\t\t\tif\n\t\t\t\t\t\t\t\t::(%s) -> ex_AlwF_%d=1;\n\t\t\t\t\t\t\t\t::!(%s) -> flagAlwF_%d=0; s=0; goto gen;"
			+ "\n\t\t\t\t\t\t\tfi;\n\t\t\t\t\t\t::ex_AlwF_%d!=0 ->skip;\n\t\t\t\t\tfi;\n";

	private String lvars_format = "\nbool flagAlwF_%d=1;\nbool ex_AlwF_%d=0;";

	// var used in the child formula
	private String var;
	/***
	 * Constructs an AlwF formula.
	 * @param f the formula inside the operator
	 * @param id the value of the global AlwF formulae counter for this formula 
	 * @param isIn if the operator is AlwF_i or not
	 */
	public AlwFFormula(TrioFormula f, int id, boolean isIn) {

		this.temporalQuality = TemporalQuality.future;
		this.logicExpression = "flagAlwF_" + id;
		this.localVariables = String.format(this.lvars_format, id, id);
		this.child_logicE = f.getLogicExpression();

		if (f instanceof ExpressionFormula) {
			ExpressionFormula eq = (ExpressionFormula) f;
			this.var = eq.getLeft().eval();

		}

		this.processCode = String.format(this.pc_format, id, this.child_logicE,
				this.child_logicE, id);
		if (isIn) {

			this.bodyCode=this.externalBodyCode = String.format(this.extbc_format_i, id,
					this.child_logicE, id, this.child_logicE, id, id);
		} else {

			this.bodyCode=this.externalBodyCode = String.format(this.extbc_format_e, id, id,
					id);
		}

	}

	/***
	 * Helper method returning the variable used inside AlwF operator.
	 * @return the variable used inside AlwF operator
	 */
	public String getVar() {
		return var;
	}

}
