package util4j.complex;

import java.io.IOException;

import util4j.math.Fmath;

public class Phasor {
	
	private double magnitude = 0.0D;
	private double phaseInDeg = 0.0D;
	private double phaseInRad = 0.0D;
	private Complex rectangular = Complex.ofRect(0.0D, 0.0D);
	private static double frequency = Double.NaN;
	private static double omega = Double.NaN;

	public Phasor() {
	}

	public Phasor(double var1, double var3) {
		this.magnitude = var1;
		this.phaseInDeg = var3;
		this.phaseInRad = Math.toRadians(var3);
		this.rectangular = Complex.ofPolar(this.magnitude, this.phaseInRad);
	}

	public Phasor(double var1) {
		this.magnitude = var1;
		this.rectangular = Complex.ofPolar(this.magnitude, this.phaseInRad);
	}

	public static void setFrequency(double var0) {
		if (Fmath.isNaN(frequency)) {
			frequency = var0;
			omega = 6.283185307179586D * var0;
		} else {
			throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency
					+ ", that differs from the one you are now attempting to enter, " + var0);
		}
	}

	public static void setRadialFrequency(double var0) {
		if (Fmath.isNaN(omega)) {
			omega = var0;
			frequency = omega / 6.283185307179586D;
		} else {
			throw new IllegalArgumentException("You have already entered a value for the radial frequency, omega, "
					+ omega + ", that differs from the one you are now attempting to enter, " + var0);
		}
	}

	public void setMagnitude(double var1) {
		this.magnitude = var1;
		this.rectangular = Complex.ofPolar(this.magnitude, this.phaseInRad);
	}

	public void setPhaseInDegrees(double var1) {
		this.phaseInDeg = var1;
		this.phaseInRad = Math.toRadians(var1);
		this.rectangular = Complex.ofPolar(this.magnitude, this.phaseInRad);
	}

	public void reset(double var1, double var3) {
		this.magnitude = var1;
		this.phaseInDeg = var3;
		this.phaseInRad = Math.toRadians(var3);
		this.rectangular= Complex.ofPolar(this.magnitude, this.phaseInRad);
	}

	public static double getFrequency() {
		return frequency;
	}

	public static double setRadialFrequency() {
		return omega;
	}

	public double getMagnitude() {
		return this.magnitude;
	}

	public double getPhaseInDegrees() {
		return this.phaseInDeg;
	}

	public double getPhaseInRadians() {
		return this.phaseInRad;
	}

	public double getReal() {
		return this.magnitude * Math.cos(this.phaseInRad);
	}

	public double getImag() {
		return this.magnitude * Math.sin(this.phaseInRad);
	}

	public static Phasor toPhasor(Complex var0) {
		Phasor var1 = new Phasor();
		var1.magnitude = var0.abs();
		var1.phaseInRad = ComplexMath.arg(var0) ;
		var1.phaseInDeg = ComplexMath.argDeg(var0) ;
		var1.rectangular = var0;
		return var1;
	}

	public Complex toRectangular() {
		return Complex.ofPolar(this.magnitude, this.phaseInRad);
	}

	public static Complex toRectangular(Phasor var0) {
		return Complex.ofPolar(var0.magnitude, var0.phaseInRad);
	}

	public Complex toComplex() {
		return Complex.ofPolar(this.magnitude, this.phaseInRad);
	}

	public static Complex toComplex(Phasor var0) {
		return Complex.ofPolar(var0.magnitude, var0.phaseInRad);
	}

	public String toString() {
		return this.magnitude + "<" + this.phaseInDeg + "deg";
	}

	public static String toString(Phasor var0) {
		return var0.magnitude + "<" + var0.phaseInDeg + "deg";
	}

	public static Phasor parsePhasor(String var0) {
		Phasor var1 = new Phasor();
		var0 = var0.trim();
		int var2 = var0.indexOf(60);
		if (var2 == -1) {
			var2 = var0.indexOf(76);
			if (var2 == -1) {
				throw new IllegalArgumentException("no angle symbol, <, in the string, ss");
			}
		}

		int var3 = var0.indexOf(100);
		if (var3 == -1) {
			var3 = var0.indexOf(68);
		}

		int var4 = var0.indexOf(114);
		if (var4 == -1) {
			var3 = var0.indexOf(82);
		}

		String var5 = var0.substring(0, var2);
		var1.magnitude = Double.parseDouble(var5);
		String var6 = null;
		if (var3 != -1) {
			var0.substring(var2 + 1, var3);
			var1.phaseInDeg = Double.parseDouble(var5);
			var1.phaseInRad = Math.toRadians(var1.phaseInDeg);
		}

		if (var3 == -1 && var4 == -1) {
			var6 = var0.substring(var2 + 1);
			var1.phaseInDeg = Double.parseDouble(var6);
			var1.phaseInRad = Math.toRadians(var1.phaseInDeg);
		}

		if (var4 != -1) {
			var6 = var0.substring(var2 + 1, var4);
			var1.phaseInRad = Double.parseDouble(var6);
			var1.phaseInDeg = Math.toDegrees(var1.phaseInRad);
		}

		var1.rectangular = Complex.ofPolar(var1.magnitude, var1.phaseInRad);
		return var1;
	}

	public static Phasor valueOf(String var0) {
		return parsePhasor(var0);
	}

	public static final synchronized Phasor readPhasor(String var0) {
		String var2 = "";
		boolean var3 = false;
		System.out.print(var0 + " ");
		System.out.flush();

		while (!var3) {
			try {
				int var6 = System.in.read();
				if (var6 >= 0 && (char) var6 != '\n') {
					var2 = var2 + (char) var6;
				} else {
					var3 = true;
				}
			} catch (IOException var5) {
				var3 = true;
			}
		}

		return parsePhasor(var2);
	}

	public static final synchronized Phasor readPhasor(String var0, String var1) {
		String var3 = "";
		boolean var4 = false;
		System.out.print(var0 + " [default value = " + var1 + "]  ");
		System.out.flush();
		int var5 = 0;

		while (!var4) {
			try {
				int var8 = System.in.read();
				if (var8 >= 0 && (char) var8 != '\n' && (char) var8 != '\r') {
					var3 = var3 + (char) var8;
					++var5;
				} else {
					if (var5 == 0) {
						var3 = var1;
						if ((char) var8 == '\r') {
							var8 = System.in.read();
						}
					}

					var4 = true;
				}
			} catch (IOException var7) {
				var4 = true;
			}
		}

		return parsePhasor(var3);
	}

	public static final synchronized Phasor readPhasor(String var0, Phasor var1) {
		String var3 = "";
		boolean var4 = false;
		System.out.print(var0 + " [default value = " + var1 + "]  ");
		System.out.flush();
		int var5 = 0;

		while (!var4) {
			try {
				int var8 = System.in.read();
				if (var8 >= 0 && (char) var8 != '\n' && (char) var8 != '\r') {
					var3 = var3 + (char) var8;
					++var5;
				} else {
					if (var5 == 0) {
						if ((char) var8 == '\r') {
							var8 = System.in.read();
						}

						return var1;
					}

					var4 = true;
				}
			} catch (IOException var7) {
				var4 = true;
			}
		}

		return parsePhasor(var3);
	}

	public static final synchronized Phasor readPhasor() {
		String var1 = "";
		boolean var2 = false;
		System.out.print(" ");
		System.out.flush();

		while (!var2) {
			try {
				int var5 = System.in.read();
				if (var5 >= 0 && (char) var5 != '\n') {
					var1 = var1 + (char) var5;
				} else {
					var2 = true;
				}
			} catch (IOException var4) {
				var2 = true;
			}
		}

		return parsePhasor(var1);
	}

	public void println(String var1) {
		System.out.println(var1 + " " + this.toString());
	}

	public void println() {
		System.out.println(" " + this.toString());
	}

	public void print(String var1) {
		System.out.print(var1 + " " + this.toString());
	}

	public void print() {
		System.out.print(" " + this.toString());
	}

	public static void println(String var0, Phasor[] var1) {
		System.out.println(var0);

		for (int var2 = 0; var2 < var1.length; ++var2) {
			System.out.println(var1[var2].toString() + "  ");
		}

	}

	public static void println(Phasor[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.println(var0[var1].toString() + "  ");
		}

	}

	public static void print(String var0, Phasor[] var1) {
		System.out.print(var0 + " ");

		for (int var2 = 0; var2 < var1.length; ++var2) {
			System.out.print(var1[var2].toString() + "   ");
		}

		System.out.println();
	}

	public static void print(Phasor[] var0) {
		for (int var1 = 0; var1 < var0.length; ++var1) {
			System.out.print(var0[var1].toString() + "  ");
		}

		System.out.println();
	}

	public Phasor truncate(int var1) {
		if (var1 < 0) {
			return this;
		} else {
			double var2 = this.magnitude;
			double var4 = this.phaseInDeg;
			double var6 = this.phaseInRad;
			Complex var8 = this.rectangular;
			Phasor var9 = new Phasor();
			var9.magnitude = Fmath.truncate(var2, var1);
			var9.phaseInDeg = Fmath.truncate(var4, var1);
			var9.phaseInRad = Fmath.truncate(var6, var1);
			var9.rectangular = Complex.ofRect(Fmath.truncate(var8.re(), var1), Fmath.truncate(var8.im(), var1)) ;
			return var9;
		}
	}

	public static Phasor truncate(Phasor var0, int var1) {
		if (var1 < 0) {
			return var0;
		} else {
			double var2 = var0.magnitude;
			double var4 = var0.phaseInDeg;
			double var6 = var0.phaseInRad;
			Complex var8 = var0.rectangular;
			Phasor var9 = new Phasor();
			var9.magnitude = Fmath.truncate(var2, var1);
			var9.phaseInDeg = Fmath.truncate(var4, var1);
			var9.phaseInRad = Fmath.truncate(var6, var1);
			var9.rectangular = Complex.ofRect(Fmath.truncate(var8.re(), var1), Fmath.truncate(var8.im(), var1)) ;
			return var9;
		}
	}

	public int hashCode() {
		long var1 = Double.doubleToLongBits(this.magnitude);
		long var3 = Double.doubleToLongBits(this.phaseInDeg);
		int var5 = (int) (var1 ^ var1 >>> 32);
		int var6 = (int) (var3 ^ var3 >>> 32);
		return 6 * (var5 / 10) + 4 * (var6 / 10);
	}

	public static Phasor[] oneDarray(int var0) {
		Phasor[] var1 = new Phasor[var0];
		Phasor var2 = new Phasor();
		var2.reset(1.0D, 0.0D);

		for (int var3 = 0; var3 < var0; ++var3) {
			var1[var3] = var2;
		}

		return var1;
	}

	public static Phasor[] oneDarray(int var0, double var1, double var3) {
		Phasor[] var5 = new Phasor[var0];
		Phasor var6 = new Phasor();
		var6.reset(var1, var3);

		for (int var7 = 0; var7 < var0; ++var7) {
			var5[var7] = var6;
		}

		return var5;
	}

	public static Phasor[] oneDarray(int var0, Phasor var1) {
		Phasor[] var2 = new Phasor[var0];

		for (int var3 = 0; var3 < var0; ++var3) {
			var2[var3] = var1.copy();
		}

		return var2;
	}

	public static Phasor[][] twoDarray(int var0, int var1) {
		Phasor[][] var2 = new Phasor[var0][var1];
		Phasor var3 = new Phasor();
		var3.reset(1.0D, 0.0D);

		for (int var4 = 0; var4 < var0; ++var4) {
			for (int var5 = 0; var5 < var1; ++var5) {
				var2[var4][var5] = var3;
			}
		}

		return var2;
	}

	public static Phasor[][] twoDarray(int var0, int var1, double var2, double var4) {
		Phasor[][] var6 = new Phasor[var0][var1];
		Phasor var7 = new Phasor();
		var7.reset(var2, var4);

		for (int var8 = 0; var8 < var0; ++var8) {
			for (int var9 = 0; var9 < var1; ++var9) {
				var6[var8][var9] = var7;
			}
		}

		return var6;
	}

	public static Phasor[][] twoDarray(int var0, int var1, Phasor var2) {
		Phasor[][] var3 = new Phasor[var0][var1];

		for (int var4 = 0; var4 < var0; ++var4) {
			for (int var5 = 0; var5 < var1; ++var5) {
				var3[var4][var5] = var2.copy();
			}
		}

		return var3;
	}

	public static Phasor[][][] threeDarray(int var0, int var1, int var2) {
		Phasor[][][] var3 = new Phasor[var0][var1][var2];
		Phasor var4 = new Phasor();
		var4.reset(1.0D, 0.0D);

		for (int var5 = 0; var5 < var0; ++var5) {
			for (int var6 = 0; var6 < var1; ++var6) {
				for (int var7 = 0; var7 < var2; ++var7) {
					var3[var5][var6][var7] = var4;
				}
			}
		}

		return var3;
	}

	public static Phasor[][][] threeDarray(int var0, int var1, int var2, double var3, double var5) {
		Phasor[][][] var7 = new Phasor[var0][var1][var2];
		Phasor var8 = new Phasor();
		var8.reset(var3, var5);

		for (int var9 = 0; var9 < var0; ++var9) {
			for (int var10 = 0; var10 < var1; ++var10) {
				for (int var11 = 0; var11 < var2; ++var11) {
					var7[var9][var10][var11] = var8;
				}
			}
		}

		return var7;
	}

	public static Phasor[][][] threeDarray(int var0, int var1, int var2, Phasor var3) {
		Phasor[][][] var4 = new Phasor[var0][var1][var2];

		for (int var5 = 0; var5 < var0; ++var5) {
			for (int var6 = 0; var6 < var1; ++var6) {
				for (int var7 = 0; var7 < var2; ++var7) {
					var4[var5][var6][var7] = var3.copy();
				}
			}
		}

		return var4;
	}

	public Phasor copy() {
		Phasor var1 = new Phasor();
		var1.magnitude = this.magnitude;
		var1.phaseInDeg = this.phaseInDeg;
		var1.phaseInRad = this.phaseInRad;
		var1.rectangular = Complex.of(this.rectangular) ;
		return var1;
	}

	public static Phasor copy(Phasor var0) {
		if (var0 == null) {
			return null;
		} else {
			Phasor var1 = new Phasor();
			var1.magnitude = var0.magnitude;
			var1.phaseInDeg = var0.phaseInDeg;
			var1.phaseInRad = var0.phaseInRad;
			return var1;
		}
	}

	public static Phasor[] copy(Phasor[] var0) {
		if (var0 == null) {
			return null;
		} else {
			int var1 = var0.length;
			Phasor[] var2 = oneDarray(var1);

			for (int var3 = 0; var3 < var1; ++var3) {
				var2[var3] = var0[var3].copy();
			}

			return var2;
		}
	}

	public static Phasor[][] copy(Phasor[][] var0) {
		if (var0 == null) {
			return (Phasor[][]) null;
		} else {
			int var1 = var0.length;
			int var2 = var0[0].length;
			Phasor[][] var3 = twoDarray(var1, var2);

			for (int var4 = 0; var4 < var1; ++var4) {
				for (int var5 = 0; var5 < var2; ++var5) {
					var3[var4][var5] = var0[var4][var5].copy();
				}
			}

			return var3;
		}
	}

	public static Phasor[][][] copy(Phasor[][][] var0) {
		if (var0 == null) {
			return (Phasor[][][]) null;
		} else {
			int var1 = var0.length;
			int var2 = var0[0].length;
			int var3 = var0[0][0].length;
			Phasor[][][] var4 = threeDarray(var1, var2, var3);

			for (int var5 = 0; var5 < var1; ++var5) {
				for (int var6 = 0; var6 < var2; ++var6) {
					for (int var7 = 0; var7 < var3; ++var7) {
						var4[var5][var6][var7] = var0[var5][var6][var7].copy();
					}
				}
			}

			return var4;
		}
	}

	public Object clone() {
		Phasor var1 = null;
		if (this != null) {
			Phasor var2 = new Phasor();
			var2.magnitude = this.magnitude;
			var2.phaseInDeg = this.phaseInDeg;
			var2.phaseInRad = this.phaseInRad;
			var1 = var2;
		}

		return var1;
	}

	public Phasor plus(Phasor var1) {
		return null ;
	}

	public Phasor plus(Complex var1) {
		new Phasor();
		Complex var3 = this.toRectangular();
		Complex var4 = var1.plus(var3);
		return toPhasor(var4);
	}

	public void plusEquals(Phasor var1) {
		Complex var2 = this.toRectangular();
		Complex var3 = var1.toRectangular();
		Complex var4 = var2.plus(var3);
		Phasor var5 = toPhasor(var4);
		this.magnitude = var5.magnitude;
		this.phaseInDeg = var5.phaseInDeg;
		this.phaseInRad = var5.phaseInRad;
	}

	public void plusEquals(Complex var1) {
		Complex var2 = this.toRectangular();
		Complex var3 = var1.plus(var2);
		Phasor var4 = toPhasor(var3);
		this.magnitude += var4.magnitude;
		this.phaseInDeg += var4.phaseInDeg;
		this.phaseInRad += var4.phaseInRad;
	}

	public Phasor minus(Phasor var1) {
		Complex var2 = this.toRectangular();
		Complex var3 = var1.toRectangular();
		Complex var4 = var2.minus(var3);
		return toPhasor(var4);
	}

	public Phasor minus(Complex var1) {
		new Phasor();
		Complex var3 = this.toRectangular();
		Complex var4 = var1.minus(var3);
		return toPhasor(var4);
	}

	public void minusEquals(Phasor var1) {
		Complex var2 = this.toRectangular();
		Complex var3 = var1.toRectangular();
		Complex var4 = var2.plus(var3);
		Phasor var5 = toPhasor(var4);
		this.magnitude = var5.magnitude;
		this.phaseInDeg = var5.phaseInDeg;
		this.phaseInRad = var5.phaseInRad;
	}

	public void minusEquals(Complex var1) {
		Complex var2 = this.toRectangular();
		Complex var3 = var1.plus(var2);
		Phasor var4 = toPhasor(var3);
		this.magnitude = var4.magnitude;
		this.phaseInDeg = var4.phaseInDeg;
		this.phaseInRad = var4.phaseInRad;
	}

	public Phasor times(Phasor var1) {
		Phasor var2 = new Phasor();
		double var3 = this.magnitude * var1.magnitude;
		double var5 = this.phaseInDeg + var1.phaseInDeg;
		var2.reset(var3, var5);
		return var2;
	}

	public Phasor times(Complex var1) {
		Phasor var2 = toPhasor(var1);
		Phasor var3 = new Phasor();
		double var4 = this.magnitude * var2.magnitude;
		double var6 = this.phaseInDeg + var2.phaseInDeg;
		var3.reset(var4, var6);
		return var3;
	}

	public Phasor times(double var1) {
		Phasor var3 = new Phasor();
		double var4 = this.magnitude * var1;
		double var6 = this.phaseInDeg;
		var3.reset(var4, var6);
		return var3;
	}

	public Phasor times(int var1) {
		Phasor var2 = new Phasor();
		double var3 = this.magnitude * (double) var1;
		double var5 = this.phaseInDeg;
		var2.reset(var3, var5);
		return var2;
	}

	public Phasor timesExpOmegaTime(double var1, double var3) {
		if (Fmath.isNaN(omega)) {
			omega = var1;
			frequency = omega / 6.283185307179586D;
			Phasor var5 = new Phasor();
			var5.reset(this.magnitude, this.phaseInDeg + Math.toDegrees(var1 * var3));
			return var5;
		} else {
			throw new IllegalArgumentException("You have already entered a value for the radial frequency, omega, "
					+ omega + ", that differs from the one you are now attempting to enter, " + var1);
		}
	}

	public Phasor timesExpTwoPiFreqTime(double var1, double var3) {
		if (Fmath.isNaN(frequency)) {
			frequency = var1;
			omega = frequency * 2.0D * 3.141592653589793D;
			Phasor var5 = new Phasor();
			var5.reset(this.magnitude, this.phaseInDeg + Math.toDegrees(6.283185307179586D * var1 * var3));
			return var5;
		} else {
			throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency
					+ ", that differs from the one you are now attempting to enter, " + var1);
		}
	}

	public void timesEquals(Phasor var1) {
		this.magnitude *= var1.magnitude;
		this.phaseInDeg += var1.phaseInDeg;
		this.phaseInRad += var1.phaseInRad;
	}

	public void timesEquals(Complex var1) {
		Phasor var2 = toPhasor(var1);
		this.magnitude *= var2.magnitude;
		this.phaseInDeg += var2.phaseInDeg;
		this.phaseInRad += var2.phaseInRad;
	}

	public void timesEquals(double var1) {
		this.magnitude *= var1;
	}

	public void timesEquals(int var1) {
		this.magnitude *= (double) var1;
	}

	public void timesEqualsOmegaTime(double var1, double var3) {
		if (Fmath.isNaN(omega)) {
			omega = var1;
			frequency = omega / 6.283185307179586D;
			this.phaseInRad += var1 * var3;
			this.phaseInDeg = Math.toDegrees(this.phaseInRad);
		} else {
			throw new IllegalArgumentException("You have already entered a value for radial frequency, omega, " + omega
					+ ", that differs from the one you are now attempting to enter, " + var1);
		}
	}

	public void timesEqualsTwoPiFreqTime(double var1, double var3) {
		if (Fmath.isNaN(frequency)) {
			frequency = var1;
			omega = frequency * 2.0D * 3.141592653589793D;
			this.phaseInRad += 6.283185307179586D * var1 * var3;
			this.phaseInDeg = Math.toDegrees(this.phaseInRad);
		} else {
			throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency
					+ ", that differs from the one you are now attempting to enter, " + var1);
		}
	}

	public Phasor over(Phasor var1) {
		Phasor var2 = new Phasor();
		double var3 = this.magnitude / var1.magnitude;
		double var5 = this.phaseInDeg - var1.phaseInDeg;
		var2.reset(var3, var5);
		return var2;
	}

	public Phasor over(Complex var1) {
		Phasor var2 = toPhasor(var1);
		Phasor var3 = new Phasor();
		double var4 = this.magnitude / var2.magnitude;
		double var6 = this.phaseInDeg - var2.phaseInDeg;
		var3.reset(var4, var6);
		return var3;
	}

	public Phasor over(double var1) {
		Phasor var3 = new Phasor();
		double var4 = this.magnitude / var1;
		double var6 = this.phaseInDeg;
		var3.reset(var4, var6);
		return var3;
	}

	public Phasor over(int var1) {
		Phasor var2 = new Phasor();
		double var3 = this.magnitude / (double) var1;
		double var5 = this.phaseInDeg;
		var2.reset(var3, var5);
		return var2;
	}

	public void overEquals(Phasor var1) {
		this.magnitude /= var1.magnitude;
		this.phaseInDeg -= var1.phaseInDeg;
		this.phaseInRad -= var1.phaseInRad;
	}

	public void overEquals(Complex var1) {
		Phasor var2 = toPhasor(var1);
		this.magnitude /= var2.magnitude;
		this.phaseInDeg -= var2.phaseInDeg;
		this.phaseInRad -= var2.phaseInRad;
	}

	public void overEquals(double var1) {
		this.magnitude /= var1;
	}

	public void overEquals(int var1) {
		this.magnitude /= (double) var1;
	}

	public double abs() {
		return Math.abs(this.magnitude);
	}

	public double argInRadians() {
		return this.phaseInRad;
	}

	public double argInDegrees() {
		return this.phaseInDeg;
	}

	public Phasor negate() {
		Phasor var1 = new Phasor();
		var1.reset(-this.magnitude, this.phaseInDeg);
		return var1;
	}

	public Phasor conjugate() {
		Phasor var1 = new Phasor();
		var1.reset(this.magnitude, -this.phaseInDeg);
		return var1;
	}

	public Phasor inverse() {
		Phasor var1 = new Phasor();
		var1.reset(1.0D / this.magnitude, -this.phaseInDeg);
		return var1;
	}

	public static Phasor sqrt(Phasor var0) {
		Phasor var1 = new Phasor();
		var1.reset(Math.sqrt(var0.magnitude), var0.phaseInDeg / 2.0D);
		return var1;
	}

	public static Phasor nthRoot(Phasor var0, int var1) {
		if (var1 <= 0) {
			throw new IllegalArgumentException("The root, " + var1 + ", must be greater than zero");
		} else {
			Phasor var2 = new Phasor();
			var2.reset(Math.pow(var0.magnitude, 1.0D / (double) var1), var0.phaseInDeg / (double) var1);
			return var2;
		}
	}

	public static Phasor square(Phasor var0) {
		Phasor var1 = new Phasor();
		var1.reset(Fmath.square(var0.magnitude), 2.0D * var0.phaseInDeg);
		return var1;
	}

	public static Phasor pow(Phasor var0, int var1) {
		Phasor var2 = new Phasor();
		var2.reset(Math.pow(var0.magnitude, (double) var1), (double) var1 * var0.phaseInDeg);
		return var2;
	}

	public static Phasor pow(Phasor var0, double var1) {
		Phasor var3 = new Phasor();
		var3.reset(Math.pow(var0.magnitude, var1), var1 * var0.phaseInDeg);
		return var3;
	}

	public static Phasor pow(Phasor var0, Complex var1) {
		ComplexNumber var2 = var0.toRectangular();
		ComplexNumber var3 = ComplexMath.pow(var2, var1) ;
		Phasor var4 = toPhasor(var3);
		return var4;
	}

	public static Phasor pow(Phasor var0, Phasor var1) {
		Complex var2 = var0.toRectangular();
		Complex var3 = var1.toRectangular();
		Complex var4 = Complex.pow(var2, var3);
		Phasor var5 = toPhasor(var4);
		return var5;
	}

	public static Phasor exp(Phasor var0) {
		Complex var1 = var0.toRectangular();
		var1 = Complex.exp(var1);
		Phasor var2 = toPhasor(var1);
		return var2;
	}

	public static Phasor log(Phasor var0) {
		Complex var1 = new Complex(Math.log(var0.magnitude), var0.phaseInDeg);
		Phasor var2 = toPhasor(var1);
		return var2;
	}

	public Phasor sin(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Math.sin(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.sin(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor cos(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Math.cos(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.cos(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor tan(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Math.tan(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.tan(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor cot(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.cot(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.cot(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor sec(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.sec(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.sec(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor csc(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.csc(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.csc(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor exsec(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.exsec(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.exsec(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor vers(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.vers(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.vers(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor covers(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.covers(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.covers(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor hav(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.hav(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.hav(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor sinh(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.sinh(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.sinh(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor cosh(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.cosh(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.cosh(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor sech(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.sech(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.sech(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor csch(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.csch(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.csch(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor asin(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Math.asin(var1.getMagnitude()), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.asin(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor acos(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Math.acos(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.acos(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor atan(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Math.atan(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.atan(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor acot(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.acot(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.acot(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor asec(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.asec(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.asec(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor acsc(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.acsc(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.acsc(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor aexsec(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.aexsec(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.aexsec(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor avers(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.avers(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.avers(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor acovers(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.acovers(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.acovers(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor ahav(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.ahav(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.ahav(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor asinh(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.asinh(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.asinh(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor acosh(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.acosh(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.acosh(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor asech(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.asech(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.asech(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public Phasor acsch(Phasor var1) {
		Phasor var2 = new Phasor();
		if (var1.phaseInDeg == 0.0D) {
			var2.reset(Fmath.acsch(var1.magnitude), 0.0D);
		} else {
			Complex var3 = var1.toRectangular();
			var3 = Complex.acsch(var3);
			var2 = toPhasor(var3);
		}

		return var2;
	}

	public boolean isReal() {
		boolean var1 = false;
		if (Math.abs(this.phaseInDeg) == 0.0D) {
			var1 = true;
		}

		return var1;
	}

	public boolean isZero() {
		boolean var1 = false;
		if (Math.abs(this.magnitude) == 0.0D || this.phaseInDeg == Double.NEGATIVE_INFINITY) {
			var1 = true;
		}

		return var1;
	}

	public boolean isPlusInfinity() {
		boolean var1 = false;
		if (this.magnitude == Double.POSITIVE_INFINITY || this.phaseInDeg == Double.POSITIVE_INFINITY) {
			var1 = true;
		}

		return var1;
	}

	public boolean isMinusInfinity() {
		boolean var1 = false;
		if (this.magnitude == Double.NEGATIVE_INFINITY) {
			var1 = true;
		}

		return var1;
	}

	public boolean isNaN() {
		boolean var1 = false;
		if (this.magnitude != this.magnitude || this.phaseInDeg != this.phaseInDeg) {
			var1 = true;
		}

		return var1;
	}

	public boolean equals(Phasor var1) {
		boolean var2 = false;
		if (this.isNaN() && var1.isNaN()) {
			var2 = true;
		} else if (this.magnitude == var1.magnitude && this.phaseInDeg == var1.phaseInDeg) {
			var2 = true;
		}

		return var2;
	}

	public boolean equalsWithinLimits(Phasor var1, double var2) {
		boolean var4 = false;
		double var5 = this.magnitude;
		double var7 = var1.magnitude;
		double var9 = this.phaseInDeg;
		double var11 = var1.phaseInDeg;
		double var13 = 0.0D;
		double var15 = 0.0D;
		double var17 = 0.0D;
		double var19 = 0.0D;
		if (var5 == 0.0D && var9 == 0.0D && var7 == 0.0D && var11 == 0.0D) {
			var4 = true;
		}

		if (!var4) {
			var13 = Math.abs(var5);
			if (Math.abs(var7) > var13) {
				var13 = Math.abs(var7);
			}

			if (var13 == 0.0D) {
				var17 = 0.0D;
			} else {
				var17 = Math.abs(var7 - var5) / var13;
			}

			var15 = Math.abs(var9);
			if (Math.abs(var11) > var15) {
				var15 = Math.abs(var11);
			}

			if (var15 == 0.0D) {
				var19 = 0.0D;
			} else {
				var19 = Math.abs(var11 - var9) / var15;
			}

			if (var17 < var2 && var19 < var2) {
				var4 = true;
			}
		}

		return var4;
	}

	public static Phasor zero() {
		Phasor var0 = new Phasor();
		var0.magnitude = 0.0D;
		var0.phaseInDeg = 0.0D;
		var0.phaseInRad = 0.0D;
		var0.rectangular.polar(var0.magnitude, var0.phaseInRad);
		return var0;
	}

	public static Phasor plusOne() {
		Phasor var0 = new Phasor();
		var0.magnitude = 1.0D;
		var0.phaseInDeg = 0.0D;
		var0.phaseInRad = 0.0D;
		var0.rectangular.polar(var0.magnitude, var0.phaseInRad);
		return var0;
	}

	public static Phasor minusOne() {
		Phasor var0 = new Phasor();
		var0.magnitude = -1.0D;
		var0.phaseInDeg = 0.0D;
		var0.phaseInRad = 0.0D;
		var0.rectangular.polar(var0.magnitude, var0.phaseInRad);
		return var0;
	}

	public static Phasor magnitudeZeroPhase(double var0) {
		Phasor var2 = new Phasor();
		var2.magnitude = var0;
		var2.phaseInDeg = 0.0D;
		var2.phaseInRad = 0.0D;
		var2.rectangular.polar(var2.magnitude, var2.phaseInRad);
		return var2;
	}

	public static Phasor plusInfinity() {
		Phasor var0 = new Phasor();
		var0.magnitude = Double.POSITIVE_INFINITY;
		var0.phaseInDeg = 0.0D;
		var0.phaseInRad = 0.0D;
		var0.rectangular = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		return var0;
	}

	public static Phasor minusInfinity() {
		Phasor var0 = new Phasor();
		var0.magnitude = Double.NEGATIVE_INFINITY;
		var0.phaseInDeg = 0.0D;
		var0.phaseInRad = 0.0D;
		var0.rectangular = new Complex(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
		return var0;
	}

	public static Phasor resistancePhasor(double var0) {
		Phasor var2 = new Phasor(var0);
		return var2;
	}

	public static Phasor inductancePhasor(double var0, double var2) {
		if (Fmath.isNaN(frequency)) {
			frequency = var2;
			omega = frequency * 2.0D * 3.141592653589793D;
			Complex var4 = Impedance.inductanceImpedance(var0, omega);
			Phasor var5 = new Phasor();
			return toPhasor(var4);
		} else {
			throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency
					+ ", that differs from the one you are now attempting to enter, " + var2);
		}
	}

	public static Phasor capacitancePhasor(double var0, double var2) {
		if (Fmath.isNaN(frequency)) {
			frequency = var2;
			omega = frequency * 2.0D * 3.141592653589793D;
			Complex var4 = Impedance.capacitanceImpedance(var0, omega);
			Phasor var5 = new Phasor();
			return toPhasor(var4);
		} else {
			throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency
					+ ", that differs from the one you are now attempting to enter, " + var2);
		}
	}

	public static Phasor infiniteWarburgPhasor(double var0, double var2) {
		if (Fmath.isNaN(frequency)) {
			frequency = var2;
			omega = frequency * 2.0D * 3.141592653589793D;
			Complex var4 = Impedance.infiniteWarburgImpedance(var0, omega);
			Phasor var5 = new Phasor();
			return toPhasor(var4);
		} else {
			throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency
					+ ", that differs from the one you are now attempting to enter, " + var2);
		}
	}

	public static Phasor finiteWarburgPhasor(double var0, double var2, double var4) {
		if (Fmath.isNaN(frequency)) {
			frequency = var4;
			omega = frequency * 2.0D * 3.141592653589793D;
			Complex var6 = Impedance.finiteWarburgImpedance(var0, var2, omega);
			Phasor var7 = new Phasor();
			return toPhasor(var6);
		} else {
			throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency
					+ ", that differs from the one you are now attempting to enter, " + var4);
		}
	}

	public static Phasor constantPhaseElementPhasor(double var0, double var2, double var4) {
		if (Fmath.isNaN(frequency)) {
			frequency = var4;
			omega = frequency * 2.0D * 3.141592653589793D;
			Complex var6 = Impedance.constantPhaseElementImpedance(var0, var2, omega);
			Phasor var7 = new Phasor();
			return toPhasor(var6);
		} else {
			throw new IllegalArgumentException("You have already entered a value for the frequency, " + frequency
					+ ", that differs from the one you are now attempting to enter, " + var4);
		}
	}

}
