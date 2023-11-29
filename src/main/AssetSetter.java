package main;

import entity.NPC_OldMan;

import monster.MON_Archer;
import monster.MON_Warrior;
import monster.MON_Wizard;
import object.OBJ_Boot;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Potion;
import object.OBJ_Trap;

public class AssetSetter {
    
    GameLoop gp;

    public AssetSetter(GameLoop gp){

        this.gp = gp;
    }

    public void setObject(){

        int mapNum = 0;

        gp.object[mapNum][0] = new OBJ_Key(gp);
        gp.object[mapNum][0].worldX = 10 * gp.titleSize;
        gp.object[mapNum][0].worldY = 7 * gp.titleSize;
        gp.object[mapNum][0].direction = "down";

        gp.object[mapNum][1] = new OBJ_Key(gp);
        gp.object[mapNum][1].worldX = 5 * gp.titleSize;
        gp.object[mapNum][1].worldY = 19 * gp.titleSize;
        gp.object[mapNum][1].direction = "down";

        gp.object[mapNum][2] = new OBJ_Key(gp);
        gp.object[mapNum][2].worldX = 15 * gp.titleSize;
        gp.object[mapNum][2].worldY = 34 * gp.titleSize;
        gp.object[mapNum][2].direction = "down";

        gp.object[mapNum][3] = new OBJ_Door(gp);
        gp.object[mapNum][3].worldX = 11 * gp.titleSize;
        gp.object[mapNum][3].worldY = 12 * gp.titleSize;
        gp.object[mapNum][3].direction = "down";

        gp.object[mapNum][4] = new OBJ_Door(gp);
        gp.object[mapNum][4].worldX = 13 * gp.titleSize;
        gp.object[mapNum][4].worldY = 27 * gp.titleSize;
        gp.object[mapNum][4].direction = "down";

        gp.object[mapNum][5] = new OBJ_Door(gp);
        gp.object[mapNum][5].worldX = 17 * gp.titleSize;
        gp.object[mapNum][5].worldY = 38 * gp.titleSize;
        gp.object[mapNum][5].direction = "down";

        gp.object[mapNum][6] = new OBJ_Boot(gp);
        gp.object[mapNum][6].worldX = 13 * gp.titleSize;
        gp.object[mapNum][6].worldY = 22 * gp.titleSize;
        gp.object[mapNum][6].direction = "down";

        gp.object[mapNum][7] = new OBJ_Potion(gp);
        gp.object[mapNum][7].worldX = 12 * gp.titleSize;
        gp.object[mapNum][7].worldY = 19 * gp.titleSize;
        gp.object[mapNum][7].direction = "down";

        // Afin de gérer sur la map2
        mapNum = 1;

        gp.object[mapNum][8] = new OBJ_Chest(gp);
        gp.object[mapNum][8].worldX = 39 * gp.titleSize;
        gp.object[mapNum][8].worldY = 45 * gp.titleSize;

        gp.object[mapNum][9] = new OBJ_Boot(gp);
        gp.object[mapNum][9].worldX = 1 * gp.titleSize;
        gp.object[mapNum][9].worldY = 39 * gp.titleSize;
        gp.object[mapNum][9].direction = "down";

        gp.object[mapNum][10] = new OBJ_Key(gp);
        gp.object[mapNum][10].worldX = 30 * gp.titleSize;
        gp.object[mapNum][10].worldY = 2 * gp.titleSize;
        gp.object[mapNum][10].direction = "down";

        gp.object[mapNum][11] = new OBJ_Potion(gp);
        gp.object[mapNum][11].worldX = 46 * gp.titleSize;
        gp.object[mapNum][11].worldY = 22 * gp.titleSize;
        gp.object[mapNum][11].direction = "down";

        gp.object[mapNum][12] = new OBJ_Trap(gp);
        gp.object[mapNum][12].worldX = 37 * gp.titleSize;
        gp.object[mapNum][12].worldY = 47 * gp.titleSize;
        gp.object[mapNum][12].direction = "down";

        gp.object[mapNum][13] = new OBJ_Trap(gp);
        gp.object[mapNum][13].worldX = 32 * gp.titleSize;
        gp.object[mapNum][13].worldY = 2 * gp.titleSize;
        gp.object[mapNum][13].direction = "down";
    }

    public void setNPC(){

        int mapNum = 0;

        gp.npc[mapNum][0] = new NPC_OldMan(gp,1);
        gp.npc[mapNum][0].worldX = 2 * gp.titleSize;
        gp.npc[mapNum][0].worldY = 7 * gp.titleSize;
        gp.npc[mapNum][0].direction = "right";

        gp.npc[mapNum][1] = new NPC_OldMan(gp,2);
        gp.npc[mapNum][1].worldX = 10 * gp.titleSize;
        gp.npc[mapNum][1].worldY = 41 * gp.titleSize;
        gp.npc[mapNum][1].direction = "down";

        // Afin de gérer sur la map2
        mapNum = 1;

        gp.npc[mapNum][2] = new NPC_OldMan(gp,3);
        gp.npc[mapNum][2].worldX = 1 * gp.titleSize;
        gp.npc[mapNum][2].worldY = 42 * gp.titleSize;
        gp.npc[mapNum][2].direction = "up";
    }

