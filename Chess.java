package chess;

import java.util.ArrayList;
import java.util.Arrays;

import chess.ReturnPiece.PieceFile;
import chess.ReturnPiece.PieceType;

class ReturnPiece {
	static enum PieceType {WP, WR, WN, WB, WQ, WK, 
		            BP, BR, BN, BB, BK, BQ};
	static enum PieceFile {a, b, c, d, e, f, g, h};
	
	PieceType pieceType;
	PieceFile pieceFile;
	int pieceRank;  // 1..8
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
	
	private static ReturnPiece[][] board = new ReturnPiece[8][8];

	public static ArrayList<ReturnPiece> getBoardAsList() {
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

	enum Player { white, black }
	


	public static ReturnPlay play(String move) {
		// Temporary implementation for testing board initialization
		ReturnPlay testReturnPlay = new ReturnPlay();
		testReturnPlay.piecesOnBoard = getBoardAsList(); // This method converts your 2D array board to ArrayList
		testReturnPlay.message = null; // No game message is necessary for this test
	
		return testReturnPlay;
	}
	
	
	
	/**
	 * This method should reset the game, and start from scratch.
	 */
	public static void start() {
		// Clear the board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = null;
			}
		}

		for (int i = 0; i < 8; i++) {
			Arrays.fill(board[i], null);
		}
		
		// Initialize white pieces
		// Rooks
		board[0][0] = new ReturnPiece(); // White Rook at a1
		board[0][0].pieceType = PieceType.WR;
		board[0][0].pieceFile = PieceFile.a;
		board[0][0].pieceRank = 1;

		board[0][7] = new ReturnPiece(); // White Rook at h1
		board[0][7].pieceType = PieceType.WR;
		board[0][7].pieceFile = PieceFile.h;
		board[0][7].pieceRank = 1;

		// Knights
		board[0][1] = new ReturnPiece(); // White Knight at b1
		board[0][1].pieceType = PieceType.WN;
		board[0][1].pieceFile = PieceFile.b;
		board[0][1].pieceRank = 1;

		board[0][6] = new ReturnPiece(); // White Knight at g1
		board[0][6].pieceType = PieceType.WN;
		board[0][6].pieceFile = PieceFile.g;
		board[0][6].pieceRank = 1;

		// Bishops
		board[0][2] = new ReturnPiece(); // White Bishop at c1
		board[0][2].pieceType = PieceType.WB;
		board[0][2].pieceFile = PieceFile.c;
		board[0][2].pieceRank = 1;

		board[0][5] = new ReturnPiece(); // White Bishop at f1
		board[0][5].pieceType = PieceType.WB;
		board[0][5].pieceFile = PieceFile.f;
		board[0][5].pieceRank = 1;

		// Queen
		board[0][3] = new ReturnPiece(); // White Queen at d1
		board[0][3].pieceType = PieceType.WQ;
		board[0][3].pieceFile = PieceFile.d;
		board[0][3].pieceRank = 1;

		// King
		board[0][4] = new ReturnPiece(); // White King at e1
		board[0][4].pieceType = PieceType.WK;
		board[0][4].pieceFile = PieceFile.e;
		board[0][4].pieceRank = 1;

		// White Pawns
		for (int i = 0; i < 8; i++) {
			board[1][i] = new ReturnPiece();
			board[1][i].pieceType = PieceType.WP;
			board[1][i].pieceFile = PieceFile.values()[i];
			board[1][i].pieceRank = 2;
		}

		// Initialize black pieces
		// Rooks
		board[7][0] = new ReturnPiece(); // Black Rook at a8
		board[7][0].pieceType = PieceType.BR;
		board[7][0].pieceFile = PieceFile.a;
		board[7][0].pieceRank = 8;

		board[7][7] = new ReturnPiece(); // Black Rook at h8
		board[7][7].pieceType = PieceType.BR;
		board[7][7].pieceFile = PieceFile.h;
		board[7][7].pieceRank = 8;
		
		// Knights
		board[7][1] = new ReturnPiece(); // Black Knight at b8
		board[7][1].pieceType = PieceType.BN;
		board[7][1].pieceFile = PieceFile.b;
		board[7][1].pieceRank = 8;

		board[7][6] = new ReturnPiece(); // Black Knight at g8
		board[7][6].pieceType = PieceType.BN;
		board[7][6].pieceFile = PieceFile.g;
		board[7][6].pieceRank = 8;

		// Bishops
		board[7][2] = new ReturnPiece(); // Black Bishop at c8
		board[7][2].pieceType = PieceType.BB;
		board[7][2].pieceFile = PieceFile.c;
		board[7][2].pieceRank = 8;

		board[7][5] = new ReturnPiece(); // Black Bishop at f8
		board[7][5].pieceType = PieceType.BB;
		board[7][5].pieceFile = PieceFile.f;
		board[7][5].pieceRank = 8;

		// Queen
		board[7][3] = new ReturnPiece(); // Black Queen at d8
		board[7][3].pieceType = PieceType.BQ;
		board[7][3].pieceFile = PieceFile.d;
		board[7][3].pieceRank = 8;
		
		// King
		board[7][4] = new ReturnPiece(); // Black King at e8
		board[7][4].pieceType = PieceType.BK;
		board[7][4].pieceFile = PieceFile.e;
		board[7][4].pieceRank = 8;

		// Black Pawns
		for (int i = 0; i < 8; i++) {
			board[6][i] = new ReturnPiece();
			board[6][i].pieceType = PieceType.BP;
			board[6][i].pieceFile = PieceFile.values()[i];
			board[6][i].pieceRank = 7;
		}

	}
		
}

