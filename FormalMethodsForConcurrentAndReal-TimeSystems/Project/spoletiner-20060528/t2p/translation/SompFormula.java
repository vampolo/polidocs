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
 * A (sub)formula containing the Somp operator.
 * 
 * @author bianculli
 * 
 */
public class SompFormula extends TrioFormula {
	private String extbc_ext_format = "\tif\n\t\t::!flagSomP_%d && !memoSomP_%d ->"
			+ "\n\t\tif\n\t\t\t::%s -> memoSomP_%d=1;\n\t\t\t::!(%s)->skip;\n\t\tfi;"
			+ "\n\t\t::!flagSomP_%d && memoSomP_%d -> flagSomP_%d=1;"
			+ "\n\t\t::flagSomP_%d->skip;\n\tfi;\n";

	private String extbc_format = "\tif\n\t\t::!flagSomP_%d ->"
			+ "\n\t\tif\n\t\t\t::%s -> flagSomP_%d=1;\n\t\t\t::!(%s)->skip;\n\t\tfi;"
			+ "\n\t\t::flagSomP_%d->skip;\n\tfi;\n";

	private String lvars_ext_format = "\nbool flagSomP_%d=0;\nbool memoSomP_%d=0;";

	private String lvars_format = "\nbool flagSomP_%d=0;";
	
	private String bc_format="\tif\n\t\t::!(%s)->s=0; goto gen;\n\t\t::(%s)->skip;\n\tfi;\n";

	/***************************************************************************
	 * Builds a Somf formula
	 * 
	 * @param tf1
	 *            the subformula
	 * @param id
	 *            id of the fomula
	 * @param isIn
	 *            if the operator is Somf_i or not (i.e. Somf_e)
	 */
	public SompFormula(TrioFormula tf1, int id, boolean isIn) {
		this.temporalQuality = TemporalQuality.present_past;
		if (isIn) {
			this.localVariables = String.format(this.lvars_format, id);
			this.externalBodyCode = String.format(this.extbc_format, id,
					tf1.logicExpression, id, tf1.logicExpression, id);
		} else {
			this.localVariables = String.format(this.lvars_ext_format, id, id);
			this.externalBodyCode = String.format(this.extbc_ext_format, id,
					id, tf1.logicExpression, id, tf1.logicExpression, id, id,
					id, id);

		}
		this.logicExpression = "flagSomP_" + id;
		this.bodyCode=String.format(this.bc_format,this.logicExpression, this.logicExpression);
	}

}
