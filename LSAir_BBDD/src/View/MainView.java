package View;

import Controller.MainController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainView extends JFrame {

    private JLabel jlTitle;
    private Login_Panel login_panel;
    private Main_Panel main_panel;

    public MainView(){
        setSize(250, 200);
        setLocationRelativeTo(null);
        setTitle("LSAIR");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        jlTitle = new JLabel("Welcome to LSAir");
        this.add(jlTitle, BorderLayout.NORTH);
        login_panel = new Login_Panel();
        main_panel = new Main_Panel();

        this.add(login_panel, BorderLayout.CENTER);

    }

    public void registerButtonAction(MainController controller){
        login_panel.registerButtonAction(controller);
        main_panel.registerButtonAction(controller);
    }

    public void changePanelToMain(){
        login_panel.setVisible(false);
        main_panel.setVisible(true);
        this.add(main_panel, BorderLayout.CENTER);
    }

    public void changePanelToLogin(){
        main_panel.setVisible(false);
        login_panel.clear();
        login_panel.setVisible(true);
        this.add(login_panel, BorderLayout.CENTER);
    }

    public String getUsername(){
        return login_panel.getUsername();
    }

    public String getPassword(){
        return login_panel.getPassword();
    }
}
