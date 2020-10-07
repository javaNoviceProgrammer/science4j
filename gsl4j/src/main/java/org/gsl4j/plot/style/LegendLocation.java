package org.gsl4j.plot.style;

public enum LegendLocation {

	best("best") ,
	upperRight("upper right") ,
	upperLeft("upper left") ,
	lowerLeft("lower left") ,
	lowerRight("lower right"),
	right("right") ,
	centerLeft("center left") ,
	centerRight("center right") ,
	lowerCenter("lower center") ,
	upperCenter("upper center") ,
	center("center") ;

	private String value ;

	private LegendLocation(String value) {
		this.value = value ;
	}

	@Override
	public String toString() {
		return value ;
	}

}
