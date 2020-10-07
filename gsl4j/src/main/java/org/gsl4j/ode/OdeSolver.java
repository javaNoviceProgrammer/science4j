package org.gsl4j.ode;

import org.gsl4j.diff.NumericalDiff;
import org.gsl4j.util.NativeLibraryLoader;

/**
 * This class describes functions for solving ordinary differential equation (ODE) initial value problems. The library provides a variety of low-level methods, such as Runge-Kutta and Bulirsch-Stoer routines, and higher-level components for adaptive step-size control. The components can be combined by the user to achieve the desired solution, with full access to any intermediate steps. A driver object can be used as a high level wrapper for easy use of low level functions.
 * <br>
 * These functions are declared in the header file {@code gsl_odeiv2.h}. This is a new interface in version 1.15 and uses the prefix {@code gsl_odeiv2} for all functions. It is recommended over the previous {@code gsl_odeiv} implementation defined in {@code gsl_odeiv.h}. The old interface has been retained under the original name for backwards compatibility.
 *
 * @author Meisam
 *
 */
public class OdeSolver {

	static {
		NativeLibraryLoader.loadLibraries();
		initIDs() ;
	}

	private static native void initIDs() ;

	DerivFunction func ; // f(x,y)
	DerivFunction df_dy  ; // df/dy
	DerivFunction df_dx ; // df/dx
	double x0 ;
	double y0 ;
	double absErr = 1e-6 ;
	double relErr = 1e-10 ;
	double minStepSize = 1e-6 ;
	double maxStepSize = 0.1 ; // not needed for adaptive algorithms
	int maxNumberOfSteps = 1000 ; // not needed for adaptive algorithms

	public OdeSolver(DerivFunction func, DerivFunction dfdx, DerivFunction dfdy, double x0, double y0) {
		this.func = func ;
		this.df_dx = dfdx ;
		this.df_dy = dfdy ;
		this.x0 = x0 ;
		this.y0 = y0 ;
	}

	public OdeSolver(DerivFunction func, double x0, double y0) {
		this.func = func ;
		this.df_dx = (x,y) -> NumericalDiff.central(t -> func.value(t, y), x, 1e-3) ;
		this.df_dy = (x,y) -> NumericalDiff.central(t -> func.value(x, t), y, 1e-3) ;
		this.x0 = x0 ;
		this.y0 = y0 ;
	}

	public void setAbsErr(double absErr) {
		this.absErr = absErr ;
	}

	public void setRelErr(double relErr) {
		this.relErr = relErr ;
	}

	public void setInitialCondition(double x0, double y0) {
		this.x0 = x0 ;
		this.y0 = y0 ;
	}

	public void setX0(double x0) {
		this.x0 = x0 ;
	}

	public void setY0(double y0) {
		this.y0 = y0 ;
	}

	public void setMinStepSize(double minStepSize) {
		this.minStepSize = minStepSize ;
	}

	/**
	 * Explicit embedded Runge-Kutta (2, 3) method.
	 * @param x
	 * @return
	 */
	public native double rk2(double x) ;

	/**
	 * Explicit embedded Runge-Kutta (2, 3) method.
	 * @param x
	 * @return
	 */
	public native double[] rk2(double[] x) ;

	/**
	 * Explicit 4th order (classical) Runge-Kutta. Error estimation is carried out by the step doubling method.
	 * @param x
	 * @return
	 */
	public native double rk4(double x) ;

	/**
	 * Explicit 4th order (classical) Runge-Kutta. Error estimation is carried out by the step doubling method.
	 * @param x
	 * @return
	 */
	public native double[] rk4(double[] x) ;

	/**
	 * Explicit embedded Runge-Kutta-Fehlberg (4, 5) method. This method is a good general-purpose integrator.
	 * @param x
	 * @return
	 */
	public native double rkf45(double x) ;

	/**
	 * Explicit embedded Runge-Kutta-Fehlberg (4, 5) method. This method is a good general-purpose integrator.
	 * @param x
	 * @return
	 */
	public native double[] rkf45(double[] x) ;

