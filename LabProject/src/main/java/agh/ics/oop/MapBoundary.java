package agh.ics.oop;

import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    private TreeSet<MapBoundaryElement> xSortedObjects=new TreeSet<>(new Comparator<MapBoundaryElement>(){
        @Override
        public int compare (MapBoundaryElement e1, MapBoundaryElement e2){
            if (e1.position.x-e2.position.x!=0){return e1.position.x-e2.position.x;}
            if (e1.position.y-e2.position.y!=0){return e1.position.y-e2.position.y;}
            if (e1.instance == MapElementInstance.ANIMAL){return 1;}
            return -1;
        }
    });
    private TreeSet<MapBoundaryElement> ySortedObjects = new TreeSet<>(new Comparator<MapBoundaryElement>(){
        @Override
        public int compare (MapBoundaryElement e1, MapBoundaryElement e2){
            if (e1.position.y-e2.position.y!=0){return e1.position.y-e2.position.y;}
            if (e1.position.x-e2.position.x!=0){return e1.position.x-e2.position.x;}
            if (e1.instance == MapElementInstance.ANIMAL){return 1;}
            return -1;
        }
    });

    @Override
    public void positionChanged( Vector2d oldPosition, Vector2d newPosition) {
        MapBoundaryElement toRemove = new MapBoundaryElement(oldPosition, MapElementInstance.ANIMAL);
        xSortedObjects.remove(toRemove);
        ySortedObjects.remove(toRemove);
        MapBoundaryElement toAdd = new MapBoundaryElement(newPosition, MapElementInstance.ANIMAL);
        xSortedObjects.add(toAdd);
        ySortedObjects.add(toAdd);
    }

    public void addObject(IMapElement object) {
        MapBoundaryElement toAdd = new MapBoundaryElement(object.getPosition(), object.getInstance());
        xSortedObjects.add(toAdd);
        ySortedObjects.add(toAdd);
        object.addObserver(this);
    }

    public Vector2d getLowerLeft(){return new Vector2d(xSortedObjects.first().position.x,ySortedObjects.first().position.y);}

    public Vector2d getUpperRight(){return new Vector2d(xSortedObjects.last().position.x,ySortedObjects.last().position.y);}

}
