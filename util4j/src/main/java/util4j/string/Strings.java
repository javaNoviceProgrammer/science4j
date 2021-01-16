package util4j.string;

import java.util.ArrayList;

public class Strings {

	private String enteredString = null;
	private int nEntered = 0;
	private String editedString = null;
	private int nEdited = 0;
	private boolean[] whiteSpaces = null;
	private String[] tokens = null;
	private int nTokens = 0;
	private int[] tokenInitialIndices = null;
	private int[] tokenFinalIndices = null;
	private boolean tokensDone = false;
	private String[] principalTokens = null;
	private int nPrincipalTokens = 0;
	private int[] principalTokenInitialIndices = null;
	private int[] principalTokenFinalIndices = null;
	private String[] symbolsA = new String[] { "À", "Á", "Â", "Ã", "Ä", "Å", "Æ", "Ç", "È", "É", "Ê", "Ë", "Ì", "Í",
			"Î", "Ï", "Ð", "Ñ", "Ò", "Ó", "Ô", "Õ", "Ö", "Ø", "Ù", "Ú", "Û", "Ü", "Ý", "Þ", "ß", "à", "á", "â", "ã",
			"ä", "å", "æ", "ç", "è", "é", "ê", "ë", "ì", "í", "î", "ï", "ð", "ñ", "ò", "ó", "ô", "õ", "ö", "ø", "ù",
			"ú", "û", "ü", "ý", "þ", "ÿ", "Œ", "œ", "Š", "š", "Ÿ", "ƒ" };
	private String[] replSymbolsA = new String[] { "A", "A", "A", "A", "A", "A", "AE", "C", "E", "E", "E", "E", "I",
			"I", "I", "I", "DH", "N", "O", "O", "O", "O", "O", "O", "U", "U", "U", "U", "Y", "TH", "ss", "a", "a", "a",
			"a", "a", "a", "ae", "c", "e", "e", "e", "e", "i", "i", "i", "i", "dh", "n", "o", "o", "o", "o", "o", "o",
			"u", "u", "u", "u", "y", "th", "y", "OE", "oe", "s", "s", "y", "f" };
	private String[] htmlNamesA = new String[] { "&Agrave;", "&Aacute;", "&Acirc;", "&Atilde;", "&Auml;", "&Aring;",
			"&AElig;", "&Ccedil;", "&Egrave;", "&Eacute;", "&Ecirc;", "&Euml;", "&Igrave;", "&Iacute;", "&Icirc;",
			"&Iuml;", "&ETH;", "&Ntilde;", "&Ograve;", "&Oacute;", "&Ocirc;", "&Otilde;", "&Ouml;", "&Oslash;",
			"&Ugrave;", "&Uacute;", "&Ucirc;", "&Uuml;", "&Yacute;", "&THORN;", "&szlig;", "&agrave;", "&aacute;",
			"&acirc;", "&atilde;", "&auml;", "&aring;", "&aelig;", "&ccedil;", "&egrave;", "&eacute;", "&ecirc;",
			"&euml;", "&igrave;", "&iacute;", "&icirc;", "&iuml;", "&eth;", "&ntilde;", "&ograve;", "&oacute;",
			"&ocirc;", "&otilde;", "&ouml;", "&oslash;", "&ugrave;", "&uacute;", "&ucirc;", "&uuml;", "&yacute;",
			"&thorn;", "&yuml;", "&OE;", "&oe;", "&Scaron;", "&scaron;", "&Ydia;", "&fhook;" };
	private String[] htmlNumbersA = new String[] { "&#192;", "&#193;", "&#194;", "&#195;", "&#196;", "&#197;", "&#198;",
			"&#199;", "&#200;", "&#201;", "&#202;", "&#203;", "&#204;", "&#205;", "&#206;", "&#207;", "&#208;",
			"&#209;", "&#210;", "&#211;", "&#212;", "&#213;", "&#214;", "&#216;", "&#217;", "&#218;", "&#219;",
			"&#220;", "&#221;", "&#222;", "&#223;", "&#224;", "&#225;", "&#226;", "&#227;", "&#228;", "&#229;",
			"&#230;", "&#231;", "&#232;", "&#233;", "&#234;", "&#235;", "&#236;", "&#237;", "&#238;", "&#239;",
			"&#240;", "&#241;", "&#242;", "&#243;", "&#244;", "&#245;", "&#246;", "&#248;", "&#249;", "&#250;",
			"&#251;", "&#252;", "&#253;", "&#254;", "&#255;", "&#338;", "&#339;", "&#352;", "&#353;", "&#376;",
			"&#402;" };
	private int[] decNumbersA = new int[] { 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206,
			207, 208, 209, 210, 211, 212, 213, 214, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228,
			229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 248, 249, 250,
			251, 252, 253, 254, 255, 338, 339, 352, 353, 376, 402 };
	private int nSymbolsA;
	private String[] symbolQ;
	private String[] htmlNamesQ;
	private String[] htmlNumbersQ;
	private int[] decNumbersQ;
	private int nSymbolsQ;
	private String[] htmlNumbersSQ;
	private String[] symbolsD;
	private String[] htmlNamesD;
	private String[] htmlNumbersD;
	private int[] decNumbersD;
	private int nSymbolsD;
	private String[] articles;
	private int nArticles;
	private String[] prepositions;
	private int nPrepositions;
	private String[] conjunctions;
	private int nConjunctions;

