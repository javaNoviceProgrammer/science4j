/*
 * interp_2d.cpp
 *
 *  Created on: Jun 24, 2020
 *      Author: meisam
 */

#include <gsl/gsl_spline2d.h>
#include "../headers/org_gsl4j_interpolate_Interpolation2D.h"

jclass interp2d_class ;
jfieldID type2d_id ;
jfieldID xid_2d ;
jfieldID yid_2d ;
jfieldID zid_2d ;


class Interpolator2D {

public:
	// defining global variables
	jdouble *xa_interp2d ;
	jdouble *ya_interp2d ;
	jdouble *za_interp2d ;
	jint len_x_interp2d ;
	jint len_y_interp2d ;
	jint len_z_interp2d ;
	jint type ;
	gsl_interp_accel *xacc_interp2d ;
	gsl_interp_accel *yacc_interp2d ;
	gsl_interp_accel *zacc_interp2d ;
	const gsl_interp2d_type *t_interp2d ;
	gsl_spline2d *spline_interp2d ;

public:
	Interpolator2D(JNIEnv *env, jobject Interpolation2D) {
		type = env -> GetIntField(Interpolation2D, type2d_id) ;
		jobject jxobj = env -> GetObjectField(Interpolation2D, xid_2d) ;
		jdoubleArray jxarray = (jdoubleArray) jxobj ;
		jobject jyobj = env -> GetObjectField(Interpolation2D, yid_2d) ;
		jdoubleArray jyarray = (jdoubleArray) jyobj ;
		jobject jzobj = env -> GetObjectField(Interpolation2D, zid_2d) ;
		jdoubleArray jzarray = (jdoubleArray) jzobj ;
		// create c++ arrays
		len_x_interp2d = env -> GetArrayLength(jxarray) ;
		xa_interp2d = new jdouble[len_x_interp2d] ;
		env -> GetDoubleArrayRegion(jxarray, 0, len_x_interp2d, xa_interp2d) ;
		len_y_interp2d = env -> GetArrayLength(jyarray) ;
		ya_interp2d = new jdouble[len_y_interp2d] ;
		env -> GetDoubleArrayRegion(jyarray, 0, len_y_interp2d, ya_interp2d) ;
		len_z_interp2d = env -> GetArrayLength(jzarray) ;
		za_interp2d = new jdouble[len_z_interp2d] ;
		// prepare interpolation
		xacc_interp2d = gsl_interp_accel_alloc ();
		yacc_interp2d = gsl_interp_accel_alloc ();
		zacc_interp2d = gsl_interp_accel_alloc ();
		if(type == org_gsl4j_interpolate_Interpolation2D_BILINEAR)
			t_interp2d = gsl_interp2d_bilinear ;
		else
			t_interp2d = gsl_interp2d_bicubic ;
		spline_interp2d = gsl_spline2d_alloc(t_interp2d, len_x_interp2d, len_y_interp2d) ;

		jdouble *za = new jdouble[len_z_interp2d] ;
		env -> GetDoubleArrayRegion(jzarray, 0, len_z_interp2d, za) ;
		for(int i=0; i<len_x_interp2d; i++)
			for(int j=0; j<len_y_interp2d; j++) {
				gsl_spline2d_set(spline_interp2d, za_interp2d, i, j, za[i*len_y_interp2d+j]) ;
			}
		delete[] za ;

		gsl_spline2d_init(spline_interp2d, xa_interp2d, ya_interp2d, za_interp2d, len_x_interp2d, len_y_interp2d) ;
	}

	~Interpolator2D() {
		gsl_spline2d_free(spline_interp2d);
		gsl_interp_accel_free(xacc_interp2d);
		gsl_interp_accel_free(yacc_interp2d);
		gsl_interp_accel_free(zacc_interp2d);
		delete[] xa_interp2d ;
		delete[] ya_interp2d ;
		delete[] za_interp2d ;
//		printf("%s", "interpolator2D deleted\n") ;
	}

};


