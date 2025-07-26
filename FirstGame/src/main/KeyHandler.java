package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean isUp;
    public boolean isDown;
    public boolean isLeft;
    public boolean isRight;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W)
        {
            isUp = true;
        }
        if(code == KeyEvent.VK_S)
        {
            isDown = true;
        }
        if(code == KeyEvent.VK_D)
        {
            isRight = true;
        }
        if(code == KeyEvent.VK_A)
        {
            isLeft = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W)
        {
            isUp = false;
        }
        if(code == KeyEvent.VK_S)
        {
            isDown = false;
        }
        if(code == KeyEvent.VK_D)
        {
            isRight = false;
        }
        if(code == KeyEvent.VK_A)
        {
            isLeft = false;
        }
    }
}
