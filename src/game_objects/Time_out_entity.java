package game_objects;

/**
 * Created by Gimhani on 10/21/2015.
 */
public class Time_Out_Entity  extends Entity{
    int startTime,lifeTime;

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }
}
