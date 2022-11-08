package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {

    @Test
    void simpleTest() {
        String[] args= {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertTrue(map.isOccupied(new Vector2d(2, 0)));
        assertTrue(map.isOccupied(new Vector2d(3, 4)));
    }

    @Test
    void placingOnOccupiedSpotTest() {
        String[] args= {"f", "l","b","l","f","f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(4, 4);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,2) };
        IEngine engine = new SimulationEngine(directions, map, positions);

        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        engine.run();
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                if (i==3&&j==1){assertTrue(map.isOccupied(new Vector2d(i, j)));}
                else{assertFalse(map.isOccupied(new Vector2d(i, j)));}
            }
        }
    }
    @Test
    void steppingOutofMapTest() {
        String[] args= {"f","f","f"};
        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(4, 4);
        Vector2d[] positions = { new Vector2d(2,2)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertTrue(map.isOccupied(new Vector2d(2, 3)));
    }
}