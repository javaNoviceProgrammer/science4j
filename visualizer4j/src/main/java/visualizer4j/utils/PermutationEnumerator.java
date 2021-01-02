package visualizer4j.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import visualizer4j.math.MoreMaths;

public class PermutationEnumerator<T> implements Iterable<ArrayList<T>> {
	
	private ArrayList<T> list;
	private long nb;
	private int size;
	
	public PermutationEnumerator(Set<T> set) {
		list = new ArrayList<T>(set);
		this.size = list.size();
		this.nb = MoreMaths.factorial(size);
		
	}

	@Override
	public Iterator<ArrayList<T>> iterator() {
		return new Iterator<ArrayList<T>>() {
			
			ArrayList<T> current = new ArrayList<T>(list);
			int counter = 0;
		
			int index = 0;
			int mode = 0;
			
			private void swap(int index) {
				T temp = current.get(index);
				current.set(index, current.get(index+1));
				current.set(index+1, temp);
			}

			@Override
			public boolean hasNext() {
				return counter < nb;
			}

			@Override
			public ArrayList<T> next() {
				ArrayList<T> copy = new ArrayList<T>(current);
				if (mode == 0) {
					swap(index);
					if (index == size-2) {
						mode = 1;
					} else {
						index++;
					}
				} else if (mode == 1) {
					if (index > 0) {
						swap(0);
						mode = 2;
					} else {
						swap(size-2);
						mode = 0;
					}
				} else if (mode == 2) {
					swap(index);
					if (index == 0) {
						mode = 1;
					} else {
						index--;
					}
				}
				counter++;
				return copy;
			}

			@Override
			public void remove() {}
		};
	}
	
	public static void main(String[] args) {
		int[] A = {0,1,2,3};
		
		long n = MoreMaths.factorial(A.length);
		
		int mode = 0; // 0 descent, 1 p, 2 rise
		int index = 0;
		System.out.println("0 : " + Arrays.toString(A));
		for (int i = 0 ; i < n - 1; i++) {
			
			if (mode == 0) {
				swap(A, index);
				if (index == A.length-2) {
					mode = 1;
				} else {
					index++;
				}
			} else if (mode == 1) {
				if (index > 0) {
					swap(A, 0);
					mode = 2;
				} else {
					swap(A, A.length-2);
					mode = 0;
				}
			} else if (mode == 2) {
				swap(A, index);
				if (index == 0) {
					mode = 1;
				} else {
					index--;
				}
			}
			System.out.println(i+1 + " : " + Arrays.toString(A));
		}
	}
	
	private static void swap(int[] array, int index) {
		int temp = array[index];
		array[index] = array[index+1];
		array[index+1] = temp;
	}	

}
