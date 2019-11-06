package agh.cs.lab2;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualizer;
import agh.cs.lab4.RectangularMap;
import agh.cs.lab5.Rock;
import agh.cs.lab5.UnboundedMap;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class MainProg {
    public static void main(String[] args) {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
//        out.println(map.place(new Animal(map)));
//        out.println(map.place(new Animal(map,new Vector2d(3,4))));
//        out.println(map.place(new Animal(map,new Vector2d(4,4))));
        out.println(map.place(new Animal(map,new Vector2d(2,2))));
        out.println(map.place(new Animal(map,new Vector2d(3,2))));
        MoveDirection[] md = new MoveDirection[2];
        md[0] = MoveDirection.FORWARD;
        md[1] = MoveDirection.FORWARD;

        map.run(md);
//        map.run(directions);
        out.println(map);

        // unbounded map
        List<Rock> rocks = new ArrayList<>();
        rocks.add(new Rock(new Vector2d(-4,4)));
        rocks.add(new Rock(new Vector2d(7,7)));
        rocks.add(new Rock(new Vector2d(3,6)));
        rocks.add(new Rock(new Vector2d(2,0)));

        IWorldMap unbMap = new UnboundedMap(rocks);
    }
}
