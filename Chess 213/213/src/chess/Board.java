package chess;

import java.util.ArrayList;
import java.util.List;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

public class Board {
    private ReturnPiece[][] board;

    public Board() {
        board = new ReturnPiece[8][8];
        initialize();
    }

    public void initialize() {
        // Initialize white pieces
        // Rooks
		board[0][0] = new ReturnPiece(); // White Rook at a1
		board[0][0].pieceType = PieceType.WR;
		board[0][0].pieceFile = PieceFile.a;
		board[0][0].pieceRank = 1;
		board[0][0].hasMoved = false; // Set hasMoved to false


		board[0][7] = new ReturnPiece(); // White Rook at h1
		board[0][7].pieceType = PieceType.WR;
		board[0][7].pieceFile = PieceFile.h;
		board[0][7].pieceRank = 1;
		board[0][7].hasMoved = false; // Set hasMoved to false


		// Knights
		board[0][1] = new ReturnPiece(); // White Knight at b1
		board[0][1].pieceType = PieceType.WN;
		board[0][1].pieceFile = PieceFile.b;
		board[0][1].pieceRank = 1;
		board[0][1].hasMoved = false; // Set hasMoved to false


		board[0][6] = new ReturnPiece(); // White Knight at g1
		board[0][6].pieceType = PieceType.WN;
		board[0][6].pieceFile = PieceFile.g;
		board[0][6].pieceRank = 1;
		board[0][6].hasMoved = false; // Set hasMoved to false


		// Bishops
		board[0][2] = new ReturnPiece(); // White Bishop at c1
		board[0][2].pieceType = PieceType.WB;
		board[0][2].pieceFile = PieceFile.c;
		board[0][2].pieceRank = 1;
		board[0][2].hasMoved = false; // Set hasMoved to false


		board[0][5] = new ReturnPiece(); // White Bishop at f1
		board[0][5].pieceType = PieceType.WB;
		board[0][5].pieceFile = PieceFile.f;
		board[0][5].pieceRank = 1;
		board[0][5].hasMoved = false; // Set hasMoved to false


		// Queen
		board[0][3] = new ReturnPiece(); // White Queen at d1
		board[0][3].pieceType = PieceType.WQ;
		board[0][3].pieceFile = PieceFile.d;
		board[0][3].pieceRank = 1;
		board[0][3].hasMoved = false; // Set hasMoved to false


		// King
		board[0][4] = new ReturnPiece(); // White King at e1
		board[0][4].pieceType = PieceType.WK;
		board[0][4].pieceFile = PieceFile.e;
		board[0][4].pieceRank = 1;
		board[0][4].hasMoved = false; // Set hasMoved to false


		// White Pawns
		for (int i = 0; i < 8; i++) {
			board[1][i] = new ReturnPiece();
			board[1][i].pieceType = PieceType.WP;
			board[1][i].pieceFile = PieceFile.values()[i];
			board[1][i].pieceRank = 2;
			board[1][i].hasMoved = false; // Set hasMoved to false

		}

		// Initialize black pieces
		// Rooks
		board[7][0] = new ReturnPiece(); // Black Rook at a8
		board[7][0].pieceType = PieceType.BR;
		board[7][0].pieceFile = PieceFile.a;
		board[7][0].pieceRank = 8;
		board[7][0].hasMoved = false; // Set hasMoved to false


		board[7][7] = new ReturnPiece(); // Black Rook at h8
		board[7][7].pieceType = PieceType.BR;
		board[7][7].pieceFile = PieceFile.h;
		board[7][7].pieceRank = 8;
		board[7][7].hasMoved = false; // Set hasMoved to false

		
		// Knights
		board[7][1] = new ReturnPiece(); // Black Knight at b8
		board[7][1].pieceType = PieceType.BN;
		board[7][1].pieceFile = PieceFile.b;
		board[7][1].pieceRank = 8;
		board[7][1].hasMoved = false; // Set hasMoved to false


		board[7][6] = new ReturnPiece(); // Black Knight at g8
		board[7][6].pieceType = PieceType.BN;
		board[7][6].pieceFile = PieceFile.g;
		board[7][6].pieceRank = 8;
		board[7][6].hasMoved = false; // Set hasMoved to false


		// Bishops
		board[7][2] = new ReturnPiece(); // Black Bishop at c8
		board[7][2].pieceType = PieceType.BB;
		board[7][2].pieceFile = PieceFile.c;
		board[7][2].pieceRank = 8;
		board[7][2].hasMoved = false; // Set hasMoved to false


		board[7][5] = new ReturnPiece(); // Black Bishop at f8
		board[7][5].pieceType = PieceType.BB;
		board[7][5].pieceFile = PieceFile.f;
		board[7][5].pieceRank = 8;
		board[7][5].hasMoved = false; // Set hasMoved to false


		// Queen
		board[7][3] = new ReturnPiece(); // Black Queen at d8
		board[7][3].pieceType = PieceType.BQ;
		board[7][3].pieceFile = PieceFile.d;
		board[7][3].pieceRank = 8;
		board[7][3].hasMoved = false; // Set hasMoved to false

		
		// King
		board[7][4] = new ReturnPiece(); // Black King at e8
		board[7][4].pieceType = PieceType.BK;
		board[7][4].pieceFile = PieceFile.e;
		board[7][4].pieceRank = 8;
		board[7][4].hasMoved = false; // Set hasMoved to false


		// Black Pawns
		for (int i = 0; i < 8; i++) {
			board[6][i] = new ReturnPiece();
			board[6][i].pieceType = PieceType.BP;
			board[6][i].pieceFile = PieceFile.values()[i];
			board[6][i].pieceRank = 7;
			board[6][i].hasMoved = false; // Set hasMoved to false

		}
    
		//null all other spaces out
		for(int i = 2; i < 6; i++){
			for(int j = 0; j < 8; j++){
				board[i][j] = null;
			}

		}
		
	}	

