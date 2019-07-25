import Controller.MainController;
import View.MainView;

import javax.swing.*;

public class Main {
    public static void  main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainView vista = new MainView();
                MainController controller = new MainController(vista);
                vista.registerButtonAction(controller);
                vista.setVisible(true);
            }
        });
    }
}
