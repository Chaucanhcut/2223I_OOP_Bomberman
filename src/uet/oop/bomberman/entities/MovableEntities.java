package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.awt.*;
import java.util.function.DoubleToIntFunction;

public abstract class MovableEntities extends Entity {

    public MovableEntities(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }

    public void updateRect() {
//        this.BoundingRect = new Rectangle(x,y,30,30);
        this.BoundingRect = new Rectangle(x,y,32,32);
    }


    public abstract void CheckImagineMove(Entity e);
}
