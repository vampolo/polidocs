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
header { 
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
}
{
import java.util.Map;
import java.util.HashMap;
import t2p.translation.VariableInfo;
import t2p.translation.MatchInfo;
}
/***
 * Parser for Trio2Promela input programs.
 */
class T2PParser extends Parser; 
options 
{   
    
    importVocab = T2P;
    exportVocab = T2PExp;
    buildAST = true;        
    defaultErrorHandler = false;
} 

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
}

specs: 	(VAR! variableDeclarations)? 
		(MATCH! matchDeclarations)?
		(CONST! constantDeclarations)?
		FORM^ trioformulae;

variableDeclarations : var_decl (COMMA! var_decl)*;
var_decl: p: ID! COLON! LBRACK! (sign1: MINUS!)? n1: NUMBER! DOT! DOT! (sign2: MINUS!)? n2: NUMBER! RBRACK!
        {
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
    ;

matchDeclarations: match_decl (COMMA! match_decl)*;
match_decl: (str:STRONG!)? LPAREN! v1:ID! EQ! n1:NUMBER! COMMA! v2:ID! EQ! n2:NUMBER! RPAREN!
	{
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
;


constantDeclarations: const_decl (COMMA! const_decl)*;
const_decl: c: ID! EQ! n: NUMBER!
        {
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
    ;

trioformulae: (trioformula_decl SEMI)+;
trioformula_decl: id: ID^ COLON! trioformula;

trioformula :  coimpl; 
coimpl : impl (IFF^ impl)*; 
impl : or (IF^ or)*; 
or : and (OR^ and)*; 
and : formula (AND^ formula)*; 
formula : 
	NOT^ formula 
    | (term EQ)=> term EQ^ term 
    | (term NEQ)=> term NEQ^ term 
    | (term LT)=> term LT^ term 
    | (term GT)=> term GT^ term 
    | (term GTE)=> term GTE^ term 
    | (term LTE)=> term LTE^ term 
    | (
        	//ALW^| 
        	ALWFE^ | ALWFI^ | ALWPE^ | ALWPI^ 
        	//| SOM^ 
        |  SOMFE^ | SOMFI^ | SOMPE^ | SOMPI^ 
        | UPTONOW^ | BECOMES^ | NOWON^ | NOW^) 
        LPAREN! trioformula RPAREN!
    | ! SOMF LPAREN! somf_tf:trioformula RPAREN!  {#formula=#([SOMFE], somf_tf);}
    | ! SOMP LPAREN! somp_tf:trioformula RPAREN!  {#formula=#([SOMPE], somp_tf);}
    | ! ALWF LPAREN! alwf_tf:trioformula RPAREN!  {#formula=#([ALWFE], alwf_tf);}
    | ! ALWP LPAREN! alwp_tf:trioformula RPAREN!  {#formula=#([ALWPE], alwp_tf);}
    | (UNTIL^ | UNTILW^ | SINCE^ | SINCEW^) 
        LPAREN! trioformula COMMA! trioformula RPAREN! 
    | (DIST^ | FUTR^ | PAST^ 
        | LASTSEE^ | LASTSEI^ | LASTSIE^ | LASTSII^ 
        | LASTEDEE^ | LASTEDEI^ | LASTEDIE^ | LASTEDII^ 
            //	| WITHIN^ 
        | WITHINFEE^ | WITHINFEI^ | WITHINFIE^ | WITHINFII^ 
        | WITHINPEE^ | WITHINPEI^ | WITHINPIE^ | WITHINPII^ 
        | NEXTTIME^ | LASTTIME^) LPAREN! trioformula COMMA! term RPAREN!  
    | ! LASTS LPAREN! l_tf:trioformula COMMA! l_t:term RPAREN!  {#formula=#([LASTSEE], l_tf, l_t);}
    | ! LASTED LPAREN! ld_tf:trioformula COMMA! ld_t:term RPAREN!  {#formula=#([LASTEDEE], ld_tf, ld_t);}
    | ! WITHINF LPAREN! wf_tf:trioformula COMMA! wf_t:term RPAREN!  {#formula=#([WITHINFEE], wf_tf, wf_t);}
    | ! WITHINP LPAREN! wp_tf:trioformula COMMA! wp_t:term RPAREN!  {#formula=#([WITHINPEE], wp_tf, wp_t);}
    | LPAREN^ trioformula RPAREN!
    ;



term :  mathExpr;


mathExpr: prodExpr ((PLUS^|MINUS^) prodExpr)*;

prodExpr: powExpr ((DIV^ | STAR^ | MOD^| INTDIV^) powExpr)*;

powExpr: signedExpr (POW^ signedExpr)*;

signedExpr : (
            m:MINUS^ {#m.setType(SIGN_MINUS);}
        | p:PLUS^  {#p.setType(SIGN_PLUS);}
        )? atom ;

atom : NUMBER
	| n: ID
        {
            String k = n.getText();
            if (! constTable.containsKey(k) && varTable.containsKey(k)) {
                #n.setType(VARIABLE);
            } else if (constTable.containsKey(k) && ! varTable.containsKey(k)) {
                #n.setType(CONSTANT);
            } else {
                System.out.println("Error: constant or variable " + k + " not defined in preamble.");
                errNum++;
            }
        }
    | LPAREN^ mathExpr RPAREN!
    ;



imaginaryToken: SIGN_PLUS | SIGN_MINUS //for handling signed nodes
    | VARIABLE | CONSTANT // variables and constants
    | OR_ROOT | AND_ROOT | IF_ROOT | IFF_ROOT // for handling formula root notes
    ;
