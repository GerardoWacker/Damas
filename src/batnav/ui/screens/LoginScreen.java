package batnav.ui.screens;

import batnav.instance.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class LoginScreen extends JFrame implements ActionListener {
    private JTextField userName;
    private JTextField userPassword;
    private JLabel userLabel;
    private JLabel logoContainerLabel;
    private JLabel PasswordLabel;
    private JButton loginButton;
    private JLabel alert;
    private JPanel loginPanel;
    private JPanel loadingPanel;
    private JLabel loadingText;
    private JButton tempButton;
    private JLabel counterLabel;
    private Timer timer;
    private int second;
    private JPanel mainPanel;
    private DecimalFormat dFormat = new DecimalFormat("00");
    private String ddSecond;
    private CardLayout cl;


    public LoginScreen() {
        this.cl = new CardLayout();
        this.setSize(300, 500);
        this.setLocationRelativeTo(null);
        this.loginPanel = new JPanel();
        this.loadingPanel = new JPanel();
        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(cl);
        this.add(mainPanel);
        mainPanel.add(loginPanel,"1");
        mainPanel.add(loadingPanel,"2");

        cl.show(mainPanel,"1");

        loginPanel.setLayout(null);

        this.logoContainerLabel = new JLabel("batnav", SwingConstants.CENTER);
        logoContainerLabel.setFont(new Font("San Francisco Display", Font.BOLD, 25));
        logoContainerLabel.setHorizontalTextPosition(JLabel.CENTER);
        logoContainerLabel.setBounds(50, 10, 200, 80);


        this.userLabel = new JLabel("Usuario");
        userLabel.setBounds(50, 100, 80, 25);


        this.userName = new JTextField(20);
        userName.setBounds(50, 130, 165, 25);


        this.PasswordLabel = new JLabel("Contraseña");
        PasswordLabel.setBounds(50, 200, 80, 25);


        this.userPassword = new JPasswordField(20);
        userPassword.setBounds(50, 230, 165, 25);

        this.loginButton = new JButton("Login");
        loginButton.setBounds(50, 300, 165, 25);
        loginButton.addActionListener(this);
        loginButton.setActionCommand("login");

        this.alert = new JLabel("Contraseña");
        alert.setOpaque(true);
        alert.setForeground(Color.white);
        alert.setBackground(Color.red);
        alert.setBounds(50, 350, 80, 25);

        this.loadingText = new JLabel("Iniciando sesion");
        loadingText.setBounds(100, 120, 200, 80);

        this.tempButton = new JButton("Back");
        tempButton.setBounds(50, 300, 165, 25);
        tempButton.addActionListener(this);
        tempButton.setActionCommand("Back");

        this.counterLabel = new JLabel();
        counterLabel.setBounds(50, 400, 165, 25);

        this.second = 0;
        iniciarTimer();
        timer.start();

        this.loginPanel.add(this.userName);
        this.loginPanel.add(this.logoContainerLabel);
        this.loginPanel.add(this.userPassword);
        this.loginPanel.add(this.userLabel);
        this.loginPanel.add(this.PasswordLabel);
        this.loginPanel.add(this.loginButton);
        this.loginPanel.add(this.alert);
        this.loginPanel.add(counterLabel);

        this.loadingPanel.add(loadingText);
        this.loadingPanel.add(tempButton);

        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setVisible(true);


        //this.paintScreen();

    }

    private void iniciarTimer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                second++;
                ddSecond = dFormat.format(30 - second);
                if(second == 0 ){
                    timer.stop();
                }

                counterLabel.setText(":" + ddSecond);

            }
        });
    }

    /**
     * private void showLoadingScreen() {
     *     this.contentPanel1.removeAll();
     *     this.contentPanel1.add(loadingText);
     *     this.contentPanel1.add(tempButton);
     *     this.revalidate();
     *     this.repaint();
     * }
     */









    public static void main(String[] args) {
        new LoginScreen();
    }

    /**
     * public void paintScreen() {
     *     this.contentPanel.removeAll();
     *     this.contentPanel.add(this.userName);
     *     this.contentPanel.add(this.logoContainerLabel);
     *     this.contentPanel.add(this.userPassword);
     *     this.contentPanel.add(this.userLabel);
     *     this.contentPanel.add(this.PasswordLabel);
     *     this.contentPanel.add(this.loginButton);
     *     this.contentPanel.add(this.alert);
     *     this.contentPanel.add(counterLabel);
     *     this.revalidate();
     *     this.repaint();
     * }
     */
















    @Override
    public void actionPerformed(ActionEvent e) {


        final String action = e.getActionCommand();
        switch (action) {
            case "login":
                cl.show(mainPanel,"2");
                //this.showLoadingScreen();
                try {
                    if (Game.getInstance().getSessionManager().login(userName.getText(), userPassword.getText())) {
                        new MainMenuScreen();
                    } else {
                        System.out.println("lol");
                    }
                    break;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                break;
            case "Back":
                cl.show(mainPanel,"1");
                break;


        }
    }
}
