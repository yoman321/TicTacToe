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
public class TicTacToeBotController extends TicTacToeController{
    
    //Create object
    TicTacToeBotClass gameBoard = new TicTacToeBotClass(new String[3][3]);
    
    //Create fxml variables
    @FXML private GridPane board;
    @FXML private Text turnText;
    @FXML private Text startCounter;
    
    //Create variables
    private boolean playerTurn = true;
    private String playerX = "";
    private String playerO = "";
    private int XPlays = 0;
    private int OPlays = 0;
    private ImageView X;
    private ImageView O;
    
    public void initialize(){
        for (int i=0; i<gameBoard.getBoardArray().length; i++){
            for (int j=0; j<gameBoard.getBoardArray()[i].length; j++){
                gameBoard.getBoardArray()[i][j] = "";
            }
        }
        turnText.setText("Turn: "+playerX);
        
        //Decrement startcounter
        Thread thread = new Thread();
        thread = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    while(Integer.parseInt(startCounter.getText()) >= 0){
                        startCounter.setText(String.valueOf(Integer.parseInt(startCounter.getText()) - 1));
                        Thread.sleep(1000);
                    }
                    startCounter.setVisible(false);
                    board.setVisible(true);
                    turnText.setVisible(true);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
                Thread.currentThread().interrupt();
            }
        });
        thread.start();
        
        //Bot play if starting as X
        if (playerX.equals("Computer")){
            gameBoard.botPlay();
            XPlays++;
            playerTurn = false;
        }
    }
    public void onclickBoard(ActionEvent actionEvent) throws Exception{
        
        //Create variables
        Text textCharacter = new Text("X");
        String character = "";
        
        //Check whether it's player's turn
        if ((!playerX.equals("Computer") && XPlays == OPlays) || (playerX.equals("Computer") && XPlays > OPlays)){
            Button btn = (Button)actionEvent.getSource();
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
            gameBoard.getBoardArray()[rowIndex][colIndex] = character;
            if (gameBoard.winCon() == 1){
                writeFile(playerX);
                out.println("X won");//text
                winScene(actionEvent, playerX);
            
            }
            if (gameBoard.winCon() == 2){
                writeFile(playerO);
                out.println("O won");//text
                winScene(actionEvent, playerO);
            }
            if (gameBoard.winCon() == 3){
                winScene(actionEvent, "Draw");
            }
            board.add(textCharacter, colIndex, rowIndex);
            board.getChildren().remove(btn);
        }
    }
    //Import names
    public void setPlayerNames(String playerX, String playerO){
        this.playerX = playerX;
        this.playerO = playerO;
    }
}
