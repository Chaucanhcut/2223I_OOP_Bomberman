package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameManagement {
    private static int WIDTH = 31;
    public static int HEIGHT = 13;

    public static char[][] mapMatrix;

    public static Entity[][] EntityMatrix;

    private int level;

    private Stage stage;
    private Group root;
    private Scene scene;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> stillObjects = new ArrayList<>();

    private List<Entity> GrassOnly = new ArrayList<>();

    //tao 1 mang 2 chieu luu vi tri dat bomb
    public static char[][] bombMap;

    // tao 1 arraylist luu cac doi tuong movable( bomber, enemy) va bomb, brick
    public static List<ActiveEntity> activeObjects = new ArrayList<>();
    public static List<Bomb> Bombs = new ArrayList<>();

    private static Bomber MainCharacter;

    public static boolean isLeftPressed = false;
    public static boolean isRightPressed = false;
    public static boolean isSpacePressed = false;

    public static boolean isUpPressed = false;

    public static boolean isDownPressed = false;


//    private int WIDTH = 31;
//    private int HEIGHT = 13;
//
//    private static char[][] mapMatrix;
//
//    private static Entity[][] EntityMatrix;
//
//    //tao 1 mang 2 chieu luu vi tri dat bomb
//    private static char[][] bombMap;
//
//    private int level;
//
//    private Stage stage;
//
//    private Group root;
//    private Scene scene;
//
//    private GraphicsContext gc;
//    private Canvas canvas;
//    private List<Entity> stillObjects = new ArrayList<>();
//
//    private List<Entity> GrassOnly = new ArrayList<>();
//
//    // tao 1 arraylist luu cac doi tuong movable( bomber, enemy) va bomb, brick
//    private static List<ActiveEntity> activeObjects = new ArrayList<>();
//    private static List<Bomb> Bombs = new ArrayList<>();
//
//    private static Bomber MainCharacter;
//
//    private static boolean isLeftPressed = false;
//    private static boolean isRightPressed = false;
//    private static boolean isSpacePressed = false;
//
//    private static boolean isUpPressed = false;
//
//    private static boolean isDownPressed = false;


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
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("res/levels/Level1.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        level = scanner.nextInt();
        HEIGHT = scanner.nextInt();
        WIDTH = scanner.nextInt();
        scanner.nextLine();

        mapMatrix = new char[HEIGHT][WIDTH];
        bombMap = new char[HEIGHT][WIDTH];
        int i = 0;
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                mapMatrix[i][j] = line.charAt(j);
            }
            i++;
        }

        for (int j = 0; j < HEIGHT; j++) {
            for (int k = 0; k < WIDTH; k++) {
                bombMap[j][k] = ' ';
            }
        }

        EntityMatrix = new Entity[HEIGHT][WIDTH];

    }

    /** Create map từ file. */
    private void createMap() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                switch (mapMatrix[i][j]) {
                    case '#': {
                        Entity object = new Wall(j, i, Sprite.wall.getFxImage());
                        stillObjects.add(object);
                        EntityMatrix[i][j] = object;
                        break;
                    }
                    case '*': {
                        Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                        GrassOnly.add(grassObject);
                        ActiveEntity object = new Brick(j, i, Sprite.brick.getFxImage());
                        activeObjects.add(object);
                        stillObjects.add(object);
                        EntityMatrix[i][j] = object;
                        break;
                    }
                    case '1': {
                        Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                        GrassOnly.add(grassObject);
                        MovableEntities object = new Balloom(j, i, Sprite.balloom_left1.getFxImage());
                        activeObjects.add(object);
                        EntityMatrix[i][j] = object;
                        break;
                    }
                    case '2': {
                        Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                        GrassOnly.add(grassObject);
                        MovableEntities object = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                        activeObjects.add(object);
                        EntityMatrix[i][j] = object;
                        break;
                    }
                    case 'p': {
                        Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                        GrassOnly.add(grassObject);
                        MainCharacter = new Bomber(j, i, Sprite.player_right.getFxImage(), gc);
//                        activeObjects.add(MainCharacter);
                        break;
                    }
                    case 'x': {
                        Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                        GrassOnly.add(grassObject);
                        Entity object = new Portal(j,i,Sprite.portal.getFxImage());
                        stillObjects.add(object);
                        EntityMatrix[i][j] = object;
                        break;
                    }
                    default: {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        GrassOnly.add(object);
                        break;
                    }
                }
            }
        }

