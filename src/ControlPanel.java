import javax.swing.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {

    private  MainFrame frame = null;
    private Top10 frame1 = null;
    JButton backBtn = new JButton("Back to menu");

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
    private void back(ActionEvent e) {
        setVisible(false);
        if(frame1 == null){
            frame.dispose();
        } else {
            frame1.dispose();
        }
        FirstWindow first = new FirstWindow();
    }
}