package latex4j.text;

import static java.lang.String.format;

public class FormattedText {
	
	// composition
	StringBuilder sb ;
	StringBuilder coloredText ;
	String color ;

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
	
	// bold font
	public FormattedText bold(String text) {
		addText(format("\\textbf{%s}", text)) ;
		return this ;
	}
	
	public FormattedText italic(String text) {
		addText(format("\\textit{%s}", text)) ;
		return this ;
	}
	
	public FormattedText underline(String text) {
		addText(format("\\underline{%s}", text)) ;
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
