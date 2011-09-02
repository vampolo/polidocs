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
package t2p.frontend;

import antlr.collections.AST;

/*******************************************************************************
 * A collection of helper methods for manipulating ASTs representing TRIO
 * formulae.
 * 
 * @author bianculli
 * 
 */
public class FrontendUtilities {

	/***************************************************************************
	 * Given the parse tree corresponding to the input file of the program,
	 * returns an array containing the ASTs for each formula in the
	 * specification.
	 * 
	 * @param parseTree
	 *            the AST obtained by parsing a Trio2Promela input file
	 * @return an array containing the AST for each formula in the specification
	 */
	public static AST[] getFormulaeASTs(AST parseTree) {
		// division by 2 remove end-formula SEMI siblings
		int nformulae = parseTree.getNumberOfChildren() / 2;

		AST[] formulae = new AST[nformulae];

		// handle first formula
		AST temp = parseTree.getFirstChild();
		// discard formula name; sub-formula root is the operator
		formulae[0] = temp.getFirstChild();
		// semi is the SEMI node
		AST semi = temp.getNextSibling();

		// if there are more than 1 formulae
		for (int i = 1; i < nformulae; i++) {
			// get next formula and discard its name
			temp = semi.getNextSibling();
			formulae[i] = temp.getFirstChild();
			// semi is the SEMI node
			semi = temp.getNextSibling();
		}

		// mark root node with ROOT attribute
		formulae = FrontendUtilities.setRootType(formulae);

		return formulae;
	}

	private static AST[] setRootType(AST[] formulae) {
		for (int i = 0; i < formulae.length; i++) {
			switch (formulae[i].getType()) {
			case T2PExpTokenTypes.AND:
				formulae[i].setType(T2PExpTokenTypes.AND_ROOT);
				break;
			case T2PExpTokenTypes.OR:
				formulae[i].setType(T2PExpTokenTypes.OR_ROOT);
				break;
			case T2PExpTokenTypes.IF:
				formulae[i].setType(T2PExpTokenTypes.IF_ROOT);
				break;
			case T2PExpTokenTypes.IFF:
				formulae[i].setType(T2PExpTokenTypes.IFF_ROOT);
				break;
			}
		}
		return formulae;
	}

}
