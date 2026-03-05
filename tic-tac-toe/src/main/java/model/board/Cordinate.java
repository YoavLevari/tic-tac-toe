package model.board;

public class Cordinate {
  private final int row;
  private final int column;

  public Cordinate(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return row;
  }
  public int getColumn() {
    return column;
  }
}
