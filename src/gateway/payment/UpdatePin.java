package gateway.payment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class UpdatePin extends JFrame implements ActionListener {

    JPasswordField oldPinField,newPinField,confirmField;
    JButton okBt,cancelBt;
    String cardNo;
    Connect c = new Connect();
    UpdatePin(String cardNumber){

        cardNo = cardNumber;

        setTitle("Update PIN");
        setLayout(null);
        setSize(500,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Title
        JLabel title = new JLabel("Update the PIN");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Georgia", Font.ITALIC, 30));
        title.setBounds(80, 50, 300, 40);
        add(title);

        //Old PIN
        JLabel oldPin = new JLabel("Old PIN:");
        oldPin.setForeground(Color.WHITE);
        oldPin.setFont(new Font("Georgia", Font.PLAIN, 20));
        oldPin.setBounds(30, 150, 200, 40);
        add(oldPin);

        oldPinField = new JPasswordField(15);
        oldPinField.setBounds(180, 150, 100, 30);
        oldPinField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(oldPinField);

        //New PIN
        JLabel newPin = new JLabel("New PIN:");
        newPin.setForeground(Color.WHITE);
        newPin.setFont(new Font("Georgia", Font.PLAIN, 20));
        newPin.setBounds(30, 190, 200, 40);
        add(newPin);

        newPinField = new JPasswordField(15);
        newPinField.setBounds(180, 190, 100, 30);
        newPinField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(newPinField);

        //Confirm PIN
        JLabel confirmPin = new JLabel("Confirm PIN:");
        confirmPin.setForeground(Color.WHITE);
        confirmPin.setFont(new Font("Georgia", Font.PLAIN, 20));
        confirmPin.setBounds(30, 230, 200, 40);
        add(confirmPin);

        confirmField = new JPasswordField(15);
        confirmField.setBounds(180, 230, 100, 30);
        confirmField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(confirmField);

        //Okay button
        okBt = new JButton("OK");
        okBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        okBt.setForeground(Color.BLACK);
        okBt.setBounds(100, 300, 100, 30);
        okBt.addActionListener(this);
        add(okBt);

        //Cancel button
        cancelBt = new JButton("Cancel");
        cancelBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        cancelBt.setForeground(Color.BLACK);
        cancelBt.setBounds(250, 300, 100, 30);
        cancelBt.addActionListener(this);
        add(cancelBt);


        //Add background
        ImageIcon bgImgIcon = new ImageIcon(ClassLoader.getSystemResource("UpdatePinBg.jpg"));
        Image scaledBgImg = bgImgIcon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        ImageIcon scaledBgIcon = new ImageIcon(scaledBgImg);
        JLabel bgLabel = new JLabel(scaledBgIcon);
        bgLabel.setBounds(0, 0, 500, 500);
        add(bgLabel);

        setVisible(true);


    }
    public static void main(String[] args) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==okBt) {
            try {
                //Check if the old pin is correct
                String checkPin = "SELECT PIN FROM SignUpTable WHERE CardNo = '" + cardNo + "' ";
                ResultSet checkPinRs = c.statement.executeQuery(checkPin);

                if(checkPinRs.next()) {
                    String pinFromDB = checkPinRs.getString("PIN");

                    if(pinFromDB.equals(oldPinField.getText())) {
                        //Check if new pin is valid
                        if (newPinField.getText().length() != 4 || !newPinField.getText().matches("[0-9]+")) {
                            JOptionPane.showMessageDialog(null, "The new PIN entered is invalid!\n" +
                                    "The PIN should contain only 4 digits");
                        } else {
                            //Check if the new pin is confirmed
                            if (newPinField.getText().equals(confirmField.getText())) {
                                String update = "UPDATE SignUpTable SET PIN = '" + confirmField.getText() + "' WHERE CardNo = '" + cardNo + "' ";
                                c.statement.executeUpdate(update);

                                JOptionPane.showMessageDialog(null, "You have successfully updated the PIN");

                                new Login();
                            }
                            else {
                                JOptionPane.showMessageDialog(null,"New PIN and Confirm PIN doesn't match");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"Old PIN is incorrect!");
                    }
                }

            } catch (Exception E) {
                System.out.println("ERROR: "+E.getMessage());
            }


        }
        else {
            new MainPage(cardNo);
        }

    }
}
