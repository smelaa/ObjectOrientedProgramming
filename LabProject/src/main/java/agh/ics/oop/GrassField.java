package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.lang.Math;

import static java.lang.Math.sqrt;

public class GrassField extends AbstractWorldMap{
    protected HashMap<Vector2d,Grass> grassOnField = new HashMap<>();
    protected MapBoundary mapBoundary=new MapBoundary();

    public GrassField(int amountOfGrasses){
        for (int  i=0; i<amountOfGrasses;i++){seedRandomGrass(amountOfGrasses);}
    }

    public void seedRandomGrass(int amountOfGrasses){
        List<Vector2d> freeSpots= new ArrayList<>();
        for (int i=0;i<sqrt(amountOfGrasses*10)+1; i++){
            for (int j=0;j<sqrt(amountOfGrasses*10)+1;j++){
                Vector2d currSpot=new Vector2d(i,j);
                if (!isOccupied(currSpot)){freeSpots.add(currSpot);}
            }
        }
        Random rand = new Random();
        int ix = rand.nextInt((int) freeSpots.size());
        Grass newGrass=new Grass(freeSpots.get(ix));
        grassOnField.put(freeSpots.get(ix),newGrass);
        mapBoundary.addObject(newGrass);
    }
    @Override
    public boolean place(Animal animal) throws IllegalArgumentException{
        if (super.place(animal)){
            mapBoundary.addObject(animal);
            return true;
        }
        throw new IllegalArgumentException("cannot place animal on"+animal.getPosition());
    }
    @Override
    public Object objectAt(Vector2d position) {
        Object mapObject = super.objectAt(position);
        if (mapObject == null) {
            return grassOnField.get(position);
        }
        return mapObject;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        Object obj = objectAt(position);
        return !(obj instanceof Animal);
    }

    @Override
    public Vector2d findLowerLeftBound() {
        return mapBoundary.getLowerLeft();
    }

    @Override
    public Vector2d findUpperRightBound() {
        return mapBoundary.getUpperRight();
    }

    @Override
    public String toString() {
        MapVisualizer mapVisualizer=new MapVisualizer(this);
        return mapVisualizer.draw(findLowerLeftBound(), findUpperRightBound());
    }


}
