package at.compus02.swd.ss2022.game.observer;

public interface Subject {
    void addObserver(Observer channel);
    void removeObserver(Observer channel);
    void notifyUpdate(String news);
}
