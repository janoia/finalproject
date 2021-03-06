import java.util.Scanner;
import java.util.Random;
/**
 * This is a Hangman game created with java code
 * @author jorda
 *
 */
public class Hangman {


private static int count = 0;
private static int lives = 0;	
private static int lifenumber=0;
static String word;
static String theword;//allows the use of theword in wordToGuess method to be used in other methods


/**
 * This method allows the program to choose a random word from the list
 * @param theword stores the word chosen by this method
 * @return returns theword in order to transfer it to another method
 */

public static String wordsToGuess(String theword) {
	String[] wordsToGuess = {"match", "love", "harmony", "sad", "outside", "jackson", "cat", "mouse", "relax"}; //words the user must guess
	Random r = new Random(); //picks a random word from the wordsToGuess
	String words = wordsToGuess[r.nextInt(wordsToGuess.length)]; //allows the word to be chosen from the list
	
	theword = words;//references word chosen
	
	return theword;//returns the word that was chosen to other methods
}	


/**
 * This method clarifies and maintains the chosenWord generated by the wordsToGuess method
 * @param word stores the clarified word to be used in other methods
 * @return allows the word to be returned from this method so that it can be take and put into another method
 */

public static String wordChosen(String word) {
	
	String hangmanword = wordsToGuess(theword);
	word = hangmanword;
	return word;
}


/**
 * This method allows us to change each letter we get from the wordsToGuess method in order to help the user know how many letters there are in the word
 * @param array allows the program to create an array with each individual letter in the word
 */

private static void letterDisplay(char[] array) { 
	for (int i = 0; i<array.length;i++) { 
		System.out.print(array[i] + " "); 
	}
}


/**
 * This method allows the program to run and function
 * @param args
 */

public static void main(String[] args) { 

	System.out.println("Hangman By Jordano Anoia");
	
	difficulty();
	}
	

/**
 * This method allows the user to input the guess and allows the program to showcase whether the users guess was correct or not as well as allows the user to know how many lives the user has left. This method also allows the program to verify if the letters guessed by the user creates the word that resembles the array of the word chosen by wordChosen method. 
 */

public static void attemps() {
		
		Scanner sc = new Scanner (System.in);
		
		char[] randomword = wordChosen(word).toCharArray(); //turns the word from the wordChosen method into a chat and allows the char to access its array
		int amountOfLetters = randomword.length; //turns the length of the word into an amount (integer)
		char[] thewordgiven = new char[amountOfLetters]; //uses the amount of letters in the word as i 
			
		
	for (int i = 0; i<thewordgiven.length; i++) { //loops until every i (each letter) is replaced
			thewordgiven[i]='_'; //replaces each letter in the word with "_" 
		}
		
	while (lives < lifenumber) {
	
		
		letterDisplay(thewordgiven); //activates the method letterdisplay
	
		System.out.println("\nThere are " + count + " lives left");
	
		System.out.println("Please Enter a lowercase letter: ");
	
		char input = sc.nextLine().charAt(0); // this means it will only register the first character you put in
		System.out.println("\n ---------------------");
		int i;
	
	for (i = 0; i < randomword.length; i++) { //loops each individual character
		
		if (randomword[i]==input) { //looks if one character of the word is found in the input given
			thewordgiven[i] = input;
			count= count + 1;
			lives = lives - 1;
			break;
		
		} 
		
	}
	
		count--;
		lives++;
	
		if (ifWordIsCorrect(thewordgiven)) { //if the word and the word the user guessed matches then this occurs
		
			gameOver(); // calls gameOver method
			break;
		}
	
	}
	
	if (count == 0) {
		
		gameOver(); 
	}
	
}
	

/**
 * This method tells the user if the user lost or won with a choice to restart or not 
 */

public static void gameOver() { 
	Scanner two = new Scanner(System.in);
	
		if (count == 0) {
			System.out.println("You have ran out of lives");
			
		} else if (count > 0) {
			System.out.println("Congratulations you won with "+count+" lives left");
		}
		System.out.println("\nIf you want to play again Enter '1' if not Enter '0'");
		
		
		
		int choice = two.nextInt();
		
		switch (choice) {
		
		case 0:
			
			break;
		case 1:
			count=0;
			lives=0;
			difficulty();
			break;
		}
}


/**
 * This method determines whether the _ gets replaced by its initial state (as in turns back into its initial letters)
 * @param array targets the array of the word
 * @return returns false if equal to that letter and returns true if anything else
 */

public static boolean ifWordIsCorrect(char[] array) { 	
	for (int i = 0; i < array.length; i++ ) { 
		if (array[i]=='_') return false;
	}
	return true;
}


/**
 * This method allows the user to choose what difficulty the game should be with the help of a switch
 */

public static void difficulty() { 
	Scanner choice = new Scanner(System.in); 
	
	System.out.println("Please Select a level by entering its number: ");
	System.out.println("\n1 - easy (20 lives available)");
	System.out.println("\n2 - normal (15 lives available)");
	System.out.println("\n3 - hard (10 lives available)");
	System.out.println("\n4 - extreme (5 lives available)");
	System.out.println("\n===============================");
	int level = choice.nextInt();
	
	switch (level) {
	
	case 1:
		count = 20;
		lifenumber=20;
		attemps();
		break;
	
	case 2:
		count = 15;
		lifenumber=15;
		attemps();
		break;
	
	case 3:
		count = 10;
		lifenumber=10;
		attemps();
		break;
	
	case 4:
		count = 5;
		lifenumber=5;
		attemps();
		break;
		
	}
	
}

}