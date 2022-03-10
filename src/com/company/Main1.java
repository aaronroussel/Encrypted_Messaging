package com.company;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;


public class Main1 {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws IOException {
        // main method
        Scanner input = new Scanner(System.in);
        int x = 1;
        System.out.println("Enter 1 to create a new message file");
        System.out.println("Enter 2 to open a file");
        while (x == 1) {
            if (Objects.equals(input.nextLine(), "1"))
            {
                //if yes, create new file under use-specified name
                System.out.print("Enter file name: ");
                String s = input.nextLine() + ".txt";
                File_Handling handler = new File_Handling(s);
                handler.createFile();
                System.out.print("input a message: ");
                String message = input.nextLine();
                int[] theMessageEncoded = new int[message.length()];

                System.out.print("Would you like to use an offset for Encoding? (Y or N): ");
                String answer1 = input.nextLine();
                int offset = 0;
                if (answer1.equals("Y")) {
                    System.out.println("Enter an offset to use: ");
                    offset = Integer.parseInt(input.nextLine());
                    theMessageEncoded = Encoder.encode(message, offset);
                    handler.writeFile(theMessageEncoded);
                } else if (answer1.equals("N")) {
                    theMessageEncoded = Encoder.encode(message);
                    handler.writeFile(theMessageEncoded);
                }

                System.out.print("Encoded message: ");
                for (int i = 0; i < theMessageEncoded.length; i++) {
                    System.out.print(ANSI_RED + theMessageEncoded[i] + ANSI_RESET);
                    System.out.print(" ");
                    if (i == theMessageEncoded.length - 1) {
                        System.out.println();
                    }
                }
                x = 2;
            }
            else
            {
                System.out.print("Enter name of file to open: ");
                String s = input.nextLine() + ".txt";
                File_Handling handler = new File_Handling(s);
                System.out.print("File contents: ");
                handler.readFile();
                x = 2;
                System.out.println();
                System.out.print("Would you like to decode this message? (Y or N): ");
                String answer2 = input.nextLine();

                if(answer2.equals("Y"))
                {
                    System.out.print("Enter Offset (Enter 0 if no offset): ");
                    int offset = Integer.parseInt(input.nextLine());
                    System.out.print("Decoded message: ");
                    System.out.print(ANSI_RED + Decoder.decode(handler.returnFileContents(), offset) + ANSI_RESET); // this prints int array still + weird stuff??
                    System.out.println(" ");System.out.println("Exiting program......");
                }
                else if(answer2.equals("N"))
                {
                    System.out.println("Exiting program......");
                    System.exit(0);
                }
            }
        }
    }
}

        /*
        System.out.print("Would you like to decode this message? (Y or N): ");
        String answer2 = input.nextLine();

        if(offset != 0)
        {
            if(answer2.equals("Y"))
            {
                System.out.print("Decoded message: ");
                System.out.print(ANSI_RED + Decoder.decode(theMessageEncoded, offset) + ANSI_RESET);
                System.out.println(" ");
                System.out.println("Exiting program......");
            }
            else if(answer2.equals("N"))
            {
                System.out.println("Exiting program......");
            }
        }
        else if(offset == 0)
        {
            if(answer2.equals("Y"))
            {
                System.out.print("Decoded message: ");
                System.out.print(ANSI_RED + Decoder.decode(theMessageEncoded) + ANSI_RESET);
                System.out.println(" ");
                System.out.println("Exiting program......");
            }
            else if(answer2.equals("N"))
            {
                System.out.println("Exiting program......");
            }
        }
 */
