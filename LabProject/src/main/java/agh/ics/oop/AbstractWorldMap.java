package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{
    protected List<Animal> animalsOnField= new ArrayList<>();
    protected List<Grass> grassOnField= new ArrayList<>();
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())){
            animalsOnField.add(animal);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {return (objectAt(position)!=null);}

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

    public void findBounds(){
        for (Animal animal: animalsOnField){
            upperRight=animal.getPosition().upperRight(upperRight);
            lowerLeft=animal.getPosition().lowerLeft(lowerLeft);
        }
        for (Grass grass: grassOnField){
            upperRight=grass.getPosition().upperRight(upperRight);
            lowerLeft=grass.getPosition().lowerLeft(lowerLeft);
        }
    }

    public String toString() {
        MapVisualizer mapVisualizer=new MapVisualizer(this);
        findBounds();
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
