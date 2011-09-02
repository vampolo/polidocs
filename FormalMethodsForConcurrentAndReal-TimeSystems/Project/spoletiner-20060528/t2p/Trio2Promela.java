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
package t2p;

import jargs.gnu.CmdLineParser;
import jargs.gnu.CmdLineParser.Option;
import jargs.gnu.CmdLineParser.OptionException;

import java.io.*;
import java.util.Map;

import t2p.frontend.*;
import t2p.translation.*;
import antlr.CommonAST;
import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.collections.AST;

/**
 * A translator of Trio specifications to Promela programs. 
 * @author bianculli
 *
 */
public class Trio2Promela {
	private static void compile(String inputFile, boolean propertyMode) {
		DataInputStream input = null;
		try {
			input = new DataInputStream(new FileInputStream(inputFile));
		} catch (FileNotFoundException e) {
			System.out.println("Error:" + e.getMessage());
			System.exit(2);
		}
		T2PLexer lexer = new T2PLexer(input);
		T2PParser parser = new T2PParser(lexer);
		try {
			parser.specs();
		} catch (RecognitionException e) {
			System.out.println("Parse error:" + e.getMessage());
			System.exit(2);
		} catch (TokenStreamException e) {
			System.out.println("Parse error:" + e.getMessage());
			System.exit(2);
		}
		if (parser.getErrNum() != 0) {
			System.out.println("Some parse errors, aborting translation");
			System.exit(2);
		}

		System.out.println("Parsing done");
		CommonAST parseTree = (CommonAST) parser.getAST();
		// System.out.println(parseTree.toStringTree());
		// antlr.debug.misc.ASTFrame frame = new antlr.debug.misc.ASTFrame("T2P
		// tree for " + args[0], parseTree);
		// frame.setVisible(true);
		Map<String, VariableInfo> varTable = parser.getVarTable();
		Map<String, Integer> constTable = parser.getConstTable();
		Map<String, MatchInfo> matchTable = parser.getMatchTable();
		
		// build sub AST for each formula in spec
		AST[] formulae = FrontendUtilities.getFormulaeASTs(parseTree);

		// System.out.println(formulae[0].toStringTree());
		// antlr.debug.misc.ASTFrame frame2 = new antlr.debug.misc.ASTFrame("T2P
		// tree", formulae[0]);
		// frame2.setVisible(true);
		
		// preprocess AST - run dismantlers
		RootDismantler _pre = new RootDismantler();
		for (int i = 0; i < formulae.length; i++) {
			try {
				_pre.trioformula(formulae[i]);
			} catch (RecognitionException e) {
				System.out.println("Preprocessor error: RootDismantler - " + e.getMessage());
				System.exit(2);
			}
			formulae[i] = _pre.getAST();
			// frame2 = new antlr.debug.misc.ASTFrame("T2P tree preprocessed",
			// formulae[0]);
			// frame2.setVisible(true);

		}
		
		T2PPreprocessor pre = new T2PPreprocessor();
		for (int i = 0; i < formulae.length; i++) {
			try {
				pre.trioformula(formulae[i]);
			} catch (RecognitionException e) {
				System.out.println("Preprocessor error: GenericDismantler - " + e.getMessage());
				System.exit(2);
			}
			formulae[i] = pre.getAST();
			// frame2 = new antlr.debug.misc.ASTFrame("T2P tree preprocessed",
			// formulae[0]);
			// frame2.setVisible(true);

		}
		NotDismantler npre = new NotDismantler();
		for (int i = 0; i < formulae.length; i++) {
			try {
				npre.trioformula(formulae[i]);
			} catch (RecognitionException e) {
				System.out.println("Preprocessor error: NotDismantler - " + e.getMessage());
				System.exit(2);
			}
			formulae[i] = npre.getAST();
			// frame2 = new antlr.debug.misc.ASTFrame("T2P tree preprocessed",
			// formulae[0]);
			// frame2.setVisible(true);

		}
		System.out.println("Preprocessing done");

		PromelaTranslator pt = new PromelaTranslator(formulae, varTable,
				constTable, matchTable);
		String fileName = inputFile + ".prm";
		pt.emitPromela(fileName, propertyMode);
		
		
		System.out.println("Translation done\nOutput written to file: "
				+ fileName);
	}

	
	
	public static void main(String args[]) {
		printHeader();
		CmdLineParser clp = new CmdLineParser();
		Option property = clp.addBooleanOption('p',"property");
		try {
			clp.parse(args);
		} catch (OptionException e) {
			System.err.println(e.getMessage());	
			printUsage();
			System.exit(2);
		}
		
		boolean propertyMode= (Boolean) clp.getOptionValue(property, Boolean.FALSE);
		if (propertyMode) {
			System.out.println("Property generation mode");
		}
		
		String[] otherArgs = clp.getRemainingArgs();
		if (otherArgs.length != 1) {
			printUsage();
			System.exit(2);
		}
		String inputFile = otherArgs[0];
		compile(inputFile, propertyMode);

	}

	private static void printHeader() {
		System.out
				.println("TRIO to Promela translator\nVersion 0.1 - codename spoletiner");
	}

	private static void printUsage() {
		System.err.println("Usage: java t2p.Trio2Promela [-p/--property] <file_name>");
	}

}