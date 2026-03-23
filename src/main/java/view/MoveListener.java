package view;

/**
 * A callback interface used by the GUI to notify the controller
 * when the user clicks a cell on the Tic-Tac-Toe board.
 *
 * Implementations of this interface define how the application
 * should respond to a move request (e.g., forwarding it to the model,
 * validating turn order, or triggering AI behavior).
 *
 * This interface is intentionally minimal so the view remains
 * decoupled from the game logic and model.
 */
public interface MoveListener {

  /**
   * Called when the user selects a cell on the board.
   *
   * @param row the row index of the clicked cell (0–2)
   * @param col the column index of the clicked cell (0–2)
   */
  void onMove(int row, int col);
}
