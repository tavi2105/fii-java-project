import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginAndSettings extends JPanel {
    final FirstWindow frame;
    public static JButton play;
//    public static JButton playWithPC;
    public static JButton top;
    public static JTextField player1;
    public static JTextField player2;
    public static JComboBox dimension;
    public static JComboBox zeroX;
    String[] data = { "X", "0"};
    String[] data1 = { "small", "medium", "big" };

    public LoginAndSettings(FirstWindow frame) {
        setPreferredSize(new Dimension(300, 400));
        this.frame = frame;
        init();
    }
    private void init() {
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(60, 5, 5, 5));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
        panel1.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));

        JLabel loginLabel = new JLabel("Player1 name:", JLabel.CENTER);

        player1= new JTextField(20);
        player1.setPreferredSize(new Dimension(10,30));

        panel.add(loginLabel, BorderLayout.NORTH);
        panel.add(player1, BorderLayout.CENTER);

        JLabel loginLabel2 = new JLabel("Player2 name:", JLabel.CENTER);

        player2= new JTextField(20);
        player2.setPreferredSize(new Dimension(10,30));

        panel1.add(loginLabel2, BorderLayout.NORTH);
        panel1.add(player2, BorderLayout.CENTER);


        play = new JButton("Play");
        panel2.add(play);

//        playWithPC = new JButton("Play with computer");
//        panel2.add(playWithPC);

        top = new JButton("View top 10 players");
        panel3.add(top);

        JPanel panOuter = new JPanel(new BorderLayout());
        JPanel panLeft = new JPanel(new BorderLayout());
        panLeft.setBorder(BorderFactory.createEmptyBorder(30, 5, 5, 5));
        JPanel panRight = new JPanel(new BorderLayout());
        panRight.setBorder(BorderFactory.createEmptyBorder(30, 5, 5, 5));

        panOuter.add(panLeft, BorderLayout.WEST);
        panOuter.add(panRight, BorderLayout.EAST);

        JLabel lblLeft = new JLabel("Choose your sign", JLabel.CENTER);
        JLabel lblRight = new JLabel("Choose table dimension", JLabel.CENTER);

        zeroX = new JComboBox(data);
        zeroX.setSelectedIndex(0);

        dimension = new JComboBox(data1);
        dimension.setSelectedIndex(1);


        panLeft.add(lblLeft, BorderLayout.NORTH);
        panLeft.add(zeroX, BorderLayout.CENTER);

        panRight.add(lblRight, BorderLayout.NORTH);
        panRight.add(dimension, BorderLayout.CENTER);


        add(panel);
        add(panel1);
        add(panel2);
        add(panel3);
        add(panOuter);


        play.addActionListener(this::play);
//        playWithPC.addActionListener(this::playWithPC);
        top.addActionListener(this::top);

    }

    private void play(ActionEvent e) {
        if(!player1.getText().equals("") && !player2.getText().equals("")){
            Player plr1 = createPlayer(player1.getText());
            Player plr2 = createPlayer(player2.getText());
            MainFrame frame = new MainFrame(plr1,plr2);
        }
    }
//    private void playWithPC(ActionEvent e) {
//        if(!player1.getText().equals("")){
//            Player player = createPlayer(player1.getText());
//            MainFrame frame = new MainFrame(player,1);
//        }
//    }
    private void top(ActionEvent e) {
        setVisible(false);
        frame.dispose();
        Top10 frame = new Top10();
    }
    private Player createPlayer(String playerName){
        setVisible(false);
        frame.dispose();
        int dim;
        if(dimension.getSelectedIndex() == 0){
            dim = 400;
        } else if(dimension.getSelectedIndex() == 1){
            dim = 600;
        } else {
            dim = 800;
        }
        char chr;
        if(zeroX.getSelectedIndex()==0){
            chr = 'X';
        } else {
            chr = '0';
        }
        Player player = new Player(playerName,chr,dim);
        return player;
    }

}