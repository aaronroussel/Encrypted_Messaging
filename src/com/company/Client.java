package com.company;

import java.io.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

public class Client
{
        private String userName;
        private String ipAddress;
        private int portNumber;
        private java.net.Socket socket;

        public Client(){}

        public Client(String userName, String ipAddress, int portNumber, java.net.Socket socket)
        {
            this.userName = userName;
            this.ipAddress = ipAddress;
            this.portNumber = portNumber;
            this.socket = socket;
        }
        public Client(String userName, String ipAddress, int portNumber)
        {
            this.userName = userName;
            this.ipAddress = ipAddress;
            this.portNumber = portNumber;
        }

        public java.net.Socket getSocket()
        {
            return this.socket;
        }

        public void startConnection()
        {
            try
            {
                System.out.println("Connecting to " + ipAddress + " on port " + portNumber);
                Socket client = new Socket(ipAddress, portNumber);
                this.socket = client;
                // this may need to use another port number, different than the port used for the other socket
            }
            catch (SocketTimeoutException s)
            {
                System.out.println("socket has timed out!");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        public void sendMessage(Socket socket, int[] nums)
        {
            try {

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(nums);
            }
            catch(IOException x)
            {
                x.printStackTrace();
            }
        }

        public String getMessage()
        {
            while(true)
            {
                try
                {
                    ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());
                    int[] x = ((int[])in.readObject());
                    String s;
                    s = Decoder.decode(x);
                    System.out.println(s);
                    return s;
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
                catch(ClassNotFoundException c)
                {
                    c.printStackTrace();
                }
            }
        }

        public void setConnection(String ipAddress, int portNumber)
        {
            this.ipAddress = ipAddress;
            this.portNumber = portNumber;
        }


}
