package entitiy;

import main.KeyHandler;
import main.windowSettings;

public class Player extends Entitiy {

    windowSettings gp;
    KeyHandler keyH;
    public Player(windowSettings gp,KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;
    }
    public void setDefaultValues()
    {
        x = 100;
        y = 100;
        speed = 4;
    }
}
