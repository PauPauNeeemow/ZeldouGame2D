package main;

import javax.swing.JFrame;

public class main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Zeldou");

        GameLoop gamePanel = new GameLoop();
        window.add(gamePanel);
        window.pack(); // Force Window à fit avec le prefered Size -> Game Panel
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

   public main() {
   }

}