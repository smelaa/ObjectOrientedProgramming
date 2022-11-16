package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    public void allCorrectLinesTest(){
        //given
        String[] moves={"forward","r","b","b","left","l","f","backword", "l","b","b","left", "right"};
        //when
        MoveDirection f = MoveDirection.FORWARD;
        MoveDirection b = MoveDirection.BACKWARD;
        MoveDirection l = MoveDirection.LEFT;
        MoveDirection r = MoveDirection.RIGHT;
        MoveDirection[] correct={f,r,b,b,l,l,f,b,l,b,b,l,r};
        //tests
        MoveDirection[] ans=OptionsParser.parse(moves);
        assertArrayEquals(correct,ans);
    }
    @Test
    void correctAndIncorrectLinesTest() {
        //given
        String[] moves={"forward","hjbgaw","r","b","b","left","l","f","xyz","backword", "l","b","bbb","left", "right"};
        //when
        MoveDirection f = MoveDirection.FORWARD;
        MoveDirection b = MoveDirection.BACKWARD;
        MoveDirection l = MoveDirection.LEFT;
        MoveDirection r = MoveDirection.RIGHT;
        MoveDirection[] correct={f,r,b,b,l,l,f,b,l,b,l,r};
        //tests
        MoveDirection[] ans=OptionsParser.parse(moves);
        assertArrayEquals(correct,ans);
    }
}