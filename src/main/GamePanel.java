package main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import entities.Player;
import tile.TileManager;


public class GamePanel extends JPanel implements Runnable{
        final int originalTileSize = 16;
        final int scale = 3;
        public final int tileSize = originalTileSize * scale;
        final public int maxScreenCol = 16;
        final public int maxScreenRow = 10;
        final int screenWidth = tileSize * maxScreenCol;
        final int screenHeight = tileSize * maxScreenRow;

        public int maxWorldCol;
        public int maxWorldRow;
        public int maxWorldWidth;
        public int maxWorldHeight;
        final int FPS = 60;

        TileManager tileM = new TileManager(this);
        KeyHandler KeyH = new KeyHandler();
        Thread gameThread;
        public CollisionChecker cChecker = new CollisionChecker(this);
        public Player player = new Player(this, KeyH);


        int playerX = 100;
        int playerY = 100;
        int playerSpeed = 4;

        public GamePanel(){

                this.setPreferredSize(new Dimension(screenWidth, screenHeight));
                this.setBackground(Color.black);
                this.setDoubleBuffered(true);
                this.addKeyListener(KeyH);
                this.setFocusable(true);
        }

        public void startGameThread(){
                
                gameThread = new Thread(this);
                gameThread.start();
        }

        @Override
        public void run(){

                double drawInterval = 1000000000 / FPS;
                double delta = 0;
                double delta2 = 0;
                long lastTime = System.nanoTime();
                long currentTime;

                while(gameThread != null) {
                        
                        currentTime = System.nanoTime();
                        delta += (currentTime - lastTime) / drawInterval;
                        delta2 += (currentTime - lastTime) / drawInterval;
                        lastTime = currentTime;

                        if (delta >= 1) {
                            update();

            //              repaint();

                            delta--;
                                
                        }

                        if (delta2 >= 0.125) {
                            repaint();
                            delta2--;
                                
                        }

                }
        }

        public void update() {

            player.update();
               
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g;
            tileM.draw(g2);
            player.draw(g2);
            g2.dispose();
        }
}
