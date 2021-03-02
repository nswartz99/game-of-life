package com.sscomputing;

import java.util.stream.IntStream;

public class GameOfLifeMain {
    private BooleanGrid start;
    private Grid<Boolean> current;
    private int iterations = 0;
    private Generation gen = new Generation();

    public void setStart(Boolean[][] init) {
        start = new BooleanGrid(init);
        current = new CompactGrid(start);
    }
    public Grid<Boolean> getCurrent() {
        return current;
    }
    public void iterate() {
        iterate(1);
    }
    public void iterate(int i) {
        IntStream.range(0, i).forEach( iter -> {
            current = gen.advance(current);
            iterations++;
        });
    }
    public int getIterations() {
        return iterations;
    }
}
