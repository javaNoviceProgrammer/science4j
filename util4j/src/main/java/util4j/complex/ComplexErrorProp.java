package util4j.complex;

import util4j.math.ErrorProp;
import util4j.math.Fmath;

public class ComplexErrorProp {
	
	private ErrorProp eReal = new ErrorProp();
	private ErrorProp eImag = new ErrorProp();
	private double corrCoeff = 0.0D;
	private static int monteCarloLength = 10000;

	public ComplexErrorProp() {
		this.eReal.reset(0.0D, 0.0D);
		this.eImag.reset(0.0D, 0.0D);
		this.corrCoeff = 0.0D;
	}

	public ComplexErrorProp(ErrorProp var1, ErrorProp var2) {
		this.eReal = var1.copy();
		this.eImag = var2.copy();
		this.corrCoeff = 0.0D;
	}

	public ComplexErrorProp(ErrorProp var1, ErrorProp var2, double var3) {
		this.eReal = var1.copy();
		this.eImag = var2.copy();
		this.corrCoeff = var3;
	}

	public ComplexErrorProp(double var1, double var3, double var5, double var7) {
		this.eReal.reset(var1, var3);
		this.eImag.reset(var5, var7);
		this.corrCoeff = 0.0D;
	}

	public ComplexErrorProp(double var1, double var3, double var5, double var7, double var9) {
		this.eReal.reset(var1, var3);
		this.eImag.reset(var5, var7);
		this.corrCoeff = var9;
	}

	public void reset(ErrorProp var1, ErrorProp var2) {
		this.eReal = var1.copy();
		this.eImag = var2.copy();
		this.corrCoeff = 0.0D;
	}

	public void reset(ErrorProp var1, ErrorProp var2, double var3) {
		this.eReal = var1.copy();
		this.eImag = var2.copy();
		this.corrCoeff = var3;
	}

	public void reset(double var1, double var3, double var5, double var7) {
		this.eReal.setValue(var1);
		this.eReal.setError(var3);
		this.eImag.setValue(var5);
		this.eImag.setError(var7);
		this.corrCoeff = 0.0D;
	}

	public void reset(double var1, double var3, double var5, double var7, double var9) {
		this.eReal.setValue(var1);
		this.eReal.setError(var3);
		this.eImag.setValue(var5);
		this.eImag.setError(var7);
		this.corrCoeff = var9;
	}

	public void polar(ErrorProp var1, ErrorProp var2) {
		this.polar(var1, var2, 0.0D);
	}

	public void polar(ErrorProp var1, ErrorProp var2, double var3) {
		new ErrorProp();
		ErrorProp var5 = var1.times(ErrorProp.cos(var2), var3);
		this.eReal = var5;
		var5 = var1.times(ErrorProp.sin(var2), var3);
		this.eImag = var5;
		PsRandom var6 = new PsRandom();
		double[][] var7 = var6.correlatedGaussianArrays(var1.getValue(), var2.getValue(), var1.getError(),
				var2.getError(), var3, monteCarloLength);
		double[] var8 = new double[monteCarloLength];
		double[] var9 = new double[monteCarloLength];

		for (int var10 = 0; var10 < monteCarloLength; ++var10) {
			var8[var10] = var7[0][var10] * Math.cos(var7[1][var10]);
			var9[var10] = var7[0][var10] * Math.sin(var7[1][var10]);
		}

		this.corrCoeff = calcRho(var8, var9);
	}

	public static double calcRho(double[] var0, double[] var1) {
		int var2 = var0.length;
		if (var2 != var1.length) {
			throw new IllegalArgumentException("length of x and y must be the same");
		} else {
			double var3 = 0.0D;
			double var5 = 0.0D;

			for (int var7 = 0; var7 < var2; ++var7) {
				var3 += var0[var7];
				var5 += var1[var7];
			}

			var3 /= (double) var2;
			var5 /= (double) var2;
			double var14 = 0.0D;
			double var9 = 0.0D;
			double var11 = 0.0D;

			for (int var13 = 0; var13 < var2; ++var13) {
				var14 += Fmath.square(var0[var13] - var3);
				var9 += Fmath.square(var1[var13] - var5);
				var11 += (var0[var13] - var3) * (var1[var13] - var5);
			}

			var14 = Math.sqrt(var14 / (double) (var2 - 1));
			var9 = Math.sqrt(var9 / (double) (var2 - 1));
			var11 /= (double) (var2 - 1);
			return var11 / (var14 * var9);
		}
	}

