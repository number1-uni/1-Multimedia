package com.number1.phonetic;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private Connection connection;

    private static final String db = "PhoneTic";
    private static final String url = "jdbc:postgresql://192.168.65.13:5432/" + db;
    private static final String user = "MP"; // !IMPORTANT! username
    private static final String password = "admin"; // !IMPORTANT! password

    private static boolean status;
    private static boolean estado;
    static Connection conn = null;

    public Database() {
        connect();
        System.out.println("connection status:" + status);
    }

    public boolean getEstado(){
        return estado;
    }

    private static Connection connect() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection(url, user, password);
                    status = true;
                    Log.i("konexioa", "DB connected:" + true);
                } catch (Exception e) {
                    status = false;
                    Log.w("konexioa", e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            status = false;
        }
        return conn;
    }

    public static void LogIn(String user, String password){


       /*
         * Ez da modu egokiena. Begiratu egin behar da.
         * sql2 sententziak ez du funtzionatzen, pkadmin-en
         * exekutatuta bai.
         * Kodigoan ez du hurrengoa "existitzen" dela hartzen
         * rs.next().
         */

       String taula = "erabiltzaileak";
       String sql = "SELECT * FROM public." + taula + ";";
       //String sql2 = "SELECT * FROM public." + taula + " WHERE user = '" + user + "' AND password = '" + password + "';";

       Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (Connection conn = connect();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String strUser = rs.getString("user");
                        String strPass = rs.getString("password");

                        if(strUser.equals(user) && strPass.equals(password) ){
                            estado = true;
                            break;
                        } else{
                            estado = false;
                        }
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            estado = false;
        }
    }

}