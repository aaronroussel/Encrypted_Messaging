package com.company;
import java.io.*;
import java.net.*;


public class Server
{
    private ServerSocket serverSocket;
    private final int port = 5353;
    private java.net.Socket socket;
    ObjectOutputStream out;

    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("waiting for client on port " + serverSocket.getLocalPort() + ".....");
                Socket server = serverSocket.accept();

                System.out.println("+ " + server.getRemoteSocketAddress());
                ObjectInputStream in = new ObjectInputStream(server.getInputStream());

                int[] x = (int[])in.readObject();
                String s = null;
                s = Decoder.decode(x);
                System.out.println("\u001b[31m" + s + "\u001b[0m");
                String confirmation = "SUCCESS!";

                if (out == null)
                {
                    ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
                    this.out = out;
                }
                int[] ray = Encoder.encode(confirmation);
                out.writeObject(ray);
                out.flush();
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