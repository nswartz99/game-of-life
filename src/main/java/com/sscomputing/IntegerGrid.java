package com.sscomputing;

public class IntegerGrid extends GridBase<Integer> implements Grid<Integer> {
    public IntegerGrid(Integer grid[][]) {
        super(grid);
    }
    public IntegerGrid(int i, int j) {
        super(i, j);
    }

    @Override
    public Grid<Integer> getNewEmptyGrid(int i, int j) {
        return new IntegerGrid(i, j);
    }

    @Override
    public Grid<Integer> getNewEmptyGrid() {
        return getNewEmptyGrid(getRowCount(), getColumnCount());
    }

    @Override
    protected Integer[][] getNewEmptyArray(int i, int j) {
        return new Integer[i][j];
    }

    @Override
    public Integer getDefaultValue() {
        return 0;
    }

    @Override
    public boolean isDefaultValue(Integer v) {
        return v == 0;
    }

    @Override
    public String toCompactString() {
        StringBuilder s = new StringBuilder();
        for (int i=0; i < this.getRowCount(); i++) {
            for (int j=0; j < this.getColumnCount(); j++) {
                s.append(getCell(i, j));
                s.append(" ");
            }
            if (i < this.getRowCount()-1) s.append(",");
        }
        return s.toString();
    }
}
