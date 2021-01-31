/*
 * func4j_roots_MultiRoot.cpp
 *
 *  Created on: Jan 27, 2021
 *      Author: meisam
 */

#include "../headers/func4j_roots_MultiRoot.h"
#include <gsl/gsl_multiroots.h>
#include <gsl/gsl_vector.h>
#include <iostream>
#include <iomanip>


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
		jobject MultiRootDerivFunction_obj ;
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

	//***** df function *******

	int df(const gsl_vector* x, void* params, gsl_matrix * J) {

		jparams param = *((jparams*) params) ;

		// x is input: x = [x1, x2, ...]
		jint len = (jint) x->size ;
		jdoubleArray x_vals = param.jvm -> NewDoubleArray(len) ;
		param.jvm -> SetDoubleArrayRegion(x_vals, 0, len, x->data) ;

		// y is output: y = Jacobian --> y_ij = df_i/dx_j --> 2D array
		jobjectArray y = (jobjectArray) param.jvm -> CallObjectMethod(param.MultiRootDerivFunction_obj, multiroot_derivfunc_value_id, x_vals) ;

		// set the vector
		for(int i=0; i<len; i++) {
			jdoubleArray y_vals = (jdoubleArray) param.jvm -> GetObjectArrayElement(y, i) ;
			jdouble* y_data = (jdouble*) param.jvm -> GetPrimitiveArrayCritical(y_vals, 0) ;
			for(int j=0; j<len; j++) {
				gsl_matrix_set(J, (size_t) i, (size_t) j, y_data[j]) ;
			}
			param.jvm -> ReleasePrimitiveArrayCritical(y_vals, y_data, 0) ;

		}

		return GSL_SUCCESS ;
	}

	//***** fdf function *******

	int fdf(const gsl_vector * x, void *params, gsl_vector * y, gsl_matrix * J) {
		f(x, params, y);
		df(x, params, J);
		return GSL_SUCCESS;
	}

	//***** print iteration status *******

	void print_state(jint iter, gsl_multiroot_fsolver *s, size_t dim) {
		std::cout << "iter = " << iter
				  << "  x = ";
		for(size_t i=0; i<dim; i++) {
			std::cout << s->x->data[i] << " " ;
		}
		std::cout << " f(x) = " ;
		for(size_t i=0; i<dim; i++) {
			std::cout << s->f->data[i] << " " ;
		}
		std::cout << '\n' ;
	}

	void print_state_fdf(jint iter, gsl_multiroot_fdfsolver *s, size_t dim) {
		std::cout << "iter = " << iter
				  << "  x = ";
		for(size_t i=0; i<dim; i++) {
			std::cout << s->x->data[i] << " " ;
		}
		std::cout << " f(x) = " ;
		for(size_t i=0; i<dim; i++) {
			std::cout << s->f->data[i] << " " ;
		}
		std::cout << '\n' ;
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
  (JNIEnv *jvm, jobject MultiRoot_obj) {

	//****** from java side ********
	jint dim = jvm -> GetIntField(MultiRoot_obj, multiroot_dim_id) ;
	jdoubleArray initial_guess = (jdoubleArray) jvm -> GetObjectField(MultiRoot_obj, multiroot_initialGuess_id) ;
	jint print_info = jvm -> GetBooleanField(MultiRoot_obj, multiroot_printInfo_id) ;
	jdouble epsabs = jvm -> GetDoubleField(MultiRoot_obj, multiroot_abserr_id) ;
	jint max_num_iterations = jvm -> GetIntField(MultiRoot_obj, multiroot_max_num_iterations_id) ;
	jobject MultiRootFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_func_id) ;
	jobject MultiRootDerivFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_derivfunc_id) ;
	jparams p = {jvm, MultiRootFunction_obj, MultiRootDerivFunction_obj} ;

	//******* c++ side ********
	jint iter = 0, status;
	const size_t n = dim ;
	gsl_multiroot_function_fdf func = {&f, &df, &fdf, n, &p};
	jdouble x_init[n] ;
	jvm -> GetDoubleArrayRegion(initial_guess, 0, dim, x_init) ;
	gsl_vector* x = gsl_vector_alloc(n);
	for(size_t i=0; i<n; i++) {
		gsl_vector_set(x, i, x_init[i]);
	}
	// setup solver
	const gsl_multiroot_fdfsolver_type* T = gsl_multiroot_fdfsolver_hybridsj ;
	gsl_multiroot_fdfsolver* s = gsl_multiroot_fdfsolver_alloc(T, n);
	gsl_multiroot_fdfsolver_set(s, &func, x);

	if(print_info) {
		print_state_fdf(iter, s, n);
	}

	do {
		iter++;
		status = gsl_multiroot_fdfsolver_iterate(s);
		if(print_info) {
			print_state_fdf(iter, s, n);
		}
		if(status) /* check if solver is stuck */
			break;
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
	gsl_multiroot_fdfsolver_free(s);
	gsl_vector_free(x);

	return result ;
}

