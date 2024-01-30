

public class HashTagTokenizer {

	//takes a hashtag word from the user and prints
	//all the words that are in the dictionary file
	//that are in the hashtag word
	public static void main(String[] args) {

		if (args.length > 0) {
			String hashTag = args[0];
			String []dictionary = readDictionary("dictionary.txt");
			breakHashTag(hashTag, dictionary);
		}
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

	//returns true if the word is in the dictionary, false otherwise
	public static boolean existInDictionary(String word, String []dictionary) {
		
		for (int i = 0; i < dictionary.length; i++) {
			if (word.equals(dictionary[i])) {
				return true;
			}
		}

		return false;
	}

	//prints all the words that exists in the dictionary
	public static void breakHashTag(String hashtag, String[] dictionary) {
		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();

        for (int i = 1; i <= N; i++) {
			if (existInDictionary(hashtag.substring(0, i).toLowerCase(), dictionary)) {
				System.out.println(hashtag.substring(0, i).toLowerCase());
				breakHashTag(hashtag.substring(i), dictionary);
				return;
			}
        }
		
		breakHashTag("", dictionary);
    }

}
