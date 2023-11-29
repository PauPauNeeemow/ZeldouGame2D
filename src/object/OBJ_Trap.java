package object;

import entity.Entity;
import main.GameLoop;

public class OBJ_Trap extends Entity {

    public OBJ_Trap(GameLoop gp){
        super(gp);

        name = "Trap";
        down1 = setup("object/TrapDown",gp.titleSize,gp.titleSize);        
    }

}
