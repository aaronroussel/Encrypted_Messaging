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
        private java.net.Socket socket = null;

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

                System.out.println("Connected to: " + client.getRemoteSocketAddress());
                OutputStream outputStream = client.getOutputStream();
                DataOutputStream out = new DataOutputStream(outputStream);

                out.writeUTF("Hello! " +  client.getLocalSocketAddress());
                InputStream inputStream = client.getInputStream();
                DataInputStream in = new DataInputStream(inputStream);

                System.out.println("Server Says: " + in.readUTF());
                client.close();

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

        public void setConnection(String ipAddress, int portNumber)
        {
            this.ipAddress = ipAddress;
            this.portNumber = portNumber;
        }

}
