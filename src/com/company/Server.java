package com.company;
import java.io.*;
import java.net.*;


public class Server
{
    private ServerSocket serverSocket;
    private final int port = 5353;
    private java.net.Socket socket;

    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("waiting for client on port " + serverSocket.getLocalPort() + ".....");
                Socket server = serverSocket.accept();

                System.out.println("Just connected to + " + server.getRemoteSocketAddress());
                ObjectInputStream in = new ObjectInputStream(server.getInputStream());

                int[] x = (int[])in.readObject();
                String s = null;
                s = Decoder.decode(x);
                System.out.println(s);
                String confirmation = "SUCCESS!";
                ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
                int[] ray = Encoder.encode(confirmation);
                out.writeObject(ray);
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
            catch (ClassNotFoundException z)
            {
                z.printStackTrace();
            }
        }
    }
    public void startSocket(int portnum) throws IOException
    {
        serverSocket = new ServerSocket(portnum);
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
