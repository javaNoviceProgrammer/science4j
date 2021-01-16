package util4j.math;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Fmath {
	
	public static final double N_AVAGADRO = 6.0221419947E23D;
	public static final double K_BOLTZMANN = 1.380650324E-23D;
	public static final double H_PLANCK = 6.6260687652E-34D;
	public static final double H_PLANCK_RED = 1.0545715972483913E-34D;
	public static final double C_LIGHT = 2.99792458E8D;
	public static final double R_GAS = 8.31447215D;
	public static final double F_FARADAY = 96485.341539D;
	public static final double T_ABS = -273.15D;
	public static final double Q_ELECTRON = -1.60217646263E-19D;
	public static final double M_ELECTRON = 9.1093818872E-31D;
	public static final double M_PROTON = 1.6726215813E-27D;
	public static final double M_NEUTRON = 1.6749271613E-27D;
	public static final double EPSILON_0 = 8.854187817E-12D;
	public static final double MU_0 = 1.2566370614359173E-6D;
	public static final double ETA_0 = 376.73031346177066D;
	public static final double EULER_CONSTANT_GAMMA = 0.5772156649015627D;
	public static final double PI = 3.141592653589793D;
	public static final double E = 2.718281828459045D;
	private static final Map<Object, Object> integers = new HashMap<Object, Object>();

	public static double log10(double var0) {
		return Math.log(var0) / Math.log(10.0D);
	}

	public static float log10(float var0) {
		return (float) (Math.log((double) var0) / Math.log(10.0D));
	}

	public static double antilog10(double var0) {
		return Math.pow(10.0D, var0);
	}

	public static float antilog10(float var0) {
		return (float) Math.pow(10.0D, (double) var0);
	}

	public static double log(double var0) {
		return Math.log(var0);
	}

	public static float log(float var0) {
		return (float) Math.log((double) var0);
	}

	public static double antilog(double var0) {
		return Math.exp(var0);
	}

	public static float antilog(float var0) {
		return (float) Math.exp((double) var0);
	}

	public static double log2(double var0) {
		return Math.log(var0) / Math.log(2.0D);
	}

	public static float log2(float var0) {
		return (float) (Math.log((double) var0) / Math.log(2.0D));
	}

	public static double antilog2(double var0) {
		return Math.pow(2.0D, var0);
	}

	public static float antilog2(float var0) {
		return (float) Math.pow(2.0D, (double) var0);
	}

	public static double log10(double var0, double var2) {
		return Math.log(var0) / Math.log(var2);
	}

	public static double log10(double var0, int var2) {
		return Math.log(var0) / Math.log((double) var2);
	}

	public static float log10(float var0, float var1) {
		return (float) (Math.log((double) var0) / Math.log((double) var1));
	}

	public static float log10(float var0, int var1) {
		return (float) (Math.log((double) var0) / Math.log((double) var1));
	}

	public static double logit(double var0) {
		if (var0 < 0.0D) {
			throw new IllegalArgumentException(
					"the argument, p = " + var0 + ", must be greater or equal to zero and less than or equal to unity");
		} else if (var0 > 1.0D) {
			throw new IllegalArgumentException(
					"the argument, p = " + var0 + ", must be greater or equal to zero and less than or equal to unity");
		} else {
			return Math.log(var0 / (1.0D - var0));
		}
	}

	public static float logit(float var0) {
		if (var0 < 0.0F) {
			throw new IllegalArgumentException(
					"the argument, p = " + var0 + ", must be greater or equal to zero and less than or equal to unity");
		} else if (var0 > 1.0F) {
			throw new IllegalArgumentException(
					"the argument, p = " + var0 + ", must be greater or equal to zero and less than or equal to unity");
		} else {
			return (float) Math.log((double) (var0 / (1.0F - var0)));
		}
	}

	public static double logit10(double var0) {
		if (var0 < 0.0D) {
			throw new IllegalArgumentException(
					"the argument, p = " + var0 + ", must be greater or equal to zero and less than or equal to unity");
		} else if (var0 > 1.0D) {
			throw new IllegalArgumentException(
					"the argument, p = " + var0 + ", must be greater or equal to zero and less than or equal to unity");
		} else {
			return Math.log10(var0 / (1.0D - var0));
		}
	}

	public static float logit10(float var0) {
		if (var0 < 0.0F) {
			throw new IllegalArgumentException(
					"the argument, p = " + var0 + ", must be greater or equal to zero and less than or equal to unity");
		} else if (var0 > 1.0F) {
			throw new IllegalArgumentException(
					"the argument, p = " + var0 + ", must be greater or equal to zero and less than or equal to unity");
		} else {
			return (float) Math.log10((double) var0 / (1.0D - (double) var0));
		}
	}

	public static double antilogit(double var0) {
		double var2 = Math.exp(var0);
		return var2 / (1.0D + var2);
	}

	public static float antilogit(float var0) {
		float var1 = (float) Math.exp((double) var0);
		return var1 / (1.0F + var1);
	}

	public static double antilogit10(double var0) {
		double var2 = Math.pow(10.0D, var0);
		return var2 / (1.0D + var2);
	}

	public static float antilogit10(float var0) {
		float var1 = (float) Math.pow(10.0D, (double) var0);
		return var1 / (1.0F + var1);
	}

	public static double square(double var0) {
		return var0 * var0;
	}

	public static float square(float var0) {
		return var0 * var0;
	}

	public static BigDecimal square(BigDecimal var0) {
		return var0.multiply(var0);
	}

	public static int square(int var0) {
		return var0 * var0;
	}

	public static long square(long var0) {
		return var0 * var0;
	}

	public static BigInteger square(BigInteger var0) {
		return var0.multiply(var0);
	}

	public static int factorial(int var0) {
		if (var0 < 0) {
			throw new IllegalArgumentException("n must be a positive integer");
		} else if (var0 > 12) {
			throw new IllegalArgumentException(
					"n must less than 13 to avoid integer overflow\nTry long or double argument");
		} else {
			int var1 = 1;

			for (int var2 = 2; var2 <= var0; ++var2) {
				var1 *= var2;
			}

			return var1;
		}
	}

	public static long factorial(long var0) {
		if (var0 < 0L) {
			throw new IllegalArgumentException("n must be a positive integer");
		} else if (var0 > 20L) {
			throw new IllegalArgumentException(
					"n must less than 21 to avoid long integer overflow\nTry double argument");
		} else {
			long var2 = 1L;

			for (long var4 = 2L; var4 <= var0; ++var4) {
				var2 *= var4;
			}

			return var2;
		}
	}

	public static BigInteger factorial(BigInteger var0) {
		if (var0.compareTo(BigInteger.ZERO) == -1) {
			throw new IllegalArgumentException(
					"\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
		} else {
			BigInteger var1 = BigInteger.ONE;
			BigInteger var2 = var1;

			BigInteger var3;
			for (var3 = new BigInteger("2"); var3.compareTo(var0) != 1; var3 = var3.add(var1)) {
				var2 = var2.multiply(var3);
			}

			var1 = null;
			var3 = null;
			return var2;
		}
	}

	public static double factorial(double var0) {
		if (var0 >= 0.0D && var0 - Math.floor(var0) == 0.0D) {
			double var2 = 1.0D;

			for (double var4 = 2.0D; var4 <= var0; ++var4) {
				var2 *= var4;
			}

			return var2;
		} else {
			throw new IllegalArgumentException(
					"\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
		}
	}

	public static BigDecimal factorial(BigDecimal var0) {
		if (var0.compareTo(BigDecimal.ZERO) != -1 && isInteger((Number) var0)) {
			BigDecimal var1 = BigDecimal.ONE;
			BigDecimal var2 = var1;

			BigDecimal var3;
			for (var3 = new BigDecimal(2.0D); var3.compareTo(var0) != 1; var3 = var3.add(var1)) {
				var2 = var2.multiply(var3);
			}

			var1 = null;
			var3 = null;
			return var2;
		} else {
			throw new IllegalArgumentException(
					"\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
		}
	}

	public static double logFactorial(int var0) {
		if (var0 < 0) {
			throw new IllegalArgumentException(
					"\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
		} else {
			double var1 = 0.0D;

			for (int var3 = 2; var3 <= var0; ++var3) {
				var1 += Math.log((double) var3);
			}

			return var1;
		}
	}

	public static double logFactorial(long var0) {
		if (var0 < 0L) {
			throw new IllegalArgumentException(
					"\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
		} else {
			double var2 = 0.0D;

			for (long var4 = 2L; var4 <= var0; ++var4) {
				var2 += Math.log((double) var4);
			}

			return var2;
		}
	}

	public static double logFactorial(double var0) {
		if (var0 >= 0.0D && var0 - Math.floor(var0) == 0.0D) {
			double var2 = 0.0D;

			for (double var4 = 2.0D; var4 <= var0; ++var4) {
				var2 += Math.log(var4);
			}

			return var2;
		} else {
			throw new IllegalArgumentException(
					"\nn must be a positive integer\nIs a Gamma funtion [Fmath.gamma(x)] more appropriate?");
		}
	}

	public static double sign(double var0) {
		return var0 < 0.0D ? -1.0D : 1.0D;
	}

	public static float sign(float var0) {
		return var0 < 0.0F ? -1.0F : 1.0F;
	}

	public static int sign(int var0) {
		return var0 < 0 ? -1 : 1;
	}

	public static long sign(long var0) {
		return var0 < 0L ? -1L : 1L;
	}

	public static double hypot(double var0, double var2) {
		double var4 = Math.abs(var0);
		double var6 = Math.abs(var2);
		double var8 = 0.0D;
		double var10 = 0.0D;
		if (var4 == 0.0D) {
			var8 = var6;
		} else if (var6 == 0.0D) {
			var8 = var4;
		} else if (var4 >= var6) {
			var10 = var6 / var4;
			var8 = var4 * Math.sqrt(1.0D + var10 * var10);
		} else {
			var10 = var4 / var6;
			var8 = var6 * Math.sqrt(1.0D + var10 * var10);
		}

		return var8;
	}

	public static float hypot(float var0, float var1) {
		return (float) hypot((double) var0, (double) var1);
	}

	public static double angle(double var0, double var2, double var4, double var6, double var8, double var10) {
		double var12 = cos(var0, var2, var4, var6, var8, var10);
		return Math.acos(var12);
	}

	public static double angle(double var0, double var2, double var4) {
		double var6 = cos(var0, var2, var4);
		return Math.acos(var6);
	}

	public static double sin(double var0, double var2, double var4, double var6, double var8, double var10) {
		double var12 = angle(var0, var2, var4, var6, var8, var10);
		return Math.sin(var12);
	}

	public static double sin(double var0, double var2, double var4) {
		double var6 = angle(var0, var2, var4);
		return Math.sin(var6);
	}

	public static double sin(double var0) {
		return Math.sin(var0);
	}

	public static double asin(double var0) {
		if (var0 < -1.0D && var0 > 1.0D) {
			throw new IllegalArgumentException("Fmath.asin argument (" + var0 + ") must be >= -1.0 and <= 1.0");
		} else {
			return Math.asin(var0);
		}
	}

	public static double cos(double var0, double var2, double var4, double var6, double var8, double var10) {
		double var12 = hypot(var0 - var8, var2 - var10);
		double var14 = hypot(var4 - var8, var6 - var10);
		double var16 = hypot(var0 - var4, var2 - var6);
		return cos(var12, var14, var16);
	}

	public static double cos(double var0, double var2, double var4) {
		return 0.5D * (var0 / var2 + var2 / var0 - var4 / var0 * (var4 / var2));
	}

	public static double cos(double var0) {
		return Math.cos(var0);
	}

	public static double acos(double var0) {
		if (var0 >= -1.0D && var0 <= 1.0D) {
			return Math.acos(var0);
		} else {
			throw new IllegalArgumentException("Fmath.acos argument (" + var0 + ") must be >= -1.0 and <= 1.0");
		}
	}

	public static double tan(double var0, double var2, double var4, double var6, double var8, double var10) {
		double var12 = angle(var0, var2, var4, var6, var8, var10);
		return Math.tan(var12);
	}

	public static double tan(double var0, double var2, double var4) {
		double var6 = angle(var0, var2, var4);
		return Math.tan(var6);
	}

	public static double tan(double var0) {
		return Math.tan(var0);
	}

	public static double atan(double var0) {
		return Math.atan(var0);
	}

	public static double atan2(double var0, double var2) {
		return Math.atan2(var0, var2);
	}

	public static double cot(double var0) {
		return 1.0D / Math.tan(var0);
	}

	public static double acot(double var0) {
		return Math.atan(1.0D / var0);
	}

	public static double acot2(double var0, double var2) {
		return Math.atan2(var2, var0);
	}

	public static double sec(double var0) {
		return 1.0D / Math.cos(var0);
	}

	public static double asec(double var0) {
		if (var0 < 1.0D && var0 > -1.0D) {
			throw new IllegalArgumentException("asec argument (" + var0 + ") must be >= 1 or <= -1");
		} else {
			return Math.acos(1.0D / var0);
		}
	}

	public static double csc(double var0) {
		return 1.0D / Math.sin(var0);
	}

	public static double acsc(double var0) {
		if (var0 < 1.0D && var0 > -1.0D) {
			throw new IllegalArgumentException("acsc argument (" + var0 + ") must be >= 1 or <= -1");
		} else {
			return Math.asin(1.0D / var0);
		}
	}

	public static double exsec(double var0) {
		return 1.0D / Math.cos(var0) - 1.0D;
	}

	public static double aexsec(double var0) {
		if (var0 < 0.0D && var0 > -2.0D) {
			throw new IllegalArgumentException("aexsec argument (" + var0 + ") must be >= 0.0 and <= -2");
		} else {
			return Math.asin(1.0D / (1.0D + var0));
		}
	}

	public static double vers(double var0) {
		return 1.0D - Math.cos(var0);
	}

	public static double avers(double var0) {
		if (var0 < 0.0D && var0 > 2.0D) {
			throw new IllegalArgumentException("avers argument (" + var0 + ") must be <= 2 and >= 0");
		} else {
			return Math.acos(1.0D - var0);
		}
	}

	public static double covers(double var0) {
		return 1.0D - Math.sin(var0);
	}

	public static double acovers(double var0) {
		if (var0 < 0.0D && var0 > 2.0D) {
			throw new IllegalArgumentException("acovers argument (" + var0 + ") must be <= 2 and >= 0");
		} else {
			return Math.asin(1.0D - var0);
		}
	}

	public static double hav(double var0) {
		return 0.5D * vers(var0);
	}

	public static double ahav(double var0) {
		if (var0 < 0.0D && var0 > 1.0D) {
			throw new IllegalArgumentException("ahav argument (" + var0 + ") must be >= 0 and <= 1");
		} else {
			return acos(1.0D - 2.0D * var0);
		}
	}

	public static double sinc(double var0) {
		return Math.abs(var0) < 1.0E-40D ? 1.0D : Math.sin(var0) / var0;
	}

	public static double nsinc(double var0) {
		return Math.abs(var0) < 1.0E-40D ? 1.0D : Math.sin(3.141592653589793D * var0) / (3.141592653589793D * var0);
	}

	public static double sinh(double var0) {
		return 0.5D * (Math.exp(var0) - Math.exp(-var0));
	}

	public static double asinh(double var0) {
		double var2 = 1.0D;
		if (var0 < 0.0D) {
			var2 = -1.0D;
			var0 = -var0;
		}

		return var2 * Math.log(var0 + Math.sqrt(var0 * var0 + 1.0D));
	}

	public static double cosh(double var0) {
		return 0.5D * (Math.exp(var0) + Math.exp(-var0));
	}

	public static double acosh(double var0) {
		if (var0 < 1.0D) {
			throw new IllegalArgumentException("acosh real number argument (" + var0 + ") must be >= 1");
		} else {
			return Math.log(var0 + Math.sqrt(var0 * var0 - 1.0D));
		}
	}

	public static double tanh(double var0) {
		return sinh(var0) / cosh(var0);
	}

	public static double atanh(double var0) {
		double var2 = 1.0D;
		if (var0 < 0.0D) {
			var2 = -1.0D;
			var0 = -var0;
		}

		if (var0 > 1.0D) {
			throw new IllegalArgumentException(
					"atanh real number argument (" + var2 * var0 + ") must be >= -1 and <= 1");
		} else {
			return 0.5D * var2 * (Math.log(1.0D + var0) - Math.log(1.0D - var0));
		}
	}

	public static double coth(double var0) {
		return 1.0D / tanh(var0);
	}

	public static double acoth(double var0) {
		double var2 = 1.0D;
		if (var0 < 0.0D) {
			var2 = -1.0D;
			var0 = -var0;
		}

		if (var0 < 1.0D) {
			throw new IllegalArgumentException(
					"acoth real number argument (" + var2 * var0 + ") must be <= -1 or >= 1");
		} else {
			return 0.5D * var2 * (Math.log(1.0D + var0) - Math.log(var0 - 1.0D));
		}
	}

	public static double sech(double var0) {
		return 1.0D / cosh(var0);
	}

	public static double asech(double var0) {
		if (var0 <= 1.0D && var0 >= 0.0D) {
			return 0.5D * Math.log(1.0D / var0 + Math.sqrt(1.0D / (var0 * var0) - 1.0D));
		} else {
			throw new IllegalArgumentException("asech real number argument (" + var0 + ") must be >= 0 and <= 1");
		}
	}

	public static double csch(double var0) {
		return 1.0D / sinh(var0);
	}

	public static double acsch(double var0) {
		double var2 = 1.0D;
		if (var0 < 0.0D) {
			var2 = -1.0D;
			var0 = -var0;
		}

		return 0.5D * var2 * Math.log(1.0D / var0 + Math.sqrt(1.0D / (var0 * var0) + 1.0D));
	}

	public static int checkPrecision(double var0) {
		boolean var2 = true;
		int var3 = 0;
		if (isNaN(var0)) {
			var2 = false;
		}

		if (isPlusInfinity(var0)) {
			var2 = false;
		}

		if (isMinusInfinity(var0)) {
			var2 = false;
		}

		while (var2) {
			if (var0 == truncate(var0, var3)) {
				var2 = false;
			} else {
				++var3;
				if (var3 > 20) {
					var2 = false;
				}
			}
		}

		return var3;
	}

	public static int checkPrecision(float var0) {
		return checkPrecision((double) var0);
	}

	public static double truncate(double var0, int var2) {
		double var3 = var0;
		if (!isNaN(var0) && !isPlusInfinity(var0) && !isMinusInfinity(var0) && var0 != 0.0D) {
			String var5 = (new Double(var0)).toString().trim();
			var3 = Double.parseDouble(truncateProcedure(var5, var2));
		}

		return var3;
	}

	public static float truncate(float var0, int var1) {
		float var2 = var0;
		if (!isNaN(var0) && !isPlusInfinity(var0) && !isMinusInfinity(var0) && (double) var0 != 0.0D) {
			String var3 = (new Float(var0)).toString().trim();
			var2 = Float.parseFloat(truncateProcedure(var3, var1));
		}

		return var2;
	}

	private static String truncateProcedure(String var0, int var1) {
		String var2 = var0;
		String var3 = var0;
		String var4 = " ";
		String var5 = "+";
		int var6 = var0.indexOf(69);
		int var7 = var0.indexOf(46);
		int var8 = var0.indexOf(45);
		if (var8 != -1 && var8 == 0) {
			var3 = var0.substring(1);
			var5 = "-";
			--var7;
			--var6;
		}

		if (var6 > -1) {
			var4 = var3.substring(var6);
			var3 = var3.substring(0, var6);
		}

		String var9 = null;
		String var10 = "0";
		String var11 = null;
		String var12 = null;
		double var13 = 0.0D;
		if (var7 > -1) {
			var9 = var3.substring(0, var7);
			var10 = var3.substring(var7 + 1);
			int var15 = var10.length();
			if (var1 < var15) {
				var11 = var10.substring(var1);
				var12 = var11.substring(0, 1) + ".";
				if (var11.length() > 1) {
					var12 = var12 + var11.substring(1);
				} else {
					var12 = var12 + "0";
				}

				var13 = (double) Math.round(Double.parseDouble(var12));
				if (var1 <= 0) {
					if (var13 >= 5.0D) {
						int var21 = Integer.parseInt(var9);
						++var21;
						var9 = (new Integer(var21)).toString();
					}

					var10 = "0";
				} else if (var13 < 5.0D) {
					var10 = var10.substring(0, var1);
				} else {
					int[] var16 = new int[var1 + 1];
					var16[0] = 0;

					for (int var17 = 0; var17 < var1; ++var17) {
						var16[var17 + 1] = Integer.parseInt(var10.substring(var17, var17 + 1));
					}

					boolean var22 = true;
					int var18 = var1;

					while (var22) {
						++var16[var18];
						if (var18 > 0) {
							if (var16[var18] < 10) {
								var22 = false;
							} else {
								var16[var18] = 0;
								--var18;
							}
						} else {
							var22 = false;
						}
					}

					int var19 = Integer.parseInt(var9);
					var19 += var16[0];
					var9 = (new Integer(var19)).toString();
					var12 = "";

					for (int var20 = 1; var20 <= var1; ++var20) {
						var12 = var12 + (new Integer(var16[var20])).toString();
					}

					var10 = var12;
				}
			}

			var2 = var5 + var9.trim() + "." + var10.trim() + var4;
		}

		return var2.trim();
	}

	public static boolean isFinite(double var0) {
		boolean var2 = true;
		if (var0 != var0) {
			var2 = false;
		} else if (var0 == Double.POSITIVE_INFINITY) {
			var2 = false;
		} else if (var0 == Double.NEGATIVE_INFINITY) {
			var2 = false;
		}

		return var2;
	}

	public static boolean isFinite(float var0) {
		boolean var1 = true;
		if (var0 != var0) {
			var1 = false;
		} else if ((double) var0 == Double.POSITIVE_INFINITY) {
			var1 = false;
		} else if ((double) var0 == Double.NEGATIVE_INFINITY) {
			var1 = false;
		}

		return var1;
	}

	public static boolean isInfinity(double var0) {
		boolean var2 = false;
		if (var0 == Double.POSITIVE_INFINITY || var0 == Double.NEGATIVE_INFINITY) {
			var2 = true;
		}

		return var2;
	}

	public static boolean isInfinity(float var0) {
		boolean var1 = false;
		if (var0 == Float.POSITIVE_INFINITY || var0 == Float.NEGATIVE_INFINITY) {
			var1 = true;
		}

		return var1;
	}

	public static boolean isPlusInfinity(double var0) {
		boolean var2 = false;
		if (var0 == Double.POSITIVE_INFINITY) {
			var2 = true;
		}

		return var2;
	}

	public static boolean isPlusInfinity(float var0) {
		boolean var1 = false;
		if (var0 == Float.POSITIVE_INFINITY) {
			var1 = true;
		}

		return var1;
	}

	public static boolean isMinusInfinity(double var0) {
		boolean var2 = false;
		if (var0 == Double.NEGATIVE_INFINITY) {
			var2 = true;
		}

		return var2;
	}

	public static boolean isMinusInfinity(float var0) {
		boolean var1 = false;
		if (var0 == Float.NEGATIVE_INFINITY) {
			var1 = true;
		}

		return var1;
	}

	public static boolean isNaN(double var0) {
		boolean var2 = false;
		if (var0 != var0) {
			var2 = true;
		}

		return var2;
	}

	public static boolean isNaN(float var0) {
		boolean var1 = false;
		if (var0 != var0) {
			var1 = true;
		}

		return var1;
	}

	public static boolean isEqual(double var0, double var2) {
		boolean var4 = false;
		if (isNaN(var0)) {
			if (isNaN(var2)) {
				var4 = true;
			}
		} else if (isPlusInfinity(var0)) {
			if (isPlusInfinity(var2)) {
				var4 = true;
			}
		} else if (isMinusInfinity(var0)) {
			if (isMinusInfinity(var2)) {
				var4 = true;
			}
		} else if (var0 == var2) {
			var4 = true;
		}

		return var4;
	}

	public static boolean isEqual(float var0, float var1) {
		boolean var2 = false;
		if (isNaN(var0)) {
			if (isNaN(var1)) {
				var2 = true;
			}
		} else if (isPlusInfinity(var0)) {
			if (isPlusInfinity(var1)) {
				var2 = true;
			}
		} else if (isMinusInfinity(var0)) {
			if (isMinusInfinity(var1)) {
				var2 = true;
			}
		} else if (var0 == var1) {
			var2 = true;
		}

		return var2;
	}

	public static boolean isEqual(int var0, int var1) {
		boolean var2 = false;
		if (var0 == var1) {
			var2 = true;
		}

		return var2;
	}

	public static boolean isEqual(char var0, char var1) {
		boolean var2 = false;
		if (var0 == var1) {
			var2 = true;
		}

		return var2;
	}

	public static boolean isEqual(String var0, String var1) {
		boolean var2 = false;
		if (var0.equals(var1)) {
			var2 = true;
		}

		return var2;
	}

	public static boolean isEqualWithinLimits(double var0, double var2, double var4) {
		boolean var6 = false;
		if (Math.abs(var0 - var2) <= Math.abs(var4)) {
			var6 = true;
		}

		return var6;
	}

	public static boolean isEqualWithinLimits(float var0, float var1, float var2) {
		boolean var3 = false;
		if (Math.abs(var0 - var1) <= Math.abs(var2)) {
			var3 = true;
		}

		return var3;
	}

	public static boolean isEqualWithinLimits(long var0, long var2, long var4) {
		boolean var6 = false;
		if (Math.abs(var0 - var2) <= Math.abs(var4)) {
			var6 = true;
		}

		return var6;
	}

	public static boolean isEqualWithinLimits(int var0, int var1, int var2) {
		boolean var3 = false;
		if (Math.abs(var0 - var1) <= Math.abs(var2)) {
			var3 = true;
		}

		return var3;
	}

	public static boolean isEqualWithinLimits(BigDecimal var0, BigDecimal var1, BigDecimal var2) {
		boolean var3 = false;
		if (var0.subtract(var1).abs().compareTo(var2.abs()) <= 0) {
			var3 = true;
		}

		return var3;
	}

	public static boolean isEqualWithinLimits(BigInteger var0, BigInteger var1, BigInteger var2) {
		boolean var3 = false;
		if (var0.subtract(var1).abs().compareTo(var2.abs()) <= 0) {
			var3 = true;
		}

		return var3;
	}

	public static boolean isEqualWithinPerCent(double var0, double var2, double var4) {
		boolean var6 = false;
		double var7 = Math.abs((var0 + var2) * var4 / 200.0D);
		if (Math.abs(var0 - var2) <= var7) {
			var6 = true;
		}

		return var6;
	}

	public static boolean isEqualWithinPerCent(float var0, float var1, float var2) {
		boolean var3 = false;
		double var4 = (double) Math.abs((var0 + var1) * var2 / 200.0F);
		if ((double) Math.abs(var0 - var1) <= var4) {
			var3 = true;
		}

		return var3;
	}

	public static boolean isEqualWithinPerCent(long var0, long var2, double var4) {
		boolean var6 = false;
		double var7 = Math.abs((double) (var0 + var2) * var4 / 200.0D);
		if ((double) Math.abs(var0 - var2) <= var7) {
			var6 = true;
		}

		return var6;
	}

	public static boolean isEqualWithinPerCent(long var0, long var2, long var4) {
		boolean var6 = false;
		double var7 = Math.abs((double) (var0 + var2) * (double) var4 / 200.0D);
		if ((double) Math.abs(var0 - var2) <= var7) {
			var6 = true;
		}

		return var6;
	}

	public static boolean isEqualWithinPerCent(int var0, int var1, double var2) {
		boolean var4 = false;
		double var5 = Math.abs((double) (var0 + var1) * var2 / 200.0D);
		if ((double) Math.abs(var0 - var1) <= var5) {
			var4 = true;
		}

		return var4;
	}

	public static boolean isEqualWithinPerCent(int var0, int var1, int var2) {
		boolean var3 = false;
		double var4 = Math.abs((double) (var0 + var1) * (double) var2 / 200.0D);
		if ((double) Math.abs(var0 - var1) <= var4) {
			var3 = true;
		}

		return var3;
	}

	public static boolean isEqualWithinPerCent(BigDecimal var0, BigDecimal var1, BigDecimal var2) {
		boolean var3 = false;
		BigDecimal var4 = var0.add(var1).multiply(var2).multiply(new BigDecimal("0.005"));
		if (var0.subtract(var1).abs().compareTo(var4.abs()) <= 0) {
			var3 = true;
		}

		var4 = null;
		return var3;
	}

	public static boolean isEqualWithinPerCent(BigInteger var0, BigInteger var1, BigDecimal var2) {
		boolean var3 = false;
		BigDecimal var4 = new BigDecimal(var0);
		BigDecimal var5 = new BigDecimal(var1);
		BigDecimal var6 = var4.add(var5).multiply(var2).multiply(new BigDecimal("0.005"));
		if (var4.subtract(var5).abs().compareTo(var6.abs()) <= 0) {
			var3 = true;
		}

		var6 = null;
		var4 = null;
		var5 = null;
		return var3;
	}

	public static boolean isEqualWithinPerCent(BigInteger var0, BigInteger var1, BigInteger var2) {
		boolean var3 = false;
		BigDecimal var4 = new BigDecimal(var0);
		BigDecimal var5 = new BigDecimal(var1);
		BigDecimal var6 = new BigDecimal(var2);
		BigDecimal var7 = var4.add(var5).multiply(var6).multiply(new BigDecimal("0.005"));
		if (var4.subtract(var5).abs().compareTo(var7.abs()) <= 0) {
			var3 = true;
		}

		var7 = null;
		var4 = null;
		var5 = null;
		var6 = null;
		return var3;
	}

	public static int compare(double var0, double var2) {
		Double var4 = new Double(var0);
		Double var5 = new Double(var2);
		return var4.compareTo(var5);
	}

	public static int compare(int var0, int var1) {
		Integer var2 = new Integer(var0);
		Integer var3 = new Integer(var1);
		return var2.compareTo(var3);
	}

	public static int compare(long var0, long var2) {
		Long var4 = new Long(var0);
		Long var5 = new Long(var2);
		return var4.compareTo(var5);
	}

	public static int compare(float var0, float var1) {
		Float var2 = new Float(var0);
		Float var3 = new Float(var1);
		return var2.compareTo(var3);
	}

	public static int compare(byte var0, byte var1) {
		Byte var2 = new Byte(var0);
		Byte var3 = new Byte(var1);
		return var2.compareTo(var3);
	}

	public static int compare(short var0, short var1) {
		Short var2 = new Short(var0);
		Short var3 = new Short(var1);
		return var2.compareTo(var3);
	}

	public static boolean compare(double[] var0, double[] var1) {
		boolean var2 = true;
		int var3 = var0.length;
		int var4 = var1.length;
		if (var3 != var4) {
			var2 = false;
		} else {
			for (int var5 = 0; var5 < var3; ++var5) {
				if (var0[var5] != var1[var5]) {
					var2 = false;
					break;
				}
			}
		}

		return var2;
	}

	public static boolean compare(float[] var0, float[] var1) {
		boolean var2 = true;
		int var3 = var0.length;
		int var4 = var1.length;
		if (var3 != var4) {
			var2 = false;
		} else {
			for (int var5 = 0; var5 < var3; ++var5) {
				if (var0[var5] != var1[var5]) {
					var2 = false;
					break;
				}
			}
		}

		return var2;
	}

	public static boolean compare(int[] var0, int[] var1) {
		boolean var2 = true;
		int var3 = var0.length;
		int var4 = var1.length;
		if (var3 != var4) {
			var2 = false;
		} else {
			for (int var5 = 0; var5 < var3; ++var5) {
				if (var0[var5] != var1[var5]) {
					var2 = false;
					break;
				}
			}
		}

		return var2;
	}

	public static boolean compare(long[] var0, long[] var1) {
		boolean var2 = true;
		int var3 = var0.length;
		int var4 = var1.length;
		if (var3 != var4) {
			var2 = false;
		} else {
			for (int var5 = 0; var5 < var3; ++var5) {
				if (var0[var5] != var1[var5]) {
					var2 = false;
					break;
				}
			}
		}

		return var2;
	}

	public static boolean isInteger(double var0) {
		boolean var2 = false;
		double var3 = Math.floor(var0);
		if (var0 - var3 == 0.0D) {
			var2 = true;
		}

		return var2;
	}

	public static boolean isInteger(double[] var0) {
		boolean var1 = true;
		boolean var2 = true;
		int var3 = 0;

		while (var2) {
			double var4 = Math.floor(var0[var3]);
			if (var0[var3] - var4 != 0.0D) {
				var1 = false;
				var2 = false;
			} else {
				++var3;
				if (var3 == var0.length) {
					var2 = false;
				}
			}
		}

		return var1;
	}

	public static boolean isInteger(float var0) {
		boolean var1 = false;
		float var2 = (float) Math.floor((double) var0);
		if (var0 - var2 == 0.0F) {
			var1 = true;
		}

		return var1;
	}

	public static boolean isInteger(float[] var0) {
		boolean var1 = true;
		boolean var2 = true;
		int var3 = 0;

		while (var2) {
			float var4 = (float) Math.floor((double) var0[var3]);
			if ((double) (var0[var3] - var4) != 0.0D) {
				var1 = false;
				var2 = false;
			} else {
				++var3;
				if (var3 == var0.length) {
					var2 = false;
				}
			}
		}

		return var1;
	}

	public static boolean isInteger(Number var0) {
		boolean var1 = integers.containsKey(var0.getClass());
		if (!var1) {
			double var2;
			if (var0 instanceof Double) {
				var2 = var0.doubleValue();
				var1 = isInteger(var2);
			}

			if (var0 instanceof Float) {
				float var4 = var0.floatValue();
				var1 = isInteger(var4);
			}

			if (var0 instanceof BigDecimal) {
				var2 = var0.doubleValue();
				var1 = isInteger(var2);
			}
		}

		return var1;
	}

	public static boolean isInteger(Number[] var0) {
		boolean var1 = true;

		for (int var2 = 0; var2 < var0.length; ++var2) {
			boolean var3 = integers.containsKey(var0[var2].getClass());
			if (!var3) {
				double var4;
				if (var0[var2] instanceof Double) {
					var4 = var0[var2].doubleValue();
					var3 = isInteger(var4);
					if (!var3) {
						var1 = false;
					}
				}

				if (var0[var2] instanceof Float) {
					float var6 = var0[var2].floatValue();
					var3 = isInteger(var6);
					if (!var3) {
						var1 = false;
					}
				}

				if (var0[var2] instanceof BigDecimal) {
					var4 = var0[var2].doubleValue();
					var3 = isInteger(var4);
					if (!var3) {
						var1 = false;
					}
				}
			}
		}

		return var1;
	}

	public static boolean isEven(int var0) {
		boolean var1 = false;
		if ((double) (var0 % 2) == 0.0D) {
			var1 = true;
		}

		return var1;
	}

	public static boolean isEven(float var0) {
		double var1 = Math.floor((double) var0);
		if ((double) var0 - var1 != 0.0D) {
			throw new IllegalArgumentException("the argument is not an integer");
		} else {
			boolean var3 = false;
			var1 = Math.floor((double) (var0 / 2.0F));
			if ((double) (var0 / 2.0F) - var1 == 0.0D) {
				var3 = true;
			}

			return var3;
		}
	}

	public static boolean isEven(double var0) {
		double var2 = Math.floor(var0);
		if (var0 - var2 != 0.0D) {
			throw new IllegalArgumentException("the argument is not an integer");
		} else {
			boolean var4 = false;
			var2 = Math.floor(var0 / 2.0D);
			if (var0 / 2.0D - var2 == 0.0D) {
				var4 = true;
			}

			return var4;
		}
	}

	public static boolean isOdd(int var0) {
		boolean var1 = true;
		if ((double) (var0 % 2) == 0.0D) {
			var1 = false;
		}

		return var1;
	}

	public static boolean isOdd(float var0) {
		double var1 = Math.floor((double) var0);
		if ((double) var0 - var1 != 0.0D) {
			throw new IllegalArgumentException("the argument is not an integer");
		} else {
			boolean var3 = true;
			var1 = Math.floor((double) (var0 / 2.0F));
			if ((double) (var0 / 2.0F) - var1 == 0.0D) {
				var3 = false;
			}

			return var3;
		}
	}

	public static boolean isOdd(double var0) {
		double var2 = Math.floor(var0);
		if (var0 - var2 != 0.0D) {
			throw new IllegalArgumentException("the argument is not an integer");
		} else {
			boolean var4 = true;
			var2 = Math.floor(var0 / 2.0D);
			if (var0 / 2.0D - var2 == 0.0D) {
				var4 = false;
			}

			return var4;
		}
	}

	public static boolean leapYear(int var0) {
		boolean var1 = false;
		if (var0 % 4 != 0) {
			var1 = false;
		} else if (var0 % 400 == 0) {
			var1 = true;
		} else if (var0 % 100 == 0) {
			var1 = false;
		} else {
			var1 = true;
		}

		return var1;
	}

	public static long dateToJavaMilliS(int var0, int var1, int var2, int var3, int var4, int var5) {
		long[] var6 = new long[]{0L, 31L, 28L, 31L, 30L, 31L, 30L, 31L, 31L, 30L, 31L, 30L, 31L};
		long var7 = 0L;
		long var9 = 0L;

		for (int var11 = var0 - 1; var11 >= 1970; --var11) {
			var9 += 365L;
			if (leapYear(var11)) {
				++var9;
			}
		}

		var9 *= 86400000L;
		long var12 = 0L;

		for (int var14 = var1 - 1; var14 > 0; --var14) {
			var12 += var6[var14];
			if (leapYear(var0)) {
				++var12;
			}
		}

		var12 *= 86400000L;
		var7 = var9 + var12 + (long) var2 * 24L * 60L * 60L * 1000L + (long) var3 * 60L * 60L * 1000L
				+ (long) var4 * 60L * 1000L + (long) var5 * 1000L;
		return var7;
	}

	public static double maximum(double[] var0) {
		int var1 = var0.length;
		double var2 = var0[0];

		for (int var4 = 1; var4 < var1; ++var4) {
			if (var0[var4] > var2) {
				var2 = var0[var4];
			}
		}

		return var2;
	}

	public static float maximum(float[] var0) {
		int var1 = var0.length;
		float var2 = var0[0];

		for (int var3 = 1; var3 < var1; ++var3) {
			if (var0[var3] > var2) {
				var2 = var0[var3];
			}
		}

		return var2;
	}

	public static int maximum(int[] var0) {
		int var1 = var0.length;
		int var2 = var0[0];

		for (int var3 = 1; var3 < var1; ++var3) {
			if (var0[var3] > var2) {
				var2 = var0[var3];
			}
		}

		return var2;
	}

	public static long maximum(long[] var0) {
		long var1 = (long) var0.length;
		long var3 = var0[0];

		for (int var5 = 1; (long) var5 < var1; ++var5) {
			if (var0[var5] > var3) {
				var3 = var0[var5];
			}
		}

		return var3;
	}

	public static double minimum(double[] var0) {
		int var1 = var0.length;
		double var2 = var0[0];

		for (int var4 = 1; var4 < var1; ++var4) {
			if (var0[var4] < var2) {
				var2 = var0[var4];
			}
		}

		return var2;
	}

	public static float minimum(float[] var0) {
		int var1 = var0.length;
		float var2 = var0[0];

		for (int var3 = 1; var3 < var1; ++var3) {
			if (var0[var3] < var2) {
				var2 = var0[var3];
			}
		}

		return var2;
	}

	public static int minimum(int[] var0) {
		int var1 = var0.length;
		int var2 = var0[0];

		for (int var3 = 1; var3 < var1; ++var3) {
			if (var0[var3] < var2) {
				var2 = var0[var3];
			}
		}

		return var2;
	}

	public static long minimum(long[] var0) {
		long var1 = (long) var0.length;
		long var3 = var0[0];

		for (int var5 = 1; (long) var5 < var1; ++var5) {
			if (var0[var5] < var3) {
				var3 = var0[var5];
			}
		}

		return var3;
	}

	public static double maximumDifference(double[] var0) {
		return maximum(var0) - minimum(var0);
	}

	public static float maximumDifference(float[] var0) {
		return maximum(var0) - minimum(var0);
	}

	public static long maximumDifference(long[] var0) {
		return maximum(var0) - minimum(var0);
	}

	public static int maximumDifference(int[] var0) {
		return maximum(var0) - minimum(var0);
	}

	public static double minimumDifference(double[] var0) {
		double[] var1 = selectionSort(var0);
		double var2 = (double) var0.length;
		double var4 = var1[1] - var1[0];
		double var6 = var4;

		for (int var8 = 1; (double) var8 < var2 - 1.0D; ++var8) {
			var4 = var1[var8 + 1] - var1[var8];
			if (var4 < var6) {
				var6 = var4;
			}
		}

		return var6;
	}

	public static float minimumDifference(float[] var0) {
		float[] var1 = selectionSort(var0);
		float var2 = (float) var0.length;
		float var3 = var1[1] - var1[0];
		float var4 = var3;

		for (int var5 = 1; (float) var5 < var2 - 1.0F; ++var5) {
			var3 = var1[var5 + 1] - var1[var5];
			if (var3 < var4) {
				var4 = var3;
			}
		}

		return var4;
	}

	public static long minimumDifference(long[] var0) {
		long[] var1 = selectionSort(var0);
		long var2 = (long) var0.length;
		long var4 = var1[1] - var1[0];
		long var6 = var4;

		for (int var8 = 1; (long) var8 < var2 - 1L; ++var8) {
			var4 = var1[var8 + 1] - var1[var8];
			if (var4 < var6) {
				var6 = var4;
			}
		}

		return var6;
	}

	public static int minimumDifference(int[] var0) {
		int[] var1 = selectionSort(var0);
		int var2 = var0.length;
		int var3 = var1[1] - var1[0];
		int var4 = var3;

		for (int var5 = 1; var5 < var2 - 1; ++var5) {
			var3 = var1[var5 + 1] - var1[var5];
			if (var3 < var4) {
				var4 = var3;
			}
		}

		return var4;
	}

	public static double[] reverseArray(double[] var0) {
		int var1 = var0.length;
		double[] var2 = new double[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = var0[var1 - 1 - var3];
		}

		return var2;
	}

	public static float[] reverseArray(float[] var0) {
		int var1 = var0.length;
		float[] var2 = new float[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = var0[var1 - 1 - var3];
		}

		return var2;
	}

	public static int[] reverseArray(int[] var0) {
		int var1 = var0.length;
		int[] var2 = new int[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = var0[var1 - 1 - var3];
		}

		return var2;
	}

	public static long[] reverseArray(long[] var0) {
		int var1 = var0.length;
		long[] var2 = new long[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = var0[var1 - 1 - var3];
		}

		return var2;
	}

	public static char[] reverseArray(char[] var0) {
		int var1 = var0.length;
		char[] var2 = new char[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = var0[var1 - 1 - var3];
		}

		return var2;
	}

	public static double[] arrayAbs(double[] var0) {
		int var1 = var0.length;
		double[] var2 = new double[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = Math.abs(var0[var3]);
		}

		return var2;
	}

	public static float[] arrayAbs(float[] var0) {
		int var1 = var0.length;
		float[] var2 = new float[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = Math.abs(var0[var3]);
		}

		return var2;
	}

	public static long[] arrayAbs(long[] var0) {
		int var1 = var0.length;
		long[] var2 = new long[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = Math.abs(var0[var3]);
		}

		return var2;
	}

	public static int[] arrayAbs(int[] var0) {
		int var1 = var0.length;
		int[] var2 = new int[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = Math.abs(var0[var3]);
		}

		return var2;
	}

	public static double[] arrayMultByConstant(double[] var0, double var1) {
		int var3 = var0.length;
		double[] var4 = new double[var3];

		for (int var5 = 0; var5 < var3; ++var5) {
			var4[var5] = var0[var5] * var1;
		}

		return var4;
	}

	public static double[] arrayMultByConstant(int[] var0, double var1) {
		int var3 = var0.length;
		double[] var4 = new double[var3];

		for (int var5 = 0; var5 < var3; ++var5) {
			var4[var5] = (double) var0[var5] * var1;
		}

		return var4;
	}

	public static double[] arrayMultByConstant(double[] var0, int var1) {
		int var2 = var0.length;
		double[] var3 = new double[var2];

		for (int var4 = 0; var4 < var2; ++var4) {
			var3[var4] = var0[var4] * (double) var1;
		}

		return var3;
	}

	public static double[] arrayMultByConstant(int[] var0, int var1) {
		int var2 = var0.length;
		double[] var3 = new double[var2];

		for (int var4 = 0; var4 < var2; ++var4) {
			var3[var4] = (double) (var0[var4] * var1);
		}

		return var3;
	}

	public static double[] log10Elements(double[] var0) {
		int var1 = var0.length;
		double[] var2 = new double[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = Math.log10(var0[var3]);
		}

		return var2;
	}

	public static float[] log10Elements(float[] var0) {
		int var1 = var0.length;
		float[] var2 = new float[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (float) Math.log10((double) var0[var3]);
		}

		return var2;
	}

	public static double[] lnElements(double[] var0) {
		int var1 = var0.length;
		double[] var2 = new double[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = Math.log10(var0[var3]);
		}

		return var2;
	}

	public static float[] lnElements(float[] var0) {
		int var1 = var0.length;
		float[] var2 = new float[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (float) Math.log10((double) var0[var3]);
		}

		return var2;
	}

	public static double[] squareRootElements(double[] var0) {
		int var1 = var0.length;
		double[] var2 = new double[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = Math.sqrt(var0[var3]);
		}

		return var2;
	}

	public static float[] squareRootElements(float[] var0) {
		int var1 = var0.length;
		float[] var2 = new float[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (float) Math.sqrt((double) var0[var3]);
		}

		return var2;
	}

	public static double[] raiseElementsToPower(double[] var0, double var1) {
		int var3 = var0.length;
		double[] var4 = new double[var3];

		for (int var5 = 0; var5 < var3; ++var5) {
			var4[var5] = Math.pow(var0[var5], var1);
		}

		return var4;
	}

	public static double[] raiseElementsToPower(double[] var0, int var1) {
		int var2 = var0.length;
		double[] var3 = new double[var2];

		for (int var4 = 0; var4 < var2; ++var4) {
			var3[var4] = Math.pow(var0[var4], (double) var1);
		}

		return var3;
	}

	public static float[] raiseElementsToPower(float[] var0, float var1) {
		int var2 = var0.length;
		float[] var3 = new float[var2];

		for (int var4 = 0; var4 < var2; ++var4) {
			var3[var4] = (float) Math.pow((double) var0[var4], (double) var1);
		}

		return var3;
	}

	public static float[] raiseElementsToPower(float[] var0, int var1) {
		int var2 = var0.length;
		float[] var3 = new float[var2];

		for (int var4 = 0; var4 < var2; ++var4) {
			var3[var4] = (float) Math.pow((double) var0[var4], (double) var1);
		}

		return var3;
	}

	public static double[] invertElements(double[] var0) {
		int var1 = var0.length;
		double[] var2 = new double[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = 1.0D / var0[var3];
		}

		return var2;
	}

	public static float[] invertElements(float[] var0) {
		int var1 = var0.length;
		float[] var2 = new float[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = 1.0F / var0[var3];
		}

		return var2;
	}

	public static int[] indicesOf(double[] var0, double var1) {
		int[] var3 = null;
		int var4 = 0;
		ArrayList<Integer> var5 = new ArrayList<>();

		int var6;
		for (var6 = 0; var6 < var0.length; ++var6) {
			if (var0[var6] == var1) {
				++var4;
				var5.add(new Integer(var6));
			}
		}

		if (var4 != 0) {
			var3 = new int[var4];

			for (var6 = 0; var6 < var4; ++var6) {
				var3[var6] = (Integer) var5.get(var6);
			}
		}

		return var3;
	}

	public static int[] indicesOf(float[] var0, float var1) {
		int[] var2 = null;
		int var3 = 0;
		ArrayList<Integer> var4 = new ArrayList<>();

		int var5;
		for (var5 = 0; var5 < var0.length; ++var5) {
			if (var0[var5] == var1) {
				++var3;
				var4.add(new Integer(var5));
			}
		}

		if (var3 != 0) {
			var2 = new int[var3];

			for (var5 = 0; var5 < var3; ++var5) {
				var2[var5] = (Integer) var4.get(var5);
			}
		}

		return var2;
	}

	public static int[] indicesOf(long[] var0, long var1) {
		int[] var3 = null;
		int var4 = 0;
		ArrayList<Integer> var5 = new ArrayList<>();

		int var6;
		for (var6 = 0; var6 < var0.length; ++var6) {
			if (var0[var6] == var1) {
				++var4;
				var5.add(new Integer(var6));
			}
		}

		if (var4 != 0) {
			var3 = new int[var4];

			for (var6 = 0; var6 < var4; ++var6) {
				var3[var6] = (Integer) var5.get(var6);
			}
		}

		return var3;
	}

	public static int[] indicesOf(int[] var0, int var1) {
		int[] var2 = null;
		int var3 = 0;
		ArrayList<Integer> var4 = new ArrayList<>();

		int var5;
		for (var5 = 0; var5 < var0.length; ++var5) {
			if (var0[var5] == var1) {
				++var3;
				var4.add(new Integer(var5));
			}
		}

		if (var3 != 0) {
			var2 = new int[var3];

			for (var5 = 0; var5 < var3; ++var5) {
				var2[var5] = (Integer) var4.get(var5);
			}
		}

		return var2;
	}

	public static int[] indicesOf(short[] var0, short var1) {
		int[] var2 = null;
		int var3 = 0;
		ArrayList<Integer> var4 = new ArrayList<>();

		int var5;
		for (var5 = 0; var5 < var0.length; ++var5) {
			if (var0[var5] == var1) {
				++var3;
				var4.add(new Integer(var5));
			}
		}

		if (var3 != 0) {
			var2 = new int[var3];

			for (var5 = 0; var5 < var3; ++var5) {
				var2[var5] = (Integer) var4.get(var5);
			}
		}

		return var2;
	}

	public static int[] indicesOf(byte[] var0, byte var1) {
		int[] var2 = null;
		int var3 = 0;
		ArrayList<Integer> var4 = new ArrayList<>();

		int var5;
		for (var5 = 0; var5 < var0.length; ++var5) {
			if (var0[var5] == var1) {
				++var3;
				var4.add(new Integer(var5));
			}
		}

		if (var3 != 0) {
			var2 = new int[var3];

			for (var5 = 0; var5 < var3; ++var5) {
				var2[var5] = (Integer) var4.get(var5);
			}
		}

		return var2;
	}

	public static int[] indicesOf(char[] var0, char var1) {
		int[] var2 = null;
		int var3 = 0;
		ArrayList<Integer> var4 = new ArrayList<>();

		int var5;
		for (var5 = 0; var5 < var0.length; ++var5) {
			if (var0[var5] == var1) {
				++var3;
				var4.add(new Integer(var5));
			}
		}

		if (var3 != 0) {
			var2 = new int[var3];

			for (var5 = 0; var5 < var3; ++var5) {
				var2[var5] = (Integer) var4.get(var5);
			}
		}

		return var2;
	}

	public static int[] indicesOf(String[] var0, String var1) {
		int[] var2 = null;
		int var3 = 0;
		ArrayList<Integer> var4 = new ArrayList<>();

		int var5;
		for (var5 = 0; var5 < var0.length; ++var5) {
			if (var0[var5].equals(var1)) {
				++var3;
				var4.add(new Integer(var5));
			}
		}

		if (var3 != 0) {
			var2 = new int[var3];

			for (var5 = 0; var5 < var3; ++var5) {
				var2[var5] = (Integer) var4.get(var5);
			}
		}

		return var2;
	}

	public static int[] indicesOf(Object[] var0, Object var1) {
		int[] var2 = null;
		int var3 = 0;
		ArrayList<Integer> var4 = new ArrayList<>();

		int var5;
		for (var5 = 0; var5 < var0.length; ++var5) {
			if (var0[var5].equals(var1)) {
				++var3;
				var4.add(new Integer(var5));
			}
		}

		if (var3 != 0) {
			var2 = new int[var3];

			for (var5 = 0; var5 < var3; ++var5) {
				var2[var5] = (Integer) var4.get(var5);
			}
		}

		return var2;
	}

	public static int indexOf(double[] var0, double var1) {
		int var3 = -1;
		boolean var4 = true;
		int var5 = 0;

		while (var4) {
			if (var0[var5] == var1) {
				var3 = var5;
				var4 = false;
			} else {
				++var5;
				if (var5 >= var0.length) {
					var4 = false;
				}
			}
		}

		return var3;
	}

	public static int indexOf(float[] var0, float var1) {
		int var2 = -1;
		boolean var3 = true;
		int var4 = 0;

		while (var3) {
			if (var0[var4] == var1) {
				var2 = var4;
				var3 = false;
			} else {
				++var4;
				if (var4 >= var0.length) {
					var3 = false;
				}
			}
		}

		return var2;
	}

	public static int indexOf(long[] var0, long var1) {
		int var3 = -1;
		boolean var4 = true;
		int var5 = 0;

		while (var4) {
			if (var0[var5] == var1) {
				var3 = var5;
				var4 = false;
			} else {
				++var5;
				if (var5 >= var0.length) {
					var4 = false;
				}
			}
		}

		return var3;
	}

	public static int indexOf(int[] var0, int var1) {
		int var2 = -1;
		boolean var3 = true;
		int var4 = 0;

		while (var3) {
			if (var0[var4] == var1) {
				var2 = var4;
				var3 = false;
			} else {
				++var4;
				if (var4 >= var0.length) {
					var3 = false;
				}
			}
		}

		return var2;
	}

	public static int indexOf(byte[] var0, byte var1) {
		int var2 = -1;
		boolean var3 = true;
		int var4 = 0;

		while (var3) {
			if (var0[var4] == var1) {
				var2 = var4;
				var3 = false;
			} else {
				++var4;
				if (var4 >= var0.length) {
					var3 = false;
				}
			}
		}

		return var2;
	}

	public static int indexOf(short[] var0, short var1) {
		int var2 = -1;
		boolean var3 = true;
		int var4 = 0;

		while (var3) {
			if (var0[var4] == var1) {
				var2 = var4;
				var3 = false;
			} else {
				++var4;
				if (var4 >= var0.length) {
					var3 = false;
				}
			}
		}

		return var2;
	}

	public static int indexOf(char[] var0, char var1) {
		int var2 = -1;
		boolean var3 = true;
		int var4 = 0;

		while (var3) {
			if (var0[var4] == var1) {
				var2 = var4;
				var3 = false;
			} else {
				++var4;
				if (var4 >= var0.length) {
					var3 = false;
				}
			}
		}

		return var2;
	}

	public static int indexOf(String[] var0, String var1) {
		int var2 = -1;
		boolean var3 = true;
		int var4 = 0;

		while (var3) {
			if (var0[var4].equals(var1)) {
				var2 = var4;
				var3 = false;
			} else {
				++var4;
				if (var4 >= var0.length) {
					var3 = false;
				}
			}
		}

		return var2;
	}

	public static int indexOf(Object[] var0, Object var1) {
		int var2 = -1;
		boolean var3 = true;
		int var4 = 0;

		while (var3) {
			if (var0[var4].equals(var1)) {
				var2 = var4;
				var3 = false;
			} else {
				++var4;
				if (var4 >= var0.length) {
					var3 = false;
				}
			}
		}

		return var2;
	}

	public static double nearestElementValue(double[] var0, double var1) {
		double var3 = Math.abs(var0[0] - var1);
		double var5 = var0[0];

		for (int var7 = 1; var7 < var0.length; ++var7) {
			if (Math.abs(var0[var7] - var1) < var3) {
				var3 = Math.abs(var0[var7] - var1);
				var5 = var0[var7];
			}
		}

		return var5;
	}

	public static int nearestElementIndex(double[] var0, double var1) {
		double var3 = Math.abs(var0[0] - var1);
		int var5 = 0;

		for (int var6 = 1; var6 < var0.length; ++var6) {
			if (Math.abs(var0[var6] - var1) < var3) {
				var3 = Math.abs(var0[var6] - var1);
				var5 = var6;
			}
		}

		return var5;
	}

	public static double nearestLowerElementValue(double[] var0, double var1) {
		double var3 = 0.0D;
		double var5 = 0.0D;
		double var7 = 0.0D;
		int var9 = 0;
		boolean var10 = true;
		double var11 = var0[0];

		while (var10) {
			if (var0[var9] < var11) {
				var11 = var0[var9];
			}

			if (var1 - var0[var9] >= 0.0D) {
				var3 = var1 - var0[var9];
				var7 = var0[var9];
				var10 = false;
			} else {
				++var9;
				if (var9 > var0.length - 1) {
					var7 = var11;
					var3 = var11 - var1;
					var10 = false;
				}
			}
		}

		for (int var13 = 0; var13 < var0.length; ++var13) {
			var5 = var1 - var0[var13];
			if (var5 >= 0.0D && var5 < var3) {
				var3 = var5;
				var7 = var0[var13];
			}
		}

		return var7;
	}

	public static int nearestLowerElementIndex(double[] var0, double var1) {
		double var3 = 0.0D;
		double var5 = 0.0D;
		int var7 = 0;
		int var8 = 0;
		boolean var9 = true;
		double var10 = var0[0];
		int var12 = 0;

		while (var9) {
			if (var0[var8] < var10) {
				var10 = var0[var8];
				var12 = var8;
			}

			if (var1 - var0[var8] >= 0.0D) {
				var3 = var1 - var0[var8];
				var7 = var8;
				var9 = false;
			} else {
				++var8;
				if (var8 > var0.length - 1) {
					var7 = var12;
					var3 = var10 - var1;
					var9 = false;
				}
			}
		}

		for (int var13 = 0; var13 < var0.length; ++var13) {
			var5 = var1 - var0[var13];
			if (var5 >= 0.0D && var5 < var3) {
				var3 = var5;
				var7 = var13;
			}
		}

		return var7;
	}

	public static double nearestHigherElementValue(double[] var0, double var1) {
		double var3 = 0.0D;
		double var5 = 0.0D;
		double var7 = 0.0D;
		int var9 = 0;
		boolean var10 = true;
		double var11 = var0[0];

		while (var10) {
			if (var0[var9] > var11) {
				var11 = var0[var9];
			}

			if (var0[var9] - var1 >= 0.0D) {
				var3 = var1 - var0[var9];
				var7 = var0[var9];
				var10 = false;
			} else {
				++var9;
				if (var9 > var0.length - 1) {
					var7 = var11;
					var3 = var1 - var11;
					var10 = false;
				}
			}
		}

		for (int var13 = 0; var13 < var0.length; ++var13) {
			var5 = var0[var13] - var1;
			if (var5 >= 0.0D && var5 < var3) {
				var3 = var5;
				var7 = var0[var13];
			}
		}

		return var7;
	}

	public static int nearestHigherElementIndex(double[] var0, double var1) {
		double var3 = 0.0D;
		double var5 = 0.0D;
		int var7 = 0;
		int var8 = 0;
		boolean var9 = true;
		double var10 = var0[0];
		int var12 = 0;

		while (var9) {
			if (var0[var8] > var10) {
				var10 = var0[var8];
				var12 = var8;
			}

			if (var0[var8] - var1 >= 0.0D) {
				var3 = var1 - var0[var8];
				var7 = var8;
				var9 = false;
			} else {
				++var8;
				if (var8 > var0.length - 1) {
					var7 = var12;
					var3 = var1 - var10;
					var9 = false;
				}
			}
		}

		for (int var13 = 0; var13 < var0.length; ++var13) {
			var5 = var0[var13] - var1;
			if (var5 >= 0.0D && var5 < var3) {
				var3 = var5;
				var7 = var13;
			}
		}

		return var7;
	}

	public static int nearestElementValue(int[] var0, int var1) {
		int var2 = Math.abs(var0[0] - var1);
		int var3 = var0[0];

		for (int var4 = 1; var4 < var0.length; ++var4) {
			if (Math.abs(var0[var4] - var1) < var2) {
				var2 = Math.abs(var0[var4] - var1);
				var3 = var0[var4];
			}
		}

		return var3;
	}

	public static int nearestElementIndex(int[] var0, int var1) {
		int var2 = Math.abs(var0[0] - var1);
		int var3 = 0;

		for (int var4 = 1; var4 < var0.length; ++var4) {
			if (Math.abs(var0[var4] - var1) < var2) {
				var2 = Math.abs(var0[var4] - var1);
				var3 = var4;
			}
		}

		return var3;
	}

	public static int nearestLowerElementValue(int[] var0, int var1) {
		int var2 = 0;
		int var4 = 0;
		int var5 = 0;
		boolean var6 = true;
		int var7 = var0[0];

		while (var6) {
			if (var0[var5] < var7) {
				var7 = var0[var5];
			}

			if (var1 - var0[var5] >= 0) {
				var2 = var1 - var0[var5];
				var4 = var0[var5];
				var6 = false;
			} else {
				++var5;
				if (var5 > var0.length - 1) {
					var4 = var7;
					var2 = var7 - var1;
					var6 = false;
				}
			}
		}

		for (int var8 = 0; var8 < var0.length; ++var8) {
			int var9 = var1 - var0[var8];
			if (var9 >= 0 && var9 < var2) {
				var2 = var9;
				var4 = var0[var8];
			}
		}

		return var4;
	}

	public static int nearestLowerElementIndex(int[] var0, int var1) {
		int var2 = 0;
		int var4 = 0;
		int var5 = 0;
		boolean var6 = true;
		int var7 = var0[0];
		int var8 = 0;

		while (var6) {
			if (var0[var5] < var7) {
				var7 = var0[var5];
				var8 = var5;
			}

			if (var1 - var0[var5] >= 0) {
				var2 = var1 - var0[var5];
				var4 = var5;
				var6 = false;
			} else {
				++var5;
				if (var5 > var0.length - 1) {
					var4 = var8;
					var2 = var7 - var1;
					var6 = false;
				}
			}
		}

		for (int var9 = 0; var9 < var0.length; ++var9) {
			int var10 = var1 - var0[var9];
			if (var10 >= 0 && var10 < var2) {
				var2 = var10;
				var4 = var9;
			}
		}

		return var4;
	}

	public static int nearestHigherElementValue(int[] var0, int var1) {
		int var2 = 0;
		int var4 = 0;
		int var5 = 0;
		boolean var6 = true;
		int var7 = var0[0];

		while (var6) {
			if (var0[var5] > var7) {
				var7 = var0[var5];
			}

			if (var0[var5] - var1 >= 0) {
				var2 = var1 - var0[var5];
				var4 = var0[var5];
				var6 = false;
			} else {
				++var5;
				if (var5 > var0.length - 1) {
					var4 = var7;
					var2 = var1 - var7;
					var6 = false;
				}
			}
		}

		for (int var8 = 0; var8 < var0.length; ++var8) {
			int var9 = var0[var8] - var1;
			if (var9 >= 0 && var9 < var2) {
				var2 = var9;
				var4 = var0[var8];
			}
		}

		return var4;
	}

	public static int nearestHigherElementIndex(int[] var0, int var1) {
		int var2 = 0;
		int var4 = 0;
		int var5 = 0;
		boolean var6 = true;
		int var7 = var0[0];
		int var8 = 0;

		while (var6) {
			if (var0[var5] > var7) {
				var7 = var0[var5];
				var8 = var5;
			}

			if (var0[var5] - var1 >= 0) {
				var2 = var1 - var0[var5];
				var4 = var5;
				var6 = false;
			} else {
				++var5;
				if (var5 > var0.length - 1) {
					var4 = var8;
					var2 = var1 - var7;
					var6 = false;
				}
			}
		}

		for (int var9 = 0; var9 < var0.length; ++var9) {
			int var10 = var0[var9] - var1;
			if (var10 >= 0 && var10 < var2) {
				var2 = var10;
				var4 = var9;
			}
		}

		return var4;
	}

	public static double arraySum(double[] var0) {
		double var1 = 0.0D;
		double[] var3 = var0;
		int var4 = var0.length;

		for (int var5 = 0; var5 < var4; ++var5) {
			double var6 = var3[var5];
			var1 += var6;
		}

		return var1;
	}

	public static float arraySum(float[] var0) {
		float var1 = 0.0F;
		float[] var2 = var0;
		int var3 = var0.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			float var5 = var2[var4];
			var1 += var5;
		}

		return var1;
	}

	public static int arraySum(int[] var0) {
		int var1 = 0;
		int[] var2 = var0;
		int var3 = var0.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			int var5 = var2[var4];
			var1 += var5;
		}

		return var1;
	}

	public static long arraySum(long[] var0) {
		long var1 = 0L;
		long[] var3 = var0;
		int var4 = var0.length;

		for (int var5 = 0; var5 < var4; ++var5) {
			long var6 = var3[var5];
			var1 += var6;
		}

		return var1;
	}

	public static long arrayPositiveElementsSum(long[] var0) {
		long var1 = 0L;
		long[] var3 = var0;
		int var4 = var0.length;

		for (int var5 = 0; var5 < var4; ++var5) {
			long var6 = var3[var5];
			if (var6 > 0L) {
				var1 += var6;
			}
		}

		return var1;
	}

	public static double arrayProduct(double[] var0) {
		double var1 = 1.0D;
		double[] var3 = var0;
		int var4 = var0.length;

		for (int var5 = 0; var5 < var4; ++var5) {
			double var6 = var3[var5];
			var1 *= var6;
		}

		return var1;
	}

	public static float arrayProduct(float[] var0) {
		float var1 = 1.0F;
		float[] var2 = var0;
		int var3 = var0.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			float var5 = var2[var4];
			var1 *= var5;
		}

		return var1;
	}

	public static int arrayProduct(int[] var0) {
		int var1 = 1;
		int[] var2 = var0;
		int var3 = var0.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			int var5 = var2[var4];
			var1 *= var5;
		}

		return var1;
	}

	public static long arrayProduct(long[] var0) {
		long var1 = 1L;
		long[] var3 = var0;
		int var4 = var0.length;

		for (int var5 = 0; var5 < var4; ++var5) {
			long var6 = var3[var5];
			var1 *= var6;
		}

		return var1;
	}

	public static double[] concatenate(double[] var0, double[] var1) {
		int var2 = var0.length;
		int var3 = var1.length;
		int var4 = var2 + var3;
		double[] var5 = new double[var4];

		int var6;
		for (var6 = 0; var6 < var2; ++var6) {
			var5[var6] = var0[var6];
		}

		for (var6 = 0; var6 < var3; ++var6) {
			var5[var6 + var2] = var1[var6];
		}

		return var5;
	}

	public static float[] concatenate(float[] var0, float[] var1) {
		int var2 = var0.length;
		int var3 = var1.length;
		int var4 = var2 + var3;
		float[] var5 = new float[var4];

		int var6;
		for (var6 = 0; var6 < var2; ++var6) {
			var5[var6] = var0[var6];
		}

		for (var6 = 0; var6 < var3; ++var6) {
			var5[var6 + var2] = var1[var6];
		}

		return var5;
	}

	public static int[] concatenate(int[] var0, int[] var1) {
		int var2 = var0.length;
		int var3 = var1.length;
		int var4 = var2 + var3;
		int[] var5 = new int[var4];

		int var6;
		for (var6 = 0; var6 < var2; ++var6) {
			var5[var6] = var0[var6];
		}

		for (var6 = 0; var6 < var3; ++var6) {
			var5[var6 + var2] = var1[var6];
		}

		return var5;
	}

	public static long[] concatenate(long[] var0, long[] var1) {
		int var2 = var0.length;
		int var3 = var1.length;
		int var4 = var2 + var3;
		long[] var5 = new long[var4];

		int var6;
		for (var6 = 0; var6 < var2; ++var6) {
			var5[var6] = var0[var6];
		}

		for (var6 = 0; var6 < var3; ++var6) {
			var5[var6 + var2] = var1[var6];
		}

		return var5;
	}

	public static short[] concatenate(short[] var0, short[] var1) {
		int var2 = var0.length;
		int var3 = var1.length;
		int var4 = var2 + var3;
		short[] var5 = new short[var4];

		int var6;
		for (var6 = 0; var6 < var2; ++var6) {
			var5[var6] = var0[var6];
		}

		for (var6 = 0; var6 < var3; ++var6) {
			var5[var6 + var2] = var1[var6];
		}

		return var5;
	}

	public static byte[] concatenate(byte[] var0, byte[] var1) {
		int var2 = var0.length;
		int var3 = var1.length;
		int var4 = var2 + var3;
		byte[] var5 = new byte[var4];

		int var6;
		for (var6 = 0; var6 < var2; ++var6) {
			var5[var6] = var0[var6];
		}

		for (var6 = 0; var6 < var3; ++var6) {
			var5[var6 + var2] = var1[var6];
		}

		return var5;
	}

	public static char[] concatenate(char[] var0, char[] var1) {
		int var2 = var0.length;
		int var3 = var1.length;
		int var4 = var2 + var3;
		char[] var5 = new char[var4];

		int var6;
		for (var6 = 0; var6 < var2; ++var6) {
			var5[var6] = var0[var6];
		}

		for (var6 = 0; var6 < var3; ++var6) {
			var5[var6 + var2] = var1[var6];
		}

		return var5;
	}

	public static String[] concatenate(String[] var0, String[] var1) {
		int var2 = var0.length;
		int var3 = var1.length;
		int var4 = var2 + var3;
		String[] var5 = new String[var4];

		int var6;
		for (var6 = 0; var6 < var2; ++var6) {
			var5[var6] = var0[var6];
		}

		for (var6 = 0; var6 < var3; ++var6) {
			var5[var6 + var2] = var1[var6];
		}

		return var5;
	}

	public static Object[] concatenate(Object[] var0, Object[] var1) {
		int var2 = var0.length;
		int var3 = var1.length;
		int var4 = var2 + var3;
		Object[] var5 = new Object[var4];

		int var6;
		for (var6 = 0; var6 < var2; ++var6) {
			var5[var6] = var0[var6];
		}

		for (var6 = 0; var6 < var3; ++var6) {
			var5[var6 + var2] = var1[var6];
		}

		return var5;
	}

	public static double[] floatTOdouble(float[] var0) {
		int var1 = var0.length;
		double[] var2 = new double[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (double) var0[var3];
		}

		return var2;
	}

	public static double[] intTOdouble(int[] var0) {
		int var1 = var0.length;
		double[] var2 = new double[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (double) var0[var3];
		}

		return var2;
	}

	public static float[] intTOfloat(int[] var0) {
		int var1 = var0.length;
		float[] var2 = new float[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (float) var0[var3];
		}

		return var2;
	}

	public static long[] intTOlong(int[] var0) {
		int var1 = var0.length;
		long[] var2 = new long[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (long) var0[var3];
		}

		return var2;
	}

	public static double[] longTOdouble(long[] var0) {
		int var1 = var0.length;
		double[] var2 = new double[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (double) var0[var3];
		}

		return var2;
	}

	public static float[] longTOfloat(long[] var0) {
		int var1 = var0.length;
		float[] var2 = new float[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (float) var0[var3];
		}

		return var2;
	}

	public static double[] shortTOdouble(short[] var0) {
		int var1 = var0.length;
		double[] var2 = new double[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (double) var0[var3];
		}

		return var2;
	}

	public static float[] shortTOfloat(short[] var0) {
		int var1 = var0.length;
		float[] var2 = new float[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (float) var0[var3];
		}

		return var2;
	}

	public static long[] shortTOlong(short[] var0) {
		int var1 = var0.length;
		long[] var2 = new long[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (long) var0[var3];
		}

		return var2;
	}

	public static int[] shortTOint(short[] var0) {
		int var1 = var0.length;
		int[] var2 = new int[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = var0[var3];
		}

		return var2;
	}

	public static double[] byteTOdouble(byte[] var0) {
		int var1 = var0.length;
		double[] var2 = new double[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (double) var0[var3];
		}

		return var2;
	}

	public static float[] byteTOfloat(byte[] var0) {
		int var1 = var0.length;
		float[] var2 = new float[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (float) var0[var3];
		}

		return var2;
	}

	public static long[] byteTOlong(byte[] var0) {
		int var1 = var0.length;
		long[] var2 = new long[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (long) var0[var3];
		}

		return var2;
	}

	public static int[] byteTOint(byte[] var0) {
		int var1 = var0.length;
		int[] var2 = new int[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = var0[var3];
		}

		return var2;
	}

	public static short[] byteTOshort(byte[] var0) {
		int var1 = var0.length;
		short[] var2 = new short[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (short) var0[var3];
		}

		return var2;
	}

	public static int[] doubleTOint(double[] var0) {
		int var1 = var0.length;
		int[] var2 = new int[var1];

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = (int) var0[var3];
		}

		return var2;
	}

	public static void print(double[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.print(var0[var1] + "   ");
		}

		System.out.println();
	}

	public static void println(double[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.println(var0[var1] + "   ");
		}

	}

	public static void print(float[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.print(var0[var1] + "   ");
		}

		System.out.println();
	}

	public static void println(float[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.println(var0[var1] + "   ");
		}

	}

	public static void print(int[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.print(var0[var1] + "   ");
		}

		System.out.println();
	}

	public static void println(int[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.println(var0[var1] + "   ");
		}

	}

	public static void print(long[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.print(var0[var1] + "   ");
		}

		System.out.println();
	}

	public static void println(long[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.println(var0[var1] + "   ");
		}

	}

	public static void print(char[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.print(var0[var1] + "   ");
		}

		System.out.println();
	}

	public static void println(char[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.println(var0[var1] + "   ");
		}

	}

	public static void print(String[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.print(var0[var1] + "   ");
		}

		System.out.println();
	}

	public static void println(String[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.println(var0[var1] + "   ");
		}

	}

	public static void print(short[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.print(var0[var1] + "   ");
		}

		System.out.println();
	}

	public static void println(short[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.println(var0[var1] + "   ");
		}

	}

	public static void print(byte[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.print(var0[var1] + "   ");
		}

		System.out.println();
	}

	public static void println(byte[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.println(var0[var1] + "   ");
		}

	}

	public static void print(double[][] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			print(var0[var1]);
		}

	}

	public static Vector<Object> selectSortVector(double[] var0) {
		ArrayList<?> var1 = selectSortArrayList(var0);
		Vector<Object> var2 = null;
		if (var1 != null) {
			int var3 = var1.size();
			var2 = new Vector<Object>(var3);

			for (int var4 = 0; var4 < var3; ++var4) {
				var2.addElement(var1.get(var4));
			}
		}

		return var2;
	}

	public static ArrayList<Object> selectSortArrayList(double[] var0) {
		int var2 = -1;
		int var3 = var0.length;
		double var4 = 0.0D;
		double[] var7 = new double[var3];
		int[] var8 = new int[var3];

		int var9;
		for (var9 = 0; var9 < var3; var8[var9] = var9++) {
			var7[var9] = var0[var9];
		}

		while (var2 != var3 - 1) {
			int var10 = var2 + 1;

			for (var9 = var2 + 2; var9 < var3; ++var9) {
				if (var7[var9] < var7[var10]) {
					var10 = var9;
				}
			}

			++var2;
			var4 = var7[var10];
			var7[var10] = var7[var2];
			var7[var2] = var4;
			int var11 = var8[var10];
			var8[var10] = var8[var2];
			var8[var2] = var11;
		}

		ArrayList<Object> var12 = new ArrayList<>();
		var12.add(var0);
		var12.add(var7);
		var12.add(var8);
		return var12;
	}

	public static double[] selectionSort(double[] var0) {
		int var2 = -1;
		int var3 = var0.length;
		double var4 = 0.0D;
		double[] var6 = new double[var3];

		int var7;
		for (var7 = 0; var7 < var3; ++var7) {
			var6[var7] = var0[var7];
		}

		while (var2 != var3 - 1) {
			int var8 = var2 + 1;

			for (var7 = var2 + 2; var7 < var3; ++var7) {
				if (var6[var7] < var6[var8]) {
					var8 = var7;
				}
			}

			++var2;
			var4 = var6[var8];
			var6[var8] = var6[var2];
			var6[var2] = var4;
		}

		return var6;
	}

	public static float[] selectionSort(float[] var0) {
		int var2 = -1;
		int var3 = var0.length;
		float var4 = 0.0F;
		float[] var5 = new float[var3];

		int var6;
		for (var6 = 0; var6 < var3; ++var6) {
			var5[var6] = var0[var6];
		}

		while (var2 != var3 - 1) {
			int var7 = var2 + 1;

			for (var6 = var2 + 2; var6 < var3; ++var6) {
				if (var5[var6] < var5[var7]) {
					var7 = var6;
				}
			}

			++var2;
			var4 = var5[var7];
			var5[var7] = var5[var2];
			var5[var2] = var4;
		}

		return var5;
	}

	public static int[] selectionSort(int[] var0) {
		int var2 = -1;
		int var3 = var0.length;
		int[] var5 = new int[var3];

		int var6;
		for (var6 = 0; var6 < var3; ++var6) {
			var5[var6] = var0[var6];
		}

		while (var2 != var3 - 1) {
			int var7 = var2 + 1;

			for (var6 = var2 + 2; var6 < var3; ++var6) {
				if (var5[var6] < var5[var7]) {
					var7 = var6;
				}
			}

			++var2;
			int var8 = var5[var7];
			var5[var7] = var5[var2];
			var5[var2] = var8;
		}

		return var5;
	}

	public static long[] selectionSort(long[] var0) {
		int var2 = -1;
		int var3 = var0.length;
		long var4 = 0L;
		long[] var6 = new long[var3];

		int var7;
		for (var7 = 0; var7 < var3; ++var7) {
			var6[var7] = var0[var7];
		}

		while (var2 != var3 - 1) {
			int var8 = var2 + 1;

			for (var7 = var2 + 2; var7 < var3; ++var7) {
				if (var6[var7] < var6[var8]) {
					var8 = var7;
				}
			}

			++var2;
			var4 = var6[var8];
			var6[var8] = var6[var2];
			var6[var2] = var4;
		}

		return var6;
	}

	public static void selectionSort(double[] var0, double[] var1, int[] var2) {
		int var4 = -1;
		int var5 = var0.length;
		double var6 = 0.0D;

		int var9;
		for (var9 = 0; var9 < var5; var2[var9] = var9++) {
			var1[var9] = var0[var9];
		}

		while (var4 != var5 - 1) {
			int var10 = var4 + 1;

			for (var9 = var4 + 2; var9 < var5; ++var9) {
				if (var1[var9] < var1[var10]) {
					var10 = var9;
				}
			}

			++var4;
			var6 = var1[var10];
			var1[var10] = var1[var4];
			var1[var4] = var6;
			int var11 = var2[var10];
			var2[var10] = var2[var4];
			var2[var4] = var11;
		}

	}

	public static void selectionSort(double[] var0, double[] var1, double[] var2, double[] var3) {
		int var5 = -1;
		int var6 = var0.length;
		int var7 = var1.length;
		if (var6 != var7) {
			throw new IllegalArgumentException("First argument array, aa, (length = " + var6
					+ ") and the second argument array, bb, (length = " + var7 + ") should be the same length");
		} else {
			int var8 = var2.length;
			if (var8 < var6) {
				throw new IllegalArgumentException("The third argument array, cc, (length = " + var8
						+ ") should be at least as long as the first argument array, aa, (length = " + var6 + ")");
			} else {
				int var9 = var3.length;
				if (var9 < var7) {
					throw new IllegalArgumentException("The fourth argument array, dd, (length = " + var9
							+ ") should be at least as long as the second argument array, bb, (length = " + var7 + ")");
				} else {
					double var10 = 0.0D;
					double var12 = 0.0D;

					int var14;
					for (var14 = 0; var14 < var6; ++var14) {
						var2[var14] = var0[var14];
						var3[var14] = var1[var14];
					}

					while (var5 != var6 - 1) {
						int var15 = var5 + 1;

						for (var14 = var5 + 2; var14 < var6; ++var14) {
							if (var2[var14] < var2[var15]) {
								var15 = var14;
							}
						}

						++var5;
						var10 = var2[var15];
						var2[var15] = var2[var5];
						var2[var5] = var10;
						var12 = var3[var15];
						var3[var15] = var3[var5];
						var3[var5] = var12;
					}

				}
			}
		}
	}

	public static void selectionSort(float[] var0, float[] var1, float[] var2, float[] var3) {
		int var5 = -1;
		int var6 = var0.length;
		int var7 = var1.length;
		if (var6 != var7) {
			throw new IllegalArgumentException("First argument array, aa, (length = " + var6
					+ ") and the second argument array, bb, (length = " + var7 + ") should be the same length");
		} else {
			int var8 = var2.length;
			if (var8 < var6) {
				throw new IllegalArgumentException("The third argument array, cc, (length = " + var8
						+ ") should be at least as long as the first argument array, aa, (length = " + var6 + ")");
			} else {
				int var9 = var3.length;
				if (var9 < var7) {
					throw new IllegalArgumentException("The fourth argument array, dd, (length = " + var9
							+ ") should be at least as long as the second argument array, bb, (length = " + var7 + ")");
				} else {
					float var10 = 0.0F;
					float var11 = 0.0F;

					int var12;
					for (var12 = 0; var12 < var6; ++var12) {
						var2[var12] = var0[var12];
						var3[var12] = var1[var12];
					}

					while (var5 != var6 - 1) {
						int var13 = var5 + 1;

						for (var12 = var5 + 2; var12 < var6; ++var12) {
							if (var2[var12] < var2[var13]) {
								var13 = var12;
							}
						}

						++var5;
						var10 = var2[var13];
						var2[var13] = var2[var5];
						var2[var5] = var10;
						var11 = var3[var13];
						var3[var13] = var3[var5];
						var3[var5] = var11;
					}

				}
			}
		}
	}

	public static void selectionSort(long[] var0, long[] var1, long[] var2, long[] var3) {
		int var5 = -1;
		int var6 = var0.length;
		int var7 = var1.length;
		if (var6 != var7) {
			throw new IllegalArgumentException("First argument array, aa, (length = " + var6
					+ ") and the second argument array, bb, (length = " + var7 + ") should be the same length");
		} else {
			int var8 = var2.length;
			if (var8 < var6) {
				throw new IllegalArgumentException("The third argument array, cc, (length = " + var8
						+ ") should be at least as long as the first argument array, aa, (length = " + var6 + ")");
			} else {
				int var9 = var3.length;
				if (var9 < var7) {
					throw new IllegalArgumentException("The fourth argument array, dd, (length = " + var9
							+ ") should be at least as long as the second argument array, bb, (length = " + var7 + ")");
				} else {
					long var10 = 0L;
					long var12 = 0L;

					int var14;
					for (var14 = 0; var14 < var6; ++var14) {
						var2[var14] = var0[var14];
						var3[var14] = var1[var14];
					}

					while (var5 != var6 - 1) {
						int var15 = var5 + 1;

						for (var14 = var5 + 2; var14 < var6; ++var14) {
							if (var2[var14] < var2[var15]) {
								var15 = var14;
							}
						}

						++var5;
						var10 = var2[var15];
						var2[var15] = var2[var5];
						var2[var5] = var10;
						var12 = var3[var15];
						var3[var15] = var3[var5];
						var3[var5] = var12;
					}

				}
			}
		}
	}

	public static void selectionSort(int[] var0, int[] var1, int[] var2, int[] var3) {
		int var5 = -1;
		int var6 = var0.length;
		int var7 = var1.length;
		if (var6 != var7) {
			throw new IllegalArgumentException("First argument array, aa, (length = " + var6
					+ ") and the second argument array, bb, (length = " + var7 + ") should be the same length");
		} else {
			int var8 = var2.length;
			if (var8 < var6) {
				throw new IllegalArgumentException("The third argument array, cc, (length = " + var8
						+ ") should be at least as long as the first argument array, aa, (length = " + var6 + ")");
			} else {
				int var9 = var3.length;
				if (var9 < var7) {
					throw new IllegalArgumentException("The fourth argument array, dd, (length = " + var9
							+ ") should be at least as long as the second argument array, bb, (length = " + var7 + ")");
				} else {

					int var12;
					for (var12 = 0; var12 < var6; ++var12) {
						var2[var12] = var0[var12];
						var3[var12] = var1[var12];
					}

					while (var5 != var6 - 1) {
						int var13 = var5 + 1;

						for (var12 = var5 + 2; var12 < var6; ++var12) {
							if (var2[var12] < var2[var13]) {
								var13 = var12;
							}
						}

						++var5;
						int var14 = var2[var13];
						var2[var13] = var2[var5];
						var2[var5] = var14;
						int var15 = var3[var13];
						var3[var13] = var3[var5];
						var3[var5] = var15;
					}

				}
			}
		}
	}

	public static void selectionSort(double[] var0, long[] var1, double[] var2, long[] var3) {
		int var5 = -1;
		int var6 = var0.length;
		int var7 = var1.length;
		if (var6 != var7) {
			throw new IllegalArgumentException("First argument array, aa, (length = " + var6
					+ ") and the second argument array, bb, (length = " + var7 + ") should be the same length");
		} else {
			int var8 = var2.length;
			if (var8 < var6) {
				throw new IllegalArgumentException("The third argument array, cc, (length = " + var8
						+ ") should be at least as long as the first argument array, aa, (length = " + var6 + ")");
			} else {
				int var9 = var3.length;
				if (var9 < var7) {
					throw new IllegalArgumentException("The fourth argument array, dd, (length = " + var9
							+ ") should be at least as long as the second argument array, bb, (length = " + var7 + ")");
				} else {
					double var10 = 0.0D;
					long var12 = 0L;

					int var14;
					for (var14 = 0; var14 < var6; ++var14) {
						var2[var14] = var0[var14];
						var3[var14] = var1[var14];
					}

					while (var5 != var6 - 1) {
						int var15 = var5 + 1;

						for (var14 = var5 + 2; var14 < var6; ++var14) {
							if (var2[var14] < var2[var15]) {
								var15 = var14;
							}
						}

						++var5;
						var10 = var2[var15];
						var2[var15] = var2[var5];
						var2[var5] = var10;
						var12 = var3[var15];
						var3[var15] = var3[var5];
						var3[var5] = var12;
					}

				}
			}
		}
	}

	public static void selectionSort(long[] var0, double[] var1, long[] var2, double[] var3) {
		int var5 = -1;
		int var6 = var0.length;
		int var7 = var1.length;
		if (var6 != var7) {
			throw new IllegalArgumentException("First argument array, aa, (length = " + var6
					+ ") and the second argument array, bb, (length = " + var7 + ") should be the same length");
		} else {
			int var8 = var2.length;
			if (var8 < var6) {
				throw new IllegalArgumentException("The third argument array, cc, (length = " + var8
						+ ") should be at least as long as the first argument array, aa, (length = " + var6 + ")");
			} else {
				int var9 = var3.length;
				if (var9 < var7) {
					throw new IllegalArgumentException("The fourth argument array, dd, (length = " + var9
							+ ") should be at least as long as the second argument array, bb, (length = " + var7 + ")");
				} else {
					long var10 = 0L;
					double var12 = 0.0D;

					int var14;
					for (var14 = 0; var14 < var6; ++var14) {
						var2[var14] = var0[var14];
						var3[var14] = var1[var14];
					}

					while (var5 != var6 - 1) {
						int var15 = var5 + 1;

						for (var14 = var5 + 2; var14 < var6; ++var14) {
							if (var2[var14] < var2[var15]) {
								var15 = var14;
							}
						}

						++var5;
						var10 = var2[var15];
						var2[var15] = var2[var5];
						var2[var5] = var10;
						var12 = var3[var15];
						var3[var15] = var3[var5];
						var3[var5] = var12;
					}

				}
			}
		}
	}

	public static void selectionSort(double[] var0, int[] var1, double[] var2, int[] var3) {
		int var5 = -1;
		int var6 = var0.length;
		int var7 = var1.length;
		if (var6 != var7) {
			throw new IllegalArgumentException("First argument array, aa, (length = " + var6
					+ ") and the second argument array, bb, (length = " + var7 + ") should be the same length");
		} else {
			int var8 = var2.length;
			if (var8 < var6) {
				throw new IllegalArgumentException("The third argument array, cc, (length = " + var8
						+ ") should be at least as long as the first argument array, aa, (length = " + var6 + ")");
			} else {
				int var9 = var3.length;
				if (var9 < var7) {
					throw new IllegalArgumentException("The fourth argument array, dd, (length = " + var9
							+ ") should be at least as long as the second argument array, bb, (length = " + var7 + ")");
				} else {
					double var10 = 0.0D;

					int var13;
					for (var13 = 0; var13 < var6; ++var13) {
						var2[var13] = var0[var13];
						var3[var13] = var1[var13];
					}

					while (var5 != var6 - 1) {
						int var14 = var5 + 1;

						for (var13 = var5 + 2; var13 < var6; ++var13) {
							if (var2[var13] < var2[var14]) {
								var14 = var13;
							}
						}

						++var5;
						var10 = var2[var14];
						var2[var14] = var2[var5];
						var2[var5] = var10;
						int var15 = var3[var14];
						var3[var14] = var3[var5];
						var3[var5] = var15;
					}

				}
			}
		}
	}

	public static void selectionSort(int[] var0, double[] var1, int[] var2, double[] var3) {
		int var5 = -1;
		int var6 = var0.length;
		int var7 = var1.length;
		if (var6 != var7) {
			throw new IllegalArgumentException("First argument array, aa, (length = " + var6
					+ ") and the second argument array, bb, (length = " + var7 + ") should be the same length");
		} else {
			int var8 = var2.length;
			if (var8 < var6) {
				throw new IllegalArgumentException("The third argument array, cc, (length = " + var8
						+ ") should be at least as long as the first argument array, aa, (length = " + var6 + ")");
			} else {
				int var9 = var3.length;
				if (var9 < var7) {
					throw new IllegalArgumentException("The fourth argument array, dd, (length = " + var9
							+ ") should be at least as long as the second argument array, bb, (length = " + var7 + ")");
				} else {
					double var11 = 0.0D;

					int var13;
					for (var13 = 0; var13 < var6; ++var13) {
						var2[var13] = var0[var13];
						var3[var13] = var1[var13];
					}

					while (var5 != var6 - 1) {
						int var14 = var5 + 1;

						for (var13 = var5 + 2; var13 < var6; ++var13) {
							if (var2[var13] < var2[var14]) {
								var14 = var13;
							}
						}

						++var5;
						int var15 = var2[var14];
						var2[var14] = var2[var5];
						var2[var5] = var15;
						var11 = var3[var14];
						var3[var14] = var3[var5];
						var3[var5] = var11;
					}

				}
			}
		}
	}

	public static void selectionSort(long[] var0, int[] var1, long[] var2, int[] var3) {
		int var5 = -1;
		int var6 = var0.length;
		int var7 = var1.length;
		if (var6 != var7) {
			throw new IllegalArgumentException("First argument array, aa, (length = " + var6
					+ ") and the second argument array, bb, (length = " + var7 + ") should be the same length");
		} else {
			int var8 = var2.length;
			if (var8 < var6) {
				throw new IllegalArgumentException("The third argument array, cc, (length = " + var8
						+ ") should be at least as long as the first argument array, aa, (length = " + var6 + ")");
			} else {
				int var9 = var3.length;
				if (var9 < var7) {
					throw new IllegalArgumentException("The fourth argument array, dd, (length = " + var9
							+ ") should be at least as long as the second argument array, bb, (length = " + var7 + ")");
				} else {
					long var10 = 0L;

					int var13;
					for (var13 = 0; var13 < var6; ++var13) {
						var2[var13] = var0[var13];
						var3[var13] = var1[var13];
					}

					while (var5 != var6 - 1) {
						int var14 = var5 + 1;

						for (var13 = var5 + 2; var13 < var6; ++var13) {
							if (var2[var13] < var2[var14]) {
								var14 = var13;
							}
						}

						++var5;
						var10 = var2[var14];
						var2[var14] = var2[var5];
						var2[var5] = var10;
						int var15 = var3[var14];
						var3[var14] = var3[var5];
						var3[var5] = var15;
					}

				}
			}
		}
	}

	public static void selectionSort(int[] var0, long[] var1, int[] var2, long[] var3) {
		int var5 = -1;
		int var6 = var0.length;
		int var7 = var1.length;
		if (var6 != var7) {
			throw new IllegalArgumentException("First argument array, aa, (length = " + var6
					+ ") and the second argument array, bb, (length = " + var7 + ") should be the same length");
		} else {
			int var8 = var2.length;
			if (var8 < var6) {
				throw new IllegalArgumentException("The third argument array, cc, (length = " + var8
						+ ") should be at least as long as the first argument array, aa, (length = " + var6 + ")");
			} else {
				int var9 = var3.length;
				if (var9 < var7) {
					throw new IllegalArgumentException("The fourth argument array, dd, (length = " + var9
							+ ") should be at least as long as the second argument array, bb, (length = " + var7 + ")");
				} else {
					long var11 = 0L;

					int var13;
					for (var13 = 0; var13 < var6; ++var13) {
						var2[var13] = var0[var13];
						var3[var13] = var1[var13];
					}

					while (var5 != var6 - 1) {
						int var14 = var5 + 1;

						for (var13 = var5 + 2; var13 < var6; ++var13) {
							if (var2[var13] < var2[var14]) {
								var14 = var13;
							}
						}

						++var5;
						int var15 = var2[var14];
						var2[var14] = var2[var5];
						var2[var5] = var15;
						var11 = var3[var14];
						var3[var14] = var3[var5];
						var3[var5] = var11;
					}

				}
			}
		}
	}

	public static void selectSort(double[] var0, double[] var1, int[] var2) {
		int var4 = -1;
		int var5 = var0.length;
		int var6 = var1.length;
		if (var6 < var5) {
			throw new IllegalArgumentException("The second argument array, bb, (length = " + var6
					+ ") should be at least as long as the first argument array, aa, (length = " + var5 + ")");
		} else {
			int var7 = var2.length;
			if (var6 < var5) {
				throw new IllegalArgumentException("The third argument array, indices, (length = " + var7
						+ ") should be at least as long as the first argument array, aa, (length = " + var5 + ")");
			} else {
				double var8 = 0.0D;
				
				int var11;
				for (var11 = 0; var11 < var5; var2[var11] = var11++) {
					var1[var11] = var0[var11];
				}

				while (var4 != var5 - 1) {
					int var12 = var4 + 1;

					for (var11 = var4 + 2; var11 < var5; ++var11) {
						if (var1[var11] < var1[var12]) {
							var12 = var11;
						}
					}

					++var4;
					var8 = var1[var12];
					var1[var12] = var1[var4];
					var1[var4] = var8;
					int var13 = var2[var12];
					var2[var12] = var2[var4];
					var2[var4] = var13;
				}

			}
		}
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
		var0 = footToMetre(var0);
		var2 = poundToKg(var2);
		return var2 / (var0 * var0);
	}

	public static double calcWeightFromBMImetric(double var0, double var2) {
		return var0 * var2 * var2;
	}

	public static double calcWeightFromBMIimperial(double var0, double var2) {
		var2 = footToMetre(var2);
		double var4 = var0 * var2 * var2;
		var4 = kgToPound(var4);
		return var4;
	}

	static {
		integers.put(Integer.class, BigDecimal.valueOf(2147483647L));
		integers.put(Long.class, BigDecimal.valueOf(Long.MAX_VALUE));
		integers.put(Byte.class, BigDecimal.valueOf(127L));
		integers.put(Short.class, BigDecimal.valueOf(32767L));
		integers.put(BigInteger.class, BigDecimal.valueOf(-1L));
	}

}
