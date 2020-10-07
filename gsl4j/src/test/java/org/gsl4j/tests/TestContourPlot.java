package org.gsl4j.tests;

import org.gsl4j.plot.contour.ContourPlot;
import org.gsl4j.plot.contour.MeshGrid;
import org.gsl4j.plot.style.Color;
import org.gsl4j.plot.style.ColorMap;
import org.gsl4j.plot.style.LineStyle;
import org.gsl4j.util.MathUtils;

class TestContourPlot {

	public static void test1() {
		ContourPlot plt = new ContourPlot("contour plot from java!!!") ;
		double[] x = MathUtils.linspace(0.0, 5.0, 100) ;
		double[] y = MathUtils.linspace(-5.0, 0.0, 500) ;
		MeshGrid func = (xi, yj) -> Math.sin(xi)*Math.cos(yj) ;
		plt.contour(x, y, func) ;
		plt.clabel().color("r").levels(MathUtils.linspace(-1.0, 1.0, 20))
		   .fontsize(11).inlineSpacing(0.0).fmt("%.2f")
		   .manual(false).rightsideUp(true)
		   .useClabelText(false);
		plt.xlabel("X values") ;
		plt.ylabel("Y vales") ;
		plt.grid(true, "both", "both") ;
		plt.title("f(x) = sin(x)cos(y)") ;
		plt.tightLayout() ;
		plt.show();
	}

	public static void test2() {
		ContourPlot plt = new ContourPlot("contour plot from java!!!") ;
		double[] x = MathUtils.linspace(0.0, 2.0*Math.PI, 200) ;
		double[] y = MathUtils.linspace(-Math.PI, 0.0, 100) ;
		MeshGrid func = (xi, yj) -> Math.sin(xi)*Math.cos(yj) ;
		plt.contour(x, y, func).filled(true).alpha(1.0)
		   .linewidth(5.0).linestyle(LineStyle.solid)
		   .antialiased(true).cmap(ColorMap.jet) ;
		plt.clabel().color("k").levels(MathUtils.linspace(-1.0, 1.0, 50))
		   .fontsize(10.0).inlineSpacing(0.0).fmt("%.2f")
		   .manual(true).rightsideUp(true)
		   .useClabelText(false)
		   .inline(false) ;
		plt.xlabel("X values") ;
		plt.ylabel("Y vales") ;
		plt.grid(false, "both", "both") ;
		plt.title("f(x) = sin(x)cos(y)") ;
		plt.tightLayout() ;
		plt.show();
	}

	public static void test3() {
		ContourPlot plt = new ContourPlot("contour plot from java!!!") ;
		double[] x = MathUtils.linspace(0.0, 2.0*Math.PI, 200) ;
		double[] y = MathUtils.linspace(-Math.PI, 0.0, 100) ;
		MeshGrid func = (xi, yj) -> Math.sin(xi)*Math.cos(yj) ;
		plt.contour(x, y, func).filled(true).colorbar(true) ;
		plt.clabel().levels(MathUtils.linspace(-1.0, 1.0, 100)).fontsize(0.0).inline(false) ;
		MeshGrid func2 = (xi, yj) -> Math.cos(xi)*Math.sin(yj) ;
		plt.contour(x, y, func2).cmap(ColorMap.flag).linestyle(LineStyle.dashed)
		   .colorbar(false) ;
		plt.clabel().color(Color.white).levels(MathUtils.linspace(-2.0, 2.0, 20)).fontsize(12.0).inline(false) ;
		plt.show();

		test4() ;


	}

	public static void test4() {
		ContourPlot plt2 = new ContourPlot("contour plot from java!!!") ;
		double[] x2 = MathUtils.linspace(0.0, 2.0*Math.PI, 200) ;
		double[] y2 = MathUtils.linspace(-Math.PI, 0.0, 100) ;
		MeshGrid func3 = (xi, yj) -> xi*Math.cos(yj) ;
		plt2.contour(x2, y2, func3).filled(false).colorbar(false).cmap(ColorMap.jet) ;
		plt2.clabel().color("k").levels(MathUtils.linspace(-10.0, 10.0, 50)).fontsize(12.0).inline(true) ;
		plt2.grid(true) ;
		plt2.show();

	}

	public static void main(String[] args) {
//		test1() ;
		test2() ;
//		test3() ;
//		test4() ;
	}

}
