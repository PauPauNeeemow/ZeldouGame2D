package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GameLoop;
import main.UtilityTool;

public class Entity {

	GameLoop gp;

	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackRight1, attackRight2, attackRight3, attackLeft1, attackLeft2, attackLeft3;
	public BufferedImage image, image2, image3;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collision = false;

	String dialogues[] = new String [20];

	// STATUT
	public int worldX, worldY;
	public String direction = "down";
	public int spriteNum = 1;
	int dialogueIndex = 0;
	public boolean collisionOn = false;
	boolean attacking = false;
	public boolean invicible = false;
	public boolean alive = true;
	public boolean dying = false;
	public boolean hpBarOn = false;
	
	// COMPTEUR
	public int spriteCounter = 0; 
	public int invicibleCounter = 0;
	public int dyingCounter = 0;
	public int hpBarCounter = 0;
	public int shotAvailableCounter = 0;
	public int actionLockCounter = 0;

	// ATTRIBU HERO
	public String name;
	public int speed;
	public int maxLife;
	public int life;
	public int type; // 0 - player ; 1 - Warrior ; 2 - Wizard ; 3 - Archer : 4 - NPC
	public int level; 
	public int exp; 
	public int nextLevelExp; 
	public String currentWeapon; 

	public int attack;
	public Projectile projectile;

	//HITBOX
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0); 

	//Collision Player boolean
	public Entity(GameLoop gp){
		this.gp = gp;

	}
	
	public void speak() {

		if(dialogues[dialogueIndex] == null ){
			dialogueIndex = 0;
		}
		
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex ++; 

		switch(gp.player.direction){
		case "up":
			direction = "down";
			break; 
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}
	
	public void update() {

		collisionOn = false; 
		gp.checker.checkTile(this);
		gp.checker.checkObject(this, false);
		gp.checker.checkEntity(this, gp.npc);
		gp.checker.checkEntity(this, gp.monster);
		boolean contactPlayer = gp.checker.checkPlayer(this);

		if (this.type == 2 && contactPlayer == true || this.type == 3 && contactPlayer == true) {
			damagePlayer(attack);
		}

		if(invicible == true) {
			invicibleCounter++;
			if(invicibleCounter > 10) {
				invicible = false;
				invicibleCounter = 0;
			}
		}

		if(shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}
	}

	public void draw(Graphics2D g2){ //dessine l'entité en fonction de sa position et de sa direction Utilise différentes images en fonction de la direction et de l'état de l'entité.
									//Effectue la vérification de la visibilité avant de dessiner.

		BufferedImage image =  null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX && 
            worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.titleSize > gp.player.worldY - gp.player.screenY && 
            worldY - gp.titleSize < gp.player.worldY + gp.player.screenY) {
                
            	switch(direction) {
					case "up":
						if(spriteNum == 1) {
							image = up1;
						}
						if(spriteNum == 2) {
							image = up2;
						}
						break;
					case "down":
						if(spriteNum == 1) {
							image = down1;
						}
						if(spriteNum == 2) {
							image = down2;
						}
						break;
					case "right":
						if(spriteNum == 1) {
							image = right1;
						}
						if(spriteNum == 2) {
							image = right2;
						}
						break;
					case "left":
						if(spriteNum == 1) {
							image = left1;
						}
						if(spriteNum == 2) {
							image = left2;
						}
						break;
				}

				// MONSTER LIFE BAR
				if(type == 2 && hpBarOn == true || type == 3 && hpBarOn == true) {

					double oneScale = (double)gp.titleSize/maxLife;
					double hpBarValue = oneScale*life;

					g2.setColor(new Color(35,35,35));
					g2.fillRect(screenX-1, screenY - 16, gp.titleSize+2, 12);

					g2.setColor(new Color(255,0,30));
					g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);
				
					hpBarCounter++;

					if(hpBarCounter > 600) {
						hpBarCounter = 0;
						hpBarOn = false;
					}
				}

				if(invicible == true) {
					hpBarOn = true;
					hpBarCounter = 0;
					changeAlpha(g2, 0.4F);
				}

				if(dying == true) {
					dyingAnimation(g2);
				}

				g2.drawImage(image, screenX, screenY, null);
        
				changeAlpha(g2, 1F);
		} 
	}

	public void damagePlayer(int attack) {
		if(gp.player.invicible == false) {
			//We can give damge
			gp.player.life -= attack;
			gp.player.invicible = true;
			gp.playSE(10);
		}
	}

	public void dyingAnimation(Graphics2D g2) {

		dyingCounter++;

		int i = 5;

		if(dyingCounter <= i) { changeAlpha(g2, 0F); }
		if(dyingCounter > i && dyingCounter <= i*2) { changeAlpha(g2, 1F); }
		if(dyingCounter > i*2 && dyingCounter <= i*3) { changeAlpha(g2, 0F); }
		if(dyingCounter > i*3 && dyingCounter <= i*4) { changeAlpha(g2, 1F); }
		if(dyingCounter > i*4 && dyingCounter <= i*5) { changeAlpha(g2, 0F); }
		if(dyingCounter > i*5 && dyingCounter <= i*6) { changeAlpha(g2, 1F); }
		if(dyingCounter > i*6 && dyingCounter <= i*7) { changeAlpha(g2, 0F); }
		if(dyingCounter > i*7 && dyingCounter <= i*8) { changeAlpha(g2, 1F); }
		if(dyingCounter > i*8) {
			dying = false;
			alive = false;
		}
	}

	public void changeAlpha(Graphics2D g2, float alphaValue) {

		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}

	public BufferedImage setup(String imagePath, int width, int height) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream("/res/"+imagePath+".png"));
			image = uTool.scaleImage(image, width, height);

		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}