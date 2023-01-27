import java.util.Scanner;
public class CharCounter{
    public static void main(String[] args) {
        // Create a new Scanner object to read input from the user
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter a sentence
        System.out.print("Skriv in en mening: ");
        String sentence = input.nextLine();

        // Check if the sentence is empty
        while(sentence.isEmpty()) {
            System.out.println("Meningen kan inte stå tom.");
            System.out.print("Skriv in en mening: ");
            sentence = input.nextLine();
        }

        // Prompt the user to enter a character
        System.out.print("Skriv in ett tecken: ");
        String character = input.nextLine();

        // Check if the user has entered an empty string or more than one char
        while (true) {
            if (character.isEmpty()) {
                System.out.println("Du har inte skrivit in något tecken.");
                System.out.print("Skriv in ett tecken: ");
            } else if (character.length() != 1) {
                System.out.println("Du har skrivit in fler än ett tecken.");
                System.out.print("Skriv in endast ett tecken: ");
            } else {
                break;
            }
            character = input.nextLine();
        }


        // Get the total number of characters in the sentence
        int totalChars = sentence.length();

        // Get the number of occurrences of the given character in the sentence
        int charCount = sentence.length() - sentence.replace(character,"").length();

        // Get the index of the first occurrence of the given character in the sentence
        int firstIndex = sentence.indexOf(character);

        // Get the index of the last occurrence of the given character in the sentence
        int lastIndex = sentence.lastIndexOf(character);

        // Print the results
        System.out.println("Meningen har totalt " + totalChars + " tecken.");
        System.out.println("Tecknet " + character + " förekommer " + charCount + " gånger.");
        System.out.println("Första gången på indexplats " + firstIndex);
        System.out.println("Sista gången på indexplats " + lastIndex);
    }
}
