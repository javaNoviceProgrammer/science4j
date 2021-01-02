package visualizer4j.utils;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.objecthunter.exp4j.ExpressionBuilder;

public class TypeParser {	

	private static Pattern threeDecimalCatchingPattern = Pattern.compile(
	"[^0-9]*([0-9]+)[^0-9]+[^0-9]*([0-9]+)[^0-9]+[^0-9]*([0-9]+)[^0-9]*");
	
	public static Pattern twoDecimalCatchingPattern = Pattern.compile(
			"[^0-9]*([0-9]+)[^0-9]+[^0-9]*([0-9]+)[^0-9]*");			

	private static Pattern hexaFormatCatchingPattern = Pattern.compile(
			"[^#]*" + // Any char but not "#" (to consume spaces e.g)
			"#"     + // the # char (starts the sequence)
			"([0-9a-fA-F]{2})" +  // the first couple
			"([0-9a-fA-F]{2})" +  // the second couple
			"([0-9a-fA-F]{2})" +
	"[^0-9a-fA-F]*");  // the third couple

	public static java.awt.Color parseColor(String s) {
		try {
			return new Color(Integer.parseInt(((int) evaluate(s))+""));
		}
		catch(Exception e) {
		}

		Matcher m = hexaFormatCatchingPattern.matcher(s);
		int red = -1;
		int green = -1;
		int blue = -1;
		if (m.matches()) {
			red = Math.max(0,Math.min(255,Integer.parseInt(m.group(1),16)));
			green = Math.max(0,Math.min(255,Integer.parseInt(m.group(2),16)));
			blue = Math.max(0,Math.min(255,Integer.parseInt(m.group(3),16)));

			return new java.awt.Color(red,green,blue);
		} else {
			Matcher m2 = threeDecimalCatchingPattern.matcher(s);
			if (m2.matches()) {
				red = Math.max(0,Math.min(255,Integer.parseInt(m2.group(1))));
				green = Math.max(0,Math.min(255,Integer.parseInt(m2.group(2))));
				blue = Math.max(0,Math.min(255,Integer.parseInt(m2.group(3))));
				return new java.awt.Color(red,green,blue);
			}
		}
		return null;
	}	

	private static Pattern meridianPositionPattern = Pattern.compile(
	"([0-9]+)[^0-9]{1}([0-9]+)[^0-9ENSW]*([ENSW]{1}).*");

	private static Pattern meridianPositionPatternNoComma = Pattern.compile(
	"([0-9]+)[^0-9ENSW]*([ENSW]{1}).*");

	public static int parsePosition(String s) {
		if (s!=null) {
			Matcher m = meridianPositionPattern.matcher(s);
			if (m.matches()) {
				float pos = Integer.parseInt(m.group(1));
				pos += ((java.lang.Integer.parseInt(m.group(2)))) / 60f;
				if ((m.group(3).equals("W")) || (m.group(3).equals("S"))) {
					return Math.round(pos /90 * (-10000f));
				} else {
					return Math.round(pos /90 * 10000f);
				}
			} else {
				Matcher m2 = meridianPositionPatternNoComma.matcher(s);
				if (m2.matches()) {
					float f = Integer.parseInt(m2.group(1));
					f = f / 90f * 10000f;
					if ((m2.group(2).equals("W")) || (m2.group(2).equals("S"))) {
						return Math.round(-f);
					} else {
						return Math.round(f);
					}
				} else {
					try {
						return Integer.parseInt(s);
					}
					catch (NumberFormatException e) {}
				}
			}
		}
		return 0;
	}

	public static int[] parseIntArray(String s) {
		if (s.contains(":")) {
			return MoreArrays.toIntArray(parseIntInterval(s));
		}
		if (s.contains(",")) {
			String[] sarray = s.split(",");
			if (sarray.length > 1) {
				int[] toReturn = new int[sarray.length];
				for (int i = 0 ; i < sarray.length ; i++) {
					String sss = sarray[i].replaceAll("[^0-9\\-]","");
					toReturn[i] = Integer.parseInt(((int) evaluate(sss))+"");
				}
				return toReturn;
			} else {
				try {
					Integer i = Integer.parseInt(""+((int) evaluate(sarray[0])));
					return new int[]{(int)i};
				}
				catch (Exception e) {
					return new int[0];
				}
			}	
		} else {
			String[] sarray = s.split(" ");
			ArrayList<Integer> ff = new ArrayList<Integer>();
			for (String st : sarray) {
				try {
					Integer i = Integer.parseInt(""+((int) evaluate(st)));
					ff.add(i);
				} catch (NumberFormatException e) {}
			}
			return MoreArrays.toIntArray(ff);
		}
	}
	
