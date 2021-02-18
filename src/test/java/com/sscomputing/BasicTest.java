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
        start[0][1] = true; start[1][1] = true; start[2][1] = true;
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
        assertEquals("Rows not correct", 5, main.getCurrent().getRowCount());
        assertEquals("Columns not correct", 5, main.getCurrent().getColumnCount());
        assertFalse("0,0", grid.getCell(0, 0));
        assertFalse("0,1", grid.getCell(0, 1));
        assertFalse("0,2", grid.getCell(0, 2));
        assertTrue("1,0", grid.getCell(1, 0));
        assertTrue("1,1", grid.getCell(1, 1));
        assertTrue("1,2", grid.getCell(1, 2));
        assertFalse("2,0", grid.getCell(2, 0));
        assertFalse("2,1", grid.getCell(2, 1));
        assertFalse("2,2", grid.getCell(2, 2));
        main.iterate();        
        grid = main.getCurrent();
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
}
