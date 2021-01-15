package util4j.math;

public class ErrorProp {
	
	private double value = 0.0D;
	private double error = 0.0D;

	public ErrorProp() {
		this.value = 0.0D;
		this.error = 0.0D;
	}

	public ErrorProp(double var1, double var3) {
		this.value = var1;
		this.error = var3;
	}

	public ErrorProp(double var1) {
		this.value = var1;
		this.error = 0.0D;
	}

	public void setValue(double var1) {
		this.value = var1;
	}

	public void setError(double var1) {
		this.error = var1;
	}

	public void reset(double var1, double var3) {
		this.value = var1;
		this.error = var3;
	}

	public double getValue() {
		return this.value;
	}

	public double getError() {
		return this.error;
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

	public static ErrorProp truncate(ErrorProp var0, int var1) {
		if (var1 < 0) {
			return var0;
		} else {
			double var2 = var0.getValue();
			double var4 = var0.getError();
			ErrorProp var6 = new ErrorProp();
			var2 = Fmath.truncate(var2, var1);
			var4 = Fmath.truncate(var4, var1);
			var6.reset(var2, var4);
			return var6;
		}
	}

	public ErrorProp truncate(int var1) {
		if (var1 < 0) {
			return this;
		} else {
			double var2 = this.getValue();
			double var4 = this.getError();
			ErrorProp var6 = new ErrorProp();
			var2 = Fmath.truncate(var2, var1);
			var4 = Fmath.truncate(var4, var1);
			var6.reset(var2, var4);
			return var6;
		}
	}

	public String toString() {
		return this.value + ", error = " + this.error;
	}

	public static String toString(ErrorProp var0) {
		return var0.value + ", error = " + var0.error;
	}

	public int hashCode() {
		long var1 = Double.doubleToLongBits(this.value);
		long var3 = Double.doubleToLongBits(this.error);
		int var5 = (int) (var1 ^ var1 >>> 32);
		int var6 = (int) (var3 ^ var3 >>> 32);
		return 7 * (var5 / 10) + 3 * (var6 / 10);
	}

	public static ErrorProp[] oneDarray(int var0) {
		ErrorProp[] var1 = new ErrorProp[var0];

		for (int var2 = 0; var2 < var0; ++var2) {
			var1[var2] = zero();
		}

		return var1;
	}

	public static ErrorProp[] oneDarray(int var0, double var1, double var3) {
		ErrorProp[] var5 = new ErrorProp[var0];

		for (int var6 = 0; var6 < var0; ++var6) {
			var5[var6] = zero();
			var5[var6].reset(var1, var3);
		}

		return var5;
	}

	public static ErrorProp[] oneDarray(int var0, ErrorProp var1) {
		ErrorProp[] var2 = new ErrorProp[var0];

		for (int var3 = 0; var3 < var0; ++var3) {
			var2[var3] = copy(var1);
		}

		return var2;
	}

	public static ErrorProp[][] twoDarray(int var0, int var1) {
		ErrorProp[][] var2 = new ErrorProp[var0][var1];

		for (int var3 = 0; var3 < var0; ++var3) {
			for (int var4 = 0; var4 < var1; ++var4) {
				var2[var3][var4] = zero();
			}
		}

		return var2;
	}

	public static ErrorProp[][] twoDarray(int var0, int var1, double var2, double var4) {
		ErrorProp[][] var6 = new ErrorProp[var0][var1];

		for (int var7 = 0; var7 < var0; ++var7) {
			for (int var8 = 0; var8 < var1; ++var8) {
				var6[var7][var8] = zero();
				var6[var7][var8].reset(var2, var4);
			}
		}

		return var6;
	}

	public static ErrorProp[][] twoDarray(int var0, int var1, ErrorProp var2) {
		ErrorProp[][] var3 = new ErrorProp[var0][var1];

		for (int var4 = 0; var4 < var0; ++var4) {
			for (int var5 = 0; var5 < var1; ++var5) {
				var3[var4][var5] = copy(var2);
			}
		}

		return var3;
	}

	public static ErrorProp copy(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = var0.value;
		var1.error = var0.error;
		return var1;
	}

	public ErrorProp copy() {
		ErrorProp var1 = new ErrorProp();
		var1.value = this.value;
		var1.error = this.error;
		return var1;
	}

	public Object clone() {
		ErrorProp var1 = new ErrorProp();
		var1.value = this.value;
		var1.error = this.error;
		return var1;
	}

	public static ErrorProp[] copy(ErrorProp[] var0) {
		int var1 = var0.length;
		ErrorProp[] var2 = oneDarray(var1);

		for (int var3 = 0; var3 < var1; ++var3) {
			var2[var3] = copy(var0[var3]);
		}

		return var2;
	}

	public static ErrorProp[][] copy(ErrorProp[][] var0) {
		int var1 = var0.length;
		int var2 = var0[0].length;
		ErrorProp[][] var3 = twoDarray(var1, var2);

		for (int var4 = 0; var4 < var1; ++var4) {
			for (int var5 = 0; var5 < var2; ++var5) {
				var3[var4][var5] = copy(var0[var4][var5]);
			}
		}

		return var3;
	}

	public ErrorProp plus(ErrorProp var1, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = var1.value + this.value;
		var4.error = hypotWithCov(var1.error, this.error, var2);
		return var4;
	}

	public static ErrorProp plus(ErrorProp var0, ErrorProp var1, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = var0.value + var1.value;
		var4.error = hypotWithCov(var0.error, var1.error, var2);
		return var4;
	}

	public ErrorProp plus(ErrorProp var1) {
		ErrorProp var2 = new ErrorProp();
		var2.value = this.value + var1.value;
		var2.error = hypotWithCov(var1.error, this.error, 0.0D);
		return var2;
	}

	public static ErrorProp plus(ErrorProp var0, ErrorProp var1) {
		ErrorProp var2 = new ErrorProp();
		var2.value = var0.value + var1.value;
		var2.error = hypotWithCov(var0.error, var1.error, 0.0D);
		return var2;
	}

	public ErrorProp plus(double var1) {
		ErrorProp var3 = new ErrorProp();
		var3.value = this.value + var1;
		var3.error = Math.abs(this.error);
		return var3;
	}

	public static ErrorProp plus(double var0, ErrorProp var2) {
		ErrorProp var3 = new ErrorProp();
		var3.value = var0 + var2.value;
		var3.error = Math.abs(var2.error);
		return var3;
	}

	public static ErrorProp plus(double var0, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = var0 + var2;
		var4.error = 0.0D;
		return var4;
	}

	public void plusEquals(ErrorProp var1, double var2) {
		this.value += var1.value;
		this.error = hypotWithCov(var1.error, this.error, var2);
	}

	public void plusEquals(ErrorProp var1) {
		this.value += var1.value;
		this.error = Math.sqrt(var1.error * var1.error + this.error * this.error);
		this.error = hypotWithCov(var1.error, this.error, 0.0D);
	}

	public void plusEquals(double var1) {
		this.value += var1;
		this.error = Math.abs(this.error);
	}

	public ErrorProp minus(ErrorProp var1, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = this.value - var1.value;
		var4.error = hypotWithCov(this.error, var1.error, -var2);
		return var4;
	}

	public static ErrorProp minus(ErrorProp var0, ErrorProp var1, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = var0.value - var1.value;
		var4.error = hypotWithCov(var0.error, var1.error, -var2);
		return var4;
	}

	public ErrorProp minus(ErrorProp var1) {
		ErrorProp var2 = new ErrorProp();
		var2.value = this.value - var1.value;
		var2.error = hypotWithCov(var1.error, this.error, 0.0D);
		return var2;
	}

	public static ErrorProp minus(ErrorProp var0, ErrorProp var1) {
		ErrorProp var2 = new ErrorProp();
		var2.value = var0.value - var1.value;
		var2.error = hypotWithCov(var0.error, var1.error, 0.0D);
		return var2;
	}

	public ErrorProp minus(double var1) {
		ErrorProp var3 = new ErrorProp();
		var3.value = this.value - var1;
		var3.error = Math.abs(this.error);
		return var3;
	}

	public static ErrorProp minus(double var0, ErrorProp var2) {
		ErrorProp var3 = new ErrorProp();
		var3.value = var0 - var2.value;
		var3.error = Math.abs(var2.error);
		return var3;
	}

	public static ErrorProp minus(double var0, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = var0 - var2;
		var4.error = 0.0D;
		return var4;
	}

	public void minusEquals(ErrorProp var1, double var2) {
		this.value -= var1.value;
		this.error = hypotWithCov(var1.error, this.error, -var2);
	}

	public void minusEquals(ErrorProp var1) {
		this.value -= var1.value;
		this.error = hypotWithCov(var1.error, this.error, 0.0D);
	}

	public void minusEquals(double var1) {
		this.value -= var1;
		this.error = Math.abs(this.error);
	}

	public ErrorProp times(ErrorProp var1, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = var1.value * this.value;
		if (var1.value == 0.0D) {
			var4.error = var1.error * this.value;
		} else if (this.value == 0.0D) {
			var4.error = this.error * var1.value;
		} else {
			var4.error = Math.abs(var4.value) * hypotWithCov(var1.error / var1.value, this.error / this.value, var2);
		}

		return var4;
	}

	public ErrorProp times(ErrorProp var1) {
		ErrorProp var2 = new ErrorProp();
		var2.value = this.value * var1.value;
		if (var1.value == 0.0D) {
			var2.error = var1.error * this.value;
		} else if (this.value == 0.0D) {
			var2.error = this.error * var1.value;
		} else {
			var2.error = Math.abs(var2.value) * hypotWithCov(var1.error / var1.value, this.error / this.value, 0.0D);
		}

		return var2;
	}

	public ErrorProp times(double var1) {
		ErrorProp var3 = new ErrorProp();
		var3.value = this.value * var1;
		var3.error = Math.abs(this.error * var1);
		return var3;
	}

	public static ErrorProp times(ErrorProp var0, ErrorProp var1, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = var0.value * var1.value;
		if (var0.value == 0.0D) {
			var4.error = var0.error * var1.value;
		} else if (var1.value == 0.0D) {
			var4.error = var1.error * var0.value;
		} else {
			var4.error = Math.abs(var4.value) * hypotWithCov(var0.error / var0.value, var1.error / var1.value, var2);
		}

		return var4;
	}

	public static ErrorProp times(ErrorProp var0, ErrorProp var1) {
		ErrorProp var2 = new ErrorProp();
		var2.value = var0.value * var1.value;
		if (var0.value == 0.0D) {
			var2.error = var0.error * var1.value;
		} else if (var1.value == 0.0D) {
			var2.error = var1.error * var0.value;
		} else {
			var2.error = Math.abs(var2.value) * hypotWithCov(var0.error / var0.value, var1.error / var1.value, 0.0D);
		}

		return var2;
	}

	public static ErrorProp times(double var0, ErrorProp var2) {
		ErrorProp var3 = new ErrorProp();
		var3.value = var0 * var2.value;
		var3.error = Math.abs(var0 * var2.error);
		return var3;
	}

	public static ErrorProp times(double var0, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = var0 * var2;
		var4.error = 0.0D;
		return var4;
	}

	public void timesEquals(ErrorProp var1, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = this.value * var1.value;
		if (var1.value == 0.0D) {
			var4.error = var1.error * this.value;
		} else if (this.value == 0.0D) {
			var4.error = this.error * var1.value;
		} else {
			var4.error = Math.abs(var4.value) * hypotWithCov(var1.error / var1.value, this.error / this.value, var2);
		}

		this.value = var4.value;
		this.error = var4.error;
	}

	public void timesEquals(ErrorProp var1) {
		ErrorProp var2 = new ErrorProp();
		var2.value = this.value * var1.value;
		if (var1.value == 0.0D) {
			var2.error = var1.error * this.value;
		} else if (this.value == 0.0D) {
			var2.error = this.error * var1.value;
		} else {
			var2.error = Math.abs(var2.value) * hypotWithCov(var1.error / var1.value, this.error / this.value, 0.0D);
		}

		this.value = var2.value;
		this.error = var2.error;
	}

	public void timesEquals(double var1) {
		this.value *= var1;
		this.error = Math.abs(this.error * var1);
	}

	public ErrorProp over(ErrorProp var1, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = this.value / var1.value;
		if (this.value == 0.0D) {
			var4.error = this.error * var1.value;
		} else {
			var4.error = Math.abs(var4.value) * hypotWithCov(this.error / this.value, var1.error / var1.value, -var2);
		}

		return var4;
	}

	public static ErrorProp over(ErrorProp var0, ErrorProp var1, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = var0.value / var1.value;
		if (var0.value == 0.0D) {
			var4.error = var0.error * var1.value;
		} else {
			var4.error = Math.abs(var4.value) * hypotWithCov(var0.error / var0.value, var1.error / var1.value, -var2);
		}

		return var4;
	}

	public ErrorProp over(ErrorProp var1) {
		ErrorProp var2 = new ErrorProp();
		var2.value = this.value / var1.value;
		var2.error = Math.abs(var2.value) * hypotWithCov(var1.error / var1.value, this.error / this.value, 0.0D);
		if (this.value == 0.0D) {
			var2.error = this.error * var2.value;
		} else {
			var2.error = Math.abs(var2.value) * hypotWithCov(var1.error / var1.value, this.error / this.value, 0.0D);
		}

		return var2;
	}

	public static ErrorProp over(ErrorProp var0, ErrorProp var1) {
		ErrorProp var2 = new ErrorProp();
		var2.value = var0.value / var1.value;
		if (var0.value == 0.0D) {
			var2.error = var0.error * var1.value;
		} else {
			var2.error = Math.abs(var2.value) * hypotWithCov(var0.error / var0.value, var1.error / var1.value, 0.0D);
		}

		return var2;
	}

	public ErrorProp over(double var1) {
		ErrorProp var3 = new ErrorProp();
		var3.value = this.value / var1;
		var3.error = Math.abs(this.error / var1);
		return var3;
	}

	public static ErrorProp over(double var0, ErrorProp var2) {
		ErrorProp var3 = new ErrorProp();
		var3.value = var0 / var2.value;
		var3.error = Math.abs(var0 * var2.error / (var2.value * var2.value));
		return var3;
	}

	public static ErrorProp over(double var0, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = var0 / var2;
		var4.error = 0.0D;
		return var4;
	}

	public void overEquals(ErrorProp var1) {
		ErrorProp var2 = new ErrorProp();
		var2.value = this.value / var1.value;
		if (this.value == 0.0D) {
			var2.error = this.error * var1.value;
		} else {
			var2.error = Math.abs(var2.value) * hypotWithCov(this.error / this.value, var1.error / var1.value, 0.0D);
		}

		this.value = var2.value;
		this.error = var2.error;
	}

	public void overEquals(ErrorProp var1, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = this.value / var1.value;
		if (this.value == 0.0D) {
			var4.error = this.error * var1.value;
		} else {
			var4.error = Math.abs(var4.value) * hypotWithCov(this.error / this.value, var1.error / var1.value, -var2);
		}

		this.value = var4.value;
		this.error = var4.error;
	}

	public void overEquals(double var1) {
		this.value /= var1;
		this.error = Math.abs(this.error / var1);
	}

	public ErrorProp inverse() {
		ErrorProp var1 = over(1.0D, this);
		return var1;
	}

	public static ErrorProp inverse(ErrorProp var0) {
		ErrorProp var1 = over(1.0D, var0);
		return var1;
	}

	public static ErrorProp hypot(ErrorProp var0, ErrorProp var1, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = Fmath.hypot(var0.value, var1.value);
		var4.error = Math.abs(hypotWithCov(var0.error * var0.value, var1.error * var1.value, var2) / var4.value);
		return var4;
	}

	public static ErrorProp hypot(ErrorProp var0, ErrorProp var1) {
		ErrorProp var2 = new ErrorProp();
		var2.value = Fmath.hypot(var0.value, var1.value);
		var2.error = Math.abs(hypotWithCov(var0.error * var0.value, var1.error * var1.value, 0.0D) / var2.value);
		return var2;
	}

	public static ErrorProp abs(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Math.abs(var0.value);
		var1.error = Math.abs(var0.error);
		return var1;
	}

	public ErrorProp abs() {
		ErrorProp var1 = new ErrorProp();
		var1.value = Math.abs(this.value);
		var1.error = Math.abs(this.error);
		return var1;
	}

	public static ErrorProp exp(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Math.exp(var0.value);
		var1.error = Math.abs(var1.value * var0.error);
		return var1;
	}

	public static ErrorProp log(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Math.log(var0.value);
		var1.error = Math.abs(var0.error / var0.value);
		return var1;
	}

	public static ErrorProp log10(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Fmath.log10(var0.value);
		var1.error = Math.abs(var0.error / (var0.value * Math.log(10.0D)));
		return var1;
	}

	public static ErrorProp sqrt(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Math.sqrt(var0.value);
		var1.error = Math.abs(var0.error / (2.0D * var0.value));
		return var1;
	}

	public static ErrorProp nthRoot(ErrorProp var0, int var1) {
		if (var1 == 0) {
			throw new ArithmeticException("Division by zero (n = 0 - infinite root) attempted in ErrorProp.nthRoot");
		} else {
			ErrorProp var2 = new ErrorProp();
			var2.value = Math.pow(var0.value, (double) (1 / var1));
			var2.error = Math.abs(var0.error * Math.pow(var0.value, (double) (1 / var1 - 1)) / (double) var1);
			return var2;
		}
	}

	public ErrorProp square() {
		ErrorProp var1 = new ErrorProp(this.value, this.error);
		return var1.times(var1, 1.0D);
	}

	public static ErrorProp square(ErrorProp var0) {
		return var0.times(var0, 1.0D);
	}

	public static ErrorProp pow(ErrorProp var0, double var1) {
		ErrorProp var3 = new ErrorProp();
		var3.value = Math.pow(var0.value, var1);
		var3.error = Math.abs(var1 * Math.pow(var0.value, var1 - 1.0D));
		return var3;
	}

	public static ErrorProp pow(double var0, ErrorProp var2) {
		ErrorProp var3 = new ErrorProp();
		var3.value = Math.pow(var0, var2.value);
		var3.error = Math.abs(var3.value * Math.log(var0) * var2.error);
		return var3;
	}

	public static ErrorProp pow(ErrorProp var0, ErrorProp var1, double var2) {
		ErrorProp var4 = new ErrorProp();
		var4.value = Math.pow(var0.value, var1.value);
		var4.error = hypotWithCov(var0.error * var1.value * Math.pow(var0.value, var1.value - 1.0D),
				var1.error * Math.log(var0.value) * Math.pow(var0.value, var1.value), var2);
		return var4;
	}

	public static ErrorProp pow(ErrorProp var0, ErrorProp var1) {
		ErrorProp var2 = new ErrorProp();
		var2.value = Math.pow(var0.value, var1.value);
		var2.error = hypotWithCov(var0.error * var1.value * Math.pow(var0.value, var1.value - 1.0D),
				var1.error * Math.log(var0.value) * Math.pow(var0.value, var1.value), 0.0D);
		return var2;
	}

	public static ErrorProp sin(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Math.sin(var0.value);
		var1.error = Math.abs(var0.error * Math.cos(var0.value));
		return var1;
	}

	public static ErrorProp cos(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Math.cos(var0.value);
		var1.error = Math.abs(var0.error * Math.sin(var0.value));
		return var1;
	}

	public static ErrorProp tan(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Math.tan(var0.value);
		var1.error = Math.abs(var0.error * Fmath.square(Fmath.sec(var0.value)));
		return var1;
	}

	public static ErrorProp sinh(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Fmath.sinh(var0.value);
		var1.error = Math.abs(var0.error * Fmath.cosh(var0.value));
		return var1;
	}

	public static ErrorProp cosh(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Fmath.cosh(var0.value);
		var1.error = Math.abs(var0.error * Fmath.sinh(var0.value));
		return var1;
	}

	public static ErrorProp tanh(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Fmath.tanh(var0.value);
		var1.error = Math.abs(var0.error * Fmath.square(Fmath.sech(var0.value)));
		return var1;
	}

	public static ErrorProp asin(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Math.asin(var0.value);
		var1.error = Math.abs(var0.error / Math.sqrt(1.0D - var0.value * var0.value));
		return var1;
	}

	public static ErrorProp acos(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Math.acos(var0.value);
		var1.error = Math.abs(var0.error / Math.sqrt(1.0D - var0.value * var0.value));
		return var1;
	}

	public static ErrorProp atan(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Math.atan(var0.value);
		var1.error = Math.abs(var0.error / (1.0D + var0.value * var0.value));
		return var1;
	}

	public static ErrorProp atan2(ErrorProp var0, ErrorProp var1) {
		ErrorProp var2 = new ErrorProp();
		ErrorProp var3 = var0.over(var1);
		var2.value = Math.atan2(var0.value, var1.value);
		var2.error = Math.abs(var3.error / (1.0D + var3.value * var3.value));
		return var2;
	}

	public static ErrorProp atan2(ErrorProp var0, ErrorProp var1, double var2) {
		ErrorProp var4 = new ErrorProp();
		ErrorProp var5 = var0.over(var1, var2);
		var4.value = Math.atan2(var0.value, var1.value);
		var4.error = Math.abs(var5.error / (1.0D + var5.value * var5.value));
		return var4;
	}

	public static ErrorProp asinh(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Fmath.asinh(var0.value);
		var1.error = Math.abs(var0.error / Math.sqrt(var0.value * var0.value + 1.0D));
		return var1;
	}

	public static ErrorProp acosh(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Fmath.acosh(var0.value);
		var1.error = Math.abs(var0.error / Math.sqrt(var0.value * var0.value - 1.0D));
		return var1;
	}

	public static ErrorProp atanh(ErrorProp var0) {
		ErrorProp var1 = new ErrorProp();
		var1.value = Fmath.atanh(var0.value);
		var1.error = Math.abs(var0.error / (1.0D - var0.value * var0.value));
		return var1;
	}

	public static ErrorProp zero() {
		ErrorProp var0 = new ErrorProp();
		var0.value = 0.0D;
		var0.error = 0.0D;
		return var0;
	}

	public static ErrorProp plusOne() {
		ErrorProp var0 = new ErrorProp();
		var0.value = 1.0D;
		var0.error = 0.0D;
		return var0;
	}

	public static ErrorProp minusOne() {
		ErrorProp var0 = new ErrorProp();
		var0.value = -1.0D;
		var0.error = 0.0D;
		return var0;
	}

	private static double hypotWithCov(double var0, double var2, double var4) {
		double var6 = 0.0D;
		double var8 = 0.0D;
		double var10 = 0.0D;
		if (var0 == 0.0D && var2 == 0.0D) {
			return 0.0D;
		} else {
			if (Math.abs(var0) > Math.abs(var2)) {
				var6 = Math.abs(var0);
				var8 = var2 / var0;
				var10 = Fmath.sign(var0);
			} else {
				var6 = Math.abs(var2);
				var8 = var0 / var2;
				var10 = Fmath.sign(var2);
			}

			return var6 * Math.sqrt(1.0D + var8 * (var8 + 2.0D * var4 * var10));
		}
	}

}
