package model.board;

import model.player.Player;

public interface Cell {

  Player getPlayer();

  void setPlayer(Player player)
      throws IllegalArgumentException;
}
