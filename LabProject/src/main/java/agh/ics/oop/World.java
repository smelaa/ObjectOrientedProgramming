package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.ArrayList;

public class World {

    public static void main(String[] args) {
        try {
            ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
            IWorldMap map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
            System.out.println(map);
        }
        catch (IllegalArgumentException exception){
            exception.printStackTrace();
        }
        //Application.launch(App.class, args);
    }
}
