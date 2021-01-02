package visualizer4j.utils;

import java.util.HashMap;
import java.util.Iterator;

public class ObjectIndexedCounter<T> implements Iterable<Pair<T, Integer>> {
	
	private HashMap<T, Counter> map;
	
	public ObjectIndexedCounter() {
		map = new HashMap<T, Counter>();
	}
	
	public void increment(T t) {
		getOrCreateCounter(t).increment();
	}
	
	public void decrement(T t) {
		getOrCreateCounter(t).decrement();
	}
	
	private Counter getOrCreateCounter(T t) {
		Counter c = map.get(t);
		if (c == null){
			c = new Counter();
			map.put(t,c);
		}
		return c;		
	}
	
	public int value(T t) {
		Counter c = map.get(t);
		return c.getValue();
	}
	
	public Iterator<Pair<T, Integer>> iterator() {
		return new Iterator<Pair<T,Integer>>() {
			Iterator<T> it = map.keySet().iterator();
			
			public boolean hasNext() {
				return it.hasNext();
			}
			
			public Pair<T,Integer> next() {
				T t = it.next();
				Counter c = map.get(t);
				return new Pair<T,Integer>(t, c.getValue());
			}
			public void remove() {
			}
		};
	}
	
}
