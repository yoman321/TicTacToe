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
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.TextField;
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
    private String playerX = "";
    private String playerO = "";
    
    public void initialize(){
        for (int i=0; i<boardArray.length; i++){
            for (int j=0; j<boardArray[i].length; j++){
                boardArray[i][j] = "";
            }
        }
    }
    public void onclickBoard(ActionEvent actionEvent) throws Exception{
      
        //Create variables
        Text textCharacter = new Text("X");
        String character = "";
       
        
        //Create button and coordinates
        Button btn = (Button)actionEvent.getSource();
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
            turnText.setText("Turn: "+playerX);
        }
        else
            turnText.setText("Turn: "+playerO);

        //Add character to baord and check for win
        boardArray[rowIndex][colIndex] = character;
        if (winCon(boardArray) == 1){
            out.println("X won");//text
            winScene(actionEvent, "X");
            
        }
        if (winCon(boardArray) == 2){
            out.println("O won");//text
            winScene(actionEvent, "O");
        }
        //Remove button and put character
        board.add(textCharacter, colIndex, rowIndex);
        board.getChildren().remove(btn);
    }
    //Create main menu button
    public void onclickBack(ActionEvent e) throws Exception{
        Parent nextPane = FXMLLoader.load(getClass().getResource("TicTacToeFXML.fxml"));
        Scene winScene = new Scene(nextPane);
        
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setScene(winScene);
        stage.show();
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
    //Create names import method
    public void setPlayerNames(String playerX, String playerO){
        this.playerX = playerX;
        this.playerO = playerO;
    }
    //Create win scene
    public void winScene(ActionEvent e, String character) throws Exception{    
        
        //Move to next scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TicTacToeWinFXML.fxml"));
        Parent nextPane = loader.load();
        Scene winScene = new Scene(nextPane);
        
        TicTacToeWinController controller = loader.getController();
        controller.setWinText(character);
        
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setScene(winScene);
        stage.show();
       
    }

}