    public ReturnPiece getPiece(int row, int col) {
		ReturnPiece piece = board[row][col];
		// Debugging: Print the piece retrieved from the board
		System.out.println("Getting piece at [" + row + ", " + col + "]: " + (piece != null ? piece.toString() : "null"));
		return piece;
	}

    public void setPiece(int row, int col, ReturnPiece piece) {
        board[row][col] = piece;
    }

    /*public ArrayList<ReturnPiece> getPiecesOnBoard() {
        ArrayList<ReturnPiece> piecesList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    piecesList.add(board[i][j]);
                }
				else{
					piecesList.remove(board[i][j]);
				}
            }
        }
        return piecesList;
    }*/

	public ArrayList<ReturnPiece> getPiecesOnBoard() {
		ArrayList<ReturnPiece> piecesList = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null) {
					piecesList.add(board[i][j]);
				}
			}
		}
		return piecesList;
	}
	

	public static boolean isValidPosition(int row, int column) {
        return row >= 0 && row < 8 && column >= 0 && column < 8;
    }

	/*public void updatePawnBoard(int startRow, int startCol, int endRow, int endCol){
		int updatePiece = pawnBoard[startRow][startCol];
		pawnBoard[startRow][startCol] = 0;
		pawnBoard[endRow][endCol] = updatePiece + 1;
	}

	public int getPawnBoardIndex(int row, int col){
		return pawnBoard[row][col];
	}*/

	public boolean isWhitePiece(int row, int column) {
        if (!isValidPosition(row, column)) {
            throw new IllegalArgumentException("Position out of bounds");
        }
        ReturnPiece piece = board[row][column];
        return piece != null && piece.pieceType.name().startsWith("W");
    }

	
	public boolean isKingInCheck(boolean isWhiteKing) {
		// Find the king's position
		int kingRow = -1, kingCol = -1;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ReturnPiece currentKing = getPiece(i, j);
				if (currentKing != null && ((isWhiteKing && currentKing.pieceType == PieceType.WK) ||
									  (!isWhiteKing && currentKing.pieceType == PieceType.BK))) {
					kingRow = i;
					kingCol = j;
					break;
				}
			}
			if (kingRow != -1) break;
		}
	
		// Check for threats to the king
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ReturnPiece piece = getPiece(i, j);
				if (piece != null && piece.isOppositeColor(isWhiteKing)) {
					if (canPieceAttackSquare(piece, kingRow, kingCol)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isCheckmate(boolean isWhiteKing) {
		if (!isKingInCheck(isWhiteKing)) {
			return false; // The king is not in check, so it can't be checkmate.
		}
	
		List<Move> allPossibleMoves = generateAllPossibleMoves(isWhiteKing);
		for (Move move : allPossibleMoves) {
			if (!resultsInCheck(this, move, isWhiteKing)) { // Pass 'this' as the Board instance
				return false; // Found a move that can get the king out of check.
			}
		}
		return true; // No moves available to escape check, so it's checkmate.
	}
	

	public List<Move> generateAllPossibleMoves(boolean isWhitePlayer) {
		List<Move> allMoves = new ArrayList<>();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				ReturnPiece piece = getPiece(row, col);
				if (piece != null && piece.isOppositeColor(!isWhitePlayer)) {
					switch (piece.pieceType) {
						case WP:
						case BP:
							Pawn pawn = new Pawn(row, col, piece.pieceType.name().startsWith("W"));
							addAllPotentialMovesForPiece(pawn, row, col, allMoves);
							break;
						case WN:
						case BN:
							Knight knight = new Knight(row, col, piece.pieceType.name().startsWith("W"));
							addAllPotentialMovesForPiece(knight, row, col, allMoves);
							break;
						case WB:
						case BB:
							Bishop bishop = new Bishop(row, col, piece.pieceType.name().startsWith("W"));
							addAllPotentialMovesForPiece(bishop, row, col, allMoves);
							break;
						case WR:
						case BR:
							Rook rook = new Rook(row, col, piece.pieceType.name().startsWith("W"));
							addAllPotentialMovesForPiece(rook, row, col, allMoves);
							break;
						case WQ:
						case BQ:
							Queen queen = new Queen(row, col, piece.pieceType.name().startsWith("W"));
							addAllPotentialMovesForPiece(queen, row, col, allMoves);
							break;
						case WK:
						case BK:
							King king = new King(row, col, piece.pieceType.name().startsWith("W"));
							addAllPotentialMovesForPiece(king, row, col, allMoves);
							break;
					}
				}
			}
		}
		return allMoves;
	}
	
	public void addAllPotentialMovesForPiece(Piece piece, int currentRow, int currentCol, List<Move> allMoves) {
		for (int newRow = 0; newRow < 8; newRow++) {
			for (int newCol = 0; newCol < 8; newCol++) {
				Move potentialMove = new Move(currentRow, currentCol, newRow, newCol);
				allMoves.addAll(piece.calculateLegalMoves(this, potentialMove));
			}
		}
	}
	
	public boolean resultsInCheck(Board board, Move move, boolean isWhiteKing) {
		ReturnPiece originalPiece = board.getPiece(move.getEndRow(), move.getEndColumn());
		ReturnPiece movingPiece = board.getPiece(move.getStartRow(), move.getStartColumn());
	
		// Apply the move
		board.setPiece(move.getEndRow(), move.getEndColumn(), movingPiece);
		board.setPiece(move.getStartRow(), move.getStartColumn(), null);
	
		// Check if the king is in check
		boolean isInCheck = board.isKingInCheck(isWhiteKing);
	
		// Revert the move
		board.setPiece(move.getStartRow(), move.getStartColumn(), movingPiece);
		board.setPiece(move.getEndRow(), move.getEndColumn(), originalPiece);
	
		return isInCheck;
	}
	
	


    public boolean canCastleKingside(int row) {
		System.out.println("Checking canCastleKingside for row: " + row);
        // Assuming the king is at column 4
        ReturnPiece king = getPiece(row, 4);
        if (king == null || getPiece(row, 7) == null) return false;
        if (king.hasMoved || getPiece(row, 7).hasMoved) return false;

        // Check if squares between king and rook are empty
        for (int col = 5; col <= 6; col++) {
            if (getPiece(row, col) != null) return false;
        }

        // Check if the king passes through check
        if (!canKingCastle(row, 4, 6, king.pieceType == PieceType.WK)) return false;

        return true;
    }

    public boolean canCastleQueenside(int row) {
		System.out.println("Checking canCastleQueenside for row: " + row);
        // Assuming the king is at column 4
        ReturnPiece king = getPiece(row, 4);
        if (king == null || getPiece(row, 0) == null) return false;
        if (king.hasMoved || getPiece(row, 0).hasMoved) return false;

        // Check if squares between king and rook are empty
        for (int col = 1; col <= 3; col++) {
            if (getPiece(row, col) != null) return false;
        }

        // Check if the king passes through check
        if (!canKingCastle(row, 4, 2, king.pieceType == PieceType.WK)) return false;

        return true;
    }

    public boolean canKingCastle(int row, int startCol, int endCol, boolean isWhiteKing) {
		System.out.println("boolean canKingCastle was used");
        // Check each square between startCol and endCol to see if it's attacked
        for (int col = startCol; col <= endCol; col++) {
            if (isSquareUnderAttack(row, col, isWhiteKing)) {
                return false;
            }
        }
        return true;
    }

    // New method to perform kingside castling
    public boolean performCastleKingside(int row) {
		// Preconditions checked: the king and the rook have not moved, and the path between them is clear.
	
		// Retrieve the king and rook
		ReturnPiece king = getPiece(row, 4);
		ReturnPiece rook = getPiece(row, 7);
	
		// Move the king to the G-column and update its state
		setPiece(row, 6, king);
		king.setHasMoved(true);
		king.pieceFile = PieceFile.values()[6]; // Update the file
		king.pieceRank = row + 1; // Update the rank
		setPiece(row, 4, null);
	
		// Move the rook to the F-column and update its state
		setPiece(row, 5, rook);
		rook.setHasMoved(true);
		rook.pieceFile = PieceFile.values()[5]; // Update the file
		rook.pieceRank = row + 1; // Update the rank
		setPiece(row, 7, null);
	
		return true;
	}

    // New method to perform queenside castling
    public boolean performCastleQueenside(int row) {
		if (!canCastleQueenside(row)) return false;
	
		// Retrieve the king and rook
		ReturnPiece king = getPiece(row, 4);
		ReturnPiece rook = getPiece(row, 0);
	
		// Move the king to the C-column and update its state
		setPiece(row, 2, king);
		king.setHasMoved(true);
		king.pieceFile = PieceFile.values()[2]; // Update the file
		king.pieceRank = row + 1; // Update the rank, assuming the row is 0-indexed
		setPiece(row, 4, null);
	
		// Move the rook to the D-column and update its state
		setPiece(row, 3, rook);
		rook.setHasMoved(true);
		rook.pieceFile = PieceFile.values()[3]; // Update the file
		rook.pieceRank = row + 1; // Update the rank, assuming the row is 0-indexed
		setPiece(row, 0, null);
	
		return true;
	}
	

	public boolean isSquareUnderAttack(int row, int col, boolean isWhiteKing) {
		System.out.println("Checking if square [" + row + ", " + col + "] is under attack");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				ReturnPiece piece = getPiece(i, j);
				if (piece != null && piece.isOppositeColor(isWhiteKing)) {
					// Instead of calculating all legal moves, check if the piece can attack the square
					// This requires a method for each piece type to check if the square can be attacked
					if (canPieceAttackSquare(piece, row, col)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean canPieceAttackSquare(ReturnPiece piece, int targetRow, int targetCol) {
		int pieceRow = piece.pieceRank - 1; // Assuming rank is 1-indexed
		int pieceCol = piece.pieceFile.ordinal();
		int rowDiff = Math.abs(pieceRow - targetRow);
		int colDiff = Math.abs(pieceCol - targetCol);
	
		switch (piece.pieceType) {
			case WP:
			case BP:
				// Pawn attack logic (diagonal one square forward)
				if (rowDiff == 1 && colDiff == 1) {
					if (piece.pieceType == PieceType.WP && pieceRow < targetRow) {
						return true;
					}
					if (piece.pieceType == PieceType.BP && pieceRow > targetRow) {
						return true;
					}
				}
				break;
			case WN:
			case BN:
				// Knight attack logic (L-shape moves)
				if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
					return true;
				}
				break;
			case WB:
			case BB:
				// Bishop attack logic (diagonal moves)
				if (rowDiff == colDiff && isPathClearDiagonal(pieceRow, pieceCol, targetRow, targetCol)) {
					return true;
				}
				break;
			case WR:
			case BR:
				// Rook attack logic (straight moves)
				if ((rowDiff == 0 || colDiff == 0) && isPathClearStraight(pieceRow, pieceCol, targetRow, targetCol)) {
					return true;
				}
				break;
			case WQ:
			case BQ:
				// Queen attack logic (straight and diagonal moves)
				if ((rowDiff == colDiff && isPathClearDiagonal(pieceRow, pieceCol, targetRow, targetCol)) ||
					((rowDiff == 0 || colDiff == 0) && isPathClearStraight(pieceRow, pieceCol, targetRow, targetCol))) {
					return true;
				}
				break;
			case WK:
			case BK:
				// King attack logic (one square in any direction)
				if (rowDiff <= 1 && colDiff <= 1) {
					return true;
				}
				break;
		}
		return false;
	}
	
	private boolean isPathClearStraight(int startRow, int startCol, int endRow, int endCol) {
		if (startRow == endRow) {
			// Horizontal movement
			int minCol = Math.min(startCol, endCol);
			int maxCol = Math.max(startCol, endCol);
			for (int col = minCol + 1; col < maxCol; col++) {
				if (board[startRow][col] != null) {
					return false; // Path is not clear
				}
			}
		} else if (startCol == endCol) {
			// Vertical movement
			int minRow = Math.min(startRow, endRow);
			int maxRow = Math.max(startRow, endRow);
			for (int row = minRow + 1; row < maxRow; row++) {
				if (board[row][startCol] != null) {
					return false; // Path is not clear
				}
			}
		}
		return true; // Path is clear
	}

	private boolean isPathClearDiagonal(int startRow, int startCol, int endRow, int endCol) {
		int rowStep = Integer.compare(endRow, startRow);
		int colStep = Integer.compare(endCol, startCol);
		int currentRow = startRow + rowStep;
		int currentCol = startCol + colStep;
	
		while (currentRow != endRow && currentCol != endCol) {
			if (board[currentRow][currentCol] != null) {
				return false; // Path is not clear
			}
			currentRow += rowStep;
			currentCol += colStep;
		}
		return true; // Path is clear
	}
	
	
	public void promotePawn(int row, int col, char promotionChar) {
		//debug
		System.out.println("Attempting pawn promotion at [" + row + ", " + col + "] with promotion character: " + promotionChar);	
		if (!isValidPosition(row, col)) {
			throw new IllegalArgumentException("Position out of bounds for promotion");
		}
		if (board[row][col] == null || !(board[row][col].pieceType == PieceType.WP || board[row][col].pieceType == PieceType.BP)) {
			throw new IllegalArgumentException("No pawn at specified position for promotion");
		}

		if (promotionChar != 'Q' && promotionChar != 'R' && promotionChar != 'B' && promotionChar != 'N') {
			promotionChar = 'Q'; // Default to Queen
		}
	
		// Call the method to get the promoted PieceType
		PieceType promotedType = charToPieceType(promotionChar, board[row][col].pieceType == PieceType.WP);
	
		// Create a new ReturnPiece with the promoted type
		ReturnPiece promotedPiece = new ReturnPiece();
		promotedPiece.pieceType = promotedType;
		promotedPiece.pieceFile = PieceFile.values()[col]; // Use the column index to set the file
		promotedPiece.pieceRank = row + 1; // Assuming row is 0-indexed
		promotedPiece.hasMoved = true; // The new piece has technically moved to get to this position
	
		// Update the pawn position with the new promoted piece
		board[row][col] = promotedPiece;
		System.out.println("Promoting pawn at [" + row + ", " + col + "] to " + promotionChar);

	}

	
	public static PieceType charToPieceType(char promotionChar, boolean isWhite) {
		PieceType promotedType;
		switch (promotionChar) {
			case 'Q':
				promotedType = isWhite ? PieceType.WQ : PieceType.BQ;
				break;
			case 'R':
				promotedType = isWhite ? PieceType.WR : PieceType.BR;
				break;
			case 'B':
				promotedType = isWhite ? PieceType.WB : PieceType.BB;
				break;
			case 'N':
				promotedType = isWhite ? PieceType.WN : PieceType.BN;
				break;
			default:
				throw new IllegalArgumentException("Invalid promotion piece: " + promotionChar);
		}
	
		System.out.println("Converted " + promotionChar + " to PieceType: " + (isWhite ? "White" : "Black") + " " + promotedType);
	
		return promotedType;
	}
	
}	