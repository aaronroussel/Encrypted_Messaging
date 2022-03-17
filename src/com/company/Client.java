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
        private ObjectOutputStream out;
        private ObjectInputStream in;

        public Client(){}

        public Client(String ipAddress, int portNumber, java.net.Socket socket)
        {
            this.ipAddress = ipAddress;
            this.portNumber = portNumber;
            this.socket = socket;
        }
        public Client( String ipAddress, int portNumber)
        {
            this.ipAddress = ipAddress;
            this.portNumber = portNumber;
        }

        public java.net.Socket getSocket()
        {
            return this.socket;
        }

        public void startSocket()
        {
            try
            {
                System.out.println("Connecting to " + ipAddress + " on port " + portNumber);

                Socket socket = new Socket(ipAddress, portNumber);
                this.socket = socket;
                System.out.println(socket.getRemoteSocketAddress());
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
        public void startOut()
        {
            try
            {
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                this.out = out;
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        public void startIn()
        {
            try
            {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                this.in = in;
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        public void sendMessage(int[] nums)
        {
            try {

                //ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(nums);
                out.flush();
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
