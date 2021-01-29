package func4j.domain;

public class Circle2D implements IntegralDomain2D {
	
	double xcenter, ycenter ;
	double radius ;
	
	public Circle2D(double xcenter, double ycenter, double radius) {
		this.xcenter = xcenter ;
		this.ycenter = ycenter ;
		this.radius = radius ;
	}

	@Override
	public double var1Min() {
		return xcenter - radius ;
	}

	@Override
	public double var1Max() {
		return xcenter + radius ;
	}

	@Override
	public double var2Min(double var1) {
		return ycenter - Math.sqrt(radius*radius - (var1-xcenter)*(var1-xcenter)) ;
	}

	@Override
	public double var2Max(double var1) {
		return ycenter + Math.sqrt(radius*radius - (var1-xcenter)*(var1-xcenter)) ;
	}

}
