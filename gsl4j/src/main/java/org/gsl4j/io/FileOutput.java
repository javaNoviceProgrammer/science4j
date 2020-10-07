package org.gsl4j.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.util.Date;

import org.gsl4j.complex.Complex;

public class FileOutput implements AutoCloseable {
	private String filename = "";
	private FileWriter fwoutput = null;
	private PrintWriter output = null;
	private boolean append = false;
	private char app = 'w';
	private boolean fileExists = true;

	public FileOutput(String var1, char var2) {
		this.filename = var1;
		this.app = var2;
		this.setFilenames(var1, var2);
	}

	public FileOutput(String var1, String var2) {
		this.filename = var1;
		this.app = var2.charAt(0);
		this.setFilenames(var1, this.app);
	}

	public FileOutput(String var1) {
		this.filename = var1;
		this.app = 'w';
		this.setFilenames(var1, this.app);
	}

	public FileOutput(File var1, char var2) {
		this.filename = var1;
		this.app = var2;
		this.setFilenames(var1.getAbsolutePath(), var2);
	}

	public FileOutput(File var1, String var2) {
		this.filename = var1;
		this.app = var2.charAt(0);
		this.setFilenames(var1.getAbsolutePath(), this.app);
	}

	public FileOutput(File var1) {
		this.filename = var1;
		this.app = 'w';
		this.setFilenames(var1.getAbsolutePath(), this.app);
	}

	private void setFilenames(String var1, char var2) {
		try(BufferedReader bf = new BufferedReader(new FileReader(var1))) {
			// nothing to do
		} catch (FileNotFoundException var13) {
			this.fileExists = false;
		} catch (IOException e) {
			this.fileExists = false;
		}

		if (this.app == 'n') {
			boolean var4 = true;
			int var5 = 0;
			String var7 = "";
			String var8 = "";
			int var9 = var1.indexOf(46);
			if (var9 != -1) {
				var7 = var7 + var1.substring(var9);
				var8 = var8 + var1.substring(0, var9);
			} else {
				var8 = var8 + var1;
			}

			while (var4) {
				++var5;
				var1 = var8 + String.valueOf(var5) + var7;

				try(BufferedReader bf = new BufferedReader(new FileReader(var1))) {
					// nothing to do
				} catch (FileNotFoundException var12) {
					var4 = false;
					this.filename = var1;
				} catch (IOException e) {
					var4 = false;
					this.filename = var1;
				}
			}
		}

		if (this.app == 'a') {
			this.append = true;
		} else {
			this.append = false;
		}

		try {
			this.fwoutput = new FileWriter(var1, this.append);
		} catch (IOException var11) {
			System.out.println(var11);
		}

		this.output = new PrintWriter(new BufferedWriter(this.fwoutput));
	}

	public String getFilename() {
		return this.filename;
	}

	public boolean checkFileAlreadyExists() {
		return this.fileExists;
	}

	public final synchronized void print(char var1) {
		this.output.print(var1);
	}

	public final synchronized void print(char var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
	}

	public final synchronized void print(String var1) {
		this.output.print(var1);
	}

	public final synchronized void print(String var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
	}

	public final synchronized void print(double var1) {
		this.output.print(var1);
	}

	public final synchronized void print(double var1, int var3) {
		String var4 = "";
		var4 = var4 + var1;
		var4 = setField(var4, var3);
		this.output.print(var4);
	}

	public final synchronized void print(float var1) {
		this.output.print(var1);
	}

	public final synchronized void print(float var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
	}

	public final synchronized void print(BigDecimal var1) {
		this.output.print(var1.toString());
	}

	public final synchronized void print(BigDecimal var1, int var2) {
		String var3 = "";
		var3 = var3 + var1.toString();
		var3 = setField(var3, var2);
		this.output.print(var3);
	}

	public final synchronized void print(BigInteger var1) {
		this.output.print(var1.toString());
	}

	public final synchronized void print(BigInteger var1, int var2) {
		String var3 = "";
		var3 = var3 + var1.toString();
		var3 = setField(var3, var2);
		this.output.print(var3);
	}

	public final synchronized void print(Complex var1) {
		this.output.print(var1.toString());
	}

	public final synchronized void print(Complex var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
	}



	public final synchronized void print(int var1) {
		this.output.print(var1);
	}

	public final synchronized void print(int var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
	}

