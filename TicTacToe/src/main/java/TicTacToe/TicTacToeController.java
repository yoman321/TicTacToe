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
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
/**
 *
 * @author luoph
 */
//Create class
public class TicTacToeController {
    
    //Create object
    TicTacToeClass gameBoard = new TicTacToeClass(new int[3][3]);
    
    //Create fxml variables
    @FXML private GridPane board;
    @FXML private Text turnText;
    
    //Create variables
    private boolean playerTurn = true;
    private String playerX = "";
    private String playerO = "";
    private ImageView X;
    private ImageView O;
    
    public void initialize(){
        for (int i=0; i<gameBoard.getBoardArray().length; i++){
            for (int j=0; j<gameBoard.getBoardArray()[i].length; j++){
                gameBoard.getBoardArray()[i][j] = 0;
            }
        }
        turnText.setText("Turn: "+playerX);
        
    }
    public void onclickBoard(ActionEvent actionEvent) throws Exception{
      
        //Create variables
        Text textCharacter = new Text("X");
        String character = "";
       
        
        //Create button and coordinates
        Button btn = (Button)actionEvent.getSource();
        out.println(btn.getText());//test
        int rowIndex = board.getRowIndex(btn);
        int colIndex = board.getColumnIndex(btn);

        //Change character 
        if (playerTurn){
            character = "X";
            textCharacter.setText(character);
            textCharacter.setFont(new Font("Arial", 200));
            playerTurn = false;
           
        }
        else{
            character = "O";
            textCharacter.setText(character);
            textCharacter.setFont(new Font("Arial", 200));
            playerTurn = true;
        }
        
        
        //Change text
        if (playerTurn){
            turnText.setText("Turn: "+playerX);
        }
        else
            turnText.setText("Turn: "+playerO);

        //Add character to baord and check for win
        if (character.equals("X")){
            gameBoard.getBoardArray()[rowIndex][colIndex] = 1;
        }
        else if (character.equals("O")){
            gameBoard.getBoardArray()[rowIndex][colIndex] = 4;
        }
//        if (gameBoard.winCon() == 1){
//            writeFile(playerX);
//            out.println("X won");//text
//            winScene(actionEvent, playerX);
//            
//        }
//        if (gameBoard.winCon() == 2){
//            writeFile(playerO);
//            out.println("O won");//text
//            winScene(actionEvent, playerO);
//        }
//        if (gameBoard.winCon() == 3){
//            winScene(actionEvent, "Draw");
//        }
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
    //Create names import method
//    public void setPlayerNames(String playerX, String playerO){
//        this.playerX = playerX;
//        this.playerO = playerO;
//    }
    //Create win scene
    public void winScene(ActionEvent e, String player) throws Exception{    
        
        //Move to next scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TicTacToeWinFXML.fxml"));
        Parent nextPane = loader.load();
        Scene winScene = new Scene(nextPane);
       
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.setScene(winScene);
        stage.show();
        
        TicTacToeWinController controller = loader.getController();
        controller.setWinText(player);
       
    }
    //Write to file method
    public void writeFile(String winner) throws IOException{
        
        //Create file
        File gameHistory = new File("gameHistory.txt");
        
        //Create variables
        ArrayList<String> fileArray = new ArrayList<>();
        boolean foundPlayer = false;
        
        //Check if file is empty
        if (gameHistory.length() == 0){
            try(PrintWriter output = new PrintWriter(gameHistory)){
                output.println(winner+" 1");
            }
        }
        else{
            //Add everything from file to ArrayList
            try(Scanner input = new Scanner(gameHistory)){ 
                while (input.hasNext()){
                    fileArray.add(input.nextLine());
                } 
            }
            //Check if winner is already in file
            for (int i=0; i<fileArray.size(); i++){
                String[] currentLineArray = fileArray.get(i).split(" ");
                ArrayList<String> currentLineList = new ArrayList<>();
                for (int j=0; j<currentLineArray.length; j++){
                    currentLineList.add(currentLineArray[j]);
                }
                if (currentLineList.contains(winner)){
                    foundPlayer = true;
                    int wins = Integer.parseInt(currentLineList.get(1));
                    fileArray.set(i, winner+" "+(wins + 1));
                }
            }
            if (!foundPlayer){
                fileArray.add(winner+" 1");
            }
            //Write to file
            try(PrintWriter output = new PrintWriter(gameHistory)){
                for (int i=0; i<fileArray.size(); i++){
                    output.println(fileArray.get(i));
                }   
            }
        }
    }

}
