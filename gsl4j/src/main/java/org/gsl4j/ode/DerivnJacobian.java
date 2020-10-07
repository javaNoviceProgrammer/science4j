package org.gsl4j.ode;

/**
 * A functional interface representing the jacobian matrix of N real-valued differential equations.
 * <br>
 * J_ij = df_i/dy_j <br>
 * where the system of N differential equations is given by: <br>
 * dy1/dx = f1(x, y1, y2, ..., yN) <br>
 * dy2/dx = f2(x, y1, y2, ..., yN) <br>
 * ... <br>
 * dyN/dx = fN(x, y1, y2, ..., yN)
 *
 * @author Meisam
 *
 */
@FunctionalInterface
public interface DerivnJacobian {

	double[][] values(double x, double... y) ;

}
