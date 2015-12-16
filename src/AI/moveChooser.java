package AI;

import Parser.Map;
import game_objects.Brick;
import game_objects.Stone;
import game_objects.Tank;
import game_objects.Water;

public class moveChooser {

     int [][] memory =new int[10][10];
     Map map=null;
     Tank myTank=null;

     int  times=20;

    //update move chooser variable
    public moveChooser(Map updateMap, Tank updateMyTank){
        memory =new int[10][10];
        map=updateMap;
        myTank=updateMyTank;
        //update the memory array.
        memory=Common.mapConvertorToArray(updateMap);
    }

    //recursive function find the movement for (left,right,up or down) achieve a given target

    public synchronized  int shortestPathCalculator(int x, int y, int x1, int y1, int numberOfTimes) {
        //x,y my tank current position
        //x1,y1 target position
        //check condition 0=< x,y,x1,y1<10
        //memory [x][y] ==0 , check whether there is water or stone in the next move
        if (x >= 0 && x < 10 && y >= 0 && y < 10 && x1 >= 0 && x1 < 10 && y1 >= 0 && y1 < 10 && numberOfTimes < times && ((memory[x][y]==0) || memory[x][y]==3) ) {

            //if tank move to target
            if (x == x1 && y == y1){
                //to reduce unnecessary recursive calls
                times = numberOfTimes;
                return numberOfTimes;
            }
            //if tank not move to target get next movement
            //move right
            int value1 =shortestPathCalculator(x + 1, y, x1, y1, numberOfTimes + 1);
            //move down
            int value2 = shortestPathCalculator(x, y + 1, x1, y1, numberOfTimes + 1);
            //move left
            int value3 = shortestPathCalculator(x - 1, y, x1, y1, numberOfTimes + 1);
            //move up
            int value4 = shortestPathCalculator(x, y - 1, x1, y1, numberOfTimes + 1);

            // find min value.that mean find shortest path if we get this movement



                //first call of the recursive tree
            if (numberOfTimes == 1) {


                if (value1 < value2 && value1 < value3 && value1 < value4) {
                    if(memory[x+1][y]==3 && myTank.getDirection()==1){
                        return 5;
                    }
                    return 3;
                } else if (value2 < value1 && value2 < value3 && value2 < value4) {
                    if(memory[x][y+1]==3 && myTank.getDirection()==2){
                        return 5;
                    }
                    return 2;
                } else if (value3 < value2 && value3 < value1 && value3 < value4) {
                    if(memory[x-1][y]==3 && myTank.getDirection()==3){
                        return 5;
                    }
                    return 4;
                } else if (value4 < value2 && value4 < value3 && value4 < value1) {
                    if(memory[x][y-1]==3 && myTank.getDirection()==0){
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




}




    /*
            int min = value1;
            if (min > value2) {
                min = value2;
            }
            if (min > value3) {
                min = value3;
            }
            if (min > value4) {
                min = value4;
            }    }*/
//shortest path you get if u move right hand side

               /*
                  if(min==value1 && x+1<10){
                    //if brick in shoot
                    if(memory[x+1][y]==3 && myTank.getDirection()==1){
                        return 5;
                    }
                    return 3;


                }
                //shortest path you get if u move down hand side

                else if(min==value2 && y+1<10){
                    if(memory[x][y+1]==3 && myTank.getDirection()==2){
                        return 5;
                    }
                    return 2;

                }//shortest path you get if u move left hand side

                else if(min==value3 && x-1>0) {
                    if(memory[x-1][y]==3 && myTank.getDirection()==3){
                        return 5;
                    }
                    return 4;
                }else if((min==value4 && y-1>0))
                //shortest path you get if u move up hand side

                {
                    if(memory[x][y-1]==3 && myTank.getDirection()==0){
                        return 5;
                    }
                    return 1;
                }else{
                    return 0;
                }



                */
