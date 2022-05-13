package at.compus02.swd.ss2022.game.tile_factory;


import at.compus02.swd.ss2022.game.gameobjects.*;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class TileFactory
{
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
        for (int i = 0; i < amountTiles; i++)
        {
            gameObject.add(getTile(tileType));
        }

        return gameObject;
    }
}
