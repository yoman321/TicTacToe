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
    
    //Create variables
    private boolean playerTurn = true;
    
    public void initialize(){
        
    }
    public void onclickBoard(ActionEvent e){
        
        //Create variables
        Text character = new Text("X");
        
        //Create button and coordinates
        Button btn = (Button)e.getSource();
        out.println(btn.getText());//test
        out.println(board.getRowIndex(btn));//test
        out.println(board.getColumnIndex(btn));//test
        int rowIndex = board.getRowIndex(btn);
        int colIndex = board.getColumnIndex(btn);
        
        //Change character 
        if (playerTurn){
            character.setText("X");
            playerTurn = false;
        }
        else{
            character.setText("O");
            playerTurn = true;
        }
        
        //Remove button and put character
        board.getChildren().remove(btn);
        board.add(character, colIndex, rowIndex);
    }
}
