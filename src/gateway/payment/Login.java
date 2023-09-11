package gateway.payment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    JLabel welcome,cardNo,pin;
    JTextField cardField;
    JPasswordField pinField;
    JButton login, signup, clear;
    Login() {

        setTitle("Payment Gateway");
        setLayout(null);
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Welcome msg
        welcome = new JLabel("Welcome to the PayGateway");
        welcome.setForeground(Color.WHITE);
        welcome.setFont(new Font("Georgia", Font.BOLD, 24));
        welcome.setBounds(100, 50, 450, 40);
        add(welcome);

        //CardNo
        cardNo = new JLabel("Card No:");
        cardNo.setFont(new Font("Georgia", Font.BOLD, 20));
        cardNo.setForeground(Color.WHITE);
        cardNo.setBounds(65, 140, 375, 30);
        add(cardNo);

        cardField = new JTextField(15);
        cardField.setBounds(180, 140, 230, 30);
        cardField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(cardField);

        //PIN
        pin = new JLabel("PIN:");
        pin.setFont(new Font("Georgia", Font.BOLD, 20));
        pin.setForeground(Color.WHITE);
        pin.setBounds(65, 200, 375, 30);
        add(pin);

        pinField = new JPasswordField(15);
        pinField.setBounds(180, 200, 230, 30);
        pinField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(pinField);

        //Login button
        login = new JButton("Login");
        login.setFont(new Font("Georgia", Font.PLAIN, 20));
        login.setForeground(Color.BLACK);
        login.setBackground(Color.gray);
        login.setBounds(100, 290, 100, 30);
        login.addActionListener(this);
        add(login);

        //Clear button
        clear = new JButton("Clear");
        clear.setFont(new Font("Georgia", Font.PLAIN, 20));
        clear.setForeground(Color.BLACK);
        clear.setBackground(Color.gray);
        clear.setBounds(260, 290, 100, 30);
        clear.addActionListener(this);
        add(clear);

        //SignUp button
        signup = new JButton("Sign Up");
        signup.setFont(new Font("Georgia", Font.PLAIN, 20));
        signup.setForeground(Color.BLACK);
        signup.setBackground(Color.gray);
        signup.setBounds(100, 350, 265, 30);
        signup.addActionListener(this);
        add(signup);

        //Add background
        ImageIcon bgImgIcon = new ImageIcon(ClassLoader.getSystemResource("LoginBg.jpg"));
        Image scaledBgImg = bgImgIcon.getImage().getScaledInstance(750, 500, Image.SCALE_SMOOTH);
        ImageIcon scaledBgIcon = new ImageIcon(scaledBgImg);
        JLabel bgLabel = new JLabel(scaledBgIcon);
        bgLabel.setBounds(0, 0, 750, 500);
        add(bgLabel);


        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource()==login){

            }else if(e.getSource()== clear) {
                cardField.setText("");
                pinField.setText("");

            }else{

            }

        }catch (Exception E) {
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Login loginPage = new Login();

    }

}
