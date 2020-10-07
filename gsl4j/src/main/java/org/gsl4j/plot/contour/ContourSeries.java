package org.gsl4j.plot.contour;

import java.util.Arrays;
import java.util.List;

import org.gsl4j.plot.style.Color;
import org.gsl4j.plot.style.ColorMap;
import org.gsl4j.plot.style.LineStyle;

import static java.lang.String.format;


public class ContourSeries {

	// variables
	double[] x ;
	double[] y ;
	String xvar ;
	String yvar ;
	double[][] z ;
	String zvar ;
	// meshgrid function
	MeshGrid func ;
	// parameters
	boolean filled = false ;
	boolean cornerMask ;
	double alpha = 1.0 ;
	String colorMap ;
	String color ;
	boolean antialiased = true ;
	double linewidth = 1.5 ;
	String linestyle ;
	List<String> hatches ;
	String extend ;
	String origin ;
	double[] extent ; // x0, x1, y0, y1
	boolean colorbar = false ;
	// labels
	ContourLabel clabel ;


	public ContourSeries(double[] x, double[] y, MeshGrid func) {
		this.x = x ;
		this.y = y ;
		this.func = func ;
		z = new double[y.length][x.length] ;
		for(int i=0; i<y.length; i++)
			for(int j=0; j<x.length; j++)
				z[i][j] = func.value(x[j], y[i]) ;
		this.clabel = new ContourLabel("") ;
	}

	public ContourSeries(double[] x, double[] y, double[][] z) {
		this.x = x ;
		this.y = y ;
		this.func = null ;
		this.z = z ;
		this.clabel = new ContourLabel("") ;
	}

	public ContourSeries setXData(double[] x) {
		this.x = x ;
		return this ;
	}

	public ContourSeries setYData(double[] y) {
		this.y = y ;
		return this ;
	}

	public ContourSeries setXvar(String xvar) {
		this.xvar = (xvar!=null) ? xvar.trim() : null ;
		return this ;
	}

	public ContourSeries setYvar(String yvar) {
		this.yvar = (yvar!=null) ? yvar.trim() : null ;
		return this ;
	}

	public ContourSeries setZvar(String zvar) {
		this.zvar = (zvar!=null) ? zvar.trim() : null ;
		return this ;
	}

	public ContourSeries filled(boolean filled) {
		this.filled = filled ;
		return this ;
	}

	public ContourSeries alpha(double alpha) {
		if(alpha > 1.0 || alpha < 0.0)
			throw new IllegalArgumentException("Valid range for alpha: 0 <= alpha <= 1") ;
		this.alpha = alpha ;
		return this ;
	}

	public ContourSeries cornerMask(boolean cornerMask) {
		this.cornerMask = cornerMask ;
		return this ;
	}

	public ContourSeries linewidth(double linewidth) {
		this.linewidth = linewidth ;
		return this ;
	}

	public ContourSeries linestyle(String linestyle) {
		this.linestyle = (linestyle!=null) ? linestyle.trim() : null ;
		return this ;
	}

	public ContourSeries linestyle(LineStyle linestyle) {
		this.linestyle = (linestyle!=null) ? linestyle.toString().trim() : null ;
		return this ;
	}

	public ContourSeries antialiased(boolean antialiased) {
		this.antialiased = antialiased ;
		return this ;
	}

	public ContourSeries cmap(String cmap) {
		this.colorMap = (cmap!=null) ? cmap.trim() : null ;
		return this ;
	}

	public ContourSeries cmap(ColorMap cmap) {
		this.colorMap = (cmap!=null) ? cmap.toString().trim() : null ;
		return this ;
	}

	public ContourSeries colorbar(boolean colorbar) {
		this.colorbar = colorbar ;
		return this ;
	}

	public ContourSeries color(String color) {
		this.color = (color!=null) ? color.trim() : null ;
		return this ;
	}

	public ContourSeries color(Color color) {
		this.color = (color!=null) ? color.toString().trim() : null ;
		return this ;
	}

	public ContourLabel clabel() {
		return clabel ;
	}


	String getPythonCode() {
		StringBuilder sb = new StringBuilder() ;
		if(func == null)
			throw new IllegalArgumentException("MeshGrid function cannot be NULL") ;
		if(xvar == null)
			throw new IllegalArgumentException("x variable cannot be NULL") ;
		if(yvar == null)
			throw new IllegalArgumentException("x variable cannot be NULL") ;
		// create z data
		sb.append(format("%s, %s = np.meshgrid(%s, %s);\n", xvar.toUpperCase(), yvar.toUpperCase(), xvar, yvar)) ;
		sb.append(format("%s = np.array(%s);\n", zvar.toUpperCase(), Arrays.deepToString(z))) ;
		if(clabel==null || clabel.levels==null) {
			if(filled)
				sb.append(format("plt.contourf(%s, %s, %s", xvar.toUpperCase(), yvar.toUpperCase(), zvar.toUpperCase())) ;
			else
				sb.append(format("plt.contour(%s, %s, %s", xvar.toUpperCase(), yvar.toUpperCase(), zvar.toUpperCase())) ;
		}
		else {
			if(filled)
				sb.append(format("%s = plt.contourf(%s, %s, %s, levels=%s", clabel.name, xvar.toUpperCase(), yvar.toUpperCase(), zvar.toUpperCase(), Arrays.toString(clabel.levels))) ;
			else
				sb.append(format("%s = plt.contour(%s, %s, %s, levels=%s", clabel.name, xvar.toUpperCase(), yvar.toUpperCase(), zvar.toUpperCase(), Arrays.toString(clabel.levels))) ;
		}
		if(alpha >=0 ) {
			sb.append(", ") ;
			sb.append(format("alpha=%f", alpha)) ;
		}
		if(cornerMask) {
			sb.append(", ") ;
			sb.append(format("corner_mask=%s", "True")) ;
		}
		else {
			sb.append(", ") ;
			sb.append(format("corner_mask=%s", "False")) ;
		}
		if(linewidth >= 0.0 && !filled) {
			sb.append(", ") ;
			sb.append(format("linewidths=%f", linewidth)) ;
		}
		if(linestyle != null) {
			sb.append(", ") ;
			sb.append(format("linestyles='%s'", linestyle)) ;
		}
		if(antialiased) {
			sb.append(", ") ;
			sb.append(format("antialiased=%s", "True")) ;
		}
		else {
			sb.append(", ") ;
			sb.append(format("antialiased=%s", "False")) ;
		}
		if(color != null) {
			sb.append(", ") ;
			sb.append(format("colors='%s'", color)) ;
		}
		if(colorMap != null) {
			sb.append(", ") ;
			sb.append(format("cmap='%s'", colorMap)) ;
		}

		sb.append(")\n") ;
		// clable style options
		if(clabel != null && clabel.levels != null && clabel.levels.length > 0)
			sb.append(format("%s\n", clabel)) ;
		// colorbar for each contour series
		if(colorbar) {
			sb.append("plt.colorbar()\n") ;
		}
		// return the result
		return sb.toString() ;
	}

	@Override
	public String toString() {
		return getPythonCode() ;
	}


	public static void main(String[] args) {
		double[] x = {1.1, 2.2, 3.3} ;
		double[] y = {-1.0, -2.0, -3.0, -4.0} ;
		ContourSeries series = new ContourSeries(x, y, (z,w)->z*w) ;
		series.setXvar("x1").setYvar("y1").setZvar("z1").colorbar(true) ;
//		System.out.println(Arrays.deepToString(series.z));
		System.out.println(series);
	}


}
