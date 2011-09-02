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

import java.util.ArrayList;
import java.util.Collection;

import t2p.translation.TranslationUtilities.TemporalQuality;

/*******************************************************************************
 * The base class representing a formula as expressed in the TRIO specification
 * language, ready to be included in a Promela program.
 * 
 * @author bianculli
 * 
 */
public abstract class TrioFormula {

	// temporal constant of metric operators
	protected int temporalConstant;

	// local variables to be declared in the Promela program
	protected String localVariables = "";

	// independent process that needs to be launched in the father process
	protected String processRun = "";

	// error propagation
	protected String error = "";

	// process status
	protected String processStatus = "";

	// external body code
	protected String externalBodyCode = "";

	// logical expression: the truth value of the formula
	protected String logicExpression = "";

	// body code
	protected String bodyCode = "";

	//code of the process associated to this operator
	protected String processCode="";
	
	// temporal quality
	protected TemporalQuality temporalQuality;
	//sub-formulae of the formula
	protected ArrayList<TrioFormula> children = null;

	/***
	 * Constructs a formula given the two children.
	 * 
	 * @param tf1 left child in the parse tree
	 * @param tf2 right child in the parse tree
	 */
	public TrioFormula(TrioFormula tf1, TrioFormula tf2) {
		this.children = new ArrayList<TrioFormula>();
		this.children.add(tf1);
		this.children.add(tf2);
	}

	/***
	 * Constructs a formula given its children.
	 * @param list the collection containing children formulae
	 */
	public TrioFormula(Collection<? extends TrioFormula> list) {
		this.children = new ArrayList<TrioFormula>();
		this.children.addAll(list);
	}

	public TrioFormula() {

	}

	/**
	 * @return Returns the code to be placed in the body of the Promela program.
	 */
	public String getBodyCode() {
		return bodyCode;
	}

	/**
	 * @return Returns the the code for grabbing the error value of the process
	 */
	public String getError(int procID) {
		return error;
	}

	/**
	 * @return Returns the code to be placed in the external body of the Promela program.
	 */
	public String getExternalBodyCode() {
		return externalBodyCode;
	}

	/**
	 * @return Returns the logic expression of the formula, corresponding to its truth value.
	 */
	public String getLogicExpression() {
		return logicExpression;
	}

	/**
	 * @return Returns the code for declaring local variables in the Promela program.
	 */
	public String getLocalVariables() {
		return localVariables;
	}

	/**
	 * @return Returns the code for grabbing the process status.
	 */
	public String getProcessStatus(int procID) {
		return processStatus;
	}

	/**
	 * @return Returns the code for the independent process that needs to be launched in the father process.
	 */
	public String getProcessRun(int procID) {
		return processRun;
	}

	/**
	 * @return Returns the temporal constant of the metric operator
	 * corrisponding to the formula.
	 */
	public int getTemporalConstant() {
		return temporalConstant;
	}

	/**
	 * @return Returns the children of the formula.
	 */
	public ArrayList<TrioFormula> getChildren() {
		return (ArrayList<TrioFormula>) children;
	}
	/***
	 * Parenthesize the formula, by enclosing its logic expression between parentheses.
	 *
	 */
	public void parenthesize() {
		this.logicExpression = "(" + this.logicExpression + ")";
	}
	/***
	 * Apply the NOT operator to the formula, by negating its logic expression.
	 */
	public void negate() {
		if (!this.logicExpression.equals("")) {
			this.logicExpression = "!(" + this.logicExpression + ")";
		}
	}

	/***
	 * 
	 * @return the temporal quality of the formula, i.e. if the operator is a future one or a present/past one.
	 */
	public TemporalQuality getTemporalQuality() {
		return temporalQuality;
	}

	public String getProcessCode() {
		return processCode;
	}

}
