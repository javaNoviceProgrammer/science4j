package org.gsl4j.ode;

import org.gsl4j.complex.Complex;
import static org.gsl4j.complex.Complex.* ;

/**
 * This class describes functions for solving ordinary differential equation (ODE) initial value problems of complex functions. The library provides a variety of low-level methods, such as Runge-Kutta and Bulirsch-Stoer routines, and higher-level components for adaptive step-size control. The components can be combined by the user to achieve the desired solution, with full access to any intermediate steps. A driver object can be used as a high level wrapper for easy use of low level functions.
 * <br>
 * These functions are declared in the header file {@code gsl_odeiv2.h}. This is a new interface in version 1.15 and uses the prefix {@code gsl_odeiv2} for all functions. It is recommended over the previous {@code gsl_odeiv} implementation defined in {@code gsl_odeiv.h}. The old interface has been retained under the original name for backwards compatibility.
 *
 * @author Meisam
 *
 */
public class OdeSolverComplex {

	DerivnFunction funcSystem ;
	DerivnFunction df_dx ;
	DerivnJacobian df_dy ;
	OdeSystemSolver odeSystemSolver ;
	double x0 ;
	Complex y0 ;

	public OdeSolverComplex(DerivFunctionComplex func, DerivFunctionComplex dfdx, DerivFunctionComplex dfdy, double x0, Complex y0) {
		this.x0 = x0 ;
		this.y0 = y0 ;
		// z[0] = realpart, z[1] = imaginary part
		this.funcSystem = (x, z) -> {
			Complex val = func.value(x, z[0]+j*z[1]) ;
			return new double[]{val.re(), val.im()} ;
		} ;
		this.df_dx = (x, z) -> {
			Complex val = dfdx.value(x, z[0]+j*z[1]) ;
			return new double[]{val.re(), val.im()} ;
		} ;
		this.df_dy = (x, z) -> {
			Complex val = dfdy.value(x, z[0]+j*z[1]) ;
			return new double[][]{ {val.re(), val.im()}, {-val.im(), val.re()}} ;
		} ;
		this.odeSystemSolver = new OdeSystemSolver(2, funcSystem, df_dx, df_dy, x0, y0.re(), y0.im()) ;
	}

	public OdeSolverComplex(DerivFunctionComplex func, double x0, Complex y0) {
		this.x0 = x0 ;
		this.y0 = y0 ;
		// z[0] = realpart, z[1] = imaginary part
		this.funcSystem = (x, z) -> {
			Complex val = func.value(x, z[0]+j*z[1]) ;
			return new double[]{val.re(), val.im()} ;
		} ;
		this.odeSystemSolver = new OdeSystemSolver(2, funcSystem, x0, y0.re(), y0.im()) ;
	}

	public void setAbsErr(double absErr) {
		this.odeSystemSolver.setAbsErr(absErr);
	}

	public void setRelErr(double relErr) {
		this.odeSystemSolver.setRelErr(relErr);
	}

	public void setInitialCondition(double x0, Complex y0) {
		this.x0 = x0 ;
		this.y0 = y0 ;
	}

	public void setInitialCondition(double x0, double y0Real, double y0Imag) {
		this.x0 = x0 ;
		this.y0 = Complex.ofRect(y0Real, y0Imag) ;
	}

	public void setX0(double x0) {
		this.x0 = x0 ;
	}

	public void setY0(Complex y0) {
		this.y0 = y0 ;
	}

	public void setY0(double y0Real, double y0Imag) {
		this.y0 = Complex.ofRect(y0Real, y0Imag) ;
	}

	public void setMinStepSize(double minStepSize) {
		this.odeSystemSolver.setMinStepSize(minStepSize);
	}

	/*------------- rk2 -------------*/

	/**
	 * Explicit embedded Runge-Kutta (2, 3) method.
	 * @param x
	 * @return
	 */
	public double[] rk2(double x) {
		return odeSystemSolver.rk2(x) ;
	}

