package com.sscomputing;

import java.text.DecimalFormat;

public class DoubleGrid  extends GridBase<Double> implements Grid<Double> {

    public DoubleGrid(Double grid[][]) {
        super(grid);
    }
    public DoubleGrid(int i, int j) {
        super(i, j);
    }

    @Override
    public Grid<Double> getNewEmptyGrid(int i, int j) {
        return new DoubleGrid(i, j);
    }

    @Override
    public Grid<Double> getNewEmptyGrid() {
        return getNewEmptyGrid(getRowCount(), getColumnCount());
    }

    @Override
    protected Double[][] getNewEmptyArray(int i, int j) {
        return new Double[i][j];
    }

    @Override
    public Double getDefaultValue() {
        return 0.0;
    }

    @Override
    public boolean isDefaultValue(Double v) {
        return v == 0.0;
    }

    @Override
    public String toCompactString() {
        DecimalFormat formatter = new DecimalFormat("###.#### ");
        StringBuilder s = new StringBuilder();
        for (int i=0; i < this.getRowCount(); i++) {
            for (int j=0; j < this.getColumnCount(); j++) {
                s.append(formatter.format(getCell(i, j)));
            }
            if (i < this.getRowCount()-1) s.append(",");
        }
        return s.toString();
    }

}
