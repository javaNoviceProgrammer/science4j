package latex4j;

public class LatexRenderer {
	
	// typesetter (default = pdflatex)
	String typesetter ;
	int numberOfRuns ;
	
	// output directory and file name
	String outputPath = "." ;
	String outputName = "rendered" ;
	
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
	
	
	
	public void render() {
		// create document class
		
		// add packages
		
		// add extra preamble
		
		// add main text
		
		// run command line 
		
		// clean up auxiliary files
	}
	
	
}
