/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

import javafx.scene.layout.GridPane;
import static java.lang.System.out;
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
        
        out.println("nothin");//test
        //Create variables
        String firstPlay = "";
        int[] rowSum = new int[3];
        int[] colSum = new int[3];
        
        //Bot moves
        if (computer.equals("X")){
            if (XPlays == 0){
               int randomNbre = (int)(Math.random() * 4);
               if (randomNbre == 0){
                   play(0, 0, computer);
                   botBoardChange(0, 0, computer);
               }
               else if (randomNbre == 1){
                   play(0, 2, computer);
                   botBoardChange(0, 2, computer);
                   getBoardArray()[0][2] = computer;
               }
               else if (randomNbre == 2){
                   play(2, 0, computer);
                   botBoardChange(2, 0, computer);
                   getBoardArray()[2][0] = computer;
               }
               else if (randomNbre == 3){
                   getBoardArray()[2][2] = computer;
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
                outerloop:
                for (int i=0; i<rowSum.length; i++){
                    if (rowSum[i] == 1){
                        if (colSum[0] < 1 && colSum[0] > 5){
                            play(i, 0, computer);
                            botBoardChange(i, 0, computer);
                        }
                        else if (colSum[2] < 1 && colSum[2] > 5){
                            play(i, 2, computer);
                            botBoardChange(i, 2, computer);
                        }
                    }
                    else if (rowSum[i] == 5){
                        for (int j=0; j<rowSum.length; j+=2){
                            for (int k=0; k<colSum.length; k+=2){
                                if (rowSum[j] == 0 && colSum[k] == 1){
                                    play(j, k, computer);
                                    botBoardChange(j, k, computer);
                                    break outerloop;
                                }
                            }
                        }
                    }
                        
//                    for (int j=0; j<colSum.length; i++){
//                        if ((rowSum[i] <= 3 || rowSum[i] == 5) && colSum[j] >= 4){
//                            if (rowSum[i] == 5){
//                                if (i == 0 && j == 0){
//                                    play(3, 3, computer);
//                                }
//                                else if (i == 3 && j == 3){
//                                    play(0, 0, computer);
//                                }
//                                else
//                                    play(j, i, computer);
//                            }
//                            else
//                                
//                        }
                    
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
    public void botBoardChange(int rowIndex, int colIndex, String player){
        TicTacToeBotController.botControllerInstance.boardChange(rowIndex, colIndex, player);
    }
}
