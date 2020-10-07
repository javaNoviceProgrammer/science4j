package org.gsl4j.plot.polar;

import org.gsl4j.plot.style.CapStyle;
import org.gsl4j.plot.style.Color;
import org.gsl4j.plot.style.DrawStyle;
import org.gsl4j.plot.style.FillStyle;
import org.gsl4j.plot.style.JoinStyle;
import org.gsl4j.plot.style.LineStyle;
import org.gsl4j.plot.style.Marker;



public class PolarSeries {

	// data
	double[] r ;
	double[] theta ;
	String rvar ;
	String thetavar ;
	// basic properties
	String label ;
	String color ;
	// additional Line2D params
	String drawStyle ;
	String fillStyle ;
	boolean inLayout ;
	int markEvery = 1 ;
	boolean rasterized ;
	boolean snap ;
	boolean visible = true ;
	// marker properties
	String marker ;
	double markerSize = 6.0 ;
	String markerEdgeColor ;
	double markerEdgeWidth = 1.0 ;
	String markerFaceColor ;
	// linestyle
	String linestyle ;
	double linewidth = 2.0 ;
	String solidCapStyle ;
	String solidJoinStyle ;

	public PolarSeries(double[] theta, double[] r) {
		this.r = r ;
		this.theta = theta ;
	}

	public PolarSeries setRData(double[] r) {
		this.r = r ;
		return this ;
	}

	public PolarSeries setThetaData(double[] theta) {
		this.theta = theta ;
		return this ;
	}

	public PolarSeries setRvar(String rvar) {
		this.rvar = (rvar!=null) ? rvar.trim() : null ;
		return this ;
	}

	public PolarSeries setThetaVar(String thetavar) {
		this.thetavar = (thetavar!=null) ? thetavar.trim() : null ;
		return this ;
	}

	public PolarSeries color(String color) {
		this.color = (color!=null) ? color.trim() : null ;
		return this ;
	}

	public PolarSeries color(Color color) {
		this.color = (color!=null) ? color.toString().trim() : null ;
		return this ;
	}

	public PolarSeries marker(String marker) {
		this.marker = (marker!=null) ? marker.trim() : null ;
		return this ;
	}

	public PolarSeries marker(Marker marker) {
		this.marker = (marker!=null) ? marker.toString().trim() : null ;
		return this ;
	}

	public PolarSeries markerSize(double markerSize) {
		this.markerSize = markerSize ;
		return this ;
	}

	public PolarSeries markerEdgeColor(String markerEdgeColor) {
		this.markerEdgeColor = (markerEdgeColor!=null) ? markerEdgeColor.trim() : null ;
		return this ;
	}

	public PolarSeries markerEdgeColor(Color markerEdgeColor) {
		this.markerEdgeColor = (markerEdgeColor!=null) ? markerEdgeColor.toString().trim() : null ;
		return this ;
	}

	public PolarSeries markerEdgeWidth(double markerEdgeWidth) {
		this.markerEdgeWidth = markerEdgeWidth ;
		return this ;
	}

	public PolarSeries markerFaceColor(String markerFaceColor) {
		this.markerFaceColor = (markerFaceColor!=null) ? markerFaceColor.trim() : null ;
		return this ;
	}

	public PolarSeries markerFaceColor(Color markerFaceColor) {
		this.markerFaceColor = (markerFaceColor!=null) ? markerFaceColor.toString().trim() : null ;
		return this ;
	}

	public PolarSeries linestyle(String linestyle) {
		this.linestyle = (linestyle!=null) ? linestyle.trim() : null ;
		return this ;
	}

	public PolarSeries linestyle(LineStyle linestyle) {
		this.linestyle = (linestyle!=null) ? linestyle.toString().trim() : null ;
		return this ;
	}

	public PolarSeries linewidth(double linewidth) {
		this.linewidth = linewidth ;
		return this ;
	}

