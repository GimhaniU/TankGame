package AI;

import Parser.Map;
import game_objects.Brick;
import game_objects.Stone;
import game_objects.Water;

/**
 * Created by Gimhani on 12/15/2015.
 */
public class Common {
    public static int[][] mapConvertorToArray(Map map){
         int [][] memory=new int[10][10];

        for (Water water : map.getWaters()) {
            memory[water.getX()][water.getY()]=1;
        }
        for (Stone stone : map.getStones()) {
            memory[stone.getX()][stone.getY()]=2;
        }
        for (Brick brick : map.getBricks()) {
            memory[brick.getX()][brick.getY()]=3;
        }

        return memory;
    }



}
