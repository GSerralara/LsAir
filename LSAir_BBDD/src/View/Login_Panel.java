package View;

import Controller.MainController;

import javax.swing.*;
import java.awt.*;

public class Login_Panel extends JPanel {

    private JButton jbLogin;
    private JTextField jtfUsername;
    private JPasswordField jpfPassword;
    private JLabel jlUsername;
    private JLabel jlPassword;
    private JPanel jpMain;


    public Login_Panel(){

        jbLogin = new JButton("Login");
        jtfUsername = new JTextField();
        jpfPassword = new JPasswordField();
        jlUsername = new JLabel("Username");
        jlPassword = new JLabel("Password");

        jpMain = new JPanel();
        jpMain.setLayout(new GridLayout(3, 2));

        jpMain.add(jlUsername);
        jpMain.add(jtfUsername);
        jpMain.add(jlPassword);
        jpMain.add(jpfPassword);
        jpMain.add(jbLogin);

        this.add(jpMain);

    }

    public void registerButtonAction(MainController controller){
        jbLogin.addActionListener(controller);
    }

    public String getUsername(){
        return jtfUsername.getText();
    }

    public String getPassword(){
        return new String(jpfPassword.getPassword());

    }

    public void clear(){
        jtfUsername.setText("");
        jpfPassword.setText("");
    }
}
