package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by Nuwantha on 10/21/2015.
 */
public class TankGame {
    private JLabel label00;
    private JLabel label01;
    private JLabel label02;
    private JLabel label03;
    private JLabel label04;
    private JLabel label05;
    private JPanel labelPanel;
    private JLabel label06;
    private JLabel label07;
    private JLabel label08;
    private JLabel label09;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;
    private JLabel label18;
    private JLabel label19;
    private JLabel label20;
    private JLabel label21;
    private JLabel label22;
    private JLabel label23;
    private JLabel label24;
    private JLabel label25;
    private JLabel label26;
    private JLabel label27;
    private JLabel label28;
    private JLabel label29;
    private JLabel label30;
    private JLabel label31;
    private JLabel label32;
    private JLabel label33;
    private JLabel label34;
    private JLabel label35;
    private JLabel label36;
    private JLabel label37;
    private JLabel label38;
    private JLabel label39;
    private JLabel label40;
    private JLabel label41;
    private JLabel label42;
    private JLabel label43;
    private JLabel label44;
    private JLabel label45;
    private JLabel label46;
    private JLabel label47;
    private JLabel label48;
    private JLabel label49;
    private JLabel label50;
    private JLabel label51;
    private JLabel label52;
    private JLabel label53;
    private JLabel label54;
    private JLabel label55;
    private JLabel label56;
    private JLabel label57;
    private JLabel label58;
    private JLabel label59;
    private JLabel label60;
    private JLabel label61;
    private JLabel label62;
    private JLabel label63;
    private JLabel label64;
    private JLabel label65;
    private JLabel label66;
    private JLabel label67;
    private JLabel label68;
    private JLabel label69;
    private JLabel label70;
    private JLabel label71;
    private JLabel label72;
    private JLabel label73;
    private JLabel label74;
    private JLabel label75;
    private JLabel label76;
    private JLabel label77;
    private JLabel label78;
    private JLabel label79;
    private JLabel label80;
    private JLabel label81;
    private JLabel label82;
    private JLabel label83;
    private JLabel label84;
    private JLabel label85;
    private JLabel label86;
    private JLabel label87;
    private JLabel label88;
    private JLabel label89;
    private JLabel label90;
    private JLabel label91;
    private JLabel label92;
    private JLabel label93;
    private JLabel label94;
    private JLabel label95;
    private JLabel label96;
    private JLabel label97;
    private JLabel label98;
    private JLabel label99;
    private JLabel[][] labelarray;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public TankGame() {
        labelPanel.setLayout(new GridLayout(10, 10));
        labelarray = new JLabel[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JLabel childLabel = new JLabel("");
                childLabel.setOpaque(true);
                childLabel.setBorder(new LineBorder(Color.black));
                if (i == 0 || j == 0) {
                    childLabel.setBackground(Color.GREEN);

                } else {

                    childLabel.setBackground(Color.yellow);
                }
                labelPanel.add(childLabel);
                labelarray[i][j] = childLabel;

            }
        }
    }
}
