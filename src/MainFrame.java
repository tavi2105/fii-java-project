import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    ControlPanel controlPanel;
    DrawingPanel canvas;
    Player player1;
    Player player2;
    Game game;
    int clickCounter;
    boolean gameOver = false;
    public MainFrame(Player player1,Player player2) {
        super(player1.getName() + " & " + player2.getName());
        this.player1 = player1;
        this.player2 = player2;
//        this.mode = mode;
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

        game();
    }
    private void game(){
        int W = player1.getTableDimension();
        try{
            postRequest("http://localhost:8083/players/addPlayer","name",player1.getName());
            postRequest("http://localhost:8083/players/addPlayer","name",player2.getName());
        } catch(Exception e){
            System.out.println(e);
        }
        if(player1.getSign() == '0')
            clickCounter = 1;
    this.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
              if(!gameOver){
                  int x = (e.getX() / (W / 3)) * (W / 3) + W / 6;
                  int y = (e.getY() / (W / 3)) * (W / 3) + W / 6;
                  int matrixX = e.getX() / (W / 3);
                  int matrixY = e.getY() / (W / 3);
                  if (game.isEmpty(matrixX, matrixY)) {
                      if (clickCounter % 2 == 0) {
                          canvas.drawX(x, y);
                          game.setByIndexes(matrixX, matrixY, 1);
                      } else {
                          canvas.drawO(x, y);
                          game.setByIndexes(matrixX, matrixY, 2);
                      }
                      clickCounter++;
                      int[] done = game.isDone();
                      int aux = done[0]*(W/3)+W/6;
                      Color color = new Color(255,0,34);
                      if(done[1] != 0){
                          if(done[0]<3){
                              if(done[1] == 1){
                                  canvas.drawLine(aux,0,aux,W,color);
                              } else {
                                  canvas.drawLine(0,aux,W,aux,color);
                              }
                          } else {
                              if(done[1] == 1){
                                  canvas.drawLine(0,0,W,W,color);
                              } else {
                                  canvas.drawLine(0,W,W,0,color);
                              }
                          }
                          if(done[2] == 1 && player1.getSign() == 'X'){
                              try {
                                  putRequest("http://localhost:8083/players/update/"+player1.getName());
                              } catch (Exception ex) {
                                  ex.printStackTrace();
                              }
                          } else {
                              if(done[2]==1){
                                  try {
                                      putRequest("http://localhost:8083/players/update/"+player2.getName());
                                  } catch (Exception ex) {
                                      ex.printStackTrace();
                                  }
                              } else {
                                  try {
                                      putRequest("http://localhost:8083/players/update/"+player1.getName());
                                  } catch (Exception ex) {
                                      ex.printStackTrace();
                                  }
                              }
                          }
                          gameOver = true;
                      }
                  }
                  repaint();
              }
          }
        });
    }
//    private void gamePC(){
//        int W = player1.getTableDimension();
//        if(player1.getSign() == '0')
//            clickCounter = 1;
//        this.addMouseListener(
//            new MouseAdapter() {
//                @Override
//                public void mousePressed(MouseEvent e) {
//                    int x = (e.getX() / (W/3)) * (W/3) + W/6;
//                    int y = (e.getY() / (W/3)) * (W/3) + W/6;
//                    if (clickCounter % 2 == 0) {
//                        canvas.drawX(x, y);
//                    } else {
//                        canvas.drawO(x, y);
//                    }
//                    clickCounter++;
//                    repaint();
//                }
//            });
//    }
    public void postRequest(String paramUrl, String parameter, String content) throws Exception { // functie pentru POST
        URL url = new URL(paramUrl);
        Map<String,Object> params = new LinkedHashMap<>();
        params.put(parameter, content);

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        for (int c; (c = in.read()) >= 0;)
            System.out.print((char)c);
    }
    public void putRequest(String paramUrl) throws Exception {
        URL url = new URL(paramUrl);

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        for (int c; (c = in.read()) >= 0;)
            System.out.print((char)c);
    }
}