	public void polar(double var1, double var3, double var5, double var7) {
		ErrorProp var9 = new ErrorProp(var1, var3);
		ErrorProp var10 = new ErrorProp(var5, var7);
		this.polar(var9, var10, 0.0D);
	}

	public void polar(double var1, double var3, double var5, double var7, double var9) {
		ErrorProp var11 = new ErrorProp(var1, var3);
		ErrorProp var12 = new ErrorProp(var5, var7);
		this.polar(var11, var12, var9);
	}

	public void setReal(ErrorProp var1) {
		this.eReal = var1.copy();
	}

	public void setReal(double var1, double var3) {
		this.eReal.setValue(var1);
		this.eReal.setError(var3);
	}

	public void setImag(ErrorProp var1) {
		this.eImag = var1.copy();
	}

	public void setImag(double var1, double var3) {
		this.eImag.setValue(var1);
		this.eImag.setError(var3);
	}

	public void setDouble(double var1) {
		this.eReal.reset(var1, 0.0D);
		this.eImag.reset(0.0D, 0.0D);
	}

	public void setCorrCoeff(double var1) {
		this.corrCoeff = var1;
	}

	public static void setMonteCarloLength(int var0) {
		monteCarloLength = var0;
	}

	public ErrorProp getReal() {
		return this.eReal.copy();
	}

	public double getRealValue() {
		return this.eReal.getValue();
	}

	public double getRealError() {
		return this.eReal.getError();
	}

	public ErrorProp getImag() {
		return this.eImag.copy();
	}

	public double getImagValue() {
		return this.eImag.getValue();
	}

	public double getImagError() {
		return this.eImag.getError();
	}

	public double getCorrCoeff() {
		return this.corrCoeff;
	}

	public static int getMonteCarloLength() {
		return monteCarloLength;
	}

	public static ComplexErrorProp copy(ComplexErrorProp var0) {
		if (var0 == null) {
			return null;
		} else {
			ComplexErrorProp var1 = new ComplexErrorProp();
			var1.eReal = var0.eReal.copy();
			var1.eImag = var0.eImag.copy();
			return var1;
		}
	}

	public ComplexErrorProp copy() {
		if (this == null) {
			return null;
		} else {
			ComplexErrorProp var1 = new ComplexErrorProp();
			var1.eReal = this.eReal.copy();
			var1.eImag = this.eImag.copy();
			return var1;
		}
	}

	public Object clone() {
		if (this == null) {
			return null;
		} else {
			ComplexErrorProp var1 = new ComplexErrorProp();
			var1.eReal = this.eReal.copy();
			var1.eImag = this.eImag.copy();
			return var1;
		}
	}

	public static ComplexErrorProp plus(ComplexErrorProp var0, ComplexErrorProp var1) {
		ComplexErrorProp var2 = new ComplexErrorProp();
		var2.eReal = var0.eReal.plus(var1.eReal);
		var2.eImag = var0.eImag.plus(var1.eImag);
		return var2;
	}

	public ComplexErrorProp plus(ComplexErrorProp var1) {
		ComplexErrorProp var2 = new ComplexErrorProp();
		var2.eReal = this.eReal.plus(var1.eReal);
		var2.eImag = this.eImag.plus(var1.eImag);
		return var2;
	}

	public static ComplexErrorProp minus(ComplexErrorProp var0, ComplexErrorProp var1) {
		ComplexErrorProp var2 = new ComplexErrorProp();
		var2.eReal = var0.eReal.minus(var1.eReal);
		var2.eImag = var0.eImag.minus(var1.eImag);
		return var2;
	}

	public ComplexErrorProp minus(ComplexErrorProp var1) {
		ComplexErrorProp var2 = new ComplexErrorProp();
		var2.eReal = this.eReal.minus(var1.eReal);
		var2.eImag = this.eImag.minus(var1.eImag);
		return var2;
	}

	public static ComplexErrorProp times(ComplexErrorProp var0, ComplexErrorProp var1) {
		ComplexErrorProp var2 = new ComplexErrorProp();
		var2.eReal = var0.eReal.times(var1.eReal).minus(var0.eImag.times(var1.eImag));
		var2.eImag = var0.eReal.times(var1.eImag).plus(var0.eImag.times(var1.eReal));
		return var2;
	}