	public final synchronized void print(long var1) {
		this.output.print(var1);
	}

	public final synchronized void print(long var1, int var3) {
		String var4 = "";
		var4 = var4 + var1;
		var4 = setField(var4, var3);
		this.output.print(var4);
	}

	public final synchronized void print(short var1) {
		this.output.print(var1);
	}

	public final synchronized void print(short var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
	}

	public final synchronized void print(byte var1) {
		this.output.print(var1);
	}

	public final synchronized void print(byte var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
	}

	public final synchronized void print(boolean var1) {
		this.output.print(var1);
	}

	public final synchronized void print(boolean var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
	}

	public final synchronized void print(double[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
		}

	}

	public final synchronized void print(float[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
		}

	}

	public final synchronized void print(BigDecimal[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
		}

	}

	public final synchronized void print(BigInteger[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
		}

	}

	public final synchronized void print(int[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
		}

	}

	public final synchronized void print(short[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
		}

	}

	public final synchronized void print(byte[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
		}

	}

	public final synchronized void print(char[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
		}

	}

	public final synchronized void print(boolean[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
		}

	}

	public final synchronized void print(String[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
		}

	}

	public final synchronized void print(Complex[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
		}

	}


	public final synchronized void print(double[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
		}

	}

	public final synchronized void print(float[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
		}

	}

	public final synchronized void print(BigDecimal[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
		}

	}

	public final synchronized void print(BigInteger[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
		}

	}

	public final synchronized void print(long[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
		}

	}

	public final synchronized void print(int[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
		}

	}

	public final synchronized void print(short[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
		}

	}

	public final synchronized void print(byte[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
		}

	}

	public final synchronized void print(char[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
		}

	}

	public final synchronized void print(boolean[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
		}

	}

	public final synchronized void print(String[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
		}

	}

	public final synchronized void print(Complex[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
		}

	}



	public final synchronized void dateAndTime() {
		Date var1 = new Date();
		String var2 = DateFormat.getDateInstance().format(var1);
		String var3 = DateFormat.getTimeInstance().format(var1);
		this.output.print("This file was created at ");
		this.output.print(var3);
		this.output.print(" on ");
		this.output.print(var2);
	}

	public final synchronized void dateAndTime(String var1) {
		Date var2 = new Date();
		String var3 = DateFormat.getDateInstance().format(var2);
		String var4 = DateFormat.getTimeInstance().format(var2);
		this.output.print("This file, " + var1 + ", was created at ");
		this.output.print(var4);
		this.output.print(" on ");
		this.output.print(var3);
	}

	public final synchronized void printsp(char var1) {
		this.output.print(var1);
		this.output.print(" ");
	}

	public final synchronized void printsp(String var1) {
		this.output.print(var1 + " ");
	}

	public final synchronized void printsp(double var1) {
		this.output.print(var1);
		this.output.print(" ");
	}

	public final synchronized void printsp(float var1) {
		this.output.print(var1);
		this.output.print(" ");
	}

	public final synchronized void printsp(BigDecimal var1) {
		this.output.print(var1.toString());
		this.output.print(" ");
	}

	public final synchronized void printsp(BigInteger var1) {
		this.output.print(var1.toString());
		this.output.print(" ");
	}

	public final synchronized void printsp(Complex var1) {
		this.output.print(var1.toString());
		this.output.print(" ");
	}



	public final synchronized void printsp(int var1) {
		this.output.print(var1);
		this.output.print(" ");
	}

	public final synchronized void printsp(long var1) {
		this.output.print(var1);
		this.output.print(" ");
	}

	public final synchronized void printsp(short var1) {
		this.output.print(var1);
		this.output.print(" ");
	}

	public final synchronized void printsp(byte var1) {
		this.output.print(var1);
		this.output.print(" ");
	}

	public final synchronized void printsp(boolean var1) {
		this.output.print(var1);
		this.output.print(" ");
	}

	public final synchronized void printsp() {
		this.output.print(" ");
	}

	public final synchronized void printsp(double[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(" ");
		}

	}

	public final synchronized void printsp(float[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(" ");
		}

	}

	public final synchronized void printsp(BigDecimal[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(" ");
		}

	}

	public final synchronized void printsp(BigInteger[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(" ");
		}

	}

	public final synchronized void printsp(long[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(" ");
		}

	}

	public final synchronized void printsp(int[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(" ");
		}

	}

