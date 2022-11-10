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

    public static Scene sceneMenu;
    public static Scene sceneGameBoard;
    public static Scene sceneGameOver;
    public static Scene sceneVictory;
    //-------------------------
    @Override
    public void start(Stage mainStage) {

        stage = mainStage;
        stage.setTitle("Bomberman Game");

        sceneMenu = GameManagement.createSceneMenu();
        stage.setScene(sceneMenu);

        sceneGameBoard = GameManagement.createSceneGameBoard();
        sceneGameOver = GameManagement.createSceneGameOver();
        sceneVictory = GameManagement.createSceneVictory();

        //---------------------------

        Image icon = new Image(new File("res/textures/Bomberman-icon.png").toURI().toString());
        stage.getIcons().add(icon);
        stage.setResizable(false);
        /** ham default.*/
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }
}