	public static Integer[] parseIntegerArray(String s) {
		String[] sarray = s.split(",");
		if (sarray.length > 1) {
			Integer[] toReturn = new Integer[sarray.length];
			for (int i = 0 ; i < sarray.length ; i++) {
				String sss = sarray[i].replaceAll("[^0-9\\-]","");
				toReturn[i] = Integer.parseInt(""+((int) evaluate(sss)));
			}
			return toReturn;
		} else {
			try {
				Integer i = Integer.parseInt(sarray[0].replaceAll("[^0-9\\-]",""));
				return new Integer[]{i};
			}
			catch (Exception e) {
				return new Integer[0];
			}
		}
	}

	public static String parseColor(Color c) {
		StringBuilder sb = new StringBuilder(7);
		sb.append("#");

		String s = Integer.toHexString(c.getRed());
		while (s.length() < 2) {
			s = "0" + s;
		}
		sb.append(s);

		s = Integer.toHexString(c.getGreen());
		while (s.length() < 2) {
			s = "0" + s;
		}
		sb.append(s);

		s = Integer.toHexString(c.getBlue());
		while (s.length() < 2) {
			s = "0" + s;
		}
		sb.append(s);


		return sb.toString();
	}
	
	public static Class getRawType(String def) {
		if (def.equals("int")) {
			return Integer.TYPE;
		} else if (def.equals("double")) {
			return Double.TYPE;
		} else if (def.equals("boolean")) {
			return Boolean.TYPE;
		} else if (def.equals("char")) {
			return Character.TYPE;
		} else if (def.equals("long")) {
			return Long.TYPE;
		} else if (def.equals("byte")) {
			return Byte.TYPE;
		} else if (def.equals("short")) {
			return Short.TYPE;
		} else if (def.equals("float")) {
			return Float.TYPE;
		} else {
			return null;
		}		
	}
	
	public static Object parseRawType(String def, String s) {
		if (def.equals("int")) {
			return Integer.parseInt(""+((int) evaluate(s)));
		} else if (def.equals("double")) {
			return Double.parseDouble(""+((double) evaluate(s)));
		} else if (def.equals("boolean")) {
			return Boolean.parseBoolean(s);
		} else if (def.equals("char")) {
			return s.charAt(0);
		} else if (def.equals("long")) {
			return Long.parseLong(""+((long) evaluate(s)));
		} else if (def.equals("byte")) {
			return Byte.parseByte(s);
		} else if (def.equals("short")) {
			return Short.parseShort(""+((short) evaluate(s)));
		} else if (def.equals("float")) {
			return Float.parseFloat(""+((float) evaluate(s)));
		} else {
			throw new IllegalStateException();
		}		
	}
	
	public static  int[] parseInt(String s) throws NumberFormatException {
		String parse = s.replace(" ", "");
		List<Integer> ret = new ArrayList<Integer>();
		String[] parts = parse.split(",");
		for (String part : parts){
			ret.addAll(parseIntInterval(part));
		}
		int[] obj = new int[ret.size()];
		int i = 0;
		for (int o : ret) {
			obj[i] = o;
			++i;
		}
		return obj;
	}
	
	public static  long[] parseLong(String s) throws NumberFormatException {
		String parse = s.replace(" ", "");
		List<Long> ret = new ArrayList<Long>();
		String[] parts = parse.split(",");
		for (String part : parts){
			ret.addAll(parseLongInterval(part));
		}
		long[] obj = new long[ret.size()];
		int i = 0;
		for (long o : ret) {
			obj[i] = o;
			++i;
		}
		return obj;
	}
	
