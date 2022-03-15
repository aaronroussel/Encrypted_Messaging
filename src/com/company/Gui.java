package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;


public class Gui
{
    // declaring some stuff
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private String filePath;

    public Gui()
    {
        startGui();
    }
    private void startGui()
    {
        // initialize the main parts of the UI
        mainFrame = new JFrame("Encode/Decoder");
        mainFrame.setSize(400,300);
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
        // initialize the rest of the UI
        headerLabel.setText("");

        JTextField ipText = new JTextField("127.0.0.1");
        JTextField portText = new JTextField("5353");
        JTextField text = new JTextField("TEXT HERE");


        JButton serverStart = new JButton("Start Server");
        serverStart.setActionCommand("START");
        serverStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // start the server process
                Server server = new Server();
                server.start();
            }
        });

        JButton connect = new JButton("Connect");
        connect.setActionCommand("CONNECT");
        connect.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // connect client to the server
                Client client = new Client();
                client.setConnection ( ipText.getText(), Integer.parseInt(portText.getText()) );
                client.startConnection();
                // send encoded message
                client.sendMessage( Encoder.encode (text.getText()) );
            }
        });
        controlPanel.add(connect);
        controlPanel.add(serverStart);
        controlPanel.add(ipText);
        controlPanel.add(portText);
        mainFrame.setVisible(true);
    }

}
