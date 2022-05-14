package at.compus02.swd.ss2022.game.factories;


import at.compus02.swd.ss2022.game.gameobjects.*;
import com.badlogic.gdx.utils.Array;

public class TileFactory implements Factory
{
    GameObject gameObject;

    @Override
    public GameObject getObject(GameObjectType gameObjectType)
    {
        switch(gameObjectType)
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

    @Override
    public Array<GameObject> createGameObjects(Array<GameObject> gameObject, GameObjectType gameObjectType, int amountTiles)
    {
        float x = -240;
        float y = 210;
        for (int i = 0; i < amountTiles; i++)
        {
            if(x <= 240){
                this.gameObject = getObject(gameObjectType);
                this.gameObject.setPosition(x,y);
                gameObject.add(this.gameObject);
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
