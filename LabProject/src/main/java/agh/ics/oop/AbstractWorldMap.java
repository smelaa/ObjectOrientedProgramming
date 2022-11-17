package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{
    protected List<Animal> animalsOnField= new ArrayList<>();

    @Override
    public boolean isOccupied(Vector2d position) {return (objectAt(position)!=null);}

    public void moveOnMap (Vector2d position){}

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())){
            animalsOnField.add(animal);
            moveOnMap(animal.getPosition());
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
        for (Animal currAnimal : animalsOnField){
            if(currAnimal.getPosition().equals(position)){
                return currAnimal;
            }
        }
        return null;
    }
}
