package main;

public class EventHandler {
    
    GameLoop gp;
    EventRect eventRect[][][];// rectangle utilisé pour définir la zone d'interaction des événements
    
    int previousEventX, previousEventY;
    public boolean canTouchEvent = true;

    public EventHandler(GameLoop gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        // eventRect.x = 23;
        // eventRect.y = 23;
        // eventRect.width = 2;
        // eventRect.height = 2;
        // eventRectDefaultX = eventRect.x;
        // eventRectDefaultY = eventRect.y;

        int map = 0;
        int col = 0;
        int row = 0;
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {

            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23; 
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
        
            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void checkEvent() { //utilise la méthode hit pour détecter les collisions 

        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.titleSize) {
            canTouchEvent = true;
        }

        if(canTouchEvent == true) {
            if(hit(0, 2,2,"up") == true || hit(0, 2,2,"left") == true) { damagePit(gp.dialogueState); }
            else if(hit(0, 11, 38, "any") == true) { teleport(1, 1, 37) ;}
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection) { // verifie si le joueur rentre en collision avec un evenement, utilise le rectangle de collision du joueur, la méthode renvoie vrai si une collision est détectée dans la direction requise

        boolean hit = false;

        if(map == gp.currentMap) {

            if(eventRect[map][col][row] != null) {
                gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
                gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
                eventRect[map][col][row].x = col*gp.titleSize + eventRect[map][col][row].x;
                eventRect[map][col][row].y = row*gp.titleSize + eventRect[map][col][row].y;
                
                // Si le jouer entre en collision avec le piège
                if(gp.player.solidArea.intersects(eventRect[map][col][row])) {
                    if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                        hit = true;

                        previousEventX = gp.player.worldX;
                        previousEventY = gp.player.worldY;
                    }
                }

                gp.player.solidArea.x = gp.player.solidAreaDefaultX;
                gp.player.solidArea.y = gp.player.solidAreaDefaultY;
                eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
                eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
            }
        }
        return hit;
    }

    public void damagePit(int gameState) { //change l'état du jeu en mode dialogue, affiche un message indiquant que le perso est tombé dans un piège, diminue la vie du joueur de 1.

        gp.gameState = gameState;
        gp.ui.currentDialogue = "Your got trap !";
        gp.player.life -= 1;
    }

    public void healingPool(int gameState) { // verifie si la touche entrée est enfoncée si oui mode dialogue affiche un message, renitialise la vie du joueur à la valeur max 

        if(gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Your life has been recovered.";
            gp.player.life = 6;
        }
    }

    public void teleport(int map, int col, int row){

        gp.currentMap = map;
        gp.player.worldX = gp.titleSize * col;
        gp.player.worldY = gp.titleSize * row;
        previousEventX = gp.player.worldX;
        previousEventY = gp.player.worldY;
        canTouchEvent = false;
    }
}