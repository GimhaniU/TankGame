package connection;

import javax.swing.*;
import java.util.Scanner;

/**
 * Created by Gimhani on 10/26/2015.
 */
public class MessageWriter extends Thread {


    Connection conn;
    public void write(String message){
        conn=Connection.getInstance();
        conn.sendMessage(message);

    }

    @Override
    public void run() {

        while(true){
            String message = JOptionPane.showInputDialog("Give Your Command : ");
            write(message);
        }

    }
}
