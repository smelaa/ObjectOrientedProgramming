package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    public MoveDirection[] directions;
    public AbstractWorldMap map;
    public Vector2d[] initialAnimalsPositions;
    private final List<Animal> animals= new ArrayList<>();
    public SimulationEngine(MoveDirection[] directions, AbstractWorldMap map, Vector2d[] initialAnimalsPositions){
        this.directions=directions;
        this.map=map;
        this.initialAnimalsPositions=initialAnimalsPositions;
        for (Vector2d currPosition: initialAnimalsPositions){
            Animal animal=new Animal(map, currPosition);
            if(map.place(animal)){
                animals.add(animal);
                animal.addObserver(map);
            }
        }
    }
    @Override
    public void run() {
        int numbersOfAnimals=animals.size();
        for (int i=0;i<directions.length;i++){
            animals.get(i%numbersOfAnimals).move(directions[i]);
        }
    }
}
