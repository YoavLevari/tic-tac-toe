package model.board;

import model.XO;

/**
 * A simple implementation of the Cell interface.
 *
 * Each BasicCell stores a single XO value (X, O, or null).
 * Cells start empty and may be marked exactly once.
 * Attempting to overwrite a non-empty cell or assign null is illegal.
 */
public class BasicCell implements Cell {

  /** The player symbol occupying this cell, or null if the cell is empty. */
  private XO player;

  /** Creates an empty cell. */
  public BasicCell() {
    this.player = null;
  }

  /**
   * Returns the symbol occupying this cell.
   *
   * @return X or O if occupied, or null if empty
   */
  @Override
  public XO getPlayer() {
    return player;
  }

  /**
   * Marks this cell with the given player symbol.
   *
   * @param player the symbol to place (X or O)
   * @throws IllegalArgumentException if:
   *         - the cell is already occupied, or
   *         - the provided player is null
   */
  @Override
  public void setPlayer(XO player) throws IllegalArgumentException {
    if (this.player != null || player == null) {
      throw new IllegalArgumentException("Cell already occupied or invalid player.");
    }
    this.player = player;
  }

  /**
   * Indicates whether this cell is currently unoccupied.
   *
   * @return true if empty, false if already marked
   */
  @Override
  public boolean isEmpty() {
    return this.player == null;
  }
}