	public Strings(String var1) {
		this.nSymbolsA = this.symbolsA.length;
		this.symbolQ = new String[] { "\"", "'", "‚", "„", "‹", "‘", "’", "“", "”", "›", "«", "»", "‘", "’", "‚", "“",
				"”", "„" };
		this.htmlNamesQ = new String[] { "&quot;", "&none", "&sbquo;", "&dbquo;", "&lsaquo;", "&lsquo;", "&rsquo;",
				"&ldquo;", "&rdquo;", "&rsaquo;", "&laquo;", "&raquo;", "&none;", "&none;", "&none;", "&none;",
				"&none;", "&none;" };
		this.htmlNumbersQ = new String[] { "&#34;", "&#39;", "&#130;", "&#132;", "&#139;", "&#145;", "&#146;", "&#147;",
				"&#148;", "&#155;", "&#171;", "&#187;", "&#8216;", "&#8217;", "&#8218;", "&#8220;", "&#8221;",
				"&#8222;" };
		this.decNumbersQ = new int[] { 34, 39, 130, 132, 139, 145, 146, 147, 148, 155, 171, 187, 8216, 8217, 8218, 8220,
				8221, 8222 };
		this.nSymbolsQ = this.symbolQ.length;
		this.htmlNumbersSQ = new String[] { "&#39;", "&#145;", "&#146;", "&#8216;", "&#8217;" };
		this.symbolsD = new String[] { "-", "–", "—", "–", "—", "–", "—" };
		this.htmlNamesD = new String[] { "&none;", "&ndash;", "&mdash;", "–", "&oline;", "&none;", "&none;" };
		this.htmlNumbersD = new String[] { "&#45;", "&#150;", "&#151;", "&#8213;", "&#8254;", "&#8211;", "&#8212;" };
		this.decNumbersD = new int[] { 45, 150, 151, 8213, 8254, 8211, 8212 };
		this.nSymbolsD = this.symbolsD.length;
		this.articles = new String[] { "a", "an", "the" };
		this.nArticles = this.articles.length;
		this.prepositions = new String[] { "about", "above", "across", "after", "against", "along", "among", "around",
				"at", "before", "behind", "below", "beneath", "beside", "between", "beyond", "but", "by", "despite",
				"down", "during", "except", "for", "from", "in", "inside", "into", "like", "near", "of", "off", "on",
				"onto", "out", "outside", "over", "past", "since", "through", "throughout", "till", "to", "toward",
				"under", "underneath", "until", "up", "upon", "with", "within", "without" };
		this.nPrepositions = this.prepositions.length;
		this.conjunctions = new String[] { "and", "&amp", "&", "but", "or", "nor", "for", "so", "yet", "not", "only",
				"also", "either", "neither", "although", "because", "since", "unless", "until", "while" };
		this.nConjunctions = this.conjunctions.length;
		this.enteredString = var1;
		this.editedString = var1;
		this.nEntered = var1.length();
		this.nEdited = this.nEntered;
	}

	public Strings() {
		this.nSymbolsA = this.symbolsA.length;
		this.symbolQ = new String[] { "\"", "'", "‚", "„", "‹", "‘", "’", "“", "”", "›", "«", "»", "‘", "’", "‚", "“",
				"”", "„" };
		this.htmlNamesQ = new String[] { "&quot;", "&none", "&sbquo;", "&dbquo;", "&lsaquo;", "&lsquo;", "&rsquo;",
				"&ldquo;", "&rdquo;", "&rsaquo;", "&laquo;", "&raquo;", "&none;", "&none;", "&none;", "&none;",
				"&none;", "&none;" };
		this.htmlNumbersQ = new String[] { "&#34;", "&#39;", "&#130;", "&#132;", "&#139;", "&#145;", "&#146;", "&#147;",
				"&#148;", "&#155;", "&#171;", "&#187;", "&#8216;", "&#8217;", "&#8218;", "&#8220;", "&#8221;",
				"&#8222;" };
		this.decNumbersQ = new int[] { 34, 39, 130, 132, 139, 145, 146, 147, 148, 155, 171, 187, 8216, 8217, 8218, 8220,
				8221, 8222 };
		this.nSymbolsQ = this.symbolQ.length;
		this.htmlNumbersSQ = new String[] { "&#39;", "&#145;", "&#146;", "&#8216;", "&#8217;" };
		this.symbolsD = new String[] { "-", "–", "—", "–", "—", "–", "—" };
		this.htmlNamesD = new String[] { "&none;", "&ndash;", "&mdash;", "–", "&oline;", "&none;", "&none;" };
		this.htmlNumbersD = new String[] { "&#45;", "&#150;", "&#151;", "&#8213;", "&#8254;", "&#8211;", "&#8212;" };
		this.decNumbersD = new int[] { 45, 150, 151, 8213, 8254, 8211, 8212 };
		this.nSymbolsD = this.symbolsD.length;
		this.articles = new String[] { "a", "an", "the" };
		this.nArticles = this.articles.length;
		this.prepositions = new String[] { "about", "above", "across", "after", "against", "along", "among", "around",
				"at", "before", "behind", "below", "beneath", "beside", "between", "beyond", "but", "by", "despite",
				"down", "during", "except", "for", "from", "in", "inside", "into", "like", "near", "of", "off", "on",
				"onto", "out", "outside", "over", "past", "since", "through", "throughout", "till", "to", "toward",
				"under", "underneath", "until", "up", "upon", "with", "within", "without" };
		this.nPrepositions = this.prepositions.length;
		this.conjunctions = new String[] { "and", "&amp", "&", "but", "or", "nor", "for", "so", "yet", "not", "only",
				"also", "either", "neither", "although", "because", "since", "unless", "until", "while" };
		this.nConjunctions = this.conjunctions.length;
	}

