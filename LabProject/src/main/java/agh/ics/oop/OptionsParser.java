package agh.ics.oop;

public class OptionsParser {
    public static MoveDirection[] parse(String[] directions){
        int cnt=0;
        for(int i=0;i<directions.length;i++){
            switch(directions[i]){
                    case "f","b","r","l","forward","backword","right","left" -> cnt++;
            }
        }
        MoveDirection[] result = new MoveDirection[cnt];
        int curr_cnt=0;
        for(int i=0;i<directions.length;i++){
            switch(directions[i]){
                case "f","forward" -> result[i-curr_cnt]=MoveDirection.FORWARD;
                case "b", "backword" -> result[i-curr_cnt]=MoveDirection.BACKWARD;
                case "r", "right" -> result[i-curr_cnt]=MoveDirection.RIGHT;
                case "l","left" -> result[i-curr_cnt]=MoveDirection.LEFT;
                default -> curr_cnt++;
            }
        }
        return result;
    }
}
