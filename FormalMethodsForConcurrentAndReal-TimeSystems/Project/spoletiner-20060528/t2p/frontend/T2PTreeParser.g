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
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import t2p.translation.*;
}
/***
 * Tree walker for preprocessed AST of TRIO formulae.
 * Given an AST corresponding to a formula, builds objects associated to
 * the operators contained in the formula.
 * These objects may be retrieved with methods <code>get</code>OperatorName<code>Formulae()</code>
 * for further processing.
 */ 
class T2PTreeParser  extends TreeParser;
options {
	importVocab = T2PExp;
	buildAST=false;
	k=1;
}

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
}

trioformula  returns [TrioFormula result]
{
	result=null;
	TrioFormula s=null;
	TrioFormula t=null;
	TrioFormula s1=null;
	TrioFormula t1=null;
	TermFormula a=null;
	TermFormula b=null;
}

    :
    	#(IFF s=trioformula t=trioformula)  
        { 
        	//TODO translaton
            System.out.println("IFF node");
        } 

    |	(#(IFF_ROOT trioformula trioformula trioformula trioformula)) => 
    	#(IFF_ROOT s=trioformula t=trioformula s1=trioformula t1=trioformula)  
        { 
         	result = new IffRootFormula(s,t,s1,t1);
        } 
        
    |	(#(IFF_ROOT trioformula trioformula trioformula)) => 
    	#(IFF_ROOT s=trioformula t=trioformula s1=trioformula)  
        { 
        	result = new IffRootFormula(s,t,s1);
        }
        
    |	(#(IFF_ROOT trioformula trioformula )) => 
   		#(IFF_ROOT s=trioformula t=trioformula)  
        { 
        	result = new IffRootFormula(s,t);
        } 

    |	 #(OR s=trioformula t=trioformula)   
        { 
        	result=new OrFormula(s,t);
            
        }

    |	#(OR_ROOT s=trioformula t=trioformula)   
        { 
        	result = new OrRootFormula(s,t);
        }   

    |	#(AND s=trioformula t=trioformula) 
        { 
        	result=new AndFormula(s,t);
        }

    |	#(AND_ROOT s=trioformula t=trioformula) 
        { 
            result = new AndRootFormula(s,t);
        }

    |	#(NOT s=trioformula)  
        { 
        	/* preprocessing guarantee us that this method is invoked
        	 only on operators for which !OP(A) = OP(!A)
        	*/
        	s.negate();
        	result=s;
       	} 

    |	#(EQ a=term b=term)
    	{
    		result= new ExpressionFormula(a,b, "==");
    	}

    |	#(NEQ a=term b=term)
    	{
    		result= new ExpressionFormula(a,b, "!=");
    	}

    |	#(LT a=term b=term)
    	{
    		result= new ExpressionFormula(a,b, "<");
    	}

    |	#(GT a=term b=term)
    	{
    		result= new ExpressionFormula(a,b, ">");
    	}

    |	#(GTE a=term b=term)
    	{
    		result= new ExpressionFormula(a,b, ">=");
    	}

    |	#(LTE a=term b=term)
    	{
    		result= new ExpressionFormula(a,b, "<=");
    	}

    |	#(ALWFE t=trioformula)
    	{
    		this.alwFCounter++;
			AlwFFormula temp  = new AlwFFormula(t, this.alwFCounter, false );
			this.alwFFormulae.add(temp);
			result= temp;
    	}

    |	#(ALWFI t=trioformula)
    	{
    		this.alwFCounter++;
			AlwFFormula temp  = new AlwFFormula(t, this.alwFCounter, true );
			this.alwFFormulae.add(temp);
			result= temp;
    	}

    |	#(ALWPE t=trioformula)
    	{
    		this.alwPCounter++;
			AlwPFormula temp  = new AlwPFormula(t, this.alwPCounter, false );
			this.alwPFormulae.add(temp);
			result= temp;
    	}

    |	#(ALWPI t=trioformula)
    	{
    		this.alwPCounter++;
			AlwPFormula temp  = new AlwPFormula(t, this.alwPCounter, true );
			this.alwPFormulae.add(temp);
			result= temp;
    	}

    |	#(SOMFE s=trioformula)
    	{
    		this.somfCounter++;
			SomfFormula temp  = new SomfFormula(s, this.somfCounter, false);
			this.somfFormulae.add(temp);
			result = temp;
    	}

    |	#(SOMFI s=trioformula)
    	{
    		this.somfCounter++;
			SomfFormula temp  = new SomfFormula(s, this.somfCounter, true);
			this.somfFormulae.add(temp);
			result = temp;
    	}

    |	#(SOMPE s=trioformula)
    	{
    		this.sompCounter++;
			SompFormula temp  = new SompFormula(s, this.sompCounter, false);
			this.sompFormulae.add(temp);
			result = temp;
    	}

    |	#(SOMPI s=trioformula)
    	{
    		this.sompCounter++;
			SompFormula temp  = new SompFormula(s, this.sompCounter, true);
			this.sompFormulae.add(temp);
			result = temp;
    	}

    |	#(NOWON t=trioformula)
    	{
    		//treat as Futr(A,1)
    		this.futrCounter++;
			FutrFormula temp;
			temp  = new FutrFormula(t,new TermFormula(1), this.futrCounter, TranslationUtilities.Lasting.no, 0, this.constTable);
			this.futrFormulae.add(temp);
			result = temp;
    	}
    |	#(UPTONOW t=trioformula)
    	{
    		//System.out.println("UPTONOW formula");
    		//treat as Past(A,1)
    		PastFormula temp = new PastFormula(t,new TermFormula(1), this.constTable);
			String var  = temp.getVar();
			updatePastVarTable(temp,var);
			result=temp;
    	} 

    |	#(BECOMES t=trioformula)
    	{
    		//treat as Past(not A, 1) and A
    		//actually build only the Past node
    		t.negate();
    		PastFormula temp = new PastFormula(t,new TermFormula(1), this.constTable);
    		String var  = temp.getVar();
			updatePastVarTable(temp,var);
    		result=temp;
    	} 

    |	#(UNTIL s=trioformula t=trioformula) 
        { 
        	this.untilCounter++;
			UntilFormula temp  = new UntilFormula(s,t, this.untilCounter);
			this.untilFormulae.add(temp);
			result = temp;
        }

    |	#(UNTILW s=trioformula t=trioformula) 
        { 
        	this.untilwCounter++;
			UntilWFormula temp  = new UntilWFormula(s,t, this.untilwCounter);
			this.untilwFormulae.add(temp);
			result = temp;
        }

    |	#(SINCE s=trioformula t=trioformula) 
        { 
        	this.sinceCounter++;
			SinceFormula temp  = new SinceFormula(s,t, this.sinceCounter, false);
			this.sinceFormulae.add(temp);
			result = temp;
        }

    |	#(SINCEW s=trioformula t=trioformula) 
        { 
        	this.sinceCounter++;
			SinceFormula temp  = new SinceFormula(s,t, this.sinceCounter, true);
			this.sinceFormulae.add(temp);
			result = temp;
        }

    |	#(DIST  t=trioformula a=term)    
        {
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

    |	#(FUTR  t=trioformula a=term)    
        {
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

    |	(#(PAST #(LASTEDEE trioformula term)  term) )=> #(PAST #(LASTEDEE t=trioformula a=term) b=term)
    	{
    		this.lastedCounter++;
    		// get past constant -> lasted formula time instant
    		int pastConstant = term2Constant(b);
            LastedFormula temp  = new LastedFormula(t, new TermFormula(Integer.parseInt(a.eval())+pastConstant), pastConstant, this.lastedCounter, TranslationUtilities.Lasting.ee, this.constTable);
            this.lastedFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
    	}

    |	(#(PAST #(LASTEDEI trioformula term)  term) )=> #(PAST #(LASTEDEI t=trioformula a=term) b=term)
    	{
    		this.lastedCounter++;
    		// get past constant -> lasted formula time instant
    		int pastConstant = term2Constant(b);
            LastedFormula temp  = new LastedFormula(t, new TermFormula(Integer.parseInt(a.eval())+pastConstant), pastConstant, this.lastedCounter, TranslationUtilities.Lasting.ei, this.constTable);
            this.lastedFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
    	}

    |	(#(PAST #(LASTEDIE trioformula term)  term) )=> #(PAST #(LASTEDIE t=trioformula a=term) b=term)
    	{
    		this.lastedCounter++;
    		// get past constant -> lasted formula time instant
    		int pastConstant = term2Constant(b);
            LastedFormula temp  = new LastedFormula(t, new TermFormula(Integer.parseInt(a.eval())+pastConstant), pastConstant, this.lastedCounter, TranslationUtilities.Lasting.ie, this.constTable);
            this.lastedFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
    	} 

    |   (#(PAST #(LASTEDII trioformula term)  term) )=> #(PAST #(LASTEDII t=trioformula a=term) b=term)
    	{
    		this.lastedCounter++;
    		// get past constant -> lasted formula time instant
    		int pastConstant = term2Constant(b);
    		LastedFormula temp  = new LastedFormula(t, new TermFormula(Integer.parseInt(a.eval())+pastConstant), pastConstant, this.lastedCounter, TranslationUtilities.Lasting.ii, this.constTable);
            this.lastedFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
    	}

    |	#(PAST  t=trioformula a=term)    
        {
            PastFormula temp = new PastFormula(t,a, this.constTable);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result=temp;
        }

    |	#(LASTSEE  t=trioformula a=term)    
        {
            this.lastsCounter++;
            LastsFormula temp  = new LastsFormula(t,a, this.lastsCounter, TranslationUtilities.Lasting.ee, this.constTable);
            this.lastsFormulae.add(temp);
            result = temp;
        }

    |	#(LASTSEI  t=trioformula a=term)    
        {
            this.lastsCounter++;
            LastsFormula temp  = new LastsFormula(t,a, this.lastsCounter, TranslationUtilities.Lasting.ei, this.constTable);
            this.lastsFormulae.add(temp);
            result = temp;
            
        }

    |	#(LASTSIE  t=trioformula a=term)    
        {
            this.lastsCounter++;
            LastsFormula temp  = new LastsFormula(t,a, this.lastsCounter, TranslationUtilities.Lasting.ie, this.constTable);
            this.lastsFormulae.add(temp);
            result = temp;
        }

    |	#(LASTSII  t=trioformula a=term)    
        {
            this.lastsCounter++;
            LastsFormula temp  = new LastsFormula(t,a, this.lastsCounter, TranslationUtilities.Lasting.ii, this.constTable);
            this.lastsFormulae.add(temp);
            result = temp;
        }

    |	#(LASTEDEE  t=trioformula a=term)    
        {
            this.lastedCounter++;
            LastedFormula temp  = new LastedFormula(t,a, 0, this.lastedCounter, TranslationUtilities.Lasting.ee, this.constTable);
            this.lastedFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
        }

    |	#(LASTEDEI  t=trioformula a=term)    
        {
            this.lastedCounter++;
            LastedFormula temp  = new LastedFormula(t,a, 0, this.lastedCounter, TranslationUtilities.Lasting.ei, this.constTable);
            this.lastedFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
        }

    |	#(LASTEDIE  t=trioformula a=term)    
        {
            this.lastedCounter++;
            LastedFormula temp  = new LastedFormula(t,a, 0, this.lastedCounter, TranslationUtilities.Lasting.ie, this.constTable);
            this.lastedFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
        }

    |	#(LASTEDII  t=trioformula a=term)    
        {
            this.lastedCounter++;
            LastedFormula temp  = new LastedFormula(t,a, 0, this.lastedCounter, TranslationUtilities.Lasting.ii, this.constTable);
            this.lastedFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
        }

	|	(#(WITHINFEE  trioformula term term)) => #(WITHINFEE  t=trioformula a=term b=term)
        {
            this.withinFCounter++;
            WithinFFormula temp = new WithinFFormula(t,a, term2Constant(b), this.withinFCounter, TranslationUtilities.Lasting.ee, this.constTable);
            this.withinFFormulae.add(temp);
            result = temp;
        }

	|	(#(WITHINFEE  trioformula term )) => #(WITHINFEE  t=trioformula a=term)    
        {
            this.withinFCounter++;
            WithinFFormula temp = new WithinFFormula(t,a, this.withinFCounter, TranslationUtilities.Lasting.ee, this.constTable);
            this.withinFFormulae.add(temp);
            result = temp;
        }

	|	(#(WITHINFEI  trioformula term term)) => #(WITHINFEI  t=trioformula a=term b=term)
        {
            this.withinFCounter++;
            WithinFFormula temp = new WithinFFormula(t,a,term2Constant(b), this.withinFCounter, TranslationUtilities.Lasting.ei, this.constTable);
            this.withinFFormulae.add(temp);
            result = temp;
        }

    |	(#(WITHINFEI  trioformula term )) => #(WITHINFEI  t=trioformula a=term)    
        {
            this.withinFCounter++;
            WithinFFormula temp = new WithinFFormula(t,a, this.withinFCounter, TranslationUtilities.Lasting.ei, this.constTable);
            this.withinFFormulae.add(temp);
            result = temp;
        }

	|	(#(WITHINFIE  trioformula term term)) => #(WITHINFIE  t=trioformula a=term b=term)
        {
            this.withinFCounter++;
            WithinFFormula temp = new WithinFFormula(t,a, term2Constant(b),this.withinFCounter, TranslationUtilities.Lasting.ie, this.constTable);
            this.withinFFormulae.add(temp);
            result = temp;
        }

    |	(#(WITHINFIE  trioformula term )) => #(WITHINFIE  t=trioformula a=term)    
        {
            this.withinFCounter++;
            WithinFFormula temp = new WithinFFormula(t,a, this.withinFCounter, TranslationUtilities.Lasting.ie, this.constTable);
            this.withinFFormulae.add(temp);
            result = temp;
        }

	|	(#(WITHINFII  trioformula term term)) => #(WITHINFII  t=trioformula a=term b=term)
        {
            this.withinFCounter++;
            WithinFFormula temp = new WithinFFormula(t,a, term2Constant(b),this.withinFCounter, TranslationUtilities.Lasting.ii, this.constTable);
            this.withinFFormulae.add(temp);
            result = temp;
        }

    |	(#(WITHINFII  trioformula term )) => #(WITHINFII  t=trioformula a=term)    
        {
            this.withinFCounter++;
            WithinFFormula temp = new WithinFFormula(t,a, this.withinFCounter, TranslationUtilities.Lasting.ii, this.constTable);
            this.withinFFormulae.add(temp);
            result = temp;
        }

	|	(#(WITHINPEE  trioformula term term)) => #(WITHINPEE  t=trioformula a=term b=term)
        {
            this.withinPCounter++;
            WithinPFormula temp  = new WithinPFormula(t,a, term2Constant(b),this.withinPCounter, TranslationUtilities.Lasting.ee, this.constTable);
            this.withinPFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
        }

    |	(#(WITHINPEE  trioformula term)) => #(WITHINPEE  t=trioformula a=term)    
        {
            this.withinPCounter++;
            WithinPFormula temp  = new WithinPFormula(t,a, this.withinPCounter, TranslationUtilities.Lasting.ee, this.constTable);
            this.withinPFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
        }

    |	(#(WITHINPEI  trioformula term term)) => #(WITHINPEI  t=trioformula a=term b=term)
        {
            this.withinPCounter++;
            WithinPFormula temp  = new WithinPFormula(t,a, term2Constant(b),this.withinPCounter, TranslationUtilities.Lasting.ei, this.constTable);
            this.withinPFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
        }

    |	(#(WITHINPEI  trioformula term)) => #(WITHINPEI  t=trioformula a=term)    
        {
            this.withinPCounter++;
            WithinPFormula temp  = new WithinPFormula(t,a, this.withinPCounter, TranslationUtilities.Lasting.ei, this.constTable);
            this.withinPFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
        }

    |	(#(WITHINPIE  trioformula term term)) => #(WITHINPIE  t=trioformula a=term b=term)
        {
            this.withinPCounter++;
            WithinPFormula temp  = new WithinPFormula(t,a, term2Constant(b),this.withinPCounter, TranslationUtilities.Lasting.ie, this.constTable);
            this.withinPFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
        }

    |	(#(WITHINPIE  trioformula term)) => #(WITHINPIE  t=trioformula a=term)       
        {
            this.withinPCounter++;
            WithinPFormula temp  = new WithinPFormula(t,a, this.withinPCounter, TranslationUtilities.Lasting.ie, this.constTable);
            this.withinPFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
        }

    |	(#(WITHINPII  trioformula term term)) => #(WITHINPII  t=trioformula a=term b=term)
        {
            this.withinPCounter++;
            WithinPFormula temp  = new WithinPFormula(t,a, term2Constant(b),this.withinPCounter, TranslationUtilities.Lasting.ii, this.constTable);
            this.withinPFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
            
        }

    |	(#(WITHINPII  trioformula term)) => #(WITHINPII  t=trioformula a=term)      
        {
            this.withinPCounter++;
            WithinPFormula temp  = new WithinPFormula(t,a, this.withinPCounter, TranslationUtilities.Lasting.ii, this.constTable);
            this.withinPFormulae.add(temp);
            String var  = temp.getVar();
            updatePastVarTable(temp,var);
            result = temp;
        }
	
	|	#(NOW t=trioformula)
		{
			this.nowCounter++;
			NowFormula temp= new NowFormula(t, this.nowCounter++);
			this.nowFormulae.add(temp);
			result=temp;
		}

    |	#(LPAREN s=trioformula)
        {
            s.parenthesize();
            result=s;
        }
    ;

term returns [TermFormula value]
{
	value=null;
	TermFormula a,b;
}
    : 
        #(PLUS a=term b=term)
        {
            int s= Integer.parseInt(a.eval());
            int t= Integer.parseInt(b.eval());
            value=new TermFormula(s+t);
        }

    |	#(MINUS a=term b=term)
        {
            int s= Integer.parseInt(a.eval());
            int t= Integer.parseInt(b.eval());
            value=new TermFormula(s-t);
        }

    |	#(DIV a=term b=term)
        {
            int s= Integer.parseInt(a.eval());
            int t= Integer.parseInt(b.eval());
            value=new TermFormula(s/t);
        }

    |	#(STAR a=term b=term)
        {
            int s= Integer.parseInt(a.eval());
            int t= Integer.parseInt(b.eval());
            value=new TermFormula(s*t);
        }

    |	#(MOD a=term b=term)
        {
            int s= Integer.parseInt(a.eval());
            int t= Integer.parseInt(b.eval());
            value=new TermFormula(s%t);
        }

    |	#(INTDIV a=term b=term)
        {
            int s= Integer.parseInt(a.eval());
            int t= Integer.parseInt(b.eval());
            value=new TermFormula(s/t);
        }

    |	#(POW a=term b=term)
        {
            int s= Integer.parseInt(a.eval());
            int t= Integer.parseInt(b.eval());
            value=new TermFormula((int)Math.pow(s,t));
        }

    |	#(SIGN_MINUS a=term)
        {
            int s= Integer.parseInt(a.eval());
            value=new TermFormula(s*-1);
        }

    |	#(SIGN_PLUS a=term)
        {
            value=a;
        }

    |   i:NUMBER
    	{	
    		value = new TermFormula(Integer.parseInt(i.getText()));
    	}

    |   m:VARIABLE
    	{
    		String k= m.getText();
            value= new TermFormula(k);
    	}
    	
    |   c:CONSTANT
    	{
    		value= new TermFormula(this.constTable.get(c.getText()));
    	}

    |	#(LPAREN a=term)
        {
            value=a;
        }
    ;




