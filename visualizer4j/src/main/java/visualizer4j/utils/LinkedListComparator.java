package visualizer4j.utils;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListComparator<T> implements Comparator<LinkedList<T>> {

	private Comparator<T> localCompar;
	private boolean compareExtremitiesFirst = false;

	public LinkedListComparator() {
	}

	public LinkedListComparator(Comparator<T> compar) {
		localCompar = compar;
	}

	public LinkedListComparator(Comparator<T> compar, boolean compareExtremitiesFirst) {
		localCompar = compar;
		this.compareExtremitiesFirst = compareExtremitiesFirst;
	}


	public int compare(LinkedList<T> o1, LinkedList<T> o2) {
		int s1 = o1.size();
		int s2 = o2.size();
		if (s1 + s2 == 0) {
			return 0;
		}
		if (s1 == 0) {
			return -1;
		}
		if (s2 == 0) {
			return 1;
		}
		if (s1 == 1 && s2 == 1) {
			return localCompare(o1.getFirst(), o2.getFirst());
		}
		if (s1 == 1) {
			return -1;
		}
		if (s2 == 1) {
			return 1;
		}
		Iterator<T> ite1 = o1.iterator();
		Iterator<T> ite2 = o2.iterator();
		if (compareExtremitiesFirst) {
			int comp = localCompare(o1.getFirst(), o2.getFirst());
			if (comp != 0) {
				return comp;
			}
			comp = localCompare(o1.getLast(), o2.getLast());
			if (comp != 0) {
				return comp;
			}
		}
		if (s1 != s2) {
			return s1-s2;
		}
		int res;
		while (ite1.hasNext()) {
			res = localCompare(ite1.next(), ite2.next());
			if (res != 0) {
				return res;
			}
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	private int localCompare(T t1, T t2) {
		if (localCompar != null) {
			return localCompar.compare(t1, t2);
		} else if (t1 instanceof Comparable ) {
			return ((Comparable)t1).compareTo(t2);
		} else {
			if (t1 == t2) {
				return 0;
			} else {
				return 1;
			}
		}
	}
}
