package chess;

import java.util.Objects;

import chess.ReturnPiece.PieceType;

public class Move {
    private int startRow;
    private int startColumn;
    private int endRow;
    private int endColumn;
    private PieceType promotionPiece;

    // Constructor for moves without promotion
    public Move(int startRow, int startColumn, int endRow, int endColumn) {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
    }

    // Constructor for moves with promotion
    public Move(int startRow, int startColumn, int endRow, int endColumn, PieceType promotionPiece) {
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.endRow = endRow;
        this.endColumn = endColumn;
        this.promotionPiece = promotionPiece;

        System.out.println("Setting promotion piece in Move constructor: " + this.promotionPiece);
}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return startRow == move.startRow &&
               startColumn == move.startColumn &&
               endRow == move.endRow &&
               endColumn == move.endColumn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startRow, startColumn, endRow, endColumn);
    }

    // Getters
    public int getStartRow() {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public int getEndRow() {
        return endRow;
    }

    public int getEndColumn() {
        return endColumn;
    }

    // Getters and setters for promotionPiece
    public boolean isPromotion() {
        return this.promotionPiece != null;
    }
    

    public PieceType getPromotionPiece() {
        return promotionPiece;
    }



    /*public boolean isDiagonalMove(){
        for(int i = 1; i < 8; i++){
            if(((endRow == startRow + i) && (endColumn == startColumn + i)) || ((endRow == startRow + i) && (endColumn == startColumn - i)) || 
            ((endRow == startRow - i) && (endColumn == startColumn + i)) || ((endRow == startRow - i) && (endColumn == startColumn - i)))
                return true;
        }
        return false;
    }

    public boolean isCardinalMove(){
        if(((endRow == startRow) && (endColumn > startColumn)) || ((endRow == startRow) && (endColumn < startColumn)) || 
        ((endRow > startRow) && (endColumn == startColumn)) || ((endRow < startRow) && (endColumn == startColumn)))
            return true;
        else
            return false;
    }*/

}