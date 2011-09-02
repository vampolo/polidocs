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
package t2p.translation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import t2p.frontend.T2PTreeParser;

import antlr.RecognitionException;
import antlr.collections.AST;

/**
 * A translator from a set of TRIO specification formulae to a Promela program.
 * @author bianculli
 *
 */
public class PromelaTranslator {
	// input axioms ASTs
	protected AST[] formulae;
	// TrioFormula representation of each axiom AST
	protected TrioFormula[] trioformulae;
	// variable table
	protected Map<String, VariableInfo> varTable;
	// variable-type table from parser
	protected Map<String,String> typedVarTable;
	// constant table from parser
	protected Map<String, Integer> constTable;
	// match table from the parser
	protected Map<String, MatchInfo> matchTable;
	// for each axiom contains the variables used in past formulae
	protected Map<?, ?>[] pastVarTable;
	//all variable used in a past formula in the specs, each of them with the
	// maximum temporal constant instantiated
	protected Map<String, Integer> globalPastVarTable;
	//array of futr formulae for each axiom
	protected ArrayList<?>[] futrFormulae; 
	// array of lasted formulae for each axiom
	protected ArrayList<?>[] lastedFormulae;
	// array of lasts formulae for each axiom
	protected ArrayList<?>[] lastsFormulae;
	// array of withinP formulae for each axiom
	protected ArrayList<?>[] withinPFormulae;
//	 array of withinF formulae for each axiom
	protected ArrayList<?>[] withinFFormulae;
//	 array of since formulae for each axiom
	protected ArrayList<?>[] sinceFormulae;
//	 array of Alwp formulae for each axiom
	protected ArrayList<?>[] alwPFormulae;
//	 array of Alwf formulae for each axiom
	protected ArrayList<?>[] alwFFormulae;	
//	 array of until formulae for each axiom
	protected ArrayList<?>[] untilFormulae;	
//	 array of somf formulae for each axiom
	protected ArrayList<?>[] somfFormulae;
//	 array of somp formulae for each axiom
	protected ArrayList<?>[] sompFormulae;
//	 array of untilw formulae for each axiom
	protected ArrayList<?>[] untilwFormulae;
//	 array of now formulae for each axiom
	protected ArrayList<?>[] nowFormulae;	
	// all futr and withinF formulae in the specs
	protected ArrayList<TrioFormula> globalFutrFormulae;
	// procIDs for all futr and withinF formulae
	protected Map<TrioFormula, Integer> procIDs; 
	
	protected PromelaTranslator() {
		
	}
	
	/**
	 * Builds a new Promela translator
	 * @param formulae the set of formulae in the specification, in their AST representation
	 * @param varTable table for resolving variables name
	 * @param constTable table for resolving constants name
	 */
	public PromelaTranslator(AST[] formulae, Map<String, VariableInfo> varTable, Map<String, Integer> constTable, Map<String, MatchInfo> matchTable ) {
		this.formulae = formulae;
		this.trioformulae = new TrioFormula[formulae.length];
		this.varTable = varTable;
		this.typedVarTable = new HashMap<String, String>(varTable.size());
		this.constTable = constTable;
		this.matchTable = matchTable;
		this.pastVarTable = new HashMap<?,?>[formulae.length];
		this.futrFormulae = new ArrayList<?>[formulae.length];
		this.lastedFormulae = new ArrayList<?>[formulae.length];
		this.lastsFormulae = new ArrayList<?>[formulae.length];
		this.withinPFormulae = new ArrayList<?>[formulae.length];
		this.withinFFormulae = new ArrayList<?>[formulae.length];
		this.sinceFormulae = new ArrayList<?>[formulae.length];
		this.alwPFormulae = new ArrayList<?>[formulae.length];
		this.alwFFormulae = new ArrayList<?>[formulae.length];
		this.untilFormulae = new ArrayList<?>[formulae.length];
		this.somfFormulae = new ArrayList<?>[formulae.length];
		this.sompFormulae = new ArrayList<?>[formulae.length];
		this.untilwFormulae = new ArrayList<?>[formulae.length];
		this.nowFormulae = new ArrayList<?>[formulae.length];
		
		T2PTreeParser walker = new T2PTreeParser(this.varTable, this.constTable);
		for (int i=0; i< formulae.length; i++) {
			try {
				walker.resetData();
				this.trioformulae[i] = walker.trioformula(formulae[i]);
				this.pastVarTable[i] = walker.getPastVarTable();
				this.futrFormulae[i] = walker.getFutrFormulae();
				this.lastedFormulae[i] = walker.getLastedFormulae();
				this.lastsFormulae[i] = walker.getLastsFormulae();
				this.withinPFormulae[i] = walker.getWithinpFormulae();
				this.withinFFormulae[i] = walker.getWithinfFormulae();
				this.sinceFormulae[i] = walker.getSinceFormulae();
				this.alwPFormulae[i] = walker.getAlwPFormulae();
				this.alwFFormulae[i] = walker.getAlwFFormulae();
				this.untilFormulae[i] = walker.getUntilFormulae();
				this.somfFormulae[i] = walker.getSomfFormulae();
				this.sompFormulae[i] = walker.getSompFormulae();
				this.untilwFormulae[i] = walker.getUntilWFormulae();
				this.nowFormulae[i] = walker.getNowFormulae();
			} catch (RecognitionException e) {
				System.out.println("Error in tree walking");
				e.printStackTrace();
			}
		}
		
		this.globalPastVarTable = this.computeGlobalPastVarTable();
		this.globalFutrFormulae = this.computeGlobalFutrFormulae();
		this.procIDs=  new HashMap<TrioFormula, Integer>();
		
	}
	
