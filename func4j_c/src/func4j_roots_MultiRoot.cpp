/*
 * func4j_roots_MultiRoot.cpp
 *
 *  Created on: Jan 27, 2021
 *      Author: meisam
 */

#include "../headers/func4j_roots_MultiRoot.h"
#include <gsl/gsl_multiroots.h>
#include <gsl/gsl_vector.h>


namespace { // anonymous namespace

	//***** MultiRoot class parameters *****

	jclass multiroot_func_class ;
	jclass multiroot_derivfunc_class ;
	jmethodID multiroot_func_value_id ;
	jmethodID multiroot_derivfunc_value_id ;
	jfieldID multiroot_func_id ;
	jfieldID multiroot_derivfunc_id ;
	jfieldID multiroot_abserr_id ;
	jfieldID multiroot_relerr_id ;
	jfieldID multiroot_max_num_iterations_id ;
	jfieldID multiroot_dim_id ;
	jfieldID multiroot_printInfo_id ;
	jfieldID multiroot_initialGuess_id ;

	struct jparams {
		JNIEnv *jvm ;
		jobject MultiRootFunction_obj ;
	};

	//***** f(x)=[f1, f2, ...] vector function *******

	int f(const gsl_vector* x, void* params, gsl_vector* fx) {
		jparams param = *((jparams*) params) ;

		// x is input: x = [x1, x2, ...]
		jint len = (jint) x->size ;
		jdoubleArray x_vals = param.jvm -> NewDoubleArray(len) ;
		param.jvm -> SetDoubleArrayRegion(x_vals, 0, len, x->data) ;

		// y is output: y = f(x) = [y1, y2, ...]
		jobject y = param.jvm -> CallObjectMethod(param.MultiRootFunction_obj, multiroot_func_value_id, x_vals) ;
		jdoubleArray y_vals = (jdoubleArray) y ;
		jdouble* y_data = (jdouble*) param.jvm -> GetPrimitiveArrayCritical(y_vals, 0) ;

		// set the vector
		for(size_t i=0; i<x->size; i++) {
			gsl_vector_set(fx, i, y_data[i]) ;
		}

		// release primitive array
		param.jvm -> ReleasePrimitiveArrayCritical(y_vals, y_data, 0) ;

		return GSL_SUCCESS ;
	}

	//***** fdf function *******


	void print_state(jint iter, gsl_multiroot_fsolver *s) {
		printf("iter = %3d x = % .3f % .3f " "f(x) = % .3e % .3e\n",
					iter, gsl_vector_get(s->x, 0), gsl_vector_get(s->x, 1),
					gsl_vector_get(s->f, 0), gsl_vector_get(s->f, 1));

	}

}

