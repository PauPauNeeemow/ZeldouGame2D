package object;

import entity.Entity;
import main.GameLoop;

public class OBJ_Boot extends Entity {

    public OBJ_Boot(GameLoop gp){
        super(gp);

        name = "Boot";
        down1 = setup("object/boots",gp.titleSize,gp.titleSize);
    }
}
