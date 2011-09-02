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

/*******************************************************************************
 * A range containing lower and upper bound for variable values.
 * 
 * @author bianculli
 * 
 */
public class VariableInfo {
	private int lower;

	private int upper;
	
	private boolean usedInMatch;
	
	private int size;

	public VariableInfo(int lower, int upper, boolean usedInMatch, int size) {
		super();
		// TODO Auto-generated constructor stub
		this.lower = lower;
		this.upper = upper;
		this.usedInMatch = usedInMatch;
		this.size= size;
	}

	public VariableInfo(int lower, int upper, boolean usedInMatch) {
		super();
		// TODO Auto-generated constructor stub
		this.lower = lower;
		this.upper = upper;
		this.usedInMatch = usedInMatch;
		this.size= 0;
	}
	/***************************************************************************
	 * Builds a new range object.
	 * 
	 * @param lower
	 *            the lower-bound
	 * @param upper
	 *            the upper-bound
	 */
	public VariableInfo(int lower, int upper) {
		this(lower, upper, false);
	}

	/***************************************************************************
	 * @return Returns the lower.
	 */
	public int getLower() {
		return lower;
	}

	/***************************************************************************
	 * @return Returns the upper.
	 */
	public int getUpper() {
		return upper;
	}

	public boolean isUsedInMatch() {
		return usedInMatch;
	}

	public int getSize() {
		return size;
	}

}