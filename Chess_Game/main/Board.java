package main;

import java.awt.Graphics2D;
import java.awt.Color;

public class Board {
    
    final int max_col =8;
    final int max_row =8;
    public static final int tilesize = 100;

    public void draw(Graphics2D g2){

        for(int row =0; row < max_row; row++){
            for(int col =0; col < max_col; col++){
                
                g2.setColor((col + row) % 2 == 0 ? new Color(227, 198, 181) : new Color(157, 105, 53));
                g2.fillRect(col*tilesize, row*tilesize, tilesize, tilesize);
            }
        }
    }

}
