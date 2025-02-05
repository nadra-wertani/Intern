package utils;

import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyDatabase {
    private static MyDatabase instance ;

    private final String URL="jdbc:mysql://127.0.0.1:3306/gestion-sujetstage";
    private final String USERNAME ="root";
    private final String PASSWORD ="";
    private Connection connection;

    private MyDatabase (){

        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("  " +
                    "Connected ... ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(" ___ Connection Failed ___");
        }
    }


    public static MyDatabase getInstance() {
        if(instance == null)
            instance = new MyDatabase();

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}


