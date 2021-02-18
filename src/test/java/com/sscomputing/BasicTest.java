package com.sscomputing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BasicTest {
    private Boolean[][] start = new Boolean[3][3];
    private GameOfLifeMain main = new GameOfLifeMain();

    @Before
    public void before() {
        blinkerInit();
    }
    private void blinkerInit() {
        start = new Boolean[3][3];
        start[0][1] = true; start[1][1] = true; start[2][1] = true;
        main.setStart(start);
    }
    private void rPentominoInit() {
        start = new Boolean[3][3];
        start[0][1] = true; start[0][2] = true; start[1][0] = true; start[1][1] = true; start[2][1] = true;
        main.setStart(start);
    }

    @Test
    public void basicSmallGrid() {
        Grid grid = new BooleanGrid(start);
        assertFalse("0,0", grid.getCell(0, 0));
        assertTrue("0,1", grid.getCell(0, 1));
        assertFalse("0,2", grid.getCell(0, 2));
        assertFalse("1,0", grid.getCell(1, 0));
        assertTrue("1,1", grid.getCell(1, 1));
        assertFalse("1,2", grid.getCell(1, 2));
        assertFalse("2,0", grid.getCell(2, 0));
        assertTrue("2,1", grid.getCell(2, 1));
        assertFalse("2,2", grid.getCell(2, 2));
    }
    @Test
    public void basicCompactGrid() {
        Grid grid = new CompactGrid(new BooleanGrid(start));
        assertFalse("0,0", grid.getCell(0, 0));
        assertTrue("0,1", grid.getCell(0, 1));
        assertFalse("0,2", grid.getCell(0, 2));
        assertFalse("1,0", grid.getCell(1, 0));
        assertTrue("1,1", grid.getCell(1, 1));
        assertFalse("1,2", grid.getCell(1, 2));
        assertFalse("2,0", grid.getCell(2, 0));
        assertTrue("2,1", grid.getCell(2, 1));
        assertFalse("2,2", grid.getCell(2, 2));
    }
    @Test
    public void basicIterate() {
        assertEquals("Rows not correct", 3, main.getCurrent().getRowCount());
        assertEquals("Columns not correct", 3, main.getCurrent().getColumnCount());
        main.iterate();
        Grid grid = main.getCurrent();
        // The Iterate will expand the grid because there are populated cells in the first and last rows.
        assertEquals("Rows not correct", 5, main.getCurrent().getRowCount());
        assertEquals("Columns not correct", 5, main.getCurrent().getColumnCount());
        // After expanding, it SHOULD move the cells down by one to avoid populated cells on the edge
        assertFalse("0,0", grid.getCell(0, 0));
        assertFalse("0,1", grid.getCell(0, 1));
        assertFalse("0,2", grid.getCell(0, 2));
        assertFalse("0,3", grid.getCell(0, 3));
        assertFalse("0,4", grid.getCell(0, 4));
        assertFalse("1,0", grid.getCell(1, 0));
        assertFalse("1,1", grid.getCell(1, 1));
        assertFalse("1,2", grid.getCell(1, 2));
        assertFalse("1,3", grid.getCell(1, 3));
        assertFalse("1,4", grid.getCell(1, 4));
        assertFalse("2,0", grid.getCell(2, 0));
        assertTrue("2,1", grid.getCell(2, 1));
        assertTrue("2,2", grid.getCell(2, 2));
        assertTrue("2,3", grid.getCell(2, 3));
        assertFalse("2,4", grid.getCell(2, 4));
        assertFalse("3,0", grid.getCell(3, 0));
        assertFalse("3,1", grid.getCell(3, 1));
        assertFalse("3,2", grid.getCell(3, 2));
        assertFalse("3,3", grid.getCell(3, 3));
        assertFalse("3,4", grid.getCell(3, 4));
        assertFalse("4,0", grid.getCell(4, 0));
        assertFalse("4,1", grid.getCell(4, 1));
        assertFalse("4,2", grid.getCell(4, 2));
        assertFalse("4,3", grid.getCell(4, 3));
        assertFalse("4,4", grid.getCell(4, 4));
        main.iterate();        
        grid = main.getCurrent();
        // The Iterate will expand the grid because there is a populated cell in the first column
        assertEquals("Rows not correct", 5, main.getCurrent().getRowCount());
        assertEquals("Columns not correct", 5, main.getCurrent().getColumnCount());
        assertFalse("1,1", grid.getCell(1, 1));
        assertTrue("1,2", grid.getCell(1, 2));
        assertFalse("1,3", grid.getCell(1, 3));
        assertFalse("2,1", grid.getCell(2, 1));
        assertTrue("2,2", grid.getCell(2, 2));
        assertFalse("2,3", grid.getCell(2, 3));
        assertFalse("3,1", grid.getCell(3, 1));
        assertTrue("3,2", grid.getCell(3, 2));
        assertFalse("3,3", grid.getCell(3, 3));
    }
    @Test
    public void printit() {
        main.iterate(100);
        Grid grid = main.getCurrent();
        printGrid(grid);
        main.iterate();
        grid = main.getCurrent();
        printGrid(grid);
    }

    @Test
    public void rPentomnio() {
        rPentominoInit();
        main.iterate(50);
        Grid grid = main.getCurrent();
        printGrid(grid);
        main.iterate(50);
        grid = main.getCurrent();
        printGrid(grid);
        main.iterate(50);
        grid = main.getCurrent();
        printGrid(grid);
    }

    private void printGrid(Grid g) {
        for (int i=0; i < g.getRowCount(); i++) {
            for (int j=0; j < g.getColumnCount(); j++) {
                System.out.print(g.getCell(i, j) ? "X" : "_");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
