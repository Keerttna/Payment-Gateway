package gateway.payment;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Withdraw extends JFrame implements ActionListener{
    Connect c = new Connect();
    String cardNo,name,account;
    JTextField withdrawField;
    JButton submitBt,backBt;
    Withdraw( String cardNumber,String accountType){

        cardNo = cardNumber;
        account = accountType;
        setTitle("Withdraw");
        setLayout(null);
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Card Holder's Name
        try {
            String SQL = "SELECT Name FROM SignUpTable WHERE CardNo = '" + cardNo + "' ";
            ResultSet rs = c.statement.executeQuery(SQL);
            if(rs.next()) {
                name = rs.getString("Name");
            }
        } catch(Exception E) {
            System.out.println("ERROR: "+E.getMessage());
        }

        JLabel cardHolder = new JLabel("Name: "+name);
        cardHolder.setForeground(Color.WHITE);
        cardHolder.setFont(new Font("Garamond", Font.BOLD, 20));
        cardHolder.setBounds(30, 30, 200, 40);
        add(cardHolder);

        //Card No
        String lastFourDigits = cardNo.substring(14);
        String maskedDigits = "XXXX XXXX XXXX ";
        String maskedCardNumber = maskedDigits + lastFourDigits;

        JLabel displayCardNo = new JLabel("Card No.: "+maskedCardNumber);
        displayCardNo.setForeground(Color.WHITE);
        displayCardNo.setFont(new Font("Garamond", Font.BOLD, 20));
        displayCardNo.setBounds(30, 60, 500, 40);
        add(displayCardNo);

        //Withdraw Column
        JLabel withdraw = new JLabel("Enter the amount to withdraw:");
        withdraw.setForeground(Color.WHITE);
        withdraw.setFont(new Font("Garamond", Font.BOLD, 22));
        withdraw.setBounds(30, 110, 600, 40);
        add(withdraw);

        JLabel Rs = new JLabel("Rs.");
        Rs.setBackground(Color.WHITE);
        Rs.setOpaque(true);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        Rs.setBorder(blackline);
        Rs.setFont(new Font("Garamond", Font.BOLD, 20));
        Rs.setBounds(30,170,40,31);
        add(Rs);

        withdrawField = new JTextField(15);
        withdrawField.setBounds(70, 170, 250, 30);
        withdrawField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(withdrawField);

        //Submit Button
        submitBt = new JButton("Withdraw");
        submitBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        submitBt.setForeground(Color.BLACK);
        submitBt.setBackground(new Color(125, 215, 161));
        submitBt.setBounds(100, 240, 150, 30);
        submitBt.addActionListener(this);
        add(submitBt);

        //Back Button
        backBt = new JButton("Back");
        backBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        backBt.setForeground(Color.WHITE);
        backBt.setBackground(new Color(100,149,237));
        backBt.setBounds(100, 305, 150, 30);
        backBt.addActionListener(this);
        add(backBt);

        //Add background
        ImageIcon bgImgIcon = new ImageIcon(ClassLoader.getSystemResource("DepositBg.jpg"));
        Image scaledBgImg = bgImgIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
        ImageIcon scaledBgIcon = new ImageIcon(scaledBgImg);
        JLabel bgLabel = new JLabel(scaledBgIcon);
        bgLabel.setBounds(0, 0, 600, 400);
        add(bgLabel);

        setVisible(true);
    }

    public static void main(String[] args) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            Double rsBalance = 0.00;

            String whichBalance = "SavingsBalance";
            if (account.equals("Savings")) {
                whichBalance = "SavingsBalance";
            } else if(account.equals("Current")) {
                whichBalance = "CurrentBalance";
            }

            String SQL = "SELECT " + whichBalance + " FROM SignUpTable WHERE CardNo = '" + cardNo + "'";
            ResultSet rs = c.statement.executeQuery(SQL);
            if(rs.next()) {
                rsBalance = Double.valueOf(rs.getString(whichBalance));
            }

            Double balance = rsBalance;

            if(e.getSource() == backBt) {
                if(account.equals("Savings")) {
                    new Savings(cardNo);
                    setVisible(false);
                } else if(account.equals("Current")) {
                    new Current(cardNo);
                    setVisible(false);
                }

            } else if(e.getSource()== submitBt) {
                if(!withdrawField.getText().matches("[0-9]+") || Double.valueOf(withdrawField.getText()) <= 0) {
                    JOptionPane.showMessageDialog(null,"Invalid amount!");
                } else if(Double.valueOf(withdrawField.getText()) > balance) {
                    JOptionPane.showMessageDialog(null, "Invalid amount!\n" +
                            "Amount is higher than account balance");
                } else if(whichBalance.equals("SavingsBalance") && Double.valueOf(withdrawField.getText())>20000) {
                    JOptionPane.showMessageDialog(null,"Cannot withdraw more than 20,000 in a single transaction in Savings Account");
                } else {

                    Double amount = Double.valueOf(withdrawField.getText());

                    //Date & Time
                    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = new Date();

                    SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
                    Date time = new Date();

                    //Enter details in Transaction table
                    String q = "insert into Transactions values('" + cardNo + "','" + account + "','" + "Withdraw" + "','" + amount + "','" + formatDate.format(date) + "','" + formatTime.format(time) + "')";
                    c.statement.executeUpdate(q);

                    //Update balance in signUpTable
                    String update = "UPDATE SignUpTable SET " + whichBalance + " = '" + (balance - amount) + "' WHERE CardNo = '" + cardNo + "'";
                    c.statement.executeUpdate(update);

                    JOptionPane.showMessageDialog(null, "You have successfully withdrawn the amount");

                    setVisible(false);

                    if(account.equals("Savings")) {
                        new Savings(cardNo);
                    } else if(account.equals("Current")) {
                        new Current(cardNo);
                    }

                }

            }

        } catch(Exception E){
            System.out.println("ERROR:"+E.getMessage());
        }

    }
}
