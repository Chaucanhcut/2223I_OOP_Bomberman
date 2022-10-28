package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
//import uet.oop.bomberman.entities.stillEntity.Wall;

import java.awt.*;

import static uet.oop.bomberman.GameManagement.*;
import static uet.oop.bomberman.graphics.Sprite.movingSprite;

public class Balloom extends Enemy {

    private boolean moveRight = false;

    private boolean moveLeft = false;

    private boolean moveUp = false;

    private boolean moveDown = false;

    private int counter = 0;

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
    }

    int l = 0;

    @Override
    public void update() {

//        if (canMove()) {
//            updateRight();
//        }


        int rand = (int)(Math.random()*4) + 1;
        if (rand == 1) {
            if (!canMove()) {

            }
        }
        if (rand == 2) {

        }
        if (rand == 3) {

        }
        if (rand == 4) {

        }
    }

    @Override
    public void CheckImagineMove(Entity e) {
        int rand = (int) (Math.random() * 3) + 1;
//        Rectangle checkRect = new Rectangle(this.getX(), this.getY() + 2, 20, 28);
        Rectangle checkRect = this.getBoundingRect();

//        if (rand == 1) {
//            while (!ifRight) {
//                if (checkRect.intersects(e.getBoundingRect()) && this.getX() + checkRect.getWidth() - e.getX() >= 0) {
//                    x = e.getX() - 20;
//                    ifRight = true;
//                }
//                x += 2;
//            }
//        }
//        else if (rand == 2) {
//            while (!ifLeft) {
//                if (checkRect.intersects(e.getBoundingRect()) && e.getX() + 32 - this.getX() >= 0) {
//                    x = e.getX() + 32;
//                    ifLeft = true;
//                }
//                x -= 2;
//            }
//        }
//        else if (rand == 3) {
//            while (!ifUp) {
//                if (checkRect.intersects(e.getBoundingRect()) && e.getY() + 32 - this.getY() >= 0) {
//                    y = e.getX() + 30;
//                    ifUp = true;
//                }
//                y -= 2;
//            }
//        }
//        else if (rand == 4) {
//            while (!ifDown) {
//                if (checkRect.intersects(e.getBoundingRect()) && this.getY() + 32 - e.getY() >= 0) {
//                    x = e.getX() - 30;
//                    ifDown = true;
//                }
//                y += 2;
//            }
//        }

//        System.out.println("Rand: " + rand);
//        if (rand == 1) {
//            this.x += 2;
//        }
//        else if (rand == 2) {
//            this.x -= 2;
//        }
//        else if (rand == 3) {
//            this.y -= 2;
//        }
//        else if (rand == 4) {
//            this.y += 2;
//        }
        x += 2;
    }

    public boolean canMove(){
        int xMap = this.getXMap() ;
        int yMap = this.getYMap() ;
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

    public void updateRight() {
        moveRight = true;
        x += 2;
    }

    public void updateLeft() {
        moveLeft = true;
        x -= 2;
    }

    public void updateUp() {
        moveUp = true;
        y -= 2;
    }

    public void updateDown() {
        moveDown = true;
        y += 2;
    }

    @Override
    public void render(GraphicsContext gc) {
        animate++;
        if (moveRight) {
            Sprite spriteNormal = Sprite.balloom_right1;
            Sprite sprite1 = Sprite.balloom_right2;
            Sprite sprite2 = Sprite.balloom_right3;
            this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
        }
        else if (moveLeft) {
            Sprite spriteNormal = Sprite.balloom_left1;
            Sprite sprite1 = Sprite.balloom_left2;
            Sprite sprite2 = Sprite.balloom_left3;
            this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
        }
        else {
            this.img = Sprite.balloom_right1.getFxImage();
        }
        gc.drawImage(img, x,y);
    }
}
