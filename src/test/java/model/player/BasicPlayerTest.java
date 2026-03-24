package model.player;

import model.XO;
import model.board.BasicCell;
import model.board.Cell;
import model.board.Coordinate;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicPlayerTest {

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
  public void constructorShouldRejectNullStrategy() {
    assertThrows(IllegalArgumentException.class, () -> new BasicPlayer(null));
  }

  @Test
  public void initShapeShouldAssignShape() {
    BasicPlayer player = new BasicPlayer(new RandomStrat());
    player.initShape(XO.X);

    assertEquals(XO.X, player.getShape());
  }

  @Test
  public void getStrategyShouldReturnStrategy() {
    PlayerStrategy strat = new RandomStrat();
    BasicPlayer player = new BasicPlayer(strat);

    assertEquals(strat, player.getStrategy());
  }

  @Test
  public void playMoveShouldDelegateToStrategy() {
    PlayerStrategy strat = new RandomStrat();
    BasicPlayer player = new BasicPlayer(strat);
    player.initShape(XO.X);

    Cell[][] board = emptyBoard();
    Coordinate move = player.playMove(board);

    assertTrue(board[move.getRow()][move.getColumn()].isEmpty());
  }
}
