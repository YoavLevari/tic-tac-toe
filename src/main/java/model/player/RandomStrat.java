package model.player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import model.XO;
import model.board.Cell;
import model.board.Coordinate;

/**
 * A simple AI strategy that selects a random open space on the board.
 *
 * This is the weakest AI difficulty (EASY). It does not analyze the board,
 * predict future moves, or block the opponent — it simply chooses any
 * available empty cell uniformly at random.
 */
public class RandomStrat implements PlayerStrategy {

  /**
   * Chooses a random empty cell from the current board.
   *
   * @param currentBoard the current game board as a 3×3 array of Cells
   * @param playerShape  the XO symbol assigned to this AI (unused here)
   * @return a Coordinate representing the randomly selected move
   */
  @Override
  public Coordinate chooseMove(Cell[][] currentBoard, XO playerShape) {

    // Collect all empty cells on the board
    List<Coordinate> openSpaces = new ArrayList<>();

    for (int r = 0; r < currentBoard.length; r++) {
      for (int c = 0; c < currentBoard[r].length; c++) {

        // A cell is empty if its player is null
        if (currentBoard[r][c].getPlayer() == null) {
          openSpaces.add(new Coordinate(r, c));
        }
      }
    }

    // Randomly pick one of the available empty cells
    return openSpaces.get(
        ThreadLocalRandom.current().nextInt(openSpaces.size())
    );
  }
}
