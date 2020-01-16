import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Hangman {

    private String word;
    private int penaltyPoints = 0;
    private List<String> fullWord = new ArrayList<>();
    private List<String> blankWord = new ArrayList<>();

    Hangman () {
        ComputerWordReader computerWordReader = new ComputerWordReader();
        this.word = computerWordReader.getRandomWord();
    }
    Hangman (String word) {
        this.word = word;
    }

    void playGameAgainstComputer() {
        System.out.println("Welcome to Hangman game!");
        convertWordToStringList();
        System.out.println(createBlankWord());
        while (!isWin() && penaltyPoints != 12) {
            System.out.println("Insert your letter!");
            String chosenLetter = Player.insertLetter();
            insertPlayerLetterInBlankWord(chosenLetter);
            System.out.println("Your word: " + blankWord);
            System.out.println("You have " + (12 - penaltyPoints) + " tries left!");
        }
        if (isWin()) {
            System.out.println("WIN!");
        } else {
            System.out.println("Lost");
            Board.printHangman();
            System.out.println("Word: " + word);
        }


    }

    void playGameAgainstPlayer() {
        System.out.println("Welcome to Hangman game!");
        convertWordToStringList();
        System.out.println(createBlankWord());
        while (!isWin() && penaltyPoints != 12) {
            System.out.println("Insert your letter!");
            String chosenLetter = Player.insertLetter();
            insertPlayerLetterInBlankWord(chosenLetter);
            System.out.println("Your word: " + blankWord);
            System.out.println("You have " + (12 - penaltyPoints) + " tries left!");
        }
        if (isWin()) {
            System.out.println("WIN!");
        } else {
            System.out.println("Lost");
            Board.printHangman();
            System.out.println("Word: " + word);
        }
    }

    List<String> convertWordToStringList() {
        List<Character> letters = new ArrayList<>();
        for (Character letter : word.toCharArray()) {
            letters.add(letter);
        }
        fullWord = letters.stream().map(Object::toString).collect(Collectors.toList());
        return fullWord;
    }

    List<String> createBlankWord() {
        String underscore = "_";
        for (int i = 0; i < fullWord.size(); i++) {
            blankWord.add(i, underscore);
        }
        return blankWord;
    }

    boolean isWin() {
        return blankWord.containsAll(fullWord) && fullWord.containsAll(blankWord);
    }

    List<String> insertPlayerLetterInBlankWord(String playerLetter) {
        if (isLetterInWord(playerLetter)) {
            System.out.println("GOOD SHOT!");
            for (int i = 0; i < fullWord.size(); i++) {
                if (fullWord.get(i).equalsIgnoreCase(playerLetter)) {
                    blankWord.set(i, playerLetter);
                }
            }
        }
        if (!isLetterInWord(playerLetter)) {
            addPenaltyPoint();
        }
        return blankWord;
    }

    boolean isLetterInWord(String playerLetter) {
        return fullWord.stream().anyMatch(letter -> letter.equals(playerLetter));
    }

    void addPenaltyPoint() {
        System.out.println("MISS!");
        ++penaltyPoints;
    }

    void setWord(String word) {
        this.word = word;
    }

    String getWord() {
        return word;
    }

}
