package latex4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import latex4j.base.Command;
import latex4j.base.Environment;
import latex4j.base.Package;
import latex4j.base.Ref;
import latex4j.document.DocumentClass;
import latex4j.math.EquationEnv;

public class LatexRenderer {
	
	// typesetter (default = pdflatex)
	String typesetter ;
	String latexHome ; // where the bin directory is: convention --> LATEX_HOME
	int numberOfRuns ;
	
	// output directory and file name
	String outputPath = "." ;
	String outputName = "rendered" ;
	boolean debug = false ;
	boolean printWarnings = true ; // latex warnings
	
	// structure of latex file
	DocumentClass documentClass ;
	
	// latex system properties --> professional software development (command-line applications)
	/*
	 * set system properties:
	 * 	latex.home --> path to the bin directory
	 *  latex.typesetter --> latex typesetter
	 */
	
	public LatexRenderer(String typesetter, int numberOfRuns) {
		setLatexHome() ;
		setLatexTypesetter(typesetter) ;
		this.numberOfRuns = numberOfRuns ;
	}
	
	public LatexRenderer(int numberOfRuns) {
		this(null, numberOfRuns) ;
	}
	
	public LatexRenderer() {
		this(null, 1) ;
	}
	
	private void setLatexHome() {
		if(System.getenv("LATEX_HOME") != null) { // LATEX_HOME environment variable
			this.latexHome = System.getenv("LATEX_HOME") ;
		}
		else if(System.getProperty("latex.home") != null) { // latex.home system property
			this.latexHome = System.getProperty("latex.home") ;
		}
		else {
			this.latexHome = "" ;
		}
	}
	
	private void setLatexTypesetter(String typesetter) {
		if(typesetter != null) { // explicit from constructor
			this.typesetter = typesetter ;
		}
		else if(System.getProperty("latex.typesetter") != null) { // latex.typesetter system property
			this.typesetter = System.getProperty("latex.typesetter") ;
		}
		else {
			this.typesetter = "pdflatex" ; // default typesetter
		}
	}
	
	public void setOutputPath(String path) {
		this.outputPath = path ;
	}
	
	public void setOutputName(String name) {
		this.outputName = name ;
	}
	
	public void debug(boolean flag) {
		this.debug = flag ;
	}
	
	public void printWarnings(boolean flag) {
		this.printWarnings = flag ;
	}
	
	//*************** Latex Elements ****************//
	
	public DocumentClass setDocumentClass(String name) {
		documentClass = new DocumentClass(name) ;
		return documentClass ;
	}
	
	public DocumentClass setDocumentClass(DocumentClass documentClass) {
		this.documentClass = documentClass ;
		return this.documentClass ;
	}
	
	
	//*************** End of Latex Elements ****************//
	
	private void runLatex(File texDir, File texFile) {
		// run command line (latex)
		String command = typesetter + " " + texFile.getAbsolutePath() ;
		Runtime runtime = Runtime.getRuntime() ;
		
		try {
			Process p = runtime.exec(command, null, texDir) ;
			if(debug) {
				Scanner debugScanner = new Scanner(p.getInputStream()) ;
				while(debugScanner.hasNextLine()) {
					System.out.println(debugScanner.nextLine());
				}
			}
			else if(printWarnings) {
				Scanner warnings = new Scanner(p.getInputStream()) ;
				while(warnings.hasNextLine()) {
					String line = warnings.nextLine() ;
					if(line.toLowerCase().contains("warning")) {
						System.out.println(line);
					}
				}
			}
			else {
				Scanner output = new Scanner(p.getInputStream()) ;
				while(output.hasNextLine()) {
					// just waiting for latex to finish
					output.nextLine() ;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private StringBuilder build() {
		StringBuilder texBuilder = new StringBuilder() ; // builds the contents of entire .tex file
		
		// create document class
		texBuilder.append(documentClass) ;
		
		// add packages
		var amsmath = new Package("amsmath") ;
		texBuilder.append(amsmath) ;
		
		// add extra preamble
		
		// add main text
		
		var docEnv = new Environment("document") ;
//		docEnv.addText("""
//				This is a simple test of LatexRenderer in java!!
//				""") ;
		
		EquationEnv eq1env = new EquationEnv() ;
		eq1env.inline(true);
//		Ref eq1 = eq1env.setLabel("eq1") ; // \label{eq1} --> equation environment: \ref{eq1}
		eq1env.addText("\\beta = f(\\zeta)") ;
		docEnv.addText(eq1env) ;
		
		
//		docEnv.addText(String.format("""
//				Now, we're citing Eq. (%s) which is placed right above this line of text...
//				""", eq1)) ; // creates ref command: \ref{label}
		
		// add main document contents
		texBuilder.append(docEnv) ;
		return texBuilder ;
	}
	
	public void render() {
		// build the content of .tex file
		StringBuilder texBuilder = build() ;
		
		// create .tex file
		File texDir = new File(outputPath) ;
		File texFile = new File(texDir + File.separator + outputName + ".tex") ;
		
		try(FileWriter texWriter = new FileWriter(texFile)) { // try with resources --> calls close() automatically
			texWriter.write(texBuilder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// run command line (latex)
		for(int i=0; i<numberOfRuns; i++)
			runLatex(texDir, texFile) ;
		
		// clean up auxiliary files (optional)
		if(!debug) {
			File auxFile = new File(texDir + File.separator + outputName + ".aux") ;
			auxFile.deleteOnExit();
			File logFile = new File(texDir + File.separator + outputName + ".log") ;
			logFile.deleteOnExit();
			texFile.deleteOnExit();
		}
	}
	
	
}