//        for (int i =0 ; i < HEIGHT; i++) {
//            for (int j = 0; j <  WIDTH; j++) {
//                System.out.print(mapMatrix[i][j]);
//            }
//            System.out.println();
//        }
    }

    public void update () {
        keyListener();
        MainCharacter.update();
        Bombs.forEach(Bomb::update);
        checkBomberCollision();

        for (int i = 0; i < activeObjects.size(); i++) {
            activeObjects.get(i).update();
            for (ActiveEntity activeObject : activeObjects) {
                activeObjects.get(i).collide(activeObject);
            }
        }

        /**
         * Game over.
         */
//        if (playerCount == 1 && !bomber1.active) {
//            gameState = "gameOver";
//            return;
//        }

        for (int i = 0; i < activeObjects.size(); i++) {
            if (activeObjects.get(i).delete) {
                activeObjects.remove(activeObjects.get(i));
            }
        }

        for (int i = 0; i < stillObjects.size(); i++) {
            if (stillObjects.get(i) instanceof Brick && ((Brick) stillObjects.get(i)).delete) {
                stillObjects.remove(stillObjects.get(i));
            }
        }
    }


    public void render () {
//        System.out.println("BombMap:");
//        for (int i = 0; i < HEIGHT; i++) {
//            for (int j = 0; j < WIDTH; j++) {
//                System.out.print(bombMap[i][j]);
//            }
//            System.out.println();
//        }

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        GrassOnly.forEach(g -> g.render(gc));
        stillObjects.forEach(g -> g.render(gc));
//        entities.forEach(g -> g.render(gc));
        MainCharacter.render(gc);
        Bombs.forEach(g -> g.render(gc));

        for (ActiveEntity entity : activeObjects) {
            if (!entity.delete) {
//                powerUpSound.play(false, 0);
                entity.render(gc);
            }
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
                if (event.getCode() == KeyCode.UP) {
                    isUpPressed = true;
                }
                if (event.getCode() == KeyCode.DOWN) {
                    isDownPressed = true;
                }
                if (event.getCode() == KeyCode.ESCAPE) {
                    stage.close();
                }
                if (event.getCode() == KeyCode.SPACE) {
                    isSpacePressed = true;
                    MainCharacter.Bombing();
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
                if (event.getCode() == KeyCode.UP) {
                    isUpPressed = false;
                }
                if (event.getCode() == KeyCode.DOWN) {
                    isDownPressed = false;
                }
                if (event.getCode() == KeyCode.SPACE) {
                    isSpacePressed  = false;
                }
            }
        });
    }


    public void checkBomberCollision() {
        for (int i = 0; i < stillObjects.size(); i++) {
            MainCharacter.CheckImagineMove(stillObjects.get(i));
        }
        for (int i = 0; i < activeObjects.size(); i++) {
            MainCharacter.checkIfDead(activeObjects.get(i));
        }
    }

    public static Bomber getBomber() {
        return MainCharacter;
    }

//    public static char[][] getMapMatrix() {
//        return mapMatrix;
//    }
//
//    public Entity[][] getEntityMatrix() {
//        return EntityMatrix;
//    }
//
//    public char[][] getBombMap() {
//        return bombMap;
//    }
//
//    public List<ActiveEntity> getActiveObjects() {
//        return activeObjects;
//    }
//
//    public static boolean isLeftPressed() {
//        return isLeftPressed;
//    }
//
//    public static boolean isRightPressed() {
//        return isRightPressed;
//    }
//
//    public static boolean isSpacePressed() {
//        return isSpacePressed;
//    }
//
//    public static boolean isUpPressed() {
//        return isUpPressed;
//    }
//
//    public static boolean isDownPressed() {
//        return isDownPressed;
//    }
}