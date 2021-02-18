package com.sscomputing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BooleanGrid implements Grid {
    public class Row {
        private List<Boolean> cells = new ArrayList<>();

        public List<Boolean> getCells() {
            return cells;
        }
        public Boolean getCell(int i) {
            return cells.get(i);
        }
        public void setCell(int i) {
            cells.set(i, true);
        }
        public void setCell(int i, boolean b) {
            cells.set(i, b);
        }
        public void clearCell(int i) {
            cells.set(i, false);
        }
        public int getCellCount() {
            return cells.size();
        }
    }

    private List<Row> rows = new ArrayList<>();

    public BooleanGrid(Boolean grid[][]) {
        for (Boolean[] bRow : grid) {
            Row r = new Row();
            rows.add(r);
            for (Boolean b : bRow) {
                r.getCells().add(b != null ? b : false);
            }
        }
    }
    public BooleanGrid(int i, int j) {
        for (int k=0; k < i; i++) {
            Row row = new Row();
            rows.add(row);
            for (int m=0; m < j; m++) {
                row.clearCell(m);
            }
        }
    }

    public List<Row> getRows() {
        return rows;
    }
    public Row getRow(int i) {
        return rows.get(i);
    }
    @Override
    public boolean getCell(int i, int j) {
        return rows.get(i).getCell(j);
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return rows.get(0).getCellCount();
    }

    @Override
    public void setCell(int i, int j) {
        rows.get(i).setCell(j);
    }

    @Override
    public void clearCell(int i, int j) {
        rows.get(i).clearCell(j);
    }

    @Override
    public void setCell(int i, int j, boolean b) {
        rows.get(i).setCell(j, b);
    }

    @Override
    public Grid getNewEmptyGrid(int i, int j) {
        return new BooleanGrid(i, j);
    }

    @Override
    public Grid getNewEmptyGrid() {
        return getNewEmptyGrid(getRowCount(), getColumnCount());
    }

    @Override
    public boolean isRowEmpty(int i) {
        for (Boolean cell : rows.get(i).cells) {
            if (cell) return true;
        }
        return false;
    }

    @Override
    public boolean isColumnEmpty(int j) {
        for (Row r : rows) {
            if (r.getCell(j)) return true;
        }
        return false;
    }
    @Override
    public void copyFromGrid(int rowOffset, int colOffset, Grid old) {
        for (int i=0; i < old.getRowCount(); i++) {
            for (int j=0; j < old.getColumnCount(); j++) {
                if (old.getCell(i, j)) rows.get(i+rowOffset).setCell(j+colOffset);
            }
        }
    }
}
