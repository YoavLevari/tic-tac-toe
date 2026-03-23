package model.player;

import model.XO;
import model.board.Cell;
import model.board.Coordinate;

/**
 * A concrete implementation of the Player interface.
 *
 * A BasicPlayer is simply:
 *  - a PlayerStrategy (Human, Easy, Medium, Hard)
 *  - plus an assigned XO shape (X or O)
 *
 * The strategy determines how moves are chosen.
 * The BasicPlayer acts as a wrapper that:
 *  - stores the player's shape
 *  - delegates move selection to the strategy
 */
public class BasicPlayer implements Player {

  /** The strategy that determines how this player chooses moves. */
  private final PlayerStrategy strategy;

  /** The XO symbol assigned to this player (X or O). */
  private XO shape;

  /**
   * Creates a BasicPlayer with the given strategy.
   *
   * @param strategy the strategy used to choose moves
   * @throws IllegalArgumentException if strategy is null
   */
  public BasicPlayer(PlayerStrategy strategy) {
    if (strategy == null) {
      throw new IllegalArgumentException("Strategy cannot be null");
    }
    this.strategy = strategy;
    this.shape = null; // Assigned later by initShape()
  }

  /**
   * Delegates move selection to the player's strategy.
   *
   * @param board the current game board as a 3×3 array of Cells
   * @return the move chosen by the strategy
   */
  @Override
  public Coordinate playMove(Cell[][] board) {
    return strategy.chooseMove(board, getShape());
  }

  /**
   * Assigns the player's XO symbol (X or O).
   *
   * @param shape the XO symbol for this player
   */
  @Override
  public void initShape(XO shape) {
    this.shape = shape;
  }

  /**
   * Returns the player's XO symbol.
   *
   * @return the XO symbol for this player
   */
  @Override
  public XO getShape() {
    return shape;
  }

  /**
   * Returns the strategy used by this player.
   *
   * @return the PlayerStrategy instance
   */
  @Override
  public PlayerStrategy getStrategy() {
    return strategy;
  }
}
