package com.number1.phonetic;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.number1.phonetic.model.Product;
import com.number1.phonetic.model.Supplier;

import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public boolean getEstado() {
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

    public static void LogIn(String user, String password) {

        String sql = "SELECT erabiltzaileak.user, password FROM public.erabiltzaileak WHERE erabiltzaileak.user='" + user + "' AND erabiltzaileak.password = '" + password + "'";

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (Connection conn = connect();
                     PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String strUser = rs.getString("user");
                        String strPass = rs.getString("password");

                        if (strUser.equals(user) && strPass.equals(password)) {
                            estado = true;
                            break;
                        } else {
                            estado = false;
                        }
                    }
                } catch (SQLException e) {
                    Toast.makeText(null, "Error", Toast.LENGTH_SHORT).show();
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

    public static ArrayList<Product> grabProducts(ArrayList<Product> products) {
        String table = "public.product_template";
        String sql = "SELECT * FROM " + table;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (Connection conn = connect();
                     PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        products.add(new com.number1.phonetic.model.Product(rs.getString("name"), rs.getInt("id"), rs.getDouble("list_price")));
                    }
                } catch (SQLException e) {
                    Log.e("Datu basea", e.getMessage());
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
        return products;
    }

    public static ArrayList<Supplier> grabSuppliers(ArrayList<Supplier> suppliers) {
        String table = "public.res_partner";
        String sql = "SELECT * FROM " + table + " WHERE email IS NOT NULL";

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (Connection conn = connect();
                     PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        suppliers.add(new com.number1.phonetic.model.Supplier(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("city")));
                    }
                } catch (SQLException e) {
                    Log.e("Datu basea", e.getMessage());
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
        return suppliers;
    }
}