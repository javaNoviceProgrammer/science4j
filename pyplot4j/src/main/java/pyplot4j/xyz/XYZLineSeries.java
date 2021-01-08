package pyplot4j.xyz;

import pyplot4j.style.CapStyle;
import pyplot4j.style.Color;
import pyplot4j.style.DrawStyle;
import pyplot4j.style.FillStyle;
import pyplot4j.style.JoinStyle;
import pyplot4j.style.LineStyle;
import pyplot4j.style.Marker;


public class XYZLineSeries {
	
	// data
	double[] x ;
	double[] y ;
	double[] z ;
	String xvar ;
	String yvar ;
	String zvar ;
	// basic properties
	String label ;
	String color ;
	// axis type
	boolean semilogx = false ; // ?
	boolean semilogy = false ; // ?
	boolean loglog = false ; // ?
	// additional Line2D params // ?
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
	// zdir
	String zdir ;
	double zs = 0.0 ;
	
	
	public XYZLineSeries(double[] x, double[] y, double[] z, String xvar, String yvar, String zvar, String zdir, String color, String marker, double markerSize, String linestyle, double linewidth, String label) {
		this.x = x ;
		this.y = y ;
		this.z = z ;
		this.xvar = (xvar!=null)? xvar.trim() : null ;
		this.yvar = (yvar!=null)? yvar.trim() : null ;
		this.zvar = (zvar!=null)? zvar.trim() : null ;
		this.zdir = (zdir!=null)? zdir.trim() : null ;
		this.color = (color!=null)? color.trim() : null ;
		this.label = (label!=null)? label.trim() : null ;
		this.marker = (marker!=null)? marker.trim() : null ;
		this.markerSize = markerSize ;
		this.linestyle = (linestyle!=null)? linestyle.trim() : null ;
		this.linewidth = linewidth ;
	}
	
	public XYZLineSeries(double[] x, double[] y, double[] z, String xvar, String yvar, String zvar, String color, String marker, double markerSize, String linestyle, double linewidth, String label) {
		this.x = x ;
		this.y = y ;
		this.z = z ;
		this.xvar = (xvar!=null)? xvar.trim() : null ;
		this.yvar = (yvar!=null)? yvar.trim() : null ;
		this.zvar = (zvar!=null)? zvar.trim() : null ;
		this.zdir = "z" ;
		this.color = (color!=null)? color.trim() : null ;
		this.label = (label!=null)? label.trim() : null ;
		this.marker = (marker!=null)? marker.trim() : null ;
		this.markerSize = markerSize ;
		this.linestyle = (linestyle!=null)? linestyle.trim() : null ;
		this.linewidth = linewidth ;
	}

	public XYZLineSeries(double[] x, double[] y, double[] z) {
		this.x = x ;
		this.y = y ;
		this.z = z ;
	}

	public XYZLineSeries() {

	}

	public XYZLineSeries setXData(double[] x) {
		this.x = x ;
		return this ;
	}

	public XYZLineSeries setYData(double[] y) {
		this.y = y ;
		return this ;
	}
	
	public XYZLineSeries setZData(double[] z) {
		this.z = z ;
		return this ;
	}

	public XYZLineSeries setXvar(String xvar) {
		this.xvar = (xvar!=null) ? xvar.trim() : null ;
		return this ;
	}

	public XYZLineSeries setYvar(String yvar) {
		this.yvar = (yvar!=null) ? yvar.trim() : null ;
		return this ;
	}
	
	public XYZLineSeries setZvar(String zvar) {
		this.zvar = (zvar!=null) ? zvar.trim() : null ;
		return this ;
	}

	public XYZLineSeries color(String color) {
		this.color = (color!=null) ? color.trim() : null ;
		return this ;
	}

	public XYZLineSeries color(Color color) {
		this.color = (color!=null) ? color.toString().trim() : null ;
		return this ;
	}

	public XYZLineSeries marker(String marker) {
		this.marker = (marker!=null) ? marker.trim() : null ;
		return this ;
	}

	public XYZLineSeries marker(Marker marker) {
		this.marker = (marker!=null) ? marker.toString().trim() : null ;
		return this ;
	}

	public XYZLineSeries markerSize(double markerSize) {
		this.markerSize = markerSize ;
		return this ;
	}

	public XYZLineSeries markerEdgeColor(String markerEdgeColor) {
		this.markerEdgeColor = (markerEdgeColor!=null) ? markerEdgeColor.trim() : null ;
		return this ;
	}

