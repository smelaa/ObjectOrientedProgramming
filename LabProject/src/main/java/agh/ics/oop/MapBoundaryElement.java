package agh.ics.oop;

public class MapBoundaryElement {
    public final Vector2d position;
    public final MapElementInstance instance;

    public MapBoundaryElement(Vector2d position,MapElementInstance instance) {
        this.instance = instance;
        this.position = position;
    }
}
