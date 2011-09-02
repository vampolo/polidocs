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
} 
/***
Lexical analyser for Trio2Promela input files.
*/
class  T2PLexer extends Lexer; 

options 
{ 
    k=2;
    exportVocab =  T2P;      
    charVocabulary = '\0'..'\377'; 
    codeGenMakeSwitchThreshold = 2;  
    codeGenBitsetTestThreshold = 3; 
    defaultErrorHandler = false;
} 

tokens {
    NOT = "not"; 
    TRUE = "true"; 
    FALSE = "false"; 
    ALW = "Alw"; 
    ALWF = "AlwF"; 
    ALWFE = "AlwF_e"; 
    ALWFI = "AlwF_i"; 
    ALWP = "AlwP"; 
    ALWPE = "AlwP_e"; 
    ALWPI = "AlwP_i"; 
    SOM = "Som"; 
    SOMF = "SomF"; 
    SOMFE = "SomF_e"; 
    SOMFI = "SomF_i"; 
    SOMP = "SomP"; 
    SOMPE = "SomP_e"; 
    SOMPI = "SomP_i"; 
    UPTONOW = "UpToNow"; 
    BECOMES = "Becomes"; 
    UNTIL = "Until"; 
    UNTILW = "Until_w"; 
    SINCE = "Since"; 
    SINCEW = "Since_w"; 
    DIST = "Dist"; 
    FUTR = "Futr"; 
    PAST = "Past"; 
    LASTS = "Lasts"; 
    LASTSEE = "Lasts_ee"; 
    LASTSEI = "Lasts_ei"; 
    LASTSIE = "Lasts_ie"; 
    LASTSII = "Lasts_ii"; 
    LASTED = "Lasted"; 
    LASTEDEE = "Lasted_ee"; 
    LASTEDEI = "Lasted_ei"; 
    LASTEDIE = "Lasted_ie"; 
    LASTEDII = "Lasted_ii"; 
    WITHIN = "Within"; 
    WITHINF = "WithinF"; 
    WITHINFEE = "WithinF_ee"; 
    WITHINFEI = "WithinF_ei"; 
    WITHINFIE = "WithinF_ie"; 
    WITHINFII = "WithinF_ii"; 
    WITHINP = "WithinP"; 
    WITHINPEE = "WithinP_ee"; 
    WITHINPEI = "WithinP_ei"; 
    WITHINPIE = "WithinP_ie"; 
    WITHINPII = "WithinP_ii"; 
    NEXTTIME = "NextTime"; 
    LASTTIME = "LastTime"; 
    NOWON	=	"NowOn";
    NOW = "Now";
    VAR ="variables";
    FORM="axioms";
    CONST="constants";
    MOD = "mod"; 
    INTDIV = "div";
    SYM = "sym";
    MATCH = "match";
    STRONG = "strong";
    PROPERTY = "property";
}


LPAREN : "("; 
RPAREN : ")";
LCURLY : "{";
RCURLY : "}";
LBRACK : "[";
RBRACK : "]";
SEMI : ";"; 
COLON : ":"; 
COMMA : ","; 
IFF : "<->"; 
IF : "->"; 
OR : "|"; 
AND : "&"; 
DOT: ".";
EQ : "="; 
NEQ : "<>"; 
LT : "<"; 
GT : ">"; 
GTE : ">="; 
LTE : "<="; 
PLUS : "+"; 
MINUS : "-"; 
DIV : "/"; 
STAR : "*"; 
POW : "^"; 

WS  :   (' '
        |   '\t'
        |   '\n'
        |   '\r')+
        { $setType(Token.SKIP); }
    ;



ID    options 
{testLiterals=true;} 
    :
        ( 'a'..'z' | 'A'..'Z' ) 
        ( 'a'..'z' | 'A'..'Z' | '_' | '0'..'9' )* 
    ;


protected 
DIGIT 
    :       '0'..'9' 
    ; 

NUMBER 
    : ( DIGIT )+ 
    ; 
// Single-line comments
SL_COMMENT
	:	"//"
		(~('\n'|'\r'))* ('\n'|'\r'('\n')?)
		{$setType(Token.SKIP);}
	;
