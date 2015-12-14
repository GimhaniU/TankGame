package com.company;
import SlickGui.SetUp;
import connection.MessageReader;
import connection.MessageWriter;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class Main {

    static  MessageReader messageReader;
    public static void main(String[] args)  {


        try {
            //Starting the gui
            SetUp tankgui=new SetUp("Tank Game");

            AppGameContainer app= new AppGameContainer(tankgui);
            app.setDisplayMode(972, 648, false);
            app.setShowFPS(false);
            app.setAlwaysRender(true);


            messageReader = new MessageReader(tankgui);
            System.out.println("reader created");
            messageReader.start();
            System.out.println("reader startd");
            MessageWriter messageWriter =new MessageWriter(messageReader);
            messageWriter.start();

            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }




    }
}
