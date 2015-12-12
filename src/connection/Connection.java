package connection;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Gimhani on 10/21/2015.
 */
public class Connection {
    static Connection connection;

    //using Singleton to create the connection
    public static Connection getInstance(){
        if(connection ==null){
            connection = new Connection();
            return connection;
        }else{
            return connection;
        }

    }

    //to send messages to server
    public void sendMessage(String message){
        Socket client=null;
        DataOutputStream out=null;
        try {
            client = new Socket("127.0.0.1", 6000);
            out = new DataOutputStream(client.getOutputStream());
            out.writeBytes(message);

        } catch (UnknownHostException ex) {
            System.out.println("Exception: \n "+Connection.class.getName()+"\n"+ex+"\n");
            ex.printStackTrace();
        } catch (IOException ex) {

            System.out.println("Exception: \n "+Connection.class.getName()+"\n"+ex);
            ex.printStackTrace();
        } catch(Exception e){
            System.out.println("Runtime Exceptions:"+e);
        }finally{
            IOUtils.closeQuietly(client);
            IOUtils.closeQuietly(out);

            /*
            try {
                client.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        }


    }
    //The Socket will be left unclosed on some exceptions.So moved close blocks to finally block.

    //this method blocks until msg receives.Use time outs.

    public String getUpdates(){

        String update;
        ServerSocket serverReader=null ;
        Socket clientReader=null;
        InputStreamReader socketReader=null;
        try {
            serverReader = new ServerSocket(7000);
            clientReader = serverReader.accept();
            socketReader = new InputStreamReader(clientReader.getInputStream());
            BufferedReader input = new BufferedReader(socketReader);
            update= input.readLine();
            return update;
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return "Error reading from the socket";
        }finally{
            IOUtils.closeQuietly(clientReader);
            IOUtils.closeQuietly(serverReader);
            IOUtils.closeQuietly(socketReader);


           /* try {

                if(serverReader!=null)serverReader.close();
                if(clientReader!=null)clientReader.close();
                if(socketReader!=null)socketReader.close();
            } catch (IOException ex) {
                System.err.println("Could not close network socket");
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }

    }



}
