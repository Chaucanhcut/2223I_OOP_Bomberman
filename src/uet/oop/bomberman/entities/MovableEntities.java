package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import java.awt.*;

public abstract class MovableEntities extends ActiveEntity {

    public MovableEntities(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    /**
     * Hàm kiểm tra xem có thể đi vào ô (x,y) ko.
     */
    public boolean canMove(int x, int y) {
        return true;
    }
}
