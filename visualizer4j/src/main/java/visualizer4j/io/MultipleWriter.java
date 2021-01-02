package visualizer4j.io;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

public class MultipleWriter extends Writer {

	private Collection<Writer> destinations = new ArrayList<Writer>();

	public MultipleWriter() {
	}

	public MultipleWriter(Writer destination) {
		if (destination == null) {
			throw new NullPointerException("Sub writer cannot be null");
		}
		destinations.add(destination);
	}

	public MultipleWriter(Collection<Writer> destinations) {

		this.destinations = destinations;
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
	public void close() throws IOException {
		for (Writer wr : destinations) {
			wr.close();
		}
	}

	@Override
	public void flush() throws IOException {
		for (Writer wr : destinations) {
			wr.flush();
		}
	}

	// overriden methods :
	@Override
	public Writer append(char c) throws IOException {
		for (Writer wr : destinations) {
			wr.append(c);
		}
		return this;
	}

	@Override
	public Writer 	append(CharSequence csq) throws IOException {
		for (Writer wr : destinations) {
			wr.append(csq);
		}
		return this;
	}

	@Override
	public Writer 	append(CharSequence csq, int start, int end) throws IOException {
		for (Writer wr : destinations) {
			wr.append(csq, start, end);
		}
		return this;
	}

	@Override
	public void write(char[] buf) throws IOException {
		for (Writer wr : destinations) {
			wr.write(buf);
			wr.flush();
		}
	}

	@Override
	public void write(char[] buf, int off, int len) throws IOException {
		for (Writer wr : destinations) {
			wr.write(buf, off, len);
			wr.flush();
		}
	}


	@Override
	public void write(int c) throws IOException {
		for (Writer wr : destinations) {
			wr.write(c);
			wr.flush();
		}
	}

	@Override
	public void write(String s) throws IOException {
		for (Writer wr : destinations) {
			wr.write(s);
			wr.flush();
		}
	}

	@Override
	public void write(String s, int off, int len) throws IOException {
		for (Writer wr : destinations) {
			wr.write(s, off, len);
			wr.flush();
		}
	}

}
