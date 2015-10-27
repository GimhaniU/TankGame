/*
 * Created by JFormDesigner on Tue Oct 27 10:46:58 IST 2015
 */

package GUI;

import java.awt.event.*;
import Parser.Map;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;
import connection.MessageWriter;
import game_objects.*;
import sun.plugin2.message.Message;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 * @author Nuwantha
 */
public class WarGui extends JFrame {
    public WarGui() {
        initComponents();

        //setting up 20x20 grid
        labelPanel.setLayout(new GridLayout(10, 10));
        labelarray = new JLabel[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JLabel childLabel = new JLabel(i + " " + j);
                childLabel.setOpaque(true);
                childLabel.setBorder(new LineBorder(Color.black));
                childLabel.setBackground(Color.lightGray);

                labelPanel.add(childLabel);
                labelarray[i][j] = childLabel;

            }
        }
        labelPanel.requestFocus();
        //setting up score board architecture
        DefaultTableModel tableModel= (DefaultTableModel) table1.getModel();
        tableModel.setColumnCount(0);
        tableModel.setRowCount(5);
        tableModel.addColumn("Player");
        tableModel.addColumn("Coins");
        tableModel.addColumn("Points");
        tableModel.addColumn("Health");
        tableModel.setValueAt("P0", 0, 0);
        tableModel.setValueAt("P1", 1, 0);
        tableModel.setValueAt("P2", 2, 0);
        tableModel.setValueAt("P3", 3, 0);
        tableModel.setValueAt("P4", 4, 0);

        setVisible(true);
    }


    private void labelPanelKeyReleased(KeyEvent e) {
     //   MessageWriter writer=new MessageWriter();
      //  writer.write(e);

    }

    public JPanel getlabelPanel(){
        return this.labelPanel;

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Gimhani Uthpala
        title_label = new JLabel();
        labelPanel = new JPanel();
        panel2 = new JPanel();
        scrollPane1 = new JScrollPane();
        textArea1 = new JTextArea();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- title_label ----
        title_label.setText("Tank War-Combat Coders");
        title_label.setHorizontalAlignment(SwingConstants.CENTER);
        title_label.setFont(new Font("Showcard Gothic", Font.BOLD, 28));
        contentPane.add(title_label);
        title_label.setBounds(0, 0, 1150, 65);

        //======== labelPanel ========
        {
            labelPanel.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    labelPanelKeyReleased(e);
                }
            });

            // JFormDesigner evaluation mark
            labelPanel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), labelPanel.getBorder())); labelPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            labelPanel.setLayout(null);
        }
        contentPane.add(labelPanel);
        labelPanel.setBounds(0, 70, 980, 570);

        //======== panel2 ========
        {
            panel2.setLayout(null);

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(textArea1);
            }
            panel2.add(scrollPane1);
            scrollPane1.setBounds(15, 10, 135, 100);

            //======== scrollPane2 ========
            {
                scrollPane2.setViewportView(table1);
            }
            panel2.add(scrollPane2);
            scrollPane2.setBounds(10, 115, 145, 180);
        }
        contentPane.add(panel2);
        panel2.setBounds(985, 80, 165, 560);

        { // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Gimhani Uthpala
    private JLabel title_label;
    private JPanel labelPanel;
    private JPanel panel2;
    private JScrollPane scrollPane1;
    private JTextArea textArea1;
    private JScrollPane scrollPane2;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    JLabel[][] labelarray;

    public void updateGrid(Map map) {
        for (JLabel[] subarray : labelarray) {
            for (JLabel label : subarray) {
                label.setIcon(null);
                label.setText("");
            }
        }

        //add bricks
        ArrayList<Brick> bricks = map.getBricks();
        for (Brick brick : bricks) {
            draw(brick);
        }
        //add water
        ArrayList<Water> waters = map.getWaters();
        for (Water water : waters) {
            draw(water);
        }
        //add stone walls
        ArrayList<Stone> stones = map.getStones();
        for (Stone stone : stones) {
            draw(stone);
        }

        ArrayList<Tank> tanks = map.getTanks();
        for (Tank tank : tanks) {
            draw(tank);
        }

        ArrayList<Coin_Pile> coin_piles = map.getCoinPiles();
        for (Coin_Pile coin_pile : coin_piles) {
            draw(coin_pile);
        }

        ArrayList<Life_Pack> life_packs = map.getLifePacks();
        for (Life_Pack life_pack : life_packs) {
            draw(life_pack);
        }

    }


    private void draw(Entity entity) {
        //System.out.println(entity.getY() + " " + entity.getX());
        JLabel childLabel = labelarray[entity.getY()][entity.getX()];
        if (entity.getEnType() == Entity_Type.Water) {
            childLabel.setIcon(new ImageIcon("src/images/water.jpg"));//water
        } else if (entity.getEnType() == Entity_Type.Stone) {
            childLabel.setIcon(new ImageIcon("src/images/stone.jpg"));//stone
        } else if (entity.getEnType() == Entity_Type.Brick) {
            childLabel.setIcon(new ImageIcon("src/images/brick.jpg")); //brick
            childLabel.setText(((Brick) entity).getHealth() + "%");
        } else if (entity.getEnType() == Entity_Type.CoinPile) {
            childLabel.setIcon(new ImageIcon("src/images/coinpile.png"));
            childLabel.setText("$:"+((Coin_Pile) entity).getValue());
        } else if (entity.getEnType() == Entity_Type.Lifepack) {
            childLabel.setIcon(new ImageIcon("src/images/lifepack.png"));
            childLabel.setText(":" + ((Life_Pack) entity).getValue());
        } else if (entity.getEnType() == Entity_Type.Tank /* && entity.getId()==myone*/) {
            Tank tank = (Tank) entity;
            //setting directed tank
            if (((Tank) entity).getDirection() == 1) {
                childLabel.setIcon(new ImageIcon("src/images/mytank-1.png"));
            } else if (((Tank) entity).getDirection() == 2) {
                childLabel.setIcon(new ImageIcon("src/images/mytank-2.png"));
            } else if (((Tank) entity).getDirection() == 3) {
                childLabel.setIcon(new ImageIcon("src/images/mytank-3.png"));
            } else {
                childLabel.setIcon(new ImageIcon("src/images/mytank-0.png"));
            }
            childLabel.setText("h : " + ((Tank) entity).getHealth() + " p :" + ((Tank) entity).getCoins());
            addDataToTable(tank);
        } else {

            childLabel.setBackground(Color.lightGray);
        }
    }

    private void addDataToTable(Tank tank) {
        int row=tank.getId();
        DefaultTableModel tableModel = (DefaultTableModel) table1.getModel();
        Object[] valueArray=new Object[]{"P"+tank.getId(),tank.getPoints(),tank.getCoins(),tank.getHealth()};
        int column = 0;
        for (Object cellValue: valueArray) {
            tableModel.setValueAt(cellValue, row, column);
            column++;
        }
    }
}
