package at.compus02.swd.ss2022.game.observer;

import at.compus02.swd.ss2022.game.playableChars.MainPlayer;

public class PlayerChannel implements Observer
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
}
