package chess;

import java.util.ArrayList;
import java.util.List;

public class King implements Piece {
    private final boolean isWhite;
    private final int row;
    private final int column;
    private boolean hasMoved;

    public King(int row, int column, boolean isWhite) {
        this.row = row;
        this.column = column;
        this.isWhite = isWhite;
        this.hasMoved = false;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board, Move move) {
        List<Move> legalMoves = new ArrayList<>();

        // King can move one square in any direction
        int[][] offsets = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        for (int[] offset : offsets) {
            int potentialRow = row + offset[0];
            int potentialColumn = column + offset[1];

            if (Board.isValidPosition(potentialRow, potentialColumn)) {
                ReturnPiece pieceAtDestination = board.getPiece(potentialRow, potentialColumn);

                if (pieceAtDestination == null || isOpponentPiece(pieceAtDestination)) {
                    legalMoves.add(new Move(row, column, potentialRow, potentialColumn));
                }
            }
        }

        // Castling moves
        addCastlingMoves(legalMoves, board);

        return legalMoves;
    }

    private void addCastlingMoves(List<Move> legalMoves, Board board) {
        int kingRow = this.isWhite ? 0 : 7;
    
        if (!this.hasMoved && !board.isKingInCheck(this.isWhite)) {
            // Kingside Castling
            if (board.canCastleKingside(kingRow)) {
                legalMoves.add(new Move(row, column, row, column + 2)); // Castling move
            }
            // Queenside Castling
            if (board.canCastleQueenside(kingRow)) {
                legalMoves.add(new Move(row, column, row, column - 2)); // Castling move
            }
        }
    }
    

    private boolean isOpponentPiece(ReturnPiece piece) {
        boolean pieceIsWhite = piece.pieceType.name().charAt(0) == 'W';
        return this.isWhite != pieceIsWhite;
    }

    public boolean hasMoved() {
        return this.hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
