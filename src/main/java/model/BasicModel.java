package model;

import model.board.Cell;
import model.board.BasicCell;

/**
 * A standard implementation of the TikTackToeModel interface.
 * This class maintains the full mutable game state:
 *  - a 3x3 board of Cells
 *  - the current player's turn
 *  - the winner (if any)
 *  - the number of moves played
 *
 * The model enforces legal moves and determines when the game ends.
 */
public class BasicModel implements TicTacToeModel {

  private final Cell[][] board;
  private XO turn;
  private XO winner;
  private int turnCount;

  /**
   * Constructs a new empty Tic-Tac-Toe game.
   * All cells start empty, X goes first, and no winner is set.
   */
  public BasicModel() {
    board = new Cell[3][3];
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        board[r][c] = new BasicCell();
      }
    }

    turn = XO.X;
    winner = null;
    turnCount = 0;
  }

  /**
   * Switches the active player from X to O or vice versa.
   */
  @Override
  public void changeTurn() {
    turn = (turn == XO.X) ? XO.O : XO.X;
  }

  /**
   * Attempts to play a move at the given board position.
   *
   * @param row    the row index (0–2)
   * @param col    the column index (0–2)
   *
   * @throws IllegalArgumentException if:
   *         - the game is already over
   *         - the position is outside the board
   *         - the cell is already occupied
   */
  @Override
  public void playTurn(int row, int col) throws IllegalArgumentException {
    if (isGameOver()) {
      throw new IllegalArgumentException("Game is already over.");
    }
    if (!inBounds(row, col)) {
      throw new IllegalArgumentException("Position out of bounds.");
    }
    if (!board[row][col].isEmpty()) {
      throw new IllegalArgumentException("Cell already occupied.");
    }

    board[row][col].setPlayer(turn);
    turnCount++;

    updateWinner();

    if (!isGameOver()) {
      changeTurn();
    }
  }

  /**
   * Checks whether the given row/column is inside the 3x3 board.
   */
  private boolean inBounds(int r, int c) {
    return r >= 0 && r < 3 && c >= 0 && c < 3;
  }

  /**
   * Updates the winner field if a winning line is found.
   * Checks all rows, columns, and both diagonals.
   */
  private void updateWinner() {
    // rows + columns
    for (int i = 0; i < 3; i++) {
      if (same(board[i][0], board[i][1], board[i][2])) {
        winner = board[i][0].getPlayer();
        return;
      }
      if (same(board[0][i], board[1][i], board[2][i])) {
        winner = board[0][i].getPlayer();
        return;
      }
    }

    // diagonals
    if (same(board[0][0], board[1][1], board[2][2])) {
      winner = board[0][0].getPlayer();
      return;
    }
    if (same(board[0][2], board[1][1], board[2][0])) {
      winner = board[0][2].getPlayer();
    }
  }

  /**
   * Returns true if all three cells contain the same non-null player.
   */
  private boolean same(Cell a, Cell b, Cell c) {
    XO p = a.getPlayer();
    return p != null && p == b.getPlayer() && p == c.getPlayer();
  }

  /**
   * A game is over if:
   *  - a winner exists, or
   *  - all 9 moves have been played (draw)
   */
  @Override
  public boolean isGameOver() {
    return winner != null || turnCount == 9;
  }

  /**
   * Returns a read-only view of the board.
   * The returned array is a shallow copy, but the Cells themselves
   * are the actual model cells (safe because Cell is read-only to views).
   */
  @Override
  public Cell[][] getBoard() {
    Cell[][] copy = new Cell[3][3];
    for (int r = 0; r < 3; r++) {
      System.arraycopy(board[r], 0, copy[r], 0, 3);
    }
    return copy;
  }

  /**
   * Returns the winning player, or null if no winner exists.
   */
  @Override
  public XO getWinner() {
    return winner;
  }

  /**
   * Returns the player whose turn it currently is.
   */
  @Override
  public XO getTurn() {
    return turn;
  }
}
