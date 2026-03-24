package model.player;

import model.XO;
import model.board.Cell;
import model.board.Coordinate;

/**
 * Represents a strategy used by a Player to choose its next move.
 *
 * This interface is the core of the Strategy Pattern in the game:
 *  - HumanStrat simply returns the move provided by the controller.
 *  - AI strategies (Easy, Medium, Hard) analyze the board and compute a move.
 *
 * The controller does not care *how* a move is chosen — it only calls this
 * method and receives a Coordinate in return.
 */
public interface PlayerStrategy {

  /**
   * Determines the next move for the player based on the current board state.
   *
   * @param currentBoard the current game board as a 3×3 array of Cells.
   *                     Each Cell contains its own XO value (or null if empty).
   *
   * @param playerShape  the XO symbol assigned to the player using this strategy.
   *
   * @return a Coordinate representing the chosen move (row, col).
   *
   * Implementations:
   *  - HumanStrat: returns the move set by the controller.
   *  - EasyStrat: random or simple heuristic.
   *  - MediumStrat: smarter heuristics or partial lookahead.
   *  - HardStrat: optimal play (e.g., minimax).
   */
  Coordinate chooseMove(Cell[][] currentBoard, XO playerShape);
}
