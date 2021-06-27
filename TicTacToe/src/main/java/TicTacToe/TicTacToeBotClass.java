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
    public void play(int rowIndex, int colIndex, String player){
        getBoardArray()[rowIndex][colIndex] = player;
        TicTacToeBotController.botControllerInstance.boardChange(rowIndex, colIndex, player);
    }
    public void botPlay(int XPlays, int OPlays, String computer){
        out.println("nothin");//test
        //Create variables
        int[] rowSum = new int[3];
        int[] colSum = new int[3];
        
        //Get rows and cols values
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
        //Bot moves
        if (computer.equals("X")){
            if (XPlays == 0){
               int randomNbre = (int)(Math.random() * 4);
               if (randomNbre == 0){
                   out.println("first");
                   play(0, 0, computer);
               }
               else if (randomNbre == 1){
                   out.println("second");
                   play(0, 2, computer);           }
               else if (randomNbre == 2){
                   out.println("thrid");
                   play(2, 0, computer);
               }
               else if (randomNbre == 3){
                   out.println("fourth");
                   play(2, 2, computer);
               }
            }
            if (XPlays == 1){
                out.println("plays1");
                if (rowSum[1] == 4 && colSum[1] == 4){
                    if (rowSum[0] == 1 && colSum[0] == 0){
                        play(2, 2, computer);
                    }
                    else if (rowSum[2] == 1 && colSum[2] == 1){
                        play(0, 0, computer);
                    }
                    else if (rowSum[2] == 1 && colSum[0] == 1){
                        play(0, 2, computer);
                    }
                    else if (rowSum[0] == 1 && colSum[2] == 1){
                        play(2, 0, computer);
                    }
                }
                else {
                    outerloop:
                    for (int i=0; i<rowSum.length; i++){
                        if (rowSum[i] == 1){
                            out.println("check rowSum[i] == 1");
                            if (colSum[0] == 4 || colSum[0] == 0){
                                out.println("here1");//test
                                play(i, 0, computer);
                            }
                            else if (colSum[2] == 4 || colSum[2] == 0){
                                out.println("here2");//test
                                play(i, 2, computer);
                            }
                        }
                        else if (rowSum[i] == 5){
                            for (int j=0; j<rowSum.length; j+=2){
                                for (int k=0; k<colSum.length; k+=2){
                                    if (rowSum[j] == 0 && colSum[k] == 1){
                                        out.println("here3");//test
                                        play(j, k, computer);
                                        break outerloop;
                                    }
                                }
                            }
                        }
                        else if (colSum[i] == 5){
                            for (int j=0; j<colSum.length; j+=2){
                                for (int k=0; k<colSum.length; k+=2){
                                    if (rowSum[j] == 1 && colSum[k] == 0){
                                        out.println("here4");
                                        play(j, k, computer);
                                        break outerloop;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (XPlays == 2){
                for (int i=0; i<rowSum.length; i++){
                    if (rowSum[i] == 2){
                        for (int j=0; j<getBoardArray()[i].length; j++){
                            if (getBoardArray()[i][j].equals("")){
                                play(i, j, computer);
                            }
                        }
                    }
                    else if (colSum[i] == 2){
                        for (int j=0; j<getBoardArray()[i].length; j++){
                            if (getBoardArray()[j][i].equals("")){
                                play(j, i, computer);
                            }
                        }
                    }
                }
            }
            
        }
        else if (computer.equals("O")){
            
        }
    }
}
