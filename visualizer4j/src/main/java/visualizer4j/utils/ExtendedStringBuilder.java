package visualizer4j.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExtendedStringBuilder {
	
	private StringBuilder sb;
	
	public ExtendedStringBuilder() {
		sb = new StringBuilder();
	}
	
	public void append(String s) {
		sb.append(s);
	}
	
	public void append(char c) {
		sb.append(c);
	}
	
	public void append(int i) {
		sb.append(i);
	}
	
	public void append(boolean b) {
		sb.append(b);
	}
	
	public void append(double d) {
		sb.append(d);
	}
	
	public void append(long l) {
		sb.append(l);
	}
	
	public void append(Object o) {
		sb.append(o);
	}
	
	public void appendln(String s) {
		sb.append(s);
		sb.append("\n");
	}
	
	public void appendln() {
		sb.append("\n");
	}	
	
	public void appendln(Object o) {
		sb.append(o);
		sb.append("\n");
	}
	
	public void appendln(int i) {
		sb.append(i);
		sb.append("\n");
	}

	public void appendln(double d) {
		sb.append(d);
		sb.append("\n");
	}
	
	public String toString() {
		return sb.toString();
	}

	public void appendFile(String string) {
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(string);
			br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
		}
		catch (IOException e) {
			throw new IllegalStateException(e);
		}
		finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw new IllegalStateException(e);
				}
			}
		}
	}
	
}
