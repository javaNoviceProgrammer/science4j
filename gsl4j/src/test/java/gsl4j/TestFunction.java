package gsl4j;

import org.gsl4j.function.MathFunction;
import org.gsl4j.function.MultiVariateMathFunction;
import org.gsl4j.function.VectorMathFunction;
import org.gsl4j.plot.style.Color;
import org.gsl4j.plot.xy.XYPlot;
import org.gsl4j.util.MathUtils;

public class TestFunction {

	public static void test1() {
		MathFunction f1 = x -> Math.sin(x*x) ;
		MathFunction f1Deriv = f1.deriv() ;
		MathFunction f1Deriv2 = f1.deriv2() ;

		// plotting
		XYPlot plt = new XYPlot("test1") ;
		double[] x = MathUtils.linspace(-3.0*Math.PI, 3.0*Math.PI, 1000) ;
		double[] y1 = f1.value(x) ;
		double[] y1deriv = f1Deriv.value(x) ;
		double[] y1deriv2 = f1Deriv2.value(x) ;

		plt.plot(x, y1).color(Color.blue).linewidth(2).label("f1(x)") ;
		plt.plot(x, y1deriv).color(Color.red).linewidth(2).label("f1deriv(x)") ;
		plt.plot(x, y1deriv2).color(Color.green).linewidth(2).label("f1deriv2(x)") ;

		plt.grid(true) ;
		plt.legend(true) ;
		plt.show();
	}

	public static void test2() {
		MultiVariateMathFunction func = xy -> {
			double x = xy[0], y = xy[1] ;
			return x*y*y ;
		}; // x*y^2

		MultiVariateMathFunction dfunc = func.deriv(0).deriv(1) ;
		System.out.println(dfunc.value(1.0, -2.0));

		MultiVariateMathFunction dfunc2 = func.deriv(1).deriv(0) ;
		System.out.println(dfunc2.value(1.0, -2.0));

	}

	public static void test3() {
		VectorMathFunction func = xyz -> {
			double x = xyz[0], y = xyz[1], z = xyz[2] ;
			return new double[] {x*y, z-x, y+z} ;
		} ;

		MultiVariateMathFunction elem = func[0]  ;
		System.out.println(elem.value(3.0, 2.0, 3.0)) ;
	}

	public static void test4() {

	}

	public static void main(String[] args) {
//		test1() ;
//		test2() ;
//		test3() ;
		test4() ;
	}

}
