package agh.ics.oop;

public class Animal {
    private static final Vector2d LOWER_BOUND = new Vector2d(0,0);
    private static final Vector2d UPPER_BOUND = new Vector2d(4,4);
    private MapDirection direction;
    private IWorldMap map;
    private Vector2d position;
    public Animal(IWorldMap map)
    {
        this.map = map;
        this.direction = MapDirection.NORTH;
    }

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

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getDirection() {
        return direction;
    }

    public boolean isAt(Vector2d position){
        return (this.position.equals(position));
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
                if (this.map.canMoveTo(newLocation)&&!this.map.isOccupied(newLocation)) {
                    this.position = newLocation;
                }}}
    }

}
