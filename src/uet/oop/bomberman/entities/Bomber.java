package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.GameManagement;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

//import static uet.oop.bomberman.GameManagement.deadSound;
//import static uet.oop.bomberman.BombermanGame.deadSound;
//import static uet.oop.bomberman.BombermanGame.powerUpSound;
//import static uet.oop.bomberman.BombermanGame.portalCheck;
import static uet.oop.bomberman.GameManagement.*;
//import static uet.oop.bomberman.GameManagement.bombMap;
//import static uet.oop.bomberman.GameManagement.mapMatrix;
//import static uet.oop.bomberman.GameManagement.Bombs;

import static uet.oop.bomberman.entities.Flame.powerFlames;


import static uet.oop.bomberman.graphics.Sprite.movingSprite;

public class Bomber extends MovableEntities {

    public int bombCount; // số lượng bomb

    private boolean bombPass = false;

    private boolean wallPass = false;

    private boolean flamePass = false;

    public int animateTime = 90;

    private int bombTime = 150;

    private int speed;

    private int animate = 0;

    private final int TIME = 30;
    private GraphicsContext gc;

    public Bomber(int x, int y, Image img, GraphicsContext gc) {
        super( x, y, img);
        this.gc = gc;
        bombCount = 1;
        bombPass = false;
        flamePass = false;
        wallPass = false;
        active = true;
        speed = 1;
        powerFlames = 1;
    }


    @Override
    public Rectangle getBoundingRect() {
        return new Rectangle(this.getX(), this.getY() + 2, 20,28);
    }

    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }

    public void Bombing() {
        int count = bombCount;
        if (bombTime <= 0) count = 1;
        int newY = (int) (this.getBoundingRect().getY() / 32);

        if (GameManagement.getMapMatrix()[newY][getXMap()] != '*' && GameManagement.getMapMatrix()[newY][getXMap()] != '#') {
            GameManagement.getBombMap()[newY][getXMap()] = '@'; // vị trí đặt bomb
             GameManagement.getBombSound().play(true, 0);
            count--;
            GameManagement.activeObjects.add(new Bomb(getXMap(), newY, Sprite.bomb.getFxImage(), powerFlames));
        } else return;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int xUnit, yUnit;
        for (int i = 0; i < 4; i++) {
            if (count == 0) break;
            xUnit = getXMap() + dx[i];
            yUnit = newY + dy[i];
            if (GameManagement.getMapMatrix()[yUnit][xUnit] != '*' && GameManagement.getMapMatrix()[yUnit][xUnit] != '#') {
                bombMap[yUnit][xUnit] = '@'; // vị trí đặt bomb
                GameManagement.getBombSound().play(true, 0);
                count--;
                activeObjects.add(new Bomb(getXMap(), (int) (this.getBoundingRect().getY() / 32), Sprite.bomb.getFxImage(), powerFlames));
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

      gc.drawImage(img, x,y);

        System.out.println(getXMap() + "-" + getYMap());
        System.out.println(getX() + "---" + getY());

    }

    @Override
    public void update() {

        int RX = getX() % 32;
        int RY = getY() % 32;

        if (GameManagement.isLeftPressed()) {
            if (canMove(getX() - 6, getY()) && canMove(getX() - 6, getY() + 32)) {
                x -= speed;
            }
            else {
                if (RY <= 4 && canMove(getX() - 6, getY() - RY)) {
                    x -= speed;
                }
                else if (RY >= 28 && canMove(getX() - 6, getY() + RY)) {
                    x -= speed;

                }
            }
        }

        else if (GameManagement.isRightPressed()) {
            if (canMove(getX() + 28, getY()) && canMove(getX() + 28, getY() + 32)) {
                x += speed;

            }
            else {
                if (RY <= 5 && canMove(getX() + 28, getY() - RY)) {
                    x += speed;

                }
                else if (RY >= 27 && canMove(getX()+ 28, getY() + RY)) {
                    x += speed;
                }
            }
        }

        else if (GameManagement.isUpPressed()) {
            if (canMove(getX(), getY() - 5) && canMove(getX() + 32, getY() - 5)) {
                y -= speed;
            }
            else {
                int R = getX() % 32;
                if (R <= 6 && canMove(getX() - R, getY() - 5)) {
                    y -= speed;

                }
                else if (R >= 26 && canMove(getX() + R, getY() - 5)) {
                    y -= speed;

                }
            }
        }

        else if (GameManagement.isDownPressed()) {
            if (canMove(getX(), getY() + 32) && (canMove(getX() + 32, getY() + 32))) {
                y += speed;
            }
            else {
                int R = getX() % 32;
                if (R <= 6 && canMove(getX() - R, getY() + 32)) {
                    y += speed;
                }
                else if (R >= 26 && canMove(getX() + R, getY() + 32)) {
                    y += speed;
                }
            }
        }

        else if (GameManagement.isSpacePressed()) {
            Bombing();
            bombCount--;
        }

        bombTime--;
        if (!active) {
            animateTime--;
            if (animateTime < 0) {
                delete = true;
                GameManagement.getDeadSound().play(false, 0);
            } else {
                if (!GameManagement.getDeadSound().isPlaying()) {
                    GameManagement.getDeadSound().play(true, 0);
                }
                setImg(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, animate, 30).getFxImage());
            }
        }
    }

    public void checkVictory(Portal p) {
        if (this.getBoundingRect().intersects(p.getBoundingRect()) && CheckEnemyKilled()) {
            portalCheck = true;
        }
    }

    @Override
    public void collide(ActiveEntity entity) {
        // Nếu bomber hoặc entity chết thì ko làm gì
        if (entity.delete || delete || entity instanceof Bomber) {
            return;
        }
        int xBomber = getYMap();
        int yBomber = getXMap();
        int xEntity = entity.getYMap();
        int yEntity = entity.getXMap();

        if (xBomber == xEntity && yBomber == yEntity) {
            if (entity instanceof Enemy) {
                active = false;
                return;
            }

            if (entity instanceof Flame) {
                if (flamePass) {
                    return;
                }
                else active = false;
            }

            if (entity instanceof Bomb) {
                Bomb bomb = (Bomb) entity;
                if (bomb.timeExplode <= 0 && !bombPass && !flamePass) {
                    active = false;
                    return;
                }
            }

            if (entity instanceof PowerItem)  {
                getPowerUpSound().play(true, 0);
                PowerItem item = (PowerItem) entity;
                item.active = true;
                item.delete = true;
                getPowerUp(item);
            }
        }
    }

    @Override
    public boolean canMove(int x, int y) {

        int xUnit = x / Sprite.SCALED_SIZE;
        int yUnit = y / Sprite.SCALED_SIZE;

        if (xUnit == 0 || xUnit == getWIDTH()-1){
            return false;
        }
        if (yUnit == 0 || yUnit == getHEIGHT()-1) {
            return false;
        }

        if (wallPass) {
            return true;
        }

        return mapMatrix[yUnit][xUnit] != '*' && mapMatrix[yUnit][xUnit] != '#';
    }

    public void getPowerUp(PowerItem power) {
        if (power instanceof FlamePower) {
            Flame.setPowerFlames(2);
        }

        // Đi xuyên tường
        if (power instanceof WallPassPower) {
            wallPass = true;
        }

        // Tăng tốc độ x2
        if (power instanceof SpeedPower) {
            speed = 2;
        }

        if (power instanceof BombPassPower) {
            bombPass = true;
        }

        if (power instanceof FlamePassPower) {
            flamePass = true;
        }
    }

    public boolean getFlamePass() {
        return flamePass;
    }
}
