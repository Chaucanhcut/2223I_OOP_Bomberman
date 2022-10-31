package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.menu.Menu;
import uet.oop.bomberman.sound.Sound;
import uet.oop.bomberman.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static uet.oop.bomberman.BombermanGame.*;

public class GameManagement {
//    private static int WIDTH = 31;
//    public static int HEIGHT = 13;
//
//    public static char[][] mapMatrix;
//
//    public static Entity[][] EntityMatrix;
//
//    private int level;
//
//    private Stage stage;
//    private Group root;
//    private Scene scene;
//
//    private GraphicsContext gc;
//    private Canvas canvas;
//    private List<Entity> stillObjects = new ArrayList<>();
//
//    private List<Entity> GrassOnly = new ArrayList<>();
//
//    //tao 1 mang 2 chieu luu vi tri dat bomb
//    public static char[][] bombMap;
//
//    // tao 1 arraylist luu cac doi tuong movable( bomber, enemy) va bomb, brick
//    public static List<ActiveEntity> activeObjects = new ArrayList<>();
//    public static List<Bomb> Bombs = new ArrayList<>();
//
//    private static Bomber MainCharacter;
//
//    public static boolean isLeftPressed = false;
//    public static boolean isRightPressed = false;
//    public static boolean isSpacePressed = false;
//
//    public static boolean isUpPressed = false;
//
//    public static boolean isDownPressed = false;


    protected static int WIDTH = 31;
    protected static int HEIGHT = 13;

    protected static char[][] mapMatrix;
    protected static Entity[][] EntityMatrix;
    //tao 1 mang 2 chieu luu vi tri dat bomb
    protected static char[][] bombMap = new char[HEIGHT][WIDTH];

    protected static int level = 1;
    protected static int width = 0;
    protected static int height = 0;

    private static Stage stage;

    private static Group root;
    private static Scene scene;

    protected static GraphicsContext gc;
    private static Canvas canvas;
    protected static List<Entity> stillObjects = new ArrayList<>();
    protected static List<Entity> GrassOnly = new ArrayList<>();
    // tao 1 arraylist luu cac doi tuong movable( bomber, enemy) va bomb, brick
    protected static List<ActiveEntity> activeObjects = new ArrayList<>();
    private static List<Bomb> Bombs = new ArrayList<>();

    protected static Bomber MainCharacter;

    private static boolean isLeftPressed = false;
    private static boolean isRightPressed = false;
    private static boolean isSpacePressed = false;
    private static boolean isUpPressed = false;
    private static boolean isDownPressed = false;

    //add 2307 311022------------------------------------
    //public static boolean portalCheck = false; // check for level-up
    private static int gameTime = 12000;// tgian moi man choi = 200s
    private static int delayTime = 100;
    public static int playerCount = 0;
    //-------SOUND--------
    public static Sound bombSound = new Sound("bombSound");
    public static Sound deadSound = new Sound("deadSound");
    public static Sound powerUpSound = new Sound("powerup");
    public static Sound victorySound = new Sound("victorySound");
    public static Sound defeatSound = new Sound("defeatSound");
    public static Sound backgroundSound = new Sound("start");
    //-----------------------------------------------
    public static String gameState = "newGame";
    public static Text time, Level;
    public static ImageView resumeView, pauseView, muteView, unMuteView;

    /** create scene Main menu. */
    public static Scene createSceneMenu() {
        return Menu.menu();
    }

    /** create scene Game Over. */
    public static Scene createSceneGameOver() {
        return Menu.GAMEOVER();
    }

    /** create scene Victory. */
    public static Scene createSceneVictory() {
        return Menu.VICTORY();
    }

