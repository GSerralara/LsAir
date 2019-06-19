import Model.Model;
import Network.Network;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import controller.FormController;
import view.WindowForm;

public class Main {

    public static void main(String[] args) {
        //MainView mainView = new MainView();
        //mainView.setVisible(true);

        //MainController controller = new MainController(mainView);
        //mainView.registerController(controller);
        //We create the view
        WindowForm view = new WindowForm();
        //We create the model
        Model model = new Model();
        //We create the access to the Server
        Network net = new Network();
        //We create the controller passing him the view, the model and the access to the Server.
        FormController c = new FormController(view, model, net);
        //We register the controller with the view
        view.registerController(c);
        //We register the controller with the net
        net.registerController(c);
        //We init the view
        view.go();
    }
}
