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
    
    //Create initialize method
    public void initialize(){
        namePane.setVisible(false);
    }
    //Create buttons methods
    public void onClickStart(ActionEvent e) throws Exception{
        
        //Set pane visiblity
        mainPane.setVisible(false);
        namePane.setVisible(true);
        
//        Parent nextPane = FXMLLoader.load(getClass().getResource("TicTacToeFXML.fxml"));
//        Scene boardScene = new Scene (nextPane);
//        
//        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
//        stage.setScene(boardScene);
//        stage.show();
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
    //Move to next scene while keeping player names
    public void onClickContinue(ActionEvent e) throws Exception{
        
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
