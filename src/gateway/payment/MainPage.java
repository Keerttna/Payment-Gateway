package gateway.payment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class MainPage extends JFrame implements ActionListener {
    String name;
    JButton okBt, updateBt;
    JRadioButton savingsBt, currentBt, fdButton;

    MainPage(String cardNumber){
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

    }
}
