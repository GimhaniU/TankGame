package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

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
                if (i == 0 || j == 0) {
                    childLabel.setBackground(Color.BLUE);//water
                }else if(i==1) {
                    childLabel.setBackground(Color.DARK_GRAY);//stone
                }else if(i==3) {
                    childLabel.setIcon(new ImageIcon("src/images/brick.jpg")); //brick
                }else if(i==5) {
                    childLabel.setIcon(new ImageIcon("src/images/coinpile.png"));
                }else if(i==7){
                    childLabel.setIcon(new ImageIcon("src/images/lifepack.png"));
                } else {

                    childLabel.setBackground(Color.yellow);
                }
                labelPanel.add(childLabel);
                labelarray[i][j] = childLabel;

            }
        }
        setSize(600, 600);
        setContentPane(labelPanel);
        setVisible(true);

    }

}
