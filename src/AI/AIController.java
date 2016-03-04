package AI;

import Parser.Map;
import game_objects.*;

/**
 * Created by Nuwantha on 12/10/2015.
 */
public class AIController {


    static Tank mytank=null;
    static int [][] memory =new int[10][10];
    static Map map=null;
    public static int pathGenerator(Map map){

        AIController.map =map;
        //choose my tank
        for (Tank tank : map.getTanks()) {
               if(tank.getId()==map.getClientID()){
                   AIController.mytank=tank;
                   break;
               }
        }

        if(mytank !=null){

            //get my tank coordinates
            int direction = mytank.getDirection();
            int x = mytank.getX();
            int y = mytank.getY();


            //use to select position of target

            //mean selected target coordinates
            int newX=100;
            int newY=100;
            boolean move=false;

            //creating a 2d with bariyers.
            // water 1 brick 2 stone 2
            AIController.memory =Common.mapConvertorToArray(map);

            //check whether other tank are in same x axis or y axis with me amd no bariyers between us
            int distroyTank = Mode.distroyTank(mytank,memory,map);

            if(distroyTank==0) {
                //if no tank in x axis or y axis
                //get suitable coin for target
                for (Coin_Pile coin_pile : map.getCoinPiles()) {
                       int xCoordinate = coin_pile.getX() - x;
                       int yCoordinate = coin_pile.getY() - y;
                       if (Math.abs(xCoordinate) + Math.abs(yCoordinate) < Math.abs(newX - x) + Math.abs(newY - y)) {
                           newX = coin_pile.getX();
                           newY = coin_pile.getY();
                           move = true;
                       }
                }

                // if no coin pile in current of health less than 100 try to get nearlest health pack
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
                MoveChooser moveChooser=new MoveChooser(AIController.map,AIController.mytank);
                return moveChooser.shortestPathCalculator(x, y, newX, newY, 1);

            }else {
                   return distroyTank;
            }

        }
       return 0;
    }

}
