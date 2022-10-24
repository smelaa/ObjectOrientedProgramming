package agh.ics.oop;

public class Animal {
    private MapDirection direction;
    private Vector2d location;

    public Animal() {
        this.direction = MapDirection.NORTH;
        this.location = new Vector2d(2,2);
    }

    @Override
    public String toString() {
        return  direction + " " + location;
    }

    public Vector2d getLocation() {
        return location;
    }

    public MapDirection getDirection() {
        return direction;
    }

    public boolean isAt(Vector2d position){
        return (this.location.equals(position));
    }

    public void move(MoveDirection direction){
        switch(direction){
            case RIGHT -> this.direction=this.direction.next();
            case LEFT -> this.direction=this.direction.previous();
            default -> {
                Vector2d directionVector = this.direction.toUnitVector();
                if (direction == MoveDirection.BACKWARD) {
                    directionVector = directionVector.opposite();
                }
                Vector2d newLocation = this.location.add(directionVector);
                if (newLocation.precedes(new Vector2d(4, 4)) && newLocation.follows(new Vector2d(0, 0))) {
                    this.location = newLocation;
                }
            }
        }
    }



}
