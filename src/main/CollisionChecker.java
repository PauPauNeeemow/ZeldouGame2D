package main;
import entity.Entity;

public class CollisionChecker {
    
    GameLoop gp;

    public CollisionChecker(GameLoop gp){

        this.gp = gp;
    }

    public void checkTile(Entity entity){ // verifie les collisions avec les tuiles de niveau en fonction de la direction du mouvement de l'entité 
    // utilise les coordonées du rectangle de collision de l'entité pour déterminer les tuiles concernés 
    //met a jour l'indicateur collisionOn en cas de collision avec l'objet
        
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.titleSize;
        int entityRightCol = entityRightWorldX/gp.titleSize;
        int entityTopRow = entityTopWorldY/gp.titleSize;
        int entityBottomRow = entityBottomWorldY/gp.titleSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.titleSize; // Prévoir la vitesse du mouvement de déplacement du perso
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;

                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.titleSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;

                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.titleSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;

                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.titleSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;

                }
            break;
        }
    }

    public int checkObject(Entity entity, boolean player){ // verifie les collisions avec les objets du jeu 

        int index = 999;

        if(gp.object[this.gp.currentMap] != null) {
            for (int i = 0; i < gp.object[1].length; i++){
                if(gp.object[gp.currentMap][i] != null){
                    
                    // Get Hero position
                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;

                    // Get Object position
                    gp.object[gp.currentMap][i].solidArea.x = gp.object[gp.currentMap][i].worldX + gp.object[gp.currentMap][i].solidArea.x;
                    gp.object[gp.currentMap][i].solidArea.y = gp.object[gp.currentMap][i].worldY + gp.object[gp.currentMap][i].solidArea.y;

                    switch(entity.direction) {
                        case"up":
                            entity.solidArea.y -= entity.speed;
                            break;
                        case"down":
                            entity.solidArea.y += entity.speed;
                            break;
                        case"left":
                            entity.solidArea.x -= entity.speed;
                            break;
                        case"right":
                            entity.solidArea.x += entity.speed;
                            break;
                    }

                    if(entity.solidArea.intersects(gp.object[gp.currentMap][i].solidArea)) {
                        if(gp.object[gp.currentMap][i].collision == true){
                            entity.collisionOn = true; // en cas de collision cela met à jour collisionON
                        }
                        if(player == true){ // si player est vrai retourne l'index de l'objet ramassé par le joueur
                            index = i;
                        }
                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gp.object[gp.currentMap][i].solidArea.x = gp.object[gp.currentMap][i].solidAreaDefaultX;
                    gp.object[gp.currentMap][i].solidArea.y = gp.object[gp.currentMap][i].solidAreaDefaultY;
                }
            }
        }

        return index;
    }

    // NPC OR MONSTER
    public int checkEntity(Entity entity, Entity[][] target) {

        //verifie les collisions avec d'autres entités du jeu  en fonction de la direction de l'entité
        int index = 999;

        for (int i = 0; i < target[1].length; i++){
            if(target[gp.currentMap][i] != null){
                
                // Get Hero position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // Get Object position
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

                switch(entity.direction) {
                    case"up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case"down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case"left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case"right":
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                    if (target[gp.currentMap][i] != entity) {
                        entity.collisionOn = true;
                        index = i;     
                    }
                }
                
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }

        return index;
    }

    //PLAYER
    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;
        // Get Hero position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        // Get Object position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch(entity.direction) {
        case"up": entity.solidArea.y -= entity.speed; break;
        case"down": entity.solidArea.y += entity.speed; break;
        case"left": entity.solidArea.x -= entity.speed; break;
        case"right": entity.solidArea.x += entity.speed; break;
        }

        if(entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    
        return contactPlayer;
    }
}