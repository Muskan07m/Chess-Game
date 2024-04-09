package main;

import piece.Piece;

public class Move {
    public Piece piece;
    public int oldCol, oldRow;
    public int newCol, newRow;

    public Move(Piece piece, int oldCol, int oldRow, int newCol, int newRow) {
        this.piece = piece;
        this.oldCol = oldCol;
        this.oldRow = oldRow;
        this.newCol = newCol;
        this.newRow = newRow;
    }
}
