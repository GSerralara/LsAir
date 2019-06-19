package view;

import controller.MainController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MainView extends JFrame{

    private JPasswordField jpfPassword;
    private JButton jbConnect;
    private JButton jbDisconnect;
    private JTextField jtfCerca;
    private JButton jbCerca;
    private JTextArea jtaResultat;


    private JTextField jtfTitle;
    private JTextField jtfIdDirector;
    private JTextField jtfYear;
    private JTextField jtfDuration;
    private JTextField jtfCountry;
    private JTextField jtfFacebookLikes;
    private JTextField jtfImdbScore;
    private JTextField jtfGross;
    private JTextField jtfBudget;
    private JButton jbAddMovie;


    public String getMovieTitle() {
        return jtfTitle.getText();
    }

    public void setMovieTitle(String title) {
        this.jtfTitle.setText(title);
    }

    public String getIdDirector() {
        return jtfIdDirector.getText();
    }

    public void setIdDirector(String idDirector) {
        this.jtfIdDirector.setText(idDirector);
    }

    public String getYear() {
        return jtfYear.getText();
    }

    public void setYear(String year) {
        this.jtfYear.setText(year);
    }

    public String getDuration() {
        return jtfDuration.getText();
    }

    public void setDuration(String duration) {
        this.jtfDuration.setText(duration);
    }

    public String getCountry() {
        return jtfCountry.getText();
    }

    public void setCountry(String country) {
        this.jtfCountry.setText(country);
    }

    public String getFacebookLikes() {
        return jtfFacebookLikes.getText();
    }

    public void setFacebookLikes(String facebookLikes) {
        this.jtfFacebookLikes.setText(facebookLikes);
    }

    public String getImdbScore() {
        return jtfImdbScore.getText();
    }

    public void setImdbScore(String imdbScore) {
        this.jtfImdbScore.setText(imdbScore);
    }

    public String getGross() {
        return jtfGross.getText();
    }

    public void setGross(String gross) {
        this.jtfGross.setText(gross);
    }

    public String getBudget() {
        return jtfBudget.getText();
    }

    public void setBudget(String budget) {
        this.jtfBudget.setText(budget);
    }



    public MainView(){
        setSize(600,600);
        setLocationRelativeTo(null);
        setTitle("Movie Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel jpConnect = new JPanel();
        jpConnect.setLayout(new BoxLayout(jpConnect,BoxLayout.LINE_AXIS));
        JLabel jlRootPassword = new JLabel("Root password: ");
        jpConnect.add(jlRootPassword);
        jpfPassword = new JPasswordField();
        jpConnect.add(jpfPassword);
        jbConnect = new JButton("Connect");
        jpConnect.add(jbConnect);
        jbDisconnect = new JButton("Disconnect");
        jpConnect.add(jbDisconnect);


        getContentPane().add(jpConnect, BorderLayout.NORTH);
        TitledBorder titleConnect = BorderFactory.createTitledBorder("Connection");
        jpConnect.setBorder(titleConnect);


        JPanel jpQueries = new JPanel();
        jpQueries.setLayout(new BorderLayout());
        JPanel jpCerca = new JPanel();
        jpCerca.setLayout(new BoxLayout(jpCerca, BoxLayout.LINE_AXIS));
        jpCerca.add(new JLabel("KeyWord:"));
        jtfCerca = new JTextField();
        jpCerca.add(jtfCerca);
        jbCerca = new JButton("Search");
        jpCerca.add(jbCerca);
        jpQueries.add(jpCerca, BorderLayout.NORTH);
        jtaResultat = new JTextArea();
        jtaResultat.setEditable(false);

        JScrollPane jspResultat = new JScrollPane(jtaResultat);

        jpQueries.add(jspResultat, BorderLayout.CENTER);
        getContentPane().add(jpQueries, BorderLayout.CENTER);
        TitledBorder titleQuery = BorderFactory.createTitledBorder("Queries");
        jpQueries.setBorder(titleQuery);


        JPanel jpGeneral = new JPanel();
        jpGeneral.setLayout(new GridLayout(4,5));


        jpGeneral.add(new Label("Title:"));
        jpGeneral.add(new Label("Id Director:"));
        jpGeneral.add(new Label("Year:"));
        jpGeneral.add(new Label("Duration:"));
        jpGeneral.add(new Label("Country:"));
        jtfTitle= new JTextField();
        jpGeneral.add(jtfTitle);
        jtfIdDirector= new JTextField();
        jpGeneral.add(jtfIdDirector);
        jtfYear= new JTextField();

        jpGeneral.add(jtfYear);
        jtfDuration= new JTextField();

        jpGeneral.add(jtfDuration);
        jtfCountry= new JTextField();

        jpGeneral.add(jtfCountry);
        jtfFacebookLikes= new JTextField();

        jpGeneral.add(new Label("Facebook Likes:"));
        jpGeneral.add(new Label("IMDB Score:"));
        jpGeneral.add(new Label("Gross:"));
        jpGeneral.add(new Label("Budget:"));
        jpGeneral.add(new Label(""));
        jpGeneral.add(jtfFacebookLikes);
        jtfImdbScore= new JTextField();
        jpGeneral.add(jtfImdbScore);
        jtfGross= new JTextField();

        jpGeneral.add(jtfGross);
        jtfBudget= new JTextField();

        jpGeneral.add(jtfBudget);
        jbAddMovie = new JButton("Done");
        jpGeneral.add(jbAddMovie);

        getContentPane().add(jpGeneral, BorderLayout.SOUTH);
        TitledBorder titleInsert = BorderFactory.createTitledBorder("Add | Modify");
        jpGeneral.setBorder(titleInsert);

        setDisconnected();

    }

    public void registerController(MainController controller) {
        jbConnect.addActionListener(controller);
        jbDisconnect.addActionListener(controller);
        jbCerca.addActionListener(controller);
        jbAddMovie.addActionListener(controller);
    }

    public void setDisconnected() {
        jbDisconnect.setEnabled(false);
        jbConnect.setEnabled(true);
        jbCerca.setEnabled(false);
        jtfCerca.setEnabled(false);
        //jbAddMovie.setEnabled(false);
    }

    public void setConnected() {
        jbDisconnect.setEnabled(true);
        jbConnect.setEnabled(false);
        jbCerca.setEnabled(true);
        jtfCerca.setEnabled(true);
        //jbAddMovie.setEnabled(true);
    }

    public String getPassword(){
        return String.valueOf(jpfPassword.getPassword());
    }

    public void setResult(String result) {
        jtaResultat.setText(result);
    }

    public String getCerca() {
        return jtfCerca.getText();
    }
}
