package org.gsl4j.tests;

import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.util.Arrays;

import org.gsl4j.plot.polar.PolarPlot;
import org.gsl4j.plot.style.LegendLocation;
import org.gsl4j.util.MathUtils;

class TestPolarPlot {

	public static void test1() {
		double[] r = MathUtils.linspace(0.0, 10.0, 1000) ;
		double[] theta = Arrays.stream(r).map(Math::cos).toArray() ;
		double[] phi = Arrays.stream(r).map(Math::sin).toArray() ;
		PolarPlot plt = new PolarPlot("Polar plot from java!!!!") ;
		plt.plot(r, theta).color("b").linewidth(2).label("sin(r)");
		plt.plot(r, phi).color("r").linewidth(2).linestyle("--").label("cos(r)") ;
		plt.legend(true, LegendLocation.upperRight) ;
		plt.grid(true) ;
		plt.show();
//		plt.savefig(System.getProperty("user.home")+"/Desktop/fig2.pdf");

	}

	public static void test2() {
		// step 0
		double[] theta = MathUtils.linspace(-2.0*Math.PI, 2.0*Math.PI, 1000) ;
		double[] r1 = Arrays.stream(theta).map(t -> abs(sin(t))).toArray() ;
		double[] r2 = Arrays.stream(theta).map(t -> abs(cos(t))).toArray() ;
		// step 1
		PolarPlot plt = new PolarPlot("A polar plot from java!!!!") ;
		plt.plot(theta, r1).color("b").linestyle("-").linewidth(2.0).label("y=sin(x)") ;
		// step 2
		plt.plot(theta, r2).color("r").linestyle("--").linewidth(3.0).label("y=cos(x)") ;
		// step 3
		plt.tightLayout() ;
		// step 4
		plt.show();
	}

	public static void main(String[] args) {
//		test1() ;
		test2() ;
	}

}
