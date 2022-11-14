package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    private final int width;
    private final int height;

    private final List<Animal> animalsOnMap= new ArrayList<>();

    public RectangularMap(int width, int height){
        this.width=width-1;
        this.height=height-1;
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer=new MapVisualizer(this);
        return mapVisualizer.draw(new Vector2d(0,0), new Vector2d(this.width,this.height));
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(new Vector2d(0,0)) && position.precedes(new Vector2d(width, height)) && !isOccupied(position);
    }
    @Override
    public boolean isOccupied(Vector2d position) {return (objectAt(position)!=null);}
    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())){
            animalsOnMap.add(animal);
            return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal currAnimal : animalsOnMap){
            if(currAnimal.getPosition().equals(position)){
                return currAnimal;
            }
        }
        return null;
    }
}
