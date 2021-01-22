package test_ode4j;

import java.util.concurrent.TimeUnit;

import ode4j.DerivFunction;
import ode4j.OdeSolver;
import pyplot4j.style.Color;
import pyplot4j.xy.XYPlot;
import util4j.math.MathUtils;
import util4j.time.Timer;

public class Test_Ode {
	
	public static void test1() {
		Timer timer = new Timer() ;
		timer.start();
		DerivFunction func = (x,y) -> -y*x ; // y' = -y
		double x0 = 0.0, y0 = 1.0 ;
		OdeSolver ode = new OdeSolver(func, x0, y0) ;
		double[] x = MathUtils.linspace(0.0, 10.0, 1000) ;
		double[] z = ode.rkf45(x) ;
		timer.stop();
		timer.show(TimeUnit.MILLISECONDS);
		
		XYPlot plt = new XYPlot("ODE solution") ;
		plt.plot(x, z).color(Color.blue).linewidth(2.0) ;
		plt.grid(true).xlabel("x values").ylabel("y(x) solution") ;
		plt.fontSize(15) ;
		plt.show();
	}

	public static void main(String[] args) {
		test1() ;
	}

}
