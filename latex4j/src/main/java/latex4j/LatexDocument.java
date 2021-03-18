package latex4j;

import java.util.ArrayList;
import java.util.List;

import latex4j.base.Environment;
import latex4j.base.Package;
import latex4j.document.ArticleClass;
import latex4j.document.DocumentClass;
import latex4j.math.EquationEnv;

/**
 * Represents the contents of the .tex file --> Builder class
 * 
 */
public class LatexDocument { // another candidate: LatexBuilder
	
	// structure of latex file
	DocumentClass documentClass ;
	List<Package> packages ;
	List<Object> preamble ; // anything after package configuration
	
	// main text
	Environment docEnv ;

	
	public LatexDocument() {
		documentClass = new ArticleClass() ;
		packages = new ArrayList<>() ;
		preamble = new ArrayList<>() ;
		docEnv = new Environment("document") ;
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
	
	public Package addPackage(String pkgName) {
		Package pkg = new Package(pkgName) ;
		packages.add(pkg) ;
		return pkg ;
	}
	
	public Package addPackage(Package pkg) {
		packages.add(pkg) ;
		return pkg ;
	}
	
	
	//*************** Adding Preambles ****************//
	
	public void addPreamble(Object obj) {
		preamble.add(obj) ;
	}
	
	//*************** Main Document (Text) ****************//
	
	public Environment mainText() {
		return docEnv ;
	}
	
	
	//*************** Adding Text ****************//
	
	// plain text
	
	public void addText(String text) {
		docEnv.addText(text) ;
	}
	
	// formatted text
	

	//*************** Adding Equations ****************//
	
	// equation
	
	public EquationEnv addEquation(String text) {
		EquationEnv eqn = new EquationEnv() ;
		eqn.addText(text) ;
		return eqn ;
	}
	
	// subequations
	
	
	//*************** Adding Figures ****************//
	
	
	
	
	//*************** Adding Tables ****************//
	
	
	
	//*************** Build contents of .tex file ****************//
	
	public StringBuilder build() {
		// contents builder
		StringBuilder texdoc = new StringBuilder() ;
		
		// step 1: add document class command
		texdoc.append(documentClass) ;
		
		// step 2: add packages
		for(Package pkg : packages) {
			texdoc.append(pkg) ;
		}
		
		// step 3: extra preamble
		for(Object obj : preamble) {
			texdoc.append(obj) ;
		}
		
		// step 4: main text docEnv
		texdoc.append(docEnv) ;
		
		// do some stuff here to texdoc
		
		
		return texdoc ;
	}

	@Override
	public String toString() {
		return build().toString() ;
	}
	
	
	

}
