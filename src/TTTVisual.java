import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class TTTVisual {
    private ArrayList<Image> sexImages; //u know what it is
    private TTTBoard board;
    private JLabel turn;
    private JFrame frame;
    private char xo = 'O';
    private int click = 0;
    private int size;
    private JButton[][] buttons;
    private ArrayList<File> sounds;

    private void playRandomSound() {
        if (sounds == null) {
            sounds = new ArrayList<>();
            File dir = new File("good/sex");
            for (File file : dir.listFiles()) {
                if (file.getName().endsWith(".wav")) {
                    sounds.add(file);
                }
            }
        }

        int rand = (int) (Math.random() * sounds.size());
        SoundManager.playSound(sounds.get(rand));
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
                button.setPreferredSize(new Dimension(50, 50));
                c.gridx = row;
                c.gridy = col;
                panel.add(button, c);

                int co = row;
                int r = col;

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (button.isEnabled()) {
                            playRandomSound();
                            if (click % 2 == 0) {
                                xo = 'O';
                            } else {
                                xo = 'X';
                            }

                            if (xo == 'O') {
                                turn.setText("X's move");
                            } else {
                                turn.setText("O's move");
                            }
                            button.setText(xo + "");
                            board.set(r, co, xo);

                            char win = board.winner();
                            Color bambisEarlobe = new Color(196, 157, 131);
                            Color toru = new Color(153, 138, 174);

                            if (win != ' ') {
                                if (win == 'X') {
                                    panel.setBackground(bambisEarlobe);
                                    turn.setText("X wins");
                                } else {
                                    panel.setBackground(toru);
                                    turn.setText("O wins");
                                }

                                for (int i = 0; i < size; ++i) {
                                    for (int j = 0; j < size; ++j) {
                                        buttons[i][j].setEnabled(false);
                                    }
                                }
                            } else {
                                if (click == (size * size - 1)) { // FOR TIE
                                    panel.setBackground(Color.red);
                                    turn.setText("Draw");
                                }
                            }
                        }
                        click++;
                        button.setEnabled(false);
                    }
                });
            }
        }
    }

    // draws the frame
    private void initFrame() {

        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sexImages = new ArrayList<>();
        String[] gstrings = new String[]{
                "good/sex-pleasedontclickonthislauren.jpg", "good/nothingsuspcisiou123.png", "good/talktothehandeatthefoot.png",
                "good/4Head.png", "good/lookatthatnose.png", "good/nice_boat_42.png", "good/angelaanguyen.png", "good/captainmarvel.png",
                "good/dirty_slut_spread.png"
        };
        try {
            for (String s : gstrings) {
                sexImages.add(ImageIO.read(new File("" + s)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 400);
            }
        };

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        panel.setBackground(Color.BLACK);

        addComponentsToPane(panel);
        frame.setLayout(new GridBagLayout());
        frame.add(panel, c);

        turn = new JLabel();
        Font labelFont = turn.getFont();
        turn.setText("O's move");
        c.gridy = 0;
        turn.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        frame.add(turn, c);

        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < size; ++i) {
                    for (int j = 0; j < size; ++j) {
                        panel.setBackground(Color.white);
                        board.set(i, j, ' ');
                        buttons[i][j].setText(" ");
                        buttons[i][j].setEnabled(true);
                        turn.setText("O's move");
                        click = 0;
                    }
                }
            }
        });
        c.gridy = 2;
        frame.add(reset, c);

        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    public void bangMeDaddySaidBamboozle() {
        int timesIBangedLoren = sexImages.size();
        Thread loop = new Thread() {
            int timesIBanegedHer = 0;

            public void run() {//OHHHHH KSAIS MIT
                while (true) {
                    int none = 0;
                    frame.setIconImage(sexImages.get(timesIBanegedHer % timesIBangedLoren + none));
                    ++timesIBanegedHer; //yes!!
                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        loop.start();
    }
}


