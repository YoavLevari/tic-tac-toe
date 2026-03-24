package view;

import javax.swing.*;
import java.awt.*;

/**
 * A Swing panel that displays the 3×3 Tic-Tac-Toe board.
 *
 * This panel is responsible only for:
 *  - creating and laying out the 9 CellButtons
 *  - forwarding button clicks to a MoveListener
 *  - updating button text when the board changes
 *
 * It contains no game logic and does not interact with the model directly.
 * The controller supplies a MoveListener to handle user actions.
 */
public class BoardPanel extends JPanel {

  /** The 3×3 grid of clickable buttons representing board cells. */
  private final CellButton[][] buttons = new CellButton[3][3];

  /** Listener that receives move events when a user clicks a cell. */
  private MoveListener listener;

  /**
   * Constructs the board panel and initializes all 9 CellButtons.
   * Each button knows its own (row, col) position and notifies the listener
   * when clicked.
   */
  public BoardPanel() {
    setLayout(new GridLayout(3, 3));

    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {

        CellButton btn = new CellButton(r, c);
        buttons[r][c] = btn;

        // When the button is clicked, notify the MoveListener
        btn.addActionListener(e -> {
          if (listener != null) {
            listener.onMove(btn.getRow(), btn.getCol());
          }
        });

        add(btn);
      }
    }
  }

  /**
   * Registers a MoveListener that will be notified whenever a cell is clicked.
   *
   * @param listener the listener to receive move events
   */
  public void setMoveListener(MoveListener listener) {
    this.listener = listener;
  }

  /**
   * Updates the text displayed on each button based on the board state.
   * The board is provided as a 3×3 array of Strings (e.g., "X", "O", or "").
   *
   * @param board a 3×3 String array representing the current board state
   */
  public void refresh(String[][] board) {
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        String val = board[r][c];
        buttons[r][c].setText(val == null ? "" : val);
      }
    }
  }
}
