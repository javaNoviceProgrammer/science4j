/*
 * ode_system.cpp
 *
 *  Created on: Jul 2, 2020
 *      Author: meisam
 */

#include <gsl/gsl_errno.h>
#include <gsl/gsl_matrix.h>
#include <gsl/gsl_odeiv2.h>
#include "../headers/org_gsl4j_ode_OdeSystemSolver.h"


jclass odesys_derivnfunc_class ;
jclass odesys_derivnfunc_df_dx_class ;
jclass odesys_derivnfunc_df_dy_class ;
jmethodID odesys_derivnfunc_value_id ;
jmethodID odesys_derivnfunc_df_dx_value_id ;
jmethodID odesys_derivnfunc_df_dy_value_id ;
jfieldID odesys_derivnfunc_id ;
jfieldID odesys_derivnfunc_df_dx_id ;
jfieldID odesys_derivnfunc_df_dy_id ;

jfieldID odesys_dim_id ;
jfieldID odesys_x0_id ;
jfieldID odesys_y0_id ;
jfieldID odesys_abserr_id ;
jfieldID odesys_relerr_id ;
jfieldID odesys_min_step_size_id ;
jfieldID odesys_max_step_size_id ;
jfieldID odesys_max_num_steps_id ;


struct ode_system_params {
	JNIEnv *env ;
	jobject OdeSystemSolver ;
};


jint f_ode_system (jdouble x, const jdouble y[], jdouble f[], void *params) {
	ode_system_params jp = *(ode_system_params *) params ;
	jint dim = jp.env -> GetIntField(jp.OdeSystemSolver, odesys_dim_id) ;
	jdoubleArray y_array = jp.env->NewDoubleArray(dim) ;
	jp.env->SetDoubleArrayRegion(y_array, 0, dim, y) ;
	jobject odesys_derivnfunc_obj = jp.env -> GetObjectField(jp.OdeSystemSolver, odesys_derivnfunc_id) ;
	jobject f_values_obj = jp.env->CallObjectMethod(odesys_derivnfunc_obj, odesys_derivnfunc_value_id, x, y_array) ;
	jdoubleArray f_values = (jdoubleArray)f_values_obj ;
	jp.env->GetDoubleArrayRegion(f_values, 0, dim, f) ;
	return GSL_SUCCESS ;
}


jint jac_ode_system (jdouble x, const jdouble y[], jdouble *dfdy, jdouble dfdt[], void *params) {
	ode_system_params jp = *(ode_system_params *) params ;
	jint dim = jp.env -> GetIntField(jp.OdeSystemSolver, odesys_dim_id) ;
	// get df/dx (or df/dt)
	jdoubleArray y_array = jp.env->NewDoubleArray(dim) ;
	jp.env->SetDoubleArrayRegion(y_array, 0, dim, y) ;
	jobject odesys_derivnfunc_df_dx_obj = jp.env -> GetObjectField(jp.OdeSystemSolver, odesys_derivnfunc_df_dx_id) ;
	jobject df_dx_values_obj = jp.env->CallObjectMethod(odesys_derivnfunc_df_dx_obj, odesys_derivnfunc_df_dx_value_id, x, y_array) ;
	jdoubleArray df_dx_values = (jdoubleArray) df_dx_values_obj ;
	jp.env->GetDoubleArrayRegion(df_dx_values, 0, dim, dfdt) ;
	// get df/dy (jacobian)
	jobject odesys_derivnfunc_df_dy_obj = jp.env -> GetObjectField(jp.OdeSystemSolver, odesys_derivnfunc_df_dy_id) ;
	jobject df_dy_values_obj = jp.env->CallObjectMethod(odesys_derivnfunc_df_dy_obj, odesys_derivnfunc_df_dy_value_id, x, y_array) ;
	jobjectArray df_dy_values_array = (jobjectArray) df_dy_values_obj ;
	gsl_matrix_view dfdy_mat = gsl_matrix_view_array(dfdy, (size_t)dim, (size_t)dim);
	gsl_matrix * m = &dfdy_mat.matrix;
	jobject row_obj ;
	jdoubleArray row_array ;
	jdouble *buf = new jdouble[dim] ;
	for(int i=0; i<dim; i++) {
		row_obj = jp.env -> GetObjectArrayElement(df_dy_values_array, i) ;
		row_array = (jdoubleArray) row_obj ;
		jp.env->GetDoubleArrayRegion(row_array, 0, dim, buf) ;
		for(int j=0; j<dim; j++) {
			gsl_matrix_set(m, i, j, buf[j]);
		}
	}
	delete[] buf ;
	return GSL_SUCCESS;
}


