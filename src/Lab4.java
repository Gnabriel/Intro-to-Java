import java.util.Map;
import java.util.HashMap;
import org.apache.commons.text.similarity.LevenshteinDistance;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Text-based glossary excercise program. The program will show a Swedish
 * word and let the user input the English translation of that word.
 * Then it will show if the user's translation was wrong, correct or
 * partially correct. A word is seen as partially correct if it is one
 * Levenshtein distance from the correct word. Once finished it will
 * display statistics of the user's score.
 * 
 * The glossary excercise if finished after 10 words or by pressing 'Q'.
 * 
 * Assignment 4 in the course Inledande programmering med Java VT2021.
 *
 * @author Gabriel Ellebrink
 */
public class Lab4 {

    /**
     * Class to create glossaries which can store words
     * along with its translation(s).
     */
    public static class Glossary {
        Map<String, List<String>> glossaryDict;
        List<String> glossaryWords;
        int currentWord;
        int wordCount;

        public Glossary() {
            this.glossaryDict = new HashMap<>();
            this.glossaryWords = new ArrayList<>();
            this.currentWord = 0;
            this.wordCount = 0;
        }

        /**
         * Adds a word with its translation(s) to the glossary.
         * 
         * @param word              word to be translated
         * @param translations      translation(s) of the word
         */
        public void addWord(String word, List<String> translations) {
            this.glossaryDict.put(word, translations);
            this.glossaryWords.add(word);
            this.wordCount++;
        }

        /**
         * Gets the next word in the glossary.
         * 
         * @return      next String in glossary
         */
        public String getNextWord() {
            String nextWord = "";
            if (this.currentWord <= this.wordCount) {
                nextWord = this.glossaryWords.get(this.currentWord);
                this.currentWord++;
            }
            return nextWord;
        }

        /**
         * Gets the correct translation(s) of a word in the glossary.
         * 
         * @return      correct translation(s) of a word
         */
        public List<String> getAnswer(String word) {
            return this.glossaryDict.get(word);
        }
        
        /**
         * Checks a user submitted translation against
         * the correct translations in the glossary.
         * 
         * @param word      word to be translated
         * @param guess     user submitted translation of the word
         * @return          1.0f if the guess is correct;
         *                  0.5f if the guess is almost correct,
         *                  i.e within 1 Levenshtein Distance;
         *                  0.0f otherwise
         */
        public float checkGuess(String word, String guess) {
            List<String> correctTranslations = this.glossaryDict.get(word);
            for (String translation : correctTranslations) {
                if (guess.equalsIgnoreCase(translation)) {
                    return 1.0f;
                }
                else if (partialCheck(translation, guess)) {
                    return 0.5f;
                }
            }
            return 0.0f;
        }

        /**
         * Checks a user submitted translation against
         * the correct translations in the glossary.
         * 
         * @param translation       correct translation
         * @param guess             user submitted translation of the word
         * @return                  true if guess is within 1 Levenshtein
         *                          Distance of the correct translation;
         *                          false otherwise
         */
        private static boolean partialCheck(String translation, String guess) {
            LevenshteinDistance stringComparator = LevenshteinDistance.getDefaultInstance();
            if (stringComparator.apply(translation, guess) == 1) {
                return true;
            }
            return false;
        }
    }

    /**
     * Class to create translations excercises by building
     * glossaries and getting user inputs and validating them.
     */
    public static class TranslationExcercise {
        Glossary glossary;
        Scanner scanner;

        public TranslationExcercise() {
            this.glossary = new Glossary();
            this.scanner = new Scanner(System.in);
            buildGlossary();
        }

        /**
         * Creates a glossary by adding words with its translation(s).
         */
        public void buildGlossary() {
            this.glossary.addWord("bil", Arrays.asList("car"));
            this.glossary.addWord("hus", Arrays.asList("house"));
            this.glossary.addWord("springa", Arrays.asList("run"));
            this.glossary.addWord("blå", Arrays.asList("blue"));
            this.glossary.addWord("baka", Arrays.asList("bake"));
            this.glossary.addWord("hoppa", Arrays.asList("jump"));
            this.glossary.addWord("simma", Arrays.asList("swim"));
            this.glossary.addWord("måne", Arrays.asList("moon"));
            this.glossary.addWord("väg", Arrays.asList("road"));
            this.glossary.addWord("snäll", Arrays.asList("kind"));
        }

        /**
         * Gets an input from the user.
         * @param prompt        user instructions for the input
         * @return              String provided by the user
         */
        public String getInput(String prompt) {
            String userInput;
            while (true) {
                // Print input instructions.
                System.out.println(prompt);
                try {
                    // Get input.
                    userInput = scanner.nextLine();
                    break;
                }
                // Catch error and print error message.
                catch (Exception e) {
                    System.out.println("Ogiltigt värde. Ange ett heltal.");
                    this.scanner.nextLine();
                }
            }
            return userInput;
        }

        /**
         * Starts the glossary excercise for English words.
         */
        public void englishExcercise() {
            System.out.println("** GLOSÖVNING - ENGELSKA **");
            System.out.println("Skriv det engelska ordet. Avsluta programmet genom att skriva Q.");
            String userInput;
            List<String> answer;
            float guessResult;
            int numGuesses = 0;
            int numCorrectGuesses = 0;
            for (int i = 0; i < this.glossary.wordCount; i++) {
                String word = this.glossary.getNextWord();
                userInput = getInput(String.format("%s : ", word));
                guessResult = this.glossary.checkGuess(word, userInput);
                answer = this.glossary.getAnswer(word);

                if (userInput.equalsIgnoreCase("Q")) {
                    break;
                }

                numGuesses++;

                if (guessResult == 1f) {
                    numCorrectGuesses++;
                    System.out.println(String.format("Korrekt! %s rätt av %s ord.", numCorrectGuesses, numGuesses));                    
                }
                else if (guessResult == 0.5f) {
                    System.out.println(String.format("Nästan rätt. Korrekt svar är %s.", answer.get(0)));
                }
                else {
                    System.out.println(String.format("Fel. Korrekt svar är %s.", answer.get(0)));
                }
            }
            System.out.println(String.format("Du svarade på totalt %s glosor och hade %s rätt. Välkommen åter!", numCorrectGuesses, numGuesses));    
            this.scanner.close();
        }
    }

    /**
     * Creates a TranslationExcercise instance and runs the glossary excercise.
     */
    public static void main(String[] args) {
        TranslationExcercise excercise = new TranslationExcercise();
        excercise.englishExcercise();
    }
}
