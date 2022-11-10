package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameManagement;

import java.awt.*;
import java.util.Random;

import static uet.oop.bomberman.GameManagement.getBombMap;


public abstract class Enemy extends MovableEntities {

    protected int speed;

    protected int animationTime;

    protected int Direction;

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public Rectangle getBoundingRect() {
        return new Rectangle(this.getX(), this.getY(), 30, 30);
    }

    /**
     * Random hướng đi của balloom với x, y là index của hàng và cột trong mapMatrix.
     */
    protected int getDirection(int x, int y) {

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
    }

    /**
     * hàm check xem có thể đi qua ô có index x, y trên mapMatrix không.
     */
    protected boolean canPass(int x, int y) {
        if (GameManagement.getMapMatrix()[x][y] == '#' || GameManagement.getMapMatrix()[x][y] == '*' || getBombMap()[x][y] == '@') {
            return false;
        }
        return true;
    }

    /**
     * Hàm pick hướng đi.
     */
    protected void Move() {
        switch (Direction) {
            case 0:
                movingUp();
                break;
            case 1:
                movingDown();
                break;
            case 2:
                movingLeft();
                break;
            case 3:
                movingRight();
                break;
        }
    }

    protected abstract void movingUp();

    protected abstract void movingDown();

    protected abstract void movingLeft();

    protected abstract void movingRight();

}
