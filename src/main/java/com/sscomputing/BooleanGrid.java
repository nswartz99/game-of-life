package com.sscomputing;

public class BooleanGrid extends GridBase<Boolean> implements Grid<Boolean> {
    public BooleanGrid(Boolean grid[][]) {
        super(grid);
    }
    public BooleanGrid(int i, int j) {
        super(i, j);
    }

    @Override
    public Grid<Boolean> getNewEmptyGrid(int i, int j) {
        return new BooleanGrid(i, j);
    }

    @Override
    public Grid<Boolean> getNewEmptyGrid() {
        return getNewEmptyGrid(getRowCount(), getColumnCount());
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
                s.append(getCell(i, j) ? "1" : "0");
            }
            if (i < this.getRowCount()-1) s.append(",");
        }
        return s.toString();
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