	/**
	 * Explicit embedded Runge-Kutta Cash-Karp (4, 5) method.
	 * @param x
	 * @return
	 */
	public native double rkck(double x) ;

	/**
	 * Explicit embedded Runge-Kutta Cash-Karp (4, 5) method.
	 * @param x
	 * @return
	 */
	public native double[] rkck(double[] x) ;

	/**
	 * Explicit embedded Runge-Kutta Prince-Dormand (8, 9) method.
	 * @param x
	 * @return
	 */
	public native double rk8pd(double x) ;

	/**
	 * Explicit embedded Runge-Kutta Prince-Dormand (8, 9) method.
	 * @param x
	 * @return
	 */
	public native double[] rk8pd(double[] x) ;

	/**
	 * Implicit Gaussian first order Runge-Kutta. Also known as implicit Euler or backward Euler method. Error estimation is carried out by the step doubling method. This algorithm requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public native double rk1imp(double x ) ;

	/**
	 * Implicit Gaussian first order Runge-Kutta. Also known as implicit Euler or backward Euler method. Error estimation is carried out by the step doubling method. This algorithm requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public native double[] rk1imp(double[] x ) ;

	/**
	 * Implicit Gaussian second order Runge-Kutta. Also known as implicit mid-point rule. Error estimation is carried out by the step doubling method. This stepper requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public native double rk2imp(double x) ;

	/**
	 * Implicit Gaussian second order Runge-Kutta. Also known as implicit mid-point rule. Error estimation is carried out by the step doubling method. This stepper requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public native double[] rk2imp(double[] x) ;

	/**
	 * Implicit Gaussian 4th order Runge-Kutta. Error estimation is carried out by the step doubling method. This algorithm requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public native double rk4imp(double x) ;

	/**
	 * Implicit Gaussian 4th order Runge-Kutta. Error estimation is carried out by the step doubling method. This algorithm requires the Jacobian and access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public native double[] rk4imp(double[] x) ;

	/**
	 * Implicit Bulirsch-Stoer method of Bader and Deuflhard. The method is generally suitable for stiff problems. This stepper requires the Jacobian.
	 * @param x
	 * @return
	 */
	public native double bsimp(double x) ;

	/**
	 * Implicit Bulirsch-Stoer method of Bader and Deuflhard. The method is generally suitable for stiff problems. This stepper requires the Jacobian.
	 * @param x
	 * @return
	 */
	public native double[] bsimp(double[] x) ;

	/**
	 * A variable-coefficient linear multistep Adams method in Nordsieck form. This stepper uses explicit Adams-Bashforth (predictor) and implicit Adams-Moulton (corrector) methods in P(EC)^m functional iteration mode. Method order varies dynamically between 1 and 12. This stepper requires the access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public native double msadams(double x) ;

	/**
	 * A variable-coefficient linear multistep Adams method in Nordsieck form. This stepper uses explicit Adams-Bashforth (predictor) and implicit Adams-Moulton (corrector) methods in P(EC)^m functional iteration mode. Method order varies dynamically between 1 and 12. This stepper requires the access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public native double[] msadams(double[] x) ;

	/**
	 * A variable-coefficient linear multistep backward differentiation formula (BDF) method in Nordsieck form. This stepper uses the explicit BDF formula as predictor and implicit BDF formula as corrector. A modified Newton iteration method is used to solve the system of non-linear equations. Method order varies dynamically between 1 and 5. The method is generally suitable for stiff problems. This stepper requires the Jacobian and the access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public native double msbdf(double x) ;

	/**
	 * A variable-coefficient linear multistep backward differentiation formula (BDF) method in Nordsieck form. This stepper uses the explicit BDF formula as predictor and implicit BDF formula as corrector. A modified Newton iteration method is used to solve the system of non-linear equations. Method order varies dynamically between 1 and 5. The method is generally suitable for stiff problems. This stepper requires the Jacobian and the access to the driver object via {@code gsl_odeiv2_step_set_driver()}.
	 * @param x
	 * @return
	 */
	public native double[] msbdf(double[] x) ;

}
