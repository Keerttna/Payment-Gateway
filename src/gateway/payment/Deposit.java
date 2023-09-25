package gateway.payment;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {
    Connect c = new Connect();
    String cardNo,name,account;
    JTextField depositField;
    JButton submitBt,savingsPgBt;
    Deposit( String cardNumber,String accountType){

        cardNo = cardNumber;
        account = accountType;
        setTitle("Deposit");
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

        //Deposit Column
        JLabel deposit = new JLabel("Enter the amount to be deposited:");
        deposit.setForeground(Color.WHITE);
        deposit.setFont(new Font("Garamond", Font.BOLD, 22));
        deposit.setBounds(30, 110, 600, 40);
        add(deposit);

        JLabel Rs = new JLabel("Rs.");
        Rs.setBackground(Color.WHITE);
        Rs.setOpaque(true);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        Rs.setBorder(blackline);
        Rs.setFont(new Font("Garamond", Font.BOLD, 20));
        Rs.setBounds(30,170,40,31);
        add(Rs);

        depositField = new JTextField(15);
        depositField.setBounds(70, 170, 250, 30);
        depositField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(depositField);

        //Submit Button
        submitBt = new JButton("Deposit");
        submitBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        submitBt.setForeground(Color.BLACK);
        submitBt.setBackground(new Color(125, 215, 161));
        submitBt.setBounds(100, 240, 150, 30);
        submitBt.addActionListener(this);
        add(submitBt);

        //Back Button
        savingsPgBt = new JButton("Back");
        savingsPgBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        savingsPgBt.setForeground(Color.WHITE);
        savingsPgBt.setBackground(new Color(100,149,237));
        savingsPgBt.setBounds(100, 305, 150, 30);
        savingsPgBt.addActionListener(this);
        add(savingsPgBt);

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
            if(e.getSource() == savingsPgBt) {
                new Savings(cardNo);
                setVisible(false);
            } else {

                if(!depositField.getText().matches("[0-9]+") || Double.valueOf(depositField.getText()) <= 0) {
                    JOptionPane.showMessageDialog(null,"Invalid amount!");
                } else {
                    Double amount = Double.valueOf(depositField.getText());
                    Double balance = 0.00;

                    //Date and Time
                    SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = new Date();

                    SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
                    Date time = new Date();

                    //Enter details in Transaction table
                    String q = "insert into Transactions values('" + cardNo + "','" + account + "','" + "Deposit" + "','" + amount + "','" + formatDate.format(date) + "','" + formatTime.format(time) + "')";
                    c.statement.executeUpdate(q);

                    //Update balance in signUpTable
                    String SQL = "SELECT SavingsBalance FROM SignUpTable WHERE CardNo = '" + cardNo + "' ";
                    ResultSet rs = c.statement.executeQuery(SQL);
                    if(rs.next()) {
                        balance = Double.valueOf(rs.getString("SavingsBalance"));
                    }

                    String update = "UPDATE SignUpTable SET SavingsBalance = '" + (balance+amount) + "' WHERE CardNo = '" + cardNo + "' ";
                    c.statement.executeUpdate(update);

                    JOptionPane.showMessageDialog(null, "You have successfully deposited the amount");

                    setVisible(false);
                    new Savings(cardNo);
                }

            }

        } catch(Exception E){
            System.out.println("ERROR:"+E.getMessage());
        }

    }
}
