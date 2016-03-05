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
    private boolean is_me_dead = false;
    private boolean is_game_started = false;
    private boolean is_game_finished = false;
    private ArrayList<Integer> deadTanks;

    public Map() {
        tanks = new ArrayList<Tank>();
        bricks = new ArrayList<Brick>();
        lifePacks = new ArrayList<Life_Pack>();
        coinPiles = new ArrayList<Coin_Pile>();
        stones = new ArrayList<Stone>();
        waters = new ArrayList<Water>();
        bullets = new ArrayList<Bullet>();
        deadTanks = new ArrayList<Integer>();

    }


    public ArrayList<Integer> getDeadTanks() {
        return deadTanks;
    }

    public void setDeadTanks(ArrayList<Integer> deadTanks) {
        this.deadTanks = deadTanks;
    }


    public boolean is_me_dead() {
        return is_me_dead;
    }

    public void setIs_me_dead(boolean is_me_dead) {
        this.is_me_dead = is_me_dead;
    }

    public boolean is_game_started() {
        return is_game_started;
    }

    public void setIs_game_started(boolean is_game_started) {
        this.is_game_started = is_game_started;
    }

    public boolean is_game_finished() {
        return is_game_finished;
    }

    public void setIs_game_finished(boolean is_game_finished) {
        this.is_game_finished = is_game_finished;
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

        long current_time = System.currentTimeMillis();


        ArrayList<Coin_Pile> tem_coin_piles = new ArrayList<>();
        for (int i = 0; i < coinPiles.size(); i++) {
            Coin_Pile coin_pile = coinPiles.get(i);
            //System.out.println("starttime :"+coin_pile.getStartTime());
            if (coin_pile.getLifeTime() != 0) {
                if ((current_time - coin_pile.getStartTime()) < coin_pile.getLifeTime()) {
                    tem_coin_piles.add(coin_pile);
                }
            } else {
                tem_coin_piles.add(coin_pile);
            }
        }

        coinPiles = tem_coin_piles;

        ArrayList<Life_Pack> tem_life_pack = new ArrayList<>();
        for (int i = 0; i < lifePacks.size(); i++) {
            Life_Pack life_pack = lifePacks.get(i);
            if ((current_time - life_pack.getStartTime()) < life_pack.getLifeTime()) {
                tem_life_pack.add(life_pack);
            }

        }

        lifePacks = tem_life_pack;

    }

}
