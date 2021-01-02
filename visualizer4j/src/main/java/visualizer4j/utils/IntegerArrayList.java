package visualizer4j.utils;

import java.util.ArrayList;
import java.util.Arrays;

public class IntegerArrayList extends ArrayList<Integer> {

	private static final long serialVersionUID = 1L;

	public IntegerArrayList(int size) {
		super(size);
	}

	public static IntegerArrayList[] getInitialisedArray(int length) {
		IntegerArrayList[] array = new IntegerArrayList[length];
		for (int i = 0 ; i < length ; i++) {
			array[i] = new IntegerArrayList(0);
		}
		return array;
	}

	public int max() {
		int max = Integer.MIN_VALUE;
		for (int i = 0 ; i < this.size() ; i++) {
			Integer thisi = this.get(i);
			if (thisi != null) {
				if (thisi > max) {
					max = thisi;
				}
			}
		}
		return max;
	}
	
	public int[] getCopy() {
		int[] copy = new int[this.size()];
		for (int i = 0 ; i < this.size() ; i++) {
			copy[i] = this.get(i);
		}
		return copy;
	}
	
	public int[] getSortedValues() {
		int[] copy = getCopy();
		Arrays.sort(copy);
		return copy;
	}
	
	public int[] getDecreasingValues() {
		int[] copy = getCopy();
		int length = copy.length;
		int[] copy2 = new int[length];
		Arrays.sort(copy);
		for (int i = 0 ; i < length ; i++) {
			copy2[length-i-1] = copy[i];
		}
		return copy2;
	}

	public int sum() {
		int sum = 0;
		for (int i = 0 ; i < this.size() ; i++) {
			Integer thisi = this.get(i);
			if (thisi != null) {
				sum += thisi;
			}
		}
		return sum;
	}
}
