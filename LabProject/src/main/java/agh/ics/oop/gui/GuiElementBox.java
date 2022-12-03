package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox {
    final static int SIZE = 30;
    private ImageView image;
    private VBox verticalBox;
    public GuiElementBox(IMapElement element){
        try{
            Image image = new Image(new FileInputStream(element.getImageName()));
            this.image = new ImageView(image);
            this.image.setFitWidth(SIZE);
            this.image.setFitHeight(SIZE);
        }
        catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }

        Label Position = new Label(element.getPosition().toString());
        this.verticalBox = new VBox(this.image, Position);
        this.verticalBox.setAlignment(Pos.CENTER);
    }

    public VBox getVBox(){
        return this.verticalBox;
    }
}
