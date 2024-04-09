package piece;

import main.Board;
import main.GamePanel;
import main.Main.Type;

import java.awt.image.BufferedImage;
public class King extends Piece{
    

    public King(int color, int col,int row){
        super(color, col, row);
       
        type = Type.KING;
        this.sprite =sheet.getSubimage( 0 * sheetScale, color == GamePanel.white ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(Board.tilesize, Board.tilesize, BufferedImage.SCALE_SMOOTH);
        
    }

    public boolean canMove(int targetCol, int targetRow){

        if(isWithinBoard(targetCol, targetRow) && isSameSquare(targetCol, targetRow) == false){
            
            if(Math.abs(targetCol - precol) + Math.abs(targetRow - preRow) == 1 || Math.abs(targetCol - precol) * Math.abs(targetRow - preRow) == 1 ){
                
                if(isValidSquare(targetCol, targetRow)){
                    return true;
                }
            }

            //CASTLING
            if(moved == false){

                //Right castling
                if(targetCol == precol + 2 && targetRow == preRow && pieceIsonStraightLine(targetCol, targetRow) == false){
                     for(Piece piece : GamePanel.simpieces){
                        if(piece.col == precol + 3 && piece.row == preRow && piece.moved == false){
                            return true;
                        }
                     }
                }

                //Right castling
                if(targetCol == precol -2 && targetRow == preRow && pieceIsonStraightLine(targetCol, targetRow) == false){
                    Piece p[] = new Piece[2];
                    for(Piece piece : GamePanel.simpieces){
                        if(piece.col == precol - 3 && piece.row == preRow ){
                            p[0] =piece;
                        }
                        if(piece.col == precol - 4 && piece.row == preRow ){
                            p[1] =piece;
                        }

                        if(p[0] == null && p[1] != null && p[1].moved == false){
                            GamePanel.castlingPiece = p[1];
                            return true;
                        }
                     }
                }

            }
        }
        return false;
    }
}
