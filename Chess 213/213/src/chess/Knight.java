package chess;

import java.util.ArrayList;
import java.util.List;

public class Knight implements Piece {
    private final boolean isWhite;
    private final int row;
    private final int column;

    public Knight(int row, int column, boolean isWhite) {
        this.row = row;
        this.column = column;
        this.isWhite = isWhite;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board, Move move) {
        List<Move> legalMoves = new ArrayList<>();

        // The 8 possible moves for a Knight
        int[][] moves = {
            {-2, -1}, {-2, 1}, 
            {-1, -2}, {-1, 2}, 
            {1, -2},  {1, 2}, 
            {2, -1},  {2, 1}
        };

        for (int[] m : moves) {
            int potentialRow = row + m[0];
            int potentialColumn = column + m[1];

            if (Board.isValidPosition(potentialRow, potentialColumn)) {
                ReturnPiece pieceAtDestination = board.getPiece(potentialRow, potentialColumn);
                if (pieceAtDestination == null || isOpponentPiece(pieceAtDestination)) {
                    legalMoves.add(new Move(row, column, potentialRow, potentialColumn));
                }
            }
        }

        return legalMoves;
    }

    private boolean isOpponentPiece(ReturnPiece piece) {
        boolean pieceIsWhite = piece.pieceType.name().charAt(0) == 'W';
        return isWhite != pieceIsWhite;
    }
}
