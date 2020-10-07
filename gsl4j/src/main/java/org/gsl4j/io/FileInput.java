package org.gsl4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import org.gsl4j.complex.Complex;

public class FileInput implements AutoCloseable {
	protected String fileName = " ";
	protected String stemName = " ";
	protected String extension = " ";
	protected String pathName = " ";
	protected String dirPath = " ";
	protected String fullLine = " ";
	protected String fullLineT = " ";
	protected BufferedReader input = null;
	protected boolean testFullLine = false;
	protected boolean testFullLineT = false;
	protected boolean eof = false;
	protected boolean fileFound = true;
	protected boolean inputType = false;
	protected boolean charType = false;
	protected boolean space = true;
	protected boolean supressMessage = false;
	protected boolean wordMethod = false;
	protected String holdingWord = "HoldingWordMLPYGV";

	public FileInput() {
	}

	public FileInput(String var1) {
		this.pathName = var1;
		this.fileName = var1;
		int var2 = var1.indexOf("//");
		int var3 = var1.indexOf("\\");
		if (var2 != -1 || var3 != -1) {
			File var4 = new File(this.pathName);
			this.fileName = var4.getName();
			this.dirPath = var4.getParentFile().toString();
		}

		int var7 = this.fileName.indexOf(46);
		if (var7 == -1) {
			this.stemName = this.fileName;
		} else {
			this.stemName = this.fileName.substring(0, var7);
			this.extension = this.fileName.substring(var7);
		}

		try {
			this.input = new BufferedReader(new FileReader(this.pathName));
		} catch (FileNotFoundException var6) {
			this.fileFound = false;
		}

	}

	public FileInput(String var1, int var2) {
		this.pathName = var1;
		this.fileName = var1;
		int var3 = var1.indexOf("//");
		int var4 = var1.indexOf("\\");
		if (var3 != -1 || var4 != -1) {
			File var5 = new File(this.pathName);
			this.fileName = var5.getName();
			this.dirPath = var5.getParentFile().toString();
		}

		int var8 = this.fileName.indexOf(46);
		if (var8 == -1) {
			this.stemName = this.fileName;
		} else {
			this.stemName = this.fileName.substring(0, var8);
			this.extension = this.fileName.substring(var8);
		}

		try {
			this.input = new BufferedReader(new FileReader(this.pathName));
		} catch (FileNotFoundException var7) {
			if (var2 == 1) {
				System.out.println(var7);
			}

			this.fileFound = false;
		}

	}

	public String getPathName() {
		return this.pathName;
	}

	public String getFileName() {
		return this.fileName;
	}

	public String getStemName() {
		return this.stemName;
	}

	public String getExtension() {
		return this.extension;
	}

	public String getDirPath() {
		return this.dirPath;
	}

	public void removeSpaceAsDelimiter() {
		this.space = false;
	}

	public void restoreSpaceAsDelimiter() {
		this.space = true;
	}

	public final synchronized void copy(String var1) {
		FileOutput var2 = new FileOutput(var1);
		int var3 = this.numberOfLines();

		for (int var4 = 0; var4 < var3; ++var4) {
			String var5 = this.readLine();
			var2.println(var5);
		}

		var2.close();
	}

	public final synchronized double readDouble() {
		this.inputType = true;
		String var1 = "";
		double var2 = 0.0D;
		if (!this.testFullLineT) {
			this.enterLine();
		}

		var1 = this.nextWord();
		if (!this.eof) {
			var2 = Double.parseDouble(var1.trim());
		}

		return var2;
	}

	public final synchronized float readFloat() {
		this.inputType = true;
		String var1 = "";
		float var2 = 0.0F;
		if (!this.testFullLineT) {
			this.enterLine();
		}

		var1 = this.nextWord();
		if (!this.eof) {
			var2 = Float.parseFloat(var1.trim());
		}

		return var2;
	}

	public final synchronized BigDecimal readBigDecimal() {
		this.inputType = true;
		String var1 = "";
		BigDecimal var2 = null;
		if (!this.testFullLineT) {
			this.enterLine();
		}

		var1 = this.nextWord();
		if (!this.eof) {
			var2 = new BigDecimal(var1.trim());
		}

		return var2;
	}

