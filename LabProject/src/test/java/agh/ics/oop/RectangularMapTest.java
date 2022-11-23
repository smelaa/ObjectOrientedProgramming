package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    @Test
    void IWorldMapTest(){
        IWorldMap map = new RectangularMap(10,10);
        Vector2d pos=new Vector2d(1,1);
        Animal animal = new Animal(map,pos);
        Animal animal2 = new Animal(map,pos);
        assertTrue(map.place(animal));
        assertEquals(map.objectAt(pos), animal);
        assertTrue(map.isOccupied(pos));
        assertFalse(map.canMoveTo(pos));
        assertFalse(map.place(animal2));
        assertFalse(map.canMoveTo(new Vector2d(3,11)));
    }
}