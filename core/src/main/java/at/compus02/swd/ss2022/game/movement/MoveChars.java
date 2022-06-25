package at.compus02.swd.ss2022.game.movement;

import at.compus02.swd.ss2022.game.converter.MapCalculator;
import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.observer.Observer;
import at.compus02.swd.ss2022.game.observer.Subject;
import at.compus02.swd.ss2022.game.playableChars.Enemy;
import at.compus02.swd.ss2022.game.playableChars.MainPlayer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MoveChars implements Subject
{
    MapCalculator mapCalculator = new MapCalculator();
    MainPlayer mainPlayer;
    private int moveLength = 1;

    private String movedDirection;

    private String message;
    private List<Observer> channels = new ArrayList<>();

    public MoveChars()
    {
    }

    public void movePlayerNormal(GameObject[][] newMap, MainPlayer mainPlayer)
    {
        moveLength = 4;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            moveLeft(newMap, mainPlayer);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveRight(newMap, mainPlayer);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            moveUp(newMap, mainPlayer);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            moveDown(newMap, mainPlayer);
        }
    }

    public void followPlayer(GameObject[][] newMap, GameObject enemy)
    {
        mainPlayer = MainPlayer.getInstance();
        moveLength = 1;
        float targetX = mainPlayer.getX();
        float targetY = mainPlayer.getY();

        if (enemy.getX() > targetX)
        {
            moveLeft(newMap, enemy);
        }
        if (enemy.getX() < targetX)
        {
            moveRight(newMap, enemy);
        }
        if (enemy.getY() > targetY)
        {
            moveDown(newMap, enemy);
        }
        if (enemy.getY() < targetY)
        {
            moveUp(newMap, enemy);
        }
    }

    public void runFromPlayer(GameObject[][] newMap, GameObject enemy)
    {
        moveLength = 1;
        float targetX = 130;
        float targetY = 130;
        mainPlayer = MainPlayer.getInstance();

        if (mainPlayer.getX() > targetX)
        {
            moveLeft(newMap, enemy);
            return;
        }
        if (mainPlayer.getY() > targetY)
        {
            moveDown(newMap, enemy);
            return;
        }

        if (enemy.getX() < targetX)
        {
            moveRight(newMap, enemy);
        }
        if (enemy.getX() > targetX)
        {
            moveLeft(newMap, enemy);
        }
        if (enemy.getY() < targetY)
        {
            moveUp(newMap, enemy);
        }
        if (enemy.getY() > targetY)
        {
            moveDown(newMap, enemy);
        }
    }

    public void moveLeft(GameObject[][] newMap, GameObject gameObject)
    {
        movedDirection = gameObject.toString() + " moved Left";
        notifyUpdate(movedDirection);

        if (mapCalculator.isMoveAllowed(newMap, gameObject.getX() - moveLength, gameObject.getY()))
        {
            gameObject.setPosition(gameObject.getX() - moveLength, gameObject.getY());
        }
    }

    public void moveRight(GameObject[][] newMap, GameObject gameObject)
    {
        movedDirection = gameObject.toString() + " moved Right";
        notifyUpdate(movedDirection);

        if (mapCalculator.isMoveAllowed(newMap, gameObject.getX() + moveLength, gameObject.getY()))
        {
            gameObject.setPosition(gameObject.getX() + moveLength, gameObject.getY());
        }
    }

    public void moveUp(GameObject[][] newMap, GameObject gameObject)
    {
        movedDirection = gameObject.toString() + " moved Up";
        notifyUpdate(movedDirection);

        if (mapCalculator.isMoveAllowed(newMap, gameObject.getX(), gameObject.getY() + moveLength))
        {
            gameObject.setPosition(gameObject.getX(), gameObject.getY() + moveLength);
        }
    }

    public void moveDown(GameObject[][] newMap, GameObject gameObject)
    {
        movedDirection = gameObject.toString() + " moved Down";
        notifyUpdate(movedDirection);

        if (mapCalculator.isMoveAllowed(newMap, gameObject.getX(), gameObject.getY() - moveLength))
        {
            gameObject.setPosition(gameObject.getX(), gameObject.getY() - moveLength);
        }
    }

    public void eliminate(LinkedList<GameObject> enemies, Array<GameObject> gameObjects)
    {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            mainPlayer.eliminate(enemies, gameObjects);
        }
    }

    public void addObserver(Observer channel)
    {
        this.channels.add(channel);
    }

    public void removeObserver(Observer channel)
    {
        this.channels.remove(channel);
    }

    public void notifyUpdate(String message)
    {
        this.message = message;
        for (Observer channel : this.channels)
        {
            channel.update(this.message);
        }
    }
}
