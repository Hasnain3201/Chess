//Hasnain Shahzad and Peter Sestito
package chess;

import java.util.ArrayList;
import java.util.List;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;
//import chess.Board;

class ReturnPiece {
	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
		            BP, BR, BN, BB, BK, BQ};
	static enum PieceFile {a, b, c, d, e, f, g, h};
	
	PieceType pieceType;
	PieceFile pieceFile;
	int pieceRank;  // 1..8
	boolean hasMoved;
	public String toString() {
		return ""+pieceFile+pieceRank+":"+pieceType;
	}
	public boolean equals(Object other) {
		if (other == null || !(other instanceof ReturnPiece)) {
			return false;
		}
		ReturnPiece otherPiece = (ReturnPiece)other;
		return pieceType == otherPiece.pieceType &&
				pieceFile == otherPiece.pieceFile &&
				pieceRank == otherPiece.pieceRank;
	}
	public boolean isOppositeColor(boolean isWhite) {
        return (this.pieceType.name().startsWith("W") != isWhite);
    }
	public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}

class ReturnPlay {
	enum Message {ILLEGAL_MOVE, DRAW, 
				  RESIGN_BLACK_WINS, RESIGN_WHITE_WINS, 
				  CHECK, CHECKMATE_BLACK_WINS,	CHECKMATE_WHITE_WINS, 
				  STALEMATE};
	
	ArrayList<ReturnPiece> piecesOnBoard;
	Message message;
}


public class Chess {
    
    private static Board board = new Board(); // The board instance
    private static Player currentPlayer = Player.white; // Track the current player

    enum Player { white, black }

    /**
     * Plays the next move for whichever player has the turn.
     * 
     * @param move String for next move, e.g. "a2 a3"
     * 
     * @return A ReturnPlay instance that contains the result of the move.
     */

