package model.board;

/**
 * Immutable value object representing a position on the game board.
 *
 * A Coordinate simply stores a row and column index.
 * It contains no game logic and cannot be modified after creation.
 */
public class Coordinate {

  /** The row index of this coordinate (0–2). */
  private final int row;

  /** The column index of this coordinate (0–2). */
  private final int column;

  /**
   * Creates a new immutable coordinate.
   *
   * @param row    the row index
   * @param column the column index
   */
  public Coordinate(int row, int column) {
    this.row = row;
    this.column = column;
  }

  /** @return the row index of this coordinate */
  public int getRow() { return row; }

  /** @return the column index of this coordinate */
  public int getColumn() { return column; }
}
