import controller.GuiController;
import model.BasicModel;
import model.TicTacToeModel;
import model.player.Difficulty;
import view.PlayerSelectView;
import view.TicTacToeView;

/**
 * Entry point for the Tic‑Tac‑Toe application.
 *
 * This class is responsible for:
 *  1. Showing the player‑selection dialog (modal)
 *  2. Retrieving the chosen difficulty levels
 *  3. Creating the model, view, and controller
 *  4. Starting the GUI‑based game
 */
public class Main {
    public static void main(String[] args) {

        // Show the modal player‑selection dialog.
        // Execution pauses here until the dialog is closed.
        System.out.println("Before dialog");
        PlayerSelectView select = new PlayerSelectView(null);
        System.out.println("After dialog");

        // Retrieve the chosen difficulty levels.
        Difficulty p1 = select.getPlayer1Difficulty();
        Difficulty p2 = select.getPlayer2Difficulty();

        // Safety check: if the dialog was closed without choosing both players.
        if (p1 == null || p2 == null) {
            System.out.println("Player selection was not completed.");
            return;
        }

        // Create the game model and view.
        TicTacToeModel model = new BasicModel();
        TicTacToeView view = new TicTacToeView();

        // Create the controller, which wires together model + view + players.
        new GuiController(model, view, p1, p2);
    }
}