/*
 * Class:     func4j_roots_MultiRoot
 * Method:    hybridj
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_roots_MultiRoot_hybridj
  (JNIEnv *jvm, jobject MultiRoot_obj) {
	//****** from java side ********
	jint dim = jvm -> GetIntField(MultiRoot_obj, multiroot_dim_id) ;
	jdoubleArray initial_guess = (jdoubleArray) jvm -> GetObjectField(MultiRoot_obj, multiroot_initialGuess_id) ;
	jint print_info = jvm -> GetBooleanField(MultiRoot_obj, multiroot_printInfo_id) ;
	jdouble epsabs = jvm -> GetDoubleField(MultiRoot_obj, multiroot_abserr_id) ;
	jint max_num_iterations = jvm -> GetIntField(MultiRoot_obj, multiroot_max_num_iterations_id) ;
	jobject MultiRootFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_func_id) ;
	jobject MultiRootDerivFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_derivfunc_id) ;
	jparams p = {jvm, MultiRootFunction_obj, MultiRootDerivFunction_obj} ;

	//******* c++ side ********
	jint iter = 0, status;
	const size_t n = dim ;
	gsl_multiroot_function_fdf func = {&f, &df, &fdf, n, &p};
	jdouble x_init[n] ;
	jvm -> GetDoubleArrayRegion(initial_guess, 0, dim, x_init) ;
	gsl_vector* x = gsl_vector_alloc(n);
	for(size_t i=0; i<n; i++) {
		gsl_vector_set(x, i, x_init[i]);
	}
	// setup solver
	const gsl_multiroot_fdfsolver_type* T = gsl_multiroot_fdfsolver_hybridj ;
	gsl_multiroot_fdfsolver* s = gsl_multiroot_fdfsolver_alloc(T, n);
	gsl_multiroot_fdfsolver_set(s, &func, x);

	if(print_info) {
		print_state_fdf(iter, s, n);
	}

	do {
		iter++;
		status = gsl_multiroot_fdfsolver_iterate(s);
		if(print_info) {
			print_state_fdf(iter, s, n);
		}
		if(status) /* check if solver is stuck */
			break;
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
	gsl_multiroot_fdfsolver_free(s);
	gsl_vector_free(x);

	return result ;
}

/*
 * Class:     func4j_roots_MultiRoot
 * Method:    newton
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_roots_MultiRoot_newton
  (JNIEnv *jvm, jobject MultiRoot_obj) {
	//****** from java side ********
	jint dim = jvm -> GetIntField(MultiRoot_obj, multiroot_dim_id) ;
	jdoubleArray initial_guess = (jdoubleArray) jvm -> GetObjectField(MultiRoot_obj, multiroot_initialGuess_id) ;
	jint print_info = jvm -> GetBooleanField(MultiRoot_obj, multiroot_printInfo_id) ;
	jdouble epsabs = jvm -> GetDoubleField(MultiRoot_obj, multiroot_abserr_id) ;
	jint max_num_iterations = jvm -> GetIntField(MultiRoot_obj, multiroot_max_num_iterations_id) ;
	jobject MultiRootFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_func_id) ;
	jobject MultiRootDerivFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_derivfunc_id) ;
	jparams p = {jvm, MultiRootFunction_obj, MultiRootDerivFunction_obj} ;

	//******* c++ side ********
	jint iter = 0, status;
	const size_t n = dim ;
	gsl_multiroot_function_fdf func = {&f, &df, &fdf, n, &p};
	jdouble x_init[n] ;
	jvm -> GetDoubleArrayRegion(initial_guess, 0, dim, x_init) ;
	gsl_vector* x = gsl_vector_alloc(n);
	for(size_t i=0; i<n; i++) {
		gsl_vector_set(x, i, x_init[i]);
	}
	// setup solver
	const gsl_multiroot_fdfsolver_type* T = gsl_multiroot_fdfsolver_newton ;
	gsl_multiroot_fdfsolver* s = gsl_multiroot_fdfsolver_alloc(T, n);
	gsl_multiroot_fdfsolver_set(s, &func, x);

	if(print_info) {
		print_state_fdf(iter, s, n);
	}

	do {
		iter++;
		status = gsl_multiroot_fdfsolver_iterate(s);
		if(print_info) {
			print_state_fdf(iter, s, n);
		}
		if(status) /* check if solver is stuck */
			break;
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
	gsl_multiroot_fdfsolver_free(s);
	gsl_vector_free(x);

	return result ;
}

