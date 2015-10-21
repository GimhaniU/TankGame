package com.company;

import GUI.TankGrid;
import connection.Connection;
import connection.MessageReader;

public class Main {

    public static void main(String[] args) {
     //   MessageReader messageReader=new MessageReader();
     //   messageReader.listen();

        TankGrid tankGrid=new TankGrid();
    tankGrid.setVisible(true);
    }
}
