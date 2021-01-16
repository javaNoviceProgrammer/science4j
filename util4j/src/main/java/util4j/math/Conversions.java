package util4j.math;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

import util4j.complex.Complex;


public class Conversions {
	
	private static double max_float_as_double = 3.4028234663852886E38D;
	private static double max_long_as_double = 9.223372036854776E18D;
	private static double max_long_as_float = 9.223372036854776E18D;
	private static double max_int_as_double = 2.147483647E9D;
	private static double max_int_as_float = 2.147483648E9D;
	private static double max_int_as_long = 2.147483647E9D;
	private static double max_short_as_double = 32767.0D;
	private static double max_short_as_long = 32767.0D;
	private static double max_short_as_float = 32767.0D;
	private static double max_short_as_int = 32767.0D;
	private static double max_byte_as_double = 127.0D;
	private static double max_byte_as_float = 127.0D;
	private static double max_byte_as_long = 127.0D;
	private static double max_byte_as_int = 127.0D;
	private static double max_byte_as_short = 127.0D;
	private static boolean suppressMessage = false;
	private static boolean suppressMessageAM = false;

	public static void suppressMessages() {
		suppressMessage = true;
	}

	public static void restoreMessages() {
		if (!suppressMessageAM) {
			suppressMessage = false;
		}

	}

	public static void suppressMessagesAM() {
		suppressMessageAM = true;
	}

	public static void restoreMessagesAM() {
		suppressMessageAM = false;
	}

	public static double[] ArrayList_to_doubles(ArrayList<?> var0) {
		return convert_ArrayList_to_doubles(var0);
	}

	public static double[] convert_ArrayList_to_doubles(ArrayList<?> var0) {
		double[] var1 = null;
		if (var0 != null) {
			int var2 = var0.size();
			var1 = new double[var2];

			for (int var3 = 0; var3 < var2; ++var3) {
				Object var4 = var0.get(var3);
				if (var4 instanceof Double) {
					var1[var3] = (Double) var4;
				} else if (var4 instanceof Float) {
					var1[var3] = (double) (Float) var4;
				} else if (var4 instanceof Long) {
					var1[var3] = (double) (Long) var4;
				} else if (var4 instanceof Integer) {
					var1[var3] = (double) (Integer) var4;
				} else if (var4 instanceof Short) {
					var1[var3] = (double) (Short) var4;
				} else if (var4 instanceof Byte) {
					var1[var3] = (double) (Byte) var4;
				} else if (var4 instanceof BigDecimal) {
					var1[var3] = ((BigDecimal) var4).doubleValue();
				} else {
					if (!(var4 instanceof BigInteger)) {
						throw new IllegalArgumentException("Element " + var3 + " is not convertible to double");
					}

					var1[var3] = ((BigInteger) var4).doubleValue();
				}
			}
		}

		return var1;
	}

	public static long[] ArrayList_to_longs(ArrayList<?> var0) {
		return convert_ArrayList_to_longs(var0);
	}

	public static long[] convert_ArrayList_to_longs(ArrayList<?> var0) {
		long[] var1 = null;
		if (var0 != null) {
			int var2 = var0.size();
			var1 = new long[var2];

			for (int var3 = 0; var3 < var2; ++var3) {
				Object var4 = var0.get(var3);
				if (var4 instanceof Double) {
					var1[var3] = (long) var4;
				} else if (var4 instanceof Float) {
					var1[var3] = (long) var4;
				} else if (var4 instanceof Long) {
					var1[var3] = (Long) var4;
				} else if (var4 instanceof Integer) {
					var1[var3] = (long) var4;
				} else if (var4 instanceof Short) {
					var1[var3] = (long) var4;
				} else if (var4 instanceof Byte) {
					var1[var3] = (long) var4;
				} else if (var4 instanceof BigDecimal) {
					var1[var3] = ((BigDecimal) var4).longValue();
				} else {
					if (!(var4 instanceof BigInteger)) {
						throw new IllegalArgumentException("Element " + var3 + " is not convertible to double");
					}

					var1[var3] = ((BigInteger) var4).longValue();
				}
			}
		}

		return var1;
	}

	public static int[] ArrayList_to_ints(ArrayList<?> var0) {
		return convert_ArrayList_to_ints(var0);
	}

	public static int[] convert_ArrayList_to_ints(ArrayList<?> var0) {
		int[] var1 = null;
		if (var0 != null) {
			int var2 = var0.size();
			var1 = new int[var2];

			for (int var3 = 0; var3 < var2; ++var3) {
				Object var4 = var0.get(var3);
				if (var4 instanceof Double) {
					var1[var3] = (int) var4;
				} else if (var4 instanceof Float) {
					var1[var3] = (int)var4;
				} else if (var4 instanceof Long) {
					var1[var3] = (int) var4;
				} else if (var4 instanceof Integer) {
					var1[var3] = (int) var4;
				} else if (var4 instanceof Short) {
					var1[var3] = (int) var4;
				} else if (var4 instanceof Byte) {
					var1[var3] = (int) var4;
				} else if (var4 instanceof BigDecimal) {
					var1[var3] = ((BigDecimal) var4).intValue();
				} else {
					if (!(var4 instanceof BigInteger)) {
						throw new IllegalArgumentException("Element " + var3 + " is not convertible to double");
					}

					var1[var3] = ((BigInteger) var4).intValue();
				}
			}
		}

		return var1;
	}

	public static Double convert_double_to_Double(double var0) {
		return new Double(var0);
	}

	public static double convert_Double_to_double(Double var0) {
		return var0;
	}

	public static float convert_double_to_float(double var0) {
		if (var0 > max_float_as_double) {
			throw new IllegalArgumentException("double is too large to be recast as float");
		} else {
			if (!suppressMessage) {
				System.out.println("Class Conv: method convert_double_to_float: possible loss of precision");
			}

			return (new Double(var0)).floatValue();
		}
	}

	public static Float convert_double_to_Float(double var0) {
		if (var0 > max_float_as_double) {
			throw new IllegalArgumentException("double is too large to be recast as float");
		} else {
			if (!suppressMessage) {
				System.out.println("Class Conv: method convert_double_to_Float: possible loss of precision");
			}

			return new Float((new Double(var0)).floatValue());
		}
	}

	public static float convert_Double_to_float(Double var0) {
		double var1 = var0;
		if (var1 > max_float_as_double) {
			throw new IllegalArgumentException("Double is too large to be recast as float");
		} else {
			if (!suppressMessage) {
				System.out.println("Class Conv: method convert_Double_to_float: possible loss of precision");
			}

			return var0.floatValue();
		}
	}

	public static Float convert_Double_to_Float(Double var0) {
		double var1 = var0;
		if (var1 > max_float_as_double) {
			throw new IllegalArgumentException("Double is too large to be recast as Float");
		} else {
			if (!suppressMessage) {
				System.out.println("Class Conv: method convert_Double_to_Float: possible loss of precision");
			}

			return new Float(var1);
		}
	}

	public static long convert_double_to_long(double var0) {
		if (var0 > max_long_as_double) {
			throw new IllegalArgumentException("double is too large to be recast as long");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("double is not, arithmetically, an integer");
		} else {
			return (new Double(var0)).longValue();
		}
	}

	public static Long convert_double_to_Long(double var0) {
		if (var0 > max_long_as_double) {
			throw new IllegalArgumentException("double is too large to be recast as long");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("double is not, arithmetically, an integer");
		} else {
			return new Long((new Double(var0)).longValue());
		}
	}

	public static long convert_Double_to_long(Double var0) {
		double var1 = var0;
		if (var1 > max_long_as_double) {
			throw new IllegalArgumentException("Double is too large to be recast as long");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Double is not, arithmetically, an integer");
		} else {
			return var0.longValue();
		}
	}

	public static Long convert_Double_to_Long(Double var0) {
		double var1 = var0;
		if (var1 > max_long_as_double) {
			throw new IllegalArgumentException("Double is too large to be recast as Long");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Double is not, arithmetically, an integer");
		} else {
			return new Long(var0.longValue());
		}
	}

	public static int convert_double_to_int(double var0) {
		if (var0 > max_int_as_double) {
			throw new IllegalArgumentException("double is too large to be recast as int");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("double is not, arithmetically, an integer");
		} else {
			return (new Double(var0)).intValue();
		}
	}

	public static Integer convert_double_to_Integer(double var0) {
		if (var0 > max_int_as_double) {
			throw new IllegalArgumentException("double is too large to be recast as int");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("double is not, arithmetically, an integer");
		} else {
			return new Integer((new Double(var0)).intValue());
		}
	}

	public static int convert_Double_to_int(Double var0) {
		double var1 = var0;
		if (var1 > max_int_as_double) {
			throw new IllegalArgumentException("Double is too large to be recast as int");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Double is not, arithmetically, an integer");
		} else {
			return var0.intValue();
		}
	}

	public static Integer convert_Double_to_Integer(Double var0) {
		double var1 = var0;
		if (var1 > max_int_as_double) {
			throw new IllegalArgumentException("Double is too large to be recast as Integer");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Double is not, arithmetically, an integer");
		} else {
			return new Integer(var0.intValue());
		}
	}

