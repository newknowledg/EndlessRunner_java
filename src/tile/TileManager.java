package tile;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.LineNumberReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import tile.Tile;
import main.GamePanel;
import java.awt.Color;

public class TileManager {
        
        GamePanel gp;
        Tile[] tile;
        public int mapTileNum[][];

        public TileManager(GamePanel gp) {
                
                this.gp = gp;
                tile = new Tile[10];
                setTileColor();
                loadMap();
                
        }

        public void loadMap() {
                
                try {
                  File file = new File("res/maps/map.txt");
                  Scanner scan = new Scanner(file);
                  LineNumberReader counter = new LineNumberReader(new FileReader(file));
                  String firstRow = counter.readLine();
                  String fRowA[] = firstRow.split(" ");
                  counter.skip(Long.MAX_VALUE);
                  gp.maxWorldCol = fRowA.length;
                  gp.maxWorldRow = counter.getLineNumber();
                  gp.maxWorldWidth = gp.maxWorldCol * gp.tileSize;
                  gp.maxWorldHeight = gp.maxWorldRow * gp.tileSize;
                  mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
                  counter.close();

//                InputStream is = getClass().getResourceAsStream("res/maps/map.txt");      
  //              BufferedReader br = new BufferedReader(new InputStreamReader(is));
                  int col = 0;
                  int row = 0;

                  while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                        String line = scan.nextLine();
   //                   System.out.println(line);

                        while(col < gp.maxWorldCol) {
                                String numbers[] = line.split(" ");
                                int num = Integer.parseInt(numbers[col]);
  //                            System.out.println(num);
                                mapTileNum[col][row] = num;

                                col ++;
                        }
                        if(col == gp.maxWorldCol){
                                col = 0;
                                row++;
                        }
                  }
                  scan.close();

                }catch(Exception e){
                       e.printStackTrace(); 
                }
        }
        public void setTileColor() {
                
                tile[0] = new Tile();
                tile[0].color = Color.blue;

                tile[1] = new Tile();
                tile[1].color = Color.green;
                tile[1].collision = true;

                tile[2] = new Tile();
                tile[2].color = Color.red;
                tile[2].collision = true;
        }

        public void draw(Graphics2D g2) {
                
                int worldCol = 0;
                int worldRow = 0;
                int tileNum;

                while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
                        tileNum = mapTileNum[worldCol][worldRow]; 
                        int worldX = worldCol * gp.tileSize;
                        int worldY = worldRow * gp.tileSize;
                        int screenX = worldX - gp.player.worldX + gp.player.screenX;
                        int screenY = worldY;
                        g2.setColor(tile[tileNum].color);
                        g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);

                        worldCol++;

                        if (worldCol == gp.maxWorldCol) {
                                
                                worldCol = 0;
                                worldRow++;
                        }
                }

        }

}
