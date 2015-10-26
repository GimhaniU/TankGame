package game_objects;

/**
 * Created by Gimhani on 10/21/2015.
 */
public class Tank extends Health_Entity {

    public boolean shot;
    public int coins;
    int points;
    int direction;


    public Tank(boolean shot, int coins, int points, int direction,int id,int x,int y) {
        this.shot = shot;
        this.coins = coins;
        this.points = points;
        this.direction = direction;
        this.setId(id);
        this.setY(y);
        this.setX(x);
        this.setEnType(Entity_Type.Tank);
    }

    public Tank() {
        setEnType(Entity_Type.Tank);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isShot() {
        return shot;
    }

    public void setShot(boolean shot) {
        this.shot = shot;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
