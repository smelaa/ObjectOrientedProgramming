package agh.ics.oop;

import agh.ics.oop.gui.App;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable{
    public ArrayList<MoveDirection> directions;
    public IWorldMap map;
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
                System.out.println(map);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