	/**
	 * Emits the Promela code into a file.
	 * @param filename the output file 
	 * @param propertyMode <code>true</code> if you want to emit a property file  
	 */
	public void emitPromela(String filename, boolean propertyMode) {
		String localVarDecl="bool s=1;\nbool t0;\n";
		String initSpe = "init{\n\trun spe();\n}";
		
		PrintWriter out=null;
		try {
			out = new PrintWriter(new FileWriter(filename));
		} catch (IOException e) {
			System.out.println("Error while creating output file: " + filename);
			e.printStackTrace();
		}
		
		
		//declare variables
		out.print(this.emitVariables(propertyMode));
		/*for each variable used in a past formula, declare the memo array with 
		 * size equals to the max constant appearing in the formulae.
		 */
		out.print(this.emitMemoVariables());
		//add general declaration
		out.println(localVarDecl);
		//for all futurer subformula write process Futr_i.BC
		for (TrioFormula f : globalFutrFormulae) {
			out.println(f.getProcessCode());
		}
		// add spe process description
		out.println(this.emitSpe(propertyMode));
		// add init spe code
		if (!propertyMode) {
			out.print(initSpe);
		}
		out.close();
		
	}
	
	protected String emitSpe(boolean propertyMode) {
				
		StringBuilder s= new StringBuilder();
		// proc definition
		s.append("\nproctype spe(){\nbyte i, proc;\nbool status, value, flag;\n");
		//if there are futr operator declared:
		if (!this.globalFutrFormulae.isEmpty())
			s.append("chan msg=[0] of {bool,bool,byte};\n");
		
		
		
		// for each lasted formula declares corresponding boolean flag
		for (int i = 0; i < this.lastedFormulae.length; i++) {
			ArrayList<LastedFormula> alf = (ArrayList<LastedFormula>) lastedFormulae[i];
			for (LastedFormula formula : alf) {
				s.append(formula.getLocalVariables());
			}
		}
		//for each withinP formula declares corresponding boolean flag
		for (int i = 0; i < this.withinPFormulae.length; i++) {
			ArrayList<WithinPFormula> alf = (ArrayList<WithinPFormula>) withinPFormulae[i];
			for (WithinPFormula formula : alf) {
				s.append(formula.getLocalVariables());
			}
		}
		// for each lasts formula declares corresponding boolean flag
		for (int i = 0; i < this.lastsFormulae.length; i++) {
			ArrayList<LastsFormula> alf = (ArrayList<LastsFormula>) lastsFormulae[i];
			for (LastsFormula formula : alf) {
				s.append(formula.getLocalVariables());
			}
		}
		
		
		//	 for each since formula declares corresponding boolean flag
		for (int i = 0; i < this.sinceFormulae.length; i++) {
			ArrayList<SinceFormula> alf = (ArrayList<SinceFormula>) sinceFormulae[i];
			for (SinceFormula formula : alf) {
				s.append(formula.getLocalVariables());
			}
		}
		
		
		//		 for each alwP formula declares corresponding boolean flag
		for (int i = 0; i < this.alwPFormulae.length; i++) {
			ArrayList<AlwPFormula> alf = (ArrayList<AlwPFormula>) alwPFormulae[i];
			for (AlwPFormula formula : alf) {
				s.append(formula.getLocalVariables());
			}
		}
		
//		 for each alwF formula declares corresponding boolean flag
		for (int i = 0; i < this.alwFFormulae.length; i++) {
			ArrayList<AlwFFormula> alf = (ArrayList<AlwFFormula>) alwFFormulae[i];
			for (AlwFFormula formula : alf) {
				s.append(formula.getLocalVariables());
			}
		}
		
//		 for each until formula declares corresponding boolean flag
		for (int i = 0; i < this.untilFormulae.length; i++) {
			ArrayList<UntilFormula> alf = (ArrayList<UntilFormula>) untilFormulae[i];
			for (UntilFormula formula : alf) {
				s.append(formula.getLocalVariables());
			}
		}
		
//		 for each somf formula declares corresponding boolean flag
		for (int i = 0; i < this.somfFormulae.length; i++) {
			ArrayList<SomfFormula> alf = (ArrayList<SomfFormula>) somfFormulae[i];
			for (SomfFormula formula : alf) {
				s.append(formula.getLocalVariables());
			}
		}
		
//		 for each somp formula declares corresponding boolean flag
		for (int i = 0; i < this.sompFormulae.length; i++) {
			ArrayList<SompFormula> alf = (ArrayList<SompFormula>) sompFormulae[i];
			for (SompFormula formula : alf) {
				s.append(formula.getLocalVariables());
			}
		}
		
//		 for each untilw formula declares corresponding boolean flag
		for (int i = 0; i < this.untilwFormulae.length; i++) {
			ArrayList<UntilWFormula> alf = (ArrayList<UntilWFormula>) untilwFormulae[i];
			for (UntilWFormula formula : alf) {
				s.append(formula.getLocalVariables());
			}
		}
		
//		 for each now formula declares corresponding boolean flag
		for (int i = 0; i < this.nowFormulae.length; i++) {
			ArrayList<NowFormula> alf = (ArrayList<NowFormula>) nowFormulae[i];
			for (NowFormula formula : alf) {
				s.append(formula.getLocalVariables());
			}
		}
		
		//for all futr and withinf process, generate corresponding ex_futr_i (or, ex_withinF_i)
		for (TrioFormula formula : this.globalFutrFormulae) {
			s.append(formula.getLocalVariables());
		}
		
		//for all futr node (Futr e WithinF)  s.append(getProcRun(procID))
		// where procID := procID+(last_Futr_formula_const) and initially procID=2;
		int procID=2;
		
		
		for (TrioFormula formula : this.globalFutrFormulae) {
			procIDs.put(formula, procID);
			s.append(formula.getProcessRun(procID));
			procID+=formula.getTemporalConstant();
			
		}
		
		
		int untilNum=0;
		for (int i = 0; i < this.untilFormulae.length; i++) {
			ArrayList<UntilFormula> alf = (ArrayList<UntilFormula>) untilFormulae[i];
			untilNum+=alf.size();
		}
		int untilwNum=0;
		for (int i = 0; i < this.untilwFormulae.length; i++) {
			ArrayList<UntilWFormula> alf = (ArrayList<UntilWFormula>) untilwFormulae[i];
			untilwNum+=alf.size();
		}
		int somfNum=0;
		for (int i = 0; i < this.somfFormulae.length; i++) {
			ArrayList<SomfFormula> alf = (ArrayList<SomfFormula>) somfFormulae[i];
			somfNum+=alf.size();
		}
		
		
		boolean untilSomf=untilNum > 0 || somfNum > 0;
		boolean untilw= untilwNum > 0;
		
//		if there are Until or SomF
		if (untilSomf || untilw) {
			s.append("\ngoto next_S;\n");
		}
		
		if (propertyMode) {
			s.append("\np:\n");
		} else {
			s.append("\nrigen:\n");
		}
		
		
		s.append("\natomic{\n");
		//for each variable used in the past,  memo update with max constant
		s.append(this.emitMemoUpdate());
		
		//forall declared variable udpate value
		if (!propertyMode) s.append(this.emitValuesUpdate());
		
		//close atomic for variable update
		s.append("\n}\n");
		
		//foreach axiom emit procStatus + verify condition
		s.append(this.emitCore());
				
		//goto gen
		s.append("\n\tgoto gen;");
		
		String label= (propertyMode)? "p;" : "rigen;";
		
		// gen label
		if (untilSomf) {
			s.append("\ngen:\n\tt0=1;\n\tif\n\t\t::s==0 -> t0=0; goto stop;" +
			"\n\t\t::s!=0->\n\t\t\tif\n\t\t\t\t::" + this.emitUntilSomfFlag(untilNum, somfNum) +
			"->goto progress_S;\n\t\t\t\t::!(" + this.emitUntilSomfFlag(untilNum, somfNum)+ ")->goto next_S;\n\t\t\tfi;\n\tfi;\n");
		}
		else if (untilw) {
			s.append("\ngen:\n\tt0=1;\n\tif\n\t\t::s==0 -> t0=0; goto stop;" +
			"\n\t\t::s!=0 -> skip;\n\tfi;\nnext_S:\n\tt0=0;\n\tgoto "+label);
		}
		else {
			s.append("\ngen:\n\tt0=1;\n\tif\n\t\t::s==0 -> t0=0; goto stop;" +
			"\n\t\t::s!=0 -> skip;\n\tfi;\n\tt0=0;\n\tgoto " + label +
			"\n");
		}
		
		if (untilSomf) {
			s.append("progress_S: skip;\nnext_S:\n\tt0=0;\n\tgoto " + label +
					"\n");
		}
		
		//stop label
		s.append("\nstop:\n");
		
		//for all futr op of all axioms add futr.getErr(id)
		
		for (TrioFormula f : globalFutrFormulae) {
			s.append(f.getError(procIDs.get(f)));
		}
		
		// skip statement, close spe process definition
		s.append("\n\tskip;\t\t\n}\n");
		return s.toString();
	}
	
