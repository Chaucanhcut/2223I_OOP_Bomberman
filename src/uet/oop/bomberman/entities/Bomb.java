package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.GameManagement;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.GameManagement.bombMap;

public class Bomb extends ImmovableEntities{
    protected int timeAfter = 30;
    public int FlamesPower;
    public static int timeExplode;
    public boolean added = false;
    private Flame flame;


    /**ham bomb co tham so powerFlame de luu do dai cua flame. */
    public Bomb(int x, int y, Image img, int FlamesPower) {
        super(x, y, img);
        this.FlamesPower = FlamesPower;
        active = true;
        delete = false;
        timeExplode = 1500;
    }

    @Override
    public void update() {
        if (timeExplode > 0) { // Chưa nổ thì cho hiện bomb
//            System.out.println("sap no");
            timeExplode--;
            setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, timeExplode, 200).getFxImage());
        } else { // Nổ rồi thì cho hiện flame
            if (!added) {
                // Tạo flame
                this.flame = new Flame(getXMap(), getYMap(), 1, false, Sprite.explosion_vertical.getFxImage());
                flame.createFlame();
                GameManagement.activeObjects.addAll(flame.flameList);
                added = true;
            }
            timeAfter--; // Đếm ngược thời gian bom khi nổ
            if (timeAfter < 0) { // Nếu đã hết thời gian nổ
                delete = true;
                active = false;
                GameManagement.bombMap[getYMap()][getXMap()] = ' ';
            }
            // Animation bom nổ
            setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, timeAfter, 200).getFxImage());
            //BombermanGame.bombSound.play(false, 0);
        }
    }
}
