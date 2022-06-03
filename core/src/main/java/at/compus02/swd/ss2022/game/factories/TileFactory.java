package at.compus02.swd.ss2022.game.factories;


import at.compus02.swd.ss2022.game.gameobjects.*;
import com.badlogic.gdx.utils.Array;

public class TileFactory implements Factory
{
    GameObject gameObject;

    @Override
    public Array<GameObject> createGameObjects(Array<GameObject> gameObject, GameObjectType gameObjectType, int amountObjects, float startX, float endX, float startY, float endY)
    {
        float x = startX;
        float y = startY;
        for (int i = 0; i < amountObjects; i++) {
            this.gameObject = getObject(gameObjectType);
            this.gameObject.setPosition(x, y);
            gameObject.add(this.gameObject);
            x += 32;
            if (x > endX) {
                y -= 32;
                x = startX;
            }
            if (y < endY) {
                y = startY;
            }
        }
        return gameObject;
    }

    public GameObject createSingleGameObject(GameObjectType gameObjectType, float x, float y)
    {
        this.gameObject = getObject(gameObjectType);
        this.gameObject.setPosition(x,y);
        System.out.println(String.format("Created %s at X-Position %f and Y-Position %f", gameObjectType.name(), x, y));
        return this.gameObject;
    }

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
            case Bridge:
                return new Bridge();
            default:
                return null;
        }
    }
}
