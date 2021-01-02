package visualizer4j.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class PairList<A,B> extends ArrayList<Pair<A,B>> implements Serializable, Comparable<PairList<A,B>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PairList() {
	}

	public PairList(int size) {
		super(size);
	}

	public PairList(A a, B b) {
		add(a,b);
	}

	public PairList(Pair<A,B> p) {
		this.add(p);
	}

	public PairList(List<A> al, List<B> bl) {
		if (al.size() != bl.size()) {
			throw new IllegalArgumentException("Input list sizes does'nt match");
		}
		for (int i = 0 ; i < al.size() ; i++) {
			add(al.get(i), bl.get(i));
		}
	}

	public PairList(List<A> al, B[] bl) {
		if (al.size() != bl.length) {
			throw new IllegalArgumentException("Input list sizes does'nt match");
		}
		for (int i = 0 ; i < al.size() ; i++) {
			add(al.get(i), bl[i]);
		}
	}

	public PairList(A[] al, List<B> bl) {
		if (al.length != bl.size()) {
			throw new IllegalArgumentException("Input list sizes does'nt match");
		}
		for (int i = 0 ; i < al.length ; i++) {
			add(al[i], bl.get(i));
		}
	}

	public PairList(A[] al, B[] bl) {
		if (al.length != bl.length) {
			throw new IllegalArgumentException("Input list sizes does'nt match");
		}
		for (int i = 0 ; i < al.length ; i++) {
			add(al[i], bl[i]);
		}
	}

	@SuppressWarnings("unchecked")
	public boolean equalsNotOrdered(PairList<A,B> pl) {
		if (pl.size() != this.size()) {
			return false;
		}
		PairList<A,B> copy = (PairList<A,B>)pl.clone();
		for (Pair p : this) {
			boolean b = copy.remove(p);
			if (b == false) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Object clone() {
		PairList<A,B> clone = new PairList<A,B>(this.size());
		for (int i = 0 ; i < this.size() ; i++) {
			clone.add(this.getFirst(i), this.getSecond(i));
		}
		return clone;
	}

	@Override
	@SuppressWarnings("unchecked")
	public int compareTo(PairList<A,B> other) {
		if (this.size() == 0) {
			if (other.size() == 0) {
				return 0;
			} else {
				return -1;
			}
		}
		int index = 0;
		while(index < other.size() && index < this.size()) {
			int comp = ((Comparable<A>)this.getFirst(index)).compareTo(other.getFirst(index));
			if (comp != 0) {
				return comp;
			}
			comp = ((Comparable<B>)this.getSecond(index)).compareTo(other.getSecond(index));
			if (comp != 0) {
				return comp;
			}
			index++;
		}
		return this.size() - other.size();
	}

	@SuppressWarnings("unchecked")
	public void add(A a, B b) {
		this.add(new Pair(a,b));
	}

	public void setFirst(int index, A a) {
		this.get(index).setFirst(a);
	}

	public void setSecond(int index, B b) {
		this.get(index).setSecond(b);
	}

	public A getFirst(int index) {
		return this.get(index).getFirst();
	}

	public B getSecond(int index) {
		return this.get(index).getSecond();
	}
	
	@SuppressWarnings("unchecked")
	public void sortAccordingToFirst() {
		Collections.sort(this, new Comparator<Pair<A,B>>() {
			@SuppressWarnings("rawtypes")
			public int compare(Pair<A,B> o1, Pair<A,B> o2) {
				Comparable c1 = (Comparable)o1.getFirst();
				Comparable c2 = (Comparable)o2.getFirst();
				return c1.compareTo(c2);
			}
		});
	}

	public Iterator<A> firstsIterator() {
		return new Iterator<A>() {
			int i = 0;
			public boolean hasNext() {
				return (i < size()-1);
			}
			public A next() {
				A a = getFirst(i);
				i++;
				return a;
			}
			public void remove() {
				PairList.this.remove(i);
				i--;
			}
		};
	}

	public Iterator<B> secondsIterator() {
		return new Iterator<B>() {
			int i = 0;
			public boolean hasNext() {
				return (i < size()-1);
			}
			public B next() {
				B b = getSecond(i);
				i++;
				return b;
			}
			public void remove() {
				PairList.this.remove(i);
				i--;
			}
		};
	}

	public Iterable<A> firsts() {
		return new Iterable<A>() {
			public Iterator<A> iterator() {
				return firstsIterator();
			}
		};
	}

	public Iterable<B> seconds() {
		return new Iterable<B>() {
			public Iterator<B> iterator() {
				return secondsIterator();
			}
		};
	}

}
