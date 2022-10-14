package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void next() {
        MapDirection n = MapDirection.NORTH;
        MapDirection e = MapDirection.EAST;
        MapDirection s = MapDirection.SOUTH;
        MapDirection w = MapDirection.WEST;
        assertEquals(n.next(), e);
        assertEquals(e.next(), s);
        assertEquals(s.next(), w);
        assertEquals(w.next(), n);
    }

    @Test
    void previous() {
        MapDirection north = MapDirection.NORTH;
        MapDirection east = MapDirection.EAST;
        MapDirection south = MapDirection.SOUTH;
        MapDirection west = MapDirection.WEST;
        assertEquals(north.previous(), west);
        assertEquals(east.previous(), north);
        assertEquals(south.previous(), east);
        assertEquals(west.previous(), south);
    }

}