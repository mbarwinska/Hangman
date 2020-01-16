import java.util.Scanner;

class Player {
    private static String playerLetter;
    private String wordForOpponent;
    private String gameMode;

    Player() {
        chooseGameMode();
    }

    private void chooseGameMode() {
        System.out.println("Do you wish to play against computer or other player?");
        System.out.println("1 - computer, 2 - other player");
        Scanner in = new Scanner(System.in);
        gameMode = in.nextLine();
    }

    void switchGameMode() {
        switch (gameMode) {
            case "1":
                Hangman hangmanComputer = new Hangman();
                hangmanComputer.playGameAgainstComputer();
                break;
            case "2":
                typeWordForOpponent();
                Hangman hangmanPlayer = new Hangman(wordForOpponent);
                hangmanPlayer.playGameAgainstPlayer();
                break;
        }
    }

    private void typeWordForOpponent() {
        System.out.println("Type word for your opponent!");
        Scanner scanner = new Scanner(System.in);
        wordForOpponent = scanner.nextLine();
    }

    static String insertLetter() {
        Scanner scanner = new Scanner(System.in);
        boolean keepInserting = true;
        while (keepInserting) {
            playerLetter = scanner.nextLine();
            if (playerLetter.isEmpty()) {
                System.out.println("You did not insert anything! Please enter a letter!");
            }
            keepInserting = false;
        }
        return playerLetter;
    }


}
