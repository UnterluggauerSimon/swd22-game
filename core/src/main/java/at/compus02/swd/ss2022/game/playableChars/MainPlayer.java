package at.compus02.swd.ss2022.game.playableChars;

import at.compus02.swd.ss2022.game.factories.GameObjectType;
import at.compus02.swd.ss2022.game.factories.PlayerFactory;
import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.gameobjects.Knight;

public class MainPlayer extends Knight
{
    private static final MainPlayer mainPlayer = new MainPlayer();

    private MainPlayer()
    {
        System.out.println("erstellt main player");
    }

    public static MainPlayer getInstance()
    {
        return mainPlayer;
    }

    public void moveLeft()
    {
        mainPlayer.setPosition(mainPlayer.getX()-4, mainPlayer.getY());
    }
    public void moveRight()
    {
        mainPlayer.setPosition(mainPlayer.getX()+4, mainPlayer.getY());
    }
    public void moveUp()
    {
        mainPlayer.setPosition(mainPlayer.getX(), mainPlayer.getY()+4);
    }
    public void moveDown()
    {
        mainPlayer.setPosition(mainPlayer.getX(), mainPlayer.getY()-4);
    }
}
