package org.gsl4j.plot.xy;

import org.gsl4j.plot.style.CapStyle;
import org.gsl4j.plot.style.Color;
import org.gsl4j.plot.style.DrawStyle;
import org.gsl4j.plot.style.FillStyle;
import org.gsl4j.plot.style.JoinStyle;
import org.gsl4j.plot.style.LineStyle;
import org.gsl4j.plot.style.Marker;

public class XYSeries {

	// data
	double[] x ;
	double[] y ;
	String xvar ;
	String yvar ;
	// basic properties
	String label ;
	String color ;
	// axis type
	boolean semilogx = false ;
	boolean semilogy = false ;
	boolean loglog = false ;
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

	public XYSeries(double[] x, double[] y, String xvar, String yvar, String color, String marker, double markerSize, String linestyle, double linewidth, String label) {
		this.x = x ;
		this.y = y ;
		this.xvar = (xvar!=null)? xvar.trim() : null ;
		this.yvar = (yvar!=null)? yvar.trim() : null ;
		this.color = (color!=null)? color.trim() : null ;
		this.label = (label!=null)? label.trim() : null ;
		this.marker = (marker!=null)? marker.trim() : null ;
		this.markerSize = markerSize ;
		this.linestyle = (linestyle!=null)? linestyle.trim() : null ;
		this.linewidth = linewidth ;
	}

	public XYSeries(double[] x, double[] y) {
		this.x = x ;
		this.y = y ;
	}

	public XYSeries() {

	}

	public XYSeries setXData(double[] x) {
		this.x = x ;
		return this ;
	}

	public XYSeries setYData(double[] y) {
		this.y = y ;
		return this ;
	}

	public XYSeries setXvar(String xvar) {
		this.xvar = (xvar!=null) ? xvar.trim() : null ;
		return this ;
	}

	public XYSeries setYvar(String yvar) {
		this.yvar = (yvar!=null) ? yvar.trim() : null ;
		return this ;
	}

	public XYSeries color(String color) {
		this.color = (color!=null) ? color.trim() : null ;
		return this ;
	}

	public XYSeries color(Color color) {
		this.color = (color!=null) ? color.toString().trim() : null ;
		return this ;
	}

	public XYSeries marker(String marker) {
		this.marker = (marker!=null) ? marker.trim() : null ;
		return this ;
	}

	public XYSeries marker(Marker marker) {
		this.marker = (marker!=null) ? marker.toString().trim() : null ;
		return this ;
	}

	public XYSeries markerSize(double markerSize) {
		this.markerSize = markerSize ;
		return this ;
	}

	public XYSeries markerEdgeColor(String markerEdgeColor) {
		this.markerEdgeColor = (markerEdgeColor!=null) ? markerEdgeColor.trim() : null ;
		return this ;
	}

	public XYSeries markerEdgeColor(Color markerEdgeColor) {
		this.markerEdgeColor = (markerEdgeColor!=null) ? markerEdgeColor.toString().trim() : null ;
		return this ;
	}

	public XYSeries markerEdgeWidth(double markerEdgeWidth) {
		this.markerEdgeWidth = markerEdgeWidth ;
		return this ;
	}

	public XYSeries markerFaceColor(String markerFaceColor) {
		this.markerFaceColor = (markerFaceColor!=null) ? markerFaceColor.trim() : null ;
		return this ;
	}

	public XYSeries markerFaceColor(Color markerFaceColor) {
		this.markerFaceColor = (markerFaceColor!=null) ? markerFaceColor.toString().trim() : null ;
		return this ;
	}

	public XYSeries linestyle(String linestyle) {
		this.linestyle = (linestyle!=null) ? linestyle.trim() : null ;
		return this ;
	}

	public XYSeries linestyle(LineStyle linestyle) {
		this.linestyle = (linestyle!=null) ? linestyle.toString().trim() : null ;
		return this ;
	}

	public XYSeries linewidth(double linewidth) {
		this.linewidth = linewidth ;
		return this ;
	}

	public XYSeries label(String label) {
		this.label = (label!=null) ? label.trim() : null ;
		return this ;
	}

	public XYSeries fillstyle(String fillStyle) {
		this.fillStyle = (fillStyle!=null) ? fillStyle.trim() : null ;
		return this ;
	}

	public XYSeries fillstyle(FillStyle fillStyle) {
		this.fillStyle = (fillStyle!=null) ? fillStyle.toString().trim() : null ;
		return this ;
	}

	public XYSeries drawstyle(String drawStyle) {
		this.drawStyle = (drawStyle!=null) ? drawStyle.trim() : null ;
		return this ;
	}

	public XYSeries drawstyle(DrawStyle drawStyle) {
		this.drawStyle = (drawStyle!=null) ? drawStyle.toString().trim() : null ;
		return this ;
	}

	public XYSeries solidCapStyle(String capStyle) {
		this.solidCapStyle = (capStyle!=null) ? capStyle.trim() : null ;
		return this ;
	}

	public XYSeries solidCapStyle(CapStyle capStyle) {
		this.solidCapStyle = (capStyle!=null) ? capStyle.toString().trim() : null ;
		return this ;
	}

	public XYSeries solidJoinStyle(String joinStyle) {
		this.solidJoinStyle = (joinStyle!=null) ? joinStyle.trim() : null ;
		return this ;
	}

	public XYSeries solidJoinStyle(JoinStyle joinStyle) {
		this.solidJoinStyle = (joinStyle!=null) ? joinStyle.toString().trim() : null ;
		return this ;
	}

	public XYSeries semilogx() {
		semilogx = true ;
		semilogy = false ;
		loglog = false ;
		return this ;
	}

	public XYSeries semilogy() {
		semilogx = false ;
		semilogy = true ;
		loglog = false ;
		return this ;
	}

	public XYSeries loglog() {
		semilogx = false ;
		semilogy = false ;
		loglog = true ;
		return this ;
	}

	String getPythonCode() {
		StringBuilder sb = new StringBuilder() ;
		if(semilogx)
			sb.append("plt.semilogx(") ;
		else if(semilogy)
			sb.append("plt.semilogy(") ;
		else if(loglog)
			sb.append("plt.loglog(") ;
		else
			sb.append("plt.plot(") ;
		if(xvar == null)
			throw new IllegalArgumentException("x variable cannot be NULL") ;
		else
			sb.append(xvar+", ") ;
		if(yvar == null)
			throw new IllegalArgumentException("x variable cannot be NULL") ;
		else
			sb.append(yvar) ;
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
