package pyplot4j.xyz;

import pyplot4j.style.CapStyle;
import pyplot4j.style.Color;
import pyplot4j.style.DrawStyle;
import pyplot4j.style.FillStyle;
import pyplot4j.style.JoinStyle;
import pyplot4j.style.LineStyle;
import pyplot4j.style.Marker;


public class XYZWireframeSeries {
	
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
	boolean depthShade = false ;
	int size = -1 ;
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
	// strides
	double rstride = -1.0 ;
	double cstride = -1.0 ;
	int rcount = 50 ;
	int ccount = 50 ;
	
	
	public XYZWireframeSeries(double[] x, double[] y, double[] z, String xvar, String yvar, String zvar, String zdir, String color, String marker, double markerSize, String linestyle, double linewidth, String label) {
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
	
	public XYZWireframeSeries(double[] x, double[] y, double[] z, String xvar, String yvar, String zvar, String color, String marker, double markerSize, String linestyle, double linewidth, String label) {
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

	public XYZWireframeSeries(double[] x, double[] y, double[] z) {
		this.x = x ;
		this.y = y ;
		this.z = z ;
	}

	public XYZWireframeSeries() {

	}

	public XYZWireframeSeries setXData(double[] x) {
		this.x = x ;
		return this ;
	}

	public XYZWireframeSeries setYData(double[] y) {
		this.y = y ;
		return this ;
	}
	
	public XYZWireframeSeries setZData(double[] z) {
		this.z = z ;
		return this ;
	}

	public XYZWireframeSeries setXvar(String xvar) {
		this.xvar = (xvar!=null) ? xvar.trim() : null ;
		return this ;
	}

	public XYZWireframeSeries setYvar(String yvar) {
		this.yvar = (yvar!=null) ? yvar.trim() : null ;
		return this ;
	}
	
	public XYZWireframeSeries setZvar(String zvar) {
		this.zvar = (zvar!=null) ? zvar.trim() : null ;
		return this ;
	}

	public XYZWireframeSeries color(String color) {
		this.color = (color!=null) ? color.trim() : null ;
		return this ;
	}

	public XYZWireframeSeries color(Color color) {
		this.color = (color!=null) ? color.toString().trim() : null ;
		return this ;
	}

	public XYZWireframeSeries marker(String marker) {
		this.marker = (marker!=null) ? marker.trim() : null ;
		return this ;
	}

	public XYZWireframeSeries marker(Marker marker) {
		this.marker = (marker!=null) ? marker.toString().trim() : null ;
		return this ;
	}

	public XYZWireframeSeries markerSize(double markerSize) {
		this.markerSize = markerSize ;
		return this ;
	}

	public XYZWireframeSeries markerEdgeColor(String markerEdgeColor) {
		this.markerEdgeColor = (markerEdgeColor!=null) ? markerEdgeColor.trim() : null ;
		return this ;
	}

	public XYZWireframeSeries markerEdgeColor(Color markerEdgeColor) {
		this.markerEdgeColor = (markerEdgeColor!=null) ? markerEdgeColor.toString().trim() : null ;
		return this ;
	}

	public XYZWireframeSeries markerEdgeWidth(double markerEdgeWidth) {
		this.markerEdgeWidth = markerEdgeWidth ;
		return this ;
	}

	public XYZWireframeSeries markerFaceColor(String markerFaceColor) {
		this.markerFaceColor = (markerFaceColor!=null) ? markerFaceColor.trim() : null ;
		return this ;
	}

	public XYZWireframeSeries markerFaceColor(Color markerFaceColor) {
		this.markerFaceColor = (markerFaceColor!=null) ? markerFaceColor.toString().trim() : null ;
		return this ;
	}

	public XYZWireframeSeries linestyle(String linestyle) {
		this.linestyle = (linestyle!=null) ? linestyle.trim() : null ;
		return this ;
	}

	public XYZWireframeSeries linestyle(LineStyle linestyle) {
		this.linestyle = (linestyle!=null) ? linestyle.toString().trim() : null ;
		return this ;
	}

	public XYZWireframeSeries linewidth(double linewidth) {
		this.linewidth = linewidth ;
		return this ;
	}

	public XYZWireframeSeries label(String label) {
		this.label = (label!=null) ? label.trim() : null ;
		return this ;
	}

	public XYZWireframeSeries fillstyle(String fillStyle) {
		this.fillStyle = (fillStyle!=null) ? fillStyle.trim() : null ;
		return this ;
	}

	public XYZWireframeSeries fillstyle(FillStyle fillStyle) {
		this.fillStyle = (fillStyle!=null) ? fillStyle.toString().trim() : null ;
		return this ;
	}

	public XYZWireframeSeries drawstyle(String drawStyle) {
		this.drawStyle = (drawStyle!=null) ? drawStyle.trim() : null ;
		return this ;
	}

	public XYZWireframeSeries drawstyle(DrawStyle drawStyle) {
		this.drawStyle = (drawStyle!=null) ? drawStyle.toString().trim() : null ;
		return this ;
	}

	public XYZWireframeSeries solidCapStyle(String capStyle) {
		this.solidCapStyle = (capStyle!=null) ? capStyle.trim() : null ;
		return this ;
	}

	public XYZWireframeSeries solidCapStyle(CapStyle capStyle) {
		this.solidCapStyle = (capStyle!=null) ? capStyle.toString().trim() : null ;
		return this ;
	}

	public XYZWireframeSeries solidJoinStyle(String joinStyle) {
		this.solidJoinStyle = (joinStyle!=null) ? joinStyle.trim() : null ;
		return this ;
	}

	public XYZWireframeSeries solidJoinStyle(JoinStyle joinStyle) {
		this.solidJoinStyle = (joinStyle!=null) ? joinStyle.toString().trim() : null ;
		return this ;
	}

	public XYZWireframeSeries semilogx() {
		semilogx = true ;
		semilogy = false ;
		loglog = false ;
		return this ;
	}

	public XYZWireframeSeries semilogy() {
		semilogx = false ;
		semilogy = true ;
		loglog = false ;
		return this ;
	}

	public XYZWireframeSeries loglog() {
		semilogx = false ;
		semilogy = false ;
		loglog = true ;
		return this ;
	}
	
	public XYZWireframeSeries depthShade(boolean depthShade) {
		this.depthShade = depthShade ;
		return this ;
	}
	
	public XYZWireframeSeries size(int s) {
		size = s ;
		return this ;
	}

	String getPythonCode() {
		StringBuilder sb = new StringBuilder() ;
		sb.append("plt.gca(projection='3d').plot_wireframe(") ;
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
		if(rstride > 0 ) {
			sb.append(", ") ;
			sb.append("rstride=" + rstride) ;
		}
		if(cstride > 0 ) {
			sb.append(", ") ;
			sb.append("cstride=" + cstride) ;
		}
		if(rcount > 0 ) {
			sb.append(", ") ;
			sb.append("rcount=" + rcount) ;
		}
		if(ccount > 0 ) {
			sb.append(", ") ;
			sb.append("ccount=" + ccount) ;
		}
		if(zdir != null) {
			sb.append(", ") ;
			sb.append("zdir='" + zdir + "'") ;
		}
		if(size > 0) {
			sb.append(", ") ;
			sb.append("s=" + size) ;
		}
		if(depthShade) {
			sb.append(", ") ;
			sb.append("depthshade=True") ;
		}
		if(color != null) {
			sb.append(", ") ;
			sb.append("color='" + color + "'") ;
		}
		if(marker != null) {
			sb.append(", ") ;
			sb.append("marker='" + marker + "'") ;
		}
//		if(markerSize > 0) {
//			sb.append(", ") ;
//			sb.append("markersize=" + markerSize) ;
//		}
		if(markerEdgeColor != null) {
			sb.append(", ") ;
			sb.append("markeredgecolor='" + markerEdgeColor + "'") ;
		}
//		if(markerEdgeWidth >= 0) {
//			sb.append(", ") ;
//			sb.append("markeredgewidth=" + markerEdgeWidth) ;
//		}
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