	/*
	 * Emits variables declarations for variables used in formulae
	 * Fill typedVarTable with type information
	 */
	private String emitVariables(boolean propertyMode) {
		StringBuilder s = new StringBuilder();
		Set<Map.Entry<String,VariableInfo>> entrySet = this.varTable.entrySet();
		for (Map.Entry<String, VariableInfo> entry : entrySet) {
			VariableInfo r = entry.getValue();
			String varName = entry.getKey();
			
			//if propertyMode do not generate variables, but fill type information for memo vars
			
				if (r.getLower() == 0)  {
					if (r.getUpper() == 1) {
						if (!propertyMode) s.append("bool ");
						this.typedVarTable.put(varName,"bool");
					}
					else {
						if (!propertyMode) s.append("byte ");
						this.typedVarTable.put(varName,"byte");
					}
				}
				else {
					if (!propertyMode) s.append("int ");
					this.typedVarTable.put(varName,"int");
				}
				if (!propertyMode) s.append(varName+";\n");
			
		}	
		
		return s.toString();
	}
	
/*
	 * Emits memo variables declarations for variables used in past formulae
	 */
	private String emitMemoVariables() {
		StringBuilder s = new StringBuilder();
		Set<Map.Entry<String,Integer>> entrySet = this.globalPastVarTable.entrySet();
		for (Map.Entry<String, Integer> entry : entrySet) {
			Integer r = entry.getValue();
			String var = entry.getKey();
			s.append(this.typedVarTable.get(var)+" memo_"+var+"["+r+"];\n");
		}
		
		return s.toString();
	}
	
