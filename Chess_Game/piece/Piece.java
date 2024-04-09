package piece;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Board;
import main.GamePanel;
import main.Main.Type;
import java.awt.*;

public class Piece {
    
    public Type type;
    public BufferedImage image;
    public int x,y;
    public int col,row, precol,preRow;
    public int color;
    public Piece hittPiece;
    public boolean moved, twoStepped;


    public Piece(int color, int col, int row){

        this.color = color;
        this.col = col;
        this.row = row;
        x = getX(col);
        y = getY(row);
        precol = col;
        preRow = row;
    }

    

    public int getX(int col){
        return col * Board.tilesize;
    }
    public int getY(int row){
        return row * Board.tilesize;
    }

    public int getCol(int x){
        return (x + Board.tilesize/2)/Board.tilesize;
    }
    public int getRow(int y){
        return (y + Board.tilesize/2)/Board.tilesize;
    }

    public int getIndex(){
        for(int index = 0; index < GamePanel.simpieces.size(); index++){
            if(GamePanel.simpieces.get(index)  == this){
                return index;
            }
        }
        return 0;
    }

    public void updatePosition(){
        
        // To check EnPassant
        if(type == Type.PAWN){
            if(Math.abs(row - preRow) == 2){
                twoStepped = true;
            }
        }
        x = getX(col);
        y = getY(row);
        precol = getCol(x);
        preRow = getRow(y);
        moved = true;
    }

    public void resetPosition(){
        col = precol;
        row = preRow;
        x =  getX(col);
        y = getY(row);
    }

    public boolean canMove(int targetCol, int targetRow){
        return false;
    }

    public  boolean isWithinBoard(int targetCol,int targetRow){
        if(targetCol >= 0 && targetCol <=7 &&targetRow>=0 && targetRow <= 7){
            return true;
        }
        return false;
    }

    public Piece getHittingP(int targetCol, int targetRow){

        for(Piece piece : GamePanel.simpieces){
            if(piece.col == targetCol && piece.row == targetRow && piece != this){
                 return piece;
            }
        }
        return null;
    }

    public boolean isSameSquare(int targetCol, int targetRow){

        if(targetCol == precol && targetRow == preRow){
            return true;
        }
        return false;
    }

    public boolean isValidSquare(int targetCol, int targetRow){
        hittPiece = getHittingP(targetCol, targetRow);

        if(hittPiece == null){
            return true;
        }else{  // This square is occupied
             
            if(hittPiece.color != this.color){ // if the color is different,it can be capture
                return true;
            }else{
                hittPiece = null;
            }
        }
         
        return false;
    }


    BufferedImage sheet; {
        try{
            sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("res/image.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    Board board;

    public Piece(Board board){
        this.board = board;
    }
    
    protected int sheetScale = sheet.getWidth()/6;
    public Image sprite;

    public void draw(Graphics2D g2){
        g2.drawImage(sprite , x, y ,Board.tilesize,Board.tilesize, null);
    }

    public boolean pieceIsonStraightLine(int targetCol, int targetRow){

        // left means our piece is at col 2 and new position is less than 2
        if (precol > targetCol) {
            for (int c = precol - 1; c > targetCol; c--) {
                Piece p = GamePanel.getPiece(c, targetRow);
                if(p != null){
                    hittPiece =p;
                    return true;
                }
            }
        }
        // Right
        if (precol < targetCol) {
            for (int c = precol + 1; c < targetCol; c++) {
                Piece p = GamePanel.getPiece(c, targetRow);
                if(p != null){
                    hittPiece =p;
                    return true;
                }
            }
        }
        // up
        if(preRow > targetRow){
            for(int r=targetRow+1 ; r<preRow ; r++){
                Piece p = GamePanel.getPiece(targetCol, r);
                if(p != null){
                    hittPiece =p;
                    return true;
                }
            }
        }
        //down
        if(preRow < targetRow){
            for(int r=targetRow-1 ; r>preRow ; r--){
                Piece p = GamePanel.getPiece(targetCol, r);
                if(p != null){
                    hittPiece =p;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean pieceIsonDiagonalLine(int targetCol, int targetRow){

        //up left
        if(precol > targetCol && preRow > targetRow){
            for(int i=1;i < Math.abs(precol - targetCol) ; i++){
                Piece p = GamePanel.getPiece(precol -i, preRow - i);
                if(p != null){
                    hittPiece =p;
                    return true;
                }
            }
        }
        // up right
        if(precol > targetCol && preRow > targetRow){
            for(int i=1;i < Math.abs(precol - targetCol) ; i++){
                Piece p = GamePanel.getPiece(precol +i, preRow - i);
                if(p != null){
                    hittPiece =p;
                    return true;
                }
            }
        }

        // down left
        if(precol > targetCol && preRow < targetRow){
            for(int i=1;i < Math.abs(precol - targetCol) ; i++){
                Piece p = GamePanel.getPiece(precol -i, preRow + i);
                if(p != null){
                    hittPiece =p;
                    return true;
                }
            }
        }

        //down right
        if(precol < targetCol && preRow < targetRow){
            for(int i=1;i < Math.abs(precol - targetCol) ; i++){
                Piece p = GamePanel.getPiece(precol +i, preRow + i);
                if(p != null){
                    hittPiece =p;
                    return true;
                }
            }
        }
        return false;
    }
    
}
