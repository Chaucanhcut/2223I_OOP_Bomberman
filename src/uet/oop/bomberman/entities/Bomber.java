package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import static uet.oop.bomberman.GameManagement.*;
import static uet.oop.bomberman.GameManagement.bombMap;
import static uet.oop.bomberman.GameManagement.mapMatrix;
import static uet.oop.bomberman.GameManagement.Bombs;

import static uet.oop.bomberman.entities.Flame.powerFlames;


import static uet.oop.bomberman.graphics.Sprite.movingSprite;

public class Bomber extends MovableEntities {

    private boolean isDead = false;

    private int animate = 0;

    private final int TIME = 30;
    private GraphicsContext gc;
    public int bombCount; // so luong bomb co the dat
    public int bombTime = 150;
    public Bomber(int x, int y, Image img, GraphicsContext gc) {
        super( x, y, img);
        this.gc = gc;
        bombCount = 1;
    }

    @Override
    public Rectangle getBoundingRect() {
        return new Rectangle(this.getX(), this.getY() + 2, 20,28);
    }

    @Override
    public void update() {
            if (isLeftPressed) {
                 x -= 2;
            }
            else if (isRightPressed) {
                 x += 2;
            }
            else if (isSpacePressed) {
                Bombing();
            }
            else if (isUpPressed) {
                 y -= 2;
            }
            else if (isDownPressed) {
                 y += 2;
            }
    }

    public void CheckImagineMove(Entity e) {
        Rectangle TestRect = new Rectangle(this.getX(), this.getY() + 2, 20,28);

        if ((isRightPressed) && TestRect.intersects(e.getBoundingRect()) && this.getX() + TestRect.getWidth() - e.getX() >= 0) {
            x = e.getX() - 20;
        }

        else if ((isLeftPressed) && TestRect.intersects(e.getBoundingRect()) && e.getX() + 32 - this.getX() >= 0) {
            x = e.getX() + 32;
        }

        else if ((isUpPressed) && TestRect.intersects(e.getBoundingRect()) && e.getY() + 32 - this.getY() >= 0) {
            y = e.getY() + 30;
        }

        else if ((isDownPressed) && TestRect.intersects(e.getBoundingRect()) && this.getY() + 32 - e.getY() >= 0) {
            y = e.getY() - 30;
        }
    }


    public void Bombing() {
        int count = bombCount;
        if (count > 1 && bombTime <=0) count = 1;
        if (mapMatrix[getYMap()][getXMap()] != '*' && mapMatrix[getYMap()][getXMap()] != '#') {
            bombMap[getYMap()][getXMap()] = '@'; // vị trí đặt bomb
            // BombermanGame.bombSound.play(true, 0);
            count--;
            activeObjects.add(new Bomb((int)(getX()/32), (int)(getY()/32), Sprite.bomb.getFxImage(), powerFlames));
        }
        else return;
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int xUnit, yUnit;
        for (int i = 0; i < 4; i++) {
            if (count == 0) break;
            xUnit = getXMap() + dx[i];
            yUnit = getYMap() + dy[i];
            if (mapMatrix[yUnit][xUnit] != '*' && mapMatrix[yUnit][xUnit] != '#') {
                bombMap[yUnit][xUnit] = '@'; // vị trí đặt bomb
                count--;
                activeObjects.add(new Bomb((int)(getX()/32), (int)(getY()/32), Sprite.bomb.getFxImage(), powerFlames));
            }
        }
        bombTime--;
    }

    @Override
    public void render(GraphicsContext gc) {
        animate++;
        if (isRightPressed) {
            Sprite spriteNormal = Sprite.player_right;
            Sprite sprite1 = Sprite.player_right_1;
            Sprite sprite2 = Sprite.player_right_2;
            this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
        }
        if (isLeftPressed) {
            Sprite spriteNormal = Sprite.player_left;
            Sprite sprite1 = Sprite.player_left_1;
            Sprite sprite2 = Sprite.player_left_2;
            this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
        }
       if (isUpPressed) {
           Sprite spriteNormal = Sprite.player_up;
           Sprite sprite1 = Sprite.player_up_1;
           Sprite sprite2 = Sprite.player_up_2;
           this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
        }
      if (isDownPressed) {
          Sprite spriteNormal = Sprite.player_down;
          Sprite sprite1 = Sprite.player_down_1;
          Sprite sprite2 = Sprite.player_down_2;
          this.img = movingSprite(spriteNormal, sprite1, sprite2, animate, TIME).getFxImage();
      }
        gc.drawImage(img, x,y);
    }

    @Override
    public boolean canMove() {
        int xMap = this.getXMap() ;
        int yMap = this.getYMap() ;
        if(xMap<=0) xMap = 1;
        if(yMap<=0) yMap = 1;
        Entity other = null;
        for(int i = xMap-1; i<= xMap+1; i++){
            for(int j = yMap - 1; j<= yMap+1; j++){
                if(EntityMatrix[j][i] != null) {
                    other = EntityMatrix[j][i];
                    if (this.getBoundingRect().intersects(other.getBoundingRect())) {
                        if (other instanceof Wall || other instanceof Brick) {
                            return false;
                        }
                        if (other instanceof Enemy) {
                            System.out.println("Die");
                            isDead = true;
                        }
                    }
                }
            }
        }
        return true;
    }
}
