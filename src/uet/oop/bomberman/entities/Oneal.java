package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import uet.oop.bomberman.GameManagement;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;
import java.util.Random;

import static uet.oop.bomberman.GameManagement.*;

public class Oneal extends Enemy {

    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        speed = 1;
        animationTime = 30;
    }

    @Override
    public void update() {
        animation++;

        if (!active) {
            animationTime--;
            if (animationTime < 0) {
                delete = true;
            }

            // Oneal chết
            if (animationTime > 60) {
                if (!getDeadSound().isPlaying()) {
                    getDeadSound().play(true, 1);
                }
                setImg(Sprite.oneal_dead.getFxImage());
            } else {
                setImg(Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, animationTime, 20).getFxImage());
            }
        } else {
            if (getY() % Sprite.SCALED_SIZE == 0 && getX() % Sprite.SCALED_SIZE == 0) {
                int xMap = getYMap();
                int yMap = getXMap();
                Direction = getAStarDirection(xMap, yMap, getMapMatrix());
            }
            Move();
        }
    }

    public int getAStarDirection(int x, int y, char[][] map) {
                int xBomber = GameManagement.getBomber().getYMap();
                int yBomber = GameManagement.getBomber().getXMap();

                AStarAlgorithm aStar = new AStarAlgorithm(map, x, y, xBomber, yBomber);
                List<Node> resultPath = aStar.aStarSearch(); // Đường đi lưu các Node từ Bomber -> Enemy
                if (resultPath != null) {
                    if (resultPath.size() < 2) return getDirection(x, y);
                    Node nextNode = resultPath.get(resultPath.size() - 2);
                    // Bước đi tiếp theo của Enemy
                    if (x - 1 == nextNode.getRow()) {
                        return 0; // up
                    } else if (x + 1 == nextNode.getRow()) {
                        return 1; // down
                    } else if (y - 1 == nextNode.getCol()) {
                        return 2; // left
                    } else if (y + 1 == nextNode.getCol()) {
                        return 3; // right
                    }
                }
        return getDirection(x, y);
    }

    @Override
    public void movingUp() {
        if (getMapMatrix()[getYMap()][getXMap()] != '*' && getMapMatrix()[getYMap()][getXMap()] != '#' || getBombMap()[getYMap()][getXMap()] != '@') {
            setY(getY() - speed);
        }
        setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animation, 20).getFxImage());
    }

    @Override
    public void movingDown() {
        if (getMapMatrix()[getYMap()][getXMap()] != '*' && getMapMatrix()[getYMap()][getXMap()] != '#' || getBombMap()[getYMap()][getXMap()] != '@') {
            setY(getY() + speed);
        }
        setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animation, 20).getFxImage());
    }

    @Override
    public void movingLeft() {
        if (getMapMatrix()[getYMap()][getXMap()] != '*' && getMapMatrix()[getYMap()][getXMap()] != '#' || getBombMap()[getYMap()][getXMap()] != '@') {
            setX(getX() - speed);
        }
        setImg(Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, animation, 20).getFxImage());
    }

    @Override
    public void movingRight() {
        if (getMapMatrix()[getYMap()][getXMap()] != '*' && getMapMatrix()[getYMap()][getXMap()] != '#' || getBombMap()[getYMap()][getXMap()] != '@') {
            setX(getX() + speed);
        }
        setImg(Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, animation, 20).getFxImage());
    }
}
