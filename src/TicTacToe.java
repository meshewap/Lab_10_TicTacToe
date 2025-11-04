import java.util.Scanner;

public class TicTacToe {

    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static String board[][] = new String[ROWS][COLS];
    private static String currentPlayer = "X";
    private static int moveCount = 0;
    private static Scanner in = new Scanner(System.in);


    public static void main(String[] args) {
        boolean playAgain;

        do {
            clearBoard();
            currentPlayer = "X";
            moveCount = 0;
            boolean gameWon = false;
            boolean gameTied = false;

            System.out.println("Welcome to Tic-Tac-Toe");
            display();

            while (!gameWon && !gameTied) {
                int rowMove;
                int colMove;
                boolean validMove;

                // 1. Get coordinates for the move
                do {
                    rowMove = SafeInput.getRangedInt(in, "Player " + currentPlayer + ", enter row", 1, 3);
                    colMove = SafeInput.getRangedInt(in, "Player " + currentPlayer + ", enter column", 1, 3);

                    // 2. Convert to 0-2 for array indices
                    rowMove--;
                    colMove--;

                    // 3. Check if move is valid
                    validMove = isValidMove(rowMove, colMove);
                    if (!validMove) {
                        System.out.println("Invalid move. That cell is already taken. Try again.");
                    }

                } while (!validMove); // 4. Loop until coordinates are valid

                // 5. Record the valid move
                board[rowMove][colMove] = currentPlayer;
                moveCount++;

                // 6. Display the board after the move
                display();

                // 7. Check for win or tie
                if (moveCount >= 5) {
                    if (isWin(currentPlayer)) {
                        gameWon = true;
                        System.out.println("Player " + currentPlayer + " wins!");
                    }
                }
                if (!gameWon && moveCount >= 7) {
                    if (isTie()) {
                        gameTied = true;
                        System.out.println("It's a tie game!");
                    }
                }

                // 8. Change the player
                if (!gameWon && !gameTied) {
                    if (currentPlayer.equals("X")) {
                        currentPlayer = "O";
                    } else {
                        currentPlayer = "X";
                    }
                }
            }

            // 9. Prompt to play again
            playAgain = SafeInput.getYNConfirm(in, "Do you want to play again?");

        } while (playAgain);

        System.out.println("Thanks for playing!");
    }


    /**
     * Sets all elements of the board to a space " ".
     */
    private static void clearBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board[r][c] = " ";
            }
        }
    }

    /**
     * Displays the current state of the Tic-Tac-Toe board.
     */
    private static void display() {
        System.out.println("-------------");
        for (int r = 0; r < ROWS; r++) {
            System.out.print("| ");
            for (int c = 0; c < COLS; c++) {
                System.out.print(board[r][c] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    /**
     * Checks if a proposed move is legal.
     *
     * @param row The row index (0-2) of the move.
     * @param col The column index (0-2) of the move.
     * @return true if the cell at [row][col] is a space " ", false otherwise.
     */
    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    /**
     * Checks if the specified player has won the game.
     *
     * @param player The player to check for a win ("X" or "O").
     * @return true if the player has won, false otherwise.
     */
    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagnalWin(player);
    }

    /**
     * Checks for a column win for the specified player.
     *
     * @param player The player to check ("X" or "O").
     * @return true if any column is filled by the player, false otherwise.
     */
    private static boolean isColWin(String player) {
        for (int c = 0; c < COLS; c++) {
            if (board[0][c].equals(player) &&
                    board[1][c].equals(player) &&
                    board[2][c].equals(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for a row win for the specified player.
     *
     * @param player The player to check ("X" or "O").
     * @return true if any row is filled by the player, false otherwise.
     */
    private static boolean isRowWin(String player) {
        for (int r = 0; r < ROWS; r++) {
            if (board[r][0].equals(player) &&
                    board[r][1].equals(player) &&
                    board[r][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks for a diagonal win for the specified player.
     *
     * @param player The player to check ("X" or "O").
     * @return true if either diagonal is filled by the player, false otherwise.
     */
    private static boolean isDiagnalWin(String player) {
        if (board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player)) {
            return true;
        }
        if (board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player)) {
            return true;
        }
        return false;
    }

    /**
     * Checks for a tie game. A tie occurs if all 8 win vectors are "blocked"
     * (contain both an "X" and an "O"), or if the board is full.
     * This method checks the "blocked vector" condition, which also covers
     * a full board with no winner.
     *
     * @return true if the game is a tie, false otherwise.
     */
    private static boolean isTie() {

        // Check rows
        if (isVectorOpen(board[0][0], board[0][1], board[0][2])) return false;
        if (isVectorOpen(board[1][0], board[1][1], board[1][2])) return false;
        if (isVectorOpen(board[2][0], board[2][1], board[2][2])) return false;

        // Check columns
        if (isVectorOpen(board[0][0], board[1][0], board[2][0])) return false;
        if (isVectorOpen(board[0][1], board[1][1], board[2][1])) return false;
        if (isVectorOpen(board[0][2], board[1][2], board[2][2])) return false;

        // Check diagonals
        if (isVectorOpen(board[0][0], board[1][1], board[2][2])) return false;
        if (isVectorOpen(board[0][2], board[1][1], board[2][0])) return false;

        return true;
    }

    /**
     * Helper method for isTie().
     * A win vector is "open" (a win is still possible) if it does NOT
     * contain *both* an 'X' and an 'O'.
     *
     * @param s1 Cell 1
     * @param s2 Cell 2
     * @param s3 Cell 3
     * @return true if the vector is open, false if it's blocked.
     */
    private static boolean isVectorOpen(String s1, String s2, String s3) {
        boolean hasX = s1.equals("X") || s2.equals("X") || s3.equals("X");
        boolean hasO = s1.equals("O") || s2.equals("O") || s3.equals("O");
        return !(hasX && hasO);
    }
}