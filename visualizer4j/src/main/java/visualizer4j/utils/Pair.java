package visualizer4j.utils;

import java.io.Serializable;
import java.util.Comparator;


public class Pair<A,B> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	A object1 = null;
	B object2 = null;


	public Pair(A a, B b) {
		object1 = a;
		object2 = b;
	}

	public Pair() {
	}

	public A getFirst() {
		return object1;
	}

	public B getSecond() {
		return object2;
	}

	public void setFirst(A a) {
		object1 = a;
	}

	public void setSecond(B b) {
		object2 = b;
	}

	@Override
	public String toString() {
		String s = object1 == null ? "null" : object1.toString();
		String s2 = object2 == null ? "null" : object2.toString();
		return "<" + s + ":" + s2 + ">";
	}

	public PairList<A,B> toPairList() {
		return new PairList<A,B>(this);
	}
	
	@SuppressWarnings("unchecked")	
	public Comparator<A> firstComparator() {
		return new Comparator<A>() {
			public int compare(A a1, A a2) {
				Comparable c1 = (Comparable)a1;
				Comparable c2 = (Comparable)a2;
				return c1.compareTo(c2);
			}
			
			public boolean equals(Object o) {
				return (this == o);
			}
		};
	}

	@Override
	public int hashCode() {
		int tot = (object1 == null) ? 0 : object1.hashCode();
		tot += (object2 == null) ? 0 : object2.hashCode();
		return tot;
		/*	final int prime = 31;
		int result = 1;
		result = prime * result + ((object1 == null) ? 0 : object1.hashCode());
		result = prime * result + ((object2 == null) ? 0 : object2.hashCode());
		return result;*/
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pair other = (Pair) obj;
		if (object1 == null) {
			if (other.object1 != null) {
				return false;
			}
		} else if (!object1.equals(other.object1)) {
			return false;
		}
		if (object2 == null) {
			if (other.object2 != null) {
				return false;
			}
		} else if (!object2.equals(other.object2)) {
			return false;
		}
		return true;
	}
}
