package visualizer4j.utils;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class SimpleMap<K, V> extends AbstractMap<K, V> implements Cloneable, Serializable {

	private static final long serialVersionUID = 8568052613444573038l;

	private transient Set<Entry<K, V>> entries = null;
	private ArrayList<Entry<K, V>> list;
	
	public static SimpleMap<String, String> getMap(String... arg) {
		SimpleMap<String, String> m = new SimpleMap<String, String>(arg.length/2);
		for (int i = 0 ; i < arg.length ; i = i+2) {
			m.put(arg[i], arg[i+1]);
		}
		return m;
	}
	
	public static SimpleMap<String, String> getMap(Object... arg) {
		SimpleMap<String, String> m = new SimpleMap<String, String>(arg.length/2);
		for (int i = 0 ; i < arg.length ; i = i+2) {
			m.put(arg[i].toString(), arg[i+1].toString());
		}
		return m;
	}	

	public SimpleMap() {
		list = new ArrayList<Entry<K, V>>();
	}

	public SimpleMap(Map<K, V> map) {
		this(map.size());
		putAll(map);
	}

	public SimpleMap(Collection<Pair<K,V>> col) {
		this(col.size());
		for (Pair<K,V> p : col) {
			this.put(p.getFirst(), p.getSecond());
		}
	}

	public SimpleMap(int initCapacity) {
		list = new ArrayList<Entry<K, V>>(initCapacity);
	}

	public SimpleMap(K k, V v) {
		this(1);
		put(k,v);

	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		if (entries == null) {
			entries = new AbstractSet<Entry<K, V>>() {
				@Override
				public void clear() {
					list.clear();
				}

				@Override
				public Iterator<Entry<K, V>> iterator() {
					return list.iterator();
				}

				@Override
				public int size() {
					return list.size();
				}
			};
		}
		return entries;
	}

	@Override
	public V put(K key, V value) {
		int size = list.size();
		Entry<K, V> entry = null;

		boolean exists = false;
		if (key == null) {
			for (int i = 0; i < size; i++) {
				entry = (list.get(i));
				if (entry.getKey() == null) {
					exists = true;
					break;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				entry = (list.get(i));
				if (key.equals(entry.getKey())) {
					exists = true;
					break;
				}
			}
		}


		V oldValue;
		if (exists) {
			oldValue = entry.getValue();
			entry.setValue(value);
		} else {
			oldValue = null;
			list.add(new SimpleEntry<K, V>(key, value));
		}
		return oldValue;
	}

	@Override
	public V remove(Object key) {
		Entry<K, V> entry = null;
		for(Entry<K, V> e: list) {
			if (e.getKey().equals(key)) {
				entry = e;
				break;
			}
		}

		if (entry == null) {
			entries = null;
			return null;
		}
		else {
			list.remove(entry);
			return entry.getValue();
		}
	}
	@Override
	public Object clone() {
		return new SimpleMap<K, V>(this);
	}
	
	public K getKey(int index) {
		return list.get(index).getKey();
	}
	
	public V getValue(int index) {
		return list.get(index).getValue();
	}

	public static String flatten(Map<String, String> allParameters) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> e : allParameters.entrySet()) {
			sb.append(e.getKey() + "-" + e.getValue() + "_");
		}
		return sb.toString();
	}

}