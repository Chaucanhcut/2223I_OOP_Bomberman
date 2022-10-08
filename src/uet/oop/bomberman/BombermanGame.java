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
        GameManagement management = new GameManagement();
        stage = management.getStage();

//        // Tao Canvas
//        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
//        gc = canvas.getGraphicsContext2D();
//
//        // Tao root container
//        Group root = new Group();
//        root.getChildren().add(canvas);
//
//        // Tao scene
//        Scene scene = new Scene(root);
//
//        // Them scene vao stage
//        stage.setScene(scene);

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

//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long l) {
//                render();
//                update();
//            }
//        };
//        timer.start();
//
//        createMap();
//
//        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
//        entities.add(bomberman);
//    }

//    public void createMap() {
//        for (int i = 0; i < WIDTH; i++) {
//            for (int j = 0; j < HEIGHT; j++) {
//                Entity object;
//                if (j == 1 && (i == 7 || i == 8 || i == 10)) {
//                    object = new Wall(i,j, Sprite.wall.getFxImage());
//                }
//                else if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
//                    object = new Wall(i, j, Sprite.wall.getFxImage());
//                }
//                else {
//                    object = new Grass(i, j, Sprite.grass.getFxImage());
//                }
//                stillObjects.add(object);
//            }
//        }
//    }
//
//    public void update() {
//        entities.forEach(Entity::update);
//    }
//
//    public void render() {
//        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//        stillObjects.forEach(g -> g.render(gc));
//        entities.forEach(g -> g.render(gc));
//    }
    }
}
