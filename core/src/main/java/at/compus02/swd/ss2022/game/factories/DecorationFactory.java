package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.*;
import com.badlogic.gdx.utils.Array;

public class DecorationFactory implements Factory
{
    GameObject gameObject;

    @Override
    public Array<GameObject> createGameObjects(Array<GameObject> gameObject, GameObjectType gameObjectType, int amountObjects, float startX, float endX, float startY, float endY)
    {
        float x = 0;
        float y = 0;

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
            case Bush:
                return new Bush();
            case Log:
                return new Log();
            case Sign:
                return new Sign();
            case Flame:
                return new Flame();
            case BigTree:
                return new BigTree();
            case LittleTree:
                return new LittleTree();
            case Hearth:
                return new Hearth();
            default:
                return null;
        }
    }

}
