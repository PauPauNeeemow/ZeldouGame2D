package object;

import entity.Entity;
import main.GameLoop;

public class OBJ_Heart extends Entity {
    
    public OBJ_Heart(GameLoop gp) {
        super(gp);

        name = "Heart";
        image = setup("object/heart_full",gp.titleSize,gp.titleSize);
        image2 = setup("object/heart_half",gp.titleSize,gp.titleSize);
        image3 = setup("object/heart_blank",gp.titleSize,gp.titleSize);

    }
}
