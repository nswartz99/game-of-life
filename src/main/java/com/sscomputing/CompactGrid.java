package com.sscomputing;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.stream.IntStream;

public class CompactGrid implements Grid {
    private List<BitSet> grid = new ArrayList<>();
    private int rows = 0;
    private int columns = 0;

    public CompactGrid(BooleanGrid init) {
        columns = init.getRow(0).getCells().size();
        for (BooleanGrid.Row row : init.getRows()) {
            BitSet bs = new BitSet(row.getCells().size());
            grid.add(bs);
            IntStream.range(0, row.getCells().size()).forEach(i -> bs.set(i, row.getCell(i)));
            rows++;
        }
    }
    public CompactGrid(int i, int j) {
        IntStream.range(0, i).forEach( jj -> grid.add(new BitSet(j)));
        rows = i;
        columns = j;
    }
    public List<BitSet> getGrid() {
        return grid;
    }
    public BitSet getRow(int i) {
        return grid.get(i);
    }
    @Override
    public boolean getCell(int i, int j) {
        return grid.get(i).get(j);
    }
    @Override
    public int getRowCount() {
        return rows;
    }
    @Override
    public int getColumnCount() {
        return columns;
    }

    @Override
    public void setCell(int i, int j) {
        grid.get(i).set(j);
    }

    @Override
    public void clearCell(int i, int j) {
        grid.get(i).clear(j);
    }

    @Override
    public void setCell(int i, int j, boolean b) {
        grid.get(i).set(j, b);
    }

    @Override
    public Grid getNewEmptyGrid(int i, int j) {
        return new CompactGrid(i, j);
    }

    @Override
    public Grid getNewEmptyGrid() {
        return getNewEmptyGrid(getRowCount(), getColumnCount());
    }
}
