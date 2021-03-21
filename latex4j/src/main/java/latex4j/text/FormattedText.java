package latex4j.text;

import java.awt.Color;
import static java.lang.String.format;

public class FormattedText {
	
	// composition
	StringBuilder sb ;
	StringBuilder coloredText ;
	String color ;
	boolean isBold = false ;
	boolean isItalic = false ;
	boolean hasUnderline = false ;

	public FormattedText() {
		sb = new StringBuilder() ;
		coloredText = new StringBuilder() ;
	}
	
	// builder pattern
	public FormattedText addText(String text) {
		if(color==null)
			sb.append(text) ;
		else {
			coloredText.append(text) ;
		}
		return this ;
	}
	
	public FormattedText space(int count) {
		for(int i=0; i<count; i++)
			addText(" ") ;
		return this ;
	}
	
	public FormattedText space() {
		addText(" ") ;
		return this ;
	}
	
	public FormattedText tab(int count) {
		for(int i=0; i<count; i++)
			addText("\t") ;
		return this ;
	}
	
	public FormattedText tab() {
		addText("\t") ;
		return this ;
	}
	
	// setting color
	
	public FormattedText color(String color) {
		if(!coloredText.isEmpty())
			sb.append(format("\\textcolor{%s}{%s}", this.color, coloredText.toString())) ;
		this.color = color ;
		coloredText = new StringBuilder() ;
		return this ;
	}
	
	public FormattedText color(Color color) { // color: rgb int 0-255, latex --> float 0-1
		
		return this ;
	}
	
	
	// bold font
	public FormattedText bold() {
		isBold = true ;
		return this ;
	}
	
	public FormattedText resetColor() {
		if(color!=null && !coloredText.isEmpty())
			sb.append(format("\\textcolor{%s}{%s}", this.color, coloredText.toString())) ;
		color = null ;
		coloredText = new StringBuilder() ;
		return this ;
	}
	
	
	
	@Override
	public String toString() {
		if(color!=null && !coloredText.isEmpty())
			sb.append(format("\\textcolor{%s}{%s}", this.color, coloredText.toString())) ;
		return sb.toString() ;
	}	
	

}
