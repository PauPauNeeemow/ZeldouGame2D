package object;

import entity.Entity;
import main.GameLoop;

public class OBJ_Chest extends Entity {

    public OBJ_Chest(GameLoop gp){
        super(gp);

        name = "Chest";
        down1 = setup("object/chest",gp.titleSize,gp.titleSize);        
    }

}
