/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

/**
 *
 * @author luoph
 */
 public class TicTacToeClass{
    
    //Create datafields
    private int[][] boardArray = new int[3][3];
    
    //Create construcotrs
    public TicTacToeClass(){
        
    }
    public TicTacToeClass(int[][] boardArray){
        this.boardArray = boardArray;
    }
    //Create getters and setters
    public int[][] getBoardArray(){
        return boardArray;
    }
    public void setBoardArray(int[][] boardArray){
        this.boardArray = boardArray;
    }
    //Create methods
    public String winCon(){
        //Create variables
        int Xcount = 0;
        int Ocount = 0;
        
        //Check for row wins
        for (int i=0; i<boardArray.length; i++){
            for (int j=0; j<boardArray[i].length; j++){
                if (boardArray[i][j] == 1){
                    Xcount++;
                }
                if (boardArray[i][j] == 4){
                    Ocount++;
                }
                if (Xcount == 3){
                    return "X";
                }
                if (Ocount == 3){
                    return "O";
                }
            }
            //Reset value for next row
            Xcount = 0;
            Ocount = 0;
        }
        
        //Check for column wins
        for (int i=0; i<boardArray.length; i++){
            for (int j=0; j<boardArray.length; j++){
                if (boardArray[j][i] == 1){
                    Xcount++;
                }
               if (boardArray[j][i] == 4){
                   Ocount++;
               }
               if (Xcount == 3){
                   return "X";
               }
               if (Ocount == 3){
                   return "O";
               }
            }
            //Reset values
            Xcount = 0;
            Ocount = 0;
        }
        
        //Check for main diagonals wins
        for (int i=0, j=0; i<boardArray.length; i++, j++){
            if (boardArray[i][j] == 1){
                Xcount++;
            }
            if (boardArray[i][j] == 4){
                 Ocount++;
            }
            if (Xcount == 3){
                   return "X";
            }
               if (Ocount == 3){
                   return "O";
            }
        }
        Xcount = 0;
        Ocount = 0;
        
        //Check for inverse diagonals wins
        for (int i=boardArray.length-1, j=0; i>-1; i--, j++){
            if (boardArray[i][j] == 1){
                Xcount++;
            }
            if (boardArray[i][j] == 4){
                 Ocount++;
            }
            if (Xcount == 3){
                   return "X";
            }
               if (Ocount == 3){
                   return "O";
            }
        }
        
        //Check for draw
        int drawCount = 0;
        for (int i=0; i<boardArray.length; i++){
            for (int j=0; j<boardArray[i].length; j++){
                if (boardArray[i][j] != 0){
                    drawCount++;
                }
            }
        }
        if(drawCount == 9){
            return "D";
        }
        return "No Win";
    }
 }