	public XYZLineSeries markerEdgeColor(Color markerEdgeColor) {
		this.markerEdgeColor = (markerEdgeColor!=null) ? markerEdgeColor.toString().trim() : null ;
		return this ;
	}

	public XYZLineSeries markerEdgeWidth(double markerEdgeWidth) {
		this.markerEdgeWidth = markerEdgeWidth ;
		return this ;
	}

	public XYZLineSeries markerFaceColor(String markerFaceColor) {
		this.markerFaceColor = (markerFaceColor!=null) ? markerFaceColor.trim() : null ;
		return this ;
	}

	public XYZLineSeries markerFaceColor(Color markerFaceColor) {
		this.markerFaceColor = (markerFaceColor!=null) ? markerFaceColor.toString().trim() : null ;
		return this ;
	}

	public XYZLineSeries linestyle(String linestyle) {
		this.linestyle = (linestyle!=null) ? linestyle.trim() : null ;
		return this ;
	}

	public XYZLineSeries linestyle(LineStyle linestyle) {
		this.linestyle = (linestyle!=null) ? linestyle.toString().trim() : null ;
		return this ;
	}

	public XYZLineSeries linewidth(double linewidth) {
		this.linewidth = linewidth ;
		return this ;
	}

	public XYZLineSeries label(String label) {
		this.label = (label!=null) ? label.trim() : null ;
		return this ;
	}

	public XYZLineSeries fillstyle(String fillStyle) {
		this.fillStyle = (fillStyle!=null) ? fillStyle.trim() : null ;
		return this ;
	}

	public XYZLineSeries fillstyle(FillStyle fillStyle) {
		this.fillStyle = (fillStyle!=null) ? fillStyle.toString().trim() : null ;
		return this ;
	}

	public XYZLineSeries drawstyle(String drawStyle) {
		this.drawStyle = (drawStyle!=null) ? drawStyle.trim() : null ;
		return this ;
	}

	public XYZLineSeries drawstyle(DrawStyle drawStyle) {
		this.drawStyle = (drawStyle!=null) ? drawStyle.toString().trim() : null ;
		return this ;
	}

	public XYZLineSeries solidCapStyle(String capStyle) {
		this.solidCapStyle = (capStyle!=null) ? capStyle.trim() : null ;
		return this ;
	}

	public XYZLineSeries solidCapStyle(CapStyle capStyle) {
		this.solidCapStyle = (capStyle!=null) ? capStyle.toString().trim() : null ;
		return this ;
	}

	public XYZLineSeries solidJoinStyle(String joinStyle) {
		this.solidJoinStyle = (joinStyle!=null) ? joinStyle.trim() : null ;
		return this ;
	}

	public XYZLineSeries solidJoinStyle(JoinStyle joinStyle) {
		this.solidJoinStyle = (joinStyle!=null) ? joinStyle.toString().trim() : null ;
		return this ;
	}

	public XYZLineSeries semilogx() {
		semilogx = true ;
		semilogy = false ;
		loglog = false ;
		return this ;
	}

	public XYZLineSeries semilogy() {
		semilogx = false ;
		semilogy = true ;
		loglog = false ;
		return this ;
	}

	public XYZLineSeries loglog() {
		semilogx = false ;
		semilogy = false ;
		loglog = true ;
		return this ;
	}

	String getPythonCode() {
		StringBuilder sb = new StringBuilder() ;
		if(semilogx)
			sb.append("plt.gca(projection='3d').semilogx(") ;
		else if(semilogy)
			sb.append("plt.gca(projection='3d').semilogy(") ;
		else if(loglog)
			sb.append("plt.gca(projection='3d').loglog(") ;
		else
			sb.append("plt.gca(projection='3d').plot(") ;
		if(xvar == null)
			throw new IllegalArgumentException("x variable cannot be NULL") ;
		else
			sb.append(xvar+", ") ;
		if(yvar == null)
			throw new IllegalArgumentException("x variable cannot be NULL") ;
		else
			sb.append(yvar+", ") ;
		if(zvar == null)
			throw new IllegalArgumentException("z variable cannot be NULL") ;
		else
			sb.append("zs=" + zvar) ;
		if(zdir != null) {
			sb.append(", ") ;
			sb.append("zdir='" + zdir + "'") ;
		}
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
