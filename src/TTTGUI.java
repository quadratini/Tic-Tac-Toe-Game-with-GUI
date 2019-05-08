import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A class that initializes a GUI depending ont he size given.
 * Good for a game of Tic-Tac-Toe. Reset button at the bottom
 * to reset the game.
 * Uses gridbag layout.
 */
public class TTTGUI {
    private TTTBoard board;
    private int size;
    private JButton[][] buttons;
    private JLabel turn = new JLabel("Your move");

    /**
     * A constructor for the TTTBoard.
     * Initializes the frame.
     *
     * @param board takes in the TTT game board.
     */
    public TTTGUI(TTTBoard board) {
        this.board = board;
        size = board.size();
        initFrame();
    }

    private void addComponentsToPane(JPanel panel) {
        char user = 'X';
        char computer = 'O';

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 2, 2, 2);
        buttons = new JButton[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                JButton button = new JButton();
                buttons[row][col] = button;
                button.setPreferredSize(new Dimension(100, 100));
                c.gridx = row;
                c.gridy = col;
                panel.add(button, c);

                ButtonActionListener button_pressed = new ButtonActionListener(board, turn, button,
                        col, row, user,
                        computer, buttons, size);

                button.addActionListener(button_pressed);
            }
        }
    }

    // draws the frame
    private void initFrame() {
        JFrame frame;

        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        panel.setBackground(Color.BLACK);

        addComponentsToPane(panel);
        frame.setLayout(new GridBagLayout());
        frame.add(panel, c);

        Font labelFont = turn.getFont();

        c.gridy = 0;
        turn.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        frame.add(turn, c);

        c.gridx = 1;
        JButton reset = new JButton("Reset");

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turn.setText("Your move");
                for (int i = 0; i < size; ++i) {
                    for (int j = 0; j < size; ++j) {
                        panel.setBackground(Color.black);
                        board.set(i, j, ' ');
                        buttons[i][j].setText(" ");
                        buttons[i][j].setEnabled(true);
                    }
                }
            }
        });
        c.gridy = 2;
        frame.add(reset, c);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }
}
