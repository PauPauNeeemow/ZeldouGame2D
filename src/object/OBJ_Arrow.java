package object;

import entity.Projectile;
import main.GameLoop;

public class OBJ_Arrow extends Projectile {

    GameLoop gp;

    public OBJ_Arrow(GameLoop gp) {
        super(gp);
        this.gp = gp;

        name = "Arrow";
        speed = 15;
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
        up1 = setup("projectile/ArrowTop", gp.titleSize,gp.titleSize);
        up2 = setup("projectile/ArrowTop", gp.titleSize,gp.titleSize);
        down1 = setup("projectile/ArrowDown", gp.titleSize,gp.titleSize);
        down2 = setup("projectile/ArrowDown", gp.titleSize,gp.titleSize);
        left1 = setup("projectile/ArrowLeft", gp.titleSize,gp.titleSize);
        left2 = setup("projectile/ArrowLeft", gp.titleSize,gp.titleSize);
        right1 = setup("projectile/ArrowRight", gp.titleSize,gp.titleSize);
        right2 = setup("projectile/ArrowRight", gp.titleSize,gp.titleSize);
    }
    
}