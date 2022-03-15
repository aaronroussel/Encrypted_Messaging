package com.company;
import java.io.*;
import java.net.*;


public class Server extends Networking
{
    private ServerSocket serverSocket;
    private final int port = 5353;

    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("waiting for client on port " + serverSocket.getLocalPort() + ".....");
                Socket server = serverSocket.accept();

                System.out.println("Just connected to + " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());

                while(true) {
                    System.out.println(in.readInt());
                }
               // DataOutputStream out = new DataOutputStream(server.getOutputStream());
                // out.writeUTF("Thank you for connecting to: " + server.getLocalSocketAddress() + "Goodbye! /n");
            }
            catch (SocketTimeoutException s)
            {
                System.out.println("Socket has timed out!");
                break;
            }
            catch (IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
    public void startSocket(int portnum) throws IOException
    {
        serverSocket = new ServerSocket(portnum);
        serverSocket.setSoTimeout(10000);
    }

    public void start()
    {
        try
        {
            startSocket(port);
            run();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
