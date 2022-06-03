package at.compus02.swd.ss2022.game.observer;

import at.compus02.swd.ss2022.game.playableChars.MainPlayer;

public class PlayerChannel implements Observer
{
    public PlayerChannel()
    {
    }

    @Override
    public void update(Object o)
    {
        System.out.println((String) o);
    }
}
