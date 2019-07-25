package Controller;

import Connector.Connector;
import View.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainController implements ActionListener {

    private Connector localConn; //Per poder mantenir la connexio amb el server local
    private MainView view;
    private boolean connectedLocal;
    private boolean connectedRemote;

    public MainController(MainView view){
        this.view = view;
        this.connectedLocal = false;
        this.connectedRemote = false;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        if (actionCommand.equals("Login")) {

            //Fem la connexio amb el localhost
            this.localConn = new Connector(view.getUsername(), view.getPassword(), "lsair", 3306, "localhost");
            connectedLocal = localConn.connect();
            if(connectedLocal){
                view.changePanelToMain(); //Si la connexió es exitosa, cambiem al main panel
            }
        }
        if(actionCommand.equals("Import OLTP")){

            //Primer fem la connexio amb el server extern de puigpedros
            Connector remoteConn = new Connector("lsair_user","lsair_bbdd","flight_db_02", 3306, "puigpedros.salle.url.edu");
            connectedRemote = remoteConn.connect();

            //Comprobem si la connexió ha estat exitosa
            if(connectedRemote){
                //Anirem important taula per taula
                ResultSet info;

                //Variables que ens ajudaran a tractar els Strings que rebem per comprovar que no siguin nulls o que no continguin un apostrof
                String iata_aux;
                String icao_aux;
                String name_aux;
                String country_aux;
                String city_aux;


                //Country
                info = remoteConn.selectQuery("Select * from country;");
                try{

                    while(info.next()){

                        country_aux = preparaString(info.getString("country"));

                        localConn.insertQuery("Insert into country_oltp values('" + country_aux + "','" +
                                info.getString("code") + "','" + info.getString("dst") + "');");

                        //System.out.println(country_aux + "  " + info.getString("code") + "  " + info.getString("dst"));
                    }

                }
                catch(SQLException e3){
                    System.out.println("Problema SQL - Country Table");
                }

                //City
                info = remoteConn.selectQuery("Select * from city;");
                try{

                    while(info.next()){

                        city_aux = preparaString(info.getString("city"));
                        country_aux = preparaString(info.getString("country"));

                        localConn.insertQuery("Insert into city_oltp values('" + country_aux + "','" +
                                city_aux + "');");
                    }

                }
                catch(SQLException e2){
                    System.out.println("Problema SQL - City Table");
                }

                //Airline
                info = remoteConn.selectQuery("Select * from airline;");
                try{

                    while(info.next()){

                        iata_aux = preparaString(info.getString("iata") + " ");
                        icao_aux = preparaString(info.getString("icao") + " ");
                        name_aux = preparaString(info.getString("name") + " ");
                        country_aux = preparaString(info.getString("country") + " ");

                        localConn.insertQuery("Insert into airline_oltp values(" + info.getInt("airline_id") + ", '" +
                                name_aux + "','" +
                                info.getString("alias") + "','" +
                                iata_aux + "','" +
                                icao_aux + "','" +
                                country_aux + "');");
                    }

                }
                catch(SQLException e1){
                    System.out.println("Problema SQL - Airline Table");
                }

                //Airport
                info = remoteConn.selectQuery("Select * from airport;");
                try{

                    while(info.next()) {

                        name_aux = preparaString(info.getString("name") + " ");
                        city_aux = preparaString(info.getString("city") + " ");
                        country_aux = preparaString(info.getString("country") + " ");
                        iata_aux = preparaString(info.getString("iata") + " ");
                        icao_aux = preparaString(info.getString("icao") + " ");

                        localConn.insertQuery("Insert into airport_oltp values(" + info.getInt("airport_id") + ",'" +
                                name_aux + "','" + city_aux + "','" +
                                country_aux + "','" + iata_aux + "','" +
                                icao_aux + "'," + info.getDouble("latitude") + "," +
                                info.getDouble("longitude") + "," + info.getDouble("altitude") + ");");
                    }
                }
                catch(SQLException e2){
                    System.out.println("Problema SQL - Airport Table");
                }

                //Plane
                info = remoteConn.selectQuery("Select * from plane;");
                try{

                    while(info.next()){

                        name_aux = preparaString(info.getString("name") + " ");
                        iata_aux = preparaString(info.getString("iata_code") + " ");
                        icao_aux = preparaString(info.getString("icao_code") + " ");

                        localConn.insertQuery("Insert into plane_oltp values(" + info.getInt("plane_id") + ",'" +
                                name_aux + "','" + iata_aux + "','" +
                                icao_aux + "');");
                    }


                }
                catch(SQLException e4){
                    System.out.println("Problema SQL - Plane Table");
                }

                //Route
                info = remoteConn.selectQuery("Select * from route;");
                try{

                    while(info.next()){

                        localConn.insertQuery("Insert into route_oltp values(" + info.getInt("route_id") + "," +
                                info.getInt("airline_id") + "," + info.getInt("src_airport_id") + "," +
                                info.getInt("dst_airport_id") + ",'" + info.getString("codeshare") + "'," +
                                info.getInt("stops") + "," + info.getInt("plane") + ");");
                    }
                }
                catch(SQLException e5){
                    System.out.println("Problema SQL - Airport Table");
                }

                //Timezone
                info = remoteConn.selectQuery("Select * from timezone;");
                try{

                    while(info.next()){

                        localConn.insertQuery("Insert into timezone_oltp values(" + info.getInt("timezone_id") + ",'" +
                                info.getString("timezone_olson") + "'," + info.getInt("timezone_utc") + ",'" +
                                info.getString("daylight_saving_time") + "');");
                    }

                }
                catch(SQLException e6){
                    System.out.println("Problema SQL - Timezone Table");
                }

                //Timezone_City
                info = remoteConn.selectQuery("Select * from timezone_city;");
                try{

                    while(info.next()){

                        country_aux = preparaString(info.getString("country"));
                        city_aux = preparaString(info.getString("city"));

                        localConn.insertQuery("Insert into timezone_city_oltp values('" + country_aux + "','" +
                                city_aux + "'," + info.getInt("timezone_id") + ");");
                    }

                }
                catch(SQLException e7){
                    System.out.println("Problema SQL - Timezone_City Table");
                }

            }

            //Desconectem la connexió remota
            remoteConn.disconnect();
        }

        if(actionCommand.equals("Import OLAP")){

            localConn.callProcedure("call importacio;"); //Cridem al procedure per importar la informacio al sistema OLAP

        }
        if(actionCommand.equals("Create Users")){

            localConn.callProcedure("call creacioUsers;"); //Cridem al procedure per crear els usuaris
            System.out.println("Users creats correctament!");
        }
        if(actionCommand.equals("Exit")){
            localConn.disconnect(); //acabem la connexió amb el servidor local
            view.changePanelToLogin();
        }

    }

    private String preparaString(String string){
        //Rebem un string i primer comprobem si es null
        if(string.equals("null ")){
            return " ";
        }
        //Si conté un apostrof, el borrarem de la paraula
        if(string.contains("'")){
            return string.replace("'"," ");
        }else{
            return string;
        }
    }

}
