package model;


public interface TikTackToeModel extends ReadOnlyModel {

  void changeTurn();

  void playTurn(int row, int column)
      throws IllegalArgumentException;

  Boolean isGameOver();
}
