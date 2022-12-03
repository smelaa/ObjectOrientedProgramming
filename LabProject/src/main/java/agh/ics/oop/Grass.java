package agh.ics.oop;

public class Grass implements IMapElement{
    private final Vector2d position;
    public Grass(Vector2d initialPosition){this.position=initialPosition;}

    @Override
    public String toString() {return "*";}
    @Override
    public Vector2d getPosition() {
        return position;
    }
    @Override
    public MapElementInstance getInstance(){
        return MapElementInstance.GRASS;
    }

    @Override
    public void addObserver(IPositionChangeObserver observer){}

    @Override
    public String getImageName() {
        return "src/main/resources/grass.png";
    }

}
