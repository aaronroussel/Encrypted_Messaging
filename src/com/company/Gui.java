package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.io.IOException;
import java.net.*;
import java.io.*;


public class Gui
{
    // declaring some stuff
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private String filePath;
    private Socket socket;

    public Gui()
    {
        startGui();
    }
    private void startGui()
    {
        // initialize the main parts of the UI
        mainFrame = new JFrame("Encode/Decoder");
        mainFrame.setSize(1000,600);
        mainFrame.setLayout(new GridLayout(3,1));

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350,100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent)
            {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);

    }
    void uiBegin()
    {
        Client client = new Client("127.0.0.1", 5353);

        //ConnectionThread connection = new ConnectionThread(client);

        OutputThread output = new OutputThread(client);

        client.startSocket();
        client.startOut();

        //output.start();

        // initialize the rest of the UI
        headerLabel.setText("");

        JTextField ipText = new JTextField("127.0.0.1");
        JTextField portText = new JTextField("5353");
        JTextField text = new JTextField("TEXT HERE", JTextField.CENTER);
        text.setColumns(50);
        client.setConnection ( ipText.getText(), Integer.parseInt(portText.getText()) );


        JTextArea text2 = new JTextArea(10,50);

        InputThread input = new InputThread(client, text2);
        input.start();

        JScrollPane scroll = new JScrollPane(text2,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        text2.append("Messages: \n");

        JButton connect = new JButton("Send Message");
        connect.setActionCommand("CONNECT");
        connect.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // connect client to the server

                //client.startConnection();

                // send encoded message
                client.sendMessage( Encoder.encode (text.getText()) );
                text.setText("");
                //String s = client.getMessage();
                //text2.append( s + "\n");
                client.startSocket();
                client.startOut();


            }
        });

        ActionListener enterKey = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client.sendMessage( Encoder.encode (text.getText()) );
                text.setText("");
                //String s = client.getMessage();
                //text2.append( s + "\n");
                client.startSocket();
                client.startOut();
            }
        };

        ActionListener updateText = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //client.setConnection ( ipText.getText(), Integer.parseInt(portText.getText()) );
                //client.startConnection();
                //client.startSocket();
                //input.start();
                //String s = client.getMessage();
                //text2.setText(s);
                //System.out.println("s");
                //client.startSocket();
            }
        };
        Timer timer = new Timer(50, updateText);
        timer.setRepeats(true);
        timer.start();

        controlPanel.add(connect);
        controlPanel.add(ipText);
        controlPanel.add(portText);
        controlPanel.add(text);
        controlPanel.add(text2);

        mainFrame.setVisible(true);



    }

}



class InputThread extends Thread
{
    Client client;
    JTextArea text;
    InputThread(Client client, JTextArea text)
    {
        this.text = text; this.client = client;
    }
    @Override
    public void run()
    {
        client.startIn();

        while(true)
        {
            String s = client.getMessage();
            text.append(s + "\n");
            //client.startSocket();
            //client.startIn();
        }
    }
}

class OutputThread extends Thread
{
    Client client;
    OutputThread(Client client)
    {
        this.client = client;
    }
    @Override
    public void run()
    {
        this.client.startOut();
    }
}
