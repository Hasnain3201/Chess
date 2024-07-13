package chess;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements Piece {
    private final boolean isWhite;
    private int row; //shouldn't be final becaue row can be changed
    private final int column;
    private int enPessantChance = 0;
    public boolean doubleMove;
    private static Pawn previousPawn = null; //maybe??? Unless this gets updated everytime

    public Pawn(int row, int column, boolean isWhite) {
        this.row = row - 1; //try out -1
        this.column = column ;
        this.isWhite = isWhite;
        this.doubleMove = false;
    }

    //@Override //What happens if I get rid of this?
    public List<Move> calculateLegalMoves(Board board, Move move) {
        List<Move> legalMoves = new ArrayList<>();

        // Direction for pawn movement (1 for black, -1 for white)
        int direction = isWhite ? -1 : 1; //1 : -1 instead of -1 : 1

        int initialRow = isWhite ? 1 : 6; // 1 for white and 6 for black in zero-based indexing

        // One-square forward move
        int forwardRow = move.getEndRow(); //row - direction; //isWhite ? (row - direction) : (row + direction);
        //if ((isWhite == true && (forwardRow != (move.getStartRow() + 1) || forwardRow != (move.getStartRow() + 2))) || (forwardRow != (move.getStartRow() - 1) || forwardRow != (move.getStartRow() - 2)))

        //Debugging: Testing Valid Positions for Pawn
        System.out.println("current direction = " + direction);
        System.out.println("forward row = " + forwardRow);
        System.out.println("column = " + column);
        System.out.println("Valid Position = " + Board.isValidPosition(forwardRow, column));
        System.out.println("Empty Position = " + board.getPiece(forwardRow, column) + "\n");

        //More debug
        System.out.println(Board.isValidPosition(forwardRow, column));
        if (Board.isValidPosition(this.row, column) && board.getPiece(forwardRow, column) == null) { //row + forwardRow instead of forwardRow in board.getPiece
            if ((isWhite == true && forwardRow == (move.getStartRow() + 1)) || (isWhite == false && forwardRow == (move.getStartRow() - 1))){
                legalMoves.add(new Move(this.row , column, forwardRow, column));
                //board.updatePawnBoard(this.row, column, forwardRow, column);
                //Debugging
                System.out.println(row + " " + column + " " + forwardRow + " " + column);
                System.out.println("\n " + legalMoves.get(0).getStartRow() + " " + legalMoves.get(0).getStartColumn() + " " + legalMoves.get(0).getEndRow() + " " + legalMoves.get(0).getEndColumn() + " ");
            }

            //Debugging
            System.out.println("current direction = " + direction);
            System.out.println("current column (pawn) = " + column);
            System.out.println("row = " + this.row);
            System.out.println("forwardRow = " + forwardRow);
            System.out.println("Two-square move calculation = " + (this.row + (2 * (direction * -1))));
            System.out.println("initialRow = " + initialRow + "\n");
            //System.out.println


            //enPessant
            if(Pawn.previousPawn != null){
                System.out.println("Pawn.previousPawn exists");
                System.out.println("Pawn.previousPawn.enPessantChance = " + Pawn.previousPawn.enPessantChance);
                System.out.println("move.getEndColumn = " + move.getEndColumn());
                System.out.println("column = " + column);
                System.out.println("forawrdRow = " + forwardRow);
                if(Pawn.previousPawn.enPessantChance == 1){
                    ReturnPiece pieceAtDestination = board.getPiece(forwardRow, move.getEndColumn());
                    if((row == initialRow + 3*(direction * -1)) && Pawn.previousPawn.doubleMove == true && (column + 1 == Pawn.previousPawn.column || column -1 == Pawn.previousPawn.column) && isWhite != Pawn.previousPawn.isWhite && pieceAtDestination == null){ //last 2 may be unnecessary
                        if(isWhite){
                            if(forwardRow == Pawn.previousPawn.row + 2 && move.getEndColumn() == Pawn.previousPawn.column){ // + 1 may be needed because of the minus 1, or even +2 honestly
                                legalMoves.add(new Move(row , column, forwardRow, move.getEndColumn()));
                                board.setPiece(Pawn.previousPawn.row + 1, Pawn.previousPawn.column, null); //+1 is needed here because of this.row
                                Pawn.previousPawn = null;
                                System.out.println("enpessant");
                            }
                            else
                                Pawn.previousPawn.enPessantChance = 0;
                        }
                        else{
                            //The test is going here correctly
                            System.out.println("Pawn.previousPawn.row - 1 = " +  (Pawn.previousPawn.row - 1));
                            System.out.println("Pawn.previousPawn.column = " + Pawn.previousPawn.column);
                            if(forwardRow == Pawn.previousPawn.row /***- 1***/ && move.getEndColumn() == Pawn.previousPawn.column){ //this.row on line 75 means that the row of previousPawn is decreased by 1 again, so the minus 1 isn't actually needed here
                                legalMoves.add(new Move(row , column, forwardRow, move.getEndColumn()));
                                board.setPiece(Pawn.previousPawn.row + 1, Pawn.previousPawn.column, null); //+1 is needed here because of this.row
                                Pawn.previousPawn = null;
                                System.out.println("enpessant");
                            }
                            else
                                Pawn.previousPawn.enPessantChance = 0;
                        }

                    }
                    else
                        Pawn.previousPawn.enPessantChance = 0;
                }
            }
            //EVERY INSTANCE OF previousPawn SHOULD BE PRECEDED BY Pawn. (Class identifier)
            /*CHECKS FOR EN PESSANT:
             * can piece of the same type en pessant each other: no, GOOD
             * can you enpessant after missing your oppertunity from a previous turn: [there may be edge cases I haven't attmepted here, but the couple checks I did do showed that it still worked]
             * can you en pessant being directly left or right from a piece: [Not fully tested, but should be good]
             * can you en pessant from being anywhere in that pieces row: no, GOOD
             * can both players en pessant each other?: yes, GOOD
             * Is enPessantChance getting update when the user puts in an invalid move?: Nope, GOOD
            */


            // Two-square initial move
            if (this.row == initialRow && board.getPiece(forwardRow, column) == null && forwardRow  == (this.row + (2 * (direction * -1)))) {
                legalMoves.add(new Move(this.row, column, forwardRow, column));
                Pawn.setPreviousPawn(new Pawn(forwardRow, column, isWhite));
                Pawn.previousPawn.doubleMove = true;
                Pawn.previousPawn.enPessantChance = 1;
                //board.updatePawnBoard(this.row, column, forwardRow, column);
                //debug
                System.out.println("Two-square initial move had been added");
            }
        }


        // Captures
        for (int offset : new int[]{-1, 1}) {
            int captureColumn = column + offset;
            if (forwardRow == (move.getStartRow() + (direction*-1)) && Board.isValidPosition(forwardRow, captureColumn)) {
                ReturnPiece pieceAtDestination = board.getPiece(forwardRow, captureColumn);
                if (pieceAtDestination != null && isOpponentPiece(pieceAtDestination)) {
                    legalMoves.add(new Move(row, column, forwardRow, captureColumn));
                    //board.updatePawnBoard(row, column, forwardRow, captureColumn);
                    //debug
                    System.out.println("Capture move had been added");
                }
            }
        }


        //Pawn promotion
        /*if(forwardRow == finalRow){

        }*/


        // Debugging: Print each legal move as it's added
        for (Move m : legalMoves) {
            System.out.println("Pawn legal move: " + m.getStartRow() + m.getStartColumn() + "->" + m.getEndRow() + m.getEndColumn());
        }
        this.row = forwardRow;//should be updated here since the row of the piece is about to be updated
        //MAY DELETE THIS LATER ON DEPENDING ON HOW WE FIX BOARD

        return legalMoves;
    }

    //Public?
    private boolean isOpponentPiece(ReturnPiece piece) {
        boolean pieceIsWhite = piece.pieceType.name().charAt(0) == 'W';
        return isWhite != pieceIsWhite;
    }

    public static void setPreviousPawn(Pawn currentPawn){
        previousPawn = currentPawn;
    }
    //no getter required since this value is kept track of by the class
    
}
