package com.company;
public class Decoder
{
    public static String decode(int[] secretMessage)
    {
        String message = "";
        for(int i = 0; i < secretMessage.length; i++)
        {
            char letter = (char)secretMessage[i];
            message += letter;
        }

        return message;
    }
    public static String decode(int[] secretMessage, int offset)
    {
        String message = "";
        for (int i = 0; i < secretMessage.length; i++)
        {
            if(secretMessage[i] < 123 || secretMessage[i] < 32 )
            {
                char letter = (char) (secretMessage[i] - offset);
                message += letter;
            }
            else
            {
                continue;
            }
        }

        return message;
    }
}