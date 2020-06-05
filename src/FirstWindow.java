import javax.swing.*;
import java.awt.*;

public class FirstWindow extends JFrame {
    LoginAndSettings login;
    int gameOver;
    public FirstWindow() {
        super("Login");
        gameOver = 0;
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout (new BorderLayout());
        login = new LoginAndSettings(this);
        add(login, BorderLayout.CENTER);


        pack();
        setVisible(true);
    }
}
