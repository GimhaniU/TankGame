package Parser;

import SlickGui.SetUp;
import SlickGui.StateGame;
import connection.Connection;
import game_objects.*;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Gimhani on 10/21/2015.
 */
public class Message_Decorder {

    private Map map = null;
    private SetUp grid;

    public Message_Decorder(Map map) {
        this.map = map;

    }

    public Message_Decorder(Map map, SetUp grid) {
        this.map = map;
        this.grid = grid;

    }

    public void setGrid(SetUp grid) {
        this.grid = grid;
    }


    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }


    public Map process(String message) {
        if (message.length() > 2) {
            char firstChar = message.charAt(0);
            char secondChar = message.charAt(1);
            //System.out.println(firstChar);
            switch (secondChar) {
                case ':':
                    switch (firstChar) {
                        case 'S':
                            processStartMessage(message);
                            map.setIs_game_started(true);
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
                    break;
                default:
                    String replyMessage = processReplyMessage(message);
                    /*
                    if(grid!=null) {
                        grid.writeMessage(replyMessage);
                    }
                    */
            }
        }
        return map;
    }

    private String processReplyMessage(String message) {
        if (message.equals("OBSTACLE;25#")) {
            return "Obstacle ahead";
        } else if (message.equals("DEAD#")) {
            map.setIs_me_dead(true);
            return "You are dead";
        } else if (message.equals("INVALID_CELL#")) {
            return "Can't go there";
        } else if (message.equals("GAME_NOT_STARTED_YET#")) {
            return "Wait. game is not started yet";
        } else if (message.equals("CELL_OCCUPIED#")) {
            return "Cell is already occupied";
        } else if (message.equals("TOO_QUICK#")) {
            return "Be slow";
        } else if (message.equals("GAME_HAS_FINISHED#")) {
            map.setIs_game_finished(true);
            Connection con=Connection.getInstance();
            con.closeSocket(map);
            return "Game over";
        } else if (message.equals("GAME_FINISHED#")) {
            return "Game over for you";
        } else if (message.equals("NOT_A_VALID_CONTESTANT#")) {
            return "You are not a player";
        } else if (message.equals("PITFALL#")) {
            return "You fall into water--dead";
        }
        return message;
    }

    private void processStartMessage(String message) {
        //remove # charactor
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

            Tank myTank = new Tank(false, 0, 0, direction, id, x, y);
            ArrayList<Tank> tanks = map.getTanks();
            tanks.add(myTank);
            map.setTanks(tanks);
        }
    }

    // process I:....message
    private void processInitializationMessage(String message) {
        message = message.substring(1, message.length() - 1);
        String[] messages = message.split(":");
        String brick_coordinates, stone_coordinates, water_coordinates;
        int count = 1;
        int id = Integer.parseInt(messages[count].substring(1)); //get player number from string
        count++;
        map.setClientID(id);
        brick_coordinates = messages[count];
        count++;
        stone_coordinates = messages[count];
        count++;
        water_coordinates = messages[count];
        count++;
        placeBricks(brick_coordinates);
        placeStone(stone_coordinates);
        placeWater(water_coordinates);

    }

    //add coin piles to the map
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

    //add life packs to the map
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

    //process G:... message
    private void processGameUpdateMessage(String message) {
        message = message.substring(0, message.length() - 2);
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

    //add bricks to map
    private void placeBricks(String message) {
        String[] coordinates = message.split(";");
        ArrayList<Brick> bricks = new ArrayList<>();
        for (int i = 0; i < coordinates.length; i++) {
            Brick brick = new Brick();
            brick.setX(Integer.parseInt(String.valueOf(coordinates[i].charAt(0))));//get x coordinates of points
            brick.setY(Integer.parseInt(String.valueOf(coordinates[i].charAt(2))));//get y coordinates of points
            int health = 0;
            if (coordinates[i].length() > 4) {
                health = Integer.parseInt(coordinates[i].substring(4));
            }
            brick.setHealth(health);
            if (health != 4) {
                bricks.add(brick);
            }
        }
        map.setBricks(bricks);
    }

    //add stones to map
    private void placeStone(String message) {
        String[] coordinates = message.split(";");
        ArrayList<Stone> stones = new ArrayList<>();

        for (int i = 0; i < coordinates.length; i++) {
            Stone stone = new Stone();
            stone.setX(Integer.parseInt(String.valueOf(coordinates[i].charAt(0))));
            stone.setY(Integer.parseInt(String.valueOf(coordinates[i].charAt(2))));
            stones.add(stone);
        }
        map.setStones(stones);
    }

    // add water to map
    private void placeWater(String message) {
        String[] coordinates = message.split(";");
        ArrayList<Water> waters = new ArrayList<>();

        for (int i = 0; i < coordinates.length; i++) {
            Water water = new Water();
            water.setX(Integer.parseInt(String.valueOf(coordinates[i].charAt(0))));//get x coordinates of points
            water.setY(Integer.parseInt(String.valueOf(coordinates[i].charAt(2))));//get y coordinates of points
            waters.add(water);
        }
        map.setWaters(waters);
    }

    // add tank to the map
    private void placeTank(ArrayList<String> players) {

        ArrayList<Tank> tanks = new ArrayList();

        for (String message : players) {

            String[] details = message.split(";");

            Tank tank = new Tank();
            int playerID = Integer.parseInt(details[0].substring(1));
            String[] coordinades = details[1].split(",");
            int x = Integer.parseInt(coordinades[0]);
            int y = Integer.parseInt(coordinades[1]);
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

            //add a coinpiles when player dead

            boolean deadNow = true;

            if (health == 0) {
                ArrayList<Integer> deadTanks = map.getDeadTanks();
                for (int id : deadTanks) {
                    if (id == playerID) {
                        deadNow = false;
                        break;
                    }
                }
                if (deadNow) {
                    System.err.println("dead now");
                    deadTanks.add(playerID);
                    map.setDeadTanks(deadTanks);
                    Coin_Pile coinPile = new Coin_Pile();

                    coinPile.setX(x);//get x coordinates of points
                    coinPile.setY(y);//get y coordinates of points


                    coinPile.setLifeTime(0);
                    coinPile.setValue(coins);
                    coinPile.setStartTime(System.currentTimeMillis());

                    ArrayList<Coin_Pile> coinPiles = map.getCoinPiles();
                    coinPiles.add(coinPile);
                    map.setCoinPiles(coinPiles);
                }
            }

            //removing coin piles or life pack when tank get that coin or life pack
            if (health != 0) {
                ArrayList<Coin_Pile> coinPiles = map.getCoinPiles();
                ArrayList<Coin_Pile> tem_coinPiles = new ArrayList();
                for (Coin_Pile coin_pile : coinPiles) {
                    if (!(coin_pile.getX() == x && coin_pile.getY() == y)) {
                        tem_coinPiles.add(coin_pile);
                    }

                }
                map.setCoinPiles(tem_coinPiles);

                ArrayList<Life_Pack> life_packs = map.getLifePacks();
                ArrayList<Life_Pack> tem_life_packs = new ArrayList();
                for (Life_Pack life_pack : life_packs) {
                    if (!(life_pack.getX() == x && life_pack.getY() == y)) {
                        tem_life_packs.add(life_pack);
                    }

                }
                map.setLifePacks(tem_life_packs);
            }
        }
        map.setTanks(tanks);
    }


}
