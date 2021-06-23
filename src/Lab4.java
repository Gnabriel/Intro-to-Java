import java.util.Map;
import java.util.HashMap;
import org.apache.commons.text.similarity.LevenshteinDistance;
import java.util.Arrays;

public class Lab4 {
    public class Glossary {
        Map<String, String[]> glossaryDict;

        public Glossary() {
            /**
             * Class to create glossaries which can store words
             * along with its translation(s).
             */
            this.glossaryDict = new HashMap<>();
        }

        public void addWord(String word, String[] translations) {
            /**
             * Adds a word with its translation(s) to the glossary.
             * 
             * @param word              word to be translated
             * @param translations      translation(s) of the word
             */
            this.glossaryDict.put(word, translations);
        }
        
        public float checkGuess(String word, String guess) {
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
            String[] correctTranslations = this.glossaryDict.get(word);
            for (String translation : correctTranslations) {
                if (guess == translation) {
                    return 1.0f;
                }
                else if (partialCheck(translation, guess)) {
                    return 0.5f;
                }
            }
            return 0.0f;
        }

        private static boolean partialCheck(String translation, String guess) {
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
            LevenshteinDistance stringComparator = LevenshteinDistance.getDefaultInstance();
            if (stringComparator.apply(translation, guess) == 1) {
                return true;
            }
            return false;
        }
    }

    public class TranslationExcercise {
        Glossary glossary;

        public TranslationExcercise() {
            glossary = new Glossary();

            // glossary.addWord("bil", Arrays.asList("car"));
            // TODO: Fortsätt här!
        }
    }
    public static void main(String[] args) {
        System.out.println("test");
    }
}


