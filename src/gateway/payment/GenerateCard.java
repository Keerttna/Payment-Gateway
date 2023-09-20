package gateway.payment;

import java.sql.*;
import java.util.Random;

public class GenerateCard {
    String uniqueCard = null;
    int pinForCard = 0;
    Connect c = new Connect();
    Random rand = new Random();

    public String uniqueCardNumber() throws SQLException {
        do {
            uniqueCard = generateRandomCardNumber();
        } while (isCardNumberUnique());

        return uniqueCard;
    }

    public int pinNumber() {
        //Generate pin
        Random rand = new Random();
        pinForCard = rand.nextInt(10000);

        return  pinForCard;
    }

    public String generateRandomCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(rand.nextInt(10));
        }
        return cardNumber.toString();
    }

    public boolean isCardNumberUnique() throws SQLException {
        String SQL = "SELECT * FROM SignUpTable WHERE CardNo = '" + uniqueCard + "' ";
        ResultSet rs = c.statement.executeQuery(SQL);
        if (rs.next()) {
            return true; //Not unique
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws SQLException {
        GenerateCard object = new GenerateCard();
        object.uniqueCardNumber();

    }

}
