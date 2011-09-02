// $ANTLR : "RootDismantler.g" -> "RootDismantler.java"$

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
 A dismantler for if and iff nodes, WithinF (WithinP) nested in Futr (Past) formulae,
 Futr (Past) in Lasts(Lasted).
 */
public class RootDismantler extends antlr.TreeParser       implements RootDismantlerTokenTypes
 {
public RootDismantler() {
	tokenNames = _tokenNames;
}

	public final void trioformula(AST _t) throws RecognitionException {
		
		AST trioformula_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST trioformula_AST = null;
		AST and0 = null;
		AST and0_AST = null;
		AST iff_and_tf1_AST = null;
		AST iff_and_tf1 = null;
		AST iff_and_tf2_AST = null;
		AST iff_and_tf2 = null;
		AST iff_and_tf3_AST = null;
		AST iff_and_tf3 = null;
		AST iff_and_tf4_AST = null;
		AST iff_and_tf4 = null;
		AST futr0 = null;
		AST futr0_AST = null;
		AST iff0_tf1_AST = null;
		AST iff0_tf1 = null;
		AST iff0_t1_AST = null;
		AST iff0_t1 = null;
		AST iff0_tf2_AST = null;
		AST iff0_tf2 = null;
		AST iff0_t2_AST = null;
		AST iff0_t2 = null;
		AST not0nn = null;
		AST not0nn_AST = null;
		AST futr0nn = null;
		AST futr0nn_AST = null;
		AST iff0nn_tf1_AST = null;
		AST iff0nn_tf1 = null;
		AST iff0nn_t1_AST = null;
		AST iff0nn_t1 = null;
		AST iff0nn_tf2_AST = null;
		AST iff0nn_tf2 = null;
		AST iff0nn_t2_AST = null;
		AST iff0nn_t2 = null;
		AST not0np = null;
		AST not0np_AST = null;
		AST futr0np = null;
		AST futr0np_AST = null;
		AST iff0np_tf1_AST = null;
		AST iff0np_tf1 = null;
		AST iff0np_t1_AST = null;
		AST iff0np_t1 = null;
		AST iff0np_tf2_AST = null;
		AST iff0np_tf2 = null;
		AST iff0np_t2_AST = null;
		AST iff0np_t2 = null;
		AST futr0pn = null;
		AST futr0pn_AST = null;
		AST iff0pn_tf1_AST = null;
		AST iff0pn_tf1 = null;
		AST iff0pn_t1_AST = null;
		AST iff0pn_t1 = null;
		AST not0pn = null;
		AST not0pn_AST = null;
		AST iff0pn_tf2_AST = null;
		AST iff0pn_tf2 = null;
		AST iff0pn_t2_AST = null;
		AST iff0pn_t2 = null;
		AST futr1 = null;
		AST futr1_AST = null;
		AST iff1_tf1_AST = null;
		AST iff1_tf1 = null;
		AST iff1_t1_AST = null;
		AST iff1_t1 = null;
		AST tf1_AST = null;
		AST tf1 = null;
		AST not1np = null;
		AST not1np_AST = null;
		AST futr1np = null;
		AST futr1np_AST = null;
		AST iff1np_tf1_AST = null;
		AST iff1np_tf1 = null;
		AST iff1np_t1_AST = null;
		AST iff1np_t1 = null;
		AST tf1np_AST = null;
		AST tf1np = null;
		AST iff_futr_lastsie_lastsie = null;
		AST iff_futr_lastsie_lastsie_AST = null;
		AST iff_futr_lastsie_tf1_AST = null;
		AST iff_futr_lastsie_tf1 = null;
		AST iff_futr_lastsie_t1_AST = null;
		AST iff_futr_lastsie_t1 = null;
		AST iff_futr_lastsie_futr = null;
		AST iff_futr_lastsie_futr_AST = null;
		AST iff_futr_lastsie_tf2_AST = null;
		AST iff_futr_lastsie_tf2 = null;
		AST iff_futr_lastsie_t2_AST = null;
		AST iff_futr_lastsie_t2 = null;
		AST tf2_AST = null;
		AST tf2 = null;
		AST futr2 = null;
		AST futr2_AST = null;
		AST iff2_tf1_AST = null;
		AST iff2_tf1 = null;
		AST iff2_t1_AST = null;
		AST iff2_t1 = null;
		AST tf2pn_AST = null;
		AST tf2pn = null;
		AST futr2pn = null;
		AST futr2pn_AST = null;
		AST iff2pn_tf1_AST = null;
		AST iff2pn_tf1 = null;
		AST iff2pn_t1_AST = null;
		AST iff2pn_t1 = null;
		AST if2_futr_tf1_AST = null;
		AST if2_futr_tf1 = null;
		AST if2_futr_t_AST = null;
		AST if2_futr_t = null;
		AST if2_futr_tf2_AST = null;
		AST if2_futr_tf2 = null;
		AST if2_tf1_AST = null;
		AST if2_tf1 = null;
		AST if2_tf2_AST = null;
		AST if2_tf2 = null;
		AST if_futr_tf1_AST = null;
		AST if_futr_tf1 = null;
		AST if_futr_t_AST = null;
		AST if_futr_t = null;
		AST if_futr_tf2_AST = null;
		AST if_futr_tf2 = null;
		AST if_tf1_AST = null;
		AST if_tf1 = null;
		AST if_tf2_AST = null;
		AST if_tf2 = null;
		AST futr_withinfee_tf_AST = null;
		AST futr_withinfee_tf = null;
		AST futr_withinfee_t1_AST = null;
		AST futr_withinfee_t1 = null;
		AST futr_withinfee_t2_AST = null;
		AST futr_withinfee_t2 = null;
		AST futr_withinfei_tf_AST = null;
		AST futr_withinfei_tf = null;
		AST futr_withinfei_t1_AST = null;
		AST futr_withinfei_t1 = null;
		AST futr_withinfei_t2_AST = null;
		AST futr_withinfei_t2 = null;
		AST futr_withinfie_tf_AST = null;
		AST futr_withinfie_tf = null;
		AST futr_withinfie_t1_AST = null;
		AST futr_withinfie_t1 = null;
		AST futr_withinfie_t2_AST = null;
		AST futr_withinfie_t2 = null;
		AST futr_withinfii_tf_AST = null;
		AST futr_withinfii_tf = null;
		AST futr_withinfii_t1_AST = null;
		AST futr_withinfii_t1 = null;
		AST futr_withinfii_t2_AST = null;
		AST futr_withinfii_t2 = null;
		AST past_withinpee_tf_AST = null;
		AST past_withinpee_tf = null;
		AST past_withinpee_t1_AST = null;
		AST past_withinpee_t1 = null;
		AST past_withinpee_t2_AST = null;
		AST past_withinpee_t2 = null;
		AST past_withinpei_tf_AST = null;
		AST past_withinpei_tf = null;
		AST past_withinpei_t1_AST = null;
		AST past_withinpei_t1 = null;
		AST past_withinpei_t2_AST = null;
		AST past_withinpei_t2 = null;
		AST past_withinpie_tf_AST = null;
		AST past_withinpie_tf = null;
		AST past_withinpie_t1_AST = null;
		AST past_withinpie_t1 = null;
		AST past_withinpie_t2_AST = null;
		AST past_withinpie_t2 = null;
		AST past_withinpii_tf_AST = null;
		AST past_withinpii_tf = null;
		AST past_withinpii_t1_AST = null;
		AST past_withinpii_t1 = null;
		AST past_withinpii_t2_AST = null;
		AST past_withinpii_t2 = null;
		AST tf_ee_AST = null;
		AST tf_ee = null;
		AST t1_ee_AST = null;
		AST t1_ee = null;
		AST t2_ee_AST = null;
		AST t2_ee = null;
		AST tf_ei_AST = null;
		AST tf_ei = null;
		AST t1_ei_AST = null;
		AST t1_ei = null;
		AST t2_ei_AST = null;
		AST t2_ei = null;
		AST tf_ie_AST = null;
		AST tf_ie = null;
		AST t1_ie_AST = null;
		AST t1_ie = null;
		AST t2_ie_AST = null;
		AST t2_ie = null;
		AST tf_ii_AST = null;
		AST tf_ii = null;
		AST t1_ii_AST = null;
		AST t1_ii = null;
		AST t2_ii_AST = null;
		AST t2_ii = null;
		AST tf_ee_p_AST = null;
		AST tf_ee_p = null;
		AST t1_ee_p_AST = null;
		AST t1_ee_p = null;
		AST t2_ee_p_AST = null;
		AST t2_ee_p = null;
		AST tf_ei_p_AST = null;
		AST tf_ei_p = null;
		AST t1_ei_p_AST = null;
		AST t1_ei_p = null;
		AST t2_ei_p_AST = null;
		AST t2_ei_p = null;
		AST tf_ie_p_AST = null;
		AST tf_ie_p = null;
		AST t1_ie_p_AST = null;
		AST t1_ie_p = null;
		AST t2_ie_p_AST = null;
		AST t2_ie_p = null;
		AST tf_ii_p_AST = null;
		AST tf_ii_p = null;
		AST t1_ii_p_AST = null;
		AST t1_ii_p = null;
		AST t2_ii_p_AST = null;
		AST t2_ii_p = null;
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case IFF:
			{
				AST __t494 = _t;
				AST tmp1_AST = null;
				AST tmp1_AST_in = null;
				tmp1_AST = astFactory.create((AST)_t);
				tmp1_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp1_AST);
				ASTPair __currentAST494 = currentAST.copy();
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
				currentAST = __currentAST494;
				_t = __t494;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case OR:
			{
				AST __t597 = _t;
				AST tmp2_AST = null;
				AST tmp2_AST_in = null;
				tmp2_AST = astFactory.create((AST)_t);
				tmp2_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp2_AST);
				ASTPair __currentAST597 = currentAST.copy();
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
				currentAST = __currentAST597;
				_t = __t597;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case OR_ROOT:
			{
				AST __t598 = _t;
				AST tmp3_AST = null;
				AST tmp3_AST_in = null;
				tmp3_AST = astFactory.create((AST)_t);
				tmp3_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp3_AST);
				ASTPair __currentAST598 = currentAST.copy();
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
				currentAST = __currentAST598;
				_t = __t598;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case AND:
			{
				AST __t599 = _t;
				AST tmp4_AST = null;
				AST tmp4_AST_in = null;
				tmp4_AST = astFactory.create((AST)_t);
				tmp4_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp4_AST);
				ASTPair __currentAST599 = currentAST.copy();
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
				currentAST = __currentAST599;
				_t = __t599;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case AND_ROOT:
			{
				AST __t600 = _t;
				AST tmp5_AST = null;
				AST tmp5_AST_in = null;
				tmp5_AST = astFactory.create((AST)_t);
				tmp5_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp5_AST);
				ASTPair __currentAST600 = currentAST.copy();
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
				currentAST = __currentAST600;
				_t = __t600;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case NOT:
			{
				AST __t601 = _t;
				AST tmp6_AST = null;
				AST tmp6_AST_in = null;
				tmp6_AST = astFactory.create((AST)_t);
				tmp6_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp6_AST);
				ASTPair __currentAST601 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,NOT);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST601;
				_t = __t601;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case EQ:
			{
				AST __t602 = _t;
				AST tmp7_AST = null;
				AST tmp7_AST_in = null;
				tmp7_AST = astFactory.create((AST)_t);
				tmp7_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp7_AST);
				ASTPair __currentAST602 = currentAST.copy();
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
				currentAST = __currentAST602;
				_t = __t602;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case NEQ:
			{
				AST __t603 = _t;
				AST tmp8_AST = null;
				AST tmp8_AST_in = null;
				tmp8_AST = astFactory.create((AST)_t);
				tmp8_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp8_AST);
				ASTPair __currentAST603 = currentAST.copy();
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
				currentAST = __currentAST603;
				_t = __t603;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LT:
			{
				AST __t604 = _t;
				AST tmp9_AST = null;
				AST tmp9_AST_in = null;
				tmp9_AST = astFactory.create((AST)_t);
				tmp9_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp9_AST);
				ASTPair __currentAST604 = currentAST.copy();
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
				currentAST = __currentAST604;
				_t = __t604;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case GT:
			{
				AST __t605 = _t;
				AST tmp10_AST = null;
				AST tmp10_AST_in = null;
				tmp10_AST = astFactory.create((AST)_t);
				tmp10_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp10_AST);
				ASTPair __currentAST605 = currentAST.copy();
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
				currentAST = __currentAST605;
				_t = __t605;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case GTE:
			{
				AST __t606 = _t;
				AST tmp11_AST = null;
				AST tmp11_AST_in = null;
				tmp11_AST = astFactory.create((AST)_t);
				tmp11_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp11_AST);
				ASTPair __currentAST606 = currentAST.copy();
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
				currentAST = __currentAST606;
				_t = __t606;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LTE:
			{
				AST __t607 = _t;
				AST tmp12_AST = null;
				AST tmp12_AST_in = null;
				tmp12_AST = astFactory.create((AST)_t);
				tmp12_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp12_AST);
				ASTPair __currentAST607 = currentAST.copy();
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
				currentAST = __currentAST607;
				_t = __t607;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALW:
			{
				AST __t608 = _t;
				AST tmp13_AST = null;
				AST tmp13_AST_in = null;
				tmp13_AST = astFactory.create((AST)_t);
				tmp13_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp13_AST);
				ASTPair __currentAST608 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALW);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST608;
				_t = __t608;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWF:
			{
				AST __t609 = _t;
				AST tmp14_AST = null;
				AST tmp14_AST_in = null;
				tmp14_AST = astFactory.create((AST)_t);
				tmp14_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp14_AST);
				ASTPair __currentAST609 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWF);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST609;
				_t = __t609;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWFE:
			{
				AST __t610 = _t;
				AST tmp15_AST = null;
				AST tmp15_AST_in = null;
				tmp15_AST = astFactory.create((AST)_t);
				tmp15_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp15_AST);
				ASTPair __currentAST610 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWFE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST610;
				_t = __t610;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWFI:
			{
				AST __t611 = _t;
				AST tmp16_AST = null;
				AST tmp16_AST_in = null;
				tmp16_AST = astFactory.create((AST)_t);
				tmp16_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp16_AST);
				ASTPair __currentAST611 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWFI);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST611;
				_t = __t611;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWP:
			{
				AST __t612 = _t;
				AST tmp17_AST = null;
				AST tmp17_AST_in = null;
				tmp17_AST = astFactory.create((AST)_t);
				tmp17_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp17_AST);
				ASTPair __currentAST612 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWP);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST612;
				_t = __t612;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWPE:
			{
				AST __t613 = _t;
				AST tmp18_AST = null;
				AST tmp18_AST_in = null;
				tmp18_AST = astFactory.create((AST)_t);
				tmp18_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp18_AST);
				ASTPair __currentAST613 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWPE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST613;
				_t = __t613;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case ALWPI:
			{
				AST __t614 = _t;
				AST tmp19_AST = null;
				AST tmp19_AST_in = null;
				tmp19_AST = astFactory.create((AST)_t);
				tmp19_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp19_AST);
				ASTPair __currentAST614 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,ALWPI);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST614;
				_t = __t614;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOM:
			{
				AST __t615 = _t;
				AST tmp20_AST = null;
				AST tmp20_AST_in = null;
				tmp20_AST = astFactory.create((AST)_t);
				tmp20_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp20_AST);
				ASTPair __currentAST615 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOM);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST615;
				_t = __t615;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMF:
			{
				AST __t616 = _t;
				AST tmp21_AST = null;
				AST tmp21_AST_in = null;
				tmp21_AST = astFactory.create((AST)_t);
				tmp21_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp21_AST);
				ASTPair __currentAST616 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMF);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST616;
				_t = __t616;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMFE:
			{
				AST __t617 = _t;
				AST tmp22_AST = null;
				AST tmp22_AST_in = null;
				tmp22_AST = astFactory.create((AST)_t);
				tmp22_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp22_AST);
				ASTPair __currentAST617 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMFE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST617;
				_t = __t617;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMFI:
			{
				AST __t618 = _t;
				AST tmp23_AST = null;
				AST tmp23_AST_in = null;
				tmp23_AST = astFactory.create((AST)_t);
				tmp23_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp23_AST);
				ASTPair __currentAST618 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMFI);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST618;
				_t = __t618;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMP:
			{
				AST __t619 = _t;
				AST tmp24_AST = null;
				AST tmp24_AST_in = null;
				tmp24_AST = astFactory.create((AST)_t);
				tmp24_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp24_AST);
				ASTPair __currentAST619 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMP);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST619;
				_t = __t619;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMPE:
			{
				AST __t620 = _t;
				AST tmp25_AST = null;
				AST tmp25_AST_in = null;
				tmp25_AST = astFactory.create((AST)_t);
				tmp25_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp25_AST);
				ASTPair __currentAST620 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMPE);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST620;
				_t = __t620;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SOMPI:
			{
				AST __t621 = _t;
				AST tmp26_AST = null;
				AST tmp26_AST_in = null;
				tmp26_AST = astFactory.create((AST)_t);
				tmp26_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp26_AST);
				ASTPair __currentAST621 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SOMPI);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST621;
				_t = __t621;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case NOWON:
			{
				AST __t622 = _t;
				AST tmp27_AST = null;
				AST tmp27_AST_in = null;
				tmp27_AST = astFactory.create((AST)_t);
				tmp27_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp27_AST);
				ASTPair __currentAST622 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,NOWON);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST622;
				_t = __t622;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case UPTONOW:
			{
				AST __t623 = _t;
				AST tmp28_AST = null;
				AST tmp28_AST_in = null;
				tmp28_AST = astFactory.create((AST)_t);
				tmp28_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp28_AST);
				ASTPair __currentAST623 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,UPTONOW);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST623;
				_t = __t623;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case BECOMES:
			{
				AST __t624 = _t;
				AST tmp29_AST = null;
				AST tmp29_AST_in = null;
				tmp29_AST = astFactory.create((AST)_t);
				tmp29_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp29_AST);
				ASTPair __currentAST624 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,BECOMES);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST624;
				_t = __t624;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case UNTIL:
			{
				AST __t625 = _t;
				AST tmp30_AST = null;
				AST tmp30_AST_in = null;
				tmp30_AST = astFactory.create((AST)_t);
				tmp30_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp30_AST);
				ASTPair __currentAST625 = currentAST.copy();
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
				currentAST = __currentAST625;
				_t = __t625;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case UNTILW:
			{
				AST __t626 = _t;
				AST tmp31_AST = null;
				AST tmp31_AST_in = null;
				tmp31_AST = astFactory.create((AST)_t);
				tmp31_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp31_AST);
				ASTPair __currentAST626 = currentAST.copy();
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
				currentAST = __currentAST626;
				_t = __t626;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SINCE:
			{
				AST __t627 = _t;
				AST tmp32_AST = null;
				AST tmp32_AST_in = null;
				tmp32_AST = astFactory.create((AST)_t);
				tmp32_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp32_AST);
				ASTPair __currentAST627 = currentAST.copy();
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
				currentAST = __currentAST627;
				_t = __t627;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case SINCEW:
			{
				AST __t628 = _t;
				AST tmp33_AST = null;
				AST tmp33_AST_in = null;
				tmp33_AST = astFactory.create((AST)_t);
				tmp33_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp33_AST);
				ASTPair __currentAST628 = currentAST.copy();
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
				currentAST = __currentAST628;
				_t = __t628;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case DIST:
			{
				AST __t629 = _t;
				AST tmp34_AST = null;
				AST tmp34_AST_in = null;
				tmp34_AST = astFactory.create((AST)_t);
				tmp34_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp34_AST);
				ASTPair __currentAST629 = currentAST.copy();
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
				currentAST = __currentAST629;
				_t = __t629;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTS:
			{
				AST __t680 = _t;
				AST tmp35_AST = null;
				AST tmp35_AST_in = null;
				tmp35_AST = astFactory.create((AST)_t);
				tmp35_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp35_AST);
				ASTPair __currentAST680 = currentAST.copy();
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
				currentAST = __currentAST680;
				_t = __t680;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTED:
			{
				AST __t737 = _t;
				AST tmp36_AST = null;
				AST tmp36_AST_in = null;
				tmp36_AST = astFactory.create((AST)_t);
				tmp36_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp36_AST);
				ASTPair __currentAST737 = currentAST.copy();
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
				currentAST = __currentAST737;
				_t = __t737;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case WITHIN:
			{
				AST __t738 = _t;
				AST tmp37_AST = null;
				AST tmp37_AST_in = null;
				tmp37_AST = astFactory.create((AST)_t);
				tmp37_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp37_AST);
				ASTPair __currentAST738 = currentAST.copy();
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
				currentAST = __currentAST738;
				_t = __t738;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case WITHINF:
			{
				AST __t739 = _t;
				AST tmp38_AST = null;
				AST tmp38_AST_in = null;
				tmp38_AST = astFactory.create((AST)_t);
				tmp38_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp38_AST);
				ASTPair __currentAST739 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,WITHINF);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST739;
				_t = __t739;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case WITHINFEE:
			{
				AST __t740 = _t;
				AST tmp39_AST = null;
				AST tmp39_AST_in = null;
				tmp39_AST = astFactory.create((AST)_t);
				tmp39_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp39_AST);
				ASTPair __currentAST740 = currentAST.copy();
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
				currentAST = __currentAST740;
				_t = __t740;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case WITHINFEI:
			{
				AST __t741 = _t;
				AST tmp40_AST = null;
				AST tmp40_AST_in = null;
				tmp40_AST = astFactory.create((AST)_t);
				tmp40_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp40_AST);
				ASTPair __currentAST741 = currentAST.copy();
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
				currentAST = __currentAST741;
				_t = __t741;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case WITHINFIE:
			{
				AST __t742 = _t;
				AST tmp41_AST = null;
				AST tmp41_AST_in = null;
				tmp41_AST = astFactory.create((AST)_t);
				tmp41_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp41_AST);
				ASTPair __currentAST742 = currentAST.copy();
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
				currentAST = __currentAST742;
				_t = __t742;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case WITHINFII:
			{
				AST __t743 = _t;
				AST tmp42_AST = null;
				AST tmp42_AST_in = null;
				tmp42_AST = astFactory.create((AST)_t);
				tmp42_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp42_AST);
				ASTPair __currentAST743 = currentAST.copy();
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
				currentAST = __currentAST743;
				_t = __t743;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case WITHINPEE:
			{
				AST __t744 = _t;
				AST tmp43_AST = null;
				AST tmp43_AST_in = null;
				tmp43_AST = astFactory.create((AST)_t);
				tmp43_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp43_AST);
				ASTPair __currentAST744 = currentAST.copy();
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
				currentAST = __currentAST744;
				_t = __t744;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case WITHINPEI:
			{
				AST __t745 = _t;
				AST tmp44_AST = null;
				AST tmp44_AST_in = null;
				tmp44_AST = astFactory.create((AST)_t);
				tmp44_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp44_AST);
				ASTPair __currentAST745 = currentAST.copy();
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
				currentAST = __currentAST745;
				_t = __t745;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case WITHINPIE:
			{
				AST __t746 = _t;
				AST tmp45_AST = null;
				AST tmp45_AST_in = null;
				tmp45_AST = astFactory.create((AST)_t);
				tmp45_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp45_AST);
				ASTPair __currentAST746 = currentAST.copy();
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
				currentAST = __currentAST746;
				_t = __t746;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case WITHINPII:
			{
				AST __t747 = _t;
				AST tmp46_AST = null;
				AST tmp46_AST_in = null;
				tmp46_AST = astFactory.create((AST)_t);
				tmp46_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp46_AST);
				ASTPair __currentAST747 = currentAST.copy();
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
				currentAST = __currentAST747;
				_t = __t747;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case NEXTTIME:
			{
				AST __t748 = _t;
				AST tmp47_AST = null;
				AST tmp47_AST_in = null;
				tmp47_AST = astFactory.create((AST)_t);
				tmp47_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp47_AST);
				ASTPair __currentAST748 = currentAST.copy();
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
				currentAST = __currentAST748;
				_t = __t748;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LASTTIME:
			{
				AST __t749 = _t;
				AST tmp48_AST = null;
				AST tmp48_AST_in = null;
				tmp48_AST = astFactory.create((AST)_t);
				tmp48_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp48_AST);
				ASTPair __currentAST749 = currentAST.copy();
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
				currentAST = __currentAST749;
				_t = __t749;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case NOW:
			{
				AST __t750 = _t;
				AST tmp49_AST = null;
				AST tmp49_AST_in = null;
				tmp49_AST = astFactory.create((AST)_t);
				tmp49_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp49_AST);
				ASTPair __currentAST750 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,NOW);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST750;
				_t = __t750;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			case LPAREN:
			{
				AST __t751 = _t;
				AST tmp50_AST = null;
				AST tmp50_AST_in = null;
				tmp50_AST = astFactory.create((AST)_t);
				tmp50_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp50_AST);
				ASTPair __currentAST751 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LPAREN);
				_t = _t.getFirstChild();
				trioformula(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST751;
				_t = __t751;
				_t = _t.getNextSibling();
				trioformula_AST = (AST)currentAST.root;
				break;
			}
			default:
				boolean synPredMatched499 = false;
				if (((_t.getType()==IFF_ROOT))) {
					AST __t499 = _t;
					synPredMatched499 = true;
					inputState.guessing++;
					try {
						{
						AST __t496 = _t;
						ASTPair __currentAST496 = currentAST.copy();
						currentAST.root = currentAST.child;
						currentAST.child = null;
						match(_t,IFF_ROOT);
						_t = _t.getFirstChild();
						AST __t497 = _t;
						ASTPair __currentAST497 = currentAST.copy();
						currentAST.root = currentAST.child;
						currentAST.child = null;
						match(_t,AND);
						_t = _t.getFirstChild();
						trioformula(_t);
						_t = _retTree;
						trioformula(_t);
						_t = _retTree;
						currentAST = __currentAST497;
						_t = __t497;
						_t = _t.getNextSibling();
						AST __t498 = _t;
						ASTPair __currentAST498 = currentAST.copy();
						currentAST.root = currentAST.child;
						currentAST.child = null;
						match(_t,AND);
						_t = _t.getFirstChild();
						trioformula(_t);
						_t = _retTree;
						trioformula(_t);
						_t = _retTree;
						currentAST = __currentAST498;
						_t = __t498;
						_t = _t.getNextSibling();
						currentAST = __currentAST496;
						_t = __t496;
						_t = _t.getNextSibling();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched499 = false;
					}
					_t = __t499;
inputState.guessing--;
				}
				if ( synPredMatched499 ) {
					AST __t500 = _t;
					AST tmp51_AST = null;
					AST tmp51_AST_in = null;
					tmp51_AST = astFactory.create((AST)_t);
					tmp51_AST_in = (AST)_t;
					ASTPair __currentAST500 = currentAST.copy();
					currentAST.root = currentAST.child;
					currentAST.child = null;
					match(_t,IFF_ROOT);
					_t = _t.getFirstChild();
					AST __t501 = _t;
					and0 = _t==ASTNULL ? null :(AST)_t;
					AST and0_AST_in = null;
					and0_AST = astFactory.create(and0);
					ASTPair __currentAST501 = currentAST.copy();
					currentAST.root = currentAST.child;
					currentAST.child = null;
					match(_t,AND);
					_t = _t.getFirstChild();
					iff_and_tf1 = _t==ASTNULL ? null : (AST)_t;
					trioformula(_t);
					_t = _retTree;
					iff_and_tf1_AST = (AST)returnAST;
					iff_and_tf2 = _t==ASTNULL ? null : (AST)_t;
					trioformula(_t);
					_t = _retTree;
					iff_and_tf2_AST = (AST)returnAST;
					currentAST = __currentAST501;
					_t = __t501;
					_t = _t.getNextSibling();
					AST __t502 = _t;
					AST tmp52_AST = null;
					AST tmp52_AST_in = null;
					tmp52_AST = astFactory.create((AST)_t);
					tmp52_AST_in = (AST)_t;
					ASTPair __currentAST502 = currentAST.copy();
					currentAST.root = currentAST.child;
					currentAST.child = null;
					match(_t,AND);
					_t = _t.getFirstChild();
					iff_and_tf3 = _t==ASTNULL ? null : (AST)_t;
					trioformula(_t);
					_t = _retTree;
					iff_and_tf3_AST = (AST)returnAST;
					iff_and_tf4 = _t==ASTNULL ? null : (AST)_t;
					trioformula(_t);
					_t = _retTree;
					iff_and_tf4_AST = (AST)returnAST;
					currentAST = __currentAST502;
					_t = __t502;
					_t = _t.getNextSibling();
					currentAST = __currentAST500;
					_t = __t500;
					_t = _t.getNextSibling();
					if ( inputState.guessing==0 ) {
						trioformula_AST = (AST)currentAST.root;
						
									trioformula_AST=
						(AST)astFactory.make( (new ASTArray(5)).add(astFactory.create(tmp51_AST)).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp52_AST)).add(iff_and_tf1_AST).add(iff_and_tf2_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp52_AST)).add(iff_and_tf3_AST).add(iff_and_tf4_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(OR)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT)).add(iff_and_tf1_AST))).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT)).add(iff_and_tf2_AST))))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(OR)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT)).add(iff_and_tf3_AST))).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT)).add(iff_and_tf4_AST))))));
								
						currentAST.root = trioformula_AST;
						currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
							trioformula_AST.getFirstChild() : trioformula_AST;
						currentAST.advanceChildToEnd();
					}
				}
				else {
					boolean synPredMatched507 = false;
					if (((_t.getType()==IFF_ROOT))) {
						AST __t507 = _t;
						synPredMatched507 = true;
						inputState.guessing++;
						try {
							{
							AST __t504 = _t;
							ASTPair __currentAST504 = currentAST.copy();
							currentAST.root = currentAST.child;
							currentAST.child = null;
							match(_t,IFF_ROOT);
							_t = _t.getFirstChild();
							AST __t505 = _t;
							ASTPair __currentAST505 = currentAST.copy();
							currentAST.root = currentAST.child;
							currentAST.child = null;
							match(_t,FUTR);
							_t = _t.getFirstChild();
							trioformula(_t);
							_t = _retTree;
							term(_t);
							_t = _retTree;
							currentAST = __currentAST505;
							_t = __t505;
							_t = _t.getNextSibling();
							AST __t506 = _t;
							ASTPair __currentAST506 = currentAST.copy();
							currentAST.root = currentAST.child;
							currentAST.child = null;
							match(_t,FUTR);
							_t = _t.getFirstChild();
							trioformula(_t);
							_t = _retTree;
							term(_t);
							_t = _retTree;
							currentAST = __currentAST506;
							_t = __t506;
							_t = _t.getNextSibling();
							currentAST = __currentAST504;
							_t = __t504;
							_t = _t.getNextSibling();
							}
						}
						catch (RecognitionException pe) {
							synPredMatched507 = false;
						}
						_t = __t507;
inputState.guessing--;
					}
					if ( synPredMatched507 ) {
						AST __t508 = _t;
						AST tmp53_AST = null;
						AST tmp53_AST_in = null;
						tmp53_AST = astFactory.create((AST)_t);
						tmp53_AST_in = (AST)_t;
						ASTPair __currentAST508 = currentAST.copy();
						currentAST.root = currentAST.child;
						currentAST.child = null;
						match(_t,IFF_ROOT);
						_t = _t.getFirstChild();
						AST __t509 = _t;
						futr0 = _t==ASTNULL ? null :(AST)_t;
						AST futr0_AST_in = null;
						futr0_AST = astFactory.create(futr0);
						ASTPair __currentAST509 = currentAST.copy();
						currentAST.root = currentAST.child;
						currentAST.child = null;
						match(_t,FUTR);
						_t = _t.getFirstChild();
						iff0_tf1 = _t==ASTNULL ? null : (AST)_t;
						trioformula(_t);
						_t = _retTree;
						iff0_tf1_AST = (AST)returnAST;
						iff0_t1 = _t==ASTNULL ? null : (AST)_t;
						term(_t);
						_t = _retTree;
						iff0_t1_AST = (AST)returnAST;
						currentAST = __currentAST509;
						_t = __t509;
						_t = _t.getNextSibling();
						AST __t510 = _t;
						AST tmp54_AST = null;
						AST tmp54_AST_in = null;
						tmp54_AST = astFactory.create((AST)_t);
						tmp54_AST_in = (AST)_t;
						ASTPair __currentAST510 = currentAST.copy();
						currentAST.root = currentAST.child;
						currentAST.child = null;
						match(_t,FUTR);
						_t = _t.getFirstChild();
						iff0_tf2 = _t==ASTNULL ? null : (AST)_t;
						trioformula(_t);
						_t = _retTree;
						iff0_tf2_AST = (AST)returnAST;
						iff0_t2 = _t==ASTNULL ? null : (AST)_t;
						term(_t);
						_t = _retTree;
						iff0_t2_AST = (AST)returnAST;
						currentAST = __currentAST510;
						_t = __t510;
						_t = _t.getNextSibling();
						currentAST = __currentAST508;
						_t = __t508;
						_t = _t.getNextSibling();
						if ( inputState.guessing==0 ) {
							trioformula_AST = (AST)currentAST.root;
							
									trioformula_AST=
							(AST)astFactory.make( (new ASTArray(5)).add(astFactory.create(tmp53_AST)).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp54_AST)).add(iff0_tf1_AST).add(iff0_t1_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp54_AST)).add(iff0_tf2_AST).add(iff0_t2_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp54_AST)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT)).add(iff0_tf1_AST))).add(iff0_t1_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp54_AST)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT)).add(iff0_tf2_AST))).add(iff0_t2_AST))));	
								
							currentAST.root = trioformula_AST;
							currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
								trioformula_AST.getFirstChild() : trioformula_AST;
							currentAST.advanceChildToEnd();
						}
					}
					else {
						boolean synPredMatched517 = false;
						if (((_t.getType()==IFF_ROOT))) {
							AST __t517 = _t;
							synPredMatched517 = true;
							inputState.guessing++;
							try {
								{
								AST __t512 = _t;
								ASTPair __currentAST512 = currentAST.copy();
								currentAST.root = currentAST.child;
								currentAST.child = null;
								match(_t,IFF_ROOT);
								_t = _t.getFirstChild();
								AST __t513 = _t;
								ASTPair __currentAST513 = currentAST.copy();
								currentAST.root = currentAST.child;
								currentAST.child = null;
								match(_t,NOT);
								_t = _t.getFirstChild();
								AST __t514 = _t;
								ASTPair __currentAST514 = currentAST.copy();
								currentAST.root = currentAST.child;
								currentAST.child = null;
								match(_t,FUTR);
								_t = _t.getFirstChild();
								trioformula(_t);
								_t = _retTree;
								term(_t);
								_t = _retTree;
								currentAST = __currentAST514;
								_t = __t514;
								_t = _t.getNextSibling();
								currentAST = __currentAST513;
								_t = __t513;
								_t = _t.getNextSibling();
								AST __t515 = _t;
								ASTPair __currentAST515 = currentAST.copy();
								currentAST.root = currentAST.child;
								currentAST.child = null;
								match(_t,NOT);
								_t = _t.getFirstChild();
								AST __t516 = _t;
								ASTPair __currentAST516 = currentAST.copy();
								currentAST.root = currentAST.child;
								currentAST.child = null;
								match(_t,FUTR);
								_t = _t.getFirstChild();
								trioformula(_t);
								_t = _retTree;
								term(_t);
								_t = _retTree;
								currentAST = __currentAST516;
								_t = __t516;
								_t = _t.getNextSibling();
								currentAST = __currentAST515;
								_t = __t515;
								_t = _t.getNextSibling();
								currentAST = __currentAST512;
								_t = __t512;
								_t = _t.getNextSibling();
								}
							}
							catch (RecognitionException pe) {
								synPredMatched517 = false;
							}
							_t = __t517;
inputState.guessing--;
						}
						if ( synPredMatched517 ) {
							AST __t518 = _t;
							AST tmp55_AST = null;
							AST tmp55_AST_in = null;
							tmp55_AST = astFactory.create((AST)_t);
							tmp55_AST_in = (AST)_t;
							ASTPair __currentAST518 = currentAST.copy();
							currentAST.root = currentAST.child;
							currentAST.child = null;
							match(_t,IFF_ROOT);
							_t = _t.getFirstChild();
							AST __t519 = _t;
							not0nn = _t==ASTNULL ? null :(AST)_t;
							AST not0nn_AST_in = null;
							not0nn_AST = astFactory.create(not0nn);
							ASTPair __currentAST519 = currentAST.copy();
							currentAST.root = currentAST.child;
							currentAST.child = null;
							match(_t,NOT);
							_t = _t.getFirstChild();
							AST __t520 = _t;
							futr0nn = _t==ASTNULL ? null :(AST)_t;
							AST futr0nn_AST_in = null;
							futr0nn_AST = astFactory.create(futr0nn);
							ASTPair __currentAST520 = currentAST.copy();
							currentAST.root = currentAST.child;
							currentAST.child = null;
							match(_t,FUTR);
							_t = _t.getFirstChild();
							iff0nn_tf1 = _t==ASTNULL ? null : (AST)_t;
							trioformula(_t);
							_t = _retTree;
							iff0nn_tf1_AST = (AST)returnAST;
							iff0nn_t1 = _t==ASTNULL ? null : (AST)_t;
							term(_t);
							_t = _retTree;
							iff0nn_t1_AST = (AST)returnAST;
							currentAST = __currentAST520;
							_t = __t520;
							_t = _t.getNextSibling();
							currentAST = __currentAST519;
							_t = __t519;
							_t = _t.getNextSibling();
							AST __t521 = _t;
							AST tmp56_AST = null;
							AST tmp56_AST_in = null;
							tmp56_AST = astFactory.create((AST)_t);
							tmp56_AST_in = (AST)_t;
							ASTPair __currentAST521 = currentAST.copy();
							currentAST.root = currentAST.child;
							currentAST.child = null;
							match(_t,NOT);
							_t = _t.getFirstChild();
							AST __t522 = _t;
							AST tmp57_AST = null;
							AST tmp57_AST_in = null;
							tmp57_AST = astFactory.create((AST)_t);
							tmp57_AST_in = (AST)_t;
							ASTPair __currentAST522 = currentAST.copy();
							currentAST.root = currentAST.child;
							currentAST.child = null;
							match(_t,FUTR);
							_t = _t.getFirstChild();
							iff0nn_tf2 = _t==ASTNULL ? null : (AST)_t;
							trioformula(_t);
							_t = _retTree;
							iff0nn_tf2_AST = (AST)returnAST;
							iff0nn_t2 = _t==ASTNULL ? null : (AST)_t;
							term(_t);
							_t = _retTree;
							iff0nn_t2_AST = (AST)returnAST;
							currentAST = __currentAST522;
							_t = __t522;
							_t = _t.getNextSibling();
							currentAST = __currentAST521;
							_t = __t521;
							_t = _t.getNextSibling();
							currentAST = __currentAST518;
							_t = __t518;
							_t = _t.getNextSibling();
							if ( inputState.guessing==0 ) {
								trioformula_AST = (AST)currentAST.root;
								
										trioformula_AST=
								(AST)astFactory.make( (new ASTArray(5)).add(astFactory.create(tmp55_AST)).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp57_AST)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp56_AST)).add(iff0nn_tf1_AST))).add(iff0nn_t1_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp57_AST)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp56_AST)).add(iff0nn_tf2_AST))).add(iff0nn_t2_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp57_AST)).add(iff0nn_tf1_AST).add(iff0nn_t1_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp57_AST)).add(iff0nn_tf2_AST).add(iff0nn_t2_AST))));	
									
								currentAST.root = trioformula_AST;
								currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
									trioformula_AST.getFirstChild() : trioformula_AST;
								currentAST.advanceChildToEnd();
							}
						}
						else {
							boolean synPredMatched528 = false;
							if (((_t.getType()==IFF_ROOT))) {
								AST __t528 = _t;
								synPredMatched528 = true;
								inputState.guessing++;
								try {
									{
									AST __t524 = _t;
									ASTPair __currentAST524 = currentAST.copy();
									currentAST.root = currentAST.child;
									currentAST.child = null;
									match(_t,IFF_ROOT);
									_t = _t.getFirstChild();
									AST __t525 = _t;
									ASTPair __currentAST525 = currentAST.copy();
									currentAST.root = currentAST.child;
									currentAST.child = null;
									match(_t,NOT);
									_t = _t.getFirstChild();
									AST __t526 = _t;
									ASTPair __currentAST526 = currentAST.copy();
									currentAST.root = currentAST.child;
									currentAST.child = null;
									match(_t,FUTR);
									_t = _t.getFirstChild();
									trioformula(_t);
									_t = _retTree;
									term(_t);
									_t = _retTree;
									currentAST = __currentAST526;
									_t = __t526;
									_t = _t.getNextSibling();
									currentAST = __currentAST525;
									_t = __t525;
									_t = _t.getNextSibling();
									AST __t527 = _t;
									ASTPair __currentAST527 = currentAST.copy();
									currentAST.root = currentAST.child;
									currentAST.child = null;
									match(_t,FUTR);
									_t = _t.getFirstChild();
									trioformula(_t);
									_t = _retTree;
									term(_t);
									_t = _retTree;
									currentAST = __currentAST527;
									_t = __t527;
									_t = _t.getNextSibling();
									currentAST = __currentAST524;
									_t = __t524;
									_t = _t.getNextSibling();
									}
								}
								catch (RecognitionException pe) {
									synPredMatched528 = false;
								}
								_t = __t528;
inputState.guessing--;
							}
							if ( synPredMatched528 ) {
								AST __t529 = _t;
								AST tmp58_AST = null;
								AST tmp58_AST_in = null;
								tmp58_AST = astFactory.create((AST)_t);
								tmp58_AST_in = (AST)_t;
								ASTPair __currentAST529 = currentAST.copy();
								currentAST.root = currentAST.child;
								currentAST.child = null;
								match(_t,IFF_ROOT);
								_t = _t.getFirstChild();
								AST __t530 = _t;
								not0np = _t==ASTNULL ? null :(AST)_t;
								AST not0np_AST_in = null;
								not0np_AST = astFactory.create(not0np);
								ASTPair __currentAST530 = currentAST.copy();
								currentAST.root = currentAST.child;
								currentAST.child = null;
								match(_t,NOT);
								_t = _t.getFirstChild();
								AST __t531 = _t;
								futr0np = _t==ASTNULL ? null :(AST)_t;
								AST futr0np_AST_in = null;
								futr0np_AST = astFactory.create(futr0np);
								ASTPair __currentAST531 = currentAST.copy();
								currentAST.root = currentAST.child;
								currentAST.child = null;
								match(_t,FUTR);
								_t = _t.getFirstChild();
								iff0np_tf1 = _t==ASTNULL ? null : (AST)_t;
								trioformula(_t);
								_t = _retTree;
								iff0np_tf1_AST = (AST)returnAST;
								iff0np_t1 = _t==ASTNULL ? null : (AST)_t;
								term(_t);
								_t = _retTree;
								iff0np_t1_AST = (AST)returnAST;
								currentAST = __currentAST531;
								_t = __t531;
								_t = _t.getNextSibling();
								currentAST = __currentAST530;
								_t = __t530;
								_t = _t.getNextSibling();
								AST __t532 = _t;
								AST tmp59_AST = null;
								AST tmp59_AST_in = null;
								tmp59_AST = astFactory.create((AST)_t);
								tmp59_AST_in = (AST)_t;
								ASTPair __currentAST532 = currentAST.copy();
								currentAST.root = currentAST.child;
								currentAST.child = null;
								match(_t,FUTR);
								_t = _t.getFirstChild();
								iff0np_tf2 = _t==ASTNULL ? null : (AST)_t;
								trioformula(_t);
								_t = _retTree;
								iff0np_tf2_AST = (AST)returnAST;
								iff0np_t2 = _t==ASTNULL ? null : (AST)_t;
								term(_t);
								_t = _retTree;
								iff0np_t2_AST = (AST)returnAST;
								currentAST = __currentAST532;
								_t = __t532;
								_t = _t.getNextSibling();
								currentAST = __currentAST529;
								_t = __t529;
								_t = _t.getNextSibling();
								if ( inputState.guessing==0 ) {
									trioformula_AST = (AST)currentAST.root;
									
											trioformula_AST=
									(AST)astFactory.make( (new ASTArray(5)).add(astFactory.create(tmp58_AST)).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp59_AST)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT)).add(iff0np_tf1_AST))).add(iff0np_t1_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp59_AST)).add(iff0np_tf2_AST).add(iff0np_t2_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp59_AST)).add(iff0np_tf1_AST).add(iff0np_t1_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp59_AST)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT)).add(iff0np_tf2_AST))).add(iff0np_t2_AST))));	
										
									currentAST.root = trioformula_AST;
									currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
										trioformula_AST.getFirstChild() : trioformula_AST;
									currentAST.advanceChildToEnd();
								}
							}
							else {
								boolean synPredMatched538 = false;
								if (((_t.getType()==IFF_ROOT))) {
									AST __t538 = _t;
									synPredMatched538 = true;
									inputState.guessing++;
									try {
										{
										AST __t534 = _t;
										ASTPair __currentAST534 = currentAST.copy();
										currentAST.root = currentAST.child;
										currentAST.child = null;
										match(_t,IFF_ROOT);
										_t = _t.getFirstChild();
										AST __t535 = _t;
										ASTPair __currentAST535 = currentAST.copy();
										currentAST.root = currentAST.child;
										currentAST.child = null;
										match(_t,FUTR);
										_t = _t.getFirstChild();
										trioformula(_t);
										_t = _retTree;
										term(_t);
										_t = _retTree;
										currentAST = __currentAST535;
										_t = __t535;
										_t = _t.getNextSibling();
										AST __t536 = _t;
										ASTPair __currentAST536 = currentAST.copy();
										currentAST.root = currentAST.child;
										currentAST.child = null;
										match(_t,NOT);
										_t = _t.getFirstChild();
										AST __t537 = _t;
										ASTPair __currentAST537 = currentAST.copy();
										currentAST.root = currentAST.child;
										currentAST.child = null;
										match(_t,FUTR);
										_t = _t.getFirstChild();
										trioformula(_t);
										_t = _retTree;
										term(_t);
										_t = _retTree;
										currentAST = __currentAST537;
										_t = __t537;
										_t = _t.getNextSibling();
										currentAST = __currentAST536;
										_t = __t536;
										_t = _t.getNextSibling();
										currentAST = __currentAST534;
										_t = __t534;
										_t = _t.getNextSibling();
										}
									}
									catch (RecognitionException pe) {
										synPredMatched538 = false;
									}
									_t = __t538;
inputState.guessing--;
								}
								if ( synPredMatched538 ) {
									AST __t539 = _t;
									AST tmp60_AST = null;
									AST tmp60_AST_in = null;
									tmp60_AST = astFactory.create((AST)_t);
									tmp60_AST_in = (AST)_t;
									ASTPair __currentAST539 = currentAST.copy();
									currentAST.root = currentAST.child;
									currentAST.child = null;
									match(_t,IFF_ROOT);
									_t = _t.getFirstChild();
									AST __t540 = _t;
									futr0pn = _t==ASTNULL ? null :(AST)_t;
									AST futr0pn_AST_in = null;
									futr0pn_AST = astFactory.create(futr0pn);
									ASTPair __currentAST540 = currentAST.copy();
									currentAST.root = currentAST.child;
									currentAST.child = null;
									match(_t,FUTR);
									_t = _t.getFirstChild();
									iff0pn_tf1 = _t==ASTNULL ? null : (AST)_t;
									trioformula(_t);
									_t = _retTree;
									iff0pn_tf1_AST = (AST)returnAST;
									iff0pn_t1 = _t==ASTNULL ? null : (AST)_t;
									term(_t);
									_t = _retTree;
									iff0pn_t1_AST = (AST)returnAST;
									currentAST = __currentAST540;
									_t = __t540;
									_t = _t.getNextSibling();
									AST __t541 = _t;
									not0pn = _t==ASTNULL ? null :(AST)_t;
									AST not0pn_AST_in = null;
									not0pn_AST = astFactory.create(not0pn);
									ASTPair __currentAST541 = currentAST.copy();
									currentAST.root = currentAST.child;
									currentAST.child = null;
									match(_t,NOT);
									_t = _t.getFirstChild();
									AST __t542 = _t;
									AST tmp61_AST = null;
									AST tmp61_AST_in = null;
									tmp61_AST = astFactory.create((AST)_t);
									tmp61_AST_in = (AST)_t;
									ASTPair __currentAST542 = currentAST.copy();
									currentAST.root = currentAST.child;
									currentAST.child = null;
									match(_t,FUTR);
									_t = _t.getFirstChild();
									iff0pn_tf2 = _t==ASTNULL ? null : (AST)_t;
									trioformula(_t);
									_t = _retTree;
									iff0pn_tf2_AST = (AST)returnAST;
									iff0pn_t2 = _t==ASTNULL ? null : (AST)_t;
									term(_t);
									_t = _retTree;
									iff0pn_t2_AST = (AST)returnAST;
									currentAST = __currentAST542;
									_t = __t542;
									_t = _t.getNextSibling();
									currentAST = __currentAST541;
									_t = __t541;
									_t = _t.getNextSibling();
									currentAST = __currentAST539;
									_t = __t539;
									_t = _t.getNextSibling();
									if ( inputState.guessing==0 ) {
										trioformula_AST = (AST)currentAST.root;
										
												trioformula_AST=
										(AST)astFactory.make( (new ASTArray(5)).add(astFactory.create(tmp60_AST)).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp61_AST)).add(iff0pn_tf1_AST).add(iff0pn_t1_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp61_AST)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT)).add(iff0pn_tf2_AST))).add(iff0pn_t2_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp61_AST)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT)).add(iff0pn_tf1_AST))).add(iff0pn_t1_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp61_AST)).add(iff0pn_tf2_AST).add(iff0pn_t2_AST))));	
											
										currentAST.root = trioformula_AST;
										currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
											trioformula_AST.getFirstChild() : trioformula_AST;
										currentAST.advanceChildToEnd();
									}
								}
								else {
									boolean synPredMatched546 = false;
									if (((_t.getType()==IFF_ROOT))) {
										AST __t546 = _t;
										synPredMatched546 = true;
										inputState.guessing++;
										try {
											{
											AST __t544 = _t;
											ASTPair __currentAST544 = currentAST.copy();
											currentAST.root = currentAST.child;
											currentAST.child = null;
											match(_t,IFF_ROOT);
											_t = _t.getFirstChild();
											AST __t545 = _t;
											ASTPair __currentAST545 = currentAST.copy();
											currentAST.root = currentAST.child;
											currentAST.child = null;
											match(_t,FUTR);
											_t = _t.getFirstChild();
											trioformula(_t);
											_t = _retTree;
											term(_t);
											_t = _retTree;
											currentAST = __currentAST545;
											_t = __t545;
											_t = _t.getNextSibling();
											trioformula(_t);
											_t = _retTree;
											currentAST = __currentAST544;
											_t = __t544;
											_t = _t.getNextSibling();
											}
										}
										catch (RecognitionException pe) {
											synPredMatched546 = false;
										}
										_t = __t546;
inputState.guessing--;
									}
									if ( synPredMatched546 ) {
										AST __t547 = _t;
										AST tmp62_AST = null;
										AST tmp62_AST_in = null;
										tmp62_AST = astFactory.create((AST)_t);
										tmp62_AST_in = (AST)_t;
										ASTPair __currentAST547 = currentAST.copy();
										currentAST.root = currentAST.child;
										currentAST.child = null;
										match(_t,IFF_ROOT);
										_t = _t.getFirstChild();
										AST __t548 = _t;
										futr1 = _t==ASTNULL ? null :(AST)_t;
										AST futr1_AST_in = null;
										futr1_AST = astFactory.create(futr1);
										ASTPair __currentAST548 = currentAST.copy();
										currentAST.root = currentAST.child;
										currentAST.child = null;
										match(_t,FUTR);
										_t = _t.getFirstChild();
										iff1_tf1 = _t==ASTNULL ? null : (AST)_t;
										trioformula(_t);
										_t = _retTree;
										iff1_tf1_AST = (AST)returnAST;
										iff1_t1 = _t==ASTNULL ? null : (AST)_t;
										term(_t);
										_t = _retTree;
										iff1_t1_AST = (AST)returnAST;
										currentAST = __currentAST548;
										_t = __t548;
										_t = _t.getNextSibling();
										tf1 = _t==ASTNULL ? null : (AST)_t;
										trioformula(_t);
										_t = _retTree;
										tf1_AST = (AST)returnAST;
										currentAST = __currentAST547;
										_t = __t547;
										_t = _t.getNextSibling();
										if ( inputState.guessing==0 ) {
											trioformula_AST = (AST)currentAST.root;
											
													trioformula_AST=
											(AST)astFactory.make( (new ASTArray(4)).add(astFactory.create(tmp62_AST)).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(FUTR)).add(iff1_tf1_AST).add(iff1_t1_AST))).add(tf1_AST).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(FUTR)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT)).add(iff1_tf1_AST))).add(iff1_t1_AST))));	
												
											currentAST.root = trioformula_AST;
											currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
												trioformula_AST.getFirstChild() : trioformula_AST;
											currentAST.advanceChildToEnd();
										}
									}
									else {
										boolean synPredMatched553 = false;
										if (((_t.getType()==IFF_ROOT))) {
											AST __t553 = _t;
											synPredMatched553 = true;
											inputState.guessing++;
											try {
												{
												AST __t550 = _t;
												ASTPair __currentAST550 = currentAST.copy();
												currentAST.root = currentAST.child;
												currentAST.child = null;
												match(_t,IFF_ROOT);
												_t = _t.getFirstChild();
												AST __t551 = _t;
												ASTPair __currentAST551 = currentAST.copy();
												currentAST.root = currentAST.child;
												currentAST.child = null;
												match(_t,NOT);
												_t = _t.getFirstChild();
												AST __t552 = _t;
												ASTPair __currentAST552 = currentAST.copy();
												currentAST.root = currentAST.child;
												currentAST.child = null;
												match(_t,FUTR);
												_t = _t.getFirstChild();
												trioformula(_t);
												_t = _retTree;
												term(_t);
												_t = _retTree;
												currentAST = __currentAST552;
												_t = __t552;
												_t = _t.getNextSibling();
												currentAST = __currentAST551;
												_t = __t551;
												_t = _t.getNextSibling();
												trioformula(_t);
												_t = _retTree;
												currentAST = __currentAST550;
												_t = __t550;
												_t = _t.getNextSibling();
												}
											}
											catch (RecognitionException pe) {
												synPredMatched553 = false;
											}
											_t = __t553;
inputState.guessing--;
										}
										if ( synPredMatched553 ) {
											AST __t554 = _t;
											AST tmp63_AST = null;
											AST tmp63_AST_in = null;
											tmp63_AST = astFactory.create((AST)_t);
											tmp63_AST_in = (AST)_t;
											ASTPair __currentAST554 = currentAST.copy();
											currentAST.root = currentAST.child;
											currentAST.child = null;
											match(_t,IFF_ROOT);
											_t = _t.getFirstChild();
											AST __t555 = _t;
											not1np = _t==ASTNULL ? null :(AST)_t;
											AST not1np_AST_in = null;
											not1np_AST = astFactory.create(not1np);
											ASTPair __currentAST555 = currentAST.copy();
											currentAST.root = currentAST.child;
											currentAST.child = null;
											match(_t,NOT);
											_t = _t.getFirstChild();
											AST __t556 = _t;
											futr1np = _t==ASTNULL ? null :(AST)_t;
											AST futr1np_AST_in = null;
											futr1np_AST = astFactory.create(futr1np);
											ASTPair __currentAST556 = currentAST.copy();
											currentAST.root = currentAST.child;
											currentAST.child = null;
											match(_t,FUTR);
											_t = _t.getFirstChild();
											iff1np_tf1 = _t==ASTNULL ? null : (AST)_t;
											trioformula(_t);
											_t = _retTree;
											iff1np_tf1_AST = (AST)returnAST;
											iff1np_t1 = _t==ASTNULL ? null : (AST)_t;
											term(_t);
											_t = _retTree;
											iff1np_t1_AST = (AST)returnAST;
											currentAST = __currentAST556;
											_t = __t556;
											_t = _t.getNextSibling();
											currentAST = __currentAST555;
											_t = __t555;
											_t = _t.getNextSibling();
											tf1np = _t==ASTNULL ? null : (AST)_t;
											trioformula(_t);
											_t = _retTree;
											tf1np_AST = (AST)returnAST;
											currentAST = __currentAST554;
											_t = __t554;
											_t = _t.getNextSibling();
											if ( inputState.guessing==0 ) {
												trioformula_AST = (AST)currentAST.root;
												
														trioformula_AST=
												(AST)astFactory.make( (new ASTArray(4)).add(astFactory.create(tmp63_AST)).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(FUTR)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT)).add(iff1np_tf1_AST))).add(iff1np_t1_AST))).add(tf1np_AST).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(FUTR)).add(iff1np_tf1_AST).add(iff1np_t1_AST))));	
													
												currentAST.root = trioformula_AST;
												currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
													trioformula_AST.getFirstChild() : trioformula_AST;
												currentAST.advanceChildToEnd();
											}
										}
										else {
											boolean synPredMatched561 = false;
											if (((_t.getType()==IFF_ROOT))) {
												AST __t561 = _t;
												synPredMatched561 = true;
												inputState.guessing++;
												try {
													{
													AST __t558 = _t;
													ASTPair __currentAST558 = currentAST.copy();
													currentAST.root = currentAST.child;
													currentAST.child = null;
													match(_t,IFF_ROOT);
													_t = _t.getFirstChild();
													AST __t559 = _t;
													ASTPair __currentAST559 = currentAST.copy();
													currentAST.root = currentAST.child;
													currentAST.child = null;
													match(_t,LASTSIE);
													_t = _t.getFirstChild();
													trioformula(_t);
													_t = _retTree;
													term(_t);
													_t = _retTree;
													currentAST = __currentAST559;
													_t = __t559;
													_t = _t.getNextSibling();
													AST __t560 = _t;
													ASTPair __currentAST560 = currentAST.copy();
													currentAST.root = currentAST.child;
													currentAST.child = null;
													match(_t,FUTR);
													_t = _t.getFirstChild();
													trioformula(_t);
													_t = _retTree;
													term(_t);
													_t = _retTree;
													currentAST = __currentAST560;
													_t = __t560;
													_t = _t.getNextSibling();
													currentAST = __currentAST558;
													_t = __t558;
													_t = _t.getNextSibling();
													}
												}
												catch (RecognitionException pe) {
													synPredMatched561 = false;
												}
												_t = __t561;
inputState.guessing--;
											}
											if ( synPredMatched561 ) {
												AST __t562 = _t;
												AST tmp64_AST = null;
												AST tmp64_AST_in = null;
												tmp64_AST = astFactory.create((AST)_t);
												tmp64_AST_in = (AST)_t;
												ASTPair __currentAST562 = currentAST.copy();
												currentAST.root = currentAST.child;
												currentAST.child = null;
												match(_t,IFF_ROOT);
												_t = _t.getFirstChild();
												AST __t563 = _t;
												iff_futr_lastsie_lastsie = _t==ASTNULL ? null :(AST)_t;
												AST iff_futr_lastsie_lastsie_AST_in = null;
												iff_futr_lastsie_lastsie_AST = astFactory.create(iff_futr_lastsie_lastsie);
												ASTPair __currentAST563 = currentAST.copy();
												currentAST.root = currentAST.child;
												currentAST.child = null;
												match(_t,LASTSIE);
												_t = _t.getFirstChild();
												iff_futr_lastsie_tf1 = _t==ASTNULL ? null : (AST)_t;
												trioformula(_t);
												_t = _retTree;
												iff_futr_lastsie_tf1_AST = (AST)returnAST;
												iff_futr_lastsie_t1 = _t==ASTNULL ? null : (AST)_t;
												term(_t);
												_t = _retTree;
												iff_futr_lastsie_t1_AST = (AST)returnAST;
												currentAST = __currentAST563;
												_t = __t563;
												_t = _t.getNextSibling();
												AST __t564 = _t;
												iff_futr_lastsie_futr = _t==ASTNULL ? null :(AST)_t;
												AST iff_futr_lastsie_futr_AST_in = null;
												iff_futr_lastsie_futr_AST = astFactory.create(iff_futr_lastsie_futr);
												ASTPair __currentAST564 = currentAST.copy();
												currentAST.root = currentAST.child;
												currentAST.child = null;
												match(_t,FUTR);
												_t = _t.getFirstChild();
												iff_futr_lastsie_tf2 = _t==ASTNULL ? null : (AST)_t;
												trioformula(_t);
												_t = _retTree;
												iff_futr_lastsie_tf2_AST = (AST)returnAST;
												iff_futr_lastsie_t2 = _t==ASTNULL ? null : (AST)_t;
												term(_t);
												_t = _retTree;
												iff_futr_lastsie_t2_AST = (AST)returnAST;
												currentAST = __currentAST564;
												_t = __t564;
												_t = _t.getNextSibling();
												currentAST = __currentAST562;
												_t = __t562;
												_t = _t.getNextSibling();
												if ( inputState.guessing==0 ) {
													trioformula_AST = (AST)currentAST.root;
													
															trioformula_AST=
													(AST)astFactory.make( (new ASTArray(5)).add(astFactory.create(tmp64_AST)).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(LASTSIE,"LASTSIE")).add(iff_futr_lastsie_tf1_AST).add(iff_futr_lastsie_t1_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(FUTR,"FUTR")).add(iff_futr_lastsie_tf2_AST).add(iff_futr_lastsie_t2_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(WITHINFIE,"WITHINFIE")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT,"NOT")).add(iff_futr_lastsie_tf1_AST))).add(iff_futr_lastsie_t1_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(FUTR,"FUTR")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT,"NOT")).add(iff_futr_lastsie_tf2_AST))).add(iff_futr_lastsie_t2_AST))));		
														
													currentAST.root = trioformula_AST;
													currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
														trioformula_AST.getFirstChild() : trioformula_AST;
													currentAST.advanceChildToEnd();
												}
											}
											else {
												boolean synPredMatched568 = false;
												if (((_t.getType()==IFF_ROOT))) {
													AST __t568 = _t;
													synPredMatched568 = true;
													inputState.guessing++;
													try {
														{
														AST __t566 = _t;
														ASTPair __currentAST566 = currentAST.copy();
														currentAST.root = currentAST.child;
														currentAST.child = null;
														match(_t,IFF_ROOT);
														_t = _t.getFirstChild();
														trioformula(_t);
														_t = _retTree;
														AST __t567 = _t;
														ASTPair __currentAST567 = currentAST.copy();
														currentAST.root = currentAST.child;
														currentAST.child = null;
														match(_t,FUTR);
														_t = _t.getFirstChild();
														trioformula(_t);
														_t = _retTree;
														term(_t);
														_t = _retTree;
														currentAST = __currentAST567;
														_t = __t567;
														_t = _t.getNextSibling();
														currentAST = __currentAST566;
														_t = __t566;
														_t = _t.getNextSibling();
														}
													}
													catch (RecognitionException pe) {
														synPredMatched568 = false;
													}
													_t = __t568;
inputState.guessing--;
												}
												if ( synPredMatched568 ) {
													AST __t569 = _t;
													AST tmp65_AST = null;
													AST tmp65_AST_in = null;
													tmp65_AST = astFactory.create((AST)_t);
													tmp65_AST_in = (AST)_t;
													ASTPair __currentAST569 = currentAST.copy();
													currentAST.root = currentAST.child;
													currentAST.child = null;
													match(_t,IFF_ROOT);
													_t = _t.getFirstChild();
													tf2 = _t==ASTNULL ? null : (AST)_t;
													trioformula(_t);
													_t = _retTree;
													tf2_AST = (AST)returnAST;
													AST __t570 = _t;
													futr2 = _t==ASTNULL ? null :(AST)_t;
													AST futr2_AST_in = null;
													futr2_AST = astFactory.create(futr2);
													ASTPair __currentAST570 = currentAST.copy();
													currentAST.root = currentAST.child;
													currentAST.child = null;
													match(_t,FUTR);
													_t = _t.getFirstChild();
													iff2_tf1 = _t==ASTNULL ? null : (AST)_t;
													trioformula(_t);
													_t = _retTree;
													iff2_tf1_AST = (AST)returnAST;
													iff2_t1 = _t==ASTNULL ? null : (AST)_t;
													term(_t);
													_t = _retTree;
													iff2_t1_AST = (AST)returnAST;
													currentAST = __currentAST570;
													_t = __t570;
													_t = _t.getNextSibling();
													currentAST = __currentAST569;
													_t = __t569;
													_t = _t.getNextSibling();
													if ( inputState.guessing==0 ) {
														trioformula_AST = (AST)currentAST.root;
														
																trioformula_AST=
														(AST)astFactory.make( (new ASTArray(4)).add(astFactory.create(tmp65_AST)).add(tf2_AST).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(FUTR)).add(iff2_tf1_AST).add(iff2_t1_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(FUTR)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT)).add(iff2_tf1_AST))).add(iff2_t1_AST))));	
															
														currentAST.root = trioformula_AST;
														currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
															trioformula_AST.getFirstChild() : trioformula_AST;
														currentAST.advanceChildToEnd();
													}
												}
												else {
													boolean synPredMatched575 = false;
													if (((_t.getType()==IFF_ROOT))) {
														AST __t575 = _t;
														synPredMatched575 = true;
														inputState.guessing++;
														try {
															{
															AST __t572 = _t;
															ASTPair __currentAST572 = currentAST.copy();
															currentAST.root = currentAST.child;
															currentAST.child = null;
															match(_t,IFF_ROOT);
															_t = _t.getFirstChild();
															trioformula(_t);
															_t = _retTree;
															AST __t573 = _t;
															ASTPair __currentAST573 = currentAST.copy();
															currentAST.root = currentAST.child;
															currentAST.child = null;
															match(_t,NOT);
															_t = _t.getFirstChild();
															AST __t574 = _t;
															ASTPair __currentAST574 = currentAST.copy();
															currentAST.root = currentAST.child;
															currentAST.child = null;
															match(_t,FUTR);
															_t = _t.getFirstChild();
															trioformula(_t);
															_t = _retTree;
															term(_t);
															_t = _retTree;
															currentAST = __currentAST574;
															_t = __t574;
															_t = _t.getNextSibling();
															currentAST = __currentAST573;
															_t = __t573;
															_t = _t.getNextSibling();
															currentAST = __currentAST572;
															_t = __t572;
															_t = _t.getNextSibling();
															}
														}
														catch (RecognitionException pe) {
															synPredMatched575 = false;
														}
														_t = __t575;
inputState.guessing--;
													}
													if ( synPredMatched575 ) {
														AST __t576 = _t;
														AST tmp66_AST = null;
														AST tmp66_AST_in = null;
														tmp66_AST = astFactory.create((AST)_t);
														tmp66_AST_in = (AST)_t;
														ASTPair __currentAST576 = currentAST.copy();
														currentAST.root = currentAST.child;
														currentAST.child = null;
														match(_t,IFF_ROOT);
														_t = _t.getFirstChild();
														tf2pn = _t==ASTNULL ? null : (AST)_t;
														trioformula(_t);
														_t = _retTree;
														tf2pn_AST = (AST)returnAST;
														AST __t577 = _t;
														AST tmp67_AST = null;
														AST tmp67_AST_in = null;
														tmp67_AST = astFactory.create((AST)_t);
														tmp67_AST_in = (AST)_t;
														ASTPair __currentAST577 = currentAST.copy();
														currentAST.root = currentAST.child;
														currentAST.child = null;
														match(_t,NOT);
														_t = _t.getFirstChild();
														AST __t578 = _t;
														futr2pn = _t==ASTNULL ? null :(AST)_t;
														AST futr2pn_AST_in = null;
														futr2pn_AST = astFactory.create(futr2pn);
														ASTPair __currentAST578 = currentAST.copy();
														currentAST.root = currentAST.child;
														currentAST.child = null;
														match(_t,FUTR);
														_t = _t.getFirstChild();
														iff2pn_tf1 = _t==ASTNULL ? null : (AST)_t;
														trioformula(_t);
														_t = _retTree;
														iff2pn_tf1_AST = (AST)returnAST;
														iff2pn_t1 = _t==ASTNULL ? null : (AST)_t;
														term(_t);
														_t = _retTree;
														iff2pn_t1_AST = (AST)returnAST;
														currentAST = __currentAST578;
														_t = __t578;
														_t = _t.getNextSibling();
														currentAST = __currentAST577;
														_t = __t577;
														_t = _t.getNextSibling();
														currentAST = __currentAST576;
														_t = __t576;
														_t = _t.getNextSibling();
														if ( inputState.guessing==0 ) {
															trioformula_AST = (AST)currentAST.root;
															
																	trioformula_AST=
															(AST)astFactory.make( (new ASTArray(4)).add(astFactory.create(tmp66_AST)).add(tf2pn_AST).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(FUTR)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(tmp67_AST)).add(iff2pn_tf1_AST))).add(iff2pn_t1_AST))).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(FUTR)).add(iff2pn_tf1_AST).add(iff2pn_t1_AST))));	
																
															currentAST.root = trioformula_AST;
															currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																trioformula_AST.getFirstChild() : trioformula_AST;
															currentAST.advanceChildToEnd();
														}
													}
													else {
														boolean synPredMatched581 = false;
														if (((_t.getType()==IFF_ROOT))) {
															AST __t581 = _t;
															synPredMatched581 = true;
															inputState.guessing++;
															try {
																{
																AST __t580 = _t;
																ASTPair __currentAST580 = currentAST.copy();
																currentAST.root = currentAST.child;
																currentAST.child = null;
																match(_t,IFF_ROOT);
																_t = _t.getFirstChild();
																trioformula(_t);
																_t = _retTree;
																trioformula(_t);
																_t = _retTree;
																currentAST = __currentAST580;
																_t = __t580;
																_t = _t.getNextSibling();
																}
															}
															catch (RecognitionException pe) {
																synPredMatched581 = false;
															}
															_t = __t581;
inputState.guessing--;
														}
														if ( synPredMatched581 ) {
															AST __t582 = _t;
															AST tmp68_AST = null;
															AST tmp68_AST_in = null;
															tmp68_AST = astFactory.create((AST)_t);
															tmp68_AST_in = (AST)_t;
															astFactory.addASTChild(currentAST, tmp68_AST);
															ASTPair __currentAST582 = currentAST.copy();
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
															currentAST = __currentAST582;
															_t = __t582;
															_t = _t.getNextSibling();
															trioformula_AST = (AST)currentAST.root;
														}
														else {
															boolean synPredMatched586 = false;
															if (((_t.getType()==IF))) {
																AST __t586 = _t;
																synPredMatched586 = true;
																inputState.guessing++;
																try {
																	{
																	AST __t584 = _t;
																	ASTPair __currentAST584 = currentAST.copy();
																	currentAST.root = currentAST.child;
																	currentAST.child = null;
																	match(_t,IF);
																	_t = _t.getFirstChild();
																	AST __t585 = _t;
																	ASTPair __currentAST585 = currentAST.copy();
																	currentAST.root = currentAST.child;
																	currentAST.child = null;
																	match(_t,FUTR);
																	_t = _t.getFirstChild();
																	trioformula(_t);
																	_t = _retTree;
																	term(_t);
																	_t = _retTree;
																	currentAST = __currentAST585;
																	_t = __t585;
																	_t = _t.getNextSibling();
																	trioformula(_t);
																	_t = _retTree;
																	currentAST = __currentAST584;
																	_t = __t584;
																	_t = _t.getNextSibling();
																	}
																}
																catch (RecognitionException pe) {
																	synPredMatched586 = false;
																}
																_t = __t586;
inputState.guessing--;
															}
															if ( synPredMatched586 ) {
																AST __t587 = _t;
																AST tmp69_AST = null;
																AST tmp69_AST_in = null;
																tmp69_AST = astFactory.create((AST)_t);
																tmp69_AST_in = (AST)_t;
																ASTPair __currentAST587 = currentAST.copy();
																currentAST.root = currentAST.child;
																currentAST.child = null;
																match(_t,IF);
																_t = _t.getFirstChild();
																AST __t588 = _t;
																AST tmp70_AST = null;
																AST tmp70_AST_in = null;
																tmp70_AST = astFactory.create((AST)_t);
																tmp70_AST_in = (AST)_t;
																ASTPair __currentAST588 = currentAST.copy();
																currentAST.root = currentAST.child;
																currentAST.child = null;
																match(_t,FUTR);
																_t = _t.getFirstChild();
																if2_futr_tf1 = _t==ASTNULL ? null : (AST)_t;
																trioformula(_t);
																_t = _retTree;
																if2_futr_tf1_AST = (AST)returnAST;
																if2_futr_t = _t==ASTNULL ? null : (AST)_t;
																term(_t);
																_t = _retTree;
																if2_futr_t_AST = (AST)returnAST;
																currentAST = __currentAST588;
																_t = __t588;
																_t = _t.getNextSibling();
																if2_futr_tf2 = _t==ASTNULL ? null : (AST)_t;
																trioformula(_t);
																_t = _retTree;
																if2_futr_tf2_AST = (AST)returnAST;
																currentAST = __currentAST587;
																_t = __t587;
																_t = _t.getNextSibling();
																if ( inputState.guessing==0 ) {
																	trioformula_AST = (AST)currentAST.root;
																	
																			trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(OR,"OR")).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp70_AST)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT,"not")).add(if_futr_tf1_AST))).add(if_futr_t_AST))).add(if_futr_tf2_AST));
																		
																	currentAST.root = trioformula_AST;
																	currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																		trioformula_AST.getFirstChild() : trioformula_AST;
																	currentAST.advanceChildToEnd();
																}
															}
															else if ((_t.getType()==IF)) {
																AST __t589 = _t;
																AST tmp71_AST = null;
																AST tmp71_AST_in = null;
																tmp71_AST = astFactory.create((AST)_t);
																tmp71_AST_in = (AST)_t;
																ASTPair __currentAST589 = currentAST.copy();
																currentAST.root = currentAST.child;
																currentAST.child = null;
																match(_t,IF);
																_t = _t.getFirstChild();
																if2_tf1 = _t==ASTNULL ? null : (AST)_t;
																trioformula(_t);
																_t = _retTree;
																if2_tf1_AST = (AST)returnAST;
																if2_tf2 = _t==ASTNULL ? null : (AST)_t;
																trioformula(_t);
																_t = _retTree;
																if2_tf2_AST = (AST)returnAST;
																currentAST = __currentAST589;
																_t = __t589;
																_t = _t.getNextSibling();
																if ( inputState.guessing==0 ) {
																	trioformula_AST = (AST)currentAST.root;
																	
																			trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(OR,"OR")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT,"NOT")).add(if2_tf1_AST))).add(if2_tf2_AST));
																		
																	currentAST.root = trioformula_AST;
																	currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																		trioformula_AST.getFirstChild() : trioformula_AST;
																	currentAST.advanceChildToEnd();
																}
															}
															else {
																boolean synPredMatched593 = false;
																if (((_t.getType()==IF_ROOT))) {
																	AST __t593 = _t;
																	synPredMatched593 = true;
																	inputState.guessing++;
																	try {
																		{
																		AST __t591 = _t;
																		ASTPair __currentAST591 = currentAST.copy();
																		currentAST.root = currentAST.child;
																		currentAST.child = null;
																		match(_t,IF_ROOT);
																		_t = _t.getFirstChild();
																		AST __t592 = _t;
																		ASTPair __currentAST592 = currentAST.copy();
																		currentAST.root = currentAST.child;
																		currentAST.child = null;
																		match(_t,FUTR);
																		_t = _t.getFirstChild();
																		trioformula(_t);
																		_t = _retTree;
																		term(_t);
																		_t = _retTree;
																		currentAST = __currentAST592;
																		_t = __t592;
																		_t = _t.getNextSibling();
																		trioformula(_t);
																		_t = _retTree;
																		currentAST = __currentAST591;
																		_t = __t591;
																		_t = _t.getNextSibling();
																		}
																	}
																	catch (RecognitionException pe) {
																		synPredMatched593 = false;
																	}
																	_t = __t593;
inputState.guessing--;
																}
																if ( synPredMatched593 ) {
																	AST __t594 = _t;
																	AST tmp72_AST = null;
																	AST tmp72_AST_in = null;
																	tmp72_AST = astFactory.create((AST)_t);
																	tmp72_AST_in = (AST)_t;
																	ASTPair __currentAST594 = currentAST.copy();
																	currentAST.root = currentAST.child;
																	currentAST.child = null;
																	match(_t,IF_ROOT);
																	_t = _t.getFirstChild();
																	AST __t595 = _t;
																	AST tmp73_AST = null;
																	AST tmp73_AST_in = null;
																	tmp73_AST = astFactory.create((AST)_t);
																	tmp73_AST_in = (AST)_t;
																	ASTPair __currentAST595 = currentAST.copy();
																	currentAST.root = currentAST.child;
																	currentAST.child = null;
																	match(_t,FUTR);
																	_t = _t.getFirstChild();
																	if_futr_tf1 = _t==ASTNULL ? null : (AST)_t;
																	trioformula(_t);
																	_t = _retTree;
																	if_futr_tf1_AST = (AST)returnAST;
																	if_futr_t = _t==ASTNULL ? null : (AST)_t;
																	term(_t);
																	_t = _retTree;
																	if_futr_t_AST = (AST)returnAST;
																	currentAST = __currentAST595;
																	_t = __t595;
																	_t = _t.getNextSibling();
																	if_futr_tf2 = _t==ASTNULL ? null : (AST)_t;
																	trioformula(_t);
																	_t = _retTree;
																	if_futr_tf2_AST = (AST)returnAST;
																	currentAST = __currentAST594;
																	_t = __t594;
																	_t = _t.getNextSibling();
																	if ( inputState.guessing==0 ) {
																		trioformula_AST = (AST)currentAST.root;
																		
																				trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(OR_ROOT,"OR_ROOT")).add((AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(tmp73_AST)).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT,"not")).add(if_futr_tf1_AST))).add(if_futr_t_AST))).add(if_futr_tf2_AST));
																			
																		currentAST.root = trioformula_AST;
																		currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																			trioformula_AST.getFirstChild() : trioformula_AST;
																		currentAST.advanceChildToEnd();
																	}
																}
																else if ((_t.getType()==IF_ROOT)) {
																	AST __t596 = _t;
																	AST tmp74_AST = null;
																	AST tmp74_AST_in = null;
																	tmp74_AST = astFactory.create((AST)_t);
																	tmp74_AST_in = (AST)_t;
																	ASTPair __currentAST596 = currentAST.copy();
																	currentAST.root = currentAST.child;
																	currentAST.child = null;
																	match(_t,IF_ROOT);
																	_t = _t.getFirstChild();
																	if_tf1 = _t==ASTNULL ? null : (AST)_t;
																	trioformula(_t);
																	_t = _retTree;
																	if_tf1_AST = (AST)returnAST;
																	if_tf2 = _t==ASTNULL ? null : (AST)_t;
																	trioformula(_t);
																	_t = _retTree;
																	if_tf2_AST = (AST)returnAST;
																	currentAST = __currentAST596;
																	_t = __t596;
																	_t = _t.getNextSibling();
																	if ( inputState.guessing==0 ) {
																		trioformula_AST = (AST)currentAST.root;
																		
																				trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(OR_ROOT,"OR_ROOT")).add((AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(NOT,"NOT")).add(if_tf1_AST))).add(if_tf2_AST));
																			
																		currentAST.root = trioformula_AST;
																		currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																			trioformula_AST.getFirstChild() : trioformula_AST;
																		currentAST.advanceChildToEnd();
																	}
																}
																else {
																	boolean synPredMatched633 = false;
																	if (((_t.getType()==FUTR))) {
																		AST __t633 = _t;
																		synPredMatched633 = true;
																		inputState.guessing++;
																		try {
																			{
																			AST __t631 = _t;
																			ASTPair __currentAST631 = currentAST.copy();
																			currentAST.root = currentAST.child;
																			currentAST.child = null;
																			match(_t,FUTR);
																			_t = _t.getFirstChild();
																			AST __t632 = _t;
																			ASTPair __currentAST632 = currentAST.copy();
																			currentAST.root = currentAST.child;
																			currentAST.child = null;
																			match(_t,WITHINFEE);
																			_t = _t.getFirstChild();
																			trioformula(_t);
																			_t = _retTree;
																			term(_t);
																			_t = _retTree;
																			currentAST = __currentAST632;
																			_t = __t632;
																			_t = _t.getNextSibling();
																			term(_t);
																			_t = _retTree;
																			currentAST = __currentAST631;
																			_t = __t631;
																			_t = _t.getNextSibling();
																			}
																		}
																		catch (RecognitionException pe) {
																			synPredMatched633 = false;
																		}
																		_t = __t633;
inputState.guessing--;
																	}
																	if ( synPredMatched633 ) {
																		AST __t634 = _t;
																		AST tmp75_AST = null;
																		AST tmp75_AST_in = null;
																		tmp75_AST = astFactory.create((AST)_t);
																		tmp75_AST_in = (AST)_t;
																		ASTPair __currentAST634 = currentAST.copy();
																		currentAST.root = currentAST.child;
																		currentAST.child = null;
																		match(_t,FUTR);
																		_t = _t.getFirstChild();
																		AST __t635 = _t;
																		AST tmp76_AST = null;
																		AST tmp76_AST_in = null;
																		tmp76_AST = astFactory.create((AST)_t);
																		tmp76_AST_in = (AST)_t;
																		ASTPair __currentAST635 = currentAST.copy();
																		currentAST.root = currentAST.child;
																		currentAST.child = null;
																		match(_t,WITHINFEE);
																		_t = _t.getFirstChild();
																		futr_withinfee_tf = _t==ASTNULL ? null : (AST)_t;
																		trioformula(_t);
																		_t = _retTree;
																		futr_withinfee_tf_AST = (AST)returnAST;
																		futr_withinfee_t1 = _t==ASTNULL ? null : (AST)_t;
																		term(_t);
																		_t = _retTree;
																		futr_withinfee_t1_AST = (AST)returnAST;
																		currentAST = __currentAST635;
																		_t = __t635;
																		_t = _t.getNextSibling();
																		futr_withinfee_t2 = _t==ASTNULL ? null : (AST)_t;
																		term(_t);
																		_t = _retTree;
																		futr_withinfee_t2_AST = (AST)returnAST;
																		currentAST = __currentAST634;
																		_t = __t634;
																		_t = _t.getNextSibling();
																		if ( inputState.guessing==0 ) {
																			trioformula_AST = (AST)currentAST.root;
																			
																			trioformula_AST=(AST)astFactory.make( (new ASTArray(4)).add(tmp76_AST).add(futr_withinfee_tf_AST).add(futr_withinfee_t1_AST).add(futr_withinfee_t2_AST));
																			
																			currentAST.root = trioformula_AST;
																			currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																				trioformula_AST.getFirstChild() : trioformula_AST;
																			currentAST.advanceChildToEnd();
																		}
																	}
																	else {
																		boolean synPredMatched639 = false;
																		if (((_t.getType()==FUTR))) {
																			AST __t639 = _t;
																			synPredMatched639 = true;
																			inputState.guessing++;
																			try {
																				{
																				AST __t637 = _t;
																				ASTPair __currentAST637 = currentAST.copy();
																				currentAST.root = currentAST.child;
																				currentAST.child = null;
																				match(_t,FUTR);
																				_t = _t.getFirstChild();
																				AST __t638 = _t;
																				ASTPair __currentAST638 = currentAST.copy();
																				currentAST.root = currentAST.child;
																				currentAST.child = null;
																				match(_t,WITHINFEI);
																				_t = _t.getFirstChild();
																				trioformula(_t);
																				_t = _retTree;
																				term(_t);
																				_t = _retTree;
																				currentAST = __currentAST638;
																				_t = __t638;
																				_t = _t.getNextSibling();
																				term(_t);
																				_t = _retTree;
																				currentAST = __currentAST637;
																				_t = __t637;
																				_t = _t.getNextSibling();
																				}
																			}
																			catch (RecognitionException pe) {
																				synPredMatched639 = false;
																			}
																			_t = __t639;
inputState.guessing--;
																		}
																		if ( synPredMatched639 ) {
																			AST __t640 = _t;
																			AST tmp77_AST = null;
																			AST tmp77_AST_in = null;
																			tmp77_AST = astFactory.create((AST)_t);
																			tmp77_AST_in = (AST)_t;
																			ASTPair __currentAST640 = currentAST.copy();
																			currentAST.root = currentAST.child;
																			currentAST.child = null;
																			match(_t,FUTR);
																			_t = _t.getFirstChild();
																			AST __t641 = _t;
																			AST tmp78_AST = null;
																			AST tmp78_AST_in = null;
																			tmp78_AST = astFactory.create((AST)_t);
																			tmp78_AST_in = (AST)_t;
																			ASTPair __currentAST641 = currentAST.copy();
																			currentAST.root = currentAST.child;
																			currentAST.child = null;
																			match(_t,WITHINFEI);
																			_t = _t.getFirstChild();
																			futr_withinfei_tf = _t==ASTNULL ? null : (AST)_t;
																			trioformula(_t);
																			_t = _retTree;
																			futr_withinfei_tf_AST = (AST)returnAST;
																			futr_withinfei_t1 = _t==ASTNULL ? null : (AST)_t;
																			term(_t);
																			_t = _retTree;
																			futr_withinfei_t1_AST = (AST)returnAST;
																			currentAST = __currentAST641;
																			_t = __t641;
																			_t = _t.getNextSibling();
																			futr_withinfei_t2 = _t==ASTNULL ? null : (AST)_t;
																			term(_t);
																			_t = _retTree;
																			futr_withinfei_t2_AST = (AST)returnAST;
																			currentAST = __currentAST640;
																			_t = __t640;
																			_t = _t.getNextSibling();
																			if ( inputState.guessing==0 ) {
																				trioformula_AST = (AST)currentAST.root;
																				
																				trioformula_AST=(AST)astFactory.make( (new ASTArray(4)).add(tmp78_AST).add(futr_withinfei_tf_AST).add(futr_withinfei_t1_AST).add(futr_withinfei_t2_AST));
																				
																				currentAST.root = trioformula_AST;
																				currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																					trioformula_AST.getFirstChild() : trioformula_AST;
																				currentAST.advanceChildToEnd();
																			}
																		}
																		else {
																			boolean synPredMatched645 = false;
																			if (((_t.getType()==FUTR))) {
																				AST __t645 = _t;
																				synPredMatched645 = true;
																				inputState.guessing++;
																				try {
																					{
																					AST __t643 = _t;
																					ASTPair __currentAST643 = currentAST.copy();
																					currentAST.root = currentAST.child;
																					currentAST.child = null;
																					match(_t,FUTR);
																					_t = _t.getFirstChild();
																					AST __t644 = _t;
																					ASTPair __currentAST644 = currentAST.copy();
																					currentAST.root = currentAST.child;
																					currentAST.child = null;
																					match(_t,WITHINFIE);
																					_t = _t.getFirstChild();
																					trioformula(_t);
																					_t = _retTree;
																					term(_t);
																					_t = _retTree;
																					currentAST = __currentAST644;
																					_t = __t644;
																					_t = _t.getNextSibling();
																					term(_t);
																					_t = _retTree;
																					currentAST = __currentAST643;
																					_t = __t643;
																					_t = _t.getNextSibling();
																					}
																				}
																				catch (RecognitionException pe) {
																					synPredMatched645 = false;
																				}
																				_t = __t645;
inputState.guessing--;
																			}
																			if ( synPredMatched645 ) {
																				AST __t646 = _t;
																				AST tmp79_AST = null;
																				AST tmp79_AST_in = null;
																				tmp79_AST = astFactory.create((AST)_t);
																				tmp79_AST_in = (AST)_t;
																				ASTPair __currentAST646 = currentAST.copy();
																				currentAST.root = currentAST.child;
																				currentAST.child = null;
																				match(_t,FUTR);
																				_t = _t.getFirstChild();
																				AST __t647 = _t;
																				AST tmp80_AST = null;
																				AST tmp80_AST_in = null;
																				tmp80_AST = astFactory.create((AST)_t);
																				tmp80_AST_in = (AST)_t;
																				ASTPair __currentAST647 = currentAST.copy();
																				currentAST.root = currentAST.child;
																				currentAST.child = null;
																				match(_t,WITHINFIE);
																				_t = _t.getFirstChild();
																				futr_withinfie_tf = _t==ASTNULL ? null : (AST)_t;
																				trioformula(_t);
																				_t = _retTree;
																				futr_withinfie_tf_AST = (AST)returnAST;
																				futr_withinfie_t1 = _t==ASTNULL ? null : (AST)_t;
																				term(_t);
																				_t = _retTree;
																				futr_withinfie_t1_AST = (AST)returnAST;
																				currentAST = __currentAST647;
																				_t = __t647;
																				_t = _t.getNextSibling();
																				futr_withinfie_t2 = _t==ASTNULL ? null : (AST)_t;
																				term(_t);
																				_t = _retTree;
																				futr_withinfie_t2_AST = (AST)returnAST;
																				currentAST = __currentAST646;
																				_t = __t646;
																				_t = _t.getNextSibling();
																				if ( inputState.guessing==0 ) {
																					trioformula_AST = (AST)currentAST.root;
																					
																					trioformula_AST=(AST)astFactory.make( (new ASTArray(4)).add(tmp80_AST).add(futr_withinfie_tf_AST).add(futr_withinfie_t1_AST).add(futr_withinfie_t2_AST));
																					
																					currentAST.root = trioformula_AST;
																					currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																						trioformula_AST.getFirstChild() : trioformula_AST;
																					currentAST.advanceChildToEnd();
																				}
																			}
																			else {
																				boolean synPredMatched651 = false;
																				if (((_t.getType()==FUTR))) {
																					AST __t651 = _t;
																					synPredMatched651 = true;
																					inputState.guessing++;
																					try {
																						{
																						AST __t649 = _t;
																						ASTPair __currentAST649 = currentAST.copy();
																						currentAST.root = currentAST.child;
																						currentAST.child = null;
																						match(_t,FUTR);
																						_t = _t.getFirstChild();
																						AST __t650 = _t;
																						ASTPair __currentAST650 = currentAST.copy();
																						currentAST.root = currentAST.child;
																						currentAST.child = null;
																						match(_t,WITHINFII);
																						_t = _t.getFirstChild();
																						trioformula(_t);
																						_t = _retTree;
																						term(_t);
																						_t = _retTree;
																						currentAST = __currentAST650;
																						_t = __t650;
																						_t = _t.getNextSibling();
																						term(_t);
																						_t = _retTree;
																						currentAST = __currentAST649;
																						_t = __t649;
																						_t = _t.getNextSibling();
																						}
																					}
																					catch (RecognitionException pe) {
																						synPredMatched651 = false;
																					}
																					_t = __t651;
inputState.guessing--;
																				}
																				if ( synPredMatched651 ) {
																					AST __t652 = _t;
																					AST tmp81_AST = null;
																					AST tmp81_AST_in = null;
																					tmp81_AST = astFactory.create((AST)_t);
																					tmp81_AST_in = (AST)_t;
																					ASTPair __currentAST652 = currentAST.copy();
																					currentAST.root = currentAST.child;
																					currentAST.child = null;
																					match(_t,FUTR);
																					_t = _t.getFirstChild();
																					AST __t653 = _t;
																					AST tmp82_AST = null;
																					AST tmp82_AST_in = null;
																					tmp82_AST = astFactory.create((AST)_t);
																					tmp82_AST_in = (AST)_t;
																					ASTPair __currentAST653 = currentAST.copy();
																					currentAST.root = currentAST.child;
																					currentAST.child = null;
																					match(_t,WITHINFII);
																					_t = _t.getFirstChild();
																					futr_withinfii_tf = _t==ASTNULL ? null : (AST)_t;
																					trioformula(_t);
																					_t = _retTree;
																					futr_withinfii_tf_AST = (AST)returnAST;
																					futr_withinfii_t1 = _t==ASTNULL ? null : (AST)_t;
																					term(_t);
																					_t = _retTree;
																					futr_withinfii_t1_AST = (AST)returnAST;
																					currentAST = __currentAST653;
																					_t = __t653;
																					_t = _t.getNextSibling();
																					futr_withinfii_t2 = _t==ASTNULL ? null : (AST)_t;
																					term(_t);
																					_t = _retTree;
																					futr_withinfii_t2_AST = (AST)returnAST;
																					currentAST = __currentAST652;
																					_t = __t652;
																					_t = _t.getNextSibling();
																					if ( inputState.guessing==0 ) {
																						trioformula_AST = (AST)currentAST.root;
																						
																						trioformula_AST=(AST)astFactory.make( (new ASTArray(4)).add(tmp82_AST).add(futr_withinfii_tf_AST).add(futr_withinfii_t1_AST).add(futr_withinfii_t2_AST));
																						
																						currentAST.root = trioformula_AST;
																						currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																							trioformula_AST.getFirstChild() : trioformula_AST;
																						currentAST.advanceChildToEnd();
																					}
																				}
																				else if ((_t.getType()==FUTR)) {
																					AST __t654 = _t;
																					AST tmp83_AST = null;
																					AST tmp83_AST_in = null;
																					tmp83_AST = astFactory.create((AST)_t);
																					tmp83_AST_in = (AST)_t;
																					astFactory.addASTChild(currentAST, tmp83_AST);
																					ASTPair __currentAST654 = currentAST.copy();
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
																					currentAST = __currentAST654;
																					_t = __t654;
																					_t = _t.getNextSibling();
																					trioformula_AST = (AST)currentAST.root;
																				}
																				else {
																					boolean synPredMatched658 = false;
																					if (((_t.getType()==PAST))) {
																						AST __t658 = _t;
																						synPredMatched658 = true;
																						inputState.guessing++;
																						try {
																							{
																							AST __t656 = _t;
																							ASTPair __currentAST656 = currentAST.copy();
																							currentAST.root = currentAST.child;
																							currentAST.child = null;
																							match(_t,PAST);
																							_t = _t.getFirstChild();
																							AST __t657 = _t;
																							ASTPair __currentAST657 = currentAST.copy();
																							currentAST.root = currentAST.child;
																							currentAST.child = null;
																							match(_t,WITHINPEE);
																							_t = _t.getFirstChild();
																							trioformula(_t);
																							_t = _retTree;
																							term(_t);
																							_t = _retTree;
																							currentAST = __currentAST657;
																							_t = __t657;
																							_t = _t.getNextSibling();
																							term(_t);
																							_t = _retTree;
																							currentAST = __currentAST656;
																							_t = __t656;
																							_t = _t.getNextSibling();
																							}
																						}
																						catch (RecognitionException pe) {
																							synPredMatched658 = false;
																						}
																						_t = __t658;
inputState.guessing--;
																					}
																					if ( synPredMatched658 ) {
																						AST __t659 = _t;
																						AST tmp84_AST = null;
																						AST tmp84_AST_in = null;
																						tmp84_AST = astFactory.create((AST)_t);
																						tmp84_AST_in = (AST)_t;
																						ASTPair __currentAST659 = currentAST.copy();
																						currentAST.root = currentAST.child;
																						currentAST.child = null;
																						match(_t,PAST);
																						_t = _t.getFirstChild();
																						AST __t660 = _t;
																						AST tmp85_AST = null;
																						AST tmp85_AST_in = null;
																						tmp85_AST = astFactory.create((AST)_t);
																						tmp85_AST_in = (AST)_t;
																						ASTPair __currentAST660 = currentAST.copy();
																						currentAST.root = currentAST.child;
																						currentAST.child = null;
																						match(_t,WITHINPEE);
																						_t = _t.getFirstChild();
																						past_withinpee_tf = _t==ASTNULL ? null : (AST)_t;
																						trioformula(_t);
																						_t = _retTree;
																						past_withinpee_tf_AST = (AST)returnAST;
																						past_withinpee_t1 = _t==ASTNULL ? null : (AST)_t;
																						term(_t);
																						_t = _retTree;
																						past_withinpee_t1_AST = (AST)returnAST;
																						currentAST = __currentAST660;
																						_t = __t660;
																						_t = _t.getNextSibling();
																						past_withinpee_t2 = _t==ASTNULL ? null : (AST)_t;
																						term(_t);
																						_t = _retTree;
																						past_withinpee_t2_AST = (AST)returnAST;
																						currentAST = __currentAST659;
																						_t = __t659;
																						_t = _t.getNextSibling();
																						if ( inputState.guessing==0 ) {
																							trioformula_AST = (AST)currentAST.root;
																							
																							trioformula_AST=(AST)astFactory.make( (new ASTArray(4)).add(tmp85_AST).add(past_withinpee_tf_AST).add(past_withinpee_t1_AST).add(past_withinpee_t2_AST));
																							
																							currentAST.root = trioformula_AST;
																							currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																								trioformula_AST.getFirstChild() : trioformula_AST;
																							currentAST.advanceChildToEnd();
																						}
																					}
																					else {
																						boolean synPredMatched664 = false;
																						if (((_t.getType()==PAST))) {
																							AST __t664 = _t;
																							synPredMatched664 = true;
																							inputState.guessing++;
																							try {
																								{
																								AST __t662 = _t;
																								ASTPair __currentAST662 = currentAST.copy();
																								currentAST.root = currentAST.child;
																								currentAST.child = null;
																								match(_t,PAST);
																								_t = _t.getFirstChild();
																								AST __t663 = _t;
																								ASTPair __currentAST663 = currentAST.copy();
																								currentAST.root = currentAST.child;
																								currentAST.child = null;
																								match(_t,WITHINPEI);
																								_t = _t.getFirstChild();
																								trioformula(_t);
																								_t = _retTree;
																								term(_t);
																								_t = _retTree;
																								currentAST = __currentAST663;
																								_t = __t663;
																								_t = _t.getNextSibling();
																								term(_t);
																								_t = _retTree;
																								currentAST = __currentAST662;
																								_t = __t662;
																								_t = _t.getNextSibling();
																								}
																							}
																							catch (RecognitionException pe) {
																								synPredMatched664 = false;
																							}
																							_t = __t664;
inputState.guessing--;
																						}
																						if ( synPredMatched664 ) {
																							AST __t665 = _t;
																							AST tmp86_AST = null;
																							AST tmp86_AST_in = null;
																							tmp86_AST = astFactory.create((AST)_t);
																							tmp86_AST_in = (AST)_t;
																							ASTPair __currentAST665 = currentAST.copy();
																							currentAST.root = currentAST.child;
																							currentAST.child = null;
																							match(_t,PAST);
																							_t = _t.getFirstChild();
																							AST __t666 = _t;
																							AST tmp87_AST = null;
																							AST tmp87_AST_in = null;
																							tmp87_AST = astFactory.create((AST)_t);
																							tmp87_AST_in = (AST)_t;
																							ASTPair __currentAST666 = currentAST.copy();
																							currentAST.root = currentAST.child;
																							currentAST.child = null;
																							match(_t,WITHINPEI);
																							_t = _t.getFirstChild();
																							past_withinpei_tf = _t==ASTNULL ? null : (AST)_t;
																							trioformula(_t);
																							_t = _retTree;
																							past_withinpei_tf_AST = (AST)returnAST;
																							past_withinpei_t1 = _t==ASTNULL ? null : (AST)_t;
																							term(_t);
																							_t = _retTree;
																							past_withinpei_t1_AST = (AST)returnAST;
																							currentAST = __currentAST666;
																							_t = __t666;
																							_t = _t.getNextSibling();
																							past_withinpei_t2 = _t==ASTNULL ? null : (AST)_t;
																							term(_t);
																							_t = _retTree;
																							past_withinpei_t2_AST = (AST)returnAST;
																							currentAST = __currentAST665;
																							_t = __t665;
																							_t = _t.getNextSibling();
																							if ( inputState.guessing==0 ) {
																								trioformula_AST = (AST)currentAST.root;
																								
																								trioformula_AST=(AST)astFactory.make( (new ASTArray(4)).add(tmp87_AST).add(past_withinpei_tf_AST).add(past_withinpei_t1_AST).add(past_withinpei_t2_AST));
																								
																								
																								currentAST.root = trioformula_AST;
																								currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																									trioformula_AST.getFirstChild() : trioformula_AST;
																								currentAST.advanceChildToEnd();
																							}
																						}
																						else {
																							boolean synPredMatched670 = false;
																							if (((_t.getType()==PAST))) {
																								AST __t670 = _t;
																								synPredMatched670 = true;
																								inputState.guessing++;
																								try {
																									{
																									AST __t668 = _t;
																									ASTPair __currentAST668 = currentAST.copy();
																									currentAST.root = currentAST.child;
																									currentAST.child = null;
																									match(_t,PAST);
																									_t = _t.getFirstChild();
																									AST __t669 = _t;
																									ASTPair __currentAST669 = currentAST.copy();
																									currentAST.root = currentAST.child;
																									currentAST.child = null;
																									match(_t,WITHINPIE);
																									_t = _t.getFirstChild();
																									trioformula(_t);
																									_t = _retTree;
																									term(_t);
																									_t = _retTree;
																									currentAST = __currentAST669;
																									_t = __t669;
																									_t = _t.getNextSibling();
																									term(_t);
																									_t = _retTree;
																									currentAST = __currentAST668;
																									_t = __t668;
																									_t = _t.getNextSibling();
																									}
																								}
																								catch (RecognitionException pe) {
																									synPredMatched670 = false;
																								}
																								_t = __t670;
inputState.guessing--;
																							}
																							if ( synPredMatched670 ) {
																								AST __t671 = _t;
																								AST tmp88_AST = null;
																								AST tmp88_AST_in = null;
																								tmp88_AST = astFactory.create((AST)_t);
																								tmp88_AST_in = (AST)_t;
																								ASTPair __currentAST671 = currentAST.copy();
																								currentAST.root = currentAST.child;
																								currentAST.child = null;
																								match(_t,PAST);
																								_t = _t.getFirstChild();
																								AST __t672 = _t;
																								AST tmp89_AST = null;
																								AST tmp89_AST_in = null;
																								tmp89_AST = astFactory.create((AST)_t);
																								tmp89_AST_in = (AST)_t;
																								ASTPair __currentAST672 = currentAST.copy();
																								currentAST.root = currentAST.child;
																								currentAST.child = null;
																								match(_t,WITHINPIE);
																								_t = _t.getFirstChild();
																								past_withinpie_tf = _t==ASTNULL ? null : (AST)_t;
																								trioformula(_t);
																								_t = _retTree;
																								past_withinpie_tf_AST = (AST)returnAST;
																								past_withinpie_t1 = _t==ASTNULL ? null : (AST)_t;
																								term(_t);
																								_t = _retTree;
																								past_withinpie_t1_AST = (AST)returnAST;
																								currentAST = __currentAST672;
																								_t = __t672;
																								_t = _t.getNextSibling();
																								past_withinpie_t2 = _t==ASTNULL ? null : (AST)_t;
																								term(_t);
																								_t = _retTree;
																								past_withinpie_t2_AST = (AST)returnAST;
																								currentAST = __currentAST671;
																								_t = __t671;
																								_t = _t.getNextSibling();
																								if ( inputState.guessing==0 ) {
																									trioformula_AST = (AST)currentAST.root;
																									
																									trioformula_AST=(AST)astFactory.make( (new ASTArray(4)).add(tmp89_AST).add(past_withinpie_tf_AST).add(past_withinpie_t1_AST).add(past_withinpie_t2_AST));
																									
																									currentAST.root = trioformula_AST;
																									currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																										trioformula_AST.getFirstChild() : trioformula_AST;
																									currentAST.advanceChildToEnd();
																								}
																							}
																							else {
																								boolean synPredMatched676 = false;
																								if (((_t.getType()==PAST))) {
																									AST __t676 = _t;
																									synPredMatched676 = true;
																									inputState.guessing++;
																									try {
																										{
																										AST __t674 = _t;
																										ASTPair __currentAST674 = currentAST.copy();
																										currentAST.root = currentAST.child;
																										currentAST.child = null;
																										match(_t,PAST);
																										_t = _t.getFirstChild();
																										AST __t675 = _t;
																										ASTPair __currentAST675 = currentAST.copy();
																										currentAST.root = currentAST.child;
																										currentAST.child = null;
																										match(_t,WITHINPII);
																										_t = _t.getFirstChild();
																										trioformula(_t);
																										_t = _retTree;
																										term(_t);
																										_t = _retTree;
																										currentAST = __currentAST675;
																										_t = __t675;
																										_t = _t.getNextSibling();
																										term(_t);
																										_t = _retTree;
																										currentAST = __currentAST674;
																										_t = __t674;
																										_t = _t.getNextSibling();
																										}
																									}
																									catch (RecognitionException pe) {
																										synPredMatched676 = false;
																									}
																									_t = __t676;
inputState.guessing--;
																								}
																								if ( synPredMatched676 ) {
																									AST __t677 = _t;
																									AST tmp90_AST = null;
																									AST tmp90_AST_in = null;
																									tmp90_AST = astFactory.create((AST)_t);
																									tmp90_AST_in = (AST)_t;
																									ASTPair __currentAST677 = currentAST.copy();
																									currentAST.root = currentAST.child;
																									currentAST.child = null;
																									match(_t,PAST);
																									_t = _t.getFirstChild();
																									AST __t678 = _t;
																									AST tmp91_AST = null;
																									AST tmp91_AST_in = null;
																									tmp91_AST = astFactory.create((AST)_t);
																									tmp91_AST_in = (AST)_t;
																									ASTPair __currentAST678 = currentAST.copy();
																									currentAST.root = currentAST.child;
																									currentAST.child = null;
																									match(_t,WITHINPII);
																									_t = _t.getFirstChild();
																									past_withinpii_tf = _t==ASTNULL ? null : (AST)_t;
																									trioformula(_t);
																									_t = _retTree;
																									past_withinpii_tf_AST = (AST)returnAST;
																									past_withinpii_t1 = _t==ASTNULL ? null : (AST)_t;
																									term(_t);
																									_t = _retTree;
																									past_withinpii_t1_AST = (AST)returnAST;
																									currentAST = __currentAST678;
																									_t = __t678;
																									_t = _t.getNextSibling();
																									past_withinpii_t2 = _t==ASTNULL ? null : (AST)_t;
																									term(_t);
																									_t = _retTree;
																									past_withinpii_t2_AST = (AST)returnAST;
																									currentAST = __currentAST677;
																									_t = __t677;
																									_t = _t.getNextSibling();
																									if ( inputState.guessing==0 ) {
																										trioformula_AST = (AST)currentAST.root;
																										
																										trioformula_AST=(AST)astFactory.make( (new ASTArray(4)).add(tmp91_AST).add(past_withinpii_tf_AST).add(past_withinpii_t1_AST).add(past_withinpii_t2_AST));
																										
																										currentAST.root = trioformula_AST;
																										currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																											trioformula_AST.getFirstChild() : trioformula_AST;
																										currentAST.advanceChildToEnd();
																									}
																								}
																								else if ((_t.getType()==PAST)) {
																									AST __t679 = _t;
																									AST tmp92_AST = null;
																									AST tmp92_AST_in = null;
																									tmp92_AST = astFactory.create((AST)_t);
																									tmp92_AST_in = (AST)_t;
																									astFactory.addASTChild(currentAST, tmp92_AST);
																									ASTPair __currentAST679 = currentAST.copy();
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
																									currentAST = __currentAST679;
																									_t = __t679;
																									_t = _t.getNextSibling();
																									trioformula_AST = (AST)currentAST.root;
																								}
																								else {
																									boolean synPredMatched684 = false;
																									if (((_t.getType()==LASTSEE))) {
																										AST __t684 = _t;
																										synPredMatched684 = true;
																										inputState.guessing++;
																										try {
																											{
																											AST __t682 = _t;
																											ASTPair __currentAST682 = currentAST.copy();
																											currentAST.root = currentAST.child;
																											currentAST.child = null;
																											match(_t,LASTSEE);
																											_t = _t.getFirstChild();
																											AST __t683 = _t;
																											ASTPair __currentAST683 = currentAST.copy();
																											currentAST.root = currentAST.child;
																											currentAST.child = null;
																											match(_t,FUTR);
																											_t = _t.getFirstChild();
																											trioformula(_t);
																											_t = _retTree;
																											term(_t);
																											_t = _retTree;
																											currentAST = __currentAST683;
																											_t = __t683;
																											_t = _t.getNextSibling();
																											term(_t);
																											_t = _retTree;
																											currentAST = __currentAST682;
																											_t = __t682;
																											_t = _t.getNextSibling();
																											}
																										}
																										catch (RecognitionException pe) {
																											synPredMatched684 = false;
																										}
																										_t = __t684;
inputState.guessing--;
																									}
																									if ( synPredMatched684 ) {
																										AST __t685 = _t;
																										AST tmp93_AST = null;
																										AST tmp93_AST_in = null;
																										tmp93_AST = astFactory.create((AST)_t);
																										tmp93_AST_in = (AST)_t;
																										ASTPair __currentAST685 = currentAST.copy();
																										currentAST.root = currentAST.child;
																										currentAST.child = null;
																										match(_t,LASTSEE);
																										_t = _t.getFirstChild();
																										AST __t686 = _t;
																										AST tmp94_AST = null;
																										AST tmp94_AST_in = null;
																										tmp94_AST = astFactory.create((AST)_t);
																										tmp94_AST_in = (AST)_t;
																										ASTPair __currentAST686 = currentAST.copy();
																										currentAST.root = currentAST.child;
																										currentAST.child = null;
																										match(_t,FUTR);
																										_t = _t.getFirstChild();
																										tf_ee = _t==ASTNULL ? null : (AST)_t;
																										trioformula(_t);
																										_t = _retTree;
																										tf_ee_AST = (AST)returnAST;
																										t1_ee = _t==ASTNULL ? null : (AST)_t;
																										term(_t);
																										_t = _retTree;
																										t1_ee_AST = (AST)returnAST;
																										currentAST = __currentAST686;
																										_t = __t686;
																										_t = _t.getNextSibling();
																										t2_ee = _t==ASTNULL ? null : (AST)_t;
																										term(_t);
																										_t = _retTree;
																										t2_ee_AST = (AST)returnAST;
																										currentAST = __currentAST685;
																										_t = __t685;
																										_t = _t.getNextSibling();
																										if ( inputState.guessing==0 ) {
																											trioformula_AST = (AST)currentAST.root;
																											
																											trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(tmp94_AST).add((AST)astFactory.make( (new ASTArray(3)).add(tmp93_AST).add(tf_ee_AST).add(t2_ee_AST))).add(t1_ee_AST));
																											
																											currentAST.root = trioformula_AST;
																											currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																												trioformula_AST.getFirstChild() : trioformula_AST;
																											currentAST.advanceChildToEnd();
																										}
																									}
																									else if ((_t.getType()==LASTSEE)) {
																										AST __t687 = _t;
																										AST tmp95_AST = null;
																										AST tmp95_AST_in = null;
																										tmp95_AST = astFactory.create((AST)_t);
																										tmp95_AST_in = (AST)_t;
																										astFactory.addASTChild(currentAST, tmp95_AST);
																										ASTPair __currentAST687 = currentAST.copy();
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
																										currentAST = __currentAST687;
																										_t = __t687;
																										_t = _t.getNextSibling();
																										trioformula_AST = (AST)currentAST.root;
																									}
																									else {
																										boolean synPredMatched691 = false;
																										if (((_t.getType()==LASTSEI))) {
																											AST __t691 = _t;
																											synPredMatched691 = true;
																											inputState.guessing++;
																											try {
																												{
																												AST __t689 = _t;
																												ASTPair __currentAST689 = currentAST.copy();
																												currentAST.root = currentAST.child;
																												currentAST.child = null;
																												match(_t,LASTSEI);
																												_t = _t.getFirstChild();
																												AST __t690 = _t;
																												ASTPair __currentAST690 = currentAST.copy();
																												currentAST.root = currentAST.child;
																												currentAST.child = null;
																												match(_t,FUTR);
																												_t = _t.getFirstChild();
																												trioformula(_t);
																												_t = _retTree;
																												term(_t);
																												_t = _retTree;
																												currentAST = __currentAST690;
																												_t = __t690;
																												_t = _t.getNextSibling();
																												term(_t);
																												_t = _retTree;
																												currentAST = __currentAST689;
																												_t = __t689;
																												_t = _t.getNextSibling();
																												}
																											}
																											catch (RecognitionException pe) {
																												synPredMatched691 = false;
																											}
																											_t = __t691;
inputState.guessing--;
																										}
																										if ( synPredMatched691 ) {
																											AST __t692 = _t;
																											AST tmp96_AST = null;
																											AST tmp96_AST_in = null;
																											tmp96_AST = astFactory.create((AST)_t);
																											tmp96_AST_in = (AST)_t;
																											ASTPair __currentAST692 = currentAST.copy();
																											currentAST.root = currentAST.child;
																											currentAST.child = null;
																											match(_t,LASTSEI);
																											_t = _t.getFirstChild();
																											AST __t693 = _t;
																											AST tmp97_AST = null;
																											AST tmp97_AST_in = null;
																											tmp97_AST = astFactory.create((AST)_t);
																											tmp97_AST_in = (AST)_t;
																											ASTPair __currentAST693 = currentAST.copy();
																											currentAST.root = currentAST.child;
																											currentAST.child = null;
																											match(_t,FUTR);
																											_t = _t.getFirstChild();
																											tf_ei = _t==ASTNULL ? null : (AST)_t;
																											trioformula(_t);
																											_t = _retTree;
																											tf_ei_AST = (AST)returnAST;
																											t1_ei = _t==ASTNULL ? null : (AST)_t;
																											term(_t);
																											_t = _retTree;
																											t1_ei_AST = (AST)returnAST;
																											currentAST = __currentAST693;
																											_t = __t693;
																											_t = _t.getNextSibling();
																											t2_ei = _t==ASTNULL ? null : (AST)_t;
																											term(_t);
																											_t = _retTree;
																											t2_ei_AST = (AST)returnAST;
																											currentAST = __currentAST692;
																											_t = __t692;
																											_t = _t.getNextSibling();
																											if ( inputState.guessing==0 ) {
																												trioformula_AST = (AST)currentAST.root;
																												
																												trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(tmp97_AST).add((AST)astFactory.make( (new ASTArray(3)).add(tmp96_AST).add(tf_ei_AST).add(t2_ei_AST))).add(t1_ei_AST));
																												
																												currentAST.root = trioformula_AST;
																												currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																													trioformula_AST.getFirstChild() : trioformula_AST;
																												currentAST.advanceChildToEnd();
																											}
																										}
																										else if ((_t.getType()==LASTSEI)) {
																											AST __t694 = _t;
																											AST tmp98_AST = null;
																											AST tmp98_AST_in = null;
																											tmp98_AST = astFactory.create((AST)_t);
																											tmp98_AST_in = (AST)_t;
																											astFactory.addASTChild(currentAST, tmp98_AST);
																											ASTPair __currentAST694 = currentAST.copy();
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
																											currentAST = __currentAST694;
																											_t = __t694;
																											_t = _t.getNextSibling();
																											trioformula_AST = (AST)currentAST.root;
																										}
																										else {
																											boolean synPredMatched698 = false;
																											if (((_t.getType()==LASTSIE))) {
																												AST __t698 = _t;
																												synPredMatched698 = true;
																												inputState.guessing++;
																												try {
																													{
																													AST __t696 = _t;
																													ASTPair __currentAST696 = currentAST.copy();
																													currentAST.root = currentAST.child;
																													currentAST.child = null;
																													match(_t,LASTSIE);
																													_t = _t.getFirstChild();
																													AST __t697 = _t;
																													ASTPair __currentAST697 = currentAST.copy();
																													currentAST.root = currentAST.child;
																													currentAST.child = null;
																													match(_t,FUTR);
																													_t = _t.getFirstChild();
																													trioformula(_t);
																													_t = _retTree;
																													term(_t);
																													_t = _retTree;
																													currentAST = __currentAST697;
																													_t = __t697;
																													_t = _t.getNextSibling();
																													term(_t);
																													_t = _retTree;
																													currentAST = __currentAST696;
																													_t = __t696;
																													_t = _t.getNextSibling();
																													}
																												}
																												catch (RecognitionException pe) {
																													synPredMatched698 = false;
																												}
																												_t = __t698;
inputState.guessing--;
																											}
																											if ( synPredMatched698 ) {
																												AST __t699 = _t;
																												AST tmp99_AST = null;
																												AST tmp99_AST_in = null;
																												tmp99_AST = astFactory.create((AST)_t);
																												tmp99_AST_in = (AST)_t;
																												ASTPair __currentAST699 = currentAST.copy();
																												currentAST.root = currentAST.child;
																												currentAST.child = null;
																												match(_t,LASTSIE);
																												_t = _t.getFirstChild();
																												AST __t700 = _t;
																												AST tmp100_AST = null;
																												AST tmp100_AST_in = null;
																												tmp100_AST = astFactory.create((AST)_t);
																												tmp100_AST_in = (AST)_t;
																												ASTPair __currentAST700 = currentAST.copy();
																												currentAST.root = currentAST.child;
																												currentAST.child = null;
																												match(_t,FUTR);
																												_t = _t.getFirstChild();
																												tf_ie = _t==ASTNULL ? null : (AST)_t;
																												trioformula(_t);
																												_t = _retTree;
																												tf_ie_AST = (AST)returnAST;
																												t1_ie = _t==ASTNULL ? null : (AST)_t;
																												term(_t);
																												_t = _retTree;
																												t1_ie_AST = (AST)returnAST;
																												currentAST = __currentAST700;
																												_t = __t700;
																												_t = _t.getNextSibling();
																												t2_ie = _t==ASTNULL ? null : (AST)_t;
																												term(_t);
																												_t = _retTree;
																												t2_ie_AST = (AST)returnAST;
																												currentAST = __currentAST699;
																												_t = __t699;
																												_t = _t.getNextSibling();
																												if ( inputState.guessing==0 ) {
																													trioformula_AST = (AST)currentAST.root;
																													
																													trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(tmp100_AST).add((AST)astFactory.make( (new ASTArray(3)).add(tmp99_AST).add(tf_ie_AST).add(t2_ie_AST))).add(t1_ie_AST));
																													
																													currentAST.root = trioformula_AST;
																													currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																														trioformula_AST.getFirstChild() : trioformula_AST;
																													currentAST.advanceChildToEnd();
																												}
																											}
																											else if ((_t.getType()==LASTSIE)) {
																												AST __t701 = _t;
																												AST tmp101_AST = null;
																												AST tmp101_AST_in = null;
																												tmp101_AST = astFactory.create((AST)_t);
																												tmp101_AST_in = (AST)_t;
																												astFactory.addASTChild(currentAST, tmp101_AST);
																												ASTPair __currentAST701 = currentAST.copy();
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
																												currentAST = __currentAST701;
																												_t = __t701;
																												_t = _t.getNextSibling();
																												trioformula_AST = (AST)currentAST.root;
																											}
																											else {
																												boolean synPredMatched705 = false;
																												if (((_t.getType()==LASTSII))) {
																													AST __t705 = _t;
																													synPredMatched705 = true;
																													inputState.guessing++;
																													try {
																														{
																														AST __t703 = _t;
																														ASTPair __currentAST703 = currentAST.copy();
																														currentAST.root = currentAST.child;
																														currentAST.child = null;
																														match(_t,LASTSII);
																														_t = _t.getFirstChild();
																														AST __t704 = _t;
																														ASTPair __currentAST704 = currentAST.copy();
																														currentAST.root = currentAST.child;
																														currentAST.child = null;
																														match(_t,FUTR);
																														_t = _t.getFirstChild();
																														trioformula(_t);
																														_t = _retTree;
																														term(_t);
																														_t = _retTree;
																														currentAST = __currentAST704;
																														_t = __t704;
																														_t = _t.getNextSibling();
																														term(_t);
																														_t = _retTree;
																														currentAST = __currentAST703;
																														_t = __t703;
																														_t = _t.getNextSibling();
																														}
																													}
																													catch (RecognitionException pe) {
																														synPredMatched705 = false;
																													}
																													_t = __t705;
inputState.guessing--;
																												}
																												if ( synPredMatched705 ) {
																													AST __t706 = _t;
																													AST tmp102_AST = null;
																													AST tmp102_AST_in = null;
																													tmp102_AST = astFactory.create((AST)_t);
																													tmp102_AST_in = (AST)_t;
																													ASTPair __currentAST706 = currentAST.copy();
																													currentAST.root = currentAST.child;
																													currentAST.child = null;
																													match(_t,LASTSII);
																													_t = _t.getFirstChild();
																													AST __t707 = _t;
																													AST tmp103_AST = null;
																													AST tmp103_AST_in = null;
																													tmp103_AST = astFactory.create((AST)_t);
																													tmp103_AST_in = (AST)_t;
																													ASTPair __currentAST707 = currentAST.copy();
																													currentAST.root = currentAST.child;
																													currentAST.child = null;
																													match(_t,FUTR);
																													_t = _t.getFirstChild();
																													tf_ii = _t==ASTNULL ? null : (AST)_t;
																													trioformula(_t);
																													_t = _retTree;
																													tf_ii_AST = (AST)returnAST;
																													t1_ii = _t==ASTNULL ? null : (AST)_t;
																													term(_t);
																													_t = _retTree;
																													t1_ii_AST = (AST)returnAST;
																													currentAST = __currentAST707;
																													_t = __t707;
																													_t = _t.getNextSibling();
																													t2_ii = _t==ASTNULL ? null : (AST)_t;
																													term(_t);
																													_t = _retTree;
																													t2_ii_AST = (AST)returnAST;
																													currentAST = __currentAST706;
																													_t = __t706;
																													_t = _t.getNextSibling();
																													if ( inputState.guessing==0 ) {
																														trioformula_AST = (AST)currentAST.root;
																														
																														trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(tmp103_AST).add((AST)astFactory.make( (new ASTArray(3)).add(tmp102_AST).add(tf_ii_AST).add(t2_ii_AST))).add(t1_ii_AST));
																														
																														currentAST.root = trioformula_AST;
																														currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																															trioformula_AST.getFirstChild() : trioformula_AST;
																														currentAST.advanceChildToEnd();
																													}
																												}
																												else if ((_t.getType()==LASTSII)) {
																													AST __t708 = _t;
																													AST tmp104_AST = null;
																													AST tmp104_AST_in = null;
																													tmp104_AST = astFactory.create((AST)_t);
																													tmp104_AST_in = (AST)_t;
																													astFactory.addASTChild(currentAST, tmp104_AST);
																													ASTPair __currentAST708 = currentAST.copy();
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
																													currentAST = __currentAST708;
																													_t = __t708;
																													_t = _t.getNextSibling();
																													trioformula_AST = (AST)currentAST.root;
																												}
																												else {
																													boolean synPredMatched712 = false;
																													if (((_t.getType()==LASTEDEE))) {
																														AST __t712 = _t;
																														synPredMatched712 = true;
																														inputState.guessing++;
																														try {
																															{
																															AST __t710 = _t;
																															ASTPair __currentAST710 = currentAST.copy();
																															currentAST.root = currentAST.child;
																															currentAST.child = null;
																															match(_t,LASTEDEE);
																															_t = _t.getFirstChild();
																															AST __t711 = _t;
																															ASTPair __currentAST711 = currentAST.copy();
																															currentAST.root = currentAST.child;
																															currentAST.child = null;
																															match(_t,PAST);
																															_t = _t.getFirstChild();
																															trioformula(_t);
																															_t = _retTree;
																															term(_t);
																															_t = _retTree;
																															currentAST = __currentAST711;
																															_t = __t711;
																															_t = _t.getNextSibling();
																															term(_t);
																															_t = _retTree;
																															currentAST = __currentAST710;
																															_t = __t710;
																															_t = _t.getNextSibling();
																															}
																														}
																														catch (RecognitionException pe) {
																															synPredMatched712 = false;
																														}
																														_t = __t712;
inputState.guessing--;
																													}
																													if ( synPredMatched712 ) {
																														AST __t713 = _t;
																														AST tmp105_AST = null;
																														AST tmp105_AST_in = null;
																														tmp105_AST = astFactory.create((AST)_t);
																														tmp105_AST_in = (AST)_t;
																														ASTPair __currentAST713 = currentAST.copy();
																														currentAST.root = currentAST.child;
																														currentAST.child = null;
																														match(_t,LASTEDEE);
																														_t = _t.getFirstChild();
																														AST __t714 = _t;
																														AST tmp106_AST = null;
																														AST tmp106_AST_in = null;
																														tmp106_AST = astFactory.create((AST)_t);
																														tmp106_AST_in = (AST)_t;
																														ASTPair __currentAST714 = currentAST.copy();
																														currentAST.root = currentAST.child;
																														currentAST.child = null;
																														match(_t,PAST);
																														_t = _t.getFirstChild();
																														tf_ee_p = _t==ASTNULL ? null : (AST)_t;
																														trioformula(_t);
																														_t = _retTree;
																														tf_ee_p_AST = (AST)returnAST;
																														t1_ee_p = _t==ASTNULL ? null : (AST)_t;
																														term(_t);
																														_t = _retTree;
																														t1_ee_p_AST = (AST)returnAST;
																														currentAST = __currentAST714;
																														_t = __t714;
																														_t = _t.getNextSibling();
																														t2_ee_p = _t==ASTNULL ? null : (AST)_t;
																														term(_t);
																														_t = _retTree;
																														t2_ee_p_AST = (AST)returnAST;
																														currentAST = __currentAST713;
																														_t = __t713;
																														_t = _t.getNextSibling();
																														if ( inputState.guessing==0 ) {
																															trioformula_AST = (AST)currentAST.root;
																															
																															trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(tmp106_AST).add((AST)astFactory.make( (new ASTArray(3)).add(tmp105_AST).add(tf_ee_p_AST).add(t1_ee_p_AST))).add(t2_ee_p_AST));
																															
																															currentAST.root = trioformula_AST;
																															currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																																trioformula_AST.getFirstChild() : trioformula_AST;
																															currentAST.advanceChildToEnd();
																														}
																													}
																													else if ((_t.getType()==LASTEDEE)) {
																														AST __t715 = _t;
																														AST tmp107_AST = null;
																														AST tmp107_AST_in = null;
																														tmp107_AST = astFactory.create((AST)_t);
																														tmp107_AST_in = (AST)_t;
																														astFactory.addASTChild(currentAST, tmp107_AST);
																														ASTPair __currentAST715 = currentAST.copy();
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
																														currentAST = __currentAST715;
																														_t = __t715;
																														_t = _t.getNextSibling();
																														trioformula_AST = (AST)currentAST.root;
																													}
																													else {
																														boolean synPredMatched719 = false;
																														if (((_t.getType()==LASTEDEI))) {
																															AST __t719 = _t;
																															synPredMatched719 = true;
																															inputState.guessing++;
																															try {
																																{
																																AST __t717 = _t;
																																ASTPair __currentAST717 = currentAST.copy();
																																currentAST.root = currentAST.child;
																																currentAST.child = null;
																																match(_t,LASTEDEI);
																																_t = _t.getFirstChild();
																																AST __t718 = _t;
																																ASTPair __currentAST718 = currentAST.copy();
																																currentAST.root = currentAST.child;
																																currentAST.child = null;
																																match(_t,PAST);
																																_t = _t.getFirstChild();
																																trioformula(_t);
																																_t = _retTree;
																																term(_t);
																																_t = _retTree;
																																currentAST = __currentAST718;
																																_t = __t718;
																																_t = _t.getNextSibling();
																																term(_t);
																																_t = _retTree;
																																currentAST = __currentAST717;
																																_t = __t717;
																																_t = _t.getNextSibling();
																																}
																															}
																															catch (RecognitionException pe) {
																																synPredMatched719 = false;
																															}
																															_t = __t719;
inputState.guessing--;
																														}
																														if ( synPredMatched719 ) {
																															AST __t720 = _t;
																															AST tmp108_AST = null;
																															AST tmp108_AST_in = null;
																															tmp108_AST = astFactory.create((AST)_t);
																															tmp108_AST_in = (AST)_t;
																															ASTPair __currentAST720 = currentAST.copy();
																															currentAST.root = currentAST.child;
																															currentAST.child = null;
																															match(_t,LASTEDEI);
																															_t = _t.getFirstChild();
																															AST __t721 = _t;
																															AST tmp109_AST = null;
																															AST tmp109_AST_in = null;
																															tmp109_AST = astFactory.create((AST)_t);
																															tmp109_AST_in = (AST)_t;
																															ASTPair __currentAST721 = currentAST.copy();
																															currentAST.root = currentAST.child;
																															currentAST.child = null;
																															match(_t,PAST);
																															_t = _t.getFirstChild();
																															tf_ei_p = _t==ASTNULL ? null : (AST)_t;
																															trioformula(_t);
																															_t = _retTree;
																															tf_ei_p_AST = (AST)returnAST;
																															t1_ei_p = _t==ASTNULL ? null : (AST)_t;
																															term(_t);
																															_t = _retTree;
																															t1_ei_p_AST = (AST)returnAST;
																															currentAST = __currentAST721;
																															_t = __t721;
																															_t = _t.getNextSibling();
																															t2_ei_p = _t==ASTNULL ? null : (AST)_t;
																															term(_t);
																															_t = _retTree;
																															t2_ei_p_AST = (AST)returnAST;
																															currentAST = __currentAST720;
																															_t = __t720;
																															_t = _t.getNextSibling();
																															if ( inputState.guessing==0 ) {
																																trioformula_AST = (AST)currentAST.root;
																																
																																trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(tmp109_AST).add((AST)astFactory.make( (new ASTArray(3)).add(tmp108_AST).add(tf_ei_p_AST).add(t1_ei_p_AST))).add(t2_ei_p_AST));
																																
																																currentAST.root = trioformula_AST;
																																currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																																	trioformula_AST.getFirstChild() : trioformula_AST;
																																currentAST.advanceChildToEnd();
																															}
																														}
																														else if ((_t.getType()==LASTEDEI)) {
																															AST __t722 = _t;
																															AST tmp110_AST = null;
																															AST tmp110_AST_in = null;
																															tmp110_AST = astFactory.create((AST)_t);
																															tmp110_AST_in = (AST)_t;
																															astFactory.addASTChild(currentAST, tmp110_AST);
																															ASTPair __currentAST722 = currentAST.copy();
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
																															currentAST = __currentAST722;
																															_t = __t722;
																															_t = _t.getNextSibling();
																															trioformula_AST = (AST)currentAST.root;
																														}
																														else {
																															boolean synPredMatched726 = false;
																															if (((_t.getType()==LASTEDIE))) {
																																AST __t726 = _t;
																																synPredMatched726 = true;
																																inputState.guessing++;
																																try {
																																	{
																																	AST __t724 = _t;
																																	ASTPair __currentAST724 = currentAST.copy();
																																	currentAST.root = currentAST.child;
																																	currentAST.child = null;
																																	match(_t,LASTEDIE);
																																	_t = _t.getFirstChild();
																																	AST __t725 = _t;
																																	ASTPair __currentAST725 = currentAST.copy();
																																	currentAST.root = currentAST.child;
																																	currentAST.child = null;
																																	match(_t,PAST);
																																	_t = _t.getFirstChild();
																																	trioformula(_t);
																																	_t = _retTree;
																																	term(_t);
																																	_t = _retTree;
																																	currentAST = __currentAST725;
																																	_t = __t725;
																																	_t = _t.getNextSibling();
																																	term(_t);
																																	_t = _retTree;
																																	currentAST = __currentAST724;
																																	_t = __t724;
																																	_t = _t.getNextSibling();
																																	}
																																}
																																catch (RecognitionException pe) {
																																	synPredMatched726 = false;
																																}
																																_t = __t726;
inputState.guessing--;
																															}
																															if ( synPredMatched726 ) {
																																AST __t727 = _t;
																																AST tmp111_AST = null;
																																AST tmp111_AST_in = null;
																																tmp111_AST = astFactory.create((AST)_t);
																																tmp111_AST_in = (AST)_t;
																																ASTPair __currentAST727 = currentAST.copy();
																																currentAST.root = currentAST.child;
																																currentAST.child = null;
																																match(_t,LASTEDIE);
																																_t = _t.getFirstChild();
																																AST __t728 = _t;
																																AST tmp112_AST = null;
																																AST tmp112_AST_in = null;
																																tmp112_AST = astFactory.create((AST)_t);
																																tmp112_AST_in = (AST)_t;
																																ASTPair __currentAST728 = currentAST.copy();
																																currentAST.root = currentAST.child;
																																currentAST.child = null;
																																match(_t,PAST);
																																_t = _t.getFirstChild();
																																tf_ie_p = _t==ASTNULL ? null : (AST)_t;
																																trioformula(_t);
																																_t = _retTree;
																																tf_ie_p_AST = (AST)returnAST;
																																t1_ie_p = _t==ASTNULL ? null : (AST)_t;
																																term(_t);
																																_t = _retTree;
																																t1_ie_p_AST = (AST)returnAST;
																																currentAST = __currentAST728;
																																_t = __t728;
																																_t = _t.getNextSibling();
																																t2_ie_p = _t==ASTNULL ? null : (AST)_t;
																																term(_t);
																																_t = _retTree;
																																t2_ie_p_AST = (AST)returnAST;
																																currentAST = __currentAST727;
																																_t = __t727;
																																_t = _t.getNextSibling();
																																if ( inputState.guessing==0 ) {
																																	trioformula_AST = (AST)currentAST.root;
																																	
																																	trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(tmp112_AST).add((AST)astFactory.make( (new ASTArray(3)).add(tmp111_AST).add(tf_ie_p_AST).add(t1_ie_p_AST))).add(t2_ie_p_AST));
																																	
																																	currentAST.root = trioformula_AST;
																																	currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																																		trioformula_AST.getFirstChild() : trioformula_AST;
																																	currentAST.advanceChildToEnd();
																																}
																															}
																															else if ((_t.getType()==LASTEDIE)) {
																																AST __t729 = _t;
																																AST tmp113_AST = null;
																																AST tmp113_AST_in = null;
																																tmp113_AST = astFactory.create((AST)_t);
																																tmp113_AST_in = (AST)_t;
																																astFactory.addASTChild(currentAST, tmp113_AST);
																																ASTPair __currentAST729 = currentAST.copy();
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
																																currentAST = __currentAST729;
																																_t = __t729;
																																_t = _t.getNextSibling();
																																trioformula_AST = (AST)currentAST.root;
																															}
																															else {
																																boolean synPredMatched733 = false;
																																if (((_t.getType()==LASTEDII))) {
																																	AST __t733 = _t;
																																	synPredMatched733 = true;
																																	inputState.guessing++;
																																	try {
																																		{
																																		AST __t731 = _t;
																																		ASTPair __currentAST731 = currentAST.copy();
																																		currentAST.root = currentAST.child;
																																		currentAST.child = null;
																																		match(_t,LASTEDII);
																																		_t = _t.getFirstChild();
																																		AST __t732 = _t;
																																		ASTPair __currentAST732 = currentAST.copy();
																																		currentAST.root = currentAST.child;
																																		currentAST.child = null;
																																		match(_t,PAST);
																																		_t = _t.getFirstChild();
																																		trioformula(_t);
																																		_t = _retTree;
																																		term(_t);
																																		_t = _retTree;
																																		currentAST = __currentAST732;
																																		_t = __t732;
																																		_t = _t.getNextSibling();
																																		term(_t);
																																		_t = _retTree;
																																		currentAST = __currentAST731;
																																		_t = __t731;
																																		_t = _t.getNextSibling();
																																		}
																																	}
																																	catch (RecognitionException pe) {
																																		synPredMatched733 = false;
																																	}
																																	_t = __t733;
inputState.guessing--;
																																}
																																if ( synPredMatched733 ) {
																																	AST __t734 = _t;
																																	AST tmp114_AST = null;
																																	AST tmp114_AST_in = null;
																																	tmp114_AST = astFactory.create((AST)_t);
																																	tmp114_AST_in = (AST)_t;
																																	ASTPair __currentAST734 = currentAST.copy();
																																	currentAST.root = currentAST.child;
																																	currentAST.child = null;
																																	match(_t,LASTEDII);
																																	_t = _t.getFirstChild();
																																	AST __t735 = _t;
																																	AST tmp115_AST = null;
																																	AST tmp115_AST_in = null;
																																	tmp115_AST = astFactory.create((AST)_t);
																																	tmp115_AST_in = (AST)_t;
																																	ASTPair __currentAST735 = currentAST.copy();
																																	currentAST.root = currentAST.child;
																																	currentAST.child = null;
																																	match(_t,PAST);
																																	_t = _t.getFirstChild();
																																	tf_ii_p = _t==ASTNULL ? null : (AST)_t;
																																	trioformula(_t);
																																	_t = _retTree;
																																	tf_ii_p_AST = (AST)returnAST;
																																	t1_ii_p = _t==ASTNULL ? null : (AST)_t;
																																	term(_t);
																																	_t = _retTree;
																																	t1_ii_p_AST = (AST)returnAST;
																																	currentAST = __currentAST735;
																																	_t = __t735;
																																	_t = _t.getNextSibling();
																																	t2_ii_p = _t==ASTNULL ? null : (AST)_t;
																																	term(_t);
																																	_t = _retTree;
																																	t2_ii_p_AST = (AST)returnAST;
																																	currentAST = __currentAST734;
																																	_t = __t734;
																																	_t = _t.getNextSibling();
																																	if ( inputState.guessing==0 ) {
																																		trioformula_AST = (AST)currentAST.root;
																																		
																																		trioformula_AST=(AST)astFactory.make( (new ASTArray(3)).add(tmp115_AST).add((AST)astFactory.make( (new ASTArray(3)).add(tmp114_AST).add(tf_ii_p_AST).add(t1_ii_p_AST))).add(t2_ii_p_AST));
																																		
																																		currentAST.root = trioformula_AST;
																																		currentAST.child = trioformula_AST!=null &&trioformula_AST.getFirstChild()!=null ?
																																			trioformula_AST.getFirstChild() : trioformula_AST;
																																		currentAST.advanceChildToEnd();
																																	}
																																}
																																else if ((_t.getType()==LASTEDII)) {
																																	AST __t736 = _t;
																																	AST tmp116_AST = null;
																																	AST tmp116_AST_in = null;
																																	tmp116_AST = astFactory.create((AST)_t);
																																	tmp116_AST_in = (AST)_t;
																																	astFactory.addASTChild(currentAST, tmp116_AST);
																																	ASTPair __currentAST736 = currentAST.copy();
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
																																	currentAST = __currentAST736;
																																	_t = __t736;
																																	_t = _t.getNextSibling();
																																	trioformula_AST = (AST)currentAST.root;
																																}
																															else {
																																throw new NoViableAltException(_t);
																															}
																															}}}}}}}}}}}}}}}}}}}}}}}}}}}}}
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
				AST __t753 = _t;
				AST tmp117_AST = null;
				AST tmp117_AST_in = null;
				tmp117_AST = astFactory.create((AST)_t);
				tmp117_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp117_AST);
				ASTPair __currentAST753 = currentAST.copy();
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
				currentAST = __currentAST753;
				_t = __t753;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case MINUS:
			{
				AST __t754 = _t;
				AST tmp118_AST = null;
				AST tmp118_AST_in = null;
				tmp118_AST = astFactory.create((AST)_t);
				tmp118_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp118_AST);
				ASTPair __currentAST754 = currentAST.copy();
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
				currentAST = __currentAST754;
				_t = __t754;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case DIV:
			{
				AST __t755 = _t;
				AST tmp119_AST = null;
				AST tmp119_AST_in = null;
				tmp119_AST = astFactory.create((AST)_t);
				tmp119_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp119_AST);
				ASTPair __currentAST755 = currentAST.copy();
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
				currentAST = __currentAST755;
				_t = __t755;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case STAR:
			{
				AST __t756 = _t;
				AST tmp120_AST = null;
				AST tmp120_AST_in = null;
				tmp120_AST = astFactory.create((AST)_t);
				tmp120_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp120_AST);
				ASTPair __currentAST756 = currentAST.copy();
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
				currentAST = __currentAST756;
				_t = __t756;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case MOD:
			{
				AST __t757 = _t;
				AST tmp121_AST = null;
				AST tmp121_AST_in = null;
				tmp121_AST = astFactory.create((AST)_t);
				tmp121_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp121_AST);
				ASTPair __currentAST757 = currentAST.copy();
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
				currentAST = __currentAST757;
				_t = __t757;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case INTDIV:
			{
				AST __t758 = _t;
				AST tmp122_AST = null;
				AST tmp122_AST_in = null;
				tmp122_AST = astFactory.create((AST)_t);
				tmp122_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp122_AST);
				ASTPair __currentAST758 = currentAST.copy();
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
				currentAST = __currentAST758;
				_t = __t758;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case POW:
			{
				AST __t759 = _t;
				AST tmp123_AST = null;
				AST tmp123_AST_in = null;
				tmp123_AST = astFactory.create((AST)_t);
				tmp123_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp123_AST);
				ASTPair __currentAST759 = currentAST.copy();
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
				currentAST = __currentAST759;
				_t = __t759;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case SIGN_MINUS:
			{
				AST __t760 = _t;
				AST tmp124_AST = null;
				AST tmp124_AST_in = null;
				tmp124_AST = astFactory.create((AST)_t);
				tmp124_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp124_AST);
				ASTPair __currentAST760 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SIGN_MINUS);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST760;
				_t = __t760;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case SIGN_PLUS:
			{
				AST __t761 = _t;
				AST tmp125_AST = null;
				AST tmp125_AST_in = null;
				tmp125_AST = astFactory.create((AST)_t);
				tmp125_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp125_AST);
				ASTPair __currentAST761 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,SIGN_PLUS);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST761;
				_t = __t761;
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case NUMBER:
			{
				AST tmp126_AST = null;
				AST tmp126_AST_in = null;
				tmp126_AST = astFactory.create((AST)_t);
				tmp126_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp126_AST);
				match(_t,NUMBER);
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case VARIABLE:
			{
				AST tmp127_AST = null;
				AST tmp127_AST_in = null;
				tmp127_AST = astFactory.create((AST)_t);
				tmp127_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp127_AST);
				match(_t,VARIABLE);
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case CONSTANT:
			{
				AST tmp128_AST = null;
				AST tmp128_AST_in = null;
				tmp128_AST = astFactory.create((AST)_t);
				tmp128_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp128_AST);
				match(_t,CONSTANT);
				_t = _t.getNextSibling();
				term_AST = (AST)currentAST.root;
				break;
			}
			case LPAREN:
			{
				AST __t762 = _t;
				AST tmp129_AST = null;
				AST tmp129_AST_in = null;
				tmp129_AST = astFactory.create((AST)_t);
				tmp129_AST_in = (AST)_t;
				astFactory.addASTChild(currentAST, tmp129_AST);
				ASTPair __currentAST762 = currentAST.copy();
				currentAST.root = currentAST.child;
				currentAST.child = null;
				match(_t,LPAREN);
				_t = _t.getFirstChild();
				term(_t);
				_t = _retTree;
				astFactory.addASTChild(currentAST, returnAST);
				currentAST = __currentAST762;
				_t = __t762;
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
	