/*
 * Class:     func4j_roots_MultiRoot
 * Method:    initFieldIDs
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_func4j_roots_MultiRoot_initFieldIDs
  (JNIEnv *jvm, jclass MultiRoot_class) {
	multiroot_func_class = jvm -> FindClass("func4j/roots/MultiRootFunction") ;
	multiroot_derivfunc_class = jvm -> FindClass("func4j/roots/MultiRootDerivFunction") ;
	multiroot_func_value_id = jvm -> GetMethodID(multiroot_func_class, "value", "([D)[D") ;
	multiroot_derivfunc_value_id = jvm -> GetMethodID(multiroot_derivfunc_class, "value", "([D)[[D") ;
	multiroot_func_id = jvm -> GetFieldID(MultiRoot_class, "func", "Lfunc4j/roots/MultiRootFunction;") ;
	multiroot_derivfunc_id = jvm -> GetFieldID(MultiRoot_class, "jacob", "Lfunc4j/roots/MultiRootDerivFunction;") ;
	multiroot_abserr_id = jvm -> GetFieldID(MultiRoot_class, "absErr", "D") ;
	multiroot_relerr_id = jvm -> GetFieldID(MultiRoot_class, "relErr", "D") ;
	multiroot_max_num_iterations_id = jvm -> GetFieldID(MultiRoot_class, "maxNumberOfIterations", "I") ;
	multiroot_dim_id = jvm -> GetFieldID(MultiRoot_class, "dim", "I") ;
	multiroot_printInfo_id = jvm -> GetFieldID(MultiRoot_class, "printInfo", "Z") ;
	multiroot_initialGuess_id = jvm -> GetFieldID(MultiRoot_class, "initialGuess", "[D") ;
}

/*
 * Class:     func4j_roots_MultiRoot
 * Method:    hybridsj
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_roots_MultiRoot_hybridsj
  (JNIEnv *jvm, jobject MultiRoot_obj) ;

/*
 * Class:     func4j_roots_MultiRoot
 * Method:    hybridj
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_roots_MultiRoot_hybridj
  (JNIEnv *jvm, jobject MultiRoot_obj);

/*
 * Class:     func4j_roots_MultiRoot
 * Method:    newton
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_roots_MultiRoot_newton
  (JNIEnv *jvm, jobject MultiRoot_obj);

/*
 * Class:     func4j_roots_MultiRoot
 * Method:    gnewton
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_roots_MultiRoot_gnewton
  (JNIEnv *jvm, jobject MultiRoot_obj);

/*
 * Class:     func4j_roots_MultiRoot
 * Method:    hybrids
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_roots_MultiRoot_hybrids
  (JNIEnv *jvm, jobject MultiRoot_obj) {
	//****** from java side ********
	jint dim = jvm -> GetIntField(MultiRoot_obj, multiroot_dim_id) ;
	jdoubleArray initial_guess = (jdoubleArray) jvm -> GetObjectField(MultiRoot_obj, multiroot_initialGuess_id) ;
	jint print_info = jvm -> GetBooleanField(MultiRoot_obj, multiroot_printInfo_id) ;
	jdouble epsabs = jvm -> GetDoubleField(MultiRoot_obj, multiroot_abserr_id) ;
	jint max_num_iterations = jvm -> GetIntField(MultiRoot_obj, multiroot_max_num_iterations_id) ;
	jobject MultiRootFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_func_id) ;
	jparams p = {jvm, MultiRootFunction_obj} ;

	//******* c++ side ********
	jint iter = 0, status;
	const size_t n = dim ;
	gsl_multiroot_function func = {&f, n, &p};
	jdouble x_init[n] ;
	jvm -> GetDoubleArrayRegion(initial_guess, 0, n, x_init) ;
	gsl_vector* x = gsl_vector_alloc(n);
	for(size_t i=0; i<n; i++) {
		gsl_vector_set(x, i, x_init[i]);
	}
	// setup solver
	const gsl_multiroot_fsolver_type* T = gsl_multiroot_fsolver_hybrids;
	gsl_multiroot_fsolver* s = gsl_multiroot_fsolver_alloc(T, n);
	gsl_multiroot_fsolver_set(s, &func, x);

	if(print_info) {
		print_state(iter, s);
	}

	do {
		iter++;
		status = gsl_multiroot_fsolver_iterate(s);
		if(print_info) {
			print_state(iter, s);
		}
		if(status) /* check if solver is stuck */
			break;
//		status = gsl_multiroot_test_residual (s->f, 1e-7);
		status = gsl_multiroot_test_residual(s->f, epsabs);
	}
	while(status == GSL_CONTINUE && iter < max_num_iterations);

	if(print_info) {
		printf("status = %s\n", gsl_strerror(status));
		printf("status = %d\n", status);
	}

	// check if fail
	if(status!=0) {
		for(int i=0; i<dim; i++) {
			((s->x)->data)[i] = GSL_NAN ;
		}
	}

	// return double array
	jdoubleArray result = jvm -> NewDoubleArray(dim) ;
	jvm -> SetDoubleArrayRegion(result, 0, dim, (s->x)->data) ;

	// free solver
	gsl_multiroot_fsolver_free(s);
	gsl_vector_free(x);

	return result ;
}

