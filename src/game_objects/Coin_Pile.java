package game_objects;

/**
 * Created by Gimhani on 10/21/2015.
 */
public class Coin_Pile extends Time_Out_Entity {
    int value;

    public Coin_Pile() {
        setEnType(Entity_Type.CoinPile);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
