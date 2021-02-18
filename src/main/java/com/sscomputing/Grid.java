package com.sscomputing;

public interface Grid {
    int getRowCount();
    int getColumnCount();
    boolean getCell(int i, int j);
    void setCell(int i, int j);
    void clearCell(int i, int j);
    void setCell(int i, int j, boolean b);
    Grid getNewEmptyGrid(int i, int j);
    Grid getNewEmptyGrid();
}
