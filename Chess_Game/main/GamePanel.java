package main;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Window.Type;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.AlphaComposite;
import java.awt.Color;
import piece.Knight;
import piece.Rook;
import piece.King;
import piece.Queen;
import piece.Bishop;
import piece.Pawn;
import piece.Piece;

public class GamePanel extends JPanel implements Runnable {

    final int FPS = 60;
    Thread gameThread;
    Board board = new Board();
    Mouse mouse = new Mouse();

    // color
    public static final int white = 0;
    public static final int Black = 1;
    int currentColor = white;

    // pieces
    public static ArrayList<Piece> pieces = new ArrayList<>();
    public static ArrayList<Piece> simpieces = new ArrayList<>();
    ArrayList<Piece> promopieces = new ArrayList<>();

    Piece activePiece , checkingPiece;
    public static Piece castlingPiece;

    // Booleans
    boolean canMove, validSquare, promotion , gameover, stalemate;

    public GamePanel() {
        setPreferredSize(new Dimension(1100, 800));
        setBackground(Color.black);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

        setPieces();
        copyPieces(pieces, simpieces);
    }

    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        // Game loop
        double drawInterval = 100000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

        }
    }

    public void setPieces() {

        pieces.add(new Rook(Black, 0, 0));
        pieces.add(new Knight(Black, 1, 0));
        pieces.add(new Bishop(Black, 2, 0));
        pieces.add(new Queen(Black, 3, 0));
        pieces.add(new King(Black, 4, 0));
        pieces.add(new Bishop(Black, 5, 0));
        pieces.add(new Knight(Black, 6, 0));
        pieces.add(new Rook(Black, 7, 0));

        pieces.add(new Pawn(Black, 0, 1));
        pieces.add(new Pawn(Black, 1, 1));
        pieces.add(new Pawn(Black, 2, 1));
        pieces.add(new Pawn(Black, 3, 1));
        pieces.add(new Pawn(Black, 4, 1));
        pieces.add(new Pawn(Black, 5, 1));
        pieces.add(new Pawn(Black, 6, 1));
        pieces.add(new Pawn(Black, 7, 1));

        pieces.add(new Rook(white, 0, 7));
        pieces.add(new Knight(white, 1, 7));
        pieces.add(new Bishop(white, 2, 7));
        pieces.add(new Queen(white, 3, 7));
        pieces.add(new King(white, 4, 7));
        pieces.add(new Bishop(white, 5, 7));
        pieces.add(new Knight(white, 6, 7));
        pieces.add(new Rook(white, 7, 7));

        pieces.add(new Pawn(white, 0, 6));
        pieces.add(new Pawn(white, 1, 6));
        pieces.add(new Pawn(white, 2, 6));
        pieces.add(new Pawn(white, 3, 6));
        pieces.add(new Pawn(white, 4, 6));
        pieces.add(new Pawn(white, 5, 6));
        pieces.add(new Pawn(white, 6, 6));
        pieces.add(new Pawn(white, 7, 6));
        
    }

    public static Piece getPiece(int targetCol, int targetRow) {

        for (Piece piece : simpieces) {
            if (piece.col == targetCol && piece.row == targetRow) {
                return piece;
            }
        }
        return null;
    }

    private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target) {
        target.clear();
        target.addAll(source);
    }

    private void changePlayer() {
        if (currentColor == white) {
            currentColor = Black;
            // Reset black's two stepped status
            for (Piece p : pieces) {
                if (p.color == Black) {
                    p.twoStepped = false;
                }
            }
        } else {
            currentColor = white;
            for (Piece p : pieces) {
                if (p.color == white) {
                    p.twoStepped = false;
                }
            }
        }
        activePiece = null;
    }

    // checkCastling
    private void checkCastling() {

        if (castlingPiece != null) {
            if (castlingPiece.col == 0) {
                castlingPiece.col += 3;
            } else if (castlingPiece.col == 7) {
                castlingPiece.col -= 2;
            }

            castlingPiece.x = castlingPiece.getX(castlingPiece.col);

        }
    }

    private boolean canPromote() {

        if (activePiece.type == Main.Type.PAWN) {
            if (currentColor == white && activePiece.row == 0 || currentColor == Black && activePiece.row == 7) {
                promopieces.clear();
                promopieces.add(new Rook(currentColor, 9, 2));
                promopieces.add(new Knight(currentColor, 9, 3));
                promopieces.add(new Bishop(currentColor, 9, 4));
                promopieces.add(new Queen(currentColor, 9, 5));
                return true;

            }
        }
        return false;
    }

    private void promoting() {

        if (mouse.pressed) {
            for (Piece piece : promopieces) {
                if (piece.col == mouse.x / Board.tilesize && piece.row == mouse.y / Board.tilesize) {

                    switch (piece.type) {
                        case ROOK:
                            simpieces.add(new Rook(currentColor, activePiece.col, activePiece.row));
                            break;

                        case KNIGHT:
                            simpieces.add(new Knight(currentColor, activePiece.col, activePiece.row));
                            break;

                        case BISHOP:
                            simpieces.add(new Bishop(currentColor, activePiece.col, activePiece.row));
                            break;

                        case QUEEN:
                            simpieces.add(new Queen(currentColor, activePiece.col, activePiece.row));
                            break;

                        default:
                            break;
                    }
                    simpieces.remove(activePiece.getIndex());
                    copyPieces(simpieces, pieces);
                    activePiece = null;
                    promotion = false;
                    changePlayer();
                }
            }
        }
    }

    private boolean isIllegal(Piece king) {

        if (king.type == Main.Type.KING) {
            for (Piece piece : simpieces) {
                if (piece != king && piece.color != king.color && piece.canMove(king.col, king.row)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isKingInCheck(){

        Piece king = getKing(true);
        if(activePiece.canMove(king.col, king.row)){
            checkingPiece = activePiece;
            return true;
        }else{
            checkingPiece = null;
        }
        return false;
    }
    
    //Find king
    private Piece getKing(boolean opponent){

        Piece king = null;

        for(Piece piece : simpieces){
            if(opponent){
                if(piece.type == Main.Type.KING && piece.color != currentColor){
                    king = piece;
                }
            }else{
                if(piece.type == Main.Type.KING && piece.color == currentColor){
                    king = piece;
                }
            }
        }
        return king;
    }

    private boolean isCheckmate(){
       
        Piece king = getKing(true);
        if(kingCanMove(king)){
            return false;
        }else{
            //But you still have a chance !!!
            // check if you can block the attack with your piece

            //check the position of the checking pieceand the king is in check
            int colDiff = Math.abs(checkingPiece.col - king.col);
            int rowDiff = Math.abs(checkingPiece.row - king.row);

            if(colDiff == 0){
                // The checking piece is attacking vertically
                if(checkingPiece.row < king.row){
                    // The checking piece is above theb king
                    for(int row = checkingPiece.row; row <  king.row; row++){
                        for(Piece p:simpieces){
                            if(p != king && p.color != currentColor && p.canMove(checkingPiece.col, row)){
                                return false;
                            }
                        }
                    }
                }
                if(checkingPiece.row > king.row){
                    // The checking piece is below the king

                    for(int row = checkingPiece.row; row >  king.row; row--){
                        for(Piece p:simpieces){
                            if(p != king && p.color != currentColor && p.canMove(checkingPiece.col, row)){
                                return false;
                            }
                        }
                    }
                }
            }else if(rowDiff == 0){
                // The checking piece is attacking horizontally

                if(checkingPiece.col < king.col){
                    // The checking piece is above theb king
                    for(int col = checkingPiece.col; col <  king.col; col++){
                        for(Piece p:simpieces){
                            if(p != king && p.color != currentColor && p.canMove(col, checkingPiece.row)){
                                return false;
                            }
                        }
                    }
                }
                if(checkingPiece.col > king.col){
                    // The checking piece is above theb king
                    for(int col = checkingPiece.col; col > king.col; col--){
                        for(Piece p:simpieces){
                            if(p != king && p.color != currentColor && p.canMove(col, checkingPiece.row)){
                                return false;
                            }
                        }
                    }
                }
            }else if(colDiff == rowDiff){
                // The checking piece is attacking diagonally

                if(checkingPiece.row < king.row){
                    //up left
                    if(checkingPiece.col < king.col){
                        for(int col = checkingPiece.col, row = checkingPiece.row;col<king.col;col++, row++){
                            for(Piece piece : simpieces){
                                if(piece != king && piece.color != currentColor && piece.canMove(col, row)){
                                    return false;
                                }
                            }
                        }
                    }
                     // up right
                    if(checkingPiece.col > king.col){
                        for(int col = checkingPiece.col, row = checkingPiece.row;col > king.col;col--, row++){
                            for(Piece piece : simpieces){
                                if(piece != king && piece.color != currentColor && piece.canMove(col, row)){
                                    return false;
                                }
                            }
                        }
                    } 
                }

                if(checkingPiece.row > king.row){
                    //down left
                    if(checkingPiece.col < king.col){
                        
                        for(int col = checkingPiece.col, row = checkingPiece.row;col<king.col;col++, row--){
                                for(Piece piece : simpieces){
                                    if(piece != king && piece.color != currentColor && piece.canMove(col, row)){
                                        return false;
                                    }
                                }
                            }
                        
                    }
                     // down right
                    if(checkingPiece.col > king.col){
                        for(int col = checkingPiece.col, row = checkingPiece.row;col > king.col;col--, row--){
                            for(Piece piece : simpieces){
                                if(piece != king && piece.color != currentColor && piece.canMove(col, row)){
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean kingCanMove(Piece king){

        //Simulate if there is any square where the king can move to
        if(isValidMove(king, -1, -1)) {return true;}
        if(isValidMove(king, 0, -1)) {return true;}
        if(isValidMove(king, 1, -1)) {return true;}
        if(isValidMove(king, -1, 0)) {return true;}
        if(isValidMove(king, 1, 0)) {return true;}
        if(isValidMove(king, -1, 1)) {return true;}
        if(isValidMove(king, 0, 1)) {return true;}
        if(isValidMove(king, 1, 1)) {return true;}

         return false;
    }
    private boolean isValidMove(Piece king, int colPlus, int rowPlus){
        boolean isValidMove = false;

        //Update the king's position for a second
        king.col += colPlus;
        king.row += rowPlus;

        if(king.canMove(king.col, king.row)){

            if(king.hittPiece != null){
                simpieces.remove(king.hittPiece.getIndex());
            }
            if(isIllegal(king) == false){
                isValidMove = true;
            }
        }
        king.resetPosition();
        copyPieces(pieces, simpieces);

        //Reset the king
        return isValidMove;

    }

    private boolean opponentCanCaptureKing(){

        Piece king = getKing(false);
        for(Piece piece : simpieces){
            if(piece.color != king.color && piece.canMove(king.col, king.row) ){
                return true;
            }
        }
        return false;
    }

    private boolean isStalemate(){

        int count =0;
        //Count the number of pieces
        for(Piece piece : simpieces){
            if(piece.color != currentColor){
                count++;
            }
        }

        // if only one piece (the king) is left
        if(count == 1 ){
            if(kingCanMove(getKing(true)) == false){
                return true;
            }
        }
        return false;
    }
    private void update() {

        if (promotion) {
            promoting();
        } else if(gameover == false ){
            if (mouse.pressed) {
                if (activePiece == null) {

                    for (Piece piece : simpieces) {

                        if (piece.color == currentColor && piece.col == mouse.x / Board.tilesize
                                && piece.row == mouse.y / Board.tilesize) {

                            activePiece = piece;
                        }
                    }
                } else {
                    simulate();
                    repaint();
                }
            }

            if (mouse.pressed == false) {
                if (activePiece != null) {

                    if (validSquare) {

                        // Move confirmed

                        // update the piece list in case a piece has been captured and removed during
                        // the simulation
                        copyPieces(simpieces, pieces);
                        activePiece.updatePosition();
                        
                        if (castlingPiece != null) {
                            castlingPiece.updatePosition();
                        }

                        if(isKingInCheck() && isCheckmate()){
                            gameover = true;
                        }else if(isStalemate() && isKingInCheck() == false){
                            stalemate = true;
                        }
                        else{ //The game is still going on
                            if (canPromote()) {
                                promotion = true;
                            } else {
                                changePlayer();
                            }
                        }

                    } else {
                        // The move is not valid so reset everything
                        copyPieces(pieces, simpieces);
                        activePiece.resetPosition();
                        activePiece = null;
                    }
                    repaint();
                }
            }
        }

    }

    private void simulate() {
        canMove = false;
        validSquare = false;

        // Reset the piece list in every loop
        // This is basically for restoring the removed piece during the simulation
        copyPieces(pieces, simpieces);

        if (castlingPiece != null) {
            castlingPiece.col = castlingPiece.precol;
            castlingPiece.x = castlingPiece.getX(castlingPiece.col);
            castlingPiece = null;
        }

        // If a piece is being held,update its position
        activePiece.x = mouse.x - Board.tilesize / 2;
        activePiece.y = mouse.y - Board.tilesize / 2;
        activePiece.col = activePiece.getCol(activePiece.x);
        activePiece.row = activePiece.getRow(activePiece.y);

        if (activePiece.canMove(activePiece.col, activePiece.row)) {

            canMove = true;
            if (activePiece.hittPiece != null) {
                simpieces.remove(activePiece.hittPiece.getIndex());

            }
            checkCastling();

            if (isIllegal(activePiece) == false && opponentCanCaptureKing() == false) {
                validSquare = true;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // BOARD
        board.draw(g2);

        // PIECES
        if (activePiece != null && canMove) {
            for (Piece p : simpieces) { // Render from simpieces during drag-and-drop
                p.draw(g2);
            }
        } else {
            for (Piece p : pieces) { // Render from pieces when move is confirmed
                p.draw(g2);
            }
        }
        if (activePiece != null) {

            if (canMove) {
                if (isIllegal(activePiece) || opponentCanCaptureKing()) {
                    g2.setColor(Color.red);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                    g2.fillRect(activePiece.col * Board.tilesize, activePiece.row * Board.tilesize, Board.tilesize,
                            Board.tilesize);

                } else {
                    g2.setColor(Color.white);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
                    g2.fillRect(activePiece.col * Board.tilesize, activePiece.row * Board.tilesize, Board.tilesize,
                            Board.tilesize);
                }

            }

            activePiece.draw(g2);
        }

        // STATUS MESSAGES
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setFont(new Font("Book Antiqua", Font.PLAIN, 40));
        g2.setColor(Color.white);

        if (promotion) {
            g2.setColor(Color.white); // Set the desired background color
            g2.fillRect(9 * Board.tilesize, 2 * Board.tilesize, Board.tilesize, Board.tilesize);
            g2.fillRect(9 * Board.tilesize, 3 * Board.tilesize, Board.tilesize, Board.tilesize);
            g2.fillRect(9 * Board.tilesize, 4 * Board.tilesize, Board.tilesize, Board.tilesize);
            g2.fillRect(9 * Board.tilesize, 5 * Board.tilesize, Board.tilesize, Board.tilesize);
            g2.drawString("Promote to:", 840, 150);
            for (Piece piece : promopieces) {
                g2.drawImage(piece.sprite, piece.getX(piece.col), piece.getY(piece.row), Board.tilesize, Board.tilesize,
                        null);
            }
        } else {
            if (currentColor == white) {
                g2.drawString("White's turn", 840, 400);
                if(checkingPiece != null && checkingPiece.color == Black){
                    g2.setColor(Color.red);
                    g2.drawString("Check", 840, 650);   
                }
            } else {
                g2.drawString("Black's turn", 840, 400);
                if(checkingPiece != null && checkingPiece.color == white){
                    g2.setColor(Color.red);
                    g2.drawString("Check", 840, 650);   
                }
            }
        }

        if(gameover){
            String s ="";
            if(currentColor == white){
                s = "White Wins";
            }else{
                s = "Black Wins";
            }
            g2.setFont(new Font("Arial",Font.PLAIN , 90));
            g2.setColor(Color.green);
            g2.drawString(s, 200, 420);
        }

        if(stalemate){
            g2.setFont(new Font("Arial",Font.PLAIN , 90));
            g2.setColor(Color.lightGray);
            g2.drawString("Stalemate", 200, 420);
        }
    }
}
