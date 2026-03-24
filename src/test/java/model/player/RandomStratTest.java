package model.player;

import model.XO;
import model.board.BasicCell;
import model.board.Cell;
import model.board.Coordinate;
import org.junit.Test;

import static org.junit.Assert.*;

public class RandomStratTest {

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
  public void randomMoveShouldBeOnEmptyCell() {
    RandomStrat strat = new RandomStrat();
    Cell[][] board = emptyBoard();

    Coordinate move = strat.chooseMove(board, XO.X);

    assertTrue(board[move.getRow()][move.getColumn()].isEmpty());
  }

  @Test
  public void randomMoveShouldNotPickOccupiedCell() {
    RandomStrat strat = new RandomStrat();
    Cell[][] board = emptyBoard();

    board[1][1].setPlayer(XO.O);

    for (int i = 0; i < 20; i++) {
      Coordinate move = strat.chooseMove(board, XO.X);
      assertFalse(move.getRow() == 1 && move.getColumn() == 1);
    }
  }

  @Test
  public void randomMoveShouldVaryOverMultipleCalls() {
    RandomStrat strat = new RandomStrat();
    Cell[][] board = emptyBoard();

    Coordinate first = strat.chooseMove(board, XO.X);

    boolean foundDifferent = false;
    for (int i = 0; i < 30; i++) {
      Coordinate next = strat.chooseMove(board, XO.X);
      if (next.getRow() != first.getRow() || next.getColumn() != first.getColumn()) {
        foundDifferent = true;
        break;
      }
    }

    assertTrue(foundDifferent);
  }
}