	public static short[] parseShort(String s) throws NumberFormatException {
		String parse = s.replace(" ", "");
		List<Short> ret = new ArrayList<Short>();
		String[] parts = parse.split(",");
		for (String part : parts){
			List<Integer> p = parseIntInterval(part);
			for (int i = 0 ; i < p.size() ; i++) {
				ret.add((short)(int)p.get(i));
			}
		}
		short[] obj = new short[ret.size()];
		int i = 0;
		for (short o : ret) {
			obj[i] = o;
			++i;
		}
		return obj;
	}		
	
	public static  float[] parseFloat(String s) throws NumberFormatException {
		String parse = s.replace(" ", "");
		List<Float> ret = new ArrayList<Float>();
		String[] parts = parse.split(",");
		for (String part : parts){
			ret.addAll(parseFloatInterval(part));
		}
		float[] obj = new float[ret.size()];
		int i = 0;
		for (float o : ret) {
			obj[i] = o;
			++i;
		}
		return obj;
	}
	
	public static  double[] parseDouble(String s) throws NumberFormatException {
		String parse = s.replace(" ", "");
		List<Double> ret = new ArrayList<Double>();
		String[] parts = parse.split(",");
		for (String part : parts){
			ret.addAll(parseDoubleInterval(part));
		}
		double[] obj = new double[ret.size()];
		int i = 0;
		for (double o : ret) {
			obj[i] = o;
			++i;
		}
		return obj;
	}
	
	public static  List<Integer> parseIntInterval(String s) throws NumberFormatException {
		if (s.contains("log")) {
			return parseIntegerLogInterval(s);
		}
		if (s.contains("lin")) {
			return parseIntegerLinInterval(s);
		}				
		if (s.contains("pow")) {
			return parseIntegerPowerInterval(s);
		}
		String[] parts = s.split(":");
		List<Integer> ret = new ArrayList<Integer>();
		int first;
		int step;
		int last;
		switch (parts.length) {
		case 1:
			ret.add(Integer.parseInt(""+((int) evaluate(parts[0]))));
			break;
		case 2:
			first = Integer.parseInt(""+((int) evaluate(parts[0])));
			last = Integer.parseInt(""+((int) evaluate(parts[1])));
			while (first <= last) {
				ret.add(first);
				++first;
			}
			break;
		case 3:
			first = Integer.parseInt(""+((int) evaluate(parts[0])));
			step = Integer.parseInt(""+((int) evaluate(parts[1])));
			last = Integer.parseInt(""+((int) evaluate(parts[2])));
			while (first <= last) {
				ret.add(first);
				first += step;
			}
			break;
		default:
			throw new NumberFormatException();
		}
		return ret;
	}

	public static  List<Long> parseLongInterval(String s) throws NumberFormatException {
		if (s.contains("log")) {
			return parseLongLogInterval(s);
		}
		if (s.contains("lin")) {
			return parseLongLinInterval(s);
		}		
		String[] parts = s.split(":");
		List<Long> ret = new ArrayList<Long>();
		long first;
		long step;
		long last;
		switch (parts.length) {
		case 1:
			ret.add(Long.parseLong(""+((long) evaluate(parts[0]))));
			break;
		case 2:
			first = Long.parseLong(""+((long) evaluate(parts[0])));
			last = Long.parseLong(""+((long) evaluate(parts[1])));
			while (first <= last) {
				ret.add(first);
				++first;
			}
			break;
		case 3:
			first = Long.parseLong(""+((long) evaluate(parts[0])));
			step = Long.parseLong(""+((long) evaluate(parts[1])));
			last = Long.parseLong(""+((long) evaluate(parts[2])));
			while (first <= last) {
				ret.add(first);
				first += step;
			}
			break;
		default:
			throw new NumberFormatException();
		}
		return ret;
	}



	public static  List<Float> parseFloatInterval(String s) throws NumberFormatException {
		if (s.contains("log")) {
			return parseFloatLogInterval(s);
		}	
		if (s.contains("lin")) {
			return parseFloatLinInterval(s);
		}			
		
		String[] parts = s.split(":");
		List<Float> ret = new ArrayList<Float>();
		BigDecimal first;
		BigDecimal step;
		BigDecimal last;
		switch (parts.length) {
		case 1:
			ret.add(Float.parseFloat(""+((float) evaluate(parts[0]))));
			break;
		case 2:
			first = new BigDecimal(Float.parseFloat(""+((float) evaluate(parts[0]))));
			last = new BigDecimal(Float.parseFloat(""+((float) evaluate(parts[1]))));
			while (first.floatValue() <= last.floatValue()) {
				ret.add(first.floatValue());
				first = first.add(BigDecimal.ONE);
			}
			break;
		case 3:
			first = new BigDecimal(Float.parseFloat(""+((float) evaluate(parts[0]))));
			step = new BigDecimal(Float.parseFloat(""+((float) evaluate(parts[1]))));
			last = new BigDecimal(Float.parseFloat(""+((float) evaluate(parts[2]))));
			while (first.floatValue() <= last.floatValue()) {
				ret.add(first.floatValue());
				first = first.add(step);
			}
			break;
		default:
			throw new NumberFormatException();
		}
		return ret;
	}



