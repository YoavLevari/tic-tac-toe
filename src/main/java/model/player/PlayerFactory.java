package model.player;

import model.XO;

/**
 * Factory class responsible for creating Player objects based on difficulty
 * and assembling complete matchups for a game.
 *
 * This centralizes player creation logic so the rest of the codebase
 * does not need to know which strategy corresponds to which difficulty.
 */
public class PlayerFactory {

  /**
   * Creates a Player instance configured with the appropriate strategy
   * based on the selected difficulty level.
   *
   * Difficulty → Strategy mapping:
   *
   * HUMAN  – HumanStrat:
   *          The controller supplies moves based on GUI clicks.
   *
   * EASY   – RandomStrat:
   *          Chooses any empty cell at random. Very weak.
   *
   * MEDIUM – MediumStrat:
   *          Win → Block → Random heuristic.
   *          Smarter than EASY but still beatable.
   *
   * HARD   – PerfectStrat:
   *          Full minimax search using a shadow XO[][] board.
   *          Never loses; optimal play.
   *
   * @param difficulty the desired difficulty level
   * @return a Player configured with the appropriate strategy
   * @throws IllegalArgumentException if the difficulty is unrecognized
   */
  public static Player createPlayer(Difficulty difficulty) {
    switch (difficulty) {
      case HUMAN:
        return new BasicPlayer(new HumanStrat());
      case EASY:
        return new BasicPlayer(new RandomStrat());
      case MEDIUM:
        return new BasicPlayer(new MediumStrat());
      case HARD:
        return new BasicPlayer(new PerfectStrat());
      default:
        throw new IllegalArgumentException("Unknown difficulty: " + difficulty);
    }
  }

  /**
   * Creates a two‑player matchup using the given difficulty levels.
   *
   * Player 1 is always assigned XO.X.
   * Player 2 is always assigned XO.O.
   *
   * This ensures consistent ordering and avoids ambiguity in the model.
   *
   * @param p1 difficulty level for player 1 (X)
   * @param p2 difficulty level for player 2 (O)
   * @return an array of two Players: [playerX, playerO]
   */
  public static Player[] createMatchup(Difficulty p1, Difficulty p2) {
    Player playerX = createPlayer(p1);
    Player playerO = createPlayer(p2);

    playerX.initShape(XO.X);
    playerO.initShape(XO.O);

    return new Player[]{playerX, playerO};
  }
}
