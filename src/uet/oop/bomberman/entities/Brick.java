package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.GameManagement;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Brick extends ImmovableEntities {

    private int animationTime = 40;
    public Brick(int x, int y, Image img) {
        super(x, y, img);
//        this.BoundingRect = new Rectangle(this.getXMap(), this.getYMap(), 32, 32);
    }

    @Override
    public void update() {
        if (!active) {
            animationTime--; // Đếm ngược bom nổ
            if (animationTime < 0) { // Nếu đã hết thời gian sau khi nổ
                int xBrick = getYMap();
                int yBrick = getXMap();
                GameManagement.getMapMatrix()[xBrick][yBrick] = ' ';
                delete = true; // Xoá
            }
            // Animation brick nổ
            setImg(Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, animationTime, 20).getFxImage());
        }
    }
}
