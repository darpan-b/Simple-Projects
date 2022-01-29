package TicTacToe;

class Board {
    private char[][] board;

    public Board() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println(" |   |   |   | ");
            System.out.println(" | " + board[i][0] + " | " + board[i][1] + " | " + board[i][2] + " | ");
            System.out.println(" |   |   |   | ");
            if (i < 2)
                System.out.println("---------------");
        }
        System.out.println("---------------------------------------------------------------");
    }

    public void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public boolean insertElement(char element, int position, boolean isRandom) {
        // decrementing position so that it becomes 0-indexed
        position--;
        
        int rowNumber = position / 3;
        int columnNumber = position % 3;

        if (element != 'X' && element != 'O') {
            if (!isRandom)
                System.out.println("Invalid element entered.");
            return false;
        }
        if (rowNumber > 2 || columnNumber > 2 || rowNumber < 0 || columnNumber < 0) {
            if (!isRandom)
                System.out.println("Invalid position entered.");
            return false;
        }
        if (board[rowNumber][columnNumber] != ' ') {
            if (!isRandom)
                System.out.println("Position is already occupied.");
            return false;
        }

        board[rowNumber][columnNumber] = element;
        return true;
    }

    public boolean isWin() {
        // check each row
        for (int i = 0; i < 3; i++) {
            boolean isWinInCurrentRow = true;
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != board[i][0])
                    isWinInCurrentRow = false;
            }
            if (isWinInCurrentRow && board[i][0] != ' ')
                return true;
        }

        // check each column
        for (int i = 0; i < 3; i++) {
            boolean isWinInCurrentColumn = true;
            for (int j = 0; j < 3; j++) {
                if (board[j][i] != board[0][i])
                    isWinInCurrentColumn = false;
            }
            if (isWinInCurrentColumn && board[0][i] != ' ')
                return true;
        }

        // check each diagonal
        boolean isWinInCurrentDiagonal = true;
        for (int i = 0; i < 3; i++) {
            if (board[i][i] != board[0][0])
                isWinInCurrentDiagonal = false;
        }
        if (isWinInCurrentDiagonal && board[0][0] != ' ')
            return true;
        isWinInCurrentDiagonal = true;
        for (int i = 0; i < 3; i++) {
            if (board[i][2 - i] != board[0][2])
                isWinInCurrentDiagonal = false;
        }
        if (isWinInCurrentDiagonal && board[0][2] != ' ')
            return true;

        return false;
    }

    public boolean isDraw() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell != 'X' && cell != 'O')
                    return false;
            }
        }
        return true;
    }

}

