package org.gsl4j.ode;

import org.gsl4j.complex.Complex;

/**
 * A functional interface representing the value of a complex-valued differential equation.
 * <br>
 * dy/dx = f(x, y) <br>
 * Intended to be used as a lambda expression for inline declaration.
 *
 * @author Meisam
 *
 */
@FunctionalInterface
public interface DerivFunctionComplex {
	Complex value(double x, Complex y) ; // y' = f(x,y) --> use as lambda expression
}
