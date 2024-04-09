package piece;

import main.Board;
import main.GamePanel;
import main.Main.Type;

import java.awt.image.BufferedImage;
public class Bishop extends Piece {
    
    public Bishop(int color, int col,int row){
        super(color, col, row);
        type = Type.BISHOP;

        this.sprite =sheet.getSubimage( 2 * sheetScale, color == GamePanel.white ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(Board.tilesize, Board.tilesize, BufferedImage.SCALE_SMOOTH); 
    }


    public boolean canMove(int targetCol, int targetRow){

        if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false){
            
            if(Math.abs(targetCol - precol) ==Math.abs( targetRow - preRow) ){ 
                if(isValidSquare(targetCol, targetRow) && pieceIsonDiagonalLine(targetCol, targetRow) == false){
                    return true;
                }
            }
        }
        return false;
    }
}
