/*
 * ode.cpp
 *
 *  Created on: Jul 1, 2020
 *      Author: meisam
 */

#include <gsl/gsl_errno.h>
#include <gsl/gsl_matrix.h>
#include <gsl/gsl_odeiv2.h>
#include "../headers/org_gsl4j_ode_OdeSolver.h"

jclass ode_derivfunc_class ;
jclass ode_derivfunc_df_dx_class ;
jclass ode_derivfunc_df_dy_class ;
jmethodID ode_derivfunc_value_id ;
jmethodID ode_derivfunc_df_dx_value_id ;
jmethodID ode_derivfunc_df_dy_value_id ;
jfieldID ode_derivfunc_id ;
jfieldID ode_derivfunc_df_dx_id ;
jfieldID ode_derivfunc_df_dy_id ;

jfieldID ode_x0_id ;
jfieldID ode_y0_id ;
jfieldID ode_abserr_id ;
jfieldID ode_relerr_id ;
jfieldID ode_min_step_size_id ;
jfieldID ode_max_step_size_Id ;
jfieldID ode_max_num_steps_id ;


struct ode_params {
	JNIEnv *env ;
	jobject OdeSolver ;
};


jint f_ode (jdouble x, const jdouble y[], jdouble f[], void *params) {
	ode_params jp = *(ode_params *) params ;
	jobject ode_derivfunc_obj = jp.env -> GetObjectField(jp.OdeSolver, ode_derivfunc_id) ;
	f[0] = jp.env -> CallDoubleMethod(ode_derivfunc_obj, ode_derivfunc_value_id, x, y[0]) ;
	return GSL_SUCCESS ;
}

jint jac (jdouble x, const jdouble y[], jdouble *dfdy, jdouble dfdt[], void *params) {
	ode_params jp = *(ode_params *) params ;
	jobject ode_derivfunc_df_dy_obj = jp.env -> GetObjectField(jp.OdeSolver, ode_derivfunc_df_dy_id) ;
	jdouble df_dy = jp.env -> CallDoubleMethod(ode_derivfunc_df_dy_obj, ode_derivfunc_df_dy_value_id, x, y[0]) ;
	gsl_matrix_view dfdy_mat = gsl_matrix_view_array (dfdy, 1, 1);
	gsl_matrix * m = &dfdy_mat.matrix;
	gsl_matrix_set(m, 0, 0, df_dy);
	jobject ode_derivfunc_df_dx_obj = jp.env -> GetObjectField(jp.OdeSolver, ode_derivfunc_df_dx_id) ;
	jdouble df_dx = jp.env -> CallDoubleMethod(ode_derivfunc_df_dx_obj, ode_derivfunc_df_dx_value_id, x, y[0]) ;
	dfdt[0] = df_dx ;
	return GSL_SUCCESS;
}


