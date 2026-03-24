package view;

import javax.swing.*;

/**
 * The main GUI window for the Tic-Tac-Toe game.
 *
 * This class is responsible for:
 *  - creating the application window
 *  - containing the BoardPanel (the 3×3 grid of buttons)
 *  - forwarding user move events to the controller
 *  - displaying board updates, winners, and error messages
 *
 * The view does not contain any game logic. It simply displays
 * information provided by the controller and notifies the controller
 * when the user interacts with the board.
 */
public class TicTacToeView extends JFrame {

  /** The panel that displays the 3×3 grid of clickable cells. */
  private final BoardPanel boardPanel;

  /** Listener provided by the controller to handle user moves. */
  private MoveListener listener;

  /**
   * Constructs the main game window and initializes the board panel.
   */
  public TicTacToeView() {
    setTitle("Tic Tac Toe");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(400, 400);

    boardPanel = new BoardPanel();
    add(boardPanel);

    setVisible(true);
  }

  /**
   * Registers the controller as the move listener.
   * The controller will be notified whenever the user clicks a cell.
   *
   * @param listener the listener that handles move events
   */
  public void setMoveListener(MoveListener listener) {
    this.listener = listener;
    boardPanel.setMoveListener(listener);
  }

  /**
   * Updates the board display using a 3×3 array of Strings.
   * Each entry should be "X", "O", or "".
   *
   * @param board the board state as a 3×3 String array
   */
  public void refresh(String[][] board) {
    boardPanel.refresh(board);
  }

  /**
   * Displays a popup announcing the winner.
   *
   * @param winner the winning player ("X", "O", or "Tie")
   */
  public void showWinner(String winner) {
    JOptionPane.showMessageDialog(this, "Winner: " + winner);
  }

  /**
   * Displays an error popup (e.g., invalid move).
   *
   * @param msg the error message to display
   */
  public void showError(String msg) {
    JOptionPane.showMessageDialog(this, msg);
  }
}
