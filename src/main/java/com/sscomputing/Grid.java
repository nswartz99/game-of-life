package com.sscomputing;

public interface Grid<cellType> {
    int getRowCount();
    int getColumnCount();
    cellType getCell(int i, int j);
    void setCell(int i, int j, cellType v);
    Grid<cellType> getNewEmptyGrid(int i, int j);
    Grid<cellType> getNewEmptyGrid();
    boolean isRowEmpty(int i);
    boolean isColumnEmpty(int j);
    void copyFromGrid(int rowOffset, int colOffset, Grid<cellType> old);
    String toString();
    String toCompactString();
    cellType[][] toCellTypeArray();
}
