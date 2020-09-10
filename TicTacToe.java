import java.util.Scanner;

public class TicTacToe {
    static Scanner s = new Scanner(System.in);

    static String[] board = new String[9];
    static String winner = null;
    static String turn = "X";
    static int input;

    static int xWins = 0;
    static int oWins = 0;

    public static void main(String[] args) {
        introMain();
    }

    //-----------------
    // game modes
    //-----------------
    static void easyMode() {

    }

    static void hardMode() {

    }

    static void multiplayerMode() {
        assignSlots();
        while(winner == null) {
            userInput();

            // checks if the number is valid
            if(input < 1 || input > 9) {
                System.out.println("Invalid Input");
                System.out.println("Choose a number 1-9");
                updateBoard();
                continue;
            }
            // checks if X or O is already in spot
            else if(board[input-1].equals("X") || board[input-1].equals("O")) {
                System.out.println("Spot already taken");
                System.out.println("Choose a number 1-9");
                updateBoard();
                continue;
            }
            // if the number is valid and spot not taken then place the character in array
            else {
                board[input-1] = turn;
                changeTurn();
                winner = checkForWinner();
                updateBoard();
            }

            win();
            tie();
        }
    }

    //-----------------
    // intro parts
    //-----------------
    static void introMain() {
        String gameType;

        System.out.println();
        System.out.println("Welcome to Tic-Tac-Toe");
        System.out.println("Do you want to play single or multiplayer?");
        System.out.print("Enter 1 or 2: ");
        gameType = s.nextLine();
        switch(gameType) {
            case "1":
                introSingleplayer();
                break;
            case "2":
                introMultiplayer();
                break;
            default:
                System.out.println("Not a valid input");
                break;
        }
    }

    static void introSingleplayer() {
        String gameMode;

        System.out.println();
        System.out.println("Welcome to Singleplayer Tic-Tac-Toe");
        System.out.println("Do you want to play easy or hard?");
        System.out.print("Enter E or H: ");
        gameMode = s.nextLine();
        switch(gameMode) {
            case "E": case "e":
                easyMode();
                break;
            case "H": case "h":
                hardMode();
                break;
            default:
                System.out.println("Not a valid input");
                break;
        }
    }

    static void introMultiplayer() {
        System.out.println();
        System.out.println("Welcome to Multiplayer Tic-Tac-Toe");
        multiplayerMode();
    }

    //-----------------
    // extra stuff
    //-----------------
    static String checkForWinner() {
        String check = null;
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0: // top row
                    check = board[0] + board[1] + board[2];
                    break;
                case 1: // middle row
                    check = board[3] + board[4] + board[5];
                    break;
                case 2: // bottom row
                    check = board[6] + board[7] + board[8];
                    break;
                case 3: // left column
                    check = board[0] + board[3] + board[6];
                    break;
                case 4: // middle column
                    check = board[1] + board[4] + board[7];
                    break;
                case 5: // right column
                    check = board[2] + board[5] + board[8];
                    break;
                case 6: // TL to BR diagonal
                    check = board[0] + board[4] + board[8];
                    break;
                case 7: // TR to BL diagonal
                    check = board[2] + board[4] + board[6];
                    break;
            }

            // checks if the case is either XXX or OOO
            if (check.equals("XXX")) {
                xWins++;
                return "X";
            } else if (check.equals("OOO")) {
                oWins++;
                return "O";
            }
        }
        return null;
    }

    static void tie() {
        // if all slots are covered (tie)
        for(int i = 0; i < 9; i++) {
            if(board[i].equals("X") || board[i].equals("O")) {
                if(i == 8) {
                    System.out.println("It was a tie");
                    System.out.println("Thanks for playing");
                    playAgain();
                }
            } else {
                break;
            }
        }
    }

    static void win() {
        // if there is a winner then do winning stuff
        if(winner != null) {
            System.out.println("The winner is " + winner);
            System.out.println("Thanks for playing");
            playAgain();
        }
    }

    static void userInput() {
        System.out.print("It is " + turn + "'s turn. Choose a number 1-9:");
        input = s.nextInt();
        s.nextLine();
    }

    static void changeTurn() {
        if(turn.equals("X")) {
            turn = "O";
        } else {
            turn = "X";
        }
    }

    static void updateBoard() {
        System.out.println();
        System.out.printf("             %s | %s | %s\n", board[0], board[1], board[2]);
        System.out.println("            ----------          X Wins: " + xWins);
        System.out.printf("             %s | %s | %s\n", board[3], board[4], board[5]);
        System.out.println("            ----------          O Wins: " + oWins);
        System.out.printf("             %s | %s | %s\n", board[6], board[7], board[8]);
        System.out.println();
    }

    static void assignSlots() {
        for(int i = 0; i < 9; i++) {
            board[i] = String.valueOf(i + 1);
        }
        updateBoard();
    }

    static void playAgain() {
        System.out.println("Do you want to play Tic-Tac-Toe again");
        System.out.print("Enter Y or N: ");
        String play = s.nextLine();
        if(play.equals("Y") || play.equals("y")) {
            changeTurn();
            winner = null;
            multiplayerMode();
        } else if(play.equals("N") || play.equals("n")) {
            System.exit(0);
        }
    }
}
