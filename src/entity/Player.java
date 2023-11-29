package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import java.awt.Rectangle;

import java.awt.image.BufferedImage;

import main.GameLoop;
import main.KeyHandler;
import object.OBJ_Arrow;


public class Player extends Entity {

	KeyHandler keyH;//entrée de clavier 

	public final int screenX;//coordonées de l'écran centrée sur le joueur 
	public final int screenY;

	//Invincible
	

	public int hasKey = 0; // Indice du nombre de clef recuperer par le Hero

	public Player(GameLoop gp, KeyHandler keyH) {

		super(gp);

		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.titleSize/2); // Centrer la caméra sur le Hero
		screenY = gp.screenHeight/2 - (gp.titleSize/2); // Centrer la caméra sur le Hero

		// Définie la zone de collision
		solidArea = new Rectangle();
		solidArea.x = 6;
		solidArea.y = 14;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 22;
		solidArea.height = 22;

		//HITBOX
		attackArea.width = 36;
		attackArea.height = 36;

		setDefaultValues();//Determine la position/la vitesse du Hero
		getPlayerImage();//Pour afficher les images du Hero
		getPlayerAttackImage();//Pour affichier les images d'attaques du Hero
	}
	
	public void setDefaultValues() {
		
		System.out.println(gp.currentMap);
		if(gp.currentMap == 0) {
			worldX = 4 * gp.titleSize;
			worldY = 8 * gp.titleSize;
			direction = "down";
		}
		else if(gp.currentMap == 1){
			worldX = 1 * gp.titleSize;
			worldY = 37 * gp.titleSize;
			direction = "down";
		}

		speed = 4;
		direction = "down";

		//Vie Joeur
		level =1; 
		maxLife = 6;
		life = maxLife;
		type = 1;
		attack = 1;
		exp =0;
		nextLevelExp = 2;
		currentWeapon = "Sword"; 

		projectile = new OBJ_Arrow(gp);
	}

	//Reset position try again
	public void setDefaultPosition() {
		System.out.println(gp.currentMap);
		if(gp.currentMap == 0) {
			worldX = 4 * gp.titleSize;
			worldY = 8 * gp.titleSize;
			direction = "down";
		}
		else if(gp.currentMap == 1){
			worldX = 1 * gp.titleSize;
			worldY = 37 * gp.titleSize;
			direction = "down";
		}
	}

	public void restoreLife() {
		life = maxLife;
		invicible = false;
	}

		// Restore items
	// public void setItems() {
		// inventory.clear();
		// inventory.add(currentWeapon);
		// inventory.add(new OBJ_Key(gp));
	// }


	//Pour charger les images du Hero
	public void getPlayerImage() {

		up1 = setup("player/HeroTop1",gp.titleSize,gp.titleSize);
		up2 = setup("player/HeroTop3",gp.titleSize,gp.titleSize);
		up3 = setup("player/HeroTop2",gp.titleSize,gp.titleSize);
		down1 = setup("player/HeroDown1",gp.titleSize,gp.titleSize);
		down2 = setup("player/HeroDown3",gp.titleSize,gp.titleSize);
		down3 = setup("player/HeroDown2",gp.titleSize,gp.titleSize);
		left1 = setup("player/HeroLeft1",gp.titleSize,gp.titleSize);
		left2 = setup("player/HeroLeft3",gp.titleSize,gp.titleSize);
		left3 = setup("player/HeroLeft2",gp.titleSize,gp.titleSize);
		right1 = setup("player/HeroRight1",gp.titleSize,gp.titleSize);
		right2 = setup("player/HeroRight3",gp.titleSize,gp.titleSize);
		right3 = setup("player/HeroRight2",gp.titleSize,gp.titleSize);
	}

	//Pour charger les images d'attaque du Hero
	public void getPlayerAttackImage() {

		attackUp1 = setup("player/HeroTop2Sword",gp.titleSize,gp.titleSize*2);
		attackUp2 = setup("player/HeroTop2Sword1",gp.titleSize,gp.titleSize*2);
		attackDown1 = setup("player/HeroDown2Sword",gp.titleSize,gp.titleSize*2);
		attackDown2 = setup("player/HeroDown2Sword1",gp.titleSize,gp.titleSize*2);
		attackLeft1 = setup("player/HeroLeft2Sword",gp.titleSize*2,gp.titleSize);
		attackLeft2 = setup("player/HeroLeft2Sword1",gp.titleSize*2,gp.titleSize);
		attackLeft3 = setup("player/HeroLeft2Sword2",gp.titleSize*2,gp.titleSize);
		attackRight1 = setup("player/HeroRight2Sword",gp.titleSize*2,gp.titleSize);
		attackRight2 = setup("player/HeroRight2Sword1",gp.titleSize*2,gp.titleSize);
		attackRight3 = setup("player/HeroRight2Sword2",gp.titleSize*2,gp.titleSize);
	}
	
	//  public BufferedImage setup(String imageName) {

	// 	UtilityTool uTool = new UtilityTool();
	// 	BufferedImage image = null;

	// 	try{
	// 		image = ImageIO.read(getClass().getResourceAsStream("/res/player/"+imageName+".png"));
	// 		image = uTool.scaleImage(image, gp.titleSize, gp.titleSize);

	// 	}catch(IOException e){
	// 		e.printStackTrace();
	// 	}
	// 	return image;
	// }

	public void update() {
		//Condition pour lancer l'animation de l'attaque
		if(attacking == true) {
			attacking();
		}

		//Condition pour lancer l'animation uniquement lors du deplacement
		else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
			//Definie la variables position en fonction de la direction demander par l'utilisateur
			if(keyH.upPressed == true) {
				direction = "up";
	        } else if (keyH.downPressed == true) {
	        	direction = "down";
	        } else if (keyH.leftPressed == true) {
	        	direction = "left";
	        } else if (keyH.rightPressed == true) {
	        	direction = "right";
	        }

			// Checker les collisions avec les murs
			collisionOn = false;
			gp.checker.checkTile(this);

			// Checker les collisions avec les objets
			int objIndex = gp.checker.checkObject(this, true);
			pickUpObject(objIndex);

			// Checker les collisions avec les NPC
			int npcIndex = gp.checker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);

			// Checker les collisions avec les monstres
			int npcMonster = gp.checker.checkEntity(this, gp.monster);
			contactMonster(npcMonster);

			// Checker les collisions avec les pieges
			gp.eHandler.checkEvent();

			gp.keyH.enterPressed = false;

			// Si pas de collision le joueur peut bouger
			if(collisionOn == false && keyH.enterPressed == false){
				// System.out.println(worldX);
				// System.out.println(worldY);
				switch(direction){
					// playerY = playerX - playerSpeed;
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
				}
			}

			gp.keyH.enterPressed = false;

			//Condition afin d'animer le Hero lors de ses déplacements
			spriteCounter++;
			if(spriteCounter > 8) {
				if(spriteNum == 3) {
					spriteNum = 1;
				}else if (spriteNum == 1) {
					spriteNum = 2;
				}else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}

			if(invicible == true) {
				invicibleCounter++;
				if(invicibleCounter > 60) {
					invicible = false;
					invicibleCounter = 0;
				}
			}
		}
		if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30) {

			// SET DEFAULT COORDINATES, DIRECTION AND USER
			projectile.set(worldX, worldY, direction, true, this);

			// ADD IT TO THE LIST
			gp.projectileList.add(projectile);

			shotAvailableCounter = 0;

			gp.playSE(9);
		}

		if(shotAvailableCounter < 30) {
			shotAvailableCounter++;
		}

		if (invicible == true) {
			invicibleCounter++;
			if (invicibleCounter > 60) {
				invicible = false;
				invicibleCounter = 0;
			}
			
		}

		if (life <= 0) {
			gp.gameState = gp.gameOverState;
			// gp.playSE(11);
		}
	}

	public void attacking() {

		spriteCounter++;

		if(direction == "up" || direction == "down") {
			if(spriteCounter <= 5) {
				spriteNum = 1;
			}

			if(spriteCounter > 5 && spriteCounter <= 15) {
				spriteNum = 2;

				//HITBOX
				int currentWorldX = worldX;
				int currentWorldY = worldY;
				int solidAreaWidth = solidArea.width;
				int solidAreaHeight = solidArea.height;

				//Adjust player's worldX/Y for the attackarea
				switch(direction) {
					case "up":
					worldY -= attackArea.height;
					break;

					case "down":
					worldY += attackArea.height;
					break;

				}

				solidArea.width = attackArea.width;
				solidArea.height = attackArea.height;

				int monsterIndex = gp.checker.checkEntity(this, gp.monster);
				//Après avoir checker la collision
				worldX = currentWorldX;
				worldY = currentWorldY;
				solidArea.width = solidAreaWidth;
				solidArea.height = solidAreaHeight;
				damageMonster(monsterIndex, attack);
			}	
			if(spriteCounter > 15) {
				spriteNum = 1;
				spriteCounter = 0;
				attacking = false;
			}
		}
		else {
			if(spriteCounter <= 5) {
				spriteNum = 1;
			}
			if(spriteCounter > 5 && spriteCounter <= 10) {
				spriteNum = 2;
			}
			if(spriteCounter > 10 && spriteCounter <= 15) {
				spriteNum = 3;

				//HITBOX
				int currentWorldX = worldX;
				int currentWorldY = worldY;
				int solidAreaWidth = solidArea.width;
				int solidAreaHeight = solidArea.height;

				//Adjust player's worldX/Y for the attackarea
				switch(direction) {

					case "left":
					worldX -= attackArea.width;
					break;

					case "right":
					worldX += attackArea.width;
					break;
				}

				solidArea.width = attackArea.width;
				solidArea.height = attackArea.height;

				int monsterIndex = gp.checker.checkEntity(this, gp.monster);
				//Après avoir checker la collision
				worldX = currentWorldX;
				worldY = currentWorldY;
				solidArea.width = solidAreaWidth;
				solidArea.height = solidAreaHeight;
				damageMonster(monsterIndex, attack);
			}
			if(spriteCounter > 15) {
				spriteNum = 1;
				spriteCounter = 0;
				attacking = false;
			}
		}
	}

	public void pickUpObject(int i){ // gère les actions associés aux objets ramassés 

		if(i != 999){

			String objectName = gp.object[gp.currentMap][i].name;

			switch(objectName){
			case "Key":
				gp.playSE(1);
				hasKey++;
				gp.object[gp.currentMap][i] = null;
				gp.ui.showMessage("You got a key!");
			break;
			case "Door":
			if(hasKey > 0) {
				gp.playSE(3);
				gp.object[gp.currentMap][i] = null;
				hasKey--;
				gp.ui.showMessage("You opened the door!");
			}
			else {
				gp.ui.showMessage("You need a key!");
			}
			break;
			case "Boot":
				gp.playSE(2);
				speed += 4;
				gp.object[gp.currentMap][i] = null;
				gp.ui.showMessage("You got a pair of boots!");
			break;
			case "Chest":
				if(hasKey > 0) {
					gp.ui.gameFinished = true;
					gp.stopMusic();
					gp.playSE(4);
				}
				else {
					gp.ui.showMessage("You need a key!");
				}
			break;
			case "Potion":
				// gp.playSE(2);
				life = maxLife;
				gp.object[gp.currentMap][i] = null;
				gp.ui.showMessage("You restore your life!");
			break;
			case "Trap":
				// gp.playSE(2);
				if(invicible == false) {
					life -= 1;
					invicible = true;
					gp.ui.showMessage("You got trapped!");
				}
				else {

				}
			break;
			}
			
		}

	}

	public void interactNPC(int i) {

		if(gp.keyH.enterPressed == true) {

			if(i != 999){

				gp.gameState = gp.dialogueState;
				gp.npc[gp.currentMap][i].speak();
			}
			else {
				gp.playSE(7);
				attacking = true;
			}
		}
	}

	public void damageMonster(int i, int attack) {
		if(i != 999) {

			if(gp.monster[gp.currentMap][i].invicible == false) {
				gp.playSE(6);
				gp.monster[gp.currentMap][i].life -= attack;
				gp.monster[gp.currentMap][i].invicible = true;

				if(gp.monster[gp.currentMap][i].life <= 0) {
					gp.playSE(8);

					exp += gp.monster[gp.currentMap][i].exp;
					checkLevelUp(); 

					gp.monster[gp.currentMap][i].dying = true;
				}
			}
		}
	}

	public void checkLevelUp(){
		if(exp >= nextLevelExp){
			level++;
			nextLevelExp = nextLevelExp*2;
			maxLife +=2;
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You are level " + level + " now!\n" + " Go to the portal";
		}
	}

	public void contactMonster(int i ) {
		if (i != 999) {
			if(invicible == false) {
				gp.playSE(10);
				life -= 1;
				invicible = true;
			}
		}
	}
	
	public void draw(Graphics2D g2) { // dessine le personnage en fonction de sa direction
		
//		g2.setColor(Color.white);
//      g2.fillRect(x, y, gp.titleSize, gp.titleSize);
		
		BufferedImage image =  null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;

		// Condition qui charge les images correspondantes
		switch(direction) {
		case "up":
			if(attacking == false) {
				if(spriteNum == 1) { image = up1; }
				if(spriteNum == 2) { image = up2; }
				if(spriteNum == 3) { image = up3; }
			}
			if(attacking == true) {
				tempScreenY = screenY - gp.titleSize;
				if(spriteNum == 1) { image = attackUp1; }
				if(spriteNum == 2) { image = attackUp2; }
			}
			break;
		case "down":
			if(attacking == false) {
				if(spriteNum == 1) { image = down1; }
				if(spriteNum == 2) { image = down2; }
				if(spriteNum == 3) { image = down3; }
			}
			if(attacking == true) {
				if(spriteNum == 1) { image = attackDown1; }
				if(spriteNum == 2) { image = attackDown2; }
			}
			break;
		case "right":
			if(attacking == false) {
				if(spriteNum == 1) { image = right1; }
				if(spriteNum == 2) { image = right2; }
				if(spriteNum == 3) { image = right3; }
			}
			if(attacking == true) {
				if(spriteNum == 1) { image = attackRight1; }
				if(spriteNum == 2) { image = attackRight2; }
				if(spriteNum == 3) { image = attackRight3; }
			}
			break;
		case "left":
			if(attacking == false) {
				if(spriteNum == 1) { image = left1; }
				if(spriteNum == 2) { image = left2; }
				if(spriteNum == 3) { image = left3; }
			}
			if(attacking == true) {
				tempScreenX = screenX - gp.titleSize;
				if(spriteNum == 1) { image = attackLeft1; }
				if(spriteNum == 2) { image = attackLeft2; }
				if(spriteNum == 3) { image = attackLeft3; }
			}
			break;
		}

		g2.drawImage(image, tempScreenX, tempScreenY, null);

		//DEBUG 
		g2.setFont(new Font("Arial", Font.PLAIN, 26));
		g2.setColor(Color.white);
		g2.drawString("invicible" + invicibleCounter, 10, 400);
	}
}