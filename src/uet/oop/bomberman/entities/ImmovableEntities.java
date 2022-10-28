package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.awt.*;

public abstract class ImmovableEntities extends ActiveEntity {

    public ImmovableEntities(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.BoundingRect = new Rectangle(this.x, this.y, 32, 32);
    }

    @Override
    public void update() {}

    @Override
    public Rectangle getBoundingRect() {
        return new Rectangle(this.getXMap(), this.getYMap(), 32, 32);
    }

}