    public void setMonster() {

        int mapNum = 0;

        gp.monster[mapNum][0] = new MON_Warrior(gp);
        gp.monster[mapNum][0].worldX = 11 * gp.titleSize;
        gp.monster[mapNum][0].worldY = 11 * gp.titleSize;
        gp.monster[mapNum][0].direction = "up";
        
        gp.monster[mapNum][1] = new MON_Warrior(gp);
        gp.monster[mapNum][1].worldX = 10 * gp.titleSize;
        gp.monster[mapNum][1].worldY = 8 * gp.titleSize;
        gp.monster[mapNum][1].direction = "down";


        gp.monster[mapNum][2] = new MON_Warrior(gp);
        gp.monster[mapNum][2].worldX = 11 * gp.titleSize;
        gp.monster[mapNum][2].worldY = 7 * gp.titleSize;
        gp.monster[mapNum][2].direction = "right";

        gp.monster[mapNum][3] = new MON_Wizard(gp);
        gp.monster[mapNum][3].worldX = 5 * gp.titleSize;
        gp.monster[mapNum][3].worldY = 22 * gp.titleSize;
        gp.monster[mapNum][3].direction = "up";

        gp.monster[mapNum][4] = new MON_Wizard(gp);
        gp.monster[mapNum][4].worldX = 7 * gp.titleSize;
        gp.monster[mapNum][4].worldY = 22 * gp.titleSize;
        gp.monster[mapNum][4].direction = "right";

        gp.monster[mapNum][5] = new MON_Wizard(gp);
        gp.monster[mapNum][5].worldX = 12 * gp.titleSize;
        gp.monster[mapNum][5].worldY = 25 * gp.titleSize;
        gp.monster[mapNum][5].direction = "left";

        gp.monster[mapNum][6] = new MON_Archer(gp);
        gp.monster[mapNum][6].worldX = 20 * gp.titleSize;
        gp.monster[mapNum][6].worldY = 29 * gp.titleSize;
        gp.monster[mapNum][6].direction = "left";

        gp.monster[mapNum][7] = new MON_Archer(gp);
        gp.monster[mapNum][7].worldX = 19 * gp.titleSize;
        gp.monster[mapNum][7].worldY = 32 * gp.titleSize;
        gp.monster[mapNum][7].direction = "right";

        // Afin de gérer sur la map2
        mapNum = 1;

        gp.monster[mapNum][8] = new MON_Warrior(gp);
        gp.monster[mapNum][8].worldX = 5* gp.titleSize;
        gp.monster[mapNum][8].worldY = 29 * gp.titleSize;
        gp.monster[mapNum][8].direction = "down";

        gp.monster[mapNum][9] = new MON_Warrior(gp);
        gp.monster[mapNum][9].worldX = 3* gp.titleSize;
        gp.monster[mapNum][9].worldY = 31 * gp.titleSize;
        gp.monster[mapNum][9].direction = "down";

        gp.monster[mapNum][10] = new MON_Warrior(gp);
        gp.monster[mapNum][10].worldX = 7* gp.titleSize;
        gp.monster[mapNum][10].worldY = 31 * gp.titleSize;
        gp.monster[mapNum][10].direction = "down";

        gp.monster[mapNum][11] = new MON_Archer(gp);
        gp.monster[mapNum][11].worldX = 15 * gp.titleSize;
        gp.monster[mapNum][11].worldY = 17 * gp.titleSize;
        gp.monster[mapNum][11].direction = "left";

        gp.monster[mapNum][12] = new MON_Wizard(gp);
        gp.monster[mapNum][12].worldX = 4 * gp.titleSize;
        gp.monster[mapNum][12].worldY = 6 * gp.titleSize;
        gp.monster[mapNum][12].direction = "left";

        gp.monster[mapNum][13] = new MON_Wizard(gp);
        gp.monster[mapNum][13].worldX = 5 * gp.titleSize;
        gp.monster[mapNum][13].worldY = 6 * gp.titleSize;
        gp.monster[mapNum][13].direction = "up";

        gp.monster[mapNum][14] = new MON_Wizard(gp);
        gp.monster[mapNum][14].worldX = 4 * gp.titleSize;
        gp.monster[mapNum][14].worldY = 3 * gp.titleSize;
        gp.monster[mapNum][14].direction = "left";

        gp.monster[mapNum][15] = new MON_Archer(gp);
        gp.monster[mapNum][15].worldX = 15 * gp.titleSize;
        gp.monster[mapNum][15].worldY = 2 * gp.titleSize;
        gp.monster[mapNum][15].direction = "down";

        gp.monster[mapNum][16] = new MON_Archer(gp);
        gp.monster[mapNum][16].worldX = 18 * gp.titleSize;
        gp.monster[mapNum][16].worldY = 6 * gp.titleSize;
        gp.monster[mapNum][16].direction = "left";

        gp.monster[mapNum][17] = new MON_Archer(gp);
        gp.monster[mapNum][17].worldX = 18 * gp.titleSize;
        gp.monster[mapNum][17].worldY = 3 * gp.titleSize;
        gp.monster[mapNum][17].direction = "left";

        gp.monster[mapNum][18] = new MON_Warrior(gp);
        gp.monster[mapNum][18].worldX = 31* gp.titleSize;
        gp.monster[mapNum][18].worldY = 2 * gp.titleSize;
        gp.monster[mapNum][18].direction = "right";

        gp.monster[mapNum][19] = new MON_Warrior(gp);
        gp.monster[mapNum][19].worldX = 30* gp.titleSize;
        gp.monster[mapNum][19].worldY = 3 * gp.titleSize;
        gp.monster[mapNum][19].direction = "down";

        gp.monster[mapNum][20] = new MON_Archer(gp);
        gp.monster[mapNum][20].worldX = 30 * gp.titleSize;
        gp.monster[mapNum][20].worldY = 6 * gp.titleSize;
        gp.monster[mapNum][20].direction = "right";

        gp.monster[mapNum][21] = new MON_Archer(gp);
        gp.monster[mapNum][21].worldX = 33 * gp.titleSize;
        gp.monster[mapNum][21].worldY = 2 * gp.titleSize;
        gp.monster[mapNum][21].direction = "left";

        gp.monster[mapNum][22] = new MON_Wizard(gp);
        gp.monster[mapNum][22].worldX = 45* gp.titleSize;
        gp.monster[mapNum][22].worldY = 17 * gp.titleSize;
        gp.monster[mapNum][22].direction = "left";

        gp.monster[mapNum][23] = new MON_Wizard(gp);
        gp.monster[mapNum][23].worldX = 46* gp.titleSize;
        gp.monster[mapNum][23].worldY = 17 * gp.titleSize;
        gp.monster[mapNum][23].direction = "down";

        gp.monster[mapNum][24] = new MON_Wizard(gp);
        gp.monster[mapNum][24].worldX = 43* gp.titleSize;
        gp.monster[mapNum][24].worldY = 22 * gp.titleSize;
        gp.monster[mapNum][24].direction = "up";

        gp.monster[mapNum][25] = new MON_Wizard(gp);
        gp.monster[mapNum][25].worldX = 44* gp.titleSize;
        gp.monster[mapNum][25].worldY = 22 * gp.titleSize;
        gp.monster[mapNum][25].direction = "right";

        gp.monster[mapNum][26] = new MON_Archer(gp);
        gp.monster[mapNum][26].worldX = 20 * gp.titleSize;
        gp.monster[mapNum][26].worldY = 45 * gp.titleSize;
        gp.monster[mapNum][26].direction = "right";

        gp.monster[mapNum][27] = new MON_Archer(gp);
        gp.monster[mapNum][27].worldX = 23 * gp.titleSize;
        gp.monster[mapNum][27].worldY = 46 * gp.titleSize;
        gp.monster[mapNum][27].direction = "left";

        gp.monster[mapNum][28] = new MON_Archer(gp);
        gp.monster[mapNum][28].worldX = 20 * gp.titleSize;
        gp.monster[mapNum][28].worldY = 47 * gp.titleSize;
        gp.monster[mapNum][28].direction = "right";

        gp.monster[mapNum][29] = new MON_Archer(gp);
        gp.monster[mapNum][29].worldX = 23 * gp.titleSize;
        gp.monster[mapNum][29].worldY = 48 * gp.titleSize;
        gp.monster[mapNum][29].direction = "left";

        gp.monster[mapNum][30] = new MON_Wizard(gp);
        gp.monster[mapNum][30].worldX = 38* gp.titleSize;
        gp.monster[mapNum][30].worldY = 45 * gp.titleSize;
        gp.monster[mapNum][30].direction = "left";

        gp.monster[mapNum][31] = new MON_Wizard(gp);
        gp.monster[mapNum][31].worldX = 39* gp.titleSize;
        gp.monster[mapNum][31].worldY = 46 * gp.titleSize;
        gp.monster[mapNum][31].direction = "down";

        gp.monster[mapNum][32] = new MON_Wizard(gp);
        gp.monster[mapNum][32].worldX = 36* gp.titleSize;
        gp.monster[mapNum][32].worldY = 47 * gp.titleSize;
        gp.monster[mapNum][32].direction = "right";

    }
}