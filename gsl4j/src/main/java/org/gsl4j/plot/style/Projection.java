package org.gsl4j.plot.style;

public enum Projection {

	none("None") ,
	aitoff("aitoff") ,
	hammer("hammer") ,
	lambert("lambert") ,
	mollweide("mollweide") ,
	polar("polar") ,
	rectilinear("rectilinear") ;

	private String value ;

	private Projection(String value) {
		this.value = value ;
	}

	@Override
	public String toString() {
		return value ;
	}

}
