package com.company;

import com.sun.org.apache.xpath.internal.SourceTree;

public class Main {

    public static void main(String[] args) {
	    Connection conn=Connection.getInstance();
        conn.sendMessage("JOIN#");
        System.out.println(conn.getUpdates());

    }
}
