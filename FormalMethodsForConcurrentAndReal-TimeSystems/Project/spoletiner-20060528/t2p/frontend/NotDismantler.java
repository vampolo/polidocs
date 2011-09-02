// $ANTLR : "NotDismantler.g" -> "NotDismantler.java"$

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

import antlr.TreeParser;
import antlr.Token;
import antlr.collections.AST;
import antlr.RecognitionException;
import antlr.ANTLRException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.collections.impl.BitSet;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

import t2p.translation.*;


/***
A dismantler for the not  operator.
Pushes the NOT operator downward the formula and applies De Morgan's rules.
*/
public class NotDismantler extends antlr.TreeParser       implements NotDismantlerTokenTypes
 {
public NotDismantler() {
	tokenNames = _tokenNames;
}

	public final void trioformula(AST _t) throws RecognitionException {
		
		AST trioformula_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST trioformula_AST = null;
		AST if2_tf1_AST = null;
		AST if2_tf1 = null;
		AST if2_tf2_AST = null;
		AST if2_tf2 = null;
		AST if_tf1_AST = null;
		AST if_tf1 = null;
		AST if_tf2_AST = null;
		AST if_tf2 = null;
		AST n_and_tf1_AST = null;
		AST n_and_tf1 = null;
		AST n_and_tf2_AST = null;
		AST n_and_tf2 = null;
		AST n_or_tf1_AST = null;
		AST n_or_tf1 = null;
		AST n_or_tf2_AST = null;
		AST n_or_tf2 = null;
		AST n_andR_tf1_AST = null;
		AST n_andR_tf1 = null;
		AST n_andR_tf2_AST = null;
		AST n_andR_tf2 = null;
		AST n_orR_tf1_AST = null;
		AST n_orR_tf1 = null;
		AST n_orR_tf2_AST = null;
		AST n_orR_tf2 = null;
		AST n_lee_tf_AST = null;
		AST n_lee_tf = null;
		AST n_lee_t_AST = null;
		AST n_lee_t = null;
		AST n_lei_tf_AST = null;
		AST n_lei_tf = null;
		AST n_lei_t_AST = null;
		AST n_lei_t = null;
		AST n_lie_tf_AST = null;
		AST n_lie_tf = null;
		AST n_lie_t_AST = null;
		AST n_lie_t = null;
		AST n_lii_tf_AST = null;
		AST n_lii_tf = null;
		AST n_lii_t_AST = null;
		AST n_lii_t = null;
		AST n_ldee_tf_AST = null;
		AST n_ldee_tf = null;
		AST n_ldee_t_AST = null;
		AST n_ldee_t = null;
		AST n_ldei_tf_AST = null;
		AST n_ldei_tf = null;
		AST n_ldei_t_AST = null;
		AST n_ldei_t = null;
		AST n_ldie_tf_AST = null;
		AST n_ldie_tf = null;
		AST n_ldie_t_AST = null;
		AST n_ldie_t = null;
		AST n_ldii_tf_AST = null;
		AST n_ldii_tf = null;
		AST n_ldii_t_AST = null;
		AST n_ldii_t = null;
		AST n_wfee_tf_AST = null;
		AST n_wfee_tf = null;
		AST n_wfee_t_AST = null;
		AST n_wfee_t = null;
		AST n_wfei_tf_AST = null;
		AST n_wfei_tf = null;
		AST n_wfei_t_AST = null;
		AST n_wfei_t = null;
		AST n_wfie_tf_AST = null;
		AST n_wfie_tf = null;
		AST n_wfie_t_AST = null;
		AST n_wfie_t = null;
		AST n_wfii_tf_AST = null;
		AST n_wfii_tf = null;
		AST n_wfii_t_AST = null;
		AST n_wfii_t = null;
		AST n_wpee_tf_AST = null;
		AST n_wpee_tf = null;
		AST n_wpee_t_AST = null;
		AST n_wpee_t = null;
		AST n_wpei_tf_AST = null;
		AST n_wpei_tf = null;
		AST n_wpei_t_AST = null;
		AST n_wpei_t = null;
		AST n_wpie_tf_AST = null;
		AST n_wpie_tf = null;
		AST n_wpie_t_AST = null;
		AST n_wpie_t = null;
		AST n_wpii_tf_AST = null;
		AST n_wpii_tf = null;
		AST n_wpii_t_AST = null;
		AST n_wpii_t = null;
		AST n_alwfe_tf_AST = null;
		AST n_alwfe_tf = null;
		AST n_alwfi_tf_AST = null;
		AST n_alwfi_tf = null;
		AST n_alwpe_tf_AST = null;
		AST n_alwpe_tf = null;
		AST n_alwpi_tf_AST = null;
		AST n_alwpi_tf = null;
		AST n_f_tf_AST = null;
		AST n_f_tf = null;
		AST n_f_t_AST = null;
		AST n_f_t = null;
		AST n_no_tf_AST = null;
		AST n_no_tf = null;
		AST n_somfe_tf_AST = null;
		AST n_somfe_tf = null;
		AST n_somfi_tf_AST = null;
		AST n_somfi_tf = null;
		AST n_sompe_tf_AST = null;
		AST n_sompe_tf = null;
		AST n_sompi_tf_AST = null;
		AST n_sompi_tf = null;
		AST b_tf_AST = null;
		AST b_tf = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case IFF:
			{
				AST __t113 = _t;
				AST tmp1_AST = null;
				AST tmp1_AST_in = null;
				tmp1_AST = astFactory.create((AST)_t);
				tmp1_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp1_AST);
				ASTPair __currentAST113 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,IFF);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST113;
				_t = __t113;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case IF:
			{
				AST __t126 = _t;
				AST tmp2_AST = null;
				AST tmp2_AST_in = null;
				tmp2_AST = astFactory.create((AST)_t);
				tmp2_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp2_AST);
				ASTPair __currentAST126 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,IF);
				_t = _t.getFirstChild();
				if2_tf1 = _t==ASTNULL ? null : (AST)_t;
				trioformula(_t);
				_t = _retTree;
				if2_tf1_AST = (AST)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if2_tf2 = _t==ASTNULL ? null : (AST)_t;
				trioformula(_t);
				_t = _retTree;
				if2_tf2_AST = (AST)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST126;
				_t = __t126;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case IF_ROOT:
			{
				AST __t127 = _t;
				AST tmp3_AST = null;
				AST tmp3_AST_in = null;
				tmp3_AST = astFactory.create((AST)_t);
				tmp3_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp3_AST);
				ASTPair __currentAST127 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,IF_ROOT);
				_t = _t.getFirstChild();
				if_tf1 = _t==ASTNULL ? null : (AST)_t;
				trioformula(_t);
				_t = _retTree;
				if_tf1_AST = (AST)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				if_tf2 = _t==ASTNULL ? null : (AST)_t;
				trioformula(_t);
				_t = _retTree;
				if_tf2_AST = (AST)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST127;
				_t = __t127;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case OR:
			{
				AST __t128 = _t;
				AST tmp4_AST = null;
				AST tmp4_AST_in = null;
				tmp4_AST = astFactory.create((AST)_t);
				tmp4_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp4_AST);
				ASTPair __currentAST128 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,OR);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST128;
				_t = __t128;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case OR_ROOT:
			{
				AST __t129 = _t;
				AST tmp5_AST = null;
				AST tmp5_AST_in = null;
				tmp5_AST = astFactory.create((AST)_t);
				tmp5_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp5_AST);
				ASTPair __currentAST129 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,OR_ROOT);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST129;
				_t = __t129;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case AND:
			{
				AST __t130 = _t;
				AST tmp6_AST = null;
				AST tmp6_AST_in = null;
				tmp6_AST = astFactory.create((AST)_t);
				tmp6_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp6_AST);
				ASTPair __currentAST130 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,AND);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST130;
				_t = __t130;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case AND_ROOT:
			{
				AST __t131 = _t;
				AST tmp7_AST = null;
				AST tmp7_AST_in = null;
				tmp7_AST = astFactory.create((AST)_t);
				tmp7_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp7_AST);
				ASTPair __currentAST131 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,AND_ROOT);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST131;
				_t = __t131;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case EQ:
			{
				AST __t373 = _t;
				AST tmp8_AST = null;
				AST tmp8_AST_in = null;
				tmp8_AST = astFactory.create((AST)_t);
				tmp8_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp8_AST);
				ASTPair __currentAST373 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,EQ);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST373;
				_t = __t373;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case NEQ:
			{
				AST __t374 = _t;
				AST tmp9_AST = null;
				AST tmp9_AST_in = null;
				tmp9_AST = astFactory.create((AST)_t);
				tmp9_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp9_AST);
				ASTPair __currentAST374 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,NEQ);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST374;
				_t = __t374;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LT:
			{
				AST __t375 = _t;
				AST tmp10_AST = null;
				AST tmp10_AST_in = null;
				tmp10_AST = astFactory.create((AST)_t);
				tmp10_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp10_AST);
				ASTPair __currentAST375 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LT);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST375;
				_t = __t375;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case GT:
			{
				AST __t376 = _t;
				AST tmp11_AST = null;
				AST tmp11_AST_in = null;
				tmp11_AST = astFactory.create((AST)_t);
				tmp11_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp11_AST);
				ASTPair __currentAST376 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,GT);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST376;
				_t = __t376;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case GTE:
			{
				AST __t377 = _t;
				AST tmp12_AST = null;
				AST tmp12_AST_in = null;
				tmp12_AST = astFactory.create((AST)_t);
				tmp12_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp12_AST);
				ASTPair __currentAST377 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,GTE);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST377;
				_t = __t377;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LTE:
			{
				AST __t378 = _t;
				AST tmp13_AST = null;
				AST tmp13_AST_in = null;
				tmp13_AST = astFactory.create((AST)_t);
				tmp13_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp13_AST);
				ASTPair __currentAST378 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LTE);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST378;
				_t = __t378;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALW:
			{
				AST __t379 = _t;
				AST tmp14_AST = null;
				AST tmp14_AST_in = null;
				tmp14_AST = astFactory.create((AST)_t);
				tmp14_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp14_AST);
				ASTPair __currentAST379 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALW);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST379;
				_t = __t379;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWF:
			{
				AST __t380 = _t;
				AST tmp15_AST = null;
				AST tmp15_AST_in = null;
				tmp15_AST = astFactory.create((AST)_t);
				tmp15_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp15_AST);
				ASTPair __currentAST380 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWF);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST380;
				_t = __t380;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWFE:
			{
				AST __t381 = _t;
				AST tmp16_AST = null;
				AST tmp16_AST_in = null;
				tmp16_AST = astFactory.create((AST)_t);
				tmp16_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp16_AST);
				ASTPair __currentAST381 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWFE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST381;
				_t = __t381;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWFI:
			{
				AST __t382 = _t;
				AST tmp17_AST = null;
				AST tmp17_AST_in = null;
				tmp17_AST = astFactory.create((AST)_t);
				tmp17_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp17_AST);
				ASTPair __currentAST382 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWFI);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST382;
				_t = __t382;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWP:
			{
				AST __t383 = _t;
				AST tmp18_AST = null;
				AST tmp18_AST_in = null;
				tmp18_AST = astFactory.create((AST)_t);
				tmp18_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp18_AST);
				ASTPair __currentAST383 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWP);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST383;
				_t = __t383;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWPE:
			{
				AST __t384 = _t;
				AST tmp19_AST = null;
				AST tmp19_AST_in = null;
				tmp19_AST = astFactory.create((AST)_t);
				tmp19_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp19_AST);
				ASTPair __currentAST384 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWPE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST384;
				_t = __t384;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWPI:
			{
				AST __t385 = _t;
				AST tmp20_AST = null;
				AST tmp20_AST_in = null;
				tmp20_AST = astFactory.create((AST)_t);
				tmp20_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp20_AST);
				ASTPair __currentAST385 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWPI);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST385;
				_t = __t385;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOM:
			{
				AST __t386 = _t;
				AST tmp21_AST = null;
				AST tmp21_AST_in = null;
				tmp21_AST = astFactory.create((AST)_t);
				tmp21_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp21_AST);
				ASTPair __currentAST386 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOM);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST386;
				_t = __t386;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMF:
			{
				AST __t387 = _t;
				AST tmp22_AST = null;
				AST tmp22_AST_in = null;
				tmp22_AST = astFactory.create((AST)_t);
				tmp22_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp22_AST);
				ASTPair __currentAST387 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMF);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST387;
				_t = __t387;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMFE:
			{
				AST __t388 = _t;
				AST tmp23_AST = null;
				AST tmp23_AST_in = null;
				tmp23_AST = astFactory.create((AST)_t);
				tmp23_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp23_AST);
				ASTPair __currentAST388 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMFE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST388;
				_t = __t388;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMFI:
			{
				AST __t389 = _t;
				AST tmp24_AST = null;
				AST tmp24_AST_in = null;
				tmp24_AST = astFactory.create((AST)_t);
				tmp24_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp24_AST);
				ASTPair __currentAST389 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMFI);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST389;
				_t = __t389;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMP:
			{
				AST __t390 = _t;
				AST tmp25_AST = null;
				AST tmp25_AST_in = null;
				tmp25_AST = astFactory.create((AST)_t);
				tmp25_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp25_AST);
				ASTPair __currentAST390 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMP);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST390;
				_t = __t390;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMPE:
			{
				AST __t391 = _t;
				AST tmp26_AST = null;
				AST tmp26_AST_in = null;
				tmp26_AST = astFactory.create((AST)_t);
				tmp26_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp26_AST);
				ASTPair __currentAST391 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMPE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST391;
				_t = __t391;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMPI:
			{
				AST __t392 = _t;
				AST tmp27_AST = null;
				AST tmp27_AST_in = null;
				tmp27_AST = astFactory.create((AST)_t);
				tmp27_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp27_AST);
				ASTPair __currentAST392 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMPI);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST392;
				_t = __t392;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case NOWON:
			{
				AST __t393 = _t;
				AST tmp28_AST = null;
				AST tmp28_AST_in = null;
				tmp28_AST = astFactory.create((AST)_t);
				tmp28_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp28_AST);
				ASTPair __currentAST393 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,NOWON);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST393;
				_t = __t393;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case UPTONOW:
			{
				AST __t394 = _t;
				AST tmp29_AST = null;
				AST tmp29_AST_in = null;
				tmp29_AST = astFactory.create((AST)_t);
				tmp29_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp29_AST);
				ASTPair __currentAST394 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,UPTONOW);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST394;
				_t = __t394;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case BECOMES:
			{
				AST __t395 = _t;
				AST tmp30_AST = null;
				AST tmp30_AST_in = null;
				tmp30_AST = astFactory.create((AST)_t);
				tmp30_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp30_AST);
				ASTPair __currentAST395 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,BECOMES);
				_t = _t.getFirstChild();
				b_tf = _t==ASTNULL ? null : (AST)_t;
				trioformula(_t);
				_t = _retTree;
				b_tf_AST = (AST)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST395;
				_t = __t395;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case UNTIL:
			{
				AST __t396 = _t;
				AST tmp31_AST = null;
				AST tmp31_AST_in = null;
				tmp31_AST = astFactory.create((AST)_t);
				tmp31_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp31_AST);
				ASTPair __currentAST396 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,UNTIL);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST396;
				_t = __t396;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case UNTILW:
			{
				AST __t397 = _t;
				AST tmp32_AST = null;
				AST tmp32_AST_in = null;
				tmp32_AST = astFactory.create((AST)_t);
				tmp32_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp32_AST);
				ASTPair __currentAST397 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,UNTILW);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST397;
				_t = __t397;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SINCE:
			{
				AST __t398 = _t;
				AST tmp33_AST = null;
				AST tmp33_AST_in = null;
				tmp33_AST = astFactory.create((AST)_t);
				tmp33_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp33_AST);
				ASTPair __currentAST398 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SINCE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST398;
				_t = __t398;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SINCEW:
			{
				AST __t399 = _t;
				AST tmp34_AST = null;
				AST tmp34_AST_in = null;
				tmp34_AST = astFactory.create((AST)_t);
				tmp34_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp34_AST);
				ASTPair __currentAST399 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SINCEW);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST399;
				_t = __t399;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case DIST:
			{
				AST __t400 = _t;
				AST tmp35_AST = null;
				AST tmp35_AST_in = null;
				tmp35_AST = astFactory.create((AST)_t);
				tmp35_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp35_AST);
				ASTPair __currentAST400 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,DIST);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST400;
				_t = __t400;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case FUTR:
			{
				AST __t401 = _t;
				AST tmp36_AST = null;
				AST tmp36_AST_in = null;
				tmp36_AST = astFactory.create((AST)_t);
				tmp36_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp36_AST);
				ASTPair __currentAST401 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,FUTR);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST401;
				_t = __t401;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case PAST:
			{
				AST __t402 = _t;
				AST tmp37_AST = null;
				AST tmp37_AST_in = null;
				tmp37_AST = astFactory.create((AST)_t);
				tmp37_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp37_AST);
				ASTPair __currentAST402 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,PAST);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST402;
				_t = __t402;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTS:
			{
				AST __t403 = _t;
				AST tmp38_AST = null;
				AST tmp38_AST_in = null;
				tmp38_AST = astFactory.create((AST)_t);
				tmp38_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp38_AST);
				ASTPair __currentAST403 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LASTS);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST403;
				_t = __t403;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTSEE:
			{
				AST __t404 = _t;
				AST tmp39_AST = null;
				AST tmp39_AST_in = null;
				tmp39_AST = astFactory.create((AST)_t);
				tmp39_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp39_AST);
				ASTPair __currentAST404 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LASTSEE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST404;
				_t = __t404;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTSEI:
			{
				AST __t405 = _t;
				AST tmp40_AST = null;
				AST tmp40_AST_in = null;
				tmp40_AST = astFactory.create((AST)_t);
				tmp40_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp40_AST);
				ASTPair __currentAST405 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LASTSEI);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST405;
				_t = __t405;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTSIE:
			{
				AST __t406 = _t;
				AST tmp41_AST = null;
				AST tmp41_AST_in = null;
				tmp41_AST = astFactory.create((AST)_t);
				tmp41_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp41_AST);
				ASTPair __currentAST406 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LASTSIE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST406;
				_t = __t406;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTSII:
			{
				AST __t407 = _t;
				AST tmp42_AST = null;
				AST tmp42_AST_in = null;
				tmp42_AST = astFactory.create((AST)_t);
				tmp42_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp42_AST);
				ASTPair __currentAST407 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LASTSII);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST407;
				_t = __t407;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTEDEE:
			{
				AST __t408 = _t;
				AST tmp43_AST = null;
				AST tmp43_AST_in = null;
				tmp43_AST = astFactory.create((AST)_t);
				tmp43_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp43_AST);
				ASTPair __currentAST408 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LASTEDEE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST408;
				_t = __t408;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTEDEI:
			{
				AST __t409 = _t;
				AST tmp44_AST = null;
				AST tmp44_AST_in = null;
				tmp44_AST = astFactory.create((AST)_t);
				tmp44_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp44_AST);
				ASTPair __currentAST409 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LASTEDEI);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST409;
				_t = __t409;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTEDIE:
			{
				AST __t410 = _t;
				AST tmp45_AST = null;
				AST tmp45_AST_in = null;
				tmp45_AST = astFactory.create((AST)_t);
				tmp45_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp45_AST);
				ASTPair __currentAST410 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LASTEDIE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST410;
				_t = __t410;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTEDII:
			{
				AST __t411 = _t;
				AST tmp46_AST = null;
				AST tmp46_AST_in = null;
				tmp46_AST = astFactory.create((AST)_t);
				tmp46_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp46_AST);
				ASTPair __currentAST411 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LASTEDII);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST411;
				_t = __t411;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTED:
			{
				AST __t412 = _t;
				AST tmp47_AST = null;
				AST tmp47_AST_in = null;
				tmp47_AST = astFactory.create((AST)_t);
				tmp47_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp47_AST);
				ASTPair __currentAST412 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LASTED);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST412;
				_t = __t412;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case WITHIN:
			{
				AST __t413 = _t;
				AST tmp48_AST = null;
				AST tmp48_AST_in = null;
				tmp48_AST = astFactory.create((AST)_t);
				tmp48_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp48_AST);
				ASTPair __currentAST413 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,WITHIN);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST413;
				_t = __t413;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case NEXTTIME:
			{
				AST __t478 = _t;
				AST tmp49_AST = null;
				AST tmp49_AST_in = null;
				tmp49_AST = astFactory.create((AST)_t);
				tmp49_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp49_AST);
				ASTPair __currentAST478 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,NEXTTIME);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST478;
				_t = __t478;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTTIME:
			{
				AST __t479 = _t;
				AST tmp50_AST = null;
				AST tmp50_AST_in = null;
				tmp50_AST = astFactory.create((AST)_t);
				tmp50_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp50_AST);
				ASTPair __currentAST479 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LASTTIME);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST479;
				_t = __t479;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case NOW:
			{
				AST __t480 = _t;
				AST tmp51_AST = null;
				AST tmp51_AST_in = null;
				tmp51_AST = astFactory.create((AST)_t);
				tmp51_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp51_AST);
				ASTPair __currentAST480 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,NOW);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST480;
				_t = __t480;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LPAREN:
			{
				AST __t481 = _t;
				AST tmp52_AST = null;
				AST tmp52_AST_in = null;
				tmp52_AST = astFactory.create((AST)_t);
				tmp52_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp52_AST);
				ASTPair __currentAST481 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LPAREN);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST481;
				_t = __t481;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			default:
				boolean synPredMatched116 = false;
				if (((_t.getType()==IFF_ROOT))) {
					AST __t116 = _t;
					synPredMatched116 = true;
					inputState.guessing++;
					try {
						{
						AST __t115 = _t;
						ASTPair __currentAST115 = currentAST.copy();
						currentAST.root = currentAST.child;
						currentAST.child = null;
						match(_t,IFF_ROOT);
						_t = _t.getFirstChild();
						trioformula(_t);
						_t = _retTree;
						trioformula(_t);
						_t = _retTree;
						trioformula(_t);
						_t = _retTree;
						trioformula(_t);
						_t = _retTree;
						currentAST = __currentAST115;
						_t = __t115;
						_t = _t.getNextSibling();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched116 = false;
					}
					_t = __t116;
inputState.guessing--;
				}
				if ( synPredMatched116 ) {
					AST __t117 = _t;
					AST tmp53_AST = null;
					AST tmp53_AST_in = null;
					tmp53_AST = astFactory.create((AST)_t);
					tmp53_AST_in = (AST)_t;
					astFactory.addASTChild(currentAST, tmp53_AST);
					ASTPair __currentAST117 = currentAST.copy();
					currentAST.root = currentAST.child;
					currentAST.child = null;
					match(_t,IFF_ROOT);
					_t = _t.getFirstChild();
					trioformula(_t);
					_t = _retTree;
					astFactory.addASTChild(currentAST, returnAST);
					trioformula(_t);
					_t = _retTree;
					astFactory.addASTChild(currentAST, returnAST);
					trioformula(_t);
					_t = _retTree;
					astFactory.addASTChild(currentAST, returnAST);
					trioformula(_t);
					_t = _retTree;
					astFactory.addASTChild(currentAST, returnAST);
					currentAST = __currentAST117;
					_t = __t117;
					_t = _t.getNextSibling();
					trioformula_AST = (AST)currentAST.root;
				}
				else {
					boolean synPredMatched120 = false;
					if (((_t.getType()==IFF_ROOT))) {
						AST __t120 = _t;
						synPredMatched120 = true;
						inputState.guessing++;
						try {
							{
							AST __t119 = _t;
							ASTPair __currentAST119 = currentAST.copy();
							currentAST.root = currentAST.child;
							currentAST.child = null;
							match(_t,IFF_ROOT);
							_t = _t.getFirstChild();
							trioformula(_t);
							_t = _retTree;
							trioformula(_t);
							_t = _retTree;
							trioformula(_t);
							_t = _retTree;
							currentAST = __currentAST119;
							_t = __t119;
							_t = _t.getNextSibling();
							}
						}
						catch (RecognitionException pe) {
							synPredMatched120 = false;
						}
						_t = __t120;
inputState.guessing--;
					}
					if ( synPredMatched120 ) {
						AST __t121 = _t;
						AST tmp54_AST = null;
						AST tmp54_AST_in = null;
						tmp54_AST = astFactory.create((AST)_t);
						tmp54_AST_in = (AST)_t;
						astFactory.addASTChild(currentAST, tmp54_AST);
						ASTPair __currentAST121 = currentAST.copy();
						currentAST.root = currentAST.child;
						currentAST.child = null;
						match(_t,IFF_ROOT);
						_t = _t.getFirstChild();
						trioformula(_t);
						_t = _retTree;
						astFactory.addASTChild(currentAST, returnAST);
						trioformula(_t);
						_t = _retTree;
						astFactory.addASTChild(currentAST, returnAST);
						trioformula(_t);
						_t = _retTree;
						astFactory.addASTChild(currentAST, returnAST);
						currentAST = __currentAST121;
						_t = __t121;
						_t = _t.getNextSibling();
						trioformula_AST = (AST)currentAST.root;
					}
					else {
						boolean synPredMatched124 = false;
						if (((_t.getType()==IFF_ROOT))) {
							AST __t124 = _t;
							synPredMatched124 = true;
							inputState.guessing++;
							try {
								{
								AST __t123 = _t;
								ASTPair __currentAST123 = currentAST.copy();
								currentAST.root = currentAST.child;
								currentAST.child = null;
								match(_t,IFF_ROOT);
								_t = _t.getFirstChild();
								trioformula(_t);
								_t = _retTree;
								trioformula(_t);
								_t = _retTree;
								currentAST = __currentAST123;
								_t = __t123;
								_t = _t.getNextSibling();
								}
							}
							catch (RecognitionException pe) {
								synPredMatched124 = false;
							}
							_t = __t124;
inputState.guessing--;
						}
						if ( synPredMatched124 ) {
							AST __t125 = _t;
							AST tmp55_AST = null;
							AST tmp55_AST_in = null;
							tmp55_AST = astFactory.create((AST)_t);
							tmp55_AST_in = (AST)_t;
							astFactory.addASTChild(currentAST, tmp55_AST);
							ASTPair __currentAST125 = currentAST.copy();
							currentAST.root = currentAST.child;
							currentAST.child = null;
							match(_t,IFF_ROOT);
							_t = _t.getFirstChild();
							trioformula(_t);
							_t = _retTree;
							astFactory.addASTChild(currentAST, returnAST);
							trioformula(_t);
							_t = _retTree;
							astFactory.addASTChild(currentAST, returnAST);
							currentAST = __currentAST125;
							_t = __t125;
							_t = _t.getNextSibling();
							trioformula_AST = (AST)currentAST.root;
						}
						else {
							boolean synPredMatched136 = false;
							if (((_t.getType()==NOT))) {
								AST __t136 = _t;
								synPredMatched136 = true;
								inputState.guessing++;
								try {
									{
									AST __t133 = _t;
									ASTPair __currentAST133 = currentAST.copy();
									currentAST.root = currentAST.child;
									currentAST.child = null;
									match(_t,NOT);
									_t = _t.getFirstChild();
									{
									AST __t135 = _t;
									ASTPair __currentAST135 = currentAST.copy();
									currentAST.root = currentAST.child;
									currentAST.child = null;
									match(_t,AND);
									_t = _t.getFirstChild();
									trioformula(_t);
									_t = _retTree;
									trioformula(_t);
									_t = _retTree;
									currentAST = __currentAST135;
									_t = __t135;
									_t = _t.getNextSibling();
									}
									currentAST = __currentAST133;
									_t = __t133;
									_t = _t.getNextSibling();
									}
								}
								catch (RecognitionException pe) {
									synPredMatched136 = false;
								}
								_t = __t136;
inputState.guessing--;
							}
							if ( synPredMatched136 ) {
								AST __t137 = _t;
								AST tmp56_AST = null;
								AST tmp56_AST_in = null;
								tmp56_AST = astFactory.create((AST)_t);
								tmp56_AST_in = (AST)_t;
								ASTPair __currentAST137 = currentAST.copy();
								currentAST.root = currentAST.child;
								currentAST.child = null;
								match(_t,NOT);
								_t = _t.getFirstChild();
								{
								AST __t139 = _t;
								AST tmp57_AST = null;
								AST tmp57_AST_in = null;
								tmp57_AST = astFactory.create((AST)_t);
								tmp57_AST_in = (AST)_t;
								ASTPair __currentAST139 = currentAST.copy();
								currentAST.root = currentAST.child;
								currentAST.child = null;
								match(_t,AND);
								_t = _t.getFirstChild();
								n_and_tf1 = _t==ASTNULL ? null : (AST)_t;
								trioformula(_t);
								_t = _retTree;
								n_and_tf1_AST = (AST)returnAST;
								n_and_tf2 = _t==ASTNULL ? null : (AST)_t;
								trioformula(_t);
								_t = _retTree;
								n_and_tf2_AST = (AST)returnAST;
								currentAST = __currentAST139;
								_t = __t139;
								_t = _t.getNextSibling();
								}
								currentAST = __currentAST137;
								_t = __t137;
								_t = _t.getNextSibling();
								if ( inputState.guessing==0 ) {
									trioformula_AST = (AST)currentAST.root;
									
											NotDismantler nd = new NotDismantler();
											nd.trioformula((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp56_AST)).add(n_and_tf1_AST)));
											AST child1= nd.getAST();
											nd.trioformula((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp56_AST)).add(n_and_tf2_AST)));
											AST child2= nd.getAST();
											trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(OR)).add(child1).add(child2));
										
									currentAST.root = trioformula_AST;
									currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
										trioformula_AST.getFirstChild() : trioformula_AST;
									currentAST.advanceChildToEnd();
								}
							}
							else {
								boolean synPredMatched144 = false;
								if (((_t.getType()==NOT))) {
									AST __t144 = _t;
									synPredMatched144 = true;
									inputState.guessing++;
									try {
										{
										AST __t141 = _t;
										ASTPair __currentAST141 = currentAST.copy();
										currentAST.root = currentAST.child;
										currentAST.child = null;
										match(_t,NOT);
										_t = _t.getFirstChild();
										{
										AST __t143 = _t;
										ASTPair __currentAST143 = currentAST.copy();
										currentAST.root = currentAST.child;
										currentAST.child = null;
										match(_t,OR);
										_t = _t.getFirstChild();
										trioformula(_t);
										_t = _retTree;
										trioformula(_t);
										_t = _retTree;
										currentAST = __currentAST143;
										_t = __t143;
										_t = _t.getNextSibling();
										}
										currentAST = __currentAST141;
										_t = __t141;
										_t = _t.getNextSibling();
										}
									}
									catch (RecognitionException pe) {
										synPredMatched144 = false;
									}
									_t = __t144;
inputState.guessing--;
								}
								if ( synPredMatched144 ) {
									AST __t145 = _t;
									AST tmp58_AST = null;
									AST tmp58_AST_in = null;
									tmp58_AST = astFactory.create((AST)_t);
									tmp58_AST_in = (AST)_t;
									ASTPair __currentAST145 = currentAST.copy();
									currentAST.root = currentAST.child;
									currentAST.child = null;
									match(_t,NOT);
									_t = _t.getFirstChild();
									{
									AST __t147 = _t;
									AST tmp59_AST = null;
									AST tmp59_AST_in = null;
									tmp59_AST = astFactory.create((AST)_t);
									tmp59_AST_in = (AST)_t;
									ASTPair __currentAST147 = currentAST.copy();
									currentAST.root = currentAST.child;
									currentAST.child = null;
									match(_t,OR);
									_t = _t.getFirstChild();
									n_or_tf1 = _t==ASTNULL ? null : (AST)_t;
									trioformula(_t);
									_t = _retTree;
									n_or_tf1_AST = (AST)returnAST;
									n_or_tf2 = _t==ASTNULL ? null : (AST)_t;
									trioformula(_t);
									_t = _retTree;
									n_or_tf2_AST = (AST)returnAST;
									currentAST = __currentAST147;
									_t = __t147;
									_t = _t.getNextSibling();
									}
									currentAST = __currentAST145;
									_t = __t145;
									_t = _t.getNextSibling();
									if ( inputState.guessing==0 ) {
										trioformula_AST = (AST)currentAST.root;
										
												NotDismantler nd = new NotDismantler();
												nd.trioformula((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp58_AST)).add(n_or_tf1_AST)));
												AST child1= nd.getAST();
												nd.trioformula((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp58_AST)).add(n_or_tf2_AST)));
												AST child2= nd.getAST();
												trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(AND)).add(child1).add(child2));
											
										currentAST.root = trioformula_AST;
										currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
											trioformula_AST.getFirstChild() : trioformula_AST;
										currentAST.advanceChildToEnd();
									}
								}
								else {
									boolean synPredMatched152 = false;
									if (((_t.getType()==NOT))) {
										AST __t152 = _t;
										synPredMatched152 = true;
										inputState.guessing++;
										try {
											{
											AST __t149 = _t;
											ASTPair __currentAST149 = currentAST.copy();
											currentAST.root = currentAST.child;
											currentAST.child = null;
											match(_t,NOT);
											_t = _t.getFirstChild();
											{
											AST __t151 = _t;
											ASTPair __currentAST151 = currentAST.copy();
											currentAST.root = currentAST.child;
											currentAST.child = null;
											match(_t,AND_ROOT);
											_t = _t.getFirstChild();
											trioformula(_t);
											_t = _retTree;
											trioformula(_t);
											_t = _retTree;
											currentAST = __currentAST151;
											_t = __t151;
											_t = _t.getNextSibling();
											}
											currentAST = __currentAST149;
											_t = __t149;
											_t = _t.getNextSibling();
											}
										}
										catch (RecognitionException pe) {
											synPredMatched152 = false;
										}
										_t = __t152;
inputState.guessing--;
									}
									if ( synPredMatched152 ) {
										AST __t153 = _t;
										AST tmp60_AST = null;
										AST tmp60_AST_in = null;
										tmp60_AST = astFactory.create((AST)_t);
										tmp60_AST_in = (AST)_t;
										ASTPair __currentAST153 = currentAST.copy();
										currentAST.root = currentAST.child;
										currentAST.child = null;
										match(_t,NOT);
										_t = _t.getFirstChild();
										{
										AST __t155 = _t;
										AST tmp61_AST = null;
										AST tmp61_AST_in = null;
										tmp61_AST = astFactory.create((AST)_t);
										tmp61_AST_in = (AST)_t;
										ASTPair __currentAST155 = currentAST.copy();
										currentAST.root = currentAST.child;
										currentAST.child = null;
										match(_t,AND_ROOT);
										_t = _t.getFirstChild();
										n_andR_tf1 = _t==ASTNULL ? null : (AST)_t;
										trioformula(_t);
										_t = _retTree;
										n_andR_tf1_AST = (AST)returnAST;
										n_andR_tf2 = _t==ASTNULL ? null : (AST)_t;
										trioformula(_t);
										_t = _retTree;
										n_andR_tf2_AST = (AST)returnAST;
										currentAST = __currentAST155;
										_t = __t155;
										_t = _t.getNextSibling();
										}
										currentAST = __currentAST153;
										_t = __t153;
										_t = _t.getNextSibling();
										if ( inputState.guessing==0 ) {
											trioformula_AST = (AST)currentAST.root;
											
													NotDismantler nd = new NotDismantler();
													nd.trioformula((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp60_AST)).add(n_andR_tf1_AST)));
													AST child1= nd.getAST();
													nd.trioformula((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp60_AST)).add(n_andR_tf2_AST)));
													AST child2= nd.getAST();
													trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(OR_ROOT)).add(child1).add(child2));
												
											currentAST.root = trioformula_AST;
											currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
												trioformula_AST.getFirstChild() : trioformula_AST;
											currentAST.advanceChildToEnd();
										}
									}
									else {
										boolean synPredMatched160 = false;
										if (((_t.getType()==NOT))) {
											AST __t160 = _t;
											synPredMatched160 = true;
											inputState.guessing++;
											try {
												{
												AST __t157 = _t;
												ASTPair __currentAST157 = currentAST.copy();
												currentAST.root = currentAST.child;
												currentAST.child = null;
												match(_t,NOT);
												_t = _t.getFirstChild();
												{
												AST __t159 = _t;
												ASTPair __currentAST159 = currentAST.copy();
												currentAST.root = currentAST.child;
												currentAST.child = null;
												match(_t,OR_ROOT);
												_t = _t.getFirstChild();
												trioformula(_t);
												_t = _retTree;
												trioformula(_t);
												_t = _retTree;
												currentAST = __currentAST159;
												_t = __t159;
												_t = _t.getNextSibling();
												}
												currentAST = __currentAST157;
												_t = __t157;
												_t = _t.getNextSibling();
												}
											}
											catch (RecognitionException pe) {
												synPredMatched160 = false;
											}
											_t = __t160;
inputState.guessing--;
										}
										if ( synPredMatched160 ) {
											AST __t161 = _t;
											AST tmp62_AST = null;
											AST tmp62_AST_in = null;
											tmp62_AST = astFactory.create((AST)_t);
											tmp62_AST_in = (AST)_t;
											ASTPair __currentAST161 = currentAST.copy();
											currentAST.root = currentAST.child;
											currentAST.child = null;
											match(_t,NOT);
											_t = _t.getFirstChild();
											{
											AST __t163 = _t;
											AST tmp63_AST = null;
											AST tmp63_AST_in = null;
											tmp63_AST = astFactory.create((AST)_t);
											tmp63_AST_in = (AST)_t;
											ASTPair __currentAST163 = currentAST.copy();
											currentAST.root = currentAST.child;
											currentAST.child = null;
											match(_t,OR_ROOT);
											_t = _t.getFirstChild();
											n_orR_tf1 = _t==ASTNULL ? null : (AST)_t;
											trioformula(_t);
											_t = _retTree;
											n_orR_tf1_AST = (AST)returnAST;
											n_orR_tf2 = _t==ASTNULL ? null : (AST)_t;
											trioformula(_t);
											_t = _retTree;
											n_orR_tf2_AST = (AST)returnAST;
											currentAST = __currentAST163;
											_t = __t163;
											_t = _t.getNextSibling();
											}
											currentAST = __currentAST161;
											_t = __t161;
											_t = _t.getNextSibling();
											if ( inputState.guessing==0 ) {
												trioformula_AST = (AST)currentAST.root;
												
														NotDismantler nd = new NotDismantler();
														nd.trioformula((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp62_AST)).add(n_orR_tf1_AST)));
														AST child1= nd.getAST();
														nd.trioformula((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp62_AST)).add(n_orR_tf2_AST)));
														AST child2= nd.getAST();
														trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(AND_ROOT)).add(child1).add(child2));
													
												currentAST.root = trioformula_AST;
												currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
													trioformula_AST.getFirstChild() : trioformula_AST;
												currentAST.advanceChildToEnd();
											}
										}
										else {
											boolean synPredMatched168 = false;
											if (((_t.getType()==NOT))) {
												AST __t168 = _t;
												synPredMatched168 = true;
												inputState.guessing++;
												try {
													{
													AST __t165 = _t;
													ASTPair __currentAST165 = currentAST.copy();
													currentAST.root = currentAST.child;
													currentAST.child = null;
													match(_t,NOT);
													_t = _t.getFirstChild();
													{
													AST __t167 = _t;
													ASTPair __currentAST167 = currentAST.copy();
													currentAST.root = currentAST.child;
													currentAST.child = null;
													match(_t,LASTSEE);
													_t = _t.getFirstChild();
													trioformula(_t);
													_t = _retTree;
													term(_t);
													_t = _retTree;
													currentAST = __currentAST167;
													_t = __t167;
													_t = _t.getNextSibling();
													}
													currentAST = __currentAST165;
													_t = __t165;
													_t = _t.getNextSibling();
													}
												}
												catch (RecognitionException pe) {
													synPredMatched168 = false;
												}
												_t = __t168;
inputState.guessing--;
											}
											if ( synPredMatched168 ) {
												AST __t169 = _t;
												AST tmp64_AST = null;
												AST tmp64_AST_in = null;
												tmp64_AST = astFactory.create((AST)_t);
												tmp64_AST_in = (AST)_t;
												ASTPair __currentAST169 = currentAST.copy();
												currentAST.root = currentAST.child;
												currentAST.child = null;
												match(_t,NOT);
												_t = _t.getFirstChild();
												{
												AST __t171 = _t;
												AST tmp65_AST = null;
												AST tmp65_AST_in = null;
												tmp65_AST = astFactory.create((AST)_t);
												tmp65_AST_in = (AST)_t;
												ASTPair __currentAST171 = currentAST.copy();
												currentAST.root = currentAST.child;
												currentAST.child = null;
												match(_t,LASTSEE);
												_t = _t.getFirstChild();
												n_lee_tf = _t==ASTNULL ? null : (AST)_t;
												trioformula(_t);
												_t = _retTree;
												n_lee_tf_AST = (AST)returnAST;
												n_lee_t = _t==ASTNULL ? null : (AST)_t;
												term(_t);
												_t = _retTree;
												n_lee_t_AST = (AST)returnAST;
												currentAST = __currentAST171;
												_t = __t171;
												_t = _t.getNextSibling();
												}
												currentAST = __currentAST169;
												_t = __t169;
												_t = _t.getNextSibling();
												if ( inputState.guessing==0 ) {
													trioformula_AST = (AST)currentAST.root;
													
																trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(WITHINFEE,"WithinF_ee")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp64_AST)).add(n_lee_tf_AST))).add(n_lee_t_AST));
															
													currentAST.root = trioformula_AST;
													currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
														trioformula_AST.getFirstChild() : trioformula_AST;
													currentAST.advanceChildToEnd();
												}
											}
											else {
												boolean synPredMatched176 = false;
												if (((_t.getType()==NOT))) {
													AST __t176 = _t;
													synPredMatched176 = true;
													inputState.guessing++;
													try {
														{
														AST __t173 = _t;
														ASTPair __currentAST173 = currentAST.copy();
														currentAST.root = currentAST.child;
														currentAST.child = null;
														match(_t,NOT);
														_t = _t.getFirstChild();
														{
														AST __t175 = _t;
														ASTPair __currentAST175 = currentAST.copy();
														currentAST.root = currentAST.child;
														currentAST.child = null;
														match(_t,LASTSEI);
														_t = _t.getFirstChild();
														trioformula(_t);
														_t = _retTree;
														term(_t);
														_t = _retTree;
														currentAST = __currentAST175;
														_t = __t175;
														_t = _t.getNextSibling();
														}
														currentAST = __currentAST173;
														_t = __t173;
														_t = _t.getNextSibling();
														}
													}
													catch (RecognitionException pe) {
														synPredMatched176 = false;
													}
													_t = __t176;
inputState.guessing--;
												}
												if ( synPredMatched176 ) {
													AST __t177 = _t;
													AST tmp66_AST = null;
													AST tmp66_AST_in = null;
													tmp66_AST = astFactory.create((AST)_t);
													tmp66_AST_in = (AST)_t;
													ASTPair __currentAST177 = currentAST.copy();
													currentAST.root = currentAST.child;
													currentAST.child = null;
													match(_t,NOT);
													_t = _t.getFirstChild();
													{
													AST __t179 = _t;
													AST tmp67_AST = null;
													AST tmp67_AST_in = null;
													tmp67_AST = astFactory.create((AST)_t);
													tmp67_AST_in = (AST)_t;
													ASTPair __currentAST179 = currentAST.copy();
													currentAST.root = currentAST.child;
													currentAST.child = null;
													match(_t,LASTSEI);
													_t = _t.getFirstChild();
													n_lei_tf = _t==ASTNULL ? null : (AST)_t;
													trioformula(_t);
													_t = _retTree;
													n_lei_tf_AST = (AST)returnAST;
													n_lei_t = _t==ASTNULL ? null : (AST)_t;
													term(_t);
													_t = _retTree;
													n_lei_t_AST = (AST)returnAST;
													currentAST = __currentAST179;
													_t = __t179;
													_t = _t.getNextSibling();
													}
													currentAST = __currentAST177;
													_t = __t177;
													_t = _t.getNextSibling();
													if ( inputState.guessing==0 ) {
														trioformula_AST = (AST)currentAST.root;
														
																	trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(WITHINFEI,"WithinF_ei")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp66_AST)).add(n_lei_tf_AST))).add(n_lei_t_AST));
																
														currentAST.root = trioformula_AST;
														currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
															trioformula_AST.getFirstChild() : trioformula_AST;
														currentAST.advanceChildToEnd();
													}
												}
												else {
													boolean synPredMatched184 = false;
													if (((_t.getType()==NOT))) {
														AST __t184 = _t;
														synPredMatched184 = true;
														inputState.guessing++;
														try {
															{
															AST __t181 = _t;
															ASTPair __currentAST181 = currentAST.copy();
															currentAST.root = currentAST.child;
															currentAST.child = null;
															match(_t,NOT);
															_t = _t.getFirstChild();
															{
															AST __t183 = _t;
															ASTPair __currentAST183 = currentAST.copy();
															currentAST.root = currentAST.child;
															currentAST.child = null;
															match(_t,LASTSIE);
															_t = _t.getFirstChild();
															trioformula(_t);
															_t = _retTree;
															term(_t);
															_t = _retTree;
															currentAST = __currentAST183;
															_t = __t183;
															_t = _t.getNextSibling();
															}
															currentAST = __currentAST181;
															_t = __t181;
															_t = _t.getNextSibling();
															}
														}
														catch (RecognitionException pe) {
															synPredMatched184 = false;
														}
														_t = __t184;
inputState.guessing--;
													}
													if ( synPredMatched184 ) {
														AST __t185 = _t;
														AST tmp68_AST = null;
														AST tmp68_AST_in = null;
														tmp68_AST = astFactory.create((AST)_t);
														tmp68_AST_in = (AST)_t;
														ASTPair __currentAST185 = currentAST.copy();
														currentAST.root = currentAST.child;
														currentAST.child = null;
														match(_t,NOT);
														_t = _t.getFirstChild();
														{
														AST __t187 = _t;
														AST tmp69_AST = null;
														AST tmp69_AST_in = null;
														tmp69_AST = astFactory.create((AST)_t);
														tmp69_AST_in = (AST)_t;
														ASTPair __currentAST187 = currentAST.copy();
														currentAST.root = currentAST.child;
														currentAST.child = null;
														match(_t,LASTSIE);
														_t = _t.getFirstChild();
														n_lie_tf = _t==ASTNULL ? null : (AST)_t;
														trioformula(_t);
														_t = _retTree;
														n_lie_tf_AST = (AST)returnAST;
														n_lie_t = _t==ASTNULL ? null : (AST)_t;
														term(_t);
														_t = _retTree;
														n_lie_t_AST = (AST)returnAST;
														currentAST = __currentAST187;
														_t = __t187;
														_t = _t.getNextSibling();
														}
														currentAST = __currentAST185;
														_t = __t185;
														_t = _t.getNextSibling();
														if ( inputState.guessing==0 ) {
															trioformula_AST = (AST)currentAST.root;
															
																		trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(WITHINFIE,"WithinF_ie")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp68_AST)).add(n_lie_tf_AST))).add(n_lie_t_AST));
																	
															currentAST.root = trioformula_AST;
															currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																trioformula_AST.getFirstChild() : trioformula_AST;
															currentAST.advanceChildToEnd();
														}
													}
													else {
														boolean synPredMatched192 = false;
														if (((_t.getType()==NOT))) {
															AST __t192 = _t;
															synPredMatched192 = true;
															inputState.guessing++;
															try {
																{
																AST __t189 = _t;
																ASTPair __currentAST189 = currentAST.copy();
																currentAST.root = currentAST.child;
																currentAST.child = null;
																match(_t,NOT);
																_t = _t.getFirstChild();
																{
																AST __t191 = _t;
																ASTPair __currentAST191 = currentAST.copy();
																currentAST.root = currentAST.child;
																currentAST.child = null;
																match(_t,LASTSII);
																_t = _t.getFirstChild();
																trioformula(_t);
																_t = _retTree;
																term(_t);
																_t = _retTree;
																currentAST = __currentAST191;
																_t = __t191;
																_t = _t.getNextSibling();
																}
																currentAST = __currentAST189;
																_t = __t189;
																_t = _t.getNextSibling();
																}
															}
															catch (RecognitionException pe) {
																synPredMatched192 = false;
															}
															_t = __t192;
inputState.guessing--;
														}
														if ( synPredMatched192 ) {
															AST __t193 = _t;
															AST tmp70_AST = null;
															AST tmp70_AST_in = null;
															tmp70_AST = astFactory.create((AST)_t);
															tmp70_AST_in = (AST)_t;
															ASTPair __currentAST193 = currentAST.copy();
															currentAST.root = currentAST.child;
															currentAST.child = null;
															match(_t,NOT);
															_t = _t.getFirstChild();
															{
															AST __t195 = _t;
															AST tmp71_AST = null;
															AST tmp71_AST_in = null;
															tmp71_AST = astFactory.create((AST)_t);
															tmp71_AST_in = (AST)_t;
															ASTPair __currentAST195 = currentAST.copy();
															currentAST.root = currentAST.child;
															currentAST.child = null;
															match(_t,LASTSII);
															_t = _t.getFirstChild();
															n_lii_tf = _t==ASTNULL ? null : (AST)_t;
															trioformula(_t);
															_t = _retTree;
															n_lii_tf_AST = (AST)returnAST;
															n_lii_t = _t==ASTNULL ? null : (AST)_t;
															term(_t);
															_t = _retTree;
															n_lii_t_AST = (AST)returnAST;
															currentAST = __currentAST195;
															_t = __t195;
															_t = _t.getNextSibling();
															}
															currentAST = __currentAST193;
															_t = __t193;
															_t = _t.getNextSibling();
															if ( inputState.guessing==0 ) {
																trioformula_AST = (AST)currentAST.root;
																
																			trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(WITHINFII,"WithinF_ii")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp70_AST)).add(n_lii_tf_AST))).add(n_lii_t_AST));
																		
																currentAST.root = trioformula_AST;
																currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																	trioformula_AST.getFirstChild() : trioformula_AST;
																currentAST.advanceChildToEnd();
															}
														}
														else {
															boolean synPredMatched200 = false;
															if (((_t.getType()==NOT))) {
																AST __t200 = _t;
																synPredMatched200 = true;
																inputState.guessing++;
																try {
																	{
																	AST __t197 = _t;
																	ASTPair __currentAST197 = currentAST.copy();
																	currentAST.root = currentAST.child;
																	currentAST.child = null;
																	match(_t,NOT);
																	_t = _t.getFirstChild();
																	{
																	AST __t199 = _t;
																	ASTPair __currentAST199 = currentAST.copy();
																	currentAST.root = currentAST.child;
																	currentAST.child = null;
																	match(_t,LASTEDEE);
																	_t = _t.getFirstChild();
																	trioformula(_t);
																	_t = _retTree;
																	term(_t);
																	_t = _retTree;
																	currentAST = __currentAST199;
																	_t = __t199;
																	_t = _t.getNextSibling();
																	}
																	currentAST = __currentAST197;
																	_t = __t197;
																	_t = _t.getNextSibling();
																	}
																}
																catch (RecognitionException pe) {
																	synPredMatched200 = false;
																}
																_t = __t200;
inputState.guessing--;
															}
															if ( synPredMatched200 ) {
																AST __t201 = _t;
																AST tmp72_AST = null;
																AST tmp72_AST_in = null;
																tmp72_AST = astFactory.create((AST)_t);
																tmp72_AST_in = (AST)_t;
																ASTPair __currentAST201 = currentAST.copy();
																currentAST.root = currentAST.child;
																currentAST.child = null;
																match(_t,NOT);
																_t = _t.getFirstChild();
																{
																AST __t203 = _t;
																AST tmp73_AST = null;
																AST tmp73_AST_in = null;
																tmp73_AST = astFactory.create((AST)_t);
																tmp73_AST_in = (AST)_t;
																ASTPair __currentAST203 = currentAST.copy();
																currentAST.root = currentAST.child;
																currentAST.child = null;
																match(_t,LASTEDEE);
																_t = _t.getFirstChild();
																n_ldee_tf = _t==ASTNULL ? null : (AST)_t;
																trioformula(_t);
																_t = _retTree;
																n_ldee_tf_AST = (AST)returnAST;
																n_ldee_t = _t==ASTNULL ? null : (AST)_t;
																term(_t);
																_t = _retTree;
																n_ldee_t_AST = (AST)returnAST;
																currentAST = __currentAST203;
																_t = __t203;
																_t = _t.getNextSibling();
																}
																currentAST = __currentAST201;
																_t = __t201;
																_t = _t.getNextSibling();
																if ( inputState.guessing==0 ) {
																	trioformula_AST = (AST)currentAST.root;
																	
																				trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(WITHINPEE,"WithinP_ee")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp72_AST)).add(n_ldee_tf_AST))).add(n_ldee_t_AST));
																			
																	currentAST.root = trioformula_AST;
																	currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																		trioformula_AST.getFirstChild() : trioformula_AST;
																	currentAST.advanceChildToEnd();
																}
															}
															else {
																boolean synPredMatched208 = false;
																if (((_t.getType()==NOT))) {
																	AST __t208 = _t;
																	synPredMatched208 = true;
																	inputState.guessing++;
																	try {
																		{
																		AST __t205 = _t;
																		ASTPair __currentAST205 = currentAST.copy();
																		currentAST.root = currentAST.child;
																		currentAST.child = null;
																		match(_t,NOT);
																		_t = _t.getFirstChild();
																		{
																		AST __t207 = _t;
																		ASTPair __currentAST207 = currentAST.copy();
																		currentAST.root = currentAST.child;
																		currentAST.child = null;
																		match(_t,LASTEDEI);
																		_t = _t.getFirstChild();
																		trioformula(_t);
																		_t = _retTree;
																		term(_t);
																		_t = _retTree;
																		currentAST = __currentAST207;
																		_t = __t207;
																		_t = _t.getNextSibling();
																		}
																		currentAST = __currentAST205;
																		_t = __t205;
																		_t = _t.getNextSibling();
																		}
																	}
																	catch (RecognitionException pe) {
																		synPredMatched208 = false;
																	}
																	_t = __t208;
inputState.guessing--;
																}
																if ( synPredMatched208 ) {
																	AST __t209 = _t;
																	AST tmp74_AST = null;
																	AST tmp74_AST_in = null;
																	tmp74_AST = astFactory.create((AST)_t);
																	tmp74_AST_in = (AST)_t;
																	ASTPair __currentAST209 = currentAST.copy();
																	currentAST.root = currentAST.child;
																	currentAST.child = null;
																	match(_t,NOT);
																	_t = _t.getFirstChild();
																	{
																	AST __t211 = _t;
																	AST tmp75_AST = null;
																	AST tmp75_AST_in = null;
																	tmp75_AST = astFactory.create((AST)_t);
																	tmp75_AST_in = (AST)_t;
																	ASTPair __currentAST211 = currentAST.copy();
																	currentAST.root = currentAST.child;
																	currentAST.child = null;
																	match(_t,LASTEDEI);
																	_t = _t.getFirstChild();
																	n_ldei_tf = _t==ASTNULL ? null : (AST)_t;
																	trioformula(_t);
																	_t = _retTree;
																	n_ldei_tf_AST = (AST)returnAST;
																	n_ldei_t = _t==ASTNULL ? null : (AST)_t;
																	term(_t);
																	_t = _retTree;
																	n_ldei_t_AST = (AST)returnAST;
																	currentAST = __currentAST211;
																	_t = __t211;
																	_t = _t.getNextSibling();
																	}
																	currentAST = __currentAST209;
																	_t = __t209;
																	_t = _t.getNextSibling();
																	if ( inputState.guessing==0 ) {
																		trioformula_AST = (AST)currentAST.root;
																		
																					trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(WITHINPEI,"WithinP_ei")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp74_AST)).add(n_ldei_tf_AST))).add(n_ldei_t_AST));
																				
																		currentAST.root = trioformula_AST;
																		currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																			trioformula_AST.getFirstChild() : trioformula_AST;
																		currentAST.advanceChildToEnd();
																	}
																}
																else {
																	boolean synPredMatched216 = false;
																	if (((_t.getType()==NOT))) {
																		AST __t216 = _t;
																		synPredMatched216 = true;
																		inputState.guessing++;
																		try {
																			{
																			AST __t213 = _t;
																			ASTPair __currentAST213 = currentAST.copy();
																			currentAST.root = currentAST.child;
																			currentAST.child = null;
																			match(_t,NOT);
																			_t = _t.getFirstChild();
																			{
																			AST __t215 = _t;
																			ASTPair __currentAST215 = currentAST.copy();
																			currentAST.root = currentAST.child;
																			currentAST.child = null;
																			match(_t,LASTEDIE);
																			_t = _t.getFirstChild();
																			trioformula(_t);
																			_t = _retTree;
																			term(_t);
																			_t = _retTree;
																			currentAST = __currentAST215;
																			_t = __t215;
																			_t = _t.getNextSibling();
																			}
																			currentAST = __currentAST213;
																			_t = __t213;
																			_t = _t.getNextSibling();
																			}
																		}
																		catch (RecognitionException pe) {
																			synPredMatched216 = false;
																		}
																		_t = __t216;
inputState.guessing--;
																	}
																	if ( synPredMatched216 ) {
																		AST __t217 = _t;
																		AST tmp76_AST = null;
																		AST tmp76_AST_in = null;
																		tmp76_AST = astFactory.create((AST)_t);
																		tmp76_AST_in = (AST)_t;
																		ASTPair __currentAST217 = currentAST.copy();
																		currentAST.root = currentAST.child;
																		currentAST.child = null;
																		match(_t,NOT);
																		_t = _t.getFirstChild();
																		{
																		AST __t219 = _t;
																		AST tmp77_AST = null;
																		AST tmp77_AST_in = null;
																		tmp77_AST = astFactory.create((AST)_t);
																		tmp77_AST_in = (AST)_t;
																		ASTPair __currentAST219 = currentAST.copy();
																		currentAST.root = currentAST.child;
																		currentAST.child = null;
																		match(_t,LASTEDIE);
																		_t = _t.getFirstChild();
																		n_ldie_tf = _t==ASTNULL ? null : (AST)_t;
																		trioformula(_t);
																		_t = _retTree;
																		n_ldie_tf_AST = (AST)returnAST;
																		n_ldie_t = _t==ASTNULL ? null : (AST)_t;
																		term(_t);
																		_t = _retTree;
																		n_ldie_t_AST = (AST)returnAST;
																		currentAST = __currentAST219;
																		_t = __t219;
																		_t = _t.getNextSibling();
																		}
																		currentAST = __currentAST217;
																		_t = __t217;
																		_t = _t.getNextSibling();
																		if ( inputState.guessing==0 ) {
																			trioformula_AST = (AST)currentAST.root;
																			
																						trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(WITHINPIE,"WithinP_ie")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp76_AST)).add(n_ldie_tf_AST))).add(n_ldie_t_AST));
																					
																			currentAST.root = trioformula_AST;
																			currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																				trioformula_AST.getFirstChild() : trioformula_AST;
																			currentAST.advanceChildToEnd();
																		}
																	}
																	else {
																		boolean synPredMatched224 = false;
																		if (((_t.getType()==NOT))) {
																			AST __t224 = _t;
																			synPredMatched224 = true;
																			inputState.guessing++;
																			try {
																				{
																				AST __t221 = _t;
																				ASTPair __currentAST221 = currentAST.copy();
																				currentAST.root = currentAST.child;
																				currentAST.child = null;
																				match(_t,NOT);
																				_t = _t.getFirstChild();
																				{
																				AST __t223 = _t;
																				ASTPair __currentAST223 = currentAST.copy();
																				currentAST.root = currentAST.child;
																				currentAST.child = null;
																				match(_t,LASTEDII);
																				_t = _t.getFirstChild();
																				trioformula(_t);
																				_t = _retTree;
																				term(_t);
																				_t = _retTree;
																				currentAST = __currentAST223;
																				_t = __t223;
																				_t = _t.getNextSibling();
																				}
																				currentAST = __currentAST221;
																				_t = __t221;
																				_t = _t.getNextSibling();
																				}
																			}
																			catch (RecognitionException pe) {
																				synPredMatched224 = false;
																			}
																			_t = __t224;
inputState.guessing--;
																		}
																		if ( synPredMatched224 ) {
																			AST __t225 = _t;
																			AST tmp78_AST = null;
																			AST tmp78_AST_in = null;
																			tmp78_AST = astFactory.create((AST)_t);
																			tmp78_AST_in = (AST)_t;
																			ASTPair __currentAST225 = currentAST.copy();
																			currentAST.root = currentAST.child;
																			currentAST.child = null;
																			match(_t,NOT);
																			_t = _t.getFirstChild();
																			{
																			AST __t227 = _t;
																			AST tmp79_AST = null;
																			AST tmp79_AST_in = null;
																			tmp79_AST = astFactory.create((AST)_t);
																			tmp79_AST_in = (AST)_t;
																			ASTPair __currentAST227 = currentAST.copy();
																			currentAST.root = currentAST.child;
																			currentAST.child = null;
																			match(_t,LASTEDII);
																			_t = _t.getFirstChild();
																			n_ldii_tf = _t==ASTNULL ? null : (AST)_t;
																			trioformula(_t);
																			_t = _retTree;
																			n_ldii_tf_AST = (AST)returnAST;
																			n_ldii_t = _t==ASTNULL ? null : (AST)_t;
																			term(_t);
																			_t = _retTree;
																			n_ldii_t_AST = (AST)returnAST;
																			currentAST = __currentAST227;
																			_t = __t227;
																			_t = _t.getNextSibling();
																			}
																			currentAST = __currentAST225;
																			_t = __t225;
																			_t = _t.getNextSibling();
																			if ( inputState.guessing==0 ) {
																				trioformula_AST = (AST)currentAST.root;
																				
																							trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(WITHINPII,"WithinP_ii")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp78_AST)).add(n_ldii_tf_AST))).add(n_ldii_t_AST));
																						
																				currentAST.root = trioformula_AST;
																				currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																					trioformula_AST.getFirstChild() : trioformula_AST;
																				currentAST.advanceChildToEnd();
																			}
																		}
																		else {
																			boolean synPredMatched232 = false;
																			if (((_t.getType()==NOT))) {
																				AST __t232 = _t;
																				synPredMatched232 = true;
																				inputState.guessing++;
																				try {
																					{
																					AST __t229 = _t;
																					ASTPair __currentAST229 = currentAST.copy();
																					currentAST.root = currentAST.child;
																					currentAST.child = null;
																					match(_t,NOT);
																					_t = _t.getFirstChild();
																					{
																					AST __t231 = _t;
																					ASTPair __currentAST231 = currentAST.copy();
																					currentAST.root = currentAST.child;
																					currentAST.child = null;
																					match(_t,WITHINFEE);
																					_t = _t.getFirstChild();
																					trioformula(_t);
																					_t = _retTree;
																					term(_t);
																					_t = _retTree;
																					currentAST = __currentAST231;
																					_t = __t231;
																					_t = _t.getNextSibling();
																					}
																					currentAST = __currentAST229;
																					_t = __t229;
																					_t = _t.getNextSibling();
																					}
																				}
																				catch (RecognitionException pe) {
																					synPredMatched232 = false;
																				}
																				_t = __t232;
inputState.guessing--;
																			}
																			if ( synPredMatched232 ) {
																				AST __t233 = _t;
																				AST tmp80_AST = null;
																				AST tmp80_AST_in = null;
																				tmp80_AST = astFactory.create((AST)_t);
																				tmp80_AST_in = (AST)_t;
																				ASTPair __currentAST233 = currentAST.copy();
																				currentAST.root = currentAST.child;
																				currentAST.child = null;
																				match(_t,NOT);
																				_t = _t.getFirstChild();
																				{
																				AST __t235 = _t;
																				AST tmp81_AST = null;
																				AST tmp81_AST_in = null;
																				tmp81_AST = astFactory.create((AST)_t);
																				tmp81_AST_in = (AST)_t;
																				ASTPair __currentAST235 = currentAST.copy();
																				currentAST.root = currentAST.child;
																				currentAST.child = null;
																				match(_t,WITHINFEE);
																				_t = _t.getFirstChild();
																				n_wfee_tf = _t==ASTNULL ? null : (AST)_t;
																				trioformula(_t);
																				_t = _retTree;
																				n_wfee_tf_AST = (AST)returnAST;
																				n_wfee_t = _t==ASTNULL ? null : (AST)_t;
																				term(_t);
																				_t = _retTree;
																				n_wfee_t_AST = (AST)returnAST;
																				currentAST = __currentAST235;
																				_t = __t235;
																				_t = _t.getNextSibling();
																				}
																				currentAST = __currentAST233;
																				_t = __t233;
																				_t = _t.getNextSibling();
																				if ( inputState.guessing==0 ) {
																					trioformula_AST = (AST)currentAST.root;
																					
																								trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(LASTSEE)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp80_AST)).add(n_wfee_tf_AST))).add(n_wfee_t_AST));
																							
																					currentAST.root = trioformula_AST;
																					currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																						trioformula_AST.getFirstChild() : trioformula_AST;
																					currentAST.advanceChildToEnd();
																				}
																			}
																			else {
																				boolean synPredMatched240 = false;
																				if (((_t.getType()==NOT))) {
																					AST __t240 = _t;
																					synPredMatched240 = true;
																					inputState.guessing++;
																					try {
																						{
																						AST __t237 = _t;
																						ASTPair __currentAST237 = currentAST.copy();
																						currentAST.root = currentAST.child;
																						currentAST.child = null;
																						match(_t,NOT);
																						_t = _t.getFirstChild();
																						{
																						AST __t239 = _t;
																						ASTPair __currentAST239 = currentAST.copy();
																						currentAST.root = currentAST.child;
																						currentAST.child = null;
																						match(_t,WITHINFEI);
																						_t = _t.getFirstChild();
																						trioformula(_t);
																						_t = _retTree;
																						term(_t);
																						_t = _retTree;
																						currentAST = __currentAST239;
																						_t = __t239;
																						_t = _t.getNextSibling();
																						}
																						currentAST = __currentAST237;
																						_t = __t237;
																						_t = _t.getNextSibling();
																						}
																					}
																					catch (RecognitionException pe) {
																						synPredMatched240 = false;
																					}
																					_t = __t240;
inputState.guessing--;
																				}
																				if ( synPredMatched240 ) {
																					AST __t241 = _t;
																					AST tmp82_AST = null;
																					AST tmp82_AST_in = null;
																					tmp82_AST = astFactory.create((AST)_t);
																					tmp82_AST_in = (AST)_t;
																					ASTPair __currentAST241 = currentAST.copy();
																					currentAST.root = currentAST.child;
																					currentAST.child = null;
																					match(_t,NOT);
																					_t = _t.getFirstChild();
																					{
																					AST __t243 = _t;
																					AST tmp83_AST = null;
																					AST tmp83_AST_in = null;
																					tmp83_AST = astFactory.create((AST)_t);
																					tmp83_AST_in = (AST)_t;
																					ASTPair __currentAST243 = currentAST.copy();
																					currentAST.root = currentAST.child;
																					currentAST.child = null;
																					match(_t,WITHINFEI);
																					_t = _t.getFirstChild();
																					n_wfei_tf = _t==ASTNULL ? null : (AST)_t;
																					trioformula(_t);
																					_t = _retTree;
																					n_wfei_tf_AST = (AST)returnAST;
																					n_wfei_t = _t==ASTNULL ? null : (AST)_t;
																					term(_t);
																					_t = _retTree;
																					n_wfei_t_AST = (AST)returnAST;
																					currentAST = __currentAST243;
																					_t = __t243;
																					_t = _t.getNextSibling();
																					}
																					currentAST = __currentAST241;
																					_t = __t241;
																					_t = _t.getNextSibling();
																					if ( inputState.guessing==0 ) {
																						trioformula_AST = (AST)currentAST.root;
																						
																									trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(LASTSEI)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp82_AST)).add(n_wfei_tf_AST))).add(n_wfei_t_AST));
																								
																						currentAST.root = trioformula_AST;
																						currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																							trioformula_AST.getFirstChild() : trioformula_AST;
																						currentAST.advanceChildToEnd();
																					}
																				}
																				else {
																					boolean synPredMatched248 = false;
																					if (((_t.getType()==NOT))) {
																						AST __t248 = _t;
																						synPredMatched248 = true;
																						inputState.guessing++;
																						try {
																							{
																							AST __t245 = _t;
																							ASTPair __currentAST245 = currentAST.copy();
																							currentAST.root = currentAST.child;
																							currentAST.child = null;
																							match(_t,NOT);
																							_t = _t.getFirstChild();
																							{
																							AST __t247 = _t;
																							ASTPair __currentAST247 = currentAST.copy();
																							currentAST.root = currentAST.child;
																							currentAST.child = null;
																							match(_t,WITHINFIE);
																							_t = _t.getFirstChild();
																							trioformula(_t);
																							_t = _retTree;
																							term(_t);
																							_t = _retTree;
																							currentAST = __currentAST247;
																							_t = __t247;
																							_t = _t.getNextSibling();
																							}
																							currentAST = __currentAST245;
																							_t = __t245;
																							_t = _t.getNextSibling();
																							}
																						}
																						catch (RecognitionException pe) {
																							synPredMatched248 = false;
																						}
																						_t = __t248;
inputState.guessing--;
																					}
																					if ( synPredMatched248 ) {
																						AST __t249 = _t;
																						AST tmp84_AST = null;
																						AST tmp84_AST_in = null;
																						tmp84_AST = astFactory.create((AST)_t);
																						tmp84_AST_in = (AST)_t;
																						ASTPair __currentAST249 = currentAST.copy();
																						currentAST.root = currentAST.child;
																						currentAST.child = null;
																						match(_t,NOT);
																						_t = _t.getFirstChild();
																						{
																						AST __t251 = _t;
																						AST tmp85_AST = null;
																						AST tmp85_AST_in = null;
																						tmp85_AST = astFactory.create((AST)_t);
																						tmp85_AST_in = (AST)_t;
																						ASTPair __currentAST251 = currentAST.copy();
																						currentAST.root = currentAST.child;
																						currentAST.child = null;
																						match(_t,WITHINFIE);
																						_t = _t.getFirstChild();
																						n_wfie_tf = _t==ASTNULL ? null : (AST)_t;
																						trioformula(_t);
																						_t = _retTree;
																						n_wfie_tf_AST = (AST)returnAST;
																						n_wfie_t = _t==ASTNULL ? null : (AST)_t;
																						term(_t);
																						_t = _retTree;
																						n_wfie_t_AST = (AST)returnAST;
																						currentAST = __currentAST251;
																						_t = __t251;
																						_t = _t.getNextSibling();
																						}
																						currentAST = __currentAST249;
																						_t = __t249;
																						_t = _t.getNextSibling();
																						if ( inputState.guessing==0 ) {
																							trioformula_AST = (AST)currentAST.root;
																							
																										trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(LASTSIE)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp84_AST)).add(n_wfie_tf_AST))).add(n_wfie_t_AST));
																									
																							currentAST.root = trioformula_AST;
																							currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																								trioformula_AST.getFirstChild() : trioformula_AST;
																							currentAST.advanceChildToEnd();
																						}
																					}
																					else {
																						boolean synPredMatched256 = false;
																						if (((_t.getType()==NOT))) {
																							AST __t256 = _t;
																							synPredMatched256 = true;
																							inputState.guessing++;
																							try {
																								{
																								AST __t253 = _t;
																								ASTPair __currentAST253 = currentAST.copy();
																								currentAST.root = currentAST.child;
																								currentAST.child = null;
																								match(_t,NOT);
																								_t = _t.getFirstChild();
																								{
																								AST __t255 = _t;
																								ASTPair __currentAST255 = currentAST.copy();
																								currentAST.root = currentAST.child;
																								currentAST.child = null;
																								match(_t,WITHINFII);
																								_t = _t.getFirstChild();
																								trioformula(_t);
																								_t = _retTree;
																								term(_t);
																								_t = _retTree;
																								currentAST = __currentAST255;
																								_t = __t255;
																								_t = _t.getNextSibling();
																								}
																								currentAST = __currentAST253;
																								_t = __t253;
																								_t = _t.getNextSibling();
																								}
																							}
																							catch (RecognitionException pe) {
																								synPredMatched256 = false;
																							}
																							_t = __t256;
inputState.guessing--;
																						}
																						if ( synPredMatched256 ) {
																							AST __t257 = _t;
																							AST tmp86_AST = null;
																							AST tmp86_AST_in = null;
																							tmp86_AST = astFactory.create((AST)_t);
																							tmp86_AST_in = (AST)_t;
																							ASTPair __currentAST257 = currentAST.copy();
																							currentAST.root = currentAST.child;
																							currentAST.child = null;
																							match(_t,NOT);
																							_t = _t.getFirstChild();
																							{
																							AST __t259 = _t;
																							AST tmp87_AST = null;
																							AST tmp87_AST_in = null;
																							tmp87_AST = astFactory.create((AST)_t);
																							tmp87_AST_in = (AST)_t;
																							ASTPair __currentAST259 = currentAST.copy();
																							currentAST.root = currentAST.child;
																							currentAST.child = null;
																							match(_t,WITHINFII);
																							_t = _t.getFirstChild();
																							n_wfii_tf = _t==ASTNULL ? null : (AST)_t;
																							trioformula(_t);
																							_t = _retTree;
																							n_wfii_tf_AST = (AST)returnAST;
																							n_wfii_t = _t==ASTNULL ? null : (AST)_t;
																							term(_t);
																							_t = _retTree;
																							n_wfii_t_AST = (AST)returnAST;
																							currentAST = __currentAST259;
																							_t = __t259;
																							_t = _t.getNextSibling();
																							}
																							currentAST = __currentAST257;
																							_t = __t257;
																							_t = _t.getNextSibling();
																							if ( inputState.guessing==0 ) {
																								trioformula_AST = (AST)currentAST.root;
																								
																											trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(LASTSII)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp86_AST)).add(n_wfii_tf_AST))).add(n_wfii_t_AST));
																										
																								currentAST.root = trioformula_AST;
																								currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																									trioformula_AST.getFirstChild() : trioformula_AST;
																								currentAST.advanceChildToEnd();
																							}
																						}
																						else {
																							boolean synPredMatched264 = false;
																							if (((_t.getType()==NOT))) {
																								AST __t264 = _t;
																								synPredMatched264 = true;
																								inputState.guessing++;
																								try {
																									{
																									AST __t261 = _t;
																									ASTPair __currentAST261 = currentAST.copy();
																									currentAST.root = currentAST.child;
																									currentAST.child = null;
																									match(_t,NOT);
																									_t = _t.getFirstChild();
																									{
																									AST __t263 = _t;
																									ASTPair __currentAST263 = currentAST.copy();
																									currentAST.root = currentAST.child;
																									currentAST.child = null;
																									match(_t,WITHINPEE);
																									_t = _t.getFirstChild();
																									trioformula(_t);
																									_t = _retTree;
																									term(_t);
																									_t = _retTree;
																									currentAST = __currentAST263;
																									_t = __t263;
																									_t = _t.getNextSibling();
																									}
																									currentAST = __currentAST261;
																									_t = __t261;
																									_t = _t.getNextSibling();
																									}
																								}
																								catch (RecognitionException pe) {
																									synPredMatched264 = false;
																								}
																								_t = __t264;
inputState.guessing--;
																							}
																							if ( synPredMatched264 ) {
																								AST __t265 = _t;
																								AST tmp88_AST = null;
																								AST tmp88_AST_in = null;
																								tmp88_AST = astFactory.create((AST)_t);
																								tmp88_AST_in = (AST)_t;
																								ASTPair __currentAST265 = currentAST.copy();
																								currentAST.root = currentAST.child;
																								currentAST.child = null;
																								match(_t,NOT);
																								_t = _t.getFirstChild();
																								{
																								AST __t267 = _t;
																								AST tmp89_AST = null;
																								AST tmp89_AST_in = null;
																								tmp89_AST = astFactory.create((AST)_t);
																								tmp89_AST_in = (AST)_t;
																								ASTPair __currentAST267 = currentAST.copy();
																								currentAST.root = currentAST.child;
																								currentAST.child = null;
																								match(_t,WITHINPEE);
																								_t = _t.getFirstChild();
																								n_wpee_tf = _t==ASTNULL ? null : (AST)_t;
																								trioformula(_t);
																								_t = _retTree;
																								n_wpee_tf_AST = (AST)returnAST;
																								n_wpee_t = _t==ASTNULL ? null : (AST)_t;
																								term(_t);
																								_t = _retTree;
																								n_wpee_t_AST = (AST)returnAST;
																								currentAST = __currentAST267;
																								_t = __t267;
																								_t = _t.getNextSibling();
																								}
																								currentAST = __currentAST265;
																								_t = __t265;
																								_t = _t.getNextSibling();
																								if ( inputState.guessing==0 ) {
																									trioformula_AST = (AST)currentAST.root;
																									
																												trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(LASTEDEE)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp88_AST)).add(n_wpee_tf_AST))).add(n_wpee_t_AST));
																											
																									currentAST.root = trioformula_AST;
																									currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																										trioformula_AST.getFirstChild() : trioformula_AST;
																									currentAST.advanceChildToEnd();
																								}
																							}
																							else {
																								boolean synPredMatched272 = false;
																								if (((_t.getType()==NOT))) {
																									AST __t272 = _t;
																									synPredMatched272 = true;
																									inputState.guessing++;
																									try {
																										{
																										AST __t269 = _t;
																										ASTPair __currentAST269 = currentAST.copy();
																										currentAST.root = currentAST.child;
																										currentAST.child = null;
																										match(_t,NOT);
																										_t = _t.getFirstChild();
																										{
																										AST __t271 = _t;
																										ASTPair __currentAST271 = currentAST.copy();
																										currentAST.root = currentAST.child;
																										currentAST.child = null;
																										match(_t,WITHINPEI);
																										_t = _t.getFirstChild();
																										trioformula(_t);
																										_t = _retTree;
																										term(_t);
																										_t = _retTree;
																										currentAST = __currentAST271;
																										_t = __t271;
																										_t = _t.getNextSibling();
																										}
																										currentAST = __currentAST269;
																										_t = __t269;
																										_t = _t.getNextSibling();
																										}
																									}
																									catch (RecognitionException pe) {
																										synPredMatched272 = false;
																									}
																									_t = __t272;
inputState.guessing--;
																								}
																								if ( synPredMatched272 ) {
																									AST __t273 = _t;
																									AST tmp90_AST = null;
																									AST tmp90_AST_in = null;
																									tmp90_AST = astFactory.create((AST)_t);
																									tmp90_AST_in = (AST)_t;
																									ASTPair __currentAST273 = currentAST.copy();
																									currentAST.root = currentAST.child;
																									currentAST.child = null;
																									match(_t,NOT);
																									_t = _t.getFirstChild();
																									{
																									AST __t275 = _t;
																									AST tmp91_AST = null;
																									AST tmp91_AST_in = null;
																									tmp91_AST = astFactory.create((AST)_t);
																									tmp91_AST_in = (AST)_t;
																									ASTPair __currentAST275 = currentAST.copy();
																									currentAST.root = currentAST.child;
																									currentAST.child = null;
																									match(_t,WITHINPEI);
																									_t = _t.getFirstChild();
																									n_wpei_tf = _t==ASTNULL ? null : (AST)_t;
																									trioformula(_t);
																									_t = _retTree;
																									n_wpei_tf_AST = (AST)returnAST;
																									n_wpei_t = _t==ASTNULL ? null : (AST)_t;
																									term(_t);
																									_t = _retTree;
																									n_wpei_t_AST = (AST)returnAST;
																									currentAST = __currentAST275;
																									_t = __t275;
																									_t = _t.getNextSibling();
																									}
																									currentAST = __currentAST273;
																									_t = __t273;
																									_t = _t.getNextSibling();
																									if ( inputState.guessing==0 ) {
																										trioformula_AST = (AST)currentAST.root;
																										
																													trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(LASTEDEI)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp90_AST)).add(n_wpei_tf_AST))).add(n_wpei_t_AST));
																												
																										currentAST.root = trioformula_AST;
																										currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																											trioformula_AST.getFirstChild() : trioformula_AST;
																										currentAST.advanceChildToEnd();
																									}
																								}
																								else {
																									boolean synPredMatched280 = false;
																									if (((_t.getType()==NOT))) {
																										AST __t280 = _t;
																										synPredMatched280 = true;
																										inputState.guessing++;
																										try {
																											{
																											AST __t277 = _t;
																											ASTPair __currentAST277 = currentAST.copy();
																											currentAST.root = currentAST.child;
																											currentAST.child = null;
																											match(_t,NOT);
																											_t = _t.getFirstChild();
																											{
																											AST __t279 = _t;
																											ASTPair __currentAST279 = currentAST.copy();
																											currentAST.root = currentAST.child;
																											currentAST.child = null;
																											match(_t,WITHINPIE);
																											_t = _t.getFirstChild();
																											trioformula(_t);
																											_t = _retTree;
																											term(_t);
																											_t = _retTree;
																											currentAST = __currentAST279;
																											_t = __t279;
																											_t = _t.getNextSibling();
																											}
																											currentAST = __currentAST277;
																											_t = __t277;
																											_t = _t.getNextSibling();
																											}
																										}
																										catch (RecognitionException pe) {
																											synPredMatched280 = false;
																										}
																										_t = __t280;
inputState.guessing--;
																									}
																									if ( synPredMatched280 ) {
																										AST __t281 = _t;
																										AST tmp92_AST = null;
																										AST tmp92_AST_in = null;
																										tmp92_AST = astFactory.create((AST)_t);
																										tmp92_AST_in = (AST)_t;
																										ASTPair __currentAST281 = currentAST.copy();
																										currentAST.root = currentAST.child;
																										currentAST.child = null;
																										match(_t,NOT);
																										_t = _t.getFirstChild();
																										{
																										AST __t283 = _t;
																										AST tmp93_AST = null;
																										AST tmp93_AST_in = null;
																										tmp93_AST = astFactory.create((AST)_t);
																										tmp93_AST_in = (AST)_t;
																										ASTPair __currentAST283 = currentAST.copy();
																										currentAST.root = currentAST.child;
																										currentAST.child = null;
																										match(_t,WITHINPIE);
																										_t = _t.getFirstChild();
																										n_wpie_tf = _t==ASTNULL ? null : (AST)_t;
																										trioformula(_t);
																										_t = _retTree;
																										n_wpie_tf_AST = (AST)returnAST;
																										n_wpie_t = _t==ASTNULL ? null : (AST)_t;
																										term(_t);
																										_t = _retTree;
																										n_wpie_t_AST = (AST)returnAST;
																										currentAST = __currentAST283;
																										_t = __t283;
																										_t = _t.getNextSibling();
																										}
																										currentAST = __currentAST281;
																										_t = __t281;
																										_t = _t.getNextSibling();
																										if ( inputState.guessing==0 ) {
																											trioformula_AST = (AST)currentAST.root;
																											
																														trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(LASTEDIE)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp92_AST)).add(n_wpie_tf_AST))).add(n_wpie_t_AST));
																													
																											currentAST.root = trioformula_AST;
																											currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																												trioformula_AST.getFirstChild() : trioformula_AST;
																											currentAST.advanceChildToEnd();
																										}
																									}
																									else {
																										boolean synPredMatched288 = false;
																										if (((_t.getType()==NOT))) {
																											AST __t288 = _t;
																											synPredMatched288 = true;
																											inputState.guessing++;
																											try {
																												{
																												AST __t285 = _t;
																												ASTPair __currentAST285 = currentAST.copy();
																												currentAST.root = currentAST.child;
																												currentAST.child = null;
																												match(_t,NOT);
																												_t = _t.getFirstChild();
																												{
																												AST __t287 = _t;
																												ASTPair __currentAST287 = currentAST.copy();
																												currentAST.root = currentAST.child;
																												currentAST.child = null;
																												match(_t,WITHINPII);
																												_t = _t.getFirstChild();
																												trioformula(_t);
																												_t = _retTree;
																												term(_t);
																												_t = _retTree;
																												currentAST = __currentAST287;
																												_t = __t287;
																												_t = _t.getNextSibling();
																												}
																												currentAST = __currentAST285;
																												_t = __t285;
																												_t = _t.getNextSibling();
																												}
																											}
																											catch (RecognitionException pe) {
																												synPredMatched288 = false;
																											}
																											_t = __t288;
inputState.guessing--;
																										}
																										if ( synPredMatched288 ) {
																											AST __t289 = _t;
																											AST tmp94_AST = null;
																											AST tmp94_AST_in = null;
																											tmp94_AST = astFactory.create((AST)_t);
																											tmp94_AST_in = (AST)_t;
																											ASTPair __currentAST289 = currentAST.copy();
																											currentAST.root = currentAST.child;
																											currentAST.child = null;
																											match(_t,NOT);
																											_t = _t.getFirstChild();
																											{
																											AST __t291 = _t;
																											AST tmp95_AST = null;
																											AST tmp95_AST_in = null;
																											tmp95_AST = astFactory.create((AST)_t);
																											tmp95_AST_in = (AST)_t;
																											ASTPair __currentAST291 = currentAST.copy();
																											currentAST.root = currentAST.child;
																											currentAST.child = null;
																											match(_t,WITHINPII);
																											_t = _t.getFirstChild();
																											n_wpii_tf = _t==ASTNULL ? null : (AST)_t;
																											trioformula(_t);
																											_t = _retTree;
																											n_wpii_tf_AST = (AST)returnAST;
																											n_wpii_t = _t==ASTNULL ? null : (AST)_t;
																											term(_t);
																											_t = _retTree;
																											n_wpii_t_AST = (AST)returnAST;
																											currentAST = __currentAST291;
																											_t = __t291;
																											_t = _t.getNextSibling();
																											}
																											currentAST = __currentAST289;
																											_t = __t289;
																											_t = _t.getNextSibling();
																											if ( inputState.guessing==0 ) {
																												trioformula_AST = (AST)currentAST.root;
																												
																															trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(LASTEDII)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp94_AST)).add(n_wpii_tf_AST))).add(n_wpii_t_AST));
																														
																												currentAST.root = trioformula_AST;
																												currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																													trioformula_AST.getFirstChild() : trioformula_AST;
																												currentAST.advanceChildToEnd();
																											}
																										}
																										else {
																											boolean synPredMatched296 = false;
																											if (((_t.getType()==NOT))) {
																												AST __t296 = _t;
																												synPredMatched296 = true;
																												inputState.guessing++;
																												try {
																													{
																													AST __t293 = _t;
																													ASTPair __currentAST293 = currentAST.copy();
																													currentAST.root = currentAST.child;
																													currentAST.child = null;
																													match(_t,NOT);
																													_t = _t.getFirstChild();
																													{
																													AST __t295 = _t;
																													ASTPair __currentAST295 = currentAST.copy();
																													currentAST.root = currentAST.child;
																													currentAST.child = null;
																													match(_t,ALWFE);
																													_t = _t.getFirstChild();
																													trioformula(_t);
																													_t = _retTree;
																													currentAST = __currentAST295;
																													_t = __t295;
																													_t = _t.getNextSibling();
																													}
																													currentAST = __currentAST293;
																													_t = __t293;
																													_t = _t.getNextSibling();
																													}
																												}
																												catch (RecognitionException pe) {
																													synPredMatched296 = false;
																												}
																												_t = __t296;
inputState.guessing--;
																											}
																											if ( synPredMatched296 ) {
																												AST __t297 = _t;
																												AST tmp96_AST = null;
																												AST tmp96_AST_in = null;
																												tmp96_AST = astFactory.create((AST)_t);
																												tmp96_AST_in = (AST)_t;
																												ASTPair __currentAST297 = currentAST.copy();
																												currentAST.root = currentAST.child;
																												currentAST.child = null;
																												match(_t,NOT);
																												_t = _t.getFirstChild();
																												{
																												AST __t299 = _t;
																												AST tmp97_AST = null;
																												AST tmp97_AST_in = null;
																												tmp97_AST = astFactory.create((AST)_t);
																												tmp97_AST_in = (AST)_t;
																												ASTPair __currentAST299 = currentAST.copy();
																												currentAST.root = currentAST.child;
																												currentAST.child = null;
																												match(_t,ALWFE);
																												_t = _t.getFirstChild();
																												n_alwfe_tf = _t==ASTNULL ? null : (AST)_t;
																												trioformula(_t);
																												_t = _retTree;
																												n_alwfe_tf_AST = (AST)returnAST;
																												currentAST = __currentAST299;
																												_t = __t299;
																												_t = _t.getNextSibling();
																												}
																												currentAST = __currentAST297;
																												_t = __t297;
																												_t = _t.getNextSibling();
																												if ( inputState.guessing==0 ) {
																													trioformula_AST = (AST)currentAST.root;
																													
																																trioformula_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(SOMFE)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp96_AST)).add(n_alwfe_tf_AST))));
																															
																													currentAST.root = trioformula_AST;
																													currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																														trioformula_AST.getFirstChild() : trioformula_AST;
																													currentAST.advanceChildToEnd();
																												}
																											}
																											else {
																												boolean synPredMatched304 = false;
																												if (((_t.getType()==NOT))) {
																													AST __t304 = _t;
																													synPredMatched304 = true;
																													inputState.guessing++;
																													try {
																														{
																														AST __t301 = _t;
																														ASTPair __currentAST301 = currentAST.copy();
																														currentAST.root = currentAST.child;
																														currentAST.child = null;
																														match(_t,NOT);
																														_t = _t.getFirstChild();
																														{
																														AST __t303 = _t;
																														ASTPair __currentAST303 = currentAST.copy();
																														currentAST.root = currentAST.child;
																														currentAST.child = null;
																														match(_t,ALWFI);
																														_t = _t.getFirstChild();
																														trioformula(_t);
																														_t = _retTree;
																														currentAST = __currentAST303;
																														_t = __t303;
																														_t = _t.getNextSibling();
																														}
																														currentAST = __currentAST301;
																														_t = __t301;
																														_t = _t.getNextSibling();
																														}
																													}
																													catch (RecognitionException pe) {
																														synPredMatched304 = false;
																													}
																													_t = __t304;
inputState.guessing--;
																												}
																												if ( synPredMatched304 ) {
																													AST __t305 = _t;
																													AST tmp98_AST = null;
																													AST tmp98_AST_in = null;
																													tmp98_AST = astFactory.create((AST)_t);
																													tmp98_AST_in = (AST)_t;
																													ASTPair __currentAST305 = currentAST.copy();
																													currentAST.root = currentAST.child;
																													currentAST.child = null;
																													match(_t,NOT);
																													_t = _t.getFirstChild();
																													{
																													AST __t307 = _t;
																													AST tmp99_AST = null;
																													AST tmp99_AST_in = null;
																													tmp99_AST = astFactory.create((AST)_t);
																													tmp99_AST_in = (AST)_t;
																													ASTPair __currentAST307 = currentAST.copy();
																													currentAST.root = currentAST.child;
																													currentAST.child = null;
																													match(_t,ALWFI);
																													_t = _t.getFirstChild();
																													n_alwfi_tf = _t==ASTNULL ? null : (AST)_t;
																													trioformula(_t);
																													_t = _retTree;
																													n_alwfi_tf_AST = (AST)returnAST;
																													currentAST = __currentAST307;
																													_t = __t307;
																													_t = _t.getNextSibling();
																													}
																													currentAST = __currentAST305;
																													_t = __t305;
																													_t = _t.getNextSibling();
																													if ( inputState.guessing==0 ) {
																														trioformula_AST = (AST)currentAST.root;
																														
																																	trioformula_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(SOMFI)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp98_AST)).add(n_alwfi_tf_AST))));
																																
																														currentAST.root = trioformula_AST;
																														currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																															trioformula_AST.getFirstChild() : trioformula_AST;
																														currentAST.advanceChildToEnd();
																													}
																												}
																												else {
																													boolean synPredMatched312 = false;
																													if (((_t.getType()==NOT))) {
																														AST __t312 = _t;
																														synPredMatched312 = true;
																														inputState.guessing++;
																														try {
																															{
																															AST __t309 = _t;
																															ASTPair __currentAST309 = currentAST.copy();
																															currentAST.root = currentAST.child;
																															currentAST.child = null;
																															match(_t,NOT);
																															_t = _t.getFirstChild();
																															{
																															AST __t311 = _t;
																															ASTPair __currentAST311 = currentAST.copy();
																															currentAST.root = currentAST.child;
																															currentAST.child = null;
																															match(_t,ALWPE);
																															_t = _t.getFirstChild();
																															trioformula(_t);
																															_t = _retTree;
																															currentAST = __currentAST311;
																															_t = __t311;
																															_t = _t.getNextSibling();
																															}
																															currentAST = __currentAST309;
																															_t = __t309;
																															_t = _t.getNextSibling();
																															}
																														}
																														catch (RecognitionException pe) {
																															synPredMatched312 = false;
																														}
																														_t = __t312;
inputState.guessing--;
																													}
																													if ( synPredMatched312 ) {
																														AST __t313 = _t;
																														AST tmp100_AST = null;
																														AST tmp100_AST_in = null;
																														tmp100_AST = astFactory.create((AST)_t);
																														tmp100_AST_in = (AST)_t;
																														ASTPair __currentAST313 = currentAST.copy();
																														currentAST.root = currentAST.child;
																														currentAST.child = null;
																														match(_t,NOT);
																														_t = _t.getFirstChild();
																														{
																														AST __t315 = _t;
																														AST tmp101_AST = null;
																														AST tmp101_AST_in = null;
																														tmp101_AST = astFactory.create((AST)_t);
																														tmp101_AST_in = (AST)_t;
																														ASTPair __currentAST315 = currentAST.copy();
																														currentAST.root = currentAST.child;
																														currentAST.child = null;
																														match(_t,ALWPE);
																														_t = _t.getFirstChild();
																														n_alwpe_tf = _t==ASTNULL ? null : (AST)_t;
																														trioformula(_t);
																														_t = _retTree;
																														n_alwpe_tf_AST = (AST)returnAST;
																														currentAST = __currentAST315;
																														_t = __t315;
																														_t = _t.getNextSibling();
																														}
																														currentAST = __currentAST313;
																														_t = __t313;
																														_t = _t.getNextSibling();
																														if ( inputState.guessing==0 ) {
																															trioformula_AST = (AST)currentAST.root;
																															
																																		trioformula_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(SOMPE)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp100_AST)).add(n_alwpe_tf_AST))));
																																	
																															currentAST.root = trioformula_AST;
																															currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																																trioformula_AST.getFirstChild() : trioformula_AST;
																															currentAST.advanceChildToEnd();
																														}
																													}
																													else {
																														boolean synPredMatched320 = false;
																														if (((_t.getType()==NOT))) {
																															AST __t320 = _t;
																															synPredMatched320 = true;
																															inputState.guessing++;
																															try {
																																{
																																AST __t317 = _t;
																																ASTPair __currentAST317 = currentAST.copy();
																																currentAST.root = currentAST.child;
																																currentAST.child = null;
																																match(_t,NOT);
																																_t = _t.getFirstChild();
																																{
																																AST __t319 = _t;
																																ASTPair __currentAST319 = currentAST.copy();
																																currentAST.root = currentAST.child;
																																currentAST.child = null;
																																match(_t,ALWPI);
																																_t = _t.getFirstChild();
																																trioformula(_t);
																																_t = _retTree;
																																currentAST = __currentAST319;
																																_t = __t319;
																																_t = _t.getNextSibling();
																																}
																																currentAST = __currentAST317;
																																_t = __t317;
																																_t = _t.getNextSibling();
																																}
																															}
																															catch (RecognitionException pe) {
																																synPredMatched320 = false;
																															}
																															_t = __t320;
inputState.guessing--;
																														}
																														if ( synPredMatched320 ) {
																															AST __t321 = _t;
																															AST tmp102_AST = null;
																															AST tmp102_AST_in = null;
																															tmp102_AST = astFactory.create((AST)_t);
																															tmp102_AST_in = (AST)_t;
																															ASTPair __currentAST321 = currentAST.copy();
																															currentAST.root = currentAST.child;
																															currentAST.child = null;
																															match(_t,NOT);
																															_t = _t.getFirstChild();
																															{
																															AST __t323 = _t;
																															AST tmp103_AST = null;
																															AST tmp103_AST_in = null;
																															tmp103_AST = astFactory.create((AST)_t);
																															tmp103_AST_in = (AST)_t;
																															ASTPair __currentAST323 = currentAST.copy();
																															currentAST.root = currentAST.child;
																															currentAST.child = null;
																															match(_t,ALWPI);
																															_t = _t.getFirstChild();
																															n_alwpi_tf = _t==ASTNULL ? null : (AST)_t;
																															trioformula(_t);
																															_t = _retTree;
																															n_alwpi_tf_AST = (AST)returnAST;
																															currentAST = __currentAST323;
																															_t = __t323;
																															_t = _t.getNextSibling();
																															}
																															currentAST = __currentAST321;
																															_t = __t321;
																															_t = _t.getNextSibling();
																															if ( inputState.guessing==0 ) {
																																trioformula_AST = (AST)currentAST.root;
																																
																																			trioformula_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(SOMPI)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp102_AST)).add(n_alwpi_tf_AST))));
																																		
																																currentAST.root = trioformula_AST;
																																currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																																	trioformula_AST.getFirstChild() : trioformula_AST;
																																currentAST.advanceChildToEnd();
																															}
																														}
																														else {
																															boolean synPredMatched328 = false;
																															if (((_t.getType()==NOT))) {
																																AST __t328 = _t;
																																synPredMatched328 = true;
																																inputState.guessing++;
																																try {
																																	{
																																	AST __t325 = _t;
																																	ASTPair __currentAST325 = currentAST.copy();
																																	currentAST.root = currentAST.child;
																																	currentAST.child = null;
																																	match(_t,NOT);
																																	_t = _t.getFirstChild();
																																	{
																																	AST __t327 = _t;
																																	ASTPair __currentAST327 = currentAST.copy();
																																	currentAST.root = currentAST.child;
																																	currentAST.child = null;
																																	match(_t,FUTR);
																																	_t = _t.getFirstChild();
																																	trioformula(_t);
																																	_t = _retTree;
																																	term(_t);
																																	_t = _retTree;
																																	currentAST = __currentAST327;
																																	_t = __t327;
																																	_t = _t.getNextSibling();
																																	}
																																	currentAST = __currentAST325;
																																	_t = __t325;
																																	_t = _t.getNextSibling();
																																	}
																																}
																																catch (RecognitionException pe) {
																																	synPredMatched328 = false;
																																}
																																_t = __t328;
inputState.guessing--;
																															}
																															if ( synPredMatched328 ) {
																																AST __t329 = _t;
																																AST tmp104_AST = null;
																																AST tmp104_AST_in = null;
																																tmp104_AST = astFactory.create((AST)_t);
																																tmp104_AST_in = (AST)_t;
																																ASTPair __currentAST329 = currentAST.copy();
																																currentAST.root = currentAST.child;
																																currentAST.child = null;
																																match(_t,NOT);
																																_t = _t.getFirstChild();
																																{
																																AST __t331 = _t;
																																AST tmp105_AST = null;
																																AST tmp105_AST_in = null;
																																tmp105_AST = astFactory.create((AST)_t);
																																tmp105_AST_in = (AST)_t;
																																ASTPair __currentAST331 = currentAST.copy();
																																currentAST.root = currentAST.child;
																																currentAST.child = null;
																																match(_t,FUTR);
																																_t = _t.getFirstChild();
																																n_f_tf = _t==ASTNULL ? null : (AST)_t;
																																trioformula(_t);
																																_t = _retTree;
																																n_f_tf_AST = (AST)returnAST;
																																n_f_t = _t==ASTNULL ? null : (AST)_t;
																																term(_t);
																																_t = _retTree;
																																n_f_t_AST = (AST)returnAST;
																																currentAST = __currentAST331;
																																_t = __t331;
																																_t = _t.getNextSibling();
																																}
																																currentAST = __currentAST329;
																																_t = __t329;
																																_t = _t.getNextSibling();
																																if ( inputState.guessing==0 ) {
																																	trioformula_AST = (AST)currentAST.root;
																																	
																																				trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(FUTR)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp104_AST)).add(n_f_tf_AST))).add(n_f_t_AST));
																																			
																																	currentAST.root = trioformula_AST;
																																	currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																																		trioformula_AST.getFirstChild() : trioformula_AST;
																																	currentAST.advanceChildToEnd();
																																}
																															}
																															else {
																																boolean synPredMatched336 = false;
																																if (((_t.getType()==NOT))) {
																																	AST __t336 = _t;
																																	synPredMatched336 = true;
																																	inputState.guessing++;
																																	try {
																																		{
																																		AST __t333 = _t;
																																		ASTPair __currentAST333 = currentAST.copy();
																																		currentAST.root = currentAST.child;
																																		currentAST.child = null;
																																		match(_t,NOT);
																																		_t = _t.getFirstChild();
																																		{
																																		AST __t335 = _t;
																																		ASTPair __currentAST335 = currentAST.copy();
																																		currentAST.root = currentAST.child;
																																		currentAST.child = null;
																																		match(_t,NOWON);
																																		_t = _t.getFirstChild();
																																		trioformula(_t);
																																		_t = _retTree;
																																		currentAST = __currentAST335;
																																		_t = __t335;
																																		_t = _t.getNextSibling();
																																		}
																																		currentAST = __currentAST333;
																																		_t = __t333;
																																		_t = _t.getNextSibling();
																																		}
																																	}
																																	catch (RecognitionException pe) {
																																		synPredMatched336 = false;
																																	}
																																	_t = __t336;
inputState.guessing--;
																																}
																																if ( synPredMatched336 ) {
																																	AST __t337 = _t;
																																	AST tmp106_AST = null;
																																	AST tmp106_AST_in = null;
																																	tmp106_AST = astFactory.create((AST)_t);
																																	tmp106_AST_in = (AST)_t;
																																	ASTPair __currentAST337 = currentAST.copy();
																																	currentAST.root = currentAST.child;
																																	currentAST.child = null;
																																	match(_t,NOT);
																																	_t = _t.getFirstChild();
																																	{
																																	AST __t339 = _t;
																																	AST tmp107_AST = null;
																																	AST tmp107_AST_in = null;
																																	tmp107_AST = astFactory.create((AST)_t);
																																	tmp107_AST_in = (AST)_t;
																																	ASTPair __currentAST339 = currentAST.copy();
																																	currentAST.root = currentAST.child;
																																	currentAST.child = null;
																																	match(_t,NOWON);
																																	_t = _t.getFirstChild();
																																	n_no_tf = _t==ASTNULL ? null : (AST)_t;
																																	trioformula(_t);
																																	_t = _retTree;
																																	n_no_tf_AST = (AST)returnAST;
																																	currentAST = __currentAST339;
																																	_t = __t339;
																																	_t = _t.getNextSibling();
																																	}
																																	currentAST = __currentAST337;
																																	_t = __t337;
																																	_t = _t.getNextSibling();
																																	if ( inputState.guessing==0 ) {
																																		trioformula_AST = (AST)currentAST.root;
																																		
																																					trioformula_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOWON)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp106_AST)).add(n_no_tf_AST))));
																																				
																																		currentAST.root = trioformula_AST;
																																		currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																																			trioformula_AST.getFirstChild() : trioformula_AST;
																																		currentAST.advanceChildToEnd();
																																	}
																																}
																																else {
																																	boolean synPredMatched344 = false;
																																	if (((_t.getType()==NOT))) {
																																		AST __t344 = _t;
																																		synPredMatched344 = true;
																																		inputState.guessing++;
																																		try {
																																			{
																																			AST __t341 = _t;
																																			ASTPair __currentAST341 = currentAST.copy();
																																			currentAST.root = currentAST.child;
																																			currentAST.child = null;
																																			match(_t,NOT);
																																			_t = _t.getFirstChild();
																																			{
																																			AST __t343 = _t;
																																			ASTPair __currentAST343 = currentAST.copy();
																																			currentAST.root = currentAST.child;
																																			currentAST.child = null;
																																			match(_t,SOMFE);
																																			_t = _t.getFirstChild();
																																			trioformula(_t);
																																			_t = _retTree;
																																			currentAST = __currentAST343;
																																			_t = __t343;
																																			_t = _t.getNextSibling();
																																			}
																																			currentAST = __currentAST341;
																																			_t = __t341;
																																			_t = _t.getNextSibling();
																																			}
																																		}
																																		catch (RecognitionException pe) {
																																			synPredMatched344 = false;
																																		}
																																		_t = __t344;
inputState.guessing--;
																																	}
																																	if ( synPredMatched344 ) {
																																		AST __t345 = _t;
																																		AST tmp108_AST = null;
																																		AST tmp108_AST_in = null;
																																		tmp108_AST = astFactory.create((AST)_t);
																																		tmp108_AST_in = (AST)_t;
																																		ASTPair __currentAST345 = currentAST.copy();
																																		currentAST.root = currentAST.child;
																																		currentAST.child = null;
																																		match(_t,NOT);
																																		_t = _t.getFirstChild();
																																		{
																																		AST __t347 = _t;
																																		AST tmp109_AST = null;
																																		AST tmp109_AST_in = null;
																																		tmp109_AST = astFactory.create((AST)_t);
																																		tmp109_AST_in = (AST)_t;
																																		ASTPair __currentAST347 = currentAST.copy();
																																		currentAST.root = currentAST.child;
																																		currentAST.child = null;
																																		match(_t,SOMFE);
																																		_t = _t.getFirstChild();
																																		n_somfe_tf = _t==ASTNULL ? null : (AST)_t;
																																		trioformula(_t);
																																		_t = _retTree;
																																		n_somfe_tf_AST = (AST)returnAST;
																																		currentAST = __currentAST347;
																																		_t = __t347;
																																		_t = _t.getNextSibling();
																																		}
																																		currentAST = __currentAST345;
																																		_t = __t345;
																																		_t = _t.getNextSibling();
																																		if ( inputState.guessing==0 ) {
																																			trioformula_AST = (AST)currentAST.root;
																																			
																																						trioformula_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(ALWFE)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp108_AST)).add(n_somfe_tf_AST))));
																																					
																																			currentAST.root = trioformula_AST;
																																			currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																																				trioformula_AST.getFirstChild() : trioformula_AST;
																																			currentAST.advanceChildToEnd();
																																		}
																																	}
																																	else {
																																		boolean synPredMatched352 = false;
																																		if (((_t.getType()==NOT))) {
																																			AST __t352 = _t;
																																			synPredMatched352 = true;
																																			inputState.guessing++;
																																			try {
																																				{
																																				AST __t349 = _t;
																																				ASTPair __currentAST349 = currentAST.copy();
																																				currentAST.root = currentAST.child;
																																				currentAST.child = null;
																																				match(_t,NOT);
																																				_t = _t.getFirstChild();
																																				{
																																				AST __t351 = _t;
																																				ASTPair __currentAST351 = currentAST.copy();
																																				currentAST.root = currentAST.child;
																																				currentAST.child = null;
																																				match(_t,SOMFI);
																																				_t = _t.getFirstChild();
																																				trioformula(_t);
																																				_t = _retTree;
																																				currentAST = __currentAST351;
																																				_t = __t351;
																																				_t = _t.getNextSibling();
																																				}
																																				currentAST = __currentAST349;
																																				_t = __t349;
																																				_t = _t.getNextSibling();
																																				}
																																			}
																																			catch (RecognitionException pe) {
																																				synPredMatched352 = false;
																																			}
																																			_t = __t352;
inputState.guessing--;
																																		}
																																		if ( synPredMatched352 ) {
																																			AST __t353 = _t;
																																			AST tmp110_AST = null;
																																			AST tmp110_AST_in = null;
																																			tmp110_AST = astFactory.create((AST)_t);
																																			tmp110_AST_in = (AST)_t;
																																			ASTPair __currentAST353 = currentAST.copy();
																																			currentAST.root = currentAST.child;
																																			currentAST.child = null;
																																			match(_t,NOT);
																																			_t = _t.getFirstChild();
																																			{
																																			AST __t355 = _t;
																																			AST tmp111_AST = null;
																																			AST tmp111_AST_in = null;
																																			tmp111_AST = astFactory.create((AST)_t);
																																			tmp111_AST_in = (AST)_t;
																																			ASTPair __currentAST355 = currentAST.copy();
																																			currentAST.root = currentAST.child;
																																			currentAST.child = null;
																																			match(_t,SOMFI);
																																			_t = _t.getFirstChild();
																																			n_somfi_tf = _t==ASTNULL ? null : (AST)_t;
																																			trioformula(_t);
																																			_t = _retTree;
																																			n_somfi_tf_AST = (AST)returnAST;
																																			currentAST = __currentAST355;
																																			_t = __t355;
																																			_t = _t.getNextSibling();
																																			}
																																			currentAST = __currentAST353;
																																			_t = __t353;
																																			_t = _t.getNextSibling();
																																			if ( inputState.guessing==0 ) {
																																				trioformula_AST = (AST)currentAST.root;
																																				
																																							trioformula_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(ALWFI)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp110_AST)).add(n_somfi_tf_AST))));
																																						
																																				currentAST.root = trioformula_AST;
																																				currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																																					trioformula_AST.getFirstChild() : trioformula_AST;
																																				currentAST.advanceChildToEnd();
																																			}
																																		}
																																		else {
																																			boolean synPredMatched360 = false;
																																			if (((_t.getType()==NOT))) {
																																				AST __t360 = _t;
																																				synPredMatched360 = true;
																																				inputState.guessing++;
																																				try {
																																					{
																																					AST __t357 = _t;
																																					ASTPair __currentAST357 = currentAST.copy();
																																					currentAST.root = currentAST.child;
																																					currentAST.child = null;
																																					match(_t,NOT);
																																					_t = _t.getFirstChild();
																																					{
																																					AST __t359 = _t;
																																					ASTPair __currentAST359 = currentAST.copy();
																																					currentAST.root = currentAST.child;
																																					currentAST.child = null;
																																					match(_t,SOMPE);
																																					_t = _t.getFirstChild();
																																					trioformula(_t);
																																					_t = _retTree;
																																					currentAST = __currentAST359;
																																					_t = __t359;
																																					_t = _t.getNextSibling();
																																					}
																																					currentAST = __currentAST357;
																																					_t = __t357;
																																					_t = _t.getNextSibling();
																																					}
																																				}
																																				catch (RecognitionException pe) {
																																					synPredMatched360 = false;
																																				}
																																				_t = __t360;
inputState.guessing--;
																																			}
																																			if ( synPredMatched360 ) {
																																				AST __t361 = _t;
																																				AST tmp112_AST = null;
																																				AST tmp112_AST_in = null;
																																				tmp112_AST = astFactory.create((AST)_t);
																																				tmp112_AST_in = (AST)_t;
																																				ASTPair __currentAST361 = currentAST.copy();
																																				currentAST.root = currentAST.child;
																																				currentAST.child = null;
																																				match(_t,NOT);
																																				_t = _t.getFirstChild();
																																				{
																																				AST __t363 = _t;
																																				AST tmp113_AST = null;
																																				AST tmp113_AST_in = null;
																																				tmp113_AST = astFactory.create((AST)_t);
																																				tmp113_AST_in = (AST)_t;
																																				ASTPair __currentAST363 = currentAST.copy();
																																				currentAST.root = currentAST.child;
																																				currentAST.child = null;
																																				match(_t,SOMPE);
																																				_t = _t.getFirstChild();
																																				n_sompe_tf = _t==ASTNULL ? null : (AST)_t;
																																				trioformula(_t);
																																				_t = _retTree;
																																				n_sompe_tf_AST = (AST)returnAST;
																																				currentAST = __currentAST363;
																																				_t = __t363;
																																				_t = _t.getNextSibling();
																																				}
																																				currentAST = __currentAST361;
																																				_t = __t361;
																																				_t = _t.getNextSibling();
																																				if ( inputState.guessing==0 ) {
																																					trioformula_AST = (AST)currentAST.root;
																																					
																																								trioformula_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(ALWPE)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp112_AST)).add(n_sompe_tf_AST))));
																																							
																																					currentAST.root = trioformula_AST;
																																					currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																																						trioformula_AST.getFirstChild() : trioformula_AST;
																																					currentAST.advanceChildToEnd();
																																				}
																																			}
																																			else {
																																				boolean synPredMatched368 = false;
																																				if (((_t.getType()==NOT))) {
																																					AST __t368 = _t;
																																					synPredMatched368 = true;
																																					inputState.guessing++;
																																					try {
																																						{
																																						AST __t365 = _t;
																																						ASTPair __currentAST365 = currentAST.copy();
																																						currentAST.root = currentAST.child;
																																						currentAST.child = null;
																																						match(_t,NOT);
																																						_t = _t.getFirstChild();
																																						{
																																						AST __t367 = _t;
																																						ASTPair __currentAST367 = currentAST.copy();
																																						currentAST.root = currentAST.child;
																																						currentAST.child = null;
																																						match(_t,SOMPI);
																																						_t = _t.getFirstChild();
																																						trioformula(_t);
																																						_t = _retTree;
																																						currentAST = __currentAST367;
																																						_t = __t367;
																																						_t = _t.getNextSibling();
																																						}
																																						currentAST = __currentAST365;
																																						_t = __t365;
																																						_t = _t.getNextSibling();
																																						}
																																					}
																																					catch (RecognitionException pe) {
																																						synPredMatched368 = false;
																																					}
																																					_t = __t368;
inputState.guessing--;
																																				}
																																				if ( synPredMatched368 ) {
																																					AST __t369 = _t;
																																					AST tmp114_AST = null;
																																					AST tmp114_AST_in = null;
																																					tmp114_AST = astFactory.create((AST)_t);
																																					tmp114_AST_in = (AST)_t;
																																					ASTPair __currentAST369 = currentAST.copy();
																																					currentAST.root = currentAST.child;
																																					currentAST.child = null;
																																					match(_t,NOT);
																																					_t = _t.getFirstChild();
																																					{
																																					AST __t371 = _t;
																																					AST tmp115_AST = null;
																																					AST tmp115_AST_in = null;
																																					tmp115_AST = astFactory.create((AST)_t);
																																					tmp115_AST_in = (AST)_t;
																																					ASTPair __currentAST371 = currentAST.copy();
																																					currentAST.root = currentAST.child;
																																					currentAST.child = null;
																																					match(_t,SOMPI);
																																					_t = _t.getFirstChild();
																																					n_sompi_tf = _t==ASTNULL ? null : (AST)_t;
																																					trioformula(_t);
																																					_t = _retTree;
																																					n_sompi_tf_AST = (AST)returnAST;
																																					currentAST = __currentAST371;
																																					_t = __t371;
																																					_t = _t.getNextSibling();
																																					}
																																					currentAST = __currentAST369;
																																					_t = __t369;
																																					_t = _t.getNextSibling();
																																					if ( inputState.guessing==0 ) {
																																						trioformula_AST = (AST)currentAST.root;
																																						
																																									trioformula_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(ALWPI)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp114_AST)).add(n_sompi_tf_AST))));
																																								
																																						currentAST.root = trioformula_AST;
																																						currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																																							trioformula_AST.getFirstChild() : trioformula_AST;
																																						currentAST.advanceChildToEnd();
																																					}
																																				}
																																				else if ((_t.getType()==NOT)) {
																																					AST __t372 = _t;
																																					AST tmp116_AST = null;
																																					AST tmp116_AST_in = null;
																																					tmp116_AST = astFactory.create((AST)_t);
																																					tmp116_AST_in = (AST)_t;
																																					astFactory.addASTChild(currentAST, tmp116_AST);
																																					ASTPair __currentAST372 = currentAST.copy();
																																					currentAST.root = currentAST.child;
																																					currentAST.child = null;
																																					match(_t,NOT);
																																					_t = _t.getFirstChild();
																																					trioformula(_t);
																																					_t = _retTree;
																																					astFactory.addASTChild(currentAST, returnAST);
																																					currentAST = __currentAST372;
																																					_t = __t372;
																																					_t = _t.getNextSibling();
																																					trioformula_AST = (AST)currentAST.root;
																																				}
																																				else {
																																					boolean synPredMatched416 = false;
																																					if (((_t.getType()==WITHINFEE))) {
																																						AST __t416 = _t;
																																						synPredMatched416 = true;
																																						inputState.guessing++;
																																						try {
																																							{
																																							AST __t415 = _t;
																																							ASTPair __currentAST415 = currentAST.copy();
																																							currentAST.root = currentAST.child;
																																							currentAST.child = null;
																																							match(_t,WITHINFEE);
																																							_t = _t.getFirstChild();
																																							trioformula(_t);
																																							_t = _retTree;
																																							term(_t);
																																							_t = _retTree;
																																							term(_t);
																																							_t = _retTree;
																																							currentAST = __currentAST415;
																																							_t = __t415;
																																							_t = _t.getNextSibling();
																																							}
																																						}
																																						catch (RecognitionException pe) {
																																							synPredMatched416 = false;
																																						}
																																						_t = __t416;
inputState.guessing--;
																																					}
																																					if ( synPredMatched416 ) {
																																						AST __t417 = _t;
																																						AST tmp117_AST = null;
																																						AST tmp117_AST_in = null;
																																						tmp117_AST = astFactory.create((AST)_t);
																																						tmp117_AST_in = (AST)_t;
																																						astFactory.addASTChild(currentAST, tmp117_AST);
																																						ASTPair __currentAST417 = currentAST.copy();
																																						currentAST.root = currentAST.child;
																																						currentAST.child = null;
																																						match(_t,WITHINFEE);
																																						_t = _t.getFirstChild();
																																						trioformula(_t);
																																						_t = _retTree;
																																						astFactory.addASTChild(currentAST, returnAST);
																																						term(_t);
																																						_t = _retTree;
																																						astFactory.addASTChild(currentAST, returnAST);
																																						term(_t);
																																						_t = _retTree;
																																						astFactory.addASTChild(currentAST, returnAST);
																																						currentAST = __currentAST417;
																																						_t = __t417;
																																						_t = _t.getNextSibling();
																																						trioformula_AST = (AST)currentAST.root;
																																					}
																																					else {
																																						boolean synPredMatched420 = false;
																																						if (((_t.getType()==WITHINFEE))) {
																																							AST __t420 = _t;
																																							synPredMatched420 = true;
																																							inputState.guessing++;
																																							try {
																																								{
																																								AST __t419 = _t;
																																								ASTPair __currentAST419 = currentAST.copy();
																																								currentAST.root = currentAST.child;
																																								currentAST.child = null;
																																								match(_t,WITHINFEE);
																																								_t = _t.getFirstChild();
																																								trioformula(_t);
																																								_t = _retTree;
																																								term(_t);
																																								_t = _retTree;
																																								currentAST = __currentAST419;
																																								_t = __t419;
																																								_t = _t.getNextSibling();
																																								}
																																							}
																																							catch (RecognitionException pe) {
																																								synPredMatched420 = false;
																																							}
																																							_t = __t420;
inputState.guessing--;
																																						}
																																						if ( synPredMatched420 ) {
																																							AST __t421 = _t;
																																							AST tmp118_AST = null;
																																							AST tmp118_AST_in = null;
																																							tmp118_AST = astFactory.create((AST)_t);
																																							tmp118_AST_in = (AST)_t;
																																							astFactory.addASTChild(currentAST, tmp118_AST);
																																							ASTPair __currentAST421 = currentAST.copy();
																																							currentAST.root = currentAST.child;
																																							currentAST.child = null;
																																							match(_t,WITHINFEE);
																																							_t = _t.getFirstChild();
																																							trioformula(_t);
																																							_t = _retTree;
																																							astFactory.addASTChild(currentAST, returnAST);
																																							term(_t);
																																							_t = _retTree;
																																							astFactory.addASTChild(currentAST, returnAST);
																																							currentAST = __currentAST421;
																																							_t = __t421;
																																							_t = _t.getNextSibling();
																																							trioformula_AST = (AST)currentAST.root;
																																						}
																																						else {
																																							boolean synPredMatched424 = false;
																																							if (((_t.getType()==WITHINFEI))) {
																																								AST __t424 = _t;
																																								synPredMatched424 = true;
																																								inputState.guessing++;
																																								try {
																																									{
																																									AST __t423 = _t;
																																									ASTPair __currentAST423 = currentAST.copy();
																																									currentAST.root = currentAST.child;
																																									currentAST.child = null;
																																									match(_t,WITHINFEI);
																																									_t = _t.getFirstChild();
																																									trioformula(_t);
																																									_t = _retTree;
																																									term(_t);
																																									_t = _retTree;
																																									term(_t);
																																									_t = _retTree;
																																									currentAST = __currentAST423;
																																									_t = __t423;
																																									_t = _t.getNextSibling();
																																									}
																																								}
																																								catch (RecognitionException pe) {
																																									synPredMatched424 = false;
																																								}
																																								_t = __t424;
inputState.guessing--;
																																							}
																																							if ( synPredMatched424 ) {
																																								AST __t425 = _t;
																																								AST tmp119_AST = null;
																																								AST tmp119_AST_in = null;
																																								tmp119_AST = astFactory.create((AST)_t);
																																								tmp119_AST_in = (AST)_t;
																																								astFactory.addASTChild(currentAST, tmp119_AST);
																																								ASTPair __currentAST425 = currentAST.copy();
																																								currentAST.root = currentAST.child;
																																								currentAST.child = null;
																																								match(_t,WITHINFEI);
																																								_t = _t.getFirstChild();
																																								trioformula(_t);
																																								_t = _retTree;
																																								astFactory.addASTChild(currentAST, returnAST);
																																								term(_t);
																																								_t = _retTree;
																																								astFactory.addASTChild(currentAST, returnAST);
																																								term(_t);
																																								_t = _retTree;
																																								astFactory.addASTChild(currentAST, returnAST);
																																								currentAST = __currentAST425;
																																								_t = __t425;
																																								_t = _t.getNextSibling();
																																								trioformula_AST = (AST)currentAST.root;
																																							}
																																							else {
																																								boolean synPredMatched428 = false;
																																								if (((_t.getType()==WITHINFEI))) {
																																									AST __t428 = _t;
																																									synPredMatched428 = true;
																																									inputState.guessing++;
																																									try {
																																										{
																																										AST __t427 = _t;
																																										ASTPair __currentAST427 = currentAST.copy();
																																										currentAST.root = currentAST.child;
																																										currentAST.child = null;
																																										match(_t,WITHINFEI);
																																										_t = _t.getFirstChild();
																																										trioformula(_t);
																																										_t = _retTree;
																																										term(_t);
																																										_t = _retTree;
																																										currentAST = __currentAST427;
																																										_t = __t427;
																																										_t = _t.getNextSibling();
																																										}
																																									}
																																									catch (RecognitionException pe) {
																																										synPredMatched428 = false;
																																									}
																																									_t = __t428;
inputState.guessing--;
																																								}
																																								if ( synPredMatched428 ) {
																																									AST __t429 = _t;
																																									AST tmp120_AST = null;
																																									AST tmp120_AST_in = null;
																																									tmp120_AST = astFactory.create((AST)_t);
																																									tmp120_AST_in = (AST)_t;
																																									astFactory.addASTChild(currentAST, tmp120_AST);
																																									ASTPair __currentAST429 = currentAST.copy();
																																									currentAST.root = currentAST.child;
																																									currentAST.child = null;
																																									match(_t,WITHINFEI);
																																									_t = _t.getFirstChild();
																																									trioformula(_t);
																																									_t = _retTree;
																																									astFactory.addASTChild(currentAST, returnAST);
																																									term(_t);
																																									_t = _retTree;
																																									astFactory.addASTChild(currentAST, returnAST);
																																									currentAST = __currentAST429;
																																									_t = __t429;
																																									_t = _t.getNextSibling();
																																									trioformula_AST = (AST)currentAST.root;
																																								}
																																								else {
																																									boolean synPredMatched432 = false;
																																									if (((_t.getType()==WITHINFIE))) {
																																										AST __t432 = _t;
																																										synPredMatched432 = true;
																																										inputState.guessing++;
																																										try {
																																											{
																																											AST __t431 = _t;
																																											ASTPair __currentAST431 = currentAST.copy();
																																											currentAST.root = currentAST.child;
																																											currentAST.child = null;
																																											match(_t,WITHINFIE);
																																											_t = _t.getFirstChild();
																																											trioformula(_t);
																																											_t = _retTree;
																																											term(_t);
																																											_t = _retTree;
																																											term(_t);
																																											_t = _retTree;
																																											currentAST = __currentAST431;
																																											_t = __t431;
																																											_t = _t.getNextSibling();
																																											}
																																										}
																																										catch (RecognitionException pe) {
																																											synPredMatched432 = false;
																																										}
																																										_t = __t432;
inputState.guessing--;
																																									}
																																									if ( synPredMatched432 ) {
																																										AST __t433 = _t;
																																										AST tmp121_AST = null;
																																										AST tmp121_AST_in = null;
																																										tmp121_AST = astFactory.create((AST)_t);
																																										tmp121_AST_in = (AST)_t;
																																										astFactory.addASTChild(currentAST, tmp121_AST);
																																										ASTPair __currentAST433 = currentAST.copy();
																																										currentAST.root = currentAST.child;
																																										currentAST.child = null;
																																										match(_t,WITHINFIE);
																																										_t = _t.getFirstChild();
																																										trioformula(_t);
																																										_t = _retTree;
																																										astFactory.addASTChild(currentAST, returnAST);
																																										term(_t);
																																										_t = _retTree;
																																										astFactory.addASTChild(currentAST, returnAST);
																																										term(_t);
																																										_t = _retTree;
																																										astFactory.addASTChild(currentAST, returnAST);
																																										currentAST = __currentAST433;
																																										_t = __t433;
																																										_t = _t.getNextSibling();
																																										trioformula_AST = (AST)currentAST.root;
																																									}
																																									else {
																																										boolean synPredMatched436 = false;
																																										if (((_t.getType()==WITHINFIE))) {
																																											AST __t436 = _t;
																																											synPredMatched436 = true;
																																											inputState.guessing++;
																																											try {
																																												{
																																												AST __t435 = _t;
																																												ASTPair __currentAST435 = currentAST.copy();
																																												currentAST.root = currentAST.child;
																																												currentAST.child = null;
																																												match(_t,WITHINFIE);
																																												_t = _t.getFirstChild();
																																												trioformula(_t);
																																												_t = _retTree;
																																												term(_t);
																																												_t = _retTree;
																																												currentAST = __currentAST435;
																																												_t = __t435;
																																												_t = _t.getNextSibling();
																																												}
																																											}
																																											catch (RecognitionException pe) {
																																												synPredMatched436 = false;
																																											}
																																											_t = __t436;
inputState.guessing--;
																																										}
																																										if ( synPredMatched436 ) {
																																											AST __t437 = _t;
																																											AST tmp122_AST = null;
																																											AST tmp122_AST_in = null;
																																											tmp122_AST = astFactory.create((AST)_t);
																																											tmp122_AST_in = (AST)_t;
																																											astFactory.addASTChild(currentAST, tmp122_AST);
																																											ASTPair __currentAST437 = currentAST.copy();
																																											currentAST.root = currentAST.child;
																																											currentAST.child = null;
																																											match(_t,WITHINFIE);
																																											_t = _t.getFirstChild();
																																											trioformula(_t);
																																											_t = _retTree;
																																											astFactory.addASTChild(currentAST, returnAST);
																																											term(_t);
																																											_t = _retTree;
																																											astFactory.addASTChild(currentAST, returnAST);
																																											currentAST = __currentAST437;
																																											_t = __t437;
																																											_t = _t.getNextSibling();
																																											trioformula_AST = (AST)currentAST.root;
																																										}
																																										else {
																																											boolean synPredMatched440 = false;
																																											if (((_t.getType()==WITHINFII))) {
																																												AST __t440 = _t;
																																												synPredMatched440 = true;
																																												inputState.guessing++;
																																												try {
																																													{
																																													AST __t439 = _t;
																																													ASTPair __currentAST439 = currentAST.copy();
																																													currentAST.root = currentAST.child;
																																													currentAST.child = null;
																																													match(_t,WITHINFII);
																																													_t = _t.getFirstChild();
																																													trioformula(_t);
																																													_t = _retTree;
																																													term(_t);
																																													_t = _retTree;
																																													term(_t);
																																													_t = _retTree;
																																													currentAST = __currentAST439;
																																													_t = __t439;
																																													_t = _t.getNextSibling();
																																													}
																																												}
																																												catch (RecognitionException pe) {
																																													synPredMatched440 = false;
																																												}
																																												_t = __t440;
inputState.guessing--;
																																											}
																																											if ( synPredMatched440 ) {
																																												AST __t441 = _t;
																																												AST tmp123_AST = null;
																																												AST tmp123_AST_in = null;
																																												tmp123_AST = astFactory.create((AST)_t);
																																												tmp123_AST_in = (AST)_t;
																																												astFactory.addASTChild(currentAST, tmp123_AST);
																																												ASTPair __currentAST441 = currentAST.copy();
																																												currentAST.root = currentAST.child;
																																												currentAST.child = null;
																																												match(_t,WITHINFII);
																																												_t = _t.getFirstChild();
																																												trioformula(_t);
																																												_t = _retTree;
																																												astFactory.addASTChild(currentAST, returnAST);
																																												term(_t);
																																												_t = _retTree;
																																												astFactory.addASTChild(currentAST, returnAST);
																																												term(_t);
																																												_t = _retTree;
																																												astFactory.addASTChild(currentAST, returnAST);
																																												currentAST = __currentAST441;
																																												_t = __t441;
																																												_t = _t.getNextSibling();
																																												trioformula_AST = (AST)currentAST.root;
																																											}
																																											else {
																																												boolean synPredMatched444 = false;
																																												if (((_t.getType()==WITHINFII))) {
																																													AST __t444 = _t;
																																													synPredMatched444 = true;
																																													inputState.guessing++;
																																													try {
																																														{
																																														AST __t443 = _t;
																																														ASTPair __currentAST443 = currentAST.copy();
																																														currentAST.root = currentAST.child;
																																														currentAST.child = null;
																																														match(_t,WITHINFII);
																																														_t = _t.getFirstChild();
																																														trioformula(_t);
																																														_t = _retTree;
																																														term(_t);
																																														_t = _retTree;
																																														currentAST = __currentAST443;
																																														_t = __t443;
																																														_t = _t.getNextSibling();
																																														}
																																													}
																																													catch (RecognitionException pe) {
																																														synPredMatched444 = false;
																																													}
																																													_t = __t444;
inputState.guessing--;
																																												}
																																												if ( synPredMatched444 ) {
																																													AST __t445 = _t;
																																													AST tmp124_AST = null;
																																													AST tmp124_AST_in = null;
																																													tmp124_AST = astFactory.create((AST)_t);
																																													tmp124_AST_in = (AST)_t;
																																													astFactory.addASTChild(currentAST, tmp124_AST);
																																													ASTPair __currentAST445 = currentAST.copy();
																																													currentAST.root = currentAST.child;
																																													currentAST.child = null;
																																													match(_t,WITHINFII);
																																													_t = _t.getFirstChild();
																																													trioformula(_t);
																																													_t = _retTree;
																																													astFactory.addASTChild(currentAST, returnAST);
																																													term(_t);
																																													_t = _retTree;
																																													astFactory.addASTChild(currentAST, returnAST);
																																													currentAST = __currentAST445;
																																													_t = __t445;
																																													_t = _t.getNextSibling();
																																													trioformula_AST = (AST)currentAST.root;
																																												}
																																												else {
																																													boolean synPredMatched448 = false;
																																													if (((_t.getType()==WITHINPEE))) {
																																														AST __t448 = _t;
																																														synPredMatched448 = true;
																																														inputState.guessing++;
																																														try {
																																															{
																																															AST __t447 = _t;
																																															ASTPair __currentAST447 = currentAST.copy();
																																															currentAST.root = currentAST.child;
																																															currentAST.child = null;
																																															match(_t,WITHINPEE);
																																															_t = _t.getFirstChild();
																																															trioformula(_t);
																																															_t = _retTree;
																																															term(_t);
																																															_t = _retTree;
																																															term(_t);
																																															_t = _retTree;
																																															currentAST = __currentAST447;
																																															_t = __t447;
																																															_t = _t.getNextSibling();
																																															}
																																														}
																																														catch (RecognitionException pe) {
																																															synPredMatched448 = false;
																																														}
																																														_t = __t448;
inputState.guessing--;
																																													}
																																													if ( synPredMatched448 ) {
																																														AST __t449 = _t;
																																														AST tmp125_AST = null;
																																														AST tmp125_AST_in = null;
																																														tmp125_AST = astFactory.create((AST)_t);
																																														tmp125_AST_in = (AST)_t;
																																														astFactory.addASTChild(currentAST, tmp125_AST);
																																														ASTPair __currentAST449 = currentAST.copy();
																																														currentAST.root = currentAST.child;
																																														currentAST.child = null;
																																														match(_t,WITHINPEE);
																																														_t = _t.getFirstChild();
																																														trioformula(_t);
																																														_t = _retTree;
																																														astFactory.addASTChild(currentAST, returnAST);
																																														term(_t);
																																														_t = _retTree;
																																														astFactory.addASTChild(currentAST, returnAST);
																																														term(_t);
																																														_t = _retTree;
																																														astFactory.addASTChild(currentAST, returnAST);
																																														currentAST = __currentAST449;
																																														_t = __t449;
																																														_t = _t.getNextSibling();
																																														trioformula_AST = (AST)currentAST.root;
																																													}
																																													else {
																																														boolean synPredMatched452 = false;
																																														if (((_t.getType()==WITHINPEE))) {
																																															AST __t452 = _t;
																																															synPredMatched452 = true;
																																															inputState.guessing++;
																																															try {
																																																{
																																																AST __t451 = _t;
																																																ASTPair __currentAST451 = currentAST.copy();
																																																currentAST.root = currentAST.child;
																																																currentAST.child = null;
																																																match(_t,WITHINPEE);
																																																_t = _t.getFirstChild();
																																																trioformula(_t);
																																																_t = _retTree;
																																																term(_t);
																																																_t = _retTree;
																																																currentAST = __currentAST451;
																																																_t = __t451;
																																																_t = _t.getNextSibling();
																																																}
																																															}
																																															catch (RecognitionException pe) {
																																																synPredMatched452 = false;
																																															}
																																															_t = __t452;
inputState.guessing--;
																																														}
																																														if ( synPredMatched452 ) {
																																															AST __t453 = _t;
																																															AST tmp126_AST = null;
																																															AST tmp126_AST_in = null;
																																															tmp126_AST = astFactory.create((AST)_t);
																																															tmp126_AST_in = (AST)_t;
																																															astFactory.addASTChild(currentAST, tmp126_AST);
																																															ASTPair __currentAST453 = currentAST.copy();
																																															currentAST.root = currentAST.child;
																																															currentAST.child = null;
																																															match(_t,WITHINPEE);
																																															_t = _t.getFirstChild();
																																															trioformula(_t);
																																															_t = _retTree;
																																															astFactory.addASTChild(currentAST, returnAST);
																																															term(_t);
																																															_t = _retTree;
																																															astFactory.addASTChild(currentAST, returnAST);
																																															currentAST = __currentAST453;
																																															_t = __t453;
																																															_t = _t.getNextSibling();
																																															trioformula_AST = (AST)currentAST.root;
																																														}
																																														else {
																																															boolean synPredMatched456 = false;
																																															if (((_t.getType()==WITHINPEI))) {
																																																AST __t456 = _t;
																																																synPredMatched456 = true;
																																																inputState.guessing++;
																																																try {
																																																	{
																																																	AST __t455 = _t;
																																																	ASTPair __currentAST455 = currentAST.copy();
																																																	currentAST.root = currentAST.child;
																																																	currentAST.child = null;
																																																	match(_t,WITHINPEI);
																																																	_t = _t.getFirstChild();
																																																	trioformula(_t);
																																																	_t = _retTree;
																																																	term(_t);
																																																	_t = _retTree;
																																																	term(_t);
																																																	_t = _retTree;
																																																	currentAST = __currentAST455;
																																																	_t = __t455;
																																																	_t = _t.getNextSibling();
																																																	}
																																																}
																																																catch (RecognitionException pe) {
																																																	synPredMatched456 = false;
																																																}
																																																_t = __t456;
inputState.guessing--;
																																															}
																																															if ( synPredMatched456 ) {
																																																AST __t457 = _t;
																																																AST tmp127_AST = null;
																																																AST tmp127_AST_in = null;
																																																tmp127_AST = astFactory.create((AST)_t);
																																																tmp127_AST_in = (AST)_t;
																																																astFactory.addASTChild(currentAST, tmp127_AST);
																																																ASTPair __currentAST457 = currentAST.copy();
																																																currentAST.root = currentAST.child;
																																																currentAST.child = null;
																																																match(_t,WITHINPEI);
																																																_t = _t.getFirstChild();
																																																trioformula(_t);
																																																_t = _retTree;
																																																astFactory.addASTChild(currentAST, returnAST);
																																																term(_t);
																																																_t = _retTree;
																																																astFactory.addASTChild(currentAST, returnAST);
																																																term(_t);
																																																_t = _retTree;
																																																astFactory.addASTChild(currentAST, returnAST);
																																																currentAST = __currentAST457;
																																																_t = __t457;
																																																_t = _t.getNextSibling();
																																																trioformula_AST = (AST)currentAST.root;
																																															}
																																															else {
																																																boolean synPredMatched460 = false;
																																																if (((_t.getType()==WITHINPEI))) {
																																																	AST __t460 = _t;
																																																	synPredMatched460 = true;
																																																	inputState.guessing++;
																																																	try {
																																																		{
																																																		AST __t459 = _t;
																																																		ASTPair __currentAST459 = currentAST.copy();
																																																		currentAST.root = currentAST.child;
																																																		currentAST.child = null;
																																																		match(_t,WITHINPEI);
																																																		_t = _t.getFirstChild();
																																																		trioformula(_t);
																																																		_t = _retTree;
																																																		term(_t);
																																																		_t = _retTree;
																																																		currentAST = __currentAST459;
																																																		_t = __t459;
																																																		_t = _t.getNextSibling();
																																																		}
																																																	}
																																																	catch (RecognitionException pe) {
																																																		synPredMatched460 = false;
																																																	}
																																																	_t = __t460;
inputState.guessing--;
																																																}
																																																if ( synPredMatched460 ) {
																																																	AST __t461 = _t;
																																																	AST tmp128_AST = null;
																																																	AST tmp128_AST_in = null;
																																																	tmp128_AST = astFactory.create((AST)_t);
																																																	tmp128_AST_in = (AST)_t;
																																																	astFactory.addASTChild(currentAST, tmp128_AST);
																																																	ASTPair __currentAST461 = currentAST.copy();
																																																	currentAST.root = currentAST.child;
																																																	currentAST.child = null;
																																																	match(_t,WITHINPEI);
																																																	_t = _t.getFirstChild();
																																																	trioformula(_t);
																																																	_t = _retTree;
																																																	astFactory.addASTChild(currentAST, returnAST);
																																																	term(_t);
																																																	_t = _retTree;
																																																	astFactory.addASTChild(currentAST, returnAST);
																																																	currentAST = __currentAST461;
																																																	_t = __t461;
																																																	_t = _t.getNextSibling();
																																																	trioformula_AST = (AST)currentAST.root;
																																																}
																																																else {
																																																	boolean synPredMatched464 = false;
																																																	if (((_t.getType()==WITHINPIE))) {
																																																		AST __t464 = _t;
																																																		synPredMatched464 = true;
																																																		inputState.guessing++;
																																																		try {
																																																			{
																																																			AST __t463 = _t;
																																																			ASTPair __currentAST463 = currentAST.copy();
																																																			currentAST.root = currentAST.child;
																																																			currentAST.child = null;
																																																			match(_t,WITHINPIE);
																																																			_t = _t.getFirstChild();
																																																			trioformula(_t);
																																																			_t = _retTree;
																																																			term(_t);
																																																			_t = _retTree;
																																																			term(_t);
																																																			_t = _retTree;
																																																			currentAST = __currentAST463;
																																																			_t = __t463;
																																																			_t = _t.getNextSibling();
																																																			}
																																																		}
																																																		catch (RecognitionException pe) {
																																																			synPredMatched464 = false;
																																																		}
																																																		_t = __t464;
inputState.guessing--;
																																																	}
																																																	if ( synPredMatched464 ) {
																																																		AST __t465 = _t;
																																																		AST tmp129_AST = null;
																																																		AST tmp129_AST_in = null;
																																																		tmp129_AST = astFactory.create((AST)_t);
																																																		tmp129_AST_in = (AST)_t;
																																																		astFactory.addASTChild(currentAST, tmp129_AST);
																																																		ASTPair __currentAST465 = currentAST.copy();
																																																		currentAST.root = currentAST.child;
																																																		currentAST.child = null;
																																																		match(_t,WITHINPIE);
																																																		_t = _t.getFirstChild();
																																																		trioformula(_t);
																																																		_t = _retTree;
																																																		astFactory.addASTChild(currentAST, returnAST);
																																																		term(_t);
																																																		_t = _retTree;
																																																		astFactory.addASTChild(currentAST, returnAST);
																																																		term(_t);
																																																		_t = _retTree;
																																																		astFactory.addASTChild(currentAST, returnAST);
																																																		currentAST = __currentAST465;
																																																		_t = __t465;
																																																		_t = _t.getNextSibling();
																																																		trioformula_AST = (AST)currentAST.root;
																																																	}
																																																	else {
																																																		boolean synPredMatched468 = false;
																																																		if (((_t.getType()==WITHINPIE))) {
																																																			AST __t468 = _t;
																																																			synPredMatched468 = true;
																																																			inputState.guessing++;
																																																			try {
																																																				{
																																																				AST __t467 = _t;
																																																				ASTPair __currentAST467 = currentAST.copy();
																																																				currentAST.root = currentAST.child;
																																																				currentAST.child = null;
																																																				match(_t,WITHINPIE);
																																																				_t = _t.getFirstChild();
																																																				trioformula(_t);
																																																				_t = _retTree;
																																																				term(_t);
																																																				_t = _retTree;
																																																				currentAST = __currentAST467;
																																																				_t = __t467;
																																																				_t = _t.getNextSibling();
																																																				}
																																																			}
																																																			catch (RecognitionException pe) {
																																																				synPredMatched468 = false;
																																																			}
																																																			_t = __t468;
inputState.guessing--;
																																																		}
																																																		if ( synPredMatched468 ) {
																																																			AST __t469 = _t;
																																																			AST tmp130_AST = null;
																																																			AST tmp130_AST_in = null;
																																																			tmp130_AST = astFactory.create((AST)_t);
																																																			tmp130_AST_in = (AST)_t;
																																																			astFactory.addASTChild(currentAST, tmp130_AST);
																																																			ASTPair __currentAST469 = currentAST.copy();
																																																			currentAST.root = currentAST.child;
																																																			currentAST.child = null;
																																																			match(_t,WITHINPIE);
																																																			_t = _t.getFirstChild();
																																																			trioformula(_t);
																																																			_t = _retTree;
																																																			astFactory.addASTChild(currentAST, returnAST);
																																																			term(_t);
																																																			_t = _retTree;
																																																			astFactory.addASTChild(currentAST, returnAST);
																																																			currentAST = __currentAST469;
																																																			_t = __t469;
																																																			_t = _t.getNextSibling();
																																																			trioformula_AST = (AST)currentAST.root;
																																																		}
																																																		else {
																																																			boolean synPredMatched472 = false;
																																																			if (((_t.getType()==WITHINPII))) {
																																																				AST __t472 = _t;
																																																				synPredMatched472 = true;
																																																				inputState.guessing++;
																																																				try {
																																																					{
																																																					AST __t471 = _t;
																																																					ASTPair __currentAST471 = currentAST.copy();
																																																					currentAST.root = currentAST.child;
																																																					currentAST.child = null;
																																																					match(_t,WITHINPII);
																																																					_t = _t.getFirstChild();
																																																					trioformula(_t);
																																																					_t = _retTree;
																																																					term(_t);
																																																					_t = _retTree;
																																																					term(_t);
																																																					_t = _retTree;
																																																					currentAST = __currentAST471;
																																																					_t = __t471;
																																																					_t = _t.getNextSibling();
																																																					}
																																																				}
																																																				catch (RecognitionException pe) {
																																																					synPredMatched472 = false;
																																																				}
																																																				_t = __t472;
inputState.guessing--;
																																																			}
																																																			if ( synPredMatched472 ) {
																																																				AST __t473 = _t;
																																																				AST tmp131_AST = null;
																																																				AST tmp131_AST_in = null;
																																																				tmp131_AST = astFactory.create((AST)_t);
																																																				tmp131_AST_in = (AST)_t;
																																																				astFactory.addASTChild(currentAST, tmp131_AST);
																																																				ASTPair __currentAST473 = currentAST.copy();
																																																				currentAST.root = currentAST.child;
																																																				currentAST.child = null;
																																																				match(_t,WITHINPII);
																																																				_t = _t.getFirstChild();
																																																				trioformula(_t);
																																																				_t = _retTree;
																																																				astFactory.addASTChild(currentAST, returnAST);
																																																				term(_t);
																																																				_t = _retTree;
																																																				astFactory.addASTChild(currentAST, returnAST);
																																																				term(_t);
																																																				_t = _retTree;
																																																				astFactory.addASTChild(currentAST, returnAST);
																																																				currentAST = __currentAST473;
																																																				_t = __t473;
																																																				_t = _t.getNextSibling();
																																																				trioformula_AST = (AST)currentAST.root;
																																																			}
																																																			else {
																																																				boolean synPredMatched476 = false;
																																																				if (((_t.getType()==WITHINPII))) {
																																																					AST __t476 = _t;
																																																					synPredMatched476 = true;
																																																					inputState.guessing++;
																																																					try {
																																																						{
																																																						AST __t475 = _t;
																																																						ASTPair __currentAST475 = currentAST.copy();
																																																						currentAST.root = currentAST.child;
																																																						currentAST.child = null;
																																																						match(_t,WITHINPII);
																																																						_t = _t.getFirstChild();
																																																						trioformula(_t);
																																																						_t = _retTree;
																																																						term(_t);
																																																						_t = _retTree;
																																																						currentAST = __currentAST475;
																																																						_t = __t475;
																																																						_t = _t.getNextSibling();
																																																						}
																																																					}
																																																					catch (RecognitionException pe) {
																																																						synPredMatched476 = false;
																																																					}
																																																					_t = __t476;
inputState.guessing--;
																																																				}
																																																				if ( synPredMatched476 ) {
																																																					AST __t477 = _t;
																																																					AST tmp132_AST = null;
																																																					AST tmp132_AST_in = null;
																																																					tmp132_AST = astFactory.create((AST)_t);
																																																					tmp132_AST_in = (AST)_t;
																																																					astFactory.addASTChild(currentAST, tmp132_AST);
																																																					ASTPair __currentAST477 = currentAST.copy();
																																																					currentAST.root = currentAST.child;
																																																					currentAST.child = null;
																																																					match(_t,WITHINPII);
																																																					_t = _t.getFirstChild();
																																																					trioformula(_t);
																																																					_t = _retTree;
																																																					astFactory.addASTChild(currentAST, returnAST);
																																																					term(_t);
																																																					_t = _retTree;
																																																					astFactory.addASTChild(currentAST, returnAST);
																																																					currentAST = __currentAST477;
																																																					_t = __t477;
																																																					_t = _t.getNextSibling();
																																																					trioformula_AST = (AST)currentAST.root;
																																																				}
																																																			else {
																																																				throw new NoViableAltException(_t);
																																																			}
																																																			}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}
																																																		}
																																																		catch (RecognitionException ex) {
																																																			if (inputState.guessing==0) {
																																																				reportError(ex);
																																																				if (_t!=null) {_t = _t.getNextSibling();}
																																																			} else {
																																																			  throw ex;
																																																			}
																																																		}
																																																		returnAST = trioformula_AST;
																																																		_retTree = _t;
																																																	}
																																																	
	public final void term(AST _t) throws RecognitionException {
		
		AST term_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST term_AST = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case PLUS:
			{
				AST __t483 = _t;
				AST tmp133_AST = null;
				AST tmp133_AST_in = null;
				tmp133_AST = astFactory.create((AST)_t);
				tmp133_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp133_AST);
				ASTPair __currentAST483 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,PLUS);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST483;
				_t = __t483;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case MINUS:
			{
				AST __t484 = _t;
				AST tmp134_AST = null;
				AST tmp134_AST_in = null;
				tmp134_AST = astFactory.create((AST)_t);
				tmp134_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp134_AST);
				ASTPair __currentAST484 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,MINUS);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST484;
				_t = __t484;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case DIV:
			{
				AST __t485 = _t;
				AST tmp135_AST = null;
				AST tmp135_AST_in = null;
				tmp135_AST = astFactory.create((AST)_t);
				tmp135_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp135_AST);
				ASTPair __currentAST485 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,DIV);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST485;
				_t = __t485;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case STAR:
			{
				AST __t486 = _t;
				AST tmp136_AST = null;
				AST tmp136_AST_in = null;
				tmp136_AST = astFactory.create((AST)_t);
				tmp136_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp136_AST);
				ASTPair __currentAST486 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,STAR);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST486;
				_t = __t486;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case MOD:
			{
				AST __t487 = _t;
				AST tmp137_AST = null;
				AST tmp137_AST_in = null;
				tmp137_AST = astFactory.create((AST)_t);
				tmp137_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp137_AST);
				ASTPair __currentAST487 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,MOD);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST487;
				_t = __t487;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case INTDIV:
			{
				AST __t488 = _t;
				AST tmp138_AST = null;
				AST tmp138_AST_in = null;
				tmp138_AST = astFactory.create((AST)_t);
				tmp138_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp138_AST);
				ASTPair __currentAST488 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,INTDIV);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST488;
				_t = __t488;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case POW:
			{
				AST __t489 = _t;
				AST tmp139_AST = null;
				AST tmp139_AST_in = null;
				tmp139_AST = astFactory.create((AST)_t);
				tmp139_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp139_AST);
				ASTPair __currentAST489 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,POW);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST489;
				_t = __t489;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case SIGN_MINUS:
			{
				AST __t490 = _t;
				AST tmp140_AST = null;
				AST tmp140_AST_in = null;
				tmp140_AST = astFactory.create((AST)_t);
				tmp140_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp140_AST);
				ASTPair __currentAST490 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SIGN_MINUS);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST490;
				_t = __t490;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case SIGN_PLUS:
			{
				AST __t491 = _t;
				AST tmp141_AST = null;
				AST tmp141_AST_in = null;
				tmp141_AST = astFactory.create((AST)_t);
				tmp141_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp141_AST);
				ASTPair __currentAST491 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SIGN_PLUS);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST491;
				_t = __t491;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case NUMBER:
			{
				AST tmp142_AST = null;
				AST tmp142_AST_in = null;
				tmp142_AST = astFactory.create((AST)_t);
				tmp142_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp142_AST);
				match(_t,NUMBER);
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case VARIABLE:
			{
				AST tmp143_AST = null;
				AST tmp143_AST_in = null;
				tmp143_AST = astFactory.create((AST)_t);
				tmp143_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp143_AST);
				match(_t,VARIABLE);
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case CONSTANT:
			{
				AST tmp144_AST = null;
				AST tmp144_AST_in = null;
				tmp144_AST = astFactory.create((AST)_t);
				tmp144_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp144_AST);
				match(_t,CONSTANT);
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case LPAREN:
			{
				AST __t492 = _t;
				AST tmp145_AST = null;
				AST tmp145_AST_in = null;
				tmp145_AST = astFactory.create((AST)_t);
				tmp145_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp145_AST);
				ASTPair __currentAST492 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LPAREN);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST492;
				_t = __t492;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			default:
			{
				throw new NoViableAltException(_t);
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				if (_t!=null) {_t = _t.getNextSibling();}
			} else {
			  throw ex;
			}
		}
		returnAST = term_AST;
		_retTree = _t;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"not\"",
		"\"true\"",
		"\"false\"",
		"\"Alw\"",
		"\"AlwF\"",
		"\"AlwF_e\"",
		"\"AlwF_i\"",
		"\"AlwP\"",
		"\"AlwP_e\"",
		"\"AlwP_i\"",
		"\"Som\"",
		"\"SomF\"",
		"\"SomF_e\"",
		"\"SomF_i\"",
		"\"SomP\"",
		"\"SomP_e\"",
		"\"SomP_i\"",
		"\"UpToNow\"",
		"\"Becomes\"",
		"\"Until\"",
		"\"Until_w\"",
		"\"Since\"",
		"\"Since_w\"",
		"\"Dist\"",
		"\"Futr\"",
		"\"Past\"",
		"\"Lasts\"",
		"\"Lasts_ee\"",
		"\"Lasts_ei\"",
		"\"Lasts_ie\"",
		"\"Lasts_ii\"",
		"\"Lasted\"",
		"\"Lasted_ee\"",
		"\"Lasted_ei\"",
		"\"Lasted_ie\"",
		"\"Lasted_ii\"",
		"\"Within\"",
		"\"WithinF\"",
		"\"WithinF_ee\"",
		"\"WithinF_ei\"",
		"\"WithinF_ie\"",
		"\"WithinF_ii\"",
		"\"WithinP\"",
		"\"WithinP_ee\"",
		"\"WithinP_ei\"",
		"\"WithinP_ie\"",
		"\"WithinP_ii\"",
		"\"NextTime\"",
		"\"LastTime\"",
		"\"NowOn\"",
		"\"Now\"",
		"\"variables\"",
		"\"axioms\"",
		"\"constants\"",
		"\"mod\"",
		"\"div\"",
		"\"sym\"",
		"\"match\"",
		"\"strong\"",
		"\"property\"",
		"LPAREN",
		"RPAREN",
		"LCURLY",
		"RCURLY",
		"LBRACK",
		"RBRACK",
		"SEMI",
		"COLON",
		"COMMA",
		"IFF",
		"IF",
		"OR",
		"AND",
		"DOT",
		"EQ",
		"NEQ",
		"LT",
		"GT",
		"GTE",
		"LTE",
		"PLUS",
		"MINUS",
		"DIV",
		"STAR",
		"POW",
		"WS",
		"ID",
		"DIGIT",
		"NUMBER",
		"SL_COMMENT",
		"SIGN_PLUS",
		"SIGN_MINUS",
		"VARIABLE",
		"CONSTANT",
		"OR_ROOT",
		"AND_ROOT",
		"IF_ROOT",
		"IFF_ROOT"
	};
	
	}
	
