# Chess
2-Player Chess Game

# 2-Player Chess Game

## Overview

This project is a 2-Player Chess game designed to be played in the terminal. The game follows standard chess rules and provides a text-based interface for two players to enjoy a classic game of chess.

## Features

- **Standard Chess Rules**: Implements basic chess moves, castling, en passant, pawn promotion, check, checkmate, and illegal move detection.
- **Text-Based Interface**: Displays the chess board and prompts players for their moves in the terminal.
- **Robust Error Handling**: Ensures illegal moves are detected and handled appropriately.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed on your system.

### **Playing the Game**

The game starts with the white player's move.

Input moves in the format FileRank FileRank (e.g., e2 e4).

Special moves:

Castling: e1 g1 (for white king's side castling). 

Pawn Promotion: g7 g8 N (promote to knight).

Resign: resign.

Draw: Append draw? to a move (e.g., e2 e4 draw?).

Example Gameplay:


e2 e4
bR bN bB bQ bK bB bN bR 8
bp bp bp bp bp bp bp bp 7
   --    --    --    -- 6
--    --    --    --    5
   --    -- wp --    -- 4
--    --    --    --    3
wp wp wp wp    wp wp wp 2
wR wN wB wQ wK wB wN wR 1
 a  b  c  d  e  f  g  h

g8 h6

bR bN bB bQ bK bB    bR 8
bp bp bp bp bp bp bp bp 7
   --    --    --    bN 6
--    --    --    --    5
   --    -- wp --    -- 4
--    --    --    --    3
wp wp wp wp    wp wp wp 2
wR wN wB wQ wK wB wN wR 1
 a  b  c  d  e  f  g  h



## **Development Details**
Packages: All classes are under the chess package.

Main Class: Chess with methods start and play.

Testing: Use PlayChess to test the game implementation.



**Authors**
Hasnain Shahzad