	public final synchronized int readInt() {
		this.inputType = true;
		String var1 = "";
		int var2 = 0;
		if (!this.testFullLineT) {
			this.enterLine();
		}

		var1 = this.nextWord();
		if (!this.eof) {
			var2 = Integer.parseInt(var1.trim());
		}

		return var2;
	}

	public final synchronized long readLong() {
		this.inputType = true;
		String var1 = "";
		long var2 = 0L;
		if (!this.testFullLineT) {
			this.enterLine();
		}

		var1 = this.nextWord();
		if (!this.eof) {
			var2 = Long.parseLong(var1.trim());
		}

		return var2;
	}

	public final synchronized BigInteger readBigInteger() {
		this.inputType = true;
		String var1 = "";
		BigInteger var2 = null;
		if (!this.testFullLineT) {
			this.enterLine();
		}

		var1 = this.nextWord();
		if (!this.eof) {
			var2 = new BigInteger(var1.trim());
		}

		return var2;
	}

	public final synchronized short readShort() {
		this.inputType = true;
		String var1 = "";
		short var2 = 0;
		if (!this.testFullLineT) {
			this.enterLine();
		}

		var1 = this.nextWord();
		if (!this.eof) {
			var2 = Short.parseShort(var1.trim());
		}

		return var2;
	}

	public final synchronized byte readByte() {
		this.inputType = true;
		String var1 = "";
		byte var2 = 0;
		if (!this.testFullLineT) {
			this.enterLine();
		}

		var1 = this.nextWord();
		if (!this.eof) {
			var2 = Byte.parseByte(var1.trim());
		}

		return var2;
	}

	public final synchronized Complex readComplex() {
		this.inputType = true;
		String var1 = "";
		Complex var2 = null;
		if (!this.testFullLineT) {
			this.enterLine();
		}

		var1 = this.nextWord();
		if (!this.eof) {
			var2 = Complex.parseComplex(var1.trim());
		}

		return var2;
	}


	public final synchronized boolean readBoolean() {
		boolean var1 = true;
		String var2 = this.readWord();
		if (!var2.equals("false") && !var2.equals("FALSE")) {
			if (!var2.equals("true") && !var2.equals("TRUE")) {
				throw new IllegalArgumentException("attempted input neither true nor false");
			}

			var1 = true;
		} else {
			var1 = false;
		}

		return var1;
	}

	public final synchronized String readWord() {
		this.inputType = true;
		this.wordMethod = true;
		String var1 = "";
		if (!this.testFullLineT) {
			this.enterLine();
		}

		if (this.fullLine.equals("")) {
			var1 = " ";
		} else {
			var1 = this.nextWord();
			if (var1.equals("")) {
				var1 = " ";
			}

			if (var1.trim().equals(this.holdingWord)) {
				var1 = " ";
			}
		}

		return var1;
	}

	public final synchronized String readWordSaceOnly() {
		this.inputType = false;
		String var1 = "";
		if (!this.testFullLineT) {
			this.enterLine();
		}

		if (this.fullLine.equals("")) {
			var1 = "";
		} else {
			var1 = this.nextWord();
			if (var1.equals("")) {
				var1 = " ";
			}
		}

		return var1;
	}

	public final synchronized String readLine() {
		this.inputType = false;
		return this.readLineL();
	}

	protected final synchronized String readLineL() {
		String var1 = "";

		try {
			var1 = this.input.readLine();
		} catch (IOException var3) {
			System.out.println(var3);
		}

		if (var1 == null) {
			if (!this.supressMessage) {
				System.out.println("Attempt to read beyond the end of the file");
			}

			this.eof = true;
			var1 = "";
		}

		return var1;
	}

	public final synchronized char readChar() {
		this.inputType = true;
		this.charType = true;
		String var1 = "";
		char var2 = ' ';
		if (!this.testFullLine) {
			this.enterLine();
		}

		var1 = this.nextWord();
		if (var1.length() != 1) {
			throw new IllegalArgumentException("attempt to read more than one character into type char");
		} else {
			if (!this.eof) {
				var2 = var1.charAt(0);
			}

			return var2;
		}
	}

