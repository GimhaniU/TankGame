package connection;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Gimhani on 10/26/2015.
 */
public class MessageWriter extends Thread {
    ArrayList<String> message_list;
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
        message_list.add(message);

    }

    @Override
    public void run() {

       /* while(true){
            String message = JOptionPane.showInputDialog("Give Your Command : ");
            write(message);
        }*/

    }
}
