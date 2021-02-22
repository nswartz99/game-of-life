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
    public boolean isRowEmpty(int i) {
        if (grid.get(i).nextSetBit(0) < 0) return true;
        return false;
    }
    @Override
    public boolean isColumnEmpty(int j) {
        for (BitSet bs : grid) {
            if (bs.get(j)) return false;
        }
        return true;
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

    // TODO This is inefficient.
    @Override
    public void copyFromGrid(int rowOffset, int colOffset, Grid old) {
        for (int i=0; i < old.getRowCount(); i++) {
            for (int j=0; j < old.getColumnCount(); j++) {
                if (old.getCell(i, j)) grid.get(i+rowOffset).set(j+colOffset);
            }
        }
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i=0; i < this.getRowCount(); i++) {
            for (int j=0; j < this.getColumnCount(); j++) {
                s.append(this.getCell(i, j) ? "X" : "_");
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    @Override
    public Boolean[][] toBooleanArray() {
        Boolean[][] b = new Boolean[this.getRowCount()][this.getColumnCount()];
        for (int i=0; i < this.getRowCount(); i++) {
            for (int j=0; j < this.getColumnCount(); j++) {
                b[i][j] = this.getCell(i, j);
            }
        }
        return b;
    }
}