	public static ReturnPlay play(String move) {
        ReturnPlay result = new ReturnPlay();
        move = move.trim(); // Trimming leading and trailing spaces
        
		boolean drawOffered = move.endsWith("draw?");

		// Check for resignation before parsing the move
		if (move.equalsIgnoreCase("resign")) {
			// Handle resignation
			result.message = currentPlayer == Player.white ? 
				ReturnPlay.Message.RESIGN_BLACK_WINS : ReturnPlay.Message.RESIGN_WHITE_WINS;
			result.piecesOnBoard = board.getPiecesOnBoard(); // The board state before resignation
			return result; // Immediately return after handling resignation
		}


		// Check for draw offer
		if (move.trim().endsWith("draw?")) {
			drawOffered = true;
			move = move.replace(" draw?", ""); // Remove the draw part from the move string
		}

		Move parsedMove = parseMove(move);

        try {
            ReturnPiece piece = board.getPiece(parsedMove.getStartRow(), parsedMove.getStartColumn());

            // Debugging information
            System.out.println("Current Player: " + currentPlayer);
            System.out.println("Parsed move: " + parsedMove.getStartRow() + parsedMove.getStartColumn() + "->" + parsedMove.getEndRow() + parsedMove.getEndColumn());
            System.out.println("Piece: " + piece);

            if (piece != null && isCorrectTurn(piece)) {

				//used to check if the King is currently in check or a resulting move on your end will put your king in position to be captured on the other player's turn
				boolean isWhiteKing = piece.pieceType.name().startsWith("W") ? true: false; //used to check if the current player's king is White or Black


				List<Move> legalMoves = calculateLegalMovesForPiece(piece, parsedMove);

				// Debugging: Print out legal moves
				for (Move m : legalMoves) {
					System.out.println("Legal move: " + m.getStartRow() + m.getStartColumn() + "->" + m.getEndRow() + m.getEndColumn());
				}

				if (legalMoves.contains(parsedMove)) {
					if (isPathClear(parsedMove, piece)) {
						// Handle special moves (like castling)
						System.out.println("Handling special moves...");
						handleSpecialMoves(parsedMove, piece);

						// Set hasMoved to true here
						piece.setHasMoved(true);

						// If the current Player's King is in check, see if the resulting move would get it out of check
						if (result.message != ReturnPlay.Message.ILLEGAL_MOVE) {
							//if current King is in Check
								Board seeIfCheckWillHappen = board;
								seeIfCheckWillHappen.setPiece(parsedMove.getEndRow(), parsedMove.getEndColumn(), piece);
								seeIfCheckWillHappen.setPiece(parsedMove.getStartRow(), parsedMove.getStartColumn(), null);
								if(seeIfCheckWillHappen.isKingInCheck(isWhiteKing)){ //the current move will NOT get the current king out of check
									System.out.println("The resulting move will put your king in check/NOT get your king out of Check");
									result.message = ReturnPlay.Message.ILLEGAL_MOVE;
								}
								seeIfCheckWillHappen = null;
						}

                        // Execute the move if it's not illegal due to special move constraints
                        if (result.message != ReturnPlay.Message.ILLEGAL_MOVE) {
                            System.out.println("Executing move...");
                            piece.setHasMoved(true);
                            piece.pieceRank = parsedMove.getEndRow() + 1;
                            piece.pieceFile = intToPieceFile(parsedMove.getEndColumn());
                            board.setPiece(parsedMove.getEndRow(), parsedMove.getEndColumn(), piece);
                            board.setPiece(parsedMove.getStartRow(), parsedMove.getStartColumn(), null);

							boolean isWhiteTurn = currentPlayer == Player.white;
							boolean isOpposingKingInCheck = board.isKingInCheck(!isWhiteTurn);

							if (isOpposingKingInCheck) {
								if (board.isCheckmate(!isWhiteTurn)) {
									// If the opposing king is checkmated, the current player wins
									result.message = isWhiteTurn ? ReturnPlay.Message.CHECKMATE_WHITE_WINS : ReturnPlay.Message.CHECKMATE_BLACK_WINS;
								} else {
									// If it's only a check, not checkmate
									result.message = ReturnPlay.Message.CHECK;
								}
							}

                            // Switch player
                            System.out.println("Switching player...");
                            currentPlayer = (currentPlayer == Player.white) ? Player.black : Player.white;

							// After a move is made, check for pawn promotion
							if ((piece.pieceType == PieceType.WP && parsedMove.getEndRow() == 7) ||
							(piece.pieceType == PieceType.BP && parsedMove.getEndRow() == 0)) {

							char promotionChar = 'Q'; // Default to Queen

							if (parsedMove.isPromotion()) {
								// Use the first character of the promotion piece's name as the promotion character
								promotionChar = parsedMove.getPromotionPiece().name().charAt(1);
							}

							System.out.println("Promotion character to use: " + promotionChar);

							board.promotePawn(parsedMove.getEndRow(), parsedMove.getEndColumn(), promotionChar);
							}



							// Check for check/checkmate after the move
							/*if (board.isCheckmate(currentPlayer)) {
								result.message = currentPlayer == Player.white ? 
									ReturnPlay.Message.CHECKMATE_BLACK_WINS : 
									ReturnPlay.Message.CHECKMATE_WHITE_WINS;
							} else if (board.isCheck(currentPlayer)) {
								result.message = ReturnPlay.Message.CHECK;
							}*/
			
							// If a draw was offered and no checkmate occurred
							if (drawOffered && result.message == null) {
								result.message = ReturnPlay.Message.DRAW;
							}
                        }
                    } else {
                        System.out.println("Path not clear for move, move illegal.");
                        result.message = ReturnPlay.Message.ILLEGAL_MOVE;
                    }
                } else {
                    System.out.println("Move not contained in legal moves, move illegal.");
                    result.message = ReturnPlay.Message.ILLEGAL_MOVE;
                }
            } else {
                System.out.println("Piece not valid or not correct turn, move illegal.");
                result.message = ReturnPlay.Message.ILLEGAL_MOVE;
            }

            result.piecesOnBoard = board.getPiecesOnBoard();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            result.message = ReturnPlay.Message.ILLEGAL_MOVE;
			//makes sure the board prints if the move is out of array bounds
			result.piecesOnBoard = board.getPiecesOnBoard();
			result.piecesOnBoard.toString();
            return result;
        }
		
    }

	//Returns the corresponding PieceFile for the integer passed through
	//0 = a, 1 = b, ..., 7 = h
	public static PieceFile intToPieceFile(int x){
		PieceFile[] values = PieceFile.values();
		return PieceFile.valueOf(values[x].toString());
	}

