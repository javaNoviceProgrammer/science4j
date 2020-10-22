package gsl4j;

import org.gsl4j.function.MathFunction;
import org.gsl4j.plot.style.Color;
import org.gsl4j.plot.xy.XYPlot;
import org.gsl4j.util.MathUtils;

public class TestFunction {

	public static void test1() {
		MathFunction f1 = x -> Math.sin(x) ;
		MathFunction f1Deriv = f1.deriv() ;
		MathFunction f1Deriv2 = f1.deriv2() ;

		// plotting
		XYPlot plt = new XYPlot("test1") ;
		double[] x = MathUtils.linspace(-3.0*Math.PI, 3.0*Math.PI, 1000) ;
		double[] y1 = f1.value(x) ;
		double[] y1deriv = f1Deriv.value(x) ;
		double[] y1deriv2 = f1Deriv2.value(x) ;

		plt.plot(x, y1).color(Color.blue).linewidth(2) ;
		plt.plot(x, y1deriv).color(Color.red).linewidth(2) ;
		plt.plot(x, y1deriv2).color(Color.green).linewidth(2) ;

		plt.grid(true) ;
		plt.show();
	}

	public static void main(String[] args) {
		test1() ;
	}

}
