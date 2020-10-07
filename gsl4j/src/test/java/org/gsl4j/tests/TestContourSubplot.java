package org.gsl4j.tests;

import org.gsl4j.plot.contour.ContourSubPlot;
import org.gsl4j.plot.contour.MeshGrid;
import org.gsl4j.plot.style.ColorMap;
import org.gsl4j.util.MathUtils;
import static java.lang.Math.* ;

class TestContourSubplot {

	public static void test1() {
		double[] x = MathUtils.linspace(-5.0, 5.0, 500) ;
		double[] y = MathUtils.linspace(2.0, 7.0, 200) ;
		MeshGrid z1 = (xi, yj) -> sin(xi)*cos(yj) ;
		MeshGrid z2 = (xi, yj) -> cos(xi)*sin(yj) ;
		MeshGrid z3 = (xi, yj) -> sin(xi)*sin(yj) ;
		ContourSubPlot plt = new ContourSubPlot() ;
		plt.subplot(2, 2, 1).grid(true).xlabel("x").ylabel("y")
		   .contour(x, y, z1).filled(true)
		   .clabel().levels(MathUtils.linspace(-1.0, 1.0, 51)).fontsize(0.0)
		   .inline(false).inlineSpacing(0.0);
		plt.subplot(2, 2, 3).grid(true)
		   .contour(x, y, z2).color("k") ;
		plt.subplot(1, 2, 2)
		   .contour(x, y, z3).cmap(ColorMap.jet)
		   .clabel().levels(MathUtils.linspace(-1.0, 1.0, 51))
		   .color("k").manual(true).fmt("%.2f") ;
		plt.tightLayout() ;
		plt.show();
	}

	public static void main(String[] args) {
		test1() ;
	}

}
