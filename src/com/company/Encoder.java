package com.company;
public class Encoder
{
    public static int[] encode(String message)
    {
        int[] encodedMessage = new int[message.length()];
        for(int i = 0; i < encodedMessage.length; i++)
        {
            int x = (int)message.charAt(i);
            encodedMessage[i] = x;
        }
        return encodedMessage;
    }

    public static int[] encode(String message, int offset)
    {
        int[] encodedMessage = new int[message.length()];
        for(int i = 0; i < encodedMessage.length; i++)
        {
            int x = (int)message.charAt(i);
            encodedMessage[i] = x + offset;
        }
        return encodedMessage;
    }
}