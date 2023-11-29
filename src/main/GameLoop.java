//PAGE D'ACCUEIL

package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Tile.TileManager;
import entity.Entity;
import entity.Player;

public class GameLoop extends JPanel implements Runnable {
    // PANNEAU DE CONFIGURATION 
    final int originaltileSize = 16; // Taille des characters
    final int scale = 3;

    public final int titleSize = originaltileSize * scale;
    public final int maxScreenCol = 20; // Nombre de colonnes total
    public final int maxScreenRow = 15; // Nombre de rangés total
    public final int screenWidth = titleSize * maxScreenCol; // 768 pixel
    public final int screenHeight = titleSize * maxScreenRow; // 576 pixel


    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 10;
    public int currentMap = 0;

    int FPS = 45;
    
    // SYSTEM
    public TileManager tileM = new TileManager(this); //Pour gérer la map du jeux
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound(); // Instantiation d'un objet music de la classe Sound
    Sound se = new Sound(); // Instantiation d'un objet se de la classe Sound
    public CollisionChecker checker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player = new Player(this,keyH); //Pour gérer le mouvement du hero
    public Entity object[][] = new Entity[maxMap][25]; //Nombre de slot d'objet
    public Entity npc[][] = new Entity[maxMap][25];
    public Entity monster[][] = new Entity[maxMap][50];

    public ArrayList<Entity> projectileList = new ArrayList<>();
    public ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int gameOverState = 4;
    public final int characterState = 5;


    public GameLoop() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Eviter les scintillement
        this.addKeyListener(keyH);
        this.setFocusable(true); // Permet de se focaliser sur les inputs
    }

    public void setupGame(){
        aSetter.setObject(); 
        aSetter.setNPC();
        aSetter.setMonster();
        playMusic(0); 
        gameState = titleState; 
    }

    public void retry() {
        player.setDefaultPosition();
        player.restoreLife();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setObject(); 

    }

    public void restart() {
        player.setDefaultPosition();
        player.setDefaultValues();
        player.restoreLife();
        // player.setItems();
        aSetter.setObject(); 
        aSetter.setNPC();
        aSetter.setMonster();
        // aSetter.setInteractiveTile();
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

    public void update() {
    	
        if(gameState == playState) {
            // PLAYER
            player.update();
            // NPC
            for(int i = 0; i < npc[1].length; i++){
                if(npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            // MONSTER
            for(int i = 0; i < monster[1].length; i++) {
                if(monster[currentMap][i] != null) {
                    if(monster[currentMap][i].alive == true) {
                        monster[currentMap][i].update();
                    }
                    if(monster[currentMap][i].alive == false) {
                        monster[currentMap][i] = null;
                    }
                }
            }

            // PROJECTILE
            for(int i = 0; i < projectileList.size(); i++) {
                if(projectileList.get(i) != null) {
                    if(projectileList.get(i).alive == true) {
                        projectileList.get(i).update();
                    }
                    if(projectileList.get(i).alive == false) {
                        projectileList.remove(i);
                    }
                }
            }
        }
        if(gameState == pauseState) {
            // nothing
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }
        drawStart = System.nanoTime();

        // Map
        tileM.draw(g2);

        // ADD ENTITIES TO THE LIST
        entityList.add(player);

        // NPC
        for(int i = 0; i < npc[1].length; i++) {
            if(npc[currentMap][i] != null) {
                entityList.add(npc[currentMap][i]);
            }
        }

        // // Object
        // for(int i = 0; i < obj[1].length; i++) {
        //     if(obj[currentMap][i] != null) {
        //         entityList.add(obj[currentMap][i]);
        //     }
        // }

        // Monster
        for(int i = 0; i < monster[1].length; i++) {
            if(monster[currentMap][i] != null) {
                entityList.add(monster[currentMap][i]);
            }
        }

        // TITLE SCREEN
        if(gameState == titleState) {

            ui.draw(g2);
        }
        // OTHERS
        else {

            // Map
            tileM.draw(g2);
            
            entityList.add(player);
            
            // NPC
            for(int i = 0; i < npc[1].length; i++) {
                if(npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }

            // OBJ
            for(int i = 0; i < object[1].length; i++) {
                if(object[currentMap][i] != null) {
                    entityList.add(object[currentMap][i]);
                }
            }

            // Monster
            for(int i = 0; i < monster[1].length; i++) {
                if(monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }

            // Projectile
            for(int i = 0; i < projectileList.size(); i++) {
                if(projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }
            }

            //SORT 
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            //DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

             //DRAW Projectile
            for(int i = 0; i < projectileList.size(); i++) {
                projectileList.get(i).draw(g2);
            }

            //EMPTY ENTITY LIST
            entityList.clear();

            // UI
            ui.draw(g2);

        }

        // DEBUG
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }
        g2.dispose();
    }

    public void playMusic(int i) { 

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() { 

        music.stop();
    }

    public void playSE(int i) { 

        se.setFile(i);
        se.play();
    }
}
