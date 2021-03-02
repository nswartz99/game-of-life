package com.sscomputing;

public class Generation {
    public Grid<Boolean> advance(Grid<Boolean> input) {
        Grid<Boolean> current = input;
        int offset = 0;
        int padding = 0;
        if (! current.isRowEmpty(0)) offset++;
        if (offset == 0 && ! current.isColumnEmpty(0)) offset++;
        if (! current.isRowEmpty(current.getRowCount()-1)) padding++;
        if (padding == 0 && ! current.isColumnEmpty(current.getColumnCount()-1)) padding++;
        if (offset > 0 || padding > 0) {
            current = current.getNewEmptyGrid(current.getRowCount() + offset + padding, current.getColumnCount() + offset + padding);
            current.copyFromGrid(offset, offset, input);
        }
        Grid<Boolean> next = current.getNewEmptyGrid(current.getRowCount(), current.getColumnCount());
        for (int i = 0; i < current.getRowCount(); i++) {
            for (int j = 0; j < current.getColumnCount(); j++) {
//                System.out.println("Offset:" + offset + " padding:" + padding + " i:" + i + " j:" + j + " current:" + current.getCell(i, j));
                int sum = 0;
                int lowerJ = j > 0 ? j-1 : j;
                int upperJ = j < current.getColumnCount() - 1 ? j + 1 : j;
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
                    if (sum == 3 || sum == 2) next.setCell(i, j, true);
                } else {
                    if (sum == 3) next.setCell(i, j, true);
                }
//                System.out.println("Offset:" + offset + " padding:" + padding + " i:" + i + " j:" + j + " upperJ:" + upperJ + " lowerJ:" + lowerJ + " current:" + current.getCell(i, j) + " next:" + next.getCell(i, j) + " sum:" + sum);
            }
        }
        return next;
    }
}
