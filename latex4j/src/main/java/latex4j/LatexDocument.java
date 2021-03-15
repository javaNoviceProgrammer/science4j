package latex4j;

import latex4j.document.DocumentClass;

/**
 * Represents the contents of the .tex file --> Builder class
 * 
 * 
 * @author meisam
 *
 */
public class LatexDocument { // another candidate: LatexBuilder
	
	// structure of latex file
	DocumentClass documentClass ;

	
	public LatexDocument() {
		
	}
	
	//*************** Document Class ****************//
	
	public DocumentClass setDocumentClass(String name) {
		documentClass = new DocumentClass(name) ;
		return documentClass ;
	}
	
	public DocumentClass setDocumentClass(DocumentClass documentClass) {
		this.documentClass = documentClass ;
		return this.documentClass ;
	}
	
	
	//*************** Adding Packages ****************//
	
	
	
	//*************** Adding Preambles ****************//
	
	
	
	//*************** Adding Text ****************//
	
	// plain text
	
	// formatted text
	

	//*************** Adding Equations ****************//
	
	// equation
	
	// subequations
	
	
	//*************** Adding Figures ****************//
	
	
	
	
	//*************** Adding Tables ****************//
	
	
	
	//*************** Build contents of .tex file ****************//
	
	public StringBuilder build() {
		// contents builder
		StringBuilder texdoc = new StringBuilder() ;
		
		// step 1: add document class command
		texdoc.append(documentClass) ;
		
		// do some stuff here to texdoc
		
		
		return texdoc ;
	}

}
