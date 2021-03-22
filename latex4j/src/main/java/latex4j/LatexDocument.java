package latex4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import latex4j.base.Environment;
import latex4j.base.Package;
import latex4j.document.ArticleClass;
import latex4j.document.DocumentClass;
import latex4j.fig.FigureEnv;
import latex4j.math.EquationEnv;
import latex4j.math.SubEquationsEnv;
import latex4j.text.FormattedText;

/**
 * Represents the contents of the .tex file --> Builder class
 * 
 */
public class LatexDocument { // another candidate: LatexBuilder
	
	// structure of latex file
	DocumentClass documentClass ;
	Set<Package> packages ;
	List<Object> preamble ; // anything after package configuration
	
	// main text
	Environment docEnv ;

	
	public LatexDocument() {
		documentClass = new ArticleClass() ;
		packages = new HashSet<>() ;
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
	
	public void defineColor(String name, float r, float g, float b) {
		if(r<0f || r>1f || g<0f || g>1f || b<0f || b>1f) 
			throw new IllegalArgumentException("r,g,b components must be in the range of [0f,1f]") ;
		// create \definecolor command
		String command = String.format("\\definecolor{%s}{rgb}{%f,%f,%f}\n", name, r, g, b) ;
		addPreamble(command) ; // important --> global definition before document environment
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
	
	public void addText(FormattedText text) {
		docEnv.addText(text) ;
	}
	

	//*************** Adding Equations ****************//
	
	// equation
	
	public void addEquation(String text) {
		EquationEnv eqn = new EquationEnv() ;
		eqn.addText(text) ;
		docEnv.addText(eqn) ;
	}
	
	public void addEquation(EquationEnv eqn) {
		if(eqn.isFloating()) {
			Package amsmath = new Package("amsmath") ;
			// check if amsmath package is already included
			if(!packages.contains(amsmath)) {
				packages.add(amsmath) ;
			}
		}
		docEnv.add(eqn) ;
	}
	
	// subequations
	
	public void addEquation(SubEquationsEnv eqns) {
		Package amsmath = new Package("amsmath") ;
		// check if amsmath package is already included
		if(!packages.contains(amsmath)) {
			packages.add(amsmath) ;
		}
		docEnv.add(eqns) ;
	}
	
	
	//*************** Adding Figures ****************//
	
	public void addFigure(FigureEnv fig) {
		docEnv.add(fig) ;
	}
	
	
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