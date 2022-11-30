package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.lang.Math;

public class GrassField extends AbstractWorldMap{
    protected HashMap<Vector2d,Grass> grassOnField = new HashMap<>();

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
        grassOnField.put(freeSpots.get(ix),new Grass(freeSpots.get(ix)));
    }
    @Override
    public Object objectAt(Vector2d position) {
        Object mapObject = super.objectAt(position);
        if (mapObject == null) {
            for (Vector2d currPosition : grassOnField.keySet()) {
                if (currPosition.equals(position)) {
                    return grassOnField.get(currPosition);
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
        for (Vector2d currPosition : animalsOnField.keySet()){
            upperRight=currPosition.upperRight(upperRight);
        }
        for (Vector2d currPosition : grassOnField.keySet()){
            upperRight=currPosition.upperRight(upperRight);
        }
        return upperRight;
    }

    @Override
    public Vector2d findUpperRightBound() {
        Vector2d lowerLeft= animalsOnField.get(1).getPosition();
        for (Vector2d currPosition : animalsOnField.keySet()){
            lowerLeft=currPosition.lowerLeft(lowerLeft);
        }
        for (Vector2d currPosition : grassOnField.keySet()){
            lowerLeft=currPosition.lowerLeft(lowerLeft);
        }
        return lowerLeft;
    }

}
