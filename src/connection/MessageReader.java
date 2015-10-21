package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by DinsuG on 10/21/2015.
 */
public class MessageReader {
    int port;
    boolean is_connected;
    boolean is_listening=false;
    ArrayList<String> message_queue;

    public MessageReader() {
       port=7000;
        is_connected=false;
        message_queue=new ArrayList<>();
    }

    public MessageReader(int port, boolean is_connected, ArrayList<String> message_queue) {
        this.port = port;
        this.is_connected = is_connected;
        this.message_queue = message_queue;
    }

    public void continue_listen(){
        is_listening=true;

        Socket socket = null;
        BufferedReader reader=null;
        PrintWriter writer;
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Connecting to server....");
            socket = new Socket("127.0.0.1", 9090);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer=new PrintWriter(socket.getOutputStream(),true);

            for(int i=0;i<3;i++){
                System.out.println(reader.readLine());
            }

            while(true) {
                String input = scanner.next();
                writer.println(input);
                String responseFromServer = reader.readLine();

                System.out.println(responseFromServer);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(socket);
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(scanner);
        }
    }
}
