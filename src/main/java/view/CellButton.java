package view;

import javax.swing.JButton;
import java.awt.Font;

/**
 * A specialized JButton representing a single cell on the Tic-Tac-Toe board.
 *
 * Each CellButton knows its own row and column position, allowing the GUI
 * controller to easily determine which board cell was clicked without
 * storing extra mapping structures.
 *
 * This class contains no game logic; it is purely a view component.
 */
public class CellButton extends JButton {

  /** The row index of this button within the board (0–2). */
  private final int row;

  /** The column index of this button within the board (0–2). */
  private final int col;

  /**
   * Creates a button representing a specific board cell.
   *
   * @param row the row index of the cell this button represents
   * @param col the column index of the cell this button represents
   */
  public CellButton(int row, int col) {
    this.row = row;
    this.col = col;

    // Large, bold font so X/O symbols are clearly visible in the GUI
    setFont(new Font("Arial", Font.BOLD, 40));
  }

  /**
   * Returns the row index associated with this button.
   *
   * @return the row index (0–2)
   */
  public int getRow() {
    return row;
  }

  /**
   * Returns the column index associated with this button.
   *
   * @return the column index (0–2)
   */
  public int getCol() {
    return col;
  }
}
