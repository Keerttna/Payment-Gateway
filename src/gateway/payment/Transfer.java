package gateway.payment;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transfer extends JFrame implements ActionListener{
    Connect c = new Connect();
    String senderCard;
    String senderName;
    String senderAccountType = "", receiverAccountType = "";
    String senderWhichBalance = "", receiverWhichBalance = "";
    JTextField transferAmtField;
    JTextField receiverCardField;
    JButton submitBt, backBt;

    JRadioButton savingsBt, currentBt;
    Transfer(String cardNumber, String accountType){

        senderCard = cardNumber;
        senderAccountType = accountType;
        setTitle("Transfer");
        setLayout(null);
        setSize(700,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            String SQL = "SELECT Name FROM SignUpTable WHERE CardNo = '" + senderCard + "' ";
            ResultSet rs = c.statement.executeQuery(SQL);
            if(rs.next()) {
                senderName = rs.getString("Name");
            }
        } catch(Exception E) {
            System.out.println("ERROR: "+E.getMessage());
        }

        //Card holder's name
        JLabel cardHolder = new JLabel("Name: "+senderName);
        cardHolder.setForeground(Color.WHITE);
        cardHolder.setFont(new Font("Garamond", Font.BOLD, 20));
        cardHolder.setBounds(30, 30, 200, 40);
        add(cardHolder);

        //Card No
        String lastFourDigits = senderCard.substring(14);
        String maskedDigits = "XXXX XXXX XXXX ";
        String maskedCardNumber = maskedDigits + lastFourDigits;

        JLabel displayCardNo = new JLabel("Card No.: "+maskedCardNumber);
        displayCardNo.setForeground(Color.WHITE);
        displayCardNo.setFont(new Font("Garamond", Font.BOLD, 20));
        displayCardNo.setBounds(30, 60, 500, 40);
        add(displayCardNo);

        //Transfer amount
        JLabel transfer = new JLabel("Enter the amount to be transferred:");
        transfer.setForeground(Color.WHITE);
        transfer.setFont(new Font("Garamond", Font.BOLD, 22));
        transfer.setBounds(30, 110, 600, 40);
        add(transfer);

        JLabel Rs = new JLabel("Rs.");
        Rs.setBackground(Color.WHITE);
        Rs.setOpaque(true);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        Rs.setBorder(blackline);
        Rs.setFont(new Font("Garamond", Font.BOLD, 20));
        Rs.setBounds(30,170,40,31);
        add(Rs);

        transferAmtField = new JTextField(15);
        transferAmtField.setBounds(70, 170, 250, 30);
        transferAmtField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(transferAmtField);

        //Reciever's card no
        JLabel receiverCard = new JLabel("Enter the card number of the receiver:");
        receiverCard.setForeground(Color.WHITE);
        receiverCard.setFont(new Font("Garamond", Font.BOLD, 22));
        receiverCard.setBounds(30, 210, 600, 40);
        add(receiverCard);

        receiverCardField = new JTextField(15);
        receiverCardField.setBounds(30, 270, 290, 30);
        receiverCardField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(receiverCardField);

        //Receiver's Account Type
        JLabel receiverAccType = new JLabel("Enter the account type of the receiver:");
        receiverAccType.setForeground(Color.WHITE);
        receiverAccType.setFont(new Font("Garamond", Font.BOLD, 22));
        receiverAccType.setBounds(30, 315, 600, 40);
        add(receiverAccType);

        savingsBt = new JRadioButton("Savings");
        savingsBt.setFont(new Font("Garamond", Font.BOLD, 20));
        savingsBt.setBackground(Color.WHITE);
        savingsBt.setBounds(50,365,90,30);
        add(savingsBt);

        currentBt = new JRadioButton("Current");
        currentBt.setFont(new Font("Garamond", Font.BOLD, 20));
        currentBt.setBackground(Color.WHITE);
        currentBt.setBounds(200,365,95,30);
        add(currentBt);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(savingsBt);
        genderGroup.add(currentBt);


        //Submit Button
        submitBt = new JButton("Transfer");
        submitBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        submitBt.setForeground(Color.BLACK);
        submitBt.setBackground(new Color(125, 215, 161));
        submitBt.setBounds(95, 420, 150, 30);
        submitBt.addActionListener(this);
        add(submitBt);

        //Back Button
        backBt = new JButton("Back");
        backBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        backBt.setForeground(Color.WHITE);
        backBt.setBackground(new Color(100,149,237));
        backBt.setBounds(510, 420, 150, 30);
        backBt.addActionListener(this);
        add(backBt);



        ImageIcon bgImgIcon = new ImageIcon(ClassLoader.getSystemResource("DepositBg.jpg"));
        Image scaledBgImg = bgImgIcon.getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH);
        ImageIcon scaledBgIcon = new ImageIcon(scaledBgImg);
        JLabel bgLabel = new JLabel(scaledBgIcon);
        bgLabel.setBounds(0, 0, 700, 500);
        add(bgLabel);

        setVisible(true);

    }
    public static void main(String[] args) {

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String receiverAccountCheck = "";
            boolean check = false;
            if(e.getSource() == submitBt) {
                if(savingsBt.isSelected()) {
                    receiverAccountType = "Savings";
                    receiverWhichBalance = "SavingsBalance";
                } else {
                    receiverAccountType = "Current";
                    receiverWhichBalance = "CurrentBalance";
                }


                //Check if receiver's card is in database
               String checkCard = "SELECT * FROM SignUpTable WHERE CardNo = '" + receiverCardField.getText() + "' ";
               ResultSet checkCardRs = c.statement.executeQuery(checkCard);

               //Check if the receiver has the account type
               if(!checkCardRs.next()) {
                   JOptionPane.showMessageDialog(null, "Receiver's card number is invalid!");
               } else if(receiverAccountType.equals("Savings")){
                   String checkAccount = "SELECT SavingsAccount FROM SignUpTable WHERE CardNo = '" + receiverCardField.getText() + "'";
                   ResultSet checkAccountRs = c.statement.executeQuery(checkAccount);
                   if(checkAccountRs.next()){
                       receiverAccountCheck = checkAccountRs.getString("SavingsAccount");
                       if(receiverAccountCheck.equals("No")) {
                           JOptionPane.showMessageDialog(null,"The receiver does not have a savings account");
                       } else {
                           check = true;
                       }
                   }
               } else if(receiverAccountType.equals("Current")){
                   //JOptionPane.showMessageDialog(null,"Test");
                   String checkAccount = "SELECT CurrentAccount FROM SignUpTable WHERE CardNo = '" + receiverCardField.getText() + "'";
                   ResultSet checkAccountRs = c.statement.executeQuery(checkAccount);
                   if(checkAccountRs.next()){
                       receiverAccountCheck = checkAccountRs.getString("CurrentAccount");
                       if(receiverAccountCheck.equals("No")) {
                           JOptionPane.showMessageDialog(null,"The receiver does not have a current account");
                       } else {
                           check = true;
                       }
                   }
               }
               if(check) {
                   //Date & Time
                   SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
                   Date date = new Date();

                   SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
                   Date time = new Date();

                   //Update (withdraw) balance in sender's SignUp table
                   Double senderRs = 0.00;

                   if (senderAccountType.equals("Savings")) {
                       senderWhichBalance = "SavingsBalance";
                   } else if(senderAccountType.equals("Current")) {
                       senderWhichBalance = "CurrentBalance";
                   }

                   String senderSQL = "SELECT " + senderWhichBalance + " FROM SignUpTable WHERE CardNo = '" + senderCard + "'";
                   ResultSet Srs = c.statement.executeQuery(senderSQL);
                   if(Srs.next()) {
                       senderRs = Double.valueOf(Srs.getString(senderWhichBalance));
                   }

                   Double senderBalanceAmt = senderRs;

                   if(!transferAmtField.getText().matches("[0-9]+") || Double.valueOf(transferAmtField.getText()) <= 0) {
                       JOptionPane.showMessageDialog(null,"Invalid amount!");
                   } else if(Double.valueOf(transferAmtField.getText()) > senderBalanceAmt) {
                       JOptionPane.showMessageDialog(null, "Invalid amount!\n" +
                               "Amount is higher than account balance");
                   } else {

                       senderBalanceAmt -= Double.valueOf(transferAmtField.getText());

                       String senderUpdate = "UPDATE SignUpTable SET " + senderWhichBalance + " = '" + senderBalanceAmt + "' WHERE CardNo = '" + senderCard + "'";
                       c.statement.executeUpdate(senderUpdate);

                       //Update sender's transaction table
                       String senderQ = "insert into Transactions values('" + senderCard + "','" + senderAccountType + "','" + "Debited" + "','" + transferAmtField.getText() + "','" + formatDate.format(date) + "','" + formatTime.format(time) + "')";
                       c.statement.executeUpdate(senderQ);


                       //Update (Deposit) receiver's SignUp table
                       Double receiverRs = 0.00;

                       String receiverSQL = "SELECT " + receiverWhichBalance + " FROM SignUpTable WHERE CardNo = '" + receiverCardField.getText() + "'";
                       ResultSet Rrs = c.statement.executeQuery(receiverSQL);
                       if(Rrs.next()) {
                           receiverRs = Double.valueOf(Rrs.getString(receiverWhichBalance));
                       }
                       Double revceiverBalanceAmt = receiverRs;
                       revceiverBalanceAmt += Double.valueOf(transferAmtField.getText());

                       String receiverUpdate = "UPDATE SignUpTable SET " + receiverWhichBalance + " = '" + revceiverBalanceAmt + "' WHERE CardNo = '" + receiverCardField.getText() + "'";
                       c.statement.executeUpdate(receiverUpdate);

                       //Update receiver's transaction table
                       String receiverQ = "insert into Transactions values('" + receiverCardField.getText() + "','" + receiverAccountType + "','" + "Credited" + "','" + transferAmtField.getText() + "','" + formatDate.format(date) + "','" + formatTime.format(time) + "')";
                       c.statement.executeUpdate(receiverQ);

                       JOptionPane.showMessageDialog(null,"Amount transferred successfully !");

                       if(senderAccountType.equals("Savings")) {
                           new Savings(senderCard);
                       } else {
                           new Current(senderCard);
                       }

                       setVisible(false);
                   }

               }
           } else {
               if(senderAccountType.equals("Savings")) {
                   new Savings(senderCard);
               } else {
                   new Current(senderCard);
               }

               setVisible(false);

           }
        } catch(Exception E) {
            System.out.println("ERROR: "+E.getMessage());
        }

    }
}
