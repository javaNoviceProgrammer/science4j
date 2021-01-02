package visualizer4j.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Sparse2DList<T> {
	
	private static ArrayList empty = new ArrayList(0);
	
	private HashMap<Integer,HashMap<Integer, ArrayList<T>>> map = new HashMap<Integer,HashMap<Integer, ArrayList<T>>>();
	
	public void add(int i , int j, T a) {
		HashMap<Integer, ArrayList<T>> sub = map.get(i);
		if (sub == null) {
			sub = new HashMap<Integer, ArrayList<T>>();
			map.put(i, sub);
		}
		
		ArrayList<T> list = sub.get(j);
		if (list == null) {
			list = new ArrayList<T>(1);
			sub.put(j, list);
		}
		list.add(a);
	}
	
	public void addAll(int i, int j, Collection<T> t) {
		HashMap<Integer, ArrayList<T>> sub = map.get(i);
		if (sub == null) {
			sub = new HashMap<Integer, ArrayList<T>>();
			map.put(i, sub);
		}
		
		ArrayList<T> list = sub.get(j);
		if (list == null) {
			list = new ArrayList<T>(1);
			sub.put(j, list);
		}
		list.addAll(t);		
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<T> get(int i, int j) {
		HashMap<Integer, ArrayList<T>> sub = map.get(i);
		if (sub == null) return empty;
		ArrayList<T> list = sub.get(j);
		if (list == null) return empty;
		return list;
	}

}