	public final synchronized void printsp(char[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(" ");
		}

	}

	public final synchronized void printsp(short[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(" ");
		}

	}

	public final synchronized void printsp(byte[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(" ");
		}

	}

	public final synchronized void printsp(boolean[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(" ");
		}

	}

	public final synchronized void printsp(String[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(" ");
		}

	}

	public final synchronized void printsp(Complex[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(" ");
		}

	}




	public final synchronized void dateAndTimesp() {
		Date var1 = new Date();
		String var2 = DateFormat.getDateInstance().format(var1);
		String var3 = DateFormat.getTimeInstance().format(var1);
		this.output.print("This file was created at ");
		this.output.print(var3);
		this.output.print(" on ");
		this.output.print(var2);
		this.output.print(" ");
	}

	public final synchronized void dateAndTimesp(String var1) {
		Date var2 = new Date();
		String var3 = DateFormat.getDateInstance().format(var2);
		String var4 = DateFormat.getTimeInstance().format(var2);
		this.output.print("This file, " + var1 + ", was created at ");
		this.output.print(var4);
		this.output.print(" on ");
		this.output.print(var3);
		this.output.print(" ");
	}

	public final synchronized void println(char var1) {
		this.output.println(var1);
	}

	public final synchronized void println(String var1) {
		this.output.println(var1);
	}

	public final synchronized void println(double var1) {
		this.output.println(var1);
	}

	public final synchronized void println(float var1) {
		this.output.println(var1);
	}

	public final synchronized void println(BigDecimal var1) {
		this.output.println(var1.toString());
	}

	public final synchronized void println(BigInteger var1) {
		this.output.println(var1.toString());
	}

	public final synchronized void println(Complex var1) {
		this.output.println(var1.toString());
	}



	public final synchronized void println(int var1) {
		this.output.println(var1);
	}

	public final synchronized void println(long var1) {
		this.output.println(var1);
	}

	public final synchronized void println(short var1) {
		this.output.println(var1);
	}

	public final synchronized void println(byte var1) {
		this.output.println(var1);
	}

	public final synchronized void println(boolean var1) {
		this.output.println(var1);
	}

	public final synchronized void println() {
		this.output.println("");
	}

	public final synchronized void println(double[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.println(var1[var3]);
		}

	}

	public final synchronized void println(float[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.println(var1[var3]);
		}

	}

	public final synchronized void println(BigDecimal[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.println(var1[var3]);
		}

	}

	public final synchronized void println(BigInteger[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.println(var1[var3]);
		}

	}

	public final synchronized void println(long[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.println(var1[var3]);
		}

	}

	public final synchronized void println(int[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.println(var1[var3]);
		}

	}

	public final synchronized void println(short[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.println(var1[var3]);
		}

	}

	public final synchronized void println(byte[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.println(var1[var3]);
		}

	}

	public final synchronized void println(char[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.println(var1[var3]);
		}

	}

	public final synchronized void println(boolean[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.println(var1[var3]);
		}

	}

	public final synchronized void println(String[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.println(var1[var3]);
		}

	}

	public final synchronized void println(Complex[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.println(var1[var3]);
		}

	}



	public final synchronized void dateAndTimeln() {
		Date var1 = new Date();
		String var2 = DateFormat.getDateInstance().format(var1);
		String var3 = DateFormat.getTimeInstance().format(var1);
		this.output.print("This file, " + this.filename + ", was created at ");
		this.output.print(var3);
		this.output.print(" on ");
		this.output.println(var2);
	}

	public final synchronized void dateAndTimeln(String var1) {
		Date var2 = new Date();
		String var3 = DateFormat.getDateInstance().format(var2);
		String var4 = DateFormat.getTimeInstance().format(var2);
		this.output.print("This file, " + var1 + ", was created at ");
		this.output.print(var4);
		this.output.print(" on ");
		this.output.println(var3);
	}

	public final synchronized void printtab(char var1) {
		this.output.print(var1);
		this.output.print("\t");
	}

	public final synchronized void printtab(char var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
		this.output.print("\t");
	}

	public final synchronized void printtab(String var1) {
		this.output.print(var1 + "\t");
	}

	public final synchronized void printtab(String var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
		this.output.print("\t");
	}

	public final synchronized void printtab(double var1) {
		this.output.print(var1);
		this.output.print("\t");
	}

