package piece;

import main.Board;
import main.GamePanel;
import main.Main.Type;

import java.awt.image.BufferedImage;
public class Knight extends Piece{
     

    public Knight(int color, int col,int row){
        super(color, col, row);
        type = Type.KNIGHT;
        this.sprite =sheet.getSubimage( 3 * sheetScale, color == GamePanel.white ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(Board.tilesize, Board.tilesize, BufferedImage.SCALE_SMOOTH);
        
    }

    public boolean canMove(int targetCol, int targetRow){

        if(isWithinBoard(targetCol, targetRow) &&isSameSquare(targetCol, targetRow) == false){
            
            if( Math.abs(targetCol - precol) * Math.abs(targetRow - preRow) == 2 ){
                
                if(isValidSquare(targetCol, targetRow)){
                    return true;
                }
            }
        }
        return false;
    }
}
