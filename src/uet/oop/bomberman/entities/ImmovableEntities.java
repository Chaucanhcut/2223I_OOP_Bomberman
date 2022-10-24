package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class ImmovableEntities extends Entity{

    public boolean isRendered = true;

    public ImmovableEntities(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        this.BoundingRect = new Rectangle(this.x, this.y, 32, 32);
    }


    @Override
    public void update() {}

}

