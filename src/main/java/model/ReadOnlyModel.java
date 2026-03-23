package model;

import model.board.Cell;
import model.XO;

/**
 * Represents a read-only view of the Tic-Tac-Toe game state.
 * This interface exposes only the information needed by views
 * (GUI, console, or AI) without allowing any modification of the game.
 *
 * Implementations of this interface guarantee that callers can safely
 * inspect the board, the current turn, and the winner (if any),
 * without risking accidental changes to the underlying model.
 */
public interface ReadOnlyModel {

  /**
   * Returns the current state of the game board.
   * The returned array provides read-only access to each Cell.
   *
   * @return a 2D array of Cells representing the board.
   *         Each Cell may be empty or occupied by X or O.
   */
  Cell[][] getBoard();

  /**
   * Returns the winner of the game, if one exists.
   *
   * @return the XO value representing the winning player,
   *         or null if there is no winner yet or the game is a draw.
   */
  XO getWinner();

  /**
   * Returns the player whose turn it currently is.
   *
   * @return the XO value (X or O) representing the active player.
   */
  XO getTurn();
}
