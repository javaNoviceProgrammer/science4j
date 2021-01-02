package visualizer4j.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SetEnumerator<T> implements Iterable<ArrayList<T>> {

	protected int selectedIndividualNumber = 0;
	protected Collection<T> priSet = null;
	private Set<T> prefix = null;
	private boolean allowRepetition = false;

	public SetEnumerator(Collection<T> baseSet, int selectedIndividualNumber) {
		if (selectedIndividualNumber > baseSet.size()) {
			throw new IllegalStateException("Cannot select " + selectedIndividualNumber + " among " + baseSet.size());
		}

		this.selectedIndividualNumber = selectedIndividualNumber;
		priSet = baseSet;
	}

	public SetEnumerator(Set<T> baseSet, int selectedIndividualNumber, Set<T> prefix) {
		this(baseSet, selectedIndividualNumber);
		this.prefix = prefix;
	}
	
	public SetEnumerator(Set<T> baseSet, int selectedIndividualNumber, boolean allowRepetitions) {
		this.selectedIndividualNumber = selectedIndividualNumber;
		this.priSet = baseSet;
		this.allowRepetition = allowRepetitions;
	}

	public Iterator<ArrayList<T>> iterator() {
		if (prefix == null) {
			if (allowRepetition) {
				return new RepetitiveIterator();
			} else {
				return new LocalIterator();				
			}
		} else {
			if (allowRepetition) {
				throw new IllegalStateException("Not supported (yet)");
			}
			return new PrefixIterator(prefix);
		}
	}

	class PrefixIterator extends LocalIterator {
		int cursor = 0;
		TreeSet<T> copy;
		boolean[] fixed;

		PrefixIterator(Set<T> prefix) {
			super();
			copy = new TreeSet<T>();
			fixed = new boolean[objs.length+1];
			for (int j = 0 ; j < objs.length ; j++) {
				fixed[j] = prefix.contains(objs[j]);
			}
			fixed[objs.length] = false;
			for (T t : prefix) {
				copy.add(t);
			}
			while (fixed[cursor] == true) {
				cursor++;
			}
		}

		@Override
		public boolean hasNext() {
			return (cursor < priSet.size());
		}

		@Override
		public ArrayList<T> next() {
			ArrayList<T> dCopy = new ArrayList<T>(copy.size());
			dCopy.addAll(copy);
			//	Set<T> dCopy = (Set<T>)copy.clone();
			dCopy.add(objs[cursor]);
			do {
				cursor++;
			} while (fixed[cursor] == true);
			return dCopy;
		}
	}

	@SuppressWarnings("unchecked")
	class LocalIterator implements Iterator<ArrayList<T>> {
		int[] indexes;
		T[] objs;
		LocalIterator() {
			objs = (T[])new Object[priSet.size()];
			priSet.toArray(objs);
			indexes = new int[Math.max(1,selectedIndividualNumber)];
			for (int i = 0; i < selectedIndividualNumber ; i++) {
				indexes[i] = i;
			}
		}

		public boolean hasNext() {
			//	System.out.println("indexes : " + indexes);
			//	System.out.println("priSet + " + priSet);
			return (selectedIndividualNumber >= 0) &&
			(indexes[0] <priSet.size() - (selectedIndividualNumber -1));
		}

		public ArrayList<T> next() {
			ArrayList<T> toReturn = new ArrayList<T>(selectedIndividualNumber);
			if (selectedIndividualNumber == 0) {
				selectedIndividualNumber = -1;
				return toReturn;
			}
			for (int i = 0 ; i < selectedIndividualNumber ; i++) {
				toReturn.add(objs[indexes[i]]);
			}
			increment(selectedIndividualNumber -1);
			return toReturn;
		}

		public void remove() {
			throw new IllegalStateException("Not implemented");
		}

		private void increment(int col) {
			//	System.out.print("Incrementing col " + col);
			indexes[col]++;
			if (indexes[col] >= objs.length - (selectedIndividualNumber-1 - col)) {
				if (col > 0) {
					increment(col-1);
					indexes[col] = indexes[col-1]+1;
				}
			}
			//	System.out.println("  new value is " + indexes[col]);
		}
	}
	
	class RepetitiveIterator implements Iterator<ArrayList<T>> {
		
		Object[] actual;
		int[] indexes;
		ArrayList<T> items;
		
		{
			items = new ArrayList<T>(priSet);
			actual = new Object[selectedIndividualNumber];
			indexes = new int[selectedIndividualNumber];
			Arrays.fill(actual, items.get(0));
		}

		@Override
		public boolean hasNext() {
			return actual != null;
		}

		@Override
		public ArrayList<T> next() {
			@SuppressWarnings("unchecked")
			ArrayList<T> toRet = (ArrayList<T>) MoreArrays.getArrayList(actual);
			increment(indexes.length -1);
			if (indexes != null) {
			//	System.out.println(Arrays.toString(indexes));
				for (int i = 0 ; i < selectedIndividualNumber ; i++) {
					actual[i] = items.get(indexes[i]);
				}
			} else {
				actual = null;
			}
		//	System.out.println(toRet);
			return toRet;
		}
		
		private void increment(int position) {
			if (indexes[position] == items.size()-1) {
				if (position == 0) {
					indexes = null;
				} else {
					increment(position-1);
					if (indexes != null) {
						indexes[position] = indexes[position-1];
					}
				}
			} else {
				indexes[position]++;
			}
		}

		@Override
		public void remove() {
			
		}
		
	}
	
	public static void main(String[] args) {
		String[] s = new String[]{"aa", "bb", "cc"};
		List<String> ls = Arrays.asList(s);
		SetEnumerator<String> setE = new SetEnumerator<String>(new HashSet<String>(ls), 4, true);
		for (ArrayList<String> s2 : setE) {
			System.out.println(s2);
		}
		
		Integer[] f = new Integer[]{0,1,2,3,4,5,6,7};
		List<Integer> li = Arrays.asList(f);
		SetEnumerator<Integer> setI = new SetEnumerator<Integer>(new HashSet<Integer>(li), 8);
		for (ArrayList<Integer> l : setI) {
			System.out.println(l);
		}
	}

}