	public String getEditedString() {
		return this.editedString;
	}

	public int getEditedStringLength() {
		return this.nEdited;
	}

	public String getEnteredString() {
		return this.enteredString;
	}

	public int getEnteredStringLength() {
		return this.nEntered;
	}

	public String[] getSingleQuotationMarkHtmlNumbers() {
		return this.htmlNumbersSQ;
	}

	public String toUpperCase() {
		this.editedString = this.editedString.toUpperCase();
		return this.editedString;
	}

	public String toUpperCase(int var1) {
		this.editedString = this.editedString.substring(0, var1)
				+ this.editedString.substring(var1, var1 + 1).toUpperCase() + this.editedString.substring(var1 + 1);
		return this.editedString;
	}

	public String toLowerCase() {
		this.editedString = this.editedString.toLowerCase();
		return this.editedString;
	}

	public String toLowerCase(int var1) {
		this.editedString = this.editedString.substring(0, var1)
				+ this.editedString.substring(var1, var1 + 1).toLowerCase() + this.editedString.substring(var1 + 1);
		return this.editedString;
	}

	public String toAllTitleCase() {
		this.editedString = this.editedString.toLowerCase();
		this.tokens();
		for (int var2 = 0; var2 < this.nTokens; ++var2) {
			int var3 = this.tokenInitialIndices[var2];
			if (Chars.isQuotationMark(this.editedString.charAt(var3)) && var3 < this.nEdited) {
				++var3;
			}

			if (!this.whiteSpaces[var3]) {
				this.toUpperCase(var3);
			}
		}

		return this.editedString;
	}

	public static String toAllTitleCase(String var0) {
		Strings var1 = new Strings(var0);
		return var1.toAllTitleCase();
	}

	public String toTitleCase() {
		this.editedString = this.editedString.toLowerCase();
		this.principalTokens();
		for (int var2 = 0; var2 < this.nPrincipalTokens; ++var2) {
			int var3 = this.principalTokenInitialIndices[var2];
			if (Chars.isQuotationMark(this.editedString.charAt(var3)) && var3 < this.nEdited) {
				++var3;
			}

			if (!this.whiteSpaces[var3]) {
				this.toUpperCase(var3);
			}
		}

		return this.editedString;
	}

	public static String toTitleCase(String var0) {
		Strings var1 = new Strings(var0);
		return var1.toTitleCase();
	}

	public String toSentenceCase() {
		this.editedString = this.editedString.toLowerCase();
		this.tokens();
		int var1 = this.tokenInitialIndices[0];
		if (Chars.isQuotationMark(this.editedString.charAt(var1)) && var1 < this.nEdited) {
			++var1;
		}

		if (!this.whiteSpaces[var1]) {
			this.toUpperCase(var1);
		}

		for (int var3 = 0; var3 < this.nTokens - 1; ++var3) {
			var1 = this.tokenFinalIndices[var3];
			if (Chars.isQuotationMark(this.editedString.charAt(var1))) {
				--var1;
			}

			if (this.editedString.charAt(var1) == '.') {
				int var4 = this.tokenInitialIndices[var3 + 1];
				if (Chars.isQuotationMark(this.editedString.charAt(var4))) {
					++var4;
				}

				if (!this.whiteSpaces[var4]) {
					this.toUpperCase(var4);
				}
			}
		}

		return this.editedString;
	}

	public static String toSentenceCase(String var0) {
		Strings var1 = new Strings(var0);
		return var1.toSentenceCase();
	}

	public boolean[] whiteSpaces() {
		this.whiteSpaces = new boolean[this.nEdited];
		for (int var3 = 0; var3 < this.nEdited; ++var3) {
			this.whiteSpaces[var3] = false;
			if (Character.isWhitespace(this.editedString.charAt(var3))) {
				this.whiteSpaces[var3] = true;
			}
		}

		return this.whiteSpaces;
	}

	public static boolean[] whiteSpaces(String var0) {
		Strings var1 = new Strings(var0);
		return var1.whiteSpaces();
	}

