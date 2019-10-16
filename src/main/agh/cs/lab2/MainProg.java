package agh.cs.lab2;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;

import static java.lang.System.out;

public class MainProg {
    public static void main(String[] args) {
        Animal animal = new Animal();
        out.println(animal);
        OptionsParser op = new OptionsParser();
        MoveDirection[] moved = op.parse(args);
        for (MoveDirection dir : moved) {
            animal.move(dir);
            out.println(animal);
        }
    }
}