	public static List<Double> parseDoubleInterval(String s) throws NumberFormatException {
		if (s.contains("log")) {
			return parseDoubleLogInterval(s);
		}
		if (s.contains("lin")) {
			return parseDoubleLinInterval(s);
		}
		String[] parts = s.split(":");
		List<Double> ret = new ArrayList<Double>();
		BigDecimal first;
		BigDecimal step;
		BigDecimal last;
		switch (parts.length) {
		case 1:
			ret.add(Double.parseDouble(""+((double) evaluate(parts[0]))));
			break;
		case 2:
			first = new BigDecimal(Double.parseDouble(""+((double) evaluate(parts[0]))));
			last = new BigDecimal(Double.parseDouble(""+((double) evaluate(parts[1]))));
			while (first.doubleValue() <= last.doubleValue()) {
				ret.add(first.doubleValue());
				first = first.add(BigDecimal.ONE);
			}
			break;
		case 3:
			first = new BigDecimal(Double.parseDouble(""+((double) evaluate(parts[0]))));
			step = new BigDecimal(Double.parseDouble(""+((double) evaluate(parts[1]))));
			last = new BigDecimal(Double.parseDouble(""+((double) evaluate(parts[2]))));
			while (first.doubleValue() <= last.doubleValue()) {
				ret.add(first.doubleValue());
				first = first.add(step);
			}
			break;
		default:
			throw new NumberFormatException();
		}
		return ret;
	}

	private static List<Double> parseDoubleLinInterval(String s) {
		String[] parts = s.split("lin");
		double elements = Integer.parseInt(""+((int) evaluate(parts[1])))-1;
		String[] interval = parts[0].split(":");
		double start = Double.parseDouble(""+((double) evaluate(interval[0])));
		double end = Double.parseDouble(""+((double) evaluate(interval[1])));
		double inc = (end - start)/elements;
		List<Double> ret = new ArrayList<Double>();
		for (int i = 0 ; i < elements+1 ; i++) {
			ret.add(start);
			start += inc;
		}
		return ret;
	}

	private static List<Double> parseDoubleLogInterval(String s) {
		String[] parts = s.split("log");
		double elements = Integer.parseInt(""+((int) evaluate(parts[1])))-1;
		String[] interval = parts[0].split(":");
		double start = Math.log(Double.parseDouble(""+((double) evaluate(interval[0]))));
		double end = Math.log(Double.parseDouble(""+((double) evaluate(interval[1]))));
		double inc = (end - start)/elements;
		List<Double> ret = new ArrayList<Double>();
		for (int i = 0 ; i < elements+1 ; i++) {
			ret.add(Math.exp(start));
			start += inc;
		}
		return ret;
	}
	
	private static List<Float> parseFloatLogInterval(String s) {
		String[] parts = s.split("log");
		double elements = Integer.parseInt(""+((int) evaluate(parts[1])))-1;
		String[] interval = parts[0].split(":");
		double start = Math.log(Double.parseDouble(""+((double) evaluate(interval[0]))));
		double end = Math.log(Double.parseDouble(""+((double) evaluate(interval[1]))));
		double inc = (end - start)/elements;
		List<Float> ret = new ArrayList<Float>();
		for (int i = 0 ; i < elements+1 ; i++) {
			ret.add((float)Math.exp(start));
			start += inc;
		}
		return ret;
	}
	
