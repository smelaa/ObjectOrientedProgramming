package agh.ics.oop;

public interface IMapElement {
    String toString();
    Vector2d getPosition();

    MapElementInstance getInstance();

    void addObserver(IPositionChangeObserver observer);
}
