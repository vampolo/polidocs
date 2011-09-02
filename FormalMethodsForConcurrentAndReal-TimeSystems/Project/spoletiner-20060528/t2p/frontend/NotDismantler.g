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
A dismantler for the not  operator.
Pushes the NOT operator downward the formula and applies De Morgan's rules.
*/ 
class NotDismantler  extends TreeParser;
options {
	
	importVocab = T2PExp;
	buildAST=true;
}

trioformula

    : 
        #(IFF trioformula trioformula) 
        
	|	(#(IFF_ROOT trioformula trioformula trioformula trioformula)) => #(IFF_ROOT trioformula trioformula trioformula trioformula)
		
	|	(#(IFF_ROOT trioformula trioformula trioformula)) => #(IFF_ROOT trioformula trioformula trioformula)  
		
    |	(#(IFF_ROOT trioformula trioformula )) => #(IFF_ROOT trioformula trioformula)  
        
	|	#(IF  if2_tf1:trioformula if2_tf2:trioformula)
        
    |	#(IF_ROOT  if_tf1:trioformula if_tf2:trioformula)
        
  	|	#(OR trioformula trioformula)   
        
    |	#(OR_ROOT trioformula trioformula)   
        
    |	#(AND trioformula trioformula) 
    	
    |	#(AND_ROOT trioformula trioformula) 
        
	|!	(#(NOT(#(AND trioformula trioformula)))) => #(NOT(#(AND n_and_tf1:trioformula n_and_tf2:trioformula))) 
    	{
    		NotDismantler nd = new NotDismantler();
    		nd.trioformula(#([NOT], n_and_tf1));
    		AST child1= nd.getAST();
    		nd.trioformula(#([NOT], n_and_tf2));
    		AST child2= nd.getAST();
    		#trioformula=#([OR], child1, child2);
       	}

    |!	(#(NOT(#(OR trioformula trioformula)))) => #(NOT(#(OR n_or_tf1:trioformula n_or_tf2:trioformula))) 
    	{
    		NotDismantler nd = new NotDismantler();
    		nd.trioformula(#([NOT], n_or_tf1));
    		AST child1= nd.getAST();
    		nd.trioformula(#([NOT], n_or_tf2));
    		AST child2= nd.getAST();
    		#trioformula=#([AND], child1, child2);
     	}

    |!	(#(NOT(#(AND_ROOT trioformula trioformula)))) => #(NOT(#(AND_ROOT n_andR_tf1:trioformula n_andR_tf2:trioformula))) 
    	{
    		NotDismantler nd = new NotDismantler();
    		nd.trioformula(#([NOT], n_andR_tf1));
    		AST child1= nd.getAST();
    		nd.trioformula(#([NOT], n_andR_tf2));
    		AST child2= nd.getAST();
    		#trioformula=#([OR_ROOT], child1, child2);
     	}

    |!	(#(NOT(#(OR_ROOT trioformula trioformula)))) => #(NOT(#(OR_ROOT n_orR_tf1:trioformula n_orR_tf2:trioformula))) 
    	{
    		NotDismantler nd = new NotDismantler();
    		nd.trioformula(#([NOT], n_orR_tf1));
    		AST child1= nd.getAST();
    		nd.trioformula(#([NOT], n_orR_tf2));
    		AST child2= nd.getAST();
    		#trioformula=#([AND_ROOT], child1, child2);
     	}
        
    |!	(#(NOT( #(LASTSEE  trioformula term)))) =>	#(NOT( #(LASTSEE  n_lee_tf: trioformula n_lee_t:term)))
		{
			#trioformula=#([WITHINFEE, "WithinF_ee"], #([NOT], n_lee_tf), n_lee_t);
		}
        
	|!	(#(NOT( #(LASTSEI  trioformula term)))) =>	#(NOT( #(LASTSEI  n_lei_tf: trioformula n_lei_t:term)))
		{
			#trioformula=#([WITHINFEI, "WithinF_ei" ], #([NOT], n_lei_tf), n_lei_t);
		}
		
	|!	(#(NOT( #(LASTSIE  trioformula term)))) =>	#(NOT( #(LASTSIE  n_lie_tf: trioformula n_lie_t:term)))
		{
			#trioformula=#([WITHINFIE, "WithinF_ie"], #([NOT], n_lie_tf), n_lie_t);
		}
        
	|!	(#(NOT( #(LASTSII  trioformula term)))) =>	#(NOT( #(LASTSII  n_lii_tf: trioformula n_lii_t:term)))
		{
			#trioformula=#([WITHINFII,"WithinF_ii"], #([NOT], n_lii_tf), n_lii_t);
		}
        
	|!	(#(NOT( #(LASTEDEE  trioformula term)))) =>	#(NOT( #(LASTEDEE  n_ldee_tf: trioformula n_ldee_t:term)))
		{
			#trioformula=#([WITHINPEE, "WithinP_ee"], #([NOT], n_ldee_tf), n_ldee_t);
		}
		
	|!	(#(NOT( #(LASTEDEI  trioformula term)))) =>	#(NOT( #(LASTEDEI  n_ldei_tf: trioformula n_ldei_t:term)))
		{
			#trioformula=#([WITHINPEI, "WithinP_ei"], #([NOT], n_ldei_tf), n_ldei_t);
		}
		
	|!	(#(NOT( #(LASTEDIE  trioformula term)))) =>	#(NOT( #(LASTEDIE  n_ldie_tf: trioformula n_ldie_t:term)))
		{
			#trioformula=#([WITHINPIE, "WithinP_ie"], #([NOT], n_ldie_tf), n_ldie_t);
		}
        
	|!	(#(NOT( #(LASTEDII  trioformula term)))) =>	#(NOT( #(LASTEDII  n_ldii_tf: trioformula n_ldii_t:term)))
		{
			#trioformula=#([WITHINPII, "WithinP_ii"], #([NOT], n_ldii_tf), n_ldii_t);
		}
        
	|!  (#(NOT( #(WITHINFEE  trioformula term)))) =>	#(NOT( #(WITHINFEE  n_wfee_tf: trioformula n_wfee_t:term)))
		{
			#trioformula=#([LASTSEE], #([NOT], n_wfee_tf), n_wfee_t);
		}
		
	|!  (#(NOT( #(WITHINFEI  trioformula term)))) =>	#(NOT( #(WITHINFEI  n_wfei_tf: trioformula n_wfei_t:term)))
		{
			#trioformula=#([LASTSEI], #([NOT], n_wfei_tf), n_wfei_t);
		}
		
	|!  (#(NOT( #(WITHINFIE  trioformula term)))) =>	#(NOT( #(WITHINFIE  n_wfie_tf: trioformula n_wfie_t:term)))
		{
			#trioformula=#([LASTSIE], #([NOT], n_wfie_tf), n_wfie_t);
		}
        
	|!  (#(NOT( #(WITHINFII  trioformula term)))) =>	#(NOT( #(WITHINFII  n_wfii_tf: trioformula n_wfii_t:term)))
		{
			#trioformula=#([LASTSII], #([NOT], n_wfii_tf), n_wfii_t);
		}
        
	|!  (#(NOT( #(WITHINPEE  trioformula term)))) =>	#(NOT( #(WITHINPEE  n_wpee_tf: trioformula n_wpee_t:term)))
		{
			#trioformula=#([LASTEDEE], #([NOT], n_wpee_tf), n_wpee_t);
		}
        
	|!  (#(NOT( #(WITHINPEI  trioformula term)))) =>	#(NOT( #(WITHINPEI  n_wpei_tf: trioformula n_wpei_t:term)))
		{
			#trioformula=#([LASTEDEI], #([NOT], n_wpei_tf), n_wpei_t);
		}
        
	|!  (#(NOT( #(WITHINPIE  trioformula term)))) =>	#(NOT( #(WITHINPIE  n_wpie_tf: trioformula n_wpie_t:term)))
		{
			#trioformula=#([LASTEDIE], #([NOT], n_wpie_tf), n_wpie_t);
		}
        
	|!  (#(NOT( #(WITHINPII  trioformula term)))) =>	#(NOT( #(WITHINPII  n_wpii_tf: trioformula n_wpii_t:term)))
		{
			#trioformula=#([LASTEDII], #([NOT], n_wpii_tf), n_wpii_t);
		}
		
	|!  (#(NOT( #(ALWFE  trioformula)))) =>	#(NOT( #(ALWFE  n_alwfe_tf: trioformula)))
		{
			#trioformula=#([SOMFE], #([NOT], n_alwfe_tf));
		}
		
	|!  (#(NOT( #(ALWFI  trioformula)))) =>	#(NOT( #(ALWFI  n_alwfi_tf: trioformula)))
		{
			#trioformula=#([SOMFI], #([NOT], n_alwfi_tf));
		}
		
	|!  (#(NOT( #(ALWPE  trioformula)))) =>	#(NOT( #(ALWPE  n_alwpe_tf: trioformula)))
		{
			#trioformula=#([SOMPE], #([NOT], n_alwpe_tf));
		}
		
	|!  (#(NOT( #(ALWPI  trioformula)))) =>	#(NOT( #(ALWPI  n_alwpi_tf: trioformula)))
		{
			#trioformula=#([SOMPI], #([NOT], n_alwpi_tf));
		}
        
	|!  (#(NOT( #(FUTR  trioformula term)))) =>	#(NOT( #(FUTR  n_f_tf: trioformula n_f_t:term)))
		{
			#trioformula=#([FUTR], #([NOT], n_f_tf), n_f_t);
		}

	|!  (#(NOT( #(NOWON  trioformula)))) =>	#(NOT( #(NOWON  n_no_tf: trioformula)))
		{
			#trioformula=#([NOWON], #([NOT], n_no_tf));
		}	
        
	|!  (#(NOT( #(SOMFE  trioformula)))) =>	#(NOT( #(SOMFE  n_somfe_tf: trioformula)))
		{
			#trioformula=#([ALWFE], #([NOT], n_somfe_tf));
		}
		
	|!  (#(NOT( #(SOMFI  trioformula)))) =>	#(NOT( #(SOMFI  n_somfi_tf: trioformula)))
		{
			#trioformula=#([ALWFI], #([NOT], n_somfi_tf));
		}
		
	|!  (#(NOT( #(SOMPE  trioformula)))) =>	#(NOT( #(SOMPE  n_sompe_tf: trioformula)))
		{
			#trioformula=#([ALWPE], #([NOT], n_sompe_tf));
		}
		
	|!  (#(NOT( #(SOMPI  trioformula)))) =>	#(NOT( #(SOMPI  n_sompi_tf: trioformula)))
		{
			#trioformula=#([ALWPI], #([NOT], n_sompi_tf));
		}
        
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
        
    |	#(BECOMES b_tf:trioformula)
    	
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
        
    |	#(NEXTTIME  trioformula term)
    	
    |	#(LASTTIME  trioformula term)  
    
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
