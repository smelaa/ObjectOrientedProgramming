package agh.ics.oop;

import java.util.Arrays;
import java.util.Objects;

public class OptionsParser {
    public static MoveDirection[] parse(String[] directions){

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


        MoveDirection[] result = new MoveDirection[directions.length];
        int idx=0;
        for(int i=0;i<directions.length;i++){
            switch(directions[i]){
                case "f","forward" -> {result[idx] = MoveDirection.FORWARD; idx++;}
                case "b", "backword" -> {result[idx]=MoveDirection.BACKWARD; idx++;}
                case "r", "right" -> {result[idx]=MoveDirection.RIGHT; idx++;}
                case "l","left" -> {result[idx]=MoveDirection.LEFT; idx++;}
                default -> {continue;}
            }
        }
        result=Arrays.copyOfRange(result,0,idx);
        return result;
    }
}
