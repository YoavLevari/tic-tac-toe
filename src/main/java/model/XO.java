package model;

public enum XO {
  X("X"), O("O");

  private final String disp;

  XO(String disp) {
    this.disp = disp;
  }

  @Override
  public String toString() {
    return disp;
  }
}
