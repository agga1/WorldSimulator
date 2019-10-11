package cs.lab3;

import cs.lab2.MoveDirection;

public class OptionsParser {
    public MoveDirection[] parse(String ar[]){
        MoveDirection md[] = new MoveDirection[ar.length];
        int i = 0;
        for(String c:ar){
            switch (c){
                case "f":
                case "forward":
                    md[i++]=MoveDirection.FORWARD;
                    break;
                case "b":
                case "backward":
                    md[i++]=MoveDirection.BACKWARD;
                    break;
                case "r":
                case "right":
                    md[i++]=MoveDirection.RIGHT;
                    break;
                case "l":
                case "left":
                    md[i++]=MoveDirection.LEFT;
            }
        }
        return md;
    }
}
