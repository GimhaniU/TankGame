package connection;

import Parser.Map;
import Parser.Message_Decorder;
import SlickGui.SetUp;
import SlickGui.StateGame;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by Nuwantha on 10/21/2015.
 */
public class MessageReader extends Thread {
    Map map = new Map();
    SetUp grid;
    Connection conn;
    ArrayList<String> messagelist;
    //MessageWriter messageWriter=new MessageWriter();
    public boolean start = false;

    public Map getMap() {
        return map;
    }

    //we keep pointer to map object and grid object
    public MessageReader(SetUp grid) {

        conn = Connection.getInstance();
        messagelist = new ArrayList<>();
        this.grid = grid;
        conn.sendMessage("JOIN#");

    }

    // we use this code to get server messages
    public void listen() throws SlickException {

        Message_Decorder decode = new Message_Decorder(map, grid);

        while (true) {

            //get server message
            String server_message = conn.getUpdates();
            if (server_message != null) {
                System.out.println(server_message + "\n");

                //update map
                map.Update();
                if (grid != null) {
                    grid.update(map);
                }

                //decode the message and update the map

                decode.setMap(map);
                decode.setGrid(grid);
                map = decode.process(server_message);

                map.Update();
                if (grid != null) {
                    grid.update(map);
                }

                messagelist.add(server_message);
                if (map.getTanks().size() > 0) {

                }

            }

        }
    }

    @Override
    public void run() {
        try {
            this.listen();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
