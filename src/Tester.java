public class Tester {

    // Convert the first size*size chars of "board" into a TTTBoard
    private static TTTBoard create(int size, String board) {
        TTTBoard b = new TTTBoard(size);
        for (int r=0; r<size; r++) {
            for (int c=0; c<size; c++) {
                b.set(r,c,board.charAt(r*size+c));
            }
        }
        return b;
    }

    public static void main(String[] args) {
        // Code passes unless we find a problem
        boolean pass = true;

        // Test picking an empty spot when no winning moves
        TTTBoard b = create(3,"XOXXOOOX ");
        TTTAI.move(b,'X');
        String expected = "X|O|X\n-+-+-\nX|O|O\n-+-+-\nO|X|X";
        pass = pass && (b.toString().indexOf(expected)==0);

        // Test picking a winning move
        b = create(3,"OOX  X   ");
        TTTAI.move(b,'X');
        expected = "O|O|X\n-+-+-\n | |X\n-+-+-\n | |X";
        pass = pass && (b.toString().indexOf(expected)==0);

        // Test picking a blocking move
        b = create(3,"OOX  X   ");
        TTTAI.move(b,'O');
        expected = "O|O|X\n-+-+-\n | |X\n-+-+-\n | |O";
        pass = pass && (b.toString().indexOf(expected)==0);

        // Test picking a winning move over a blocking move
        b = create(3," OX OX   ");
        TTTAI.move(b,'X');
        expected = " |O|X\n-+-+-\n |O|X\n-+-+-\n | |X";
        pass = pass && (b.toString().indexOf(expected)==0);

        // Test using unusual characters
        b = create(3," 01 01   ");
        TTTAI.move(b,'0');
        expected = " |0|1\n-+-+-\n |0|1\n-+-+-\n |0| ";
        pass = pass && (b.toString().indexOf(expected)==0);

        // Test first move on an empty board
        b = new TTTBoard(2);
        TTTAI.move(b,'0');
        pass = pass && ((b.get(0,0)=='0')||(b.get(0,1)=='0')||
                (b.get(1,0)=='0')||(b.get(1,1)=='0'));

        // Test picking a winning move over a blocking move on size 4 board
        b = create(4,"X  O X O  XO    ");
        TTTAI.move(b,'X');
        expected = "X| | |O\n-+-+-+-\n |X| |O\n-+-+-+-\n | |X|O\n-+-+-+-\n | | |X";
        pass = pass && (b.toString().indexOf(expected)==0);
        // Test exception on already winning board
        try {
            b = create(2,"XOX ");   // X winner
            TTTAI.move(b,'O');

            pass = false;                 // Exception will bypass this
        }
        catch(IllegalArgumentException e) {
            // Geting here won't change 'pass'
        }
        catch(Exception e) {
            pass = false;
        }

        // Test exception on full board
        try {
            b = create(3,"XOXXOOOXX");
            TTTAI.move(b,'O');
            pass = false;                 // Exception will bypass this
        }
        catch(IllegalArgumentException e) {
            // Geting here won't change 'pass'
        }
        catch(Exception e) {
            pass = false;
        }

        // Print final result
        System.out.println(pass);
    }

}