    /** create scene Game board. */
    public static Scene createSceneGameBoard() {
//       setProperties();
//       createMap();
//       stage = new Stage();

        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        Image image = new Image(new File("res/textures/levelUp.png").toURI().toString());
        ImageView levelUp = new ImageView(image);
        levelUp.setVisible(false);

       Image pause = new Image(new File("res/textures/pause.png").toURI().toString());
       pauseView = new ImageView(pause);
       pauseView.setX(20);
       pauseView.setY(0);
       pauseView.setFitHeight(32);
       pauseView.setFitWidth(32);

       Image resume = new Image(new File("res/textures/resume.png").toURI().toString());
       resumeView = new ImageView(resume);
       resumeView.setX(20);
       resumeView.setY(0);
       resumeView.setFitHeight(32);
       resumeView.setFitWidth(32);
       resumeView.setVisible(false);

       Image mute = new Image(new File("res/textures/mute.png").toURI().toString());
       muteView = new ImageView(mute);
       muteView.setX(60);
       muteView.setY(0);
       muteView.setFitHeight(32);
       muteView.setFitWidth(32);

       Image unMute = new Image(new File("res/textures/unMute.png").toURI().toString());
       unMuteView = new ImageView(unMute);
       unMuteView.setX(60);
       unMuteView.setY(0);
       unMuteView.setFitHeight(32);
       unMuteView.setFitWidth(32);

       if (backgroundSound.isPlaying()) {
           muteView.setVisible(false);
       } else unMuteView.setVisible(false);

       time = new Text("Time: " + gameTime);
       time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
       time.setFill(Color.WHITE);
       time.setX(250);
       time.setY(22);

       Level = new Text("Level: " + level);
       Level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
       Level.setFill(Color.WHITE);
       Level.setX(150);
       Level.setY(22);

       Pane gameControl = new Pane();
       gameControl.getChildren().addAll(pauseView, resumeView, Level, time, muteView, unMuteView);
       gameControl.setMinSize(992, 448);
       gameControl.setStyle("-fx-background-color: #427235");

        // Tao root container
        Group root = new Group();
        root.getChildren().addAll(gameControl, canvas, levelUp);
        canvas.setTranslateY(32);

        // Tao scene
        sceneGameBoard = new Scene(root);
        //stage.setScene(sceneGameBoard);

        resetGame();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (gameState.equals("newGame")) {
                    resetGame();
                  // if (level == 0) level = 1;
                  // if (playerCount > 0 && level > 0) {
                  // //if (level > 0) {
                  //     try {
                  //         mapMatrix = Map.readMap("res/levels/Level" + level + ".txt");
                  //     } catch (IOException e) {
                  //         throw new RuntimeException(e);
                  //     }
                  //     Map.loadMap();
                  //     if (playerCount == 1) addPlayer();
                  // }

                    setProperties();
                    createMap();
                }

                if (gameState.equals("Running")) {
                    render();
   //                 try {
   //                     update();
   //                 } catch (FileNotFoundException e) {
   //                     throw new RuntimeException(e);
   //                 }
                }

                if (gameState.equals("Pause")) {
                    render();
                }

                if (gameState.equals("gameOver")) {
                    bombSound.play(false, 0);
                    powerUpSound.play(false, 0);
                    render();
 //                  try {
 //                      update();
 //                  } catch (FileNotFoundException e) {
 //                      throw new RuntimeException(e);
 //                  }
                    if (delayTime-- == 0) {
                        level = 0;
                        defeatSound.play(true, 0);
                        stage.setScene(sceneGameOver); // Chuyển sang scene gameOver
                    }
                    return;
                }

                if (gameState.equals("levelUp")) {
                    powerUpSound.play(true, 0);
                    if (level > 3) return;
                    if (level == 3) { // Đã chơi qua hết tất cả level
                        gameState = "winning";
                        victorySound.play(true, 0);
                        stage.setScene(sceneVictory); // Chuyển qua scene victory
                        return;
                    }
                    levelUp.setVisible(true);
                    if (delayTime-- == 0) {
                        powerUpSound.play(false, 0);
                        gameState = "newGame";
                        //portalCheck = false;
                        level++;
                        levelUp.setVisible(false);
                    }
                }
            }
        };
        timer.start();
        return sceneGameBoard;
    }

    private static void resetGame() {
        activeObjects.clear();
        stillObjects.clear();
        mapMatrix = new char[WIDTH][HEIGHT];
        gameTime = 12000;
        delayTime = 100;
        victorySound.play(false, 0);
        defeatSound.play(false, 0);
        bombSound.play(false, 0);
        deadSound.play(false, 0);
        powerUpSound.play(false, 0);
    }

    private static void addPlayer() {
        MainCharacter = new Bomber(1, 1, Sprite.player_right.getFxImage(), gc);
        activeObjects.add(MainCharacter);
        gameState = "Running";
        //sceneGameBoard.setOnKeyPressed(keyEvent -> MainCharacter.handleKeyEvent1(keyEvent));
        sceneGameBoard.setOnKeyPressed(keyEvent -> MainCharacter.update());
    }
    //end add---------------------------------------------------


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
    public static void setProperties() {
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
    private static void createMap() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                switch (mapMatrix[i][j]) {
                    // Wall
                    case '#': {
                        Entity object = new Wall(j, i, Sprite.wall.getFxImage());
                        stillObjects.add(object);
                        EntityMatrix[i][j] = object;
                        break;
                    }
                    // Brick
                    case '*': {
                        Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                        GrassOnly.add(grassObject);
                        ActiveEntity object = new Brick(j, i, Sprite.brick.getFxImage());
                        activeObjects.add(object);
                        stillObjects.add(object);
                        EntityMatrix[i][j] = object;
                        break;
                    }
                    // Balloon
                    case '1': {
                        Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                        GrassOnly.add(grassObject);
                        MovableEntities object = new Balloom(j, i, Sprite.balloom_left1.getFxImage());
                        activeObjects.add(object);
                        EntityMatrix[i][j] = object;
                        break;
                    }
                    // Oneal
                    case '2': {
                        Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                        GrassOnly.add(grassObject);
                        MovableEntities object = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                        activeObjects.add(object);
                        EntityMatrix[i][j] = object;
                        break;
                    }
                    // Player - bomber
                    case 'p': {
                        Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                        GrassOnly.add(grassObject);
                        MainCharacter = new Bomber(j, i, Sprite.player_right.getFxImage(), gc);
//                        activeObjects.add(MainCharacter);
                        break;
                    }
                    // Portal
                    case 'x': {
                        Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                        GrassOnly.add(grassObject);
                        Entity object = new Portal(j,i,Sprite.portal.getFxImage());
                        stillObjects.add(object);
                        EntityMatrix[i][j] = object;
                        break;
                    }
                    // Grass
                    default: {
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        GrassOnly.add(object);
                        break;
                    }
                }
            }
        }
    }

    public static void update() {
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


    public static void render () {
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
    public static void keyListener() {

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


    public static void checkBomberCollision() {
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

    public static char[][] getMapMatrix() {
        return mapMatrix;
    }

    public static Entity[][] getEntityMatrix() {
        return EntityMatrix;
    }

    public static char[][] getBombMap() {
        return bombMap;
    }

    public static List<ActiveEntity> getActiveObjects() {
        return activeObjects;
    }

    public static boolean isLeftPressed() {
        return isLeftPressed;
    }

    public static boolean isRightPressed() {
        return isRightPressed;
    }

    public static boolean isSpacePressed() {
        return isSpacePressed;
    }

    public static boolean isUpPressed() {
        return isUpPressed;
    }

    public static boolean isDownPressed() {
        return isDownPressed;
    }
}