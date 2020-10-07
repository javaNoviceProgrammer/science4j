package org.gsl4j.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class StringUtils {

	public static boolean isNumeric(String s) {
		return s != null && s.matches("[-+]?\\d*\\.?\\d+");
	}

	public static double toDouble(String s) {
		if (isNumeric(s)) {
			return Double.parseDouble(s);
		} else {
			return Double.NaN;
		}
	}

	public static String fixedWidthDoubletoString(double number, int decimals) {
		DecimalFormat var4 = new DecimalFormat();
		var4.setMaximumFractionDigits(decimals);
		var4.setMinimumFractionDigits(decimals);
		var4.setGroupingUsed(false);
		return var4.format(number);
	}

	public static double[] toDoubleArray(String s) {
		String str = s.replaceAll("[!?,;]", "");
		String[] words = str.split("\\s+");
		int n = words.length;
		double[] numbers = new double[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = toDouble(words[i]);
		}
		return numbers;
	}

	public static ArrayList<String> toArrayList(String[] st) {
		ArrayList<String> list = new ArrayList<>();
		for (String s : st) {
			list.add(s);
		}
		return list;
	}

	public static String getFileExtension(File file) {
		int m = file.getAbsolutePath().lastIndexOf(".") ;
		String extension = file.getAbsolutePath().substring(m+1) ;
		return extension ;
	}

	public static boolean isNullOrEmpty(String s) {
		return (s==null) || s.isEmpty() ;
	}

	public static String join(List<? super String> vals, char c) {
		StringBuilder sb = new StringBuilder() ;
		int size = vals.size() ;
		for(int i=0; i<size-1; i++) {
			sb.append(vals.get(i)).append(c) ;
		}
		sb.append(vals.get(size-1)) ;
		return sb.toString() ;
	}

	public static String join(List<Object> vals, String c) {
		StringBuilder sb = new StringBuilder() ;
		int size = vals.size() ;
		for(int i=0; i<size-1; i++) {
			sb.append(vals.get(i)).append(c) ;
		}
		sb.append(vals.get(size-1)) ;
		return sb.toString() ;
	}

	public static String join(String[] vals, String c) {
		StringBuilder sb = new StringBuilder() ;
		int size = vals.length ;
		for(int i=0; i<size-1; i++) {
			sb.append(vals[i]).append(c) ;
		}
		sb.append(vals[size-1]) ;
		return sb.toString() ;
	}

	public static String join(Map<String, ? extends Object> vals, String c, String keyValueSeparator) {
		StringBuilder sb = new StringBuilder() ;
		Set<String> ents = vals.keySet() ;
		List<String> entries = new LinkedList<>() ;
		for(String s: ents) {
			entries.add(s) ;
		}
		int size = entries.size() ;
		for(int i=0; i<size-1; i++) {
			sb.append(entries.get(i)).append(keyValueSeparator)
			  .append(vals.get(entries.get(i))).append(c) ;
		}
		sb.append(entries.get(size-1)).append(keyValueSeparator).append(vals.get(entries.get(size-1))) ;
		return sb.toString() ;
	}


	// for test
	public static void main(String[] args) {
//		System.out.println(fixedWidthDoubletoString(11111/2d, 4));
//		List<Object> values = new ArrayList<>() ;
//		values.add("hi") ;
//		values.add("how's it going?") ;
//		values.add("Meisam") ;
//		System.out.println(join(values, '\n'));
//		System.out.println(join(values, ", "));

//		Map<String, Object> map = new HashMap<>() ;
//		map.put("hi", 11.2) ;
//		map.put("how are", "you") ;
//		System.out.println(join(map, "\n", "="));

//		Map<String, Integer> salary = new HashMap<>();
//	    salary.put("John", 1000);
//	    salary.put("Jane", 1500);
//	    String result = join(salary, " , ", " = ") ;
//	    System.out.println(result);

	    List<String> names = Arrays.asList("John", "Jane", "Adam", "Tom");
	    String result = join(names, ',') ;
	    System.out.println(result);
	}

}
