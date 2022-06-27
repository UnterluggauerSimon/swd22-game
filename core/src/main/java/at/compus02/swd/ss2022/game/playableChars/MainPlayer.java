package at.compus02.swd.ss2022.game.playableChars;

import at.compus02.swd.ss2022.game.factories.DecorationFactory;
import at.compus02.swd.ss2022.game.factories.GameObjectType;
import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.gameobjects.Knight;
import at.compus02.swd.ss2022.game.observer.Observer;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainPlayer extends Knight
{
    private static final MainPlayer mainPlayer = new MainPlayer();
    private String movedDirection;

    private String message;
    private int previousDirection;
    private List<Observer> channels = new ArrayList<>();

    private MainPlayer()
    {
        System.out.println("erstellt main player");
    }

    public static MainPlayer getInstance()
    {
        return mainPlayer;
    }

    public void eliminate(LinkedList<GameObject> enemies, Array<GameObject> gameObjects, int range)
    {
        int posleft = (int) mainPlayer.getX() - range;
        int posright = (int) mainPlayer.getX() + range;
        int posup = (int) mainPlayer.getY() - range;
        int posdown = (int) mainPlayer.getY() + range;

        for (GameObject enemy : enemies)
        {
            if ((enemy.getX() <= posright && enemy.getX() >= posleft) && (enemy.getY() <= posdown && enemy.getY() >= posup))
            {
                int enemyX = (int) enemy.getX();
                int enemyY = (int) enemy.getY();

                Enemy currentEnemy = (Enemy) enemy;
                int newLife = currentEnemy.getLife() - 1;
                if (newLife == 0)
                {
                    DecorationFactory df = new DecorationFactory();
                    gameObjects.add(df.createSingleGameObject(GameObjectType.Stone, enemyX, enemyY));
                    enemies.remove(currentEnemy);
                }

                currentEnemy.setLife(newLife);
            }
        }
    }

}
