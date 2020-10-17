package gsl4j;

import java.util.List;

import org.gsl4j.roots.RealRoot;
import org.gsl4j.roots.RealRootFunction;

public class TestRealRoots {

	public static void test1() {
		RealRootFunction func = x -> Math.sin(x*x*x) ;
		RealRoot rootFinder = new RealRoot(func) ;
		List<Double> allRoots = rootFinder.brent(-2.0, 2.0, 1000) ;
		System.out.println(allRoots);
	}

	public static void main(String[] args) {
		test1() ;
	}

}
