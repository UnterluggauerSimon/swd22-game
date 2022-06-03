package at.compus02.swd.ss2022.game.playableChars;

import at.compus02.swd.ss2022.game.factories.GameObjectType;
import at.compus02.swd.ss2022.game.factories.PlayerFactory;
import at.compus02.swd.ss2022.game.gameobjects.GameObject;
import at.compus02.swd.ss2022.game.gameobjects.Knight;
import at.compus02.swd.ss2022.game.observer.Observer;
import at.compus02.swd.ss2022.game.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class MainPlayer extends Knight implements Subject
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

    public void moveLeft(int key)
    {
        movedDirection = "Player moved left";
        mainPlayer.setPosition(mainPlayer.getX()-4, mainPlayer.getY());
        if(this.previousDirection != key){
            notifyUpdate(movedDirection);
        }
        this.previousDirection = key;
    }
    public void moveRight(int key)
    {
        movedDirection = "Player moved right";
        mainPlayer.setPosition(mainPlayer.getX()+4, mainPlayer.getY());
        if(this.previousDirection != key){
            notifyUpdate(movedDirection);
        }
        this.previousDirection = key;
    }
    public void moveUp(int key)
    {
        movedDirection = "Player moved up";
        mainPlayer.setPosition(mainPlayer.getX(), mainPlayer.getY()+4);
        if(this.previousDirection != key){
            notifyUpdate(movedDirection);
        }
        this.previousDirection = key;
    }
    public void moveDown(int key)
    {
        movedDirection = "Player moved down";
        mainPlayer.setPosition(mainPlayer.getX(), mainPlayer.getY()-4);
        if(this.previousDirection != key){
            notifyUpdate(movedDirection);
        }
        this.previousDirection = key;
    }

    public void addObserver(Observer channel) {
        this.channels.add(channel);
    }

    public void removeObserver(Observer channel) {
        this.channels.remove(channel);
    }

    public void notifyUpdate(String message) {
        this.message = message;
        for (Observer channel : this.channels) {
            channel.update(this.message);
        }
    }
}
