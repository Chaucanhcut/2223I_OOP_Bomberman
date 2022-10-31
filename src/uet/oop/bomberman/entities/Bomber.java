package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.GameManagement;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

//import static uet.oop.bomberman.GameManagement.deadSound;
import static uet.oop.bomberman.GameManagement.*;
//import static uet.oop.bomberman.GameManagement.bombMap;
//import static uet.oop.bomberman.GameManagement.mapMatrix;
//import static uet.oop.bomberman.GameManagement.Bombs;

import static uet.oop.bomberman.entities.Flame.powerFlames;


import static uet.oop.bomberman.graphics.Sprite.movingSprite;

public class Bomber extends MovableEntities {
    private boolean isDead = false;

    private int speed = 2;

    private int animate = 0;

    private final int TIME = 30;
    private GraphicsContext gc;
    public int bombCount; // so luong bomb
    public int bombTime = 150;

    public Bomber(int x, int y, Image img, GraphicsContext gc) {
        super( x, y, img);
        this.gc = gc;
        bombCount = 1;
    }

    @Override
    public Rectangle getBoundingRect() {
        return new Rectangle(this.getX(), this.getY() + 2, 20,28);
    }

    public void setbombCount(int bombCount) {
        this.bombCount = bombCount;
    }

    @Override
    public void update() {
//        if (isLeftPressed) {
//            x -= speed;
//        }
//        else if (isRightPressed) {
//            x += speed;
//        }
//        else if (isSpacePressed) {
//            Bombing();
//            setbombCount(0);
//        }
//        else if (isUpPressed) {
//            y -= speed;
//        }
//        else if (isDownPressed) {
//            y += speed;
//        }

        if (GameManagement.isLeftPressed()) {
            x -= speed;
        }
        else if (GameManagement.isRightPressed()) {
            x += speed;
        }
        else if (GameManagement.isSpacePressed()) {
            Bombing();
            setbombCount(0);
        }
        else if (GameManagement.isUpPressed()) {
            y -= speed;
        }
        else if (GameManagement.isDownPressed()) {
            y += speed;
        }


//            if (GameManagement.isLeftPressed()) {
//                x -= speed;
//            }
//            else if (GameManagement.isRightPressed()) {
//                x += speed;
//            }
//            else if (GameManagement.isSpacePressed()) {
//                Bombing();
//                setbombCount(0);
//            }
//            else if (GameManagement.isUpPressed()) {
//                y -= speed;
//            }
//            else if (GameManagement.isDownPressed()) {
//                y += speed;
//            }

//        bombTime--;
//        if (!active) {
//            animationTime--;
//            if (animationTime < 0) {
//                delete = true;
////                deadSound.play(false, 0);
//            } else {
////                if (!deadSound.isPlaying()) {
////                    deadSound.play(true, 0);
////                }
//                setImg(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animationTime, 30).getFxImage());
//            }
//        }
    }

    public void CheckImagineMove(Entity e) {
        Rectangle TestRect = new Rectangle(this.getX(), this.getY() + 2, 24,28);

//        if (isRightPressed && TestRect.intersects(e.getBoundingRect()) && this.getX() + TestRect.getWidth() - e.getX() >= 0) {
//            x = e.getX() - 24;
//        }
//        else if (isLeftPressed && TestRect.intersects(e.getBoundingRect()) && e.getX() + 32 - this.getX() >= 0) {
//            x = e.getX() + 32;
//        }
//
//        else if (isUpPressed && TestRect.intersects(e.getBoundingRect()) && e.getY() + 32 - this.getY() >= 0) {
//            y = e.getY() + 30;
//        }
//
//        else if (isDownPressed && TestRect.intersects(e.getBoundingRect()) && this.getY() + 32 - e.getY() >= 0) {
//            y = e.getY() - 30;
//        }

        if ((GameManagement.isRightPressed()) && TestRect.intersects(e.getBoundingRect()) && this.getX() + TestRect.getWidth() - e.getX() >= 0) {
            x = e.getX() - 24;
        }

        else if ((GameManagement.isLeftPressed()) && TestRect.intersects(e.getBoundingRect()) && e.getX() + 32 - this.getX() >= 0) {
            x = e.getX() + 32;
        }

        else if ((GameManagement.isUpPressed()) && TestRect.intersects(e.getBoundingRect()) && e.getY() + 32 - this.getY() >= 0) {
            y = e.getY() + 30;
        }

        else if ((GameManagement.isDownPressed()) && TestRect.intersects(e.getBoundingRect()) && this.getY() + 32 - e.getY() >= 0) {
            y = e.getY() - 30;
        }
    }

