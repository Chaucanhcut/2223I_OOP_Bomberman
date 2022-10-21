package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends ImmovableEntities{


    private boolean isBombed  = false;

    public Bomb(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {

    }

    public void setImage() {

    }

    @Override
    public void render(GraphicsContext gc) {

        gc.drawImage(img, x, y);
    }
}