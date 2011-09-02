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

/***
 * A (sub)formula, not root, having IFF as operator.
 * 
 * @author bianculli
 */
public class IffFormula extends TrioFormula {
	/***
	 * Constructs an IFF formula given its children
	 * 
	 * @param tf1
	 *            the left child
	 * @param tf2
	 *            the right child
	 * @param tf1_neg
	 *            left child negated
	 * @param tf2_neg
	 *            right child negated
	 */
	public IffFormula(TrioFormula tf1, TrioFormula tf2, TrioFormula tf1_neg,
			TrioFormula tf2_neg) {
		super(tf1, tf2);
		this.children.add(tf1_neg);
		this.children.add(tf2_neg);
		this.temporalQuality = TranslationUtilities.computeTemporalQuality(tf1
				.getTemporalQuality(), tf2.getTemporalQuality());
	}

	/***
	 * Constructs an IFF formula given its children
	 * 
	 * @param tf1
	 *            the left child
	 * @param tf2
	 *            the right child
	 * @param tf_neg
	 *            a child negated
	 */
	public IffFormula(TrioFormula tf1, TrioFormula tf2, TrioFormula tf_neg) {
		this(tf1, tf2, tf_neg, null);
	}

	/**
	 * Constructs an IFF formula given its children
	 * 
	 * @param tf1
	 *            the left child
	 * @param tf2
	 *            the right child
	 */
	public IffFormula(TrioFormula tf1, TrioFormula tf2) {
		this(tf1, tf2, null, null);
	}

	@Override
	public String getExternalBodyCode() {
		StringBuilder s = new StringBuilder();
		TrioFormula left = this.children.get(0);
		TrioFormula right = this.children.get(1);
		TrioFormula left_neg = this.children.get(2);
		TrioFormula right_neg = this.children.get(3);

		// formula future IFF future
		if (left.getTemporalQuality() == TemporalQuality.future
				&& right.getTemporalQuality() == TemporalQuality.future) {
			s.append("\n\t\t\tif");
			s.append("\n\t\t\t\t ::\n");
			s.append(left.getExternalBodyCode());
			s.append(right.getExternalBodyCode());
			s.append("\n\t\t\t\t ::\n");
			s.append(left_neg.getExternalBodyCode());
			s.append(right_neg.getExternalBodyCode());
			s.append("\n\t\t\tfi;");
		} else
		// formula past IFF future
		if (right.getTemporalQuality() == TemporalQuality.future) {
			s.append(this.pastAndFutureCode(left, right));
		}
		// formula future IFF past
		else if (left.getTemporalQuality() == TemporalQuality.future) {
			s.append(this.pastAndFutureCode(right, left));
		}
		// past /present
		else {
			s.append("\n\t\t\tif");
			s.append("\n\t\t\t\t::!((" + left.getLogicExpression() + " && "
					+ right.getLogicExpression() + ") || (!("
					+ left.getLogicExpression() + ") && !("
					+ right.getLogicExpression() + ")))-> s=0; goto gen;\n");
			s.append("\n\t\t\t\t::((" + left.getLogicExpression() + " && "
					+ right.getLogicExpression() + ") || (!("
					+ left.getLogicExpression() + ") && !("
					+ right.getLogicExpression() + ")))->skip;\n");
			s.append("\n\t\t\tfi;");
		}
		return s.toString();
	}

	private String pastAndFutureCode(TrioFormula past, TrioFormula futr) {
		StringBuilder s = new StringBuilder();
		s.append("\n\t\t\tif");
		s.append("\n\t\t\t\t::" + past.getLogicExpression() + "->\n");
		s.append(futr.getExternalBodyCode());
		s.append("\n\t\t\t\t:: !(" + past.getLogicExpression() + ")->\n");
		s.append(this.children.get(2).getExternalBodyCode());
		s.append("\n\t\t\tfi;");
		return s.toString();
	}

}