	@Override
	public final synchronized void close() {
		if (this.fileFound) {
			try {
				this.input.close();
			} catch (IOException var2) {
				System.out.println(var2);
			}
		}

	}

	public boolean eol() {
		boolean var1 = false;
		if (!this.testFullLineT) {
			var1 = true;
		}

		return var1;
	}

	public boolean eof() {
		return this.eof;
	}

	public boolean fileFound() {
		return this.fileFound;
	}

	protected final synchronized void enterLine() {
		this.fullLine = this.readLineL();
		if (this.wordMethod) {
			this.checkWordSpaces();
		}

		this.fullLineT = this.fullLine;
		if (!this.fullLine.equals("")) {
			for (int var2 = this.fullLineT.length() - 1; this.fullLineT.charAt(var2) == ' ' && var2 >= 0; --var2) {
				this.fullLineT = this.fullLineT.substring(0, var2);
			}
		}

	}

	public void checkWordSpaces() {
		int var1 = this.fullLine.length();
		if (this.wordMethod) {
			boolean var2 = false;
			ArrayList<Integer> var3 = new ArrayList<>();
			ArrayList<Integer> var4 = new ArrayList<>();

			int var5;
			for (var5 = 0; var5 < var1; ++var5) {
				var2 = false;
				if (this.fullLine.charAt(var5) == '\t') {
					var2 = true;
				}

				if (this.fullLine.charAt(var5) == ',') {
					var2 = true;
				}

				if (this.fullLine.charAt(var5) == ':') {
					var2 = true;
				}

				if (this.fullLine.charAt(var5) == ';') {
					var2 = true;
				}

				if (var2) {
					var3.add(new Integer(var5));
				}
			}

			var5 = var3.size();
			if (var5 > 0) {
				boolean var6 = true;
				boolean var7 = true;
				boolean var8 = false;
				int var9 = 0;

				int var12;
				while (var6) {
					var7 = true;
					var8 = false;
					int var17 = (Integer) var3.get(var9);
					if (var17 == 0) {
						var8 = true;
					} else if (var17 == var1 - 1) {
						var8 = true;
						var6 = false;
					} else if (var9 == var5 - 1) {
						var7 = true;

						for (var12 = var17 + 1; var12 < var1; ++var12) {
							if (this.fullLine.charAt(var12) != ' ') {
								var7 = false;
							}

							if (!var7) {
								break;
							}
						}

						if (var7) {
							var8 = true;
						}

						var6 = false;
					} else {
						int var18 = (Integer) var3.get(var9 + 1);
						var7 = true;

						for (var12 = var17 + 1; var12 < var18; ++var12) {
							if (this.fullLine.charAt(var12) != ' ') {
								var7 = false;
							}

							if (!var7) {
								break;
							}
						}

						if (var7) {
							var8 = true;
						}

						++var9;
					}

					if (var8) {
						var4.add(new Integer(var17 + 1));
					}

					if (var9 >= var5) {
						var6 = false;
					}
				}

				var12 = var4.size();
				if (var12 > 0) {
					for (int var13 = var12 - 1; var13 >= 0; --var13) {
						int var14 = (Integer) var4.get(var13);
						if (var14 >= var1 - 1) {
							this.fullLine = this.fullLine + this.holdingWord;
						} else if (var14 == 1) {
							this.fullLine = this.holdingWord + this.fullLine;
						} else {
							String var15 = this.fullLine.substring(0, var14);
							String var16 = this.fullLine.substring(var14);
							this.fullLine = var15 + this.holdingWord + var16;
						}
					}
				}
			}
		}

	}

