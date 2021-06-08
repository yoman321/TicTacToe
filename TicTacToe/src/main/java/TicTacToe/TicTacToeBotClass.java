/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

import javafx.scene.layout.GridPane;
/**
 *
 * @author luoph
 */
public class TicTacToeBotClass extends TicTacToeClass{
    
    //Create constructors
    public TicTacToeBotClass(){
        
    }
    public TicTacToeBotClass(String[][] boardArray){
        super(boardArray);
    }
    
    //Create methods
    public void botPlay(int XPlays, int OPlays, String computer, GridPane board){
        
        //Create variables
        String firstPlay = "";
        int[] rowSum = new int[3];
        int[] colSum = new int[3];
        
        //Bot moves
        if (computer.equals("X")){
            if (XPlays == 0){
               int randomNbre = (int)(Math.random() * 4);
               if (randomNbre == 0){
                   getBoardArray()[0][0] = computer;
                   firstPlay = "uLeft";
               }
               else if (randomNbre == 1){
                   getBoardArray()[0][2] = computer;
                   firstPlay = "uRight";
               }
               else if (randomNbre == 2){
                   getBoardArray()[2][0] = computer;
                   firstPlay = "dLeft";
               }
               else if (randomNbre == 3){
                   getBoardArray()[2][2] = computer;
                   firstPlay = "dRight";
               }
            }
            if (XPlays == 1){
                for (int i=0; i<getBoardArray().length; i++){
                    for (int j=0; j<getBoardArray()[i].length; j++){
                        if (getBoardArray()[i][j].equals("X")){
                            rowSum[i] += 1;
                        }
                        else if (getBoardArray()[i][j].equals("O")){
                            rowSum[i] += 4;
                        }
                        if (getBoardArray()[j][i].equals("X")){
                            colSum[i] += 1;
                        }
                        else if (getBoardArray()[j][i].equals("O")){
                            colSum[i] += 4;
                        }
                    }
                }
            }
//                if (getBoardArray()[1][1].equals(" ")){
//                    int[] oppPlay = new int[2];
//                    for (int i=0; i<getBoardArray().length; i++){
//                        for (int j=0; j<getBoardArray()[i].length; j++){
//                            if (getBoardArray()[i][j].equals("O")){
//                                oppPlay[0] = i;
//                                oppPlay[1] = j;
//                            }
//                        }
//                    }
//                }
//                if (firstPlay.equals("uLeft") && )
//            }
        }
        else if (computer.equals("O")){
            
        }
    }
}
