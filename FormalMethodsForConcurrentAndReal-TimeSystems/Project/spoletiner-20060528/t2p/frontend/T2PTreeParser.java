// $ANTLR : "T2PTreeParser.g" -> "T2PTreeParser.java"$

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

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import t2p.translation.*;


/***
 * Tree walker for preprocessed AST of TRIO formulae.
 * Given an AST corresponding to a formula, builds objects associated to
 * the operators contained in the formula.
 * These objects may be retrieved with methods <code>get</code>OperatorName<code>Formulae()</code>
 * for further processing.
 */
public class T2PTreeParser extends antlr.TreeParser       implements T2PTreeParserTokenTypes
 {

	private Map<String, VariableInfo> varTable;
	private Map<String, Integer> constTable;
	private HashMap<String, Integer> pastVarTable;
	//counters for operators
	private int futrCounter =0;
	private int lastedCounter =0;
	private int lastsCounter =0;
	private int withinPCounter = 0;
	private int withinFCounter = 0;
	private int sinceCounter = 0;
	private int alwPCounter = 0;
	private int alwFCounter = 0;
	private int untilCounter = 0;
	private int somfCounter = 0;
	private int sompCounter = 0;
	private int untilwCounter = 0;
	private int nowCounter=0;
	//collections of operator objects
	private ArrayList<FutrFormula> futrFormulae;
	private ArrayList<LastedFormula> lastedFormulae;
	private ArrayList<LastsFormula> lastsFormulae;
	private ArrayList<WithinPFormula> withinPFormulae;
	private ArrayList<WithinFFormula> withinFFormulae;
	private ArrayList<SinceFormula> sinceFormulae;
	private ArrayList<AlwPFormula> alwPFormulae;
	private ArrayList<AlwFFormula> alwFFormulae;
	private ArrayList<UntilFormula> untilFormulae;
	private ArrayList<SomfFormula> somfFormulae;
	private ArrayList<SompFormula> sompFormulae;
	private ArrayList<UntilWFormula> untilwFormulae;
	private ArrayList<NowFormula> nowFormulae;
	
	/* add to pastVarTable the variable used in the formula and the maximum 
		constant associated with it. */
	private void updatePastVarTable(TrioFormula temp, String var) {
	
		
		int pastConstant = temp.getTemporalConstant();
		if (pastVarTable.containsKey(var)) {
			if (pastConstant > pastVarTable.get(var)) {
				pastVarTable.put(var,pastConstant);
			}		
		} else {
			pastVarTable.put(var, pastConstant);
		}
	}
	
	private int term2Constant(TermFormula t) {
		int tempConstant = 0;
		// retrieve TermFormula value
		String numericValue = t.eval();
		// suppose t represents a number
		try {
		 	tempConstant = Integer.parseInt(numericValue);
		 } 
		//here t is not a number; check in constant Table
		 catch (NumberFormatException ex){
			if (constTable.containsKey(numericValue)) {
				tempConstant=constTable.get(numericValue);
			} else {
				System.out.println("No suitable numeric instantion found for " + numericValue);
				
			}
		 }
		return tempConstant;
	}
		
	public Map<String, Integer> getPastVarTable() {
		 return (Map<String, Integer>) this.pastVarTable.clone();
	}
	
	public ArrayList<LastedFormula> getLastedFormulae() {
		return (ArrayList<LastedFormula>) this.lastedFormulae.clone();
	}
	
	public ArrayList<LastsFormula> getLastsFormulae() {
		return (ArrayList<LastsFormula>) this.lastsFormulae.clone();
	}
	
	public ArrayList<WithinPFormula> getWithinpFormulae() {
		return (ArrayList<WithinPFormula>) this.withinPFormulae.clone();
	}
	
	public ArrayList<WithinFFormula> getWithinfFormulae() {
		return (ArrayList<WithinFFormula>) this.withinFFormulae.clone();
	}
	
	public ArrayList<FutrFormula> getFutrFormulae() {
		return (ArrayList<FutrFormula>) this.futrFormulae.clone();
	}
	
	public ArrayList<SinceFormula> getSinceFormulae() {
		return (ArrayList<SinceFormula>) this.sinceFormulae.clone();
	}
	
	public ArrayList<AlwPFormula> getAlwPFormulae() {
		return (ArrayList<AlwPFormula>) this.alwPFormulae.clone();
	}
	
	public ArrayList<AlwFFormula> getAlwFFormulae() {
		return (ArrayList<AlwFFormula>) this.alwFFormulae.clone();
	}
	
	public ArrayList<UntilFormula> getUntilFormulae() {
		return (ArrayList<UntilFormula>) this.untilFormulae.clone();
	}
	
	public ArrayList<SomfFormula> getSomfFormulae() {
		return (ArrayList<SomfFormula>) this.somfFormulae.clone();
	}
	
	public ArrayList<SompFormula> getSompFormulae() {
		return (ArrayList<SompFormula>) this.sompFormulae.clone();
	}
	
	public ArrayList<UntilWFormula> getUntilWFormulae() {
		return (ArrayList<UntilWFormula>) this.untilwFormulae.clone();
	}
	
	public ArrayList<NowFormula> getNowFormulae() {
		return (ArrayList<NowFormula>) this.nowFormulae.clone();
	}
	
	public void resetData() {
		this.pastVarTable.clear();
		this.futrFormulae.clear();
		this.lastedFormulae.clear();
		this.lastsFormulae.clear();
		this.withinPFormulae.clear();
		this.withinFFormulae.clear();
		this.sinceFormulae.clear();
		this.alwPFormulae.clear();
		this.alwFFormulae.clear();
		this.untilFormulae.clear();
		this.somfFormulae.clear();
		this.sompFormulae.clear();
		this.untilwFormulae.clear();
		this.nowFormulae.clear();
	} 
	
	public T2PTreeParser(Map<String, VariableInfo> varTable, Map<String, Integer> constTable) {
		this();
		this.varTable=varTable;
		this.constTable=constTable;
		this.pastVarTable=new HashMap<String,Integer>(varTable.size());
		this.futrFormulae=new ArrayList<FutrFormula>();
		this.lastedFormulae=new ArrayList<LastedFormula>();
		this.lastsFormulae=new ArrayList<LastsFormula>();
		this.withinPFormulae = new ArrayList<WithinPFormula>();
		this.withinFFormulae = new ArrayList<WithinFFormula>();
		this.sinceFormulae= new ArrayList<SinceFormula>();
		this.alwPFormulae= new ArrayList<AlwPFormula>();
		this.alwFFormulae= new ArrayList<AlwFFormula>();
		this.untilFormulae= new ArrayList<UntilFormula>();
		this.somfFormulae= new ArrayList<SomfFormula>();
		this.sompFormulae= new ArrayList<SompFormula>();
		this.untilwFormulae= new ArrayList<UntilWFormula>();
		this.nowFormulae= new ArrayList<NowFormula>();
	}
public T2PTreeParser() {
	tokenNames = _tokenNames;
}

	public final TrioFormula  trioformula(AST _t) throws RecognitionException {
		TrioFormula result;
		
		AST trioformula_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		
			result=null;
			TrioFormula s=null;
			TrioFormula t=null;
			TrioFormula s1=null;
			TrioFormula t1=null;
			TermFormula a=null;
			TermFormula b=null;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case IFF:
			{
				AST __t905 = _t;
				AST tmp1_AST_in = (AST)_t;
				match(_t,IFF);
				_t = _t.getFirstChild();
				s=trioformula(_t);
				_t = _retTree;
				t=trioformula(_t);
				_t = _retTree;
				_t = __t905;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
						//TODO translaton
					System.out.println("IFF node");
					
				}
				break;
			}
			case OR:
			{
				AST __t918 = _t;
				AST tmp2_AST_in = (AST)_t;
				match(_t,OR);
				_t = _t.getFirstChild();
				s=trioformula(_t);
				_t = _retTree;
				t=trioformula(_t);
				_t = _retTree;
				_t = __t918;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
						result=new OrFormula(s,t);
					
					
				}
				break;
			}
			case OR_ROOT:
			{
				AST __t919 = _t;
				AST tmp3_AST_in = (AST)_t;
				match(_t,OR_ROOT);
				_t = _t.getFirstChild();
				s=trioformula(_t);
				_t = _retTree;
				t=trioformula(_t);
				_t = _retTree;
				_t = __t919;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
						result = new OrRootFormula(s,t);
					
				}
				break;
			}
			case AND:
			{
				AST __t920 = _t;
				AST tmp4_AST_in = (AST)_t;
				match(_t,AND);
				_t = _t.getFirstChild();
				s=trioformula(_t);
				_t = _retTree;
				t=trioformula(_t);
				_t = _retTree;
				_t = __t920;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
						result=new AndFormula(s,t);
					
				}
				break;
			}
			case AND_ROOT:
			{
				AST __t921 = _t;
				AST tmp5_AST_in = (AST)_t;
				match(_t,AND_ROOT);
				_t = _t.getFirstChild();
				s=trioformula(_t);
				_t = _retTree;
				t=trioformula(_t);
				_t = _retTree;
				_t = __t921;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					result = new AndRootFormula(s,t);
					
				}
				break;
			}
			case NOT:
			{
				AST __t922 = _t;
				AST tmp6_AST_in = (AST)_t;
				match(_t,NOT);
				_t = _t.getFirstChild();
				s=trioformula(_t);
				_t = _retTree;
				_t = __t922;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
						/* preprocessing guarantee us that this method is invoked
						 only on operators for which !OP(A) = OP(!A)
						*/
						s.negate();
						result=s;
						
				}
				break;
			}
			case EQ:
			{
				AST __t923 = _t;
				AST tmp7_AST_in = (AST)_t;
				match(_t,EQ);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				b=term(_t);
				_t = _retTree;
				_t = __t923;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							result= new ExpressionFormula(a,b, "==");
						
				}
				break;
			}
			case NEQ:
			{
				AST __t924 = _t;
				AST tmp8_AST_in = (AST)_t;
				match(_t,NEQ);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				b=term(_t);
				_t = _retTree;
				_t = __t924;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							result= new ExpressionFormula(a,b, "!=");
						
				}
				break;
			}
			case LT:
			{
				AST __t925 = _t;
				AST tmp9_AST_in = (AST)_t;
				match(_t,LT);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				b=term(_t);
				_t = _retTree;
				_t = __t925;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							result= new ExpressionFormula(a,b, "<");
						
				}
				break;
			}
			case GT:
			{
				AST __t926 = _t;
				AST tmp10_AST_in = (AST)_t;
				match(_t,GT);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				b=term(_t);
				_t = _retTree;
				_t = __t926;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							result= new ExpressionFormula(a,b, ">");
						
				}
				break;
			}
			case GTE:
			{
				AST __t927 = _t;
				AST tmp11_AST_in = (AST)_t;
				match(_t,GTE);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				b=term(_t);
				_t = _retTree;
				_t = __t927;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							result= new ExpressionFormula(a,b, ">=");
						
				}
				break;
			}
			case LTE:
			{
				AST __t928 = _t;
				AST tmp12_AST_in = (AST)_t;
				match(_t,LTE);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				b=term(_t);
				_t = _retTree;
				_t = __t928;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							result= new ExpressionFormula(a,b, "<=");
						
				}
				break;
			}
			case ALWFE:
			{
				AST __t929 = _t;
				AST tmp13_AST_in = (AST)_t;
				match(_t,ALWFE);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				_t = __t929;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							this.alwFCounter++;
								AlwFFormula temp  = new AlwFFormula(t, this.alwFCounter, false );
								this.alwFFormulae.add(temp);
								result= temp;
						
				}
				break;
			}
			case ALWFI:
			{
				AST __t930 = _t;
				AST tmp14_AST_in = (AST)_t;
				match(_t,ALWFI);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				_t = __t930;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							this.alwFCounter++;
								AlwFFormula temp  = new AlwFFormula(t, this.alwFCounter, true );
								this.alwFFormulae.add(temp);
								result= temp;
						
				}
				break;
			}
			case ALWPE:
			{
				AST __t931 = _t;
				AST tmp15_AST_in = (AST)_t;
				match(_t,ALWPE);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				_t = __t931;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							this.alwPCounter++;
								AlwPFormula temp  = new AlwPFormula(t, this.alwPCounter, false );
								this.alwPFormulae.add(temp);
								result= temp;
						
				}
				break;
			}
			case ALWPI:
			{
				AST __t932 = _t;
				AST tmp16_AST_in = (AST)_t;
				match(_t,ALWPI);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				_t = __t932;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							this.alwPCounter++;
								AlwPFormula temp  = new AlwPFormula(t, this.alwPCounter, true );
								this.alwPFormulae.add(temp);
								result= temp;
						
				}
				break;
			}
			case SOMFE:
			{
				AST __t933 = _t;
				AST tmp17_AST_in = (AST)_t;
				match(_t,SOMFE);
				_t = _t.getFirstChild();
				s=trioformula(_t);
				_t = _retTree;
				_t = __t933;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							this.somfCounter++;
								SomfFormula temp  = new SomfFormula(s, this.somfCounter, false);
								this.somfFormulae.add(temp);
								result = temp;
						
				}
				break;
			}
			case SOMFI:
			{
				AST __t934 = _t;
				AST tmp18_AST_in = (AST)_t;
				match(_t,SOMFI);
				_t = _t.getFirstChild();
				s=trioformula(_t);
				_t = _retTree;
				_t = __t934;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							this.somfCounter++;
								SomfFormula temp  = new SomfFormula(s, this.somfCounter, true);
								this.somfFormulae.add(temp);
								result = temp;
						
				}
				break;
			}
			case SOMPE:
			{
				AST __t935 = _t;
				AST tmp19_AST_in = (AST)_t;
				match(_t,SOMPE);
				_t = _t.getFirstChild();
				s=trioformula(_t);
				_t = _retTree;
				_t = __t935;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							this.sompCounter++;
								SompFormula temp  = new SompFormula(s, this.sompCounter, false);
								this.sompFormulae.add(temp);
								result = temp;
						
				}
				break;
			}
			case SOMPI:
			{
				AST __t936 = _t;
				AST tmp20_AST_in = (AST)_t;
				match(_t,SOMPI);
				_t = _t.getFirstChild();
				s=trioformula(_t);
				_t = _retTree;
				_t = __t936;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							this.sompCounter++;
								SompFormula temp  = new SompFormula(s, this.sompCounter, true);
								this.sompFormulae.add(temp);
								result = temp;
						
				}
				break;
			}
			case NOWON:
			{
				AST __t937 = _t;
				AST tmp21_AST_in = (AST)_t;
				match(_t,NOWON);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				_t = __t937;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							//treat as Futr(A,1)
							this.futrCounter++;
								FutrFormula temp;
								temp  = new FutrFormula(t,new TermFormula(1), this.futrCounter, TranslationUtilities.Lasting.no, 0, this.constTable);
								this.futrFormulae.add(temp);
								result = temp;
						
				}
				break;
			}
			case UPTONOW:
			{
				AST __t938 = _t;
				AST tmp22_AST_in = (AST)_t;
				match(_t,UPTONOW);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				_t = __t938;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							//System.out.println("UPTONOW formula");
							//treat as Past(A,1)
							PastFormula temp = new PastFormula(t,new TermFormula(1), this.constTable);
								String var  = temp.getVar();
								updatePastVarTable(temp,var);
								result=temp;
						
				}
				break;
			}
			case BECOMES:
			{
				AST __t939 = _t;
				AST tmp23_AST_in = (AST)_t;
				match(_t,BECOMES);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				_t = __t939;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							//treat as Past(not A, 1) and A
							//actually build only the Past node
							t.negate();
							PastFormula temp = new PastFormula(t,new TermFormula(1), this.constTable);
							String var  = temp.getVar();
								updatePastVarTable(temp,var);
							result=temp;
						
				}
				break;
			}
			case UNTIL:
			{
				AST __t940 = _t;
				AST tmp24_AST_in = (AST)_t;
				match(_t,UNTIL);
				_t = _t.getFirstChild();
				s=trioformula(_t);
				_t = _retTree;
				t=trioformula(_t);
				_t = _retTree;
				_t = __t940;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
						this.untilCounter++;
								UntilFormula temp  = new UntilFormula(s,t, this.untilCounter);
								this.untilFormulae.add(temp);
								result = temp;
					
				}
				break;
			}
			case UNTILW:
			{
				AST __t941 = _t;
				AST tmp25_AST_in = (AST)_t;
				match(_t,UNTILW);
				_t = _t.getFirstChild();
				s=trioformula(_t);
				_t = _retTree;
				t=trioformula(_t);
				_t = _retTree;
				_t = __t941;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
						this.untilwCounter++;
								UntilWFormula temp  = new UntilWFormula(s,t, this.untilwCounter);
								this.untilwFormulae.add(temp);
								result = temp;
					
				}
				break;
			}
			case SINCE:
			{
				AST __t942 = _t;
				AST tmp26_AST_in = (AST)_t;
				match(_t,SINCE);
				_t = _t.getFirstChild();
				s=trioformula(_t);
				_t = _retTree;
				t=trioformula(_t);
				_t = _retTree;
				_t = __t942;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
						this.sinceCounter++;
								SinceFormula temp  = new SinceFormula(s,t, this.sinceCounter, false);
								this.sinceFormulae.add(temp);
								result = temp;
					
				}
				break;
			}
			case SINCEW:
			{
				AST __t943 = _t;
				AST tmp27_AST_in = (AST)_t;
				match(_t,SINCEW);
				_t = _t.getFirstChild();
				s=trioformula(_t);
				_t = _retTree;
				t=trioformula(_t);
				_t = _retTree;
				_t = __t943;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
						this.sinceCounter++;
								SinceFormula temp  = new SinceFormula(s,t, this.sinceCounter, true);
								this.sinceFormulae.add(temp);
								result = temp;
					
				}
				break;
			}
			case DIST:
			{
				AST __t944 = _t;
				AST tmp28_AST_in = (AST)_t;
				match(_t,DIST);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				a=term(_t);
				_t = _retTree;
				_t = __t944;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					// retrieve TermFormula value
					String numericValue = a.eval();
					int value;
					// suppose a represents a number
					try {
					value = Integer.parseInt(numericValue);
					} 
					//here t is not a number; check in constant Table
					catch (NumberFormatException ex){
					if (constTable.containsKey(numericValue)) {
					value=constTable.get(numericValue);
					} else {
					System.out.println("No suitable numeric instantion found for " + numericValue);
					value=0;
					}
					}
					if (value > 0) {
					//build a Futr Formula
					this.futrCounter++;
					FutrFormula temp;
					temp  = new FutrFormula(t,a, this.futrCounter, TranslationUtilities.Lasting.no, 0, this.constTable);
					this.futrFormulae.add(temp);
					result = temp;
					} else if (value < 0) {
					// build a past formula
					PastFormula temp = new PastFormula(t,new TermFormula(-value), this.constTable);
					String var  = temp.getVar();
					updatePastVarTable(temp,var);
					result=temp;
					} else {
					//expression formula
					result=t;
					}
					
				}
				break;
			}
			case FUTR:
			{
				AST __t945 = _t;
				AST tmp29_AST_in = (AST)_t;
				match(_t,FUTR);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				a=term(_t);
				_t = _retTree;
				_t = __t945;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					this.futrCounter++;
					FutrFormula temp;
					if (t instanceof LastsFormula) {
					LastsFormula lf = (LastsFormula) t;
					lf.setProcessStatusInFutr();
					temp  = new FutrFormula(t,a, this.futrCounter, lf.getInterval(), lf.getId(), this.constTable);
					} else if (t instanceof UntilWFormula) {
					UntilWFormula uwf = (UntilWFormula) t;
					temp  = new FutrFormula(uwf,a, this.futrCounter, TranslationUtilities.Lasting.untilw, uwf.getId(), this.constTable);
					} else {
					temp  = new FutrFormula(t,a, this.futrCounter, TranslationUtilities.Lasting.no, 0, this.constTable);
					}
					this.futrFormulae.add(temp);
					result = temp;
					
				}
				break;
			}
			case LASTSEE:
			{
				AST __t971 = _t;
				AST tmp30_AST_in = (AST)_t;
				match(_t,LASTSEE);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				a=term(_t);
				_t = _retTree;
				_t = __t971;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					this.lastsCounter++;
					LastsFormula temp  = new LastsFormula(t,a, this.lastsCounter, TranslationUtilities.Lasting.ee, this.constTable);
					this.lastsFormulae.add(temp);
					result = temp;
					
				}
				break;
			}
			case LASTSEI:
			{
				AST __t972 = _t;
				AST tmp31_AST_in = (AST)_t;
				match(_t,LASTSEI);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				a=term(_t);
				_t = _retTree;
				_t = __t972;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					this.lastsCounter++;
					LastsFormula temp  = new LastsFormula(t,a, this.lastsCounter, TranslationUtilities.Lasting.ei, this.constTable);
					this.lastsFormulae.add(temp);
					result = temp;
					
					
				}
				break;
			}
			case LASTSIE:
			{
				AST __t973 = _t;
				AST tmp32_AST_in = (AST)_t;
				match(_t,LASTSIE);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				a=term(_t);
				_t = _retTree;
				_t = __t973;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					this.lastsCounter++;
					LastsFormula temp  = new LastsFormula(t,a, this.lastsCounter, TranslationUtilities.Lasting.ie, this.constTable);
					this.lastsFormulae.add(temp);
					result = temp;
					
				}
				break;
			}
			case LASTSII:
			{
				AST __t974 = _t;
				AST tmp33_AST_in = (AST)_t;
				match(_t,LASTSII);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				a=term(_t);
				_t = _retTree;
				_t = __t974;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					this.lastsCounter++;
					LastsFormula temp  = new LastsFormula(t,a, this.lastsCounter, TranslationUtilities.Lasting.ii, this.constTable);
					this.lastsFormulae.add(temp);
					result = temp;
					
				}
				break;
			}
			case LASTEDEE:
			{
				AST __t975 = _t;
				AST tmp34_AST_in = (AST)_t;
				match(_t,LASTEDEE);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				a=term(_t);
				_t = _retTree;
				_t = __t975;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					this.lastedCounter++;
					LastedFormula temp  = new LastedFormula(t,a, 0, this.lastedCounter, TranslationUtilities.Lasting.ee, this.constTable);
					this.lastedFormulae.add(temp);
					String var  = temp.getVar();
					updatePastVarTable(temp,var);
					result = temp;
					
				}
				break;
			}
			case LASTEDEI:
			{
				AST __t976 = _t;
				AST tmp35_AST_in = (AST)_t;
				match(_t,LASTEDEI);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				a=term(_t);
				_t = _retTree;
				_t = __t976;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					this.lastedCounter++;
					LastedFormula temp  = new LastedFormula(t,a, 0, this.lastedCounter, TranslationUtilities.Lasting.ei, this.constTable);
					this.lastedFormulae.add(temp);
					String var  = temp.getVar();
					updatePastVarTable(temp,var);
					result = temp;
					
				}
				break;
			}
			case LASTEDIE:
			{
				AST __t977 = _t;
				AST tmp36_AST_in = (AST)_t;
				match(_t,LASTEDIE);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				a=term(_t);
				_t = _retTree;
				_t = __t977;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					this.lastedCounter++;
					LastedFormula temp  = new LastedFormula(t,a, 0, this.lastedCounter, TranslationUtilities.Lasting.ie, this.constTable);
					this.lastedFormulae.add(temp);
					String var  = temp.getVar();
					updatePastVarTable(temp,var);
					result = temp;
					
				}
				break;
			}
			case LASTEDII:
			{
				AST __t978 = _t;
				AST tmp37_AST_in = (AST)_t;
				match(_t,LASTEDII);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				a=term(_t);
				_t = _retTree;
				_t = __t978;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					this.lastedCounter++;
					LastedFormula temp  = new LastedFormula(t,a, 0, this.lastedCounter, TranslationUtilities.Lasting.ii, this.constTable);
					this.lastedFormulae.add(temp);
					String var  = temp.getVar();
					updatePastVarTable(temp,var);
					result = temp;
					
				}
				break;
			}
			case NOW:
			{
				AST __t1043 = _t;
				AST tmp38_AST_in = (AST)_t;
				match(_t,NOW);
				_t = _t.getFirstChild();
				t=trioformula(_t);
				_t = _retTree;
				_t = __t1043;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
								this.nowCounter++;
								NowFormula temp= new NowFormula(t, this.nowCounter++);
								this.nowFormulae.add(temp);
								result=temp;
							
				}
				break;
			}
			case LPAREN:
			{
				AST __t1044 = _t;
				AST tmp39_AST_in = (AST)_t;
				match(_t,LPAREN);
				_t = _t.getFirstChild();
				s=trioformula(_t);
				_t = _retTree;
				_t = __t1044;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					s.parenthesize();
					result=s;
					
				}
				break;
			}
			default:
				boolean synPredMatched908 = false;
				if (((_t.getType()==IFF_ROOT))) {
					AST __t908 = _t;
					synPredMatched908 = true;
					inputState.guessing++;
					try {
						{
						AST __t907 = _t;
						AST tmp40_AST_in = (AST)_t;
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
						_t = __t907;
						_t = _t.getNextSibling();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched908 = false;
					}
					_t = __t908;
inputState.guessing--;
				}
				if ( synPredMatched908 ) {
					AST __t909 = _t;
					AST tmp41_AST_in = (AST)_t;
					match(_t,IFF_ROOT);
					_t = _t.getFirstChild();
					s=trioformula(_t);
					_t = _retTree;
					t=trioformula(_t);
					_t = _retTree;
					s1=trioformula(_t);
					_t = _retTree;
					t1=trioformula(_t);
					_t = _retTree;
					_t = __t909;
					_t = _t.getNextSibling();
					if ( inputState.guessing==0 ) {
						
							result = new IffRootFormula(s,t,s1,t1);
						
					}
				}
				else {
					boolean synPredMatched912 = false;
					if (((_t.getType()==IFF_ROOT))) {
						AST __t912 = _t;
						synPredMatched912 = true;
						inputState.guessing++;
						try {
							{
							AST __t911 = _t;
							AST tmp42_AST_in = (AST)_t;
							match(_t,IFF_ROOT);
							_t = _t.getFirstChild();
							trioformula(_t);
							_t = _retTree;
							trioformula(_t);
							_t = _retTree;
							trioformula(_t);
							_t = _retTree;
							_t = __t911;
							_t = _t.getNextSibling();
							}
						}
						catch (RecognitionException pe) {
							synPredMatched912 = false;
						}
						_t = __t912;
inputState.guessing--;
					}
					if ( synPredMatched912 ) {
						AST __t913 = _t;
						AST tmp43_AST_in = (AST)_t;
						match(_t,IFF_ROOT);
						_t = _t.getFirstChild();
						s=trioformula(_t);
						_t = _retTree;
						t=trioformula(_t);
						_t = _retTree;
						s1=trioformula(_t);
						_t = _retTree;
						_t = __t913;
						_t = _t.getNextSibling();
						if ( inputState.guessing==0 ) {
							
								result = new IffRootFormula(s,t,s1);
							
						}
					}
					else {
						boolean synPredMatched916 = false;
						if (((_t.getType()==IFF_ROOT))) {
							AST __t916 = _t;
							synPredMatched916 = true;
							inputState.guessing++;
							try {
								{
								AST __t915 = _t;
								AST tmp44_AST_in = (AST)_t;
								match(_t,IFF_ROOT);
								_t = _t.getFirstChild();
								trioformula(_t);
								_t = _retTree;
								trioformula(_t);
								_t = _retTree;
								_t = __t915;
								_t = _t.getNextSibling();
								}
							}
							catch (RecognitionException pe) {
								synPredMatched916 = false;
							}
							_t = __t916;
inputState.guessing--;
						}
						if ( synPredMatched916 ) {
							AST __t917 = _t;
							AST tmp45_AST_in = (AST)_t;
							match(_t,IFF_ROOT);
							_t = _t.getFirstChild();
							s=trioformula(_t);
							_t = _retTree;
							t=trioformula(_t);
							_t = _retTree;
							_t = __t917;
							_t = _t.getNextSibling();
							if ( inputState.guessing==0 ) {
								
									result = new IffRootFormula(s,t);
								
							}
						}
						else {
							boolean synPredMatched949 = false;
							if (((_t.getType()==PAST))) {
								AST __t949 = _t;
								synPredMatched949 = true;
								inputState.guessing++;
								try {
									{
									AST __t947 = _t;
									AST tmp46_AST_in = (AST)_t;
									match(_t,PAST);
									_t = _t.getFirstChild();
									AST __t948 = _t;
									AST tmp47_AST_in = (AST)_t;
									match(_t,LASTEDEE);
									_t = _t.getFirstChild();
									trioformula(_t);
									_t = _retTree;
									term(_t);
									_t = _retTree;
									_t = __t948;
									_t = _t.getNextSibling();
									term(_t);
									_t = _retTree;
									_t = __t947;
									_t = _t.getNextSibling();
									}
								}
								catch (RecognitionException pe) {
									synPredMatched949 = false;
								}
								_t = __t949;
inputState.guessing--;
							}
							if ( synPredMatched949 ) {
								AST __t950 = _t;
								AST tmp48_AST_in = (AST)_t;
								match(_t,PAST);
								_t = _t.getFirstChild();
								AST __t951 = _t;
								AST tmp49_AST_in = (AST)_t;
								match(_t,LASTEDEE);
								_t = _t.getFirstChild();
								t=trioformula(_t);
								_t = _retTree;
								a=term(_t);
								_t = _retTree;
								_t = __t951;
								_t = _t.getNextSibling();
								b=term(_t);
								_t = _retTree;
								_t = __t950;
								_t = _t.getNextSibling();
								if ( inputState.guessing==0 ) {
									
											this.lastedCounter++;
											// get past constant -> lasted formula time instant
											int pastConstant = term2Constant(b);
									LastedFormula temp  = new LastedFormula(t, new TermFormula(Integer.parseInt(a.eval())+pastConstant), pastConstant, this.lastedCounter, TranslationUtilities.Lasting.ee, this.constTable);
									this.lastedFormulae.add(temp);
									String var  = temp.getVar();
									updatePastVarTable(temp,var);
									result = temp;
										
								}
							}
							else {
								boolean synPredMatched955 = false;
								if (((_t.getType()==PAST))) {
									AST __t955 = _t;
									synPredMatched955 = true;
									inputState.guessing++;
									try {
										{
										AST __t953 = _t;
										AST tmp50_AST_in = (AST)_t;
										match(_t,PAST);
										_t = _t.getFirstChild();
										AST __t954 = _t;
										AST tmp51_AST_in = (AST)_t;
										match(_t,LASTEDEI);
										_t = _t.getFirstChild();
										trioformula(_t);
										_t = _retTree;
										term(_t);
										_t = _retTree;
										_t = __t954;
										_t = _t.getNextSibling();
										term(_t);
										_t = _retTree;
										_t = __t953;
										_t = _t.getNextSibling();
										}
									}
									catch (RecognitionException pe) {
										synPredMatched955 = false;
									}
									_t = __t955;
inputState.guessing--;
								}
								if ( synPredMatched955 ) {
									AST __t956 = _t;
									AST tmp52_AST_in = (AST)_t;
									match(_t,PAST);
									_t = _t.getFirstChild();
									AST __t957 = _t;
									AST tmp53_AST_in = (AST)_t;
									match(_t,LASTEDEI);
									_t = _t.getFirstChild();
									t=trioformula(_t);
									_t = _retTree;
									a=term(_t);
									_t = _retTree;
									_t = __t957;
									_t = _t.getNextSibling();
									b=term(_t);
									_t = _retTree;
									_t = __t956;
									_t = _t.getNextSibling();
									if ( inputState.guessing==0 ) {
										
												this.lastedCounter++;
												// get past constant -> lasted formula time instant
												int pastConstant = term2Constant(b);
										LastedFormula temp  = new LastedFormula(t, new TermFormula(Integer.parseInt(a.eval())+pastConstant), pastConstant, this.lastedCounter, TranslationUtilities.Lasting.ei, this.constTable);
										this.lastedFormulae.add(temp);
										String var  = temp.getVar();
										updatePastVarTable(temp,var);
										result = temp;
											
									}
								}
								else {
									boolean synPredMatched961 = false;
									if (((_t.getType()==PAST))) {
										AST __t961 = _t;
										synPredMatched961 = true;
										inputState.guessing++;
										try {
											{
											AST __t959 = _t;
											AST tmp54_AST_in = (AST)_t;
											match(_t,PAST);
											_t = _t.getFirstChild();
											AST __t960 = _t;
											AST tmp55_AST_in = (AST)_t;
											match(_t,LASTEDIE);
											_t = _t.getFirstChild();
											trioformula(_t);
											_t = _retTree;
											term(_t);
											_t = _retTree;
											_t = __t960;
											_t = _t.getNextSibling();
											term(_t);
											_t = _retTree;
											_t = __t959;
											_t = _t.getNextSibling();
											}
										}
										catch (RecognitionException pe) {
											synPredMatched961 = false;
										}
										_t = __t961;
inputState.guessing--;
									}
									if ( synPredMatched961 ) {
										AST __t962 = _t;
										AST tmp56_AST_in = (AST)_t;
										match(_t,PAST);
										_t = _t.getFirstChild();
										AST __t963 = _t;
										AST tmp57_AST_in = (AST)_t;
										match(_t,LASTEDIE);
										_t = _t.getFirstChild();
										t=trioformula(_t);
										_t = _retTree;
										a=term(_t);
										_t = _retTree;
										_t = __t963;
										_t = _t.getNextSibling();
										b=term(_t);
										_t = _retTree;
										_t = __t962;
										_t = _t.getNextSibling();
										if ( inputState.guessing==0 ) {
											
													this.lastedCounter++;
													// get past constant -> lasted formula time instant
													int pastConstant = term2Constant(b);
											LastedFormula temp  = new LastedFormula(t, new TermFormula(Integer.parseInt(a.eval())+pastConstant), pastConstant, this.lastedCounter, TranslationUtilities.Lasting.ie, this.constTable);
											this.lastedFormulae.add(temp);
											String var  = temp.getVar();
											updatePastVarTable(temp,var);
											result = temp;
												
										}
									}
									else {
										boolean synPredMatched967 = false;
										if (((_t.getType()==PAST))) {
											AST __t967 = _t;
											synPredMatched967 = true;
											inputState.guessing++;
											try {
												{
												AST __t965 = _t;
												AST tmp58_AST_in = (AST)_t;
												match(_t,PAST);
												_t = _t.getFirstChild();
												AST __t966 = _t;
												AST tmp59_AST_in = (AST)_t;
												match(_t,LASTEDII);
												_t = _t.getFirstChild();
												trioformula(_t);
												_t = _retTree;
												term(_t);
												_t = _retTree;
												_t = __t966;
												_t = _t.getNextSibling();
												term(_t);
												_t = _retTree;
												_t = __t965;
												_t = _t.getNextSibling();
												}
											}
											catch (RecognitionException pe) {
												synPredMatched967 = false;
											}
											_t = __t967;
inputState.guessing--;
										}
										if ( synPredMatched967 ) {
											AST __t968 = _t;
											AST tmp60_AST_in = (AST)_t;
											match(_t,PAST);
											_t = _t.getFirstChild();
											AST __t969 = _t;
											AST tmp61_AST_in = (AST)_t;
											match(_t,LASTEDII);
											_t = _t.getFirstChild();
											t=trioformula(_t);
											_t = _retTree;
											a=term(_t);
											_t = _retTree;
											_t = __t969;
											_t = _t.getNextSibling();
											b=term(_t);
											_t = _retTree;
											_t = __t968;
											_t = _t.getNextSibling();
											if ( inputState.guessing==0 ) {
												
														this.lastedCounter++;
														// get past constant -> lasted formula time instant
														int pastConstant = term2Constant(b);
														LastedFormula temp  = new LastedFormula(t, new TermFormula(Integer.parseInt(a.eval())+pastConstant), pastConstant, this.lastedCounter, TranslationUtilities.Lasting.ii, this.constTable);
												this.lastedFormulae.add(temp);
												String var  = temp.getVar();
												updatePastVarTable(temp,var);
												result = temp;
													
											}
										}
										else if ((_t.getType()==PAST)) {
											AST __t970 = _t;
											AST tmp62_AST_in = (AST)_t;
											match(_t,PAST);
											_t = _t.getFirstChild();
											t=trioformula(_t);
											_t = _retTree;
											a=term(_t);
											_t = _retTree;
											_t = __t970;
											_t = _t.getNextSibling();
											if ( inputState.guessing==0 ) {
												
												PastFormula temp = new PastFormula(t,a, this.constTable);
												String var  = temp.getVar();
												updatePastVarTable(temp,var);
												result=temp;
												
											}
										}
										else {
											boolean synPredMatched981 = false;
											if (((_t.getType()==WITHINFEE))) {
												AST __t981 = _t;
												synPredMatched981 = true;
												inputState.guessing++;
												try {
													{
													AST __t980 = _t;
													AST tmp63_AST_in = (AST)_t;
													match(_t,WITHINFEE);
													_t = _t.getFirstChild();
													trioformula(_t);
													_t = _retTree;
													term(_t);
													_t = _retTree;
													term(_t);
													_t = _retTree;
													_t = __t980;
													_t = _t.getNextSibling();
													}
												}
												catch (RecognitionException pe) {
													synPredMatched981 = false;
												}
												_t = __t981;
inputState.guessing--;
											}
											if ( synPredMatched981 ) {
												AST __t982 = _t;
												AST tmp64_AST_in = (AST)_t;
												match(_t,WITHINFEE);
												_t = _t.getFirstChild();
												t=trioformula(_t);
												_t = _retTree;
												a=term(_t);
												_t = _retTree;
												b=term(_t);
												_t = _retTree;
												_t = __t982;
												_t = _t.getNextSibling();
												if ( inputState.guessing==0 ) {
													
													this.withinFCounter++;
													WithinFFormula temp = new WithinFFormula(t,a, term2Constant(b), this.withinFCounter, TranslationUtilities.Lasting.ee, this.constTable);
													this.withinFFormulae.add(temp);
													result = temp;
													
												}
											}
											else {
												boolean synPredMatched985 = false;
												if (((_t.getType()==WITHINFEE))) {
													AST __t985 = _t;
													synPredMatched985 = true;
													inputState.guessing++;
													try {
														{
														AST __t984 = _t;
														AST tmp65_AST_in = (AST)_t;
														match(_t,WITHINFEE);
														_t = _t.getFirstChild();
														trioformula(_t);
														_t = _retTree;
														term(_t);
														_t = _retTree;
														_t = __t984;
														_t = _t.getNextSibling();
														}
													}
													catch (RecognitionException pe) {
														synPredMatched985 = false;
													}
													_t = __t985;
inputState.guessing--;
												}
												if ( synPredMatched985 ) {
													AST __t986 = _t;
													AST tmp66_AST_in = (AST)_t;
													match(_t,WITHINFEE);
													_t = _t.getFirstChild();
													t=trioformula(_t);
													_t = _retTree;
													a=term(_t);
													_t = _retTree;
													_t = __t986;
													_t = _t.getNextSibling();
													if ( inputState.guessing==0 ) {
														
														this.withinFCounter++;
														WithinFFormula temp = new WithinFFormula(t,a, this.withinFCounter, TranslationUtilities.Lasting.ee, this.constTable);
														this.withinFFormulae.add(temp);
														result = temp;
														
													}
												}
												else {
													boolean synPredMatched989 = false;
													if (((_t.getType()==WITHINFEI))) {
														AST __t989 = _t;
														synPredMatched989 = true;
														inputState.guessing++;
														try {
															{
															AST __t988 = _t;
															AST tmp67_AST_in = (AST)_t;
															match(_t,WITHINFEI);
															_t = _t.getFirstChild();
															trioformula(_t);
															_t = _retTree;
															term(_t);
															_t = _retTree;
															term(_t);
															_t = _retTree;
															_t = __t988;
															_t = _t.getNextSibling();
															}
														}
														catch (RecognitionException pe) {
															synPredMatched989 = false;
														}
														_t = __t989;
inputState.guessing--;
													}
													if ( synPredMatched989 ) {
														AST __t990 = _t;
														AST tmp68_AST_in = (AST)_t;
														match(_t,WITHINFEI);
														_t = _t.getFirstChild();
														t=trioformula(_t);
														_t = _retTree;
														a=term(_t);
														_t = _retTree;
														b=term(_t);
														_t = _retTree;
														_t = __t990;
														_t = _t.getNextSibling();
														if ( inputState.guessing==0 ) {
															
															this.withinFCounter++;
															WithinFFormula temp = new WithinFFormula(t,a,term2Constant(b), this.withinFCounter, TranslationUtilities.Lasting.ei, this.constTable);
															this.withinFFormulae.add(temp);
															result = temp;
															
														}
													}
													else {
														boolean synPredMatched993 = false;
														if (((_t.getType()==WITHINFEI))) {
															AST __t993 = _t;
															synPredMatched993 = true;
															inputState.guessing++;
															try {
																{
																AST __t992 = _t;
																AST tmp69_AST_in = (AST)_t;
																match(_t,WITHINFEI);
																_t = _t.getFirstChild();
																trioformula(_t);
																_t = _retTree;
																term(_t);
																_t = _retTree;
																_t = __t992;
																_t = _t.getNextSibling();
																}
															}
															catch (RecognitionException pe) {
																synPredMatched993 = false;
															}
															_t = __t993;
inputState.guessing--;
														}
														if ( synPredMatched993 ) {
															AST __t994 = _t;
															AST tmp70_AST_in = (AST)_t;
															match(_t,WITHINFEI);
															_t = _t.getFirstChild();
															t=trioformula(_t);
															_t = _retTree;
															a=term(_t);
															_t = _retTree;
															_t = __t994;
															_t = _t.getNextSibling();
															if ( inputState.guessing==0 ) {
																
																this.withinFCounter++;
																WithinFFormula temp = new WithinFFormula(t,a, this.withinFCounter, TranslationUtilities.Lasting.ei, this.constTable);
																this.withinFFormulae.add(temp);
																result = temp;
																
															}
														}
														else {
															boolean synPredMatched997 = false;
															if (((_t.getType()==WITHINFIE))) {
																AST __t997 = _t;
																synPredMatched997 = true;
																inputState.guessing++;
																try {
																	{
																	AST __t996 = _t;
																	AST tmp71_AST_in = (AST)_t;
																	match(_t,WITHINFIE);
																	_t = _t.getFirstChild();
																	trioformula(_t);
																	_t = _retTree;
																	term(_t);
																	_t = _retTree;
																	term(_t);
																	_t = _retTree;
																	_t = __t996;
																	_t = _t.getNextSibling();
																	}
																}
																catch (RecognitionException pe) {
																	synPredMatched997 = false;
																}
																_t = __t997;
inputState.guessing--;
															}
															if ( synPredMatched997 ) {
																AST __t998 = _t;
																AST tmp72_AST_in = (AST)_t;
																match(_t,WITHINFIE);
																_t = _t.getFirstChild();
																t=trioformula(_t);
																_t = _retTree;
																a=term(_t);
																_t = _retTree;
																b=term(_t);
																_t = _retTree;
																_t = __t998;
																_t = _t.getNextSibling();
																if ( inputState.guessing==0 ) {
																	
																	this.withinFCounter++;
																	WithinFFormula temp = new WithinFFormula(t,a, term2Constant(b),this.withinFCounter, TranslationUtilities.Lasting.ie, this.constTable);
																	this.withinFFormulae.add(temp);
																	result = temp;
																	
																}
															}
															else {
																boolean synPredMatched1001 = false;
																if (((_t.getType()==WITHINFIE))) {
																	AST __t1001 = _t;
																	synPredMatched1001 = true;
																	inputState.guessing++;
																	try {
																		{
																		AST __t1000 = _t;
																		AST tmp73_AST_in = (AST)_t;
																		match(_t,WITHINFIE);
																		_t = _t.getFirstChild();
																		trioformula(_t);
																		_t = _retTree;
																		term(_t);
																		_t = _retTree;
																		_t = __t1000;
																		_t = _t.getNextSibling();
																		}
																	}
																	catch (RecognitionException pe) {
																		synPredMatched1001 = false;
																	}
																	_t = __t1001;
inputState.guessing--;
																}
																if ( synPredMatched1001 ) {
																	AST __t1002 = _t;
																	AST tmp74_AST_in = (AST)_t;
																	match(_t,WITHINFIE);
																	_t = _t.getFirstChild();
																	t=trioformula(_t);
																	_t = _retTree;
																	a=term(_t);
																	_t = _retTree;
																	_t = __t1002;
																	_t = _t.getNextSibling();
																	if ( inputState.guessing==0 ) {
																		
																		this.withinFCounter++;
																		WithinFFormula temp = new WithinFFormula(t,a, this.withinFCounter, TranslationUtilities.Lasting.ie, this.constTable);
																		this.withinFFormulae.add(temp);
																		result = temp;
																		
																	}
																}
																else {
																	boolean synPredMatched1005 = false;
																	if (((_t.getType()==WITHINFII))) {
																		AST __t1005 = _t;
																		synPredMatched1005 = true;
																		inputState.guessing++;
																		try {
																			{
																			AST __t1004 = _t;
																			AST tmp75_AST_in = (AST)_t;
																			match(_t,WITHINFII);
																			_t = _t.getFirstChild();
																			trioformula(_t);
																			_t = _retTree;
																			term(_t);
																			_t = _retTree;
																			term(_t);
																			_t = _retTree;
																			_t = __t1004;
																			_t = _t.getNextSibling();
																			}
																		}
																		catch (RecognitionException pe) {
																			synPredMatched1005 = false;
																		}
																		_t = __t1005;
inputState.guessing--;
																	}
																	if ( synPredMatched1005 ) {
																		AST __t1006 = _t;
																		AST tmp76_AST_in = (AST)_t;
																		match(_t,WITHINFII);
																		_t = _t.getFirstChild();
																		t=trioformula(_t);
																		_t = _retTree;
																		a=term(_t);
																		_t = _retTree;
																		b=term(_t);
																		_t = _retTree;
																		_t = __t1006;
																		_t = _t.getNextSibling();
																		if ( inputState.guessing==0 ) {
																			
																			this.withinFCounter++;
																			WithinFFormula temp = new WithinFFormula(t,a, term2Constant(b),this.withinFCounter, TranslationUtilities.Lasting.ii, this.constTable);
																			this.withinFFormulae.add(temp);
																			result = temp;
																			
																		}
																	}
																	else {
																		boolean synPredMatched1009 = false;
																		if (((_t.getType()==WITHINFII))) {
																			AST __t1009 = _t;
																			synPredMatched1009 = true;
																			inputState.guessing++;
																			try {
																				{
																				AST __t1008 = _t;
																				AST tmp77_AST_in = (AST)_t;
																				match(_t,WITHINFII);
																				_t = _t.getFirstChild();
																				trioformula(_t);
																				_t = _retTree;
																				term(_t);
																				_t = _retTree;
																				_t = __t1008;
																				_t = _t.getNextSibling();
																				}
																			}
																			catch (RecognitionException pe) {
																				synPredMatched1009 = false;
																			}
																			_t = __t1009;
inputState.guessing--;
																		}
																		if ( synPredMatched1009 ) {
																			AST __t1010 = _t;
																			AST tmp78_AST_in = (AST)_t;
																			match(_t,WITHINFII);
																			_t = _t.getFirstChild();
																			t=trioformula(_t);
																			_t = _retTree;
																			a=term(_t);
																			_t = _retTree;
																			_t = __t1010;
																			_t = _t.getNextSibling();
																			if ( inputState.guessing==0 ) {
																				
																				this.withinFCounter++;
																				WithinFFormula temp = new WithinFFormula(t,a, this.withinFCounter, TranslationUtilities.Lasting.ii, this.constTable);
																				this.withinFFormulae.add(temp);
																				result = temp;
																				
																			}
																		}
																		else {
																			boolean synPredMatched1013 = false;
																			if (((_t.getType()==WITHINPEE))) {
																				AST __t1013 = _t;
																				synPredMatched1013 = true;
																				inputState.guessing++;
																				try {
																					{
																					AST __t1012 = _t;
																					AST tmp79_AST_in = (AST)_t;
																					match(_t,WITHINPEE);
																					_t = _t.getFirstChild();
																					trioformula(_t);
																					_t = _retTree;
																					term(_t);
																					_t = _retTree;
																					term(_t);
																					_t = _retTree;
																					_t = __t1012;
																					_t = _t.getNextSibling();
																					}
																				}
																				catch (RecognitionException pe) {
																					synPredMatched1013 = false;
																				}
																				_t = __t1013;
inputState.guessing--;
																			}
																			if ( synPredMatched1013 ) {
																				AST __t1014 = _t;
																				AST tmp80_AST_in = (AST)_t;
																				match(_t,WITHINPEE);
																				_t = _t.getFirstChild();
																				t=trioformula(_t);
																				_t = _retTree;
																				a=term(_t);
																				_t = _retTree;
																				b=term(_t);
																				_t = _retTree;
																				_t = __t1014;
																				_t = _t.getNextSibling();
																				if ( inputState.guessing==0 ) {
																					
																					this.withinPCounter++;
																					WithinPFormula temp  = new WithinPFormula(t,a, term2Constant(b),this.withinPCounter, TranslationUtilities.Lasting.ee, this.constTable);
																					this.withinPFormulae.add(temp);
																					String var  = temp.getVar();
																					updatePastVarTable(temp,var);
																					result = temp;
																					
																				}
																			}
																			else {
																				boolean synPredMatched1017 = false;
																				if (((_t.getType()==WITHINPEE))) {
																					AST __t1017 = _t;
																					synPredMatched1017 = true;
																					inputState.guessing++;
																					try {
																						{
																						AST __t1016 = _t;
																						AST tmp81_AST_in = (AST)_t;
																						match(_t,WITHINPEE);
																						_t = _t.getFirstChild();
																						trioformula(_t);
																						_t = _retTree;
																						term(_t);
																						_t = _retTree;
																						_t = __t1016;
																						_t = _t.getNextSibling();
																						}
																					}
																					catch (RecognitionException pe) {
																						synPredMatched1017 = false;
																					}
																					_t = __t1017;
inputState.guessing--;
																				}
																				if ( synPredMatched1017 ) {
																					AST __t1018 = _t;
																					AST tmp82_AST_in = (AST)_t;
																					match(_t,WITHINPEE);
																					_t = _t.getFirstChild();
																					t=trioformula(_t);
																					_t = _retTree;
																					a=term(_t);
																					_t = _retTree;
																					_t = __t1018;
																					_t = _t.getNextSibling();
																					if ( inputState.guessing==0 ) {
																						
																						this.withinPCounter++;
																						WithinPFormula temp  = new WithinPFormula(t,a, this.withinPCounter, TranslationUtilities.Lasting.ee, this.constTable);
																						this.withinPFormulae.add(temp);
																						String var  = temp.getVar();
																						updatePastVarTable(temp,var);
																						result = temp;
																						
																					}
																				}
																				else {
																					boolean synPredMatched1021 = false;
																					if (((_t.getType()==WITHINPEI))) {
																						AST __t1021 = _t;
																						synPredMatched1021 = true;
																						inputState.guessing++;
																						try {
																							{
																							AST __t1020 = _t;
																							AST tmp83_AST_in = (AST)_t;
																							match(_t,WITHINPEI);
																							_t = _t.getFirstChild();
																							trioformula(_t);
																							_t = _retTree;
																							term(_t);
																							_t = _retTree;
																							term(_t);
																							_t = _retTree;
																							_t = __t1020;
																							_t = _t.getNextSibling();
																							}
																						}
																						catch (RecognitionException pe) {
																							synPredMatched1021 = false;
																						}
																						_t = __t1021;
inputState.guessing--;
																					}
																					if ( synPredMatched1021 ) {
																						AST __t1022 = _t;
																						AST tmp84_AST_in = (AST)_t;
																						match(_t,WITHINPEI);
																						_t = _t.getFirstChild();
																						t=trioformula(_t);
																						_t = _retTree;
																						a=term(_t);
																						_t = _retTree;
																						b=term(_t);
																						_t = _retTree;
																						_t = __t1022;
																						_t = _t.getNextSibling();
																						if ( inputState.guessing==0 ) {
																							
																							this.withinPCounter++;
																							WithinPFormula temp  = new WithinPFormula(t,a, term2Constant(b),this.withinPCounter, TranslationUtilities.Lasting.ei, this.constTable);
																							this.withinPFormulae.add(temp);
																							String var  = temp.getVar();
																							updatePastVarTable(temp,var);
																							result = temp;
																							
																						}
																					}
																					else {
																						boolean synPredMatched1025 = false;
																						if (((_t.getType()==WITHINPEI))) {
																							AST __t1025 = _t;
																							synPredMatched1025 = true;
																							inputState.guessing++;
																							try {
																								{
																								AST __t1024 = _t;
																								AST tmp85_AST_in = (AST)_t;
																								match(_t,WITHINPEI);
																								_t = _t.getFirstChild();
																								trioformula(_t);
																								_t = _retTree;
																								term(_t);
																								_t = _retTree;
																								_t = __t1024;
																								_t = _t.getNextSibling();
																								}
																							}
																							catch (RecognitionException pe) {
																								synPredMatched1025 = false;
																							}
																							_t = __t1025;
inputState.guessing--;
																						}
																						if ( synPredMatched1025 ) {
																							AST __t1026 = _t;
																							AST tmp86_AST_in = (AST)_t;
																							match(_t,WITHINPEI);
																							_t = _t.getFirstChild();
																							t=trioformula(_t);
																							_t = _retTree;
																							a=term(_t);
																							_t = _retTree;
																							_t = __t1026;
																							_t = _t.getNextSibling();
																							if ( inputState.guessing==0 ) {
																								
																								this.withinPCounter++;
																								WithinPFormula temp  = new WithinPFormula(t,a, this.withinPCounter, TranslationUtilities.Lasting.ei, this.constTable);
																								this.withinPFormulae.add(temp);
																								String var  = temp.getVar();
																								updatePastVarTable(temp,var);
																								result = temp;
																								
																							}
																						}
																						else {
																							boolean synPredMatched1029 = false;
																							if (((_t.getType()==WITHINPIE))) {
																								AST __t1029 = _t;
																								synPredMatched1029 = true;
																								inputState.guessing++;
																								try {
																									{
																									AST __t1028 = _t;
																									AST tmp87_AST_in = (AST)_t;
																									match(_t,WITHINPIE);
																									_t = _t.getFirstChild();
																									trioformula(_t);
																									_t = _retTree;
																									term(_t);
																									_t = _retTree;
																									term(_t);
																									_t = _retTree;
																									_t = __t1028;
																									_t = _t.getNextSibling();
																									}
																								}
																								catch (RecognitionException pe) {
																									synPredMatched1029 = false;
																								}
																								_t = __t1029;
inputState.guessing--;
																							}
																							if ( synPredMatched1029 ) {
																								AST __t1030 = _t;
																								AST tmp88_AST_in = (AST)_t;
																								match(_t,WITHINPIE);
																								_t = _t.getFirstChild();
																								t=trioformula(_t);
																								_t = _retTree;
																								a=term(_t);
																								_t = _retTree;
																								b=term(_t);
																								_t = _retTree;
																								_t = __t1030;
																								_t = _t.getNextSibling();
																								if ( inputState.guessing==0 ) {
																									
																									this.withinPCounter++;
																									WithinPFormula temp  = new WithinPFormula(t,a, term2Constant(b),this.withinPCounter, TranslationUtilities.Lasting.ie, this.constTable);
																									this.withinPFormulae.add(temp);
																									String var  = temp.getVar();
																									updatePastVarTable(temp,var);
																									result = temp;
																									
																								}
																							}
																							else {
																								boolean synPredMatched1033 = false;
																								if (((_t.getType()==WITHINPIE))) {
																									AST __t1033 = _t;
																									synPredMatched1033 = true;
																									inputState.guessing++;
																									try {
																										{
																										AST __t1032 = _t;
																										AST tmp89_AST_in = (AST)_t;
																										match(_t,WITHINPIE);
																										_t = _t.getFirstChild();
																										trioformula(_t);
																										_t = _retTree;
																										term(_t);
																										_t = _retTree;
																										_t = __t1032;
																										_t = _t.getNextSibling();
																										}
																									}
																									catch (RecognitionException pe) {
																										synPredMatched1033 = false;
																									}
																									_t = __t1033;
inputState.guessing--;
																								}
																								if ( synPredMatched1033 ) {
																									AST __t1034 = _t;
																									AST tmp90_AST_in = (AST)_t;
																									match(_t,WITHINPIE);
																									_t = _t.getFirstChild();
																									t=trioformula(_t);
																									_t = _retTree;
																									a=term(_t);
																									_t = _retTree;
																									_t = __t1034;
																									_t = _t.getNextSibling();
																									if ( inputState.guessing==0 ) {
																										
																										this.withinPCounter++;
																										WithinPFormula temp  = new WithinPFormula(t,a, this.withinPCounter, TranslationUtilities.Lasting.ie, this.constTable);
																										this.withinPFormulae.add(temp);
																										String var  = temp.getVar();
																										updatePastVarTable(temp,var);
																										result = temp;
																										
																									}
																								}
																								else {
																									boolean synPredMatched1037 = false;
																									if (((_t.getType()==WITHINPII))) {
																										AST __t1037 = _t;
																										synPredMatched1037 = true;
																										inputState.guessing++;
																										try {
																											{
																											AST __t1036 = _t;
																											AST tmp91_AST_in = (AST)_t;
																											match(_t,WITHINPII);
																											_t = _t.getFirstChild();
																											trioformula(_t);
																											_t = _retTree;
																											term(_t);
																											_t = _retTree;
																											term(_t);
																											_t = _retTree;
																											_t = __t1036;
																											_t = _t.getNextSibling();
																											}
																										}
																										catch (RecognitionException pe) {
																											synPredMatched1037 = false;
																										}
																										_t = __t1037;
inputState.guessing--;
																									}
																									if ( synPredMatched1037 ) {
																										AST __t1038 = _t;
																										AST tmp92_AST_in = (AST)_t;
																										match(_t,WITHINPII);
																										_t = _t.getFirstChild();
																										t=trioformula(_t);
																										_t = _retTree;
																										a=term(_t);
																										_t = _retTree;
																										b=term(_t);
																										_t = _retTree;
																										_t = __t1038;
																										_t = _t.getNextSibling();
																										if ( inputState.guessing==0 ) {
																											
																											this.withinPCounter++;
																											WithinPFormula temp  = new WithinPFormula(t,a, term2Constant(b),this.withinPCounter, TranslationUtilities.Lasting.ii, this.constTable);
																											this.withinPFormulae.add(temp);
																											String var  = temp.getVar();
																											updatePastVarTable(temp,var);
																											result = temp;
																											
																											
																										}
																									}
																									else {
																										boolean synPredMatched1041 = false;
																										if (((_t.getType()==WITHINPII))) {
																											AST __t1041 = _t;
																											synPredMatched1041 = true;
																											inputState.guessing++;
																											try {
																												{
																												AST __t1040 = _t;
																												AST tmp93_AST_in = (AST)_t;
																												match(_t,WITHINPII);
																												_t = _t.getFirstChild();
																												trioformula(_t);
																												_t = _retTree;
																												term(_t);
																												_t = _retTree;
																												_t = __t1040;
																												_t = _t.getNextSibling();
																												}
																											}
																											catch (RecognitionException pe) {
																												synPredMatched1041 = false;
																											}
																											_t = __t1041;
inputState.guessing--;
																										}
																										if ( synPredMatched1041 ) {
																											AST __t1042 = _t;
																											AST tmp94_AST_in = (AST)_t;
																											match(_t,WITHINPII);
																											_t = _t.getFirstChild();
																											t=trioformula(_t);
																											_t = _retTree;
																											a=term(_t);
																											_t = _retTree;
																											_t = __t1042;
																											_t = _t.getNextSibling();
																											if ( inputState.guessing==0 ) {
																												
																												this.withinPCounter++;
																												WithinPFormula temp  = new WithinPFormula(t,a, this.withinPCounter, TranslationUtilities.Lasting.ii, this.constTable);
																												this.withinPFormulae.add(temp);
																												String var  = temp.getVar();
																												updatePastVarTable(temp,var);
																												result = temp;
																												
																											}
																										}
																									else {
																										throw new NoViableAltException(_t);
																									}
																									}}}}}}}}}}}}}}}}}}}}}}}
																								}
																								catch (RecognitionException ex) {
																									if (inputState.guessing==0) {
																										reportError(ex);
																										if (_t!=null) {_t = _t.getNextSibling();}
																									} else {
																									  throw ex;
																									}
																								}
																								_retTree = _t;
																								return result;
																							}
																							
	public final TermFormula  term(AST _t) throws RecognitionException {
		TermFormula value;
		
		AST term_AST_in = (_t == ASTNULL) ? null : (AST)_t;
		AST i = null;
		AST m = null;
		AST c = null;
		
			value=null;
			TermFormula a,b;
		
		
		try {      // for error handling
			if (_t==null) _t=ASTNULL;
			switch ( _t.getType()) {
			case PLUS:
			{
				AST __t1046 = _t;
				AST tmp95_AST_in = (AST)_t;
				match(_t,PLUS);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				b=term(_t);
				_t = _retTree;
				_t = __t1046;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					int s= Integer.parseInt(a.eval());
					int t= Integer.parseInt(b.eval());
					value=new TermFormula(s+t);
					
				}
				break;
			}
			case MINUS:
			{
				AST __t1047 = _t;
				AST tmp96_AST_in = (AST)_t;
				match(_t,MINUS);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				b=term(_t);
				_t = _retTree;
				_t = __t1047;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					int s= Integer.parseInt(a.eval());
					int t= Integer.parseInt(b.eval());
					value=new TermFormula(s-t);
					
				}
				break;
			}
			case DIV:
			{
				AST __t1048 = _t;
				AST tmp97_AST_in = (AST)_t;
				match(_t,DIV);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				b=term(_t);
				_t = _retTree;
				_t = __t1048;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					int s= Integer.parseInt(a.eval());
					int t= Integer.parseInt(b.eval());
					value=new TermFormula(s/t);
					
				}
				break;
			}
			case STAR:
			{
				AST __t1049 = _t;
				AST tmp98_AST_in = (AST)_t;
				match(_t,STAR);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				b=term(_t);
				_t = _retTree;
				_t = __t1049;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					int s= Integer.parseInt(a.eval());
					int t= Integer.parseInt(b.eval());
					value=new TermFormula(s*t);
					
				}
				break;
			}
			case MOD:
			{
				AST __t1050 = _t;
				AST tmp99_AST_in = (AST)_t;
				match(_t,MOD);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				b=term(_t);
				_t = _retTree;
				_t = __t1050;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					int s= Integer.parseInt(a.eval());
					int t= Integer.parseInt(b.eval());
					value=new TermFormula(s%t);
					
				}
				break;
			}
			case INTDIV:
			{
				AST __t1051 = _t;
				AST tmp100_AST_in = (AST)_t;
				match(_t,INTDIV);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				b=term(_t);
				_t = _retTree;
				_t = __t1051;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					int s= Integer.parseInt(a.eval());
					int t= Integer.parseInt(b.eval());
					value=new TermFormula(s/t);
					
				}
				break;
			}
			case POW:
			{
				AST __t1052 = _t;
				AST tmp101_AST_in = (AST)_t;
				match(_t,POW);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				b=term(_t);
				_t = _retTree;
				_t = __t1052;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					int s= Integer.parseInt(a.eval());
					int t= Integer.parseInt(b.eval());
					value=new TermFormula((int)Math.pow(s,t));
					
				}
				break;
			}
			case SIGN_MINUS:
			{
				AST __t1053 = _t;
				AST tmp102_AST_in = (AST)_t;
				match(_t,SIGN_MINUS);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				_t = __t1053;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					int s= Integer.parseInt(a.eval());
					value=new TermFormula(s*-1);
					
				}
				break;
			}
			case SIGN_PLUS:
			{
				AST __t1054 = _t;
				AST tmp103_AST_in = (AST)_t;
				match(_t,SIGN_PLUS);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				_t = __t1054;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					value=a;
					
				}
				break;
			}
			case NUMBER:
			{
				i = (AST)_t;
				match(_t,NUMBER);
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
						
							value = new TermFormula(Integer.parseInt(i.getText()));
						
				}
				break;
			}
			case VARIABLE:
			{
				m = (AST)_t;
				match(_t,VARIABLE);
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							String k= m.getText();
					value= new TermFormula(k);
						
				}
				break;
			}
			case CONSTANT:
			{
				c = (AST)_t;
				match(_t,CONSTANT);
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
							value= new TermFormula(this.constTable.get(c.getText()));
						
				}
				break;
			}
			case LPAREN:
			{
				AST __t1055 = _t;
				AST tmp104_AST_in = (AST)_t;
				match(_t,LPAREN);
				_t = _t.getFirstChild();
				a=term(_t);
				_t = _retTree;
				_t = __t1055;
				_t = _t.getNextSibling();
				if ( inputState.guessing==0 ) {
					
					value=a;
					
				}
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
		_retTree = _t;
		return value;
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
	
