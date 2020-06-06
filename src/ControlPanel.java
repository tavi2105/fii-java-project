import javax.swing.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    private  MainFrame frame = null;
    private Player player1;
    private Player player2;
    private Top10 frame1 = null;
    JButton backBtn = new JButton("Back to menu");
    JButton playAgain = new JButton("Play Again");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    public ControlPanel(Top10 frame) {
        this.frame1 = frame;
        init();
    }
    private void init() {
        add(backBtn);
        backBtn.addActionListener(this::back);

    }
    public void addPlayAgain(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        add(playAgain);
        revalidate();
        playAgain.addActionListener(this::playAgain);
    }
    private void back(ActionEvent e) {
        setVisible(false);
        if(frame1 == null){
            frame.dispose();
        } else {
            frame1.dispose();
        }
        FirstWindow first = new FirstWindow();
    }
    private void playAgain(ActionEvent e) {
        setVisible(false);
        frame.dispose();
        MainFrame mainFrame = new MainFrame(player1,player2);
    }
}