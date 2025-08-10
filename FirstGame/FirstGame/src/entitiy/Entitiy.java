package entitiy;

import java.awt.image.BufferedImage;

public class Entitiy {

    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage up1 ,up2 ,down1 ,down2 ,left1 ,left2,right1 ,right2 ;
    public String direction;

    protected int imageCounter = 0;
    protected int imageNum = 1;
}
