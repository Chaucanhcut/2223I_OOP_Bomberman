package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.GameManagement;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.Random;

import static uet.oop.bomberman.GameManagement.*;
import static uet.oop.bomberman.graphics.Sprite.movingSprite;

public class Balloom extends Enemy {

    private boolean movingRight = false;

    private boolean movingLeft = false;

    private boolean movingUp = false;

    private boolean movingDown = false;

    private boolean[] dir = new boolean[4];

    private int counter = 0;

    private int direction = 0;

    /**
     *
     *
     */
    private int randomDirection = 2;

    // Thời gian giữa 2 lần chuyển hướng
    private int randomTimeInterval = 0;
    private int animationTime = 30;

    private int speed = 1;

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
//        for (int i = 0; i < 3; i++) {
//            dir[i] = true;
//        }
    }

    @Override
    public void update() {

        animation++;

        if (!active) {
            animationTime--;
            if (animationTime < 0) {
//                deadSound.play(false, 1);
                delete = true;
            } else {
                // Animation ballon chết
//                if (!deadSound.isPlaying()) {
//                    deadSound.play(true, 1);
//                }
                if (animationTime > 60) {
                    setImg(Sprite.balloom_dead.getFxImage());
                } else {
                    setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animationTime, 20).getFxImage());
                }
            }
        } else {
            if (getY() % Sprite.SCALED_SIZE == 0 && getX() % Sprite.SCALED_SIZE == 0 && randomTimeInterval <= 0) {
                int xMap = getYMap();
                int yMap = getXMap();
                randomDirection = getDirection(xMap, yMap);
                randomTimeInterval = 30;
            } else {
                randomTimeInterval--;
            }
            Move();
        }



//            animation++;
//            if (!active) {
//                animationTime--;
//                if (animationTime < 0) {
////                deadSound.play(false, 1);
//                    delete = true;
//                } else {
//                    // Animation ballom chết
////                if (!deadSound.isPlaying()) {
////                    deadSound.play(true, 1);
////                }
//                    if (animationTime > 60) {
//                        setImg(Sprite.balloom_dead.getFxImage());
//                    } else {
//                        setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animationTime, 20).getFxImage());
//                    }
//                }
//            } else {
//                if (getY() % Sprite.SCALED_SIZE == 0 && getX() % Sprite.SCALED_SIZE == 0 && randomTimeInterval <= 0) {
//                    int xMap = getYMap();
//                    int yMap = getXMap();
//                    if (xMap <= 0) xMap = 1;
//                    if (yMap <= 0) yMap = 1;
//                    randomDirection = getDirection(xMap, yMap);
//                    randomTimeInterval = 30;
//                } else {
//                    randomTimeInterval--;
//                }
//                Move();
//            }

//        System.out.println(this.getYMap() + "---" + this.getXMap());
//        System.out.println(canPass(this.getYMap()-1, this.getXMap()));
    }

    public void Move() {
        switch (randomDirection) {
            case 0:
                moveUp();
                break;
            case 1:
                moveDown();
                break;
            case 2:
                moveLeft();
                break;
            case 3:
                moveRight();
                break;
        }
    }

    public void moveUp() {
        setY(this.getY() - speed);
        setImg(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animation, 20).getFxImage());
    }

    public void moveDown() {
        setY(getY() + speed);
        setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animation, 20).getFxImage());
    }

    public void moveLeft() {
        setX(getX() - speed);
        setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animation, 20).getFxImage());
    }

    public void moveRight() {
        setX(getX() + speed);
        setImg(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animation, 20).getFxImage());
    }