	protected final synchronized String nextWord() {
		this.testFullLine = true;
		this.testFullLineT = true;
		String var1 = "";
		int var2 = -1;
		boolean var10 = true;
		boolean var11 = false;

		for (int var12 = this.fullLine.length(); var10; var11 = false) {
			--var12;
			if (this.fullLine.charAt(var12) == ' ') {
				var11 = true;
			}

			if (this.fullLine.charAt(var12) == '\t') {
				var11 = true;
			}

			if (this.inputType) {
				if (this.fullLine.charAt(var12) == ',') {
					var11 = true;
				}

				if (this.fullLine.charAt(var12) == ':') {
					var11 = true;
				}

				if (this.fullLine.charAt(var12) == ';') {
					var11 = true;
				}
			}

			if (var11) {
				this.fullLine = this.fullLine.substring(0, var12);
			} else {
				var10 = false;
			}
		}

		var10 = true;

		for (var11 = false; var10; var11 = false) {
			if (this.fullLine.charAt(0) == ' ') {
				var11 = true;
			}

			if (this.fullLine.charAt(0) == '\t') {
				var11 = true;
			}

			if (this.inputType) {
				if (this.fullLine.charAt(0) == ',') {
					var11 = true;
				}

				if (this.fullLine.charAt(0) == ':') {
					var11 = true;
				}

				if (this.fullLine.charAt(0) == ';') {
					var11 = true;
				}
			}

			if (var11) {
				this.fullLine = this.fullLine.substring(1);
			} else {
				var10 = false;
			}
		}

		int var13 = this.fullLine.length() + 10;
		if (this.space) {
			var2 = this.fullLine.indexOf(32);
		}

		int var17 = this.fullLine.indexOf(9);
		int var14 = var13;
		if (this.space) {
			if (var2 == -1 && var17 == -1) {
				var14 = var13;
			} else if (var2 == -1) {
				var14 = var17;
			} else if (var17 == -1) {
				var14 = var2;
			} else {
				var14 = Math.min(var2, var17);
			}
		} else if (var17 != -1) {
			var14 = var17;
		}

		if (this.inputType) {
			int var18 = this.fullLine.indexOf(44);
			int var19 = this.fullLine.indexOf(58);
			int var20 = this.fullLine.indexOf(59);
			int var15;
			if (var18 == -1 && var19 == -1) {
				var15 = var13;
			} else if (var18 == -1) {
				var15 = var19;
			} else if (var19 == -1) {
				var15 = var18;
			} else {
				var15 = Math.min(var18, var19);
			}

			int var16;
			if (var20 == -1) {
				var16 = var13;
			} else {
				var16 = var20;
			}

			var15 = Math.min(var15, var16);
			var14 = Math.min(var14, var15);
		}

		if (var14 == var13) {
			var1 = this.fullLine;
			this.fullLine = "";
			this.testFullLine = false;
		} else {
			var1 = this.fullLine.substring(0, var14);
			if (var14 + 1 > this.fullLine.length()) {
				this.fullLine = "";
				this.testFullLine = false;
			} else {
				this.fullLine = this.fullLine.substring(var14 + 1);
				if (this.fullLine.length() == 0) {
					this.testFullLine = false;
				}
			}
		}

		if (this.testFullLineT) {
			if (!this.testFullLine) {
				this.testFullLineT = false;
				this.fullLineT = "";
			} else if (var14 + 1 > this.fullLineT.length()) {
				this.fullLineT = "";
				this.testFullLineT = false;
			}
		}

		return var1;
	}

	protected final synchronized char nextCharInString() {
		this.testFullLine = true;
		char var3 = this.fullLine.charAt(0);
		this.fullLine = this.fullLine.substring(1);
		if (this.fullLine.length() == 0) {
			this.testFullLine = false;
		}

		if (this.testFullLineT) {
			this.fullLineT = this.fullLineT.substring(1);
			if (this.fullLineT.length() == 0) {
				this.testFullLineT = false;
			}
		}

		return var3;
	}

	public void setSupressMessageToTrue() {
		this.supressMessage = true;
	}

	public void setSupressMessageToFalse() {
		this.supressMessage = false;
	}

	public final synchronized int numberOfLines() {
		FileInput var1 = new FileInput(this.pathName);
		var1.setSupressMessageToTrue();
		boolean var2 = true;
		int var3 = 0;

		while (var2) {
			if (var1.eof) {
				var2 = false;
			} else if (var3 == Integer.MAX_VALUE) {
				System.out.println(
						"Class FileInput; method numberOfLines; The number of lines is greater than the maximum integer value, 2147483647");
				System.out.println("-1 returned");
				var3 = -1;
			} else {
				var1.readLine() ;
				++var3;
			}
		}

		var1.close();
		return var3;
	}

	public boolean deleteFile() {
		return deleteFile(this.pathName);
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
		return renameFile(this.pathName, var1);
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