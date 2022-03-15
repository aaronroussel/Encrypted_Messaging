package com.company;
import java.net.*;
import java.io.*;

public class Networking
{
    private String host;
    private int port;
    Socket clientSocket;
    InputStream input;
    OutputStream output;


    Networking(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    public String getHost()
    {
        return host;
    }
    public int getPort()
    {
        return port;
    }
    public void startSocket()
    {

    }

}
