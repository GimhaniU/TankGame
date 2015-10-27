package GUI;

import Parser.Map;
import game_objects.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nuwantha on 10/21/2015.
 */
public class TankGrid extends JFrame{
    JLabel[][] labelarray;
    private JPanel labelPanel;

    public TankGrid() {
        super();
        labelPanel.setLayout(new GridLayout(10, 10));
        labelarray = new JLabel[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JLabel childLabel = new JLabel(i+" "+j);
                childLabel.setOpaque(true);
                childLabel.setBorder(new LineBorder(Color.black));


                    childLabel.setBackground(Color.lightGray);

                labelPanel.add(childLabel);
                labelarray[i][j] = childLabel;

            }
        }
        setSize(1200,600);
        setContentPane(labelPanel);
        setVisible(true);

    }
    public void updateGrid(Map map){
        for(JLabel[] subarray:labelarray) {
            for(JLabel label:subarray) {
                label.setIcon(null);
                label.setText("");
            }
        }

        //add bricks
        ArrayList<Brick> bricks = map.getBricks();
        for(Brick brick:bricks){
            draw(brick);
        }
        //add water
        ArrayList<Water> waters = map.getWaters();
        for(Water water:waters){
            draw(water);
        }
        //add stone walls
        ArrayList<Stone> stones = map.getStones();
        for(Stone stone:stones){
            draw(stone);
        }

        ArrayList<Tank> tanks = map.getTanks();
        for(Tank tank:tanks){
            draw(tank);
        }

        ArrayList<Coin_Pile> coin_piles = map.getCoinPiles();
        for(Coin_Pile coin_pile:coin_piles){
            draw(coin_pile);
        }

        ArrayList<Life_Pack> life_packs = map.getLifePacks();
        for(Life_Pack life_pack:life_packs){
            draw(life_pack);
        }

    }


    private void draw(Entity entity) {
        //System.out.println(entity.getY()+" "+entity.getX());
        JLabel childLabel=labelarray[entity.getY()][entity.getX()];
        if (entity.getEnType()== Entity_Type.Water) {
            childLabel.setIcon(new ImageIcon("src/images/water.jpg"));//water
        }else if(entity.getEnType()== Entity_Type.Stone) {
            childLabel.setIcon(new ImageIcon("src/images/stone.jpg"));//stone
        }else if(entity.getEnType()== Entity_Type.Brick) {
            childLabel.setIcon(new ImageIcon("src/images/brick.jpg")); //brick
            childLabel.setText(((Brick)entity).getHealth()+"%");
        }else if(entity.getEnType()== Entity_Type.CoinPile) {
            childLabel.setIcon(new ImageIcon("src/images/coinpile.png"));
        }else if(entity.getEnType()== Entity_Type.Lifepack){
            childLabel.setIcon(new ImageIcon("src/images/lifepack.png"));
        } else if(entity.getEnType()== Entity_Type.Tank /* && entity.getId()==myone*/){
            Tank tank=(Tank)entity;
            //setting directed tank
            if(((Tank)entity).getDirection()==1) {
                childLabel.setIcon(new ImageIcon("src/images/mytank-1.png"));
            }else if(((Tank)entity).getDirection()==2) {
                childLabel.setIcon(new ImageIcon("src/images/mytank-2.png"));
            }else if(((Tank)entity).getDirection()==3) {
                childLabel.setIcon(new ImageIcon("src/images/mytank-3.png"));
            }else {
                childLabel.setIcon(new ImageIcon("src/images/mytank-0.png"));
            }
            childLabel.setText("h : " + ((Tank) entity).getHealth() + " p :" + ((Tank) entity).getCoins());
        } else {

            childLabel.setBackground(Color.lightGray);
        }

    }

}
