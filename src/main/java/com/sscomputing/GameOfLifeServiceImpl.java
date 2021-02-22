package com.sscomputing;

import javax.jws.WebService;

@WebService(serviceName = "GameOfLifeService", portName = "GameOfLife", name = "GameOfLife", endpointInterface = "com.sscomputing.GameOfLifeService",
    targetNamespace = "http://sscomputing.com/game-of-life/GameOfLife")
public class GameOfLifeServiceImpl implements GameOfLifeService {

    @Override
    public String sayHello() {
        System.out.println("Saying hello!");
        return "Hello Game";
    }

    @Override
    public Boolean[][] iterate(Boolean[][] g) {
        System.out.println("Grid:" + g[0][0] + "," + g[1][0]);
        Grid grid = new CompactGrid(new BooleanGrid(g));
        Generation main = new Generation();
        grid = main.advance(grid);
        return grid.toBooleanArray();
    }

    @Override
    public Boolean[][] sayBooleanArray() {
        Boolean[][] g = new Boolean[3][3];
        g[0][1] = true; g[1][1] = true; g[2][1] = true; 
        g[0][0] = false;
        return g;
    }
    
}
