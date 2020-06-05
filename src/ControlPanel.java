import javax.swing.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {

    final MainFrame frame;
    JButton backBtn = new JButton("Back to menu");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        add(backBtn);
        backBtn.addActionListener(this::back);

    }
    private void back(ActionEvent e) {
        setVisible(false);
        frame.dispose();
        FirstWindow first = new FirstWindow();
    }
}