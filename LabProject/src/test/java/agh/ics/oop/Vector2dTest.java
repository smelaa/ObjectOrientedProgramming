package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    void testToString() {
        assertEquals("(1, 2)", new Vector2d(1,2).toString());
    }

    @Test
    void precedes() {
        //given
        //when
        //then
        assertTrue(new Vector2d(1,2).precedes(new Vector2d(3,5)));
        assertFalse(new Vector2d(7,9).precedes(new Vector2d(6,8)));
        assertTrue(new Vector2d(2,2).precedes(new Vector2d(2,2)));
    }

    @Test
    void follows() {
        assertTrue(new Vector2d(8,9).follows(new Vector2d(3,5)));
        assertFalse(new Vector2d(1,3).follows(new Vector2d(6,8)));
        assertTrue(new Vector2d(2,2).follows(new Vector2d(2,2)));
    }

    @Test
    void add() {
        assertEquals(new Vector2d(4,7), new Vector2d(1,2).add(new Vector2d(3,5)));
    }

    @Test
    void subtract() {
        assertEquals(new Vector2d(-2,-3), new Vector2d(1,2).subtract(new Vector2d(3,5)));
    }

    @Test
    void upperRight() {
        assertEquals(new Vector2d(3,2), new Vector2d(1,2).upperRight(new Vector2d(3,1)));
        assertEquals(new Vector2d(5,2), new Vector2d(5,2).upperRight(new Vector2d(3,1)));
    }

    @Test
    void lowerLeft() {
        assertEquals(new Vector2d(1,1), new Vector2d(1,2).lowerLeft(new Vector2d(3,1)));
        assertEquals(new Vector2d(3,1), new Vector2d(5,2).lowerLeft(new Vector2d(3,1)));
    }

    @Test
    void opposite() {
        assertEquals(new Vector2d(-1,-2), new Vector2d(1,2).opposite());
        assertEquals(new Vector2d(7,-5), new Vector2d(-7,5).opposite());
    }

    @Test
    void equals() {
        assertTrue(new Vector2d(8,9).equals(new Vector2d(8,9)));
        assertFalse(new Vector2d(1,3).equals(new Vector2d(6,8)));
    }
}