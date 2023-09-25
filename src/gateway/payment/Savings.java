package gateway.payment;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Savings extends JFrame implements ActionListener {
    Connect c = new Connect();
    String cardNo,name;
    Double balance = 0.00;
    JButton depositBt,withdrawBt,transferBt,miniStatementBt, mainPgBt;
    Savings(String cardNumber){
        cardNo = cardNumber;
        setTitle("Savings Page");
        setLayout(null);
        setSize(700,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //User Icon
        ImageIcon userIcon = new ImageIcon(ClassLoader.getSystemResource("SavingsUserIcon.png"));
        Image scaledUserImg = userIcon.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
        ImageIcon scaledUserIcon = new ImageIcon(scaledUserImg);
        JLabel userLabel = new JLabel(scaledUserIcon);
        userLabel.setBounds(260, 30, 65, 65);
        add(userLabel);

        //Card Holder's Name
        try {
            //Connecting DB
            String SQL = "SELECT Name FROM SignUpTable WHERE CardNo = '" + cardNo + "' ";
            ResultSet rs = c.statement.executeQuery(SQL);
            if(rs.next()) {
                name = rs.getString("Name");
            }
        } catch(Exception E) {
            System.out.println("ERROR: "+E.getMessage());
        }


        JLabel cardHolder = new JLabel("Name: "+name);
        cardHolder.setForeground(Color.BLACK);
        cardHolder.setFont(new Font("Garamond", Font.BOLD, 18));
        cardHolder.setBounds(375, 40, 600, 40);
        add(cardHolder);

        //Card No
        String lastFourDigits = cardNo.substring(14);
        String maskedDigits = "XXXX XXXX XXXX ";
        String maskedCardNumber = maskedDigits + lastFourDigits;

        JLabel displayCardNo = new JLabel("Card No.: "+maskedCardNumber);
        displayCardNo.setForeground(Color.BLACK);
        displayCardNo.setFont(new Font("Garamond", Font.BOLD, 18));
        displayCardNo.setBounds(375, 80, 600, 40);
        add(displayCardNo);

        //Date and Time
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        JLabel displayDate = new JLabel("Date: "+formatDate.format(date));
        displayDate.setForeground(Color.BLACK);
        displayDate.setFont(new Font("Garamond", Font.BOLD, 18));
        displayDate.setBounds(50, 40, 600, 40);
        add(displayDate);

        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
        Date time = new Date();

        JLabel displayTime = new JLabel("Date: "+formatTime.format(time));
        displayTime.setForeground(Color.BLACK);
        displayTime.setFont(new Font("Garamond", Font.BOLD, 18));
        displayTime.setBounds(50, 80, 600, 40);
        add(displayTime);

        //Max Transaction
        JLabel maxTransaction = new JLabel("Maximum Transaction Limit for a day : Rs.50,000");
        maxTransaction.setForeground(Color.BLACK);
        maxTransaction.setFont(new Font("Garamond", Font.BOLD, 18));
        maxTransaction.setBounds(50, 160, 600, 40);
        add(maxTransaction);

        //Current Balance
        JLabel displayBalance = new JLabel("Balance : ");
        displayBalance.setForeground(Color.BLACK);
        displayBalance.setFont(new Font("Garamond", Font.BOLD, 18));
        displayBalance.setBounds(50, 200, 80, 40);
        add(displayBalance);

        JLabel balanceField = new JLabel("  Rs. "+balance);
        balanceField.setBackground(Color.WHITE);
        balanceField.setOpaque(true);
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        balanceField.setBorder(blackline);
        balanceField.setForeground(Color.BLACK);
        balanceField.setFont(new Font("Garamond", Font.BOLD, 18));
        balanceField.setBounds(150, 205, 200, 30);
        add(balanceField);

        //Deposit Button
        depositBt = new JButton("Deposit");
        depositBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        depositBt.setForeground(Color.BLACK);
        depositBt.setBackground(Color.gray);
        depositBt.setBounds(410, 205, 200, 30);
        depositBt.addActionListener(this);
        add(depositBt);

        //Withdraw Button
        withdrawBt = new JButton("Withdraw");
        withdrawBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        withdrawBt.setForeground(Color.BLACK);
        withdrawBt.setBackground(Color.gray);
        withdrawBt.setBounds(410, 255, 200, 30);
        withdrawBt.addActionListener(this);
        add(withdrawBt);

        //Transfer Button
        transferBt = new JButton("Transfer");
        transferBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        transferBt.setForeground(Color.BLACK);
        transferBt.setBackground(Color.gray);
        transferBt.setBounds(410, 305, 200, 30);
        transferBt.addActionListener(this);
        add(transferBt);

        //Mini-Statement Button
        miniStatementBt = new JButton("Mini-Statement");
        miniStatementBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        miniStatementBt.setForeground(Color.BLACK);
        miniStatementBt.setBackground(Color.gray);
        miniStatementBt.setBounds(410, 355, 200, 30);
        miniStatementBt.addActionListener(this);
        add(miniStatementBt);

        //Main Page Button
        mainPgBt = new JButton("Back");
        mainPgBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        mainPgBt.setForeground(Color.WHITE);
        mainPgBt.setBackground(new Color(100,149,237));
        mainPgBt.setBounds(435, 415, 130, 30);
        mainPgBt.addActionListener(this);
        add(mainPgBt);

        //Transaction Icon
        ImageIcon TransactIcon = new ImageIcon(ClassLoader.getSystemResource("TransactionIcon.png"));
        Image scaledTransactImg = TransactIcon.getImage().getScaledInstance(390, 340, Image.SCALE_SMOOTH);
        ImageIcon scaledTransactIcon = new ImageIcon(scaledTransactImg);
        JLabel transactLabel = new JLabel(scaledTransactIcon);
        transactLabel.setBounds(35, 230, 390, 340);
        add(transactLabel);

        //Card Icon
        ImageIcon cardIcon = new ImageIcon(ClassLoader.getSystemResource("SavingsCardIcon.png"));
        Image scaledCardImg = cardIcon.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
        ImageIcon scaledCardIcon = new ImageIcon(scaledCardImg);
        JLabel cardIconLabel = new JLabel(scaledCardIcon);
        cardIconLabel.setBounds(550, 450, 65, 65);
        add(cardIconLabel);

        //Background
        ImageIcon bgImgIcon = new ImageIcon(ClassLoader.getSystemResource("SavingsBg.jpg"));
        Image scaledBgImg = bgImgIcon.getImage().getScaledInstance(700, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledBgIcon = new ImageIcon(scaledBgImg);
        JLabel bgLabel = new JLabel(scaledBgIcon);
        bgLabel.setBounds(0, 0, 700, 600);
        add(bgLabel);


        setVisible(true);

    }

    public static void main(String[] args) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if(e.getSource() == mainPgBt) {
                new MainPage(cardNo);
                setVisible(false);
            }

        } catch(Exception E) {
            System.out.println("ERROR: "+E.getMessage());
        }

    }
}
