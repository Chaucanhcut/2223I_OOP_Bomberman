package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class ActiveEntity extends Entity {
    public boolean delete = false; // Nếu ko còn hoạt động nữa thì xóa khỏi Activelist, delete = true
    public boolean active = true;  // Kiểm tra đang hoạt động không

    public int animation = 0;

    public ActiveEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    /**
     * Kiểm tra va chạm với ActiveEntity khác.
     */
    public void collide(ActiveEntity entity){};

    public abstract void update();
}
