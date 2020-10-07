package org.gsl4j.plot.style;

public enum LineStyle {

	solid("-") ,
	dashed("--") ,
	dashdot("-.") ,
	dotted(":") ,
	none("") ;

	private String value ;

	private LineStyle(String value) {
		this.value = value ;
	}

	@Override
	public String toString() {
		return value ;
	}

}
