package model.board;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordinateTest {

  @Test
  public void constructorShouldStoreRowAndColumn() {
    Coordinate c = new Coordinate(2, 1);

    assertEquals(2, c.getRow());
    assertEquals(1, c.getColumn());
  }

  @Test
  public void gettersShouldReturnCorrectValues() {
    Coordinate c = new Coordinate(0, 2);

    assertEquals(0, c.getRow());
    assertEquals(2, c.getColumn());
  }
}
