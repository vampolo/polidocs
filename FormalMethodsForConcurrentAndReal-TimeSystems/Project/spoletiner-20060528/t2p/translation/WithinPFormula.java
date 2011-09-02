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
 * A (sub)formula containing the WithinP operator.
 * 
 * @author bianculli
 * 
 */
public class WithinPFormula extends TrioFormula {

	// var used in the child formula
	private String var;
	//real name of the variable; need for supporting symmetry
	private String varName;

	// right hand of child logic expression
	// var + child_logic == full child logicEx
	private String child_logicE;

	private String lvars_format = "\nbool flagWithinP_%d;";

	private String extbc_format = "\ti=0;\n\tdo\n\t\t::i<%d -> i++\n\t\t::i>=%d && i<%d ->\n\t\t\tif\n\t\t\t\t::(memo_%s[i]%s) -> flagWithinP_%d=1; break;\n"
			+ "\t\t\t\t::!(memo_%s[i]%s)->skip;\n\t\t\tfi;\n\t\t\ti++\n\t\t::i>=%d -> flagWithinP_%d=0; break;\n\tod;";
	
	private String bc_format="\tif\n\t\t::!(%s)->s=0; goto gen;\n\t\t::(%s)->skip;\n\tfi;\n";

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
	public WithinPFormula(TrioFormula f, TermFormula t, int id,
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
	public WithinPFormula(TrioFormula f, TermFormula t, int base, int id,
			TranslationUtilities.Lasting interval,
			Map<String, Integer> constTable) {
		this.temporalQuality = TemporalQuality.present_past;
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

		this.logicExpression = "flagWithinP_" + id;

		if (f instanceof ExpressionFormula) {
			ExpressionFormula eq = (ExpressionFormula) f;
			this.var = eq.getLeft().eval();
			varName=var;
			
			this.child_logicE = eq.getOperator() + eq.getRight().eval();
		}

		//int tempConstant_prime = temporalConstant;
		int basePrime = base;
		switch (interval) {
		case ee:
			temporalConstant--;
			//basePrime++;
			break;
		case ei:
			break;
		case ie:
			this.logicExpression = "(" + this.logicExpression;
			this.logicExpression += " || " + this.var + this.child_logicE + ")";
			temporalConstant--;
			//basePrime++;
			break;
		case ii:
			this.logicExpression = "(" + this.logicExpression;
			this.logicExpression += " || " + this.var + this.child_logicE + ")";
			break;
		}
		this.externalBodyCode = String.format(this.extbc_format, basePrime,
				basePrime, temporalConstant, this.varName, this.child_logicE, id,
				this.varName, this.child_logicE, temporalConstant, id);
		this.localVariables = String.format(this.lvars_format, id);
		this.bodyCode=String.format(this.bc_format,this.logicExpression, this.logicExpression);
	}

	/**
	 * @return Returns the variable used in the child formula.
	 */
	public String getVar() {
		return var;
	}
}
