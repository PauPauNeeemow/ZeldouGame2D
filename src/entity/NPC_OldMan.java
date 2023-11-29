package entity;

import main.GameLoop;

public class NPC_OldMan extends Entity {
    

    public NPC_OldMan(GameLoop gp, int npcDialogue) {
        super(gp);

		type = 4;
        direction = "down";
        speed = 1;//vitesse

        getImage();
		setDialogue(npcDialogue);
    }

    public void getImage() { //permet de récuperer les différentes images du pnj 

		up1 = setup("npc/oldman_up_1",gp.titleSize,gp.titleSize);
		up2 = setup("npc/oldman_up_2",gp.titleSize,gp.titleSize);
		down1 = setup("npc/oldman_down_1",gp.titleSize,gp.titleSize);
		down2 = setup("npc/oldman_down_2",gp.titleSize,gp.titleSize);
		left1 = setup("npc/oldman_left_1",gp.titleSize,gp.titleSize);
		left2 = setup("npc/oldman_left_2",gp.titleSize,gp.titleSize);
		right1 = setup("npc/oldman_right_1",gp.titleSize,gp.titleSize);
		right2 = setup("npc/oldman_right_2",gp.titleSize,gp.titleSize);
	}
	public void setDialogue(int dialogueNPC){ // le dialogue du pnj 
		if(dialogueNPC == 1) {
			dialogues[0] ="Hello Hero Welcome to the game";
			dialogues[1]= "So you've come to this dungeon to find the treasure";
			dialogues[2]="But be careful, there are many traps and enemies in \nhere!";
		}
		else if (dialogueNPC == 2) {
			dialogues[0] ="Pass this portal to go to the next lvl !!!";
		}
		else if (dialogueNPC == 3) {
			dialogues[0] ="Don't forget to find the treasure !!!";
		}
	}
	public void speak(){ //fonction appelée pour que le pnj parle 
		
		super.speak();
	}
}