	public ComplexErrorProp times(ComplexErrorProp var1) {
		ComplexErrorProp var2 = new ComplexErrorProp();
		var2.eReal = this.eReal.times(var1.eReal).minus(this.eImag.times(var1.eImag));
		var2.eImag = this.eReal.times(var1.eImag).plus(this.eImag.times(var1.eReal));
		return var2;
	}

	public void timesEquals(ComplexErrorProp var1) {
		ComplexErrorProp var2 = new ComplexErrorProp();
		var2.eReal = var1.eReal.times(this.eReal).minus(var1.eImag.times(this.eImag));
		var2.eImag = var1.eReal.times(this.eImag).plus(var1.eImag.times(this.eReal));
		this.eReal = var2.eReal.copy();
		this.eImag = var2.eImag.copy();
	}

	public static ComplexErrorProp over(ComplexErrorProp var0, ComplexErrorProp var1) {
		ComplexErrorProp var2 = new ComplexErrorProp();
		PsRandom var3 = new PsRandom();
		double[] var4 = var3.gaussianArray(var0.eReal.getValue(), var0.eReal.getError(), monteCarloLength);
		double[] var5 = var3.gaussianArray(var0.eImag.getValue(), var0.eImag.getError(), monteCarloLength);
		double[] var6 = var3.gaussianArray(var1.eReal.getValue(), var1.eReal.getError(), monteCarloLength);
		double[] var7 = var3.gaussianArray(var1.eImag.getValue(), var1.eImag.getError(), monteCarloLength);
		double[] var8 = new double[monteCarloLength];
		double[] var9 = new double[monteCarloLength];
		double[] var10 = new double[monteCarloLength];
		double[] var11 = new double[monteCarloLength];

		for (int var12 = 0; var12 < monteCarloLength; ++var12) {
			if (Math.abs(var6[var12]) >= Math.abs(var7[var12])) {
				var8[var12] = var7[var12] / var6[var12];
				var9[var12] = var6[var12] + var7[var12] * var8[var12];
				var10[var12] = (var4[var12] + var5[var12] * var8[var12]) / var9[var12];
				var11[var12] = (var5[var12] - var4[var12] * var8[var12]) / var9[var12];
			} else {
				var8[var12] = var6[var12] / var7[var12];
				var9[var12] = var6[var12] * var8[var12] + var7[var12];
				var10[var12] = (var4[var12] * var8[var12] + var5[var12]) / var9[var12];
				var11[var12] = (var5[var12] * var8[var12] - var4[var12]) / var9[var12];
			}
		}

		double var25 = 0.0D;
		double var14 = 0.0D;
		double var16 = 0.0D;
		double var18 = 0.0D;

		int var20;
		for (var20 = 0; var20 < monteCarloLength; ++var20) {
			var25 += var10[var20];
			var14 += var11[var20];
		}

		var25 /= (double) monteCarloLength;
		var14 /= (double) monteCarloLength;

		for (var20 = 0; var20 < monteCarloLength; ++var20) {
			var16 += Fmath.square(var25 - var10[var20]);
			var18 += Fmath.square(var14 - var11[var20]);
		}

		var16 = Math.sqrt(var16 / (double) (monteCarloLength - 1));
		var18 = Math.sqrt(var18 / (double) (monteCarloLength - 1));
		var2.eReal.setError(var16);
		var2.eImag.setError(var18);
		double var24 = 0.0D;
		double var22 = 0.0D;
		if (Math.abs(var1.eReal.getValue()) >= Math.abs(var1.eImag.getValue())) {
			var22 = var1.eImag.getValue() / var1.eReal.getValue();
			var24 = var1.eReal.getValue() + var1.eImag.getValue() * var22;
			var2.eReal.setValue((var0.eReal.getValue() + var0.eImag.getValue() * var22) / var24);
			var2.eImag.setValue((var0.eImag.getValue() - var0.eReal.getValue() * var22) / var24);
		} else {
			var22 = var1.eReal.getValue() / var1.eImag.getValue();
			var24 = var1.eReal.getValue() * var22 + var1.eImag.getValue();
			var2.eReal.setValue((var0.eReal.getValue() * var22 + var0.eImag.getValue()) / var24);
			var2.eImag.setValue((var0.eImag.getValue() * var22 - var0.eReal.getValue()) / var24);
		}

		return var2;
	}

