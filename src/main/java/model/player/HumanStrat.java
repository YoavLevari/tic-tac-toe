package model.player;

import model.XO;
import model.board.Cell;
import model.board.Coordinate;

/**
 * Strategy used by a human player.
 *
 * This strategy does not compute moves on its own.
 * Instead, the controller sets the next move when the user clicks a cell
 * in the GUI. The strategy simply returns that move when asked.
 *
 * This cleanly separates GUI input from game logic and keeps the
 * controller–strategy interaction consistent with AI strategies.
 */
public class HumanStrat implements PlayerStrategy {

  /** The move selected by the human via the GUI. */
  private Coordinate nextMove;

  /**
   * Called by the controller when the user clicks a cell.
   *
   * @param move the coordinate chosen by the human player
   */
  public void setNextMove(Coordinate move) {
    this.nextMove = move;
  }

  /**
   * Returns the move previously set by the controller.
   *
   * For human players:
   *  - The controller waits for a GUI click.
   *  - When the user clicks, the controller calls setNextMove().
   *  - Then the controller calls playMove(), which triggers this method.
   *
   * @param currentBoard the current board (unused for humans)
   * @param playerShape  the player's XO symbol (unused for humans)
   * @return the move selected by the human
   */
  @Override
  public Coordinate chooseMove(Cell[][] currentBoard, XO playerShape) {
    return nextMove;
  }
}
