package pyplot4j.tests;

import java.util.Arrays;

import pyplot4j.style.Color;
import pyplot4j.util.MathUtils;
import pyplot4j.xy.XYPlot;

public class Test1 {
	
	public static void test1() {
		double[] x = MathUtils.linspace(-10.0, 10.0, 1000) ;
		double[] y = Arrays.stream(x).map(t -> Math.sin(t)).toArray() ;
		XYPlot plt = new XYPlot("Just a simple java plot!!!") ;
		plt.plot(x, y).color(Color.black).linewidth(3) ;
		plt.grid(true) ;
		plt.xlabel("X data").ylabel("Y data") ;
		plt.show();
		plt.savefig("test1.pdf");
	}

	public static void main(String[] args) {
		test1() ;
	}

}
