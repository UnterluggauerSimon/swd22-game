package at.compus02.swd.ss2022.game.factories;

import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import com.badlogic.gdx.utils.Array;

public interface Factory
{
    Array<GameObject> createGameObjects(Array<GameObject> gameObject, GameObjectType gameObjectType, int amountTiles);
    GameObject getObject(GameObjectType gameObjectType);
}
