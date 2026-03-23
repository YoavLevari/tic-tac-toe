package controller;

import model.TicTacToeModel;
import model.XO;
import model.board.Cell;
import model.board.Coordinate;
import model.player.BasicPlayer;
import model.player.Difficulty;
import model.player.HumanStrat;
import model.player.Player;
import model.player.PlayerFactory;
import view.MoveListener;
import view.TicTacToeView;

/**
 * The main controller for the GUI version of Tic-Tac-Toe.
 *
 * This class coordinates all interaction between:
 *  - the model (game rules and board state)
 *  - the view (Swing GUI)
 *  - the players (human or AI)
 *
 * The controller listens for GUI events, forwards moves to the model,
 * triggers AI decisions, updates the view, and handles game-over logic.
 */
public class GuiController {

  /** The game model containing board state and rules. */
  private final TicTacToeModel model;

  /** The GUI view that displays the board and messages. */
  private final TicTacToeView view;

  /** Player assigned to X (may be human or AI). */
  private final Player playerX;

  /** Player assigned to O (may be human or AI). */
  private final Player playerO;

  /**
   * Creates a GUI controller, initializes players, and connects the view.
   *
   * @param model the game model
   * @param view the GUI view
   * @param p1Difficulty difficulty setting for player X
   * @param p2Difficulty difficulty setting for player O
   */
  public GuiController(TicTacToeModel model,
                       TicTacToeView view,
                       Difficulty p1Difficulty,
                       Difficulty p2Difficulty) {

    this.model = model;
    this.view = view;

    // Create players using your factory (Human or AI depending on difficulty)
    Player[] players = PlayerFactory.createMatchup(p1Difficulty, p2Difficulty);
    this.playerX = players[0];
    this.playerO = players[1];

    // Connect GUI clicks to the controller
    view.setMoveListener(new MoveListener() {
      @Override
      public void onMove(int row, int col) {
        handleHumanMove(row, col);
      }
    });

    // Draw the initial board
    view.refresh(convert(model.getBoard()));

    // If X is an AI and goes first, make the opening move automatically
    if (model.getTurn() == XO.X && isAI(playerX)) {
      handleAIMove(playerX);
    }
  }

  /**
   * Handles a click from the GUI when the current player is human.
   *
   * @param row the clicked row
   * @param col the clicked column
   */
  private void handleHumanMove(int row, int col) {
    Player current = (model.getTurn() == XO.X) ? playerX : playerO;

    // Ignore clicks if it's not a human's turn
    if (!isHuman(current)) {
      return;
    }

    // Extract the human strategy from the player
    BasicPlayer bp = (BasicPlayer) current;
    HumanStrat hs = (HumanStrat) bp.getStrategy();

    // Give the human strategy the move so it can return it to the controller
    hs.setNextMove(new Coordinate(row, col));

    // Process the move normally
    processMove(current);
  }

  /**
   * Handles an AI player's turn.
   *
   * @param ai the AI player whose turn it is
   */
  private void handleAIMove(Player ai) {
    processMove(ai);
  }

  /**
   * Core move-processing logic shared by both human and AI players.
   * The player's strategy chooses a move, the model applies it,
   * the view updates, and the controller checks for game-over.
   *
   * @param player the player making the move
   */
  private void processMove(Player player) {
    try {
      // Strategy chooses the move based on the current board
      Coordinate move = player.playMove(model.getBoard());

      // Apply the move to the model
      model.playTurn(move.getRow(), move.getColumn());

      // Update the GUI to reflect the new board state
      view.refresh(convert(model.getBoard()));

      // Check for game over
      if (model.isGameOver()) {
        if (model.getWinner() != null) {
          view.showWinner(model.getWinner().toString());
        } else {
          view.showWinner("Tie");
        }
        return;
      }

      // If the next player is an AI, trigger its move automatically
      Player next = (model.getTurn() == XO.X) ? playerX : playerO;
      if (isAI(next)) {
        handleAIMove(next);
      }

    } catch (IllegalArgumentException e) {
      // Invalid move (e.g., cell already occupied)
      view.showError("Invalid move");
    }
  }

  /**
   * Returns true if the player is human-controlled.
   */
  private boolean isHuman(Player p) {
    return p instanceof BasicPlayer &&
        ((BasicPlayer) p).getStrategy() instanceof HumanStrat;
  }

  /**
   * Returns true if the player is AI-controlled.
   */
  private boolean isAI(Player p) {
    return p instanceof BasicPlayer &&
        !(((BasicPlayer) p).getStrategy() instanceof HumanStrat);
  }

  /**
   * Converts a Cell[][] board into a String[][] for the GUI.
   * Empty cells become "", occupied cells become "X" or "O".
   *
   * @param board the model's Cell[][] board
   * @return a String[][] representation for the view
   */
  private String[][] convert(Cell[][] board) {
    String[][] out = new String[3][3];
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        XO symbol = board[r][c].getPlayer();
        out[r][c] = (symbol == null) ? "" : symbol.toString();
      }
    }
    return out;
  }
}
