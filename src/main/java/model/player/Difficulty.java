package model.player;

/**
 * Represents the type of player or AI difficulty used in the game.
 *
 * Each difficulty level corresponds to a specific PlayerStrategy:
 *
 * HUMAN  – A real human player using the GUI. The controller supplies moves.
 *
 * EASY   – Uses RandomStrat:
 *          Chooses any available empty cell uniformly at random.
 *          Makes no attempt to win or block. Very weak and unpredictable.
 *
 * MEDIUM – Uses MediumStrat:
 *          1. If it can win immediately, it takes the winning move.
 *          2. Otherwise, if the opponent can win next turn, it blocks.
 *          3. Otherwise, it chooses a random move.
 *          This makes it noticeably smarter than EASY but still beatable.
 *
 * HARD   – Uses PerfectStrat:
 *          Implements a full minimax search using a shadow XO[][] board.
 *          Always plays optimally:
 *              - Never loses
 *              - Wins whenever possible
 *              - Forces a draw against perfect play
 */
public enum Difficulty {
  HUMAN,
  EASY,
  MEDIUM,
  HARD
}
