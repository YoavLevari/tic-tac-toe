package model.player;

import model.XO;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerFactoryTest {

  @Test
  public void humanDifficultyShouldCreateHumanStrat() {
    Player p = PlayerFactory.createPlayer(Difficulty.HUMAN);
    assertTrue(p.getStrategy() instanceof HumanStrat);
  }

  @Test
  public void easyDifficultyShouldCreateRandomStrat() {
    Player p = PlayerFactory.createPlayer(Difficulty.EASY);
    assertTrue(p.getStrategy() instanceof RandomStrat);
  }

  @Test
  public void mediumDifficultyShouldCreateMediumStrat() {
    Player p = PlayerFactory.createPlayer(Difficulty.MEDIUM);
    assertTrue(p.getStrategy() instanceof MediumStrat);
  }

  @Test
  public void hardDifficultyShouldCreatePerfectStrat() {
    Player p = PlayerFactory.createPlayer(Difficulty.HARD);
    assertTrue(p.getStrategy() instanceof PerfectStrat);
  }

  @Test
  public void createMatchupShouldAssignXToPlayer1() {
    Player[] players = PlayerFactory.createMatchup(Difficulty.EASY, Difficulty.HARD);
    assertEquals(XO.X, players[0].getShape());
  }

  @Test
  public void createMatchupShouldAssignOToPlayer2() {
    Player[] players = PlayerFactory.createMatchup(Difficulty.EASY, Difficulty.HARD);
    assertEquals(XO.O, players[1].getShape());
  }

  @Test
  public void createMatchupShouldReturnTwoPlayers() {
    Player[] players = PlayerFactory.createMatchup(Difficulty.MEDIUM, Difficulty.MEDIUM);
    assertEquals(2, players.length);
  }
}
