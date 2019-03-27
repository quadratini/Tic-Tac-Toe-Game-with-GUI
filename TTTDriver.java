public class TTTDriver {

    public static void main(String[] args) {
    
        TTTBoard b = new TTTBoard();
        b.set(0,2,'X');
        b.set(1,1,'X');
        b.set(2,0,'X');
        b.set(2,2,'X');
        b.set(1,1,' ');
        b.set(1,2,'X');
        System.out.println(b);
        System.out.println(b.winner() == 'X');
    }
    
}