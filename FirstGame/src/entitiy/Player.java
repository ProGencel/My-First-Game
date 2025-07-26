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
    public Player(windowSettings gp,KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues()
    {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void Update()
    {
        if(keyH.isUp)
        {
            direction = "up";
            y -= speed;
        }
        else if(keyH.isDown)
        {
            direction = "down";
            y += speed;
        }
        else if(keyH.isRight)
        {
            direction = "right";
            x += speed;
        }
        else if(keyH.isLeft)
        {
            direction = "left";
            x -= speed;
        }
        imageCounter++;
        if(imageCounter>12)
        {
            if(imageNum == 1)
            {
                imageNum = 2;
            }
            else if(imageNum == 2)
            {
                imageNum = 1;
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

        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);

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
