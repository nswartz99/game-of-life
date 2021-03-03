package com.sscomputing;

import javax.jws.WebService;

@WebService(serviceName = "GameOfLifeService", portName = "GameOfLife", name = "GameOfLife", endpointInterface = "com.sscomputing.GameOfLifeService",
    targetNamespace = "http://sscomputing.com")
public class GameOfLifeServiceImpl implements GameOfLifeService {

    @Override
    public String sayHello() {
        System.out.println("Saying hello!");
        return "Hello Game";
    }

    @Override
    public Boolean[][] iterate(Boolean[][] g) {
        if (g == null) return new Boolean[0][0];
        long start = System.currentTimeMillis();
         System.out.println("Grid:" + g[0][0] + "," + g[1][1]);
        Grid<Boolean> grid = new CompactGrid(new BooleanGrid(g));
        Generation main = new Generation();
        grid = main.advance(grid);
        System.out.println("Time for request is " + (System.currentTimeMillis() - start));
        System.out.println(grid.toString());
        return grid.toCellTypeArray();
    }

    @Override
    public String iterateCompact(String g) {
        if (g == null || g.length() == 0) return "";
        long start = System.currentTimeMillis();
//        System.out.println("Input is " + g);
        Grid<Boolean> grid = new CompactGrid(BooleanGrid.fromString(g));
        Generation main = new Generation();
        grid = main.advance(grid);
//        System.out.println("Grid is " + grid.toCompactString());
        System.out.println("Time for request is " + (System.currentTimeMillis() - start));
        return grid.toCompactString();
    }
    
}
