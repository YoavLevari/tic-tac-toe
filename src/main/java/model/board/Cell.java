package model.board;

import model.XO;

/**
 * Represents a single square on the Tic-Tac-Toe board.
 * A cell may be empty or occupied by a player symbol (X or O).
 */
public interface Cell {

  /**
   * Returns the symbol occupying this cell.
   *
   * @return the XO value (X or O) if occupied, or null if the cell is empty.
   */
  XO getPlayer();

  /**
   * Marks this cell with the given player symbol.
   *
   * @param player the symbol (X or O) to place in this cell.
   * @throws IllegalArgumentException if the cell is already occupied
   *                                  or if the provided player is null.
   */
  void setPlayer(XO player) throws IllegalArgumentException;

  /**
   * Indicates whether this cell is currently unoccupied.
   *
   * @return true if no player has been assigned to this cell, false otherwise.
   */
  boolean isEmpty();
}