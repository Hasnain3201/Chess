#makefile begins
JFLAGS = -g
JC = javac
JVM = java
#FILE = 
.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Board.java \
	Chess.java \
	King.java \
	Move.java \
	Pawn.java \
	Piece.java \
	PlayChess.java \
	Rook.java 

MAIN = chess.PlayChess

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN)

clean:
	$(RM) *.class