	private static boolean isCorrectTurn(ReturnPiece piece) {
		boolean isWhiteTurn = currentPlayer == Player.white;
		boolean pieceIsWhite = piece.pieceType.name().startsWith("W");
		return isWhiteTurn == pieceIsWhite;
	}
	
	private static boolean isPathClear(Move move, ReturnPiece piece) {
		// Knights can jump over pieces
		if (piece.pieceType == PieceType.WN || piece.pieceType == PieceType.BN) {
			return true;
		}
	
		int startRow = move.getStartRow();
		int startCol = move.getStartColumn();
		int endRow = move.getEndRow();
		int endCol = move.getEndColumn();
		int rowStep = Integer.compare(endRow, startRow);
		int colStep = Integer.compare(endCol, startCol);
	
		// Check each square along the path
		int currentRow = startRow + rowStep;
		int currentCol = startCol + colStep;
		while (currentRow != endRow || currentCol != endCol) {
			if (board.getPiece(currentRow, currentCol) != null) {
				return false;
			}
			currentRow += rowStep;
			currentCol += colStep;
		}
		
		return true;
	}
	
	public static void handleSpecialMoves(Move move, ReturnPiece piece) {
		// Castling logic
		if ((piece.pieceType == PieceType.WK || piece.pieceType == PieceType.BK) && !piece.hasMoved) {
			boolean isKingSideCastling = move.getEndColumn() == 6;
			boolean isQueenSideCastling = move.getEndColumn() == 2;
			boolean isWhiteKing = piece.pieceType == PieceType.WK;
			int row = isWhiteKing ? 0 : 7;
	
			if ((isKingSideCastling && board.canCastleKingside(row)) || 
				(isQueenSideCastling && board.canCastleQueenside(row))) {
				// Check if the king's current position, the squares it passes through, or the square it lands on are under attack
				boolean canCastle = true;
				int[] kingPositionsToCheck = isKingSideCastling ? new int[]{4, 5, 6} : new int[]{4, 3, 2};
				for (int column : kingPositionsToCheck) {
					if (board.isSquareUnderAttack(row, column, isWhiteKing)) {
						canCastle = false;
						break;
					}
				}
	
				if (canCastle) {
					if (isKingSideCastling) {
						board.performCastleKingside(row);
					} else {
						board.performCastleQueenside(row);
					}
				} else {
					// Handle the illegal attempt to castle here
					System.out.println("Cannot castle through, out of, or into check.");
					// You could potentially set a flag here or handle it appropriately
				}
			}
		}
	
	}
	
