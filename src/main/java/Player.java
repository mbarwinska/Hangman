import java.util.Scanner;

class Player {
    private String playerLetter;

    String insertLetter() {
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

    String getPlayerLetter() {
        return playerLetter;
    }

    void setPlayerLetter(String playerLetter) {
        this.playerLetter = playerLetter;
    }

}