	protected Map<String,Integer>  computeGlobalPastVarTable() {
		Map<String, Integer> result = new HashMap<String,Integer>(this.varTable.size());
		for (int i = 0; i < pastVarTable.length; i++) {
			Map<String,Integer> m = (Map<String, Integer>)pastVarTable[i];
			Set<Map.Entry<String,Integer>> entrySet = m.entrySet();
			for (Map.Entry<java.lang.String, java.lang.Integer> entry : entrySet) {
				String k = entry.getKey();
				Integer val = entry.getValue();
				if (result.containsKey(k)) {
					if (val > result.get(k)) {
						result.put(k,val);
					}
				}
				else {
					result.put(k,val);
				}
			}
		}
		
		return result;
	}
	
	protected ArrayList<TrioFormula> computeGlobalFutrFormulae() {
		ArrayList<TrioFormula> result = new ArrayList<TrioFormula>();
		for (int i = 0; i < futrFormulae.length; i++) {
			ArrayList<FutrFormula> a = (ArrayList<FutrFormula>)futrFormulae[i];
			
			result.addAll(a);
		}
		
		for (int i = 0; i < withinFFormulae.length; i++) {
			ArrayList<WithinFFormula> a = (ArrayList<WithinFFormula>)withinFFormulae[i];
			
			result.addAll(a);
		}
		return result;
	}
	