	public ComplexErrorProp over(ComplexErrorProp var1) {
		ComplexErrorProp var2 = new ComplexErrorProp();
		PsRandom var3 = new PsRandom();
		double[] var4 = var3.gaussianArray(this.eReal.getValue(), this.eReal.getError(), monteCarloLength);
		double[] var5 = var3.gaussianArray(this.eImag.getValue(), this.eImag.getError(), monteCarloLength);
		double[] var6 = var3.gaussianArray(var1.eReal.getValue(), var1.eReal.getError(), monteCarloLength);
		double[] var7 = var3.gaussianArray(var1.eImag.getValue(), var1.eImag.getError(), monteCarloLength);
		double[] var8 = new double[monteCarloLength];
		double[] var9 = new double[monteCarloLength];
		double[] var10 = new double[monteCarloLength];
		double[] var11 = new double[monteCarloLength];

		for (int var12 = 0; var12 < monteCarloLength; ++var12) {
			if (Math.abs(var6[var12]) >= Math.abs(var7[var12])) {
				var8[var12] = var7[var12] / var6[var12];
				var9[var12] = var6[var12] + var7[var12] * var8[var12];
				var10[var12] = (var4[var12] + var5[var12] * var8[var12]) / var9[var12];
				var11[var12] = (var5[var12] - var4[var12] * var8[var12]) / var9[var12];
			} else {
				var8[var12] = var6[var12] / var7[var12];
				var9[var12] = var6[var12] * var8[var12] + var7[var12];
				var10[var12] = (var4[var12] * var8[var12] + var5[var12]) / var9[var12];
				var11[var12] = (var5[var12] * var8[var12] - var4[var12]) / var9[var12];
			}
		}

		double var25 = 0.0D;
		double var14 = 0.0D;
		double var16 = 0.0D;
		double var18 = 0.0D;

		int var20;
		for (var20 = 0; var20 < monteCarloLength; ++var20) {
			var25 += var10[var20];
			var14 += var11[var20];
		}

		var25 /= (double) monteCarloLength;
		var14 /= (double) monteCarloLength;

		for (var20 = 0; var20 < monteCarloLength; ++var20) {
			var16 += Fmath.square(var25 - var10[var20]);
			var18 += Fmath.square(var14 - var11[var20]);
		}

		var16 = Math.sqrt(var16 / (double) (monteCarloLength - 1));
		var18 = Math.sqrt(var18 / (double) (monteCarloLength - 1));
		var2.eReal.setError(var16);
		var2.eImag.setError(var18);
		double var24 = 0.0D;
		double var22 = 0.0D;
		if (Math.abs(var1.eReal.getValue()) >= Math.abs(var1.eImag.getValue())) {
			var22 = var1.eImag.getValue() / var1.eReal.getValue();
			var24 = var1.eReal.getValue() + var1.eImag.getValue() * var22;
			var2.eReal.setValue((this.eReal.getValue() + this.eImag.getValue() * var22) / var24);
			var2.eImag.setValue((this.eImag.getValue() - this.eReal.getValue() * var22) / var24);
		} else {
			var22 = var1.eReal.getValue() / var1.eImag.getValue();
			var24 = var1.eReal.getValue() * var22 + var1.eImag.getValue();
			var2.eReal.setValue((this.eReal.getValue() * var22 + this.eImag.getValue()) / var24);
			var2.eImag.setValue((this.eImag.getValue() * var22 - this.eReal.getValue()) / var24);
		}

		return var2;
	}

	public static ComplexErrorProp exp(ComplexErrorProp var0) {
		ComplexErrorProp var1 = new ComplexErrorProp();
		ErrorProp var2 = ErrorProp.exp(var0.eReal);
		var1.eReal = var2.times(ErrorProp.cos(var0.eImag), var0.corrCoeff);
		var1.eImag = var2.times(ErrorProp.sin(var0.eImag), var0.corrCoeff);
		return var1;
	}

	public ComplexErrorProp exp() {
		ComplexErrorProp var1 = new ComplexErrorProp();
		ErrorProp var2 = ErrorProp.exp(this.eReal);
		var1.eReal = var2.times(ErrorProp.cos(this.eImag), this.corrCoeff);
		var1.eImag = var2.times(ErrorProp.sin(this.eImag), this.corrCoeff);
		return var1;
	}

