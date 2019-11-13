package agh.cs.lab2;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.RectangularMap;
import agh.cs.lab5.GrassField;
import agh.cs.lab7.IPositionChangeObserver;

import static java.lang.System.out;

public class MainProg {
    public static void main(String[] args) {
        try{
            MoveDirection[] directions = new OptionsParser().parse(args);
            IWorldMap map = new RectangularMap(10, 5);
            Animal animal1 = new Animal(map,new Vector2d(2,2));
            Animal animal2= new Animal(map,new Vector2d(4,3));

            map.place(animal1);
            map.place(animal2);
            animal1.move(MoveDirection.LEFT);
            animal1.move(MoveDirection.FORWARD);
            out.println(map);
            MoveDirection[] md = new OptionsParser().parse(new String[]{"f", "b", "r", "l"});

//            out.println(map);

            IWorldMap unbMap = new GrassField(4);
            unbMap.place(new Animal(unbMap,new Vector2d(2,2)));
            unbMap.place(new Animal(unbMap,new Vector2d(5,10)));

//            unbMap.place(new Animal(unbMap,new Vector2d(2,2)));
            out.println(unbMap);

        }catch(IllegalArgumentException e){
            out.println(e);
        }

    }
}
