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

/*******************************************************************************
 * A (sub)formula containing the Lasts operator.
 * 
 * @author bianculli
 * 
 */
public class PastFormula extends TrioFormula {
	// var used in the child formula
	private String var;
	private String varName;
	
	private String bc_format="\tif\n\t\t::!(%s)->s=0; goto gen;\n\t\t::(%s)->skip;\n\tfi;\n";
	/***************************************************************************
	 * Builds a Past formula.
	 * 
	 * @param f
	 *            the child formula
	 * @param t
	 *            temporal expression of the formula
	 * @param constTable
	 *            table for resolving constants name
	 */
	public PastFormula(TrioFormula f, TermFormula t,
			Map<String, Integer> constTable) {
		this.temporalQuality = TemporalQuality.present_past;
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
			
			varName=var;
			
			this.logicExpression = "memo_" + this.varName + "["
					+ (this.temporalConstant - 1) + "]" + eq.getOperator()
					+ eq.getRight().eval();
		}

		this.bodyCode=String.format(this.bc_format,this.logicExpression, this.logicExpression);
	}

	/***************************************************************************
	 * @return Returns the var.
	 */
	public String getVar() {
		return var;
	}

}
