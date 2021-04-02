package latex4j.text;

import java.util.ArrayList;
import java.util.List;

import latex4j.base.Command;

public class HyperSetup {

	boolean isActive ;
	
	// Linking style options
	
	boolean hyperindex = true ;
	boolean linktocpage = false ;
	boolean breaklinks = false ;
	boolean colorlinks = false ;
	String linkcolor = "red" ;
	String anchorcolor = "black" ;
	String citecolor = "green" ;
	String filecolor = "cyan" ;
	String urlcolor = "magenta" ;
	boolean frenchlinks = false ;

	
	public HyperSetup(boolean isActive) {
		this.isActive = isActive ;
	}
	
	// configuration
	
	public HyperSetup hyperIndex(boolean flag) {
		this.hyperindex = flag ;
		return this ;
	}
	
	public HyperSetup linkTocPage(boolean flag) {
		this.linktocpage = flag ;
		return this ;
	}
	
	public HyperSetup breakLinks(boolean flag) {
		this.breaklinks = flag ;
		return this ;
	}
	
	public HyperSetup colorLinks(boolean flag) {
		this.colorlinks = flag ;
		return this ;
	}
	
	public HyperSetup linkColor(String color) {
		this.linkcolor = color ;
		return this ;
	}
	
	public HyperSetup anchorColor(String color) {
		this.anchorcolor = color ;
		return this ;
	}
	
	public HyperSetup citeColor(String color) {
		this.citecolor = color ;
		return this ;
	}
	
	public HyperSetup fileColor(String color) {
		this.filecolor = color ;
		return this ;
	}
	
	public HyperSetup urlColor(String color) {
		this.urlcolor = color ;
		return this ;
	}
	
	
	// particular command for \href
	public String href(String command, String text) {
		if(isActive) {
			Command hrefCommand = new Command("href", command, text) ;
			return hrefCommand.addNewLine(false).toString() ;
		}
		else {
			return text ;
		}
	}
	
	
	@Override
	public String toString() {
		if(isActive) {
			StringBuilder sb = new StringBuilder() ;
			sb.append("\\hypersetup{") ;
			List<String> options = new ArrayList<>() ;
			// go through all the flags (options) and add them
			options.add("hyperindex="+hyperindex) ;
			options.add("linktocpage="+linktocpage) ;
			options.add("breaklinks="+breaklinks) ;
			options.add("colorlinks="+colorlinks) ;
			options.add("linkcolor="+linkcolor) ;
			options.add("anchorcolor="+anchorcolor) ;
			options.add("citecolor="+citecolor) ;
			options.add("filecolor="+filecolor) ;
			options.add("urlcolor="+urlcolor) ;
			options.add("frenchlinks="+frenchlinks) ;
			// finalize the command
			if(!options.isEmpty())
				sb.append(String.join(", ", options)) ;
			sb.append("}\n") ;
			return sb.toString() ;
		}
		else
			return "" ;
	}
	

}
