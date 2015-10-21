package GUI;

import Parser.Map;
import game_objects.Brick;
import game_objects.Entity;
import game_objects.Entity_Type;

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
        setSize(600, 600);
        setContentPane(labelPanel);
        setVisible(true);

    }
    public void updateGrid(Map map){
        ArrayList<Brick> bricks = map.getBricks();
        for(Brick brick:bricks){
            draw(brick);
        }
    }

    private void draw(Entity entity) {
        System.out.println(entity.getY()+" "+entity.getX());
        JLabel childLabel=labelarray[entity.getY()][entity.getX()];
        if (entity.getEnType()== Entity_Type.Water) {
            childLabel.setIcon(new ImageIcon("src/images/water.jpg"));//water
        }else if(entity.getEnType()== Entity_Type.Stone) {
            childLabel.setIcon(new ImageIcon("src/images/stone.jpg"));//stone
        }else if(entity.getEnType()== Entity_Type.Brick) {
            childLabel.setIcon(new ImageIcon("src/images/brick.jpg")); //brick
        }else if(entity.getEnType()== Entity_Type.CoinPile) {
            childLabel.setIcon(new ImageIcon("src/images/coinpile.png"));
        }else if(entity.getEnType()== Entity_Type.Lifepack){
            childLabel.setIcon(new ImageIcon("src/images/lifepack.png"));
        } else {

            childLabel.setBackground(Color.lightGray);
        }

    }

}
