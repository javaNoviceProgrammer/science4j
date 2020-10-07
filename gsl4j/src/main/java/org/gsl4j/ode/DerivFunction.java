package org.gsl4j.ode;


/**
 * A functional interface representing the value of a real-valued differential equation.
 * <br>
 * dy/dx = f(x, y) <br>
 * Intended to be used as a lambda expression for inline declaration.
 *
 * @author Meisam
 *
 */
@FunctionalInterface
public interface DerivFunction {
	double value(double x, double y) ; // y' = f(x,y)
}
