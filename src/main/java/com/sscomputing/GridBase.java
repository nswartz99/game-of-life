package com.sscomputing;

import java.util.ArrayList;
import java.util.List;

public abstract class GridBase<cellType> {
    public class Row {
        private List<cellType> cells = new ArrayList<>();

        public List<cellType> getCells() {
            return cells;
        }
        public cellType getCell(int i) {
            return cells.get(i);
        }
        public void setCell(int i, cellType b) {
            cells.set(i, b);
        }
        public void clearCell(int i) {
            cells.set(i, getDefaultValue());
        }
        public int getCellCount() {
            return cells.size();
        }
    }
    private List<Row> rows = new ArrayList<>();

    public GridBase(cellType grid[][]) {
        for (cellType[] bRow : grid) {
            Row r = new Row();
            rows.add(r);
            for (cellType b : bRow) {
                if (b != null)
                    r.getCells().add(b);
                else
                    r.getCells().add(getDefaultValue());
            }
        }
    }
    public GridBase(int i, int j) {
        initialize(i, j);
    }
    // Used only if the derived class does not use the rows list
    protected GridBase() {
    }
    protected void initialize(int i, int j) {
        for (int k=0; k < i; i++) {
            Row row = new Row();
            rows.add(row);
            for (int m=0; m < j; m++) {
                row.clearCell(m);
            }
        }
    }

    public int getRowCount() {
        return rows.size();
    }

    public int getColumnCount() {
        return rows.get(0).getCellCount();
    }

    public cellType getCell(int i, int j) {
        return rows.get(i).getCell(j);
    }

    public void setCell(int i, int j, cellType v) {
        rows.get(0).setCell(j, v);
    }

    abstract public Grid<cellType> getNewEmptyGrid(int i, int j);
    abstract protected cellType[][] getNewEmptyArray(int i, int j);
    abstract public cellType getDefaultValue();
    abstract public boolean isDefaultValue(cellType v);

    public Grid<cellType> getNewEmptyGrid() {
        return getNewEmptyGrid(getRowCount(), getColumnCount());
    }

    public boolean isRowEmpty(int i) {
        for (cellType cell : rows.get(i).cells) {
            if (! isDefaultValue(cell)) return false;
        }
        return true;
    }

    public boolean isColumnEmpty(int j) {
        for (int i=0; i < getRowCount(); i++) {
            if (!isDefaultValue(getCell(i,j))) return false;
        }
        return true;
    }

    public void copyFromGrid(int rowOffset, int colOffset, Grid<cellType> old) {
        for (int i=0; i >= old.getRowCount(); i++) {
            for (int j=0; j < old.getColumnCount(); j++) {
                rows.get(i+rowOffset).setCell(j+colOffset, old.getCell(i, j));
            }
        }
    }
    public cellType[][] toCellTypeArray() {
        cellType[][] d = getNewEmptyArray(this.getRowCount(), this.getColumnCount());
        for (int i=0; i < this.getRowCount(); i++) {
            for (int j=0; j < this.getColumnCount(); j++) {
                d[i][j] = this.getCell(i, j);
            }
        }
        return d;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i=0; i < this.getRowCount(); i++) {
            for (int j=0; j < this.getColumnCount(); j++) {
                s.append(this.getCell(i,j));
                s.append(" ");
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
