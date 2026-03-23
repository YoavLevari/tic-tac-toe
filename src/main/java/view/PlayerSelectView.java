package view;

import model.player.Difficulty;

import javax.swing.*;
import java.awt.*;

/**
 * A modal dialog that allows the user to choose the difficulty levels
 * for Player 1 (X) and Player 2 (O) before starting the game.
 *
 * The dialog appears centered on the parent frame and blocks interaction
 * until both selections are made.
 */
public class PlayerSelectView extends JDialog {

  /** Stores the chosen difficulty for Player 1 (X). */
  private Difficulty p1Choice = null;

  /** Stores the chosen difficulty for Player 2 (O). */
  private Difficulty p2Choice = null;

  /**
   * Constructs the selection dialog and displays the Player 1 screen first.
   *
   * @param parent the parent window that owns this dialog
   */
  public PlayerSelectView(JFrame parent) {
    super(parent, "Choose Players", true);
    setSize(300, 300);
    setLocationRelativeTo(parent);

    showPlayer1Screen();

    setVisible(true);
  }

  /**
   * Displays the UI for selecting Player 1 (X).
   * Clears the dialog and rebuilds the layout.
   */
  private void showPlayer1Screen() {
    getContentPane().removeAll();
    setLayout(new BorderLayout());

    JLabel label = new JLabel("Choose Player 1 (X)", SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 18));
    add(label, BorderLayout.NORTH);

    add(createButtonPanel(true), BorderLayout.CENTER);

    revalidate();
    repaint();
  }

  /**
   * Displays the UI for selecting Player 2 (O).
   * Called automatically after Player 1 makes a selection.
   */
  private void showPlayer2Screen() {
    getContentPane().removeAll();
    setLayout(new BorderLayout());

    JLabel label = new JLabel("Choose Player 2 (O)", SwingConstants.CENTER);
    label.setFont(new Font("Arial", Font.BOLD, 18));
    add(label, BorderLayout.NORTH);

    add(createButtonPanel(false), BorderLayout.CENTER);

    revalidate();
    repaint();
  }

  /**
   * Creates a panel containing the four difficulty buttons:
   *  - Human
   *  - AI Level 1 (Easy)
   *  - AI Level 2 (Medium)
   *  - AI Level 3 (Hard)
   *
   * @param isPlayer1 true if selecting Player 1, false for Player 2
   * @return a panel containing the difficulty selection buttons
   */
  private JPanel createButtonPanel(boolean isPlayer1) {
    JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

    JButton humanBtn = new JButton("Human");
    JButton ai1Btn = new JButton("AI Level 1");
    JButton ai2Btn = new JButton("AI Level 2");
    JButton ai3Btn = new JButton("AI Level 3");

    humanBtn.addActionListener(e -> choose(isPlayer1, Difficulty.HUMAN));
    ai1Btn.addActionListener(e -> choose(isPlayer1, Difficulty.EASY));
    ai2Btn.addActionListener(e -> choose(isPlayer1, Difficulty.MEDIUM));
    ai3Btn.addActionListener(e -> choose(isPlayer1, Difficulty.HARD));

    panel.add(humanBtn);
    panel.add(ai1Btn);
    panel.add(ai2Btn);
    panel.add(ai3Btn);

    return panel;
  }

  private void choose(boolean isPlayer1, Difficulty diff) {
    if (isPlayer1) {
      p1Choice = diff;
      showPlayer2Screen();
    } else {
      p2Choice = diff;
      dispose(); // close dialog and return control
    }
  }

  /** @return the difficulty selected for Player 1 (X). */
  public Difficulty getPlayer1Difficulty() {
    return p1Choice;
  }

  /** @return the difficulty selected for Player 2 (O). */
  public Difficulty getPlayer2Difficulty() {
    return p2Choice;
  }
}