	public String[] tokens() {
		this.whiteSpaces();
		ArrayList<Integer> var1 = new ArrayList<>();
		boolean var2 = true;
		boolean var3 = false;

		int var4;
		for (var4 = 0; var4 < this.nEdited; ++var4) {
			if (var2) {
				if (!this.whiteSpaces[var4]) {
					var1.add(var4);
					var2 = false;
					var3 = true;
				}
			} else if (var3 && this.whiteSpaces[var4]) {
				var1.add(var4);
				var3 = false;
				var2 = true;
			}
		}

		var4 = var1.size();
		if (var4 % 2 == 1) {
			var1.add(this.nEdited);
			++var4;
		}

		this.nTokens = var4 / 2;
		this.tokens = new String[this.nTokens];
		this.tokenInitialIndices = new int[this.nTokens];
		this.tokenFinalIndices = new int[this.nTokens];
		int var5 = -1;

		for (int var6 = 0; var6 < this.nTokens; ++var6) {
			++var5;
			int var7 = (Integer) var1.get(var5);
			++var5;
			int var8 = (Integer) var1.get(var5);
			this.tokens[var6] = this.editedString.substring(var7, var8).trim();
			this.tokenInitialIndices[var6] = var7;
			this.tokenFinalIndices[var6] = var8 - 1;
		}

		this.tokensDone = true;
		return this.tokens;
	}

	public static String[] tokens(String var0) {
		Strings var1 = new Strings(var0);
		return var1.tokens();
	}

	public int[] tokenInitialIndices() {
		if (!this.tokensDone) {
			this.tokens();
		}

		return this.tokenInitialIndices;
	}

	public static int[] tokenInitialIndices(String var0) {
		Strings var1 = new Strings(var0);
		return var1.tokenInitialIndices();
	}

	public int[] tokenFinalIndices() {
		if (!this.tokensDone) {
			this.tokens();
		}

		return this.tokenFinalIndices;
	}

	public static int[] tokenFinalIndices(String var0) {
		Strings var1 = new Strings(var0);
		return var1.tokenFinalIndices();
	}

	public String[] principalTokens() {
		if (!this.tokensDone) {
			this.tokens();
		}

		ArrayList<Integer> var1 = new ArrayList<>();
		var1.add(0);

		int var2;
		for (var2 = 1; var2 < this.nTokens; ++var2) {
			String var3 = removeQuotationMarks(this.tokens[var2]);
			boolean var4 = true;

			int var5;
			for (var5 = 0; var5 < this.nArticles; ++var5) {
				if (var3.equalsIgnoreCase(this.articles[var5])) {
					var4 = false;
					break;
				}
			}

			if (var4) {
				for (var5 = 0; var5 < this.nPrepositions; ++var5) {
					if (var3.equalsIgnoreCase(this.prepositions[var5])) {
						var4 = false;
						break;
					}
				}
			}

			if (var4) {
				for (var5 = 0; var5 < this.nConjunctions; ++var5) {
					if (var3.equalsIgnoreCase(this.conjunctions[var5])) {
						var4 = false;
						break;
					}
				}
			}

			if (var4) {
				var1.add(var2);
			}
		}

		this.nPrincipalTokens = var1.size();
		this.principalTokens = new String[this.nPrincipalTokens];
		this.principalTokenInitialIndices = new int[this.nPrincipalTokens];
		this.principalTokenFinalIndices = new int[this.nPrincipalTokens];

		for (var2 = 0; var2 < this.nPrincipalTokens; ++var2) {
			int var6 = (Integer) var1.get(var2);
			this.principalTokens[var2] = this.tokens[var6];
			this.principalTokenInitialIndices[var2] = this.tokenInitialIndices[var6];
			this.principalTokenFinalIndices[var2] = this.tokenFinalIndices[var6];
		}

		return this.principalTokens;
	}

	public static String[] principalTokens(String var0) {
		Strings var1 = new Strings(var0);
		return var1.principalTokens();
	}

	public String[] principalTokensZero() {
		if (!this.tokensDone) {
			this.tokens();
		}

		ArrayList<Integer> var1 = new ArrayList<>();

		int var2;
		for (var2 = 0; var2 < this.nTokens; ++var2) {
			String var3 = removeQuotationMarks(this.tokens[var2]);
			boolean var4 = true;

			int var5;
			for (var5 = 0; var5 < this.nArticles; ++var5) {
				if (var3.equalsIgnoreCase(this.articles[var5])) {
					var4 = false;
					break;
				}
			}

			if (var4) {
				for (var5 = 0; var5 < this.nPrepositions; ++var5) {
					if (var3.equalsIgnoreCase(this.prepositions[var5])) {
						var4 = false;
						break;
					}
				}
			}

			if (var4) {
				for (var5 = 0; var5 < this.nConjunctions; ++var5) {
					if (var3.equalsIgnoreCase(this.conjunctions[var5])) {
						var4 = false;
						break;
					}
				}
			}

			if (var4) {
				var1.add(var2);
			}
		}

		this.nPrincipalTokens = var1.size();
		this.principalTokens = new String[this.nPrincipalTokens];
		this.principalTokenInitialIndices = new int[this.nPrincipalTokens];
		this.principalTokenFinalIndices = new int[this.nPrincipalTokens];

		for (var2 = 0; var2 < this.nPrincipalTokens; ++var2) {
			int var6 = (Integer) var1.get(var2);
			this.principalTokens[var2] = this.tokens[var6];
			this.principalTokenInitialIndices[var2] = this.tokenInitialIndices[var6];
			this.principalTokenFinalIndices[var2] = this.tokenFinalIndices[var6];
		}

		return this.principalTokens;
	}

	public static String[] principalTokensZero(String var0) {
		Strings var1 = new Strings(var0);
		return var1.principalTokensZero();
	}

