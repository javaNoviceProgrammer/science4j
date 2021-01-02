package visualizer4j.utils;

import java.util.Comparator;
import java.util.PriorityQueue;

@SuppressWarnings("unchecked")
public class DoublePriorityQueue<K> implements Comparator {

	private PriorityQueue<Entry> pq;

	public class Entry {
		private Entry(K k, Comparable c1, Comparable c2) {
			this.value = k;
			this.c1 = c1;
			this.c2 = c2;
		}
		Comparable c1;
		Comparable c2;
		K value;

		@Override
		public String toString() {
			return "{"+c1+","+c2+"="+value+"}";
		}

		@Override
		public boolean equals(Object o1) {
			try {
				Entry e1 = (Entry)o1;
				int r = e1.c1.compareTo(this.c1);
				if (r != 0) {
					return false;
				}
				return (e1.c2.compareTo(this.c2) == 0);
			} catch(ClassCastException e) {
				return false;
			}

		}
	}

	public int compare(Object o1, Object o2) {


		Entry e1 = (Entry)o1;
		Entry e2 = (Entry)o2;
		/*	if (e1.c1.equals(4418) && e1.c1.equals(4172)) {
			int hutr = 0;
		}
		if (e2.c1.equals(4418) && e1.c1.equals(4172)) {
			int iruge = 0;
		}*/
		int r1 = e1.c1.compareTo(e2.c1);
		if (r1 != 0) {
			return r1;
		}
		return e1.c2.compareTo(e2.c2);
	}

	public DoublePriorityQueue(int initVal) {
		pq = new PriorityQueue(initVal, this);
	}

	public boolean add(K k, Comparable c1, Comparable c2) {
		Entry e = new Entry(k,c1,c2);
		return pq.add(e);
	}

	public void clear() {
		pq.clear();
	}

	public K peek() {
		return pq.peek().value;
	}

	public K poll() {
		return pq.poll().value;
	}

	public int size() {
		return pq.size();
	}

	@Override
	public String toString() {
		return pq.toString();
	}


}
