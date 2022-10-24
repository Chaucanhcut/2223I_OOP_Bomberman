package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.awt.*;

public class Balloom extends MovableEntities {
    private final int MAX = 4;
    private final int MIN = 1;

    private final int RANGE = MAX - MIN + 1;

    private boolean ifRight = false;

    private boolean ifLeft = false;

    private boolean ifUp = false;

    private boolean ifDown = false;

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        this.updateRect();
    }

    @Override
    public void CheckImagineMove(Entity e) {
        int rand = (int) (Math.random() * RANGE) + MIN;
        Rectangle checkRect = new Rectangle(this.getX(), this.getY() + 2, 20, 28);
        if (rand == 1) {
//            checkRect = new Rectangle(this.getX(), this.getY() + 2, 20, 28);
            if (checkRect.intersects(e.getBoundingRect()) && this.getX() + checkRect.getWidth() - e.getX() >= 0) {
                x = e.getX() - 20;
                ifRight = true;
            }
            while (!ifRight) {
                x += 2;
            }
        }
        else if (rand == 2) {
            if (checkRect.intersects(e.getBoundingRect()) && e.getX() + 32 - this.getX() >= 0) {
                x = e.getX() + 32;
                ifLeft = true;
            }
            while (!ifLeft) {
                x -= 2;
            }
        }
        else if (rand == 3) {
            if (checkRect.intersects(e.getBoundingRect()) && e.getY() + 32 - this.getY() >= 0) {
                y = e.getX() + 30;
                ifUp = true;
            }
            while (!ifUp) {
                y -= 2;
            }
        }
        else if (rand == 4) {
            if (checkRect.intersects(e.getBoundingRect()) && this.getY() + 32 - e.getY() >= 0) {
                x = e.getX() - 30;
                ifDown = true;
            }
            while (!ifDown) {
                y += 2;
            }
        }



    }
}
