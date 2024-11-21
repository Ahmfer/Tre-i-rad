import java.util.Scanner;

public class TicTacToe {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Scanner scanner;

    public TicTacToe() {
        board = new Board();
        player1 = new Player('X');
        player2 = new Player('O');
        currentPlayer = player1;
        scanner = new Scanner(System.in);
    }

    public void play() {
        boolean playing = true;

        while (playing) {
            board.resetBoard(); // Reset the board for a new game
            boolean gameInProgress = true;

            while (gameInProgress) {
                board.printBoard();
                boolean validMove = false;

                while (!validMove) {
                    System.out.println("Player " + currentPlayer.getMark() + ", enter your move (row and column: 1-3): ");
                    int row = scanner.nextInt() - 1;
                    int col = scanner.nextInt() - 1;
                    validMove = board.makeMove(row, col, currentPlayer.getMark());
                    if (!validMove) {
                        System.out.println("Invalid move, try again.");
                    }
                }

                // Check if the current player has won
                if (board.checkWin(currentPlayer.getMark())) {
                    board.printBoard();
                    System.out.println("Player " + currentPlayer.getMark() + " wins!");
                    gameInProgress = false; // End the current game
                }
                // Check if the board is full (draw)
                else if (board.isFull()) {
                    board.printBoard();
                    System.out.println("The game is a draw!");
                    gameInProgress = false; // End the current game
                } else {
                    switchPlayer(); // Switch to the next player
                }
            }

            // Ask the players if they want to play again
            System.out.println("Do you want to play again? (yes/no): ");
            String answer = scanner.next();
            if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
                playing = false; // Exit the game loop
            } else {
                currentPlayer = player1; // Reset the current player for the next game
            }
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.play();
    }
}
