package sg.edu.nus.iss.scissorspaperstone;

import java.util.Scanner;

public class SPSApp {
    public static void main (String[] args) {

        // keep track of the number of times the player and computer wins
        int playerWinCount = 0;
        int computerWinCount = 0;

        Scanner scanner = new Scanner(System.in);

        String playerSelectionStr = "";

        SCP scp = new SCP();

        String winner = "";

        while (true) {
            System.out.println("Enter (0) for scissors, (1) for paper or (2) for stone. Enter (Q) to quit application");
            playerSelectionStr = scanner.nextLine();

            if (!playerSelectionStr.equalsIgnoreCase("Q")) {
                int playerSelection = Integer.parseInt(playerSelectionStr);

                if (playerSelection >= 0 && playerSelection < 3) {
                    int computerSelection = scp.generateComputerchoice();
                    System.out.println(computerSelection);

                    winner = scp.checkWinner(playerSelection, computerSelection);
                    if (winner.contains("player")) {
                        playerWinCount++;
                    } else if (winner.contains("computer")) {
                        computerWinCount++;
                    }
                    System.out.println(winner);
                } else {
                    System.out.println("You have entered an invalid option.");
                    continue;
                }
            } else {
                break;
            }
        }

        System.out.printf("Player win count: %d\n", playerWinCount);
        System.out.printf("Computer win count: %d\n", computerWinCount);
        System.out.println(playerWinCount>computerWinCount?"Player wins...": "Computer wins...");

        scanner.close();
    }
}