    /**
     * This method should reset the game, and start from scratch.
     */
    public static void start() {
        board.initialize(); // Reset the board to its initial state
        currentPlayer = Player.white; // White always starts
		char fauxPlayer = 'b';
		String[][] fauxBoard = new String[8][9];
		printInitialBoard();
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 9; j++){
				if(i < 8){
					if(i == 2)
					fauxPlayer = 'w';
				if((i == 0 || i == 7) && (j == 0 || j == 7))
					fauxBoard[i][j] = fauxPlayer + "R ";
				else if((i == 0 || i == 7) && (j == 1 || j == 6))
					fauxBoard[i][j] = fauxPlayer + "N ";
				else if((i == 0 || i == 7) && (j == 2 || j == 5))
					fauxBoard[i][j] = fauxPlayer + "B ";
				else if((i == 0 || i == 7) && j == 3)
					fauxBoard[i][j] = fauxPlayer + "Q ";
				else if((i == 0 || i == 7) && j == 4)
					fauxBoard[i][j] = fauxPlayer + "K ";
				else if((i == 1 || i == 6) && j != 8)
					fauxBoard[i][j] = fauxPlayer + "p ";
				else if(j == 8)
					fauxBoard[i][j] = (8 - i) + "\n";
				else if ((j + i) % 2 == 0)
					fauxBoard[i][j] = "   ";
				else
					fauxBoard[i][j] = "## ";
				}
				else
					System.out.print(fauxBoard[i-8][j]);	
			}
		}
		System.out.println(" a  b  c  d  e  f  g  h\n");
    }
	
	public static void printInitialBoard() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				ReturnPiece piece = board.getPiece(row, col);
				String pieceNotation = piece != null ? piece.toString() : "##";
				System.out.print(pieceNotation + " ");
			}
			System.out.println(8 - row); // Print row number
		}
		System.out.println(" a  b  c  d  e  f  g  h");
	}
	

	private static Move parseMove(String moveString) {
		moveString = moveString.trim(); // Remove leading/trailing whitespace
	
		// Check for a draw offer and remove it from the move string
		if (moveString.endsWith("draw?")) {
			moveString = moveString.substring(0, moveString.length() - "draw?".length()).trim();
		}
	
		// Check for resignation
		if (moveString.equalsIgnoreCase("resign")) {
			throw new IllegalArgumentException("Resignation should be handled before parsing moves.");
		}
	
		String[] parts = moveString.split(" ");
		if (parts.length < 2 || parts.length > 3) {
			throw new IllegalArgumentException("Invalid move format");
		}
	
		// Convert chess notation to array indices
		int startRow = parts[0].charAt(1) - '1'; // '1' becomes 0, '2' becomes 1, etc.
		int startColumn = parts[0].charAt(0) - 'a'; // 'a' becomes 0, 'b' becomes 1, etc.
		int endRow = parts[1].charAt(1) - '1';
		int endColumn = parts[1].charAt(0) - 'a';
	
		// Check for pawn promotion
		PieceType promotionPiece = null;
		if (parts.length == 3 && parts[2].length() == 1) {
			char promotionChar = parts[2].charAt(0);
			for (int i = 0; i < parts.length; i++) {
				System.out.println("parts[" + i + "]: " + parts[i]);
			}			boolean isWhite = currentPlayer == Player.white;
			promotionPiece = Board.charToPieceType(promotionChar, isWhite);
			System.out.println("Parsed promotion character: " + promotionChar);
		}
	
		// Return the Move object, including the promotion piece if applicable
		return new Move(startRow, startColumn, endRow, endColumn, promotionPiece);
	}

	public static List<Move> calculateLegalMovesForPiece(ReturnPiece piece, Move move) { //MAJOR IMPLICATION ADDING (Move move) HERE
		List<Move> legalMoves = new ArrayList<>();
	
		// Determine the piece type
		switch (piece.pieceType) {
			case WP: // White Pawn
			case BP: // Black Pawn
				Pawn pawn = new Pawn(piece.pieceRank, piece.pieceFile.ordinal(), piece.pieceType.name().startsWith("W"));//try with piece.pieceRank -1
				//debug
				System.out.println("Pawn pieceRank = " + piece.pieceRank);
				System.out.println("Pawn pieceFile.ordinal = " + piece.pieceFile.ordinal());
				legalMoves = pawn.calculateLegalMoves(board, move);
				break;
				case WN: // White Knight
				case BN: // Black Knight
					Knight knight = new Knight(piece.pieceRank - 1, piece.pieceFile.ordinal(), piece.pieceType.name().startsWith("W"));
					legalMoves = knight.calculateLegalMoves(board, move);
					break;
			case WB: // White Bishop
			case BB: // Black Bishop
				Bishop bishop= new Bishop(piece.pieceRank, piece.pieceFile.ordinal(), piece.pieceType.name().startsWith("W"));
				legalMoves = bishop.calculateLegalMoves(board, move);
				break;
			case WR: // White Rook
			case BR: // Black Rook
				Rook rook = new Rook(piece.pieceRank -1, piece.pieceFile.ordinal(), piece.pieceType.name().startsWith("W"));
				legalMoves = rook.calculateLegalMoves(board, move);
				break;
			case WQ: // White Queen
			case BQ: // Black Queen
				Queen queen = new Queen(piece.pieceRank, piece.pieceFile.ordinal(), piece.pieceType.name().startsWith("W"));
				legalMoves = queen.calculateLegalMoves(board, move);
				break;
			case WK: // White King
			case BK: // Black King
				King king = new King(piece.pieceRank - 1, piece.pieceFile.ordinal(), piece.pieceType.name().startsWith("W"));
				legalMoves = king.calculateLegalMoves(board, move);
				break;
		}

		// Debug: Print all generated legal moves for this piece
		for (Move m : legalMoves) {
			System.out.println("Generated legal move: " + m);
		}

		if (piece.pieceType == PieceType.WP || piece.pieceType == PieceType.BP) {
			System.out.println("Legal moves for " + piece + ":");
			for (Move m : legalMoves) {
				System.out.println(m);
			}
		}
	
		return legalMoves;
	}
	
	
}
