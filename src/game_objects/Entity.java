package game_objects;

/**
 * Created by Gimhani on 10/21/2015.
 */
public class Entity {

    private int id;
    private int x;
    private int y;
    Entity_Type enType;


    public Entity_Type getEnType() {
        return enType;
    }

    public void setEnType(Entity_Type enType) {
        this.enType = enType;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
