package visualizer4j.utils;


public class Counter {

	private int val;

	public Counter() {
	}

	public void increment() {
		val++;
	}

	public void decrement() {
		val--;
	}

	public int getValue() {
		return val;
	}

	@Override
	public String toString() {
		return "count="+val;
	}
}
