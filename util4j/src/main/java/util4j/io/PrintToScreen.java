package util4j.io;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import util4j.complex.Complex;
import util4j.math.Fmath;

public class PrintToScreen {
	
	public static void print(ArrayList<?> var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.size(); ++var1) {
				System.out.print(var0.get(var1) + "   ");
			}

			System.out.println();
		}

	}

	public static void println(ArrayList<?> var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.size(); ++var1) {
				System.out.println(var0.get(var1) + "   ");
			}
		}

	}

	public static void print(double[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void print(double[] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var2 = 0; var2 < var0.length; ++var2) {
				System.out.print(Fmath.truncate(var0[var2], var1) + "   ");
			}

			System.out.println();
		}

	}

	public static void println(double[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void println(double[] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var2 = 0; var2 < var0.length; ++var2) {
				System.out.println(Fmath.truncate(var0[var2], var1) + "   ");
			}
		}

	}

	public static void print(float[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void print(float[] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var2 = 0; var2 < var0.length; ++var2) {
				System.out.print(Fmath.truncate(var0[var2], var1) + "   ");
			}

			System.out.println();
		}

	}

	public static void println(float[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void println(float[] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var2 = 0; var2 < var0.length; ++var2) {
				System.out.println(Fmath.truncate(var0[var2], var1) + "   ");
			}
		}

	}

	public static void print(int[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(int[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void print(long[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(long[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void print(short[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(short[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void print(char[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(char[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void print(byte[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(byte[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void print(Double[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void print(Double[] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			ArrayMaths var2 = new ArrayMaths(var0);
			var2 = var2.truncate(var1);
			Double[] var3 = var2.array_as_Double();

			for (int var4 = 0; var4 < var0.length; ++var4) {
				System.out.print(var3[var4] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(Double[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void println(Double[] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			ArrayMaths var2 = new ArrayMaths(var0);
			var2 = var2.truncate(var1);
			Double[] var3 = var2.array_as_Double();

			for (int var4 = 0; var4 < var0.length; ++var4) {
				System.out.println(var3[var4] + "   ");
			}
		}

	}

	public static void print(Float[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void print(Float[] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			ArrayMaths var2 = new ArrayMaths(var0);
			var2 = var2.truncate(var1);
			Float[] var3 = var2.array_as_Float();

			for (int var4 = 0; var4 < var0.length; ++var4) {
				System.out.print(var3[var4] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(Float[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void println(Float[] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			ArrayMaths var2 = new ArrayMaths(var0);
			var2 = var2.truncate(var1);
			Float[] var3 = var2.array_as_Float();

			for (int var4 = 0; var4 < var0.length; ++var4) {
				System.out.println(var3[var4] + "   ");
			}
		}

	}

	public static void print(Integer[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(Integer[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void print(Long[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(Long[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void print(Short[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(Short[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void print(Character[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(Character[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void print(Byte[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(Byte[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void print(String[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(String[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void print(Complex[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void print(Complex[] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var2 = 0; var2 < var0.length; ++var2) {
				System.out.print(Complex.truncate(var0[var2], var1) + "   ");
			}

			System.out.println();
		}

	}

	public static void println(Complex[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void println(Complex[] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var2 = 0; var2 < var0.length; ++var2) {
				System.out.println(Complex.truncate(var0[var2], var1) + "   ");
			}
		}

	}

	public static void print(Phasor[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void print(Phasor[] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var2 = 0; var2 < var0.length; ++var2) {
				System.out.print(Phasor.truncate(var0[var2], var1) + "   ");
			}

			System.out.println();
		}

	}

	public static void println(Phasor[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void println(Phasor[] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var2 = 0; var2 < var0.length; ++var2) {
				System.out.println(Phasor.truncate(var0[var2], var1) + "   ");
			}
		}

	}

	public static void print(BigDecimal[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(BigDecimal[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void print(BigInteger[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(BigInteger[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void print(boolean[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.print(var0[var1] + "   ");
			}

			System.out.println();
		}

	}

	public static void println(boolean[] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				System.out.println(var0[var1] + "   ");
			}
		}

	}

	public static void print(double[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(double[][] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var2 = 0; var2 < var0.length; ++var2) {
				print(var0[var2], var1);
			}
		}

	}

	public static void print(float[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(float[][] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var2 = 0; var2 < var0.length; ++var2) {
				print(var0[var2], var1);
			}
		}

	}

	public static void print(int[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(long[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(char[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(byte[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(short[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(Double[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(Double[][] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var2 = 0; var2 < var0.length; ++var2) {
				print(var0[var2], var1);
			}
		}

	}

	public static void print(Float[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(Float[][] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var2 = 0; var2 < var0.length; ++var2) {
				print(var0[var2], var1);
			}
		}

	}

	public static void print(Integer[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(Long[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(Character[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(Byte[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(Short[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(String[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(Complex[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				Complex.print(var0[var1]);
			}
		}

	}

	public static void print(Complex[][] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var2 = 0; var2 < var0.length; ++var2) {
				print(var0[var2], var1);
			}
		}

	}

	public static void print(Phasor[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				Phasor.print(var0[var1]);
			}
		}

	}

	public static void print(Phasor[][] var0, int var1) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			Phasor[][] var2 = Conv.copy(var0);

			int var3;
			for (var3 = 0; var3 < var2.length; ++var3) {
				for (int var4 = 0; var4 < var2[var3].length; ++var4) {
					var2[var3][var4] = Phasor.truncate(var2[var3][var4], var1);
				}
			}

			for (var3 = 0; var3 < var0.length; ++var3) {
				Phasor.print(var2[var3]);
			}
		}

	}

	public static void print(BigDecimal[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(BigInteger[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

	public static void print(boolean[][] var0) {
		if (var0 == null) {
			System.out.println("null array entered in PrintToScreen");
		} else {
			for (int var1 = 0; var1 < var0.length; ++var1) {
				print(var0[var1]);
			}
		}

	}

}
