package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.GameManagement;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends ImmovableEntities{
    protected int timeAfter = 30;
    public int FlamesPower;
    public static int timeExplode;
    public boolean added = false;
    private Flame flame;


    public Bomb(int x, int y, Image img, int FlamesPower) {
        super(x, y, img);
        this.FlamesPower = FlamesPower;
        active = true;
        delete = false;
        timeExplode = 1200;
    }

    @Override
    public void update() {
        if (timeExplode > 0) { // Chưa nổ thì cho hiện bomb
            timeExplode--;
            setImg(Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, timeExplode, 100).getFxImage());
        } else { // Nổ rồi thì cho hiện flame
            if (!added) {
                // Tạo flame
                this.flame = new Flame(getXMap(), getYMap(), 1, false, Sprite.explosion_vertical.getFxImage());
                flame.createFlame();
                GameManagement.getActiveObjects().addAll(flame.flameList);
                added = true;
            }
            timeAfter--; // Đếm ngược thời gian bom khi nổ
            if (timeAfter < 0) { // Nếu đã hết thời gian nổ
                delete = true;
                active = false;
                GameManagement.getBombMap()[getYMap()][getXMap()] = ' ';
            }
            // bom nổ
            setImg(Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, timeAfter, 100).getFxImage());
            GameManagement.getBombSound().play(false, 0);
        }
    }
}
