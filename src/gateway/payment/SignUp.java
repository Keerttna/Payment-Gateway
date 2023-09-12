package gateway.payment;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;

public class SignUp extends JFrame {
    
    JTextField nameField, mailField;
    JDateChooser dobField;
    JRadioButton male, female;

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
        JLabel title = new JLabel("APPLICATION FORM ");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Georgia", Font.BOLD, 22));
        title.setBounds(200,100,400,40);
        add(title);

        // Page no.1
        JLabel page = new JLabel("Page 1 ");
        page.setForeground(Color.BLACK);
        page.setFont(new Font("Georgia", Font.BOLD, 20));
        page.setBounds(300,130,600,30);
        add(page);

        JLabel details = new JLabel("Personal Details ");
        details.setForeground(Color.BLACK);
        details.setFont(new Font("Garamond", Font.BOLD, 20));
        details.setBounds(245,160,600,30);
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

        //Name
        JLabel mail = new JLabel("E-Mail: ");
        mail.setForeground(Color.BLACK);
        mail.setFont(new Font("Garamond", Font.BOLD, 20));
        mail.setBounds(100,320,90,30);
        add(mail);

        mailField = new JTextField(15);
        mailField.setBounds(270, 320, 300, 30);
        mailField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(mailField);
















        //Add background
        ImageIcon bgImgIcon = new ImageIcon(ClassLoader.getSystemResource("SignUpBg.jpg"));
        Image scaledBgImg = bgImgIcon.getImage().getScaledInstance(700, 600, Image.SCALE_SMOOTH);
        ImageIcon scaledBgIcon = new ImageIcon(scaledBgImg);
        JLabel bgLabel = new JLabel(scaledBgIcon);
        bgLabel.setBounds(0, 0, 700, 600);
        add(bgLabel);

        setVisible(true);


    }



    public static void main(String[] args) {
        new SignUp();

    }
}
