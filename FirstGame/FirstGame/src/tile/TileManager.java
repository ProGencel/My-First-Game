package tile;

import main.windowSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    windowSettings gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(windowSettings gp)
    {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
        mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];
        loadMap("/maps/worldmap.txt");
    }

    public void getTileImage()
    {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/!grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/!wall.png")));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/!water.png")));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sand.png")));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadMap(String map)
    {
        try {
            InputStream is = getClass().getResourceAsStream(map);
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            for(int i = 0 ;i< gp.maxWorldRow;i++)
            {
                String line = br.readLine();

                for(int j = 0;j< gp.maxWorldCol;j++)
                {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[j]);

                    mapTileNum[i][j] = num;
                }
            }
            br.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2)
    {
        for(int row =0; row<gp.maxWorldRow; row++)
        {
            for(int col = 0; col<gp.maxWorldCol; col++)
            {
                int num = mapTileNum[row][col];

                int worldX = col * gp.tileSize;
                int worldY = row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

                if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize> gp.player.worldY - gp.player.screenY && worldY - gp.tileSize< gp.player.worldY + gp.player.screenY)
                {
                    g2.drawImage(tile[num].image,screenX,screenY,gp.tileSize,gp.tileSize,null);
                }
            }
        }
    }
}
