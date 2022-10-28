package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class ActiveEntity extends Entity {
    public boolean delete = false; // Nếu ko còn hoạt động nữa thì xóa khỏi list
    public boolean active = true;  // Xem có còn đang hoạt động ko, nếu ko thì delete = true

    public int animation = 0;

    public ActiveEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    /**
     * Kiểm tra sự va chạm với entity khác.
     *
     * @param entity vật bị va chạm
     */
    public void collide(ActiveEntity entity) {

    }

    public abstract void update();
}
