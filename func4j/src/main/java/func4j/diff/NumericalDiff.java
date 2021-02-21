package func4j.diff;

import java.awt.Desktop;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import func4j.function.MathFunction;
//import func4j.natives.NativeLibraryLoader;
import func4j.special.SpecialFuncs;
import util4j.natives.NativeLibraryLoader;

/**
 * The functions described in this chapter compute numerical derivatives by finite
 * differencing. An adaptive algorithm is used to find the best choice of finite
 * difference and to estimate the error in the derivative. These functions are
 * declared in the header file {@code gsl_deriv.h}.
 *
 * @author Meisam
 * @since 1.0
 *
 */
public class NumericalDiff {

	static {
		NativeLibraryLoader.loadLibraries("/libfunc4j_c");
		initIDs() ;
	}

	private NumericalDiff() {

	}

	private static native void initIDs() ;
	
	/**
	 * This function computes the numerical derivative of the function f at the point x using an adaptive central difference algorithm with a step-size of h. The derivative is returned in result and an estimate of its absolute error is returned in abserr.
	 * <br>The initial value of h is used to estimate an optimal step-size, based on the scaling of the truncation error and round-off error in the derivative calculation. The derivative is computed using a 5-point rule for equally spaced abscissae at x - h, x - h/2, x, x + h/2, x+h, with an error estimate taken from the difference between the 5-point rule and the corresponding 3-point rule x-h, x, x+h. Note that the value of the function at x does not contribute to the derivative calculation, so only 4-points are actually used.
	 * @param func : {@link MathFunction} representing a real-valued function f(x)
	 * @param x : point at which the derivative is calculated
	 * @param h : initial step size for discretization
	 * @return double : derivative at point x
	 */
	public static native double central(MathFunction func, double x, double h) ;

	/**
	 * Same as {@link #central(MathFunction, double, double)} but acts on an array of double values.
	 *
	 * @param func : {@link MathFunction} representing a real-valued function f(x)
	 * @param x : array of points at which the derivative is calculated
	 * @param h : initial step size for discretization
	 * @return double[] : array of derivatives at points x[]
	 */
	public static native double[] central(MathFunction func, double[] x, double h) ;

	/**
	 * This function computes the numerical derivative of the function f at the point x using an adaptive forward difference algorithm with a step-size of h. The function is evaluated only at points greater than x, and never at x itself. The derivative is returned in result and an estimate of its absolute error is returned in abserr. This function should be used if f(x) has a discontinuity at x, or is undefined for values less than x.
	 * The initial value of h is used to estimate an optimal step-size, based on the scaling of the truncation error and round-off error in the derivative calculation. The derivative at x is computed using an “open” 4-point rule for equally spaced abscissae at x+h/4, x + h/2, x + 3h/4, x+h, with an error estimate taken from the difference between the 4-point rule and the corresponding 2-point rule x+h/2, x+h.
	 * @param func : {@link MathFunction} representing a real-valued function f(x)
	 * @param x : point at which the derivative is calculated
	 * @param h : initial step size for discretization
	 * @return {@code double} : derivative at point x
	 */
	public static native double forward(MathFunction func, double x, double h) ;

	/**
	 * Same as {@link #forward(MathFunction, double, double)} but acts on an array of double values.
	 *
	 * @param func : {@link MathFunction} representing a real-valued function f(x)
	 * @param x : array of points at which the derivative is calculated
	 * @param h : initial step size for discretization
	 * @return {@code double[]} : derivative at points x[]
	 */
	public static native double[] forward(MathFunction func, double[] x, double h) ;

	/**
	 * This function computes the numerical derivative of the function f at the point x using an adaptive backward difference algorithm with a step-size of h. The function is evaluated only at points less than x, and never at x itself. The derivative is returned in result and an estimate of its absolute error is returned in abserr. This function should be used if f(x) has a discontinuity at x, or is undefined for values greater than x.
	 * This function is equivalent to calling gsl_deriv_forward() with a negative step-size.
	 * @param func : {@link MathFunction} representing a real-valued function f(x)
	 * @param x : point at which the derivative is calculated
	 * @param h : initial step size for discretization
	 * @return {@code double} : derivative at point x
	 */
	public static native double backward(MathFunction func, double x, double h) ;

