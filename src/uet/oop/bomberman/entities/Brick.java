package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.awt.*;

public class Brick extends ImmovableEntities {
    public Brick(int x, int y, Image img) {
        super(x, y, img);
//        this.BoundingRect = new Rectangle(this.getXMap(), this.getYMap(), 32, 32);
    }

    @Override
    public void update() {

    }
}
