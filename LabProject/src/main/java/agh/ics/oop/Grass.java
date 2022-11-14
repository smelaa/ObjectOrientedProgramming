package agh.ics.oop;

public class Grass {
    private final Vector2d position;

    public Grass(Vector2d initialPosition){this.position=initialPosition;}

    public Vector2d getPosition(){return position;}

    @Override
    public String toString() {return "*";}
}
