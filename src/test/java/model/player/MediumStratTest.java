package model.player;

import model.XO;
import model.board.BasicCell;
import model.board.Cell;
import model.board.Coordinate;
import org.junit.Test;

import static org.junit.Assert.*;

public class MediumStratTest {

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
  public void mediumShouldTakeWinningMove() {
    MediumStrat strat = new MediumStrat();
    Cell[][] board = emptyBoard();

    board[0][0].setPlayer(XO.X);
    board[0][1].setPlayer(XO.X);

    Coordinate move = strat.chooseMove(board, XO.X);

    assertEquals(0, move.getRow());
    assertEquals(2, move.getColumn());
  }

  @Test
  public void mediumShouldBlockOpponentWin() {
    MediumStrat strat = new MediumStrat();
    Cell[][] board = emptyBoard();

    board[1][0].setPlayer(XO.O);
    board[1][1].setPlayer(XO.O);

    Coordinate move = strat.chooseMove(board, XO.X);

    assertEquals(1, move.getRow());
    assertEquals(2, move.getColumn());
  }

  @Test
  public void mediumShouldReturnValidEmptyCellOtherwise() {
    MediumStrat strat = new MediumStrat();
    Cell[][] board = emptyBoard();

    board[0][0].setPlayer(XO.X);
    board[2][2].setPlayer(XO.O);

    Coordinate move = strat.chooseMove(board, XO.X);

    assertTrue(board[move.getRow()][move.getColumn()].isEmpty());
  }
}
