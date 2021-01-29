package func4j.roots;


@FunctionalInterface
public interface MultiRootDerivFunction {

	// this is the Jacobian: J_ij = df_i/dx_j
	double[][] value(double... x) ;

}
