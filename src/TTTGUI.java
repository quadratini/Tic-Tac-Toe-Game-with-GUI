import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TTTVisual {
    private TTTBoard board;
    private JLabel turn;
    private JFrame frame;
    private int size;
    private char xo = 'X';
    private JButton[][] buttons;

    public void nextPlayer() {
        /*if (xo == 'O') {
            xo = 'X';
        } else {
            xo = 'O';
        }*/
        turn.setText("Your move");
    }

    public int boardSpots() {
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

    public void updateButtons() {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.size(); j++) {
                char p = board.get(j, i);
                buttons[i][j].setText(p + "");
                if (p == ' ') {
                    buttons[i][j].setEnabled(true);
                }
            }
        }
    }

    public TTTVisual(TTTBoard board) {
        this.board = board;
        size = board.size();
        initFrame();
    }

    public void addComponentsToPane(JPanel panel) {
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

                int co = row;
                int r = col;

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (button.isEnabled()) {
                            nextPlayer();
                            button.setText(xo + "");
                            board.set(r, co, xo);

                            char win = board.winner();
                            Color pale = new Color(196, 157, 131);
                            Color toru = new Color(153, 138, 174);

                            if (win == ' ') {
                                TTTAI.move(board, 'O');
                            }
                            updateButtons();
                            System.out.println(board);
                            win = board.winner();
                            if (win != ' ') {
                                if (win == 'X') {
                                    panel.setBackground(pale);
                                    turn.setText("You won!");
                                } else {
                                    panel.setBackground(toru);
                                    turn.setText("Sorry, the computer won!");
                                }
                                for (int i = 0; i < size; ++i) {
                                    for (int j = 0; j < size; ++j) {
                                        buttons[i][j].setEnabled(false);
                                    }
                                }
                            } else {
                                if (boardSpots() == 0) { // FOR TIE
                                    panel.setBackground(Color.red);
                                    turn.setText("It's a draw!");
                                    for (int i = 0; i < size; ++i) {
                                        for (int j = 0; j < size; ++j) {
                                            buttons[i][j].setEnabled(false);
                                        }
                                    }
                                }
                            }
                        }
                        // button.setEnabled(false); //uncomment to play REAL tic-tac-toe
                    }
                });
            }
        }
    }

    // draws the frame
    private void initFrame() {
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

        turn = new JLabel();
        Font labelFont = turn.getFont();

        c.gridy = 0;
        turn.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        frame.add(turn, c);

        c.gridx = 1;
        JButton reset = new JButton("Reset");

        nextPlayer();
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
