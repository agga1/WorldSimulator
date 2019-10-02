package agh.cs.lab1;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

public class World {
    public static void main(String[] args) {
        System.out.println("Start systemu");
        Direction[] dirs = toEnum(args);
        run(dirs);
        System.out.println("Zakończenie systemu");
    }

    private static Direction[] toEnum(String[] args) {
        String[] res1 = Arrays.stream(args).filter(World::filterInapt).toArray(String[]::new);
        Direction[] res2 = Arrays.stream(res1).map(World::mapToEnum).toArray(Direction[]::new);
        out.println(res1.length);
        return res2;
    }

    private static Boolean filterInapt(String arg) {
        return (arg.equals("f") || arg.equals("b") || arg.equals("r") || arg.equals("l"));
    }

    private static Direction mapToEnum(String arg) {
        Direction ans = null;
        switch (arg) {
            case "f":
                ans = Direction.FORWARD;
                break;
            case "b":
                ans = Direction.BACKWARD;
                break;
            case "r":
                ans = Direction.RIGHT;
                break;
            case "l":
                ans = Direction.LEFT;
                break;
        }
        return ans;
    }
    private static void printText(Direction d){
        switch (d) {
            case FORWARD:
                out.println("zwierzak idzie do przodu");
                break;
            case BACKWARD:
                out.println("zwierzak idzie do tylu");
                break;
            case RIGHT:
                out.println("zwierzak skręca w prawo");
                break;
            case LEFT:
                out.println("zwierzak skręca w lewo");
                break;
        }
    }
    private static void run(Direction[] dirs) {
        Stream<Direction> res1 = Arrays.stream(dirs);
        out.println("start");
        res1.forEach(World::printText);
        out.println("end");
    }
}
