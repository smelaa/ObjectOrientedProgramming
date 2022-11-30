package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{
    private final int width;
    private final int height;

    private final List<Animal> animalsOnMap= new ArrayList<>();

    public RectangularMap(int width, int height){
        this.width=width-1;
        this.height=height-1;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0,0)) && position.precedes(new Vector2d(width, height)) && !isOccupied(position);
    }

    @Override
    protected Vector2d findLowerLeftBound() {
        return new Vector2d(0,0);
    }

    @Override
    protected Vector2d findUpperRightBound() {
        return new Vector2d(width, height);
    }

}
