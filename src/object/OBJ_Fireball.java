package object;

import entity.Projectile;
import main.GameLoop;

public class OBJ_Fireball extends Projectile {
    GameLoop gp;

    public OBJ_Fireball(GameLoop gp) {
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 5;
        maxLife = 60;
        life = maxLife;
        attack = 2;
        alive = false;
        getImage();

        solidArea.x = 9;
        solidArea.y = 10;
        solidArea.width = 13;
        solidArea.height = 13;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void getImage() {
        up1 = setup("projectile/FireballTop1", gp.titleSize,gp.titleSize);
        up2 = setup("projectile/FireballTop2", gp.titleSize,gp.titleSize);
        down1 = setup("projectile/FireballDown1", gp.titleSize,gp.titleSize);
        down2 = setup("projectile/FireballDown2", gp.titleSize,gp.titleSize);
        left1 = setup("projectile/FireballLeft1", gp.titleSize,gp.titleSize);
        left2 = setup("projectile/FireballLeft2", gp.titleSize,gp.titleSize);
        right1 = setup("projectile/FireballRight1", gp.titleSize,gp.titleSize);
        right2 = setup("projectile/FireballRight2", gp.titleSize,gp.titleSize);
    }
}