	public static short convert_double_to_short(double var0) {
		if (var0 > max_short_as_double) {
			throw new IllegalArgumentException("double is too large to be recast as short");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("double is not, arithmetically, an integer");
		} else {
			return (new Double(var0)).shortValue();
		}
	}

	public static Short convert_double_to_Short(double var0) {
		if (var0 > max_short_as_double) {
			throw new IllegalArgumentException("double is too large to be recast as short");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("double is not, arithmetically, an integer");
		} else {
			return new Short((new Double(var0)).shortValue());
		}
	}

	public static short convert_Double_to_short(Double var0) {
		double var1 = var0;
		if (var1 > max_short_as_double) {
			throw new IllegalArgumentException("Double is too large to be recast as short");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Double is not, arithmetically, an integer");
		} else {
			return var0.shortValue();
		}
	}

	public static Short convert_Double_to_Short(Double var0) {
		double var1 = var0;
		if (var1 > max_short_as_double) {
			throw new IllegalArgumentException("Double is too large to be recast as Short");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Double is not, arithmetically, an integer");
		} else {
			return new Short(var0.shortValue());
		}
	}

	public static byte convert_double_to_byte(double var0) {
		if (var0 > max_byte_as_double) {
			throw new IllegalArgumentException("double is too large to be recast as byte");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("double is not, arithmetically, an integer");
		} else {
			return (new Double(var0)).byteValue();
		}
	}

	public static Byte convert_double_to_Byte(double var0) {
		if (var0 > max_byte_as_double) {
			throw new IllegalArgumentException("double is too large to be recast as byte");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("double is not, arithmetically, an integer");
		} else {
			return new Byte((new Double(var0)).byteValue());
		}
	}

	public static byte convert_Double_to_byte(Double var0) {
		double var1 = var0;
		if (var1 > max_byte_as_double) {
			throw new IllegalArgumentException("Double is too large to be recast as byte");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Double is not, arithmetically, an integer");
		} else {
			return var0.byteValue();
		}
	}

	public static Byte convert_Double_to_Byte(Double var0) {
		double var1 = var0;
		if (var1 > max_byte_as_double) {
			throw new IllegalArgumentException("Double is too large to be recast as Byte");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Double is not, arithmetically, an integer");
		} else {
			return new Byte(var0.byteValue());
		}
	}

	public static BigDecimal convert_double_to_BigDecimal(double var0) {
		return new BigDecimal(var0);
	}

	public static BigDecimal convert_Double_to_BigDecimal(Double var0) {
		return new BigDecimal(var0);
	}

