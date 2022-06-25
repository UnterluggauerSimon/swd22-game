package at.compus02.swd.ss2022.game.playableChars;

import at.compus02.swd.ss2022.game.converter.MapCalculator;
import at.compus02.swd.ss2022.game.gameobjects.BadKnight;
import at.compus02.swd.ss2022.game.gameobjects.GameObject;

public class MainEnemy extends BadKnight
{
    private static final MainEnemy mainEnemy = new MainEnemy();
    MapCalculator mapCalculator = new MapCalculator();
    MainPlayer mainPlayer = MainPlayer.getInstance();

    private MainEnemy()
    {
        System.out.println("erstellt main Enemy");
    }

    public static MainEnemy getInstance()
    {
        return mainEnemy;
    }

    public void followPlayer(GameObject[][] newMap)
    {
        float targetX = mainPlayer.getX();
        float targetY = mainPlayer.getY();

        if (mainEnemy.getX() > targetX)
        {
            mainEnemy.moveLeft(newMap);
        }
        if (mainEnemy.getX() < targetX)
        {
            mainEnemy.moveRight(newMap);
        }
        if (mainEnemy.getY() > targetY)
        {
            mainEnemy.moveDown(newMap);
        }
        if (mainEnemy.getY() < targetY)
        {
            mainEnemy.moveUp(newMap);
        }
    }

    public void runFromPlayer(GameObject[][] newMap)
    {
        float targetX = 130;
        float targetY = 130;

        if (mainPlayer.getX() > targetX)
        {
            mainEnemy.moveLeft(newMap);
            return;
        }
        if (mainPlayer.getY() > targetY)
        {
            mainEnemy.moveDown(newMap);
            return;
        }

        if (mainEnemy.getX() < targetX)
        {
            mainEnemy.moveRight(newMap);
        }
        if (mainEnemy.getX() > targetX)
        {
            mainEnemy.moveLeft(newMap);
        }
        if (mainEnemy.getY() < targetY)
        {
            mainEnemy.moveUp(newMap);
        }
        if (mainEnemy.getY() > targetY)
        {
            mainEnemy.moveDown(newMap);
        }
    }

    public void moveLeft(GameObject[][] newMap)
    {
        if (mapCalculator.isMoveAllowed(newMap, mainEnemy.getX() - 1, mainEnemy.getY()))
        {
            mainEnemy.setPosition(mainEnemy.getX() - 1, mainEnemy.getY());
        }
    }

    public void moveRight(GameObject[][] newMap)
    {
        if (mapCalculator.isMoveAllowed(newMap, mainEnemy.getX() + 1, mainEnemy.getY()))
        {
            mainEnemy.setPosition(mainEnemy.getX() + 1, mainEnemy.getY());
        }
    }

    public void moveUp(GameObject[][] newMap)
    {
        if (mapCalculator.isMoveAllowed(newMap, mainEnemy.getX(), mainEnemy.getY() + 1))
        {
            mainEnemy.setPosition(mainEnemy.getX(), mainEnemy.getY() + 1);
        }
    }

    public void moveDown(GameObject[][] newMap)
    {
        if (mapCalculator.isMoveAllowed(newMap, mainEnemy.getX(), mainEnemy.getY() - 1))
        {
            mainEnemy.setPosition(mainEnemy.getX(), mainEnemy.getY() - 1);
        }
    }
}
