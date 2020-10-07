package org.gsl4j.plot.style;

public enum FontSize {

	xx_small("xx-small") ,
	x_small("x-small") ,
	small("small") ,
	medium("medium") ,
	large("large") ,
	x_large("x-large") ,
	xx_large("xx-large") ;

	private String value ;

	private FontSize(String value) {
		this.value = value ;
	}

	@Override
	public String toString() {
		return value ;
	}

}
