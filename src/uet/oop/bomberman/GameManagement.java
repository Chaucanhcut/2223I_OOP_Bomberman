package uet.oop.bomberman;

import com.sun.javafx.scene.shape.RectangleHelper;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import uet.oop.bomberman.*;


public class GameManagement {
    public static int WIDTH;
    public static int HEIGHT;

    private int level;

    private Stage stage;
    private Group root;
    private Scene scene;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<MovableEntities> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    private List<Entity> GrassOnly = new ArrayList<>();

    private Bomber MainCharacter;

    public static boolean isLeftPressed = false;
    public static boolean isRightPressed = false;
    public static boolean isSpacePressed = false;

    public static boolean isUpPressed = false;

    public static boolean isDownPressed = false;

    public static boolean isEvent = false;

    public static int timeStart = 0;

    public static int timeStop = 0;


    public GameManagement() {
        setProperties();
        createMap();

        stage = new Stage();
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        /** Tạo root container. */
        Group root = new Group();
        root.getChildren().add(canvas);

        /** Tạo scene. */
        scene = new Scene(root);

        /** Thêm scene vào stage. */
        stage.setScene(scene);
        createMap();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
    }

    public Stage getStage () {
        return stage;
    }

    /** Hàm để lấy 3 số trong file, lần lượt là level - height (số cột) - width (số hàng). File level1_test.txt là tách từ file level ra nhưng chỉ lấy đoạn map. */
    public void setProperties() {
        try (Scanner scanner = new Scanner(new File("res/levels/Level1.txt"))) {
            level = scanner.nextInt();
            HEIGHT = scanner.nextInt();
            WIDTH = scanner.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** Create map từ file. */
    private void createMap() {
        try (Scanner scanner = new Scanner(new File("res/levels/Level1.txt"))) {
            scanner.nextLine();

            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int j = 0; j < WIDTH; j++) {
                    switch (line.charAt(j)) {
                        case '#': {
                            Entity object = new Wall(j, i, Sprite.wall.getFxImage());
                            stillObjects.add(object);
                            break;
                        }
                        case '*': {
                            Entity object = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(object);
                            break;
                        }
                        case '1': {
                            Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                            GrassOnly.add(grassObject);
                            MovableEntities object = new Balloom(j, i, Sprite.balloom_left1.getFxImage());
                            entities.add(object);
                            break;
                        }
                        case '2': {
                            Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                            GrassOnly.add(grassObject);
                            MovableEntities object = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                            entities.add(object);
                            break;
                        }
                        case 'p': {
                            Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                            GrassOnly.add(grassObject);
                            MainCharacter = new Bomber(j, i, Sprite.player_right.getFxImage(),gc);
                            break;
                        }
                        default: {
                            Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                            GrassOnly.add(object);
                            break;
                        }
                    }
                }
                i++;
            }
            System.out.println(stillObjects.size());
            System.out.println(entities.size());
            System.out.println(GrassOnly.size());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void update () {
        keyListener();
        entities.forEach(Entity::update);
        MainCharacter.update();
        checkBomberCollision();
    }

    public void render () {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        GrassOnly.forEach(g -> g.render(gc));
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        MainCharacter.render(gc);
    }

    /** Hàm nhận event từ Keyboard. */
    public void keyListener() {

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    isLeftPressed = true;
                    isEvent = true;
                    timeStart = (int)System.currentTimeMillis();
                }
                if (event.getCode() == KeyCode.RIGHT) {
                    isRightPressed = true;
                    isEvent = true;
                    timeStart = (int)System.currentTimeMillis();
                }
                if (event.getCode() == KeyCode.SPACE) {
                    isSpacePressed = true;
                }
                if (event.getCode() == KeyCode.UP) {
                    isUpPressed = true;
                    isEvent = true;
                    timeStart = (int)System.currentTimeMillis();
                }
                if (event.getCode() == KeyCode.DOWN) {
                    isDownPressed = true;
                    isEvent = true;
                    timeStart = (int)System.currentTimeMillis();
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    isLeftPressed = false;
                    if (isEvent) {
                        timeStop = (int)System.currentTimeMillis();
                    }
                    isEvent = false;
                }
                if (event.getCode() == KeyCode.RIGHT) {
                    if (isEvent) {
                        timeStop = (int)System.currentTimeMillis();
                    }
                    isEvent = false;
                    isRightPressed = false;
                }
                if (event.getCode() == KeyCode.UP) {
                    isUpPressed = false;
                    if (isEvent) {
                        timeStop = (int)System.currentTimeMillis();
                    }
                    isEvent = false;
                }
                if (event.getCode() == KeyCode.DOWN) {
                    isDownPressed = false;
                    if (isEvent) {
                        timeStop = (int)System.currentTimeMillis();
                    }
                    isEvent = false;
                }
                if (event.getCode() == KeyCode.SPACE) {
                    isSpacePressed = false;
                }
            }
        });
    }


    public void checkBomberCollision() {
        for (int i = 0; i < stillObjects.size(); i++) {
//            System.out.println(stillObjects.get(0).getX() + " - " + stillObjects.get(0).getY());
//            System.out.println(stillObjects.get(1).getX() + " - " + stillObjects.get(1).getY());
//            System.out.println(stillObjects.get(31).getX() + " - " + stillObjects.get(31).getY());

            MainCharacter.CheckImagineMove(stillObjects.get(i));
        }
    }

//    public void checkEnemyCollision() {
//        for (int i = 0; i < entities.size(); i++) {
//            for (int j = 0; j < stillObjects.size(); j++) {
//                entities.get(i).CheckImagineMove(stillObjects.get(j));
//            }
//        }

//        for (int j = 0; j < stillObjects.size(); j++) {
//            entities.get(0).CheckImagineMove(stillObjects.get(j));
//        }
//    }

}

