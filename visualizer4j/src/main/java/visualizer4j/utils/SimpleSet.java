package visualizer4j.utils;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class SimpleSet<V> extends AbstractSet<V> {

	public static final SimpleSet EMPTY_SET = new SimpleSet(0);

	private ArrayList<V> elements;

	public SimpleSet(Set<V> s) {
		elements = new ArrayList<V>(s.size());
		elements.addAll(s);
	}

	public SimpleSet() {
		elements = new ArrayList<V>();
	}

	public SimpleSet(int forecastedSize) {
		elements = new ArrayList<V>(forecastedSize);
	}

	@Override
	public int size() {
		return elements.size();
	}

	@Override
	public Iterator<V> iterator() {
		return elements.iterator();
	}

	@Override
	public boolean add(V v) {
		for (V alt : elements) {
			if (alt.equals(v)) {
				return false;
			}
		}
		elements.add(v);
		return true;
	}
}
