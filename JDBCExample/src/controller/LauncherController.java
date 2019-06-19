package controller;

import Model.Student;
import view.Launcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LauncherController implements ActionListener {
    // attributes of the class
    private Launcher launcher;
    private FormController listener;
    /**
     * Constructor by default of the class.
     * @param listener it's the controller father that manages all petitions
     * */
    public LauncherController(FormController listener) {
        this.listener = listener;
    }
    /**
     * Setter that sets the view that will retrieve data from.
     * @param launcher the view corresponding to this controller
     * */
    public void setLauncher(Launcher launcher) {
        this.launcher = launcher;
    }
    /**
     * Override Method from ActionListener that activates when a Swing element,
     * with this class as an ActionListener, is interacted with.
     * @param e ActionEvent that will get the method.
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Validate login, or whatever.
        // All went well, notify listener to progress.
        switch (e.getActionCommand()){
            case "SIGN_IN":
                //first we check that all camps are filled
                if(launcher.allFieldsFilled()){
                    //after that we try to connect to the server
                    Student student = new Student(launcher.getUserField(),launcher.getPwField());
                    listener.login(student);
                    launcher.goToWindow("Login");

                }
                break;
            default:
                System.err.println("Unknown Button Launcher");
                break;
        }
        //this.listener.progressFrom(ProgressListener.AppState.LAUNCHER);
    }

}
