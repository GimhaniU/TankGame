package connection;

import java.util.ArrayList;

/**
 * Created by Nuwantha on 10/21/2015.
 */
public class MessageReader {
    Connection conn;
    ArrayList<String> messagelist;

    public MessageReader() {
        conn = Connection.getInstance();
        messagelist=new ArrayList<>();

        //request to join
        conn.sendMessage("JOIN#");
        System.out.println(conn.getUpdates());
    }
    public void listen(){
        while(true){
            String server_message=conn.getUpdates();
            System.out.println(server_message+"\n");
            //process message
            messagelist.add(server_message);
        }
    }
}
