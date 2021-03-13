package latex4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import latex4j.base.Command;
import latex4j.base.Environment;

public class LatexRenderer {
	
	// typesetter (default = pdflatex)
	String typesetter ;
	int numberOfRuns ;
	
	// output directory and file name
	String outputPath = "." ;
	String outputName = "rendered" ;
	boolean debug = false ;
	boolean printWarnings = true ; // latex warnings
	
	// structure of latex file
	
	public LatexRenderer(String typesetter, int numberOfRuns) {
		this.typesetter = typesetter ;
		this.numberOfRuns = numberOfRuns ;
	}
	
	public LatexRenderer() {
		this.typesetter = "pdflatex" ;
		this.numberOfRuns = 1 ;
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
	
	public void setDocument(String documentClass) {
		
	}
	
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
	
	public void render() {
		StringBuilder texBuilder = new StringBuilder() ; // builds the contents of entire .tex file
		// create document class
		
		var docClass = new Command("documentclass", "article") ;
		docClass.addOption("12pt") ;
		texBuilder.append(docClass) ;
		
		// add packages
		
		// add extra preamble
		
		// add main text
		
		var docEnv = new Environment("document") ;
		docEnv.addText("""
				This is a simple test of LatexRenderer in java!!
				""") ;
		
		texBuilder.append(docEnv) ;
		
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
