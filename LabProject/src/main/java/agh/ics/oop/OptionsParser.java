package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class OptionsParser {
    public static ArrayList<MoveDirection> parse(String[] directions) throws IllegalArgumentException{

        /*
        //mamy strumień informacji i zamieniamy na tablicę
        Arrays.stream(directions)
                .map(instruction->switch(instruction){
                    case "f","forward" -> MoveDirection.FORWARD;
                    case "b", "backword" -> MoveDirection.BACKWARD;
                    case "r", "right" -> MoveDirection.RIGHT;
                    case "l","left" -> MoveDirection.LEFT;
                    default -> null;
                })
                .filter(Objects::nonNull)
                .toArray(MoveDirection[]::new);
         */


        ArrayList<MoveDirection> result = new ArrayList<>();
        for(int i=0;i<directions.length;i++){
            switch(directions[i]){
                case "f","forward" -> result.add(MoveDirection.FORWARD);
                case "b", "backword" -> result.add(MoveDirection.BACKWARD);
                case "r", "right" -> result.add(MoveDirection.RIGHT);
                case "l","left" -> result.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(directions[i] + " is not legal move specification");
            }
        }
        return result;
    }
}
