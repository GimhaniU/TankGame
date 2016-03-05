package connection;

import AI.Command;
import AI.AIController;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Gimhani on 10/26/2015.
 */
public class MessageWriter extends Thread {
    // private  WarGui warGui=null;
    ArrayList<String> message_list = new ArrayList();
    String message;
    Connection conn;
    MessageReader messageReader;

    public MessageWriter(MessageReader messageReader) {
        this.messageReader = messageReader;
    }

    public void write(Command command) {

        conn = Connection.getInstance();
        if (command == command.UP) {
            message = ("UP#");
        } else if (command == command.DOWN) {
            message = ("DOWN#");
        } else if (command == command.RIGHT) {

            message = ("RIGHT#");
        } else if (command == command.LEFT) {
            message = ("LEFT#");
        } else if (command == command.SHOOT) {
            message = ("SHOOT#");
        }
        conn.sendMessage(message);
        if (message_list != null) {
            message_list.add(message);
        }

    }


    public void run() {
        /*
        JPanel panel = warGui.getlabelPanel();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                write(e);
            }
        });

        */
        while (true) {

            int value = AIController.pathGenerator(messageReader.getMap());
            switch (value) {
                case 1:
                    write(Command.UP);
                    break;
                case 2:
                    write(Command.DOWN);
                    break;
                case 3:

                    write(Command.RIGHT);
                    break;
                case 4:
                    write(Command.LEFT);
                    break;
                case 5:
                    write(Command.SHOOT);
                    break;
            }

            try {
                sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
