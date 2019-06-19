package Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pop extends JFrame implements ActionListener {
    /**
     * Constant for the Buttons and Action Commands
     */
    // Constants of the buttons
    private static final String JB_OK = "OK";

    /**
     * Constructor by default of the class.
     * @param msg it's a String that will display the Pop-Up Window
     * */
    public Pop(String msg)
    {
        // set title of the frame
        setTitle("Message Window");
        // instance the content panel
        JPanel content =  new JPanel();
        content.setLayout(new BorderLayout());
        // create a label with the message
        JLabel str = new JLabel(msg);
        // instance the panel that will align the elements
        JPanel align =  new JPanel(new FlowLayout(FlowLayout.CENTER));
        // adding the string
        align.add(str);
        //  instance the panel that will align have the button
        JPanel command = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // create a button
        JButton b = new JButton(JB_OK);
        // add action listener
        b.addActionListener(this);// adding the action listener of this button
        command.add(b);
        // adding elements to the content
        content.add(align,BorderLayout.CENTER);
        content.add(command,BorderLayout.SOUTH);
        // setting the content pane and size
        setContentPane(content);
        setSize(150,150);
        // make frame visible
        setVisible(true);
    }

    /**
     * Override Method from ActionListener that activates when a Swing element,
     * with this class as an ActionListener, is interacted with.
     * @param e ActionEvent that will get the method.
     * In this case the calling of this method destroys the frame
     * */
    public void actionPerformed(ActionEvent e)
    {
        //you can't see the frame
        setVisible(false);
        //Destroy the JFrame object
        dispose();
    }
}
