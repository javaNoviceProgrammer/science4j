package org.pyplot4j.style;

public enum FillStyle {

	full("full") ,
	left("left") ,
	right("right") ,
	bottom("bottom") ,
	top("top") ,
	none("none") ;


	private String value ;

	private FillStyle(String value) {
		this.value = value ;
	}

	@Override
	public String toString() {
		return value ;
	}

}
