package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;


public class Gui
{
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
        headerLabel.setText("");

        JButton okButton = new JButton("Select file");


        okButton.setActionCommand("Select File to Decode");


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(mainFrame);
                if(returnVal == JFileChooser.APPROVE_OPTION);
                {
                    filePath = fc.getSelectedFile().getAbsolutePath();
                    File_Handling handler = new File_Handling(filePath);
                    headerLabel.setText(Decoder.decode (handler.returnFileContents()) );
                    //actions to read file and do whatever. not there yet lol

                }

            }
        });
        controlPanel.add(okButton);
        mainFrame.setVisible(true);

    }

}
