package AI;

import Parser.Map;
import game_objects.Tank;

/**
 * Created by Gimhani on 12/15/2015.
 */
public class Mode {

    public static int distroyTank(Tank mytank,int [][]memory,Map map){
        if(mytank!=null) {

            //get mytank coordinates
            int xMy = mytank.getX();
            int yMy = mytank.getY();
            int direction = mytank.getDirection();


            //check all tanks
            l1:for (Tank tank : map.getTanks()) {
                //to identify enemy tank and who has more than 0 health
                if (tank.getId() != map.getClientID() && tank.getHealth()>0) {

                    int x = tank.getX();
                    int y = tank.getY();

                    //if enemy situated same x axis with me and right side
                    if(xMy<x && y==yMy){
                        for(int i=xMy+1;i<x;i++){
                            if(memory[i][yMy]!=0){
                                continue l1;
                            }
                        }
                        if(direction==1){
                            return 5;
                        }else{
                            return 3;
                        }
                    }
                    //if enemy situated same x axis with me and left side

                    else if(xMy>x && y==yMy){

                        for(int i=x+1;i<xMy;i++){
                            if(memory[i][yMy]!=0){
                                continue l1;
                            }
                        }

                        if(direction==3){
                            return 5;
                        }else{
                            return 4;
                        }

                       ////if enemy situated same y axis with me and up to me

                    } else if(yMy<y && x==xMy){
                        for(int i=yMy+1;i<y;i++){
                            if(memory[xMy][i]!=0){
                                continue l1;
                            }
                        }

                        if(direction==2){
                            return 5;
                        }else{
                            return 2;
                        }

                        //if enemy situated same y axis with me and down to me

                    }else if(yMy>y && x==xMy){
                        for(int i=y+1;i<yMy;i++){
                            if(memory[xMy][i]!=0){
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
