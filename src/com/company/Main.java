package com.company;
import GUI.TankGrid;
import Parser.Map;
import connection.Connection;
import connection.MessageReader;
import connection.MessageWriter;

public class Main {


    public static void main(String[] args) {

        MessageWriter messageWriter = new MessageWriter();
        messageWriter.start();

        Map map=new Map();
        TankGrid tankGrid=new TankGrid();
        tankGrid.setVisible(true);
        MessageReader messageReader = new MessageReader(tankGrid);

        while(true) {
            map = messageReader.listen(map);
            map.Update();
            tankGrid.updateGrid(map);
        }
    }
}