	/**
	 * Explicit embedded Runge-Kutta (2, 3) method.
	 * @param x
	 * @return
	 */
	public double[][] rk2(double[] x) {
		return odeSystemSolver.rk2(x) ;
	}

	/**
	 * Explicit embedded Runge-Kutta (2, 3) method.
	 * @param x
	 * @return
	 */
	public Complex rk2Complex(double x) {
		double[] z = odeSystemSolver.rk2(x) ;
		return ofRect(z[0], z[1]) ;
	}

	/**
	 * Explicit embedded Runge-Kutta (2, 3) method.
	 * @param x
	 * @return
	 */
	public Complex[] rk2Complex(double[] x) {
		double[][] z = odeSystemSolver.rk2(x) ;
		Complex[] result = new Complex[x.length] ;
		for(int i=0; i<result.length; i++)
			result[i] = ofRect(z[0][i], z[1][i]) ;
		return result ;
	}

	/*------------- rk4 -------------*/

	/**
	 * Explicit 4th order (classical) Runge-Kutta. Error estimation is carried out by the step doubling method.
	 * @param x
	 * @return
	 */
	public double[] rk4(double x) {
		return odeSystemSolver.rk4(x) ;
	}

	/**
	 * Explicit 4th order (classical) Runge-Kutta. Error estimation is carried out by the step doubling method.
	 * @param x
	 * @return
	 */
	public double[][] rk4(double[] x) {
		return odeSystemSolver.rk4(x) ;
	}

	/**
	 * Explicit 4th order (classical) Runge-Kutta. Error estimation is carried out by the step doubling method.
	 * @param x
	 * @return
	 */
	public Complex rk4Complex(double x) {
		double[] z = odeSystemSolver.rk4(x) ;
		return ofRect(z[0], z[1]) ;
	}

	/**
	 * Explicit 4th order (classical) Runge-Kutta. Error estimation is carried out by the step doubling method.
	 * @param x
	 * @return
	 */
	public Complex[] rk4Complex(double[] x) {
		double[][] z = odeSystemSolver.rk4(x) ;
		Complex[] result = new Complex[x.length] ;
		for(int i=0; i<result.length; i++)
			result[i] = ofRect(z[0][i], z[1][i]) ;
		return result ;
	}

	/*------------- rkf45 -------------*/

	/**
	 * Explicit embedded Runge-Kutta-Fehlberg (4, 5) method. This method is a good general-purpose integrator.
	 * @param x
	 * @return
	 */
	public double[] rkf45(double x) {
		return odeSystemSolver.rkf45(x) ;
	}

	/**
	 * Explicit embedded Runge-Kutta-Fehlberg (4, 5) method. This method is a good general-purpose integrator.
	 * @param x
	 * @return
	 */
	public double[][] rkf45(double[] x) {
		return odeSystemSolver.rk4(x) ;
	}

	/**
	 * Explicit embedded Runge-Kutta-Fehlberg (4, 5) method. This method is a good general-purpose integrator.
	 * @param x
	 * @return
	 */
	public Complex rkf45Complex(double x) {
		double[] z = odeSystemSolver.rkf45(x) ;
		return ofRect(z[0], z[1]) ;
	}

	/**
	 * Explicit embedded Runge-Kutta-Fehlberg (4, 5) method. This method is a good general-purpose integrator.
	 * @param x
	 * @return
	 */
	public Complex[] rkf45Complex(double[] x) {
		double[][] z = odeSystemSolver.rkf45(x) ;
		Complex[] result = new Complex[x.length] ;
		for(int i=0; i<result.length; i++)
			result[i] = ofRect(z[0][i], z[1][i]) ;
		return result ;
	}

	/*------------- rkck -------------*/

	/**
	 * Explicit embedded Runge-Kutta Cash-Karp (4, 5) method.
	 * @param x
	 * @return
	 */
	public double[] rkck(double x) {
		return odeSystemSolver.rkck(x) ;
	}

