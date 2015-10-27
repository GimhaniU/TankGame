package connection;

import GUI.WarGui;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Gimhani on 10/26/2015.
 */
public class MessageWriter extends Thread {
    private  WarGui warGui=null;
    
    public MessageWriter(WarGui warGui){
        this.warGui=warGui;
    }

    ArrayList<String> message_list =new ArrayList();
    String message;
    Connection conn;


    public void write(KeyEvent e){
        conn=Connection.getInstance();
        if(e.getKeyCode()== KeyEvent.VK_UP){
            message=("UP#");
        }else if(e.getKeyCode()==KeyEvent.VK_DOWN){
            message=("DOWN#");
        }else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            message=("RIGHT#");
        }else if(e.getKeyCode()==KeyEvent.VK_LEFT){
            message=("LEFT#");
        }else if(e.getKeyCode()==KeyEvent.VK_SPACE){
            message=("SHOOT#");
        }
        conn.sendMessage(message);
        if(message_list!=null) {
            message_list.add(message);
        }

    }

    @Override
    public void run() {

        JPanel panel = warGui.getlabelPanel();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                write(e);
            }
        });



    }
}
