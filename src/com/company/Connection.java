package com.company;

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
 * Created by DinsuG on 10/21/2015.
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
        try {
            Socket client = new Socket("127.0.0.1", 6000);
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            out.writeBytes(message);
            client.close();
            out.close();
        } catch (UnknownHostException ex) {
            // Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Severe: \n "+Connection.class.getName()+"\n"+ex+"\n");
            ex.printStackTrace();
        } catch (IOException ex) {
            //Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Severe: \n "+Connection.class.getName()+"\n"+ex);
            ex.printStackTrace();
        } catch(Exception e){
            System.out.println("Runtime Exceptions:"+e);
        }


    }
    //to hasintha.The Socket will be left unclosed on some exceptions.So moved close blocks to finally block.
    public String getUpdates(){//this method blocks untill msg receives.Use time outs.
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
            return "Error while Rading from the socket";
        }finally{
            try {
                if(serverReader!=null)serverReader.close();
                if(clientReader!=null)clientReader.close();
                if(socketReader!=null)socketReader.close();
            } catch (IOException ex) {
                System.err.println("Could not close network socket");
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    public String getUpdates(int timeout){//this method blocks untill msg received or timeout
        String update;
        ServerSocket serverReader=null ;
        Socket clientReader=null;
        InputStreamReader socketReader=null;
        try {
            serverReader = new ServerSocket(7000);
            serverReader.setSoTimeout(timeout);
            clientReader = serverReader.accept();
            socketReader = new InputStreamReader(clientReader.getInputStream());
            BufferedReader input = new BufferedReader(socketReader);
            update= input.readLine();
            return update;
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return "Error while Rading from the socket";
        }finally{
            try {
                if(serverReader!=null)serverReader.close();
                if(clientReader!=null)clientReader.close();
                if(socketReader!=null)socketReader.close();
            } catch (IOException ex) {
                System.err.println("Could not close network socket");
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

}
