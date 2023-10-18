package ttp.hystrix;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloWorld
{
    public static void main(String[] args)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String s = new HelloWorldCommand("Nick").execute();
        System.out.println(sdf.format(new Date()) + "->" + s);

        s = new HelloWorldCommand("TimeoutCommand").execute();
        System.out.println(sdf.format(new Date()) + "->" + s);

        s = new HelloWorldCommand("ExceptionCommand").execute();
        System.out.println(sdf.format(new Date()) + "->" + s);
    }
}