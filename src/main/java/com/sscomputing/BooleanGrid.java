package com.sscomputing;

import java.util.ArrayList;
import java.util.List;

public class BooleanGrid implements Grid<Boolean> {
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
    public Boolean getCell(int i, int j) {
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
    public void setCell(int i, int j, Boolean b) {
        rows.get(i).setCell(j, b);
    }

    @Override
    public Grid<Boolean> getNewEmptyGrid(int i, int j) {
        return new BooleanGrid(i, j);
    }

    @Override
    public Grid<Boolean> getNewEmptyGrid() {
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
    public void copyFromGrid(int rowOffset, int colOffset, Grid<Boolean> old) {
        for (int i=0; i < old.getRowCount(); i++) {
            for (int j=0; j < old.getColumnCount(); j++) {
                if (old.getCell(i, j)) rows.get(i+rowOffset).setCell(j+colOffset);
            }
        }
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i=0; i < this.getRowCount(); i++) {
            for (int j=0; j < this.getColumnCount(); j++) {
                s.append(rows.get(i).getCell(j) ? "X" : "_");
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
    @Override
    public Boolean[][] toCellTypeArray() {
        Boolean[][] b = new Boolean[this.getRowCount()][this.getColumnCount()];
        for (int i=0; i < this.getRowCount(); i++) {
            for (int j=0; j < this.getColumnCount(); j++) {
                b[i][j] = this.getCell(i, j);
            }
        }
        return b;
    }
    public static BooleanGrid fromString(String s) {
        String[] compactRows = s.split(",");
        Boolean[][] b = new Boolean[compactRows.length][compactRows[0].length()];
        for (int i=0; i < compactRows.length; i++) {
            for (int j=0; j < compactRows[0].length(); j++) {
                b[i][j] = (compactRows[i].charAt(j) == '1' ? true : false);
            }
        }
        return new BooleanGrid(b);
    }

    @Override
    public String toCompactString() {
        StringBuilder s = new StringBuilder();
        for (int i=0; i < this.getRowCount(); i++) {
            for (int j=0; j < this.getColumnCount(); j++) {
                s.append(rows.get(i).getCell(j) ? "1" : "0");
            }
            if (i < this.getRowCount()-1) s.append(",");
        }
        return s.toString();
    }

}
