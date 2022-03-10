package com.company;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class File_Handling
{
    // Data Variables //-------------------------------------------------------------------------------------------
    private String theFileName;

    // Constructors //-------------------------------------------------------------------------------------------

    File_Handling(String name)
    {
        theFileName = name;
    }

    // Methods //-------------------------------------------------------------------------------------------

    public void createFile() throws IOException {
            File newFile = new File(theFileName);
            if(newFile.createNewFile())
            {
                System.out.println("File created: " + theFileName);
            }
            else
            {
                System.out.println("ERROR: file might already exist");
            }
    }

    //-------------------------------------------------------------------------------------------

    public void writeFile(int[] a)
    {
        try(FileWriter writer = new FileWriter(theFileName))
        {
            String s = Arrays.toString(a);
            writer.write(s);
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println("An error has occurred");
        }
    }

    //-------------------------------------------------------------------------------------------

    public void readFile()
    {
        try(FileReader reader = new FileReader(theFileName))
        {
            char[] charStream = new char[100];
            reader.read(charStream);
            int i = 0;
            while(charStream[i] != '\u0000')
            {
                switch(charStream[i])
                {
                    case ',':
                    case '[':
                    case ']':
                        i++;
                        continue;
                    default:
                        System.out.print(charStream[i]);
                        i++;
                        break;
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch(IOException e)
        {
            System.out.println("An error has occurred");
        }
    }

    //-------------------------------------------------------------------------------------------

    public int[] returnFileContents()
    {
        String message = "";
        try(FileReader reader = new FileReader(theFileName))
        {
            char[] charStream = new char[255];
            reader.read(charStream);
            int i = 0;
            while(charStream[i] != '\u0000')
            {
                if(charStream[i] >= 48 && charStream[i] <= 57 || charStream[i] == 32)
                {
                    message += charStream[i];
                }
                i++;
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch(IOException e)
        {
            System.out.println("An error has occurred");
        }
        String[] strArray = message.split(" ");
        int[] ray = new int[strArray.length];
        for(int i = 0; i < strArray.length; i++)
        {
            ray[i] = Integer.parseInt(strArray[i]);
        }

        return ray;
    }

    //-------------------------------------------------------------------------------------------

    public void setFileName(String name)
    {
        theFileName = name;
    }

    //-------------------------------------------------------------------------------------------
}