/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_ode_OdeSolver_initIDs
  (JNIEnv *env, jclass OdeSolver) {
	ode_derivfunc_class = env -> FindClass("org/gsl4j/ode/DerivFunction") ;
	ode_derivfunc_value_id = env -> GetMethodID(ode_derivfunc_class, "value", "(DD)D") ;
	ode_derivfunc_id = env -> GetFieldID(OdeSolver, "func", "Lorg/gsl4j/ode/DerivFunction;") ;

	ode_derivfunc_df_dx_class = env -> FindClass("org/gsl4j/ode/DerivFunction") ;
	ode_derivfunc_df_dx_value_id = env -> GetMethodID(ode_derivfunc_df_dx_class, "value", "(DD)D") ;
	ode_derivfunc_df_dx_id = env -> GetFieldID(OdeSolver, "df_dx", "Lorg/gsl4j/ode/DerivFunction;") ;

	ode_derivfunc_df_dy_class = env -> FindClass("org/gsl4j/ode/DerivFunction") ;
	ode_derivfunc_df_dy_value_id = env -> GetMethodID(ode_derivfunc_df_dy_class, "value", "(DD)D") ;
	ode_derivfunc_df_dy_id = env -> GetFieldID(OdeSolver, "df_dy", "Lorg/gsl4j/ode/DerivFunction;") ;

	ode_x0_id = env -> GetFieldID(OdeSolver, "x0", "D") ;
	ode_y0_id = env -> GetFieldID(OdeSolver, "y0", "D") ;
	ode_abserr_id = env -> GetFieldID(OdeSolver, "absErr", "D") ;
	ode_relerr_id = env -> GetFieldID(OdeSolver, "relErr", "D") ;
	ode_min_step_size_id = env -> GetFieldID(OdeSolver, "minStepSize", "D") ;
	ode_max_step_size_Id = env -> GetFieldID(OdeSolver, "maxStepSize", "D") ;
	ode_max_num_steps_id = env -> GetFieldID(OdeSolver, "maxNumberOfSteps", "I") ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rk2
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ode_OdeSolver_rk2__D
  (JNIEnv *env, jobject OdeSolver, jdouble x) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk2, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	return y[0] ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rk2
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSolver_rk2___3D
  (JNIEnv *env, jobject OdeSolver, jdoubleArray jx) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jint len = env -> GetArrayLength(jx) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(jx, 0, len, x) ;
	jdouble result[len] ;
	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk2, min_step_size, epsabs, epsrel);
	for(int i=0; i<len; i++) {
		jint status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		result[i] = y[0] ;
	}
	gsl_odeiv2_driver_free(d);
	delete[] x ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rk4
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ode_OdeSolver_rk4__D
  (JNIEnv *env, jobject OdeSolver, jdouble x) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk4, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	return y[0] ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rk4
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSolver_rk4___3D
  (JNIEnv *env, jobject OdeSolver, jdoubleArray jx) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jint len = env -> GetArrayLength(jx) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(jx, 0, len, x) ;
	jdouble result[len] ;
	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk4, min_step_size, epsabs, epsrel);
	for(int i=0; i<len; i++) {
		jint status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		result[i] = y[0] ;
	}
	gsl_odeiv2_driver_free(d);
	delete[] x ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rkf45
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ode_OdeSolver_rkf45__D
  (JNIEnv *env, jobject OdeSolver, jdouble x) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rkf45, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	return y[0] ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rkf45
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSolver_rkf45___3D
  (JNIEnv *env, jobject OdeSolver, jdoubleArray jx) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jint len = env -> GetArrayLength(jx) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(jx, 0, len, x) ;
	jdouble result[len] ;
	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rkf45, min_step_size, epsabs, epsrel);
	for(int i=0; i<len; i++) {
		jint status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		result[i] = y[0] ;
	}
	gsl_odeiv2_driver_free(d);
	delete[] x ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rkck
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ode_OdeSolver_rkck__D
  (JNIEnv *env, jobject OdeSolver, jdouble x) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rkck, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	return y[0] ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rkck
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSolver_rkck___3D
  (JNIEnv *env, jobject OdeSolver, jdoubleArray jx) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jint len = env -> GetArrayLength(jx) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(jx, 0, len, x) ;
	jdouble result[len] ;
	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rkck, min_step_size, epsabs, epsrel);
	for(int i=0; i<len; i++) {
		jint status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		result[i] = y[0] ;
	}
	gsl_odeiv2_driver_free(d);
	delete[] x ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rk8pd
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ode_OdeSolver_rk8pd__D
  (JNIEnv *env, jobject OdeSolver, jdouble x) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk8pd, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	return y[0] ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rk8pd
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSolver_rk8pd___3D
  (JNIEnv *env, jobject OdeSolver, jdoubleArray jx) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jint len = env -> GetArrayLength(jx) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(jx, 0, len, x) ;
	jdouble result[len] ;
	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk8pd, min_step_size, epsabs, epsrel);
	for(int i=0; i<len; i++) {
		jint status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		result[i] = y[0] ;
	}
	gsl_odeiv2_driver_free(d);
	delete[] x ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rk1imp
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ode_OdeSolver_rk1imp__D
  (JNIEnv *env, jobject OdeSolver, jdouble x) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk1imp, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	return y[0] ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rk1imp
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSolver_rk1imp___3D
  (JNIEnv *env, jobject OdeSolver, jdoubleArray jx) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jint len = env -> GetArrayLength(jx) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(jx, 0, len, x) ;
	jdouble result[len] ;
	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk1imp, min_step_size, epsabs, epsrel);
	for(int i=0; i<len; i++) {
		jint status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		result[i] = y[0] ;
	}
	gsl_odeiv2_driver_free(d);
	delete[] x ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rk2imp
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ode_OdeSolver_rk2imp__D
  (JNIEnv *env, jobject OdeSolver, jdouble x) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk2imp, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	return y[0] ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rk2imp
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSolver_rk2imp___3D
  (JNIEnv *env, jobject OdeSolver, jdoubleArray jx) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jint len = env -> GetArrayLength(jx) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(jx, 0, len, x) ;
	jdouble result[len] ;
	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk2imp, min_step_size, epsabs, epsrel);
	for(int i=0; i<len; i++) {
		jint status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		result[i] = y[0] ;
	}
	gsl_odeiv2_driver_free(d);
	delete[] x ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rk4imp
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ode_OdeSolver_rk4imp__D
  (JNIEnv *env, jobject OdeSolver, jdouble x) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk4imp, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	return y[0] ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    rk4imp
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSolver_rk4imp___3D
  (JNIEnv *env, jobject OdeSolver, jdoubleArray jx) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jint len = env -> GetArrayLength(jx) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(jx, 0, len, x) ;
	jdouble result[len] ;
	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk4imp, min_step_size, epsabs, epsrel);
	for(int i=0; i<len; i++) {
		jint status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		result[i] = y[0] ;
	}
	gsl_odeiv2_driver_free(d);
	delete[] x ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    bsimp
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ode_OdeSolver_bsimp__D
  (JNIEnv *env, jobject OdeSolver, jdouble x) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_bsimp, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	return y[0] ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    bsimp
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSolver_bsimp___3D
  (JNIEnv *env, jobject OdeSolver, jdoubleArray jx) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jint len = env -> GetArrayLength(jx) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(jx, 0, len, x) ;
	jdouble result[len] ;
	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_bsimp, min_step_size, epsabs, epsrel);
	for(int i=0; i<len; i++) {
		jint status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		result[i] = y[0] ;
	}
	gsl_odeiv2_driver_free(d);
	delete[] x ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    msadams
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ode_OdeSolver_msadams__D
  (JNIEnv *env, jobject OdeSolver, jdouble x) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_msadams, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	return y[0] ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    msadams
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSolver_msadams___3D
  (JNIEnv *env, jobject OdeSolver, jdoubleArray jx) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jint len = env -> GetArrayLength(jx) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(jx, 0, len, x) ;
	jdouble result[len] ;
	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_msadams, min_step_size, epsabs, epsrel);
	for(int i=0; i<len; i++) {
		jint status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		result[i] = y[0] ;
	}
	gsl_odeiv2_driver_free(d);
	delete[] x ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    msbdf
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_ode_OdeSolver_msbdf__D
  (JNIEnv *env, jobject OdeSolver, jdouble x) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_msbdf, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	return y[0] ;
}

/*
 * Class:     org_gsl4j_ode_OdeSolver
 * Method:    msbdf
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSolver_msbdf___3D
  (JNIEnv *env, jobject OdeSolver, jdoubleArray jx) {
	jdouble epsabs = env -> GetDoubleField(OdeSolver, ode_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSolver, ode_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSolver, ode_min_step_size_id) ;
	jdouble max_step_size = env -> GetDoubleField(OdeSolver, ode_max_step_size_Id) ;
	jdouble max_num_steps = env -> GetDoubleField(OdeSolver, ode_max_num_steps_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSolver, ode_x0_id) ;
	jdouble y0 = env -> GetDoubleField(OdeSolver, ode_y0_id) ;

	jint len = env -> GetArrayLength(jx) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(jx, 0, len, x) ;
	jdouble result[len] ;
	jdouble y[1] = {y0} ;
	ode_params p = {env, OdeSolver} ;
	gsl_odeiv2_system sys = {f_ode, jac, 1, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_msbdf, min_step_size, epsabs, epsrel);
	for(int i=0; i<len; i++) {
		jint status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		result[i] = y[0] ;
	}
	gsl_odeiv2_driver_free(d);
	delete[] x ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	return jresult ;
}

