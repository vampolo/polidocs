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
 * A (sub)formula without a temporal operator.
 * 
 * @author bianculli
 * 
 */
public class ExpressionFormula extends TrioFormula {

	private TermFormula left;

	private String operator;

	private TermFormula right;
	/***
	 * Constructs a formula given its terms and the operator
	 * @param left the left term of the formula
	 * @param right the right term of the formula
	 * @param operator the operator in the formula
	 */
	public ExpressionFormula(TermFormula left, TermFormula right,
			String operator) {
		this.temporalQuality = TemporalQuality.present_past;
		this.left = left;
		this.right = right;
		this.operator = operator;
		this.logicExpression = left.eval() + operator + right.eval();

	}

	/**
	 * @return Returns the left term of the formula.
	 */
	public TermFormula getLeft() {
		return left;
	}

	/**
	 * @return the (non-temporal) operator of the formula
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @return Returns the right term of the formula.
	 */
	public TermFormula getRight() {
		return right;
	}

	@Override
	public void negate() {
		this.operator = negateOperator(this.operator);
		// rebuild logic expression
		this.logicExpression = this.left.eval() + this.operator
				+ this.right.eval();
	}

	private String negateOperator(String operator) {
		if (operator.equals("=="))
			return "!=";
		if (operator.equals("!="))
			return "==";
		if (operator.equals(">"))
			return "<=";
		if (operator.equals(">="))
			return "<";
		if (operator.equals("<"))
			return ">=";
		if (operator.equals("<="))
			return ">=";
		return operator;
	}

}
