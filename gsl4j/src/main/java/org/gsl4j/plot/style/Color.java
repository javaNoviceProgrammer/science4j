package org.gsl4j.plot.style;

public enum Color {

	blue("b") ,
	green("g") ,
	red("r") ,
	cyan("c") ,
	magenta("m") ,
	yellow("y") ,
	black("k") ,
	white("w") ;

	private String value ;

	private Color(String value) {
		this.value = value ;
	}

	private Color(int value) {
		this.value = String.valueOf(Integer.toHexString(value)) ;
	}

	@Override
	public String toString() {
		return value ;
	}

}