	/**
	 * Same as {@link #backward(MathFunction, double, double)} but acts on an array of double values.
	 *
	 * @param func : {@link MathFunction} representing a real-valued function f(x)
	 * @param x : array of points at which the derivative is calculated
	 * @param h : initial step size for discretization
	 * @return {@code double[]} : derivative at points x[]
	 */
	public static native double[] backward(MathFunction func, double[] x, double h) ;

	/**
	 * This function computes the numerical derivative of the function f at the point x using an adaptive central difference algorithm with a step-size of h. The derivative is returned in result and an estimate of its absolute error is returned in abserr.
	 * The initial value of h is used to estimate an optimal step-size, based on the scaling of the truncation error and round-off error in the derivative calculation. The derivative is computed using a 5-point rule for equally spaced abscissae at x - h, x - h/2, x, x + h/2, x+h, with an error estimate taken from the difference between the 5-point rule and the corresponding 3-point rule x-h, x, x+h. Note that the value of the function at x does not contribute to the derivative calculation, so only 4-points are actually used.
	 * @param func : {@link MathFunction} representing a real-valued function f(x)
	 * @param x : point at which the derivative is calculated
	 * @param h : initial step size for discretization
	 * @return {@code double[]} : derivative and its error at point x
	 */
	public static native double[] centralWithError(MathFunction func, double x, double h) ;

	/**
	 * This function computes the numerical derivative of the function f at the point x using an adaptive forward difference algorithm with a step-size of h. The function is evaluated only at points greater than x, and never at x itself. The derivative is returned in result and an estimate of its absolute error is returned in abserr. This function should be used if f(x) has a discontinuity at x, or is undefined for values less than x.
	 * The initial value of h is used to estimate an optimal step-size, based on the scaling of the truncation error and round-off error in the derivative calculation. The derivative at x is computed using an “open” 4-point rule for equally spaced abscissae at x+h/4, x + h/2, x + 3h/4, x+h, with an error estimate taken from the difference between the 4-point rule and the corresponding 2-point rule x+h/2, x+h.
	 * @param func : {@link MathFunction} representing a real-valued function f(x)
	 * @param x : point at which the derivative is calculated
	 * @param h : initial step size for discretization
	 * @return {@code double[]} : derivative and its error at point x
	 */
	public static native double[] forwardWithError(MathFunction func, double x, double h) ;

	/**
	 * This function computes the numerical derivative of the function f at the point x using an adaptive backward difference algorithm with a step-size of h. The function is evaluated only at points less than x, and never at x itself. The derivative is returned in result and an estimate of its absolute error is returned in abserr. This function should be used if f(x) has a discontinuity at x, or is undefined for values greater than x.
	 * This function is equivalent to calling {@code gsl_deriv_forward()} with a negative step-size.
	 * @param func : {@link MathFunction} representing a real-valued function f(x)
	 * @param x : point at which the derivative is calculated
	 * @param h : initial step size for discretization
	 * @return {@code double[]} : derivative and its error at point x
	 */
	public static native double[] backwardWithError(MathFunction func, double x, double h) ;
	
	
	
	// open PDF documentation
	public static void help() {
		try {
	        String inputPdf = "doc/gsl_numerical_differentiation.pdf";
	        Path tempOutput = Files.createTempFile("gsl_numerical_differentiation", ".pdf");
	        tempOutput.toFile().deleteOnExit();
	        try (InputStream is = SpecialFuncs.class.getClassLoader().getResourceAsStream(inputPdf)) {
	            Files.copy(is, tempOutput, StandardCopyOption.REPLACE_EXISTING);
	        }
	        Desktop.getDesktop().open(tempOutput.toFile());
			
		} catch (IOException e) {
			System.err.println("could not open PDF document...");
		}
	}

}
