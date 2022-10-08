package uet.oop.bomberman;

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
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public static boolean isLeftPressed = false;
    public static boolean isRightPressed = false;
    public static boolean isSpacePressed = false;


    public GameManagement() {
        setProperties();

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

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();
        keyListener();

//        checkColliding();

//        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
//        entities.add(bomberman);

//        Colliding(bomberman);
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

            System.out.println(level);
            System.out.println(HEIGHT);
            System.out.println(WIDTH);

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
                            stillObjects.add(grassObject);
                            Entity object = new Balloom(j, i, Sprite.balloom_left1.getFxImage());
                            entities.add(object);
                            object.update();
                            break;
                        }
                        case '2': {
                            Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(grassObject);
                            Entity object = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                            entities.add(object);
                            object.update();
                            break;
                        }
                        case 'p': {
                            Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(grassObject);
                            Entity object = new Bomber(j, i, Sprite.player_right.getFxImage());
                            entities.add(object);
                            object.update();
                            break;
                        }
                        default: {
                            Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(object);
                            break;
                        }
                    }
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void update () {
        entities.forEach(Entity::update);
    }

    public void render () {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public Stage getStage () {
        return stage;
    }

    /** Hàm check va chạm. */
    public void checkColliding() {
        for (int i = 0; i < entities.size(); i++) {
            final int pos = i;
            entities.forEach(g -> g.checkCollide(entities.get(pos)));
        }
    }

    /** Hàm nhận event từ Keyboard. */
    public void keyListener() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    isLeftPressed = true;
                }
                if (event.getCode() == KeyCode.RIGHT) {
                    isRightPressed = true;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    isLeftPressed = false;
                }
                if (event.getCode() == KeyCode.RIGHT) {
                    isRightPressed = false;
                }
            }
        });
    }
}

