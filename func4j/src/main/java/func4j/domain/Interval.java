package func4j.domain;

public class Interval implements IntegralDomain1D {
	
	double xmin, xmax ;
	
	public Interval(double xmin, double xmax) {
		this.xmin = xmin ;
		this.xmax = xmax ;
	}

	@Override
	public double var1Min() {
		return xmin ;
	}

	@Override
	public double var1Max() {
		return xmax ;
	}
	
	public static Interval of(double xmin, double xmax) {
		return new Interval(xmin, xmax) ;
	}

}
