package at.compus02.swd.ss2022.game.playableChars;

import at.compus02.swd.ss2022.game.gameobjects.Knight;
import at.compus02.swd.ss2022.game.observer.Observer;
import java.util.ArrayList;
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

    public void eliminate(int key)
    {

    }

}
