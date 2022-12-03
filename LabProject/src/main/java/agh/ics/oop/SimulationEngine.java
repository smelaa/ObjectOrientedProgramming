package agh.ics.oop;

import agh.ics.oop.gui.App;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable{
    private ArrayList<MoveDirection> directions;
    private IWorldMap map;
    private int moveDelay=0;

    private final List<Animal> animals= new ArrayList<>();
    public SimulationEngine(ArrayList<MoveDirection> directions, IWorldMap map, Vector2d[] initialAnimalsPositions){
        this.directions=directions;
        this.map=map;
        for (Vector2d currPosition: initialAnimalsPositions){
            Animal animal=new Animal(map, currPosition);
            if(map.place(animal)){
                animals.add(animal);
            }
        }
    }
    public SimulationEngine(ArrayList<MoveDirection> directions, IWorldMap map, Vector2d[] initialAnimalsPositions, App gui){
        this(directions,map,initialAnimalsPositions);
        for (Animal animal: animals){
            animal.addObserver(gui);
        }
    }

    public SimulationEngine(IWorldMap map, Vector2d[] initialAnimalsPositions, App gui){
        this.map=map;
        for (Vector2d currPosition: initialAnimalsPositions){
            Animal animal=new Animal(map, currPosition);
            if(map.place(animal)){
                animals.add(animal);
                animal.addObserver(gui);
            }
        }
    }

    public void setDirections(ArrayList<MoveDirection> directions) {
        this.directions = directions;
    }

    public void setMoveDelay(int moveDelay) {
        this.moveDelay = moveDelay;
    }

    @Override
    public void run() {
        int numbersOfAnimals=animals.size();
        for (int i=0;i<directions.size();i++){
            animals.get(i%numbersOfAnimals).move(directions.get(i));
            try {
                Thread.sleep(moveDelay);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
