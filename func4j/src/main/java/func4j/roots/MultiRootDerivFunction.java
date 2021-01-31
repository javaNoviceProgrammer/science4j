package func4j.roots;


@FunctionalInterface
public interface MultiRootDerivFunction {

	/**
	 * This is the Jacobian: J_ij = df_i/dx_j
	 * @param x
	 * @return
	 */
	double[][] value(double... x) ;

}
