package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.awt.*;

public abstract class StillEntity extends Entity {
    public StillEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public Rectangle getBoundingRect() {
        return new Rectangle(this.x, this.y, 32, 32);
    }
}
