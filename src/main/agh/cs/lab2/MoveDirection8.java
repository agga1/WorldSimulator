package agh.cs.lab2;

public enum MoveDirection8 {
    UP(0),
    UPRIGHT(1),
    RIGHT(2),
    RIGHTDOWN(3),
    DOWN(4),
    DOWNLEFT(5),
    LEFT(6),
    LEFTUP(7);

    private int value;
    private MoveDirection8(int value){
        this.value = value;
    }




}
