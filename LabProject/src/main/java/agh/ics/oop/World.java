package agh.ics.oop;

public class World {
    public static void run (Direction[] directions){
//        for(int i=0; i<directions.length; i++){
//            if (i>0){
//                System.out.print(", ");
//            }
//            System.out.print(directions[i]);
//        }
//        String joinedDirections = String.join(", ", directions);
//        System.out.println(joinedDirections);
        for (Direction direction: directions){
//            switch (direction){
//                case "f" -> System.out.println("Zwierzak idzie do przodu");
//                case "b" -> System.out.println("Zwierzak idzie do tyłu");
//                case "l" -> System.out.println("Zwierzak idzie w lewo");
//                case "r" -> System.out.println("Zwierzak idzie w prawo");
//            }
            String text = switch(direction){
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tyłu";
                case LEFT -> "Zwierzak idzie w lewo";
                case RIGHT -> "Zwierzak idzie w prawo";
                default -> null;
            };
            System.out.println(text);
        }

    }
    public static Direction[] translate (String[] args){
        Direction[] directions = new Direction[args.length];
        for (int i=0;i<args.length; i++){
            directions[i]= switch(args[i]){
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "l" -> Direction.LEFT;
                case "r" -> Direction.RIGHT;
                default -> null;
            };
        }
        return directions;
    }
    public static void main(String[] args) {
        Direction[] directions = translate(args);
        System.out.println("System startuje");
        run(directions);
        System.out.println("System zakonczyl dzialanie");
    }

}