	/**
	 * Explicit embedded Runge-Kutta Cash-Karp (4, 5) method.
	 * @param x
	 * @return
	 */
	public double[][] rkck(double[] x) {
		return odeSystemSolver.rkck(x) ;
	}

	/**
	 * Explicit embedded Runge-Kutta Cash-Karp (4, 5) method.
	 * @param x
	 * @return
	 */
	public Complex rkckComplex(double x) {
		double[] z = odeSystemSolver.rkck(x) ;
		return ofRect(z[0], z[1]) ;
	}

	/**
	 * Explicit embedded Runge-Kutta Cash-Karp (4, 5) method.
	 * @param x
	 * @return
	 */
	public Complex[] rkckComplex(double[] x) {
		double[][] z = odeSystemSolver.rkck(x) ;
		Complex[] result = new Complex[x.length] ;
		for(int i=0; i<result.length; i++)
			result[i] = ofRect(z[0][i], z[1][i]) ;
		return result ;
	}

	/*------------- rk8pd -------------*/

	/**
	 * Explicit embedded Runge-Kutta Prince-Dormand (8, 9) method.
	 * @param x
	 * @return
	 */
	public double[] rk8pd(double x) {
		return odeSystemSolver.rk8pd(x) ;
	}

	/**
	 * Explicit embedded Runge-Kutta Prince-Dormand (8, 9) method.
	 * @param x
	 * @return
	 */
	public double[][] rk8pd(double[] x) {
		return odeSystemSolver.rk8pd(x) ;
	}

	/**
	 * Explicit embedded Runge-Kutta Prince-Dormand (8, 9) method.
	 * @param x
	 * @return
	 */
	public Complex rk8pdComplex(double x) {
		double[] z = odeSystemSolver.rk8pd(x) ;
		return ofRect(z[0], z[1]) ;
	}

	/**
	 * Explicit embedded Runge-Kutta Prince-Dormand (8, 9) method.
	 * @param x
	 * @return
	 */
	public Complex[] rk8pdComplex(double[] x) {
		double[][] z = odeSystemSolver.rk8pd(x) ;
		Complex[] result = new Complex[x.length] ;
		for(int i=0; i<result.length; i++)
			result[i] = ofRect(z[0][i], z[1][i]) ;
		return result ;
	}

	/*------------- rk1imp -------------*/

	/**
	 * Implicit Gaussian first order Runge-Kutta. Also known as implicit Euler or backward Euler method. Error estimation is carried out by the step doubling method. This algorithm requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public double[] rk1imp(double x ) {
		return odeSystemSolver.rk1imp(x) ;
	}

	/**
	 * Implicit Gaussian first order Runge-Kutta. Also known as implicit Euler or backward Euler method. Error estimation is carried out by the step doubling method. This algorithm requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public double[][] rk1imp(double[] x ) {
		return odeSystemSolver.rk1imp(x) ;
	}

	/**
	 * Implicit Gaussian first order Runge-Kutta. Also known as implicit Euler or backward Euler method. Error estimation is carried out by the step doubling method. This algorithm requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public Complex rk1impComplex(double x) {
		double[] z = odeSystemSolver.rk1imp(x) ;
		return ofRect(z[0], z[1]) ;
	}

	/**
	 * Implicit Gaussian first order Runge-Kutta. Also known as implicit Euler or backward Euler method. Error estimation is carried out by the step doubling method. This algorithm requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public Complex[] rk1impComplex(double[] x) {
		double[][] z = odeSystemSolver.rk1imp(x) ;
		Complex[] result = new Complex[x.length] ;
		for(int i=0; i<result.length; i++)
			result[i] = ofRect(z[0][i], z[1][i]) ;
		return result ;
	}

	/*------------- rk2imp -------------*/

