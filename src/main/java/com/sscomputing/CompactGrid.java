package com.sscomputing;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.stream.IntStream;

public class CompactGrid extends GridBase<Boolean> implements Grid<Boolean> {
    private List<BitSet> grid = new ArrayList<>();
    private int columns = 0;

    public CompactGrid(BooleanGrid init) {
        columns = init.getColumnCount();
        for (int i=0; i < init.getRowCount(); i++) {
            BitSet bs = new BitSet(columns);
            grid.add(bs);
            for (int j=0; j < columns; j++) {
                Boolean v = init.getCell(i, j);
                bs.set(j, v != null ? v.booleanValue() : getDefaultValue());
            }
        }
    }
    public CompactGrid(int i, int j) {
        columns = j;
        IntStream.range(0, i).forEach( jj -> grid.add(new BitSet(j)));
    }
    public List<BitSet> getGrid() {
        return grid;
    }
    public BitSet getRow(int i) {
        return grid.get(i);
    }
    @Override
    public boolean isRowEmpty(int i) {
        if (grid.get(i).nextSetBit(0) < 0) return true;
        return false;
    }
    @Override
    public Boolean getCell(int i, int j) {
        return grid.get(i).get(j);
    }
    @Override
    public int getRowCount() {
        return grid.size();
    }
    @Override
    public int getColumnCount() {
        return columns;
    }

    @Override
    public void setCell(int i, int j, Boolean b) {
        grid.get(i).set(j, b);
    }

    @Override
    public Grid<Boolean> getNewEmptyGrid(int i, int j) {
        return new CompactGrid(i, j);
    }

    @Override
    public Grid<Boolean> getNewEmptyGrid() {
        return getNewEmptyGrid(getRowCount(), getColumnCount());
    }

    // TODO This is inefficient.
    @Override
    public void copyFromGrid(int rowOffset, int colOffset, Grid<Boolean> old) {
        for (int i=0; i < old.getRowCount(); i++) {
            for (int j=0; j < old.getColumnCount(); j++) {
                if (old.getCell(i, j)) grid.get(i+rowOffset).set(j+colOffset);
            }
        }
    }

    @Override
    public String toCompactString() {
        StringBuilder s = new StringBuilder();
        for (int i=0; i < this.getRowCount(); i++) {
            for (int j=0; j < this.getColumnCount(); j++) {
                s.append(this.getCell(i, j) ? "1 " : "0 ");
            }
            if (i < this.getRowCount()-1) s.append(",");
        }
        return s.toString().replaceAll(" ,", ",");
    }

    @Override
    protected Boolean[][] getNewEmptyArray(int i, int j) {
        return new Boolean[i][j];
    }

    @Override
    public Boolean getDefaultValue() {
        return false;
    }

    @Override
    public boolean isDefaultValue(Boolean v) {
        return !v;
    }
}
