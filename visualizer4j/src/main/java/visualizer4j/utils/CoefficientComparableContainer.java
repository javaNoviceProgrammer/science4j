package visualizer4j.utils;

import java.util.Comparator;


public class CoefficientComparableContainer<T, K extends Comparable<K>> implements Comparable<CoefficientComparableContainer<T, K>> {

	private K coeff;

	private T content;


	/**
	 * Method compareTo
	 *
	 *
	 * @param o
	 *
	 * @return
	 *
	 */
	public int compareTo(CoefficientComparableContainer<T, K> c) {

		double tt = this.coeff.compareTo(c.coeff);
		if (tt < 0) {
			return -1;
		} else if (tt > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public Comparator<CoefficientComparableContainer<T, K>> getParticularComparator(final Comparator<K> comp) {
		return new Comparator<CoefficientComparableContainer<T,K>>() {
			public int compare(CoefficientComparableContainer<T,K> c1, CoefficientComparableContainer<T,K> c2) {
				return comp.compare(c1.coeff, c2.coeff);
			}
		};
	}


	public CoefficientComparableContainer(T o) {
		content = o;
	}

	/*	public CoefficientComparableContainer(T o, double d) {
		content = o;
		coeff = (Double)d;
	}

	public CoefficientComparableContainer(T o, int d) {
		content = o;
		coeff = (Integer)d;
	}

	public CoefficientComparableContainer(T o, float d) {
		content = o;
		coeff = (Float)d;
	}

	public CoefficientComparableContainer(T o, long d) {
		content = o;
		coeff = (Long)d;
	}*/

	public CoefficientComparableContainer(T o, K c) {
		content = o;
		coeff = c;
	}

	public T getContainedObject() {
		return content;
	}

	public K getCoefficient() {
		return coeff;
	}

	@Override
	public String toString() {
		return content.toString() + "(" + coeff + ")";
	}
}
