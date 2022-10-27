package uet.oop.bomberman.entities.stillEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import java.awt.*;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        this.BoundingRect = new Rectangle(this.x, this.y, 32, 32);
    }

    @Override
    public void update() {

    }
}