    public void checkIfDead(ActiveEntity e) {
        Rectangle TestRect = new Rectangle(this.getX(), this.getY() , 30,30);

        if ((e instanceof Flame || e instanceof Enemy) && this.getBoundingRect().intersects(e.getBoundingRect())) {
            isDead = true;
        }
    }


    public void Bombing() {
//        System.out.println("Before: " + bombCount);
        int count = bombCount;
        if (bombTime <= 0) count = 1;
        int newY = (int) (this.getBoundingRect().getY() / 32);
//        if (mapMatrix[newY][getXMap()] != '*' && mapMatrix[newY][getXMap()] != '#') {
//            bombMap[newY][getXMap()] = '@'; // vị trí đặt bomb
//            // BombermanGame.bombSound.play(true, 0);
//            count--;
//            activeObjects.add(new Bomb(getXMap(), newY, Sprite.bomb.getFxImage(), powerFlames));
//        } else return;
//        int[] dx = {1, -1, 0, 0};
//        int[] dy = {0, 0, 1, -1};
//        int xUnit, yUnit;
//        for (int i = 0; i < 4; i++) {
//            if (count == 0) break;
//            xUnit = getXMap() + dx[i];
//            yUnit = newY + dy[i];
//            if (mapMatrix[yUnit][xUnit] != '*' && mapMatrix[yUnit][xUnit] != '#') {
//                bombMap[yUnit][xUnit] = '@'; // vị trí đặt bomb
//                count--;
//                System.out.println(bombCount);
//                activeObjects.add(new Bomb(getXMap(), (int) (this.getBoundingRect().getY() / 32), Sprite.bomb.getFxImage(), powerFlames));
//            }
//        }

        if (GameManagement.getMapMatrix()[newY][getXMap()] != '*' && GameManagement.getMapMatrix()[newY][getXMap()] != '#') {
            GameManagement.getBombMap()[newY][getXMap()] = '@'; // vị trí đặt bomb
            // BombermanGame.bombSound.play(true, 0);
            count--;
            GameManagement.getActiveObjects().add(new Bomb(getXMap(), newY, Sprite.bomb.getFxImage(), powerFlames));
        } else return;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int xUnit, yUnit;
        for (int i = 0; i < 4; i++) {
            if (count == 0) break;
            xUnit = getXMap() + dx[i];
            yUnit = newY + dy[i];
            if (GameManagement.getMapMatrix()[yUnit][xUnit] != '*' && GameManagement.getMapMatrix()[yUnit][xUnit] != '#') {
                GameManagement.getBombMap()[yUnit][xUnit] = '@'; // vị trí đặt bomb
                count--;
                System.out.println(bombCount);
                GameManagement.getActiveObjects().add(new Bomb(getXMap(), (int) (this.getBoundingRect().getY() / 32), Sprite.bomb.getFxImage(), powerFlames));
            }
        }

        bombTime--;
    }

    @Override
    public void render(GraphicsContext gc) {
        animate++;
        if (GameManagement.isRightPressed()) {
            Sprite spriteNormal = Sprite.player_right;
            Sprite sprite1 = Sprite.player_right_1;
            Sprite sprite2 = Sprite.player_right_2;
            this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
        }
        if (GameManagement.isLeftPressed()) {
            Sprite spriteNormal = Sprite.player_left;
            Sprite sprite1 = Sprite.player_left_1;
            Sprite sprite2 = Sprite.player_left_2;
            this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
        }
       if (GameManagement.isUpPressed()) {
           Sprite spriteNormal = Sprite.player_up;
           Sprite sprite1 = Sprite.player_up_1;
           Sprite sprite2 = Sprite.player_up_2;
           this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
        }
      if (GameManagement.isDownPressed()) {
          Sprite spriteNormal = Sprite.player_down;
          Sprite sprite1 = Sprite.player_down_1;
          Sprite sprite2 = Sprite.player_down_2;
          this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
      }
      if (isDead) {
          Sprite spriteNormal = Sprite.player_dead1;
          Sprite sprite1 = Sprite.player_dead2;
          Sprite sprite2 = Sprite.player_dead3;
          this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
      }
      gc.drawImage(img, x,y);
    }

    public boolean canMove(int x, int y) {
        int xUnit = (int) (getBoundingRect().getX() / Sprite.SCALED_SIZE);
        int yUnit = (int) (getBoundingRect().getY() / Sprite.SCALED_SIZE);
        return GameManagement.getMapMatrix()[yUnit][xUnit] != '*' && GameManagement.getMapMatrix()[yUnit][xUnit] != '#';
    }
}
