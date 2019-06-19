package Network;

import Model.Student;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import controller.FormController;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.sql.Connection;
import java.sql.DriverManager;

public class Network {
    static int lport;
    static String rhost;
    static int rport;
    private final String host = "puigpedros.salle.url.edu";
    private final int port=22;
    private JSch jsch;
    private Session session;
    private int assinged_port;
    private FormController c;
    private String dbuserName = "lsair_user";
    private String dbpassword = "lsair_bbdd";
    private String url = "jdbc:mysql://localhost:"+lport+"/flight_db_00";
    private String driverName="com.mysql.jdbc.Driver";
    private Connection conn = null;
    public Network() {
    }
    public void connect(Student student){
        try
        {
            jsch = new JSch();
            session = jsch.getSession(student.getUsername(), host, port);
            lport = 4321;
            rhost = "localhost";
            rport = 3306;
            session.setPassword(student.getPassword());
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("Establishing Connection...");
            session.connect();
            assinged_port=session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
            //mysql database connectivity
            Class.forName(driverName).newInstance();
            conn = DriverManager.getConnection (url, dbuserName, dbpassword);
            System.out.println ("Database connection established");
            System.out.println("DONE");
        }
        catch(Exception e){System.err.print(e);}
    }
    public void registerController(FormController c){
        this.c = c;
    }
}
