package Parser;

import Parser.Map;
import com.sun.org.apache.xpath.internal.SourceTree;
import game_objects.*;

import java.util.ArrayList;

/**
 * Created by Gimhani on 10/21/2015.
 */
public class Message_Decorder {

    private Map map = null;

    public Message_Decorder(Map map) {
        this.map = map;

    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map process(String message) {
        if (message.length() > 1) {
            char firstChar = message.charAt(0);
            //System.out.println(firstChar);
            switch (firstChar) {
                case 'S':
                    processStartMessage(message);
                    break;
                case 'I':
                    processInitializationMessage(message);
                    break;
                case 'C':
                    placeCoinPile(message);
                    break;
                case 'L':
                    placeLifePack(message);
                    break;
                case 'G':
                    processGameUpdateMessage(message);
                    break;
            }
        }
        return map;
    }

    private void processStartMessage(String message) {
        if (message.endsWith("#")) {
            message = message.substring(1, message.length() - 1);
        }
        if (message.startsWith("S")) {
            message = message.substring(1);
            String[] messages = message.split(":");
            int x, y, direction, id;
            String[] cordinates = messages[1].split(",");
            id = Integer.parseInt(messages[0].substring(1));
            x = Integer.parseInt(cordinates[0]);
            y = Integer.parseInt(cordinates[1]);
            direction = Integer.parseInt(messages[2]);
            map.setClientID(id);

            //System.out.println("id :" + id + "  x :" + x + " y :" + y + " direction :" + direction);
            Tank myTank = new Tank(false, 0, 0, direction, id, x, y);
            ArrayList<Tank> tanks = map.getTanks();
            tanks.add(myTank);
            map.setTanks(tanks);
        }
    }


    private void processInitializationMessage(String message) {
        message = message.substring(1, message.length() - 1);
        String[] messages = message.split(":");
        String brick_coordinates, stone_coordinates, water_coordinates;

        int count = 1;


        int id = Integer.parseInt(messages[count].substring(1)); //get player number from string
        count++;
        map.setClientID(id);
       // System.out.println("client id :" + id);
        brick_coordinates = messages[count];
        count++;
        stone_coordinates = messages[count];
        count++;
        water_coordinates = messages[count];
        count++;
        placeBricks(brick_coordinates);
        placeStone(stone_coordinates);
        placeWater(water_coordinates);
        //map.Joined = true;
        //joinCheck = true;

    }


    private void placeCoinPile(String message) {

        if (message.endsWith("#")) {
            message = message.substring(0, message.length() - 1);
        }
        String[] messages = message.split(":");
        Coin_Pile coinPile = new Coin_Pile();

        coinPile.setX(Integer.parseInt(String.valueOf(messages[1].charAt(0))));//get x coordinates of points
        coinPile.setY(Integer.parseInt(String.valueOf(messages[1].charAt(2))));//get y coordinates of points


        coinPile.setLifeTime(Integer.parseInt(messages[2]));
        coinPile.setValue(Integer.parseInt(messages[3]));
        coinPile.setStartTime(System.currentTimeMillis());

        ArrayList<Coin_Pile> coinPiles = map.getCoinPiles();
        coinPiles.add(coinPile);
        map.setCoinPiles(coinPiles);

    }


    private void placeLifePack(String message) {
        if (message.endsWith("#")) {
            message = message.substring(0, message.length() - 1);
        }
        String[] messages = message.split(":");
        Life_Pack lifeFack = new Life_Pack();

        lifeFack.setX(Integer.parseInt(String.valueOf(messages[1].charAt(0))));//get x coordinates of points
        lifeFack.setY(Integer.parseInt(String.valueOf(messages[1].charAt(2))));//get y coordinates of points
        lifeFack.setLifeTime(Integer.parseInt(messages[2]));
        lifeFack.setStartTime(System.currentTimeMillis());

        ArrayList<Life_Pack> lifeFacks = map.getLifePacks();
        lifeFacks.add(lifeFack);
        map.setLifePacks(lifeFacks);

    }


    private void processGameUpdateMessage(String message) {
        message=message.substring(0,message.length()-2);
        String[] messages = message.split(":");
        ArrayList<String> players = new ArrayList();

        for (int i = 0; i < messages.length; i++) {
            if (messages[i].startsWith("P")) {
                players.add(messages[i]);
            }
        }
        placeTank(players);
        placeBricks(messages[messages.length - 1]);

    }


    private void placeBricks(String message) {
        String[] coordinates = message.split(";");
        ArrayList<Brick> bricks = new ArrayList<>();
        //System.out.println("Bricks co-ordinates\n---------------------------------------");
        for (int i = 0; i < coordinates.length; i++) {
            Brick brick = new Brick();
          //  System.out.println("x : " + coordinates[i].charAt(0) + ", y : " + coordinates[i].charAt(2));
            brick.setX(Integer.parseInt(String.valueOf(coordinates[i].charAt(0))));//get x coordinates of points
            brick.setY(Integer.parseInt(String.valueOf(coordinates[i].charAt(2))));//get y coordinates of points
            int health=0;
            if (coordinates[i].length() > 4) {
               health= Integer.parseInt(coordinates[i].substring(4));
            }
            brick.setHealth(health);
            if(health!=4) {
                bricks.add(brick);
            }
        }
        map.setBricks(bricks);


    }

    private void placeStone(String message) {
        String[] coordinates = message.split(";");
        ArrayList<Stone> stones = new ArrayList<>();

        for (int i = 0; i < coordinates.length; i++) {
            Stone stone = new Stone();
           // System.out.println("x : " + coordinates[i].charAt(0) + ", y : " + coordinates[i].charAt(2));
            stone.setX(Integer.parseInt(String.valueOf(coordinates[i].charAt(0))));//get x coordinates of points
            stone.setY(Integer.parseInt(String.valueOf(coordinates[i].charAt(2))));//get y coordinates of points

            stones.add(stone);
        }
        map.setStones(stones);
    }


    private void placeWater(String message) {
        String[] coordinates = message.split(";");
        ArrayList<Water> waters = new ArrayList<>();

        for (int i = 0; i < coordinates.length; i++) {
            Water water = new Water();
           // System.out.println("x : " + coordinates[i].charAt(0) + ", y : " + coordinates[i].charAt(2));
            water.setX(Integer.parseInt(String.valueOf(coordinates[i].charAt(0))));//get x coordinates of points
            water.setY(Integer.parseInt(String.valueOf(coordinates[i].charAt(2))));//get y coordinates of points

            waters.add(water);
        }
        map.setWaters(waters);
    }


    private void placeTank(ArrayList<String> players) {

        ArrayList<Tank> tanks = new ArrayList();

        for (String message : players) {

            String[] details = message.split(";");

            Tank tank = new Tank();
            int playerID = Integer.parseInt(details[0].substring(1));
            int x = Integer.parseInt(String.valueOf(details[1].charAt(0)));
            int y = Integer.parseInt(String.valueOf(details[1].charAt(2)));
            int direction = Integer.parseInt(details[2]);
            int health = Integer.parseInt(details[4]);
            int coins = Integer.parseInt(details[5]);
            int points = Integer.parseInt(details[6]);

            boolean is_shot = false;
            if (Integer.parseInt(details[3]) == 1) {
                is_shot = true;
            }


            tank.setHealth(health);
            tank.setCoins(coins);
            tank.setPoints(points);
            tank.setId(playerID);
            tank.setX(x);
            tank.setY(y);
            tank.setDirection(direction);
            tank.setShot(is_shot);
            tanks.add(tank);


            //removing coin piles or life pack when tank get that coin or life pack
            ArrayList<Coin_Pile> coinPiles = map.getCoinPiles();
            ArrayList<Coin_Pile> tem_coinPiles=new ArrayList();
            for(Coin_Pile coin_pile:coinPiles){
                if(!(coin_pile.getX()==x && coin_pile.getY()==y))
                {
                    tem_coinPiles.add(coin_pile);
                }

            }
            map.setCoinPiles(tem_coinPiles);

            ArrayList<Life_Pack> life_packs = map.getLifePacks();
            ArrayList<Life_Pack> tem_life_packs=new ArrayList();
            for(Life_Pack life_pack:life_packs){
                if(!(life_pack.getX()==x && life_pack.getY()==y))
                {
                    tem_life_packs.add(life_pack);
                }

            }
            map.setLifePacks(tem_life_packs);

        }
        map.setTanks(tanks);
    }


}
