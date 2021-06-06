/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import static java.lang.System.out;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import java.io.*;

/**
 *
 * @author luoph
 */
public class TicTacToeMenuController {
    
    //Create FXML variables
    @FXML private Pane mainPane;
    @FXML private Pane namePane;
    @FXML private TextField playerX;
    @FXML private TextField playerO;
    
    //Create variable
    boolean botGame = false;
    
    //Create initialize method
    public void initialize(){
        namePane.setVisible(false);
        playerX.setEditable(true);
        playerO.setEditable(true);
    }
    //Create buttons methods
    public void onClickHuman(ActionEvent e) throws Exception{
        
        //Set pane visiblity
        mainPane.setVisible(false);
        namePane.setVisible(true);

        botGame = false;
    }
    public void onClickBot(ActionEvent e) throws Exception{
        
        //Set pane visiblity
        mainPane.setVisible(false);
        namePane.setVisible(true);
        
        int letter = (int) Math.random() * 2;
        if (letter == 0){
            playerX.getText().equals("Computer");
            playerX.setEditable(false);
        }
        else{
            playerO.getText().equals("Computer");
            playerO.setEditable(false);
        }
        botGame = true;
    }
    public void onClickRecords(ActionEvent e) throws Exception{
        
        //Check if file exist
        File gameHistory = new File("gameHistory.txt");
        if (!gameHistory.exists()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("No games has been recorded in the game files.");
            alert.show();
        }
        else{
            Parent nextPane = FXMLLoader.load(getClass().getResource("TicTacToeRecordsFXML.fxml"));
            Scene recordsScene = new Scene (nextPane);
        
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(recordsScene);
            stage.show();
        }
    
    }
    public void onClickExit(){
        System.exit(0); 
    }
    //Move to next scene while keeping player names
    public void onClickContinue(ActionEvent e) throws Exception{
        //Check for bot game and requirements
        Alert alert = new Alert(AlertType.WARNING);
        if (botGame){
            if ((playerX.equals("Computer") && playerO.equals("")) || (playerX.equals("") && playerO.equals("Computer"))){
                alert.setContentText("Player name is required.");
            }
            else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TicTacToeBotFXML.fxml"));
                Parent nextPane = loader.load();
                Scene boardScene = new Scene(nextPane);
        
                TicTacToeController controller = loader.getController();
                controller.setPlayerNames(playerX.getText(), playerO.getText());
        
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                stage.setScene(boardScene);
                stage.show();
            }
        }
        if (!botGame){
            if (playerX.getText().equals("") || playerO.getText().equals("")){
                alert.setContentText("Both players name are required.");
                alert.show();
            }
            else if (playerX.getText().equals(playerO.getText())){
                alert.setContentText("Players name cannot be the same.");
                alert.show();
            }
            else{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TicTacToeFXML.fxml"));
                Parent nextPane = loader.load();
                Scene boardScene = new Scene(nextPane);
        
                TicTacToeController controller = loader.getController();
                controller.setPlayerNames(playerX.getText(), playerO.getText());
        
                Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
                stage.setScene(boardScene);
                stage.show();
            }
        }
    }
}
