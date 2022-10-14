package agh.ics.oop;

public class World {
    public static void run (MoveDirection[] directions){
//        for(int i=0; i<directions.length; i++){
//            if (i>0){
//                System.out.print(", ");
//            }
//            System.out.print(directions[i]);
//        }
//        String joinedDirections = String.join(", ", directions);
//        System.out.println(joinedDirections);
        for (MoveDirection direction: directions){
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
            if (text!=null) {System.out.println(text);}

        }

    }
    public static MoveDirection[] translate (String[] args){
        MoveDirection[] directions = new MoveDirection[args.length];
        for (int i=0;i<args.length; i++){
            directions[i]= switch(args[i]){
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "l" -> MoveDirection.LEFT;
                case "r" -> MoveDirection.RIGHT;
                default -> null;
            };
        }
        return directions;
    }
    public static void main(String[] args) {
        MoveDirection[] directions = translate(args);
        System.out.println("System startuje");
        run(directions);
        System.out.println("System zakonczyl dzialanie");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        MapDirection d1= MapDirection.NORTH;
        System.out.println(d1);
        MapDirection d2=d1.next();
        System.out.println(d2);
        MapDirection d3=d1.previous();
        System.out.println(d3);
        Vector2d v1=d1.toUnitVector();
        Vector2d v2=d2.toUnitVector();
        Vector2d v3=d3.toUnitVector();
        System.out.println(v1);
        System.out.println(v2);
        System.out.println(v3);
    }

}
