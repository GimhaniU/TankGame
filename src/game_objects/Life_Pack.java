package game_objects;

/**
 * Created by Gimhani on 10/21/2015.
 */
public class Life_Pack extends Time_Out_Entity {
    final int value = 20;

    public Life_Pack() {
        setEnType(Entity_Type.Lifepack);
    }

    public int getValue() {
        return value;
    }
}
