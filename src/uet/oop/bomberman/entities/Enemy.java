package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.awt.*;

public abstract class Enemy extends MovableEntities {

    protected int animate = 0;

    protected final int TIME = 30;

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void CheckImagineMove(Entity e) {

    }

    @Override
    public Rectangle getBoundingRect() {
        return new Rectangle(this.getX(), this.getY(), 32, 32);
    }

//    @Override
//    public boolean canMove() {
//
//    }

}
