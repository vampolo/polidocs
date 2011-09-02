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
 A dismantler for if and iff nodes, WithinF (WithinP) nested in Futr (Past) formulae,
 Futr (Past) in Lasts(Lasted).
 */ 
class RootDismantler  extends TreeParser;
options {
	
	importVocab = T2PExp;
	buildAST=true;
}

trioformula

    : 
        #(IFF trioformula trioformula) 

	|!  (#(IFF_ROOT #(AND trioformula trioformula) #(AND trioformula trioformula))) => 
		#(IFF_ROOT #(and0:AND iff_and_tf1:trioformula iff_and_tf2:trioformula) #(AND iff_and_tf3:trioformula iff_and_tf4:trioformula))
		{
			#trioformula=
            #(
                #[IFF_ROOT], 
                #([AND], iff_and_tf1, iff_and_tf2),
                #([AND], iff_and_tf3, iff_and_tf4),
                #([OR], #([NOT], iff_and_tf1), #([NOT], iff_and_tf2)),
                #([OR], #([NOT], iff_and_tf3), #([NOT], iff_and_tf4))
            );
		}
        
        //TODO and <-> or, or<->and, or<->or and others
        
        // futr <-> futr 
    |!	(#(IFF_ROOT #(FUTR trioformula term) #(FUTR trioformula term))) => 
    	#(IFF_ROOT #(futr0:FUTR iff0_tf1:trioformula iff0_t1:term) #(FUTR iff0_tf2:trioformula iff0_t2:term)) 
    	{
    		#trioformula=
            #(
                #[IFF_ROOT], 
                #(#[FUTR],iff0_tf1,iff0_t1),
                #(#[FUTR],iff0_tf2,iff0_t2),
                #(#[FUTR],#([NOT],iff0_tf1),iff0_t1),
                #(#[FUTR],#([NOT],iff0_tf2),iff0_t2)
            );	
    	}

        // not futr <-> not futr	
    |!	(#(IFF_ROOT #(NOT #(FUTR trioformula term)) #(NOT #(FUTR trioformula term)))) => 
    	#(IFF_ROOT #(not0nn:NOT #(futr0nn:FUTR iff0nn_tf1:trioformula iff0nn_t1:term)) #(NOT #(FUTR iff0nn_tf2:trioformula iff0nn_t2:term))) 
    	{
    		#trioformula=
            #(
                #[IFF_ROOT], 
                #(#[FUTR],#([NOT],iff0nn_tf1),iff0nn_t1),
                #(#[FUTR],#([NOT],iff0nn_tf2),iff0nn_t2),
                #(#[FUTR],iff0nn_tf1,iff0nn_t1),
                #(#[FUTR],iff0nn_tf2,iff0nn_t2)
            );	
    	}

        // not futr <-> futr
    |!	(#(IFF_ROOT #(NOT #(FUTR trioformula term)) #(FUTR trioformula term))) => 
    	#(IFF_ROOT #(not0np:NOT #(futr0np:FUTR iff0np_tf1:trioformula iff0np_t1:term))  #(FUTR iff0np_tf2:trioformula iff0np_t2:term)) 
    	{
    		#trioformula=
            #(
                #[IFF_ROOT], 
                #(#[FUTR],#([NOT],iff0np_tf1),iff0np_t1),
                #(#[FUTR],iff0np_tf2,iff0np_t2),
                #(#[FUTR],iff0np_tf1,iff0np_t1),
                #(#[FUTR],#([NOT],iff0np_tf2),iff0np_t2)
            );	
    	}

        // futr <-> not futr
 	|!	(#(IFF_ROOT #(FUTR trioformula term) #(NOT #(FUTR trioformula term)))) => 
    	#(IFF_ROOT #(futr0pn:FUTR iff0pn_tf1:trioformula iff0pn_t1:term) #(not0pn:NOT #(FUTR iff0pn_tf2:trioformula iff0pn_t2:term))) 
    	{
    		#trioformula=
            #(
                #[IFF_ROOT],
                #(#[FUTR],iff0pn_tf1,iff0pn_t1), 
                #(#[FUTR],#([NOT],iff0pn_tf2),iff0pn_t2),
                #(#[FUTR],#([NOT],iff0pn_tf1),iff0pn_t1),
                #(#[FUTR],iff0pn_tf2,iff0pn_t2)
            );	
    	}

        //  futr <-> formula 
    |!	(#(IFF_ROOT #(FUTR trioformula term) trioformula )) => 
    	#(IFF_ROOT #(futr1:FUTR iff1_tf1:trioformula iff1_t1:term) tf1:trioformula ) 
    	{
    		#trioformula=
            #(
                #[IFF_ROOT], 
                #(#[FUTR],iff1_tf1,iff1_t1),
                tf1,
                #(#[FUTR],#([NOT],iff1_tf1),iff1_t1)
            );	
    	}

        //  not futr <-> formula 
    |!	(#(IFF_ROOT #(NOT #(FUTR trioformula term)) trioformula )) => 
    	#(IFF_ROOT #(not1np:NOT #(futr1np:FUTR iff1np_tf1:trioformula iff1np_t1:term)) tf1np:trioformula ) 
    	{
    		#trioformula=
            #(
                #[IFF_ROOT], 
                #(#[FUTR],#([NOT],iff1np_tf1),iff1np_t1),
                tf1np,
                #(#[FUTR],iff1np_tf1,iff1np_t1)
            );	
    	}
    	
        //inserted to parse lasts_IE <-> futr
        //TODO missing combinations
    |!	(#(IFF_ROOT #(LASTSIE trioformula term) #(FUTR trioformula term))) =>
    	#(IFF_ROOT #(iff_futr_lastsie_lastsie:LASTSIE iff_futr_lastsie_tf1:trioformula iff_futr_lastsie_t1:term) #(iff_futr_lastsie_futr:FUTR iff_futr_lastsie_tf2:trioformula iff_futr_lastsie_t2:term))
    	{
    		#trioformula=
            #(
                #[IFF_ROOT],
                #(#[LASTSIE,"LASTSIE"],iff_futr_lastsie_tf1,iff_futr_lastsie_t1 ),
                #(#[FUTR,"FUTR"], iff_futr_lastsie_tf2,iff_futr_lastsie_t2),
                #(#[WITHINFIE, "WITHINFIE"], #([NOT, "NOT"], iff_futr_lastsie_tf1), iff_futr_lastsie_t1),
                #(#[FUTR, "FUTR"], #([NOT, "NOT"], iff_futr_lastsie_tf2),iff_futr_lastsie_t2)
            );		
    	}

        // formula <-> futr
    |!	(#(IFF_ROOT trioformula #(FUTR trioformula term)  )) => 
    	#(IFF_ROOT tf2:trioformula #(futr2:FUTR iff2_tf1:trioformula iff2_t1:term)  ) 
    	{
    		#trioformula=
            #(
                #[IFF_ROOT], 
                tf2,
                #(#[FUTR],iff2_tf1,iff2_t1),
                #(#[FUTR],#([NOT],iff2_tf1),iff2_t1)
            );	
    	}

        // formula <-> not futr
    |!	(#(IFF_ROOT trioformula #(NOT #(FUTR trioformula term)))) => 
    	#(IFF_ROOT tf2pn:trioformula #(NOT #(futr2pn:FUTR iff2pn_tf1:trioformula iff2pn_t1:term))) 
    	{
    		#trioformula=
            #(
                #[IFF_ROOT], 
                tf2pn,
                #(#[FUTR],#([NOT],iff2pn_tf1),iff2pn_t1),
                #(#[FUTR],iff2pn_tf1,iff2pn_t1)
            );	
    	}
        
        // formula <-> formula	
    |	(#(IFF_ROOT trioformula trioformula)) =>
    	#(IFF_ROOT trioformula trioformula)
        
    |!  (#(IF #(FUTR trioformula term) trioformula)) => #(IF  #(FUTR if2_futr_tf1:trioformula if2_futr_t:term) if2_futr_tf2:trioformula)
    	{
    		#trioformula=#(#[OR, "OR"], #(#[FUTR], #([NOT, "not"], if_futr_tf1), if_futr_t), if_futr_tf2);
    	}
        
    |!  #(IF  if2_tf1:trioformula if2_tf2:trioformula)
    	{
    		#trioformula=#(#[OR, "OR"], #([NOT, "NOT"], if2_tf1), if2_tf2);
    	}
        
    |!  (#(IF_ROOT #(FUTR trioformula term) trioformula)) => #(IF_ROOT  #(FUTR if_futr_tf1:trioformula if_futr_t:term) if_futr_tf2:trioformula)
    	{
    		#trioformula=#(#[OR_ROOT, "OR_ROOT"], #(#[FUTR], #([NOT, "not"], if_futr_tf1), if_futr_t), if_futr_tf2);
    	}
        
    |!  #(IF_ROOT  if_tf1:trioformula if_tf2:trioformula)
    	{
    		#trioformula=#(#[OR_ROOT, "OR_ROOT"], #([NOT, "NOT"], if_tf1), if_tf2);
    	}
        
  	|   #(OR trioformula trioformula)   
        
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
        
    |	#(BECOMES trioformula)
    	
    |	#(UNTIL trioformula trioformula) 
        
    |	#(UNTILW trioformula trioformula) 
        
    |	#(SINCE trioformula trioformula) 
        
    |	#(SINCEW trioformula trioformula) 
        
    |	#(DIST  trioformula term)    
        
	|!  (#(FUTR #(WITHINFEE trioformula term) term)) => #(FUTR #(WITHINFEE futr_withinfee_tf:trioformula futr_withinfee_t1:term) futr_withinfee_t2: term)
        {
            #trioformula=#(WITHINFEE, futr_withinfee_tf, futr_withinfee_t1,futr_withinfee_t2);
        }

	|!  (#(FUTR #(WITHINFEI trioformula term) term)) => #(FUTR #(WITHINFEI futr_withinfei_tf:trioformula futr_withinfei_t1:term) futr_withinfei_t2: term) 
        {
            #trioformula=#(WITHINFEI, futr_withinfei_tf, futr_withinfei_t1,futr_withinfei_t2);
        }

	|!  (#(FUTR #(WITHINFIE trioformula term) term)) => #(FUTR #(WITHINFIE futr_withinfie_tf:trioformula futr_withinfie_t1:term) futr_withinfie_t2: term) 
        {
            #trioformula=#(WITHINFIE, futr_withinfie_tf, futr_withinfie_t1,futr_withinfie_t2);
        }

	|! (#(FUTR #(WITHINFII trioformula term) term)) => #(FUTR #(WITHINFII futr_withinfii_tf:trioformula futr_withinfii_t1:term) futr_withinfii_t2: term) 
        {
            #trioformula=#(WITHINFII, futr_withinfii_tf, futr_withinfii_t1,futr_withinfii_t2);
        }

    |	#(FUTR  trioformula term)    
        
	|!  (#(PAST #(WITHINPEE trioformula term) term)) => #(PAST #(WITHINPEE past_withinpee_tf:trioformula past_withinpee_t1:term) past_withinpee_t2: term) 
        {
            #trioformula=#(WITHINPEE, past_withinpee_tf, past_withinpee_t1,past_withinpee_t2);
        }

	|!  (#(PAST #(WITHINPEI trioformula term) term)) => #(PAST #(WITHINPEI past_withinpei_tf:trioformula past_withinpei_t1:term) past_withinpei_t2: term) 
        {
            #trioformula=#(WITHINPEI, past_withinpei_tf, past_withinpei_t1,past_withinpei_t2);
            
        }

	|! (#(PAST #(WITHINPIE trioformula term) term)) => #(PAST #(WITHINPIE past_withinpie_tf:trioformula past_withinpie_t1:term) past_withinpie_t2: term) 
        {
            #trioformula=#(WITHINPIE, past_withinpie_tf, past_withinpie_t1,past_withinpie_t2);
        }

	|! (#(PAST #(WITHINPII trioformula term) term)) => #(PAST #(WITHINPII past_withinpii_tf:trioformula past_withinpii_t1:term) past_withinpii_t2: term) 
        {
            #trioformula=#(WITHINPII, past_withinpii_tf, past_withinpii_t1,past_withinpii_t2);
        }

    |	#(PAST  trioformula term)    
        
    |	#(LASTS  trioformula term)    
    	
    |!  ( #(LASTSEE #(FUTR trioformula term) term)) =>	#(LASTSEE #(FUTR tf_ee: trioformula t1_ee: term) t2_ee: term)
        {
            #trioformula=#(FUTR, #(LASTSEE, tf_ee, t2_ee), t1_ee);
        }

    |	#(LASTSEE  trioformula term) 
        
    |!  ( #(LASTSEI #(FUTR trioformula term) term)) =>	#(LASTSEI #(FUTR tf_ei: trioformula t1_ei: term) t2_ei: term)
        {
            #trioformula=#(FUTR, #(LASTSEI, tf_ei, t2_ei), t1_ei);
        }

    |	#(LASTSEI  trioformula term)
        
    |!  ( #(LASTSIE #(FUTR trioformula term) term)) =>	#(LASTSIE #(FUTR tf_ie: trioformula t1_ie: term) t2_ie: term)
        {
            #trioformula=#(FUTR, #(LASTSIE, tf_ie, t2_ie), t1_ie);
        }

    |	#(LASTSIE  trioformula term)  

    |!  ( #(LASTSII #(FUTR trioformula term) term)) =>	#(LASTSII #(FUTR tf_ii: trioformula t1_ii: term) t2_ii: term)
        {
            #trioformula=#(FUTR, #(LASTSII, tf_ii, t2_ii), t1_ii);
        }
		
    |	#(LASTSII  trioformula term)    

    |!  ( #(LASTEDEE #(PAST trioformula term) term)) =>	#(LASTEDEE #(PAST tf_ee_p: trioformula t1_ee_p: term) t2_ee_p: term)
        {
            #trioformula=#(PAST, #(LASTEDEE, tf_ee_p, t1_ee_p), t2_ee_p);
        }

    |	#(LASTEDEE  trioformula term) 
        
    |!  ( #(LASTEDEI #(PAST trioformula term) term)) =>	#(LASTEDEI #(PAST tf_ei_p: trioformula t1_ei_p: term) t2_ei_p: term)
        {
            #trioformula=#(PAST, #(LASTEDEI, tf_ei_p, t1_ei_p), t2_ei_p);
        }

    |	#(LASTEDEI  trioformula term)    
        
    |!  ( #(LASTEDIE #(PAST trioformula term) term)) =>	#(LASTEDIE #(PAST tf_ie_p: trioformula t1_ie_p: term) t2_ie_p: term)
        {
            #trioformula=#(PAST, #(LASTEDIE, tf_ie_p, t1_ie_p), t2_ie_p);
        }

    |	#(LASTEDIE  trioformula term) 

    |!  ( #(LASTEDII #(PAST trioformula term) term)) =>	#(LASTEDII #(PAST tf_ii_p: trioformula t1_ii_p: term) t2_ii_p: term)
        {
            #trioformula=#(PAST, #(LASTEDII, tf_ii_p, t1_ii_p), t2_ii_p);
        }

    |	#(LASTEDII  trioformula term) 

    |	#(LASTED  trioformula term)    
        
    |	#(WITHIN  trioformula term)    
        
    |	#(WITHINF  trioformula term)    
        
    |	#(WITHINFEE  trioformula term)    
        
    |	#(WITHINFEI  trioformula term)    
        
    |	#(WITHINFIE  trioformula term)    
        
    |	#(WITHINFII  trioformula term)    
        
    |	#(WITHINPEE  trioformula term)    
        
    |	#(WITHINPEI  trioformula term)    
        
    |	#(WITHINPIE  trioformula term)    
        
    |	#(WITHINPII  trioformula term)    
        
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




