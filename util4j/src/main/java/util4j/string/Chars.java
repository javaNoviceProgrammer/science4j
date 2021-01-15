package util4j.string;

public class Chars {

	private char enteredChar = ' ';
	private char editedChar = ' ';
	private char[] symbolsA = new char[]{'À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'Ç', '?', '?', 'È', 'É', 'Ê', 'Ë', '?', 'Ì', 'Í',
			'Î', 'Ï', 'Ð', 'Ñ', 'Ò', 'Ó', 'Ô', 'Õ', 'Ö', 'Ø', 'Ù', 'Ú', 'Û', 'Ü', 'Ý', 'à', 'á', 'â', 'ã', 'ä', 'å',
			'ç', '?', '?', 'è', 'é', 'ê', 'ë', '?', 'ì', 'í', 'î', 'ï', 'ð', 'ñ', 'ò', 'ó', 'ô', 'õ', 'ö', 'ø', 'ù',
			'ú', 'û', 'ü', 'ý', 'ÿ', 'Š', 'š', 'Ÿ', 'ƒ'};
	private char[] replSymbolsA = new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'C', 'C', 'C', 'E', 'E', 'E', 'E', 'G', 'I',
			'I', 'I', 'I', 'D', 'N', 'O', 'O', 'O', 'O', 'O', 'O', 'U', 'U', 'U', 'U', 'Y', 'a', 'a', 'a', 'a', 'a',
			'a', 'c', 'e', 'e', 'e', 'e', 'g', 'i', 'i', 'i', 'i', 'd', 'n', 'o', 'o', 'o', 'o', 'o', 'o', 'u', 'u',
			'u', 'u', 'y', 'y', 's', 's', 'y', 'f'};
	
	@SuppressWarnings("unused")
	private int[] decNumbersA = new int[]{192, 193, 194, 195, 196, 197, 199, 262, 268, 200, 201, 202, 203, 286, 204,
			205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 216, 217, 218, 219, 220, 221, 224, 225, 226, 227, 228,
			229, 231, 232, 233, 263, 269, 234, 235, 287, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 248,
			249, 250, 251, 252, 253, 255, 352, 353, 376, 402};
	
	private int nSymbolsA;
	private int[] decNumbersQ;
	private int nSymbolsQ;

	public Chars(char var1) {
		this.nSymbolsA = this.symbolsA.length;
		this.decNumbersQ = new int[]{34, 39, 130, 132, 139, 145, 146, 147, 148, 155, 171, 187};
		this.nSymbolsQ = this.decNumbersQ.length;
		this.enteredChar = var1;
		this.editedChar = var1;
	}

	public char getEdited_as_char() {
		return this.editedChar;
	}

	public Character getEdited_as_Character() {
		return new Character(this.editedChar);
	}

	public char getEntered_as_char() {
		return this.enteredChar;
	}

	public Character getEntered_as_Character() {
		return new Character(this.enteredChar);
	}

	public boolean isQuotationMark() {
		boolean var1 = false;

		for (int var2 = 0; var2 < this.nSymbolsQ; ++var2) {
			if (this.editedChar == this.decNumbersQ[var2]) {
				var1 = true;
				break;
			}
		}

		return var1;
	}

	public static boolean isQuotationMark(char var0) {
		Chars var1 = new Chars(var0);
		return var1.isQuotationMark();
	}

	public boolean isUpperCase() {
		return Character.isUpperCase(this.editedChar);
	}

	public boolean isLowerCase() {
		return Character.isLowerCase(this.editedChar);
	}

	public char toUpperCase() {
		this.editedChar = Character.toUpperCase(this.editedChar);
		return this.editedChar;
	}

	public static char toUpperCase(char var0) {
		return Character.toUpperCase(var0);
	}

	public char toLowerCase() {
		this.editedChar = Character.toLowerCase(this.editedChar);
		return this.editedChar;
	}

	public static char toLowerCase(char var0) {
		return Character.toLowerCase(var0);
	}

	public char removeAccent() {
		for (int var1 = 0; var1 < this.nSymbolsA; ++var1) {
			if (this.editedChar == this.symbolsA[var1]) {
				this.editedChar = this.replSymbolsA[var1];
				break;
			}
		}

		return this.editedChar;
	}

	public static char removeAccent(char var0) {
		Chars var1 = new Chars(var0);
		return var1.removeAccent();
	}

	public boolean isAlphabetic() {
		boolean var1 = false;
		if (Character.isLetter(this.editedChar)) {
			;
		}

		return var1;
	}

	public boolean isDigit() {
		boolean var1 = false;
		if (Character.isDigit(this.editedChar)) {
			;
		}

		return var1;
	}

	public boolean isAlphanumeric() {
		boolean var1 = false;
		if (Character.isLetterOrDigit(this.editedChar)) {
			;
		}

		return var1;
	}
	
}
