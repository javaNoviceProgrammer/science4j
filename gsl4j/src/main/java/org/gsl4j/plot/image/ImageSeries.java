package org.gsl4j.plot.image;

public class ImageSeries {

	// plt.imshow(vals, interpolation='gaussian', extent=(0.0, 5.0, 2.0, -3.0))

	String title ;
	String interpolation ;
	double[] extent ;
	String cmap ;
	String aspect ;
	double alpha = 1.0 ;
	double vmin = Double.NaN ;
	double vmax = Double.NaN ;
	String origin ;
	boolean filterNorm = true ;
	double filterrad = 4.0 ;
	boolean resample = false ;
	String url ;




	public ImageSeries(String title) {
		this.title = title ;
	}


	String getPythonCode() {
		return null ;
	}

	@Override
	public String toString() {
		return getPythonCode() ;
	}



}
