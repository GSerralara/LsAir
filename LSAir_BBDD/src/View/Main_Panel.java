package View;

import Controller.MainController;

import javax.swing.*;
import java.awt.*;

public class Main_Panel extends JPanel {

    private JLabel jlImport;
    private JButton jbImport;

    private JLabel jlShow;
    private JButton jbShow;

    private JLabel jlCreateUsers;
    private JButton jbCreateUsers;

    private JLabel jlLogOut;
    private JButton jbLogOut;

    private JLabel jlText;

    private JPanel jpMain;

    public Main_Panel(){

        jpMain = new JPanel();
        jpMain.setLayout(new GridLayout(2, 1));
        JPanel aux = new JPanel();
        aux.setLayout(new GridLayout(5, 2));

        jlImport = new JLabel("Import OLTP DB");
        aux.add(jlImport);
        jbImport = new JButton("Import OLTP");
        aux.add(jbImport);

        jlShow = new JLabel("Import OLAP DB");
        aux.add(jlShow);
        jbShow = new JButton("Import OLAP");
        aux.add(jbShow);

        jlCreateUsers = new JLabel("Create Users");
        aux.add(jlCreateUsers);
        jbCreateUsers = new JButton("Create Users");
        aux.add(jbCreateUsers);

        jlLogOut = new JLabel("Log Out");
        aux.add(jlLogOut);
        jbLogOut = new JButton("Exit");
        aux.add(jbLogOut);

        jlText = new JLabel("");

        jpMain.add(aux);
        jpMain.add(jlText);

        this.add(jpMain);

    }

    public void registerButtonAction(MainController controller){
        jbImport.addActionListener(controller);
        jbShow.addActionListener(controller);
        jbCreateUsers.addActionListener(controller);
        jbLogOut.addActionListener(controller);
    }

}
