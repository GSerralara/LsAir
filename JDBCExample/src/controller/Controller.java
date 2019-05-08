package controller;

import db.ConectorDB;
import view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller implements ActionListener{
    private boolean connected;
    private MainView mainView;
    private ConectorDB conn;

    public Controller(MainView mainView) {
        this.mainView = mainView;
        this.connected = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botoClickat = (JButton)e.getSource();
        if(botoClickat.getText().equals("Connect") && !connected){
            conn = new ConectorDB("root", mainView.getPassword(), "Movies", 3306);
            connected = conn.connect();
        }
        if(botoClickat.getText().equals("Disconnect") && connected) {
            conn.disconnect();
            connected = false;
        }

        if(botoClickat.getText().equals("Search") && connected){
            //try{
                //Extreiem la paraula per la que l'usuari està cercant:
                String paraulaClau = mainView.getCerca();

                //Iniciem el resultat a cadena de caràcters buida
                String result = "";

                //TODO: Mitjançant ResultSet i selectQuery ompliu la variable result amb el resultat de la query

                //Mostrem el resultat de la query a la interfície gràfica:
                mainView.setResult(result);

            //} catch (SQLException e1) {
                System.out.println("Error a MySQL");
            //}
        }

        if(botoClickat.getText().equals("Done")) {
            String title = mainView.getTitle();
            String idDirector = mainView.getIdDirector();
            String year = mainView.getYear();
            String duration = mainView.getDuration();
            String country = mainView.getCountry();
            String facebookLikes = mainView.getFacebookLikes();
            String imdbScore = mainView.getImdbScore();
            String gross = mainView.getGross();
            String budget = mainView.getBudget();

            System.out.println("hola");

            //TODO: Inserir o modificar el nou element
        }

        if(connected){
            mainView.setConnected();
        }else {
            mainView.setDisconnected();
        }
    }
}
