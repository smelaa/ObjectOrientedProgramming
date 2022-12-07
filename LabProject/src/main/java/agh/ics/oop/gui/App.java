package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App extends Application {
    private AbstractWorldMap map;
    @Override
    public void init() throws Exception{
        try {
            String[] args = getParameters().getRaw().toArray(new String[0]);
            ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
            map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        }
        catch (IllegalArgumentException exception){
            exception.printStackTrace();
        }
    }
    public void start(Stage primaryStage){
        int minX=map.findLowerLeftBound().x;
        int minY=map.findLowerLeftBound().y;
        int maxX=map.findUpperRightBound().x;
        int maxY=map.findUpperRightBound().y;
        int height=30;
        int width=30;
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);
        grid.getColumnConstraints().add(new ColumnConstraints(width));
        Label labelxy = new Label("y/x");
        grid.getRowConstraints().add(new RowConstraints(height));
        GridPane.setHalignment(labelxy, HPos.CENTER);
        grid.add(labelxy, 0, 0);

        for (int i = 1; i <= maxX - minX + 1; i++){
            Label label = new Label(Integer.toString(minX + i -1));
            grid.getColumnConstraints().add(new ColumnConstraints(width));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, i, 0);
        }
        for (int i =1 ; i <=  maxY - minY + 1; i++){
            Label label = new Label(Integer.toString(maxY-i+1));
            grid.getRowConstraints().add(new RowConstraints(height));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.add(label, 0,i);
        }
        for (int x=minX;x<=maxX;x++){
            for (int y=minY;y<=maxY;y++){
                Vector2d position = new Vector2d(x,y);
                Object object=  map.objectAt(position);
                if (object!=null){
                    Label label = new Label(object.toString());
                    grid.add(label, position.x - minX + 1, maxY - position.y + 1);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }
        }

        Scene scene = new Scene(grid, (maxX-minX+2)*width, (maxY-minY+2)*height);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
