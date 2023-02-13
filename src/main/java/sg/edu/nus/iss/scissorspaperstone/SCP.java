package sg.edu.nus.iss.scissorspaperstone;

import java.util.Random;

public class SCP {
    public int generateComputerchoice() {
        Random rand = new Random();
        return rand.nextInt(3);
    }

    public String checkWinner(int playerChoice, int computerChoice) {
        String winner = "";
        if (playerChoice == computerChoice) {
            winner = "tie game";
        } else if (playerChoice != 2 && playerChoice == computerChoice - 1){
            winner = "player wins !";
        } else if (playerChoice == 2 && computerChoice == 0) {
            winner = "player wins !";
        } else {
            winner = "computer wins !";
        }
        return winner;
    }
}
