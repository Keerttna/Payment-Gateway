package gateway.payment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class MainPage extends JFrame implements ActionListener {
    String name, checkSaving, checkCurrent, checkFd;
    JButton okBt, updateBt, loginPgBt;
    JRadioButton savingsBt, currentBt, fdButton;
    String cardNo;
    Connect c = new Connect();

    MainPage(String cardNumber){
        cardNo = cardNumber;
        setTitle("Main Page");
        setLayout(null);
        setSize(700,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        try {
            //Connecting db
            Connect c = new Connect();
            String SQL = "SELECT Name FROM SignUpTable WHERE CardNo = '" + cardNumber + "' ";

            ResultSet rs = c.statement.executeQuery(SQL);
            if(rs.next()) {
                name = rs.getString("Name");
            }
        } catch(Exception E) {
            System.out.println("ERROR: "+E.getMessage());
        }

        //Title
        JLabel title = new JLabel("Payment Gateway");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Georgia", Font.ITALIC, 34));
        title.setBounds(360, 50, 600, 40);
        add(title);

        //Welcome
        JLabel welcome = new JLabel("Hello, "+name);
        welcome.setForeground(Color.WHITE);
        welcome.setFont(new Font("Georgia", Font.PLAIN, 30));
        welcome.setBounds(400, 100, 600, 40);
        add(welcome);

        //Add icon
        ImageIcon emojiIcon = new ImageIcon(ClassLoader.getSystemResource("MainPgEmoji.png"));
        Image scaledEmojiImg = emojiIcon.getImage().getScaledInstance(100, 71, Image.SCALE_SMOOTH);
        ImageIcon scaledEmojiIcon = new ImageIcon(scaledEmojiImg);
        JLabel emojiLabel = new JLabel(scaledEmojiIcon);
        emojiLabel.setBounds(450, 140, 100, 71);
        add(emojiLabel);

        //Selection instruction
        JLabel select = new JLabel("Select the account to be \n" +
                "accessed:");
        select.setForeground(Color.WHITE);
        select.setFont(new Font("Georgia", Font.PLAIN, 20));
        select.setBounds(360, 215, 300, 40);
        add(select);

        //Add radiobutton
        savingsBt = new JRadioButton("Savings Account");
        savingsBt.setFont(new Font("Garamond", Font.BOLD, 20));
        savingsBt.setBounds(360,265,200,30);
        add(savingsBt);

        currentBt = new JRadioButton("Current Account");
        currentBt.setFont(new Font("Garamond", Font.BOLD, 20));
        currentBt.setBounds(360,305,200,30);
        add(currentBt);

        fdButton = new JRadioButton("Fixed Deposit");
        fdButton.setFont(new Font("Garamond", Font.BOLD, 20));
        fdButton.setBounds(360,345,200,30);
        add(fdButton);

        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(savingsBt);
        accountGroup.add(currentBt);
        accountGroup.add(fdButton);

        if(fdButton.isSelected()) {
            JOptionPane.showMessageDialog(null,"Hi");
        }

        //Okay button
        okBt = new JButton("OK");
        okBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        okBt.setForeground(Color.BLACK);
        okBt.setBounds(400, 400, 100, 30);
        okBt.addActionListener(this);
        add(okBt);

        //Update pin button
        updateBt = new JButton("Update PIN");
        updateBt.setFont(new Font("Georgia", Font.ITALIC, 20));
        updateBt.setForeground(Color.BLACK);
        updateBt.setBounds(380, 470, 150, 30);
        updateBt.addActionListener(this);
        add(updateBt);

        loginPgBt = new JButton("Back");
        loginPgBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        loginPgBt.setForeground(Color.WHITE);
        loginPgBt.setBackground(new Color(100,149,237));
        loginPgBt.setBounds(50, 500, 130, 30);
        loginPgBt.addActionListener(this);
        add(loginPgBt);



        //Add background
        ImageIcon bgImgIcon = new ImageIcon(ClassLoader.getSystemResource("MainPg.jpg"));
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
        if(e.getSource()==okBt) {
            //Check if user has the account type selected
            try {

                if(savingsBt.isSelected()) {
                    String SQL = "SELECT SavingsAccount FROM SignUpTable WHERE CardNo = '" + cardNo + "' ";
                    ResultSet rs = c.statement.executeQuery(SQL);
                    if(rs.next()) {
                        checkSaving = rs.getString("SavingsAccount");
                    }
                    if(checkSaving.equals("Yes")) {
                        new Savings(cardNo);
                        setVisible(false);

                    } else {
                        Object[] options = {"Yes", "No"};
                        int selection = JOptionPane.showOptionDialog(null, "You do not have a Savings Account \n" +
                                "Would you like to open one?", "Message", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if(selection==0) {
                            String update = "UPDATE SignUpTable SET SavingsAccount = 'Yes' WHERE CardNo = '" + cardNo + "' ";
                            c.statement.executeUpdate(update);

                            JOptionPane.showMessageDialog(null,"You have successfully opened a Savings account");
                        }
                    }
                }

                else if(currentBt.isSelected()) {
                    String SQL = "SELECT CurrentAccount FROM SignUpTable WHERE CardNo = '" + cardNo + "' ";
                    ResultSet rs = c.statement.executeQuery(SQL);
                    if (rs.next()) {
                        checkCurrent = rs.getString("CurrentAccount");
                    }
                    if (checkCurrent.equals("Yes")) {
                        //Open the corresponding class
                        new Current(cardNo);
                        setVisible(false);

                    } else {
                        Object[] options = {"Yes", "No"};
                        int selection = JOptionPane.showOptionDialog(null, "You do not have a Current Account \n" +
                                "Would you like to open one?", "Message", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if(selection==0) {
                            String update = "UPDATE SignUpTable SET CurrentAccount = 'Yes' WHERE CardNo = '" + cardNo + "' ";
                            c.statement.executeUpdate(update);

                            JOptionPane.showMessageDialog(null, "You have successfully opened a Current account");
                        }
                    }
                }

                else if(fdButton.isSelected()){
                    String SQL = "SELECT fdAccount FROM SignUpTable WHERE CardNo = '" + cardNo + "' ";
                    ResultSet rs = c.statement.executeQuery(SQL);
                    if (rs.next()) {
                        checkFd = rs.getString("fdAccount");
                    }
                    if (checkFd.equals("Yes")) {

                    } else {
                        Object[] options = {"Yes", "No"};
                        int selection = JOptionPane.showOptionDialog(null, "You do not have a FD Account \n" +
                                "Would you like to open one?", "Message", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                        if(selection==0) {
                            String update = "UPDATE SignUpTable SET fdAccount = 'Yes' WHERE CardNo = '" + cardNo + "' ";
                            c.statement.executeUpdate(update);

                            JOptionPane.showMessageDialog(null, "You have successfully opened a Fixed Deposit");
                        }
                    }

                }

            } catch(Exception E) {
                System.out.println("ERROR: "+E.getMessage());
            }

        } else if(e.getSource()==updateBt){
            //Updating PIN
            try {
                Object[] options = {"Yes", "No"};
                int selection = JOptionPane.showOptionDialog(null, "Are you sure you want to update the PIN ?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

                if(selection==0) {
                    setVisible(false);
                    new UpdatePin(cardNo);
                }

            } catch (Exception E) {
                System.out.println("ERROR: "+E.getMessage());
            }
        } else {
            new Login();
            setVisible(false);
        }

    }
}
