import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class TTTVisual {
    TTTBoard board;
    JFrame frame;
    JPanel p;
    public static String  xo = "O";
    public static int click = 0;

    public TTTVisual(TTTBoard board) {
        initFrame();
        // now draw the board as buttons here

    }

    public void addComponentsToPane(JPanel panel) {
        //panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //c.fill = GridBagConstraints.HORIZONTAL;
        //button = new JButton("button 1");
        //c.weightx = 0.10; //used to distribute space among columns
        //c.weighty = 0.10;
        c.insets = new Insets(2,2,2,2);
        //c.fill = GridBagConstraints.HORIZONTAL; // fill fills IT UP
        //c.gridx = 0;
        //c.gridy = 0;
        //panel.add(button, c);

        //button.setPreferredSize(new Dimension(100,100));

        int size = 3;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                JButton button = new JButton("b" + (((row) * size) + col));
                button.setPreferredSize(new Dimension(50,50));
                c.gridx = row;
                c.gridy = col;
                panel.add(button, c);

                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        if (button.isEnabled()) {
                            if (click % 2 == 0) {
                                xo = "O";
                            } else {
                                xo = "X";
                            }
                            button.setText(xo);
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

        p = new JPanel(new GridBagLayout()) {
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
        p.setBackground(Color.BLACK);

        addComponentsToPane(p);
        frame.setLayout(new GridBagLayout());
        frame.add(p, c);

        frame.setSize(500,500);
        frame.setVisible(true);
    }
}


