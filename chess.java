import java.util.*;
class Game{
    Scanner sc=new Scanner(System.in);
    String arr[][]=new String[100][102];
    String m;
    String p;
    int n;
    String chaal;
    String white[]={"rook1w" ,"rook2w" ,"knight1w" ,"knight2w" ,"bishop1w","bishop2w","kingw" ,"queenw","pawn1w" ,"pawn2w" ,"pawn3w" ,"pawn4w" ,"pawn5w","pawn6w","pawn7w" ,"pawn8w"};
    String Black[]= {"rook1b" ,"rook2b" ,"knight1b" ,"knight2b" ,"bishop1b","bishop2b","kingb" ,"queenb","pawn1b" ,"pawn2b" ,"pawn3b" ,"pawn4b" ,"pawn5b","pawn6b","pawn7b" ,"pawn8b"};
    void show(){
        switch(1){
            case 1: System.out.println(" 1 = Bishop");
            case 2:System.out.println("2 = Rook");
            case 3:System.out.println("3 = Knight");
            case 4:System.out.println("4 = KING");
            case 5:System.out.println("5 = QUEEN");
            case 6:System.out.println("6 = Pawn");
        }    
    }
    void read(){
        for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                if(i==1){
                 arr[i][1]="rook1w" ;
                 arr[i][8]= "rook2w" ;
                 arr[i][2]= "knight1w" ;
                 arr[i][7]= "knight2w" ;
                 arr[i][3]= "bishop1w";
                 arr[i][6]= "bishop2w";
                 arr[i][4]= "kingw" ;
                 arr[i][5]= "queenw"; 
                }else if(i==2){
                    arr[i][j]="pawn"+j+"w";
                }
                else if(i==8){
                          arr[i][1]="rook1b" ;
                        arr[i][8]= "rook2b" ;
                        arr[i][2]= "knight1b" ;
                        arr[i][7]= "knight2b" ;
                        arr[i][3]= "bishop1b";
                        arr[i][6]= "bishop2b";
                        arr[i][4]= "kingb" ;
                        arr[i][5]= "queenb";
                }
                else if(i==7){
                    arr[i][j]="pawn"+j+"b";
                }else{
                    arr[i][j]="#     ";
                }
                System.out.print(arr[i][j]+ " ");
            }
            System.out.println(" ");
        }
    }
    String rook(String haathi,int step ,String m){  
       for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                for(int k=0;k<=15;k++){
                    if(haathi.equals(white[k]) && arr[i][j].equals(haathi) ){
                        if(m.equals("up")  && (arr[i-step][j].charAt(arr[i-step][j].length()-1))!='w'){
                               arr[i][j]="#     ";
                               i=i-step;
                                arr[i][j]=haathi; 
                                  
                        }else if(m.equals("down")  &&   (arr[i+step][j].charAt(arr[i+step][j].length()-1))!='w'){
                            arr[i][j]="#     ";
                            i=i+step;
                             arr[i][j]=haathi;
                        
                        }else if(m.equals("right") &&  (arr[i][j+step].charAt(arr[i][j+step].length()-1))!='w'){
                            arr[i][j]="#     ";
                            j=j+step;
                             arr[i][j]=haathi;
                            
                        }else if(m.equals("left")&& (arr[i][j-step].charAt(arr[i][j-step].length()-1))!='w' ){
                                 arr[i][j]="#     ";
                                j=j-step;
                                 arr[i][j]=haathi;
                        
                       }
                    }else if(haathi.equals(Black[k]) && arr[i][j].equals(haathi) ){
                        if(m.equals("up")  && (arr[i-step][j].charAt(arr[i-step][j].length()-1))!='b'){
                               arr[i][j]="#     ";
                               i=i-step;
                                arr[i][j]=haathi; 
                          
                        }else if(m.equals("down")  &&   (arr[i+step][j].charAt(arr[i+step][j].length()-1))!='b'){
                            arr[i][j]="#     ";
                            i=i+step;
                              arr[i][j]=haathi;
                        
                        }else if(m.equals("right") &&  (arr[i][j+step].charAt(arr[i][j+step].length()-1))!='b'){
                            arr[i][j]="#     ";
                            j=j+step;
                              arr[i][j]=haathi;
                              
                        }else if(m.equals("left")&& (arr[i][j-step].charAt(arr[i][j-step].length()-1))!='b'){
                                arr[i][j]="#     ";
                                j=j-step;
                              arr[i][j]=haathi;
                              haathi=arr[i][j];
                        }
                    }
                }
            }
        }
    return haathi;
    }
    String bishop(String chaal,int step ,String move){
            for(int i=1;i<=8;i++){
              for(int j=1;j<=8;j++){
                for(int k=0;k<=15;k++){
                    if(chaal.equals(white[k]) && arr[i][j].equals(chaal)){
                        if(move.equals("up" ) ) {
                            System.out.println("on which direction left or right");
                            String d=sc.next();
                            if(d.equals("left") && (arr[i-step][j-step].charAt(arr[i-step][j-step].length()-1))!='w'){
                               arr[i][j]="#     ";
                               i=i-step;
                               j=j-step;
                              arr[i][j]=chaal;
                            }else if(d.equals("right") && (arr[i-step][j+step].charAt(arr[i-step][j+step].length()-1))!='w'){
                               arr[i][j]="#     ";
                               i=i-step;
                               j=j+step;
                               arr[i][j]=chaal;
                            }   
                        }else if(move.equals("down")   ){
                        System.out.println("on which direction left or right");
                        String d=sc.next();
                        if(d.equals("left") && (arr[i+step][j-step].charAt(arr[i+step][j-step].length()-1))!='w'){
                            arr[i][j]="#     ";
                            i=i+step;
                            j=j-step;
                            arr[i][j]=chaal;
                        }else if(d.equals("right") && (arr[i+step][j+step].charAt(arr[i+step][j+step].length()-1))!='w'){
                            arr[i][j]="#     ";
                            i=i+step;
                            j=j+step;
                            arr[i][j]=chaal;
                        }
                    }
                }else if(chaal.equals(Black[k]) && arr[i][j].equals(chaal)){
                    if(move.equals("up" ) ) {
                            System.out.println("on which direction left or right");
                            String d=sc.next();
                            if(d.equals("left") && (arr[i-step][j-step].charAt(arr[i-step][j-step].length()-1))!='b'){
                               arr[i][j]="#     ";
                               i=i-step;
                               j=j-step;
                              arr[i][j]=chaal;
                            }else if(d.equals("right") && (arr[i-step][j+step].charAt(arr[i-step][j+step].length()-1))!='b'){
                               arr[i][j]="#     ";
                               i=i-step;
                               j=j+step;
                               arr[i][j]=chaal;
                            }   
                        }else if(move.equals("down")   ){
                            System.out.println("on which direction left or right");
                            String d=sc.next();
                            if(d.equals("left") && (arr[i+step][j-step].charAt(arr[i+step][j-step].length()-1))!='b'){
                               arr[i][j]="#     ";
                               i=i+step;
                               j=j-step;
                               arr[i][j]=chaal;
                            }else if(d.equals("right") && (arr[i+step][j+step].charAt(arr[i+step][j+step].length()-1))!='b'){
                               arr[i][j]="#     ";
                               i=i+step;
                               j=j+step;
                               arr[i][j]=chaal;
                            }
                        }
                   } 
               } 
           }
       }
            return chaal;
    }
    String knight(String horse){
        System.out.println("enter your move left ,right,up,down");
        m=sc.next();
       for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                for(int k=0;k<=15;k++){
                if(horse.equals(white[k]) && arr[i][j].equals(horse)){
                    if(m.equals("up" ) ) {
                        System.out.println("on which direction left or right");
                        String d=sc.next();
                        if(d.equals("left") && (arr[i-2][j-1].charAt(arr[i-2][j-1].length()-1))!='w'){
                            arr[i][j]="#     ";
                            i=i-2;
                            j=j-1;
                            arr[i][j]=horse;
                        }else if(d.equals("right") && (arr[i-2][j+1].charAt(arr[i-2][j+1].length()-1))!='w'){
                            arr[i][j]="#     ";
                            i=i-2;
                            j=j+1;
                            arr[i][j]=horse;
                        }  
                    }else if(m.equals("down")   ){
                        System.out.println("on which direction left or right");
                        String d=sc.next();
                        if(d.equals("left") && (arr[i+2][j-1].charAt(arr[i+2][j-1].length()-1))!='w'){
                            arr[i][j]="#     ";
                            i=i+2;
                            j=j-1;
                            arr[i][j]=horse;
                        }else if(d.equals("right") && (arr[i+2][j+1].charAt(arr[i+2][j+1].length()-1))!='w'){
                            arr[i][j]="#     ";
                            i=i+2;
                            j=j+1;
                            arr[i][j]=horse;
                        }
                    }else if(m.equals("left")   ){
                        System.out.println("on which direction up or down");
                        String d=sc.next();
                        if(d.equals("down") && (arr[i+1][j-2].charAt(arr[i+1][j-2].length()-1))!='w'){
                            arr[i][j]="#     ";
                            i=i+1;
                            j=j-2;
                            arr[i][j]=horse;
                        }else if(d.equals("up") &&  (arr[i-1][j-2].charAt(arr[i-1][j-2].length()-1))!='w'){
                            arr[i][j]="#     ";
                            i=i-1;
                            j=j-2;
                            arr[i][j]=horse;
                        }
                    }else if(m.equals("right")   ){
                        System.out.println("on which direction up or down");
                        String d=sc.next();
                        if(d.equals("down") && (arr[i+1][j+2].charAt(arr[i+1][j+2].length()-1))!='w'){
                            arr[i][j]="#     ";
                            i=i+1;
                            j=j+2;
                            arr[i][j]=horse;
                        }else if(d.equals("up") && (arr[i-1][j+2].charAt(arr[i-1][j+2].length()-1))!='w'){
                            arr[i][j]="#     ";
                            i=i-1;
                            j=j+2;
                            arr[i][j]=horse;
                        }
                    }
                }else if(horse.equals(Black[k]) && arr[i][j].equals(horse)){
                    if(m.equals("up" ) ) {
                        System.out.println("on which direction left or right");
                        String d=sc.next();
                        if(d.equals("left") && (arr[i-2][j-1].charAt(arr[i-2][j-1].length()-1))!='b'){
                            arr[i][j]="#     ";
                            i=i-2;
                            j=j-1;
                            arr[i][j]=horse;
                        }else if(d.equals("right") && (arr[i-2][j+1].charAt(arr[i-2][j+1].length()-1))!='b'){
                            arr[i][j]="#     ";
                            i=i-2;
                            j=j+1;
                            arr[i][j]=horse;
                        }  
                    }else if(m.equals("down")   ){
                        System.out.println("on which direction left or right");
                        String d=sc.next();
                        if(d.equals("left") && (arr[i+2][j-1].charAt(arr[i+2][j-1].length()-1))!='b'){
                            arr[i][j]="#     ";
                            i=i+2;
                            j=j-1;
                            arr[i][j]=horse;
                        }else if(d.equals("right") && (arr[i+2][j+1].charAt(arr[i+2][j+1].length()-1))!='b'){
                            arr[i][j]="#     ";
                            i=i+2;
                            j=j+1;
                            arr[i][j]=horse;
                        }
                    }else if(m.equals("left")   ){
                        System.out.println("on which direction up or down");
                        String d=sc.next();
                        if(d.equals("down") && (arr[i+1][j-2].charAt(arr[i+1][j-2].length()-1))!='b'){
                            arr[i][j]="#     ";
                            i=i+1;
                            j=j-2;
                            arr[i][j]=horse;
                        }else if(d.equals("up") &&  (arr[i-1][j-2].charAt(arr[i-1][j-2].length()-1))!='b'){
                            arr[i][j]="#     ";
                            i=i-1;
                            j=j-2;
                            arr[i][j]=horse;
                        }
                    }else if(m.equals("right")   ){
                        System.out.println("on which direction up or down");
                        String d=sc.next();
                        if(d.equals("down") && (arr[i+1][j+2].charAt(arr[i+1][j+2].length()-1))!='b'){
                            arr[i][j]="#     ";
                            i=i+1;
                            j=j+2;
                            arr[i][j]=horse;
                        }else if(d.equals("up") && (arr[i-1][j+2].charAt(arr[i-1][j+2].length()-1))!='b'){
                            arr[i][j]="#     ";
                            i=i-1;
                            j=j+2;
                            arr[i][j]=horse;
                        }
                    }
                }
                }    
            }
       }
    return horse;
    }
    String queen(){
        System.out.println("which queen you want to play");
        String queen=sc.next();
        System.out.println("enter your move diagonal or straight");
        m=sc.next();
        System.out.println("how much step you want to move");
        int step=sc.nextInt();
        if(m.equals("diagonal")){
            System.out.println("where you want to move UP OR DOWN");
            String move=sc.next();
            bishop( queen, step,move);
        }else if(m.equals("straight")){
            System.out.println("where you want to move UP , DOWN ,LEFT ,RIGHT");
            String move=sc.next();
            rook(queen,step,move);
        }
        return queen;
    }
    String king(){
        System.out.println("which king you want to play");
        String king=sc.next();
        System.out.println("enter your move diagonal or straight");
        String m=sc.next();
        if(m.equals("diagonal")){
            System.out.println("where you want to move UP OR DOWN");
            String move=sc.next();
            bishop(king,1,move);
        }else if(m.equals("straight")){
            System.out.println("where you want to move UP , DOWN ,LEFT ,RIGHT");
            String move=sc.next();
            rook(king, 1, move);
        }
        return king;
    }
    String pawn(String pyade,int n){
        for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                for (int k=0;k<=15;k++) {
                      if(pyade.equals(white[k]) && arr[i][j].equals(pyade) ){
                        if(j==1){
                            if(arr[i+1][j].equals("#     ")  &&  ((arr[i+1][j+1].charAt(arr[i+1][j+1].length()-1))=='b')){
                                System.out.println("you want to cut or not");
                                String q=sc.next();
                                if(q.equals("no")){   
                                    arr[i][j]="#     ";
                                    i=i+n;
                                     arr[i][j]=pyade;
                                    
                                }else{
                                  arr[i][j]="#     ";
                                  i=i+1;
                                  j=2;
                                 arr[i][j]=pyade;
                                   
                                }
                            }else if(arr[i+1][j].equals("#     ") && arr[i+1][j+1].equals("#     ")){
                                arr[i][j]="#     ";
                                i=i+n;
                                arr[i][j]=pyade;
                            }

                        }else if(j==8){
                             if(arr[i+1][j]=="#     " &&  ((arr[i+1][j-1].charAt(arr[i+1][j-1].length()-1))=='b')){
                                System.out.println("you want to cut or not");
                                String q=sc.next();
                                if(q.equals("no")){   
                                    arr[i][j]="#     ";
                                    i=i+n;
                                   arr[i][j]=pyade;
                        
                                }else{
                                  arr[i][j]="#     ";
                                  i=i+1;
                                  j=j-1;
                                   arr[i][j]=pyade;
                            
                                }
                            }else if(arr[i+1][j]=="#     " && arr[i+1][j-1]=="#     "){
                                arr[i][j]="#     ";
                                i=i+n;
                                arr[i][j]=pyade;
                            }

                        }else{
                            if((arr[i+1][j]=="#     ") &&  ((arr[i+1][j+1].charAt(arr[i+1][j+1].length()-1))=='b'|| (arr[i+1][j-1].charAt(arr[i+1][j-1].length()-1))=='b') )   {
                                System.out.println("you want to cut or not");
                                String q=sc.next();
                                if(q.equals("no")){   
                                    arr[i][j]="#     ";
                                    i=i+n;
                                    arr[i][j]=pyade;
                                    
                                }else{
                                    System.out.println("where you want to moved left or right");
                                    String move=sc.next();
                                    if(move.equals("right"))  {
                                         arr[i][j]="#     ";
                                         i=i+1;
                                         j=j+1;
                                         arr[i][j]=pyade;
                                         
                                    }else if(move.equals("left") ){
                                        arr[i][j]="#     ";
                                        i=i+1;
                                        j=j-1;
                                        arr[i][j]=pyade;
                                    
                                    }
                                }
                            }else if(arr[i+1][j]=="#     " && (arr[i+1][j-1]=="#     " && arr[i+1][j+1]=="#     ")){
                                arr[i][j]="#     ";
                                i=i+n;
                                arr[i][j]=pyade;
                                
                            }
                        }
                        if(arr[8][j].equals(pyade)){
                            if(arr[i][j]!="ele1w" || arr[i][j]!="ele2w" || arr[i][j]!="hor1w" || arr[i][j]!="hor2w" || arr[i][j]!="sip1w" || arr[i][j]!="sip2w" || arr[i][j]!="queenw"){
                                   System.out.println("what you want to make from queen ,sipahi ,elephant,horse except these piece not present in chess");
                                   String a=sc.next();
                                   arr[8][j]=a;
                                   
                            }    
                        }
                    }else if(pyade.equals(Black[k]) && arr[i][j].equals(pyade) ){
                        if(j==1){
                            if(arr[i-1][j]=="#     " &&   (arr[i-1][j+1].charAt(arr[i-1][j+1].length()-1))=='w'){
                                System.out.println("you want to cut or not");
                                String q=sc.next();
                                if(q.equals("no")){   
                                    arr[i][j]="#     ";
                                    i=i-n;
                                    arr[i][j]=pyade;
                                
                                }else{
                                  arr[i][j]="#     ";
                                  i=i-1;
                                  j=j+1;
                                  arr[i][j]=pyade;
                            
                                }
                            }else if(arr[i-1][j]=="#     " || arr[i-1][j+1]=="#     "){
                                arr[i][j]="#     ";
                                i=i-n;
                                arr[i][j]=pyade;
                                
                            }

                        }else if(j==8){
                             if(arr[i-1][j]=="#     " &&  (arr[i-1][j-1].charAt(arr[i-1][j-1].length()-1))=='w'){
                                System.out.println("you want to cut or not");
                                String q=sc.next();
                                if(q.equals("no")){   
                                    arr[i][j]="#     ";
                                    i=i+n;
                                    arr[i][j]=pyade;
                                
                                }else{
                                  arr[i][j]="#     ";
                                  i=i+1;
                                  j=j-1;
                                  arr[i][j]=pyade;
                                  
                                }
                            }else if(arr[i+1][j]=="#     " || arr[i+1][j-1]=="#     "){
                                arr[i][j]="#     ";
                                i=i+n;
                                arr[i][j]=pyade;
                                
                            }

                        }else{
                            if((arr[i-1][j]=="#     ") &&  ((arr[i-1][j+1].charAt(arr[i-1][j+1].length()-1))=='w'||(arr[i-1][j+1].charAt(arr[i-1][j+1].length()-1))=='w') )   {
                                System.out.println("you want to cut or not");
                                String q=sc.next();
                                if(q.equals("no")){   
                                    arr[i][j]="#     ";
                                    i=i-n;
                                    arr[i][j]=pyade;
                            
                                }else{
                                    System.out.println("where you want to moved left or right");
                                    String move=sc.next();
                                    if(move.equals("right"))  {
                                         arr[i][j]="#     ";
                                         i=i-1;
                                         j=j+1;
                                         arr[i][j]=pyade;
                                         
                                    }else if(move.equals("left") ){
                                        arr[i][j]="#     ";
                                        i=i-1;
                                        j=j-1;
                                        arr[i][j]=pyade;
                                        
                                    }
                                }
                            }else if(arr[i-1][j]=="#     " || (arr[i-1][j-1]=="#     " && arr[i-1][j+1]=="#     ")){
                                arr[i][j]="#     ";
                                i=i-n;
                                arr[i][j]=pyade;
                                
                            }
                        }
                        if(arr[1][j].equals(pyade)){
                            System.out.println("what you want to make from queen ,sipahi ,elephant,horse");
                            if(arr[i][j]!="ele1b" || arr[i][j]!="ele2b" || arr[i][j]!="hor1b" || arr[i][j]!="hor2b" || arr[i][j]!="sip1b" || arr[i][j]!="sip2b" || arr[i][j]!="queenb"){
                                   System.out.println("what you want to make from queen ,sipahi ,elephant,horse");
                                   String a=sc.next();
                                   arr[1][j]=a;
                                   return a;
                            }    
                        }
                    }
                    
                }
                
            }
        }
        return pyade;
    }
    
    void print(){
        for(int i=1;i<=8;i++){
            for(int j=1;j<=8;j++){
                   System.out.print(arr[i][j] + "      ");
            }
            System.out.println(" ");
        }
    }
    void play(int i){
        String player,move;
        int step;
        System.out.println("which pieces you want to play from chess like rook,pawn etc  in terms of integer identificaton");
            int m=sc.nextInt();
            switch(m){
            case 1: System.out.println("BISHOP TURN");
                     System.out.println("which bishop you want to play");
                     player=sc.next();
                     System.out.println("how much step you want to move");
                     step=sc.nextInt();
                     System.out.println("where you want to move UP or DOWN");
                     move=sc.next();
                     bishop(player,step,move);
                     print();
                     break;
            case 2:System.out.println("ROOK TURN");
                    System.out.println("which rook you want to play");
                     player=sc.next();
                     System.out.println("how much step you want to move");
                     step=sc.nextInt();
                     System.out.println("where you want to move UP , DOWN ,LEFT ,RIGHT" );
                     move=sc.next();
                     rook(player,step,move);
                     print();
                     break;
            case 3: System.out.println("KNIGHT TURN");
                     System.out.println("which horse you want to play");
                     player=sc.next();
                     knight(player);
                     print();
                    break;
            case 4:System.out.println("KING TURN"); 
                   king();
                   print();
                   break;
            case 5: System.out.println("QUEEN TURN");
                  queen();
                  print();
                  break;
            case 6:System.out.println("PAWN TURN");
                    System.out.println("which pawn you want to play");
                    player=sc.next();
                    if(i==1){
                        System.out.println("how much step you want to move 1 or 2");
                        step=sc.nextInt();
                    }else{
                        step=1;
                    }
                    pawn(player,step);
                    print();
                    break;
            }
    }
    void game(){
       boolean b=false;
       boolean c=false;
       for(String[] row: arr){
          for (String s: row){
                if("kingw".equals(s)){
                    b=true;
                }
                if("kingb".equals(s)){
                    c=true;
                }
                
            }
        }
        if(b && c){
          System.out.println("Game Draw");
        }
        else if(b){
            System.out.println("white player win");
        } else if(c){
            System.out.println("Black player win");
        }
    }
}
public class chess{
    public static void main(String[] args) {
         Game a=new Game();
         Scanner sc=new Scanner(System.in);
         System.out.println("how many pieces moves you want to make");
         int n=sc.nextInt();
         System.out.println("player identification in terms of integer");
         a.show();
         System.out.println("CHESS SETTING");
         a.read();
         for(int i=1;i<n;i++){
              System.out.println("WHITE PLAYER MOVE");
              a.play(i);
              System.out.println("Black PLAYER MOVE");
              a.play(i);
        }
        a.game();    
    }
}
