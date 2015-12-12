package com.company;
import GUI.TankGrid;
import GUI.WarGui;
import Parser.Map;
import Parser.MapUpdater;
import connection.MessageReader;
import connection.MessageWriter;

public class Main {

static  MessageReader messageReader;
    public static void main(String[] args) {

        //Starting the gui
        WarGui tankGrid=new WarGui();
        tankGrid.setVisible(true);
        //thread to write messages
        messageReader = new MessageReader(tankGrid);
        messageReader.start();

        MessageWriter messageWriter =new MessageWriter(messageReader);
        messageWriter.start();




    }
}