/*
 * Class:     func4j_roots_MultiRoot
 * Method:    hybrid
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_roots_MultiRoot_hybrid
  (JNIEnv *jvm, jobject MultiRoot_obj) {
	//****** from java side ********
	jint dim = jvm -> GetIntField(MultiRoot_obj, multiroot_dim_id) ;
	jdoubleArray initial_guess = (jdoubleArray) jvm -> GetObjectField(MultiRoot_obj, multiroot_initialGuess_id) ;
	jint print_info = jvm -> GetBooleanField(MultiRoot_obj, multiroot_printInfo_id) ;
	jdouble epsabs = jvm -> GetDoubleField(MultiRoot_obj, multiroot_abserr_id) ;
	jint max_num_iterations = jvm -> GetIntField(MultiRoot_obj, multiroot_max_num_iterations_id) ;
	jobject MultiRootFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_func_id) ;
	jparams p = {jvm, MultiRootFunction_obj} ;

	//******* c++ side ********
	jint iter = 0, status;
	const size_t n = dim ;
	gsl_multiroot_function func = {&f, n, &p};
	jdouble x_init[n] ;
	jvm -> GetDoubleArrayRegion(initial_guess, 0, n, x_init) ;
	gsl_vector* x = gsl_vector_alloc(n);
	for(size_t i=0; i<n; i++) {
		gsl_vector_set(x, i, x_init[i]);
	}
	// setup solver
	const gsl_multiroot_fsolver_type* T = gsl_multiroot_fsolver_hybrid ;
	gsl_multiroot_fsolver* s = gsl_multiroot_fsolver_alloc(T, n);
	gsl_multiroot_fsolver_set(s, &func, x);

	if(print_info) {
		print_state(iter, s);
	}

	do {
		iter++;
		status = gsl_multiroot_fsolver_iterate(s);
		if(print_info) {
			print_state(iter, s);
		}
		if(status) /* check if solver is stuck */
			break;
//		status = gsl_multiroot_test_residual (s->f, 1e-7);
		status = gsl_multiroot_test_residual(s->f, epsabs);
	}
	while(status == GSL_CONTINUE && iter < max_num_iterations);

	if(print_info) {
		printf("status = %s\n", gsl_strerror(status));
		printf("status = %d\n", status);
	}

	// check if fail
	if(status!=0) {
		for(int i=0; i<dim; i++) {
			((s->x)->data)[i] = GSL_NAN ;
		}
	}

	// return double array
	jdoubleArray result = jvm -> NewDoubleArray(dim) ;
	jvm -> SetDoubleArrayRegion(result, 0, dim, (s->x)->data) ;

	// free solver
	gsl_multiroot_fsolver_free(s);
	gsl_vector_free(x);

	return result ;
}

/*
 * Class:     func4j_roots_MultiRoot
 * Method:    dnewton
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_roots_MultiRoot_dnewton
  (JNIEnv *jvm, jobject MultiRoot_obj) {
	//****** from java side ********
	jint dim = jvm -> GetIntField(MultiRoot_obj, multiroot_dim_id) ;
	jdoubleArray initial_guess = (jdoubleArray) jvm -> GetObjectField(MultiRoot_obj, multiroot_initialGuess_id) ;
	jint print_info = jvm -> GetBooleanField(MultiRoot_obj, multiroot_printInfo_id) ;
	jdouble epsabs = jvm -> GetDoubleField(MultiRoot_obj, multiroot_abserr_id) ;
	jint max_num_iterations = jvm -> GetIntField(MultiRoot_obj, multiroot_max_num_iterations_id) ;
	jobject MultiRootFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_func_id) ;
	jparams p = {jvm, MultiRootFunction_obj} ;

	//******* c++ side ********
	jint iter = 0, status;
	const size_t n = dim ;
	gsl_multiroot_function func = {&f, n, &p};
	jdouble x_init[n] ;
	jvm -> GetDoubleArrayRegion(initial_guess, 0, n, x_init) ;
	gsl_vector* x = gsl_vector_alloc(n);
	for(size_t i=0; i<n; i++) {
		gsl_vector_set(x, i, x_init[i]);
	}
	// setup solver
	const gsl_multiroot_fsolver_type* T = gsl_multiroot_fsolver_dnewton ;
	gsl_multiroot_fsolver* s = gsl_multiroot_fsolver_alloc(T, n);
	gsl_multiroot_fsolver_set(s, &func, x);

	if(print_info) {
		print_state(iter, s);
	}

	do {
		iter++;
		status = gsl_multiroot_fsolver_iterate(s);
		if(print_info) {
			print_state(iter, s);
		}
		if(status) /* check if solver is stuck */
			break;
