package model;

/**
 * Represents the full mutable Tic-Tac-Toe game model.
 * This interface defines all operations that modify game state,
 * such as playing moves and advancing turns.
 *
 * A TikTackToeModel tracks:
 *  - the current player
 *  - the board state
 *  - whether the game has ended
 *
 * Read-only access to the board and game status is provided
 * through the ReadOnlyModel interface.
 */
public interface TicTacToeModel extends ReadOnlyModel {

  /**
   * Switches the active player from X to O or from O to X.
   * This method should be called after a successful move.
   */
  void changeTurn();

  /**
   * Attempts to play a move at the specified board position.
   *
   * @param row    the row index of the desired move (0-based)
   * @param column the column index of the desired move (0-based)
   *
   * @throws IllegalArgumentException if:
   *         - the position is outside the board bounds
   *         - the cell at (row, column) is already occupied
   *         - the game is already over
   */
  void playTurn(int row, int column) throws IllegalArgumentException;

  /**
   * Determines whether the game has reached a terminal state.
   * A game is over if:
   *  - a player has won, or
   *  - the board is full (draw)
   *
   * @return true if the game has ended, false otherwise.
   */
  boolean isGameOver();
}
