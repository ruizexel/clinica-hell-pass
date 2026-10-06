package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String url = "jdbc:mariadb://localhost:3307/db_hells";
    private static final String username = "root";
    private static final String password = "root";

    private static Connection myConnection;

    public Connection getInstance() throws SQLException{
        if(myConnection == null){
            myConnection = DriverManager.getConnection(url, username, password);
        }

        return myConnection;
    }
}
