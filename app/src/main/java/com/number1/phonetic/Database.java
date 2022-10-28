package com.number1.phonetic;

import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    public static Connection connecta() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
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

    public static void LogIn(String usuario, String password){
        String taula = "erabiltzaileak";
        String sql = "SELECT * FROM " + taula + "WHERE user ='" + usuario + "' AND password = '" + password + "';";

        try (Connection conn = connecta();
             Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next() == true) {
                String a = null;
                Log.d( a , "eginda");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}