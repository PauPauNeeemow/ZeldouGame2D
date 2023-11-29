package Tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GameLoop;
import main.UtilityTool;

public class TileManager {

	GameLoop gp;

	public Tile[] tile;
	public int mapTileNum[][][];

	
	public TileManager(GameLoop gp) {
		
		this.gp = gp;
		
		tile = new Tile[50];

		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();//Charge toutes les tuiles pour construires la map
		loadMap("/res/maps/WorldMap.txt",0);//Fonction qui permet d'afficher la map
		loadMap("/res/maps/WorldMapLvl2.txt",1);//Fonction qui permet d'afficher la map

	}
	//Pour charger les images des maps
	public void getTileImage() {
			
		setUp(10, "BlackHole", true);
		setUp(11, "woodGrind", false);
		setUp(12, "wood2Grind", false);
		setUp(13, "wallCornerUpLeft", true);
		setUp(14, "wallCornerUpRight", true);
		setUp(15, "wallCornerDownRight", true);
		setUp(16, "wallCornerDownLeft", true);
		setUp(17, "wallDown", true);
		setUp(18, "wallSide", true);
		setUp(19, "woodGrindPiq", false);
		setUp(20, "woodGrindPotion", false);
		setUp(21, "Portal", false);
	}

	public void setUp(int index, String imageName, boolean collision) {

		UtilityTool uTool = new UtilityTool();

		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/"+ imageName +".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.titleSize, gp.titleSize);
			tile[index].collision = collision;

		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath, int map) {
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));//Fonction permettante de lire la map .txt
			
			int col = 0;
			int row = 0;
			//Boucle while pour parcourir chaque ligne du fichier txt
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {

					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
				
					mapTileNum[map][col][row] = num; //Recupere les numéros afin d'afficher les bonne images pour generer la map
					col++;
				}

				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	
	public void draw(Graphics2D g2) {

		int worldCol = 0;
		int worldRow = 0;

		//Parcour le jeux afin de génerer la map au bon nombre de case
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

			int worldX = worldCol * gp.titleSize; //Pour récupérer la taille du world
			int worldY = worldRow * gp.titleSize; //Pour récupérer la taille du world
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			if(worldX + gp.titleSize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.titleSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.titleSize > gp.player.worldY - gp.player.screenY && 
				worldY - gp.titleSize < gp.player.worldY + gp.player.screenY){
					
					g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
				worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}