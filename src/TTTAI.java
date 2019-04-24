/**
 * A class that has an artificial intelligence.
 * Wins if possible, and blocks enemy from winning
 * if possible.
 *
 * @author 5047
 * @version 4/06/2019
 */

public class TTTAI {

    private static int getBoardSpaceCount(TTTBoard board) {
        int count = 0;
        for (int i = 0; i < board.size(); ++i) {
            for (int j = 0; j < board.size(); ++j) {
                if (board.get(i, j) == ' ') ++count;
            }
        }
        return count;
    }

    private static int[] getFirstOpp(TTTBoard board, char piece) throws Exception {
        for (int i = 0; i < board.size(); ++i) {
            for (int j = 0; j < board.size(); ++j) {
                char p = board.get(i, j);
                if (p != ' ' && p != piece) {
                    return new int[]{i, j};
                }
            }
        }
        throw new Exception("doesnt have opponent");
    }

    private static int getBoardTurnNumber(TTTBoard board) {
        return (board.size() * board.size() - getBoardSpaceCount(board) + 1);
    }

    // Assuming it is the AI's turn, it gets the best move

    /**
     * The AI makes a move, if it can win it will, if
     * it can block an opponent from winning it will.
     *
     * @param board reads in board.
     * @param piece piece for char.
     */
    public static void move(TTTBoard board, char piece) {
        if (board.winner() != ' ') {
            throw new IllegalArgumentException("Game Over");
        }
        if (board.size() == 1) {
            board.set(0, 0, piece);
            return;
        }
        if (board.size() == 3) {
            int turnNumber = getBoardTurnNumber(board);
            if (turnNumber == 1) {
                board.set(0, 0, piece);
                return;
            } else if (turnNumber == 2) {
                if (board.get(1, 1) == ' ') {
                    board.set(1, 1, piece);
                    return;
                } else if (board.get(0, 0) == ' ') {
                    board.set(0, 0, piece);
                    return;
                }
            } else if (turnNumber == 3) {
                try {
                    int[] opp = getFirstOpp(board, piece);
                    int oppRow = opp[0];
                    //int oppCol = opp[1];
                    //System.out.println("wa");
                    if (oppRow == 0) {
                        board.set(2, 0, piece);
                        return;
                    } else if (oppRow == 1) {
                        board.set(0, 2, piece);
                        return;
                    } else {
                        board.set(0, 2, piece);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // check if win diagonal
        int selfC = 0;
        int oppC = 0;
        int spaceC = 0;
        for (int i = 0; i < board.size(); ++i) {
            int j = i;
            char p = board.get(i, j);
            if (p == ' ') spaceC++;
            else if (p == piece) selfC++;
            else oppC++;
        }

        if (spaceC == 1 && (selfC == 0 || oppC == 0)) {
            // fill in that space
            for (int i = 0; i < board.size(); ++i) {
                int j = i;
                char p = board.get(i, j);
                if (p == ' ') {
                    board.set(i, j, piece);
                    return;
                }
            }
        }

        selfC = 0;
        oppC = 0;
        spaceC = 0;
        for (int i = 0; i < board.size(); ++i) {
            int j = board.size() - i - 1;
            char p = board.get(i, j);
            if (p == ' ') spaceC++;
            else if (p == piece) selfC++;
            else oppC++;

        }

        if (spaceC == 1 && (selfC == 0 || oppC == 0)) {
            // fill in that space
            for (int i = 0; i < board.size(); ++i) {
                int j = board.size() - i - 1;
                char p = board.get(i, j);
                if (p == ' ') {
                    board.set(i, j, piece);
                    return;
                }
            }
        }

        // check if win row col

        boolean[] selfWinnableRows = new boolean[board.size()];
        boolean[] oppWinnableRows = new boolean[board.size()];
        boolean[] selfWinnableCols = new boolean[board.size()];
        boolean[] oppWinnableCols = new boolean[board.size()];
        int[] selfCountRows = new int[board.size()];
        int[] selfCountCols = new int[board.size()];
        int[] oppCountRows = new int[board.size()];
        int[] oppCountCols = new int[board.size()];

        // checks if any rows can be won
        for (int i = 0; i < board.size(); ++i) {
            boolean containsSelf = false;
            boolean containsOpp = false;
            boolean hasEmpty = false;
            int selfCount = 0;
            int oppCount = 0;

            for (int j = 0; j < board.size(); ++j) {
                char p = board.get(i, j);

                if (p == ' ') {
                    hasEmpty = true;
                } else if (p == piece) {
                    containsSelf = true;
                    selfCount++;
                } else {
                    containsOpp = true;
                    oppCount++;
                }
            }

            selfCountRows[i] = selfCount;
            oppCountRows[i] = oppCount;

            if (!hasEmpty) continue;

            if (!containsSelf && !containsOpp) {
                selfWinnableRows[i] = true;
                oppWinnableRows[i] = true;
            }

            if (containsSelf && !containsOpp) {
                selfWinnableRows[i] = true;
            }
            if (containsOpp && !containsSelf) {
                oppWinnableRows[i] = true;
            }
        }

        // checks if any cols can be won
        for (int i = 0; i < board.size(); ++i) {
            boolean containsSelf = false;
            boolean containsOpp = false;
            boolean hasEmpty = false;
            int selfCount = 0;
            int oppCount = 0;
            for (int j = 0; j < board.size(); ++j) {
                char p = board.get(j, i);

                if (p == ' ') {
                    hasEmpty = true;
                } else if (p == piece) {
                    containsSelf = true;
                    selfCount++;
                } else {
                    containsOpp = true;
                    oppCount++;
                }
            }

            selfCountCols[i] = selfCount;
            oppCountCols[i] = oppCount;

            if (!hasEmpty) continue;

            if (!containsSelf && !containsOpp) {
                selfWinnableCols[i] = true;
                oppWinnableCols[i] = true;
            }

            if (containsSelf && !containsOpp) {
                selfWinnableCols[i] = true;
            }

            if (containsOpp && !containsSelf) {
                oppWinnableCols[i] = true;
            }
        }

        int[] selfInRowRows = new int[board.size()];
        int[] selfInRowCols = new int[board.size()];
        int[] oppInRowRows = new int[board.size()];
        int[] oppInRowCols = new int[board.size()];

        for (int i = 0; i < selfWinnableRows.length; ++i) {
            if (selfWinnableRows[i]) {
                int count = selfCountRows[i];
                selfInRowRows[count]++;
            }
        }
        for (int i = 0; i < selfWinnableCols.length; ++i) {
            if (selfWinnableCols[i]) {
                int count = selfCountCols[i];
                selfInRowCols[count]++;
            }
        }
        for (int i = 0; i < oppWinnableRows.length; ++i) {
            if (oppWinnableRows[i]) {
                int count = oppCountRows[i];
                oppInRowRows[count]++;
            }
        }
        for (int i = 0; i < oppWinnableCols.length; ++i) {
            if (oppWinnableCols[i]) {
                int count = oppCountCols[i];
                oppInRowCols[count]++;
            }
        }

        if (board.size() == 3) {
            int turnNumber = getBoardTurnNumber(board);
            if (selfInRowRows[board.size() - 1] == 0 &&
                    selfInRowCols[board.size() - 1] == 0 &&
                    oppInRowRows[board.size() - 1] == 0 &&
                    oppInRowCols[board.size() - 1] == 0) {
                if (turnNumber == 4) {
                    if ((board.get(1, 1) != ' ' && board.get(1, 1) != piece)
                            && (board.get(2, 2) != ' ' && board.get(2, 2) != piece)) {
                        board.set(2, 0, piece);
                        return;
                    }
                } else if (turnNumber == 5) {
                    if (selfCountCols[0] == 2) {
                        board.set(2, 2, piece);
                        return;
                    } else if (selfCountRows[0] == 2) {
                        if (board.get(1, 0) != piece && board.get(1, 0) != ' ') {
                            board.set(2, 2, piece);
                            return;
                        }
                        if (board.get(2, 0) != piece && board.get(2, 0) != ' ') {
                            board.set(2, 2, piece);
                            return;
                        }
                        board.set(2, 0, piece);
                        return;
                    }
                }
            }
        }

        for (int i = board.size() - 1; i >= 0; i--) {
            int selfRowsCount = selfInRowRows[i];
            //System.out.println(i + " self rows count: " + selfRowsCount);
            if (selfRowsCount > 0) {
                // get a row index that contains this self in row rows
                try {
                    int rowIndex = getRowIndexForInRowRows(board, i, selfWinnableRows, selfCountRows);
                    int colIndex = getNextInRow(board, rowIndex);
                    board.set(rowIndex, colIndex, piece);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            int selfColsCount = selfInRowCols[i];
            if (selfColsCount > 0) {
                try {
                    int colIndex = getRowIndexForInRowRows(board, i, selfWinnableCols, selfCountCols);
                    int rowIndex = getNextInCol(board, colIndex);
                    board.set(rowIndex, colIndex, piece);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            int oppRowsCount = oppInRowRows[i];
            // System.out.println(i + " opp rows count: " + oppRowsCount);
            if (oppRowsCount > 0) {

                try {
                    int rowIndex = getRowIndexForInRowRows(board, i, oppWinnableRows, oppCountRows);
                    int colIndex = getNextInRow(board, rowIndex);
                    board.set(rowIndex, colIndex, piece);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            int oppColsCount = oppInRowCols[i];
            if (oppColsCount > 0) {
                try {
                    int colIndex = getRowIndexForInRowRows(board, i, oppWinnableCols, oppCountCols);
                    int rowIndex = getNextInCol(board, colIndex);
                    board.set(rowIndex, colIndex, piece);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            int[] nextEmpty = getNextEmpty(board);
            board.set(nextEmpty[0], nextEmpty[1], piece);
        } catch (Exception e) {
            throw new IllegalArgumentException("DRAW / SOMEONE WON");
        }
    }

    private static int getRowIndexForInRowRows(TTTBoard board, int inARow,
                                               boolean[] selfWinnableRows,
                                               int[] selfCountRows) throws Exception {
        for (int i = 0; i < board.size(); ++i) {
            // if not winnable find next
            if (!selfWinnableRows[i]) continue;
            if (selfCountRows[i] == inARow) return i;
        }
        throw new Exception("uhoh");
    }

    private static int getNextInRow(TTTBoard board, int targetRow) throws Exception {
        for (int i = 0; i < board.size(); ++i) {
            char p = board.get(targetRow, i);
            if (p == ' ') return i;
        }
        throw new Exception("this bad");
    }

    private static int getNextInCol(TTTBoard board, int targetCol) throws Exception {
        for (int i = 0; i < board.size(); ++i) {
            char p = board.get(i, targetCol);
            if (p == ' ') return i;
        }
        throw new Exception("this bad");
    }

    private static int[] getNextEmpty(TTTBoard board) throws Exception {
        for (int i = 0; i < board.size(); ++i) {
            for (int j = 0; j < board.size(); ++j) {
                if (board.get(i, j) == ' ') return new int[]{i, j};
            }
        }
        throw new Exception("bruh its full");
    }
}
