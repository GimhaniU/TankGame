import game_objects.*;

import java.util.ArrayList;

/**
 * Created by Gimhani on 10/21/2015.
 */
public class Map {

    private ArrayList<Tank> tanks;
    private ArrayList<Brick> bricks;
    private ArrayList<Life_Pack> lifePacks;
    private ArrayList<Coin_Pile> coinPiles;
    private ArrayList<Stone> stones;
    private ArrayList<Water> waters;
    private ArrayList<Bullet> bullets;
    public final int MAP_WIDTH = 10, MAP_HEIGHT = 10;
    private int clientID;

    public Map() {
        tanks = new ArrayList<Tank>();
        bricks = new ArrayList<Brick>();
        lifePacks = new ArrayList<Life_Pack>();
        coinPiles = new ArrayList<Coin_Pile>();
        stones = new ArrayList<Stone>();
        waters = new ArrayList<Water>();
        bullets = new ArrayList<Bullet>();

    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

}
