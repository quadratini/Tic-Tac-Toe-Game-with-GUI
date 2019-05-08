import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An action listener that updates the board and puts the player's
 * and AI's move into the correct positions.
 */
public class ButtonActionListener implements ActionListener {
    private TTTBoard board;
    private JLabel turn;
    private JButton button;
    private int row;
    private int col;
    private char user;
    private char computer;
    private JButton[][] buttons;
    private int size;

    /**
     * Constructor needing many parameters of the GUI class to be passed.
     * That will set up the action listener for the gui.
     *
     * @param board the TTTBoard being used.
     * @param turn label of whose turn it is.
     * @param button the buttons on the gui.
     * @param row the row of the buttons in the gui.
     * @param col the column of the buttons in the gui.
     * @param user the user of the program.
     * @param computer the AI.
     * @param buttons the buttons created by the gui.
     * @param size size of the board.
     */
    public ButtonActionListener(TTTBoard board, JLabel turn, JButton button,
                                int row, int col, char user, char computer,
                                JButton[][] buttons, int size) {
        this.board = board;
        this.turn = turn;
        this.row = row;
        this.col = col;
        this.user = user;
        this.computer = computer;
        this.buttons = buttons;
        this.size = size;
        this.button = button;
    }

    /**
     * Sets the player's move onto the position that was clicked.
     * Checks if there is a winner or draw and updates the gui.
     *
     * @param e the action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (button.isEnabled()) {
            button.setText(user + "");
            board.set(row, col, user);

            char win = board.winner();

            if (win == ' ') {
                TTTAI.move(board, computer);
            }
            updateButtons();
            win = board.winner();
            if (win != ' ') {
                if (win == 'X') {
                    turn.setText("You won!");
                } else {
                    turn.setText("Sorry, the computer won!");
                }
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        buttons[i][j].setEnabled(false);
                    }
                }
            } else {
                if (boardSpots() == 0) { // FOR TIE
                    turn.setText("It's a draw!");
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            buttons[i][j].setEnabled(false);
                        }
                    }
                }
            }
        }
    }

    private void updateButtons() {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                char p = board.get(j, i);         // Get the pieces that are on the board.
                buttons[i][j].setText(p + "");    // Set the pieces in place on GUI.
            }
        }
    }

    private int boardSpots() {
        int spots = 0;
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                if (board.get(j, i) == ' ') {
                    spots++;
                }
            }
        }
        return spots;
    }
}