//		status = gsl_multiroot_test_residual (s->f, 1e-7);
		status = gsl_multiroot_test_residual(s->f, epsabs);
	}
	while(status == GSL_CONTINUE && iter < max_num_iterations);

	if(print_info) {
		printf("status = %s\n", gsl_strerror(status));
		printf("status = %d\n", status);
	}

	// check if fail
	if(status!=0) {
		for(int i=0; i<dim; i++) {
			((s->x)->data)[i] = GSL_NAN ;
		}
	}

	// return double array
	jdoubleArray result = jvm -> NewDoubleArray(dim) ;
	jvm -> SetDoubleArrayRegion(result, 0, dim, (s->x)->data) ;

	// free solver
	gsl_multiroot_fsolver_free(s);
	gsl_vector_free(x);

	return result ;
}

/*
 * Class:     func4j_roots_MultiRoot
 * Method:    broyden
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_roots_MultiRoot_broyden
  (JNIEnv *jvm, jobject MultiRoot_obj) {
	//****** from java side ********
	jint dim = jvm -> GetIntField(MultiRoot_obj, multiroot_dim_id) ;
	jdoubleArray initial_guess = (jdoubleArray) jvm -> GetObjectField(MultiRoot_obj, multiroot_initialGuess_id) ;
	jint print_info = jvm -> GetBooleanField(MultiRoot_obj, multiroot_printInfo_id) ;
	jdouble epsabs = jvm -> GetDoubleField(MultiRoot_obj, multiroot_abserr_id) ;
	jint max_num_iterations = jvm -> GetIntField(MultiRoot_obj, multiroot_max_num_iterations_id) ;
	jobject MultiRootFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_func_id) ;
	jparams p = {jvm, MultiRootFunction_obj} ;

	//******* c++ side ********
	jint iter = 0, status;
	const size_t n = dim ;
	gsl_multiroot_function func = {&f, n, &p};
	jdouble x_init[n] ;
	jvm -> GetDoubleArrayRegion(initial_guess, 0, n, x_init) ;
	gsl_vector* x = gsl_vector_alloc(n);
	for(size_t i=0; i<n; i++) {
		gsl_vector_set(x, i, x_init[i]);
	}
	// setup solver
	const gsl_multiroot_fsolver_type* T = gsl_multiroot_fsolver_broyden ;
	gsl_multiroot_fsolver* s = gsl_multiroot_fsolver_alloc(T, n);
	gsl_multiroot_fsolver_set(s, &func, x);

	if(print_info) {
		print_state(iter, s);
	}

	do {
		iter++;
		status = gsl_multiroot_fsolver_iterate(s);
		if(print_info) {
			print_state(iter, s);
		}
		if(status) /* check if solver is stuck */
			break;
//		status = gsl_multiroot_test_residual (s->f, 1e-7);
		status = gsl_multiroot_test_residual(s->f, epsabs);
	}
	while(status == GSL_CONTINUE && iter < max_num_iterations);

	if(print_info) {
		printf("status = %s\n", gsl_strerror(status));
		printf("status = %d\n", status);
	}

	// check if fail
	if(status!=0) {
		for(int i=0; i<dim; i++) {
			((s->x)->data)[i] = GSL_NAN ;
		}
	}

	// return double array
	jdoubleArray result = jvm -> NewDoubleArray(dim) ;
	jvm -> SetDoubleArrayRegion(result, 0, dim, (s->x)->data) ;

	// free solver
	gsl_multiroot_fsolver_free(s);
	gsl_vector_free(x);

	return result ;
}


