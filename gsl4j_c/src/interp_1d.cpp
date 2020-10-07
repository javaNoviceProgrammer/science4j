/*
 * interp_1d.cpp
 *
 *  Created on: Jun 23, 2020
 *      Author: meisam
 */

#include <gsl/gsl_spline.h>
#include "../headers/org_gsl4j_interpolate_Interpolation1D.h"

jclass interp1d_class ;
jfieldID type1d_id ;
jfieldID xid_1d ;
jfieldID yid_1d ;

class Interpolator1D {

public:
	// defining global variables
	jdouble *xa_interp1d ;
	jdouble *ya_interp1d ;
	jint len_interp1d ;
	jint type ;
	gsl_interp_accel *acc_interp1d ;
	const gsl_interp_type *t_interp1d ;
	gsl_spline *spline_interp1d ;

public:
	Interpolator1D(JNIEnv *env, jobject Interpolation1D) {
		type = env -> GetIntField(Interpolation1D, type1d_id) ;
		jobject jxobj = env -> GetObjectField(Interpolation1D, xid_1d) ;
		jdoubleArray jxarray = (jdoubleArray) jxobj ;
		jobject jyobj = env -> GetObjectField(Interpolation1D, yid_1d) ;
		jdoubleArray jyarray = (jdoubleArray) jyobj ;
		// create c++ arrays
		len_interp1d = env -> GetArrayLength(jxarray) ;
		xa_interp1d = new jdouble[len_interp1d] ;
		env -> GetDoubleArrayRegion(jxarray, 0, len_interp1d, xa_interp1d) ;
		ya_interp1d = new jdouble[len_interp1d] ;
		env -> GetDoubleArrayRegion(jyarray, 0, len_interp1d, ya_interp1d) ;
		// prepare interpolation
		acc_interp1d = gsl_interp_accel_alloc ();
		if(type == org_gsl4j_interpolate_Interpolation1D_LINEAR)
			t_interp1d = gsl_interp_linear ;
		else if(type == org_gsl4j_interpolate_Interpolation1D_POLYNOMIAL)
			t_interp1d = gsl_interp_polynomial ;
		else if(type == org_gsl4j_interpolate_Interpolation1D_CUBIC_SPLINE)
			t_interp1d = gsl_interp_cspline ;
		else if(type == org_gsl4j_interpolate_Interpolation1D_CUBIC_SPLINE_PERIODIC)
			t_interp1d = gsl_interp_cspline_periodic ;
		else if(type == org_gsl4j_interpolate_Interpolation1D_AKIMA)
			t_interp1d = gsl_interp_akima ;
		else if(type == org_gsl4j_interpolate_Interpolation1D_AKIMA_PERIODIC)
			t_interp1d = gsl_interp_akima_periodic ;
		else
			t_interp1d = gsl_interp_steffen ;
		spline_interp1d = gsl_spline_alloc(t_interp1d, len_interp1d);
		gsl_spline_init(spline_interp1d, xa_interp1d, ya_interp1d, len_interp1d) ;
	}

	~Interpolator1D() {
		gsl_spline_free(spline_interp1d);
		gsl_interp_accel_free(acc_interp1d);
		delete[] xa_interp1d ;
		delete[] ya_interp1d ;
//		printf("%s", "interpolator deleted\n") ;
	}

};


