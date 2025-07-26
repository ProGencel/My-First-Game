package main;

import javax.swing.*;
import java.awt.*;

public class windowSettings extends JPanel implements Runnable
{
    final int originalTileSize = 16;    //16x16 tiles
    final int scale = 4;    //16 pixel kucuk olacagi icin herseyi carpacagimiz katsayi

    final int tileSize = scale * originalTileSize;  //64x64 tiles
    final int maxScreenCol = 16;    //kolon sayiis
    final int maxScreenRow = 12;    // satir sayisi
    final int screenWidth = maxScreenCol*tileSize;  //16x64 pixels
    final int screenHeigh = maxScreenRow*tileSize;  //12x64 pixels

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 5;
    int FPS = 60;

    public windowSettings()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeigh));  //kabaca boyut ayarlama
        this.setBackground(Color.DARK_GRAY);                                //renk
        this.setDoubleBuffered(true);                                   //cizimlerin daha akici olmasini saglar
        this.addKeyListener(keyH);                                      // keyHandler classimizi ekliyoruz
        this.setFocusable(true);            //bir tusa fokuslanma
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (double) 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null)
        {

            update();

            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if(remainingTime < 0)
                {
                    remainingTime = 0;
                }

                nextDrawTime += drawInterval;

                Thread.sleep((long) remainingTime);
            } catch (InterruptedException ignored) {

            }
        }
    }

    public void update()
    {
        if(keyH.isUp)
        {
            playerY -= playerSpeed;
        }
        else if(keyH.isDown)
        {
            playerY += playerSpeed;
        }
        else if(keyH.isRight)
        {
            playerX += playerSpeed;
        }
        else if(keyH.isLeft)
        {
            playerX -= playerSpeed;
        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);    //guzel bir arkaplan ayarliyor

        Graphics2D g2 = (Graphics2D)g;//graphics i graphics2d ye cevirme
        g2.setColor(Color.lightGray);//renk
        g2.fillRect(playerX,playerY,tileSize,tileSize);//rectangle olusturur (konum,konum,boyut,boyut)
        g2.dispose();//salmak
    }
}