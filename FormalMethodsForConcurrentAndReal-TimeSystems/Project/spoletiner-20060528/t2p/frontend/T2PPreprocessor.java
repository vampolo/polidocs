// $ANTLR : "T2PPreprocessor.g" -> "T2PPreprocessor.java"$

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
 A preprocessor for building AST according to derivation rules for operator in TRIO.
 */
public class T2PPreprocessor extends antlr.TreeParser       implements T2PPreprocessorTokenTypes
 {
public T2PPreprocessor() {
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
		AST b_tf_AST = null;
		AST b_tf = null;
		AST next_tf_AST = null;
		AST next_tf = null;
		AST next_t_AST = null;
		AST next_t = null;
		AST last_tf_AST = null;
		AST last_tf = null;
		AST last_t_AST = null;
		AST last_t = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case IFF:
			{
				AST __t764 = _t;
				AST tmp1_AST = null;
				AST tmp1_AST_in = null;
				tmp1_AST = astFactory.create((AST)_t);
				tmp1_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp1_AST);
				ASTPair __currentAST764 = currentAST.copy();
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
				currentAST = __currentAST764;
				_t = __t764;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case IF:
			{
				AST __t777 = _t;
				AST tmp2_AST = null;
				AST tmp2_AST_in = null;
				tmp2_AST = astFactory.create((AST)_t);
				tmp2_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp2_AST);
				ASTPair __currentAST777 = currentAST.copy();
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
				currentAST = __currentAST777;
				_t = __t777;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case IF_ROOT:
			{
				AST __t778 = _t;
				AST tmp3_AST = null;
				AST tmp3_AST_in = null;
				tmp3_AST = astFactory.create((AST)_t);
				tmp3_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp3_AST);
				ASTPair __currentAST778 = currentAST.copy();
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
				currentAST = __currentAST778;
				_t = __t778;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case OR:
			{
				AST __t779 = _t;
				AST tmp4_AST = null;
				AST tmp4_AST_in = null;
				tmp4_AST = astFactory.create((AST)_t);
				tmp4_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp4_AST);
				ASTPair __currentAST779 = currentAST.copy();
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
				currentAST = __currentAST779;
				_t = __t779;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case OR_ROOT:
			{
				AST __t780 = _t;
				AST tmp5_AST = null;
				AST tmp5_AST_in = null;
				tmp5_AST = astFactory.create((AST)_t);
				tmp5_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp5_AST);
				ASTPair __currentAST780 = currentAST.copy();
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
				currentAST = __currentAST780;
				_t = __t780;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case AND:
			{
				AST __t781 = _t;
				AST tmp6_AST = null;
				AST tmp6_AST_in = null;
				tmp6_AST = astFactory.create((AST)_t);
				tmp6_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp6_AST);
				ASTPair __currentAST781 = currentAST.copy();
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
				currentAST = __currentAST781;
				_t = __t781;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case AND_ROOT:
			{
				AST __t782 = _t;
				AST tmp7_AST = null;
				AST tmp7_AST_in = null;
				tmp7_AST = astFactory.create((AST)_t);
				tmp7_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp7_AST);
				ASTPair __currentAST782 = currentAST.copy();
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
				currentAST = __currentAST782;
				_t = __t782;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case NOT:
			{
				AST __t783 = _t;
				AST tmp8_AST = null;
				AST tmp8_AST_in = null;
				tmp8_AST = astFactory.create((AST)_t);
				tmp8_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp8_AST);
				ASTPair __currentAST783 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,NOT);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST783;
				_t = __t783;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case EQ:
			{
				AST __t784 = _t;
				AST tmp9_AST = null;
				AST tmp9_AST_in = null;
				tmp9_AST = astFactory.create((AST)_t);
				tmp9_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp9_AST);
				ASTPair __currentAST784 = currentAST.copy();
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
				currentAST = __currentAST784;
				_t = __t784;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case NEQ:
			{
				AST __t785 = _t;
				AST tmp10_AST = null;
				AST tmp10_AST_in = null;
				tmp10_AST = astFactory.create((AST)_t);
				tmp10_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp10_AST);
				ASTPair __currentAST785 = currentAST.copy();
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
				currentAST = __currentAST785;
				_t = __t785;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LT:
			{
				AST __t786 = _t;
				AST tmp11_AST = null;
				AST tmp11_AST_in = null;
				tmp11_AST = astFactory.create((AST)_t);
				tmp11_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp11_AST);
				ASTPair __currentAST786 = currentAST.copy();
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
				currentAST = __currentAST786;
				_t = __t786;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case GT:
			{
				AST __t787 = _t;
				AST tmp12_AST = null;
				AST tmp12_AST_in = null;
				tmp12_AST = astFactory.create((AST)_t);
				tmp12_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp12_AST);
				ASTPair __currentAST787 = currentAST.copy();
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
				currentAST = __currentAST787;
				_t = __t787;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case GTE:
			{
				AST __t788 = _t;
				AST tmp13_AST = null;
				AST tmp13_AST_in = null;
				tmp13_AST = astFactory.create((AST)_t);
				tmp13_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp13_AST);
				ASTPair __currentAST788 = currentAST.copy();
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
				currentAST = __currentAST788;
				_t = __t788;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LTE:
			{
				AST __t789 = _t;
				AST tmp14_AST = null;
				AST tmp14_AST_in = null;
				tmp14_AST = astFactory.create((AST)_t);
				tmp14_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp14_AST);
				ASTPair __currentAST789 = currentAST.copy();
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
				currentAST = __currentAST789;
				_t = __t789;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALW:
			{
				AST __t790 = _t;
				AST tmp15_AST = null;
				AST tmp15_AST_in = null;
				tmp15_AST = astFactory.create((AST)_t);
				tmp15_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp15_AST);
				ASTPair __currentAST790 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALW);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST790;
				_t = __t790;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWF:
			{
				AST __t791 = _t;
				AST tmp16_AST = null;
				AST tmp16_AST_in = null;
				tmp16_AST = astFactory.create((AST)_t);
				tmp16_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp16_AST);
				ASTPair __currentAST791 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWF);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST791;
				_t = __t791;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWFE:
			{
				AST __t792 = _t;
				AST tmp17_AST = null;
				AST tmp17_AST_in = null;
				tmp17_AST = astFactory.create((AST)_t);
				tmp17_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp17_AST);
				ASTPair __currentAST792 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWFE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST792;
				_t = __t792;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWFI:
			{
				AST __t793 = _t;
				AST tmp18_AST = null;
				AST tmp18_AST_in = null;
				tmp18_AST = astFactory.create((AST)_t);
				tmp18_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp18_AST);
				ASTPair __currentAST793 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWFI);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST793;
				_t = __t793;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWP:
			{
				AST __t794 = _t;
				AST tmp19_AST = null;
				AST tmp19_AST_in = null;
				tmp19_AST = astFactory.create((AST)_t);
				tmp19_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp19_AST);
				ASTPair __currentAST794 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWP);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST794;
				_t = __t794;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWPE:
			{
				AST __t795 = _t;
				AST tmp20_AST = null;
				AST tmp20_AST_in = null;
				tmp20_AST = astFactory.create((AST)_t);
				tmp20_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp20_AST);
				ASTPair __currentAST795 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWPE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST795;
				_t = __t795;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWPI:
			{
				AST __t796 = _t;
				AST tmp21_AST = null;
				AST tmp21_AST_in = null;
				tmp21_AST = astFactory.create((AST)_t);
				tmp21_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp21_AST);
				ASTPair __currentAST796 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWPI);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST796;
				_t = __t796;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOM:
			{
				AST __t797 = _t;
				AST tmp22_AST = null;
				AST tmp22_AST_in = null;
				tmp22_AST = astFactory.create((AST)_t);
				tmp22_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp22_AST);
				ASTPair __currentAST797 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOM);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST797;
				_t = __t797;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMF:
			{
				AST __t798 = _t;
				AST tmp23_AST = null;
				AST tmp23_AST_in = null;
				tmp23_AST = astFactory.create((AST)_t);
				tmp23_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp23_AST);
				ASTPair __currentAST798 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMF);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST798;
				_t = __t798;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMFE:
			{
				AST __t799 = _t;
				AST tmp24_AST = null;
				AST tmp24_AST_in = null;
				tmp24_AST = astFactory.create((AST)_t);
				tmp24_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp24_AST);
				ASTPair __currentAST799 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMFE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST799;
				_t = __t799;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMFI:
			{
				AST __t800 = _t;
				AST tmp25_AST = null;
				AST tmp25_AST_in = null;
				tmp25_AST = astFactory.create((AST)_t);
				tmp25_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp25_AST);
				ASTPair __currentAST800 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMFI);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST800;
				_t = __t800;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMP:
			{
				AST __t801 = _t;
				AST tmp26_AST = null;
				AST tmp26_AST_in = null;
				tmp26_AST = astFactory.create((AST)_t);
				tmp26_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp26_AST);
				ASTPair __currentAST801 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMP);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST801;
				_t = __t801;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMPE:
			{
				AST __t802 = _t;
				AST tmp27_AST = null;
				AST tmp27_AST_in = null;
				tmp27_AST = astFactory.create((AST)_t);
				tmp27_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp27_AST);
				ASTPair __currentAST802 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMPE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST802;
				_t = __t802;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMPI:
			{
				AST __t803 = _t;
				AST tmp28_AST = null;
				AST tmp28_AST_in = null;
				tmp28_AST = astFactory.create((AST)_t);
				tmp28_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp28_AST);
				ASTPair __currentAST803 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMPI);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST803;
				_t = __t803;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case NOWON:
			{
				AST __t804 = _t;
				AST tmp29_AST = null;
				AST tmp29_AST_in = null;
				tmp29_AST = astFactory.create((AST)_t);
				tmp29_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp29_AST);
				ASTPair __currentAST804 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,NOWON);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST804;
				_t = __t804;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case UPTONOW:
			{
				AST __t805 = _t;
				AST tmp30_AST = null;
				AST tmp30_AST_in = null;
				tmp30_AST = astFactory.create((AST)_t);
				tmp30_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp30_AST);
				ASTPair __currentAST805 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,UPTONOW);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST805;
				_t = __t805;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case BECOMES:
			{
				AST __t806 = _t;
				AST tmp31_AST = null;
				AST tmp31_AST_in = null;
				tmp31_AST = astFactory.create((AST)_t);
				tmp31_AST_in = (AST)_t;
				ASTPair __currentAST806 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,BECOMES);
				_t = _t.getFirstChild();
				b_tf = _t==ASTNULL ? null : (AST)_t;
				trioformula(_t);
				_t = _retTree;
				b_tf_AST = (AST)returnAST;
				currentAST = __currentAST806;
				_t = __t806;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					trioformula_AST = (AST)currentAST.root;
					
							//treat as Past(not A,1) & A
							// build and node; TreeParser will substitute node BECOMES with a Past
							trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(AND,"AND")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp31_AST)).add(b_tf_AST))).add(b_tf_AST));
						
					currentAST.root = trioformula_AST;
					currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
						trioformula_AST.getFirstChild() : trioformula_AST;
					currentAST.advanceChildToEnd();
				}
				break;
			}
			case UNTIL:
			{
				AST __t807 = _t;
				AST tmp32_AST = null;
				AST tmp32_AST_in = null;
				tmp32_AST = astFactory.create((AST)_t);
				tmp32_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp32_AST);
				ASTPair __currentAST807 = currentAST.copy();
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
				currentAST = __currentAST807;
				_t = __t807;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case UNTILW:
			{
				AST __t808 = _t;
				AST tmp33_AST = null;
				AST tmp33_AST_in = null;
				tmp33_AST = astFactory.create((AST)_t);
				tmp33_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp33_AST);
				ASTPair __currentAST808 = currentAST.copy();
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
				currentAST = __currentAST808;
				_t = __t808;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SINCE:
			{
				AST __t809 = _t;
				AST tmp34_AST = null;
				AST tmp34_AST_in = null;
				tmp34_AST = astFactory.create((AST)_t);
				tmp34_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp34_AST);
				ASTPair __currentAST809 = currentAST.copy();
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
				currentAST = __currentAST809;
				_t = __t809;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SINCEW:
			{
				AST __t810 = _t;
				AST tmp35_AST = null;
				AST tmp35_AST_in = null;
				tmp35_AST = astFactory.create((AST)_t);
				tmp35_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp35_AST);
				ASTPair __currentAST810 = currentAST.copy();
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
				currentAST = __currentAST810;
				_t = __t810;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case DIST:
			{
				AST __t811 = _t;
				AST tmp36_AST = null;
				AST tmp36_AST_in = null;
				tmp36_AST = astFactory.create((AST)_t);
				tmp36_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp36_AST);
				ASTPair __currentAST811 = currentAST.copy();
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
				currentAST = __currentAST811;
				_t = __t811;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case FUTR:
			{
				AST __t812 = _t;
				AST tmp37_AST = null;
				AST tmp37_AST_in = null;
				tmp37_AST = astFactory.create((AST)_t);
				tmp37_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp37_AST);
				ASTPair __currentAST812 = currentAST.copy();
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
				currentAST = __currentAST812;
				_t = __t812;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case PAST:
			{
				AST __t813 = _t;
				AST tmp38_AST = null;
				AST tmp38_AST_in = null;
				tmp38_AST = astFactory.create((AST)_t);
				tmp38_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp38_AST);
				ASTPair __currentAST813 = currentAST.copy();
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
				currentAST = __currentAST813;
				_t = __t813;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTS:
			{
				AST __t814 = _t;
				AST tmp39_AST = null;
				AST tmp39_AST_in = null;
				tmp39_AST = astFactory.create((AST)_t);
				tmp39_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp39_AST);
				ASTPair __currentAST814 = currentAST.copy();
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
				currentAST = __currentAST814;
				_t = __t814;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTSEE:
			{
				AST __t815 = _t;
				AST tmp40_AST = null;
				AST tmp40_AST_in = null;
				tmp40_AST = astFactory.create((AST)_t);
				tmp40_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp40_AST);
				ASTPair __currentAST815 = currentAST.copy();
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
				currentAST = __currentAST815;
				_t = __t815;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTSEI:
			{
				AST __t816 = _t;
				AST tmp41_AST = null;
				AST tmp41_AST_in = null;
				tmp41_AST = astFactory.create((AST)_t);
				tmp41_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp41_AST);
				ASTPair __currentAST816 = currentAST.copy();
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
				currentAST = __currentAST816;
				_t = __t816;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTSIE:
			{
				AST __t817 = _t;
				AST tmp42_AST = null;
				AST tmp42_AST_in = null;
				tmp42_AST = astFactory.create((AST)_t);
				tmp42_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp42_AST);
				ASTPair __currentAST817 = currentAST.copy();
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
				currentAST = __currentAST817;
				_t = __t817;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTSII:
			{
				AST __t818 = _t;
				AST tmp43_AST = null;
				AST tmp43_AST_in = null;
				tmp43_AST = astFactory.create((AST)_t);
				tmp43_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp43_AST);
				ASTPair __currentAST818 = currentAST.copy();
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
				currentAST = __currentAST818;
				_t = __t818;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTEDEE:
			{
				AST __t819 = _t;
				AST tmp44_AST = null;
				AST tmp44_AST_in = null;
				tmp44_AST = astFactory.create((AST)_t);
				tmp44_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp44_AST);
				ASTPair __currentAST819 = currentAST.copy();
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
				currentAST = __currentAST819;
				_t = __t819;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTEDEI:
			{
				AST __t820 = _t;
				AST tmp45_AST = null;
				AST tmp45_AST_in = null;
				tmp45_AST = astFactory.create((AST)_t);
				tmp45_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp45_AST);
				ASTPair __currentAST820 = currentAST.copy();
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
				currentAST = __currentAST820;
				_t = __t820;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTEDIE:
			{
				AST __t821 = _t;
				AST tmp46_AST = null;
				AST tmp46_AST_in = null;
				tmp46_AST = astFactory.create((AST)_t);
				tmp46_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp46_AST);
				ASTPair __currentAST821 = currentAST.copy();
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
				currentAST = __currentAST821;
				_t = __t821;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTEDII:
			{
				AST __t822 = _t;
				AST tmp47_AST = null;
				AST tmp47_AST_in = null;
				tmp47_AST = astFactory.create((AST)_t);
				tmp47_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp47_AST);
				ASTPair __currentAST822 = currentAST.copy();
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
				currentAST = __currentAST822;
				_t = __t822;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTED:
			{
				AST __t823 = _t;
				AST tmp48_AST = null;
				AST tmp48_AST_in = null;
				tmp48_AST = astFactory.create((AST)_t);
				tmp48_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp48_AST);
				ASTPair __currentAST823 = currentAST.copy();
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
				currentAST = __currentAST823;
				_t = __t823;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case WITHIN:
			{
				AST __t824 = _t;
				AST tmp49_AST = null;
				AST tmp49_AST_in = null;
				tmp49_AST = astFactory.create((AST)_t);
				tmp49_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp49_AST);
				ASTPair __currentAST824 = currentAST.copy();
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
				currentAST = __currentAST824;
				_t = __t824;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case NEXTTIME:
			{
				AST __t889 = _t;
				AST tmp50_AST = null;
				AST tmp50_AST_in = null;
				tmp50_AST = astFactory.create((AST)_t);
				tmp50_AST_in = (AST)_t;
				ASTPair __currentAST889 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,NEXTTIME);
				_t = _t.getFirstChild();
				next_tf = _t==ASTNULL ? null : (AST)_t;
				trioformula(_t);
				_t = _retTree;
				next_tf_AST = (AST)returnAST;
				next_t = _t==ASTNULL ? null : (AST)_t;
				term(_t);
				_t = _retTree;
				next_t_AST = (AST)returnAST;
				currentAST = __currentAST889;
				_t = __t889;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					trioformula_AST = (AST)currentAST.root;
					
							trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(AND,"AND")).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(FUTR,"FUTR")).add(next_tf_AST).add(next_t_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(LASTSEE,"LASTSEE")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT,"not")).add(next_tf_AST))).add(next_t_AST))));
						
					currentAST.root = trioformula_AST;
					currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
						trioformula_AST.getFirstChild() : trioformula_AST;
					currentAST.advanceChildToEnd();
				}
				break;
			}
			case LASTTIME:
			{
				AST __t890 = _t;
				AST tmp51_AST = null;
				AST tmp51_AST_in = null;
				tmp51_AST = astFactory.create((AST)_t);
				tmp51_AST_in = (AST)_t;
				ASTPair __currentAST890 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LASTTIME);
				_t = _t.getFirstChild();
				last_tf = _t==ASTNULL ? null : (AST)_t;
				trioformula(_t);
				_t = _retTree;
				last_tf_AST = (AST)returnAST;
				last_t = _t==ASTNULL ? null : (AST)_t;
				term(_t);
				_t = _retTree;
				last_t_AST = (AST)returnAST;
				currentAST = __currentAST890;
				_t = __t890;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					trioformula_AST = (AST)currentAST.root;
					
							trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(AND,"AND")).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(FUTR,"FUTR")).add(last_tf_AST).add(last_t_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(LASTEDEE,"LASTEDEE")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT,"not")).add(last_tf_AST))).add(last_t_AST))));
						
					currentAST.root = trioformula_AST;
					currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
						trioformula_AST.getFirstChild() : trioformula_AST;
					currentAST.advanceChildToEnd();
				}
				break;
			}
			case NOW:
			{
				AST __t891 = _t;
				AST tmp52_AST = null;
				AST tmp52_AST_in = null;
				tmp52_AST = astFactory.create((AST)_t);
				tmp52_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp52_AST);
				ASTPair __currentAST891 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,NOW);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST891;
				_t = __t891;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LPAREN:
			{
				AST __t892 = _t;
				AST tmp53_AST = null;
				AST tmp53_AST_in = null;
				tmp53_AST = astFactory.create((AST)_t);
				tmp53_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp53_AST);
				ASTPair __currentAST892 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LPAREN);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST892;
				_t = __t892;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			default:
				boolean synPredMatched767 = false;
				if (((_t.getType()==IFF_ROOT))) {
					AST __t767 = _t;
					synPredMatched767 = true;
					inputState.guessing++;
					try {
						{
						AST __t766 = _t;
						ASTPair __currentAST766 = currentAST.copy();
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
						currentAST = __currentAST766;
						_t = __t766;
						_t = _t.getNextSibling();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched767 = false;
					}
					_t = __t767;
inputState.guessing--;
				}
				if ( synPredMatched767 ) {
					AST __t768 = _t;
					AST tmp54_AST = null;
					AST tmp54_AST_in = null;
					tmp54_AST = astFactory.create((AST)_t);
					tmp54_AST_in = (AST)_t;
					astFactory.addASTChild(currentAST, tmp54_AST);
					ASTPair __currentAST768 = currentAST.copy();
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
					currentAST = __currentAST768;
					_t = __t768;
					_t = _t.getNextSibling();
					trioformula_AST = (AST)currentAST.root;
				}
				else {
					boolean synPredMatched771 = false;
					if (((_t.getType()==IFF_ROOT))) {
						AST __t771 = _t;
						synPredMatched771 = true;
						inputState.guessing++;
						try {
							{
							AST __t770 = _t;
							ASTPair __currentAST770 = currentAST.copy();
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
							currentAST = __currentAST770;
							_t = __t770;
							_t = _t.getNextSibling();
							}
						}
						catch (RecognitionException pe) {
							synPredMatched771 = false;
						}
						_t = __t771;
inputState.guessing--;
					}
					if ( synPredMatched771 ) {
						AST __t772 = _t;
						AST tmp55_AST = null;
						AST tmp55_AST_in = null;
						tmp55_AST = astFactory.create((AST)_t);
						tmp55_AST_in = (AST)_t;
						astFactory.addASTChild(currentAST, tmp55_AST);
						ASTPair __currentAST772 = currentAST.copy();
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
						currentAST = __currentAST772;
						_t = __t772;
						_t = _t.getNextSibling();
						trioformula_AST = (AST)currentAST.root;
					}
					else {
						boolean synPredMatched775 = false;
						if (((_t.getType()==IFF_ROOT))) {
							AST __t775 = _t;
							synPredMatched775 = true;
							inputState.guessing++;
							try {
								{
								AST __t774 = _t;
								ASTPair __currentAST774 = currentAST.copy();
								currentAST.root = currentAST.child;
								currentAST.child = null;
								match(_t,IFF_ROOT);
								_t = _t.getFirstChild();
								trioformula(_t);
								_t = _retTree;
								trioformula(_t);
								_t = _retTree;
								currentAST = __currentAST774;
								_t = __t774;
								_t = _t.getNextSibling();
								}
							}
							catch (RecognitionException pe) {
								synPredMatched775 = false;
							}
							_t = __t775;
inputState.guessing--;
						}
						if ( synPredMatched775 ) {
							AST __t776 = _t;
							AST tmp56_AST = null;
							AST tmp56_AST_in = null;
							tmp56_AST = astFactory.create((AST)_t);
							tmp56_AST_in = (AST)_t;
							astFactory.addASTChild(currentAST, tmp56_AST);
							ASTPair __currentAST776 = currentAST.copy();
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
							currentAST = __currentAST776;
							_t = __t776;
							_t = _t.getNextSibling();
							trioformula_AST = (AST)currentAST.root;
						}
						else {
							boolean synPredMatched827 = false;
							if (((_t.getType()==WITHINFEE))) {
								AST __t827 = _t;
								synPredMatched827 = true;
								inputState.guessing++;
								try {
									{
									AST __t826 = _t;
									ASTPair __currentAST826 = currentAST.copy();
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
									currentAST = __currentAST826;
									_t = __t826;
									_t = _t.getNextSibling();
									}
								}
								catch (RecognitionException pe) {
									synPredMatched827 = false;
								}
								_t = __t827;
inputState.guessing--;
							}
							if ( synPredMatched827 ) {
								AST __t828 = _t;
								AST tmp57_AST = null;
								AST tmp57_AST_in = null;
								tmp57_AST = astFactory.create((AST)_t);
								tmp57_AST_in = (AST)_t;
								astFactory.addASTChild(currentAST, tmp57_AST);
								ASTPair __currentAST828 = currentAST.copy();
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
								currentAST = __currentAST828;
								_t = __t828;
								_t = _t.getNextSibling();
								trioformula_AST = (AST)currentAST.root;
							}
							else {
								boolean synPredMatched831 = false;
								if (((_t.getType()==WITHINFEE))) {
									AST __t831 = _t;
									synPredMatched831 = true;
									inputState.guessing++;
									try {
										{
										AST __t830 = _t;
										ASTPair __currentAST830 = currentAST.copy();
										currentAST.root = currentAST.child;
										currentAST.child = null;
										match(_t,WITHINFEE);
										_t = _t.getFirstChild();
										trioformula(_t);
										_t = _retTree;
										term(_t);
										_t = _retTree;
										currentAST = __currentAST830;
										_t = __t830;
										_t = _t.getNextSibling();
										}
									}
									catch (RecognitionException pe) {
										synPredMatched831 = false;
									}
									_t = __t831;
inputState.guessing--;
								}
								if ( synPredMatched831 ) {
									AST __t832 = _t;
									AST tmp58_AST = null;
									AST tmp58_AST_in = null;
									tmp58_AST = astFactory.create((AST)_t);
									tmp58_AST_in = (AST)_t;
									astFactory.addASTChild(currentAST, tmp58_AST);
									ASTPair __currentAST832 = currentAST.copy();
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
									currentAST = __currentAST832;
									_t = __t832;
									_t = _t.getNextSibling();
									trioformula_AST = (AST)currentAST.root;
								}
								else {
									boolean synPredMatched835 = false;
									if (((_t.getType()==WITHINFEI))) {
										AST __t835 = _t;
										synPredMatched835 = true;
										inputState.guessing++;
										try {
											{
											AST __t834 = _t;
											ASTPair __currentAST834 = currentAST.copy();
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
											currentAST = __currentAST834;
											_t = __t834;
											_t = _t.getNextSibling();
											}
										}
										catch (RecognitionException pe) {
											synPredMatched835 = false;
										}
										_t = __t835;
inputState.guessing--;
									}
									if ( synPredMatched835 ) {
										AST __t836 = _t;
										AST tmp59_AST = null;
										AST tmp59_AST_in = null;
										tmp59_AST = astFactory.create((AST)_t);
										tmp59_AST_in = (AST)_t;
										astFactory.addASTChild(currentAST, tmp59_AST);
										ASTPair __currentAST836 = currentAST.copy();
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
										currentAST = __currentAST836;
										_t = __t836;
										_t = _t.getNextSibling();
										trioformula_AST = (AST)currentAST.root;
									}
									else {
										boolean synPredMatched839 = false;
										if (((_t.getType()==WITHINFEI))) {
											AST __t839 = _t;
											synPredMatched839 = true;
											inputState.guessing++;
											try {
												{
												AST __t838 = _t;
												ASTPair __currentAST838 = currentAST.copy();
												currentAST.root = currentAST.child;
												currentAST.child = null;
												match(_t,WITHINFEI);
												_t = _t.getFirstChild();
												trioformula(_t);
												_t = _retTree;
												term(_t);
												_t = _retTree;
												currentAST = __currentAST838;
												_t = __t838;
												_t = _t.getNextSibling();
												}
											}
											catch (RecognitionException pe) {
												synPredMatched839 = false;
											}
											_t = __t839;
inputState.guessing--;
										}
										if ( synPredMatched839 ) {
											AST __t840 = _t;
											AST tmp60_AST = null;
											AST tmp60_AST_in = null;
											tmp60_AST = astFactory.create((AST)_t);
											tmp60_AST_in = (AST)_t;
											astFactory.addASTChild(currentAST, tmp60_AST);
											ASTPair __currentAST840 = currentAST.copy();
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
											currentAST = __currentAST840;
											_t = __t840;
											_t = _t.getNextSibling();
											trioformula_AST = (AST)currentAST.root;
										}
										else {
											boolean synPredMatched843 = false;
											if (((_t.getType()==WITHINFIE))) {
												AST __t843 = _t;
												synPredMatched843 = true;
												inputState.guessing++;
												try {
													{
													AST __t842 = _t;
													ASTPair __currentAST842 = currentAST.copy();
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
													currentAST = __currentAST842;
													_t = __t842;
													_t = _t.getNextSibling();
													}
												}
												catch (RecognitionException pe) {
													synPredMatched843 = false;
												}
												_t = __t843;
inputState.guessing--;
											}
											if ( synPredMatched843 ) {
												AST __t844 = _t;
												AST tmp61_AST = null;
												AST tmp61_AST_in = null;
												tmp61_AST = astFactory.create((AST)_t);
												tmp61_AST_in = (AST)_t;
												astFactory.addASTChild(currentAST, tmp61_AST);
												ASTPair __currentAST844 = currentAST.copy();
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
												currentAST = __currentAST844;
												_t = __t844;
												_t = _t.getNextSibling();
												trioformula_AST = (AST)currentAST.root;
											}
											else {
												boolean synPredMatched847 = false;
												if (((_t.getType()==WITHINFIE))) {
													AST __t847 = _t;
													synPredMatched847 = true;
													inputState.guessing++;
													try {
														{
														AST __t846 = _t;
														ASTPair __currentAST846 = currentAST.copy();
														currentAST.root = currentAST.child;
														currentAST.child = null;
														match(_t,WITHINFIE);
														_t = _t.getFirstChild();
														trioformula(_t);
														_t = _retTree;
														term(_t);
														_t = _retTree;
														currentAST = __currentAST846;
														_t = __t846;
														_t = _t.getNextSibling();
														}
													}
													catch (RecognitionException pe) {
														synPredMatched847 = false;
													}
													_t = __t847;
inputState.guessing--;
												}
												if ( synPredMatched847 ) {
													AST __t848 = _t;
													AST tmp62_AST = null;
													AST tmp62_AST_in = null;
													tmp62_AST = astFactory.create((AST)_t);
													tmp62_AST_in = (AST)_t;
													astFactory.addASTChild(currentAST, tmp62_AST);
													ASTPair __currentAST848 = currentAST.copy();
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
													currentAST = __currentAST848;
													_t = __t848;
													_t = _t.getNextSibling();
													trioformula_AST = (AST)currentAST.root;
												}
												else {
													boolean synPredMatched851 = false;
													if (((_t.getType()==WITHINFII))) {
														AST __t851 = _t;
														synPredMatched851 = true;
														inputState.guessing++;
														try {
															{
															AST __t850 = _t;
															ASTPair __currentAST850 = currentAST.copy();
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
															currentAST = __currentAST850;
															_t = __t850;
															_t = _t.getNextSibling();
															}
														}
														catch (RecognitionException pe) {
															synPredMatched851 = false;
														}
														_t = __t851;
inputState.guessing--;
													}
													if ( synPredMatched851 ) {
														AST __t852 = _t;
														AST tmp63_AST = null;
														AST tmp63_AST_in = null;
														tmp63_AST = astFactory.create((AST)_t);
														tmp63_AST_in = (AST)_t;
														astFactory.addASTChild(currentAST, tmp63_AST);
														ASTPair __currentAST852 = currentAST.copy();
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
														currentAST = __currentAST852;
														_t = __t852;
														_t = _t.getNextSibling();
														trioformula_AST = (AST)currentAST.root;
													}
													else {
														boolean synPredMatched855 = false;
														if (((_t.getType()==WITHINFII))) {
															AST __t855 = _t;
															synPredMatched855 = true;
															inputState.guessing++;
															try {
																{
																AST __t854 = _t;
																ASTPair __currentAST854 = currentAST.copy();
																currentAST.root = currentAST.child;
																currentAST.child = null;
																match(_t,WITHINFII);
																_t = _t.getFirstChild();
																trioformula(_t);
																_t = _retTree;
																term(_t);
																_t = _retTree;
																currentAST = __currentAST854;
																_t = __t854;
																_t = _t.getNextSibling();
																}
															}
															catch (RecognitionException pe) {
																synPredMatched855 = false;
															}
															_t = __t855;
inputState.guessing--;
														}
														if ( synPredMatched855 ) {
															AST __t856 = _t;
															AST tmp64_AST = null;
															AST tmp64_AST_in = null;
															tmp64_AST = astFactory.create((AST)_t);
															tmp64_AST_in = (AST)_t;
															astFactory.addASTChild(currentAST, tmp64_AST);
															ASTPair __currentAST856 = currentAST.copy();
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
															currentAST = __currentAST856;
															_t = __t856;
															_t = _t.getNextSibling();
															trioformula_AST = (AST)currentAST.root;
														}
														else {
															boolean synPredMatched859 = false;
															if (((_t.getType()==WITHINPEE))) {
																AST __t859 = _t;
																synPredMatched859 = true;
																inputState.guessing++;
																try {
																	{
																	AST __t858 = _t;
																	ASTPair __currentAST858 = currentAST.copy();
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
																	currentAST = __currentAST858;
																	_t = __t858;
																	_t = _t.getNextSibling();
																	}
																}
																catch (RecognitionException pe) {
																	synPredMatched859 = false;
																}
																_t = __t859;
inputState.guessing--;
															}
															if ( synPredMatched859 ) {
																AST __t860 = _t;
																AST tmp65_AST = null;
																AST tmp65_AST_in = null;
																tmp65_AST = astFactory.create((AST)_t);
																tmp65_AST_in = (AST)_t;
																astFactory.addASTChild(currentAST, tmp65_AST);
																ASTPair __currentAST860 = currentAST.copy();
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
																currentAST = __currentAST860;
																_t = __t860;
																_t = _t.getNextSibling();
																trioformula_AST = (AST)currentAST.root;
															}
															else {
																boolean synPredMatched863 = false;
																if (((_t.getType()==WITHINPEE))) {
																	AST __t863 = _t;
																	synPredMatched863 = true;
																	inputState.guessing++;
																	try {
																		{
																		AST __t862 = _t;
																		ASTPair __currentAST862 = currentAST.copy();
																		currentAST.root = currentAST.child;
																		currentAST.child = null;
																		match(_t,WITHINPEE);
																		_t = _t.getFirstChild();
																		trioformula(_t);
																		_t = _retTree;
																		term(_t);
																		_t = _retTree;
																		currentAST = __currentAST862;
																		_t = __t862;
																		_t = _t.getNextSibling();
																		}
																	}
																	catch (RecognitionException pe) {
																		synPredMatched863 = false;
																	}
																	_t = __t863;
inputState.guessing--;
																}
																if ( synPredMatched863 ) {
																	AST __t864 = _t;
																	AST tmp66_AST = null;
																	AST tmp66_AST_in = null;
																	tmp66_AST = astFactory.create((AST)_t);
																	tmp66_AST_in = (AST)_t;
																	astFactory.addASTChild(currentAST, tmp66_AST);
																	ASTPair __currentAST864 = currentAST.copy();
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
																	currentAST = __currentAST864;
																	_t = __t864;
																	_t = _t.getNextSibling();
																	trioformula_AST = (AST)currentAST.root;
																}
																else {
																	boolean synPredMatched867 = false;
																	if (((_t.getType()==WITHINPEI))) {
																		AST __t867 = _t;
																		synPredMatched867 = true;
																		inputState.guessing++;
																		try {
																			{
																			AST __t866 = _t;
																			ASTPair __currentAST866 = currentAST.copy();
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
																			currentAST = __currentAST866;
																			_t = __t866;
																			_t = _t.getNextSibling();
																			}
																		}
																		catch (RecognitionException pe) {
																			synPredMatched867 = false;
																		}
																		_t = __t867;
inputState.guessing--;
																	}
																	if ( synPredMatched867 ) {
																		AST __t868 = _t;
																		AST tmp67_AST = null;
																		AST tmp67_AST_in = null;
																		tmp67_AST = astFactory.create((AST)_t);
																		tmp67_AST_in = (AST)_t;
																		astFactory.addASTChild(currentAST, tmp67_AST);
																		ASTPair __currentAST868 = currentAST.copy();
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
																		currentAST = __currentAST868;
																		_t = __t868;
																		_t = _t.getNextSibling();
																		trioformula_AST = (AST)currentAST.root;
																	}
																	else {
																		boolean synPredMatched871 = false;
																		if (((_t.getType()==WITHINPEI))) {
																			AST __t871 = _t;
																			synPredMatched871 = true;
																			inputState.guessing++;
																			try {
																				{
																				AST __t870 = _t;
																				ASTPair __currentAST870 = currentAST.copy();
																				currentAST.root = currentAST.child;
																				currentAST.child = null;
																				match(_t,WITHINPEI);
																				_t = _t.getFirstChild();
																				trioformula(_t);
																				_t = _retTree;
																				term(_t);
																				_t = _retTree;
																				currentAST = __currentAST870;
																				_t = __t870;
																				_t = _t.getNextSibling();
																				}
																			}
																			catch (RecognitionException pe) {
																				synPredMatched871 = false;
																			}
																			_t = __t871;
inputState.guessing--;
																		}
																		if ( synPredMatched871 ) {
																			AST __t872 = _t;
																			AST tmp68_AST = null;
																			AST tmp68_AST_in = null;
																			tmp68_AST = astFactory.create((AST)_t);
																			tmp68_AST_in = (AST)_t;
																			astFactory.addASTChild(currentAST, tmp68_AST);
																			ASTPair __currentAST872 = currentAST.copy();
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
																			currentAST = __currentAST872;
																			_t = __t872;
																			_t = _t.getNextSibling();
																			trioformula_AST = (AST)currentAST.root;
																		}
																		else {
																			boolean synPredMatched875 = false;
																			if (((_t.getType()==WITHINPIE))) {
																				AST __t875 = _t;
																				synPredMatched875 = true;
																				inputState.guessing++;
																				try {
																					{
																					AST __t874 = _t;
																					ASTPair __currentAST874 = currentAST.copy();
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
																					currentAST = __currentAST874;
																					_t = __t874;
																					_t = _t.getNextSibling();
																					}
																				}
																				catch (RecognitionException pe) {
																					synPredMatched875 = false;
																				}
																				_t = __t875;
inputState.guessing--;
																			}
																			if ( synPredMatched875 ) {
																				AST __t876 = _t;
																				AST tmp69_AST = null;
																				AST tmp69_AST_in = null;
																				tmp69_AST = astFactory.create((AST)_t);
																				tmp69_AST_in = (AST)_t;
																				astFactory.addASTChild(currentAST, tmp69_AST);
																				ASTPair __currentAST876 = currentAST.copy();
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
																				currentAST = __currentAST876;
																				_t = __t876;
																				_t = _t.getNextSibling();
																				trioformula_AST = (AST)currentAST.root;
																			}
																			else {
																				boolean synPredMatched879 = false;
																				if (((_t.getType()==WITHINPIE))) {
																					AST __t879 = _t;
																					synPredMatched879 = true;
																					inputState.guessing++;
																					try {
																						{
																						AST __t878 = _t;
																						ASTPair __currentAST878 = currentAST.copy();
																						currentAST.root = currentAST.child;
																						currentAST.child = null;
																						match(_t,WITHINPIE);
																						_t = _t.getFirstChild();
																						trioformula(_t);
																						_t = _retTree;
																						term(_t);
																						_t = _retTree;
																						currentAST = __currentAST878;
																						_t = __t878;
																						_t = _t.getNextSibling();
																						}
																					}
																					catch (RecognitionException pe) {
																						synPredMatched879 = false;
																					}
																					_t = __t879;
inputState.guessing--;
																				}
																				if ( synPredMatched879 ) {
																					AST __t880 = _t;
																					AST tmp70_AST = null;
																					AST tmp70_AST_in = null;
																					tmp70_AST = astFactory.create((AST)_t);
																					tmp70_AST_in = (AST)_t;
																					astFactory.addASTChild(currentAST, tmp70_AST);
																					ASTPair __currentAST880 = currentAST.copy();
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
																					currentAST = __currentAST880;
																					_t = __t880;
																					_t = _t.getNextSibling();
																					trioformula_AST = (AST)currentAST.root;
																				}
																				else {
																					boolean synPredMatched883 = false;
																					if (((_t.getType()==WITHINPII))) {
																						AST __t883 = _t;
																						synPredMatched883 = true;
																						inputState.guessing++;
																						try {
																							{
																							AST __t882 = _t;
																							ASTPair __currentAST882 = currentAST.copy();
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
																							currentAST = __currentAST882;
																							_t = __t882;
																							_t = _t.getNextSibling();
																							}
																						}
																						catch (RecognitionException pe) {
																							synPredMatched883 = false;
																						}
																						_t = __t883;
inputState.guessing--;
																					}
																					if ( synPredMatched883 ) {
																						AST __t884 = _t;
																						AST tmp71_AST = null;
																						AST tmp71_AST_in = null;
																						tmp71_AST = astFactory.create((AST)_t);
																						tmp71_AST_in = (AST)_t;
																						astFactory.addASTChild(currentAST, tmp71_AST);
																						ASTPair __currentAST884 = currentAST.copy();
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
																						currentAST = __currentAST884;
																						_t = __t884;
																						_t = _t.getNextSibling();
																						trioformula_AST = (AST)currentAST.root;
																					}
																					else {
																						boolean synPredMatched887 = false;
																						if (((_t.getType()==WITHINPII))) {
																							AST __t887 = _t;
																							synPredMatched887 = true;
																							inputState.guessing++;
																							try {
																								{
																								AST __t886 = _t;
																								ASTPair __currentAST886 = currentAST.copy();
																								currentAST.root = currentAST.child;
																								currentAST.child = null;
																								match(_t,WITHINPII);
																								_t = _t.getFirstChild();
																								trioformula(_t);
																								_t = _retTree;
																								term(_t);
																								_t = _retTree;
																								currentAST = __currentAST886;
																								_t = __t886;
																								_t = _t.getNextSibling();
																								}
																							}
																							catch (RecognitionException pe) {
																								synPredMatched887 = false;
																							}
																							_t = __t887;
inputState.guessing--;
																						}
																						if ( synPredMatched887 ) {
																							AST __t888 = _t;
																							AST tmp72_AST = null;
																							AST tmp72_AST_in = null;
																							tmp72_AST = astFactory.create((AST)_t);
																							tmp72_AST_in = (AST)_t;
																							astFactory.addASTChild(currentAST, tmp72_AST);
																							ASTPair __currentAST888 = currentAST.copy();
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
																							currentAST = __currentAST888;
																							_t = __t888;
																							_t = _t.getNextSibling();
																							trioformula_AST = (AST)currentAST.root;
																						}
																					else {
																						throw new NoViableAltException(_t);
																					}
																					}}}}}}}}}}}}}}}}}}}
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
				AST __t894 = _t;
				AST tmp73_AST = null;
				AST tmp73_AST_in = null;
				tmp73_AST = astFactory.create((AST)_t);
				tmp73_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp73_AST);
				ASTPair __currentAST894 = currentAST.copy();
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
				currentAST = __currentAST894;
				_t = __t894;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case MINUS:
			{
				AST __t895 = _t;
				AST tmp74_AST = null;
				AST tmp74_AST_in = null;
				tmp74_AST = astFactory.create((AST)_t);
				tmp74_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp74_AST);
				ASTPair __currentAST895 = currentAST.copy();
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
				currentAST = __currentAST895;
				_t = __t895;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case DIV:
			{
				AST __t896 = _t;
				AST tmp75_AST = null;
				AST tmp75_AST_in = null;
				tmp75_AST = astFactory.create((AST)_t);
				tmp75_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp75_AST);
				ASTPair __currentAST896 = currentAST.copy();
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
				currentAST = __currentAST896;
				_t = __t896;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case STAR:
			{
				AST __t897 = _t;
				AST tmp76_AST = null;
				AST tmp76_AST_in = null;
				tmp76_AST = astFactory.create((AST)_t);
				tmp76_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp76_AST);
				ASTPair __currentAST897 = currentAST.copy();
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
				currentAST = __currentAST897;
				_t = __t897;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case MOD:
			{
				AST __t898 = _t;
				AST tmp77_AST = null;
				AST tmp77_AST_in = null;
				tmp77_AST = astFactory.create((AST)_t);
				tmp77_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp77_AST);
				ASTPair __currentAST898 = currentAST.copy();
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
				currentAST = __currentAST898;
				_t = __t898;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case INTDIV:
			{
				AST __t899 = _t;
				AST tmp78_AST = null;
				AST tmp78_AST_in = null;
				tmp78_AST = astFactory.create((AST)_t);
				tmp78_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp78_AST);
				ASTPair __currentAST899 = currentAST.copy();
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
				currentAST = __currentAST899;
				_t = __t899;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case POW:
			{
				AST __t900 = _t;
				AST tmp79_AST = null;
				AST tmp79_AST_in = null;
				tmp79_AST = astFactory.create((AST)_t);
				tmp79_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp79_AST);
				ASTPair __currentAST900 = currentAST.copy();
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
				currentAST = __currentAST900;
				_t = __t900;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case SIGN_MINUS:
			{
				AST __t901 = _t;
				AST tmp80_AST = null;
				AST tmp80_AST_in = null;
				tmp80_AST = astFactory.create((AST)_t);
				tmp80_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp80_AST);
				ASTPair __currentAST901 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SIGN_MINUS);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST901;
				_t = __t901;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case SIGN_PLUS:
			{
				AST __t902 = _t;
				AST tmp81_AST = null;
				AST tmp81_AST_in = null;
				tmp81_AST = astFactory.create((AST)_t);
				tmp81_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp81_AST);
				ASTPair __currentAST902 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SIGN_PLUS);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST902;
				_t = __t902;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case NUMBER:
			{
				AST tmp82_AST = null;
				AST tmp82_AST_in = null;
				tmp82_AST = astFactory.create((AST)_t);
				tmp82_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp82_AST);
				match(_t,NUMBER);
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case VARIABLE:
			{
				AST tmp83_AST = null;
				AST tmp83_AST_in = null;
				tmp83_AST = astFactory.create((AST)_t);
				tmp83_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp83_AST);
				match(_t,VARIABLE);
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case CONSTANT:
			{
				AST tmp84_AST = null;
				AST tmp84_AST_in = null;
				tmp84_AST = astFactory.create((AST)_t);
				tmp84_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp84_AST);
				match(_t,CONSTANT);
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case LPAREN:
			{
				AST __t903 = _t;
				AST tmp85_AST = null;
				AST tmp85_AST_in = null;
				tmp85_AST = astFactory.create((AST)_t);
				tmp85_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp85_AST);
				ASTPair __currentAST903 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LPAREN);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST903;
				_t = __t903;
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
	
