package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{
    public ArrayList<MoveDirection> directions;
    public IWorldMap map;
    public Vector2d[] initialAnimalsPositions;
    private final List<Animal> animals= new ArrayList<>();
    public SimulationEngine(ArrayList<MoveDirection> directions, IWorldMap map, Vector2d[] initialAnimalsPositions){
        this.directions=directions;
        this.map=map;
        this.initialAnimalsPositions=initialAnimalsPositions;
        for (Vector2d currPosition: initialAnimalsPositions){
            Animal animal=new Animal(map, currPosition);
            if(map.place(animal)){
                animals.add(animal);
            }
        }
    }
    @Override
    public void run() {
        int numbersOfAnimals=animals.size();
        for (int i=0;i<directions.size();i++){
            animals.get(i%numbersOfAnimals).move(directions.get(i));
        }
    }
}
