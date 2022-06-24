package at.compus02.swd.ss2022.game.playableChars;

import at.compus02.swd.ss2022.game.converter.MapCalculator;
import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.gameobjects.GoodKnight;

public class Enemy extends GoodKnight
{
    private static final Enemy Enemy = new Enemy();
    MapCalculator mapCalculator = new MapCalculator();
    MainPlayer mainPlayer = MainPlayer.getInstance();

    private Enemy()
    {
        System.out.println("erstellt main Enemy");
    }

    public static Enemy getInstance()
    {
        return Enemy;
    }

    public void runFromPlayer(GameObject[][] newMap)
    {
        float targetX = 130;
        float targetY = 130;

        if(mainPlayer.getX() > targetX)
        {
            Enemy.moveLeft(newMap);
            return;
        }
        if(mainPlayer.getY() > targetY)
        {
            Enemy.moveDown(newMap);
            return;
        }

        if(Enemy.getX() < targetX)
        {
            Enemy.moveRight(newMap);
        }
        if(Enemy.getX() > targetX)
        {
            Enemy.moveLeft(newMap);
        }
        if(Enemy.getY() < targetY)
        {
            Enemy.moveUp(newMap);
        }
        if(Enemy.getY() > targetY)
        {
            Enemy.moveDown(newMap);
        }
    }

    public void moveLeft(GameObject[][] newMap)
    {
        if(mapCalculator.isMoveAllowed(newMap, Enemy.getX()-1,Enemy.getY()))
        {
            Enemy.setPosition(Enemy.getX()-1, Enemy.getY());
        }
    }
    public void moveRight(GameObject[][] newMap)
    {
        if(mapCalculator.isMoveAllowed(newMap, Enemy.getX()+1,Enemy.getY()))
        {
            Enemy.setPosition(Enemy.getX()+1, Enemy.getY());
        }
    }
    public void moveUp(GameObject[][] newMap)
    {
        if(mapCalculator.isMoveAllowed(newMap, Enemy.getX(),Enemy.getY()+1))
        {
            Enemy.setPosition(Enemy.getX(), Enemy.getY()+1);
        }
    }
    public void moveDown(GameObject[][] newMap)
    {
        if(mapCalculator.isMoveAllowed(newMap, Enemy.getX(),Enemy.getY()-1))
        {
            Enemy.setPosition(Enemy.getX(), Enemy.getY()-1);
        }
    }
}
