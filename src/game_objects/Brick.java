package game_objects;

/**
 * Created by Gimhani on 10/21/2015.
 */
public class Brick extends Health_Entity {

    public Brick() {
        setEnType(Entity_Type.Brick);
    }

    @Override
    public void setHealth(int health) {
        switch (health) {
            case 0:
                super.health = 100;
                break;
            case 1:
                super.health = 75;
                break;
            case 2:
                super.health = 50;
                break;
            case 3:
                super.health = 25;
                break;
            case 5:
                super.health = 0;

                break;
        }
    }
}
