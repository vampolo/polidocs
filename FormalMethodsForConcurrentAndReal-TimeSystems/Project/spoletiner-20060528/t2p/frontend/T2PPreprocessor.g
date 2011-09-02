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
import t2p.translation.*;
}
/***
 A preprocessor for building AST according to derivation rules for operator in TRIO.
 */ 
class T2PPreprocessor  extends TreeParser;
options {
	
	importVocab = T2PExp;
	buildAST=true;
}

trioformula

    : 	#(IFF trioformula trioformula) 
        
    |	(#(IFF_ROOT trioformula trioformula trioformula trioformula)) => 
    	#(IFF_ROOT trioformula trioformula trioformula trioformula) 
        
    |	(#(IFF_ROOT trioformula trioformula trioformula)) => 
    	#(IFF_ROOT trioformula trioformula trioformula)  
         
    |	(#(IFF_ROOT trioformula trioformula )) => 
   		#(IFF_ROOT trioformula trioformula)  
         
    |	#(IF  if2_tf1:trioformula if2_tf2:trioformula)
        
    |	#(IF_ROOT  if_tf1:trioformula if_tf2:trioformula)
         
  	|	 #(OR trioformula trioformula)   
        
    |	#(OR_ROOT trioformula trioformula)   
        
    |	#(AND trioformula trioformula) 
    	
    |	#(AND_ROOT trioformula trioformula) 
        
    |	#(NOT trioformula)  
        
    |	#(EQ term term) 
    	
    |	#(NEQ term term)
    	
    |	#(LT term term)
    	
    |	#(GT term term)
    	
    |	#(GTE term term)
    	
    |	#(LTE term term)
    	
    |	#(ALW trioformula)
        
    |	#(ALWF trioformula)
    	
    |	#(ALWFE trioformula)
    	
    |	#(ALWFI trioformula)
    	
    |	#(ALWP trioformula)
    	
    |	#(ALWPE trioformula)
    	
    |	#(ALWPI trioformula)
    	
    |	#(SOM trioformula)
        
    |	#(SOMF trioformula)
    	
    |	#(SOMFE trioformula)
    	
    |	#(SOMFI trioformula)
    	
    |	#(SOMP trioformula)
    	
    |	#(SOMPE trioformula)
    	
    |	#(SOMPI trioformula)
        
    |	#(NOWON trioformula)
    	
    |	#(UPTONOW trioformula)
        
    |!	#(BECOMES b_tf:trioformula)
    	{
    		//treat as Past(not A,1) & A
    		// build and node; TreeParser will substitute node BECOMES with a Past
    		#trioformula=#([AND, "AND"], #([BECOMES], b_tf), b_tf );
    	}
    	
    |	#(UNTIL trioformula trioformula) 
        
    |	#(UNTILW trioformula trioformula) 
        
    |	#(SINCE trioformula trioformula) 
        
    |	#(SINCEW trioformula trioformula) 
        
    |	#(DIST  trioformula term)    
        
    |	#(FUTR  trioformula term)    
        
    |	#(PAST  trioformula term)    
        
    |	#(LASTS  trioformula term)    
        
    |	#(LASTSEE  trioformula term) 
        
    |	#(LASTSEI  trioformula term)
        
    |	#(LASTSIE  trioformula term)  
		
    |	#(LASTSII  trioformula term)    
        
    |	#(LASTEDEE  trioformula term) 
        
    |	#(LASTEDEI  trioformula term)    
        
    |	#(LASTEDIE  trioformula term) 
        
    |	#(LASTEDII  trioformula term) 

    |	#(LASTED  trioformula term)    
        
    |	#(WITHIN  trioformula term)    
        
    |	(#(WITHINFEE  trioformula term term)) => #(WITHINFEE  trioformula term term)
        
    |	(#(WITHINFEE  trioformula term )) => #(WITHINFEE  trioformula term )
        
    |	(#(WITHINFEI  trioformula term term)) => #(WITHINFEI  trioformula term term)
        
    |	(#(WITHINFEI  trioformula term )) => #(WITHINFEI  trioformula term )
        
    |	(#(WITHINFIE  trioformula term term)) => #(WITHINFIE  trioformula term term)
        
    |	(#(WITHINFIE  trioformula term )) => #(WITHINFIE  trioformula term )  
        
    |	(#(WITHINFII  trioformula term term)) => #(WITHINFII  trioformula term term)
        
    |	(#(WITHINFII  trioformula term )) => #(WITHINFII  trioformula term )  
        
	|	(#(WITHINPEE  trioformula term term)) => #(WITHINPEE  trioformula term term)
        
    |	(#(WITHINPEE  trioformula term )) => #(WITHINPEE  trioformula term )
        
    |	(#(WITHINPEI  trioformula term term)) => #(WITHINPEI  trioformula term term)
        
    |	(#(WITHINPEI  trioformula term )) => #(WITHINPEI  trioformula term )
        
    |	(#(WITHINPIE  trioformula term term)) => #(WITHINPIE  trioformula term term)
        
    |	(#(WITHINPIE  trioformula term )) => #(WITHINPIE  trioformula term )
        
	|	(#(WITHINPII  trioformula term term)) => #(WITHINPII  trioformula term term)
        
    |	(#(WITHINPII  trioformula term )) => #(WITHINPII  trioformula term )
        
    |!	#(NEXTTIME  next_tf:trioformula next_t:term)
    	{
    		#trioformula=#([AND, "AND"], #([FUTR, "FUTR"], next_tf, next_t), #([LASTSEE, "LASTSEE"],#([NOT, "not"], next_tf) ,next_t));
    	}
        
    |!	#(LASTTIME  last_tf:trioformula last_t:term)  
    	{
    		#trioformula=#([AND, "AND"], #([FUTR, "FUTR"], last_tf, last_t), #([LASTEDEE, "LASTEDEE"],#([NOT, "not"], last_tf) ,last_t));
    	}
    	
    |	#(NOW trioformula)
                
    |	#(LPAREN trioformula)
        
    ;

term 
    : 
        #(PLUS term term)
        
    |	#(MINUS term term)
        
    |	#(DIV term term)
        
    |	#(STAR term term)
        
    |	#(MOD term term)
        
    |	#(INTDIV term term)
        
    |	#(POW term term)
        
    |	#(SIGN_MINUS term)
        
    |	#(SIGN_PLUS term)
        
    |   NUMBER
    	
    |   VARIABLE
    	
    |   CONSTANT
    	
    |	#(LPAREN term)
        
    ;




