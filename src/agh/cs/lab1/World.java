package agh.cs.lab1;
import java.util.Arrays;

import static java.lang.System.out;
public class World {
    public static void main(String[] args) {
        System.out.println("Start systemu");
        Direction[] dirs =toEnum(args);
        run(dirs);
        System.out.println("Zakończenie systemu");
    }
    static Direction[] toEnum(String[] ar){
        Direction[] ans = new Direction[ar.length];
        int i=0;
        for(String str : ar){
            switch(str){
                case "f":
                    ans[i++]= Direction.FORWARD;
                    break;
                case "b":
                    ans[i++]= Direction.BACKWARD;
                    break;
                case "r":
                    ans[i++]= Direction.RIGHT;
                    break;
                case "l":
                    ans[i++]= Direction.LEFT;
                    break;
            }
        }

        return  Arrays.copyOfRange(ans, 0, i );
    }
    static void run(Direction[] dirs){
        out.println("start");
        for(Direction dir : dirs){
            switch(dir){
                case FORWARD:
                    out.println("zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    out.println("zwierzak idzie do przodu");
                    break;
                case RIGHT:
                    out.println("zwierzak skręca w prawo");
                    break;
                case LEFT:
                    out.println("zwierzak skręca w lewo");
                    break;
            }
        }
        /* for run(String[] ar)
        out.println("start");
        for(String str : ar){
            switch(str){
                case "f":
                    out.println("zwierzak idzie do przodu");
                    break;
                case "b":
                    out.println("zwierzak idzie do przodu");
                    break;
                case "r":
                    out.println("zwierzak skręca w prawo");
                    break;
                case "l":
                    out.println("zwierzak skręca w lewo");
                    break;
            }
        }
        out.println("Stop");
        */
        /*
        System.out.println("zwierzak idzie do przodu");
        String ans="";
        for(String animal : ar){
            ans += animal + ", ";
        }
        ans = ans.substring(0, ans.length() -2);
        System.out.println(ans);
        */
    }
}
