package AI;

import Parser.Map;
import game_objects.Brick;
import game_objects.Coin_Pile;
import game_objects.Stone;
import game_objects.Water;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static java.util.Collections.*;

/**
 * Created by Nuwantha on 12/10/2015.
 */
public class PathFinder {

   static Map mainMap;
   static public int pathGenerator(Map map){
       mainMap=map;
       if(map.getTanks().size()>0){
            int direction = map.getTanks().get(0).getDirection();
            int x = map.getTanks().get(0).getX();
            int y = map.getTanks().get(0).getY();


            int newX=100;
            int newY=100;
            boolean move=false;
            for (Coin_Pile coin_pile : map.getCoinPiles()) {
                int xCoordinate = coin_pile.getX() - x;
                int yCoordinate = coin_pile.getY() - y;
                if(Math.abs(xCoordinate)+ Math.abs(yCoordinate)<Math.abs(newX-x)+Math.abs(newY-y)){
                    newX=coin_pile.getX();
                    newY=coin_pile.getY();
                    move=true;
                }
            }
           moveChooser moveChooser = new moveChooser(mainMap);
           int b=moveChooser.shortestpathCalculator(x,y,newX,newY,1);
           System.out.println( "return value is  : " + b);
           return b;


           /**

           System.out.println("newX : "+newX+"  newY :"+newY );
           if((newY-y)>0 && move){
               boolean next=false;
               for (Water water : map.getWaters()) {
                   if(water.getX()==x && water.getY()==y+1){
                       next=true;
                       break;
                   }
               }
               if(!next ) {
                   for (Stone stone : map.getStones()) {
                       if(stone.getX()==x && stone.getY()==y+1){
                           next=true;
                           break;
                       }
                   }
                   if(!next ){
                       for (Brick brick : map.getBricks()) {
                           if(brick.getX()==x && brick.getY()==y+1){
                               next=true;
                               break;
                           }
                       }
                       if(!next){
                           return 2;
                       }
                   }
               }
           }

            if(newY-y<0 && move) {
                int checkNeibours = checkNeibours(x , y-1);
                if(checkNeibours==1) {
                    return 1;
                }
            }
            if(newX-x>0&& move ){
                int checkNeibours = checkNeibours(x + 1, y);
                if(checkNeibours==1) {
                    return 3;
                }
            }

            if(newX-x<0 && move){
                int checkNeibours = checkNeibours(x - 1, y);
                if(checkNeibours==1) {
                    return 4;
                }


            }
            **/


       }

       return 0;


    }

    public static int checkNeibours(int x,int y){
        boolean next=false;
        for (Water water : mainMap.getWaters()) {
            System.out.println("water coordinate = :"+water.getX() + ":"+y+1);
            if(water.getX()==x && water.getY()==y){
                next=true;
                break;
            }
        }
        if(!next ) {
            for (Stone stone : mainMap.getStones()) {
                if(stone.getX()==x && stone.getY()==y){
                    next=true;
                    break;
                }
            } if(!next ){
                for (Brick brick : mainMap.getBricks()) {
                    if(brick.getX()==x && brick.getY()==y+1){
                        next=true;
                        break;
                    }
                }
                if(!next){

                    return 1;

                }
            }
        }
        return 0;
    }

  // ArrayList<Integer> list= new ArrayList();

}
