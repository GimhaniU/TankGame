package Parser;

import Parser.Map;
import com.sun.org.apache.xpath.internal.SourceTree;
import game_objects.Brick;
import game_objects.Tank;

import java.util.ArrayList;

/**
 * Created by Gimhani on 10/21/2015.
 */
public class Message_Decorder {

    private Map map=null;

    public Message_Decorder(Map map) {
        this.map=map;

    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map process(String message) {
        if (message.length() > 1) {
            char firstChar= message.charAt(0);
            System.out.println(firstChar);
            switch (firstChar) {
                case 'S':
                   // processStartMessage(message);
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
        return  map;
    }

    private void processStartMessage(String message) {
        if (message.endsWith("#"))
        {
            message = message.substring(1, message.length() - 1);
        }
        if (message.startsWith("S"))
        {
            message = message.substring(1);
            String[] messages = message.split(":");
            int x, y, direction, id;
            String[] cordinates=messages[1].split(",");
            id = Integer.parseInt(messages[0].substring(1));
            x = Integer.parseInt(cordinates[0]);
            y = Integer.parseInt(cordinates[1]);
            direction = Integer.parseInt(messages[2]);
            map.setClientID(id);
            System.out.println("id :"+id+"  x :"+x+" y :"+y +" direction :"+direction);
            Tank myTank = new Tank(false,0,0,direction,id,x,y);
            ArrayList<Tank> tanks = map.getTanks();
            tanks.add(myTank);
            map.setTanks(tanks);
        }
    }


    private void processInitializationMessage(String message) {
        message = message.substring(0, message.length() - 1);
        String[] messages = message.split(":");
        String brick_coordinates, stone_coordinates, water_coordinates;

        int count = 1;

        if (true)
        {

            int id = Integer.parseInt(messages[count].substring(1)); //get player number from string
            count++;
            map.setClientID(id);
            System.out.println("client id :" + id);
            brick_coordinates = messages[count];
            count++;
            stone_coordinates = messages[count];
            count++;
            water_coordinates = messages[count];
            count++;
            placeBricks(brick_coordinates);
            //placeStone(stoneCoords);
            //placeWater(waterCoords);
            //map.Joined = true;
            //joinCheck = true;
        }

    }


    private void processGameUpdateMessage(String message) {

    }

    private void placeLifePack(String message) {

    }

    private void placeCoinPile(String message) {

    }

    private void placeBricks(String message){
        String[] coordinates = message.split(";");
        ArrayList<Brick> bricks = new ArrayList<>();
        System.out.println("Bricks co-ordinates\n---------------------------------------");
        for (int i = 0; i <coordinates.length ; i++) {
            Brick brick = new Brick();
            System.out.println("x : "+coordinates[i].charAt(0)+", y : "+coordinates[i].charAt(2));
            brick.setX(Integer.parseInt(String.valueOf(coordinates[i].charAt(0))));//get x coordinates of points
            brick.setY(Integer.parseInt(String.valueOf(coordinates[i].charAt(2))));//get y coordinates of points
            brick.setHealth(100);
            bricks.add(brick);
        }
        map.setBricks(bricks);


    }


}
