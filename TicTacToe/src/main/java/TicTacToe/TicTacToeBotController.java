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
import javafx.animation.PauseTransition;
import javafx.util.Duration;
/**
 *
 * @author luoph
 */
public class TicTacToeBotController extends TicTacToeController{
    
    //Create controller instance
    public static TicTacToeBotController botControllerInstance;
    
    //Create object
    TicTacToeBotClass gameBoard = new TicTacToeBotClass(new int[3][3]);
    
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
                gameBoard.getBoardArray()[i][j] = 0;
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
            if (player.equals("X")){
                gameBoard.getBoardArray()[rowIndex][colIndex] = 1;
            }
            else if (player.equals("O")){
                gameBoard.getBoardArray()[rowIndex][colIndex] = 4;
            }
            if (player.equals("X")){
                XPlays++;
                playerCharacter = ("X");
            }
            else{
                OPlays++;
                playerCharacter = ("O");
            }
            
            if (gameBoard.winCon().equals(player)){
                writeFile(playerName);
                out.println("player");//test
                winScene(actionEvent, playerName);
            }

            boardChange(rowIndex, colIndex, playerCharacter);
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> {
                try{
                    out.println("OUTSIDE1");//TEST
                    gameBoard.botPlay(XPlays, OPlays, computer);
                    turnText.setText("Turn: "+playerName);
                        
                    if (computer.equals("X")){
                        XPlays++;
                    }
                    else{
                        OPlays++;
                    }
                    out.println("outside");//test
                    PauseTransition winPause = new PauseTransition(Duration.seconds(1));
                    if (gameBoard.winCon().equals(computer) || gameBoard.winCon().equals("D")){
                        winPause.setOnFinished(e -> {
                            try{
                                if (gameBoard.winCon().equals(computer)){
                                    writeFile("Computer");
                                    out.println("computer");//test
                                    winScene(actionEvent, "Computer");
                                }
                                else if (gameBoard.winCon().equals("D")){
                                    out.println("draw1");//test
                                    winScene(actionEvent, "Draw");
                                }
                            }
                            catch (Exception ex){
                                ex.printStackTrace();
                            }
                        });
                        winPause.play();
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            });
            pause.play();
        
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
                break;
            }
        }
        button.setVisible(false);
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
        else{
            computer = "O";
            player = "X";
            playerName = playerX;
        }
            
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
                    
                    Thread.currentThread().interrupt();
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
                finally{
                    startCounter.setVisible(false);
                    board.setVisible(true);
                    turnText.setVisible(true);
                }
            }
        });
        thread.start();
        
        //Bot play if starting as X
        try{
            if (computer.equals("X")){
                PauseTransition pause = new PauseTransition(Duration.seconds(4));
                pause.setOnFinished(event -> {
                    out.println("everything");//test
                    gameBoard.botPlay(XPlays, OPlays, computer);
                    XPlays++;
                    turnText.setText("Turn: ");
                });
                pause.play();
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
