package object;

import entity.Entity;
import main.GameLoop;

public class OBJ_Potion extends Entity {

    public OBJ_Potion(GameLoop gp){
        super(gp);

        name = "Potion";
        down1 = setup("object/PotionDown",gp.titleSize,gp.titleSize);        
    }

}
