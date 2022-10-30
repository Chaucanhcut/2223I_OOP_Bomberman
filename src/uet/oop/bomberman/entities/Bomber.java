package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.GameManagement;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

//import static uet.oop.bomberman.GameManagement.deadSound;
import static uet.oop.bomberman.GameManagement.*;
import static uet.oop.bomberman.GameManagement.bombMap;
import static uet.oop.bomberman.GameManagement.mapMatrix;
import static uet.oop.bomberman.GameManagement.Bombs;

import static uet.oop.bomberman.entities.Flame.powerFlames;


import static uet.oop.bomberman.graphics.Sprite.movingSprite;

public class Bomber extends MovableEntities {


//    public int animationTime = 500;

    private boolean isDead = false;

    private int speed = 3;

    private int animate = 0;

    private final int TIME = 30;
    private GraphicsContext gc;
    public int bombCount; // so luong bomb co the dat
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
//        if (canMove(this.getX(), this.getY())) {
            if (isLeftPressed) {
                x -= speed;
            }
            else if (isRightPressed) {
                x += speed;
            }
            else if (isSpacePressed) {
                Bombing();
                setbombCount(0);
            }
            else if (isUpPressed) {
                y -= speed;
            }
            else if (isDownPressed) {
                y += speed;
            }
//        }

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

    public void handleKeyEvent1(KeyEvent keyEvent) {
//        animation++;
        switch (keyEvent.getCode()) {
            case UP:
                isUpPressed  = true;
                moveUp();
                break;
            case DOWN:
                isDownPressed = true;
                moveDown();
                break;
            case LEFT:
                isLeftPressed  = true;
                moveLeft();
                break;
            case RIGHT:
                isRightPressed = true;
                moveRight();
                break;
            case ENTER:
                Bombing();
                break;
        }
    }

    public void CheckImagineMove(Entity e) {
        Rectangle TestRect = new Rectangle(this.getX(), this.getY() + 2, 20,28);

        if ((isRightPressed) && TestRect.intersects(e.getBoundingRect()) && this.getX() + TestRect.getWidth() - e.getX() >= 0) {
            x = e.getX() - 20;
        }

        else if ((isLeftPressed) && TestRect.intersects(e.getBoundingRect()) && e.getX() + 32 - this.getX() >= 0) {
            x = e.getX() + 32;
        }

        else if ((isUpPressed) && TestRect.intersects(e.getBoundingRect()) && e.getY() + 32 - this.getY() >= 0) {
            y = e.getY() + 30;
        }

        else if ((isDownPressed) && TestRect.intersects(e.getBoundingRect()) && this.getY() + 32 - e.getY() >= 0) {
            y = e.getY() - 30;
        }
    }

    public void checkIfDead(ActiveEntity e) {
        Rectangle TestRect = new Rectangle(this.getX(), this.getY() , 30,30);

        if ((e instanceof Flame || e instanceof Enemy) && this.getBoundingRect().intersects(e.getBoundingRect())) {
//            System.out.println("Dead");
            isDead = true;
        }
    }


    public void Bombing() {
        int count = bombCount;
        if (count > 1 && bombTime <=0) count = 1;
        if (mapMatrix[getYMap()][getXMap()] != '*' && mapMatrix[getYMap()][getXMap()] != '#') {
            bombMap[getYMap()][getXMap()] = '@'; // vị trí đặt bomb
            // BombermanGame.bombSound.play(true, 0);
            count--;
            activeObjects.add(new Bomb((int)(getX()/32), (int)(getY()/32), Sprite.bomb.getFxImage(), powerFlames));
        }
        else return;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int xUnit, yUnit;
        for (int i = 0; i < 4; i++) {
            if (count == 0) break;
            xUnit = getXMap() + dx[i];
            yUnit = getYMap() + dy[i];
            if (mapMatrix[yUnit][xUnit] != '*' && mapMatrix[yUnit][xUnit] != '#') {
                bombMap[yUnit][xUnit] = '@'; // vị trí đặt bomb
                count--;
                activeObjects.add(new Bomb((int)(getX()/32), (int)(getY()/32), Sprite.bomb.getFxImage(), powerFlames));
            }
        }
        bombTime--;
    }

    @Override
    public void render(GraphicsContext gc) {
        animate++;
        if (isRightPressed) {
            Sprite spriteNormal = Sprite.player_right;
            Sprite sprite1 = Sprite.player_right_1;
            Sprite sprite2 = Sprite.player_right_2;
            this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
        }
        if (isLeftPressed) {
            Sprite spriteNormal = Sprite.player_left;
            Sprite sprite1 = Sprite.player_left_1;
            Sprite sprite2 = Sprite.player_left_2;
            this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
        }
       if (isUpPressed) {
           Sprite spriteNormal = Sprite.player_up;
           Sprite sprite1 = Sprite.player_up_1;
           Sprite sprite2 = Sprite.player_up_2;
           this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
        }
      if (isDownPressed) {
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

    @Override
    public boolean canMove() {
        return true;
//        int xMap = this.getXMap() ;
//        int yMap = this.getYMap() ;
//        if(xMap<=0) xMap = 1;
//        if(yMap<=0) yMap = 1;
//        Entity other = null;
//        for(int i = xMap-1; i<= xMap+1; i++){
//            for(int j = yMap - 1; j<= yMap+1; j++){
//                if(EntityMatrix[j][i] != null) {
//                    other = EntityMatrix[j][i];
//                    if (this.getBoundingRect().intersects(other.getBoundingRect())) {
//                        if (other instanceof Wall || other instanceof Brick) {
//                            return false;
//                        }
//                        if (other instanceof Enemy) {
//                            System.out.println("Die");
//                            isDead = true;
//                        }
//                    }
//                }
//            }
//        }
//        return true;
    }

    public boolean canMove(int x, int y) {
//        if (wallPass) return true;
        int xUnit = x / Sprite.SCALED_SIZE;
        int yUnit = y / Sprite.SCALED_SIZE;
        return mapMatrix[yUnit][xUnit] != '*' && mapMatrix[yUnit][xUnit] != '#';
    }

    public void moveUp() {
        if (canMove(getX(), getY() - speed)) {
//            isUpPressed  = true;
            setY(getY() - speed);
        }
//        setImg(Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animation, 20).getFxImage());
    }

    public void moveDown() {
        if (canMove(getX(), getY() + speed)) {
//            isDownPressed  = true;
            setY(getY() + speed);
        }
//        setImg(Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animation, 20).getFxImage());
    }

    public void moveLeft() {
        if (canMove(getX() - speed, getY())) {
//            isLeftPressed = true;
            setX(getX() - speed);
        }
//        setImg(Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animation, 20).getFxImage());
    }

    public void moveRight() {
        if (canMove(getX() + speed, getY())) {
//            isRightPressed = true;
            setX(getX() + speed);
        }
//        setImg(Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animation, 20).getFxImage());
    }

}
