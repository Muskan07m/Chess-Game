
package piece;

import main.Board;
import main.GamePanel;
import main.Main.Type;

import java.awt.image.BufferedImage;
public class Queen extends Piece{

    public Queen(int color, int col,int row){
        super(color, col, row);
        type = Type.QUEEN;

        this.sprite =sheet.getSubimage( 1 * sheetScale, color == GamePanel.white ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(Board.tilesize, Board.tilesize, BufferedImage.SCALE_SMOOTH);
        
    }

    public boolean canMove(int targetCol, int targetRow){

        if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false){
            //vertical and horizontal
            if(targetCol == precol || targetRow == preRow ){ 
                if(isValidSquare(targetCol, targetRow) && pieceIsonStraightLine(targetCol, targetRow) == false){
                    return true;
                }
            }


            //Diagonal
            if(Math.abs(targetCol - precol) == Math.abs( targetRow - preRow) ){ 
                if(isValidSquare(targetCol, targetRow) && pieceIsonDiagonalLine(targetCol, targetRow) == false){
                    return true;
                }
            }
        }
        return false;
    }
    
}
