package com.sscomputing;

import java.util.stream.IntStream;

public class Generation {
    public Grid advance(Grid current) {
        Grid next = current.getNewEmptyGrid(getDimension(current), getDimension(current));
        for (int i = 0; i < current.getRowCount(); i++) {
            for (int j = 0; j < current.getColumnCount(); j++) {
                int sum = 0;
                int lowerJ = j > 0 ? j-1 : j;
                int upperJ = j < current.getColumnCount()-1 ? j+1 : j;
                if (i > 0) {
                    for (int k = lowerJ; k <= upperJ; k++) {
                        if (current.getCell(i-1, k)) sum++;
                    };
                }
                if (i < current.getRowCount()-1) {
                    for (int k = lowerJ; k <= upperJ; k++) {
                        if (current.getCell(i+1, k)) sum++;
                    }
                }
                if (j > lowerJ && current.getCell(i, j-1)) sum++;
                if (j < upperJ && current.getCell(i, j+1)) sum++;
                if (current.getCell(i, j)) {
                    if (sum == 3 || sum == 2) next.setCell(i, j);
                } else {
                    if (sum == 3) next.setCell(i, j);
                }
            }
        }
        return next;
    }

    private int getDimension(Grid g) {
        int adder = 0;
        for (int i=0; i < g.getColumnCount(); i++) {
            if (g.getCell(0, i)) adder++;
            if (g.getCell(g.getRowCount()-1, i)) adder++;
        }
        for (int i=0; i < g.getRowCount(); i++) {
            if (g.getCell(i, 0)) adder++;
            if (g.getCell(i, g.getColumnCount()-1)) adder++;
        }
        return g.getRowCount() + adder;
    }
}
