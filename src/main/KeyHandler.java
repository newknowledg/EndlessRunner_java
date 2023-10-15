package main;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class KeyHandler implements KeyListener{
        
        public boolean up, down, left, right, jump;

        @Override
        public void keyPressed(KeyEvent e) {
                
                int code = e.getKeyCode();

                if (code == KeyEvent.VK_W) {
                       up = true;
                }
                if (code == KeyEvent.VK_S) {
                       down = true;
                }
                if (code == KeyEvent.VK_A) {
                       right = true;
                }
                if (code == KeyEvent.VK_D) {
                       left = true;
                }
                if (code == KeyEvent.VK_SPACE) {
                       jump = true;
                }
        }

        @Override
        public void keyReleased(KeyEvent e) {
                
                int code = e.getKeyCode();

                if (code == KeyEvent.VK_W) {
                       up = false;
                }
                if (code == KeyEvent.VK_S) {
                       down = false;
                }
                if (code == KeyEvent.VK_A) {
                       right = false;
                }
                if (code == KeyEvent.VK_D) {
                       left = false;
                }
                if (code == KeyEvent.VK_SPACE) {
                       jump = false;
                }
        }

        @Override
        public void keyTyped(KeyEvent e) {
                
        }
}
