package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App extends Application implements IPositionChangeObserver{
    private AbstractWorldMap map;
    private GridPane grid = new GridPane();

    private VBox box=new VBox(addStartButton(), grid);
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private static int height=50;
    private static int width=50;
    private SimulationEngine engine;

    @Override
    public void init() throws Exception{
        try {
            String[] args = getParameters().getRaw().toArray(new String[0]);
            map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            engine = new SimulationEngine( map, positions, this);
            engine.setMoveDelay(300);
        }
        catch (IllegalArgumentException exception){
            exception.printStackTrace();
        }
    }
    
    public void runEngine(String args){
        ArrayList<MoveDirection> directions = new OptionsParser().parse(args.split(" "));
        engine.setDirections(directions);
        Thread engineThread = new Thread(engine);
        engineThread.start();
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

    public VBox addStartButton(){
        TextField textField = new TextField();
        Button button = new Button("START");
        button.setOnAction(action -> {
            String givenArgs= textField.getText();
            runEngine(givenArgs);
        });
        return new VBox(textField, button);
    }


    public void renderGrid(){
        updateBounds();
        grid.setGridLinesVisible(false);
        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        grid.getRowConstraints().clear();
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
        Scene currentScene=new Scene(box, 20*width, 20*height);
        primaryStage.setScene(currentScene);
        primaryStage.show();
//
    }
}
