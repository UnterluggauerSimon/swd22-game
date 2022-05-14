package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.*;
import com.badlogic.gdx.utils.Array;

public class PlayerFactory implements Factory
{
    GameObject gameObject;

    @Override
    public Array<GameObject> createGameObjects(Array<GameObject> gameObject, GameObjectType gameObjectType, int amountTiles)
    {
        float x = 50;
        float y = -50;

        this.gameObject = getObject(gameObjectType);
        this.gameObject.setPosition(x,y);
        gameObject.add(this.gameObject);
        return gameObject;
    }

    @Override
    public GameObject getObject(GameObjectType gameObjectType)
    {
        switch(gameObjectType)
        {
            case Knight:
                return new Knight();
            default:
                return null;
        }
    }
}
