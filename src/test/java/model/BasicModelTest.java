package model;

import model.board.Cell;
import model.board.BasicCell;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicModelTest {

  @Test
  public void boardShouldStartEmpty() {
    TicTacToeModel model = new BasicModel();
    Cell[][] board = model.getBoard();

    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        assertTrue(board[r][c].isEmpty());
      }
    }
  }

  @Test
  public void validMoveShouldPlacePlayer() {
    TicTacToeModel model = new BasicModel();
    model.playTurn(0, 0);

    assertEquals(XO.X, model.getBoard()[0][0].getPlayer());
  }

  @Test
  public void movesShouldAlternateBetweenXandO() {
    TicTacToeModel model = new BasicModel();

    model.playTurn(0, 0); // X
    model.playTurn(1, 1); // O
    model.playTurn(0, 1); // X

    assertEquals(XO.X, model.getBoard()[0][0].getPlayer());
    assertEquals(XO.O, model.getBoard()[1][1].getPlayer());
    assertEquals(XO.X, model.getBoard()[0][1].getPlayer());
  }

  @Test
  public void cannotPlaceOnOccupiedCell() {
    TicTacToeModel model = new BasicModel();

    model.playTurn(0, 0);

    assertThrows(IllegalArgumentException.class, () -> model.playTurn(0, 0));
  }

  @Test
  public void rowWinShouldBeDetected() {
    TicTacToeModel model = new BasicModel();

    model.playTurn(0, 0); // X
    model.playTurn(1, 0); // O
    model.playTurn(0, 1); // X
    model.playTurn(1, 1); // O
    model.playTurn(0, 2); // X wins

    assertEquals(XO.X, model.getWinner());
  }

  @Test
  public void columnWinShouldBeDetected() {
    TicTacToeModel model = new BasicModel();

    model.playTurn(0, 0); // X
    model.playTurn(0, 1); // O
    model.playTurn(1, 0); // X
    model.playTurn(1, 1); // O
    model.playTurn(2, 0); // X wins

    assertEquals(XO.X, model.getWinner());
  }

  @Test
  public void diagonalWinShouldBeDetected() {
    TicTacToeModel model = new BasicModel();

    model.playTurn(0, 0); // X
    model.playTurn(0, 1); // O
    model.playTurn(1, 1); // X
    model.playTurn(0, 2); // O
    model.playTurn(2, 2); // X wins

    assertEquals(XO.X, model.getWinner());
  }

  @Test
  public void drawShouldBeDetected() {
    TicTacToeModel model = new BasicModel();

    // X O X
    // X X O
    // O X O
    model.playTurn(0,0); // X
    model.playTurn(0,1); // O
    model.playTurn(0,2); // X
    model.playTurn(1,2); // O
    model.playTurn(1,0); // X
    model.playTurn(2,0); // O
    model.playTurn(1,1); // X
    model.playTurn(2,2); // O
    model.playTurn(2,1); // X

    assertNull(model.getWinner());
  }
}
