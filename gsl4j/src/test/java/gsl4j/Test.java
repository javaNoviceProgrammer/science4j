package gsl4j;

import org.gsl4j.util.MathUtils;

public class Test {

	public double test1(double x) {
		return x*x + Math.sin(x*x) ;
	}

	public static void main(String[] args) {
		Test test = new Test() ;

		long start = System.currentTimeMillis() ;
		double[] xvals = MathUtils.linspace(-100.0, 100.0, 10_000_000) ;
		double[] result = new double[xvals.length] ;

		for(int i=0; i<xvals.length; i++) {
			result[i] = test.test1(xvals[i]) ;
		}
		System.out.println(result[0]);
//		result = Arrays.stream(xvals).parallel().map(test::test1).toArray() ;
		long end = System.currentTimeMillis() ;
		System.out.println("Time (msec) = " + (end-start));
	}

}
