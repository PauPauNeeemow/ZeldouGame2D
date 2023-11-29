package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_Key;

public class UI {
    
    GameLoop gp;
    Graphics2D g2;
    Font arial_40, arial_80B, arial_90B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public int commandNum = 0;
    public String currentDialogue = ""; 

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    BufferedImage heart_full, heart_half, heart_blank;

    public UI(GameLoop gp) {
        this.gp = gp;

        arial_40 = new Font("Pixel Coleco", Font.PLAIN, 30);
        arial_80B = new Font("Pixel Coleco", Font.BOLD, 80);
        arial_90B = new Font("Pixel Coleco", Font.BOLD, 90);
        Entity key = new OBJ_Key(gp);

        keyImage = key.image;

        //SUPER OBJECT
        Entity heart = new OBJ_Heart(gp); // objet coeur pour la vie avec les images correspondantes
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        if(gameFinished == true) {

            g2.setFont(arial_40);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "You found the treasure!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.titleSize*3);
            g2.drawString(text, x, y);

            g2.setFont(arial_90B);
            g2.setColor(Color.yellow);
            text = "Congratulation!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.titleSize*2);
            g2.drawString(text, x, y);

            gp.gameThread = null;
        }
        else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);

            // MESSAGE
            if(messageOn == true) {

                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.titleSize/2, gp.titleSize*5);

                messageCounter++;

                if(messageCounter > 50) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }

            // TITLE STATE
            if(gp.gameState == gp.titleState) {
                drawTitleScreen();
            }
            // PLAY STATE
            if(gp.gameState == gp.playState) {
                drawPlayerLife();
                g2.drawImage(keyImage, gp.titleSize/2, gp.titleSize*2, gp.titleSize, gp.titleSize, null);
                g2.drawString("x "+ gp.player.hasKey, 75, 140);

            }
            // PAUSE STATE
            if(gp.gameState == gp.pauseState) {
                drawPlayerLife();
                drawPauseScreen();
            }
            // DIALOGUE STATE
            if(gp.gameState == gp.dialogueState) {
                drawPlayerLife();
                drawDialogueScreen();
            }
            //Game Over
            if (gp.gameState == gp.gameOverState) {
                drawGameOverScreen();
            }
            // STATUS CHARACTER
            if (gp.gameState == gp.characterState){
                drawCharacterScreen();
            }
        }
    }

    public void drawTitleScreen() {

        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 90F));
        String text = "Zeldou";
        int x = getXforCenteredText(text);
        int y = gp.titleSize * 3;

        // SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);
        // MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        // HERO
        x = gp.screenWidth/2 - (gp.titleSize*2)/2;
        y += gp.titleSize*3;
        g2.drawImage(gp.player.down2, x, y, gp.titleSize*2, gp.titleSize*2, null);
        // MENU
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.titleSize*5;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-gp.titleSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.titleSize;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-gp.titleSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.titleSize;
        g2.drawString(text, x, y);
        if(commandNum == 2) {
            g2.drawString(">", x-gp.titleSize, y);
        }
    }

    public void drawPlayerLife() {
        // gp.player.life = 6;
        int x = gp.titleSize/2;
        int y = gp.titleSize/2;
        int i = 0;

        // Dessine les coeurs maximums
        while(i < gp.player.maxLife/2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.titleSize;
        }

        // Remet à 0
        x = gp.titleSize/2;
        y = gp.titleSize/2;
        i = 0;

        // Dessine les coeurs 
        while(i < gp.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);   
            }
            i++;
            x += gp.titleSize;
        }
    }

    public void drawPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";

        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen() {

        // WINDOW
        int x = gp.titleSize*2;
        int y = gp.titleSize/2;
        int width = gp.screenWidth - (gp.titleSize*4);
        int height = gp.titleSize*4;

        drawSubWindow(x, y, width, height);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 23F));
        g2.setColor(Color.black);
        x += gp.titleSize; 
        y += gp.titleSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

        // TEXT AREA
    }
    
    public void drawCharacterScreen(){
        // CREATE FRAME
        final int frameX = gp.titleSize*2;
        final int frameY = gp.titleSize; 
        final int frameWidth = gp.titleSize*5 ; 
        final int frameHeight = gp.titleSize*10;
        drawSubWindow(frameX, frameY, frameWidth,frameHeight );

        //text 
        g2.setColor(Color.black);
        g2.setFont(g2.getFont().deriveFont(24F));

        int textX = frameX + 20;
        int textY = frameY + gp.titleSize;
        final int lineHeight = 30;

        //NAMES
        g2.drawString("level", textX, textY);
        textY += lineHeight;
        g2.drawString("life", textX, textY);
        textY += lineHeight;
        g2.drawString("exp", textX, textY);
        textY += lineHeight;
        g2.drawString("nextLevelExp", textX, textY);
        textY += lineHeight;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight;

        //VALUES 
        int tailX =(frameX +frameWidth) -30;
        // reset textY
        textY = frameY + gp.titleSize;
        String value; 

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.currentWeapon);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0,0,0);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color (255,255,255,200);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.fillRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    public void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0,gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";
        //Ombre
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.titleSize*4;
        g2.drawString(text, x, y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        // Try again
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.titleSize*4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-40, y);
        }

        // Revenir sur le titre principal
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-40, y);
        }
    }

    public int getXforAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x; 

    }
}