package model.player;

import model.XO;
import model.board.Cell;
import model.board.Coordinate;

/**
 * A perfect AI strategy using minimax.
 *
 * This version uses a shadow XO[][] board so it never modifies the real Cell[][].
 */
public class PerfectStrat implements PlayerStrategy {

  @Override
  public Coordinate chooseMove(Cell[][] board, XO aiShape) {

    XO[][] shadow = toShadow(board);

    int bestScore = Integer.MIN_VALUE;
    Coordinate bestMove = null;

    // Try every empty cell
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {

        if (shadow[r][c] == null) {

          // Simulate move
          shadow[r][c] = aiShape;

          int score = minimax(shadow, false, aiShape);

          // Undo
          shadow[r][c] = null;

          if (score > bestScore) {
            bestScore = score;
            bestMove = new Coordinate(r, c);
          }
        }
      }
    }

    return bestMove;
  }

  /**
   * Minimax using XO[][] shadow board.
   */
  private int minimax(XO[][] board, boolean isMaximizing, XO aiShape) {

    XO opponent = (aiShape == XO.X) ? XO.O : XO.X;

    // Terminal states
    if (hasWon(board, aiShape)) return 1;
    if (hasWon(board, opponent)) return -1;
    if (isFull(board)) return 0;

    if (isMaximizing) {
      int bestScore = Integer.MIN_VALUE;

      for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {

          if (board[r][c] == null) {
            board[r][c] = aiShape;
            int score = minimax(board, false, aiShape);
            board[r][c] = null;

            bestScore = Math.max(bestScore, score);
          }
        }
      }
      return bestScore;
    }

    else {
      int bestScore = Integer.MAX_VALUE;

      for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {

          if (board[r][c] == null) {
            board[r][c] = opponent;
            int score = minimax(board, true, aiShape);
            board[r][c] = null;

            bestScore = Math.min(bestScore, score);
          }
        }
      }
      return bestScore;
    }
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

  /**
   * Returns true if no empty cells remain.
   */
  private boolean isFull(XO[][] board) {
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        if (board[r][c] == null) {
          return false;
        }
      }
    }
    return true;
  }
}
