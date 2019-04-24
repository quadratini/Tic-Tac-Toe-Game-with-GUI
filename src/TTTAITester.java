import java.awt.event.MouseAdapter;
        import java.awt.event.MouseEvent;

public class TTTAITester {
    public static void main(String[] args) {
        TTTBoard board = new TTTBoard(3);
        TTTVisual dong = new TTTVisual(board);
        dong.getFrame().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON2) {
                    if (dong.getXO() == 'O') {
                        TTTAI.move(board, 'X');
                        //System.out.println("X should go: " + res[0] + "," + res[1]);
                        //board.set(res[0], res[1], 'X');
                        dong.updateButtons();
                        dong.nextPlayer();
                    } else {
                        TTTAI.move(board, 'O');
                        //System.out.println("O should go: " + res[0] + "," + res[1]);
                        //board.set(res[0], res[1], 'O');
                        dong.updateButtons();
                        dong.nextPlayer();
                    }
                }
            }
        });
    }
}
