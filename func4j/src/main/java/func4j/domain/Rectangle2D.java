package func4j.domain;

public class Rectangle2D implements IntegralDomain2D {
	
	double xmin, xmax ;
	double ymin, ymax ;
	
	public Rectangle2D(double xmin, double xmax, double ymin, double ymax) {
		this.xmin = xmin ;
		this.xmax = xmax ;
		this.ymin = ymin ;
		this.ymax = ymax ;
	}
	
	public Rectangle2D(Interval x, Interval y) {
		this.xmin = x.var1Min() ;
		this.xmax = x.var1Max() ;
		this.ymin = y.var1Min() ;
		this.ymax = y.var1Max() ;
	}
	
	public double circumference() {
		return 2.0*(Math.abs(xmax-xmin)+Math.abs(ymax-ymin)) ;
	}
	
	public double area() {
		return Math.abs((xmax-xmin)*(ymax-ymin)) ;
	}

	@Override
	public double var1Min() {
		return xmin ;
	}

	@Override
	public double var1Max() {
		return xmax ;
	}

	@Override
	public double var2Min(double var1) {
		return ymin ;
	}

	@Override
	public double var2Max(double var1) {
		return ymax ;
	}

}
