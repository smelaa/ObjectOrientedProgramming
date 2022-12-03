package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App extends Application implements IPositionChangeObserver{
    private AbstractWorldMap map;
    private GridPane grid = new GridPane();
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private int height=50;
    private int width=50;
    @Override
    public void init() throws Exception{
        try {
            String[] args = getParameters().getRaw().toArray(new String[0]);
            ArrayList<MoveDirection> directions = new OptionsParser().parse(args);
            map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            SimulationEngine engine = new SimulationEngine(directions, map, positions, this);
            engine.setMoveDelay(300);
            Thread thread = new Thread(engine);
            thread.start();
        }
        catch (IllegalArgumentException exception){
            exception.printStackTrace();
        }
    }
    public void updateBounds(){
        minX=map.findLowerLeftBound().x;
        minY=map.findLowerLeftBound().y;
        maxX=map.findUpperRightBound().x;
        maxY=map.findUpperRightBound().y;
    }

    public void addLabelXY(){
        Label labelxy = new Label("y/x");
        GridPane.setHalignment(labelxy, HPos.CENTER);
        grid.getColumnConstraints().add(new ColumnConstraints(width));
        grid.getRowConstraints().add(new RowConstraints(height));
        grid.add(labelxy, 0, 0);
    }

    public void addColumnsLabels(){
        for (int i = 1; i <= maxX - minX + 1; i++){
            Label label = new Label(Integer.toString(minX + i -1));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getColumnConstraints().add(new ColumnConstraints(width));
            grid.add(label, i, 0);
        }
    }
    public void addRowsLabels(){
        for (int i =1 ; i <=  maxY - minY + 1; i++){
            Label label = new Label(Integer.toString(maxY-i+1));
            GridPane.setHalignment(label, HPos.CENTER);
            grid.getRowConstraints().add(new RowConstraints(height));
            grid.add(label, 0,i);
        }
    }

    public void addObjects(){
        for (int x=minX;x<=maxX;x++){
            for (int y=minY;y<=maxY;y++){
                Vector2d position = new Vector2d(x,y);
                IMapElement object=  map.objectAt(position);
                if (object!=null){
                    VBox box=new GuiElementBox(object).getVBox();
                    grid.add(box, position.x - minX + 1, maxY - position.y + 1);
                    GridPane.setHalignment(box, HPos.CENTER);
                }
            }
        }
    }

    public void renderGrid(){
        updateBounds();
        grid.getChildren().clear();
        grid.setGridLinesVisible(true);
        addLabelXY();
        addColumnsLabels();
        addRowsLabels();
        addObjects();

    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Platform.runLater(() -> {renderGrid();});
    }

    public void start(Stage primaryStage){
        renderGrid();
        Scene currentScene=new Scene(grid, (20)*width, (20)*height);
        primaryStage.setScene(currentScene);
        primaryStage.show();
//        Thread engineThread = new Thread(new Runnable(){
//            @Override
//            public void run()
//            {
//                for(int i = 0; i < engine.getDirectionLength(); i++)
//                {
//                    try {
//                        Thread.sleep(moveDelay);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Platform.runLater(engine);
//                }
//            }
//        }
//        );
//
//        engineThread.setDaemon(true);
//        engineThread.start();
    }
}
