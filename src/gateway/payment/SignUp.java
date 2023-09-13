package gateway.payment;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SignUp extends JFrame implements ActionListener {
    
    JTextField nameField, mailField, aadharField, panField, phoneField;
    JDateChooser dobField;
    JRadioButton male, female;
    JButton loginBt, continueBt;

    SignUp() {
        setTitle("Application Form");
        setLayout(null);
        setSize(700,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Add icon
        ImageIcon afImgIcon = new ImageIcon(ClassLoader.getSystemResource("SignUpIcon.png"));
        Image scaledAfImg = afImgIcon.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
        ImageIcon scaledAfIcon = new ImageIcon(scaledAfImg);
        JLabel iconLabel = new JLabel(scaledAfIcon);
        iconLabel.setBounds(300, 20, 65, 65);
        add(iconLabel);

        //Title
        JLabel title = new JLabel("Sign Up ");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Georgia", Font.PLAIN, 22));
        title.setBounds(290,100,400,40);
        add(title);


        JLabel details = new JLabel("Personal Details ");
        details.setForeground(Color.BLACK);
        details.setFont(new Font("Georgia", Font.PLAIN, 22));
        details.setBounds(255,150,600,30);
        add(details);
        
        //Name
        JLabel name = new JLabel("Name: ");
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Garamond", Font.BOLD, 20));
        name.setBounds(100,200,90,30);
        add(name);

        nameField = new JTextField(15);
        nameField.setBounds(270, 200, 300, 30);
        nameField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(nameField);

        //DOB
        JLabel dob = new JLabel("Date of Birth: ");
        dob.setForeground(Color.BLACK);
        dob.setFont(new Font("Garamond", Font.BOLD, 20));
        dob.setBounds(100,240,150,30);
        add(dob);

        dobField = new JDateChooser();
        dobField.setBounds(270, 240, 300, 30);
        dobField.setForeground(new Color(105,105,105));
        add(dobField);

        //Gender
        JLabel gender = new JLabel("Gender: ");
        gender.setForeground(Color.BLACK);
        gender.setFont(new Font("Garamond", Font.BOLD, 20));
        gender.setBounds(100,280,150,30);
        add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Garamond", Font.BOLD, 20));
        male.setBackground(new Color(184,241,251));
        male.setBounds(270,280,90,30);
        add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Garamond", Font.BOLD, 20));
        female.setBackground(new Color(184,241,251));
        female.setBounds(470,280,90,30);
        add(female);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        //Mail
        JLabel mail = new JLabel("E-Mail: ");
        mail.setForeground(Color.BLACK);
        mail.setFont(new Font("Garamond", Font.BOLD, 20));
        mail.setBounds(100,320,90,30);
        add(mail);

        mailField = new JTextField(15);
        mailField.setBounds(270, 320, 300, 30);
        mailField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(mailField);

        //Aadhar No
        JLabel aadharNo = new JLabel("Aadhar No: ");
        aadharNo.setForeground(Color.BLACK);
        aadharNo.setFont(new Font("Garamond", Font.BOLD, 20));
        aadharNo.setBounds(100,360,150,30);
        add(aadharNo);

        aadharField = new JTextField(15);
        aadharField.setBounds(270, 360, 300, 30);
        aadharField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(aadharField);

        //PAN No
        JLabel panNo = new JLabel("PAN No: ");
        panNo.setForeground(Color.BLACK);
        panNo.setFont(new Font("Garamond", Font.BOLD, 20));
        panNo.setBounds(100,400,150,30);
        add(panNo);

        panField = new JTextField(15);
        panField.setBounds(270, 400, 300, 30);
        panField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(panField);

        //Phone No
        JLabel phoneNo = new JLabel("Phone No.");
        phoneNo.setForeground(Color.BLACK);
        phoneNo.setFont(new Font("Garamond", Font.BOLD, 20));
        phoneNo.setBounds(100,440,150,30);
        add(phoneNo);

        JLabel info = new JLabel("(To be linked with the account)");
        info.setForeground(Color.DARK_GRAY);
        info.setFont(new Font("Garamond", Font.BOLD, 18));
        info.setBounds(40,460,300,30);
        add(info);

        phoneField = new JTextField(15);
        phoneField.setBounds(270, 440, 300, 30);
        phoneField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(phoneField);

        //Member info
        JLabel accountInfo = new JLabel("Already have an account?");
        accountInfo.setForeground(Color.BLACK);
        accountInfo.setFont(new Font("Garamond", Font.BOLD, 18));
        accountInfo.setBounds(40,510,300,30);
        add(accountInfo);

        //Login button
        loginBt = new JButton("Login");
        loginBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        loginBt.setForeground(Color.BLACK);
        loginBt.setBackground(new Color(184,241,251));
        loginBt.setBounds(250, 510, 100, 30);
        loginBt.addActionListener(this);
        add(loginBt);

        //Continue button
        continueBt = new JButton("Continue");
        continueBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        continueBt.setForeground(Color.BLACK);
        continueBt.setBackground(new Color(125, 215, 161));
        continueBt.setBounds(450, 510, 130, 30);
        continueBt.addActionListener(this);
        add(continueBt);

        //Add background
        ImageIcon bgImgIcon = new ImageIcon(ClassLoader.getSystemResource("SignUpBg.jpg"));
        Image scaledBgImg = bgImgIcon.getImage().getScaledInstance(700, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledBgIcon = new ImageIcon(scaledBgImg);
        JLabel bgLabel = new JLabel(scaledBgIcon);
        bgLabel.setBounds(0, 0, 700, 600);
        add(bgLabel);

        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }



    public static void main(String[] args) {
        new SignUp();

    }


}
