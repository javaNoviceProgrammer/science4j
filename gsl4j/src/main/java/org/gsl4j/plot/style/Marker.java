package org.gsl4j.plot.style;

public enum Marker {

	point(".") ,
	pixel(",") ,
	circle("o") ,
	triangleDown("v") ,
	triangleUp("^") ,
	triangleLeft("<") ,
	triangleRight(">") ,
	triDown("1") ,
	triUp("2") ,
	triLeft("3") ,
	triRight("4") ,
	square("s") ,
	pentagon("p") ,
	star("*") ,
	hexagon1("h") ,
	hexagon2("H") ,
	plus("+") ,
	x("x") ,
	diamond("D") ,
	thinDimond("d") ,
	vline("|") ,
	hline("_") ,
	none("") ;


	private String value ;

	private Marker(String value) {
		this.value = value ;
	}

	@Override
	public String toString() {
		return value ;
	}

}
