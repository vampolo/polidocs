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
 * A (sub)formula containing the Until_weak operator.
 * 
 * @author bianculli
 * 
 */
public class UntilWFormula extends TrioFormula {
	private String lvars_format = "\nbool ex_UntilW_%d=0;";

	private int id;

	private String pc_format = "\n\tif\n\t\t::ex_UntilW_%d ->\n\t\t\t\t\tif\n\t\t\t\t\t\t::%s -> \n\t\t\t\t\t\t\tex_UntilW_%d=0; \n"
			+ "\t\t\t\t\t\t::!(%s)->\n\t\t\t\t\t\t\tif\n\t\t\t\t\t\t\t\t::!(%s)->flag=1;\n\t\t\t\t\t\t\t\t::%s->skip;\n\t\t\t\t\t\t\tfi;"
			+ "\n\t\t\t\t\tfi;\n\t\t::!ex_UntilW_%d->skip;\n\tfi;\n";

	/***************************************************************************
	 * Builds an Until_weak formula
	 * 
	 * @param tf1
	 *            first subformula
	 * @param tf2
	 *            second subformula
	 * @param id
	 *            id of the fomula
	 */
	public UntilWFormula(TrioFormula tf1, TrioFormula tf2, int id) {
		super(tf1, tf2);
		this.id = id;
		this.temporalQuality = TemporalQuality.future;
		this.localVariables = String.format(this.lvars_format, id);
		this.processCode = String.format(this.pc_format, id, tf2
				.getLogicExpression(), id, tf2.getLogicExpression(), tf1
				.getLogicExpression(), tf1.getLogicExpression(), id);
		this.logicExpression = "ex_UntilW_" + id;
		this.bodyCode=this.externalBodyCode = "\t\t\t\t\t\tex_UntilW_" + id + "=1;";

	}

	/***************************************************************************
	 * @return Returns the id if the formula.
	 */
	public int getId() {
		return id;
	}

}