//    @Override
//    public void CheckImagineMove(Entity e) {
//        int rand = (int) (Math.random() * 3) + 1;
////        Rectangle checkRect = new Rectangle(this.getX(), this.getY() + 2, 20, 28);
//        Rectangle checkRect = this.getBoundingRect();
//
////        if (rand == 1) {
////            while (!ifRight) {
////                if (checkRect.intersects(e.getBoundingRect()) && this.getX() + checkRect.getWidth() - e.getX() >= 0) {
////                    x = e.getX() - 20;
////                    ifRight = true;
////                }
////                x += 2;
////            }
////        }
////        else if (rand == 2) {
////            while (!ifLeft) {
////                if (checkRect.intersects(e.getBoundingRect()) && e.getX() + 32 - this.getX() >= 0) {
////                    x = e.getX() + 32;
////                    ifLeft = true;
////                }
////                x -= 2;
////            }
////        }
////        else if (rand == 3) {
////            while (!ifUp) {
////                if (checkRect.intersects(e.getBoundingRect()) && e.getY() + 32 - this.getY() >= 0) {
////                    y = e.getX() + 30;
////                    ifUp = true;
////                }
////                y -= 2;
////            }
////        }
////        else if (rand == 4) {
////            while (!ifDown) {
////                if (checkRect.intersects(e.getBoundingRect()) && this.getY() + 32 - e.getY() >= 0) {
////                    x = e.getX() - 30;
////                    ifDown = true;
////                }
////                y += 2;
////            }
////        }
//
////        System.out.println("Rand: " + rand);
////        if (rand == 1) {
////            this.x += 2;
////        }
////        else if (rand == 2) {
////            this.x -= 2;
////        }
////        else if (rand == 3) {
////            this.y -= 2;
////        }
////        else if (rand == 4) {
////            this.y += 2;
////        }
//        x += 2;
//    }

    @Override
    public boolean canMove(){
        int xMap = (this.getX()/32) ;
        int yMap = (this.getY()/32) ;
        if(xMap<=0) xMap = 1;
        if(yMap<=0) yMap = 1;
        Entity other = null;
        for(int i = xMap-1; i<= xMap+1; i++){
            for(int j = yMap - 1; j<= yMap+1; j++){
                if(EntityMatrix[j][i] != null) {
                    other = EntityMatrix[j][i];
                    if (this.getBoundingRect().intersects(other.getBoundingRect())) {
                        if (other instanceof Brick || other instanceof Wall) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void ChangeDir(){
        int xMap = (this.getX()/32) ;
        int yMap = (this.getY()/32) ;
        if(xMap<=0) xMap = 1;
        if(yMap<=0) yMap = 1;
        Entity other = null;
        for(int i = xMap-1; i<= xMap+1; i++){
            for(int j = yMap - 1; j<= yMap+1; j++){
                if(EntityMatrix[j][i] != null) {
                    other = EntityMatrix[j][i];
                    if (i == xMap && j == yMap-1) {
                        if (this.getBoundingRect().intersects(other.getBoundingRect())) {
                            movingUp = false;
                        }
                        else movingUp = true;
                    }
                    if (i == xMap-1 && j == yMap) {
                        if (this.getBoundingRect().intersects(other.getBoundingRect())) {
                            movingLeft = false;
                        }
                        else movingLeft = true;
                    }
                    if (i == xMap+1 && j == yMap) {
                        if (this.getBoundingRect().intersects(other.getBoundingRect())) {
                            movingRight = false;
                        }
                        else movingRight = true;
                    }
                    if (i == xMap && j == yMap+1) {
                        if (this.getBoundingRect().intersects(other.getBoundingRect())) {
                            movingDown = false;
                        }
                        else movingDown = true;
                    }
                }
            }
        }
    }

    public void updateRight() {
        movingRight = true;
        x += 2;
    }

    public void updateLeft() {
        movingLeft = true;
        x -= 2;
    }

    public void updateUp() {
        movingUp = true;
        y -= 2;
    }

    public void updateDown() {
        movingDown = true;
        y += 2;
    }

//    @Override
//    public void render(GraphicsContext gc) {
//        animate++;
//        movingRight = true;
//        if (movingRight) {
//            Sprite spriteNormal = Sprite.balloom_right1;
//            Sprite sprite1 = Sprite.balloom_right2;
//            Sprite sprite2 = Sprite.balloom_right3;
//            this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
//        }
//        else if (movingLeft) {
//            Sprite spriteNormal = Sprite.balloom_left1;
//            Sprite sprite1 = Sprite.balloom_left2;
//            Sprite sprite2 = Sprite.balloom_left3;
//            this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
//        }
//        else {
//            this.img = Sprite.balloom_right1.getFxImage();
//        }
//        gc.drawImage(img, x,y);
//    }

    /**
     * Random hướng đi của balloom
     *
     * @param x tọa độ hàng trên map
     * @param y tọa độ cột trên map
     * @return hướng random đi được
     */
    public int getDirection(int x, int y) {

        Random random = new Random();
        int randomDirection;

        while (true) {
            randomDirection = random.nextInt(4);
            if (randomDirection == 0 && canPass(x - 1, y)) { // up
                return randomDirection;
            }
            if (randomDirection == 1 && canPass(x + 1, y)) { // down
                return randomDirection;
            }
            if (randomDirection == 3 && canPass(x, y + 1)) { // left
                return randomDirection;
            }
            if (randomDirection == 2 && canPass(x, y - 1)) { // right
                return randomDirection;
            }
        }

//            randomDirection = random.nextInt(4);
//            if (randomDirection == 0 && canPass(x - 1, y)) { // up
////                return randomDirection;
//                randomDirection = 0;
//            }
//            else if (randomDirection == 1 && canPass(x + 1, y)) { // down
////                return randomDirection;
//                randomDirection = 1;
//            }
//            else if (randomDirection == 3 && canPass(x, y + 1)) { // left
////                return randomDirection;
//                randomDirection = 3;
//            }
//            else if (randomDirection == 2 && canPass(x, y - 1)) { // right
////                return randomDirection;
//                randomDirection = 2;
//            }
////        System.out.println(randomDirection);
//            return randomDirection;

//        while (true) {
//            randomDirection = random.nextInt(4);
//            if (randomDirection == 0 && canPass(x - 1, y)) { // up
////                return randomDirection;
//                randomDirection = 0;
//            }
//            if (randomDirection == 1 && canPass(x + 1, y)) { // down
////                return randomDirection;
//                randomDirection = 1;
//            }
//            if (randomDirection == 3 && canPass(x, y + 1)) { // left
////                return randomDirection;
//                randomDirection = 3;
//            }
//            if (randomDirection == 2 && canPass(x, y - 1)) { // right
////                return randomDirection;
//                randomDirection = 2;
//            }
//            return randomDirection;
//        }
    }

    private boolean canPass(int x, int y) {
//        return GameManagement.mapMatrix[x][y] != '*' && GameManagement.mapMatrix[x][y] != '#'
//                && GameManagement.bombMap[x][y] == ' ';

//        System.out.println(x + "-" + y);
//
//        System.out.println(EntityMatrix[x][y] instanceof Wall);

        if (EntityMatrix[x][y] instanceof Wall || EntityMatrix[x][y] instanceof Brick ) {
            return false;
        }
        return true;



//        if(EntityMatrix[x][y] != null) {
//            Entity other = EntityMatrix[x][y];
//            if (this.getBoundingRect().intersects(other.getBoundingRect())) {
//                if (other instanceof Brick || other instanceof Wall) {
//                    return false;
//                }
//            }
//        }
//        return true;
    }
}
