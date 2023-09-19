package gateway.payment;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class SignUp extends JFrame implements ActionListener {
    
    JTextField nameField, mailField, aadharField, phoneField;
    JDateChooser dobField;
    JRadioButton male, female;
    JCheckBox savings, current, fd;
    JButton loginBt, submitBt;

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
        iconLabel.setBounds(230, 20, 65, 65);
        add(iconLabel);

        //Title
        JLabel title = new JLabel("Sign Up ");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Georgia", Font.PLAIN, 22));
        title.setBounds(320,30,400,40);
        add(title);


        JLabel details = new JLabel("Personal Details ");
        details.setForeground(Color.BLACK);
        details.setFont(new Font("Georgia", Font.PLAIN, 22));
        details.setBounds(255,100,600,30);
        add(details);
        
        //Name
        JLabel name = new JLabel("Name: ");
        name.setForeground(Color.BLACK);
        name.setFont(new Font("Garamond", Font.BOLD, 20));
        name.setBounds(100,150,90,30);
        add(name);

        nameField = new JTextField(15);
        nameField.setBounds(270, 150, 300, 30);
        nameField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(nameField);

        //DOB
        JLabel dob = new JLabel("Date of Birth: ");
        dob.setForeground(Color.BLACK);
        dob.setFont(new Font("Garamond", Font.BOLD, 20));
        dob.setBounds(100,190,150,30);
        add(dob);

        dobField = new JDateChooser();
        dobField.setBounds(270, 190, 300, 30);
        dobField.setForeground(new Color(105,105,105));
        add(dobField);

        //Gender
        JLabel gender = new JLabel("Gender: ");
        gender.setForeground(Color.BLACK);
        gender.setFont(new Font("Garamond", Font.BOLD, 20));
        gender.setBounds(100,230,150,30);
        add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Garamond", Font.BOLD, 20));
        male.setBackground(new Color(184,241,251));
        male.setBounds(270,230,90,30);
        add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Garamond", Font.BOLD, 20));
        female.setBackground(new Color(184,241,251));
        female.setBounds(470,230,90,30);
        add(female);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        //Mail
        JLabel mail = new JLabel("E-Mail: ");
        mail.setForeground(Color.BLACK);
        mail.setFont(new Font("Garamond", Font.BOLD, 20));
        mail.setBounds(100,270,90,30);
        add(mail);

        mailField = new JTextField(15);
        mailField.setBounds(270, 270, 300, 30);
        mailField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(mailField);

        //Aadhar No
        JLabel aadharNo = new JLabel("Aadhar No: ");
        aadharNo.setForeground(Color.BLACK);
        aadharNo.setFont(new Font("Garamond", Font.BOLD, 20));
        aadharNo.setBounds(100,310,150,30);
        add(aadharNo);

        aadharField = new JTextField(15);
        aadharField.setBounds(270, 310, 300, 30);
        aadharField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(aadharField);

        //Phone No
        JLabel phoneNo = new JLabel("Phone No.");
        phoneNo.setForeground(Color.BLACK);
        phoneNo.setFont(new Font("Garamond", Font.BOLD, 20));
        phoneNo.setBounds(100,350,150,30);
        add(phoneNo);

        JLabel info = new JLabel("(To be linked with the account)");
        info.setForeground(Color.DARK_GRAY);
        info.setFont(new Font("Garamond", Font.BOLD, 18));
        info.setBounds(40,370,300,30);
        add(info);

        JLabel countryCode = new JLabel("+91 ");
        Border blackline = BorderFactory.createLineBorder(Color.black);
        countryCode.setBorder(blackline);
        countryCode.setFont(new Font("Garamond", Font.BOLD, 20));
        countryCode.setBounds(270,350,40,30);
        add(countryCode);

        phoneField = new JTextField(15);
        phoneField.setBounds(310, 350, 260, 30);
        phoneField.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(phoneField);

        //Type of account to be created
        JLabel accountType = new JLabel("Account Type");
        accountType.setForeground(Color.BLACK);
        accountType.setFont(new Font("Garamond", Font.BOLD, 20));
        accountType.setBounds(100,420,150,30);
        add(accountType);

        savings = new JCheckBox("Savings Account");
        savings.setFont(new Font("Garamond", Font.BOLD, 20));
        savings.setBackground(new Color(184,241,251));
        savings.setBounds(270,420,180,30);
        add(savings);

        current = new JCheckBox("Current Account");
        current.setFont(new Font("Garamond", Font.BOLD, 20));
        current.setBackground(new Color(184,241,251));
        current.setBounds(270,450,180,30);
        add(current);

        fd = new JCheckBox("Fixed Deposit");
        fd.setFont(new Font("Garamond", Font.BOLD, 20));
        fd.setBackground(new Color(184,241,251));
        fd.setBounds(270,480,180,30);
        add(fd);

        //Login button
        loginBt = new JButton("Login");
        loginBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        loginBt.setForeground(Color.BLACK);
        loginBt.setBackground(new Color(100,149,237));
        loginBt.setBounds(100, 510, 130, 30);
        loginBt.addActionListener(this);
        add(loginBt);

        //Continue button
        submitBt = new JButton("Submit");
        submitBt.setFont(new Font("Georgia", Font.PLAIN, 20));
        submitBt.setForeground(Color.BLACK);
        submitBt.setBackground(new Color(125, 215, 161));
        submitBt.setBounds(470, 510, 130, 30);
        submitBt.addActionListener(this);
        add(submitBt);

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
        String name = nameField.getText();
        String dob = ((JTextField)dobField.getDateEditor().getUiComponent()).getText();
        String gender = null;
        String email = mailField.getText();
        String aadhar = aadharField.getText();
        String phoneno = phoneField.getText();
        String accountType = null;


        if(male.isSelected()) {
            gender = "Male";
        } else if(female.isSelected()){
            gender = "Female";
        }

        if(savings.isSelected()) {
            accountType = "Savings";
        }
        if(current.isSelected()) {
            accountType = "Current";
        }
        if(fd.isSelected()) {
            accountType = "Fixed Deposit";
        }

        //Data validation
        try {
            if (e.getSource() == submitBt) {
                if (nameField.getText().isEmpty() || ((JTextField) dobField.getDateEditor().getUiComponent()).getText().isEmpty() || phoneField.getText().isEmpty() || mailField.getText().isEmpty() || aadharField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fill all the fields");
                } else if (!male.isSelected() && !female.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please select your gender!");
                } else if (aadharField.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "Please enter aadhar no. without spaces!");
                } else if (aadharField.getText().length() != 12 || !aadharField.getText().matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Invalid aadhar no.!");
                } else if (phoneField.getText().length() != 10 || !phoneField.getText().matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Invalid phone no.!");
                } else if (phoneField.getText().length() != 10 || !phoneField.getText().matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(null, "Invalid phone no.!");
                } else if (!savings.isSelected() && !current.isSelected() && !fd.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please choose atleast one type of account");
                } else {
                    //Check for Existing Account
                    Connect c = new Connect();
                    String SQL = "SELECT * FROM SignUpTable WHERE Aadhar = '" + aadharField.getText() + "' ";
                    ResultSet rs = c.statement.executeQuery(SQL);

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "Account already exists! Please Login.");
                    } else {

                        String q = "insert into SignUpTable values('" + name + "','" + dob + "','" + gender + "','" + email + "','" + aadhar + "','" + phoneno + "','" + accountType + "')";
                        c.statement.executeUpdate(q);
                        new GenerateCardNo();
                        setVisible(false);
                    }
                }
            } else if (e.getSource()==loginBt) {
                new Login();
                setVisible(false);
            }
        } catch(Exception E) {
            System.out.println("ERROR: "+E.getMessage());
        }
    }

    public static void main(String[] args) {
        new SignUp();

    }


}
