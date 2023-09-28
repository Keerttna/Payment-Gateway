package gateway.payment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.SimpleDateFormat;




public class MiniStatement extends JFrame {
    Connect c = new Connect();
    MiniStatement( String accountType, String cardNumber) {

        String account = accountType;
        String cardNo = cardNumber;

        try {

            // Get the current date and format it to match the database date format
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


            String sqlQuery = "SELECT TransactionType, Amount, Date, Time FROM Transactions " +
                    "WHERE CardNo = ? AND AccountType = ? " +
                    "AND DATE_FORMAT(STR_TO_DATE(Date, '%d-%m-%Y'), '%m-%Y') = DATE_FORMAT(NOW(), '%m-%Y')";

            PreparedStatement preparedStatement = c.connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, cardNo);
            preparedStatement.setString(2, account);

            ResultSet resultSet = preparedStatement.executeQuery();

            /// Create a DefaultTableModel to hold the data
            DefaultTableModel tableModel = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            tableModel.addColumn("Transaction Type");
            tableModel.addColumn("Amount");
            tableModel.addColumn("Date");
            tableModel.addColumn("Time");

            boolean hasData = false;

            // Enter the data in the table
            while (resultSet.next()) {
                String transactionType = resultSet.getString("TransactionType");
                double amount = resultSet.getDouble("Amount");
                String date = resultSet.getString("Date");
                String time = resultSet.getString("Time");

                // Add a row to the table model
                tableModel.addRow(new Object[]{transactionType, "Rs." + amount, date, time});
                hasData = true;
            }

            if (!hasData) {
                JOptionPane.showMessageDialog(
                        null,
                        "No transaction done this month!",
                        "Transaction History",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JTable table = new JTable(tableModel);
                table.setDefaultEditor(Object.class, null);
                JScrollPane scrollPane = new JScrollPane(table);

                // Display the JOptionPane
                JOptionPane.showMessageDialog(
                        null,
                        scrollPane,
                        "Transaction History",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (SQLException E) {
            System.out.println("ERROR: " + E.getMessage());
        }
    }
}


