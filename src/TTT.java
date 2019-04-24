import java.util.Scanner;

public class TTT {

    public static void main(String[] args) {
        TTTBoard board = new TTTBoard(3);

        Scanner kb = new Scanner(System.in);
        char p1 = ' ';
        int turns = 0;
        int fOrS = 0;
        char piece = ' ';
        int[] playerPos = new int[2];

        System.out.println("TTT game:");
        System.out.print("Type your character: ");
        p1 = kb.next().charAt(0);
        p1 = Character.toUpperCase(p1);
        if (p1 == 'X') {
            piece = 'O';
        } else {
            piece = 'X';
        }
        System.out.println("Want to go first or second?");
        while (fOrS != 1 && fOrS != 2) {
            fOrS = validInt(kb, "Type 1 for first, 2 for second: ", "Enter 1 or 2:");
        }

        if (fOrS == 1) {
            while (board.winner() == ' ' && turns < (board.size() * board.size())) {
                System.out.println(board);
                playerPos = playerMove(kb, board);
                board.set(playerPos[0], playerPos[1], p1);
                System.out.println(board);
                turns++;
                if (!(board.winner() == ' ' && turns < (board.size() * board.size()))) break;
                TTTAI.move(board, piece);
                turns++;
            }
        } else {
            while (board.winner() == ' ' && turns < (board.size() * board.size())) {
                TTTAI.move(board, piece);
                System.out.println(board);
                playerPos = playerMove(kb, board);
                turns++;
                if (!(board.winner() == ' ' && turns < (board.size() * board.size()))) break;
                board.set(playerPos[0], playerPos[1], p1);
                System.out.println(board);
                turns++;
            }
        }

        System.out.println(board);
        if (board.winner() != p1 && board.winner() != ' ') {
            System.out.print("The AI won...");
        } else if (board.winner() == ' ') {
            System.out.println("A draw");
        } else {
            System.out.println("SHOULD NEVER REACH HERE YOU CANT BEAT AI");
        }
    }

    private static int[] playerMove(Scanner kb, TTTBoard board) {
        int[] pos = new int[2];
        pos[0] = getPos(kb, "row", board);
        pos[1] = getPos(kb, "col", board);
        while (board.get(pos[0], pos[1]) != ' ') {
            System.out.println("Invalid choice");
            pos[0] = getPos(kb, "row", board);
            pos[1] = getPos(kb, "col", board);
        }
        return pos;
    }

    private static int validInt(Scanner kb, String prompt, String invalid) {
        System.out.print(prompt);

        while (!kb.hasNextInt()) {
            kb.next();
            kb.nextLine();
            System.out.print(invalid);
        }
        return kb.nextInt();
    }

    private static int getPos(Scanner kb, String rowcol, TTTBoard board) {
        String invalid = "enter a valid " + rowcol + " number: ";
        int input = validInt(kb, "Choose your " + rowcol + ":", invalid);

        while (input < 0 || input > board.size() - 1) {
            input = validInt(kb, "Choose your " + rowcol + ":", invalid);
        }
        return input;
    }
}