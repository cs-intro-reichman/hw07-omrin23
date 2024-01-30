
public class SpellChecker {


	//takes word and threshold as args from the user
	//and prints the correction using spellChecker
	public static void main(String[] args) {
		if (args.length > 0) {
			String word = args[0];
			int threshold = Integer.parseInt(args[1]);
			String[] dictionary = readDictionary("dictionary.txt");
			String correction = spellChecker(word, threshold, dictionary);
			System.out.println(correction);
		}
	}

	//returns the first character of the given string
	public static char head(String str) {
		return str.charAt(0);
	}

	//returns the given string excluding the first character
	//if length of the string is 1, then return en empty string
	public static String tail(String str) {
		if (str.length() == 1) {
			return "";
		}
		return str.substring(1);
	}

	//returns the min of the three given numbers
	public static int specialMin(int n1, int n2, int n3) {
		int min = Math.min(n1, n2);
		min = Math.min(n3, min);
		return min;
	}

	//returns the edit distance
	//between these two words, as an integer
	public static int levenshtein(String word1, String word2) {

		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		
		if (word2.length() == 0) {
			return word1.length();
		}
		if (word1.length() == 0) {
			return word2.length();
		}

		if (head(word1) == head(word2)) {
			return levenshtein(tail(word1), tail(word2));
		}

		return 1 + specialMin(levenshtein(tail(word1), word2), 
			levenshtein(word1, tail(word2)), levenshtein(tail(word1), tail(word2)));
	}

	// reads the data from the file and returns it in an array
	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		int index = 0;

		while (!in.isEmpty()) {
			dictionary[index] = in.readString();
			index++;
		}

		return dictionary;
	}

	// if the lev of the given word is less than or equal to the threshold
	// it returns the closest word, otherwise it returns the original word
	public static String spellChecker(String word, int threshold, String[] dictionary) {
		
		int min = Integer.MAX_VALUE;
		String minWord = "";

		for (int i = 0; i < dictionary.length; i++) {
			if (levenshtein(word, dictionary[i]) < min) {
				min = levenshtein(word, dictionary[i]);
				minWord = dictionary[i];
			}
		}

		if (min > threshold) {
			return word;
		}
		return minWord;

	}

}
