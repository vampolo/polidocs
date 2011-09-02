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

public interface RootDismantlerTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int NOT = 4;
	int TRUE = 5;
	int FALSE = 6;
	int ALW = 7;
	int ALWF = 8;
	int ALWFE = 9;
	int ALWFI = 10;
	int ALWP = 11;
	int ALWPE = 12;
	int ALWPI = 13;
	int SOM = 14;
	int SOMF = 15;
	int SOMFE = 16;
	int SOMFI = 17;
	int SOMP = 18;
	int SOMPE = 19;
	int SOMPI = 20;
	int UPTONOW = 21;
	int BECOMES = 22;
	int UNTIL = 23;
	int UNTILW = 24;
	int SINCE = 25;
	int SINCEW = 26;
	int DIST = 27;
	int FUTR = 28;
	int PAST = 29;
	int LASTS = 30;
	int LASTSEE = 31;
	int LASTSEI = 32;
	int LASTSIE = 33;
	int LASTSII = 34;
	int LASTED = 35;
	int LASTEDEE = 36;
	int LASTEDEI = 37;
	int LASTEDIE = 38;
	int LASTEDII = 39;
	int WITHIN = 40;
	int WITHINF = 41;
	int WITHINFEE = 42;
	int WITHINFEI = 43;
	int WITHINFIE = 44;
	int WITHINFII = 45;
	int WITHINP = 46;
	int WITHINPEE = 47;
	int WITHINPEI = 48;
	int WITHINPIE = 49;
	int WITHINPII = 50;
	int NEXTTIME = 51;
	int LASTTIME = 52;
	int NOWON = 53;
	int NOW = 54;
	int VAR = 55;
	int FORM = 56;
	int CONST = 57;
	int MOD = 58;
	int INTDIV = 59;
	int SYM = 60;
	int MATCH = 61;
	int STRONG = 62;
	int PROPERTY = 63;
	int LPAREN = 64;
	int RPAREN = 65;
	int LCURLY = 66;
	int RCURLY = 67;
	int LBRACK = 68;
	int RBRACK = 69;
	int SEMI = 70;
	int COLON = 71;
	int COMMA = 72;
	int IFF = 73;
	int IF = 74;
	int OR = 75;
	int AND = 76;
	int DOT = 77;
	int EQ = 78;
	int NEQ = 79;
	int LT = 80;
	int GT = 81;
	int GTE = 82;
	int LTE = 83;
	int PLUS = 84;
	int MINUS = 85;
	int DIV = 86;
	int STAR = 87;
	int POW = 88;
	int WS = 89;
	int ID = 90;
	int DIGIT = 91;
	int NUMBER = 92;
	int SL_COMMENT = 93;
	int SIGN_PLUS = 94;
	int SIGN_MINUS = 95;
	int VARIABLE = 96;
	int CONSTANT = 97;
	int OR_ROOT = 98;
	int AND_ROOT = 99;
	int IF_ROOT = 100;
	int IFF_ROOT = 101;
}
