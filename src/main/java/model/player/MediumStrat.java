package model.player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import model.XO;
import model.board.Cell;
import model.board.Coordinate;

/**
 * Medium‑difficulty AI strategy.
 *
 * Behavior:
 *  1. If the AI can win immediately → take the winning move.
 *  2. Else if the opponent can win next turn → block it.
 *  3. Else → choose a random available move.
 *
 * This version uses a shadow XO[][] board so it never modifies the real Cell[][].
 */
public class MediumStrat implements PlayerStrategy {

  @Override
  public Coordinate chooseMove(Cell[][] board, XO aiShape) {

    XO[][] shadow = toShadow(board);
    XO opponent = (aiShape == XO.X) ? XO.O : XO.X;

    // 1. Try to win
    Coordinate win = findWinningMove(shadow, aiShape);
    if (win != null) return win;

    // 2. Try to block
    Coordinate block = findWinningMove(shadow, opponent);
    if (block != null) return block;

    // 3. Otherwise pick random
    return chooseRandom(shadow);
  }

  /**
   * Converts the real Cell[][] into a simple XO[][] shadow board.
   */
  private XO[][] toShadow(Cell[][] board) {
    XO[][] shadow = new XO[3][3];

    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        shadow[r][c] = board[r][c].getPlayer();
      }
    }

    return shadow;
  }

  /**
   * Finds a move that would allow the given player to win immediately.
   */
  private Coordinate findWinningMove(XO[][] board, XO shape) {

    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {

        if (board[r][c] == null) {

          // Simulate move
          board[r][c] = shape;

          boolean wins = hasWon(board, shape);

          // Undo
          board[r][c] = null;

          if (wins) {
            return new Coordinate(r, c);
          }
        }
      }
    }

    return null;
  }

  /**
   * Chooses a random empty cell.
   */
  private Coordinate chooseRandom(XO[][] board) {
    List<Coordinate> open = new ArrayList<>();

    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        if (board[r][c] == null) {
          open.add(new Coordinate(r, c));
        }
      }
    }

    return open.get(ThreadLocalRandom.current().nextInt(open.size()));
  }

  /**
   * Checks if a player has won.
   */
  private boolean hasWon(XO[][] board, XO shape) {

    // Rows
    for (int r = 0; r < 3; r++) {
      if (board[r][0] == shape &&
          board[r][1] == shape &&
          board[r][2] == shape) {
        return true;
      }
    }

    // Columns
    for (int c = 0; c < 3; c++) {
      if (board[0][c] == shape &&
          board[1][c] == shape &&
          board[2][c] == shape) {
        return true;
      }
    }

    // Diagonals
    return (board[0][0] == shape &&
        board[1][1] == shape &&
        board[2][2] == shape)
        ||
        (board[0][2] == shape &&
            board[1][1] == shape &&
            board[2][0] == shape);
  }
}
