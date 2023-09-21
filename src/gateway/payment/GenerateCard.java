package gateway.payment;

import javax.swing.*;
import java.sql.*;
import java.util.Random;

public class GenerateCard extends JFrame {
    String uniqueCard = null, cardWithSpace = "";
    int pinForCard = 0;
    Connect c = new Connect();
    Random rand = new Random();

    public String uniqueCardNumber() throws SQLException {
        do {
            uniqueCard = generateRandomCardNumber();
        } while (isCardNumberUnique());

        // Iterate through the string and add spaces after every 4 digits
        for (int i = 0; i < uniqueCard.length(); i++) {
            cardWithSpace += uniqueCard.charAt(i);
            if ((i + 1) % 4 == 0 && i != uniqueCard.length() - 1) {
                cardWithSpace += " "; // Add a space after every 4 digits (except the last group)
            }
        }


        return cardWithSpace;
    }

    public int pinNumber() {
        //Generate pin
        Random rand = new Random();
        pinForCard = rand.nextInt(1000,10000);

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

    public void displayCardDetails() {
        JOptionPane.showMessageDialog(null,"Card No. : "+cardWithSpace+"\nPIN: "+pinForCard+"\nPlease note the credentials");
    }


    public static void main(String[] args) throws SQLException {
        GenerateCard object = new GenerateCard();
        object.uniqueCardNumber();


    }

}
