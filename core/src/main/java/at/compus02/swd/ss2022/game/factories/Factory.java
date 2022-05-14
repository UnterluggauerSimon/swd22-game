package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import com.badlogic.gdx.utils.Array;

public interface Factory
{
    Array<GameObject> createGameObjects(Array<GameObject> gameObject, GameObjectType gameObjectType, int amountObjects, float startX, float endX, float startY, float endY);
    GameObject getObject(GameObjectType gameObjectType);
}