/*
 * Class:     func4j_roots_MultiRoot
 * Method:    gnewton
 * Signature: ()[D
 */
JNIEXPORT jdoubleArray JNICALL Java_func4j_roots_MultiRoot_gnewton
  (JNIEnv *jvm, jobject MultiRoot_obj) {
	//****** from java side ********
	jint dim = jvm -> GetIntField(MultiRoot_obj, multiroot_dim_id) ;
	jdoubleArray initial_guess = (jdoubleArray) jvm -> GetObjectField(MultiRoot_obj, multiroot_initialGuess_id) ;
	jint print_info = jvm -> GetBooleanField(MultiRoot_obj, multiroot_printInfo_id) ;
	jdouble epsabs = jvm -> GetDoubleField(MultiRoot_obj, multiroot_abserr_id) ;
	jint max_num_iterations = jvm -> GetIntField(MultiRoot_obj, multiroot_max_num_iterations_id) ;
	jobject MultiRootFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_func_id) ;
	jobject MultiRootDerivFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_derivfunc_id) ;
	jparams p = {jvm, MultiRootFunction_obj, MultiRootDerivFunction_obj} ;

	//******* c++ side ********
	jint iter = 0, status;
	const size_t n = dim ;
	gsl_multiroot_function_fdf func = {&f, &df, &fdf, n, &p};
	jdouble x_init[n] ;
	jvm -> GetDoubleArrayRegion(initial_guess, 0, dim, x_init) ;
	gsl_vector* x = gsl_vector_alloc(n);
	for(size_t i=0; i<n; i++) {
		gsl_vector_set(x, i, x_init[i]);
	}
	// setup solver
	const gsl_multiroot_fdfsolver_type* T = gsl_multiroot_fdfsolver_gnewton ;
	gsl_multiroot_fdfsolver* s = gsl_multiroot_fdfsolver_alloc(T, n);
	gsl_multiroot_fdfsolver_set(s, &func, x);

	if(print_info) {
		print_state_fdf(iter, s, n);
	}

	do {
		iter++;
		status = gsl_multiroot_fdfsolver_iterate(s);
		if(print_info) {
			print_state_fdf(iter, s, n);
		}
		if(status) /* check if solver is stuck */
			break;
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
	gsl_multiroot_fdfsolver_free(s);
	gsl_vector_free(x);

	return result ;
}

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
	jobject MultiRootDerivFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_derivfunc_id) ;
	jparams p = {jvm, MultiRootFunction_obj, MultiRootDerivFunction_obj} ;

	//******* c++ side ********
	jint iter = 0, status;
	const size_t n = dim ;
	gsl_multiroot_function func = {&f, n, &p};
	jdouble x_init[n] ;
	jvm -> GetDoubleArrayRegion(initial_guess, 0, dim, x_init) ;
	gsl_vector* x = gsl_vector_alloc(n);
	for(size_t i=0; i<n; i++) {
		gsl_vector_set(x, i, x_init[i]);
	}
	// setup solver
	const gsl_multiroot_fsolver_type* T = gsl_multiroot_fsolver_hybrids;
	gsl_multiroot_fsolver* s = gsl_multiroot_fsolver_alloc(T, n);
	gsl_multiroot_fsolver_set(s, &func, x);

	if(print_info) {
		print_state(iter, s, n);
	}

	do {
		iter++;
		status = gsl_multiroot_fsolver_iterate(s);
		if(print_info) {
			print_state(iter, s, n);
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
	jobject MultiRootDerivFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_derivfunc_id) ;
	jparams p = {jvm, MultiRootFunction_obj, MultiRootDerivFunction_obj} ;

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
		print_state(iter, s, n);
	}

	do {
		iter++;
		status = gsl_multiroot_fsolver_iterate(s);
		if(print_info) {
			print_state(iter, s, n);
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
	jobject MultiRootDerivFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_derivfunc_id) ;
	jparams p = {jvm, MultiRootFunction_obj, MultiRootDerivFunction_obj} ;

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
		print_state(iter, s, n);
	}

	do {
		iter++;
		status = gsl_multiroot_fsolver_iterate(s);
		if(print_info) {
			print_state(iter, s, n);
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
	jobject MultiRootDerivFunction_obj = jvm -> GetObjectField(MultiRoot_obj, multiroot_derivfunc_id) ;
	jparams p = {jvm, MultiRootFunction_obj, MultiRootDerivFunction_obj} ;

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
		print_state(iter, s, n);
	}

	do {
		iter++;
		status = gsl_multiroot_fsolver_iterate(s);
		if(print_info) {
			print_state(iter, s, n);
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


