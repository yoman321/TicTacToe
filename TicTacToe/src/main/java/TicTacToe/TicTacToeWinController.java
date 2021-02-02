/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.text.Text;
/**
 *
 * @author luoph
 */
public class TicTacToeWinController {
    
    @FXML private Text winnerText;
    public void initialize(){
        
//        Text u = (Text) stage.getUserData();

    }
    public void setWinText(String character){
        winnerText.setText("Winner: "+character);
    }
     //Create main menu button
    public void onclickBack(ActionEvent e) throws Exception{
        Parent nextPane = FXMLLoader.load(getClass().getResource("TicTacToeMenuFXML.fxml"));
        Scene winScene = new Scene(nextPane);
        
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setScene(winScene);
        stage.show();
    }
}
