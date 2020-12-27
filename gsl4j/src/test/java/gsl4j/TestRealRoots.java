package gsl4j;

import java.util.List;

import org.gsl4j.roots.RealRoot;
import org.gsl4j.roots.RealRootFunction;
import org.gsl4j.util.Timer;

public class TestRealRoots {

	public static void test1() {
		Timer timer = new Timer() ;
		timer.start();

		RealRootFunction func = x -> Math.sin(x*x) ;
		RealRoot rootFinder = new RealRoot(func) ;
		rootFinder.setAbsErrForUniqueness(0.0001);
		List<Double> allRoots = rootFinder.brent(-2.0, 2.0, 10) ;
		System.out.println(allRoots);

		timer.stop();
		timer.show();
	}

	public static void main(String[] args) {
		for(int i=0; i<1; i++)
			test1() ;
	}

}
