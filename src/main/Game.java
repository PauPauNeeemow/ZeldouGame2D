//LANCEMENT DU JEU 

package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {
    protected GameLoop gl;
    //Permet de mettre une postion par default
    int playerX = 100;
    int playerY = 100;
    // La vitesse du personnage
    int playerSpeed = 4;

    KeyHandler keyH = new KeyHandler(gl);
    // PANNEAU DE CONFIGURATION 
    final int originaltileSize = 16; // Taille des characters
    final int scale = 3;
    final int titleSize = originaltileSize * scale;
    final int maxScreenCol = 20; // Nombre de colonnes total
    final int maxScreenRow = 20; // Nombre de rangés total
    final int screenWidth = titleSize * maxScreenCol; // 768 pixel
    final int screenHeight = titleSize * maxScreenRow; // 576 pixel

    final int FPS = 60;

    Thread gameThread;

    public Game() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Zeldou");

        GameLoop gamePanel = new GameLoop();
        window.add(gamePanel);
        window.pack(); // Force Window à fit avec le prefered Size -> Game Panel
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();

        // Hero hero = new Hero();
        // this.hero = hero;
        // Conditions(hero);
    }

    // public void Conditions(Hero hero) {
    //     if((this.hero.getLife()) == 0  && (this.hero.isAlive() == false)) {
    //         System.out.println("C'est la mort");;
    //     } else {
    //         System.out.println("Ca marche mashallah");
    //     } 
            
    // }

    public void update() {
        if(keyH.upPressed == true) {
            playerY -= playerSpeed; // playerY = playerX - playerSpeed;
        } else if (keyH.downPressed == true) {
            playerY += playerSpeed;
        } else if (keyH.leftPressed == true) {
            playerX -= playerSpeed;
        } else if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }
    }


    // Permet de créer le rectangle blanc 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, titleSize, titleSize);
        g2.dispose();
    }
    
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; //1000000000 nano second = 1s  -> résultat 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval; 
        
        while(gameThread != null) {
            long currentTime = System.nanoTime(); // Permet de mesurer le temps en nano secondes -> calcul du temps d'exécution de certaines opérations
            // Permet de update la position du personnage
            update();
            
            // Permet de redessiner le personnage avec les information changées 
            repaint();
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // Permet de redessiner le personnage en décalé 
                remainingTime = remainingTime/1000000; // La fonction sleep, prend en milisecond et pas en nao second -> 1000000 = 1s
                
                if (remainingTime < 0 ) {
                    remainingTime = 0;
                }
                
                nextDrawTime += drawInterval;
                Thread.sleep((long) remainingTime); // Permet de mettre en pause la boucle
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
        // public static void main(String[] args) {
        //     GameLoop game = new GameLoop();
        // }
    
    // Ennemis 
    // Player 
    // Objet 

    // Method à faire :

    // Condition win / lose 
    // update --> déplacer le update de GameLoop à içi 


}