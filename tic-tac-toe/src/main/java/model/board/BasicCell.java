package model.board;

import javax.swing.JColorChooser;
import model.player.Player;

public class BasicCell implements Cell {

  private Player player;


  public BasicCell() {
    this.player = null;
  }

  @Override
  public Player getPlayer() {
    return player;
  }

  @Override
  public void setPlayer(Player player) throws IllegalArgumentException {
    if (player != null) {
      throw new IllegalArgumentException();
    }
    this.player = player;
  }
}