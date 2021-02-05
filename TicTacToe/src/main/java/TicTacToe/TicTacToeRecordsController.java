/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.Node;


/**
 * FXML Controller class
 *
 * @author luoph
 */
public class TicTacToeRecordsController {
    
    /**
     * Initializes the controller class.
     */
    //Create FXML variable
    @FXML private GridPane topPane;

    public void initialize() throws IOException{
        topPane.getStyleClass().add("records-text-font");
        //Create variables
        File gameHistory = new File("gameHistory.txt");
        ArrayList<String> fileArray = new ArrayList<>();
        
        //Get values from file
        try(Scanner input = new Scanner(gameHistory)){
            while(input.hasNext()){
                fileArray.add(input.nextLine());
            }
        }
        //Sort file
        for (int i=0; i<fileArray.size(); i++){
            for (int j=i+1; j<fileArray.size(); j++){
                String[] array1 = fileArray.get(i).split(" ");
                String[] array2 = fileArray.get(j).split(" ");
                
                if (Integer.valueOf(array1[1]) < Integer.valueOf(array2[1])){
                    String temp = fileArray.get(i);
                    fileArray.set(i, fileArray.get(j));
                    fileArray.set(j, temp);
                }
            }
        }
        //Add numbering 
        for (int i=0; i<fileArray.size(); i++){
            String[] currentLineArray = fileArray.get(i).split(" ");
            Text top = new Text(i+1+")");
            Text name = new Text(currentLineArray[0]);
            Text wins = new Text(currentLineArray[1]);
            top.setStyle("records-text-font");
            name.setStyle("records-text-font");
            wins.setStyle("records-text-font");
            topPane.add(top, 0, i+1);
            topPane.add(name, 1, i+1);
            topPane.add(wins, 2, i+1);
        }
        
    }    
    
}
