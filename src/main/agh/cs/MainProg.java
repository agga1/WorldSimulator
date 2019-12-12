package agh.cs;

import agh.cs.map.WorldMap;

import java.util.stream.IntStream;

public class MainProg {
    public static void main(String[] args) throws InterruptedException {
        WorldMap map = new WorldMap();
        IntStream.range(0, 15).forEach(i -> {
            try {
                map.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
