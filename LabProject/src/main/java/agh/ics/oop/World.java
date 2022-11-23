package agh.ics.oop;

public class World {

    public static void main(String[] args) {
//        Animal timon = new Animal();
//        System.out.println(timon.getLocation());
//        timon.move(MoveDirection.RIGHT);
//        timon.move(MoveDirection.FORWARD);
//        timon.move(MoveDirection.FORWARD);
//        timon.move(MoveDirection.FORWARD);
//        timon.move(MoveDirection.FORWARD);
//        System.out.println(timon.toString());
//        MoveDirection[] orders=OptionsParser.parse(args);
//        Animal pumba = new Animal();
//        System.out.println(pumba);
//        for(int i=0;i<orders.length;i++){
//            pumba.move(orders[i]);
//            System.out.println(pumba);
//        }
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map);
    }
}
