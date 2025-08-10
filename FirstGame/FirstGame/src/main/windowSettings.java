package main;

import entitiy.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class windowSettings extends JPanel implements Runnable
{
    final int originalTileSize = 16;    //16x16 tiles
    final int scale = 4;    //16 pixel kucuk olacagi icin herseyi carpacagimiz katsayi

    public final int tileSize = scale * originalTileSize;  //64x64 tiles
    public final int maxScreenCol = 16;    //sutun sayiis
    public final int maxScreenRow = 12;    // satir sayisi
    public final int screenWidth = maxScreenCol*tileSize;  //16x64 pixels
    public final int screenHeigh = maxScreenRow*tileSize;  //12x64 pixels

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    TileManager tileM = new TileManager(this);

    int FPS = 60;
    public Player player = new Player(this,keyH);

    public windowSettings()
    {
        this.setPreferredSize(new Dimension(screenWidth,screenHeigh));  //kabaca boyut ayarlama
        //this.setBackground(Color.DARK_GRAY);                                //renk
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

        // FPS sayaç değişkenleri
        int frameCount = 0;
        long lastTimer = System.currentTimeMillis();

        while (gameThread != null) {

            update();
            repaint();
            frameCount++;

            // Her 1 saniyede bir FPS'yi yazdır
            if (System.currentTimeMillis() - lastTimer >= 1000) {
                System.out.println("FPS: " + frameCount);
                frameCount = 0;
                lastTimer += 1000;
            }

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException ignored) {}
        }
    }


    public void update()
    {
        player.Update();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);    //guzel bir arkaplan ayarliyor

        Graphics2D g2 = (Graphics2D)g;//graphics i graphics2d ye cevirme

        tileM.draw(g2);

        player.Draw(g2);

        g2.dispose();//salmak
    }
}