	public String removeWhiteSpaces() {
		String var1 = "";

		for (int var2 = 0; var2 < this.nEdited; ++var2) {
			char var3 = this.editedString.charAt(var2);
			if (!Character.isWhitespace(var3)) {
				var1 = var1 + var3;
			}
		}

		return var1.trim();
	}

	public static String removeWhiteSpaces(String var0) {
		Strings var1 = new Strings(var0);
		return var1.removeWhiteSpaces();
	}

	public String removeAccents() {
		String var1 = this.editedString;
		boolean var2 = true;
		int var5;
		String var6;
		int var7;
		int var8;
		for (var5 = 0; var5 < this.nSymbolsA; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNamesA[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNamesA[var5].length();
					var6 = var1.substring(0, var7) + this.replSymbolsA[var5];
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsA; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNumbersA[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNumbersA[var5].length();
					var6 = var1.substring(0, var7) + this.replSymbolsA[var5];
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsA; ++var5) {
			for (int var9 = 0; var9 < this.nEdited; ++var9) {
				if (var1.charAt(var9) == this.decNumbersA[var5]) {
					var1 = var1.substring(0, var9) + this.replSymbolsA[var5] + var1.substring(var9 + 1);
					this.nEdited = var1.length();
				}
			}
		}

		this.editedString = var1;
		return var1;
	}

	public static String removeAccents(String var0) {
		Strings var1 = new Strings(var0);
		return var1.removeAccents();
	}

	public String quotationMarksToHtmlNumbers() {
		String var1 = this.editedString;
		boolean var2 = true;
		int var5;
		for (var5 = 0; var5 < this.nSymbolsQ; ++var5) {
			for (var2 = true; var2; var2 = false) {
				int var9 = var1.indexOf(this.htmlNamesQ[var5]);
				if (var9 != -1) {
					int var10 = var9 + this.htmlNamesQ[var5].length();
					String var6 = var1.substring(0, var9) + this.htmlNumbersQ[var5];
					if (var10 < this.nEdited) {
						var6 = var6 + var1.substring(var10);
					}

					var1 = var6;
					this.nEdited = var6.length();
					break;
				}
			}
		}

		for (var5 = 0; var5 < this.nEdited; ++var5) {
			char var11 = var1.charAt(var5);

			for (int var7 = 0; var7 < this.nSymbolsQ; ++var7) {
				if (var11 == this.decNumbersQ[var7]) {
					String var8 = var1.substring(0, var5) + this.htmlNumbersQ[var7];
					if (var5 + 1 < this.nEdited) {
						var8 = var8 + var1.substring(var5 + 1);
					}

					var1 = var8;
					this.nEdited = var8.length();
					break;
				}
			}
		}

		this.editedString = var1;
		return var1;
	}

	public String quotationMarksToHtmlNumbersD() {
		String var1 = this.quotationMarksToHtmlNumbers();
		boolean var2 = true;
		String var3 = "&#34;";

		while (var2) {
			int var4 = var1.indexOf(var3);
			if (var4 == -1) {
				var2 = false;
			} else {
				var1 = var1.substring(0, var4) + "\"" + var1.substring(var4 + 5);
			}
		}

		return var1;
	}

	public static String quotationMarksToHtmlNumbers(String var0) {
		Strings var1 = new Strings(var0);
		return var1.quotationMarksToHtmlNumbers();
	}

	public static String quotationMarksToHtmlNumbersD(String var0) {
		Strings var1 = new Strings(var0);
		return var1.quotationMarksToHtmlNumbersD();
	}

	public String accentsToHtmlNumbers() {
		String var1 = this.editedString;
		boolean var2 = true;
		int var5;
		for (var5 = 0; var5 < this.nSymbolsA; ++var5) {
			for (var2 = true; var2; var2 = false) {
				int var9 = var1.indexOf(this.htmlNamesA[var5]);
				if (var9 != -1) {
					int var10 = var9 + this.htmlNamesA[var5].length();
					String var6 = var1.substring(0, var9) + this.htmlNumbersA[var5];
					if (var10 < this.nEdited) {
						var6 = var6 + var1.substring(var10);
					}

					var1 = var6;
					this.nEdited = var6.length();
					break;
				}
			}
		}

		for (var5 = 0; var5 < this.nEdited; ++var5) {
			char var11 = var1.charAt(var5);

			for (int var7 = 0; var7 < this.nSymbolsA; ++var7) {
				if (var11 == this.decNumbersA[var7]) {
					String var8 = var1.substring(0, var5) + this.htmlNumbersA[var7];
					if (var5 + 1 < this.nEdited) {
						var8 = var8 + var1.substring(var5 + 1);
					}

					var1 = var8;
					this.nEdited = var8.length();
					break;
				}
			}
		}

		this.editedString = var1;
		return var1;
	}

	public static String accentsToHtmlNumbers(String var0) {
		Strings var1 = new Strings(var0);
		return var1.accentsToHtmlNumbers();
	}

	public String dashesToHtmlNumbers() {
		String var1 = this.editedString;
		boolean var2 = true;
		int var5;
		for (var5 = 0; var5 < this.nSymbolsD; ++var5) {
			for (var2 = true; var2; var2 = false) {
				int var9 = var1.indexOf(this.htmlNamesD[var5]);
				if (var9 != -1) {
					int var10 = var9 + this.htmlNamesD[var5].length();
					String var6 = var1.substring(0, var9) + this.htmlNumbersD[var5];
					if (var10 < this.nEdited) {
						var6 = var6 + var1.substring(var10);
					}

					var1 = var6;
					this.nEdited = var6.length();
					break;
				}
			}
		}

		for (var5 = 0; var5 < this.nEdited; ++var5) {
			char var11 = var1.charAt(var5);

			for (int var7 = 0; var7 < this.nSymbolsD; ++var7) {
				if (var11 == this.decNumbersD[var7]) {
					String var8 = var1.substring(0, var5) + this.htmlNumbersD[var7];
					if (var5 + 1 < this.nEdited) {
						var8 = var8 + var1.substring(var5 + 1);
					}

					var1 = var8;
					this.nEdited = var8.length();
					break;
				}
			}
		}

		this.editedString = var1;
		return var1;
	}

	public static String dashesToHtmlNumbers(String var0) {
		Strings var1 = new Strings(var0);
		return var1.dashesToHtmlNumbers();
	}

	public String accentsToDec() {
		String var1 = this.editedString;
		boolean var2 = true;
		int var5;
		String var6;
		int var7;
		int var8;
		for (var5 = 0; var5 < this.nSymbolsA; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNamesA[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNamesA[var5].length();
					var6 = var1.substring(0, var7) + (char) this.decNumbersA[var5];
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsA; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNumbersA[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNumbersA[var5].length();
					var6 = var1.substring(0, var7) + (char) this.decNumbersA[var5];
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		this.editedString = var1;
		return var1;
	}

	public static String accentsToDec(String var0) {
		Strings var1 = new Strings(var0);
		return var1.accentsToDec();
	}

	public String dashesToDec() {
		String var1 = this.editedString;
		boolean var2 = true;
		int var5;
		String var6;
		int var7;
		int var8;
		for (var5 = 0; var5 < this.nSymbolsD; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNamesD[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNamesD[var5].length();
					var6 = var1.substring(0, var7) + (char) this.decNumbersD[var5];
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsD; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNumbersD[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNumbersD[var5].length();
					var6 = var1.substring(0, var7) + (char) this.decNumbersD[var5];
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		this.editedString = var1;
		return var1;
	}

	public static String dashesToDec(String var0) {
		Strings var1 = new Strings(var0);
		return var1.dashesToDec();
	}

	public String quotationMarksToDec() {
		String var1 = this.editedString;
		boolean var2 = true;
		int var5;
		String var6;
		int var7;
		int var8;
		for (var5 = 0; var5 < this.nSymbolsQ; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNamesQ[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNamesQ[var5].length();
					var6 = var1.substring(0, var7) + (char) this.decNumbersQ[var5];
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsQ; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNumbersQ[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNumbersQ[var5].length();
					var6 = var1.substring(0, var7) + (char) this.decNumbersQ[var5];
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		this.editedString = var1;
		return var1;
	}

	public static String quotationMarksToDec(String var0) {
		Strings var1 = new Strings(var0);
		return var1.quotationMarksToDec();
	}

	public String quotationMarksDecToName() {
		for (int var1 = 0; var1 < this.nEdited; ++var1) {
			char var2 = this.editedString.charAt(var1);

			for (int var3 = 0; var3 < this.nSymbolsQ; ++var3) {
				if (var2 == this.decNumbersQ[var3]) {
					String var4 = this.editedString.substring(0, var1) + this.htmlNamesQ[var3];
					if (var1 < this.nEdited - 1) {
						var4 = var4 + this.editedString.substring(var1 + 1);
					}

					this.editedString = var4;
					this.nEdited = this.editedString.length();
					break;
				}
			}
		}

		return this.editedString;
	}

	public static String quotationMarksDecToName(String var0) {
		Strings var1 = new Strings(var0);
		return var1.quotationMarksDecToName();
	}

	public String quotationMarksDecToHtmlNumber() {
		for (int var1 = 0; var1 < this.nEdited; ++var1) {
			char var2 = this.editedString.charAt(var1);

			for (int var3 = 0; var3 < this.nSymbolsQ; ++var3) {
				if (var2 == this.decNumbersQ[var3]) {
					String var4 = this.editedString.substring(0, var1) + this.htmlNumbersQ[var3];
					if (var1 < this.nEdited - 1) {
						var4 = var4 + this.editedString.substring(var1 + 1);
					}

					this.editedString = var4;
					this.nEdited = this.editedString.length();
					break;
				}
			}
		}

		return this.editedString;
	}

	public static String quotationMarksDecToHtmlNumber(String var0) {
		Strings var1 = new Strings(var0);
		return var1.quotationMarksDecToHtmlNumber();
	}

	public String replaceDashesWithSpaces() {
		String var1 = this.editedString;
		boolean var2 = true;
		int var5;
		String var6;
		int var7;
		int var8;
		for (var5 = 0; var5 < this.nSymbolsD; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNamesD[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNamesD[var5].length();
					var6 = var1.substring(0, var7) + " ";
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsD; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNumbersD[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNumbersD[var5].length();
					var6 = var1.substring(0, var7) + " ";
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsD; ++var5) {
			for (int var9 = 0; var9 < this.nEdited; ++var9) {
				if (var1.charAt(var9) == this.decNumbersD[var5]) {
					var1 = var1.substring(0, var9) + " " + var1.substring(var9 + 1);
					this.nEdited = var1.length();
				}
			}
		}

		this.editedString = var1;
		return var1;
	}

	public static String replaceDashesWithSpaces(String var0) {
		Strings var1 = new Strings(var0);
		return var1.replaceDashesWithSpaces();
	}

	public String removeQuotationMarks() {
		String var1 = this.editedString;
		boolean var2 = true;
		int var5;
		String var6;
		int var7;
		int var8;
		for (var5 = 0; var5 < this.nSymbolsQ; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNamesQ[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNamesQ[var5].length();
					var6 = var1.substring(0, var7);
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsQ; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNumbersQ[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNumbersQ[var5].length();
					var6 = var1.substring(0, var7);
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsQ; ++var5) {
			for (int var9 = 0; var9 < this.nEdited; ++var9) {
				if (var1.charAt(var9) == this.decNumbersQ[var5]) {
					var1 = var1.substring(0, var9) + var1.substring(var9 + 1);
					this.nEdited = var1.length();
				}
			}
		}

		this.editedString = var1;
		return var1;
	}

	public static String removeQuotationMarks(String var0) {
		Strings var1 = new Strings(var0);
		return var1.removeQuotationMarks();
	}

	public String replaceQuotationMarksBySpaces() {
		String var1 = this.editedString;
		boolean var2 = true;
		int var5;
		String var6;
		int var7;
		int var8;
		for (var5 = 0; var5 < this.nSymbolsQ; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNamesQ[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNamesQ[var5].length();
					var6 = var1.substring(0, var7);
					if (var8 < this.nEdited) {
						var6 = var6 + " " + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsQ; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNumbersQ[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNumbersQ[var5].length();
					var6 = var1.substring(0, var7);
					if (var8 < this.nEdited) {
						var6 = var6 + " " + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsQ; ++var5) {
			for (int var9 = 0; var9 < this.nEdited; ++var9) {
				if (var1.charAt(var9) == this.decNumbersQ[var5]) {
					var1 = var1.substring(0, var9) + " " + var1.substring(var9 + 1);
					this.nEdited = var1.length();
				}
			}
		}

		this.editedString = var1;
		return var1;
	}

	public String replaceQuotationMarksWithSpaces() {
		String var1 = this.editedString;
		boolean var2 = true;
		int var5;
		String var6;
		int var7;
		int var8;
		for (var5 = 0; var5 < this.nSymbolsQ; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNamesQ[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNamesQ[var5].length();
					var6 = var1.substring(0, var7) + " ";
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsQ; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNumbersQ[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNumbersQ[var5].length();
					var6 = var1.substring(0, var7) + " ";
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsQ; ++var5) {
			for (int var9 = 0; var9 < this.nEdited; ++var9) {
				if (var1.charAt(var9) == this.decNumbersQ[var5]) {
					var1 = var1.substring(0, var9) + " " + var1.substring(var9 + 1);
					this.nEdited = var1.length();
				}
			}
		}

		this.editedString = var1;
		return var1;
	}

	public static String replaceQuotationMarksWithSpaces(String var0) {
		Strings var1 = new Strings(var0);
		return var1.replaceQuotationMarksWithSpaces();
	}

	public String removeQuotationMarksPlus() {
		String var1 = this.editedString;
		boolean var2 = true;
		int var5;
		char var6;
		int var8;
		int var9;
		String var10;
		label128: for (var5 = 0; var5 < this.nSymbolsQ; ++var5) {
			var2 = true;

			while (true) {
				while (true) {
					if (!var2) {
						continue label128;
					}

					var8 = var1.indexOf(this.htmlNamesQ[var5]);
					if (var8 != -1) {
						var9 = var8 + this.htmlNamesQ[var5].length();
						if (var9 < this.nEdited && var8 > 0) {
							var6 = var1.charAt(var8 - 1);
							if (!Character.isWhitespace(var6)
									&& (var1.charAt(var9) == 's' || var1.charAt(var9) == 'S')) {
								++var9;
							}
						}

						var10 = var1.substring(0, var8);
						if (var9 < this.nEdited) {
							var10 = var10 + var1.substring(var9);
						}

						var1 = var10;
						this.nEdited = var10.length();
					} else {
						var2 = false;
					}
				}
			}
		}

		label102: for (var5 = 0; var5 < this.nSymbolsQ; ++var5) {
			var2 = true;

			while (true) {
				while (true) {
					if (!var2) {
						continue label102;
					}

					var8 = var1.indexOf(this.htmlNumbersQ[var5]);
					if (var8 != -1) {
						var9 = var8 + this.htmlNumbersQ[var5].length();
						if (var9 < this.nEdited && var8 > 0) {
							var6 = var1.charAt(var8 - 1);
							if (!Character.isWhitespace(var6)
									&& (var1.charAt(var9) == 's' || var1.charAt(var9) == 'S')) {
								++var9;
							}
						}

						var10 = var1.substring(0, var8);
						if (var9 < this.nEdited) {
							var10 = var10 + var1.substring(var9);
						}

						var1 = var10;
						this.nEdited = var10.length();
					} else {
						var2 = false;
					}
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsQ; ++var5) {
			for (int var11 = 0; var11 < this.nEdited; ++var11) {
				var9 = var11 + 1;
				if (var9 < this.nEdited && var11 > 0) {
					char var7 = var1.charAt(var11 - 1);
					if (!Character.isWhitespace(var7) && (var1.charAt(var9) == 's' || var1.charAt(var9) == 'S')) {
						++var9;
					}
				}

				if (var1.charAt(var11) == this.decNumbersQ[var5]) {
					var1 = var1.substring(0, var11) + var1.substring(var9);
					this.nEdited = var1.length();
				}
			}
		}

		this.editedString = var1;
		return var1;
	}

	public static String removeQuotationMarksPlus(String var0) {
		Strings var1 = new Strings(var0);
		return var1.removeQuotationMarksPlus();
	}

	public String removeDashes() {
		String var1 = this.editedString;
		boolean var2 = true;
		int var5;
		String var6;
		int var7;
		int var8;
		for (var5 = 0; var5 < this.nSymbolsD; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNamesD[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNamesD[var5].length();
					var6 = var1.substring(0, var7);
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsD; ++var5) {
			var2 = true;

			while (var2) {
				var7 = var1.indexOf(this.htmlNumbersD[var5]);
				if (var7 != -1) {
					var8 = var7 + this.htmlNumbersD[var5].length();
					var6 = var1.substring(0, var7);
					if (var8 < this.nEdited) {
						var6 = var6 + var1.substring(var8);
					}

					var1 = var6;
					this.nEdited = var6.length();
				} else {
					var2 = false;
				}
			}
		}

		for (var5 = 0; var5 < this.nSymbolsD; ++var5) {
			for (int var9 = 0; var9 < this.nEdited; ++var9) {
				if (var1.charAt(var9) == this.decNumbersD[var5]) {
					var1 = var1.substring(0, var9) + var1.substring(var9 + 1);
					this.nEdited = var1.length();
				}
			}
		}

		this.editedString = var1;
		return var1;
	}

	public static String removeDashes(String var0) {
		Strings var1 = new Strings(var0);
		return var1.removeDashes();
	}

	public int indexOfDash() {
		int var1 = -1;
		int var2 = -1;
		boolean var3 = false;

		int var6;
		for (int var4 = 0; var4 < this.nEdited; ++var4) {
			String var5 = this.editedString.substring(var4, var4 + 1);

			for (var6 = 0; var6 < this.nSymbolsD; ++var6) {
				if (var5.equals(this.symbolsD[var6])) {
					var1 = var4;
					var2 = var4;
					var3 = true;
					break;
				}
			}

			if (var3) {
				break;
			}
		}

		String[] var8 = new String[] { "&ndash;", "&mdash;", "&oline;" };
		var6 = -1;

		int var7;
		int var10;
		for (var7 = 0; var7 < 3; ++var7) {
			var10 = this.editedString.indexOf(var8[var7]);
			if (var10 != -1) {
				var1 = var10;
				if (var6 == -1) {
					var6 = var10;
				} else if (var6 < var10) {
					var1 = var6;
				} else {
					var6 = var10;
				}
			}
		}

		if (var2 != -1) {
			if (var6 != -1) {
				var1 = Math.min(var2, var6);
				var2 = var1;
			}
		} else if (var6 != -1) {
			var1 = var6;
			var2 = var6;
		}

		var6 = -1;

		for (var7 = 0; var7 < this.nSymbolsD; ++var7) {
			var10 = this.editedString.indexOf(this.htmlNumbersD[var7]);
			if (var10 != -1) {
				var1 = var10;
				if (var6 == -1) {
					var6 = var10;
				} else if (var6 < var10) {
					var1 = var6;
				} else {
					var6 = var10;
				}
			}
		}

		if (var2 != -1) {
			if (var6 != -1) {
				var1 = Math.min(var2, var6);
			}
		} else if (var6 != -1) {
			var1 = var6;
		}

		return var1;
	}

	public static int indexOfDash(String var0) {
		Strings var1 = new Strings(var0);
		return var1.indexOfDash();
	}

	public boolean nearlyEquals(String var1, double var2) {
		boolean var4 = false;
		String[] var5 = new String[] { this.editedString, var1 };
		if (var5[0].equalsIgnoreCase(var5[1])) {
			var4 = true;
		} else {
			int var6 = var5[0].length();
			int var7 = var5[1].length();
			int var8 = Math.min(var6, var7);
			int var9 = Math.max(var6, var7);
			byte var10 = 0;
			byte var11 = 1;
			if (var6 == var9) {
				var10 = 1;
				var11 = 0;
			}

			int var12 = (int) Math.round(var2 * (double) var8);
			int var13 = var9 - var8;

			for (int var14 = 0; var14 < var13 + 1; ++var14) {
				int var15 = 0;

				for (int var16 = 0; var16 < var8; ++var16) {
					if (var5[var10].charAt(var16) == var5[var11].charAt(var16 + var14)) {
						++var15;
					}
				}

				if (var15 >= var12) {
					var4 = true;
					break;
				}
			}
		}

		return var4;
	}

	public static boolean nearlyEquals(String var0, String var1, double var2) {
		Strings var4 = new Strings(var1);
		return var4.nearlyEquals(var0, var2);
	}

}
