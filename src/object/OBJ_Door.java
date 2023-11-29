package object;

import entity.Entity;
import main.GameLoop;

public class OBJ_Door extends Entity {

    public OBJ_Door(GameLoop gp){

        super(gp);
        name = "Door";
        down1 = setup("object/door",gp.titleSize,gp.titleSize);
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}
