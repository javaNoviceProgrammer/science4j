/*
 * func4j_optimize_Minimize1D.cpp
 *
 *  Created on: Feb 6, 2021
 *      Author: meisam
 */

#include "../headers/func4j_optimize_Minimize1D.h"

#include <gsl/gsl_errno.h>
#include <gsl/gsl_math.h>
#include <gsl/gsl_min.h>

namespace {

	jclass minimize1d_func_class ;
	jmethodID minimize1d_func_value_id ;
	jfieldID minimize1d_func_id ;
	jfieldID minimize1d_xlower_id ;
	jfieldID minimize1d_xupper_id ;
	jfieldID minimize1d_xguess_id ;
	jfieldID minimize1d_abserr_id ;
	jfieldID minimize1d_relerr_id ;
	jfieldID minimize1d_max_num_iterations_id ;

	struct minimize_1d_params {
		JNIEnv *jvm ;
		jobject Minimize_obj ;
	};

	jdouble f_minimize_1d(jdouble x, void *params) {
		minimize_1d_params jp = *(minimize_1d_params *) params ;
		jobject minimize1d_func_obj = jp.jvm -> GetObjectField(jp.Minimize_obj, minimize1d_func_id) ;
		return jp.jvm -> CallDoubleMethod(minimize1d_func_obj, minimize1d_func_value_id, x) ;
	}

}


