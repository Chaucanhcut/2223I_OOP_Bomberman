package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import java.awt.*;

public class Wall extends StillEntity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        this.BoundingRect = new Rectangle(this.x, this.y, 32, 32);
    }
}
