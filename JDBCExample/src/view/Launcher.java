package view;

import Resources.Pop;
import controller.LauncherController;

import javax.swing.*;
import java.awt.*;

public class Launcher extends JPanel{
    // instance variables
    private final ProgressListener listener;
    private LauncherController controller;

    /**
     * Constants for the Buttons and Action Commands
     */
    // Constants of the buttons
    public static final String JB_SINGIN = "Sing In";

    // Constants of the Action Commands
    public static final String AC_SIGNIN= "SIGN_IN";
    /**
     * Constants for the UI
     */
    // Name of the view
    private static final String WINDOW_TITLE = "SING IN";

    // General constants
    private static final String USER_LABEL_TEXT = "Username";
    private static final String PW_LABEL_TEXT = "Password";
    /**
     * Attributes of the UI
     */
    private JLabel msg;
    private JPasswordField pw;
    private JTextField username;
    private JButton signIn;
    /**
     * Constructor by default of the class.
     * @param listener it's a ProgressListener that the class will use to move to other views
     * @param controller it's the respective controller of this view
     * */
    public Launcher(ProgressListener listener, LauncherController controller) {
        // instance attributes with passed parameters
        this.listener = listener;
        this.controller = controller;
        this.controller.setLauncher(this);
        // UI configuration of the panel
        windowConfiguration();
    }
    /**
     * Method that will create all the components of the panel.
     */
    private void windowConfiguration(){
        // We configure the window.
        new BorderLayout();
        // We instance the title of the window
        JLabel title = new JLabel(WINDOW_TITLE);
        // We instance the panel body of the window
        JPanel content = new JPanel(new BorderLayout());
        // We create the different parts of the window.
        content.add(title,BorderLayout.NORTH);
        content.add(mainPart(),BorderLayout.CENTER);
        // We add the content to the window.
        add(content,BorderLayout.CENTER);
    }
    /**
     * Function that will return a JPanel.
     * It's responsible for creating the components of the center of the Layout.
     */
    private JPanel mainPart(){
        // instance JPanel that will be return
        JPanel main = new JPanel(new BorderLayout());
        // instance JPanels that will from the main panel
        JPanel form = new JPanel();
        JPanel bottom = new JPanel(new FlowLayout());//For UX positioning will have a FlowLayout
        // set Layout to a box Form for the content
        form.setLayout(new BoxLayout(form,BoxLayout.Y_AXIS));
        // instance labels of the view
        JLabel userLabel = new JLabel(USER_LABEL_TEXT);
        JLabel pwLabel = new JLabel(PW_LABEL_TEXT);
        // instance attributes
        username = new JTextField();
        pw = new JPasswordField();
        // add user camp to the from
        form.add(userLabel);
        form.add(username);
        // add password camp to the fom
        form.add(pwLabel);
        form.add(pw);
        // add Sing In button to the bottom
        signIn = new JButton(JB_SINGIN);
        signIn.setActionCommand(AC_SIGNIN);//set action command that will get the ActionListener
        signIn.addActionListener(controller);//set which ActionListener
        bottom.add(signIn,FlowLayout.LEFT);//set Flow position for UX purposes
        // add secondary panels (from & bottom) to the main panel
        main.add(form,BorderLayout.CENTER);
        main.add(bottom,BorderLayout.SOUTH);
        // return Statement
        return main;
    }
    /**
     * Method that will create all the components of the panel.
     * @param windowName that indicate which window will target and change into.
     */
    public void goToWindow(String windowName){
        // List of available views from this one
        switch (windowName){
            case "Login":
                this.listener.progressFrom(ProgressListener.AppState.MAIN);
                break;
            default:
                System.err.println("Unknown window name Launcher");
                break;
        }
    }
    /**
     * Function that will return the String of the username TextField.
     */
    public String getUserField(){
        // return Statement
        return username.getText();
    }
    /**
     * Function that will return the String of the pw PasswordField.
     */
    public String getPwField(){
        // instance String that will be return
        String pwd = new String(pw.getPassword());
        // return Statement
        return pwd;
    }
    /**
     * Function that will return:
     *      -->True: in case all fields are filled
     *      -->False:in case not all fields are filled
     */
    public boolean allFieldsFilled(){
        if(username.getText().length() != 0 && pw.getPassword().length != 0) return true;
        Pop popup = new Pop("All fields must be filled");
        return false;
    }
}
