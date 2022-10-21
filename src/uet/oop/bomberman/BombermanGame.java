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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    private boolean isOver = false;

//    public static final int WIDTH = 20;
//    public static final int HEIGHT = 15;
//
//    private GraphicsContext gc;
//    private Canvas canvas;
//    private List<Entity> entities = new ArrayList<>();
//    private List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {

        /** Test game management.*/
        GameManagement game = new GameManagement();
        stage = game.getStage();

        /** Test Event Key Press. */
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


        /** ham default.*/
        stage.show();
    }
}
