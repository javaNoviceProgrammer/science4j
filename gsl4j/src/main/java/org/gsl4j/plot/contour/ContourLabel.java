package org.gsl4j.plot.contour;

import static java.lang.String.format ;
import java.util.List;
import org.gsl4j.plot.style.Color;


public class ContourLabel {

	// variable name
	String name ;
	// parameters
	double[] levels ;
	double fontsize = 10.0 ;
	String color ;
	boolean inline = true ;
	double inlineSpacing = 5.0 ;
	String fmt ; // label formatter
	boolean manual = false ;
	boolean rightsideUp = true ;
	boolean useClabelText = false ;


	public ContourLabel(String name) {
		this.name = name ;
	}

	public ContourLabel name(String name) {
		this.name = name ;
		return this ;
	}

	// vararg
	public ContourLabel levels(double... levels) {
		this.levels = levels ;
		return this ;
	}

	public ContourLabel levels(List<Double> levels) {
		this.levels = new double[levels.size()] ;
		for(int i=0; i<this.levels.length; i++)
			this.levels[i] = levels.get(i) ;
		return this ;
	}

	public ContourLabel fontsize(double fontsize) {
		this.fontsize = fontsize ;
		return this ;
	}

	public ContourLabel color(String color) {
		this.color = (color!=null) ? color.trim() : null ;
		return this ;
	}

	public ContourLabel color(Color color) {
		this.color = (color!=null) ? color.toString().trim() : null ;
		return this ;
	}

	public ContourLabel inline(boolean inline) {
		this.inline = inline ;
		return this ;
	}

	public ContourLabel inlineSpacing(double inlineSpacing) {
		this.inlineSpacing = inlineSpacing ;
		return this ;
	}

	public ContourLabel fmt(String fmt) {
		this.fmt = (fmt!=null) ? fmt.trim() : null ;
		return this ;
	}

	public ContourLabel manual(boolean manual) {
		this.manual = manual ;
		return this ;
	}

	public ContourLabel rightsideUp(boolean rightsideUp) {
		this.rightsideUp = rightsideUp ;
		return this ;
	}

	public ContourLabel useClabelText(boolean useClabelText) {
		this.useClabelText = useClabelText ;
		return this ;
	}

	String getPythonCode() {
		StringBuilder sb = new StringBuilder() ;
		sb.append(format("plt.clabel(%s ", name)) ;
		if(color != null) {
			sb.append(", ") ;
			sb.append(format("colors='%s'", color)) ;
		}
		if(!inline) {
			sb.append(", ") ;
			sb.append(format("inline=%s", "False")) ;
		}
		else {
			sb.append(", ") ;
			sb.append(format("inline=%s", "True")) ;
		}
		if(inlineSpacing >= 0.0 ) {
			sb.append(", ") ;
			sb.append(format("inline_spacing=%f", inlineSpacing)) ;
		}
		if(fontsize >= 0.0 ) {
			sb.append(", ") ;
			sb.append(format("fontsize=%f", fontsize)) ;
		}
		if(fmt != null) {
			sb.append(", ") ;
			sb.append(format("fmt='%s'", fmt)) ;
		}
		if(manual) {
			sb.append(", ") ;
			sb.append(format("manual=%s", "True")) ;
		}
		else {
			sb.append(", ") ;
			sb.append(format("manual=%s", "False")) ;
		}
		if(rightsideUp) {
			sb.append(", ") ;
			sb.append(format("rightside_up=%s", "True")) ;
		}
		else {
			sb.append(", ") ;
			sb.append(format("rightside_up=%s", "False")) ;
		}
		if(useClabelText) {
			sb.append(", ") ;
			sb.append(format("use_clabeltext=%s", "True")) ;
		}
		else {
			sb.append(", ") ;
			sb.append(format("use_clabeltext=%s", "False")) ;
		}


		sb.append(")") ;
		return sb.toString() ;
	}


	@Override
	public String toString() {
		return getPythonCode() ;
	}

	// test
	public static void main(String[] args) {
		ContourLabel clabel = new ContourLabel("cs1") ;
		clabel.color("b").inline(false) ;
		System.out.println(clabel);
	}

}
