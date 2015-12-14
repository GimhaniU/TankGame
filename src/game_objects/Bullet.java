package game_objects;

/**
 * Created by Gimhani on 10/21/2015.
 */
public class Bullet extends Entity {
    private int id=0;
    public Bullet() {
        setEnType(Entity_Type.Bullet);
    }
    public Bullet(int id) {
        this.id=id;
        setEnType(Entity_Type.Bullet);
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
