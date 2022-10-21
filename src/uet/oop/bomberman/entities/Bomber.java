package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import static uet.oop.bomberman.GameManagement.*;
import static uet.oop.bomberman.graphics.Sprite.movingSprite;

public class Bomber extends MovableEntities {
    private GraphicsContext gc;

    public Bomber(int x, int y, Image img, GraphicsContext gc) {
        super( x, y, img);
        this.gc = gc;
    }

    public void setX(int X) {
        this.x = X;
    }

    public void setY(int Y) {
        this.y = Y;
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
            Bombing();
        }

        if (isUpPressed) {
            y -= 3;
        }

        if (isDownPressed) {
            y += 3;
        }
        this.updateRect();
    }

    @Override
    public void CheckImagineMove(Entity e) {
        Rectangle TestRect = new Rectangle(this.getX() , this.getY(), 15,15);
        if (TestRect.intersects(e.getBoundingRect())) {
            x = e.getX() - 16;
        }
    }


    public void Bombing() {

    }

  @Override
    public void render(GraphicsContext gc) {
        if (isRightPressed) {
            Sprite spriteNormal = Sprite.player_right;
            Sprite sprite1 = Sprite.player_right_1;
            Sprite sprite2 = Sprite.player_right_2;
            this.img = movingSprite(spriteNormal, sprite1, sprite2, Math.abs(timeStart- timeStop), 300).getFxImage();
        }
        if (isLeftPressed) {
            Sprite spriteNormal = Sprite.player_left;
            Sprite sprite1 = Sprite.player_left_1;
            Sprite sprite2 = Sprite.player_left_2;
            this.img = movingSprite(spriteNormal, sprite1, sprite2, Math.abs(timeStart- timeStop), 300).getFxImage();
        }

        gc.drawImage(img, x,y);
    }
}
