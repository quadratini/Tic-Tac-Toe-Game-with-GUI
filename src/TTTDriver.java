import java.util.Scanner;

public class TTTDriver {

    public static void main(String[] args) {
        TTTBoard board = new TTTBoard(3);
        //TTTAI ai2 = new TTTAI(board, 'O');
        //TTTVisual dong = new TTTVisual(board);
        //dong.bangMeDaddySaidBamboozle();

        Scanner in = new Scanner(System.in);
        int row = 0;
        int col = 0;
        char p1 = ' ';
        char p2 = ' ';
        int[] aiMove = {};
        int size = 2;


        System.out.println("TTT game:");
        System.out.println(board);
        System.out.print("Choose your character: ");
        p1 = in.next().charAt(0);
        System.out.print("Choose your row: ");
        row = in.nextInt();
        System.out.print("Choose your col: ");
        col = in.nextInt();
        board.set(row, col, p1);
        System.out.println(board);

        p2 = 'O';
        //board.set(aiMove[0], aiMove[1], p2);


        //if (board.get(row, col) == 'O') {
        //    int[] res = ai2.getNextBestMove();
        //    System.out.println("X should go: " + res[0] + "," + res[1]);
        //    board.set(res[0], res[1], 'X');
        //   dong.updateButtons();
        //    dong.nextPlayer();
        /*} else {
            int[] res = ai2.getNextBestMove();
            System.out.println("O should go: " + res[0] + "," + res[1]);
            board.set(res[0], res[1], 'O');
            dong.updateButtons();
            dong.nextPlayer();
        }*/
        System.out.println(board);




    }
}