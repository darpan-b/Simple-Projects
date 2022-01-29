package TicTacToe;

import java.io.IOException;
import java.util.Random;

class Game {
    private InputReader inputReader;
    private Board board;
    private boolean multiplayer;
    private int autoPlayer;
    private int currentPlayer;
    
    public Game() {
        inputReader = new InputReader();
        board = new Board();
        multiplayer = true;
        autoPlayer = -1;
        currentPlayer = 1;
    }

    public void initGame() {
        System.out.println("WELCOME TO THIS GAME OF TIC-TAC-TOE!");
        System.out.println("---------------------------------------------------------------");
        System.out.println("DESCRIPTION:");
        System.out.println("Tic-Tac-Toe is a 2 player game where both player alternately");
        System.out.println("take turns to insert an element in a desired position in a");
        System.out.println("3x3 grid. The first player who inserts the same element in");
        System.out.println("all the cells of a single row, column or diagonal wins the");
        System.out.println("game. If no player succeeds in doing so, then the game ends");
        System.out.println("in a draw.");
        System.out.println("---------------------------------------------------------------");
        System.out.println("RULES:");
        System.out.println("1. You can play this game either in SINGLE PLAYER mode or");
        System.out.println("   in MULTI PLAYER mode. If you play in SINGLE PLAYER mode,");
        System.out.println("   then the moves of the other player will be auto-generated.");
        System.out.println("2. The first player to move uses the character 'X', while the");
        System.out.println("   second player uses the character 'O'.");
        System.out.println("3. If you play in SINGLE PLAYER mode, you can choose who will");
        System.out.println("   move first.");
        System.out.println("---------------------------------------------------------------");
        System.out.println("CELL NUMBERS:");
        System.out.println("|   |   |   |");
        System.out.println("| 1 | 2 | 3 |");
        System.out.println("|   |   |   |");
        System.out.println("---------------");
        System.out.println("|   |   |   |");
        System.out.println("| 4 | 5 | 6 |");
        System.out.println("|   |   |   |");
        System.out.println("---------------");
        System.out.println("|   |   |   |");
        System.out.println("| 7 | 8 | 9 |");
        System.out.println("|   |   |   |");
        System.out.println("---------------------------------------------------------------");
        System.out.println("ALL THE BEST! MAY THE BEST PLAYER WIN!\n\n");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Enter '1' if you want to play in SINGLE PLAYER mode, and '2' if");
        System.out.println("you want to play in MULTI PLAYER mode.");
        System.out.println("Enter your choice:");
        try {
            boolean choiceMade = false;
            do {
                int choice = inputReader.nextInt();
                if (choice == 1) {
                    multiplayer = false;
                    System.out.println("Enter '1' if you want to play as Player 1, or '2' if you want to");
                    System.out.println("play as Player 2.");
                    System.out.println("Enter your choice:");
                    boolean playerChosen = false;
                    do {
                        int playerChoice = inputReader.nextInt();
                        if (playerChoice == 1) {
                            autoPlayer = 2;
                            playerChosen = true;
                        }
                        else if (playerChoice == 2) {
                            autoPlayer = 1;
                            playerChosen = true;
                        }
                        else {
                            System.out.println("Invalid choice. Please re-enter your choice:");
                        }
                    } while(!playerChosen);
                    choiceMade = true;
                }
                else if (choice == 2) {
                    choiceMade = true;
                }
            } while (!choiceMade);
        } catch (IOException e) {
            System.err.println("IOException encountered while making a choice.");
        }
    }

    public void makeMove() {
        char element = (currentPlayer == 1 ? 'X' : 'O');
        int position = -1;
        if (!(!multiplayer && currentPlayer == autoPlayer)) {
            do {
                System.out.println("Player " + currentPlayer + ", please make your move.");
                System.out.println("Enter the position that you want to make your move in: ");
                try {
                    position = inputReader.nextInt();
                } catch (IOException e) {
                    System.err.println("IOException encountered while making a move.");
                }
            } while (!board.insertElement(element, position, false));
        }
        else {
            Random random = new Random();
            do {
                position = random.nextInt(9) + 1;
            } while (!board.insertElement(element, position, true));
        }
        if (currentPlayer == 1)
            currentPlayer++;
        else
            currentPlayer--;
    }

    public boolean isGameOver() {
        if (board.isWin()) {
            System.out.println("Game Over!");
            System.out.println("Congratulations!");
            if (currentPlayer == 1)
                currentPlayer++;
            else
                currentPlayer--;
            System.out.println("Player " + currentPlayer + " has WON the game!");
            return true;
        }
        if (board.isDraw()) {
            System.out.println("Game Over!");
            System.out.println("The game has ended in a DRAW!");
            return true;
        }
        return false;
    }

    public void run() {
        initGame();
        while (!isGameOver()) {
            makeMove();
            board.printBoard();
        }
    }
    
}

