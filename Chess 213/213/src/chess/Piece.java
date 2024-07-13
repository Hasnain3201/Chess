package chess;

import java.util.List;

public interface Piece {
    List<Move> calculateLegalMoves(final Board board, Move move); //WARNING: Major implications from adding String Move here
}