	public final synchronized void printtab(double var1, int var3) {
		String var4 = "";
		var4 = var4 + var1;
		var4 = setField(var4, var3);
		this.output.print(var4);
		this.output.print("\t");
	}

	public final synchronized void printtab(float var1) {
		this.output.print(var1);
		this.output.print("\t");
	}

	public final synchronized void printtab(float var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
		this.output.print("\t");
	}

	public final synchronized void printtab(BigDecimal var1) {
		this.output.print(var1.toString());
		this.output.print("\t");
	}

	public final synchronized void printtab(BigDecimal var1, int var2) {
		String var3 = "";
		var3 = var3 + var1.toString();
		var3 = setField(var3, var2);
		this.output.print(var3);
		this.output.print("\t");
	}

	public final synchronized void printtab(BigInteger var1) {
		this.output.print(var1.toString());
		this.output.print("\t");
	}

	public final synchronized void printtab(BigInteger var1, int var2) {
		String var3 = "";
		var3 = var3 + var1.toString();
		var3 = setField(var3, var2);
		this.output.print(var3);
		this.output.print("\t");
	}

	public final synchronized void printtab(Complex var1) {
		this.output.print(var1.toString());
		this.output.print("\t");
	}



	public final synchronized void printtab(Complex var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
		this.output.print("\t");
	}


	public final synchronized void printtab(int var1) {
		this.output.print(var1);
		this.output.print("\t");
	}

	public final synchronized void printtab(int var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
		this.output.print("\t");
	}

	public final synchronized void printtab(long var1) {
		this.output.print(var1);
		this.output.print("\t");
	}

	public final synchronized void printtab(long var1, int var3) {
		String var4 = "";
		var4 = var4 + var1;
		var4 = setField(var4, var3);
		this.output.print(var4);
		this.output.print("\t");
	}

	public final synchronized void printtab(short var1) {
		this.output.print(var1);
		this.output.print("\t");
	}

	public final synchronized void printtab(short var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
		this.output.print("\t");
	}

	public final synchronized void printtab(byte var1) {
		this.output.print(var1);
		this.output.print("\t");
	}

	public final synchronized void printtab(byte var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
		this.output.print("\t");
	}

	public final synchronized void printtab(boolean var1) {
		this.output.print(var1);
		this.output.print("\t");
	}

	public final synchronized void printtab(boolean var1, int var2) {
		String var3 = "";
		var3 = var3 + var1;
		var3 = setField(var3, var2);
		this.output.print(var3);
		this.output.print("\t");
	}

	public final synchronized void printtab() {
		this.output.print("\t");
	}

