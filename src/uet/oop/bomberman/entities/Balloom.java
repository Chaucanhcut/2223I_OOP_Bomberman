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

    // Thời gian giữa 2 lần chuyển hướng
    private int randomTimeInterval = 0;

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
        speed = 1;
        animationTime = 30;
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
                // Animation balloom chết
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
                Direction = getDirection(xMap, yMap);
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
    }

    @Override
    public void movingUp() {
        setY(this.getY() - speed);
        setImg(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animation, 20).getFxImage());
    }

    @Override
    public void movingDown() {
        setY(getY() + speed);
        setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animation, 20).getFxImage());
    }

    @Override
    public void movingLeft() {
        setX(getX() - speed);
        setImg(Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animation, 20).getFxImage());
    }

    @Override
    public void movingRight() {
        setX(getX() + speed);
        setImg(Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animation, 20).getFxImage());
    }
}
