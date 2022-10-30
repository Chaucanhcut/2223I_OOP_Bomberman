package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

//    @Override
//    public void CheckImagineMove(Entity e) {
//        x -= 2;
//    }

    @Override
    public boolean canMove() {
        return false;
    }
}
