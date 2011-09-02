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
 * A (sub)formula containing the Somf operator.
 * 
 * @author bianculli
 * 
 */
public class SomfFormula extends TrioFormula {
	private String lvars_format = "\nbool ex_SomF_%d=0;";

	private String pc_format = "\n\tif\n\t\t::ex_SomF_%d ->\n\t\t\t\t\tif\n\t\t\t\t\t\t::%s -> \n\t\t\t\t\t\t\tprogress_S_%d: ex_SomF_%d=0; \n"
			+ "\t\t\t\t\t\t::!(%s)->skip;"
			+ "\n\t\t\t\t\tfi;\n\t\t::!ex_SomF_%d->skip;\n\tfi;\n";
	
	private boolean isIn;

	/**
	 *  If the formula is is a <code>Somf_i</code> or a <code>Somf_e</code>.
	 * @return <code>true</code> if the formula is <code>Somf_i</code>, <code>false</code> otherwise
	 */
	public boolean isIn() {
		return isIn;
	}

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
	public SomfFormula(TrioFormula tf1, int id, boolean isIn) {
		this.isIn=isIn;
		this.temporalQuality = TemporalQuality.future;
		this.localVariables = String.format(this.lvars_format, id);
		this.processCode = String.format(this.pc_format, id, tf1
				.getLogicExpression(), id, id, tf1.getLogicExpression(), id);
		this.logicExpression = "ex_SomF_" + id;
		if (isIn) {
			this.bodyCode=this.externalBodyCode = "\n\t\t\t\t\t\tif\n\t\t\t\t\t\t\t::!("
					+ tf1.getLogicExpression() + ") -> ex_SomF_" + id
					+ "=1;\n\t\t\t\t\t\t\t::(" + tf1.getLogicExpression()
					+ ")->skip;\n\t\t\t\t\t\tfi;";
		} else {
			this.bodyCode=this.externalBodyCode = "\n\t\t\t\t\t\tex_SomF_" + id + "=1;";
		}

	}

}
