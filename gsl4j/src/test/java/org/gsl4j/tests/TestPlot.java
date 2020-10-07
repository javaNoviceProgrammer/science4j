package org.gsl4j.tests;

import java.util.Arrays;

import org.gsl4j.plot.Plot;
import org.gsl4j.plot.contour.MeshGrid;
import org.gsl4j.plot.style.ColorMap;
import org.gsl4j.plot.style.LegendLocation;
import org.gsl4j.plot.style.LineStyle;
import org.gsl4j.util.MathUtils;

public class TestPlot {

	public static void test1() {
		double[] x = MathUtils.linspace(-5.0, 5.0, 200) ;
		double[] y = Arrays.stream(x).map(Math::sin).toArray() ;
		double[] z = Arrays.stream(x).map(Math::cos).toArray() ;
		Plot plt = new Plot("A simple plot from java!!") ;
		// xy plots
		plt.plot(x, y).color("b").label("y=sin(x)") ;
		plt.plot(x, z).color("r").linestyle(LineStyle.dashed).label("y=cos(x)") ;
		// contour plot
		MeshGrid grid = (xi, yj) -> Math.sin(xi) ;
		plt.contour(x, y, grid).cmap(ColorMap.jet).filled(true).colorbar(true)
		   .clabel()
		   .fmt("%.2f").color("k").inline(true).inlineSpacing(0.0);


		plt.grid(true).legend(true, LegendLocation.upperRight)
		   .xlabel("x values").ylabel("y values") ;
		plt.tightLayout() ;
		plt.show();
	}

	public static void main(String[] args) {
		test1() ;
	}

}
