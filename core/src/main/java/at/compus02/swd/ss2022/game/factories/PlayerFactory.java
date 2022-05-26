package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.*;
import com.badlogic.gdx.utils.Array;

public class PlayerFactory implements Factory
{
    GameObject gameObject;

    @Override
    public Array<GameObject> createGameObjects(Array<GameObject> gameObject, GameObjectType gameObjectType, int amountObjects, float startX, float endX, float startY, float endY)
    {
        float x = startX;
        float y = startY;

        this.gameObject = getObject(gameObjectType);
        this.gameObject.setPosition(x,y);
        gameObject.add(this.gameObject);
        return gameObject;
    }

    public GameObject createSingleGameObject(GameObjectType gameObjectType)
    {
        this.gameObject = getObject(gameObjectType);
        this.gameObject.setPosition(0,0);
        return gameObject;
    }

    @Override
    public GameObject getObject(GameObjectType gameObjectType)
    {
        switch(gameObjectType)
        {
            case Knight:
                return new Knight();
            case Questmaster:
                return new Questmaster();
            case GoodKnight:
                return new GoodKnight();
            case BadKnight:
                return new BadKnight();
            default:
                return null;
        }
    }
}
