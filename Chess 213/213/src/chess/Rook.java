package chess;

import java.util.ArrayList;
import java.util.List;

public class Rook implements Piece {
    private final boolean isWhite;
    private final int row;
    private final int column;
    private boolean hasMoved;

    public Rook(int row, int column, boolean isWhite) {
        this.row = row;
        this.column = column;
        this.isWhite = isWhite;
        this.hasMoved = false;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board, Move move) {
        List<Move> legalMoves = new ArrayList<>();
        // Directions the Rook can move: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

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

                if (board.getPiece(currentRow, currentColumn) == null) {
                    // If the tile is not occupied, add as a legal move
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

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean isWhitePiece() {
        return this.isWhite;
    }
    
}
