import java.util.Scanner;

/**
 * The TranslatingGameUI class represents the user interface for a translating game.
 * It uses a TranslatingGameLogic object to play the game and a Scanner object to read user input.
 */
public class TranslatingGameUI {
    private final TranslatingGameLogic game;
    private final Scanner scanner;

    /**
     * Constructs a new TranslatingGameUI object with the given TranslatingGameLogic object.
     * @param game the game object to use for the UI.
     */
    public TranslatingGameUI(TranslatingGameLogic game) {
        this.game = game;
        scanner = new Scanner(System.in);
    }

    /**
     * Runs the user interface for the game.
     * Prompts the user to enter the English translation for a Swedish word, and provides feedback on the answer.
     * The game ends when the user enters 'Q'.
     */
    public void run() {
        System.out.println("** GLOSÖVNING - ENGELSKA **");
        System.out.println("Skriv det engelska ordet. Avsluta programmet genom att skriva Q.");

        int counter = 0;
        String swedish = game.getNextWord();

        while (swedish != null && counter < 10) {
            String english;
            do {
                System.out.print(swedish + ": ");
                english = scanner.nextLine().trim();

                if (english.isEmpty()) {
                   System.out.println("Ange minst en bokstav.");
                }
            } while (english.isEmpty());
            
            if (english.equalsIgnoreCase("q")) {
                break;
            }

            try {
                game.checkAnswer(swedish, english);
                game.markWordAsLearned(swedish);
            } catch (IllegalArgumentException e) {
                System.out.println("Felaktig input. Vänligen ange en giltig inmatning.");
            }

            swedish = game.getNextWord();
            counter++;
        }

        int totalAnswers = game.getTotalAnswers();
        int numCorrect = game.getNumCorrect();

        System.out.printf("Du svarade på totalt %d glosor och hade %d rätt. Välkommen åter!%n", totalAnswers, numCorrect);
    }
}