	protected String emitMemoUpdate() {
		
		String extbc_format=
			"\n\ti=%d;\n\tdo\n\t\t::i>0->\n\t\t\tmemo_%s[i]=memo_%s[i-1];\n\t\t\ti--;\n\t\t::i<=0-> break;\n\tod;\n" +
			"\tmemo_%s[0]=%s;\n";
		StringBuilder s = new StringBuilder();
		Set<Map.Entry<String,Integer>> entrySet = this.globalPastVarTable.entrySet();
		for (Map.Entry<String, Integer> entry : entrySet) {
			Integer r = entry.getValue();
			String var = entry.getKey();
			String varName="";
			varName=var;
			s.append(String.format(extbc_format,r-1,varName,varName,varName,var));
			
		}
		
		return s.toString();
	}
	
	protected String emitValuesUpdate() {
		StringBuilder s = new StringBuilder();
		Set<Map.Entry<String,VariableInfo>> entrySet = this.varTable.entrySet();
		for (Map.Entry<String, VariableInfo> entry : entrySet) {
			VariableInfo r = entry.getValue();
			String var = entry.getKey();
			//if not used in a match
			if (!r.isUsedInMatch()) {
				s.append("\n\tif\n");
				//if has a match
				if (this.matchTable.containsKey(var)) {
					MatchInfo mi = this.matchTable.get(var);
					String matchedVar = mi.getVariable();
					int matchingValue = mi.getMyValue();
					int otherValue = mi.getValue();
					for (int i=r.getLower(); i<=r.getUpper();i++) {
						if (i==matchingValue) {
							s.append("\t\t::"+var+"="+i+"; "+matchedVar+"="+otherValue+ ";\n");
						}
						else {
							s.append("\t\t::"+var+"="+i+";\n");
							VariableInfo other = varTable.get(matchedVar);
							s.append("\t\tif\n");
							if (mi.isStrong()) {
								for (int j=other.getLower(); j<=other.getUpper();j++) {
									if (j != otherValue) {
										s.append("\t\t\t::"+matchedVar+"="+j+";\n");
									}
								}
							} else {
								for (int j=other.getLower(); j<=other.getUpper();j++) {
									s.append("\t\t\t::"+matchedVar+"="+j+";\n");
								}
							}
							s.append("\t\tfi;\n");
						}
						
					}
				}
				else {
					String varName = var;
					for (int i=r.getLower(); i<=r.getUpper();i++) {
						s.append("\t\t::"+varName+"="+i+";\n");
					}
				}
				s.append("\tfi;\n");
			}
		}
		
		return s.toString();
	}
	
