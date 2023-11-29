package object;

import entity.Entity;
import main.GameLoop;

public class OBJ_Key extends Entity {
    
    public OBJ_Key(GameLoop gp){
        super(gp);
        
        name = "Key";
        down1 = setup("object/key",gp.titleSize,gp.titleSize);

        solidArea.x = 5;
        solidArea.y = 5;
        solidArea.width = 20;
        solidArea.height = 25;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

}