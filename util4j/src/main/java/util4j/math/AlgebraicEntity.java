package util4j.math;

/**
 * Interface for defining a math entity with algebraic operations
 *
 * @author Meisam
 * @since 1.0
 * @param <T> Class subject to algebraic operations
 */
public interface AlgebraicEntity<T extends AlgebraicEntity<T>> {
	
	/*-------- identity ----------*/
	
//	T zero() ; // addition & subtraction identity
//	T one() ;  // multiplication identity

	/*-------- addition ----------*/

	/**
	 * Addition of a double number to the algebraic entity: this + v
	 * @param v double value
	 * @return type T
	 */
	T add(double v) ; // this + v

	/**
	 * Reverse addition of a double number to the algebraic entity: v + this
	 * @param v double value
	 * @return type T
	 */
//	T addRev(double v) ; // v + this
	default T addRev(double v) { // v + this
		return add(v) ;
	}

	/**
	 * Addition of a type T (algebraic entity) to the algebraic entity: this + v
	 * @param v double value
	 * @return type T
	 */
	T add(T v) ; // this + v

	/**
	 * Reverse addition of a type T (algebraic entity) to the algebraic entity: v + this
	 * @param v double value
	 * @return type T
	 */
//	T addRev(T v) ; // v + this
	default T addRev(T v) { // v + this
		return add(v) ;
	}
	
	default T plus(T v) {
		return add(v) ;
	}
	
	default T plus(double v) {
		return add(v) ;
	}

	/*-------- subtraction ----------*/

	T subtract(double v) ; // this - v
	
//	T subtractRev(double v) ; // v - this
	default T subtractRev(double v) {
		return multiply(-1.0).add(v) ;
	}
	
	T subtract(T v) ;
	
//	T subtractRev(T v) ;
	
	default T subtractRev(T v) {
		return multiply(-1.0).add(v) ;
	}
	
	default T minus(double v) {
		return subtract(v) ;
	}
	
	default T minus(T v) {
		return subtract(v) ;
	}

	/*-------- multiplication ----------*/

	T multiply(double v) ;
	
//	T multiplyRev(double v) ;
	default T multiplyRev(double v) {
		return multiply(v) ;
	}
	
	T multiply(T v) ;
	
//	T multiplyRev(T v) ;
	default T multiplyRev(T v) {
		return multiply(v) ;
	}
	
	default T times(double v) {
		return multiply(v) ;
	}
	
	default T times(T v) {
		return multiply(v) ;
	}
	
	default T mul(double v) {
		return multiply(v) ;
	}
	
	default T mul(T v) {
		return multiply(v) ;
	}

	/*-------- division ----------*/

	T divide(double v) ;
	
	T divideRev(double v) ;
	
	T divide(T v) ;
	
	T divideRev(T v) ;
	
	default T over(double v) {
		return divide(v) ;
	}
	
	default T over(T v) {
		return divide(v) ;
	}

	/*-------- negation ----------*/

	T negate() ;

}
