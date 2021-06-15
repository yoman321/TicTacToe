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
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.collections.ObservableList;
/**
 *
 * @author luoph
 */
public class TicTacToeBotController extends TicTacToeController{
    
    //Create controller instance
    public static TicTacToeBotController botControllerInstance;
    
    //Create object
    TicTacToeBotClass gameBoard = new TicTacToeBotClass(new String[3][3]);
    
    //Create fxml variables
    @FXML private GridPane board;
    @FXML private Text turnText;
    @FXML private Text startCounter;
    
    //Create variables
    private String player;
    private String computer;
    private String playerName;
    private int XPlays = 0;
    private int OPlays = 0;
    private ImageView X;
    private ImageView O;
    
    public TicTacToeBotController(){
        botControllerInstance = this;
    }
    public void initialize(){
        for (int i=0; i<gameBoard.getBoardArray().length; i++){
            for (int j=0; j<gameBoard.getBoardArray()[i].length; j++){
                gameBoard.getBoardArray()[i][j] = "";
            }
        }
    }
    public void onclickBoard(ActionEvent actionEvent) throws Exception{
        
        turnText.setText("Turn: Computer");
        
        //Get click location
        Button btn = (Button)actionEvent.getSource();
        int rowIndex = board.getRowIndex(btn);
        int colIndex = board.getColumnIndex(btn);
        
        //Check whether it's player's turn
        if ((player.equals("X") && XPlays == OPlays) || (player.equals("O") && XPlays > OPlays)){
            
            //Create variable
            String playerCharacter = " ";
            
            //Add character to board and check for win
            gameBoard.getBoardArray()[rowIndex][colIndex] = player;
            if (player.equals("X")){
                XPlays++;
                playerCharacter = ("X");
            }
            else 
                OPlays++;
                playerCharacter = ("O");
            
            if (gameBoard.winCon() == 1){
                writeFile(playerName);
                out.println("X won");//text
                winScene(actionEvent, playerName);
            
            }
            if (gameBoard.winCon() == 2){
                writeFile("Computer");
                out.println("O won");//text
                winScene(actionEvent, "Computer");
            }
            if (gameBoard.winCon() == 3){
                winScene(actionEvent, "Draw");
            }
            boardChange(rowIndex, colIndex, playerCharacter);
            board.getChildren().remove(btn);
        }
        try{
            gameBoard.botPlay(XPlays, OPlays, computer, board);
            turnText.setText("Turn: "+playerName);
            Thread.sleep(1000);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        
    }
    public void boardChange(int rowIndex, int colIndex, String player){
        Text playerCharacter = new Text(player);
        playerCharacter.setFont(new Font("Arial", 200));
        ObservableList<Node> childrens = board.getChildren();
        Node button = null;
        
        for (Node node: childrens){
            if (node instanceof Button && board.getRowIndex(node) == rowIndex && board.getColumnIndex(node) == colIndex){
                button = node;
                out.println("something");
                break;
            }
        }
        board.getChildren().remove(button);
        board.add(playerCharacter, colIndex, rowIndex);
    }
    //Game start events
    public void setPlayerNames(String playerX, String playerO){
        if (playerX.equals("Computer")){
            computer = "X";
            player = "O";
            playerName = playerO;
            out.println(computer);
        }
        else 
            computer = "O";
            player = "X";
            playerName = playerX;
            
    }
    public void gameStartEvents(){
        if (player.equals("X")){
            turnText.setText("Turn: "+playerName);
        }
        else 
            turnText.setText("Turn: Computer");
    
        //Decrement startcounter
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    while(Integer.parseInt(startCounter.getText()) > 0){
                        Platform.runLater(() -> startCounter.setText(String.valueOf(Integer.parseInt(startCounter.getText()) - 1)));
                        Thread.sleep(1000);
                        out.println(startCounter.getText());
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                finally{
                    startCounter.setVisible(false);
                    board.setVisible(true);
                    turnText.setVisible(true);
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
        
        //Bot play if starting as X
        if (computer.equals("X")){
            out.println("everything");//test
            gameBoard.botPlay(XPlays, OPlays, computer, board);
            XPlays++;
            turnText.setText("Turn: ");
        }
    }
}
