package connection;

import GUI.TankGrid;
import GUI.WarGui;
import Parser.Map;
import Parser.Message_Decorder;

import java.util.ArrayList;

/**
 * Created by Nuwantha on 10/21/2015.
 */
public class MessageReader extends Thread {
    Map map=new Map();
    WarGui grid;
    Connection conn;
    ArrayList<String> messagelist;

    public MessageReader(WarGui grid) {

        conn = Connection.getInstance();
        messagelist=new ArrayList<>();
        this.grid=grid;
        conn.sendMessage("JOIN#");

    }


    public void listen(){

        Message_Decorder decode = new Message_Decorder(map);

        //Map tem_map=null;
        //int i=0;
        while(true){

            String server_message = conn.getUpdates();
            if (server_message != null) {
                System.out.println(server_message + "\n");
                decode = new Message_Decorder(map);
                map = decode.process(server_message);
                map.Update();
                if (grid != null) {
                    grid.updateGrid(map);
                }
                messagelist.add(server_message);
            }


        }
    }

    @Override
    public void run() {
        this.listen();
    }
}
