package org.gsl4j.plot.style;

public enum InterpolationType {

	none("none") ,
	antialiased("antialiased") ,
	nearest("nearest") ,
	bilinear("bilinear") ,
	bicubic("bicubic") ,
	spline16("spline16") ,
	spline36("spline36") ,
	hanning("hanning") ,
	hamming("hamming") ,
	hermite("hermite") ,
	kaiser("kaiser") ,
	quadric("quadric") ,
	catrom("catrom") ,
	gaussian("gaussian") ,
	bessel("bessel") ,
	mitchell("mitchell") ,
	sinc("sinc") ,
	lanczos("lanczos") ;

	private String value ;

	private InterpolationType(String value) {
		this.value = value ;
	}

	@Override
	public String toString() {
		return value ;
	}

}
