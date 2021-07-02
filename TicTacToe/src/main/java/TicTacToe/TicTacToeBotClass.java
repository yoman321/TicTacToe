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
    public TicTacToeBotClass(int[][] boardArray){
        super(boardArray);
    }
    //Create methods
    public void play(int rowIndex, int colIndex, String player){
        if (player.equals("X")){
            getBoardArray()[rowIndex][colIndex] = 1;
        }
        else if (player.equals("O")){
            getBoardArray()[rowIndex][colIndex] = 4;
        }
        TicTacToeBotController.botControllerInstance.boardChange(rowIndex, colIndex, player);
    }
    public void botPlay(int XPlays, int OPlays, String computer){
        out.println("nothin");//test
        //Create variables
        int[] rowSum = new int[3];
        int[] colSum = new int[3];
        int mainDiago = 0;
        int reverseDiago = 0;
        
        //Get rows and cols values
        for (int i=0; i<getBoardArray().length; i++){
            for (int j=0; j<getBoardArray()[i].length; j++){
                rowSum[i] += getBoardArray()[i][j];
                colSum[i] += getBoardArray()[j][i];
            }
           
        }
        //Get diagonals values
        for (int i=0, j=0; i<getBoardArray().length; i++, j++){
            mainDiago += getBoardArray()[i][j];
        }
        for (int i=0, j=getBoardArray().length-1; i<getBoardArray().length; i++, j--){
            reverseDiago += getBoardArray()[i][j];
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
                out.println(getBoardArray()[1][1]+" 1 1");//test
                if (getBoardArray()[1][1] == 4){
                    out.println("counterplay 1");
                    if (rowSum[0] == 1 && colSum[0] == 1){
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
            //Set up for winning move
            if (XPlays == 2 && getBoardArray()[1][1] != 4){
                Boolean winMove = false;
                for (int i=0; i<rowSum.length; i++){
                    if (rowSum[i] == 2){
                        playEmptySpace(i, computer, "row");
                        winMove = true;
                        break;
                    }
                    else if (colSum[i] == 2){
                        playEmptySpace(i, computer, "col");
                        winMove = true;
                        break;
                    }
                    else if (rowSum[i] == 8){
                        playEmptySpace(i, computer, "row");
                        winMove = true;
                        break;
                    }
                    else if (colSum[i] == 8){
                        playEmptySpace(i, computer, "col");
                        winMove = true;
                        break;
                    }
                }
                if (winMove == false){
                    for (int i=0; i<rowSum.length; i++){
                        if (rowSum[i] == 1){
                            if (getBoardArray()[i][0] == 0){
                                play(i, 0, computer);
                            }
                            else if (getBoardArray()[i][2] == 0){
                                play(i, 2, computer);
                            }
                        }
                        else if (colSum[i] == 1){
                            if (getBoardArray()[0][i] == 0){
                                play(0, i, computer);
                            }
                            else if (getBoardArray()[2][i] == 0){
                                play(2, i, computer);
                            }
                        }
                    }
                }
            }
            if (XPlays >= 3 || (XPlays == 2 && getBoardArray()[1][1] == 4)){
                for (int i=0; i<rowSum.length; i++){
                    if (rowSum[i] == 2){
                        playEmptySpace(i, computer, "row");
                        break;
                    }
                    else if (colSum[i] == 2){
                        playEmptySpace(i, computer, "col");
                        break;
                    }
                    else if (rowSum[i] == 8){
                        playEmptySpace(i, computer, "row");
                        break;
                    }
                    else if (colSum[i] == 8){
                        playEmptySpace(i, computer, "col");
                        break;
                    }
                    else if (mainDiago == 2){
                        playEmptySpace(i, computer, "mainDiago");
                        break;
                    }
                    else if (mainDiago == 8){
                        playEmptySpace(i, computer, "mainDiago");
                        break;
                    }
                    else if (reverseDiago == 2){
                        playEmptySpace(i, computer, "reverseDiago");
                        break;
                    }
                    else if (reverseDiago == 8){
                        playEmptySpace(i, computer, "reverseDiago");
                        break;
                    }
                }
            }
            
        }
        else if (computer.equals("O")){
            
        }
    }
    public void playEmptySpace(int i, String character, String line){
        if (line.equals("row") || line.equals("col")){
            for (int j=0; j<getBoardArray()[i].length; j++){
                if (getBoardArray()[i][j] == 0 && line.equals("row")){
                    play(i, j, character);
                    out.println("play empty space");//test
                    break;
                }
                else if (getBoardArray()[j][i] == 0 && line.equals("col")){
                    play(j, i, character);
                    break;
                }
            }
        }
        else if (line.equals("mainDiago")){
            for (int j=0, k=0; j<getBoardArray().length; j++, k++){
                if (getBoardArray()[j][k] == 0){
                    play(j, k, character);
                    break;
                }
            }
        }
        else if (line.equals("reverseDiago")){
            for (int j=0, k=getBoardArray().length-1; i<getBoardArray().length; j++, k--){
                if (getBoardArray()[j][k] == 0){
                    play(j, k, character);
                    break;
                }
            }
        }
    }
}
