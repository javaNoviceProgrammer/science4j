/*
 * minimize_1d.cpp
 *
 *  Created on: Jul 4, 2020
 *      Author: meisam
 */

#include <gsl/gsl_errno.h>
#include <gsl/gsl_math.h>
#include <gsl/gsl_min.h>
#include "../headers/org_gsl4j_optimize_Minimize.h"

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
	JNIEnv *env ;
	jobject Minimize ;
};

jdouble f_minimize_1d(jdouble x, void *params) {
	minimize_1d_params jp = *(minimize_1d_params *) params ;
	jobject minimize1d_func_obj = jp.env -> GetObjectField(jp.Minimize, minimize1d_func_id) ;
	return jp.env -> CallDoubleMethod(minimize1d_func_obj, minimize1d_func_value_id, x) ;
}


/*
 * Class:     org_gsl4j_optimize_Minimize
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_optimize_Minimize_initIDs
  (JNIEnv *env, jclass Minimize) {
	minimize1d_func_class = env -> FindClass("org/gsl4j/function/MathFunction") ;
	minimize1d_func_value_id = env -> GetMethodID(minimize1d_func_class, "value", "(D)D") ;
	minimize1d_func_id = env -> GetFieldID(Minimize, "func", "Lorg/gsl4j/function/MathFunction;") ;
	minimize1d_xlower_id = env -> GetFieldID(Minimize, "xlower", "D") ;
	minimize1d_xupper_id = env -> GetFieldID(Minimize, "xupper", "D") ;
	minimize1d_xguess_id = env -> GetFieldID(Minimize, "xguess", "D") ;
	minimize1d_abserr_id = env -> GetFieldID(Minimize, "absErr", "D") ;
	minimize1d_relerr_id = env -> GetFieldID(Minimize, "relErr", "D") ;
	minimize1d_max_num_iterations_id = env -> GetFieldID(Minimize, "maxNumberOfIterations", "I") ;
}

/*
 * Class:     org_gsl4j_optimize_Minimize
 * Method:    goldenSection
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_optimize_Minimize_goldenSection
  (JNIEnv *env, jobject Minimize) {
	jdouble epsabs = env -> GetDoubleField(Minimize, minimize1d_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(Minimize, minimize1d_relerr_id) ;
	jdouble x_lower = env -> GetDoubleField(Minimize, minimize1d_xlower_id) ;
	jdouble x_upper = env -> GetDoubleField(Minimize, minimize1d_xupper_id) ;
	jdouble x_guess = env -> GetDoubleField(Minimize, minimize1d_xguess_id) ;
	jint max_iter = env -> GetIntField(Minimize, minimize1d_max_num_iterations_id) ;

	minimize_1d_params p = {env, Minimize} ;
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
 * Class:     org_gsl4j_optimize_Minimize
 * Method:    brent
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_optimize_Minimize_brent
  (JNIEnv *env, jobject Minimize) {
	jdouble epsabs = env -> GetDoubleField(Minimize, minimize1d_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(Minimize, minimize1d_relerr_id) ;
	jdouble x_lower = env -> GetDoubleField(Minimize, minimize1d_xlower_id) ;
	jdouble x_upper = env -> GetDoubleField(Minimize, minimize1d_xupper_id) ;
	jdouble x_guess = env -> GetDoubleField(Minimize, minimize1d_xguess_id) ;
	jint max_iter = env -> GetIntField(Minimize, minimize1d_max_num_iterations_id) ;

	minimize_1d_params p = {env, Minimize} ;
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
 * Class:     org_gsl4j_optimize_Minimize
 * Method:    quadGolden
 * Signature: ()D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_optimize_Minimize_quadGolden
  (JNIEnv *env, jobject Minimize) {
	jdouble epsabs = env -> GetDoubleField(Minimize, minimize1d_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(Minimize, minimize1d_relerr_id) ;
	jdouble x_lower = env -> GetDoubleField(Minimize, minimize1d_xlower_id) ;
	jdouble x_upper = env -> GetDoubleField(Minimize, minimize1d_xupper_id) ;
	jdouble x_guess = env -> GetDoubleField(Minimize, minimize1d_xguess_id) ;
	jint max_iter = env -> GetIntField(Minimize, minimize1d_max_num_iterations_id) ;

	minimize_1d_params p = {env, Minimize} ;
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
