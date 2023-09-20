package gateway.payment;

import java.sql.*;


public class Connect {
    Connection connection;
    Statement statement;
    public Connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SignUpDB","root","srk_1513");
            statement = connection.createStatement();


        } catch (Exception E){
            System.out.println("ERROR: "+E.getMessage());
        }
    }
}
