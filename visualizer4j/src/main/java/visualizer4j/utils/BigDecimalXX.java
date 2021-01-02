package visualizer4j.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

@SuppressWarnings("serial")
public class BigDecimalXX extends java.math.BigDecimal {

	private static MathContext context = null;

	static {
		String precision = System.getProperty("ch.epfl.general_libraries.utils.precision");
		if (precision != null) {
			int pre = Integer.parseInt(precision);
			if (pre == 32) {
				context = MathContext.DECIMAL32;
			} else if (pre == 64) {
				context = MathContext.DECIMAL64;
			} else if (pre == 128) {
				context = MathContext.DECIMAL128;
			} else {
				context = MathContext.DECIMAL64;
				throw new IllegalStateException(
						"Wrong property \"ch.epfl.general_libraries.utils.precision\""+
				": must be set to 32, 64 or 128.");
			}
		} else {
			context = MathContext.DECIMAL64;
		}
	}


	public BigDecimalXX(BigInteger val) {
		super(val, context);
	}
	public BigDecimalXX(BigInteger unscaledVal, int scale) {
		super(unscaledVal, scale, context);
	}
	public BigDecimalXX(char[] in) {
		super(in, context);
	}

	public BigDecimalXX(char[] in, int offset, int len) {
		super(in, offset, len, context);
	}

	public BigDecimalXX(double val) {
		super(val, context);
	}

	public BigDecimalXX(int val) {
		super(val, context);
	}

	public BigDecimalXX(long val) {
		super(val, context);
	}

	public BigDecimalXX(String val) {
		super(val, context);
	}

	public static BigDecimal factorial( int n ){
		if( n <= 1 ){
			return new BigDecimalXX("1");
		}else{
			BigDecimal u = new BigDecimalXX(n);
			return u.multiply(factorial( n - 1 ));
		}
	}

	public static BigDecimal factorial( int n, MathContext mc) {
		if( n <= 1 ){
			return new BigDecimal("1",mc);
		}else{
			BigDecimal u = new BigDecimal(n);
			return u.multiply(factorial( n - 1 ),mc);
		}
	}

	public MathContext getContext() {
		return context;
	}

	@Override
	public BigDecimal pow(int n) {
		return super.pow(n, context);
	}



}

