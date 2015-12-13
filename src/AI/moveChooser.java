package AI;

import Parser.Map;
import game_objects.Brick;
import game_objects.Stone;
import game_objects.Tank;
import game_objects.Water;

/**
 * Created by Nuwantha on 12/12/2015.
 */
public class moveChooser {

    int [][] memory2D=new int[10][10];
    Map map;
    Tank myTank;

    moveChooser(Map map, Tank mytank){
         memory2D=new int[10][10];
         this.map=map;
            this.myTank=mytank;
        mapConvertorToArray();
    }

    int  times=20;
    public synchronized int shortestpathCalculator(int x,int y,int x1,int y1,int numberOfTimes) {
        if (x >= 0 && x < 10 && y >= 0 && y < 10 && x1 >= 0 && x1 < 10 && y1 >= 0 && y1 < 10 && numberOfTimes < times && ((memory2D[x][y]==0) || memory2D[x][y]==3) ) {
            if (x == x1 && y == y1){
                times = numberOfTimes;
                return numberOfTimes;
            }
            int value1 = shortestpathCalculator(x + 1, y, x1, y1, numberOfTimes + 1);

            int value2 = shortestpathCalculator(x, y + 1, x1, y1, numberOfTimes + 1);

            int value3 = shortestpathCalculator(x - 1, y, x1, y1, numberOfTimes + 1);


            int value4 = shortestpathCalculator(x, y - 1, x1, y1, numberOfTimes + 1);

            if (numberOfTimes == 1) {
                if (value1 < value2 && value1 < value3 && value1 < value4) {
                    if(memory2D[x+1][y]==3 &&myTank.getDirection()==1){
                        return 5;
                    }
                    return 3;
                } else if (value2 < value1 && value2 < value3 && value2 < value4) {
                    if(memory2D[x][y+1]==3 && myTank.getDirection()==2){
                        return 5;
                    }
                    return 2;
                } else if (value3 < value2 && value3 < value1 && value3 < value4) {
                    if(memory2D[x-1][y]==3 && myTank.getDirection()==3){
                        return 5;
                    }
                    return 4;
                } else if (value4 < value2 && value4 < value3 && value4 < value1) {
                    if(memory2D[x][y-1]==3 && myTank.getDirection()==0){
                        return 5;
                    }
                    return 1;
                }
            } else {
                int min = value1;
                if (min > value2) {
                    min = value2;
                }
                if (min > value3) {
                    min = value3;
                }
                if (min > value4) {
                    min = value4;
                }

                return min;
            }
        }
        return 20;

    }


    public  void mapConvertorToArray(){

        for (Water water : map.getWaters()) {
            memory2D[water.getX()][water.getY()]=1;
        }
        for (Stone stone : map.getStones()) {
            memory2D[stone.getX()][stone.getY()]=2;
        }
        for (Brick brick : map.getBricks()) {
            memory2D[brick.getX()][brick.getY()]=3;
        }
    }

}
