package visualizer4j.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class MoreString {
	
	public static Collection<Character> toCharCollection(String source) {
		ArrayList<Character> al = new ArrayList<Character>(source.length());
		for (int i = 0; i < source.length() ; i++) {
			al.add(source.charAt(i));
		}
		return al;
	}
	
	public static Set<Character> toCharSet(String source) {
		TreeSet<Character> al = new TreeSet<Character>();
		for (int i = 0; i < source.length() ; i++) {
			al.add(source.charAt(i));
		}
		return al;
	}	

	public static String toString(ArrayList<Character> alc) {
		StringBuilder sb = new StringBuilder();
		int size = alc.size();
		for (int i = 0 ; i < size ; i++) {
			sb.append(alc.get(i));
		}
		return sb.toString();
	}



}
