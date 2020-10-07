package org.gsl4j.tests;

import java.util.Arrays;

import org.gsl4j.plot.xy.XYPlot;
import org.gsl4j.plot.xy.XYSubPlot;
import org.gsl4j.util.MathUtils;

public class TestSubplot {

	public static void test1() {
		double[] x = MathUtils.linspace(-5.0, 5.0, 1000) ;
		double[] y = Arrays.stream(x).map(Math::sin).toArray() ;
		double[] z = Arrays.stream(x).map(Math::cos).toArray() ;
		double[] w = Arrays.stream(x).map(t -> t*t).toArray() ;
		XYSubPlot plt = new XYSubPlot() ;
		plt.subplot(2, 2, 1)
		   .title("first plot").grid(true).xlabel("freq (GHz)").ylabel("EO |S21|")
		   .plot(x, y).color("b") ;
		plt.subplot(2, 2, 1)
		   .plot(x, z).color("k").linestyle("--") ;
		plt.subplot(2, 2, 3)
		   .title("second plot")
		   .plot(x, z).color("r") ;
		plt.subplot(1, 2, 2)
		   .title("third plot (1,2,2)").grid(true, "both", "both").xlabel("Time (psec)").ylabel("Intensity")
		   .plot(x, w).color("g") ;

		plt.tightLayout() ;
		plt.show();
	}

	public static void test2() {
		// generate some data
		double[] x = MathUtils.linspace(-5.0, 5.0, 1000) ;
		double[] y = Arrays.stream(x).map(Math::sin).toArray() ;
		double[] z = Arrays.stream(x).map(Math::cos).toArray() ;
		double[] w = Arrays.stream(x).map(t -> t*t).toArray() ;
		// create plot
		XYSubPlot plt = new XYSubPlot() ;
		// create subplot
		XYPlot sub221 = plt.subplot(2, 2, 1) ;
		sub221.title("first plot").grid(true).xlabel("freq (GHz)").ylabel("EO |S21|") ;
		sub221.plot(x, y).color("b") ;
		sub221.plot(x, z).color("k").linestyle("--") ;
		// create subplot
		plt.subplot(2, 2, 3)
		   .title("second plot").grid(true)
		   .plot(x, z).color("r") ;
		// create subplot
		plt.subplot(1, 2, 2)
		   .title("third plot (1,2,2)").grid(true, "both", "both").xlabel("Time (psec)").ylabel("Intensity")
		   .plot(x, w).color("g") ;

		plt.tightLayout() ;
		plt.show();
	}

	public static void test3() {
		// generate some data
		double[] x = MathUtils.linspace(-5.0, 5.0, 1000) ;
		double[] y = Arrays.stream(x).map(Math::sin).toArray() ;
		double[] z = Arrays.stream(x).map(Math::cos).toArray() ;
		double[] w = Arrays.stream(x).map(t -> t*t).toArray() ;
		// create plot
		XYSubPlot plt = new XYSubPlot() ;
		// create subplot
		XYPlot sub221 = plt.subplot(2, 2, 1) ;
		sub221.title("first plot").grid(true).xlabel("freq (GHz)").ylabel("EO |S21|") ;
		sub221.plot(x, y).color("b") ;
		sub221.plot(x, z).color("k").linestyle("--") ;
		// create subplot
		plt.subplot(2, 2, 2)
		   .title("second plot").grid(true)
		   .plot(x, z).color("r") ;
		// create subplot
		plt.subplot(2, 1, 2)
		   .title("third plot (2,1,2)").grid(true, "both", "both").xlabel("Time (psec)").ylabel("Intensity")
		   .plot(x, w).color("g") ;

		plt.tightLayout() ;
		plt.show();

	}

	public static void main(String[] args) {
//		test1() ;
//		test2() ;
		test3() ;
	}

}
