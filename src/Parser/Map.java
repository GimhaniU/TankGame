package Parser;

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

    public Map() {
        tanks = new ArrayList<Tank>();
        bricks = new ArrayList<Brick>();
        lifePacks = new ArrayList<Life_Pack>();
        coinPiles = new ArrayList<Coin_Pile>();
        stones = new ArrayList<Stone>();
        waters = new ArrayList<Water>();
        bullets = new ArrayList<Bullet>();

    }



    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(ArrayList<Tank> tanks) {
        this.tanks = tanks;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public void setBricks(ArrayList<Brick> bricks) {
        this.bricks = bricks;
    }

    public ArrayList<Life_Pack> getLifePacks() {
        return lifePacks;
    }

    public void setLifePacks(ArrayList<Life_Pack> lifePacks) {
        this.lifePacks = lifePacks;
    }

    public ArrayList<Coin_Pile> getCoinPiles() {
        return coinPiles;
    }

    public void setCoinPiles(ArrayList<Coin_Pile> coinPiles) {
        this.coinPiles = coinPiles;
    }

    public ArrayList<Stone> getStones() {
        return stones;
    }

    public void setStones(ArrayList<Stone> stones) {
        this.stones = stones;
    }

    public ArrayList<Water> getWaters() {
        return waters;
    }

    public void setWaters(ArrayList<Water> waters) {
        this.waters = waters;
    }

    private ArrayList<Bullet> bullets;
    public final int MAP_WIDTH = 10, MAP_HEIGHT = 10;
    private int clientID;


    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }


    public void Update() {

        long current_time = System.currentTimeMillis() / 1000;

        ArrayList<Coin_Pile> tem_coin_piles = new ArrayList<>();
        for (int i=0;i<coinPiles.size();i++){
            Coin_Pile coin_pile = coinPiles.get(i);
            if(current_time-coin_pile.getStartTime()<coin_pile.getLifeTime()){
                tem_coin_piles.add(coin_pile);
            }

        }
        coinPiles=tem_coin_piles;


        ArrayList<Life_Pack> tem_life_pack = new ArrayList<>();
        for (int i=0;i<lifePacks.size();i++){
            Life_Pack life_pack = lifePacks.get(i);
            if(current_time-life_pack.getStartTime()<life_pack.getLifeTime()){
                tem_life_pack.add(life_pack);
            }

        }
        lifePacks=tem_life_pack;


    }



}