/*
 * Class:     org_gsl4j_interpolate_Interpolation1D
 * Method:    cacheData
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_interpolate_Interpolation1D_cacheData
(JNIEnv *env, jobject Interpolation1D, jint type) {
	// get type from java class
	interp1d_class = env -> GetObjectClass(Interpolation1D) ;
	type1d_id = env -> GetFieldID(interp1d_class, "type", "I") ;
	// get x[] and y[] from java class
	xid_1d = env -> GetFieldID(interp1d_class, "x", "[D") ;
	yid_1d = env -> GetFieldID(interp1d_class, "y", "[D") ;
}


/*
 * Class:     org_gsl4j_interpolate_Interpolation1D
 * Method:    name
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_gsl4j_interpolate_Interpolation1D_name
(JNIEnv *env, jobject Interpolation1D) {
	Interpolator1D *interp = new Interpolator1D(env, Interpolation1D) ;
	jstring jname = env -> NewStringUTF(gsl_spline_name((*interp).spline_interp1d)) ;
	delete interp ;
	return jname ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation1D
 * Method:    minSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_gsl4j_interpolate_Interpolation1D_minSize
(JNIEnv *env, jobject Interpolation1D) {
	Interpolator1D *interp = new Interpolator1D(env, Interpolation1D) ;
	jint jmin_size = gsl_spline_min_size(interp->spline_interp1d) ;
	delete interp ;
	return jmin_size ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation1D
 * Method:    eval
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_interpolate_Interpolation1D_eval__D
(JNIEnv *env, jobject Interpolation1D, jdouble x) {
	Interpolator1D *interp = new Interpolator1D(env, Interpolation1D) ;
	jdouble jresult = gsl_spline_eval(interp->spline_interp1d, x, interp->acc_interp1d) ;
	delete interp ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation1D
 * Method:    eval
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_interpolate_Interpolation1D_eval___3D
  (JNIEnv *env, jobject Interpolation1D, jdoubleArray x) {
	Interpolator1D *interp = new Interpolator1D(env, Interpolation1D) ;
	jint len = env -> GetArrayLength(x) ;
	jdouble *result = new jdouble[len] ;
	jdouble *x_array = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x, 0, len, x_array) ;
	for(int i=0; i<len; i++)
		result[i] = gsl_spline_eval(interp->spline_interp1d, x_array[i], interp->acc_interp1d) ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] result ;
	delete[] x_array ;
	delete interp ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation1D
 * Method:    deriv
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_interpolate_Interpolation1D_deriv__D
(JNIEnv *env, jobject Interpolation1D, jdouble x) {
	Interpolator1D *interp = new Interpolator1D(env, Interpolation1D) ;
	jdouble jresult = gsl_spline_eval_deriv(interp->spline_interp1d, x, interp->acc_interp1d) ;
	delete interp ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation1D
 * Method:    deriv
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_interpolate_Interpolation1D_deriv___3D
  (JNIEnv *env, jobject Interpolation1D, jdoubleArray x) {
	Interpolator1D *interp = new Interpolator1D(env, Interpolation1D) ;
	jint len = env -> GetArrayLength(x) ;
	jdouble *result = new jdouble[len] ;
	jdouble *x_array = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x, 0, len, x_array) ;
	for(int i=0; i<len; i++)
		result[i] = gsl_spline_eval_deriv(interp->spline_interp1d, x_array[i], interp->acc_interp1d) ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] result ;
	delete[] x_array ;
	delete interp ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation1D
 * Method:    deriv2
 * Signature: (D)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_interpolate_Interpolation1D_deriv2__D
(JNIEnv *env, jobject Interpolation1D, jdouble x) {
	Interpolator1D *interp = new Interpolator1D(env, Interpolation1D) ;
	jdouble jresult = gsl_spline_eval_deriv2(interp->spline_interp1d, x, interp->acc_interp1d) ;
	delete interp ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation1D
 * Method:    deriv2
 * Signature: ([D)[D
 */
JNIEXPORT jdoubleArray JNICALL Java_org_gsl4j_interpolate_Interpolation1D_deriv2___3D
  (JNIEnv *env, jobject Interpolation1D, jdoubleArray x) {
	Interpolator1D *interp = new Interpolator1D(env, Interpolation1D) ;
	jint len = env -> GetArrayLength(x) ;
	jdouble *result = new jdouble[len] ;
	jdouble *x_array = new jdouble[len] ;
	env -> GetDoubleArrayRegion(x, 0, len, x_array) ;
	for(int i=0; i<len; i++)
		result[i] = gsl_spline_eval_deriv2(interp->spline_interp1d, x_array[i], interp->acc_interp1d) ;
	jdoubleArray jresult = env -> NewDoubleArray(len) ;
	env -> SetDoubleArrayRegion(jresult, 0, len, result) ;
	delete[] result ;
	delete[] x_array ;
	delete interp ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation1D
 * Method:    integrate
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_interpolate_Interpolation1D_integrate
(JNIEnv *env, jobject Interpolation1D, jdouble a, jdouble b) {
	Interpolator1D *interp = new Interpolator1D(env, Interpolation1D) ;
	jdouble jresult = gsl_spline_eval_integ(interp->spline_interp1d, a, b, interp->acc_interp1d) ;
	delete interp ;
	return jresult ;
}



