package uet.oop.bomberman;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.GameManagement;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static uet.oop.bomberman.GameManagement.*;

/** Load map từ file cấu hình Level. */
public final class Map {
    public static char[][] readMap(String path) throws IOException {
        char[][] map = new char[GameManagement.getHEIGHT()][GameManagement.getWIDTH()];
        File reader = new File(path);
        Scanner in = new Scanner(reader);

        try {
            GameManagement.setLevel(in.nextInt());
            GameManagement.setHeight(in.nextInt());
            GameManagement.setWidth(in.nextInt());

            in.nextLine();
            for (int i = 0; i < GameManagement.getHeight(); i++) {
                String line = in.nextLine();
                for (int j = 0; j < GameManagement.getWidth(); j++) {
                    map[i][j] = line.charAt(j);
//                    bombMap[i][j] = ' ';
                    GameManagement.setBombMap(GameManagement.getBombMap(), i, j, '-');
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return map;
    }

    public static void loadMap() {
        for (int i = 0; i < GameManagement.getHEIGHT(); i++) {
            for (int j = 0; j < GameManagement.getWIDTH(); j++) {
                switch (GameManagement.getMapMatrix()[i][j]) {
                    // Wall
                    case '#':
                        // Add wall
                        Entity wallObject = new Wall(j, i, Sprite.wall.getFxImage());
                        stillObjects.add(wallObject);
                        EntityToCheck.add(wallObject);
                        break;
                    // Brick
                    case '*':
                        Entity grassObject = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grassObject);
                        ActiveEntity brickObject = new Brick(j, i, Sprite.brick.getFxImage());
                        activeObjects.add(brickObject);
                        EntityToCheck.add(brickObject);
                        break;
                    // Portal
//                    case 'x': {
//                        GameManagement.mapMatrix[i][j] = '*';
//                        // Layer 1: Add grass
//                        Entity grassObject2 = new Grass(j, i, Sprite.grass.getFxImage());
//                        stillObjects.add(grassObject2);
//                        // Layer 2: Add portal
//                        //activeObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
//
////                        Entity portalObject = new Portal(j,i,Sprite.portal.getFxImage());
////                        stillObjects.add(portalObject);
//
//                        portal = new Portal(j,i,Sprite.portal.getFxImage());
//
////                        EntityMatrix[i][j] = portalObject;
//                        // Layer 3: Add brick
//                        ActiveEntity brickObject2 = new Brick(j, i, Sprite.brick.getFxImage());
//                        activeObjects.add(brickObject2);
////                        EntityMatrix[i][j] = brickObject2;
//                        break;
//                    }

                    case 'x': {
                        mapMatrix[i][j] = '*';
                        Entity grassObject2 = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grassObject2);
                        Entity object = new Portal(j,i,Sprite.portal.getFxImage());
                        stillObjects.add(object);
                        ActiveEntity brickObject2 = new Brick(j, i, Sprite.brick.getFxImage());
                        activeObjects.add(brickObject2);
                        break;
                    }

                    // Player - Bomber
                    case 'p': {
                        Entity grassObject3 = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grassObject3);
                        MainCharacter = new Bomber(j, i, Sprite.player_right.getFxImage(), gc);
                        // activeObjects.add(MainCharacter);
                        break;
                    }
                    // Balloon
                    case '1':
                        // Layer 1: Add grass
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        // Layer 2: Add balloon
                        MovableEntities baloon = new Balloom(j, i, Sprite.balloom_left1.getFxImage());
                        activeObjects.add(baloon);
//                        EntityMatrix[i][j] = baloon;
                        break;
                    // Oneal
                    case '2':
                        // Layer 1: Add grass
                        stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                        // Layer 2: Add oneal
                        MovableEntities oneal = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                        activeObjects.add(oneal);
//                        EntityMatrix[i][j] = oneal;
                        break;

                    case 'f': {
                        mapMatrix[i][j] = '*';
                        Entity grassObject2 = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grassObject2);
                        ActiveEntity object = new FlamePower(j,i,Sprite.powerup_flames.getFxImage());
                        activeObjects.add(object);
                        ActiveEntity brickObject2 = new Brick(j, i, Sprite.brick.getFxImage());
                        activeObjects.add(brickObject2);
                        break;
                    }

                    case 'B': {
                        mapMatrix[i][j] = '*';
                        Entity grassObject2 = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grassObject2);
                        ActiveEntity object = new BombPassPower(j,i,Sprite.powerup_bombpass.getFxImage());
                        activeObjects.add(object);
                        ActiveEntity brickObject2 = new Brick(j, i, Sprite.brick.getFxImage());
                        activeObjects.add(brickObject2);
                        break;
                    }

                    case 'w': {
                        mapMatrix[i][j] = '*';
                        Entity grassObject2 = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grassObject2);
                        ActiveEntity object = new WallPassPower(j,i,Sprite.powerup_wallpass.getFxImage());
                        activeObjects.add(object);
                        ActiveEntity brickObject2 = new Brick(j, i, Sprite.brick.getFxImage());
                        activeObjects.add(brickObject2);
                        break;
                    }

                    case 's': {
                        mapMatrix[i][j] = '*';
                        Entity grassObject2 = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grassObject2);
                        ActiveEntity object = new SpeedPower(j,i,Sprite.powerup_speed.getFxImage());
                        activeObjects.add(object);
                        ActiveEntity brickObject2 = new Brick(j, i, Sprite.brick.getFxImage());
                        activeObjects.add(brickObject2);
                        break;
                    }


                    // Grass
                    default:
                        // Add grass
                        Entity object = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(object);
                        break;
                }
            }
        }
    }
}