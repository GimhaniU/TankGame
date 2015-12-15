package AI;

import Parser.Map;
import game_objects.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static java.util.Collections.*;

/**
 * Created by Nuwantha on 12/10/2015.
 */
public class PathFinder {
    static Tank mytank=null;
    static int [][] memory2D=new int[10][10];

    static Map mainMap;
    public static int pathGenerator(Map map){
        mainMap=map;
        for (Tank tank : map.getTanks()) {
               if(tank.getId()==map.getClientID()){
                   mytank=tank;
                   break;
               }
        }
           if(mytank !=null){

           int direction = mytank.getDirection();
            int x = mytank.getX();
            int y = mytank.getY();


            int newX=100;
            int newY=100;
            boolean move=false;
               mapConvertorToArray();
               int distroyTank = distroyTank();
               if(distroyTank()==0) {

                   for (Coin_Pile coin_pile : map.getCoinPiles()) {
                       int xCoordinate = coin_pile.getX() - x;
                       int yCoordinate = coin_pile.getY() - y;
                       if (Math.abs(xCoordinate) + Math.abs(yCoordinate) < Math.abs(newX - x) + Math.abs(newY - y)) {
                           newX = coin_pile.getX();
                           newY = coin_pile.getY();
                           move = true;
                       }
                   }
                   if (!move || mytank.getHealth() < 100) {
                       for (Life_Pack life_pack : map.getLifePacks()) {
                           int xCoordinate = life_pack.getX() - x;
                           int yCoordinate = life_pack.getY() - y;
                           if (Math.abs(xCoordinate) + Math.abs(yCoordinate) < Math.abs(newX - x) + Math.abs(newY - y)) {
                               newX = life_pack.getX();
                               newY = life_pack.getY();
                               move = true;
                           }

                       }

                   }
                   moveChooser moveChooser = new moveChooser(mainMap, mytank);
                   int b = moveChooser.shortestpathCalculator(x, y, newX, newY, 1);
                   System.out.println("return value is  : " + b);
                   return b;
               }else {
                   return distroyTank;
               }

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
  public static void mapConvertorToArray(){
      memory2D=new int[10][10];

      for (Water water : mainMap.getWaters()) {
          memory2D[water.getX()][water.getY()]=1;
      }
      for (Stone stone : mainMap.getStones()) {
          memory2D[stone.getX()][stone.getY()]=2;
      }
      for (Brick brick : mainMap.getBricks()) {
          memory2D[brick.getX()][brick.getY()]=3;
      }
  }


    public static int distroyTank(){
        if(mytank!=null) {
            int xMy = mytank.getX();
            int yMy = mytank.getY();
            int direction = mytank.getDirection();


            //check all tanks
            l1:for (Tank tank : mainMap.getTanks()) {
                System.out.println(tank.getHealth());
                //to identify enemy tank and who has more than 0 health
                if (tank.getId() != mainMap.getClientID() && tank.getHealth()>0) {

                    int x = tank.getX();
                    int y = tank.getY();

                    if(xMy<x && y==yMy){
                        for(int i=xMy+1;i<x;i++){
                            if(memory2D[i][yMy]!=0){
                                continue l1;
                            }
                        }

                            if(direction==1){
                                return 5;
                            }else{
                                return 3;
                            }

                    }else if(xMy>x &&y==yMy){

                        for(int i=x+1;i<xMy;i++){
                            if(memory2D[i][yMy]!=0){
                                continue l1;
                            }
                        }

                        if(direction==3){
                            return 5;
                        }else{
                            return 4;
                        }
                    } else if(yMy<y && x==xMy){
                        for(int i=yMy+1;i<y;i++){
                            if(memory2D[xMy][i]!=0){
                                continue l1;
                            }
                        }

                        if(direction==2){
                            return 5;
                        }else{
                            return 2;
                        }
                    }else if(yMy>y && x==xMy){
                        for(int i=y+1;i<yMy;i++){
                            if(memory2D[xMy][i]!=0){
                                continue l1;
                            }
                        }
                        if(direction==0){
                            return 5;
                        }else{
                            return 1;
                        }
                    }

                }



            }

            return 0;

        }
        return 0;
    }
}