	public static ErrorProp abs(ComplexErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		double var2 = var0.eReal.getValue();
		double var4 = var0.eImag.getValue();
		double var6 = Math.abs(var2);
		double var8 = Math.abs(var4);
		double var10 = 0.0D;
		double var12 = 0.0D;
		if (var6 == 0.0D) {
			var12 = var8;
		} else {
			if (var8 == 0.0D) {
				;
			}

			if (var6 >= var8) {
				var10 = var4 / var2;
				var12 = var6 * Math.sqrt(1.0D + var10 * var10);
			} else {
				var10 = var2 / var4;
				var12 = var8 * Math.sqrt(1.0D + var10 * var10);
			}
		}

		var1.setValue(var12);
		double var14 = var0.eReal.getError();
		double var16 = var0.eImag.getError();
		var12 = hypotWithRho(2.0D * var14 * var2, 2.0D * var16 * var4, var0.corrCoeff);
		var1.setError(var12);
		return var1;
	}

	public ErrorProp abs() {
		ErrorProp var1 = new ErrorProp();
		double var2 = this.eReal.getValue();
		double var4 = this.eImag.getValue();
		double var6 = Math.abs(var2);
		double var8 = Math.abs(var4);
		double var10 = 0.0D;
		double var12 = 0.0D;
		if (var6 == 0.0D) {
			var12 = var8;
		} else {
			if (var8 == 0.0D) {
				;
			}

			if (var6 >= var8) {
				var10 = var4 / var2;
				var12 = var6 * Math.sqrt(1.0D + var10 * var10);
			} else {
				var10 = var2 / var4;
				var12 = var8 * Math.sqrt(1.0D + var10 * var10);
			}
		}

		var1.setValue(var12);
		double var14 = this.eReal.getError();
		double var16 = this.eImag.getError();
		var12 = hypotWithRho(2.0D * var14 * var2, 2.0D * var16 * var4, this.corrCoeff);
		var1.setError(var12);
		return var1;
	}

	public static ErrorProp arg(ComplexErrorProp var0) {
		new ErrorProp();
		ErrorProp var1 = ErrorProp.atan2(var0.eReal, var0.eImag, var0.corrCoeff);
		return var1;
	}

	public ErrorProp arg(double var1) {
		new ErrorProp();
		ErrorProp var3 = ErrorProp.atan2(this.eReal, this.eImag, this.corrCoeff);
		return var3;
	}

	public static double hypotWithRho(double var0, double var2, double var4) {
		double var6 = Math.abs(var0);
		double var8 = Math.abs(var2);
		double var10 = 0.0D;
		double var12 = 0.0D;
		if (var6 == 0.0D) {
			var10 = var8;
		} else if (var8 == 0.0D) {
			var10 = var6;
		} else if (var6 >= var8) {
			var12 = var8 / var6;
			var10 = var6 * Math.sqrt(1.0D + var12 * var12 + 2.0D * var4 * var12);
		} else {
			var12 = var6 / var8;
			var10 = var8 * Math.sqrt(1.0D + var12 * var12 + 2.0D * var4 * var12);
		}

		return var10;
	}

	public static ComplexErrorProp truncate(ComplexErrorProp var0, int var1) {
		if (var1 < 0) {
			return var0;
		} else {
			double var2 = var0.eReal.getValue();
			double var4 = var0.eReal.getError();
			double var6 = var0.eImag.getValue();
			double var8 = var0.eImag.getError();
			ComplexErrorProp var10 = new ComplexErrorProp();
			var2 = Fmath.truncate(var2, var1);
			var4 = Fmath.truncate(var4, var1);
			var6 = Fmath.truncate(var6, var1);
			var8 = Fmath.truncate(var8, var1);
			var10.reset(var2, var4, var6, var8);
			return var10;
		}
	}

	public ComplexErrorProp truncate(int var1) {
		if (var1 < 0) {
			return this;
		} else {
			double var2 = this.eReal.getValue();
			double var4 = this.eReal.getError();
			double var6 = this.eImag.getValue();
			double var8 = this.eImag.getError();
			ComplexErrorProp var10 = new ComplexErrorProp();
			var2 = Fmath.truncate(var2, var1);
			var4 = Fmath.truncate(var4, var1);
			var6 = Fmath.truncate(var6, var1);
			var8 = Fmath.truncate(var8, var1);
			var10.reset(var2, var4, var6, var8);
			return var10;
		}
	}

	public String toString() {
		return "Real part: " + this.eReal.getValue() + ", error = " + this.eReal.getError() + "; Imaginary part: "
				+ this.eImag.getValue() + ", error = " + this.eImag.getError();
	}

	public static String toString(ComplexErrorProp var0) {
		return "Real part: " + var0.eReal.getValue() + ", error = " + var0.eReal.getError() + "; Imaginary part: "
				+ var0.eImag.getValue() + ", error = " + var0.eImag.getError();
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

}
