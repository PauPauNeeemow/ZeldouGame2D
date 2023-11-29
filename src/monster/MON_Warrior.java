package monster;

import entity.Entity;
import main.GameLoop;

public class MON_Warrior extends Entity {
    
    GameLoop gp;

    public int actionLockCounter;

    public MON_Warrior(GameLoop gp){
        super(gp);

        this.gp = gp;
        
        type = 2;
        name = "Warrior";
        speed = 1;
        maxLife = 3;
        life = maxLife;
        type = 2;
        exp = 1; 

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 32;
        solidArea.height = 32;
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 32;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }

    public void getImage(){

        up1 = setup("monster/WarriorUp2",gp.titleSize,gp.titleSize);
        down1 = setup("monster/WarriorBas2",gp.titleSize,gp.titleSize);
        left1 = setup("monster/WarriorLeft2",gp.titleSize,gp.titleSize);
        right1 = setup("monster/WarriorRight2",gp.titleSize,gp.titleSize);
    }
    
}
