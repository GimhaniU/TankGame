package game_objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Gimhani on 10/21/2015.
 */
public class Bullet extends Entity {
    private int id = 0;

    private Vector2f pos;
    private Vector2f speed = null;
    private boolean is_active = true;
    private int direction;
    private Image bullet_img;

    public Image getBullet_img() {
        return bullet_img;
    }

    public void setBullet_img(Image bullet_img) {
        this.bullet_img = bullet_img;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Bullet() {
        setEnType(Entity_Type.Bullet);

    }

    public Bullet(int id) {
        this.id = id;
        setEnType(Entity_Type.Bullet);
    }

    public Bullet(Vector2f init_pos) {
        this.pos = init_pos;

    }

    public Bullet(Vector2f init_pos, int direction, Image img) {
        this.pos = init_pos;
        this.direction = direction;
        this.bullet_img = img;
        this.speed = getSpeed(direction);
    }

    private Vector2f getSpeed(int direction) {
        switch (direction) {
            case 0:
                return new Vector2f(0, -200);
            case 1:
                return new Vector2f(200, 0);
            case 2:
                return new Vector2f(0, 200);
            case 3:
                return new Vector2f(-200, 0);
        }
        return new Vector2f(0, 200);
    }

    //to update the position
    public void update(int t) {
        if (is_active) {
            Vector2f real_speed = speed.copy();
            real_speed.scale(t / 3.0f);
            pos.add(real_speed);
            if (pos.getX() < 0 || pos.getX() > 972 || pos.getY() < 0 || pos.getY() > 648) {
                is_active = false;

            }
        }
    }


    public boolean is_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public Vector2f getSpeed() {
        return speed;
    }

    public void setSpeed(Vector2f speed) {
        this.speed = speed;
    }

    public Vector2f getPos() {
        return pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
