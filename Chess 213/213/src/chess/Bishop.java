package chess;

import java.util.ArrayList;
import java.util.List;

public class Bishop implements Piece {
    private final boolean isWhite;
    private int row; //shouldn't be final becaue row can be changed
    private final int column;

    public Bishop(int row, int column, boolean isWhite) {
        this.row = row - 1; //try out -1
        this.column = column ;
        this.isWhite = isWhite;
    }

    public List<Move> calculateLegalMoves(final Board board, Move move){
        List<Move> legalMoves = new ArrayList<>();
        // Directions the Queen can move: up, down, left, right
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        for (int[] direction : directions) {
            int currentRow = this.row;
            int currentColumn = this.column;

            while (true) {
                currentRow += direction[0];
                currentColumn += direction[1];

                if (!Board.isValidPosition(currentRow, currentColumn)) {
                    // Break if the position is off the limits of the board
                    break;
                }
                //System.out.println("IsValid");

                if (board.getPiece(currentRow, currentColumn) == null) {
                    // If the tile is not occupied, add as a legal move
                    //System.out.println(currentRow + " " + currentColumn + " = legal");
                    legalMoves.add(new Move(this.row, this.column, currentRow, currentColumn));
                } else {
                    // If the tile is occupied, check if it's an opponent's piece
                    if (this.isWhite != board.isWhitePiece(currentRow, currentColumn)) {
                        legalMoves.add(new Move(this.row, this.column, currentRow, currentColumn));
                    }
                    // Whether it's an opponent's piece or our own, we can't move past it
                    break;
                }
            }
        }

        return legalMoves;
    }

    public boolean isWhitePiece() {
        return this.isWhite;
    }
    
}
