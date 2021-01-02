package visualizer4j.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import visualizer4j.random.RandomSource;

public abstract class AbstractSortedHangingList<T, K> implements Collection<T> {

	protected TreeMap<K, Cell<T, K>> sorter;

	private int size = 0;

	public AbstractSortedHangingList() {
		sorter = new TreeMap<K, Cell<T, K>>();
	}

	public AbstractSortedHangingList(Comparator<K> comp) {
		sorter = new TreeMap<K, Cell<T, K>>(comp);
	}

	public T pollFirstVal() {
		size--;
		Map.Entry<K, Cell<T, K>> entry = sorter.firstEntry();
		Cell<T, K> c = entry.getValue();
		if (c.next == null) {
			sorter.pollFirstEntry();
			return c.value;
		} else {
			Cell<T, K> penultian = c.getPenultian();
			T toRet = penultian.next.value;
			penultian.next = null;
			return toRet;
		}
	}

	public void add(T i, K attached) {
		Cell<T, K> actual = sorter.get(attached);
		if (actual != null) {
			actual.hang(i);
		} else {
			sorter.put(attached, new Cell<T,K>(i, true, attached));
		}
		size++;
	}

	public boolean addAll(Collection<? extends T> e) {
		throw new IllegalStateException("Object must be added with a coeff (or override this method)");
	}

	/*	public boolean remove(K f) {
		Cell<T, K> c = sorter.remove(f);
		Cell<T, K> cur = c;
		while (cur != null) {
			size--;
			cur = cur.next;
		}
		return (c != null);

	//	return sorter.remove(sorter.get(f));
	}*/

	public Pair<K, T> pollFirst() {
		size--;
		Map.Entry<K, Cell<T, K>> entry = sorter.firstEntry();
		K key = entry.getKey();
		Cell<T, K> c = entry.getValue();
		if (c.next == null) {
			sorter.pollFirstEntry();
			return new Pair<K, T>(key, c.value);

			//float[]{key, c.value};
		} else {
			Cell<T, K> penultian = c.getPenultian();
			T toRet = penultian.next.value;
			penultian.next = null;
			return new Pair<K, T>(key, toRet);

			//float[]{key, toRet};
		}
	}

	public void clear() {
		sorter.clear();
		size = 0;
	}

	public boolean add(T i) {
		throw new IllegalStateException("Should be added with a coeff");
	}

	public boolean contains(Object o) {
		for (Cell c : this.sorter.values()) {
			if (c.contains(o)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsAll(Collection<?> e) {
		for (Object t : e) {
			if (this.contains(t) == false) {
				return false;
			}
		}
		return true;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] a) {
		return getAscending().toArray(a);
	}

	public Object[] toArray() {
		return getAscending().toArray();
	}

	public Iterator<T> iterator() {
		return getAscending().iterator();
	}

	public boolean remove(Object o) {
		boolean rem = false;
		ArrayList<Cell<T, K>> toRemove = new ArrayList<Cell<T, K>>();
		for (Cell<T, K> c : sorter.values()) {
			while (c != null) {
				if (c.value.equals(o)) {
					Cell<T, K> toDel = c;
					c = c.next;
					toRemove.add(toDel);
					rem |= true;
				} else {
					c = c.next;
				}
			}
		}
		for (Cell<T, K> c : toRemove) {
			removeCell(c);
		}
		return rem;

	}

	private void removeCell(Cell<T, K> c) {
		if (c.isFirst) {
			Cell<T, K> next = c.next;
			sorter.remove(c.key);
			if (next != null) {
				next.previous = null;
				next.isFirst = true;
				c.next = null;
				sorter.put(c.key, next);
			}
		} else {
			if (c.next != null) {
				c.previous.next = c.next;
				c.next.previous = c.previous;
				c.next = null;
				c.previous = null;
			} else {
				c.previous.next = null;
				c.previous = null;
			}
		}
	}

	public ArrayList<T> getDescending(RandomSource src) {
		ArrayList<T> tab = new ArrayList<T>(size);
		for (K f : sorter.descendingKeySet()) {
			ArrayList<T> loctab = new ArrayList<T>(size);
			Cell<T, K> cell = sorter.get(f);
			loctab.add(cell.getValue());
			while (cell.hasNext()) {
				cell = cell.next();
				loctab.add(cell.getValue());
			}
			Collections.shuffle(loctab, src.toRandom());
			tab.addAll(loctab);
		}
		return tab;
	}

	public ArrayList<T> getDescending() {
		ArrayList<T> tab = new ArrayList<T>(size);
		for (K f : sorter.descendingKeySet()) {
			Cell<T, K> cell = sorter.get(f);
			tab.add(cell.getValue());
			while (cell.hasNext()) {
				cell = cell.next();
				tab.add(cell.getValue());
			}
		}
		return tab;
	}

	public ArrayList<T> getAscending(RandomSource src) {
		ArrayList<T> tab = new ArrayList<T>(size);
		for (K f : sorter.keySet()) {
			ArrayList<T> loctab = new ArrayList<T>(size);
			Cell<T, K> cell = sorter.get(f);
			loctab.add(cell.getValue());
			while (cell.hasNext()) {
				cell = cell.next();
				loctab.add(cell.getValue());
			}
			Collections.shuffle(loctab, src.toRandom());
			tab.addAll(loctab);
		}
		return tab;
	}

	public ArrayList<T> getAscending() {
		ArrayList<T> tab = new ArrayList<T>(size);
		for (K f : sorter.keySet()) {
			Cell<T, K> cell = sorter.get(f);
			tab.add(cell.getValue());
			while (cell.hasNext()) {
				cell = cell.next();
				tab.add(cell.getValue());
			}
		}
		return tab;
	}

	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		return sorter.toString();
	}


	protected static class Cell<T, K> {

		T value;
		Cell<T, K> next;
		Cell<T, K> previous;
		boolean isFirst;
		K key;

		Cell(T value, boolean isFirst, K key) {
			this.value = value;
			this.isFirst = isFirst;
			this.key = key;
		}

		void hang(T val) {
			if (next == null) {
				next = new Cell<T,K>(val, false, this.key);
				next.previous = this;
			} else {
				next.hang(val);
			}
		}
		boolean hasNext() { return (next != null); }
		Cell<T, K> next() {
			return next;
		}
		Cell<T, K> getPenultian() {
			if (next.next == null) {
				return this;
			}
			return next.getPenultian();
		}

		T getValue() { return value; }

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("["+value+"]");
			if (next != null) {
				sb.append(next.toString());
			}
			return sb.toString();
		}

		public boolean contains(Object o) {
			if (value.equals(o)) {
				return true;
			}
			if (hasNext()) {
				return next.contains(o);
			}
			return false;
		}

	}

}
