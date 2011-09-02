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

/**
 * A formula representing initialization
 * 
 * @author bianculli
 * 
 */
public class NowFormula extends TrioFormula {

	// type missing (depending on embedded formula)
	private String lvars_format = "start_%d=1;";

	private String pc_somfe_format = "\n\tif\n\t\t::start_%d==1->start_%d=2;"
			+ "\n\t\t::start_%d==2->%s;start_%d=0;"
			+ "\n\t\t::start_%d==0->skip;\n\tfi;\n";

	private String pc_somfi_format = "\n\tif\n\t\t::start_%d==1->%s;start_%d=0;"
			+ "\n\t\t::start_%d==0->skip;\n\tfi;\n";
	
	private String pc_format="\n\tif\n\t\t::start_%d==1->\n\t\t\tif\n\t\t\t\t::!(%s)-> s=0; goto gen;\n\t\t\t\t::(%s)->start_%d=0;\n\t\t\tfi;" +
			 "\n\t\t::!(start_%d==1)->skip;\n\tfi;\n";

	public NowFormula(TrioFormula tf, int id) {
		this.temporalQuality=tf.getTemporalQuality();
		//match different types of formulae
		if (tf instanceof SomfFormula) {
			boolean isIn = ((SomfFormula) tf).isIn();
			if (isIn) {
				this.localVariables = "\nbool "
						+ String.format(lvars_format, id);
				this.processCode = String.format(pc_somfi_format, id, tf
						.getLogicExpression()+"=1", id, id);
			} else {
				this.localVariables = "\nbyte "
						+ String.format(lvars_format, id);
				this.processCode = String.format(pc_somfe_format, id, id, id,
						tf.getLogicExpression()+"=1", id, id);
			}
			this.externalBodyCode = this.bodyCode = tf.getBodyCode();

		} else {
			//TODO check for not supported formulae
			this.localVariables = "\nbool "	+ String.format(lvars_format, id);
			this.processCode= String.format(pc_format, id, tf.getLogicExpression(), tf.getLogicExpression(), id, id);
			this.externalBodyCode = this.bodyCode = tf.getBodyCode();
		}

	}
}
