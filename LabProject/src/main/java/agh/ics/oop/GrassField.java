package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;

public class GrassField implements IWorldMap{
    private final int amountOfGrasses;
    private final List<Animal> animalsOnField= new ArrayList<>();
    private final List<Grass> grassOnField= new ArrayList<>();

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
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())){
            animalsOnField.add(animal);
            return true;
        }
        return false;
    }


    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position)!=null;
    }

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
    public String toString() {
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        Vector2d lowerLeft= animalsOnField.get(0).getPosition();
        Vector2d upperRight = animalsOnField.get(0).getPosition();
        for (Animal animal: animalsOnField){
            upperRight=animal.getPosition().upperRight(upperRight);
            lowerLeft=animal.getPosition().lowerLeft(lowerLeft);
        }
        for (Grass grass: grassOnField){
            upperRight=grass.getPosition().upperRight(upperRight);
            lowerLeft=grass.getPosition().lowerLeft(lowerLeft);
        }
        return mapVisualizer.draw(lowerLeft,upperRight);
    }

}
