import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TTTVisual extends TTTActionListener {
    TTTBoard board;
    JFrame frame;
    JPanel p;
    public static JButton button;

    public TTTVisual(TTTBoard board) {
        initFrame();
        // now draw the board as buttons here

    }

    public void addComponentsToPane(Container pane) {
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //c.fill = GridBagConstraints.HORIZONTAL;
        button = new JButton("button 1");
        c.weightx = 0.25; //used to distribute space among columns
        c.weighty = 0.25;
        //c.fill = GridBagConstraints.HORIZONTAL; // fill fills IT UP
        c.gridx = 0;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton("button 2");
        c.gridx = 1;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton("button 3");
        c.gridx = 2;
        c.gridy = 0;
        pane.add(button, c);

        button = new JButton("button 4");
        c.gridx = 0;
        c.gridy = 1;
        pane.add(button, c);

        button = new JButton("button 5");
        c.gridx = 1;
        c.gridy = 1;
        pane.add(button, c);

        button = new JButton("button 6");
        c.gridx = 2;
        c.gridy = 1;
        pane.add(button, c);

        button = new JButton("button 7");
        c.gridx = 0;
        c.gridy = 3;
        pane.add(button, c);

        button = new JButton("button 8");
        c.gridx = 1;
        c.gridy = 3;
        pane.add(button, c);

        button = new JButton("button 9");
        c.gridx = 2;
        c.gridy = 3;
        pane.add(button, c);




    }

    // draws the frame
    private void initFrame() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        p = new JPanel();
        addComponentsToPane(p);
        frame.add(p);
        frame.pack();
        frame.setSize(300,300);
        frame.setVisible(true);
    }
//just do it it sos easy... ok
}


