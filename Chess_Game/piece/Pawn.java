package piece;

import main.GamePanel;
import main.Board;
import main.GamePanel;
import java.awt.image.BufferedImage;
import main.Main.Type;
public class Pawn extends Piece{
    
    public Pawn(int color, int col,int row){
        super(color, col, row);
       
        type = Type.PAWN;
        
        this.sprite =sheet.getSubimage( 5 * sheetScale, color == GamePanel.white ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(Board.tilesize, Board.tilesize, BufferedImage.SCALE_SMOOTH);
        
    }

    public boolean canMove(int targetCol, int targetRow){

        if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false){
            
            //Define the move value based on its color
            int moveValue = (color == GamePanel.white) ? -1 : 1 ;
            
            // check the hitting piece
            hittPiece = getHittingP(targetCol, targetRow);
            
            // 1 square movement
            if(targetCol == precol && targetRow == preRow + moveValue && hittPiece == null){
                return true;
            }

            // 2 square movement
            if(moved == false && targetCol == precol && targetRow == preRow + moveValue * 2 && hittPiece == null && pieceIsonStraightLine(targetCol,targetRow) == false){
                return true;
            }

            //Diagonal movement and capture (if a piece is on a square diagonally in front of it)
            if(Math.abs(targetCol - precol) == 1 && targetRow == preRow + moveValue && hittPiece != null && hittPiece.color != color){
                  return true;
            }

            // En Passant
            if(Math.abs(targetCol - precol) == 1 && targetRow == preRow + moveValue ){
                for(Piece piece : GamePanel.simpieces){
                    if(piece.col == targetCol && piece.row == preRow && piece.twoStepped  == true){
                        hittPiece = piece;
                        return true;
                    }
                }
           }


        }
        return false;
    }
}