	/**
	 * Implicit Gaussian second order Runge-Kutta. Also known as implicit mid-point rule. Error estimation is carried out by the step doubling method. This stepper requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public double[] rk2imp(double x) {
		return odeSystemSolver.rk2imp(x) ;
	}

	/**
	 * Implicit Gaussian second order Runge-Kutta. Also known as implicit mid-point rule. Error estimation is carried out by the step doubling method. This stepper requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public double[][] rk2imp(double[] x) {
		return odeSystemSolver.rk2imp(x) ;
	}

	/**
	 * Implicit Gaussian second order Runge-Kutta. Also known as implicit mid-point rule. Error estimation is carried out by the step doubling method. This stepper requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public Complex rk2impComplex(double x) {
		double[] z = odeSystemSolver.rk2imp(x) ;
		return ofRect(z[0], z[1]) ;
	}

	/**
	 * Implicit Gaussian second order Runge-Kutta. Also known as implicit mid-point rule. Error estimation is carried out by the step doubling method. This stepper requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public Complex[] rk2impComplex(double[] x) {
		double[][] z = odeSystemSolver.rk2imp(x) ;
		Complex[] result = new Complex[x.length] ;
		for(int i=0; i<result.length; i++)
			result[i] = ofRect(z[0][i], z[1][i]) ;
		return result ;
	}

	/*------------- rk4imp -------------*/

	/**
	 * Implicit Gaussian 4th order Runge-Kutta. Error estimation is carried out by the step doubling method. This algorithm requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public double[] rk4imp(double x) {
		return odeSystemSolver.rk4imp(x) ;
	}

	/**
	 * Implicit Gaussian 4th order Runge-Kutta. Error estimation is carried out by the step doubling method. This algorithm requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public double[][] rk4imp(double[] x) {
		return odeSystemSolver.rk4imp(x) ;
	}

	/**
	 * Implicit Gaussian 4th order Runge-Kutta. Error estimation is carried out by the step doubling method. This algorithm requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public Complex rk4impComplex(double x) {
		double[] z = odeSystemSolver.rk4imp(x) ;
		return ofRect(z[0], z[1]) ;
	}

	/**
	 * Implicit Gaussian 4th order Runge-Kutta. Error estimation is carried out by the step doubling method. This algorithm requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public Complex[] rk4impComplex(double[] x) {
		double[][] z = odeSystemSolver.rk4imp(x) ;
		Complex[] result = new Complex[x.length] ;
		for(int i=0; i<result.length; i++)
			result[i] = ofRect(z[0][i], z[1][i]) ;
		return result ;
	}

	/*------------- bsimp -------------*/

	/**
	 * Implicit Bulirsch-Stoer method of Bader and Deuflhard. The method is generally suitable for stiff problems. This stepper requires the Jacobian.
	 * @param x
	 * @return
	 */
	public double[] bsimp(double x) {
		return odeSystemSolver.bsimp(x) ;
	}

	/**
	 * Implicit Bulirsch-Stoer method of Bader and Deuflhard. The method is generally suitable for stiff problems. This stepper requires the Jacobian.
	 * @param x
	 * @return
	 */
	public double[][] bsimp(double[] x) {
		return odeSystemSolver.bsimp(x) ;
	}

	/**
	 * Implicit Bulirsch-Stoer method of Bader and Deuflhard. The method is generally suitable for stiff problems. This stepper requires the Jacobian.
	 * @param x
	 * @return
	 */
	public Complex bsimpComplex(double x) {
		double[] z = odeSystemSolver.bsimp(x) ;
		return ofRect(z[0], z[1]) ;
	}

	/**
	 * Implicit Bulirsch-Stoer method of Bader and Deuflhard. The method is generally suitable for stiff problems. This stepper requires the Jacobian.
	 * @param x
	 * @return
	 */
	public Complex[] bsimpComplex(double[] x) {
		double[][] z = odeSystemSolver.bsimp(x) ;
		Complex[] result = new Complex[x.length] ;
		for(int i=0; i<result.length; i++)
			result[i] = ofRect(z[0][i], z[1][i]) ;
		return result ;
	}

	/*------------- msadams -------------*/

