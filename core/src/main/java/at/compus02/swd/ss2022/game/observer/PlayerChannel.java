package at.compus02.swd.ss2022.game.observer;

import at.compus02.swd.ss2022.game.playableChars.MainPlayer;

public class PlayerChannel implements Channel
{
    private String news;
    private float oldX;
    private float oldY;
    private MainPlayer mainPlayer;

    public PlayerChannel()
    {
        //mainPlayer = MainPlayer.getInstance();
        oldX = 0;//mainPlayer.getX();
        oldY = 0;//mainPlayer.getY();
    }

    @Override
    public void update(Object o)
    {
        System.out.println((String) o);
    }

    public void observePlayer()
    {

        mainPlayer = MainPlayer.getInstance();

        if(mainPlayer.getX() > oldX)
            update("Player moved right");
        if(mainPlayer.getX() < oldX)
            update("Player moved left");
        if(mainPlayer.getY() > oldY)
            update("Player moved up");
        if(mainPlayer.getY() < oldY)
            update("Player moved down");

        oldX = mainPlayer.getX();
        oldY = mainPlayer.getY();
    }
}
