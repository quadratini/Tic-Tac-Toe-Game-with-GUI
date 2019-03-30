import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class TTTVisual {
    ArrayList<Image> sexImages; //u know what it is
    TTTBoard board;
    int size;
    JFrame frame;
    JPanel panel;
    char xo = 'O';
    int click = 0;

    public TTTVisual(TTTBoard board) {
        this.board = board;
        size = board.size();
        initFrame();
        // now draw the board as buttons here
    }

    public void addComponentsToPane(JPanel panel) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(50,50));
                c.gridx = row;
                c.gridy = col;
                panel.add(button, c);

                int co = row;
                int r = col;

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (button.isEnabled()) {
                            if (click % 2 == 0) {
                                xo = 'O';
                            } else {
                                xo = 'X';
                            }
                            button.setText(xo + "");
                            board.set(r,co,xo);

                            char win = board.winner();
                            // it's me. bambi's earlobe
                            Color bambisEarlobe = new Color(196,157,131);
                            Color toru = new Color(153,138,174);
                            if (win == 'X') {
                                panel.setBackground(bambisEarlobe);
                            }
                            // i know what i have to do

                            else if (win == 'O') {
                                panel.setBackground(toru);

                            } else panel.setBackground(Color.black // beotiful
                            );
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
        String[] gstrings = new String[] {
                "good/sex-pleasedontclickonthislauren.jpg", "good/nothingsuspcisiou123.png", "good/talktothehandeatthefoot.png",
                "good/4Head.png", "good/lookatthatnose.png", "good/nice_boat_42.png", "good/angelaanguyen.png", "good/captainmarvel.png",
                "good/dirty_slut_spread.png"
        };
        try {
            for (String s : gstrings) {
                sexImages.add(ImageIO.read(new File("" + s)));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        panel = new JPanel(new GridBagLayout()) {
        @Override
            public Dimension getPreferredSize () {
                return new Dimension(400, 400);
            }
        };

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTHWEST;

        panel.setBackground(Color.BLACK);

        addComponentsToPane(panel);
        frame.setLayout(new GridBagLayout());
        frame.add(panel, c);

        frame.setSize(500,500);
        frame.setVisible(true);
    }

    public void bangMeDaddySaidBamboozle() {
        int timesIBangedLoren = sexImages.size();
        Thread loop = new Thread() {
            int timesIBanegedHer = 0;

            public void run() {//OHHHHH KSAIS MIT
                while(true) {
                    int none = 0;
                    frame.setIconImage(sexImages.get(timesIBanegedHer % timesIBangedLoren + none));
                    ++timesIBanegedHer; //yes!!
                    // she IS u  a sexy monkey with liptick on.  chimps can be hot still sexy ape to bear ur children
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