	/**
	 * A variable-coefficient linear multistep Adams method in Nordsieck form. This stepper uses explicit Adams-Bashforth (predictor) and implicit Adams-Moulton (corrector) methods in P(EC)^m functional iteration mode. Method order varies dynamically between 1 and 12. This stepper requires the access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public double[] msadams(double x) {
		return odeSystemSolver.msadams(x) ;
	}

	/**
	 * A variable-coefficient linear multistep Adams method in Nordsieck form. This stepper uses explicit Adams-Bashforth (predictor) and implicit Adams-Moulton (corrector) methods in P(EC)^m functional iteration mode. Method order varies dynamically between 1 and 12. This stepper requires the access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public double[][] msadams(double[] x) {
		return odeSystemSolver.msadams(x) ;
	}

	/**
	 * A variable-coefficient linear multistep Adams method in Nordsieck form. This stepper uses explicit Adams-Bashforth (predictor) and implicit Adams-Moulton (corrector) methods in P(EC)^m functional iteration mode. Method order varies dynamically between 1 and 12. This stepper requires the access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public Complex msadamsComplex(double x) {
		double[] z = odeSystemSolver.msadams(x) ;
		return ofRect(z[0], z[1]) ;
	}

	/**
	 * A variable-coefficient linear multistep Adams method in Nordsieck form. This stepper uses explicit Adams-Bashforth (predictor) and implicit Adams-Moulton (corrector) methods in P(EC)^m functional iteration mode. Method order varies dynamically between 1 and 12. This stepper requires the access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public Complex[] msadamsComplex(double[] x) {
		double[][] z = odeSystemSolver.msadams(x) ;
		Complex[] result = new Complex[x.length] ;
		for(int i=0; i<result.length; i++)
			result[i] = ofRect(z[0][i], z[1][i]) ;
		return result ;
	}

	/*------------- msbdf -------------*/

	/**
	 * A variable-coefficient linear multistep backward differentiation formula (BDF) method in Nordsieck form. This stepper uses the explicit BDF formula as predictor and implicit BDF formula as corrector. A modified Newton iteration method is used to solve the system of non-linear equations. Method order varies dynamically between 1 and 5. The method is generally suitable for stiff problems. This stepper requires the Jacobian and the access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public double[] msbdf(double x) {
		return odeSystemSolver.msbdf(x) ;
	}

	/**
	 * A variable-coefficient linear multistep backward differentiation formula (BDF) method in Nordsieck form. This stepper uses the explicit BDF formula as predictor and implicit BDF formula as corrector. A modified Newton iteration method is used to solve the system of non-linear equations. Method order varies dynamically between 1 and 5. The method is generally suitable for stiff problems. This stepper requires the Jacobian and the access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public double[][] msbdf(double[] x) {
		return odeSystemSolver.msbdf(x) ;
	}

	/**
	 * A variable-coefficient linear multistep backward differentiation formula (BDF) method in Nordsieck form. This stepper uses the explicit BDF formula as predictor and implicit BDF formula as corrector. A modified Newton iteration method is used to solve the system of non-linear equations. Method order varies dynamically between 1 and 5. The method is generally suitable for stiff problems. This stepper requires the Jacobian and the access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public Complex msbdfComplex(double x) {
		double[] z = odeSystemSolver.msbdf(x) ;
		return ofRect(z[0], z[1]) ;
	}

	/**
	 * A variable-coefficient linear multistep backward differentiation formula (BDF) method in Nordsieck form. This stepper uses the explicit BDF formula as predictor and implicit BDF formula as corrector. A modified Newton iteration method is used to solve the system of non-linear equations. Method order varies dynamically between 1 and 5. The method is generally suitable for stiff problems. This stepper requires the Jacobian and the access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public Complex[] msbdfComplex(double[] x) {
		double[][] z = odeSystemSolver.msbdf(x) ;
		Complex[] result = new Complex[x.length] ;
		for(int i=0; i<result.length; i++)
			result[i] = ofRect(z[0][i], z[1][i]) ;
		return result ;
	}

}
