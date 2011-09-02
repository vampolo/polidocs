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
 * A repository for enumerations used in the translator backend
 * 
 * @author bianculli
 * 
 */
public class TranslationUtilities {
	/***************************************************************************
	 * Enumeration defining intervals for formulae, such as
	 * Lasts/Lasted/WithinF,WithinP_{ee,ei,ie,ii}, AlwP/AlwF/SomP/SomF_{e,i},
	 * Until/UntilW
	 */
	public enum Lasting {
		ee, ei, ie, ii, no, i, e, untilw
	};

	/***************************************************************************
	 * Enumeration defining the temporal quality of fomulae
	 */
	public enum TemporalQuality {
		future, present_past
	}

	/***************************************************************************
	 * Computes the temporal quality of the combination of two formulae
	 * 
	 * @param f1
	 *            the temporal quality of the first formula
	 * @param f2
	 *            the temporal quality of the second formula
	 * @return the resulting temporal quality
	 */
	public static TemporalQuality computeTemporalQuality(TemporalQuality f1,
			TemporalQuality f2) {
		TemporalQuality result;
		if (f1 == TemporalQuality.future || f2 == TemporalQuality.future)
			result = TemporalQuality.future;
		else
			result = TemporalQuality.present_past;
		return result;
	}

}
