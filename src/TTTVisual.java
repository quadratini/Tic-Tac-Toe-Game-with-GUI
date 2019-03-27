import javax.swing.*;


public class TTTVisual {
    TTTBoard board;
    JFrame frame;

    public TTTVisual(TTTBoard board) {
        initFrame();
        // now draw the board as buttons here

    }

    // draws the frame
    private void initFrame() {
        frame = new JFrame("Tic-Tac-Toe-DickSucker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }
//just do it it sos easy...
}