/*
 * Class:     func4j_optimize_Minimize1D
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_func4j_optimize_Minimize1D_initIDs
  (JNIEnv *jvm, jclass Minimize_class) {
	minimize1d_func_class = jvm -> FindClass("func4j/function/MathFunction") ;
	minimize1d_func_value_id = jvm -> GetMethodID(minimize1d_func_class, "value", "(D)D") ;
	minimize1d_func_id = jvm -> GetFieldID(Minimize_class, "func", "Lfunc4j/function/MathFunction;") ;
	minimize1d_xlower_id = jvm -> GetFieldID(Minimize_class, "xlower", "D") ;
	minimize1d_xupper_id = jvm -> GetFieldID(Minimize_class, "xupper", "D") ;
	minimize1d_xguess_id = jvm -> GetFieldID(Minimize_class, "xguess", "D") ;
	minimize1d_abserr_id = jvm -> GetFieldID(Minimize_class, "absErr", "D") ;
	minimize1d_relerr_id = jvm -> GetFieldID(Minimize_class, "relErr", "D") ;
	minimize1d_max_num_iterations_id = jvm -> GetFieldID(Minimize_class, "maxNumberOfIterations", "I") ;
}

/*
 * Class:     func4j_optimize_Minimize1D
 * Method:    goldenSection
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_func4j_optimize_Minimize1D_goldenSection
  (JNIEnv *jvm, jobject Minimize) {
	jdouble epsabs = jvm -> GetDoubleField(Minimize, minimize1d_abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Minimize, minimize1d_relerr_id) ;
	jdouble x_lower = jvm -> GetDoubleField(Minimize, minimize1d_xlower_id) ;
	jdouble x_upper = jvm -> GetDoubleField(Minimize, minimize1d_xupper_id) ;
	jdouble x_guess = jvm -> GetDoubleField(Minimize, minimize1d_xguess_id) ;
	jint max_iter = jvm -> GetIntField(Minimize, minimize1d_max_num_iterations_id) ;

	minimize_1d_params p = {jvm, Minimize} ;
	gsl_function F ;
	F.function = &f_minimize_1d ;
	F.params = &p ;

	const gsl_min_fminimizer_type *T = gsl_min_fminimizer_goldensection ;
	gsl_min_fminimizer *s = gsl_min_fminimizer_alloc(T) ;
	gsl_min_fminimizer_set(s, &F, x_guess, x_lower, x_upper) ;
	jint iter = 0 ;
	jint status ;

	do {
	  iter++;
	  status = gsl_min_fminimizer_iterate(s);
	  x_guess = gsl_min_fminimizer_x_minimum(s);
	  x_lower = gsl_min_fminimizer_x_lower(s);
	  x_upper = gsl_min_fminimizer_x_upper(s);
	  status = gsl_min_test_interval(x_lower, x_upper, epsabs, epsrel) ;
	  if (status == GSL_SUCCESS) {
		  break ;
	  }

	} while(status == GSL_CONTINUE && iter<max_iter);

	gsl_min_fminimizer_free(s);

	return x_guess ;
}

/*
 * Class:     func4j_optimize_Minimize1D
 * Method:    brent
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_func4j_optimize_Minimize1D_brent
  (JNIEnv *jvm, jobject Minimize) {
	jdouble epsabs = jvm -> GetDoubleField(Minimize, minimize1d_abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Minimize, minimize1d_relerr_id) ;
	jdouble x_lower = jvm -> GetDoubleField(Minimize, minimize1d_xlower_id) ;
	jdouble x_upper = jvm -> GetDoubleField(Minimize, minimize1d_xupper_id) ;
	jdouble x_guess = jvm -> GetDoubleField(Minimize, minimize1d_xguess_id) ;
	jint max_iter = jvm -> GetIntField(Minimize, minimize1d_max_num_iterations_id) ;

	minimize_1d_params p = {jvm, Minimize} ;
	gsl_function F ;
	F.function = &f_minimize_1d ;
	F.params = &p ;

	const gsl_min_fminimizer_type *T = gsl_min_fminimizer_brent ;
	gsl_min_fminimizer *s = gsl_min_fminimizer_alloc(T) ;
	gsl_min_fminimizer_set(s, &F, x_guess, x_lower, x_upper) ;
	jint iter = 0 ;
	jint status ;

	do {
	  iter++;
	  status = gsl_min_fminimizer_iterate(s);
	  x_guess = gsl_min_fminimizer_x_minimum(s);
	  x_lower = gsl_min_fminimizer_x_lower(s);
	  x_upper = gsl_min_fminimizer_x_upper(s);
	  status = gsl_min_test_interval(x_lower, x_upper, epsabs, epsrel) ;
	  if (status == GSL_SUCCESS) {
		  break ;
	  }

	} while (status == GSL_CONTINUE && iter<max_iter);

	gsl_min_fminimizer_free (s);

	return x_guess ;
}

/*
 * Class:     func4j_optimize_Minimize1D
 * Method:    quadGolden
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_func4j_optimize_Minimize1D_quadGolden
  (JNIEnv *jvm, jobject Minimize) {
	jdouble epsabs = jvm -> GetDoubleField(Minimize, minimize1d_abserr_id) ;
	jdouble epsrel = jvm -> GetDoubleField(Minimize, minimize1d_relerr_id) ;
	jdouble x_lower = jvm -> GetDoubleField(Minimize, minimize1d_xlower_id) ;
	jdouble x_upper = jvm -> GetDoubleField(Minimize, minimize1d_xupper_id) ;
	jdouble x_guess = jvm -> GetDoubleField(Minimize, minimize1d_xguess_id) ;
	jint max_iter = jvm -> GetIntField(Minimize, minimize1d_max_num_iterations_id) ;

	minimize_1d_params p = {jvm, Minimize} ;
	gsl_function F ;
	F.function = &f_minimize_1d ;
	F.params = &p ;

	const gsl_min_fminimizer_type *T = gsl_min_fminimizer_quad_golden ;
	gsl_min_fminimizer *s = gsl_min_fminimizer_alloc(T) ;
	gsl_min_fminimizer_set(s, &F, x_guess, x_lower, x_upper) ;
	jint iter = 0 ;
	jint status ;

	do {
	  iter++;
	  status = gsl_min_fminimizer_iterate(s);
	  x_guess = gsl_min_fminimizer_x_minimum(s);
	  x_lower = gsl_min_fminimizer_x_lower(s);
	  x_upper = gsl_min_fminimizer_x_upper(s);
	  status = gsl_min_test_interval(x_lower, x_upper, epsabs, epsrel) ;
	  if (status == GSL_SUCCESS) {
		  break ;
	  }

	} while (status == GSL_CONTINUE && iter<max_iter);

	gsl_min_fminimizer_free (s);

	return x_guess ;
}


