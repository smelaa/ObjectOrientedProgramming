package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    //protected List<Animal> animalsOnField= new ArrayList<>();
    protected HashMap<Vector2d,Animal> animalsOnField = new HashMap<>();
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        animalsOnField.put(newPosition, animalsOnField.get(oldPosition));
        animalsOnField.remove(oldPosition);
    }
    @Override
    public boolean isOccupied(Vector2d position) {return (objectAt(position)!=null);}

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())){
            animalsOnField.put(animal.getPosition(),animal);
            return true;
        }
        return false;
    }
    protected abstract Vector2d findLowerLeftBound();
    protected abstract Vector2d findUpperRightBound();

    @Override
    public String toString() {
        MapVisualizer mapVisualizer=new MapVisualizer(this);
        return mapVisualizer.draw(findLowerLeftBound(), findUpperRightBound());
    }
    @Override
    public Object objectAt(Vector2d position) {
        for (Vector2d currPossition : animalsOnField.keySet()){
            if(currPossition.equals(position)){
                return animalsOnField.get(currPossition);
            }
        }
        return null;
    }
}
