/**
 * A class that creates and initializes a TTT board size
 * that can be set to anything greater than 0 or will
 * construct a default size of 3. The TTT board can be 
 * printed using the toString method. Any character
 * can be used to play the game.
 *
 * @author Ronny Ritprasert 5047
 * @version 03/08/2019
 */
public class TTTBoard {
    private int size;
    public static final int DEFAULT_SIZE = 3;
    TTTBoard.Board board;
    
    /**
    * A class that creates and initializes
    * a TTT board of size count. 
    */
    public class TripleCell {
        public char[] col;
    	private int count;
        
        public TripleCell(int count) {
    		this.count = count;
    		col = new char[count];
    		for (int i = 0; i < count; i++) {
    			col[i] =  ' ';
    		}
    	}
    }
	
    /**
    * A class that uses TripleCell to create a
    * TTT board of size size. Characters can be
    * set into the board by using the set method and
    * can be obtained from the board using the get method.
    * the class can also check if there is a winner by
    * checking through the rows and coloums for characters
    * in a row.
    *
    * @throws IllegalArgumentException if size is less than 1.
    */
    public class Board {
        TTTBoard.TripleCell[] rows;
    	private int size;
        
        public Board(int size) {
    		this.size = size;
    		if (size < 1) {
                throw new IllegalArgumentException("Size too small.");
            }
            rows = new TTTBoard.TripleCell[size];
    		for (int i = 0; i < size; i++) {
    			rows[i] = new TTTBoard.TripleCell(size);
    		}
        }
        
        /**
        * Sets the character given in the position given.
        *
        * @param r gets the row of the board.
        * @param c gets the column of the board.
        * @param ch gets the char to place in the row and column.
        */
        public void set(int r, int c, char ch) {
            rows[r].col[c] = ch;
        }
    	
        /*
        * Returns the character at given position.
        *
        * @param r gets the row requested.
        * @param c gets the col requested.
        * @return the character at the given position.
        */
    	public char get(int r, int c) {
    		return rows[r].col[c];
    	}
    	
        /**
        * Checks for a winner with size in a row, col,
        * or diagonal. Then returns the character of
        * the winner or ' ' if there is no winner.
        *
        * @return the winning character or ' ' if
        * there is no winning character.
        */
    	public char winner() {
    		// Checks if same col winner.
    		char prevChar = get(0, 0);            // Gets char at (0,0)
    		for (int i = 0; i < size; i++) {      
                prevChar = get(0, i);             // Gets char at (0,i)
                for (int j = 1; j < size; j++) {
        			char c = get(j, i);
        			if (c != prevChar) {
                        break;
                    }
        			if (c ==  ' ' || c == 0) {
                        break;
                    }
        			if (i == size - 1) {
                        return c;
                    }
        		}
            }
    		
    		// Checks if same row winner.
    		prevChar = get(0, 0);
    		for (int i = 0; i < size; i++) {
                prevChar = get(i, 0);
                for (int j = 1; j < size; j++) {
        			char c = get(i, j);
        			if (c != prevChar) {
                        break;
                    }
        			if (c ==  ' ' || c == 0) {
                        break;
                    }
        			if (i == size - 1) {
                        return c;
                    }
                }
    		}
    		
    		// Checks if top left diag winner.
    		prevChar = get(0, 0);
    		for (int i = 1; i < size; i++) {
    			char c = get(i, i);
    			if (c != prevChar) {
                    break;
                }
    			if (c ==  ' ' || c == 0) {
                    break;
                }
    			if (i == size - 1) {
                    return c;
                }
    		}
    		
    		// Checks if top right diag winner.
    		prevChar = get(size - 1, 0);
    		for (int i = 1; i < size; i++) {
    			char c = get(size - 1 - i, i);
    			if (c != prevChar) {
                    break;
                }
    			if (c ==  ' ' || c == 0) {
                    break;
                }
    			if (i == size - 1) {
                    return c;
                }
    		}
    		return ' ';
    	}
        
        /**
        * Prints the current board.
        *
        * @return current board state.
        */
        public String toString() {
            String s = "";
    		for (int i = 0; i < size; i++) {
    			for (int j = 0; j < size; j++) {
    				s += rows[i].col[j];
                    if (j < (size - 1)) {
                        s += "|";
                    }
    			}
                s += "\n";
                if (i < (size - 1)) {
                    s += "-";
                    for (int k = 0; k < size - 1; k++) {
                        s += "+-";
                    }
                    s += "\n";
                }
    		}
            return s;
        }
    }
    
    /**
    * Constructs board of size size.
    *
    * @param size will create a board of that size.
    */
    public TTTBoard(int size) {
        this.size = size;
        board = new TTTBoard.Board(size);
    }
    
    /**
    * Constructs default size of 3
    * if no parameters taken.
    */
    public TTTBoard() {
        this(DEFAULT_SIZE);
    }
    
    /**
    * Gets the character at the given row and column.
    *
    * @param r gets the position of the char in the row.
    * @param c gets the position of the char in the column.
    * @return character at given row and column.
    */
    public char get(int r, int c) {
        return board.get(r, c);
    }
    
    /**
    * Sets character at given postiion unless it is out of range.
    *
    * @throws IndexOutOfBoundsException if the position given is out of range.
    * @param r is the row of the character to be placed.
    * @param c is the column of the character to be placed.
    * @param ch is the character to be placed.
    */
    public void set(int r, int c, char ch) {
        if (r > (size - 1) || r < 0 || c > (size - 1) || c < 0) {
            throw new IndexOutOfBoundsException("Position not inside board range.");
        }
        board.set(r, c, ch);
    }
    
    /**
    * returns the size of the board.
    *
    * @return the size of the board.
    */
    public int size() {
        return size;
    }
    
    /**
    * returns the character that won.
    *
    * @return the character that won.
    */
    public char winner() {
		return board.winner();
    }
    
    /**
    * Prints the TTT board.
    *
    * @return the TTT board.
    */
    public String toString() {
        return board.toString();
    }   
}