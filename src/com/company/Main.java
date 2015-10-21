package com.company;

import connection.Connection;
import connection.MessageReader;

public class Main {

    public static void main(String[] args) {
        MessageReader messageReader=new MessageReader();
        messageReader.listen();

    }
}
