package at.compus02.swd.ss2022.game.tile_factory;


import at.compus02.swd.ss2022.game.gameobjects.*;
import com.badlogic.gdx.utils.Array;

public class TileFactory
{
    public GameObject getTile(TileType tileType)
    {
        switch(tileType)
        {
            case Gras:
                return new TileGras();
            case Gravel:
                return new TileGravel();
            case Wall:
                return new TileWall();
            case Water:
                return new TileWater();
            default:
                return null;
        }
    }

    public Array<GameObject> createTiles(Array<GameObject> gameObject, TileType tileType, int amountTiles)
    {
        for (int i = 0; i < amountTiles; i++)
        {
            gameObject.add(getTile(tileType));
        }

        return gameObject;
    }
}
