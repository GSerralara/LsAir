package controller;

import Model.Model;
import Model.Student;
import Network.Network;
import view.WindowForm;

public class FormController {
    private WindowForm view;
    private Model model;
    private Network network;

    private MainController mainController;
    private LauncherController launcherController;

    public FormController(WindowForm view, Model model, Network network) {
        this.view = view;
        this.model = model;
        this.network = network;
        this.network.registerController(this);
        //network.connect();

        //mainController = new MainController();
        launcherController = new LauncherController(this);
    }
    public void login(Student stdt){
        network.connect(stdt);
        model.setStudent(stdt);
    }
    public MainController getMainController() {
        return mainController;
    }

    public LauncherController getLauncherController() {
        return launcherController;
    }
}
