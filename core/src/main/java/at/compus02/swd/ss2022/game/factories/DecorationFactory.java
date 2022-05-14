package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.*;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class DecorationFactory implements Factory
{
    GameObject gameObject;

    @Override
    public Array<GameObject> createGameObjects(Array<GameObject> gameObject, GameObjectType gameObjectType, int amountObjects, float startX, float endX, float startY, float endY)
    {
        float x = startX;
        float y = startY;
        Random rand = new Random();
        int counter = 0;

        while(counter < amountObjects) {
            this.gameObject = getObject(gameObjectType);

            if(rand.nextInt(10) % 10 == 0)
            {
                this.gameObject.setPosition(x, y);
                gameObject.add(this.gameObject);
                counter++;
            }
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
            default:
                return null;
        }
    }

}
