package func4j.domain;

public class Ellipse2D implements IntegralDomain2D {

	double xcenter, ycenter ;
	double radiusX ;
	double radiusY ;
	
	// x^2/(radiusX^2) + y^2/(radiusY)^2 = 1
	public Ellipse2D(double xcenter, double ycenter, double radiusX, double radiusY) {
		this.xcenter = xcenter ;
		this.ycenter = ycenter ;
		this.radiusX = radiusX ;
		this.radiusY = radiusY ;
	}
	
	public static Ellipse2D of(double xmin, double xmax, double ymin, double ymax) {
		double xcenter = 0.5*(xmin+xmax) ;
		double ycenter = 0.5*(ymin+ymax) ;
		double radiusX = xmax - xcenter ;
		double radiusY = ymax - ycenter ;
		return new Ellipse2D(xcenter, ycenter, radiusX, radiusY) ;
	}
	
	@Override
	public double var1Min() {
		return xcenter - radiusX ;
	}

	@Override
	public double var1Max() {
		return xcenter + radiusX ;
	}

	@Override
	public double var2Min(double var1) {
		double x = (var1-xcenter)/radiusX ;
		return ycenter - Math.sqrt(1 - x*x) * radiusY ;
	}

	@Override
	public double var2Max(double var1) {
		double x = (var1-xcenter)/radiusX ;
		return ycenter + Math.sqrt(1 - x*x) * radiusY ;
	}

}
