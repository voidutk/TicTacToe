import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TicTacToe {
    int gamewidth=500;
    int gameheight=550;

    JFrame frame=new JFrame(" Tic-Tac-Toe!");
    JLabel textJLabel=new JLabel();
    JPanel tPanel=new JPanel();
    JPanel gamePanel=new JPanel();


    JButton[][] buttons=new JButton[3][3];
    JButton restartButton = new JButton("Restart");
        String PlayerA="X";
        String PlayerB="O";
        String currentPlayer=PlayerA;

        boolean gameOver=false;
        int chances=0;

    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(gamewidth,gameheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textJLabel.setBackground(Color.BLACK);
        textJLabel.setForeground(Color.WHITE);
        textJLabel.setFont(new Font("Arial",Font.ROMAN_BASELINE,40));
        textJLabel.setHorizontalAlignment(JLabel.CENTER);
        textJLabel.setText("X vs O!!!");
        textJLabel.setOpaque(true);

        tPanel.setLayout(new BorderLayout());
        tPanel.add(textJLabel);
        frame.add(tPanel,BorderLayout.NORTH);

        gamePanel.setLayout(new GridLayout(3,3));
        gamePanel.setBackground(Color.black);
        frame.add(gamePanel);

        // Bottom Panel for Restart Button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        restartButton.setFont(new Font("Arial", Font.ITALIC, 20));
        restartButton.setFocusable(false);
        restartButton.setBackground(Color.DARK_GRAY);
        restartButton.setForeground(Color.WHITE);
        bottomPanel.add(restartButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        restartButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        resetGame();
    }
});



        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                JButton tile=new JButton();
                
                buttons[i][j]=tile;
                gamePanel.add(tile);
                tile.setBackground(Color.black);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial",Font.ITALIC,120));
                tile.setFocusable(false);
                //tile.setText(PlayerA);
                tile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if(gameOver)return;
                        JButton tile=(JButton)e.getSource();
                        if(tile.getText()=="") {
                          tile.setText(currentPlayer);
                          chances++;
                          checkWin();
                          if(!gameOver) {
                            currentPlayer = currentPlayer.equals(PlayerA) ? PlayerB : PlayerA;
                            textJLabel.setText(currentPlayer + "'s turn");
                          }
                       
                        }
                       

                    }
                });
            }
        }

    }

    void checkWin(){
        for(int i=0;i<3;i++){
            if(buttons[i][0].getText()=="") continue;

            if(buttons[i][0].getText()==buttons[i][1].getText() && buttons[i][1].getText()==buttons[i][2].getText()){
            JButton[] tiles = { buttons[i][0], buttons[i][1], buttons[i][2] };
            for(JButton b : tiles){
                setWinner(b);
            }
            flashTiles(tiles);
            gameOver=true;

               return;
            }
        }
        for(int i=0;i<3;i++){
            if(buttons[0][i].getText()=="") continue;

            if(buttons[0][i].getText()==buttons[1][i].getText() && buttons[1][i].getText()==buttons[2][i].getText()){
                JButton[] tiles = { buttons[0][i], buttons[1][i], buttons[2][i] };
            for(JButton b : tiles){
                setWinner(b);
            }
            flashTiles(tiles);
            gameOver=true;

                return;
            }
        }
        if(buttons[0][0].getText()==buttons[1][1].getText() && buttons[1][1].getText()==buttons[2][2].getText() && buttons[0][0].getText()!=""){
           JButton[] tiles = { buttons[0][0], buttons[1][1], buttons[2][2] };
            for(JButton b : tiles){
                setWinner(b);
            }
            flashTiles(tiles);
            gameOver=true;

            return;
        }
        if(buttons[0][2].getText()!="" && buttons[0][2].getText()==buttons[1][1].getText() && buttons[1][1].getText()==buttons[2][0].getText() ){
            JButton[] tiles = { buttons[0][2], buttons[1][1], buttons[2][0] };
            for(JButton b : tiles){
                setWinner(b);
            }
            flashTiles(tiles);
            gameOver=true;

            return;
        }
        if(chances==9){
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    setDraw(buttons[i][j]);
                }
            }
            gameOver=true;
        }
    }

    void setWinner(JButton tile){
        tile.setForeground(Color.red);
        tile.setBackground(Color.black);
        textJLabel.setText(currentPlayer+" wins!");
    }
    void flashTiles(JButton[] tiles) {
    Timer timer = new Timer(200, null);
    final boolean[] toggle = { true };
    final int[] count = { 0 };

    timer.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            for(JButton tile : tiles) {
                tile.setBackground(toggle[0] ? Color.YELLOW : new Color(40, 180, 99));
            }
            toggle[0] = !toggle[0];
            count[0]++;
            if(count[0] > 6) timer.stop();
        }
    });
    timer.start();
}

    void setDraw(JButton tile){
        tile.setForeground(Color.pink);
        tile.setBackground(Color.gray);
        textJLabel.setText("Opps! It's a draw!");
    }
    void resetGame() {
    currentPlayer = PlayerA;
    textJLabel.setText("X vs O!!!");
    gameOver = false;
    chances = 0;

    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            buttons[i][j].setText("");
            buttons[i][j].setForeground(Color.WHITE);
            buttons[i][j].setBackground(Color.BLACK);
            buttons[i][j].setFont(new Font("Arial", Font.ITALIC, 120));
        }
    }

    
    glowAllTiles();
}

void glowAllTiles() {
    Timer timer = new Timer(100, null);  // 100ms per frame
    final boolean[] toggle = { true };
    final int[] count = { 0 };

    timer.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Color glowColor = new Color(200, 200, 200); // glow gray
            Color baseColor = Color.BLACK;

            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    buttons[i][j].setBackground(toggle[0] ? glowColor : baseColor);
                }
            }

            toggle[0] = !toggle[0];
            count[0]++;

            // After 6 flashes, stop and restore all to default (black)
            if(count[0] > 6) {
                timer.stop();
                for(int i = 0; i < 3; i++) {
                    for(int j = 0; j < 3; j++) {
                        buttons[i][j].setBackground(baseColor);
                    }
                }
            }
        }
    });
    timer.start();
}



    
}