	protected String emitCore() {
		StringBuilder s = new StringBuilder();
		
		for (int i = 0; i < trioformulae.length; i++) {
			TrioFormula tf = trioformulae[i];
			
			ArrayList<FutrFormula> futr = (ArrayList<FutrFormula>) futrFormulae[i];
			ArrayList<WithinFFormula> withinf = (ArrayList<WithinFFormula>) withinFFormulae[i];
			ArrayList<AlwPFormula> alwp = (ArrayList<AlwPFormula>) alwPFormulae[i];
			ArrayList<AlwFFormula> alwf = (ArrayList<AlwFFormula>) alwFFormulae[i];
			ArrayList<SinceFormula> since = (ArrayList<SinceFormula>) sinceFormulae[i];
			ArrayList<LastedFormula> lasted = (ArrayList<LastedFormula>) lastedFormulae[i];
			ArrayList<WithinPFormula> withinP = (ArrayList<WithinPFormula>) withinPFormulae[i];
			ArrayList<LastsFormula> lasts = (ArrayList<LastsFormula>) lastsFormulae[i];
			ArrayList<SompFormula> somps = (ArrayList<SompFormula>) sompFormulae[i];
			ArrayList<NowFormula> nows= (ArrayList<NowFormula>) nowFormulae[i];
			
			// determine if open an atomicBlock
			boolean needAtomic= (futr.size() > 0 || withinf.size() > 0 || 
									alwp.size() > 0 || alwf.size() > 0 || 
									since.size() > 0 || lasted.size() > 0 ||
									withinP.size() > 0 || lasts.size() > 0 || somps.size() > 0);
			if (needAtomic) s.append("\n\tatomic{\n");

			
			for (FutrFormula ff : futr) {
				s.append(ff.getProcessStatus(procIDs.get(ff)));
			}
			
			
			for (WithinFFormula wff : withinf) {
				s.append(wff.getProcessStatus(procIDs.get(wff)));
			}
			
			for (NowFormula nf : nows) {
				s.append(nf.getProcessCode());
			}
			
			for (AlwPFormula awf : alwp) {
				s.append(awf.getExternalBodyCode());
			}
			
			
			for (AlwFFormula awf : alwf) {
				s.append(awf.getProcessCode());
			}
			
			
			for (SinceFormula sf : since) {
				s.append(sf.getExternalBodyCode());
			}
			
			
			for (LastedFormula lf : lasted) {
				s.append(lf.getExternalBodyCode());
			}
			
			
			for (WithinPFormula wpf : withinP) {
				s.append(wpf.getExternalBodyCode());
			}
			
			
			for (LastsFormula lf : lasts) {
				s.append(lf.getProcessStatus(0));
			}
			
			for (SompFormula somp : somps) {
				s.append(somp.getExternalBodyCode());
			}
			//close atomic block
			if (needAtomic) s.append("\n\t}\n");
			
			ArrayList<UntilFormula> untils = (ArrayList<UntilFormula>) untilFormulae[i];
			for (UntilFormula uf : untils) {
				s.append(uf.getProcessCode());
			}
			
			ArrayList<SomfFormula> somfs = (ArrayList<SomfFormula>) somfFormulae[i];
			for (SomfFormula somf : somfs) {
				s.append(somf.getProcessCode());
			}
			
			ArrayList<UntilWFormula> untilws = (ArrayList<UntilWFormula>) untilwFormulae[i];
			for (UntilWFormula uf : untilws) {
				s.append(uf.getProcessCode());
			}
			
			//if there are futr operator in the formula
			if (!futr.isEmpty() || !withinf.isEmpty() || !lasts.isEmpty() || !untils.isEmpty() || !somfs.isEmpty() || !untilws.isEmpty()) {
				s.append("\tif\n");
				s.append("\t\t::flag==1 -> s=0; goto gen;\n");
				s.append("\t\t::flag!=1 ->");
				s.append(tf.getBodyCode());
				s.append("\n\tfi;"); 
			} 
			else {
				s.append(tf.getBodyCode());
			}
			
			
		}
		return s.toString();
	}
	
	private String emitUntilSomfFlag(int untilNum, int somfNum) {
		StringBuffer s = new StringBuffer();
		
		if (untilNum > 0) {
			s.append("ex_Until_1!=1");
			for (int i = 2; i <= untilNum; i++) {
				s.append(" && ex_Until_");
				s.append(i);
				s.append("!=1");
			}
		}
	
		
		if (somfNum>0) {
	 		if (untilNum>0) s.append(" && ");
			s.append("ex_SomF_1!=1");
			for (int i = 2; i <= somfNum; i++) {
				s.append(" && ex_SomF_");
				s.append(i);
				s.append("!=1");
			}
		}
		
		
		return s.toString();
	}
}
