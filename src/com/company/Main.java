package com.company;
import GUI.TankGrid;
import GUI.WarGui;
import Parser.Map;
import connection.MessageReader;
import connection.MessageWriter;

public class Main {


    public static void main(String[] args) {

        MessageWriter messageWriter = new MessageWriter();
        messageWriter.start();

        Map map=new Map();
        WarGui tankGrid=new WarGui();
        tankGrid.setVisible(true);
        MessageReader messageReader = new MessageReader(tankGrid);

        while(true) {
            map = messageReader.listen(map);
            map.Update();
            tankGrid.updateGrid(map);
        }
    }
}
