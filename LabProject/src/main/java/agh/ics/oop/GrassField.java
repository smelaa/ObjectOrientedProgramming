package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;

public class GrassField extends AbstractWorldMap{
    protected List<Grass> grassOnField= new ArrayList<>();

    public GrassField(int amountOfGrasses){
        for (int  i=0; i<amountOfGrasses;i++){seedRandomGrass();}
    }

    public void seedRandomGrass(){
        List<Vector2d> freeSpots= new ArrayList<>();
        for (int i=0;i<grassOnField.size()*10+1; i++){
            for (int j=0;j<grassOnField.size()*10+1;j++){
                Vector2d currSpot=new Vector2d(i,j);
                if (!isOccupied(currSpot)){freeSpots.add(currSpot);}
            }
        }
        Random rand = new Random();
        int ix = rand.nextInt((int) Math.sqrt(freeSpots.size()));
        grassOnField.add(new Grass(freeSpots.get(ix)));
    }
    @Override
    public Object objectAt(Vector2d position) {
        Object mapObject = super.objectAt(position);
        if (mapObject == null) {
            for (Grass currGrass : grassOnField) {
                if (currGrass.getPosition().equals(position)) {
                    return currGrass;
                }
            }
        }
        return mapObject;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        Object obj = objectAt(position);
        return obj==null || !(obj instanceof Animal);
    }

    @Override
    public Vector2d findLowerLeftBound() {
        Vector2d upperRight = animalsOnField.get(1).getPosition();
        for (Animal animal: animalsOnField){
            upperRight=animal.getPosition().upperRight(upperRight);
        }
        for (Grass grass: grassOnField){
            upperRight=grass.getPosition().upperRight(upperRight);
        }
        return upperRight;
    }

    @Override
    public Vector2d findUpperRightBound() {
        Vector2d lowerLeft= animalsOnField.get(1).getPosition();
        for (Animal animal: animalsOnField){
            lowerLeft=animal.getPosition().lowerLeft(lowerLeft);
        }
        for (Grass grass: grassOnField){
            lowerLeft=grass.getPosition().lowerLeft(lowerLeft);
        }
        return lowerLeft;
    }

    @Override
    public void moveOnMap(Vector2d position){
        for (Grass grass: grassOnField){
            if (grass.getPosition()==position){
                grassOnField.remove(grass);
                seedRandomGrass();
            }
        }
    }
}