	public final synchronized void printtab(double[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(float[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(BigDecimal[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4].toString();
			var5 = setField(var5, var2);
			this.output.print(var5);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(BigInteger[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4].toString();
			var5 = setField(var5, var2);
			this.output.print(var5);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(long[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(int[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(short[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(byte[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(char[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(boolean[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(String[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(Complex[] var1, int var2) {
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = "";
			var5 = var5 + var1[var4];
			var5 = setField(var5, var2);
			this.output.print(var5);
			this.output.print("\t");
		}

	}



	public final synchronized void printtab(double[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(float[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(BigDecimal[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3].toString());
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(BigInteger[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3].toString());
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(long[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(int[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(char[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(boolean[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(String[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print("\t");
		}

	}

	public final synchronized void printtab(Complex[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print("\t");
		}

	}



	public final synchronized void dateAndTimetab() {
		Date var1 = new Date();
		String var2 = DateFormat.getDateInstance().format(var1);
		String var3 = DateFormat.getTimeInstance().format(var1);
		this.output.print("This file was created at ");
		this.output.print(var3);
		this.output.print(" on ");
		this.output.print(var2);
		this.output.print("\t");
	}

	public final synchronized void dateAndTimetab(String var1) {
		Date var2 = new Date();
		String var3 = DateFormat.getDateInstance().format(var2);
		String var4 = DateFormat.getTimeInstance().format(var2);
		this.output.print("This file, " + var1 + ", was created at ");
		this.output.print(var4);
		this.output.print(" on ");
		this.output.print(var3);
		this.output.print("\t");
	}

	public final synchronized void printcomma(char var1) {
		this.output.print(var1);
		this.output.print(",");
	}

	public final synchronized void printcomma(String var1) {
		this.output.print(var1 + ",");
	}

	public final synchronized void printcomma(double var1) {
		this.output.print(var1);
		this.output.print(",");
	}

	public final synchronized void printcomma(float var1) {
		this.output.print(var1);
		this.output.print(",");
	}

	public final synchronized void printcomma(BigDecimal var1) {
		this.output.print(var1.toString());
		this.output.print(",");
	}

	public final synchronized void printcomma(BigInteger var1) {
		this.output.print(var1.toString());
		this.output.print(",");
	}

	public final synchronized void printcomma(Complex var1) {
		this.output.print(var1.toString());
		this.output.print(",");
	}


	public final synchronized void printcomma(int var1) {
		this.output.print(var1);
		this.output.print(",");
	}

	public final synchronized void printcomma(long var1) {
		this.output.print(var1);
		this.output.print(",");
	}

	public final synchronized void printcomma(boolean var1) {
		this.output.print(var1);
		this.output.print(",");
	}

	public final synchronized void printcomma(short var1) {
		this.output.print(var1);
		this.output.print(",");
	}

	public final synchronized void printcomma(byte var1) {
		this.output.print(var1);
		this.output.print(",");
	}

	public final synchronized void printcomma() {
		this.output.print(",");
	}

	public final synchronized void printcomma(double[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2-1; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(",");
		}
		this.output.print(var1[var2-1]);
	}

	public final synchronized void printcomma(float[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(",");
		}

	}

	public final synchronized void printcomma(BigDecimal[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3].toString());
			this.output.print(",");
		}

	}

	public final synchronized void printcomma(BigInteger[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3].toString());
			this.output.print(",");
		}

	}

	public final synchronized void printcomma(long[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(",");
		}

	}

	public final synchronized void printcomma(int[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(",");
		}

	}

	public final synchronized void printcomma(short[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(",");
		}

	}

	public final synchronized void printcomma(byte[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(",");
		}

	}

	public final synchronized void printcomma(char[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(",");
		}

	}

	public final synchronized void printcomma(boolean[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(",");
		}

	}

	public final synchronized void printcomma(String[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(",");
		}

	}

	public final synchronized void printcomma(Complex[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(",");
		}

	}



	public final synchronized void dateAndTimecomma() {
		Date var1 = new Date();
		String var2 = DateFormat.getDateInstance().format(var1);
		String var3 = DateFormat.getTimeInstance().format(var1);
		this.output.print("This file was created at ");
		this.output.print(var3);
		this.output.print(" on ");
		this.output.print(var2);
		this.output.print(",");
	}

	public final synchronized void dateAndTimecomma(String var1) {
		Date var2 = new Date();
		String var3 = DateFormat.getDateInstance().format(var2);
		String var4 = DateFormat.getTimeInstance().format(var2);
		this.output.print("This file, " + var1 + ", was created at ");
		this.output.print(var4);
		this.output.print(" on ");
		this.output.print(var3);
		this.output.print(",");
	}

	public final synchronized void printsc(char var1) {
		this.output.print(var1);
		this.output.print(";");
	}

	public final synchronized void printsc(String var1) {
		this.output.print(var1 + ";");
	}

	public final synchronized void printsc(double var1) {
		this.output.print(var1);
		this.output.print(";");
	}

	public final synchronized void printsc(float var1) {
		this.output.print(var1);
		this.output.print(";");
	}

	public final synchronized void printsc(BigDecimal var1) {
		this.output.print(var1.toString());
		this.output.print(";");
	}

	public final synchronized void printsc(BigInteger var1) {
		this.output.print(var1.toString());
		this.output.print(";");
	}

	public final synchronized void printsc(Complex var1) {
		this.output.print(var1.toString());
		this.output.print(";");
	}


	public final synchronized void printsc(int var1) {
		this.output.print(var1);
		this.output.print(";");
	}

	public final synchronized void printsc(long var1) {
		this.output.print(var1);
		this.output.print(";");
	}

	public final synchronized void printsc(short var1) {
		this.output.print(var1);
		this.output.print(";");
	}

	public final synchronized void printsc(byte var1) {
		this.output.print(var1);
		this.output.print(";");
	}

	public final synchronized void printsc(boolean var1) {
		this.output.print(var1);
		this.output.print(";");
	}

	public final synchronized void printsc() {
		this.output.print(";");
	}

	public final synchronized void printsc(double[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(";");
		}

	}

	public final synchronized void printsc(float[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(";");
		}

	}

	public final synchronized void printsc(BigDecimal[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3].toString());
			this.output.print(";");
		}

	}

	public final synchronized void printsc(BigInteger[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3].toString());
			this.output.print(";");
		}

	}

	public final synchronized void printsc(long[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(";");
		}

	}

	public final synchronized void printsc(short[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(";");
		}

	}

	public final synchronized void printsc(byte[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(";");
		}

	}

	public final synchronized void printsc(int[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(";");
		}

	}

	public final synchronized void printsc(char[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(";");
		}

	}

	public final synchronized void printsc(boolean[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(";");
		}

	}

	public final synchronized void printsc(String[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(";");
		}

	}

	public final synchronized void printsc(Complex[] var1) {
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			this.output.print(var1[var3]);
			this.output.print(";");
		}

	}


	public final synchronized void dateAndTimesc() {
		Date var1 = new Date();
		String var2 = DateFormat.getDateInstance().format(var1);
		String var3 = DateFormat.getTimeInstance().format(var1);
		this.output.print("This file was created at ");
		this.output.print(var3);
		this.output.print(" on ");
		this.output.print(var2);
		this.output.print(";");
	}

	public final synchronized void dateAndTimesc(String var1) {
		Date var2 = new Date();
		String var3 = DateFormat.getDateInstance().format(var2);
		String var4 = DateFormat.getTimeInstance().format(var2);
		this.output.print("This file, " + var1 + ", was created at ");
		this.output.print(var4);
		this.output.print(" on ");
		this.output.print(var3);
		this.output.print(";");
	}

	@Override
	public final synchronized void close() {
		this.output.close();
	}

	public static void printArrayToText(double[][] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, double[][] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var5 = 0; var5 < var3; ++var5) {
			int var7 = var1[var5].length;

			for (int var6 = 0; var6 < var7; ++var6) {
				var2.printtab(var1[var5][var6]);
			}

			var2.println();
		}

		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(double[] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, double[] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			var2.printtab(var1[var4]);
		}

		var2.println();
		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(float[][] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, float[][] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var5 = 0; var5 < var3; ++var5) {
			int var7 = var1[var5].length;

			for (int var6 = 0; var6 < var7; ++var6) {
				var2.printtab(var1[var5][var6]);
			}

			var2.println();
		}

		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(float[] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, float[] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			var2.printtab(var1[var4]);
		}

		var2.println();
		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(BigDecimal[][] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, BigDecimal[][] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var5 = 0; var5 < var3; ++var5) {
			int var7 = var1[var5].length;

			for (int var6 = 0; var6 < var7; ++var6) {
				var2.printtab(var1[var5][var6].toString());
			}

			var2.println();
		}

		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(BigInteger[][] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, BigInteger[][] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var5 = 0; var5 < var3; ++var5) {
			int var7 = var1[var5].length;

			for (int var6 = 0; var6 < var7; ++var6) {
				var2.printtab(var1[var5][var6].toString());
			}

			var2.println();
		}

		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(BigDecimal[] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, BigDecimal[] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			var2.printtab(var1[var4].toString());
		}

		var2.println();
		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(BigInteger[] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, BigInteger[] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			var2.printtab(var1[var4].toString());
		}

		var2.println();
		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(int[][] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, int[][] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var5 = 0; var5 < var3; ++var5) {
			int var7 = var1[var5].length;

			for (int var6 = 0; var6 < var7; ++var6) {
				var2.printtab(var1[var5][var6]);
			}

			var2.println();
		}

		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(int[] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, int[] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			var2.printtab(var1[var4]);
		}

		var2.println();
		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(long[][] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, long[][] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var5 = 0; var5 < var3; ++var5) {
			int var7 = var1[var5].length;

			for (int var6 = 0; var6 < var7; ++var6) {
				var2.printtab(var1[var5][var6]);
			}

			var2.println();
		}

		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(long[] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, long[] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			var2.printtab(var1[var4]);
		}

		var2.println();
		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(short[][] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, short[][] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var5 = 0; var5 < var3; ++var5) {
			int var7 = var1[var5].length;

			for (int var6 = 0; var6 < var7; ++var6) {
				var2.printtab(var1[var5][var6]);
			}

			var2.println();
		}

		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(short[] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, short[] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			var2.printtab(var1[var4]);
		}

		var2.println();
		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(byte[][] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, byte[][] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var5 = 0; var5 < var3; ++var5) {
			int var7 = var1[var5].length;

			for (int var6 = 0; var6 < var7; ++var6) {
				var2.printtab(var1[var5][var6]);
			}

			var2.println();
		}

		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(byte[] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, byte[] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			var2.printtab(var1[var4]);
		}

		var2.println();
		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(String[][] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, String[][] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var5 = 0; var5 < var3; ++var5) {
			int var7 = var1[var5].length;

			for (int var6 = 0; var6 < var7; ++var6) {
				var2.printtab(var1[var5][var6]);
			}

			var2.println();
		}

		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(String[] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, String[] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			var2.printtab(var1[var4]);
		}

		var2.println();
		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(char[][] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, char[][] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var5 = 0; var5 < var3; ++var5) {
			int var7 = var1[var5].length;

			for (int var6 = 0; var6 < var7; ++var6) {
				var2.printtab(var1[var5][var6]);
			}

			var2.println();
		}

		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(char[] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, char[] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			var2.printtab(var1[var4]);
		}

		var2.println();
		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(boolean[][] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, boolean[][] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var5 = 0; var5 < var3; ++var5) {
			int var7 = var1[var5].length;

			for (int var6 = 0; var6 < var7; ++var6) {
				var2.printtab(var1[var5][var6]);
			}

			var2.println();
		}

		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(boolean[] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, boolean[] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			var2.printtab(var1[var4]);
		}

		var2.println();
		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(Complex[][] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, Complex[][] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var5 = 0; var5 < var3; ++var5) {
			int var7 = var1[var5].length;

			for (int var6 = 0; var6 < var7; ++var6) {
				var2.printtab(var1[var5][var6]);
			}

			var2.println();
		}

		var2.println("End of file.");
		var2.close();
	}

	public static void printArrayToText(Complex[] var0) {
		String var1 = "ArrayToText.txt";
		printArrayToText(var1, var0);
	}

	public static void printArrayToText(String var0, Complex[] var1) {
		FileOutput var2 = new FileOutput(var0, 'n');
		var2.dateAndTimeln(var0);
		int var3 = var1.length;

		for (int var4 = 0; var4 < var3; ++var4) {
			var2.printtab(var1[var4]);
		}

		var2.println();
		var2.println("End of file.");
		var2.close();
	}

	private static String setField(String var0, int var1) {
		char var2 = ' ';
		int var3 = var0.length();
		if (var1 > var3) {
			for (int var4 = var3 + 1; var4 <= var1; ++var4) {
				var0 = var0 + var2;
			}
		}

		return var0;
	}

	public static boolean deleteFile(String var0) {
		boolean var1 = true;
		File var2 = new File(var0);
		if (!var2.exists()) {
			System.out.println("Method deleteFile: no file or directory of the name " + var0 + " found");
			var1 = false;
		}

		if (!var2.canWrite()) {
			System.out.println("Method deleteFile: " + var0 + " is write protected and cannot be deleted");
			var1 = false;
		}

		if (var2.isDirectory()) {
			String[] var3 = var2.list();
			if (var3.length > 0) {
				System.out.println(
						"Method deleteFile: " + var0 + " is a directory which is not empty; no action was taken");
				var1 = false;
			}
		}

		boolean var4 = var2.delete();
		if (!var4) {
			System.out.println("Method deleteFile: deletion of the file " + var0 + " failed");
		}

		return var1;
	}

	public int renameFile(String var1) {
		return renameFile(this.filename, var1);
	}

	public static int renameFile(String var0, String var1) {
		boolean var2 = true;
		byte var3 = 0;
		File var4 = new File(var0);
		if (!var4.exists()) {
			System.out.println("file " + var0 + " does not exist");
			return 1;
		} else {
			File var5 = new File(var1);
			if (var5.exists()) {
				var2 = deleteFile(var1);
				if (!var2) {
					System.out.println("Class FileInput: method renameFile failed to delete the file " + var1);
					return 2;
				}
			}

			var2 = var4.renameTo(var5);
			if (!var2) {
				System.out.println(
						"Class FileInput: method renameFile failed to rename the file " + var0 + " to " + var1);
				return 3;
			} else {
				return var3;
			}
		}
	}
}