	public PolarSeries label(String label) {
		this.label = (label!=null) ? label.trim() : null ;
		return this ;
	}

	public PolarSeries fillstyle(String fillStyle) {
		this.fillStyle = (fillStyle!=null) ? fillStyle.trim() : null ;
		return this ;
	}

	public PolarSeries fillstyle(FillStyle fillStyle) {
		this.fillStyle = (fillStyle!=null) ? fillStyle.toString().trim() : null ;
		return this ;
	}

	public PolarSeries drawstyle(String drawStyle) {
		this.drawStyle = (drawStyle!=null) ? drawStyle.trim() : null ;
		return this ;
	}

	public PolarSeries drawstyle(DrawStyle drawStyle) {
		this.drawStyle = (drawStyle!=null) ? drawStyle.toString().trim() : null ;
		return this ;
	}

	public PolarSeries solidCapStyle(String capStyle) {
		this.solidCapStyle = (capStyle!=null) ? capStyle.trim() : null ;
		return this ;
	}

	public PolarSeries solidCapStyle(CapStyle capStyle) {
		this.solidCapStyle = (capStyle!=null) ? capStyle.toString().trim() : null ;
		return this ;
	}

	public PolarSeries solidJoinStyle(String joinStyle) {
		this.solidJoinStyle = (joinStyle!=null) ? joinStyle.trim() : null ;
		return this ;
	}

	public PolarSeries solidJoinStyle(JoinStyle joinStyle) {
		this.solidJoinStyle = (joinStyle!=null) ? joinStyle.toString().trim() : null ;
		return this ;
	}

	String getPythonCode() {
		StringBuilder sb = new StringBuilder() ;
		sb.append("plt.plot(") ;
		if(thetavar == null)
			throw new IllegalArgumentException("Theta variable cannot be NULL") ;
		else
			sb.append(thetavar+",") ;
		if(rvar == null)
			throw new IllegalArgumentException("R variable cannot be NULL") ;
		else
			sb.append(rvar) ;
		if(color != null) {
			sb.append(", ") ;
			sb.append("color='" + color + "'") ;
		}
		if(marker != null) {
			sb.append(", ") ;
			sb.append("marker='" + marker + "'") ;
		}
		if(markerSize > 0) {
			sb.append(", ") ;
			sb.append("markersize=" + markerSize) ;
		}
		if(markerEdgeColor != null) {
			sb.append(", ") ;
			sb.append("markeredgecolor='" + markerEdgeColor + "'") ;
		}
		if(markerEdgeWidth >= 0) {
			sb.append(", ") ;
			sb.append("markeredgewidth=" + markerEdgeWidth) ;
		}
		if(markerFaceColor != null) {
			sb.append(", ") ;
			sb.append("markerfacecolor='" + markerFaceColor + "'") ;
		}
		if(linestyle != null) {
			sb.append(", ") ;
			sb.append("linestyle='" + linestyle + "'") ;
		}
		if(linewidth >= 0) {
			sb.append(", ") ;
			sb.append("linewidth=" + linewidth) ;
		}
		if(label != null) {
			sb.append(", ") ;
			sb.append("label='" + label + "'") ;
		}
		if(fillStyle != null) {
			sb.append(", ") ;
			sb.append("fillstyle='" + fillStyle + "'") ;
		}
		if(drawStyle != null) {
			sb.append(", ") ;
			sb.append("drawstyle='" + drawStyle + "'") ;
		}
		if(solidCapStyle != null) {
			sb.append(", ") ;
			sb.append("solid_capstyle='" + solidCapStyle + "'") ;
		}
		if(solidJoinStyle != null) {
			sb.append(", ") ;
			sb.append("solid_joinstyle='" + solidJoinStyle + "'") ;
		}
		sb.append(")") ;
		return sb.toString() ;
	}

	@Override
	public String toString() {
		return getPythonCode() ;
	}


}
