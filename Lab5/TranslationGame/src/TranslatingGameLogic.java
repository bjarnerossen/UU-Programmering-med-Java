import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The TranslatingGameLogic class represents the logic for a translating game
 * between Swedish and English.
 * It stores a vocabulary of Swedish words and their English translations, as well as
 * the number of correct answers and total number of answers.
 */
public class TranslatingGameLogic {
    private final Properties vocabulary;
    private int numCorrect;
    private int totalAnswers;

    /**
     * Constructs a new TranslatingGameLogic object.
     * Initializes the vocabulary with a set of predefined Swedish words and their English translations,
     * and sets the number of correct answers to 0.
     */
    public TranslatingGameLogic(String vocabularyList) {
        vocabulary = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(vocabularyList)) {
            if (inputStream != null) {
                vocabulary.load(inputStream);
            } else {
                throw new IOException("Det gick inte att ladda ordförrådsfilen: " + vocabularyList);
            }
        } catch (IOException e) {
            System.err.println("Det gick inte att läsa in ordförrådsfilen: " + e.getMessage());
        }
        numCorrect = 0;
    }

    /**
     * Checks the user's answer to a Swedish word translation and returns feedback.
     * Increments the total number of answers.
     * If the user's answer is correct, increments the number of correct answers and returns feedback.
     * If the user's answer is almost correct, returns feedback indicating the correct answer.
     * If the user's answer is incorrect, returns feedback indicating the correct answer.
     *
     * @param swedishWord the Swedish word to be translated
     * @param englishTranslation the user's attempt at the English translation
     * @return null (the return value is not used in the current implementation)
     */
    public boolean checkAnswer(String swedishWord, String englishTranslation) {
        String correctAnswer = vocabulary.getProperty(swedishWord);
        totalAnswers++;

        if (correctAnswer == null) {
            String correctTranslation = getCorrectTranslation(swedishWord);
            System.out.printf("Fel. Korrekt svar är %s.%n", correctTranslation);
            return false;
        }

        int numMatching = getNumMatchingLetters(englishTranslation, correctAnswer);
        int requiredMatching = (int) Math.ceil(correctAnswer.length() * 0.7);

        if (numMatching == correctAnswer.length() && englishTranslation.length() == correctAnswer.length()) {
            numCorrect++;
            System.out.printf("Korrekt! %d rätt av %d ord.%n", getNumCorrect(), getTotalAnswers());
            return true;
        } else if (numMatching >= requiredMatching && Math.abs(englishTranslation.length() - correctAnswer.length()) <= 2) {
            String correctTranslation = getCorrectTranslation(swedishWord);
            System.out.printf("Nästan rätt. Korrekt svar är %s.%n", correctTranslation);
        } else {
            String correctTranslation = getCorrectTranslation(swedishWord);
            System.out.printf("Fel. Korrekt svar är %s.%n", correctTranslation);
        }

        return false;
    }



    /**
     * Retrieves the next Swedish word from the vocabulary to be used in the game.
     * @return The next Swedish word to be used in the game, or null if all words have been learned.
     */
    public String getNextWord() {
        for (String swedishWord : vocabulary.stringPropertyNames()) {
            if (!vocabulary.getProperty(swedishWord).startsWith("*")) {
                return swedishWord;
            }
        }
        return null;
    }


    /**
     * Marks a word as learned by adding an asterisk (*) in front of its translation.
     * @param swedishWord the Swedish word to mark as learned.
     */
    public void markWordAsLearned(String swedishWord) {
        vocabulary.setProperty(swedishWord, "*" + vocabulary.getProperty(swedishWord));
    }

    /**
     * Returns the number of correct translations the user has given.
     * @return the number of correct translations.
     */
    public int getNumCorrect() {
        return numCorrect;
    }

    /**
     * Returns the total number of translations the user has attempted.
     * @return the total number of translations attempted.
     */
    public int getTotalAnswers() {
        return totalAnswers;
    }

    /**
     * Returns the correct translation of a given Swedish word.
     * @param swedishWord the Swedish word to retrieve the translation of.
     * @return the correct translation of the given word, or null if the word is not in the vocabulary.
     */
    public String getCorrectTranslation(String swedishWord) {
        return vocabulary.getProperty(swedishWord);
    }

    /**
     * Returns the number of matching letters between two strings.
     * @param inputWord the first string to compare.
     * @param correctAnswer the second string to compare.
     * @return the number of matching letters between the two strings.
     */
    private int getNumMatchingLetters(String inputWord, String correctAnswer) {
        int counter = 0;
        int minLength = Math.min(inputWord.length(), correctAnswer.length());
        for (int i = 0; i < minLength; i++) {
            if (inputWord.charAt(i) == correctAnswer.charAt(i)) {
                counter++;
            }
        }
        return counter;
    }
}


