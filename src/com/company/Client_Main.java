package com.company;

public class Main {

    public static void main(String[] args)
    {
        final String IP_ADDRESS = "127.0.0.1";
        final int PORT_NUMBER = 5353;

        Client client = new Client();
        client.setConnection(IP_ADDRESS, PORT_NUMBER);
        client.startConnection();
    }
}
