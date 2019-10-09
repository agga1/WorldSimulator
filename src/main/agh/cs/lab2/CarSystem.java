package agh.cs.lab2;
import static java.lang.System.out;

public class CarSystem {
    public static void main(String[] args){
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
        MapDirection md = MapDirection.EAST;
        out.println(md);
        out.println(md.next());
        out.println(md.toUnitVector());

    }
}
