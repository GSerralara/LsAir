import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import controller.Controller;
import view.MainView;

import java.sql.*;

public class Main {
    static int lport;
    static String rhost;
    static int rport;
    public static void go(){
        String user = "";
        String password = "";
        String host = "puigpedros.salle.url.edu";
        int port=22;
        try
        {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            lport = 4321;
            rhost = "localhost";
            rport = 3306;
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            int assinged_port=session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
        }
        catch(Exception e){System.err.print(e);}
    }
    public static void main(String[] args) {
        //MainView mainView = new MainView();
        //mainView.setVisible(true);

        //Controller controller = new Controller(mainView);
        //mainView.registerController(controller);
        try{
            go();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