	public static BigInteger convert_double_to_BigInteger(double var0) {
		if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("double is not, arithmetically, an integer");
		} else {
			return new BigInteger(Double.toString(var0));
		}
	}

	public static BigInteger convert_Double_to_BigInteger(Double var0) {
		double var1 = var0;
		if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("double is not, arithmetically, an integer");
		} else {
			return new BigInteger(Double.toString(var1).trim());
		}
	}

	public static String convert_double_to_String(double var0) {
		return Double.toString(var0).trim();
	}

	public static String convert_Double_to_String(Double var0) {
		return Double.toString(var0).trim();
	}

	public static Float convert_float_to_Float(float var0) {
		return new Float(var0);
	}

	public static float convert_Float_to_float(Float var0) {
		return var0;
	}

	public static double convert_float_to_double(float var0) {
		return (new Float(var0)).doubleValue();
	}

	public static Double convert_float_to_Double(float var0) {
		return new Double((new Float(var0)).doubleValue());
	}

	public static double convert_Float_to_double(Float var0) {
		return var0.doubleValue();
	}

	public static Double convert_Float_to_Double(Float var0) {
		return new Double(var0.doubleValue());
	}

	public static long convert_float_to_long(float var0) {
		if ((double) var0 > max_long_as_float) {
			throw new IllegalArgumentException("float is too large to be recast as long");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("float is not, arithmetically, an integer");
		} else {
			return (new Float(var0)).longValue();
		}
	}

	public static Long convert_float_to_Long(float var0) {
		if ((double) var0 > max_long_as_float) {
			throw new IllegalArgumentException("float is too large to be recast as long");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("float is not, arithmetically, an integer");
		} else {
			return new Long((new Float(var0)).longValue());
		}
	}

	public static long convert_Float_to_long(Float var0) {
		float var1 = var0;
		if ((double) var1 > max_long_as_float) {
			throw new IllegalArgumentException("Float is too large to be recast as long");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Float is not, arithmetically, an integer");
		} else {
			return var0.longValue();
		}
	}

	public static Long convert_Float_to_Long(Float var0) {
		float var1 = var0;
		if ((double) var1 > max_long_as_float) {
			throw new IllegalArgumentException("Float is too large to be recast as Long");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Float is not, arithmetically, an integer");
		} else {
			return new Long(var0.longValue());
		}
	}

	public static int convert_float_to_int(float var0) {
		if ((double) var0 > max_int_as_float) {
			throw new IllegalArgumentException("double is too large to be recast as int");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("double is not, arithmetically, an integer");
		} else {
			return (new Float(var0)).intValue();
		}
	}

	public static Integer convert_float_to_Integer(float var0) {
		if ((double) var0 > max_int_as_float) {
			throw new IllegalArgumentException("float is too large to be recast as int");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("float is not, arithmetically, an integer");
		} else {
			return new Integer((new Float(var0)).intValue());
		}
	}

	public static int convert_Float_to_int(Float var0) {
		float var1 = var0;
		if ((double) var1 > max_int_as_float) {
			throw new IllegalArgumentException("Float is too large to be recast as int");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Float is not, arithmetically, an integer");
		} else {
			return var0.intValue();
		}
	}

	public static Integer convert_Float_to_Integer(Float var0) {
		float var1 = var0;
		if ((double) var1 > max_int_as_float) {
			throw new IllegalArgumentException("Float is too large to be recast as Integer");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Float is not, arithmetically, an integer");
		} else {
			return new Integer(var0.intValue());
		}
	}

	public static short convert_float_to_short(float var0) {
		if ((double) var0 > max_short_as_float) {
			throw new IllegalArgumentException("float is too large to be recast as short");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("float is not, arithmetically, an integer");
		} else {
			return (new Float(var0)).shortValue();
		}
	}

	public static Short convert_float_to_Short(float var0) {
		if ((double) var0 > max_short_as_float) {
			throw new IllegalArgumentException("float is too large to be recast as short");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("float is not, arithmetically, an integer");
		} else {
			return new Short((new Float(var0)).shortValue());
		}
	}

	public static short convert_Float_to_short(Float var0) {
		float var1 = var0;
		if ((double) var1 > max_short_as_float) {
			throw new IllegalArgumentException("Float is too large to be recast as short");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Float is not, arithmetically, an integer");
		} else {
			return var0.shortValue();
		}
	}

	public static Short convert_Float_to_Short(Float var0) {
		float var1 = var0;
		if ((double) var1 > max_short_as_float) {
			throw new IllegalArgumentException("Float is too large to be recast as Short");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Float is not, arithmetically, an integer");
		} else {
			return new Short(var0.shortValue());
		}
	}

	public static byte convert_float_to_byte(float var0) {
		if ((double) var0 > max_byte_as_float) {
			throw new IllegalArgumentException("float is too large to be recast as byte");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("float is not, arithmetically, an integer");
		} else {
			return (new Float(var0)).byteValue();
		}
	}

	public static Byte convert_float_to_Byte(float var0) {
		if ((double) var0 > max_byte_as_float) {
			throw new IllegalArgumentException("float is too large to be recast as byte");
		} else if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("float is not, arithmetically, an integer");
		} else {
			return new Byte((new Float(var0)).byteValue());
		}
	}

	public static byte convert_Float_to_byte(Float var0) {
		float var1 = var0;
		if ((double) var1 > max_byte_as_float) {
			throw new IllegalArgumentException("Float is too large to be recast as byte");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Float is not, arithmetically, an integer");
		} else {
			return var0.byteValue();
		}
	}

	public static Byte convert_Float_to_Byte(Float var0) {
		float var1 = var0;
		if ((double) var1 > max_byte_as_float) {
			throw new IllegalArgumentException("Float is too large to be recast as Byte");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Float is not, arithmetically, an integer");
		} else {
			return new Byte(var0.byteValue());
		}
	}

	public static BigDecimal convert_float_to_BigDecimal(float var0) {
		return new BigDecimal((double) var0);
	}

	public static BigDecimal convert_Float_to_BigDecimal(Float var0) {
		return new BigDecimal(var0.doubleValue());
	}

	public static BigInteger convert_double_to_BigInteger(float var0) {
		if (!Fmath.isInteger(var0)) {
			throw new IllegalArgumentException("float is not, arithmetically, an integer");
		} else {
			return new BigInteger(Float.toString(var0).trim());
		}
	}

	public static BigInteger convert_Float_to_BigInteger(Float var0) {
		double var1 = var0.doubleValue();
		if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("Float is not, arithmetically, an integer");
		} else {
			return new BigInteger(Double.toString(var1).trim());
		}
	}

	public static String convert_float_to_String(float var0) {
		return Float.toString(var0).trim();
	}

	public static String convert_Float_to_String(Float var0) {
		return Float.toString(var0).trim();
	}

	public static Long convert_long_to_Long(long var0) {
		return new Long(var0);
	}

	public static long convert_Long_to_long(Long var0) {
		return var0;
	}

	public static double convert_long_to_double(long var0) {
		if (!suppressMessage) {
			System.out.println("Class Conv: method convert_long_to_double: possible loss of precision");
		}

		return (new Long(var0)).doubleValue();
	}

	public static Double convert_long_to_Double(long var0) {
		if (!suppressMessage) {
			System.out.println("Class Conv: method convert_long_to_Double: possible loss of precision");
		}

		return new Double((new Long(var0)).doubleValue());
	}

	public static double convert_Long_to_double(Long var0) {
		if (!suppressMessage) {
			System.out.println("Class Conv: method convert_Long_to_double: possible loss of precision");
		}

		return var0.doubleValue();
	}

	public static Double convert_Long_to_Double(Long var0) {
		if (!suppressMessage) {
			System.out.println("Class Conv: method convert_Long_to_Double: possible loss of precision");
		}

		return new Double(var0.doubleValue());
	}

	public static float convert_long_to_float(long var0) {
		if (!suppressMessage) {
			System.out.println("Class Conv: method convert_long_to_float: possible loss of precision");
		}

		return (new Long(var0)).floatValue();
	}

	public static Float convert_long_to_Float(long var0) {
		if (!suppressMessage) {
			System.out.println("Class Conv: method convert_long_to_Float: possible loss of precision");
		}

		return new Float((new Long(var0)).floatValue());
	}

	public static float convert_Long_to_float(Long var0) {
		if (!suppressMessage) {
			System.out.println("Class Conv: method convert_Long_to_float: possible loss of precision");
		}

		return var0.floatValue();
	}

	public static Float convert_Long_to_Float(Long var0) {
		if (!suppressMessage) {
			System.out.println("Class Conv: method convert_Long_to_Float: possible loss of precision");
		}

		return new Float(var0.floatValue());
	}

	public static int convert_long_to_int(long var0) {
		if ((double) var0 > max_int_as_long) {
			throw new IllegalArgumentException("long is too large to be recast as int");
		} else {
			return (new Float((float) var0)).intValue();
		}
	}

	public static Integer convert_long_to_Integer(long var0) {
		if ((double) var0 > max_int_as_long) {
			throw new IllegalArgumentException("long is too large to be recast as Integer");
		} else {
			return new Integer((new Long(var0)).intValue());
		}
	}

	public static int convert_Long_to_int(Long var0) {
		long var1 = var0;
		if ((double) var1 > max_int_as_long) {
			throw new IllegalArgumentException("Long is too large to be recast as int");
		} else {
			return var0.intValue();
		}
	}

	public static Integer convert_Long_to_Integer(Long var0) {
		long var1 = var0;
		if ((double) var1 > max_int_as_long) {
			throw new IllegalArgumentException("Long is too large to be recast as Integer");
		} else {
			return new Integer(var0.intValue());
		}
	}

	public static short convert_long_to_short(long var0) {
		if ((double) var0 > max_short_as_long) {
			throw new IllegalArgumentException("long is too large to be recast as short");
		} else {
			return (new Long(var0)).shortValue();
		}
	}

	public static Short convert_long_to_Short(long var0) {
		if ((double) var0 > max_short_as_long) {
			throw new IllegalArgumentException("long is too large to be recast as Short");
		} else {
			return new Short((new Long(var0)).shortValue());
		}
	}

	public static short convert_Long_to_short(Long var0) {
		long var1 = var0;
		if ((double) var1 > max_short_as_long) {
			throw new IllegalArgumentException("Long is too large to be recast as short");
		} else {
			return var0.shortValue();
		}
	}

	public static Short convert_Long_to_Short(Long var0) {
		long var1 = var0;
		if ((double) var1 > max_short_as_long) {
			throw new IllegalArgumentException("Long is too large to be recast as Short");
		} else {
			return new Short(var0.shortValue());
		}
	}

	public static byte convert_long_to_byte(long var0) {
		if ((double) var0 > max_byte_as_long) {
			throw new IllegalArgumentException("long is too large to be recast as byte");
		} else {
			return (new Long(var0)).byteValue();
		}
	}

	public static Byte convert_long_to_Byte(long var0) {
		if ((double) var0 > max_byte_as_long) {
			throw new IllegalArgumentException("long is too large to be recast as Byte");
		} else {
			return new Byte((new Long(var0)).byteValue());
		}
	}

	public static byte convert_Long_to_byte(Long var0) {
		long var1 = var0;
		if ((double) var1 > max_byte_as_long) {
			throw new IllegalArgumentException("Long is too large to be recast as byte");
		} else {
			return var0.byteValue();
		}
	}

	public static Byte convert_Long_to_Byte(Long var0) {
		long var1 = var0;
		if ((double) var1 > max_byte_as_long) {
			throw new IllegalArgumentException("Long is too large to be recast as Byte");
		} else {
			return new Byte(var0.byteValue());
		}
	}

	public static BigDecimal convert_long_to_BigDecimal(long var0) {
		return new BigDecimal((new Long(var0)).toString());
	}

	public static BigDecimal convert_Long_to_BigDecimal(Long var0) {
		return new BigDecimal(var0.toString().trim());
	}

	public static BigInteger convert_long_to_BigInteger(long var0) {
		return new BigInteger(Long.toString(var0).trim());
	}

	public static BigInteger convert_Long_to_BigInteger(Long var0) {
		return new BigInteger(var0.toString().trim());
	}

	public static String convert_long_to_String(long var0) {
		return Long.toString(var0).trim();
	}

	public static String convert_Long_to_String(Long var0) {
		return Long.toString(var0).trim();
	}

	public static Integer convert_int_to_Integer(int var0) {
		return new Integer(var0);
	}

	public static int convert_Integer_to_int(Integer var0) {
		return var0;
	}

	public static double convert_int_to_double(int var0) {
		return (new Integer(var0)).doubleValue();
	}

	public static Double convert_int_to_Double(int var0) {
		return new Double((new Integer(var0)).doubleValue());
	}

	public static double convert_Integer_to_double(Integer var0) {
		return var0.doubleValue();
	}

	public static Double convert_Integer_to_Double(Integer var0) {
		return new Double(var0.doubleValue());
	}

	public static float convert_int_to_float(int var0) {
		if (!suppressMessage) {
			System.out.println("Class Conv: method convert_int_to_float: possible loss of precision");
		}

		return (new Integer(var0)).floatValue();
	}

	public static Float convert_int_to_Float(int var0) {
		if (!suppressMessage) {
			System.out.println("Class Conv: method convert_int_to_Float: possible loss of precision");
		}

		return new Float((new Integer(var0)).floatValue());
	}

	public static float convert_Integer_to_float(Integer var0) {
		if (!suppressMessage) {
			System.out.println("Class Conv: method convert_Integer_to_float: possible loss of precision");
		}

		return var0.floatValue();
	}

	public static Float convert_Integer_to_Float(Integer var0) {
		if (!suppressMessage) {
			System.out.println("Class Conv: method convert_Integer_to_Float: possible loss of precision");
		}

		return new Float(var0.floatValue());
	}

	public static long convert_int_to_long(int var0) {
		return (new Integer(var0)).longValue();
	}

	public static Long convert_int_to_Long(int var0) {
		return new Long((new Integer(var0)).longValue());
	}

	public static long convert_Integer_to_long(Integer var0) {
		return var0.longValue();
	}

	public static Long convert_Integer_to_Long(Integer var0) {
		return new Long(var0.longValue());
	}

	public static short convert_int_to_short(int var0) {
		if ((double) var0 > max_short_as_int) {
			throw new IllegalArgumentException("int is too large to be recast as short");
		} else {
			return (new Integer(var0)).shortValue();
		}
	}

	public static Short convert_int_to_Short(int var0) {
		if ((double) var0 > max_short_as_int) {
			throw new IllegalArgumentException("int is too large to be recast as Short");
		} else {
			return new Short((new Integer(var0)).shortValue());
		}
	}

	public static short convert_Integer_to_short(Integer var0) {
		int var1 = var0;
		if ((double) var1 > max_short_as_int) {
			throw new IllegalArgumentException("Integer is too large to be recast as short");
		} else {
			return var0.shortValue();
		}
	}

	public static Short convert_Integer_to_Short(Integer var0) {
		int var1 = var0;
		if ((double) var1 > max_short_as_int) {
			throw new IllegalArgumentException("Integer is too large to be recast as Short");
		} else {
			return new Short(var0.shortValue());
		}
	}

	public static byte convert_int_to_byte(int var0) {
		if ((double) var0 > max_byte_as_int) {
			throw new IllegalArgumentException("int is too large to be recast as byte");
		} else {
			return (new Integer(var0)).byteValue();
		}
	}

	public static Byte convert_int_to_Byte(int var0) {
		if ((double) var0 > max_byte_as_int) {
			throw new IllegalArgumentException("int is too large to be recast as Byte");
		} else {
			return new Byte((new Integer(var0)).byteValue());
		}
	}

	public static byte convert_Integer_to_byte(Integer var0) {
		int var1 = var0;
		if ((double) var1 > max_byte_as_int) {
			throw new IllegalArgumentException("Integer is too large to be recast as byte");
		} else {
			return var0.byteValue();
		}
	}

	public static Byte convert_Integer_to_Byte(Integer var0) {
		int var1 = var0;
		if ((double) var1 > max_byte_as_int) {
			throw new IllegalArgumentException("Integer is too large to be recast as Byte");
		} else {
			return new Byte(var0.byteValue());
		}
	}

	public static BigDecimal convert_int_to_BigDecimal(int var0) {
		return new BigDecimal((new Integer(var0)).toString());
	}

	public static BigDecimal convert_Integer_to_BigDecimal(Integer var0) {
		return new BigDecimal(var0.toString().trim());
	}

	public static BigInteger convert_int_to_BigInteger(int var0) {
		return new BigInteger(Long.toString((long) var0).trim());
	}

	public static BigInteger convert_Integer_to_BigInteger(Integer var0) {
		return new BigInteger(var0.toString().trim());
	}

	public static String convert_int_to_String(int var0) {
		return Integer.toString(var0).trim();
	}

	public static String convert_Integer_to_String(Integer var0) {
		return Integer.toString(var0).trim();
	}

	public static char convert_int_to_char(int var0) {
		return (char) var0;
	}

	public static Short convert_short_to_Short(short var0) {
		return new Short(var0);
	}

	public static short convert_Short_to_short(Short var0) {
		return var0;
	}

	public static double convert_short_to_double(short var0) {
		return (new Short(var0)).doubleValue();
	}

	public static Double convert_short_to_Double(short var0) {
		return new Double((new Short(var0)).doubleValue());
	}

	public static double convert_Short_to_double(Short var0) {
		return var0.doubleValue();
	}

	public static Double convert_Short_to_Double(Short var0) {
		return new Double(var0.doubleValue());
	}

	public static float convert_short_to_float(short var0) {
		return (new Short(var0)).floatValue();
	}

	public static Float convert_short_to_Float(short var0) {
		return new Float((new Short(var0)).floatValue());
	}

	public static float convert_Short_to_float(Short var0) {
		return var0.floatValue();
	}

	public static Float convert_Short_to_Float(Short var0) {
		return new Float(var0.floatValue());
	}

	public static long convert_short_to_long(short var0) {
		return (new Short(var0)).longValue();
	}

	public static Long convert_short_to_Long(short var0) {
		return new Long((new Short(var0)).longValue());
	}

	public static long convert_Short_to_long(Short var0) {
		return var0.longValue();
	}

	public static Long convert_Short_to_Long(Short var0) {
		return new Long(var0.longValue());
	}

	public static int convert_short_to_int(short var0) {
		return (new Short(var0)).intValue();
	}

	public static Integer convert_short_to_Integer(short var0) {
		return new Integer((new Short(var0)).intValue());
	}

	public static int convert_Short_to_int(Short var0) {
		return var0.intValue();
	}

	public static Integer convert_Short_to_Integer(Short var0) {
		return new Integer(var0.intValue());
	}

	public static byte convert_short_to_byte(short var0) {
		if ((double) var0 > max_byte_as_short) {
			throw new IllegalArgumentException("short is too large to be recast as byte");
		} else {
			return (new Short(var0)).byteValue();
		}
	}

	public static Byte convert_short_to_Byte(short var0) {
		if ((double) var0 > max_byte_as_short) {
			throw new IllegalArgumentException("short is too large to be recast as Byte");
		} else {
			return new Byte((new Short(var0)).byteValue());
		}
	}

	public static byte convert_Short_to_byte(Short var0) {
		short var1 = var0;
		if ((double) var1 > max_byte_as_short) {
			throw new IllegalArgumentException("Short is too large to be recast as byte");
		} else {
			return var0.byteValue();
		}
	}

	public static Byte convert_Short_to_Byte(Short var0) {
		short var1 = var0;
		if ((double) var1 > max_byte_as_short) {
			throw new IllegalArgumentException("Short is too large to be recast as Byte");
		} else {
			return new Byte(var0.byteValue());
		}
	}

	public static BigDecimal convert_short_to_BigDecimal(short var0) {
		return new BigDecimal((new Short(var0)).toString().trim());
	}

	public static BigDecimal convert_Short_to_BigDecimal(Short var0) {
		return new BigDecimal(var0.toString().trim());
	}

	public static BigInteger convert_short_to_BigInteger(short var0) {
		return new BigInteger(Short.toString(var0).trim());
	}

	public static BigInteger convert_Short_to_BigInteger(Short var0) {
		return new BigInteger(var0.toString().trim());
	}

	public static String convert_short_to_String(short var0) {
		return Short.toString(var0).trim();
	}

	public static String convert_Short_to_String(Short var0) {
		return Short.toString(var0).trim();
	}

	public static Byte convert_byte_to_Byte(byte var0) {
		return new Byte(var0);
	}

	public static byte convert_Byte_to_byte(Byte var0) {
		return var0;
	}

	public static double convert_byte_to_double(byte var0) {
		return (new Byte(var0)).doubleValue();
	}

	public static Double convert_byte_to_Double(byte var0) {
		return new Double((new Byte(var0)).doubleValue());
	}

	public static double convert_Byte_to_double(Byte var0) {
		return var0.doubleValue();
	}

	public static Double convert_Byte_to_Double(Byte var0) {
		return new Double(var0.doubleValue());
	}

	public static float convert_byte_to_float(byte var0) {
		return (new Byte(var0)).floatValue();
	}

	public static Float convert_byte_to_Float(byte var0) {
		return new Float((new Byte(var0)).floatValue());
	}

	public static float convert_Byte_to_float(Byte var0) {
		return var0.floatValue();
	}

	public static Float convert_Byte_to_Float(Byte var0) {
		return new Float(var0.floatValue());
	}

	public static long convert_byte_to_long(byte var0) {
		return (new Byte(var0)).longValue();
	}

	public static Long convert_byte_to_Long(byte var0) {
		return new Long((new Byte(var0)).longValue());
	}

	public static long convert_Byte_to_long(Byte var0) {
		return var0.longValue();
	}

	public static Long convert_Byte_to_Long(Byte var0) {
		return new Long(var0.longValue());
	}

	public static int convert_byte_to_int(byte var0) {
		return (new Byte(var0)).intValue();
	}

	public static Integer convert_byte_to_Integer(byte var0) {
		return new Integer((new Byte(var0)).intValue());
	}

	public static int convert_Byte_to_int(Byte var0) {
		return var0.intValue();
	}

	public static Integer convert_Byte_to_Integer(Byte var0) {
		return new Integer(var0.intValue());
	}

	public static short convert_byte_to_short(byte var0) {
		return (new Byte(var0)).shortValue();
	}

	public static Short convert_byte_to_Short(byte var0) {
		return new Short((new Byte(var0)).shortValue());
	}

	public static short convert_Byte_to_short(Byte var0) {
		return var0.shortValue();
	}

	public static Short convert_Byte_to_Short(Byte var0) {
		return new Short(var0.shortValue());
	}

	public static BigDecimal convert_byte_to_BigDecimal(byte var0) {
		return new BigDecimal((new Byte(var0)).toString().trim());
	}

	public static BigDecimal convert_Byte_to_BigDecimal(Byte var0) {
		return new BigDecimal(var0.toString().trim());
	}

	public static BigInteger convert_byte_to_BigInteger(byte var0) {
		return new BigInteger(Byte.toString(var0).trim());
	}

	public static BigInteger convert_Byte_to_BigInteger(Byte var0) {
		return new BigInteger(var0.toString().trim());
	}

	public static String convert_byte_to_String(byte var0) {
		return Byte.toString(var0).trim();
	}

	public static String convert_Byte_to_String(Byte var0) {
		return Byte.toString(var0).trim();
	}

	public static double convert_BigDecimal_to_double(BigDecimal var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as double");
		} else {
			if (!suppressMessage) {
				System.out.println("Class Conv: method convert_BigDecimal_to_double: possible loss of precision");
			}

			return var1;
		}
	}

	public static Double convert_BigDecimal_to_Double(BigDecimal var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as double");
		} else {
			if (!suppressMessage) {
				System.out.println("Class Conv: method convert_BigDecimal_to_double: possible loss of precision");
			}

			return new Double(var1);
		}
	}

	public static float convert_BigDecimal_to_float(BigDecimal var0) {
		float var1 = var0.floatValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as float");
		} else {
			if (!suppressMessage) {
				System.out.println("Class Conv: method convert_BigDecimal_to_float: possible loss of precision");
			}

			return var1;
		}
	}

	public static Float convert_BigDecimal_to_Float(BigDecimal var0) {
		float var1 = var0.floatValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as float");
		} else {
			if (!suppressMessage) {
				System.out.println("Class Conv: method convert_BigDecimal_to_float: possible loss of precision");
			}

			return new Float(var1);
		}
	}

	public static long convert_BigDecimal_to_long(BigDecimal var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as long");
		} else if (var1 > max_long_as_double) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as long");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
		} else {
			return var0.longValue();
		}
	}

	public static Long convert_BigDecimal_to_Long(BigDecimal var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as Long");
		} else if (var1 > max_long_as_double) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as Long");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
		} else {
			return new Long(var0.longValue());
		}
	}

	public static int convert_BigDecimal_to_int(BigDecimal var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as int");
		} else if (var1 > max_int_as_double) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as int");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
		} else {
			return var0.intValue();
		}
	}

	public static Integer convert_BigDecimal_to_Integer(BigDecimal var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as Integer");
		} else if (var1 > max_int_as_double) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as Integer");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
		} else {
			return new Integer(var0.intValue());
		}
	}

	public static short convert_BigDecimal_to_short(BigDecimal var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as short");
		} else if (var1 > max_short_as_double) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as short");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
		} else {
			return var0.shortValue();
		}
	}

	public static Short convert_BigDecimal_to_Short(BigDecimal var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as Short");
		} else if (var1 > max_short_as_double) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as Short");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
		} else {
			return new Short(var0.shortValue());
		}
	}

	public static byte convert_BigDecimal_to_byte(BigDecimal var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as byte");
		} else if (var1 > max_byte_as_double) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as byte");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
		} else {
			return var0.byteValue();
		}
	}

	public static Byte convert_BigDecimal_to_Byte(BigDecimal var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as Byte");
		} else if (var1 > max_byte_as_double) {
			throw new IllegalArgumentException("BigDecimal is too large to be recast as Byte");
		} else if (!Fmath.isInteger(var1)) {
			throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
		} else {
			return new Byte(var0.byteValue());
		}
	}

	public static BigInteger convert_BigDecimal_to_BigInteger(BigDecimal var0) {
		String var1 = var0.toString().trim();
		int var2 = var1.indexOf(46);
		int var3 = var1.indexOf(69);
		String var4 = null;
		if (var2 == -1) {
			return var0.toBigInteger();
		} else {
			if (var3 == -1) {
				var4 = var1.substring(var2 + 1);
			} else {
				var4 = var1.substring(var2 + 1, var3);
			}

			int var5 = var4.length();
			boolean var6 = true;
			boolean var7 = true;
			int var8 = 0;

			while (var6) {
				if (var4.charAt(var8) != '0') {
					var6 = false;
					var7 = false;
				} else {
					++var8;
					if (var8 == var5) {
						var6 = false;
					}
				}
			}

			if (var7) {
				return var0.toBigInteger();
			} else {
				throw new IllegalArgumentException("BigDecimal is not, arithmetically, an integer");
			}
		}
	}

	public static String convert_BigDecimal_to_String(BigDecimal var0) {
		return var0.toEngineeringString().trim();
	}

	public static double convert_BigInteger_to_double(BigInteger var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as double");
		} else {
			if (!suppressMessage) {
				System.out.println("Class Conv: method convert_BigInteger_to_double: possible loss of precision");
			}

			return var1;
		}
	}

	public static Double convert_BigInteger_to_Double(BigInteger var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as double");
		} else {
			if (!suppressMessage) {
				System.out.println("Class Conv: method convert_BigInteger_to_double: possible loss of precision");
			}

			return new Double(var1);
		}
	}

	public static float convert_BigInteger_to_float(BigInteger var0) {
		float var1 = var0.floatValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as float");
		} else {
			if (!suppressMessage) {
				System.out.println("Class Conv: method convert_BigInteger_to_float: possible loss of precision");
			}

			return var1;
		}
	}

	public static Float convert_BigInteger_to_Float(BigInteger var0) {
		float var1 = var0.floatValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as float");
		} else {
			if (!suppressMessage) {
				System.out.println("Class Conv: method convert_BigInteger_to_float: possible loss of precision");
			}

			return new Float(var1);
		}
	}

	public static long convert_BigInteger_to_long(BigInteger var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as long");
		} else if (var1 > max_long_as_double) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as long");
		} else {
			return var0.longValue();
		}
	}

	public static Long convert_BigInteger_to_Long(BigInteger var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as Long");
		} else if (var1 > max_long_as_double) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as Long");
		} else {
			return new Long(var0.longValue());
		}
	}

	public static int convert_BigInteger_to_int(BigInteger var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as int");
		} else if (var1 > max_int_as_double) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as int");
		} else {
			return var0.intValue();
		}
	}

	public static Integer convert_BigInteger_to_Integer(BigInteger var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as Integer");
		} else if (var1 > max_int_as_double) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as Integer");
		} else {
			return new Integer(var0.intValue());
		}
	}

	public static short convert_BigInteger_to_short(BigInteger var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as short");
		} else if (var1 > max_short_as_double) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as short");
		} else {
			return var0.shortValue();
		}
	}

	public static Short convert_BigInteger_to_Short(BigInteger var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as Short");
		} else if (var1 > max_short_as_double) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as Short");
		} else {
			return new Short(var0.shortValue());
		}
	}

	public static byte convert_BigInteger_to_byte(BigInteger var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as byte");
		} else if (var1 > max_byte_as_double) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as byte");
		} else {
			return var0.byteValue();
		}
	}

	public static Byte convert_BigInteger_to_Byte(BigInteger var0) {
		double var1 = var0.doubleValue();
		if (Fmath.isInfinity(var1)) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as Byte");
		} else if (var1 > max_byte_as_double) {
			throw new IllegalArgumentException("BigInteger is too large to be recast as Byte");
		} else {
			return new Byte(var0.byteValue());
		}
	}

	public static BigDecimal convert_BigInteger_to_BigDecimal(BigInteger var0) {
		return new BigDecimal(var0);
	}

	public static String convert_BigInteger_to_String(BigInteger var0) {
		return var0.toString().trim();
	}

	public static String convert_Complex_to_String(Complex var0) {
		return var0.toString().trim();
	}

	public static double convert_char_to_double(char var0) {
		return convert_int_to_double(var0);
	}

	public static Double convert_char_to_Double(char var0) {
		return convert_int_to_Double(var0);
	}

	public static double convert_Character_to_double(Character var0) {
		int var1 = Character.getNumericValue(var0);
		return convert_int_to_double(var1);
	}

	public static Double convert_Character_to_Double(Character var0) {
		int var1 = Character.getNumericValue(var0);
		return convert_int_to_Double(var1);
	}

	public static float convert_char_to_float(char var0) {
		return convert_int_to_float(var0);
	}

	public static Float convert_char_to_Float(char var0) {
		return convert_int_to_Float(var0);
	}

	public static float convert_Character_to_float(Character var0) {
		int var1 = Character.getNumericValue(var0);
		return convert_int_to_float(var1);
	}

	public static Float convert_Character_to_Float(Character var0) {
		int var1 = Character.getNumericValue(var0);
		return convert_int_to_Float(var1);
	}

	public static long convert_char_to_long(char var0) {
		return convert_int_to_long(var0);
	}

	public static Long convert_char_to_Long(char var0) {
		return convert_int_to_Long(var0);
	}

	public static long convert_Character_to_long(Character var0) {
		int var1 = Character.getNumericValue(var0);
		return convert_int_to_long(var1);
	}

	public static Long convert_Character_to_Long(Character var0) {
		int var1 = Character.getNumericValue(var0);
		return convert_int_to_Long(var1);
	}

	public static int convert_char_to_int(char var0) {
		return var0;
	}

	public static Integer convert_char_to_Integer(char var0) {
		return new Integer(var0);
	}

	public static int convert_Character_to_int(Character var0) {
		return Character.getNumericValue(var0);
	}

	public static Integer convert_Character_to_Integer(Character var0) {
		int var1 = Character.getNumericValue(var0);
		return new Integer(var1);
	}

	public static short convert_char_to_short(char var0) {
		return convert_int_to_short(var0);
	}

	public static Short convert_char_to_Short(char var0) {
		return convert_int_to_Short(var0);
	}

	public static short convert_Character_to_short(Character var0) {
		int var1 = Character.getNumericValue(var0);
		return convert_int_to_short(var1);
	}

	public static Short convert_Character_to_Short(Character var0) {
		int var1 = Character.getNumericValue(var0);
		return convert_int_to_Short(var1);
	}

	public static byte convert_char_to_byte(char var0) {
		return convert_int_to_byte(var0);
	}

	public static Byte convert_char_to_Byte(char var0) {
		return convert_int_to_Byte(var0);
	}

	public static byte convert_Character_to_byte(Character var0) {
		int var1 = Character.getNumericValue(var0);
		return convert_int_to_byte(var1);
	}

	public static Byte convert_Character_to_Byte(Character var0) {
		int var1 = Character.getNumericValue(var0);
		return convert_int_to_Byte(var1);
	}

	public static String convert_char_to_String(char var0) {
		return Character.toString(var0).trim();
	}

	public static String convert_Character_to_String(Character var0) {
		return var0.toString().trim();
	}

	public static Character convert_char_to_Character(char var0) {
		return new Character(var0);
	}

	public static char convert_Character_to_char(Character var0) {
		return var0;
	}

	public static BigDecimal convert_char_to_BigDecimal(char var0) {
		return new BigDecimal(convert_char_to_String(var0).trim());
	}

	public static BigDecimal convert_Character_to_BigDecimal(Character var0) {
		return new BigDecimal(convert_Character_to_String(var0).trim());
	}

	public static BigInteger convert_char_to_BigInteger(char var0) {
		return new BigInteger(convert_char_to_String(var0).trim());
	}

	public static BigInteger convert_Character_to_BigInteger(Character var0) {
		return new BigInteger(convert_char_to_String(var0).trim());
	}

	public static double convert_String_to_double(String var0) {
		return Double.parseDouble(var0.trim());
	}

	public static Double convert_String_to_Double(String var0) {
		return Double.valueOf(var0.trim());
	}

	public static float convert_String_to_float(String var0) {
		return Float.parseFloat(var0.trim());
	}

	public static Float convert_String_to_Float(String var0) {
		return Float.valueOf(var0.trim());
	}

	public static long convert_String_to_long(String var0) {
		return Long.parseLong(var0.trim());
	}

	public static Long convert_String_to_Long(String var0) {
		return Long.valueOf(var0.trim());
	}

	public static int convert_String_to_int(String var0) {
		return Integer.parseInt(var0.trim());
	}

	public static Integer convert_String_to_Integer(String var0) {
		return Integer.valueOf(var0.trim());
	}

	public static short convert_String_to_short(String var0) {
		return Short.parseShort(var0.trim());
	}

	public static Short convert_String_to_Short(String var0) {
		return Short.valueOf(var0.trim());
	}

	public static byte convert_String_to_byte(String var0) {
		return Byte.parseByte(var0.trim());
	}

	public static Byte convert_String_to_Byte(String var0) {
		return Byte.valueOf(var0.trim());
	}

	public static BigDecimal convert_String_to_BigDecimal(String var0) {
		return new BigDecimal(var0.trim());
	}

	public static BigInteger convert_String_to_BigInteger(String var0) {
		return new BigInteger(var0.trim());
	}

	public static String toTitleCase(String var0) {
		StringTokenizer var1 = new StringTokenizer(var0);
		String var2 = "";
		int var3 = var1.countTokens();
		String[] var4 = new String[var3];
		int[] var5 = new int[var3];
		int[] var6 = new int[var3];
		int[] var7 = new int[var3];
		int[] var8 = new int[var3];

		int var9;
		for (var9 = 0; var9 < var3; ++var9) {
			var4[var9] = var1.nextToken();
			var6[var9] = var4[var9].length();
			var5[var9] = var0.indexOf(var4[var9]);
			var7[var9] = var5[var9] + var6[var9];
			var4[var9] = var4[var9].substring(0, 1).toUpperCase() + var4[var9].substring(1).toLowerCase();
		}

		for (var9 = 1; var9 < var3; ++var9) {
			var8[var9 - 1] = var5[var9] - var7[var9 - 1];
		}

		var8[var3 - 1] = var0.length() - var7[var3 - 1] - 1;
		if (var5[0] > 0) {
			for (var9 = 0; var9 < var5[0]; ++var9) {
				var2 = var2 + " ";
			}
		}

		for (var9 = 0; var9 < var3; ++var9) {
			var2 = var2 + var4[var9];

			for (int var10 = 0; var10 < var8[var9]; ++var10) {
				var2 = var2 + " ";
			}
		}

		return var2;
	}

	public static double[] copy(double[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			double[] var2 = new double[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static float[] copy(float[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			float[] var2 = new float[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static int[] copy(int[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			int[] var2 = new int[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static long[] copy(long[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			long[] var2 = new long[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static double[][] copy(double[][] var0) {
		if (var0 == null) {
			return (double[][]) null;
		} else {
			int var1 = var0.length;
			double[][] var2 = new double[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new double[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static float[][] copy(float[][] var0) {
		if (var0 == null) {
			return (float[][]) null;
		} else {
			int var1 = var0.length;
			float[][] var2 = new float[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new float[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static int[][] copy(int[][] var0) {
		if (var0 == null) {
			return (int[][]) null;
		} else {
			int var1 = var0.length;
			int[][] var2 = new int[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new int[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static long[][] copy(long[][] var0) {
		if (var0 == null) {
			return (long[][]) null;
		} else {
			int var1 = var0.length;
			long[][] var2 = new long[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new long[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static double[][][] copy(double[][][] var0) {
		if (var0 == null) {
			return (double[][][]) null;
		} else {
			int var1 = var0.length;
			double[][][] var2 = new double[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new double[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new double[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static float[][][] copy(float[][][] var0) {
		if (var0 == null) {
			return (float[][][]) null;
		} else {
			int var1 = var0.length;
			float[][][] var2 = new float[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new float[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new float[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static int[][][] copy(int[][][] var0) {
		if (var0 == null) {
			return (int[][][]) null;
		} else {
			int var1 = var0.length;
			int[][][] var2 = new int[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new int[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new int[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static long[][][] copy(long[][][] var0) {
		if (var0 == null) {
			return (long[][][]) null;
		} else {
			int var1 = var0.length;
			long[][][] var2 = new long[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new long[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new long[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static double[][][][] copy(double[][][][] var0) {
		if (var0 == null) {
			return (double[][][][]) null;
		} else {
			int var1 = var0.length;
			double[][][][] var2 = new double[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new double[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new double[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new double[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static float[][][][] copy(float[][][][] var0) {
		if (var0 == null) {
			return (float[][][][]) null;
		} else {
			int var1 = var0.length;
			float[][][][] var2 = new float[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new float[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new float[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new float[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static int[][][][] copy(int[][][][] var0) {
		if (var0 == null) {
			return (int[][][][]) null;
		} else {
			int var1 = var0.length;
			int[][][][] var2 = new int[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new int[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new int[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new int[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static long[][][][] copy(long[][][][] var0) {
		if (var0 == null) {
			return (long[][][][]) null;
		} else {
			int var1 = var0.length;
			long[][][][] var2 = new long[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new long[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new long[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new long[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static String[] copy(String[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			String[] var2 = new String[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static String[][] copy(String[][] var0) {
		if (var0 == null) {
			return (String[][]) null;
		} else {
			int var1 = var0.length;
			String[][] var2 = new String[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new String[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static String[][][] copy(String[][][] var0) {
		if (var0 == null) {
			return (String[][][]) null;
		} else {
			int var1 = var0.length;
			String[][][] var2 = new String[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new String[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new String[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static String[][][][] copy(String[][][][] var0) {
		if (var0 == null) {
			return (String[][][][]) null;
		} else {
			int var1 = var0.length;
			String[][][][] var2 = new String[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new String[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new String[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new String[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static boolean[] copy(boolean[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			boolean[] var2 = new boolean[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static boolean[][] copy(boolean[][] var0) {
		if (var0 == null) {
			return (boolean[][]) null;
		} else {
			int var1 = var0.length;
			boolean[][] var2 = new boolean[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new boolean[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static boolean[][][] copy(boolean[][][] var0) {
		if (var0 == null) {
			return (boolean[][][]) null;
		} else {
			int var1 = var0.length;
			boolean[][][] var2 = new boolean[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new boolean[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new boolean[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static boolean[][][][] copy(boolean[][][][] var0) {
		if (var0 == null) {
			return (boolean[][][][]) null;
		} else {
			int var1 = var0.length;
			boolean[][][][] var2 = new boolean[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new boolean[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new boolean[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new boolean[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static char[] copy(char[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			char[] var2 = new char[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static char[][] copy(char[][] var0) {
		if (var0 == null) {
			return (char[][]) null;
		} else {
			int var1 = var0.length;
			char[][] var2 = new char[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new char[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static char[][][] copy(char[][][] var0) {
		if (var0 == null) {
			return (char[][][]) null;
		} else {
			int var1 = var0.length;
			char[][][] var2 = new char[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new char[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new char[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static char[][][][] copy(char[][][][] var0) {
		if (var0 == null) {
			return (char[][][][]) null;
		} else {
			int var1 = var0.length;
			char[][][][] var2 = new char[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new char[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new char[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new char[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static Character[] copy(Character[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			Character[] var2 = new Character[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static Character[][] copy(Character[][] var0) {
		if (var0 == null) {
			return (Character[][]) null;
		} else {
			int var1 = var0.length;
			Character[][] var2 = new Character[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Character[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static Character[][][] copy(Character[][][] var0) {
		if (var0 == null) {
			return (Character[][][]) null;
		} else {
			int var1 = var0.length;
			Character[][][] var2 = new Character[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Character[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Character[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static Character[][][][] copy(Character[][][][] var0) {
		if (var0 == null) {
			return (Character[][][][]) null;
		} else {
			int var1 = var0.length;
			Character[][][][] var2 = new Character[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Character[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Character[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new Character[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static Complex[] copy(Complex[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			Complex[] var2 = new Complex[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = Complex.of(var0[var3]) ;
			}

			return var2;
		}
	}

	public static Complex[][] copy(Complex[][] var0) {
		if (var0 == null) {
			return (Complex[][]) null;
		} else {
			int var1 = var0.length;
			Complex[][] var2 = new Complex[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Complex[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5].copy();
				}
			}

			return var2;
		}
	}

	public static Complex[][][] copy(Complex[][][] var0) {
		if (var0 == null) {
			return (Complex[][][]) null;
		} else {
			int var1 = var0.length;
			Complex[][][] var2 = new Complex[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Complex[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Complex[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7].copy();
					}
				}
			}

			return var2;
		}
	}

	public static Complex[][][][] copy(Complex[][][][] var0) {
		if (var0 == null) {
			return (Complex[][][][]) null;
		} else {
			int var1 = var0.length;
			Complex[][][][] var2 = new Complex[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Complex[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Complex[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new Complex[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9].copy();
						}
					}
				}
			}

			return var2;
		}
	}

	public static BigDecimal[] copy(BigDecimal[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			BigDecimal[] var2 = new BigDecimal[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static BigDecimal[][] copy(BigDecimal[][] var0) {
		if (var0 == null) {
			return (BigDecimal[][]) null;
		} else {
			int var1 = var0.length;
			BigDecimal[][] var2 = new BigDecimal[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new BigDecimal[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static BigDecimal[][][] copy(BigDecimal[][][] var0) {
		if (var0 == null) {
			return (BigDecimal[][][]) null;
		} else {
			int var1 = var0.length;
			BigDecimal[][][] var2 = new BigDecimal[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new BigDecimal[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new BigDecimal[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static BigDecimal[][][][] copy(BigDecimal[][][][] var0) {
		if (var0 == null) {
			return (BigDecimal[][][][]) null;
		} else {
			int var1 = var0.length;
			BigDecimal[][][][] var2 = new BigDecimal[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new BigDecimal[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new BigDecimal[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new BigDecimal[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static BigInteger[] copy(BigInteger[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			BigInteger[] var2 = new BigInteger[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static BigInteger[][] copy(BigInteger[][] var0) {
		if (var0 == null) {
			return (BigInteger[][]) null;
		} else {
			int var1 = var0.length;
			BigInteger[][] var2 = new BigInteger[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new BigInteger[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static BigInteger[][][] copy(BigInteger[][][] var0) {
		if (var0 == null) {
			return (BigInteger[][][]) null;
		} else {
			int var1 = var0.length;
			BigInteger[][][] var2 = new BigInteger[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new BigInteger[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new BigInteger[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static BigInteger[][][][] copy(BigInteger[][][][] var0) {
		if (var0 == null) {
			return (BigInteger[][][][]) null;
		} else {
			int var1 = var0.length;
			BigInteger[][][][] var2 = new BigInteger[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new BigInteger[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new BigInteger[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new BigInteger[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static ErrorProp[] copy(ErrorProp[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			ErrorProp[] var2 = new ErrorProp[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3].copy();
			}

			return var2;
		}
	}

	public static ErrorProp[][] copy(ErrorProp[][] var0) {
		if (var0 == null) {
			return (ErrorProp[][]) null;
		} else {
			int var1 = var0.length;
			ErrorProp[][] var2 = new ErrorProp[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new ErrorProp[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5].copy();
				}
			}

			return var2;
		}
	}

	public static ErrorProp[][][] copy(ErrorProp[][][] var0) {
		if (var0 == null) {
			return (ErrorProp[][][]) null;
		} else {
			int var1 = var0.length;
			ErrorProp[][][] var2 = new ErrorProp[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new ErrorProp[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new ErrorProp[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7].copy();
					}
				}
			}

			return var2;
		}
	}

	public static ErrorProp[][][][] copy(ErrorProp[][][][] var0) {
		if (var0 == null) {
			return (ErrorProp[][][][]) null;
		} else {
			int var1 = var0.length;
			ErrorProp[][][][] var2 = new ErrorProp[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new ErrorProp[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new ErrorProp[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new ErrorProp[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9].copy();
						}
					}
				}
			}

			return var2;
		}
	}


	public static short[] copy(short[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			short[] var2 = new short[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static short[][] copy(short[][] var0) {
		if (var0 == null) {
			return (short[][]) null;
		} else {
			int var1 = var0.length;
			short[][] var2 = new short[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new short[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static short[][][] copy(short[][][] var0) {
		if (var0 == null) {
			return (short[][][]) null;
		} else {
			int var1 = var0.length;
			short[][][] var2 = new short[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new short[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new short[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static short[][][][] copy(short[][][][] var0) {
		if (var0 == null) {
			return (short[][][][]) null;
		} else {
			int var1 = var0.length;
			short[][][][] var2 = new short[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new short[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new short[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new short[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static byte[] copy(byte[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			byte[] var2 = new byte[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static byte[][] copy(byte[][] var0) {
		if (var0 == null) {
			return (byte[][]) null;
		} else {
			int var1 = var0.length;
			byte[][] var2 = new byte[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new byte[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static byte[][][] copy(byte[][][] var0) {
		if (var0 == null) {
			return (byte[][][]) null;
		} else {
			int var1 = var0.length;
			byte[][][] var2 = new byte[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new byte[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new byte[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static byte[][][][] copy(byte[][][][] var0) {
		if (var0 == null) {
			return (byte[][][][]) null;
		} else {
			int var1 = var0.length;
			byte[][][][] var2 = new byte[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new byte[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new byte[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new byte[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static Double[] copy(Double[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			Double[] var2 = new Double[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static Double[][] copy(Double[][] var0) {
		if (var0 == null) {
			return (Double[][]) null;
		} else {
			int var1 = var0.length;
			Double[][] var2 = new Double[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Double[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static Double[][][] copy(Double[][][] var0) {
		if (var0 == null) {
			return (Double[][][]) null;
		} else {
			int var1 = var0.length;
			Double[][][] var2 = new Double[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Double[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Double[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static Double[][][][] copy(Double[][][][] var0) {
		if (var0 == null) {
			return (Double[][][][]) null;
		} else {
			int var1 = var0.length;
			Double[][][][] var2 = new Double[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Double[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Double[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new Double[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static Float[] copy(Float[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			Float[] var2 = new Float[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static Float[][] copy(Float[][] var0) {
		if (var0 == null) {
			return (Float[][]) null;
		} else {
			int var1 = var0.length;
			Float[][] var2 = new Float[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Float[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static Float[][][] copy(Float[][][] var0) {
		if (var0 == null) {
			return (Float[][][]) null;
		} else {
			int var1 = var0.length;
			Float[][][] var2 = new Float[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Float[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Float[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static Float[][][][] copy(Float[][][][] var0) {
		if (var0 == null) {
			return (Float[][][][]) null;
		} else {
			int var1 = var0.length;
			Float[][][][] var2 = new Float[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Float[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Float[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new Float[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static Long[] copy(Long[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			Long[] var2 = new Long[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static Long[][] copy(Long[][] var0) {
		if (var0 == null) {
			return (Long[][]) null;
		} else {
			int var1 = var0.length;
			Long[][] var2 = new Long[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Long[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static Long[][][] copy(Long[][][] var0) {
		if (var0 == null) {
			return (Long[][][]) null;
		} else {
			int var1 = var0.length;
			Long[][][] var2 = new Long[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Long[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Long[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static Long[][][][] copy(Long[][][][] var0) {
		if (var0 == null) {
			return (Long[][][][]) null;
		} else {
			int var1 = var0.length;
			Long[][][][] var2 = new Long[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Long[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Long[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new Long[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static Integer[] copy(Integer[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			Integer[] var2 = new Integer[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static Integer[][] copy(Integer[][] var0) {
		if (var0 == null) {
			return (Integer[][]) null;
		} else {
			int var1 = var0.length;
			Integer[][] var2 = new Integer[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Integer[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static Integer[][][] copy(Integer[][][] var0) {
		if (var0 == null) {
			return (Integer[][][]) null;
		} else {
			int var1 = var0.length;
			Integer[][][] var2 = new Integer[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Integer[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Integer[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static Integer[][][][] copy(Integer[][][][] var0) {
		if (var0 == null) {
			return (Integer[][][][]) null;
		} else {
			int var1 = var0.length;
			Integer[][][][] var2 = new Integer[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Integer[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Integer[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new Integer[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static Short[] copy(Short[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			Short[] var2 = new Short[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static Short[][] copy(Short[][] var0) {
		if (var0 == null) {
			return (Short[][]) null;
		} else {
			int var1 = var0.length;
			Short[][] var2 = new Short[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Short[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static Short[][][] copy(Short[][][] var0) {
		if (var0 == null) {
			return (Short[][][]) null;
		} else {
			int var1 = var0.length;
			Short[][][] var2 = new Short[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Short[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Short[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static Short[][][][] copy(Short[][][][] var0) {
		if (var0 == null) {
			return (Short[][][][]) null;
		} else {
			int var1 = var0.length;
			Short[][][][] var2 = new Short[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Short[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Short[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new Short[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static Byte[] copy(Byte[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			Byte[] var2 = new Byte[var1];

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3];
			}

			return var2;
		}
	}

	public static Byte[][] copy(Byte[][] var0) {
		if (var0 == null) {
			return (Byte[][]) null;
		} else {
			int var1 = var0.length;
			Byte[][] var2 = new Byte[var1][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Byte[var4];

				for (int var5 = 0; var5 < var4; ++var5) {
					var2[var3][var5] = var0[var3][var5];
				}
			}

			return var2;
		}
	}

	public static Byte[][][] copy(Byte[][][] var0) {
		if (var0 == null) {
			return (Byte[][][]) null;
		} else {
			int var1 = var0.length;
			Byte[][][] var2 = new Byte[var1][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Byte[var4][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Byte[var6];

					for (int var7 = 0; var7 < var6; ++var7) {
						var2[var3][var5][var7] = var0[var3][var5][var7];
					}
				}
			}

			return var2;
		}
	}

	public static Byte[][][][] copy(Byte[][][][] var0) {
		if (var0 == null) {
			return (Byte[][][][]) null;
		} else {
			int var1 = var0.length;
			Byte[][][][] var2 = new Byte[var1][][][];

			for (int var3 = 0; var3 < var1; ++var3) {
				int var4 = var0[var3].length;
				var2[var3] = new Byte[var4][][];

				for (int var5 = 0; var5 < var4; ++var5) {
					int var6 = var0[var3][var5].length;
					var2[var3][var5] = new Byte[var6][];

					for (int var7 = 0; var7 < var6; ++var7) {
						int var8 = var0[var3][var5][var7].length;
						var2[var3][var5][var7] = new Byte[var8];

						for (int var9 = 0; var9 < var8; ++var9) {
							var2[var3][var5][var7][var9] = var0[var3][var5][var7][var9];
						}
					}
				}
			}

			return var2;
		}
	}

	public static Object copy(Object var0) {
		return var0 == null ? null : copyObject(var0);
	}

	public static Object copyObject(Object var0) {
		if (var0 == null) {
			return null;
		} else {
			Object var1 = null;

			try {
				ByteArrayOutputStream var2 = new ByteArrayOutputStream();
				ObjectOutputStream var3 = new ObjectOutputStream(var2);
				var3.writeObject(var0);
				var3.flush();
				var3.close();
				ObjectInputStream var4 = new ObjectInputStream(new ByteArrayInputStream(var2.toByteArray()));
				var1 = var4.readObject();
			} catch (IOException var5) {
				var5.printStackTrace();
			} catch (ClassNotFoundException var6) {
				var6.printStackTrace();
			}

			return var1;
		}
	}

	public static double radToDeg(double var0) {
		return var0 * 180.0D / 3.141592653589793D;
	}

	public static double degToRad(double var0) {
		return var0 * 3.141592653589793D / 180.0D;
	}

	public static double frequencyToRadialFrequency(double var0) {
		return 6.283185307179586D * var0;
	}

	public static double radialFrequencyToFrequency(double var0) {
		return var0 / 6.283185307179586D;
	}

	public static double evToNm(double var0) {
		return 2.99792458E17D / (-var0 * -1.60217646263E-19D / 6.6260687652E-34D);
	}

	public static double nmToEv(double var0) {
		return 2.99792458E8D / (-var0 * 1.0E-9D) * 6.6260687652E-34D / -1.60217646263E-19D;
	}

	public static double molarToPercentWeightByVol(double var0, double var2) {
		return var0 * var2 / 10.0D;
	}

	public static double percentWeightByVolToMolar(double var0, double var2) {
		return var0 * 10.0D / var2;
	}

	public static double celsiusToKelvin(double var0) {
		return var0 - -273.15D;
	}

	public static double kelvinToCelsius(double var0) {
		return var0 + -273.15D;
	}

	public static double celsiusToFahren(double var0) {
		return var0 * 1.8D + 32.0D;
	}

	public static double fahrenToCelsius(double var0) {
		return (var0 - 32.0D) * 5.0D / 9.0D;
	}

	public static double calorieToJoule(double var0) {
		return var0 * 4.1868D;
	}

	public static double jouleToCalorie(double var0) {
		return var0 * 0.23884D;
	}

	public static double gramToOunce(double var0) {
		return var0 / 28.3459D;
	}

	public static double ounceToGram(double var0) {
		return var0 * 28.3459D;
	}

	public static double kgToPound(double var0) {
		return var0 / 0.4536D;
	}

	public static double poundToKg(double var0) {
		return var0 * 0.4536D;
	}

	public static double kgToTon(double var0) {
		return var0 / 1016.05D;
	}

	public static double tonToKg(double var0) {
		return var0 * 1016.05D;
	}

	public static double millimetreToInch(double var0) {
		return var0 / 25.4D;
	}

	public static double inchToMillimetre(double var0) {
		return var0 * 25.4D;
	}

	public static double footToMetre(double var0) {
		return var0 * 0.3048D;
	}

	public static double metreToFoot(double var0) {
		return var0 / 0.3048D;
	}

	public static double yardToMetre(double var0) {
		return var0 * 0.9144D;
	}

	public static double metreToYard(double var0) {
		return var0 / 0.9144D;
	}

	public static double mileToKm(double var0) {
		return var0 * 1.6093D;
	}

	public static double kmToMile(double var0) {
		return var0 / 1.6093D;
	}

	public static double gallonToLitre(double var0) {
		return var0 * 4.546D;
	}

	public static double litreToGallon(double var0) {
		return var0 / 4.546D;
	}

	public static double quartToLitre(double var0) {
		return var0 * 1.137D;
	}

	public static double litreToQuart(double var0) {
		return var0 / 1.137D;
	}

	public static double pintToLitre(double var0) {
		return var0 * 0.568D;
	}

	public static double litreToPint(double var0) {
		return var0 / 0.568D;
	}

	public static double gallonPerMileToLitrePerKm(double var0) {
		return var0 * 2.825D;
	}

	public static double litrePerKmToGallonPerMile(double var0) {
		return var0 / 2.825D;
	}

	public static double milePerGallonToKmPerLitre(double var0) {
		return var0 * 0.354D;
	}

	public static double kmPerLitreToMilePerGallon(double var0) {
		return var0 / 0.354D;
	}

	public static double fluidOunceUKtoUS(double var0) {
		return var0 * 0.961D;
	}

	public static double fluidOunceUStoUK(double var0) {
		return var0 * 1.041D;
	}

	public static double pintUKtoUS(double var0) {
		return var0 * 1.201D;
	}

	public static double pintUStoUK(double var0) {
		return var0 * 0.833D;
	}

	public static double quartUKtoUS(double var0) {
		return var0 * 1.201D;
	}

	public static double quartUStoUK(double var0) {
		return var0 * 0.833D;
	}

	public static double gallonUKtoUS(double var0) {
		return var0 * 1.201D;
	}

	public static double gallonUStoUK(double var0) {
		return var0 * 0.833D;
	}

	public static double pintUKtoCupUS(double var0) {
		return var0 / 0.417D;
	}

	public static double cupUStoPintUK(double var0) {
		return var0 * 0.417D;
	}

	public static double calcBMImetric(double var0, double var2) {
		return var2 / (var0 * var0);
	}

	public static double calcBMIimperial(double var0, double var2) {
		var0 = Fmath.footToMetre(var0);
		var2 = Fmath.poundToKg(var2);
		return var2 / (var0 * var0);
	}

	public static double calcWeightFromBMImetric(double var0, double var2) {
		return var0 * var2 * var2;
	}

	public static double calcWeightFromBMIimperial(double var0, double var2) {
		var2 = Fmath.footToMetre(var2);
		double var4 = var0 * var2 * var2;
		var4 = Fmath.kgToPound(var4);
		return var4;
	}

	public static String decToHex(long var0) {
		char[] var2 = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
		String var3 = "";
		boolean var4 = true;

		while (var4) {
			long var5 = var0 % 16L;
			var3 = var2[(int) var5] + var3;
			var0 /= 16L;
			if (var0 < 16L) {
				var3 = var2[(int) var0] + var3;
				var4 = false;
			}
		}

		return var3;
	}

	public static String decToHex(int var0) {
		return decToHex((long) var0);
	}

	public static String decToBin(long var0) {
		String var2 = "";
		boolean var3 = true;

		while (var3) {
			long var4 = var0 % 2L;
			var0 /= 2L;
			var2 = var4 + var2;
			if (var0 == 0L) {
				var3 = false;
			}
		}

		return var2;
	}

	public static String decToBin(int var0) {
		return decToBin(var0);
	}
	
	public static long binToDec(String var0) {
		int var1 = var0.length();
		long var2 = 0L;

		for (int var4 = 0; var4 < var1; ++var4) {
			if (var0.charAt(var1 - var4 - 1) == '1') {
				var2 = (long) ((double) var2 + Math.pow(2.0D, (double) var4));
			}
		}

		return var2;
	}

	public static String binToHex(String var0) {
		long var1 = binToDec(var0);
		String var3 = decToHex(var1);
		return var3;
	}

	public static String hexToBin(String var0) {
		long var1 = hexToDec(var0);
		return decToBin(var1);
	}

	public static long hexToDec(String var0) {
		return hexToDec(var0, false);
	}

	public static long hexToDec(String var0, boolean var1) {
		int var2 = var0.length();
		long var3 = 0L;

		for (int var5 = 0; var5 < var2; ++var5) {
			int var6 = var0.charAt(var2 - var5 - 1);
			if (var6 <= 56) {
				if (var6 < 48) {
					if (var1) {
						throw new IllegalArgumentException(var0 + " is not an integer hexadecimal number\nMethod hexToDec aborted");
					}
				} else {
					var6 -= 48;
				}
			} else if (var6 > 64 && var6 < 71) {
				var6 -= 55;
			} else {
				if (var1) {
					throw new IllegalArgumentException(var0 + " is not an integer hexadecimal number\nMethod hexToDec aborted");
				}

			}

			var3 = (long) ((double) var3 + (double) ((long) var6) * Math.pow(16.0D, (double) var5));
		}

		return var3;
	}

}
