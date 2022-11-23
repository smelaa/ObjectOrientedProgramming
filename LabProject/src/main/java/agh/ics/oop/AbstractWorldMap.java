package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{
    protected List<Animal> animalsOnField= new ArrayList<>();
    protected List<Grass> grassOnField= new ArrayList<>();

    @Override
    public boolean isOccupied(Vector2d position) {return (objectAt(position)!=null);}

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal currAnimal : animalsOnField){
            if(currAnimal.getPosition().equals(position)){
                return currAnimal;
            }
        }
        for (Grass currGrass : grassOnField){
            if(currGrass.getPosition().equals(position)){
                return currGrass;
            }
        }
        return null;
    }
    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())){
            animalsOnField.add(animal);
            return true;
        }
        return false;
    }
    public abstract Vector2d findLowerLeftBound();
    public abstract Vector2d findUpperRightBound();

    @Override
    public String toString() {
        MapVisualizer mapVisualizer=new MapVisualizer(this);
        return mapVisualizer.draw(findLowerLeftBound(), findUpperRightBound());
    }

    @Override
    public void moveOnMap (Vector2d position){}
}
