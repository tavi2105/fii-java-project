import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DrawingPanel canvas;
    Player player1;
    Player player2;
    Game game;
    int mode;
    int clickCounter;
    public MainFrame(Player player, int mode) {
        super(player.getName());
        this.player1 = player;
        this.mode = mode;
        init();
    }
    public MainFrame(Player player1,Player player2, int mode) {
        super(player1.getName() + " & " + player2.getName());
        this.player1 = player1;
        this.player2 = player2;
        this.mode = mode;
        game = new Game();
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        canvas = new DrawingPanel(player1);
        controlPanel = new ControlPanel(this);

        setLayout (new BorderLayout());
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);

        if(mode == 0){
            game();
        } else {
            gamePC();
        }

    }
    private void game(){
        int W = player1.getTableDimension();
        if(player1.getSign() == '0')
            clickCounter = 1;
    this.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
            int x = (e.getX() / (W / 3)) * (W / 3) + W / 6;
            int y = (e.getY() / (W / 3)) * (W / 3) + W / 6;
            int matrixX = e.getX() / (W / 3);
            int matrixY = e.getY() / (W / 3);
            if (game.isEmpty(matrixX, matrixY)) {
                if (clickCounter % 2 == 0) {
                    canvas.drawX(x, y);
                    game.setByIndexes(matrixX, matrixY, 2);
                } else {
                    canvas.drawO(x, y);
                    game.setByIndexes(matrixX, matrixY, 1);
                }
                clickCounter++;
                int[] done = game.isDone();
                if(done[1] != 0){
                    
                }
            }
            repaint();
          }
        });
    }
    private void gamePC(){
        int W = player1.getTableDimension();
        if(player1.getSign() == '0')
            clickCounter = 1;
        this.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    int x = (e.getX() / (W/3)) * (W/3) + W/6;
                    int y = (e.getY() / (W/3)) * (W/3) + W/6;
                    if (clickCounter % 2 == 0) {
                        canvas.drawX(x, y);
                    } else {
                        canvas.drawO(x, y);
                    }
                    clickCounter++;
                    repaint();
                }
            });
    }
}
