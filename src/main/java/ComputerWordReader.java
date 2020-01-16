import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ComputerWordReader {

    private static final String WORDS_FILE = "words.txt";
    private List<String> words;
    private String randomWord;

     ComputerWordReader() {
        readWords();
        drawRandomWord();
    }

    void readWords () {
        words = new ArrayList<>();
        try {
            words = Files.readAllLines(Paths.get(WORDS_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void drawRandomWord() {
        Random random = new Random();
        randomWord = words.get(random.nextInt(words.size()));
    }

     String getRandomWord() {
        return randomWord;
    }
}
