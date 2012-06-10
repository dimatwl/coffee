package ru.spbau.shestavin.coffee.consoleUI;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: DimaTWL
 * Date: 6/6/12
 * Time: 1:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class Message
{
    static String cp = System.getProperty("console.encoding","Cp1251");
    public static void print(String msg) throws IOException
    {
        msg += "\n";
        byte[] b;
        try { b = msg.getBytes(cp); }
        catch( UnsupportedEncodingException e )
        {
            b = msg.getBytes();       // В случае отсутствия нужной кодировки,
            // делаем преобразование по умолчанию
        }
        System.out.write(b);
    }
}
