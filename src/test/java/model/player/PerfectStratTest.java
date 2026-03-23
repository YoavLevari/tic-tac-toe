package model.player;

import model.XO;
import model.board.BasicCell;
import model.board.Cell;
import model.board.Coordinate;
import org.junit.Test;

import static org.junit.Assert.*;

public class PerfectStratTest {

  private Cell[][] emptyBoard() {
    Cell[][] board = new Cell[3][3];
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        board[r][c] = new BasicCell();
      }
    }
    return board;
  }

  @Test
  public void perfectShouldReturnEmptyCell() {
    PerfectStrat strat = new PerfectStrat();
    Cell[][] board = emptyBoard();

    Coordinate move = strat.chooseMove(board, XO.X);

    assertTrue(board[move.getRow()][move.getColumn()].isEmpty());
  }

  @Test
  public void perfectShouldTakeWinningMove() {
    PerfectStrat strat = new PerfectStrat();
    Cell[][] board = emptyBoard();

    board[0][0].setPlayer(XO.X);
    board[0][1].setPlayer(XO.X);

    Coordinate move = strat.chooseMove(board, XO.X);

    assertEquals(0, move.getRow());
    assertEquals(2, move.getColumn());
  }

  @Test
  public void perfectShouldBlockOpponentWin() {
    PerfectStrat strat = new PerfectStrat();
    Cell[][] board = emptyBoard();

    board[2][0].setPlayer(XO.O);
    board[1][1].setPlayer(XO.X);
    board[2][1].setPlayer(XO.O);

    Coordinate move = strat.chooseMove(board, XO.X);

    assertEquals(2, move.getRow());
    assertEquals(2, move.getColumn());
  }

  @Test
  public void perfectShouldNeverChooseOccupiedCell() {
    PerfectStrat strat = new PerfectStrat();
    Cell[][] board = emptyBoard();

    board[1][1].setPlayer(XO.X);

    for (int i = 0; i < 20; i++) {
      Coordinate move = strat.chooseMove(board, XO.O);
      assertFalse(move.getRow() == 1 && move.getColumn() == 1);
    }
  }
}
