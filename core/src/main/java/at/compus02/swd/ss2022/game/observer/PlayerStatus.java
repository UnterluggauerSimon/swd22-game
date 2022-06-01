package at.compus02.swd.ss2022.game.observer;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatus implements Subject
{
    private String news;
    private List<Observer> channels = new ArrayList<>();

    public void addObserver(Observer channel) {
        this.channels.add(channel);
    }

    public void removeObserver(Observer channel) {
        this.channels.remove(channel);
    }

    public void notifyUpdate(String news) {
        this.news = news;
        for (Observer channel : this.channels) {
            channel.update(this.news);
        }
    }
}
