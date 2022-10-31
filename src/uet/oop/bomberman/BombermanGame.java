package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
//import uet.oop.bomberman.entities.stillEntity.Grass;
//import uet.oop.bomberman.entities.stillEntity.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static Stage stage;

    //add 2218 311022----------
    public static Scene sceneMenu;
    public static Scene sceneGameBoard;
    public static Scene sceneGameOver;
    public static Scene sceneVictory;
    //-------------------------

    private boolean isOver = false;

    @Override
    public void start(Stage mainStage) {

        /** Test game management.*/
        // 2 dong code tiep theo dung de load game board
        //GameManagement game = new GameManagement();
        //mainStage = game.getStage();

        stage = mainStage;
        stage.setTitle("Bomberman game");

        //add 2246 311022------------
//       sceneGameBoard = GameManagement.createSceneGameBoard();
//       sceneGameOver = GameManagement.createSceneGameOver();
//       sceneVictory = GameManagement.createSceneVictory();

        // 2 dong code tiep theo dung de load menu
         sceneMenu = GameManagement.createSceneMenu();
         stage.setScene(sceneMenu);
        //---------------------------

        Image icon = new Image(new File("res/textures/Bomberman-icon.png").toURI().toString());
        stage.getIcons().add(icon);
        stage.setResizable(false);
        /** ham default.*/
        stage.show();


//        /** Test Event Key Press. */
//        ArrayList<String> KeyInput = new ArrayList<String>();
//        scene.setOnKeyPressed(
//                new EventHandler<KeyEvent>() {
//                    @Override
//                    public void handle(KeyEvent e) {
//                        String code = e.getCode().toString();
//                        if (!KeyInput.contains(code)) {
//                            KeyInput.add(code);
//                        }
//                    }
//                }
//        );
//        scene.setOnKeyReleased(
//                new EventHandler<KeyEvent>() {
//                    @Override
//                    public void handle(KeyEvent e) {
//                        String code = e.getCode().toString();
//                        KeyInput.remove(code);
//                    }
//                }
//        );
//        gc = canvas.getGraphicsContext2D();
//        Image left = new Image("player_left.png");
//        Image right = new Image("player_right.png");
//
//        new AnimationTimer()() {
//            public void handle(long currentNanoTime) {
//                if (KeyInput.contains("RIGHT"))
//
//            }
//        }
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }
}
