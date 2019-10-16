package agh.cs.lab2;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;

import static java.lang.System.out;

public class MainProg {
    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1, 2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2, 1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        MapDirection md = MapDirection.EAST;
        out.println(md);
        out.println(md.next());
        out.println(md.toUnitVector());
        out.println("lab3");
        Animal car = new Animal();
        car.move(MoveDirection.FORWARD);
        out.println(car);
        car.move(MoveDirection.LEFT);
        out.println(car);
        car.move(MoveDirection.FORWARD);
        out.println(car);
        car.move(MoveDirection.LEFT);
        out.println(car);
        OptionsParser op = new OptionsParser();
        MoveDirection[] moved = op.parse(new String[]{"f", "backward", "x"});
        for (MoveDirection dir : moved) {
            out.println(dir);
        }
    }
}
