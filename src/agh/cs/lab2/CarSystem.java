package cs.lab2;

import cs.lab3.Car;

import static java.lang.System.out;

public class CarSystem {
    public static void main(String[] args){
        Car car = new Car();
        out.println(car);
        car.move(MoveDirection.FORWARD);
        out.println(car);
        car.move(MoveDirection.RIGHT);
        car.move(MoveDirection.FORWARD);
        car.move(MoveDirection.FORWARD);
        car.move(MoveDirection.FORWARD);

        out.println(car);
    }
}
