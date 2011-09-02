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
 * A (sub)formula containing the Since operator.
 * 
 * @author bianculli
 * 
 */
public class SinceFormula extends TrioFormula {

	private String lvars_format = "\nbool flagSince_%d=0;\nbool suppSince_%d=0;";

	private String lvars_weak_format = "\nbool flagSince_%d=0;\nbool suppSince_%d=0;\nbool ftSinceW_%d=0;";

	private String extbc_format = "\tif\n\t\t::%s && suppSince_%d -> flagSince_%d=1;"
			+ "\n\t\t::!(%s) && suppSince_%d -> flagSince_%d=0; suppSince_%d=0;"
			+ "\n\t\t::!suppSince_%d->skip;\n\tfi;"
			+ "\n\tif\n\t\t::%s -> flagSince_%d=1; suppSince_%d=1;"
			+ "\n\t\t::!(%s)->skip;\n\tfi;\n";

	private String extbc_weak_format = "\tif\n\t\t::!ftSinceW_%d && %s -> ftSinceW_%d=1; suppSince_%d=1;"
			+ "\n\t\t::!ftSinceW_%d && !(%s) -> ftSinceW_%d=1;"
			+ "\n\t\t::ftSincew_%d->skip;\n\tfi;\n"
			+ "\tif\n\t\t::%s && suppSince_%d -> flagSince_%d=1;"
			+ "\n\t\t::!(%s) && suppSince_%d -> flagSince_%d=0; suppSince_%d=0;"
			+ "\n\t\t::!suppSince_%d->skip;\n\tfi;"
			+ "\n\tif\n\t\t::%s -> flagSince_%d=1; suppSince_%d=1;"
			+ "\n\t\t::!(%s)->skip;\n\tfi;\n";

	private String bc_format="\tif\n\t\t::!%s->s=0; goto gen;\n\t\t::%s->skip;\n\tfi;\n";
	
	/***************************************************************************
	 * Builds a Since formula.
	 * 
	 * @param tf1
	 *            first subformula
	 * @param tf2
	 *            second subformula
	 * @param id
	 *            id of the formula
	 * @param isWeak
	 *            if the operator is in the weak form or not
	 */
	public SinceFormula(TrioFormula tf1, TrioFormula tf2, int id, boolean isWeak) {
		this.temporalQuality = TemporalQuality.present_past;
		if (isWeak) {
			this.localVariables = String.format(this.lvars_weak_format, id, id,
					id);
			this.externalBodyCode = String.format(this.extbc_weak_format, id,
					tf1.logicExpression, id, id, id, tf1.logicExpression, id,
					id, tf1.logicExpression, id, id, tf1.logicExpression, id,
					id, id, id, tf2.logicExpression, id, id,
					tf2.logicExpression);
		} else {
			this.localVariables = String.format(this.lvars_format, id, id);
			this.externalBodyCode = String.format(this.extbc_format,
					tf1.logicExpression, id, id, tf1.logicExpression, id, id,
					id, id, tf2.logicExpression, id, id, tf2.logicExpression);
		}
		this.logicExpression = "flagSince_" + id;
		this.bodyCode=String.format(this.bc_format,this.logicExpression, this.logicExpression);
	}

}
