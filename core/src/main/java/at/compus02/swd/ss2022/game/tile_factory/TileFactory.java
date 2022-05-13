package at.compus02.swd.ss2022.game.tile_factory;


import at.compus02.swd.ss2022.game.gameobjects.*;
import com.badlogic.gdx.utils.Array;

public class TileFactory
{
    GameObject gO;
    public GameObject getTile(String tileType)
    {
        switch(tileType)
        {
            case "gras":
                return new TileGras();
            case "gravel":
                return new TileGravel();
            case "wall":
                return new TileWall();
            case "water":
                return new TileWater();
            default:
                return null;
        }
    }

    public Array<GameObject> createTiles(Array<GameObject> gameObject, String tileType, int amountTiles)
    {
        float x = -240;
        float y = 210;
        for (int i = 0; i < amountTiles; i++)
        {
            if(x <= 240){
                gO = getTile(tileType);
                gO.setPosition(x,y);
                gameObject.add(gO);
                x=x+32;
            }else{
                x=-240;
                if(y <= -210){
                    return gameObject;
                }
                y=y-32;
            }
        }

        return gameObject;
    }
}
