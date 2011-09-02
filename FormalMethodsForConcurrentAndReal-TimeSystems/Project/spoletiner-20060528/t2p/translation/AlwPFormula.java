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
 * A (sub)formula containg the AlwP operator.
 * 
 * @author bianculli
 * 
 */
public class AlwPFormula extends TrioFormula {

	private String extbc_format_e = "\tif\n\t\t::ctrAlwP_%d==0 -> flagAlwP_%d=0;\n\t\t::ctrAlwP_%d!=0 ->skip;\n\tfi;\n"
			+ "\tif\n\t\t::%s && ctrAlwP_%d==1 -> ctrAlwP_%d=1;"
			+ "\n\t\t::!(%s) && ctrAlwP_%d==1 -> ctrAlwP_%d=0;"
			+ "\n\t\t::ctrAlwP_%d!=1-> skip;\n\tfi;";

	private String extbc_format_i = "\tif\n\t\t::%s && flagAlwP_%d==1 -> flagAlwP_%d=1;"
			+ "\n\t\t::!(%s) && flagAlwP_%d==1 -> flagAlwP_%d=0;"
			+ "\n\t\t::flagAlwP_%d!=1-> skip;\n\tfi;";

	private String lvars_format_e = "\nbool flagAlwP_%d=1;\nbool ctrAlwP_%d=1;";

	private String lvars_format_i = "\nbool flagAlwP_%d=1;";
	
	private String bc_format="\tif\n\t\t::!%s->s=0; goto gen;\n\t\t::%s->skip;\n\tfi;\n";

	/***
	 * Constructs an AlwP formula.
	 * @param f the formula inside the operator
	 * @param id the value of the global AlwF formulae counter for this formula
	 * @param isIn if the operator is AlwP_i or not
	 */
	public AlwPFormula(TrioFormula f, int id, boolean isIn) {
		this.temporalQuality = TemporalQuality.present_past;
		this.logicExpression = "flagAlwP_" + id;

		if (isIn) {
			this.localVariables = String.format(this.lvars_format_i, id);
			this.externalBodyCode = String.format(this.extbc_format_i,
					f.logicExpression, id, id, f.logicExpression, id, id, id);
		} else {
			this.localVariables = String.format(this.lvars_format_e, id, id);
			this.externalBodyCode = String.format(this.extbc_format_e, id, id,
					id, f.logicExpression, id, id, f.logicExpression, id, id,
					id);
		}
		
		this.bodyCode=String.format(this.bc_format,this.logicExpression, this.logicExpression);

	}

}
