package main;

import main.GamePanel;
import entities.Entity;

public class CollisionChecker {
        
        GamePanel gp;
        Entity entity;

        public CollisionChecker(GamePanel gp) {
                this.gp = gp;
        }
        public boolean checkTile (Entity entity) {
            this.entity = entity;
            if(!isSolid(entity.worldX + gp.tileSize + entity.speed, entity.worldY + entity.ySpeed, gp.tileM.mapTileNum))
                if(!isSolid(entity.worldX + gp.tileSize + entity.speed, entity.worldY + (gp.tileSize * 2) + entity.ySpeed, gp.tileM.mapTileNum))
                    if(!isSolid(entity.worldX + entity.speed, entity.worldY + entity.ySpeed, gp.tileM.mapTileNum))
                        if(!isSolid(entity.worldX + entity.speed, entity.worldY + (gp.tileSize * 2) + entity.ySpeed, gp.tileM.mapTileNum))
                        return true;
            return false;
        }
        private boolean isSolid(int x, int y, int[][] lvldata) {
            int yIndex = y / gp.tileSize;
            int xIndex = x / gp.tileSize;
            int tileNum = lvldata[xIndex][yIndex];
//          System.out.println(tileNum + " "+ x + " " + y);
            if (tileNum > 0)
                return true;
            return false;
        }
}
