package entities;

import main.GamePanel;
import main.KeyHandler;
import java.awt.Graphics2D; 
import java.awt.Color;
import java.awt.Rectangle;

public class Player extends Entity {
        
        KeyHandler KeyH;
        GamePanel gp;

        public final int screenX;
        public final int screenY;
        private float gravity;
        private float jumpSpeed;
        private boolean inAir;
        private boolean jumped;
        private int groundTile;
        private int ground;
//      collisionON = true;

        public Player(GamePanel gp, KeyHandler KeyH){

            this.gp = gp;
            this.KeyH = KeyH;

            screenX = 4 * gp.tileSize;
            screenY = 7 * gp.tileSize;

            setDefaultValues();
            solidArea = new Rectangle(worldX, worldY, gp.tileSize, (gp.tileSize*2));
            gravity = 0.06f * gp.tileSize;

        }
        public void setDefaultValues() {
                
                worldX = 191;
                worldY = screenY-1;
                speed = 8;
                jumpSpeed = 8;
        }
        public void update() {
               checkInAir(2);
               if (!inAir){
                   jumped = false;
                   ySpeed = 0;
               }
               if (inAir && !KeyH.jump)
                   jumped = true;
//             worldX += speed;
               if (KeyH.up == true) {
                   ySpeed = -speed;
               }
               if (KeyH.down == true) {
                   ySpeed = speed;
               }
               if (KeyH.jump == true && worldY >= 239 && !jumped) {
                  ySpeed -= jumpSpeed;
               }
//             if (KeyH.right == true) {
//                 worldX -= speed;
//             }
//             if (KeyH.left == true) {
//                 worldX += speed;
//             }
               if (worldX >= (452 * gp.tileSize)) {
                    worldX = 192;
               }
               
               if (inAir){
//                  System.out.println("in air");
                    if (worldY <= 0 || ySpeed <= -24)
                        jumped = true;

                    if (gp.cChecker.checkTile(this)) {
                         worldX += speed;
                         worldY += ySpeed;
                         ySpeed += gravity;
                    }
                    else{
                            checkInAir(ySpeed);
//                          System.out.println(inAir + " " + ySpeed);
                            if (!inAir){
                                    groundTile = (worldY / gp.tileSize) + 1;
                                    ground = (groundTile * gp.tileSize) - 1;
                                    worldY = ground;
                            }
                    }
                            
                }
                else{
 //                 System.out.println("not in ajr");
                    if (gp.cChecker.checkTile(this)) {
                         worldX += speed;
                         worldY += ySpeed;
                    }
                }

        }
        void checkInAir(int testSpeed){
              int storeSpeed = ySpeed;
              ySpeed = testSpeed;
              speed = 0;
              if (gp.cChecker.checkTile(this)){
                    inAir = true;
              }
              else
                inAir = false;

              ySpeed = storeSpeed;
              speed = 8;
        }
        public void draw(Graphics2D g2) {
                
            g2.setColor(Color.white);
            g2.fillRect(screenX, worldY, gp.tileSize, gp.tileSize * 2);
        }
}
