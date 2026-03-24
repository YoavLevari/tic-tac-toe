package model.board;

import model.XO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class BasicCellTest {

  @Test
  public void newCellShouldBeEmpty() {
    BasicCell cell = new BasicCell();
    assertTrue(cell.isEmpty());
    assertNull(cell.getPlayer());
  }

  @Test
  public void setPlayerShouldAssignValue() {
    BasicCell cell = new BasicCell();
    cell.setPlayer(XO.X);
    assertEquals(XO.X, cell.getPlayer());
    assertFalse(cell.isEmpty());
  }

  @Test
  public void setPlayerShouldNotAllowNull() {
    BasicCell cell = new BasicCell();
    assertThrows(IllegalArgumentException.class, () -> cell.setPlayer(null));
  }

  @Test
  public void setPlayerShouldNotOverwriteExistingValue() {
    BasicCell cell = new BasicCell();
    cell.setPlayer(XO.O);

    assertThrows(IllegalArgumentException.class, () -> cell.setPlayer(XO.X));
  }

  @Test
  public void isEmptyShouldReturnFalseAfterSettingPlayer() {
    BasicCell cell = new BasicCell();
    cell.setPlayer(XO.X);
    assertFalse(cell.isEmpty());
  }
}
