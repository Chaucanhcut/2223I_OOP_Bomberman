package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.GameManagement.*;
import static uet.oop.bomberman.graphics.Sprite.DEFAULT_SIZE;
import static uet.oop.bomberman.graphics.Sprite.movingSprite;

public class Bomber extends Entity {

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }


    @Override
    public void checkCollide(Entity entity){
        this.ifCollide(entity);
        while (this.checkCollision == true) {
            int i = 0;
            if (entity instanceof Brick){
                System.out.println("Hello" + i);
                i++;
            }
            if (entity instanceof Wall){}
        }
    }

    @Override
    public void update() {
        if (isLeftPressed) {
            x -= 3;
        }
        if (isRightPressed) {
            x += 3;
        }

        if (isSpacePressed) {
        }
    }
}
