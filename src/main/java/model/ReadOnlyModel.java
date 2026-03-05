package model;

public interface ReadOnlyModel {

  XO[][] getBoard();

  XO getWinner();

  XO getTurn();
}
