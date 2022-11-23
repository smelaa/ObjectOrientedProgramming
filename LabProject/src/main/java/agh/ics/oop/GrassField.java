package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;

public class GrassField extends AbstractWorldMap{
    private final int amountOfGrasses;

    public GrassField(int amountOfGrasses){
        this.amountOfGrasses=amountOfGrasses;
        for (int  i=0; i<amountOfGrasses;i++){seedRandomGrass();}
    }

    public void seedRandomGrass(){
        Random rand = new Random();
        int x=rand.nextInt((int) Math.sqrt(amountOfGrasses*10)+1);
        int y=rand.nextInt((int) Math.sqrt(amountOfGrasses*10)+1);
        Vector2d newGrassPosition=new Vector2d(x,y);
        if (!isOccupied(newGrassPosition)){
            grassOnField.add(new Grass(newGrassPosition));
        }
    }

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
