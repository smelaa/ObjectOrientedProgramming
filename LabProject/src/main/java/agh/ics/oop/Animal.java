package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;

public class Animal implements IMapElement{
    private MapDirection direction;
    private IWorldMap map;
    private Vector2d position;
    private final ArrayList<IPositionChangeObserver> observers = new ArrayList();
    public Animal(IWorldMap map) {this(map, new Vector2d(2,2));}

    public Animal(IWorldMap map, Vector2d initialPosition)
    {
        this.map = map;
        this.position = initialPosition;
        this.direction = MapDirection.NORTH;
    }

    @Override
    public String toString() {
        return switch (this.direction){
            case EAST -> "E";
            case WEST -> "W";
            case NORTH -> "N";
            case SOUTH -> "S";
        };

    }
    @Override
    public Vector2d getPosition() {
        return position;
    }
    @Override
    public MapElementInstance getInstance(){
        return MapElementInstance.ANIMAL;
    }
    public MapDirection getDirection() {
        return direction;
    }

    public boolean isAt(Vector2d position){
        return (this.position.equals(position));
    }

    @Override
    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    private void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for(IPositionChangeObserver observer:observers){
            observer.positionChanged(oldPosition,newPosition);
        }
    }
    public void move(MoveDirection direction){
        switch(direction) {
            case RIGHT -> this.direction = this.direction.next();
            case LEFT -> this.direction = this.direction.previous();
            default -> {
                Vector2d directionVector = this.direction.toUnitVector();
                if (direction == MoveDirection.BACKWARD) {
                    directionVector = directionVector.opposite();
                }
                Vector2d newLocation = this.position.add(directionVector);
                if (this.map.canMoveTo(newLocation)) {
                    positionChanged(this.position, newLocation);
                    this.position = newLocation;
                }}}
    }

    public void setPosition(Vector2d newPosition){position=newPosition;}

}
