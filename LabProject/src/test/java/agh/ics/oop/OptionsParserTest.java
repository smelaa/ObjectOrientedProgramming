package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
        String[] moves_s=new String[]{"forward","ghf","r","b","b","left","l","f","backword","xyz", "l","b","b","b"};
        MoveDirection[] moves=OptionsParser.parse(moves_s);
        MoveDirection[] correct_moves=new MoveDirection[]{MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.BACKWARD,MoveDirection.BACKWARD,MoveDirection.LEFT,MoveDirection.LEFT,MoveDirection.FORWARD,MoveDirection.BACKWARD,MoveDirection.LEFT,MoveDirection.BACKWARD,MoveDirection.BACKWARD,MoveDirection.BACKWARD};
        assertArrayEquals(correct_moves,moves);
    }
}