package AI;

import Parser.Map;
import game_objects.Coin_Pile;
import game_objects.Stone;
import game_objects.Water;

import java.lang.reflect.Array;

/**
 * Created by Nuwantha on 12/10/2015.
 */
public class PathFinder {


   static public int pathGenerator(Map map){
       if(map.getTanks().size()>0){
            int direction = map.getTanks().get(0).getDirection();
            int x = map.getTanks().get(0).getX();
            int y = map.getTanks().get(0).getY();


            int newX=20;
            int newY=20;
            boolean move=false;
            for (Coin_Pile coin_pile : map.getCoinPiles()) {
                int xCoordinate = coin_pile.getX() - x;
                int yCoordinate = coin_pile.getY() - y;
                if(Math.abs(xCoordinate)<Math.abs(newX-x) && Math.abs(yCoordinate)<Math.abs(newY-y)){
                    newX=coin_pile.getX();
                    newY=coin_pile.getY();
                    move=true;
                }

            }
          // System.out.println(newX+" : "+newY);
        boolean next=false;
       if(newX-x>0 && move){
           for (Water water : map.getWaters()) {
               if(water.getX()==x+1 && water.getY()==y){
                   System.out.println("water coordinate = :"+water.getX());
                   next=true;
                   break;
               }
           }
           if(!next ) {
               for (Stone stone : map.getStones()) {
                   if(stone.getX()==x+1 && stone.getY()==y){

                       next=true;
                       break;
                   }
               }
               if(!next ) {
                   System.out.println("DOWN DOWN");
                   return 2;
               }
           }
       }

       if(newX-x<0 && move)
           {
           return  1;
       }
       if(newY-y>0&& move ){
           return 3;
       }

       if(newY-y<0 && move){
           return 4;
       }

       }

        return 5;


    }
}
