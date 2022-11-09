package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void move() {
        Animal mufasa= new Animal();
        assertEquals("Północ (2, 2)",mufasa.toString());
        MoveDirection[] moves=new MoveDirection[]{MoveDirection.FORWARD,MoveDirection.RIGHT,MoveDirection.BACKWARD,MoveDirection.BACKWARD,MoveDirection.BACKWARD,MoveDirection.LEFT,MoveDirection.LEFT,MoveDirection.FORWARD,MoveDirection.BACKWARD,MoveDirection.LEFT,MoveDirection.BACKWARD,MoveDirection.BACKWARD};
        String[] correct={"Północ (2, 3)","Wschód (2, 3)","Wschód (1, 3)","Wschód (0, 3)","Wschód (0, 3)","Północ (0, 3)", "Zachód (0, 3)", "Zachód (0, 3)", "Zachód (1, 3)", "Południe (1, 3)","Południe (1, 4)", "Południe (1, 4)"};
        for (int i=0;i<correct.length;i++){
            mufasa.move(moves[i]);
            assertEquals(correct[i],mufasa.toString());
        }
    }
}