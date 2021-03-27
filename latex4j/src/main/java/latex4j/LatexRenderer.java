package latex4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
	
	// Latex Document
	LatexDocument latexDoc ;
	
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
		else if(System.getProperty("latex.home") != null) { // latex.home system property: -Dkey=value
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
	
	
	
	private void runLatex(File texDir, File texFile) {
		// run command line (latex)
//		String command = typesetter + " " + texFile.getAbsolutePath() ;
		String command = typesetter + " -halt-on-error " + texFile.getAbsolutePath() ;
		Runtime runtime = Runtime.getRuntime() ;
		
		try {
			Process p = runtime.exec(command, null, texDir) ;
			if(debug) {
				Scanner debugScanner = new Scanner(p.getInputStream()) ;
				while(debugScanner.hasNextLine()) {
					String line = debugScanner.nextLine() ;
					if(line.contains("!") || line.toLowerCase().contains("undefined") 
							|| line.toLowerCase().contains("error") || line.toLowerCase().contains("not")) {
						System.err.println(line);
					}
					else {
						System.out.println(line);
					}
				}
			}
			else if(printWarnings) {
				Scanner warnings = new Scanner(p.getInputStream()) ;
				while(warnings.hasNextLine()) {
					String line = warnings.nextLine() ;
					if(line.toLowerCase().contains("warning")) {
						System.out.println(line);
					}
					else if(line.toLowerCase().contains("error")) {
						System.err.println(line);
						System.exit(-1) ;
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
		
	public LatexDocument createEmptyDocument() {
		latexDoc = new LatexDocument() ;
		return latexDoc ;
	}
	
	private StringBuilder build() {
		return latexDoc.build() ;
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
