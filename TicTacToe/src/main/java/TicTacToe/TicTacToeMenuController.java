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

/**
 *
 * @author luoph
 */
public class TicTacToeMenuController {
    
    //Create FXML variables
    
    //Create buttons methods
    public void onClickStart(ActionEvent e) throws Exception{
        Parent nextPane = FXMLLoader.load(getClass().getResource("TicTacToeFXML.fxml"));
        Scene boardScene = new Scene (nextPane);
        
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setScene(boardScene);
        stage.show();
    }
    public void onClickRecords(ActionEvent e) throws Exception{
        Parent nextPane = FXMLLoader.load(getClass().getResource("TicTacToeRecordsFXML.fxml"));
        Scene recordsScene = new Scene (nextPane);
        
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setScene(recordsScene);
        stage.show();
    
    }
    public void onClickExit(){
        System.exit(0);
    }
}
