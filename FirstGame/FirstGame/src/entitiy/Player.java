package entitiy;

import main.KeyHandler;
import main.windowSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entitiy {

    windowSettings gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(windowSettings gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;
        this.screenX = (gp.screenWidth/2) - (gp.tileSize/2);
        this.screenY = (gp.screenHeigh/2) - (gp.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues()
    {
        worldX = 25 * gp.tileSize;
        worldY = 25 * gp.tileSize;
        speed = 6;
        direction = "down";
    }
    public void Update()
    {
        if(keyH.isUp)
        {
            direction = "up";
            worldY -= speed;
        }
        if(keyH.isDown)
        {
            direction = "down";
            worldY += speed;
        }
        if(keyH.isRight)
        {
            direction = "right";
            worldX += speed;
        }
        if(keyH.isLeft)
        {
            direction = "left";
            worldX -= speed;
        }

        imageCounter++;
        if(imageCounter>12)
        {
            if(keyH.isAnythingPressed)
            {
                if(imageNum == 1)
                {
                    imageNum = 2;
                }
                else if(imageNum == 2)
                {
                    imageNum = 1;
                }
            }
            imageCounter = 0;
        }
    }
    public void Draw(Graphics2D g2)
    {
        BufferedImage image = null;

        switch (direction)
        {
            case "up":
                if(imageNum == 1)
                {
                    image = up1;
                }
                else if(imageNum == 2)
                {
                    image = up2;
                }
                break;
            case "down":
                if(imageNum == 1)
                {
                    image = down1;
                }
                else if(imageNum == 2)
                {
                    image = down2;
                }
                break;
            case "left":
                if(imageNum == 1)
                {
                    image = left1;
                }
                else if(imageNum == 2)
                {
                    image = left2;
                }
                break;
            case "right":
                if(imageNum == 1)
                {
                    image = right1;
                }
                else if(imageNum == 2)
                {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);

    }
    public void getPlayerImage()
    {
        try {

            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
