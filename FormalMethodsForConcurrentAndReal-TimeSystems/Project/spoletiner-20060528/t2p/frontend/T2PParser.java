// $ANTLR : "T2PParser.g" -> "T2PParser.java"$
 
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

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

import java.util.Map;
import java.util.HashMap;
import t2p.translation.VariableInfo;
import t2p.translation.MatchInfo;

/***
 * Parser for Trio2Promela input programs.
 */
public class T2PParser extends antlr.LLkParser       implements T2PExpTokenTypes
 {

	private HashMap<String, VariableInfo> varTable = new HashMap<String, VariableInfo>();
	private HashMap<String, Integer> constTable = new HashMap<String, Integer>();
	private HashMap<String, MatchInfo> matchTable = new HashMap<String, MatchInfo>();
	
	
	private int errNum=0;
	private String lastVariable;
    
	public Map<String, VariableInfo> getVarTable() {
		return  this.varTable;
	}
	
	public Map<String, Integer> getConstTable() {
		return  this.constTable;
	}
	
	public Map<String, MatchInfo> getMatchTable() {
		return  this.matchTable;
	}
	
	public int getErrNum() {
		return this.errNum;
	}

protected T2PParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public T2PParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected T2PParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public T2PParser(TokenStream lexer) {
  this(lexer,1);
}

public T2PParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void specs() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST specs_AST = null;
		
		{
		switch ( LA(1)) {
		case VAR:
		{
			match(VAR);
			variableDeclarations();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case FORM:
		case CONST:
		case MATCH:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case MATCH:
		{
			match(MATCH);
			matchDeclarations();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case FORM:
		case CONST:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case CONST:
		{
			match(CONST);
			constantDeclarations();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case FORM:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		AST tmp4_AST = null;
		tmp4_AST = astFactory.create(LT(1));
		astFactory.makeASTRoot(currentAST, tmp4_AST);
		match(FORM);
		trioformulae();
		astFactory.addASTChild(currentAST, returnAST);
		specs_AST = (AST)currentAST.root;
		returnAST = specs_AST;
	}
	
	public final void variableDeclarations() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST variableDeclarations_AST = null;
		
		var_decl();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop1130:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				var_decl();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop1130;
			}
			
		} while (true);
		}
		variableDeclarations_AST = (AST)currentAST.root;
		returnAST = variableDeclarations_AST;
	}
	
	public final void matchDeclarations() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST matchDeclarations_AST = null;
		
		match_decl();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop1136:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				match_decl();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop1136;
			}
			
		} while (true);
		}
		matchDeclarations_AST = (AST)currentAST.root;
		returnAST = matchDeclarations_AST;
	}
	
	public final void constantDeclarations() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST constantDeclarations_AST = null;
		
		const_decl();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop1141:
		do {
			if ((LA(1)==COMMA)) {
				match(COMMA);
				const_decl();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop1141;
			}
			
		} while (true);
		}
		constantDeclarations_AST = (AST)currentAST.root;
		returnAST = constantDeclarations_AST;
	}
	
	public final void trioformulae() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST trioformulae_AST = null;
		
		{
		int _cnt1145=0;
		_loop1145:
		do {
			if ((LA(1)==ID)) {
				trioformula_decl();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp8_AST = null;
				tmp8_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp8_AST);
				match(SEMI);
			}
			else {
				if ( _cnt1145>=1 ) { break _loop1145; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt1145++;
		} while (true);
		}
		trioformulae_AST = (AST)currentAST.root;
		returnAST = trioformulae_AST;
	}
	
	public final void var_decl() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST var_decl_AST = null;
		Token  p = null;
		AST p_AST = null;
		Token  sign1 = null;
		AST sign1_AST = null;
		Token  n1 = null;
		AST n1_AST = null;
		Token  sign2 = null;
		AST sign2_AST = null;
		Token  n2 = null;
		AST n2_AST = null;
		
		p = LT(1);
		p_AST = astFactory.create(p);
		match(ID);
		match(COLON);
		match(LBRACK);
		{
		switch ( LA(1)) {
		case MINUS:
		{
			sign1 = LT(1);
			sign1_AST = astFactory.create(sign1);
			match(MINUS);
			break;
		}
		case NUMBER:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		n1 = LT(1);
		n1_AST = astFactory.create(n1);
		match(NUMBER);
		match(DOT);
		match(DOT);
		{
		switch ( LA(1)) {
		case MINUS:
		{
			sign2 = LT(1);
			sign2_AST = astFactory.create(sign2);
			match(MINUS);
			break;
		}
		case NUMBER:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		n2 = LT(1);
		n2_AST = astFactory.create(n2);
		match(NUMBER);
		match(RBRACK);
		if ( inputState.guessing==0 ) {
			
			int lower=0;
			int upper=0;
			String k = p.getText();
			String number1;
			String number2;
			if (sign1!=null) number1="-"+n1.getText(); else number1=n1.getText();
			if (sign2!=null) number2="-"+n2.getText(); else number2=n2.getText();
			try {
			lower=Integer.parseInt(number1);
			upper=Integer.parseInt(number2);
			} 
			catch (NumberFormatException ex){
			System.out.println("Error in defining range domain for variable " + p);
			errNum++;
			}
			if (! varTable.containsKey(k)) {
			varTable.put(k,new VariableInfo(lower,upper));
			} 	 else {
			System.out.println("Error: variable " + k + " already defined.");
			errNum++;
			}
			
		}
		var_decl_AST = (AST)currentAST.root;
		returnAST = var_decl_AST;
	}
	
	public final void match_decl() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST match_decl_AST = null;
		Token  str = null;
		AST str_AST = null;
		Token  v1 = null;
		AST v1_AST = null;
		Token  n1 = null;
		AST n1_AST = null;
		Token  v2 = null;
		AST v2_AST = null;
		Token  n2 = null;
		AST n2_AST = null;
		
		{
		switch ( LA(1)) {
		case STRONG:
		{
			str = LT(1);
			str_AST = astFactory.create(str);
			match(STRONG);
			break;
		}
		case LPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		match(LPAREN);
		v1 = LT(1);
		v1_AST = astFactory.create(v1);
		match(ID);
		match(EQ);
		n1 = LT(1);
		n1_AST = astFactory.create(n1);
		match(NUMBER);
		match(COMMA);
		v2 = LT(1);
		v2_AST = astFactory.create(v2);
		match(ID);
		match(EQ);
		n2 = LT(1);
		n2_AST = astFactory.create(n2);
		match(NUMBER);
		match(RPAREN);
		if ( inputState.guessing==0 ) {
			
					boolean isStrong= (str==null)? false : true;
					String k1= v1.getText();
					String k2=v2.getText();
					if (! varTable.containsKey(k1)) {
								System.out.println("Error: variable " + k1 + " not defined.");
					errNum++;
			}
			if (! varTable.containsKey(k2)) {
								System.out.println("Error: variable " + k2 + " not defined.");
					errNum++;
			} else {
				VariableInfo vi = varTable.get(k2);
				varTable.put(k2, new VariableInfo(vi.getLower(), vi.getUpper(), true));
			}
			
					matchTable.put(k1, new MatchInfo(Integer.valueOf(n1.getText()), k2, Integer.valueOf(n2.getText()), isStrong));
				
		}
		match_decl_AST = (AST)currentAST.root;
		returnAST = match_decl_AST;
	}
	
	public final void const_decl() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST const_decl_AST = null;
		Token  c = null;
		AST c_AST = null;
		Token  n = null;
		AST n_AST = null;
		
		c = LT(1);
		c_AST = astFactory.create(c);
		match(ID);
		match(EQ);
		n = LT(1);
		n_AST = astFactory.create(n);
		match(NUMBER);
		if ( inputState.guessing==0 ) {
			
			String k=c.getText();
			Integer value = null;
			try {
			value=Integer.valueOf(n.getText());
			} 
			catch (NumberFormatException ex){
			System.out.println("Error: in defining constant " + k);
			errNum++;
			}
			
			if (! constTable.containsKey(k)) {
			constTable.put(k, value );
			} else { 
			System.out.println("Error: constant " + k + " already defined.");
			errNum++;
			}
			
		}
		const_decl_AST = (AST)currentAST.root;
		returnAST = const_decl_AST;
	}
	
	public final void trioformula_decl() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST trioformula_decl_AST = null;
		Token  id = null;
		AST id_AST = null;
		
		id = LT(1);
		id_AST = astFactory.create(id);
		astFactory.makeASTRoot(currentAST, id_AST);
		match(ID);
		match(COLON);
		trioformula();
		astFactory.addASTChild(currentAST, returnAST);
		trioformula_decl_AST = (AST)currentAST.root;
		returnAST = trioformula_decl_AST;
	}
	
	public final void trioformula() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST trioformula_AST = null;
		
		coimpl();
		astFactory.addASTChild(currentAST, returnAST);
		trioformula_AST = (AST)currentAST.root;
		returnAST = trioformula_AST;
	}
	
	public final void coimpl() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST coimpl_AST = null;
		
		impl();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop1150:
		do {
			if ((LA(1)==IFF)) {
				AST tmp21_AST = null;
				tmp21_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp21_AST);
				match(IFF);
				impl();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop1150;
			}
			
		} while (true);
		}
		coimpl_AST = (AST)currentAST.root;
		returnAST = coimpl_AST;
	}
	
	public final void impl() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST impl_AST = null;
		
		or();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop1153:
		do {
			if ((LA(1)==IF)) {
				AST tmp22_AST = null;
				tmp22_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp22_AST);
				match(IF);
				or();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop1153;
			}
			
		} while (true);
		}
		impl_AST = (AST)currentAST.root;
		returnAST = impl_AST;
	}
	
	public final void or() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST or_AST = null;
		
		and();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop1156:
		do {
			if ((LA(1)==OR)) {
				AST tmp23_AST = null;
				tmp23_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp23_AST);
				match(OR);
				and();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop1156;
			}
			
		} while (true);
		}
		or_AST = (AST)currentAST.root;
		returnAST = or_AST;
	}
	
	public final void and() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST and_AST = null;
		
		formula();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop1159:
		do {
			if ((LA(1)==AND)) {
				AST tmp24_AST = null;
				tmp24_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp24_AST);
				match(AND);
				formula();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop1159;
			}
			
		} while (true);
		}
		and_AST = (AST)currentAST.root;
		returnAST = and_AST;
	}
	
	public final void formula() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST formula_AST = null;
		AST somf_tf_AST = null;
		AST somp_tf_AST = null;
		AST alwf_tf_AST = null;
		AST alwp_tf_AST = null;
		AST l_tf_AST = null;
		AST l_t_AST = null;
		AST ld_tf_AST = null;
		AST ld_t_AST = null;
		AST wf_tf_AST = null;
		AST wf_t_AST = null;
		AST wp_tf_AST = null;
		AST wp_t_AST = null;
		
		switch ( LA(1)) {
		case NOT:
		{
			AST tmp25_AST = null;
			tmp25_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp25_AST);
			match(NOT);
			formula();
			astFactory.addASTChild(currentAST, returnAST);
			formula_AST = (AST)currentAST.root;
			break;
		}
		case ALWFE:
		case ALWFI:
		case ALWPE:
		case ALWPI:
		case SOMFE:
		case SOMFI:
		case SOMPE:
		case SOMPI:
		case UPTONOW:
		case BECOMES:
		case NOWON:
		case NOW:
		{
			{
			switch ( LA(1)) {
			case ALWFE:
			{
				AST tmp26_AST = null;
				tmp26_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp26_AST);
				match(ALWFE);
				break;
			}
			case ALWFI:
			{
				AST tmp27_AST = null;
				tmp27_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp27_AST);
				match(ALWFI);
				break;
			}
			case ALWPE:
			{
				AST tmp28_AST = null;
				tmp28_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp28_AST);
				match(ALWPE);
				break;
			}
			case ALWPI:
			{
				AST tmp29_AST = null;
				tmp29_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp29_AST);
				match(ALWPI);
				break;
			}
			case SOMFE:
			{
				AST tmp30_AST = null;
				tmp30_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp30_AST);
				match(SOMFE);
				break;
			}
			case SOMFI:
			{
				AST tmp31_AST = null;
				tmp31_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp31_AST);
				match(SOMFI);
				break;
			}
			case SOMPE:
			{
				AST tmp32_AST = null;
				tmp32_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp32_AST);
				match(SOMPE);
				break;
			}
			case SOMPI:
			{
				AST tmp33_AST = null;
				tmp33_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp33_AST);
				match(SOMPI);
				break;
			}
			case UPTONOW:
			{
				AST tmp34_AST = null;
				tmp34_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp34_AST);
				match(UPTONOW);
				break;
			}
			case BECOMES:
			{
				AST tmp35_AST = null;
				tmp35_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp35_AST);
				match(BECOMES);
				break;
			}
			case NOWON:
			{
				AST tmp36_AST = null;
				tmp36_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp36_AST);
				match(NOWON);
				break;
			}
			case NOW:
			{
				AST tmp37_AST = null;
				tmp37_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp37_AST);
				match(NOW);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LPAREN);
			trioformula();
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			formula_AST = (AST)currentAST.root;
			break;
		}
		case SOMF:
		{
			AST tmp40_AST = null;
			tmp40_AST = astFactory.create(LT(1));
			match(SOMF);
			match(LPAREN);
			trioformula();
			somf_tf_AST = (AST)returnAST;
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				formula_AST = (AST)currentAST.root;
				formula_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(SOMFE)).add(somf_tf_AST));
				currentAST.root = formula_AST;
				currentAST.child = formula_AST!=null &&formula_AST.getFirstChild()!=null ?
					formula_AST.getFirstChild() : formula_AST;
				currentAST.advanceChildToEnd();
			}
			break;
		}
		case SOMP:
		{
			AST tmp43_AST = null;
			tmp43_AST = astFactory.create(LT(1));
			match(SOMP);
			match(LPAREN);
			trioformula();
			somp_tf_AST = (AST)returnAST;
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				formula_AST = (AST)currentAST.root;
				formula_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(SOMPE)).add(somp_tf_AST));
				currentAST.root = formula_AST;
				currentAST.child = formula_AST!=null &&formula_AST.getFirstChild()!=null ?
					formula_AST.getFirstChild() : formula_AST;
				currentAST.advanceChildToEnd();
			}
			break;
		}
		case ALWF:
		{
			AST tmp46_AST = null;
			tmp46_AST = astFactory.create(LT(1));
			match(ALWF);
			match(LPAREN);
			trioformula();
			alwf_tf_AST = (AST)returnAST;
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				formula_AST = (AST)currentAST.root;
				formula_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(ALWFE)).add(alwf_tf_AST));
				currentAST.root = formula_AST;
				currentAST.child = formula_AST!=null &&formula_AST.getFirstChild()!=null ?
					formula_AST.getFirstChild() : formula_AST;
				currentAST.advanceChildToEnd();
			}
			break;
		}
		case ALWP:
		{
			AST tmp49_AST = null;
			tmp49_AST = astFactory.create(LT(1));
			match(ALWP);
			match(LPAREN);
			trioformula();
			alwp_tf_AST = (AST)returnAST;
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				formula_AST = (AST)currentAST.root;
				formula_AST=(AST)astFactory.make( (new ASTArray(2)).add(astFactory.create(ALWPE)).add(alwp_tf_AST));
				currentAST.root = formula_AST;
				currentAST.child = formula_AST!=null &&formula_AST.getFirstChild()!=null ?
					formula_AST.getFirstChild() : formula_AST;
				currentAST.advanceChildToEnd();
			}
			break;
		}
		case UNTIL:
		case UNTILW:
		case SINCE:
		case SINCEW:
		{
			{
			switch ( LA(1)) {
			case UNTIL:
			{
				AST tmp52_AST = null;
				tmp52_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp52_AST);
				match(UNTIL);
				break;
			}
			case UNTILW:
			{
				AST tmp53_AST = null;
				tmp53_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp53_AST);
				match(UNTILW);
				break;
			}
			case SINCE:
			{
				AST tmp54_AST = null;
				tmp54_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp54_AST);
				match(SINCE);
				break;
			}
			case SINCEW:
			{
				AST tmp55_AST = null;
				tmp55_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp55_AST);
				match(SINCEW);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LPAREN);
			trioformula();
			astFactory.addASTChild(currentAST, returnAST);
			match(COMMA);
			trioformula();
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			formula_AST = (AST)currentAST.root;
			break;
		}
		case DIST:
		case FUTR:
		case PAST:
		case LASTSEE:
		case LASTSEI:
		case LASTSIE:
		case LASTSII:
		case LASTEDEE:
		case LASTEDEI:
		case LASTEDIE:
		case LASTEDII:
		case WITHINFEE:
		case WITHINFEI:
		case WITHINFIE:
		case WITHINFII:
		case WITHINPEE:
		case WITHINPEI:
		case WITHINPIE:
		case WITHINPII:
		case NEXTTIME:
		case LASTTIME:
		{
			{
			switch ( LA(1)) {
			case DIST:
			{
				AST tmp59_AST = null;
				tmp59_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp59_AST);
				match(DIST);
				break;
			}
			case FUTR:
			{
				AST tmp60_AST = null;
				tmp60_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp60_AST);
				match(FUTR);
				break;
			}
			case PAST:
			{
				AST tmp61_AST = null;
				tmp61_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp61_AST);
				match(PAST);
				break;
			}
			case LASTSEE:
			{
				AST tmp62_AST = null;
				tmp62_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp62_AST);
				match(LASTSEE);
				break;
			}
			case LASTSEI:
			{
				AST tmp63_AST = null;
				tmp63_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp63_AST);
				match(LASTSEI);
				break;
			}
			case LASTSIE:
			{
				AST tmp64_AST = null;
				tmp64_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp64_AST);
				match(LASTSIE);
				break;
			}
			case LASTSII:
			{
				AST tmp65_AST = null;
				tmp65_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp65_AST);
				match(LASTSII);
				break;
			}
			case LASTEDEE:
			{
				AST tmp66_AST = null;
				tmp66_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp66_AST);
				match(LASTEDEE);
				break;
			}
			case LASTEDEI:
			{
				AST tmp67_AST = null;
				tmp67_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp67_AST);
				match(LASTEDEI);
				break;
			}
			case LASTEDIE:
			{
				AST tmp68_AST = null;
				tmp68_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp68_AST);
				match(LASTEDIE);
				break;
			}
			case LASTEDII:
			{
				AST tmp69_AST = null;
				tmp69_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp69_AST);
				match(LASTEDII);
				break;
			}
			case WITHINFEE:
			{
				AST tmp70_AST = null;
				tmp70_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp70_AST);
				match(WITHINFEE);
				break;
			}
			case WITHINFEI:
			{
				AST tmp71_AST = null;
				tmp71_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp71_AST);
				match(WITHINFEI);
				break;
			}
			case WITHINFIE:
			{
				AST tmp72_AST = null;
				tmp72_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp72_AST);
				match(WITHINFIE);
				break;
			}
			case WITHINFII:
			{
				AST tmp73_AST = null;
				tmp73_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp73_AST);
				match(WITHINFII);
				break;
			}
			case WITHINPEE:
			{
				AST tmp74_AST = null;
				tmp74_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp74_AST);
				match(WITHINPEE);
				break;
			}
			case WITHINPEI:
			{
				AST tmp75_AST = null;
				tmp75_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp75_AST);
				match(WITHINPEI);
				break;
			}
			case WITHINPIE:
			{
				AST tmp76_AST = null;
				tmp76_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp76_AST);
				match(WITHINPIE);
				break;
			}
			case WITHINPII:
			{
				AST tmp77_AST = null;
				tmp77_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp77_AST);
				match(WITHINPII);
				break;
			}
			case NEXTTIME:
			{
				AST tmp78_AST = null;
				tmp78_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp78_AST);
				match(NEXTTIME);
				break;
			}
			case LASTTIME:
			{
				AST tmp79_AST = null;
				tmp79_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp79_AST);
				match(LASTTIME);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LPAREN);
			trioformula();
			astFactory.addASTChild(currentAST, returnAST);
			match(COMMA);
			term();
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			formula_AST = (AST)currentAST.root;
			break;
		}
		case LASTS:
		{
			AST tmp83_AST = null;
			tmp83_AST = astFactory.create(LT(1));
			match(LASTS);
			match(LPAREN);
			trioformula();
			l_tf_AST = (AST)returnAST;
			match(COMMA);
			term();
			l_t_AST = (AST)returnAST;
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				formula_AST = (AST)currentAST.root;
				formula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(LASTSEE)).add(l_tf_AST).add(l_t_AST));
				currentAST.root = formula_AST;
				currentAST.child = formula_AST!=null &&formula_AST.getFirstChild()!=null ?
					formula_AST.getFirstChild() : formula_AST;
				currentAST.advanceChildToEnd();
			}
			break;
		}
		case LASTED:
		{
			AST tmp87_AST = null;
			tmp87_AST = astFactory.create(LT(1));
			match(LASTED);
			match(LPAREN);
			trioformula();
			ld_tf_AST = (AST)returnAST;
			match(COMMA);
			term();
			ld_t_AST = (AST)returnAST;
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				formula_AST = (AST)currentAST.root;
				formula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(LASTEDEE)).add(ld_tf_AST).add(ld_t_AST));
				currentAST.root = formula_AST;
				currentAST.child = formula_AST!=null &&formula_AST.getFirstChild()!=null ?
					formula_AST.getFirstChild() : formula_AST;
				currentAST.advanceChildToEnd();
			}
			break;
		}
		case WITHINF:
		{
			AST tmp91_AST = null;
			tmp91_AST = astFactory.create(LT(1));
			match(WITHINF);
			match(LPAREN);
			trioformula();
			wf_tf_AST = (AST)returnAST;
			match(COMMA);
			term();
			wf_t_AST = (AST)returnAST;
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				formula_AST = (AST)currentAST.root;
				formula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(WITHINFEE)).add(wf_tf_AST).add(wf_t_AST));
				currentAST.root = formula_AST;
				currentAST.child = formula_AST!=null &&formula_AST.getFirstChild()!=null ?
					formula_AST.getFirstChild() : formula_AST;
				currentAST.advanceChildToEnd();
			}
			break;
		}
		case WITHINP:
		{
			AST tmp95_AST = null;
			tmp95_AST = astFactory.create(LT(1));
			match(WITHINP);
			match(LPAREN);
			trioformula();
			wp_tf_AST = (AST)returnAST;
			match(COMMA);
			term();
			wp_t_AST = (AST)returnAST;
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				formula_AST = (AST)currentAST.root;
				formula_AST=(AST)astFactory.make( (new ASTArray(3)).add(astFactory.create(WITHINPEE)).add(wp_tf_AST).add(wp_t_AST));
				currentAST.root = formula_AST;
				currentAST.child = formula_AST!=null &&formula_AST.getFirstChild()!=null ?
					formula_AST.getFirstChild() : formula_AST;
				currentAST.advanceChildToEnd();
			}
			break;
		}
		default:
			boolean synPredMatched1162 = false;
			if (((_tokenSet_0.member(LA(1))))) {
				int _m1162 = mark();
				synPredMatched1162 = true;
				inputState.guessing++;
				try {
					{
					term();
					match(EQ);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched1162 = false;
				}
				rewind(_m1162);
inputState.guessing--;
			}
			if ( synPredMatched1162 ) {
				term();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp99_AST = null;
				tmp99_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp99_AST);
				match(EQ);
				term();
				astFactory.addASTChild(currentAST, returnAST);
				formula_AST = (AST)currentAST.root;
			}
			else {
				boolean synPredMatched1164 = false;
				if (((_tokenSet_0.member(LA(1))))) {
					int _m1164 = mark();
					synPredMatched1164 = true;
					inputState.guessing++;
					try {
						{
						term();
						match(NEQ);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched1164 = false;
					}
					rewind(_m1164);
inputState.guessing--;
				}
				if ( synPredMatched1164 ) {
					term();
					astFactory.addASTChild(currentAST, returnAST);
					AST tmp100_AST = null;
					tmp100_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp100_AST);
					match(NEQ);
					term();
					astFactory.addASTChild(currentAST, returnAST);
					formula_AST = (AST)currentAST.root;
				}
				else {
					boolean synPredMatched1166 = false;
					if (((_tokenSet_0.member(LA(1))))) {
						int _m1166 = mark();
						synPredMatched1166 = true;
						inputState.guessing++;
						try {
							{
							term();
							match(LT);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched1166 = false;
						}
						rewind(_m1166);
inputState.guessing--;
					}
					if ( synPredMatched1166 ) {
						term();
						astFactory.addASTChild(currentAST, returnAST);
						AST tmp101_AST = null;
						tmp101_AST = astFactory.create(LT(1));
						astFactory.makeASTRoot(currentAST, tmp101_AST);
						match(LT);
						term();
						astFactory.addASTChild(currentAST, returnAST);
						formula_AST = (AST)currentAST.root;
					}
					else {
						boolean synPredMatched1168 = false;
						if (((_tokenSet_0.member(LA(1))))) {
							int _m1168 = mark();
							synPredMatched1168 = true;
							inputState.guessing++;
							try {
								{
								term();
								match(GT);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched1168 = false;
							}
							rewind(_m1168);
inputState.guessing--;
						}
						if ( synPredMatched1168 ) {
							term();
							astFactory.addASTChild(currentAST, returnAST);
							AST tmp102_AST = null;
							tmp102_AST = astFactory.create(LT(1));
							astFactory.makeASTRoot(currentAST, tmp102_AST);
							match(GT);
							term();
							astFactory.addASTChild(currentAST, returnAST);
							formula_AST = (AST)currentAST.root;
						}
						else {
							boolean synPredMatched1170 = false;
							if (((_tokenSet_0.member(LA(1))))) {
								int _m1170 = mark();
								synPredMatched1170 = true;
								inputState.guessing++;
								try {
									{
									term();
									match(GTE);
									}
								}
								catch (RecognitionException pe) {
									synPredMatched1170 = false;
								}
								rewind(_m1170);
inputState.guessing--;
							}
							if ( synPredMatched1170 ) {
								term();
								astFactory.addASTChild(currentAST, returnAST);
								AST tmp103_AST = null;
								tmp103_AST = astFactory.create(LT(1));
								astFactory.makeASTRoot(currentAST, tmp103_AST);
								match(GTE);
								term();
								astFactory.addASTChild(currentAST, returnAST);
								formula_AST = (AST)currentAST.root;
							}
							else {
								boolean synPredMatched1172 = false;
								if (((_tokenSet_0.member(LA(1))))) {
									int _m1172 = mark();
									synPredMatched1172 = true;
									inputState.guessing++;
									try {
										{
										term();
										match(LTE);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched1172 = false;
									}
									rewind(_m1172);
inputState.guessing--;
								}
								if ( synPredMatched1172 ) {
									term();
									astFactory.addASTChild(currentAST, returnAST);
									AST tmp104_AST = null;
									tmp104_AST = astFactory.create(LT(1));
									astFactory.makeASTRoot(currentAST, tmp104_AST);
									match(LTE);
									term();
									astFactory.addASTChild(currentAST, returnAST);
									formula_AST = (AST)currentAST.root;
								}
								else if ((LA(1)==LPAREN)) {
									AST tmp105_AST = null;
									tmp105_AST = astFactory.create(LT(1));
									astFactory.makeASTRoot(currentAST, tmp105_AST);
									match(LPAREN);
									trioformula();
									astFactory.addASTChild(currentAST, returnAST);
									match(RPAREN);
									formula_AST = (AST)currentAST.root;
								}
							else {
								throw new NoViableAltException(LT(1), getFilename());
							}
							}}}}}}
							returnAST = formula_AST;
						}
						
	public final void term() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST term_AST = null;
		
		mathExpr();
		astFactory.addASTChild(currentAST, returnAST);
		term_AST = (AST)currentAST.root;
		returnAST = term_AST;
	}
	
	public final void mathExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST mathExpr_AST = null;
		
		prodExpr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop1180:
		do {
			if ((LA(1)==PLUS||LA(1)==MINUS)) {
				{
				switch ( LA(1)) {
				case PLUS:
				{
					AST tmp107_AST = null;
					tmp107_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp107_AST);
					match(PLUS);
					break;
				}
				case MINUS:
				{
					AST tmp108_AST = null;
					tmp108_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp108_AST);
					match(MINUS);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				prodExpr();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop1180;
			}
			
		} while (true);
		}
		mathExpr_AST = (AST)currentAST.root;
		returnAST = mathExpr_AST;
	}
	
	public final void prodExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST prodExpr_AST = null;
		
		powExpr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop1184:
		do {
			if ((_tokenSet_1.member(LA(1)))) {
				{
				switch ( LA(1)) {
				case DIV:
				{
					AST tmp109_AST = null;
					tmp109_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp109_AST);
					match(DIV);
					break;
				}
				case STAR:
				{
					AST tmp110_AST = null;
					tmp110_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp110_AST);
					match(STAR);
					break;
				}
				case MOD:
				{
					AST tmp111_AST = null;
					tmp111_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp111_AST);
					match(MOD);
					break;
				}
				case INTDIV:
				{
					AST tmp112_AST = null;
					tmp112_AST = astFactory.create(LT(1));
					astFactory.makeASTRoot(currentAST, tmp112_AST);
					match(INTDIV);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				powExpr();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop1184;
			}
			
		} while (true);
		}
		prodExpr_AST = (AST)currentAST.root;
		returnAST = prodExpr_AST;
	}
	
	public final void powExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST powExpr_AST = null;
		
		signedExpr();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop1187:
		do {
			if ((LA(1)==POW)) {
				AST tmp113_AST = null;
				tmp113_AST = astFactory.create(LT(1));
				astFactory.makeASTRoot(currentAST, tmp113_AST);
				match(POW);
				signedExpr();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop1187;
			}
			
		} while (true);
		}
		powExpr_AST = (AST)currentAST.root;
		returnAST = powExpr_AST;
	}
	
	public final void signedExpr() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST signedExpr_AST = null;
		Token  m = null;
		AST m_AST = null;
		Token  p = null;
		AST p_AST = null;
		
		{
		switch ( LA(1)) {
		case MINUS:
		{
			m = LT(1);
			m_AST = astFactory.create(m);
			astFactory.makeASTRoot(currentAST, m_AST);
			match(MINUS);
			if ( inputState.guessing==0 ) {
				m_AST.setType(SIGN_MINUS);
			}
			break;
		}
		case PLUS:
		{
			p = LT(1);
			p_AST = astFactory.create(p);
			astFactory.makeASTRoot(currentAST, p_AST);
			match(PLUS);
			if ( inputState.guessing==0 ) {
				p_AST.setType(SIGN_PLUS);
			}
			break;
		}
		case LPAREN:
		case ID:
		case NUMBER:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		atom();
		astFactory.addASTChild(currentAST, returnAST);
		signedExpr_AST = (AST)currentAST.root;
		returnAST = signedExpr_AST;
	}
	
	public final void atom() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST atom_AST = null;
		Token  n = null;
		AST n_AST = null;
		
		switch ( LA(1)) {
		case NUMBER:
		{
			AST tmp114_AST = null;
			tmp114_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp114_AST);
			match(NUMBER);
			atom_AST = (AST)currentAST.root;
			break;
		}
		case ID:
		{
			n = LT(1);
			n_AST = astFactory.create(n);
			astFactory.addASTChild(currentAST, n_AST);
			match(ID);
			if ( inputState.guessing==0 ) {
				
				String k = n.getText();
				if (! constTable.containsKey(k) && varTable.containsKey(k)) {
				n_AST.setType(VARIABLE);
				} else if (constTable.containsKey(k) && ! varTable.containsKey(k)) {
				n_AST.setType(CONSTANT);
				} else {
				System.out.println("Error: constant or variable " + k + " not defined in preamble.");
				errNum++;
				}
				
			}
			atom_AST = (AST)currentAST.root;
			break;
		}
		case LPAREN:
		{
			AST tmp115_AST = null;
			tmp115_AST = astFactory.create(LT(1));
			astFactory.makeASTRoot(currentAST, tmp115_AST);
			match(LPAREN);
			mathExpr();
			astFactory.addASTChild(currentAST, returnAST);
			match(RPAREN);
			atom_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = atom_AST;
	}
	
	public final void imaginaryToken() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST imaginaryToken_AST = null;
		
		switch ( LA(1)) {
		case SIGN_PLUS:
		{
			AST tmp117_AST = null;
			tmp117_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp117_AST);
			match(SIGN_PLUS);
			imaginaryToken_AST = (AST)currentAST.root;
			break;
		}
		case SIGN_MINUS:
		{
			AST tmp118_AST = null;
			tmp118_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp118_AST);
			match(SIGN_MINUS);
			imaginaryToken_AST = (AST)currentAST.root;
			break;
		}
		case VARIABLE:
		{
			AST tmp119_AST = null;
			tmp119_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp119_AST);
			match(VARIABLE);
			imaginaryToken_AST = (AST)currentAST.root;
			break;
		}
		case CONSTANT:
		{
			AST tmp120_AST = null;
			tmp120_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp120_AST);
			match(CONSTANT);
			imaginaryToken_AST = (AST)currentAST.root;
			break;
		}
		case OR_ROOT:
		{
			AST tmp121_AST = null;
			tmp121_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp121_AST);
			match(OR_ROOT);
			imaginaryToken_AST = (AST)currentAST.root;
			break;
		}
		case AND_ROOT:
		{
			AST tmp122_AST = null;
			tmp122_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp122_AST);
			match(AND_ROOT);
			imaginaryToken_AST = (AST)currentAST.root;
			break;
		}
		case IF_ROOT:
		{
			AST tmp123_AST = null;
			tmp123_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp123_AST);
			match(IF_ROOT);
			imaginaryToken_AST = (AST)currentAST.root;
			break;
		}
		case IFF_ROOT:
		{
			AST tmp124_AST = null;
			tmp124_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp124_AST);
			match(IFF_ROOT);
			imaginaryToken_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = imaginaryToken_AST;
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
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 0L, 338690049L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 864691128455135232L, 12582912L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	
	}
