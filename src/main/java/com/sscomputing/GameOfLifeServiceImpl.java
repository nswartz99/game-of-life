package com.sscomputing;

import javax.jws.WebService;

@WebService(serviceName = "GameOfLifeService", portName = "GameOfLife", name = "GameOfLife", endpointInterface = "com.sscomputing.GameOfLifeService",
    targetNamespace = "http://sscomputing.com/game-of-life/GameOfLife")
public class GameOfLifeServiceImpl implements GameOfLifeService {

    @Override
    public String sayHello() {
        return "Hello Game";
    }
    
}
