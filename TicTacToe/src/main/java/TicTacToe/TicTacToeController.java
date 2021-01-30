/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane; 
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import static java.lang.System.out;
/**
 *
 * @author luoph
 */
public class TicTacToeController {
   
    //Create fxml variables
    @FXML private GridPane board;
    @FXML private Text turnText;
    
    //Create variables
    private boolean playerTurn = true;
    private String[][] boardArray = new String[3][3];
    
    public void initialize(){
        for (int i=0; i<boardArray.length; i++){
            for (int j=0; j<boardArray[i].length; j++){
                boardArray[i][j] = "";
            }
        }
    }
    public void onclickBoard(ActionEvent e){
        
        //Create variables
        Text textCharacter = new Text("X");
        String character = "";
        
        //Create button and coordinates
        Button btn = (Button)e.getSource();
        out.println(btn.getText());//test
        out.println(board.getRowIndex(btn));//test
        out.println(board.getColumnIndex(btn));//test
        int rowIndex = board.getRowIndex(btn);
        int colIndex = board.getColumnIndex(btn);
      
        
        //Change character 
        if (playerTurn){
            character = "X";
            textCharacter.setText(character);
            playerTurn = false;
        }
        else{
            character = "O";
            textCharacter.setText(character);
            playerTurn = true;
        }
        
        
        //Change text
        if (playerTurn){
            turnText.setText("Turn: X");
        }
        else
            turnText.setText("Turn: O");
        
        //Remove button and put character
        board.getChildren().remove(btn);
        board.add(textCharacter, colIndex, rowIndex);
        
        //Add character to baord and check for win
        boardArray[rowIndex][colIndex] = character;
        for (int i=0; i<boardArray.length; i++){
            for (int j=0; j<boardArray[i].length; j++){
                out.print(boardArray[i][j]+" ");
            }
            out.println();
        }
        if (winCon(boardArray) == 1){
            out.println("X won");//text
        }
        if (winCon(boardArray) == 2){
            out.println("O won");//text
        }
    }
    //Create win method
    public int winCon(String[][] boardArray){
        
        //Create variables
        int Xcount = 0;
        int Ocount = 0;
        
        //Check for row wins
        for (int i=0; i<boardArray.length; i++){
            for (int j=0; j<boardArray[i].length; j++){
                if (boardArray[i][j].equals("X")){
                    Xcount++;
                }
                if (boardArray[i][j].equals("O")){
                    Ocount++;
                }
                if (Xcount == 3){
                    return 1;
                }
                if (Ocount == 3){
                    return 2;
                }
            }
            //Reset value for next row
            Xcount = 0;
            Ocount = 0;
        }
        
        //Check for column wins
        for (int i=0; i<boardArray.length; i++){
            for (int j=0; j<boardArray.length; j++){
                if (boardArray[j][i].equals("X")){
                    Xcount++;
                }
               if (boardArray[j][i].equals("O")){
                   Ocount++;
               }
               if (Xcount == 3){
                   return 1;
               }
               if (Ocount == 3){
                   return 2;
               }
            }
            //Reset values
            Xcount = 0;
            Ocount = 0;
        }
        
        //Check for main diagonals wins
        for (int i=0, j=0; i<boardArray.length; i++, j++){
            if (boardArray[i][j].equals("X")){
                Xcount++;
            }
            if (boardArray[i][j].equals("O")){
                 Ocount++;
            }
            if (Xcount == 3){
                   return 1;
            }
               if (Ocount == 3){
                   return 2;
            }
        }
        Xcount = 0;
        Ocount = 0;
        
        //Check for inverse diagonals wins
        for (int i=boardArray.length-1, j=0; i>-1; i--, j++){
            if (boardArray[i][j].equals("X")){
                Xcount++;
            }
            if (boardArray[i][j].equals("O")){
                 Ocount++;
            }
            if (Xcount == 3){
                   return 1;
            }
               if (Ocount == 3){
                   return 2;
            }
        }
        return 0;
    }
}
