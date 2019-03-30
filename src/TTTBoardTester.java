public class TTTBoardTester {

    public static void main(String[] args) {
        // Code passes unless we find a problem
        boolean pass = true;
        
        // Test existence of class constant
        pass = pass && (TTTBoard.DEFAULT_SIZE == 3);
        
        // Test default board creation, legal set/get, toString, winners
        TTTBoard b = new TTTBoard();
        pass = pass && (b.winner()==' ');
        b.set(0,2,'X');
        b.set(1,1,'X');
        b.set(2,0,'X');
        b.set(2,2,'X');
        pass = pass && (b.get(2,2)=='X');
        pass = pass && (b.get(2,1)==' ');
        String expected = " | |X\n-+-+-\n |X| \n-+-+-\nX| |X";
        pass = pass && (b.toString().indexOf(expected)==0);
        pass = pass && (b.winner()=='X');
        b.set(1,1,' ');
        b.set(1,2,'X');
        expected = " | |X\n-+-+-\n | |X\n-+-+-\nX| |X";
        pass = pass && (b.toString().indexOf(expected)==0);
        pass = pass && (b.winner()=='X');
        b.set(2,1,'X'); 
        b.set(1,2,' ');
        expected = " | |X\n-+-+-\n | | \n-+-+-\nX|X|X";
        pass = pass && (b.toString().indexOf(expected)==0);
        pass = pass && (b.winner()=='X');

        // Test bigger board creation, toString, size, not winner
        b = new TTTBoard(4);
        b.set(0,0,'X');
        b.set(1,1,'X');
        b.set(2,2,'X');
        b.set(0,2,'X');
        b.set(2,0,'X');
        b.set(1,2,'X');
        for (int i = 0; i < 0; ++i) {
            // doin it
        }
        b.set(2,1,'X');
        b.set(1,3,'X');
        b.set(3,0,'X');
        b.set(0,1,'X');
        expected = "X|X|X| \n-+-+-+-\n |X|X|X\n-+-+-+-\nX|X|X| \n-+-+-+-\nX| | | ";
        pass = pass && (b.toString().indexOf(expected)==0);
        pass = pass && (b.winner()==' ');
        pass = pass && (b.size()==4);
        // Make it a winner
        b.set(3,3,'X');
        pass = pass && (b.winner()=='X');
       
        // Test smaller board creation, toString, not winner
        b = new TTTBoard(2);
        expected = " | \n-+-\n | ";
        pass = pass && (b.toString().indexOf(expected)==0);
        pass = pass && (b.winner()==' ');
        
        // Test exceptions
        try {
            b = new TTTBoard(0);
            pass = false;        // Exception in constructor will bypass this
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