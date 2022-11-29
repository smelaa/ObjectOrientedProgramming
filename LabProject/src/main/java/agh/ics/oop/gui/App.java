package agh.ics.oop.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    public void start(Stage primaryStage){
        GridPane grid = new GridPane();
        grid.add(new Label("malpka"),0,0);
        grid.add(new Label("chomik"),1,1);
        grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
