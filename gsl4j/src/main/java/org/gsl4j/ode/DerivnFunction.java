package org.gsl4j.ode;

/**
 * A functional interface representing the values of N real-valued differential equations.
 * <br>
 * dy1/dx = f1(x, y1, y2, ..., yN) <br>
 * dy2/dx = f2(x, y1, y2, ..., yN) <br>
 * ... <br>
 * dyN/dx = fN(x, y1, y2, ..., yN)
 *
 * @author Meisam
 *
 */
@FunctionalInterface
public interface DerivnFunction {
	double[] values(double x, double... y) ; // represents n-dimensional array f_1, f_2, ..., f_n
}
