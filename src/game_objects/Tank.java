package game_objects;

/**
 * Created by Gimhani on 10/21/2015.
 */
public class Tank extends Entity {

    public boolean shot;
    public int coins;
    int points;

    public Tank() {
        setEnType(Entity_Type.Tank);
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
