import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
	// Path to dictionary of common english words
	public static final String DICTIONARY_PATH = "/resources/google-10000-english/google-10000-english-usa-no-swears-medium.txt";
	
	public static void main(String[] args) throws FileNotFoundException {
		int passLength;
		if (args.length != 1) {
			System.out.println("<Defaulting to length 4 password>");
			passLength = 4;
		} else {
			passLength = Integer.parseInt(args[0]);
		}
		
		ArrayList<String> words = readWords(new InputStreamReader(
				PasswordGenerator.class.getResourceAsStream(DICTIONARY_PATH)));
		String passElements = buildPass(words, passLength);
		String pass =  passElements.replace(" ", "");
		
		System.out.println("xkcd-ified password (length " + pass.length() + "):");
		System.out.println("   " + passElements);
		System.out.println();
		System.out.println("   " + pass);
	}
	
	public static String buildPass(ArrayList<String> words, int passLength) {
		String pass = "";
		
		Random r = new Random();
		
		for (int i = 0; i < passLength; i++) {
			pass += words.get(r.nextInt(words.size())) + " ";
		}
		
		return pass;
	}
	
	public static ArrayList<String> readWords(InputStreamReader f) throws FileNotFoundException {
		Scanner input = new Scanner(f);
		
		ArrayList<String> words = new ArrayList<String>();

		while (input.hasNextLine()) {
			words.add(input.nextLine());
		}
		
		return words;
	}

}
