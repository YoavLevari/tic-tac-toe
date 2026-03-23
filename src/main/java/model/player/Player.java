package model.player;

import model.XO;
import model.board.Cell;
import model.board.Coordinate;

/**
 * Represents a player in the Tic-Tac-Toe game.
 *
 * A Player may be either:
 *  - a human (using HumanStrat), or
 *  - an AI (using one of the AI strategies)
 *
 * The controller interacts with players only through this interface,
 * allowing different strategies to be swapped in without changing
 * the controller or model logic.
 */
public interface Player {

  /**
   * Determines the player's next move based on the current board state.
   *
   * For AI players, this method triggers the strategy's decision-making.
   * For human players, the strategy returns the move previously set
   * by the controller via HumanStrat.setNextMove().
   *
   * @param board the current game board as a 3×3 array of Cells
   * @return the chosen move as a Coordinate (row, col)
   */
  Coordinate playMove(Cell[][] board);

  /**
   * Initializes the player's assigned shape (X or O).
   * Called once at the start of the game.
   *
   * @param shape the XO value representing this player's symbol
   */
  void initShape(XO shape);

  /**
   * Returns the player's assigned shape (X or O).
   *
   * @return the XO symbol for this player
   */
  XO getShape();

  /**
   * Returns the strategy object that determines how this player chooses moves.
   *
   * This allows the controller to:
   *  - detect whether the player is human or AI
   *  - access strategy-specific behavior when needed
   *
   * @return the PlayerStrategy used by this player
   */
  PlayerStrategy getStrategy();
}
