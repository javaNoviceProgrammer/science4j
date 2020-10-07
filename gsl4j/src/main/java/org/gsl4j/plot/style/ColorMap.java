package org.gsl4j.plot.style;

public enum ColorMap {

	// Sequential
	viridis("viridis") ,
	plasma("plasma") ,
	inferno("inferno") ,
	magma("magma") ,
	cividis("cividis") ,
	// Cyclic
	twilight("twilight") ,
	twilight_shifted("twilight_shifted") ,
	hsv("hsv") ,
	// Miscellaneous
	flag("flag") ,
	prism("prism") ,
	ocean("ocean") ,
	gist_earth("gist_earth") ,
	terrain("terrain") ,
	gist_stern("gist_stern") ,
    gnuplot("gnuplot") ,
    gnuplot2("gnuplot2") ,
    cmr_map("CMRmap") ,
    cubehelix("cubehelix") ,
    brg("brg") ,
    gist_rainbow("gist_rainbow") ,
    rainbow("rainbow") ,
    jet("jet") ,
    nipy_spectral("nipy_spectral") ,
    gist_ncar("gist_ncar") ;






	private String value ;

	private ColorMap(String value) {
		this.value = value ;
	}

	@Override
	public String toString() {
		return value ;
	}

}
