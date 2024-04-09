package piece;

import main.Board;
import main.GamePanel;
import main.Main.Type;

import java.awt.image.BufferedImage;
public class Rook extends Piece {
    
    public Rook(int color, int col,int row){
        super(color, col, row);
       type = Type.ROOK;

        this.sprite =sheet.getSubimage( 4 * sheetScale, color == GamePanel.white ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(Board.tilesize, Board.tilesize, BufferedImage.SCALE_SMOOTH);  
    }

    public boolean canMove(int targetCol, int targetRow){

        if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false){
            
            if(targetCol == precol || targetRow == preRow ){ 
                if(isValidSquare(targetCol, targetRow) && pieceIsonStraightLine(targetCol, targetRow) == false){
                    return true;
                }
            }
        }
        return false;
    }
}