/*
 * Class:     org_gsl4j_interpolate_Interpolation2D
 * Method:    cacheData
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_gsl4j_interpolate_Interpolation2D_cacheData
  (JNIEnv *env, jobject Interpolation2D, jint type) {
	// get type from java class
	interp2d_class = env -> GetObjectClass(Interpolation2D) ;
	type2d_id = env -> GetFieldID(interp2d_class, "type", "I") ;
	// get x[] and y[] from java class
	xid_2d = env -> GetFieldID(interp2d_class, "x", "[D") ;
	yid_2d = env -> GetFieldID(interp2d_class, "y", "[D") ;
	zid_2d = env -> GetFieldID(interp2d_class, "z", "[D") ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation2D
 * Method:    name
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_org_gsl4j_interpolate_Interpolation2D_name
  (JNIEnv *env, jobject Interpolation2D) {
	Interpolator2D *interp = new Interpolator2D(env, Interpolation2D) ;
	jstring jname = env -> NewStringUTF(gsl_spline2d_name((*interp).spline_interp2d)) ;
	delete interp ;
	return jname ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation2D
 * Method:    minSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_gsl4j_interpolate_Interpolation2D_minSize
  (JNIEnv *env, jobject Interpolation2D) {
	Interpolator2D *interp = new Interpolator2D(env, Interpolation2D) ;
	jint jmin_size = gsl_spline2d_min_size(interp->spline_interp2d) ;
	delete interp ;
	return jmin_size ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation2D
 * Method:    eval
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_interpolate_Interpolation2D_eval
  (JNIEnv *env, jobject Interpolation2D, jdouble x, jdouble y) {
	Interpolator2D *interp = new Interpolator2D(env, Interpolation2D) ;
	jdouble jresult = gsl_spline2d_eval(interp->spline_interp2d, x, y, interp->xacc_interp2d, interp->yacc_interp2d) ;
	delete interp ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation2D
 * Method:    derivX
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_interpolate_Interpolation2D_derivX
  (JNIEnv *env, jobject Interpolation2D, jdouble x, jdouble y) {
	Interpolator2D *interp = new Interpolator2D(env, Interpolation2D) ;
	jdouble jresult = gsl_spline2d_eval_deriv_x(interp->spline_interp2d, x, y, interp->xacc_interp2d, interp->yacc_interp2d) ;
	delete interp ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation2D
 * Method:    derivY
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_interpolate_Interpolation2D_derivY
  (JNIEnv *env, jobject Interpolation2D, jdouble x, jdouble y) {
	Interpolator2D *interp = new Interpolator2D(env, Interpolation2D) ;
	jdouble jresult = gsl_spline2d_eval_deriv_y(interp->spline_interp2d, x, y, interp->xacc_interp2d, interp->yacc_interp2d) ;
	delete interp ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation2D
 * Method:    derivXX
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_interpolate_Interpolation2D_derivXX
  (JNIEnv *env, jobject Interpolation2D, jdouble x, jdouble y) {
	Interpolator2D *interp = new Interpolator2D(env, Interpolation2D) ;
	jdouble jresult = gsl_spline2d_eval_deriv_xx(interp->spline_interp2d, x, y, interp->xacc_interp2d, interp->yacc_interp2d) ;
	delete interp ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation2D
 * Method:    derivYY
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_interpolate_Interpolation2D_derivYY
  (JNIEnv *env, jobject Interpolation2D, jdouble x, jdouble y) {
	Interpolator2D *interp = new Interpolator2D(env, Interpolation2D) ;
	jdouble jresult = gsl_spline2d_eval_deriv_yy(interp->spline_interp2d, x, y, interp->xacc_interp2d, interp->yacc_interp2d) ;
	delete interp ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation2D
 * Method:    derivXY
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_interpolate_Interpolation2D_derivXY
  (JNIEnv *env, jobject Interpolation2D, jdouble x, jdouble y) {
	Interpolator2D *interp = new Interpolator2D(env, Interpolation2D) ;
	jdouble jresult = gsl_spline2d_eval_deriv_xy(interp->spline_interp2d, x, y, interp->xacc_interp2d, interp->yacc_interp2d) ;
	delete interp ;
	return jresult ;
}

/*
 * Class:     org_gsl4j_interpolate_Interpolation2D
 * Method:    derivYX
 * Signature: (DD)D
 */
JNIEXPORT jdouble JNICALL Java_org_gsl4j_interpolate_Interpolation2D_derivYX
  (JNIEnv *env, jobject Interpolation2D, jdouble x, jdouble y) {
	Interpolator2D *interp = new Interpolator2D(env, Interpolation2D) ;
	jdouble jresult = gsl_spline2d_eval_deriv_xy(interp->spline_interp2d, x, y, interp->xacc_interp2d, interp->yacc_interp2d) ;
	delete interp ;
	return jresult ;
}