/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    initIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_ode_OdeSystemSolver_initIDs
  (JNIEnv *env, jclass OdeSystemSolver) {
	odesys_derivnfunc_class = env -> FindClass("org/gsl4j/ode/DerivnFunction") ;
	odesys_derivnfunc_value_id = env -> GetMethodID(odesys_derivnfunc_class, "values", "(D[D)[D") ;
	odesys_derivnfunc_id = env -> GetFieldID(OdeSystemSolver, "func", "Lorg/gsl4j/ode/DerivnFunction;") ;

	odesys_derivnfunc_df_dx_class = env -> FindClass("org/gsl4j/ode/DerivnFunction") ;
	odesys_derivnfunc_df_dx_value_id = env -> GetMethodID(odesys_derivnfunc_df_dx_class, "values", "(D[D)[D") ;
	odesys_derivnfunc_df_dx_id = env -> GetFieldID(OdeSystemSolver, "df_dx", "Lorg/gsl4j/ode/DerivnFunction;") ;

	odesys_derivnfunc_df_dy_class = env -> FindClass("org/gsl4j/ode/DerivnJacobian") ;
	odesys_derivnfunc_df_dy_value_id = env -> GetMethodID(odesys_derivnfunc_df_dy_class, "values", "(D[D)[[D") ;
	odesys_derivnfunc_df_dy_id = env -> GetFieldID(OdeSystemSolver, "df_dy", "Lorg/gsl4j/ode/DerivnJacobian;") ;

	odesys_dim_id = env -> GetFieldID(OdeSystemSolver, "dim", "I") ;
	odesys_x0_id = env -> GetFieldID(OdeSystemSolver, "x0", "D") ;
	odesys_y0_id = env -> GetFieldID(OdeSystemSolver, "y0", "[D") ;
	odesys_abserr_id = env -> GetFieldID(OdeSystemSolver, "absErr", "D") ;
	odesys_relerr_id = env -> GetFieldID(OdeSystemSolver, "relErr", "D") ;
	odesys_min_step_size_id = env -> GetFieldID(OdeSystemSolver, "minStepSize", "D") ;
	odesys_max_step_size_id = env -> GetFieldID(OdeSystemSolver, "maxStepSize", "D") ;
	odesys_max_num_steps_id = env -> GetFieldID(OdeSystemSolver, "maxNumberOfSteps", "I") ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rk2
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rk2__D
  (JNIEnv *env, jobject OdeSystemSolver, jdouble x) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble y[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk2, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	jdoubleArray jresult = env->NewDoubleArray(dim) ;
	env->SetDoubleArrayRegion(jresult, 0, dim, y) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rk2
 * Signature: ([D)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rk2___3D
  (JNIEnv *env, jobject OdeSystemSolver, jdoubleArray x_array) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble *y = new jdouble[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk2, min_step_size, epsabs, epsrel);

	jint len = env -> GetArrayLength(x_array) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x_array, 0, len, x) ;
	jdouble *result = new jdouble[dim*len] ;
	jint status ;
	for(int i=0; i<len; i++) {
		status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		for(int j=0; j<dim; j++) {
			result[i+j*len] = y[j] ;
		}
	}
	gsl_odeiv2_driver_free(d);
	delete[] y ;
	delete[] x ;
	// create jobjectArray
	jclass doubleArrCls = env->FindClass("[D");
	jobjectArray jresult_obj = env -> NewObjectArray(dim, doubleArrCls, NULL) ;
	jdoubleArray jresult ;
	jdouble *buf = new jdouble[len] ;
	for(int i=0; i<dim; i++) {
		jresult = env->NewDoubleArray(len) ;
		for(int j=0; j<len; j++) {
			buf[j] = result[i*len+j] ;
		}
		env->SetDoubleArrayRegion(jresult, 0, len, buf) ;
		env->SetObjectArrayElement(jresult_obj, i, jresult) ;
	}
	delete[] result;
	delete[] buf ;
	return jresult_obj ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rk4
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rk4__D
  (JNIEnv *env, jobject OdeSystemSolver, jdouble x) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble y[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk4, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	jdoubleArray jresult = env->NewDoubleArray(dim) ;
	env->SetDoubleArrayRegion(jresult, 0, dim, y) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rk4
 * Signature: ([D)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rk4___3D
  (JNIEnv *env, jobject OdeSystemSolver, jdoubleArray x_array) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble *y = new jdouble[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk4, min_step_size, epsabs, epsrel);

	jint len = env -> GetArrayLength(x_array) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x_array, 0, len, x) ;
	jdouble *result = new jdouble[dim*len] ;
	jint status ;
	for(int i=0; i<len; i++) {
		status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		for(int j=0; j<dim; j++) {
			result[i+j*len] = y[j] ;
		}
	}
	gsl_odeiv2_driver_free(d);
	delete[] y ;
	delete[] x ;
	// create jobjectArray
	jclass doubleArrCls = env->FindClass("[D");
	jobjectArray jresult_obj = env -> NewObjectArray(dim, doubleArrCls, NULL) ;
	jdoubleArray jresult ;
	jdouble *buf = new jdouble[len] ;
	for(int i=0; i<dim; i++) {
		jresult = env->NewDoubleArray(len) ;
		for(int j=0; j<len; j++) {
			buf[j] = result[i*len+j] ;
		}
		env->SetDoubleArrayRegion(jresult, 0, len, buf) ;
		env->SetObjectArrayElement(jresult_obj, i, jresult) ;
	}
	delete[] result;
	delete[] buf ;
	return jresult_obj ;
  }

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rkf45
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rkf45__D
  (JNIEnv *env, jobject OdeSystemSolver, jdouble x) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble y[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rkf45, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	jdoubleArray jresult = env->NewDoubleArray(dim) ;
	env->SetDoubleArrayRegion(jresult, 0, dim, y) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rkf45
 * Signature: ([D)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rkf45___3D
  (JNIEnv *env, jobject OdeSystemSolver, jdoubleArray x_array) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble *y = new jdouble[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rkf45, min_step_size, epsabs, epsrel);

	jint len = env -> GetArrayLength(x_array) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x_array, 0, len, x) ;
	jdouble *result = new jdouble[dim*len] ;
	jint status ;
	for(int i=0; i<len; i++) {
		status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		for(int j=0; j<dim; j++) {
			result[i+j*len] = y[j] ;
		}
	}
	gsl_odeiv2_driver_free(d);
	delete[] y ;
	delete[] x ;
	// create jobjectArray
	jclass doubleArrCls = env->FindClass("[D");
	jobjectArray jresult_obj = env -> NewObjectArray(dim, doubleArrCls, NULL) ;
	jdoubleArray jresult ;
	jdouble *buf = new jdouble[len] ;
	for(int i=0; i<dim; i++) {
		jresult = env->NewDoubleArray(len) ;
		for(int j=0; j<len; j++) {
			buf[j] = result[i*len+j] ;
		}
		env->SetDoubleArrayRegion(jresult, 0, len, buf) ;
		env->SetObjectArrayElement(jresult_obj, i, jresult) ;
	}
	delete[] result;
	delete[] buf ;
	return jresult_obj ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rkck
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rkck__D
  (JNIEnv *env, jobject OdeSystemSolver, jdouble x) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble y[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rkck, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	jdoubleArray jresult = env->NewDoubleArray(dim) ;
	env->SetDoubleArrayRegion(jresult, 0, dim, y) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rkck
 * Signature: ([D)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rkck___3D
  (JNIEnv *env, jobject OdeSystemSolver, jdoubleArray x_array) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble *y = new jdouble[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rkck, min_step_size, epsabs, epsrel);

	jint len = env -> GetArrayLength(x_array) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x_array, 0, len, x) ;
	jdouble *result = new jdouble[dim*len] ;
	jint status ;
	for(int i=0; i<len; i++) {
		status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		for(int j=0; j<dim; j++) {
			result[i+j*len] = y[j] ;
		}
	}
	gsl_odeiv2_driver_free(d);
	delete[] y ;
	delete[] x ;
	// create jobjectArray
	jclass doubleArrCls = env->FindClass("[D");
	jobjectArray jresult_obj = env -> NewObjectArray(dim, doubleArrCls, NULL) ;
	jdoubleArray jresult ;
	jdouble *buf = new jdouble[len] ;
	for(int i=0; i<dim; i++) {
		jresult = env->NewDoubleArray(len) ;
		for(int j=0; j<len; j++) {
			buf[j] = result[i*len+j] ;
		}
		env->SetDoubleArrayRegion(jresult, 0, len, buf) ;
		env->SetObjectArrayElement(jresult_obj, i, jresult) ;
	}
	delete[] result;
	delete[] buf ;
	return jresult_obj ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rk8pd
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rk8pd__D
  (JNIEnv *env, jobject OdeSystemSolver, jdouble x) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble y[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk8pd, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	jdoubleArray jresult = env->NewDoubleArray(dim) ;
	env->SetDoubleArrayRegion(jresult, 0, dim, y) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rk8pd
 * Signature: ([D)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rk8pd___3D
  (JNIEnv *env, jobject OdeSystemSolver, jdoubleArray x_array) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble *y = new jdouble[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk8pd, min_step_size, epsabs, epsrel);

	jint len = env -> GetArrayLength(x_array) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x_array, 0, len, x) ;
	jdouble *result = new jdouble[dim*len] ;
	jint status ;
	for(int i=0; i<len; i++) {
		status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		for(int j=0; j<dim; j++) {
			result[i+j*len] = y[j] ;
		}
	}
	gsl_odeiv2_driver_free(d);
	delete[] y ;
	delete[] x ;
	// create jobjectArray
	jclass doubleArrCls = env->FindClass("[D");
	jobjectArray jresult_obj = env -> NewObjectArray(dim, doubleArrCls, NULL) ;
	jdoubleArray jresult ;
	jdouble *buf = new jdouble[len] ;
	for(int i=0; i<dim; i++) {
		jresult = env->NewDoubleArray(len) ;
		for(int j=0; j<len; j++) {
			buf[j] = result[i*len+j] ;
		}
		env->SetDoubleArrayRegion(jresult, 0, len, buf) ;
		env->SetObjectArrayElement(jresult_obj, i, jresult) ;
	}
	delete[] result;
	delete[] buf ;
	return jresult_obj ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rk1imp
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rk1imp__D
  (JNIEnv *env, jobject OdeSystemSolver, jdouble x) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble y[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk1imp, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	jdoubleArray jresult = env->NewDoubleArray(dim) ;
	env->SetDoubleArrayRegion(jresult, 0, dim, y) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rk1imp
 * Signature: ([D)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rk1imp___3D
  (JNIEnv *env, jobject OdeSystemSolver, jdoubleArray x_array) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble *y = new jdouble[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk1imp, min_step_size, epsabs, epsrel);

	jint len = env -> GetArrayLength(x_array) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x_array, 0, len, x) ;
	jdouble *result = new jdouble[dim*len] ;
	jint status ;
	for(int i=0; i<len; i++) {
		status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		for(int j=0; j<dim; j++) {
			result[i+j*len] = y[j] ;
		}
	}
	gsl_odeiv2_driver_free(d);
	delete[] y ;
	delete[] x ;
	// create jobjectArray
	jclass doubleArrCls = env->FindClass("[D");
	jobjectArray jresult_obj = env -> NewObjectArray(dim, doubleArrCls, NULL) ;
	jdoubleArray jresult ;
	jdouble *buf = new jdouble[len] ;
	for(int i=0; i<dim; i++) {
		jresult = env->NewDoubleArray(len) ;
		for(int j=0; j<len; j++) {
			buf[j] = result[i*len+j] ;
		}
		env->SetDoubleArrayRegion(jresult, 0, len, buf) ;
		env->SetObjectArrayElement(jresult_obj, i, jresult) ;
	}
	delete[] result;
	delete[] buf ;
	return jresult_obj ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rk2imp
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rk2imp__D
  (JNIEnv *env, jobject OdeSystemSolver, jdouble x) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble y[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk2imp, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	jdoubleArray jresult = env->NewDoubleArray(dim) ;
	env->SetDoubleArrayRegion(jresult, 0, dim, y) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rk2imp
 * Signature: ([D)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rk2imp___3D
  (JNIEnv *env, jobject OdeSystemSolver, jdoubleArray x_array) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble *y = new jdouble[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk2imp, min_step_size, epsabs, epsrel);

	jint len = env -> GetArrayLength(x_array) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x_array, 0, len, x) ;
	jdouble *result = new jdouble[dim*len] ;
	jint status ;
	for(int i=0; i<len; i++) {
		status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		for(int j=0; j<dim; j++) {
			result[i+j*len] = y[j] ;
		}
	}
	gsl_odeiv2_driver_free(d);
	delete[] y ;
	delete[] x ;
	// create jobjectArray
	jclass doubleArrCls = env->FindClass("[D");
	jobjectArray jresult_obj = env -> NewObjectArray(dim, doubleArrCls, NULL) ;
	jdoubleArray jresult ;
	jdouble *buf = new jdouble[len] ;
	for(int i=0; i<dim; i++) {
		jresult = env->NewDoubleArray(len) ;
		for(int j=0; j<len; j++) {
			buf[j] = result[i*len+j] ;
		}
		env->SetDoubleArrayRegion(jresult, 0, len, buf) ;
		env->SetObjectArrayElement(jresult_obj, i, jresult) ;
	}
	delete[] result;
	delete[] buf ;
	return jresult_obj ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rk4imp
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rk4imp__D
  (JNIEnv *env, jobject OdeSystemSolver, jdouble x) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble y[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk4imp, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	jdoubleArray jresult = env->NewDoubleArray(dim) ;
	env->SetDoubleArrayRegion(jresult, 0, dim, y) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    rk4imp
 * Signature: ([D)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_rk4imp___3D
  (JNIEnv *env, jobject OdeSystemSolver, jdoubleArray x_array) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble *y = new jdouble[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_rk4imp, min_step_size, epsabs, epsrel);

	jint len = env -> GetArrayLength(x_array) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x_array, 0, len, x) ;
	jdouble *result = new jdouble[dim*len] ;
	jint status ;
	for(int i=0; i<len; i++) {
		status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		for(int j=0; j<dim; j++) {
			result[i+j*len] = y[j] ;
		}
	}
	gsl_odeiv2_driver_free(d);
	delete[] y ;
	delete[] x ;
	// create jobjectArray
	jclass doubleArrCls = env->FindClass("[D");
	jobjectArray jresult_obj = env -> NewObjectArray(dim, doubleArrCls, NULL) ;
	jdoubleArray jresult ;
	jdouble *buf = new jdouble[len] ;
	for(int i=0; i<dim; i++) {
		jresult = env->NewDoubleArray(len) ;
		for(int j=0; j<len; j++) {
			buf[j] = result[i*len+j] ;
		}
		env->SetDoubleArrayRegion(jresult, 0, len, buf) ;
		env->SetObjectArrayElement(jresult_obj, i, jresult) ;
	}
	delete[] result;
	delete[] buf ;
	return jresult_obj ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    bsimp
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_bsimp__D
  (JNIEnv *env, jobject OdeSystemSolver, jdouble x) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble y[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_bsimp, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	jdoubleArray jresult = env->NewDoubleArray(dim) ;
	env->SetDoubleArrayRegion(jresult, 0, dim, y) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    bsimp
 * Signature: ([D)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_bsimp___3D
  (JNIEnv *env, jobject OdeSystemSolver, jdoubleArray x_array) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble *y = new jdouble[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_bsimp, min_step_size, epsabs, epsrel);

	jint len = env -> GetArrayLength(x_array) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x_array, 0, len, x) ;
	jdouble *result = new jdouble[dim*len] ;
	jint status ;
	for(int i=0; i<len; i++) {
		status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		for(int j=0; j<dim; j++) {
			result[i+j*len] = y[j] ;
		}
	}
	gsl_odeiv2_driver_free(d);
	delete[] y ;
	delete[] x ;
	// create jobjectArray
	jclass doubleArrCls = env->FindClass("[D");
	jobjectArray jresult_obj = env -> NewObjectArray(dim, doubleArrCls, NULL) ;
	jdoubleArray jresult ;
	jdouble *buf = new jdouble[len] ;
	for(int i=0; i<dim; i++) {
		jresult = env->NewDoubleArray(len) ;
		for(int j=0; j<len; j++) {
			buf[j] = result[i*len+j] ;
		}
		env->SetDoubleArrayRegion(jresult, 0, len, buf) ;
		env->SetObjectArrayElement(jresult_obj, i, jresult) ;
	}
	delete[] result;
	delete[] buf ;
	return jresult_obj ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    msadams
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_msadams__D
  (JNIEnv *env, jobject OdeSystemSolver, jdouble x) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble y[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_msadams, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	jdoubleArray jresult = env->NewDoubleArray(dim) ;
	env->SetDoubleArrayRegion(jresult, 0, dim, y) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    msadams
 * Signature: ([D)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_msadams___3D
  (JNIEnv *env, jobject OdeSystemSolver, jdoubleArray x_array) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble *y = new jdouble[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_msadams, min_step_size, epsabs, epsrel);

	jint len = env -> GetArrayLength(x_array) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x_array, 0, len, x) ;
	jdouble *result = new jdouble[dim*len] ;
	jint status ;
	for(int i=0; i<len; i++) {
		status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		for(int j=0; j<dim; j++) {
			result[i+j*len] = y[j] ;
		}
	}
	gsl_odeiv2_driver_free(d);
	delete[] y ;
	delete[] x ;
	// create jobjectArray
	jclass doubleArrCls = env->FindClass("[D");
	jobjectArray jresult_obj = env -> NewObjectArray(dim, doubleArrCls, NULL) ;
	jdoubleArray jresult ;
	jdouble *buf = new jdouble[len] ;
	for(int i=0; i<dim; i++) {
		jresult = env->NewDoubleArray(len) ;
		for(int j=0; j<len; j++) {
			buf[j] = result[i*len+j] ;
		}
		env->SetDoubleArrayRegion(jresult, 0, len, buf) ;
		env->SetObjectArrayElement(jresult_obj, i, jresult) ;
	}
	delete[] result;
	delete[] buf ;
	return jresult_obj ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    msbdf
 * Signature: (D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_msbdf__D
  (JNIEnv *env, jobject OdeSystemSolver, jdouble x) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble y[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_msbdf, min_step_size, epsabs, epsrel);
	jint status = gsl_odeiv2_driver_apply(d, &x0, x, y) ;
	if(status != GSL_SUCCESS) {
		printf ("error, return value=%d\n", status);
	}
	gsl_odeiv2_driver_free(d);
	jdoubleArray jresult = env->NewDoubleArray(dim) ;
	env->SetDoubleArrayRegion(jresult, 0, dim, y) ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_ode_OdeSystemSolver
 * Method:    msbdf
 * Signature: ([D)[[D
 */
JNIEXPORT jobjectArray JNICALL Java_org_gsl4j_ode_OdeSystemSolver_msbdf___3D
  (JNIEnv *env, jobject OdeSystemSolver, jdoubleArray x_array) {
	jint dim = env -> GetIntField(OdeSystemSolver, odesys_dim_id) ;
	jdouble epsabs = env -> GetDoubleField(OdeSystemSolver, odesys_abserr_id) ;
	jdouble epsrel = env -> GetDoubleField(OdeSystemSolver, odesys_relerr_id) ;
	jdouble min_step_size = env -> GetDoubleField(OdeSystemSolver, odesys_min_step_size_id) ;
	jdouble x0 = env -> GetDoubleField(OdeSystemSolver, odesys_x0_id) ;
	jobject y0_obj = env -> GetObjectField(OdeSystemSolver, odesys_y0_id) ;
	jdoubleArray y0 = (jdoubleArray) y0_obj ;

	ode_system_params p = {env, OdeSystemSolver} ;
	jdouble *y = new jdouble[dim] ;
	env -> GetDoubleArrayRegion(y0, 0, dim, y) ;

	gsl_odeiv2_system sys = {f_ode_system, jac_ode_system, (size_t)dim, &p};
	gsl_odeiv2_driver * d = gsl_odeiv2_driver_alloc_y_new(&sys, gsl_odeiv2_step_msbdf, min_step_size, epsabs, epsrel);

	jint len = env -> GetArrayLength(x_array) ;
	jdouble *x = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x_array, 0, len, x) ;
	jdouble *result = new jdouble[dim*len] ;
	jint status ;
	for(int i=0; i<len; i++) {
		status = gsl_odeiv2_driver_apply(d, &x0, x[i], y) ;
		if(status != GSL_SUCCESS) {
			printf ("error, return value=%d\n", status);
		}
		for(int j=0; j<dim; j++) {
			result[i+j*len] = y[j] ;
		}
	}
	gsl_odeiv2_driver_free(d);
	delete[] y ;
	delete[] x ;
	// create jobjectArray
	jclass doubleArrCls = env->FindClass("[D");
	jobjectArray jresult_obj = env -> NewObjectArray(dim, doubleArrCls, NULL) ;
	jdoubleArray jresult ;
	jdouble *buf = new jdouble[len] ;
	for(int i=0; i<dim; i++) {
		jresult = env->NewDoubleArray(len) ;
		for(int j=0; j<len; j++) {
			buf[j] = result[i*len+j] ;
		}
		env->SetDoubleArrayRegion(jresult, 0, len, buf) ;
		env->SetObjectArrayElement(jresult_obj, i, jresult) ;
	}
	delete[] result;
	delete[] buf ;
	return jresult_obj ;
}



