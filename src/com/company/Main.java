package com.company;
import GUI.TankGrid;
import GUI.WarGui;
import Parser.Map;
import Parser.MapUpdater;
import connection.MessageReader;
import connection.MessageWriter;

public class Main {


    public static void main(String[] args) {

       // Map map=new Map();

        WarGui tankGrid=new WarGui();
        tankGrid.setVisible(true);


        MessageWriter messageWriter = new MessageWriter(tankGrid);
        messageWriter.start();


        MessageReader messageReader = new MessageReader(tankGrid);
        messageReader.start();


       //MapUpdater mapUpdater = new MapUpdater(map);
        //mapUpdater.start();

        /*Thread t=new Thread(tankGrid);
        try {
            t.join(mapUpdater.getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.start();*//*
        while(true) {
            map = messageReader.listen(map);
           // mapUpdater.setMap(map);
            map.Update();
            tankGrid.updateGrid(map);
        }*/
    }
}
