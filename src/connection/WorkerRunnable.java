package connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Nuwantha on 10/26/2015.
 */


public class WorkerRunnable implements Runnable {

    protected Socket clientSocket = null;
    protected String serverText = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    public void run() {
        try {
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            long time = System.currentTimeMillis();
            output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " +
                    this.serverText + " - " +
                    time +
                    "").getBytes());
            output.close();
            input.close();
            System.out.println("Request processed: " + time);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////

    public void sendMessage(String message) {
        //   Socket client=null;
        DataOutputStream out = null;
        try {
            //  client = new Socket("127.0.0.1", 6000);
            out = new DataOutputStream(clientSocket.getOutputStream());
            out.writeBytes(message);

        } catch (UnknownHostException ex) {
            System.out.println("Severe: \n " + Connection.class.getName() + "\n" + ex + "\n");
            ex.printStackTrace();
        } catch (IOException ex) {

            System.out.println("Severe: \n " + Connection.class.getName() + "\n" + ex);
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println("Runtime Exceptions:" + e);
        } finally {
            try {
                //  client.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    //The Socket will be left unclosed on some exceptions.So moved close blocks to finally block.
    public String getUpdates() {//this method blocks untill msg receives.Use time outs.
        String update;
        ServerSocket serverReader = null;
        Socket clientReader = null;
        InputStreamReader socketReader = null;
        try {
            serverReader = new ServerSocket(7000);
            clientReader = serverReader.accept();
            socketReader = new InputStreamReader(clientReader.getInputStream());
            BufferedReader input = new BufferedReader(socketReader);
            update = input.readLine();
            return update;
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return "Error while Rading from the socket";
        } finally {
            try {
                if (serverReader != null) serverReader.close();
                if (clientReader != null) clientReader.close();
                if (socketReader != null) socketReader.close();
            } catch (IOException ex) {
                System.err.println("Could not close network socket");
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    public String getUpdates(int timeout) {//this method blocks untill msg received or timeout
        String update;
        ServerSocket serverReader = null;
        Socket clientReader = null;
        InputStreamReader socketReader = null;
        try {
            serverReader = new ServerSocket(7000);
            serverReader.setSoTimeout(timeout);
            clientReader = serverReader.accept();
            socketReader = new InputStreamReader(clientReader.getInputStream());
            BufferedReader input = new BufferedReader(socketReader);
            update = input.readLine();
            return update;
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            return "Error while Rading from the socket";
        } finally {
            try {
                if (serverReader != null) serverReader.close();
                if (clientReader != null) clientReader.close();
                if (socketReader != null) socketReader.close();
            } catch (IOException ex) {
                System.err.println("Could not close network socket");
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

}