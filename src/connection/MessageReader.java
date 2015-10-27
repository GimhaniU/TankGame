package connection;

import GUI.TankGrid;
import Parser.Map;
import Parser.Message_Decorder;

import java.util.ArrayList;

/**
 * Created by Nuwantha on 10/21/2015.
 */
public class MessageReader {
    TankGrid grid;
    Connection conn;
    ArrayList<String> messagelist;

    public MessageReader(TankGrid grid) {

        conn = Connection.getInstance();
        messagelist=new ArrayList<>();
        this.grid=grid;
        conn.sendMessage("JOIN#");

    }


    public Map listen(Map map){

        Map tem_map=null;
        int i=0;
        while(i<1){


            String server_message = conn.getUpdates();
            if (server_message != null) {
                System.out.println(server_message + "\n");
                //process message
                Message_Decorder decode = new Message_Decorder(map);
                Map map2 = decode.process(server_message);
                tem_map=map2;
                if (grid != null) {
                    grid.updateGrid(map2);
                }
                messagelist.add(server_message);
            }

            i++;
        }
        return tem_map;
    }
}
