package com.number1.phonetic;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    private Connection connection;

    private static final String db = "PhoneTic";
    private static final String url = "jdbc:postgresql://192.168.65.13:5432/" + db;
    private static final String user = "MP"; // !IMPORTANT! username
    private static final String password = "admin"; // !IMPORTANT! password

    private boolean status;

    public Database()
    {
        connect();
        System.out.println("connection status:" + status);
    }

    private void connect()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, password);
                    status = true;
                    Log.i("konexioa", "DB connected:" + true);
                }
                catch (Exception e)
                {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.status = false;
        }
    }
}