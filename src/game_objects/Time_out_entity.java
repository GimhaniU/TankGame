package game_objects;

/**
 * Created by Gimhani on 10/21/2015.
 */
public class Time_Out_Entity extends Entity {
    int lifeTime;
    long startTime;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }
}
