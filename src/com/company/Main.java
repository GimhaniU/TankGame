package com.company;

import GUI.TankGrid;
import Parser.Map;
import connection.Connection;
import connection.MessageReader;

public class Main {


    public static void main(String[] args) {

        TankGrid tankGrid=new TankGrid();
        tankGrid.setVisible(true);
        MessageReader messageReader=new MessageReader(tankGrid);
        messageReader.listen();

        Map map=new Map();
        map.Update();

    }
}