	private static List<Float> parseFloatLinInterval(String s) {
		String[] parts = s.split("lin");
		double elements = Integer.parseInt(""+((int) evaluate(parts[1])))-1;
		String[] interval = parts[0].split(":");
		double start = Double.parseDouble(""+((double) evaluate(interval[0])));
		double end = Double.parseDouble(""+((double) evaluate(interval[1])));
		double inc = (end - start)/elements;
		List<Float> ret = new ArrayList<Float>();
		for (int i = 0 ; i < elements+1 ; i++) {
			ret.add((float)start);
			start += inc;
		}
		return ret;
	}	
	
	private static List<Integer> parseIntegerLogInterval(String s) {
		String[] parts = s.split("log");
		double elements = Integer.parseInt(""+((int) evaluate(parts[1])))-1;
		String[] interval = parts[0].split(":");
		double start = Math.log(Double.parseDouble(""+((double) evaluate(interval[0]))));
		double end = Math.log(Double.parseDouble(""+((double) evaluate(interval[1]))));
		double inc = (end - start)/elements;
		HashSet<Integer> ret = new HashSet<Integer>();
		for (int i = 0 ; i < elements+1 ; i++) {
			ret.add((int)Math.round(Math.exp(start)));
			start += inc;
		}
		ArrayList<Integer> list = new ArrayList<Integer>(ret);
		Collections.sort(list);
		return list;
	}
	
	private static List<Integer> parseIntegerLinInterval(String s) {
		String[] parts = s.split("lin");
		double elements = Integer.parseInt(""+((int) evaluate(parts[1])))-1;
		String[] interval = parts[0].split(":");
		double start = Double.parseDouble(""+((double) evaluate(interval[0])));
		double end = Double.parseDouble(""+((double) evaluate(interval[1])));
		double inc = (end - start)/elements;
		HashSet<Integer> ret = new HashSet<Integer>();
		for (int i = 0 ; i < elements+1 ; i++) {
			ret.add((int)Math.round(start));
			start += inc;
		}
		ArrayList<Integer> list = new ArrayList<Integer>(ret);
		Collections.sort(list);
		return list;
	}	
	
	private static List<Integer> parseIntegerPowerInterval(String s) {
		String[] parts = s.split("pow");
		double power = Integer.parseInt(""+((int) evaluate(parts[1])));
		List<Integer> list = parseIntInterval(parts[0]);
		
		List<Integer> ret = new ArrayList<Integer>();
		for (Integer i : list) {
			ret.add((int)Math.pow(i, power));
		}
		return ret;
		
	/*	String[] interval = parts[0].split(":");
		int start = Integer.parseInt(interval[0]);
		int end = Integer.parseInt(interval[1]);
		HashSet<Integer> ret = new HashSet<Integer>();
		for (int i = start ; i <= end ; i++) {
			ret.add((int)Math.pow(i, power));
		}
		ArrayList<Integer> list = new ArrayList<Integer>(ret);
		return list;	*/	
	}	
	
	private static List<Long> parseLongLogInterval(String s) {
		String[] parts = s.split("log");
		double elements = Integer.parseInt(""+((int) evaluate(parts[1])))-1;
		String[] interval = parts[0].split(":");
		double start = Math.log(Double.parseDouble(""+((double) evaluate(interval[0]))));
		double end = Math.log(Double.parseDouble(""+((double) evaluate(interval[1]))));
		double inc = (end - start)/elements;
		HashSet<Long> ret = new HashSet<Long>();
		for (int i = 0 ; i < elements+1 ; i++) {
			ret.add((long)Math.exp(start));
			start += inc;
		}
		return new ArrayList<Long>(ret);
	}
	
	private static List<Long> parseLongLinInterval(String s) {
		String[] parts = s.split("lin");
		double elements = Integer.parseInt(""+((int) evaluate(parts[1])))-1;
		String[] interval = parts[0].split(":");
		double start = Double.parseDouble(""+((double) evaluate(interval[0])));
		double end = Double.parseDouble(""+((double) evaluate(interval[1])));
		double inc = (end - start)/elements;
		HashSet<Long> ret = new HashSet<Long>();
		for (int i = 0 ; i < elements+1 ; i++) {
			ret.add((long)start);
			start += inc;
		}
		return new ArrayList<Long>(ret);
	}	
	
	// added by Meisam
	public static double evaluate(String expression){
		double result = new ExpressionBuilder(expression).build().evaluate() ;
		return result ;
	}


	
	
}