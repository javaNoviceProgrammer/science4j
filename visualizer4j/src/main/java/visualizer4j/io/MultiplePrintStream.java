package visualizer4j.io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

public class MultiplePrintStream extends PrintStream {

	private static class LocalDummyOutputStream extends OutputStream {
		@Override
		public void write(int b) {
		}
	}

	private Collection<Writer> destinations = new ArrayList<Writer>();
	private String lineSeparator = System.getProperty("line.separator");
	private boolean withSdtOut = false;
	private PrintStream defOut = null;

	public MultiplePrintStream(boolean withSdtOut) {
		super(new LocalDummyOutputStream());
		this.withSdtOut = withSdtOut;
		if (withSdtOut) {
			defOut = System.out;
		}
	}

	public MultiplePrintStream(Writer destination, boolean withSdtOut) {
		super(new LocalDummyOutputStream());
		if (destination == null) {
			throw new NullPointerException("Sub writer cannot be null");
		}
		destinations.add(destination);
		this.withSdtOut = withSdtOut;
		if (withSdtOut) {
			defOut = System.out;
		}
	}

	public MultiplePrintStream(Collection<Writer> destinations, boolean withSdtOut) {
		super(new LocalDummyOutputStream());
		this.destinations = destinations;
		this.withSdtOut = withSdtOut;
		if (withSdtOut) {
			defOut = System.out;
		}
	}

	public void addDestination(Writer wr) {
		destinations.add(wr);
	}

	public Object removeDestination(Writer we) {
		return destinations.remove(we);
	}

	public Collection<Writer> getDestinations() {
		Collection<Writer> backCopy = new ArrayList<Writer>();
		backCopy.addAll(destinations);
		return backCopy;
	}

	@Override
	public PrintStream 	append(char c) {
		print(c);
		return this;
	}
	@Override
	public PrintStream append(CharSequence csq) {
		print(csq.toString());
		return this;
	}
	@Override
	public PrintStream 	append(CharSequence csq, int start, int end) {
		print(csq.toString().substring(start,end));
		return this;
	}

	@Override
	public void close() {
		try {
			for (Writer wr : destinations) {
				wr.close();
			}
		}
		catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void flush() {
		try {
			for (Writer wr : destinations) {
				wr.flush();
			}
		}
		catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public PrintStream format(Locale l, String format, Object... args) {
		print(String.format(l,format,args));
		return this;
	}
	@Override
	public PrintStream 	format(String format, Object... args) {
		print(String.format(format, args));
		return this;
	}
	@Override
	public void print(boolean b) {
		print(""+b);
	}
	@Override
	public void print(char c) {
		print(""+c);
	}
	@Override
	public void print(char[] s) {
		print(Arrays.toString(s));
	}
	@Override
	public void print(double d) {
		print(d+"");
	}
	@Override
	public void print(float f) {
		print(f+"");
	}
	@Override
	public void print(int i) {
		print(i+"");
	}
	@Override
	public void print(long l) {
		print(l+"");
	}
	@Override
	public void print(Object obj) {
		print(obj.toString());
	}
	@Override
	public void print(String s) {
		try {
			for (Writer wr : destinations) {
				wr.append(s);
			}
			if (withSdtOut) {
				defOut.print(s);
			}
		}catch (Exception e) {}
	}

	@Override
	public PrintStream printf(Locale l, String format, Object... args) {
		print(String.format(l,format, args));
		return this;
	}
	@Override
	public PrintStream 	printf(String format, Object... args) {
		print(String.format(format, args));
		return this;
	}
	@Override
	public void println() {
		print(lineSeparator);
	}

	@Override
	public void println(boolean x) {
		println(x+"");
	}

	@Override
	public void println(char x) {
		println(x+"");
	}
	@Override
	public void println(char[] x) {
		println(Arrays.toString(x));
	}
	@Override
	public void println(double x) {
		println(x+"");
	}
	@Override
	public void println(float x) {
		println(x+"");
	}
	@Override
	public void println(int x) {
		println(x+"");
	}

	@Override
	public void println(long x) {
		println(x+"");
	}

	@Override
	public void	println(Object x) {
		println(x.toString());
	}
	@Override
	public void	println(String x) {
		print(x+lineSeparator);
	}

	@Override
	public void write(byte[] buf, int off, int len) {
		for (int i = off ; i < len ; i++) {
			print((char)buf[i]);
		}
	}
	@Override
	public void write(int b) {
		print((char)(b & 0xff));
	}
}
