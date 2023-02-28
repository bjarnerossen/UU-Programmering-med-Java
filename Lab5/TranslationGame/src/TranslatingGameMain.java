/**
 * The main class that initializes the game logic
 * and user interface, and runs the game.
 */
public class TranslatingGameMain {
    /**
     * Program entry point.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        TranslatingGameLogic game = new TranslatingGameLogic("vocabulary.properties");
        TranslatingGameUI ui = new TranslatingGameUI(game);
        ui.run();
    }
}
